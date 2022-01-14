package com.kh.project.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DogInformation {
	
	private int no;
	private String petName;
	private int petAge;
	private String breed;
	private String status;
	private String petGender;
	private int userNo;
	
//필드명 html name 일치 시켜야 자동 넣어줌 안 그면 일치값없음
	
	
	

}
