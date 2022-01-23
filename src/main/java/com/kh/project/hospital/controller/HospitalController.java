package com.kh.project.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.project.hospital.model.service.HospitalService;
import com.kh.project.hospital.model.vo.MedicalDevice;
import com.kh.project.subAdmin.model.vo.Veterinarian;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/about")
public class HospitalController {
	
	private HospitalService hospitalService;
	
	@Autowired
	public HospitalController(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}
	
	@GetMapping("hospital")
	public String hospitalIntroduce() {
		return "about/hospital";
	}
	
	@GetMapping("team")
	public String hospitalTeamIntroduce(Model model) {
		
		List<Veterinarian> vList = hospitalService.selectVeterinarianList();
		
		if(vList != null) {
			model.addAttribute("vList", vList);
		}
		
		return "about/team";
	}
	
	@GetMapping("device")
	public String medicalDeviceIntroduce(Model model) {
		int category = 5;   // 영상장비
		List<MedicalDevice> deviceList = hospitalService.selectDeviceListByCategory(category);
		model.addAttribute("deviceList", deviceList);
		return "about/device";
	}
	
	@GetMapping("device/{category}")
	@ResponseBody
	public List<MedicalDevice> getDeviceListByCategory(@PathVariable int category) {	
		return hospitalService.selectDeviceListByCategory(category);
	}

	@GetMapping("location")
	public String hospitalLocation() {
		return "about/location";
	}
	
	
}
