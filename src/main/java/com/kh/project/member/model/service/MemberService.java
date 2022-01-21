package com.kh.project.member.model.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kh.project.member.model.vo.DogInformation;
import com.kh.project.member.model.vo.Member;
import com.kh.project.member.model.vo.ReservationSelect;
import com.kh.project.member.model.vo.WithdrawalReason;
import com.kh.project.reservation.model.vo.ReservationInfo;


public interface MemberService extends UserDetailsService{


	void signUp(Member member, DogInformation dogInformation);

	int idCheck(String userId);

	String idFind(String name, String email);

	void withdrawal(Member member, WithdrawalReason withdrawal);


	Member myPageUpdate(Member member, DogInformation dogInformation);

	void pwdUpdate(Member member);

	int pwdFind(String id, String email);


	List<ReservationSelect> reservationList(String id);

	int reservationUpdate(int reservation_no);

	List<ReservationSelect> afterReservationList(String id);
	
	int reservationCancel(int reservation_no);



}
