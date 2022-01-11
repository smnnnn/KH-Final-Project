package com.kh.project.admin.memberManage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.project.admin.memberManage.model.service.MemberManageService;
import com.kh.project.admin.memberManage.model.vo.MemberInfo;

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
		
		log.info("memberList : {}", memberInfoList);
		
		model.addAttribute("memberInfoList", memberInfoList);
		
		return "admin/memberList";
	}
	

}
