package com.kh.project.admin.hospitalManage.model.service;

import java.util.List;

import com.kh.project.admin.common.model.vo.Search;
import com.kh.project.hospital.model.vo.MDeviceFile;
import com.kh.project.hospital.model.vo.MedicalDevice;

public interface HospitalManageService {

	int registMedicalDevice(MedicalDevice medicalDevice);

	List<MedicalDevice> selectMedicalDeviceList(int startRow, int endRow, int sort, Search search);

	MedicalDevice selectMedicalDeviceByNo(int deviceNo);

	int modifyMedicalDevice(MedicalDevice medicalDevice);

	int deleteDevice(int deviceNo);

	MDeviceFile selectDeviceFile(int deviceNo);

	int getListCount(int sort, Search search);
	
	
}
