package com.kh.project.reservation.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.project.reservation.model.dao.ReservationMapper;
import com.kh.project.reservation.model.vo.DogInformationInput;
import com.kh.project.reservation.model.vo.ReservationInfo;
import com.kh.project.reservation.model.vo.VeterinarianAndTreatmentType;
import com.kh.project.subAdmin.model.vo.Veterinarian;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService{
	
	private final ReservationMapper reservationMapper;
	
	@Autowired
	public ReservationServiceImpl(ReservationMapper reservationMapper) {
		this.reservationMapper = reservationMapper;
	}

	@Override
	public List<VeterinarianAndTreatmentType> findVname(int tno) {
		return reservationMapper.findVname(tno);
	}
	
	@Override
	public int registReservation(ReservationInfo reservationInfo, String id) {
		return reservationMapper.registReservation(reservationInfo, id);
	}

	@Override
	public int registDog(DogInformationInput dogInfo) {
		return reservationMapper.registDog(dogInfo);
	}

}
