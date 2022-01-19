package com.kh.project.hospital.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.project.hospital.model.dao.HospitalMapper;
import com.kh.project.hospital.model.vo.MedicalDevice;
import com.kh.project.subAdmin.model.vo.Veterinarian;

@Service
public class HospitalServiceImpl implements HospitalService {

	private HospitalMapper hospitalMapper;
	
	@Autowired
	public HospitalServiceImpl(HospitalMapper hospitalMapper) {
		this.hospitalMapper = hospitalMapper;
	}
	
	@Override
	public List<Veterinarian> selectVeterinarianList() {
		return hospitalMapper.selectVeterinarianList();
	}

	@Override
	public List<MedicalDevice> selectDeviceListByCategory(int category) {	
		return hospitalMapper.selectDeviceListByCategory(category);
	}

}
