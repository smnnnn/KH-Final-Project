package com.kh.project.cs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CSController {

	@RequestMapping("/csPage")
	public String csPage() {
		return "cs/csPage";
	}
}
