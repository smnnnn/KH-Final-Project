package com.kh.project.reservation.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.reservation.model.vo.VeterinarianAndTreatmentType;
import com.kh.project.subAdmin.model.vo.Veterinarian;

@Mapper
public interface ReservationMapper {

	List<VeterinarianAndTreatmentType> findVname(int tno);

}
