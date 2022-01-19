package com.kh.project.subAdmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.project.subAdmin.model.service.VeterinarianService;
import com.kh.project.subAdmin.model.vo.Holiday;
import com.kh.project.subAdmin.model.vo.Veterinarian;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/")
public class SubManageController {
	
	private VeterinarianService veterinarianService;
	
	@Autowired
	public SubManageController(VeterinarianService veterinarianService) {
		this.veterinarianService = veterinarianService;
		
	}
	
	
	@GetMapping("sub_veterinarianModify")
	public String veterinarianModifyPage() {
		return "admin/sub_veterinarianModify";
	}
	
	@GetMapping("sub_veterinarianRegist")
	public String veterinarianRegistPage() {
		return "admin/sub_veterinarianRegist";
	}
	
	/* 의료진 등록 */
	@PostMapping("sub_veterinarianRegist")
	public String veterinarianRegist(Veterinarian newVerterinarian, RedirectAttributes rttr, Holiday holiday) {	
		
		/* 의료진을 등록할 때 의료진 테이블과 휴무일 테이블에 모두 insert가 필요 */
		veterinarianService.registVeterinarian(newVerterinarian);
		veterinarianService.registHoliday(holiday);
		
		rttr.addFlashAttribute("successMessage", "의료진 등록이 완료되었습니다.");
		
		return "redirect:/admin/sub_veterinarianRegist";	
	}

	
	/* 의료진 검색 */
	@GetMapping("/sub_veterinarianModify/vname/{vname}")
	@ResponseBody
	public List<Veterinarian> findVeterinarianList(@PathVariable String vname){
		/*log.info(vname);*/
		
		return veterinarianService.findVeterinarian(vname);
	}
	
	
	/* 의료진 정보 수정 */
	@PostMapping("sub_veterinarianModify")
	public String veterinarianModify(Veterinarian VeterinarianModify, RedirectAttributes rttr, Holiday holiday) {
		
		veterinarianService.modifyVeterinarian(VeterinarianModify);
		veterinarianService.modifyHoliday(holiday);
		
		rttr.addFlashAttribute("successMessage", "의료진 정보 수정이 완료되었습니다.");
		
		return "redirect:/admin/sub_veterinarianModify";	
	}
}

