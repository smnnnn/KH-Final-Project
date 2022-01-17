package com.kh.project.reservation.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.member.model.vo.DogInformation;
import com.kh.project.reservation.model.vo.DogInformationInput;
import com.kh.project.reservation.model.vo.ReservationInfo;
import com.kh.project.reservation.model.vo.VeterinarianAndTreatmentType;
import com.kh.project.subAdmin.model.vo.Veterinarian;

@Mapper
public interface ReservationMapper {

	List<VeterinarianAndTreatmentType> notice();
	
	List<VeterinarianAndTreatmentType> findVname(int tno);

	int registReservation(ReservationInfo reservationInfo, String id);
	
	int registDog(DogInformationInput dogInfo);

	List<DogInformation> selectDogInfo(String id);


}
