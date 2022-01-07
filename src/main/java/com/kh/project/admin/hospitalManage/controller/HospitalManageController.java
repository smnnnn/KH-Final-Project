package com.kh.project.admin.hospitalManage.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.project.admin.hospitalManage.model.service.HospitalManageService;
import com.kh.project.hospital.model.vo.MDeviceFile;
import com.kh.project.hospital.model.vo.MedicalDevice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/hospital")
public class HospitalManageController {
	
	private HospitalManageService hospitalManageService;
	
	@Autowired
	public HospitalManageController(HospitalManageService hospitalManageService) {
		this.hospitalManageService = hospitalManageService;
	}

	@GetMapping("deviceList")
	public String deviceManageList(Model model) {
		
		List<MedicalDevice> mdeviceList = hospitalManageService.selectMedicalDeviceList();
		if(mdeviceList != null) {
			model.addAttribute("mdeviceList", mdeviceList);
		}
		
		return "admin/deviceList";
	}
	
	@GetMapping("registDevice")
	public String registDeviceView() {
		return "admin/deviceRegist";
	}
	
	@GetMapping("deviceDetail")
	public String deviceDetailView(@RequestParam("no") int deviceNo, Model model) {
		
		MedicalDevice medicalDevice = hospitalManageService.selectMedicalDeviceByNo(deviceNo);
		if(medicalDevice != null) {
			model.addAttribute("device", medicalDevice);
		}		
		return "admin/deviceDetail";
	}
	
	
	@PostMapping("registDevice")
	public String registDevice(MedicalDevice medicalDevice, @RequestParam MultipartFile deviceFile, 
			HttpServletRequest request, RedirectAttributes rttr) throws IllegalStateException, IOException {
		
		if(!deviceFile.isEmpty()) {
			// 파일명 변경
			String originName = deviceFile.getOriginalFilename();
			String ext = originName.substring(originName.lastIndexOf("."));	
			String changeName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			// 파일경로 지정 및 저장
			String root = request.getSession().getServletContext().getRealPath("/");
			String uploadPath = root + "uploadFiles\\device";
			
			File file = new File(uploadPath);
			if(!file.exists()) file.mkdirs();
			
			deviceFile.transferTo(new File(uploadPath + "\\" + changeName));
			
			MDeviceFile mdeviceFile = new MDeviceFile();
			mdeviceFile.setDchangeName(changeName);
			mdeviceFile.setDfilePath("/uploadFiles/device/");
			
			medicalDevice.setMdeviceFile(mdeviceFile);
			
			// 비즈니스 로직
			int result = hospitalManageService.registMedicalDevice(medicalDevice);
			
			if(result > 0) {
				rttr.addFlashAttribute("deviceResultMessage", "장비등록에 성공하였습니다.");
			} else {
				File failFile = new File(uploadPath + "\\" + changeName);
				failFile.delete();
				rttr.addFlashAttribute("deviceResultMessage", "장비등록에 실패하였습니다.");				
			}
		}
		
		return "redirect:/admin/hospital/deviceList";
	}
	
	@GetMapping("modifyDevice/{deviceNo}")
	public String modifyDeviceView(@PathVariable int deviceNo, Model model) {
		
		MedicalDevice medicalDevice = hospitalManageService.selectMedicalDeviceByNo(deviceNo);
		if(medicalDevice != null) {
			model.addAttribute("device", medicalDevice);
		}		
		
		return "admin/deviceModify";
	}
	
	@PostMapping("modifyDevice")
	public String modifyDevice(MedicalDevice medicalDevice, @RequestParam MultipartFile deviceFile, @RequestParam String originFileName,
			HttpServletRequest request, RedirectAttributes rttr) throws IllegalStateException, IOException {
		
		String root = request.getSession().getServletContext().getRealPath("/");
		String uploadPath = root + "uploadFiles\\device";
		
		MDeviceFile mdeviceFile = new MDeviceFile();
		
		if(!deviceFile.isEmpty()) {
			// 파일변경이 이루어졌다는 뜻
			// 파일명 변경
			String originName = deviceFile.getOriginalFilename();
			String ext = originName.substring(originName.lastIndexOf("."));	
			String changeName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			// 파일경로 지정 및 저장
			File file = new File(uploadPath);
			if(!file.exists()) file.mkdirs();
			deviceFile.transferTo(new File(uploadPath + "\\" + changeName));
			
			mdeviceFile.setDchangeName(changeName);
			mdeviceFile.setDfilePath("/uploadFiles/device/");
			mdeviceFile.setDeviceNo(medicalDevice.getDeviceNo());
			mdeviceFile.setDeletedName(originFileName);   // 장비 수정 전 파일명	
			
			medicalDevice.setMdeviceFile(mdeviceFile);			
		}
			
		// 비즈니스 로직
		int result = hospitalManageService.modifyMedicalDevice(medicalDevice);
		
		if(result > 0) {
			// 기존 파일삭제
			if(mdeviceFile != null) {
				File deletedFile = new File(uploadPath + "\\" + mdeviceFile.getDeletedName());
				deletedFile.delete();
			}
			rttr.addFlashAttribute("deviceResultMessage", "장비 정보 수정에 성공하였습니다.");
			
		} else {
			File failFile = new File(uploadPath + "\\" + medicalDevice.getMdeviceFile().getDchangeName());
			failFile.delete();
			rttr.addFlashAttribute("deviceResultMessage", "장비 정보 수정에 실패하였습니다.");				
		}
		
		return "redirect:/admin/hospital/deviceList";
	}
	
	@GetMapping("deleteDevice/{deviceNo}")
	public String deleteDevice(@PathVariable int deviceNo, RedirectAttributes rttr, HttpServletRequest request) {

		String root = request.getSession().getServletContext().getRealPath("/");
		String filePath = root + "uploadFiles\\device";
		
		// 해당장비의 파일정보
		MDeviceFile mdeviceFile = hospitalManageService.selectDeviceFile(deviceNo);
		
		int result = hospitalManageService.deleteDevice(deviceNo);
		
		if(result > 0) {
			File deleteFile = new File(filePath + "\\" + mdeviceFile.getDchangeName());
			deleteFile.delete();
			
			rttr.addFlashAttribute("deviceResultMessage", "장비 정보 삭제에 성공하였습니다.");
		}
		
		return "redirect:/admin/hospital/deviceList";
	}
	
	
	
	
	
	
}
