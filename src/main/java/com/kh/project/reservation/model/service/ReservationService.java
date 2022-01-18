package com.kh.project.reservation.model.service;

import java.util.List;

import com.kh.project.member.model.vo.DogInformation;
import com.kh.project.reservation.model.vo.DogInformationInput;
import com.kh.project.reservation.model.vo.ReservationInfo;
import com.kh.project.reservation.model.vo.VeterinarianAndTreatmentType;


public interface ReservationService {

	List<VeterinarianAndTreatmentType> notice();

	List<VeterinarianAndTreatmentType> findVname(int tno);

	List<ReservationInfo> selectTime(String vno, String reservation_date);
	
	int registReservation(ReservationInfo reservationInfo, String id);
	
	int registDog(DogInformationInput dogInfo);

	List<DogInformation> selectDogInfo(String id);

	
}
