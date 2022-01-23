package com.kh.project.admin.memberManage.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.project.admin.common.model.vo.Search;
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
	public int getListCount(int sort, Search search) {
		search.setSort(sort);
		return memberManageMapper.getListCount(search);
	}
	
	@Override
	public List<MemberInfo> selectMemberList(int startRow, int endRow, int sort, Search search) {
		
		search.setStartRow(startRow);
		search.setEndRow(endRow);
		search.setSort(sort);
		
		List<MemberInfo> memberInfo = memberManageMapper.selectMemberList(search);		
		return memberInfo;
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
		log.info("reservation : {}", reservation);
		if(reservation.getDogNo() == 0) {
			Dog inputDog = memberManageMapper.selectDogInputInfo(rno);
			log.info("userRNO  : {}", rno);
			reservation.setDog(inputDog);
		} else if(reservation.getDogNo() != 0) {
			Dog dog = memberManageMapper.selectDogInfo(userNo);
			log.info("userNONO  : {}", userNo);
			reservation.setDog(dog);
		}
		return reservation;
	}

	@Override
	@Transactional
	public int deleteMemberInfo(int memberNo, String reason) {
		int memberResult = memberManageMapper.deleteMemberInfo(memberNo);
		Map<String, Object> map = new HashMap<>();
		map.put("mNo", memberNo);
		map.put("reason", reason);
		int reasonResult = memberManageMapper.updateReason(map);
		
		return memberResult > 0 && reasonResult > 0 ? 1 : 0;
	}


}
