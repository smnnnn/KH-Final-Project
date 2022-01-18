package com.kh.project.subAdmin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 의료진 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veterinarian {

	private int vno;			// 의료진 번호
	private String vname;		// 의료진 이름
	private int tno;			// 진료 과목 번호
	private String profile;		// 약력 및 기본 정보
	private String career;		// 경력 사항
	private String status;		// 의료진 등록 상태
	private String phone;		// 연락처
	
	
	
}
