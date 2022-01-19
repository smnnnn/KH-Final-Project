package com.kh.project.reservation.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.project.member.model.vo.DogInformation;
import com.kh.project.reservation.model.service.ReservationService;
import com.kh.project.reservation.model.vo.DogInformationInput;
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
	public ModelAndView ReservaionNotice(ModelAndView mv) {
		//공지 사항에 보이는 진료 과목 별 휴무일
		List<VeterinarianAndTreatmentType> HolidayList = reservationService.notice();
		
		mv.addObject("HolidayList", HolidayList);
		mv.setViewName("reservation/notice");
		
		return mv;
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
	
	@GetMapping(value="timeChoice", produces="application/json; charset=utf-8")
	@ResponseBody
	public List<ReservationInfo> selectTime(@RequestParam String vno, @RequestParam String reservation_date){
		//String date = reservation_date.toString();
		/*
		 * log.info(reservation_date); 
		 * log.info(vno);
		 */
		
		return reservationService.selectTime(vno, reservation_date);
	}
	
	@PostMapping("reservation_form")
	public String Reservation_form(@ModelAttribute("reserInfo") ReservationInfo reservationInfo, Principal principal){
			
		/* 입력받은 정보 reserInfo에 넣어두고 화면만 전환
		 * String reser = String.valueOf(reservationInfo); 
		 * log.info(reser);
		 */
	
		return "reservation/reservation_form";
	}
	
	@PostMapping("reservation_form/input")
	public String Reservation_form_input(ReservationInfo reservationInfo, DogInformationInput dogInfo, Principal principal, RedirectAttributes rttr) { 
		
		/* 위 컨트롤러에서 받았던 예약 정보까지 합쳐서 한번에 insert 
		 * dog info input 테이블도 입력받은 정보로 insert 
		 */
		/*
		 * String reser = String.valueOf(reservationInfo); log.info(reser);
		 */
		
		String id = principal.getName();
		reservationService.registReservation(reservationInfo, id);
		
		reservationService.registDog(dogInfo);
		
		rttr.addFlashAttribute("successMessage", "진료 예약이 완료되었습니다.");
		return "redirect:/main";
	}
	
	@GetMapping("reservation_form/input")
	@ResponseBody
	public List<DogInformation> selectDogInfo(Principal principal){
		String id = principal.getName();
		
		return reservationService.selectDogInfo(id);
	}
	
	
}
