package com.kh.project.member.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kh.project.member.model.vo.DogInformation;
import com.kh.project.member.model.vo.Member;
import com.kh.project.member.model.vo.WithdrawalReason;


public interface MemberService extends UserDetailsService{

	void signUp(Member member, DogInformation dogInformation);

	int idCheck(String userId);

	String idFind(String name, String email);

	void withdrawal(Member member, WithdrawalReason withdrawal);

	Member myPageUpdate(Member member, DogInformation dogInformation);

	void pwdUpdate(Member member);

	int pwdFind(String id, String email);

}
