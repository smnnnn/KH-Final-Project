package com.kh.project.admin.csManage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.project.admin.common.model.vo.Pagination;
import com.kh.project.admin.csManage.model.service.CSManageService;
import com.kh.project.admin.csManage.model.vo.Question;
import com.kh.project.admin.csManage.model.vo.SortAndSearch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/cs")
public class CSManageController {
	
	private CSManageService csManageService;
	
	@Autowired
	public CSManageController(CSManageService csManageService) {
		this.csManageService = csManageService;
	}
	
	@GetMapping("list")
	public String csListView(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "0") int sort) {
		
		/* 문의 목록 전체 갯수 조회 */
		int totalListCount = 0;
		if(sort == 4 || sort == 5) {
			// 문의 답변여부로 갯수 조회
			totalListCount = csManageService.getAnswerStatusCount(sort);
		} else {
			totalListCount = csManageService.getListCount(sort);			
		}
		
		/* Pagination 객체 => 5 : 하단에 보여질 페이질 목록 수, 10 : 한 페이지에 보여질 목록 최대 */
		Pagination pagination = new Pagination(page, totalListCount, 5, 10);
		
		/* 문의 목록 조회 => 페이징 처리 */
		int startRow = (pagination.getPage() - 1) * pagination.getListLimit() + 1;
		int endRow = startRow + pagination.getListLimit() - 1;
		
		List<Question> questionList = csManageService.selectQuestionList(startRow, endRow, sort);			
				
		model.addAttribute("pagination", pagination);
		model.addAttribute("questionList", questionList);
		model.addAttribute("sort", sort);
		
		return "admin/csList";
	}
	
	
	@GetMapping("detail")
	public String questionDetailView(@RequestParam int no, Model model) {
		
		Question question = csManageService.selectQuestionByNo(no);
		model.addAttribute("question", question);
		
		return "admin/csDetail";
	}
	
	@GetMapping("delete")
	public String deleteQuestion(@RequestParam int qno, @RequestParam int ano, RedirectAttributes rttr) {
		
		int result = csManageService.deleteQuestion(qno, ano);
		
		if(result > 0) {
			rttr.addFlashAttribute("questionResultMessage", "문의 정보 삭제에 성공하였습니다.");
		} else {
			rttr.addFlashAttribute("questionResultMessage", "문의 정보 삭제에 실패하였습니다.");
		}
		
		return "redirect:/admin/cs/list";
	}
	
	
}
