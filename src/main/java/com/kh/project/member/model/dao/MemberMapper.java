package com.kh.project.member.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.member.model.vo.DogInformation;
import com.kh.project.member.model.vo.Member;
import com.kh.project.member.model.vo.MemberRole;
import com.kh.project.member.model.vo.WithdrawalReason;
import com.kh.project.reservation.model.vo.ReservationInfo;

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

	List<ReservationInfo> reservationList(String id);

	int reservationCancel(int reservation_no);
	
	
	

}
