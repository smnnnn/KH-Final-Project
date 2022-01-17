package com.kh.project.admin.adminManage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.kh.project.admin.adminManage.model.vo.DashBoard;
import com.kh.project.admin.common.model.vo.Pagination;
import com.kh.project.admin.common.model.vo.Search;
import com.kh.project.admin.memberManage.model.vo.MemberInfo;
import com.kh.project.admin.visit.model.vo.VisitCount;
import com.kh.project.member.model.vo.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private AdminService adminService;
	private AuthenticationManager authenticationManager;
	
	@Autowired
	public AdminController(AdminService adminService, AuthenticationManager authenticationManager) {
		this.adminService = adminService;
		this.authenticationManager = authenticationManager;
	}
	
	@GetMapping("/account/list")
	public String accountList(Model model, @RequestParam(defaultValue = "1") int page) {
		
		/* 관리자 전체 갯수 조회 */
		int totalListCount = adminService.getListCount();
		
		/* Pagination 객체 => 3 : 하단에 보여질 페이질 목록 수, 10 : 한 페이지에 보여질 목록 최대 */
		Pagination pagination = new Pagination(page, totalListCount, 3, 10);
		
		/* 회원 목록 조회 => 페이징 처리 */
		int startRow = (pagination.getPage() - 1) * pagination.getListLimit() + 1;
		int endRow = startRow + pagination.getListLimit() - 1;
		
		List<MemberInfo> adminList = adminService.selectAdminList(startRow, endRow);

		Search search = new Search();
		search.setStartRow(startRow);
		search.setEndRow(endRow);
		
		model.addAttribute("adminList", adminList);
		model.addAttribute("search", search);
		model.addAttribute("pagination", pagination);
		return "admin/adminList";
	}
	
	@GetMapping("/account/modify/{adminNo}")
	@ResponseBody
	public MemberInfo adminModifyView(@PathVariable int adminNo) {
		MemberInfo memberInfo = adminService.selectAdminByNo(adminNo);
		if(memberInfo == null) memberInfo = new MemberInfo();
		return memberInfo;
	}
	
	@PostMapping("/account/modify")
	public String modifyAdmin(@RequestParam int no, @RequestParam int authority, RedirectAttributes rttr) {
		
		int result = adminService.modifyAdminAuthority(no, authority);
		if(result > 0) {
			rttr.addFlashAttribute("adminResultMessage", "관리자 등급 변경에 성공했습니다.");
		} else {
			rttr.addFlashAttribute("adminResultMessage", "관리자 등급 변경에 실패했습니다.");
		}
		return "redirect:/admin/account/list";
	}
	
	@GetMapping("/account/delete/{adminNo}")
	public String deleteAdmin(@PathVariable int adminNo, RedirectAttributes rttr) {
		
		int result = adminService.deleteAdmin(adminNo);
		if(result > 0) {
			rttr.addFlashAttribute("adminResultMessage", "해당 관리자를 삭제하였습니다.");			
		} else {
			rttr.addFlashAttribute("adminResultMessage", "해당 관리자를 삭제하는데 실패하였습니다.");		
		}
		return "redirect:/admin/account/list";
	}
	
	@GetMapping("/account/regist")
	public String registAdminView() {
		return "admin/adminRegist";
	}
	
	@PostMapping("/account/regist")
	public String registAdmin(MemberInfo admin, RedirectAttributes rttr) {

		int result = adminService.registAdmin(admin);
		if(result > 0) {
			rttr.addFlashAttribute("adminResultMessage", "관리자를 등록하였습니다.");			
		} else {
			rttr.addFlashAttribute("adminResultMessage", "관리자를 등록하는데 실패하였습니다.");	
		}		
		
		return "redirect:/admin/account/list";
	}
	
	@GetMapping("/mypage")
	public void myPageForm(Model model, @AuthenticationPrincipal UserImpl user){	
	}  // 로그인 객체 바로 가져오기 	
	
	@PostMapping("/account/mypageModify")
	public String mypageModify(@AuthenticationPrincipal UserImpl user, MemberInfo admin, RedirectAttributes rttr) {
		MemberInfo changeInfo = new MemberInfo();
		changeInfo.setId(user.getId());
		if(!admin.getPwd().equals("")) changeInfo.setPwd(admin.getPwd());
		if(!admin.getName().equals("")) changeInfo.setName(admin.getName());
		if(!admin.getPhone().equals("")) changeInfo.setPhone(admin.getPhone());
		if(!admin.getEmail().equals("")) changeInfo.setEmail(admin.getEmail());
		
		int result = adminService.modifyAdminInfo(changeInfo);
		if(result > 0) {			
			/* 변경된 세션 등록 - 로그아웃처리됨.. */ 
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())); 
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			rttr.addFlashAttribute("adminMessage", "정보 수정에 성공하였습니다.");	
			
		} else {
			rttr.addFlashAttribute("adminMessage", "정보 수정에 실패하였습니다.");			
		}

		return "redirect:/admin";
	}
	
	@GetMapping("dashboard")
	@ResponseBody
	public DashBoard dashboardView() {
		DashBoard dashBoard = adminService.dashBoard();
		if(dashBoard == null) dashBoard = new DashBoard();
		return dashBoard;
	}
	
	@GetMapping("visitCount")
	@ResponseBody
	public List<VisitCount> getVisitCount(){
		List<VisitCount> visitList = adminService.getVisitCount();
		return visitList;
	}
}
