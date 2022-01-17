package com.kh.project.subAdmin.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.subAdmin.model.vo.Holiday;
import com.kh.project.subAdmin.model.vo.Veterinarian;

@Mapper
public interface SubAdminMapper {

	int registVeterinarian(Veterinarian newVeterinarian);
	
	int registHoliday(Holiday holiday);

	List<Veterinarian> findVeterinarian(String vname);

	int modifyVeterinarian(Veterinarian veterinarianModify);

	int modifyHoliday(Holiday holiday);
}
