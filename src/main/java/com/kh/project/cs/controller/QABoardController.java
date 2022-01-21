package com.kh.project.cs.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.project.cs.model.service.QABoardService;
import com.kh.project.cs.model.vo.Answer;
import com.kh.project.cs.model.vo.QABoard;
import com.kh.project.cs.model.vo.Search;
import com.kh.project.member.model.vo.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/qaBoard")
public class QABoardController {
	
	private QABoardService qaBoardService;
	
	public QABoardController(QABoardService qaBoardService) {
		this.qaBoardService = qaBoardService;
	}
	

	@RequestMapping("list")
	public ModelAndView selectQAList(Search search, ModelAndView mv,HttpServletRequest request) {
		
		// 기본적으로 게시판은 1페이지부터 시작
		int page = 1;
				
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 페이지를 page로 적용
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}	
		
		// 페이징과 관련 된 데이터, 조회 된 boardList를 map에 담아 리턴
		Map<String, Object> map 
		= qaBoardService.selectQAList(page, search);
		
		log.info("페이지 : {} ", map.get("pi"));
		log.info("boardList : {} ",map.get("boardList"));
		
		mv.addAllObjects(map);
		mv.setViewName("cs/QABoardList"); 
		
		return mv;
	
	}
	

	/* 공개글: 모든 사람 접근 가능(비회원 포함) 비밀글: 인가 받아야만 접근 가능(+본인만) */
	/*http://localhost:8007/qaBoard?QNo=100*/
	@GetMapping("") 
	public String selectQA(@RequestParam("qno") int QNo, Model model, @AuthenticationPrincipal UserImpl user, 
			RedirectAttributes rttr, @CookieValue(value="bcount", required=false) String bcount, HttpServletResponse response) {

		/* board의 userId와 로그인 user ID 비교해서 일치해야 비밀글 확인 가능(총관리자 계정 제외) */
		
		/* board 조회해오기 */
		QABoard board = qaBoardService.selectQA(QNo);
		
		if(user != null) {		
			// 로그인 유저가 가진 권한 몇개인지 조회해오기
			int memberRoleCount = qaBoardService.selectAdminById(user.getUsername());
			log.info("authority : {}", memberRoleCount);
			
			// 총관리자가 가진 권한은 3개, 총관리자만 모든 게시글 조회 가능
			if(memberRoleCount == 3) {
				
				/* cookie 활용한 조회수 무한 증가 방지 처리 */			
		
				// Ex. "|1||22||100|" 와 같은 bcount cookie의 value 값에서 indexOf로 해당 문자열 찾기
				// 처음 읽는 게시글일 경우 (해당 쿠키 없음 (-1))
				if(bcount.indexOf("|" + QNo + "|") == -1) {
					// 기본 bcount 값에 지금 요청한 qNo 값 추가하여 새로운 쿠키 생성
					Cookie newBcount = new Cookie("bcount", bcount + "|" + QNo + "|");
					// 초 단위로 유효 기간 설정 가능
					// newBcount.setMaxAge(1 * 24 * 60 * 60);
					
					// 설정하지 않을 시 session cookie
					// 클라이언트가 저장하고 있을 수 있도록 응답에 담는다
					response.addCookie(newBcount);
					
					// DB의 해당 게시글 조회수 증가
					int increaseCountResult = qaBoardService.increaseCount(QNo);
					
					if(increaseCountResult > 0) {
						log.info("조회수 증가 성공");
					} else{
						log.info("조회수 증가 실패");
					}		
				}
				
				board = qaBoardService.selectQA(QNo);
				log.info("게시판 조회 : {}", board);
				
				model.addAttribute("board",board);
				
				return "cs/qDetail"; 
			
				
			// 비밀글일 경우 작성자와 로그인 아이디가 일치하지 않는 경우 비밀글입니다 알리고 목록으로 redirect
			}else if(board.getSecretStatus().equals("Y") && !board.getUserId().equals(user.getUsername())) {
				
				rttr.addFlashAttribute("msg", "비밀글입니다");
				
				return "redirect:/qaBoard/list";
				
			}
			
		}
		
		/* 비밀글 아닐 경우 member 조회 가능 */
		/* cookie 활용한 조회수 무한 증가 방지 처리 */

		if(bcount.indexOf("|" + QNo + "|") == -1) {

			Cookie newBcount = new Cookie("bcount", bcount + "|" + QNo + "|");

			response.addCookie(newBcount);
			
			qaBoardService.increaseCount(QNo);
				
		}
		
		board = qaBoardService.selectQA(QNo);
		log.info("게시판 조회 : {}", board);
		
		model.addAttribute("board",board);
		
		return "cs/qDetail"; 
		
	}
	
	/* 인가 받아야만 접근 가능 */
	@GetMapping("insert")
	public String insertPage() {
		return "cs/questionPage";
	}
	
	/* 인가 받아야만 접근 가능 */
	@PostMapping("insert")
	public String insertQA(QABoard qaBoard, @AuthenticationPrincipal UserImpl user) {
		
		if(user != null) {
			qaBoard.setUserNo(user.getNo());
		}
		
		int result = qaBoardService.insertQA(qaBoard);
		
		if(result > 0) {
			log.info("문의 등록 성공");
		} else{
			log.info("문의 등록 실패");
		}		
		
		return "redirect:/qaBoard/list";
	}
	
	/* 인가 받아야만 접근 가능(+본인) */
	// 수정 화면
	@RequestMapping("updateView")
	public String updateQAView(int qNo, Model model) {
		
		QABoard board = qaBoardService.selectQA(qNo);
		
		if(board != null) {
			model.addAttribute("board",board);
		} else {
			/* 에러 메세지 or 페이지 */
		}
		
		return "cs/questionUpdateView";
	}
	
	/* 인가 받아야만 접근 가능(+본인) */
	@PostMapping("update")
	public String updateQA(QABoard qaBoard, Model model) {
	
		int QNo = qaBoard.getQno();
		
		int result = qaBoardService.updateQA(qaBoard);
		if(result > 0) {
			log.info("문의 수정 성공");
		} else{
			log.info("문의 수정 실패");
		}	
		
		return "redirect:/qaBoard?qno=" + QNo;
	}
	
	/* 인가 받아야만 접근 가능 (+본인,관리자) */
	@RequestMapping("delete")
	public String deleteQA(int qNo) {
		
		int result = qaBoardService.deleteQA(qNo);
		if(result > 0) {
			log.info("삭제 성공");
		} else{
			log.info("삭제 실패");
		}	
		
		// 삭제되었습니다 메세지 추가할가
		
		return "redirect:/qaBoard/list";
	}
	
	/* 관리자만 접근 가능 (비동기)*/
	@ResponseBody
	@RequestMapping("insertReply")
	public Answer insertReply(int qno, String AContent){
		
		Answer answer = new Answer();
		answer.setQno(qno);

		answer.setAcontent(AContent);
		
		answer = qaBoardService.insertReply(answer);
		
		log.info("answer : {}", answer);
	
		
		return answer;
	}
	
	
	/* 관리자만 접근 가능(비동기) */
	@ResponseBody
	@PutMapping("/updateReply/{qno}")
	public int updateAnswer(@PathVariable int qno, @RequestBody Answer answer) {
		
		log.info("answer {} : " , answer);
		int updateAnswer = qaBoardService.updateReply(answer);
		
		return updateAnswer;
	}
	
	/* 관리자만 접근 가능(비동기) */
	@ResponseBody
	@DeleteMapping("/deleteReply/{qno}")
	public int deleteBook(@PathVariable int qno) {
		log.info("삭제 요청 qno : {}, qno");
		
		return qaBoardService.deleteReply(qno);
	}
	
}
