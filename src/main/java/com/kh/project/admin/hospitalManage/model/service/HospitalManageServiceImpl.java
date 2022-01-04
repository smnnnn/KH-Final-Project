package com.kh.project.admin.hospitalManage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.project.admin.hospitalManage.model.dao.HospitalManageMapper;
import com.kh.project.hospital.model.vo.MDeviceFile;
import com.kh.project.hospital.model.vo.MedicalDevice;

@Service
public class HospitalManageServiceImpl implements HospitalManageService {

	private HospitalManageMapper hospitalManageMapper;
	
	@Autowired
	public HospitalManageServiceImpl(HospitalManageMapper hospitalManageMapper) {
		this.hospitalManageMapper = hospitalManageMapper;
	}

	@Transactional
	@Override
	public int registMedicalDevice(MedicalDevice medicalDevice) {
		/* MEDICAL_DEVICE 에 삽입 */
		int deviceResult = hospitalManageMapper.registMedicalDevice(medicalDevice);
		
		/* DEVICE_FILE 에 삽입 */
		MDeviceFile mdeviceFile = medicalDevice.getMdeviceFile();
		int fileResult = hospitalManageMapper.registDeviceFile(mdeviceFile);
		
		int result = 0;
		if(deviceResult > 0 && fileResult > 0) result = 1;
		
		return result;
	}

	@Override
	public List<MedicalDevice> selectMedicalDeviceList() {		
		return hospitalManageMapper.selectMedicalDeviceList();
	}

	@Transactional
	@Override
	public MedicalDevice selectMedicalDeviceByNo(int deviceNo) {
		MedicalDevice medicalDevice = hospitalManageMapper.selectMedicalDeviceByNo(deviceNo);
		MDeviceFile mdeviceFile = hospitalManageMapper.selectDeviceFileByNo(deviceNo);
		medicalDevice.setMdeviceFile(mdeviceFile);
		
		return medicalDevice;
	}

	@Transactional
	@Override
	public int modifyMedicalDevice(MedicalDevice medicalDevice) {
		/* MEDICAL_DEVICE 에 수정 */
		int deviceResult = hospitalManageMapper.modifyMedicalDevice(medicalDevice);
		
		/* DEVICE_FILE 에 수정 */
		int fileResult = 1;
		if( medicalDevice.getMdeviceFile() != null) {
			MDeviceFile mdeviceFile = medicalDevice.getMdeviceFile();
			fileResult = hospitalManageMapper.modifyDeviceFile(mdeviceFile);			
		}
		
		int result = 0;
		if(deviceResult > 0 && fileResult > 0) result = 1;
		
		return result;
	}

}
