package com.kh.project.reservation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 진료 과목 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentType {
	
	private int tno;			// 진료 과목 번호
	private String tname;		// 진료 과목 명
	
	
}
