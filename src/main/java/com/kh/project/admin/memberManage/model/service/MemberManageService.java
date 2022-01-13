package com.kh.project.admin.memberManage.model.service;

import java.util.List;

import com.kh.project.admin.memberManage.model.vo.MemberInfo;
import com.kh.project.admin.reservationManage.model.vo.ReservationManage;

public interface MemberManageService {

	List<MemberInfo> selectMemberList();

	MemberInfo selectMemberByNo(int no);

	ReservationManage getReservationInfo(int rno, int userNo);

}
