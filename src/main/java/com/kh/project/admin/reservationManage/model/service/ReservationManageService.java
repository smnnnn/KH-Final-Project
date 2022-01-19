package com.kh.project.admin.reservationManage.model.service;

import java.util.List;

import com.kh.project.admin.common.model.vo.Search;
import com.kh.project.admin.reservationManage.model.vo.ReservationManage;

public interface ReservationManageService {

	List<ReservationManage> selectReservationList(int startRow, int endRow, int sort, Search search);

	ReservationManage selectReservationByNo(int no);

	int modifyReservation(ReservationManage reservation);

	int deleteReservation(int reservationNo, int dogNo);

	int getListCount(int sort, Search search);

}
