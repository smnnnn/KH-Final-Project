package com.kh.project.admin.hospitalManage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.project.admin.common.model.vo.Search;
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
	public int getListCount(int sort, Search search) {
		search.setSort(sort);
		return hospitalManageMapper.getListCount(search);
	}

	@Override
	public List<MedicalDevice> selectMedicalDeviceList(int startRow, int endRow, int sort, Search search) {	
		search.setStartRow(startRow);
		search.setEndRow(endRow);
		search.setSort(sort);
		
		return hospitalManageMapper.selectMedicalDeviceList(search);
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

	@Transactional
	@Override
	public int deleteDevice(int deviceNo) {
		/* MEDICAL_DEVICE status 'N'으로 변경 */
		int deviceResult = hospitalManageMapper.deleteDevice(deviceNo);
		
		/* DEVICE_FILE 삭제 */
		int fileResult = hospitalManageMapper.deleteDeviceFile(deviceNo);
		
		return deviceResult > 0 && fileResult > 0 ? 1 : 0;
	}

	@Override
	public MDeviceFile selectDeviceFile(int deviceNo) {
		return hospitalManageMapper.selectDeviceFileByNo(deviceNo);
	}


}
