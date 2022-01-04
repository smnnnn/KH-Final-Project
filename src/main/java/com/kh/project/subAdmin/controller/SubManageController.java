package com.kh.project.subAdmin.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.project.subAdmin.model.service.VeterinarianService;
import com.kh.project.subAdmin.model.vo.Veterinarian;

@Controller
@RequestMapping("/admin/")
public class SubManageController {
	
	private VeterinarianService veterinarianService;
	private MessageSource messageSource;
	
	@Autowired
	public SubManageController(VeterinarianService veterinarianService, MessageSource messageSource) {
		this.veterinarianService = veterinarianService;
		this.messageSource = messageSource;
	}
	
	@GetMapping("sub_holiday")
	public String holidayManage() {
		return "admin/sub_holiday";
	}
	
	
	@GetMapping("sub_veterinarianModify")
	public String veterinarianModify() {
		return "admin/sub_veterinarianModify";
	}
	
	@GetMapping("sub_veterinarianRegist")
	public String veterinarianRegistPage() {
		return "admin/sub_veterinarianRegist";
	}
	
	@PostMapping("sub_veterinarianRegist")
	public String veterinarianRegist(Veterinarian newVerterinarian, RedirectAttributes rttr, Locale locale) {
		
		veterinarianService.registVeterinarian(newVerterinarian);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("registMessage",null, locale));
		
		return "redirect:/admin/sub_veterinarianRegist";
		
	}

}
