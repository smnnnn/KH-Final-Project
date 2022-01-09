package com.kh.project.reservation.model.service;

import java.util.List;

import com.kh.project.reservation.model.vo.ReservationInfo;
import com.kh.project.reservation.model.vo.VeterinarianAndTreatmentType;


public interface ReservationService {

	List<VeterinarianAndTreatmentType> findVname(int tno);

	int inputReser(ReservationInfo reservationInfo);
	
}
