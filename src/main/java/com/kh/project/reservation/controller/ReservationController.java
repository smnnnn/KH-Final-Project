package com.kh.project.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.project.reservation.model.service.ReservationService;
import com.kh.project.reservation.model.vo.ReservationInfo;
import com.kh.project.reservation.model.vo.VeterinarianAndTreatmentType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/reservation")
public class ReservationController {
	
	private ReservationService reservationService;
	
	@Autowired
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("notice")
	public String ReservaionNotice() {
		return "reservation/notice";
	}
	
	@GetMapping("treatmentType")
	public String ReservationTreatmentType() {
		return "reservation/treatmentType";
	}
	
	@GetMapping("reservation_typeChoice")
	public String Reservation_typeChoice() {
		return "reservation/reservation_typeChoice";
	}
	
	@PostMapping("reservation_timeChoice")
	public ModelAndView Reservation_timeChoice(@RequestParam("tno") int tno, ModelAndView mv, RedirectAttributes rttr) {
		
		/* 진료 유형을 선택하면 진료 시간 선택으로 화면은 넘어가고
		 * 선택한 유형에 맞게 진료 유형의 이름과 담당의의 이름이 표시되어야 하기때문에
		 * 진료 과목과 담당의의 이름을 알아오기 위한 select 구현 */
		List<VeterinarianAndTreatmentType> VList = reservationService.findVname(tno);
		
		mv.addObject("VList", VList);
		mv.setViewName("reservation/reservation_timeChoice");
		rttr.addFlashAttribute("tno", tno);
		
		return mv;
	}
	
	@PostMapping("reservation_form")
	public String Reservation_form(ReservationInfo reservationInfo){
		
		/*ReservationInfo reservationInfo, , @RequestParam("reservation_date") @DateTimeFormat(pattern="yyyy-MM-dd") Date formatDate*/
		
		/*
		 * SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd"); 
		 * String da = formatDate.format(reservationInfo.getReservation_date());  => 이러면 yyyy-MM-dd 형태로 들어감
		 * Date to = formatDate.parse(da); => 이러면 다시 제자리로..
		 */
		
		 /* 왜 날짜 형태가 포멧 안되고 Sun Jan 16 00:00:00 KST 2022 형태로 들어갈까 */
		
		String reser = String.valueOf(reservationInfo);
		log.info(reser);
		
		reservationService.inputReser(reservationInfo);
		
		return "reservation/reservation_form";
	}
}
