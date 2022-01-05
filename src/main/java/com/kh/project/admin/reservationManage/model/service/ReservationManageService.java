package com.kh.project.admin.reservationManage.model.service;

import java.util.List;
import com.kh.project.admin.reservationManage.model.vo.ReservationManage;

public interface ReservationManageService {

	List<ReservationManage> selectReservationList();

	ReservationManage selectReservationByNo(int no);

	int modifyReservation(ReservationManage reservation);

	int deleteReservation(int reservationNo, int dogNo);

}
