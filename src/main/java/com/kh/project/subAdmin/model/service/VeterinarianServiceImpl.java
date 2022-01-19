package com.kh.project.subAdmin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.project.subAdmin.model.dao.SubAdminMapper;
import com.kh.project.subAdmin.model.vo.Holiday;
import com.kh.project.subAdmin.model.vo.Veterinarian;

@Service("veterinarianService")
public class VeterinarianServiceImpl implements VeterinarianService{

	private final SubAdminMapper subAdminMapper;
	
	@Autowired
	public VeterinarianServiceImpl(SubAdminMapper subAdminMapper) {
		this.subAdminMapper = subAdminMapper;
	}
	

	@Override
	public int registVeterinarian(Veterinarian newVeterinarian) {
		return subAdminMapper.registVeterinarian(newVeterinarian);
	}


	@Override
	public int registHoliday(Holiday holiday) {
		return subAdminMapper.registHoliday(holiday);
	}


	@Override
	public List<Veterinarian> findVeterinarian(String vname) {
		return subAdminMapper.findVeterinarian(vname);
	}


	@Override
	public int modifyVeterinarian(Veterinarian veterinarianModify) {
		return subAdminMapper.modifyVeterinarian(veterinarianModify);
	}


	@Override
	public int modifyHoliday(Holiday holiday) {
		return subAdminMapper.modifyHoliday(holiday);
	}

	
}
