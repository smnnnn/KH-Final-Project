package com.kh.project.member.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kh.project.member.model.vo.Member;


public interface MemberService extends UserDetailsService{

	void signUp(Member member);

	int idCheck(String userId);

}
