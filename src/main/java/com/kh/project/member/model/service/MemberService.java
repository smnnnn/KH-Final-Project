package com.kh.project.member.model.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kh.project.member.model.vo.DogInformation;
import com.kh.project.member.model.vo.Member;
import com.kh.project.member.model.vo.WithdrawalReason;
import com.kh.project.reservation.model.vo.ReservationInfo;


public interface MemberService extends UserDetailsService{


	void signUp(Member member, DogInformation dogInformation);

	int idCheck(String userId);

	String idFind(String name, String email);

	void withdrawal(Member member, WithdrawalReason withdrawal);

	List<ReservationInfo> reservationList(String id);

	int reservationCancel(int reservation_no);
}
