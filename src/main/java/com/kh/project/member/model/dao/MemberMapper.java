package com.kh.project.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.member.model.vo.Member;
import com.kh.project.member.model.vo.MemberRole;

@Mapper
public interface MemberMapper {

	Member findMemberById(String username);

	void insertMember(Member member);

	void insertMemberRole(MemberRole memberRole);
	
	
	

}
