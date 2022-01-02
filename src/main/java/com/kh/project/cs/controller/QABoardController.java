package com.kh.project.cs.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.project.cs.model.service.QABoardService;
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

	@GetMapping("list")
	public ModelAndView selectQAList(ModelAndView mv,HttpServletRequest request) {
		/*HttpServletRequest 써서 가져오는 방법 말고 생각해보기 */
		
		
		// 기본적으로 게시판은 1페이지부터 시작
		int page = 1;
				
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 페이지를 page로 적용
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
				
		// 검색 관련 파라미터 추출
		String searchCondition = request.getParameter("searchCondition"); //넘어온상황
		String searchValue = request.getParameter("searchValue"); //넘어오지않은상황
		
		//page랑 검색조건 전달
//		List<QABoard> qaBoardList = qaBoardService.selectQAList(page, new Search(searchCondition, searchValue)); 
//		mv.addObject("qaBoardList", qaBoardList);
//		mv.setViewName("qaBoard/list");
		
		
		// 페이징과 관련 된 데이터, 조회 된 boardList를 map에 담아 리턴
		Map<String, Object> map 
		= qaBoardService.selectQAList(page, new Search(searchCondition, searchValue));
		
		log.info("페이지 : ", map.get("pi"));
		log.info("boardList : ",map.get("boardList"));
		
		mv.addAllObjects(map);
		mv.setViewName("qaBoard/list"); //엥?
		
		return mv;
		
		
	}
}
