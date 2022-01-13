package com.kh.project.admin.memberManage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.project.admin.common.model.vo.Pagination;
import com.kh.project.admin.common.model.vo.Search;
import com.kh.project.admin.memberManage.model.service.MemberManageService;
import com.kh.project.admin.memberManage.model.vo.MemberInfo;
import com.kh.project.admin.reservationManage.model.vo.ReservationManage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/member")
public class MemberManageController {
	
	private MemberManageService memberManageService;
	
	@Autowired
	public MemberManageController(MemberManageService memberManageService) {
		this.memberManageService = memberManageService;
	}
	
	@GetMapping("list")
	public String memberManageList(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "0") int sort
			, Search search) {
		
		/* 예약 목록 전체 갯수 조회 */
		int totalListCount = memberManageService.getListCount(sort, search);
		
		/* Pagination 객체 => 5 : 하단에 보여질 페이질 목록 수, 10 : 한 페이지에 보여질 목록 최대 */
		Pagination pagination = new Pagination(page, totalListCount, 5, 10);
		
		/* 예약 목록 조회 => 페이징 처리 */
		int startRow = (pagination.getPage() - 1) * pagination.getListLimit() + 1;
		int endRow = startRow + pagination.getListLimit() - 1;
		
		
		List<MemberInfo> memberInfoList = memberManageService.selectMemberList(startRow, endRow, sort, search);
		if(memberInfoList != null) {
			model.addAttribute("memberInfoList", memberInfoList);			
		}
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("sort", sort);
		model.addAttribute("search", search);
		
		return "admin/memberList";
	}
	
	@GetMapping("detail")
	public String memberDetailView(@RequestParam int no, Model model) {
		
		MemberInfo member = memberManageService.selectMemberByNo(no);
		model.addAttribute("member", member);
		
		return "admin/memberDetail";
	}
	
	@GetMapping(value = {"detail/{rno}/{userNo}"})
	@ResponseBody
	public ReservationManage getReservationInfo(@PathVariable int rno, @PathVariable int userNo) {
		
		return memberManageService.getReservationInfo(rno, userNo);
	}
	
	@GetMapping(value = {"delete/{memberNo}/{outReason}"})
	public String deleteMemberInfo(@PathVariable int memberNo, @PathVariable String outReason, RedirectAttributes rttr) {
		String reason = "[관리자]" + outReason;
		
		int result = memberManageService.deleteMemberInfo(memberNo, reason);
		if(result > 0) {
			rttr.addFlashAttribute("memberResultMessage", "해당 회원을 탈퇴시키는데 성공했습니다.");
		} else {
			rttr.addFlashAttribute("memberResultMessage", "해당 회원을 탈퇴시키는데 실패하였습니다.");
		}
		
		return "redirect:/admin/member/list";
	}
}
