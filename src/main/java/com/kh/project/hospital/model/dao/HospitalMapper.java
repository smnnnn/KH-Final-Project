package com.kh.project.hospital.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.hospital.model.vo.MedicalDevice;
import com.kh.project.subAdmin.model.vo.Veterinarian;

@Mapper
public interface HospitalMapper {

	List<Veterinarian> selectVeterinarianList();

	List<MedicalDevice> selectDeviceListByCategory(int category);

}
