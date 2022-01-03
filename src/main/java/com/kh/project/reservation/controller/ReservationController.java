package com.kh.project.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

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
}
