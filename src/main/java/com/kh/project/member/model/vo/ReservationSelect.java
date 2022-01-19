package com.kh.project.member.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kh.project.reservation.model.vo.TreatmentType;
import com.kh.project.subAdmin.model.vo.Veterinarian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationSelect {
	private int reservation_no;				// 예약 번호
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date reservation_date;			// 예약 날짜
	private String reservation_time;		// 예약 시간
	private String reservation_status;		// 예약 상태
	private Member user_id;					// 회원 번호
	private String vname;					// 의료진 이름
	private String tname;					// 진료 과목 이름
	
	
}
