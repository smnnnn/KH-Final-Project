package com.kh.project.admin.hospitalManage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.hospital.model.vo.MDeviceFile;
import com.kh.project.hospital.model.vo.MedicalDevice;

@Mapper
public interface HospitalManageMapper {
	
	int registMedicalDevice(MedicalDevice medicalDevice);
	
	int registDeviceFile(MDeviceFile mdeviceFile);

	List<MedicalDevice> selectMedicalDeviceList();

	MedicalDevice selectMedicalDeviceByNo(int deviceNo);

	MDeviceFile selectDeviceFileByNo(int deviceNo);

	int modifyMedicalDevice(MedicalDevice medicalDevice);

	int modifyDeviceFile(MDeviceFile mdeviceFile);

	int deleteDevice(int deviceNo);

	int deleteDeviceFile(int deviceNo);

}
