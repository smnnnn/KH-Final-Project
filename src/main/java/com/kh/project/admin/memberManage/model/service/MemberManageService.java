package com.kh.project.admin.memberManage.model.service;

import java.util.List;

import com.kh.project.admin.common.model.vo.Search;
import com.kh.project.admin.memberManage.model.vo.MemberInfo;
import com.kh.project.admin.reservationManage.model.vo.ReservationManage;

public interface MemberManageService {

	List<MemberInfo> selectMemberList(int startRow, int endRow, int sort, Search search);

	MemberInfo selectMemberByNo(int no);

	ReservationManage getReservationInfo(int rno, int userNo);

	int getListCount(int sort, Search search);

	int deleteMemberInfo(int memberNo, String reason);

}
