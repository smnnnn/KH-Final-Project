package com.kh.project.admin.memberManage.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.project.admin.memberManage.model.dao.MemberManageMapper;
import com.kh.project.admin.memberManage.model.vo.MemberInfo;
import com.kh.project.admin.reservationManage.model.vo.Dog;
import com.kh.project.admin.reservationManage.model.vo.ReservationManage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberManageServiceImpl implements MemberManageService{

	private MemberManageMapper memberManageMapper;
	
	@Autowired
	public MemberManageServiceImpl(MemberManageMapper memberManageMapper) {
		this.memberManageMapper = memberManageMapper;
	}
	
	@Override
	public List<MemberInfo> selectMemberList() {
		
		List<MemberInfo> memberInfo = memberManageMapper.selectMemberList();
		List<MemberInfo> returnMemberInfo = new ArrayList<>();
		for(MemberInfo member : memberInfo) {
			if(member.getMemberRoleList().size() == 1) {
				Dog dog = memberManageMapper.selectDogInfo(member.getNo());
				member.setDog(dog);
				returnMemberInfo.add(member);
			}
		}
		
		return returnMemberInfo;
	}

	@Override
	public MemberInfo selectMemberByNo(int no) {
		/* 회원 기보정보 조회 */
		MemberInfo member = memberManageMapper.selectMemberByNo(no);
		/* 반려견 정보 조회 */
		Dog dog = memberManageMapper.selectDogInfo(no);
		member.setDog(dog);
		/* 회원 예약정보 조회 */
		List<ReservationManage> reservationList = memberManageMapper.selectReservationList(no);
		for(ReservationManage r : reservationList) {
			if(r.getDogNo() == 0) {
				Dog inputDog = memberManageMapper.selectDogInputInfo(r.getReservationNo());		
				r.setDog(inputDog);
			} else {
				r.setDog(memberManageMapper.selectDogInfo(no));	
			}
		}
		
		member.setReservation(reservationList);
		
		return member;
	}

	@Override
	public ReservationManage getReservationInfo(int rno, int userNo) {
		ReservationManage reservation = memberManageMapper.getReservationInfo(rno);
		if(reservation.getDogNo() == 0) {
			Dog inputDog = memberManageMapper.selectDogInputInfo(rno);
			reservation.setDog(inputDog);
		} else {
			Dog dog = memberManageMapper.selectDogInfo(userNo);
			reservation.setDog(dog);
		}
		return reservation;
	}

}
