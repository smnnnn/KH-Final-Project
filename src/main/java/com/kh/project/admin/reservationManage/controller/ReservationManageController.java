package com.kh.project.admin.reservationManage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.project.admin.common.model.vo.Pagination;
import com.kh.project.admin.common.model.vo.Search;
import com.kh.project.admin.reservationManage.model.service.ReservationManageService;
import com.kh.project.admin.reservationManage.model.vo.ReservationManage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/reservation")
public class ReservationManageController {
	
	private ReservationManageService reservationManageService;
	
	@Autowired
	public ReservationManageController(ReservationManageService reservationManageService) {
		this.reservationManageService = reservationManageService;
	}
	
	@GetMapping("list")
	public String reservationList(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "0") int sort
			, Search search) {
		
		/* 예약 목록 전체 갯수 조회 */
		int totalListCount = reservationManageService.getListCount(sort, search);
		
		/* Pagination 객체 => 5 : 하단에 보여질 페이질 목록 수, 10 : 한 페이지에 보여질 목록 최대 */
		Pagination pagination = new Pagination(page, totalListCount, 5, 10);
		
		/* 예약 목록 조회 => 페이징 처리 */
		int startRow = (pagination.getPage() - 1) * pagination.getListLimit() + 1;
		int endRow = startRow + pagination.getListLimit() - 1;
		
		List<ReservationManage> reservationList = reservationManageService.selectReservationList(startRow, endRow, sort, search);
		
		if(reservationList != null) {
			model.addAttribute("reservationList", reservationList);			
		}
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("sort", sort);
		model.addAttribute("search", search);
		
		return "admin/reservationList";
	}
	
	@GetMapping("detail")
	public String reservationDetail(@RequestParam int no, Model model) {
		
		ReservationManage reservation = reservationManageService.selectReservationByNo(no);
		
		model.addAttribute("reservation", reservation);
		
		return "admin/reservationDetail";
	}
	
	@GetMapping("modify/{reservationNo}")
	public String modifyReservationView(@PathVariable int reservationNo, Model model) {
		
		ReservationManage reservation = reservationManageService.selectReservationByNo(reservationNo);
		model.addAttribute("reservation", reservation);
		
		return "admin/reservationModify";
	}
	
	@PostMapping("modify")
	public String modifyReservation(ReservationManage reservation, RedirectAttributes rttr) {

		int result = reservationManageService.modifyReservation(reservation);
		
		if(result > 0) {
			rttr.addFlashAttribute("reservationResultMessage", "예약 정보 수정에 성공하였습니다.");
		} else {
			rttr.addFlashAttribute("reservationResultMessage", "예약 정보 수정에 실패하였습니다.");
		}
		
		return "redirect:/admin/reservation/list";
	}
	
	@GetMapping("delete")
	public String deleteReservation(@RequestParam("reservationNo") int reservationNo, @RequestParam("dogNo") int dogNo,
			RedirectAttributes rttr) {
		
		int result = reservationManageService.deleteReservation(reservationNo, dogNo);
		
		if(result > 0) {
			rttr.addFlashAttribute("reservationResultMessage", "예약 정보 삭제에 성공하였습니다.");			
		} else {
			rttr.addFlashAttribute("reservationResultMessage", "예약 정보 삭제에 실패하였습니다.");	
		}
		
		return "redirect:/admin/reservation/list";
	}
	
}
