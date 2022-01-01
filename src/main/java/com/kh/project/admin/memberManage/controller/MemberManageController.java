package com.kh.project.admin.memberManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/memberManage")
public class MemberManageController {

	@GetMapping("list")
	public String memberManageList() {
		return "admin/memberList";
	}
	

}
