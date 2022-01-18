package com.kh.project.subAdmin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 휴무일 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Holiday {

	private String day;		// 휴무 요일
	private int vno; 		// 의사 번호
	
		
}
