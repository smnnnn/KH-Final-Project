package com.kh.project.member.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority {
	
	/* 하나의 AUTHORITY 정보 - 어떤 코드가 어떤 권한명을 가지고 있는지 */
	
	private int code;
	private String name;
	private String desc;
	
//	public Authority() {};
//	
//	public Authority(int code, String name, String desc) {
//		super();
//		this.code = code;
//		this.name = name;
//		this.desc = desc;
//	}
//	
	
	
	

}
