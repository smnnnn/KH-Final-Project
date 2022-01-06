package com.kh.project.reservation.model.service;

import java.util.List;

import com.kh.project.reservation.model.vo.VeterinarianAndTreatmentType;
import com.kh.project.subAdmin.model.vo.Veterinarian;

public interface ReservationService {

	List<VeterinarianAndTreatmentType> findVname(int tno);
	
}
