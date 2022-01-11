package com.kh.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.project.member.model.service.MemberService;
import com.kh.project.member.model.vo.Member;




@Controller
@RequestMapping("/member")
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	public MemberController (MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/login")
	public void loginForm() {}
	
	@GetMapping("/signUp")
	public void signUpForm() {}
	
	@GetMapping("/idFind")
	public void idFindForm() {}
	
	@GetMapping("/myPage")
	public void myPageForm() {}
	
	@GetMapping("/pwdFind")
	public void pwdFindForm() {}
	
	@GetMapping("/reservationConfirmation")
	public void reservationConfirmationForm() {}
	
	@GetMapping("/withdrawal")
	public void withdrawalForm() {}
	
	
	@PostMapping("signUp")
	public String signUp(Member member) {    //요청하면서 넘어온 데이터를 넘겨야 함 name="id" 멤버 필드랑 이름 똑같이 함, 멤버 객체로 받아올 수 있음
		
		memberService.signUp(member);  // 받아온 값 서비스 쪽으로 전달 
		
		return "redirect:/";  // 회원가입 완료되면 루트로 리다이렉트 해줌
	}

	
	

}
