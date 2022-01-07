package com.kh.project.member.model.vo;


import lombok.Data;

@Data
public class Authority {
	
	/* 하나의 AUTHORITY 정보 - 어떤 코드가 어떤 권한명을 가지고 있는지 */
	
	private int code;
	private String name;
	private String desc;
	
	

}
