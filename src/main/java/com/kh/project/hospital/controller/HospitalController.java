package com.kh.project.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class HospitalController {
	
	@GetMapping("hospital")
	public String hospitalIntroduce() {
		return "about/hospital";
	}
	
	@GetMapping("team")
	public String hospitalTeamIntroduce() {
		return "about/team";
	}
	
	@GetMapping("device")
	public String MedicalDeviceIntroduce() {
		return "about/device";
	}

	@GetMapping("location")
	public String hospitalLocation() {
		return "about/location";
	}
	
	
}
