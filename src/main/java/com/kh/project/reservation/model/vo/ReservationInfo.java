package com.kh.project.reservation.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 진료 예약 정보 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInfo {
	private int reservation_no;				// 예약 번호
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date reservation_date;			// 예약 날짜
	private String reservation_time;		// 예약 시간
	private String reservation_status;		// 예약 상태
	private String symptom;					// 증상 및 요청
	private int tno;						// 진료 과목 번호
	private int user_no;					// 회원 번호
	private int vno;						// 의료진 번호
	private int dog_no;						// 동물 번호
	
	
}
