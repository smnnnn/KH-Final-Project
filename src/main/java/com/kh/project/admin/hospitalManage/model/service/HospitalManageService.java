package com.kh.project.admin.hospitalManage.model.service;

import java.util.List;

import com.kh.project.hospital.model.vo.MedicalDevice;

public interface HospitalManageService {

	int registMedicalDevice(MedicalDevice medicalDevice);

	List<MedicalDevice> selectMedicalDeviceList();

	MedicalDevice selectMedicalDeviceByNo(int deviceNo);

	int modifyMedicalDevice(MedicalDevice medicalDevice);
	
	
}
