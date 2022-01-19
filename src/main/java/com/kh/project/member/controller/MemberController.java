package com.kh.project.member.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.servlet.ModelAndView;


import com.kh.project.member.model.service.MemberService;
import com.kh.project.member.model.vo.DogInformation;
import com.kh.project.member.model.vo.Member;
import com.kh.project.member.model.vo.UserImpl;
import com.kh.project.member.model.vo.WithdrawalReason;
import com.kh.project.reservation.model.vo.ReservationInfo;

import lombok.extern.slf4j.Slf4j;



@Slf4j
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


	@GetMapping("/withdrawal")
	public void withdrawalForm() {}
	
	@GetMapping("/pwdUpdate")
	public void pwdUpdateForm() {}
	
	
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
	
	
	@PostMapping("/pwdFind")
	public String pwdFind(String id, String email, RedirectAttributes rttr, Model model ) {
		
		int result = memberService.pwdFind(id, email);
	
		log.info("controller : " + result);
		if(result > 0 ) {
			//rttr.addFlashAttribute("successMessage", "계정확인이 완료되었습니다.");
			model.addAttribute("id", id);
			model.addAttribute("email", email);
			
			model.addAttribute("successMessage", "계정확인이 완료되었습니다.");
			
			return "member/pwdUpdate";
			
		}else {
			rttr.addFlashAttribute("failMessage", "해당하는 회원이 존재하지 않습니다.");
			return "redirect:/member/pwdFind";
			
		}

	}
	
	
	
	
	

	@PostMapping("/withdrawal")
	public String withdrawal(Member member, WithdrawalReason withdrawal) {
		
		memberService.withdrawal(member, withdrawal);
	
		return "redirect:/member/logout";

	}
	
	
	
	@PostMapping("/signUp")
	public String signUp(Member member, DogInformation dogInformation) {    //요청하면서 넘어온 데이터를 넘겨야 함 name="id" 멤버 필드랑 이름 똑같이 함, 멤버 객체로 받아올 수 있음
		//  DogInformation dogInformation 화면에서 넘어온 값
		
		
		memberService.signUp(member, dogInformation);  // 받아온 값 서비스 쪽으로 전달 
		
		

		
		return "redirect:/";  // 회원가입 완료되면 루트로 리다이렉트 해줌
	}

	
	/* 진료 예약 */
	@GetMapping("/reservationConfirmation")
	public ModelAndView reservationConfirmationForm(Principal principal, ModelAndView mv) {
		
		String id = principal.getName();
		
		List<ReservationInfo> ReserInfo = memberService.reservationList(id);
		
		mv.addObject("ReserInfo", ReserInfo);
		mv.setViewName("member/reservationConfirmation");
		
		return mv;
	}
	

	@PostMapping("/myPage")
	public String myPageUpdate(Member member, DogInformation dogInformation,  @AuthenticationPrincipal UserImpl user) {    //요청하면서 넘어온 데이터를 넘겨야 함 name="id" 멤버 필드랑 이름 똑같이 함, 멤버 객체로 받아올 수 있음
		//  DogInformation dogInformation 화면에서 넘어온 값
		
		dogInformation.setUserNo(user.getNo()); // 로그인된 상태에서도 업데이트 되도록 
		log.info("controller doginfo : " + dogInformation);
		log.info("controller member : " + member);
		member = memberService.myPageUpdate(member, dogInformation);  // 받아온 값 서비스 쪽으로 전달 
		
		user.setDetails(member);  // 로그인정보에

		
		return "redirect:/";  // 회원가입 완료되면 루트로 리다이렉트 해줌
	}
	
	@PostMapping("/pwdUpdate")
	public String pwdUpdate(Member member) { 		
		
		memberService.pwdUpdate(member);  
		
		

		
		return "redirect:/";  // 회원가입 완료되면 루트로 리다이렉트 해줌
	}
	
	
	

	/* 예약 취소 */
	@GetMapping("/reservationConfirmation/no/{reservation_no}")
	@ResponseBody
	public int ReservationCancel(@PathVariable int reservation_no){
	
		return memberService.reservationCancel(reservation_no);
		
	}


}
