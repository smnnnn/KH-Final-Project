package com.kh.project.admin.adminManage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.project.admin.adminManage.model.service.AdminService;
import com.kh.project.admin.common.model.vo.Pagination;
import com.kh.project.admin.common.model.vo.Search;
import com.kh.project.admin.memberManage.model.vo.MemberInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/account")
public class AdminController {
	
	private AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping("list")
	public String accountList(Model model, @RequestParam(defaultValue = "1") int page) {
		
		/* 관리자 전체 갯수 조회 */
		int totalListCount = adminService.getListCount();
		
		/* Pagination 객체 => 3 : 하단에 보여질 페이질 목록 수, 10 : 한 페이지에 보여질 목록 최대 */
		Pagination pagination = new Pagination(page, totalListCount, 3, 10);
		
		/* 회원 목록 조회 => 페이징 처리 */
		int startRow = (pagination.getPage() - 1) * pagination.getListLimit() + 1;
		int endRow = startRow + pagination.getListLimit() - 1;
		
		List<MemberInfo> adminList = adminService.selectAdminList(startRow, endRow);
//		log.info("list: {}", adminList);
		Search search = new Search();
		search.setStartRow(startRow);
		search.setEndRow(endRow);
		
		model.addAttribute("adminList", adminList);
		model.addAttribute("search", search);
		model.addAttribute("pagination", pagination);
		return "admin/adminList";
	}
	
	@GetMapping("modify/{adminNo}")
	@ResponseBody
	public MemberInfo adminModifyView(@PathVariable int adminNo) {	
		return adminService.selectAdminByNo(adminNo);
	}
	
	@PostMapping("modify")
	public String modifyAdmin(@RequestParam int no, @RequestParam int authority, RedirectAttributes rttr) {
		
		int result = adminService.modifyAdminAuthority(no, authority);
		if(result > 0) {
			rttr.addFlashAttribute("adminResultMessage", "관리자 등급 변경에 성공했습니다.");
		} else {
			rttr.addFlashAttribute("adminResultMessage", "관리자 등급 변경에 실패했습니다.");
		}
		return "redirect:/admin/account/list";
	}
	
	@GetMapping("delete/{adminNo}")
	public String deleteAdmin(@PathVariable int adminNo, RedirectAttributes rttr) {
		
		int result = adminService.deleteAdmin(adminNo);
		if(result > 0) {
			rttr.addFlashAttribute("adminResultMessage", "해당 관리자를 삭제하였습니다.");			
		} else {
			rttr.addFlashAttribute("adminResultMessage", "해당 관리자를 삭제하는데 실패하였습니다.");		
		}
		return "redirect:/admin/account/list";
	}
	
	@GetMapping("regist")
	public String registAdminView() {
		return "admin/adminRegist";
	}
	
	@PostMapping("regist")
	public String registAdmin(MemberInfo admin, RedirectAttributes rttr) {

		int result = adminService.registAdmin(admin);
		if(result > 0) {
			rttr.addFlashAttribute("adminResultMessage", "관리자를 등록하였습니다.");			
		} else {
			rttr.addFlashAttribute("adminResultMessage", "관리자를 등록하는데 실패하였습니다.");	
		}		
		
		return "redirect:/admin/account/list";
	}
	
	@GetMapping("mypage")
	public String mypageView(@RequestParam int adminNo) {
		log.info("no : {}", adminNo);
		
		return "admin/mypage";
	}
	
	@PostMapping("mypageModify")
	public String mypageModify(MemberInfo admin, RedirectAttributes rttr) {
		
		return "redirect:/admin";
	}
	
	
}
