package com.kh.project.reservation.model.service;

import java.util.List;

import com.kh.project.reservation.model.vo.DogInformationInput;
import com.kh.project.reservation.model.vo.ReservationInfo;
import com.kh.project.reservation.model.vo.VeterinarianAndTreatmentType;


public interface ReservationService {

	List<VeterinarianAndTreatmentType> findVname(int tno);

	int registReservation(ReservationInfo reservationInfo, String id);
	
	int registDog(DogInformationInput dogInfo);

	
}
