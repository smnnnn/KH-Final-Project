package com.kh.project.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.member.model.vo.DogInformation;
import com.kh.project.member.model.vo.Member;
import com.kh.project.member.model.vo.MemberRole;
import com.kh.project.member.model.vo.WithdrawalReason;

@Mapper
public interface MemberMapper {

	Member findMemberById(String userId);

	void insertMember(Member member);

	void insertMemberRole(MemberRole memberRole);
	
	void insertDogInformaion(DogInformation dogInformation);

	int idCheck(String userId);

	String idFind(String name, String email);

	void withdrawal(WithdrawalReason withdrawal);

	void updateaccSecssionYn(Member member);

	void updateMember(Member member);

	void updateDogInformaion(DogInformation dogInformation);

	void pwdUpdate(Member member);

	int pwdFind(String id, String email);

	void myPageUpdate(Member member);

	int selectDogInformation(int no);

	void updateInsertDogInformaion(DogInformation dogInformation);
	
	
	

}
