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
	public String memberManageList(Model model) {
		
		List<MemberInfo> memberInfoList = memberManageService.selectMemberList();
		
		model.addAttribute("memberInfoList", memberInfoList);
		
		return "admin/memberList";
	}
	
	@GetMapping("detail")
	public String memberDetailView(@RequestParam int no, Model model) {
		
		MemberInfo member = memberManageService.selectMemberByNo(no);
		model.addAttribute("member", member);
		log.info("member : {}", member);
		
		return "admin/memberDetail";
	}
	
	@GetMapping("detail/{rno}/{userNo}")
	@ResponseBody
	public ReservationManage getReservationInfo(@PathVariable int rno, @PathVariable int userNo) {
		
		return memberManageService.getReservationInfo(rno, userNo);
	}
}
