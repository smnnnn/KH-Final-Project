package com.kh.project.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

	@GetMapping(value= {"/", "/main"})
	public String main() {
		return "main/main";
	}
	
	/* 루트로 포워딩 하더라도 처리(main으로 이동)되게끔 하는 메소드 */
	@PostMapping(value="/")
	public String redirectMain() {
		return "redirect:/";
	}
	
	/* admin main page*/
	@GetMapping("/admin")
	public String adminMain() {
		return "admin/dashboard";
	}
}
