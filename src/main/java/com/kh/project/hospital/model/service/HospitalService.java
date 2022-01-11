package com.kh.project.hospital.model.service;

import java.util.List;

import com.kh.project.hospital.model.vo.MedicalDevice;
import com.kh.project.subAdmin.model.vo.Veterinarian;

public interface HospitalService {

	List<Veterinarian> selectVeterinarianList();

	List<MedicalDevice> selectDeviceListByCategory(int category);



}
