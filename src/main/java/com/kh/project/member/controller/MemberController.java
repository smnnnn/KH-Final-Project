package com.kh.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.project.member.model.service.MemberService;
import com.kh.project.member.model.vo.DogInformation;
import com.kh.project.member.model.vo.Member;
import com.kh.project.member.model.vo.UserImpl;
import com.kh.project.member.model.vo.WithdrawalReason;




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
	public void myPageForm(Model model, @AuthenticationPrincipal UserImpl user)// 로그인 객체 바로 가져오기 
	{
		
		
	}
	
	@GetMapping("/pwdFind")
	public void pwdFindForm() {}
	
	@GetMapping("/reservationConfirmation")
	public void reservationConfirmationForm() {}
	
	@GetMapping("/withdrawal")
	public void withdrawalForm() {}
	
	
	@ResponseBody
	@PostMapping("/idCheck")
	public String idCheck(String userId ) {
		
		int result = memberService.idCheck(userId);
		
		return result > 0 ? "fail" : "success";
	}
	
	
	
	@ResponseBody
	@PostMapping("/idFind")
	public String idFind(String name, String email ) {
		
		String result = memberService.idFind(name, email);
	
		
		//
		return result;

	}
	
	

	@PostMapping("/withdrawal")
	public String withdrawal(Member member, WithdrawalReason withdrawal) {
		
		memberService.withdrawal(member, withdrawal);
	
		return "redirect:/";

	}
	
	
	
	@PostMapping("signUp")
	public String signUp(Member member, DogInformation dogInformation) {    //요청하면서 넘어온 데이터를 넘겨야 함 name="id" 멤버 필드랑 이름 똑같이 함, 멤버 객체로 받아올 수 있음
		//  DogInformation dogInformation 화면에서 넘어온 값
		
		
		memberService.signUp(member, dogInformation);  // 받아온 값 서비스 쪽으로 전달 
		
		

		
		return "redirect:/";  // 회원가입 완료되면 루트로 리다이렉트 해줌
	}

	
	

}
