package com.kh.project.admin.csManage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.project.admin.csManage.model.service.CSManageService;
import com.kh.project.cs.model.vo.QABoard;

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
	public String csListView(Model model) {
		
		List<QABoard> questionList = csManageService.selectQuestionList();
		model.addAttribute("questionList", questionList);
		
		return "admin/csList";
	}
	
	@GetMapping("detail")
	public String questionDetailView(@RequestParam int no, Model model) {
		
		QABoard question = csManageService.selectQuestionByNo(no);
		model.addAttribute("question", question);
		
		return "admin/csDetail";
	}
	
	@GetMapping("delete")
	public String deleteQuestion(@RequestParam int QNo, @RequestParam int ANo, RedirectAttributes rttr) {
		
		int result = csManageService.deleteQuestion(QNo, ANo);
		
		if(result > 0) {
			rttr.addFlashAttribute("questionResultMessage", "문의 정보 삭제에 성공하였습니다.");
		} else {
			rttr.addFlashAttribute("questionResultMessage", "문의 정보 삭제에 실패하였습니다.");
		}
		
		return "redirect:/admin/cs/list";
	}
	
	
}
