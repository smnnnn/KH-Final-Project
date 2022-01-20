package com.kh.project.subAdmin.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.subAdmin.model.vo.Holiday;
import com.kh.project.subAdmin.model.vo.Veterinarian;
import com.kh.project.subAdmin.model.vo.VeterinarianAndHoliday;

@Mapper
public interface SubAdminMapper {
	
	int modifyStatusVeterinarian(int tno);

	int registVeterinarian(Veterinarian newVeterinarian);
	
	int registHoliday(Holiday holiday);

	List<VeterinarianAndHoliday> findVeterinarian(String vname);

	int modifyVeterinarian(Veterinarian veterinarianModify);

	int modifyHoliday(Holiday holiday);

}
