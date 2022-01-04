package com.kh.project.subAdmin.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kh.project.subAdmin.model.dao.SubAdminMapper;
import com.kh.project.subAdmin.model.vo.Veterinarian;

@Service("veterinarianService")
public class VeterinarianServiceImpl implements VeterinarianService{

	private final SubAdminMapper subAdminMapper;
	
	@Autowired
	public VeterinarianServiceImpl(SubAdminMapper subAdminMapper) {
		this.subAdminMapper = subAdminMapper;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.SERIALIZABLE, rollbackFor= {Exception.class})
	public int registVeterinarian(Veterinarian newVeterinarian) {
		return subAdminMapper.insertVeterinarian(newVeterinarian);
	}

	
}
