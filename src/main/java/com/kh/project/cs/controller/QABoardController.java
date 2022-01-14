package com.kh.project.cs.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import com.kh.project.cs.model.service.QABoardService;
import com.kh.project.cs.model.vo.Answer;
import com.kh.project.cs.model.vo.QABoard;
import com.kh.project.cs.model.vo.Search;

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
		/*HttpServletRequest 써서 가져오는 방법 말고 생각해보기 */
		
		
		// 기본적으로 게시판은 1페이지부터 시작
		int page = 1;
				
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 페이지를 page로 적용
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
				
		// 검색 관련 파라미터 추출
//		String searchCondition = request.getParameter("searchCondition"); //검색조건(선택 카테고리)
//		String searchValue = request.getParameter("searchValue"); //검색어
		
		//page랑 검색조건 전달
//		List<QABoard> qaBoardList = qaBoardService.selectQAList(page, new Search(searchCondition, searchValue)); 
//		mv.addObject("qaBoardList", qaBoardList);
//		mv.setViewName("qaBoard/list");
		
		
		// 페이징과 관련 된 데이터, 조회 된 boardList를 map에 담아 리턴
		Map<String, Object> map 
		= qaBoardService.selectQAList(page, search);
		
		log.info("페이지 : {} ", map.get("pi"));
		log.info("boardList : {} ",map.get("boardList"));
		
		mv.addAllObjects(map);
		mv.setViewName("cs/QABoardList"); 
		
		return mv;
	
	}
	

	
	/*http://localhost:8007/qaBoard?QNo=100*/
	@GetMapping("") 
	public String selectQA(@RequestParam("qno") int QNo, Model model, HttpServletRequest request, HttpServletResponse response) {
		//int qNo = Integer.parseInt(request.getParameter("QNo")); // ?
		
		/* cookie 활용한 조회수 무한 증가 방지 처리 */
		Cookie[] cookies = request.getCookies();
		
		String bcount = "";
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				/* 읽은 게시물 bid를 저장해두는 쿠키의 이름 bcount가 있는지 확인*/
				if(c.getName().equals("bcount")) {
					bcount = c.getValue();
				}
			}
		}
		
		// 처음 읽는 게시글일 경우
		// Ex. "|1||22||100|" 와 같은 bcount cookie의 value 값에서 indexOf로 해당 문자열 찾기
		if(bcount.indexOf("|" + QNo + "|") == -1) {
			// 기본 bcount 값에 지금 요청한 qNo 값 추가하여 새로운 쿠키 생성
			Cookie newBcount = new Cookie("bcount", bcount + "|" + QNo + "|");
			// 초 단위로 유효 기간 설정 가능
			// newBcount.setMaxAge(1 * 24 * 60 * 60);
			
			// 설정하지 않을 시 session cookie
			// 클라이언트가 저장하고 있을 수 있도록 응답에 담는다
			response.addCookie(newBcount);
			
			// DB의 해당 게시글 조회수 증가
			int result = qaBoardService.increaseCount(QNo);
			
			if(result > 0) {
				log.info("조회수 증가 성공");
			} else{
				log.info("조회수 증가 실패");
			}		
		}
		
		QABoard board = qaBoardService.selectQA(QNo);
		log.info("게시판 조회 : {}", board);
		
		model.addAttribute("board",board);
		
		return "cs/qDetail"; 
		
	}
	
	@GetMapping("insert")
	public String insertPage() {
		return "cs/questionPage";
	}
	
	@PostMapping("insert")
	public String insertQA(QABoard qaBoard) {
		
		/* 로그인 기능 완성 전 테스트 */
		int userNo = 1;
		qaBoard.setUserNo(userNo);
		
		int result = qaBoardService.insertQA(qaBoard);
		
		if(result > 0) {
			log.info("문의 등록 성공");
		} else{
			log.info("문의 등록 실패");
		}		
		
		return "redirect:/qaBoard/list";
	}
	
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
	
//	@ResponseBody
//	@GetMapping("/updateReply/{qno}")
//	public Answer updateAnswerView(@PathVariable int qno) {
//		
//		Answer answer = qaBoardService.selectReply(qno);
//		
//		return answer;
//	}
	

	@ResponseBody
	@PutMapping("/updateReply/{qno}")
	public int updateAnswer(@PathVariable int qno, @RequestBody Answer answer) {
		
		log.info("answer {} : " , answer);
		int updateAnswer = qaBoardService.updateReply(answer);
		
		return updateAnswer;
	}
	
	@ResponseBody
	@DeleteMapping("/deleteReply/{qno}")
	public int deleteBook(@PathVariable int qno) {
		log.info("삭제 요청 qno : {}, qno");
		
		return qaBoardService.deleteReply(qno);
	}
	
}
