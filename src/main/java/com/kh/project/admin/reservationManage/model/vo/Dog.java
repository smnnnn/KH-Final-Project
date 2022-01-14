package com.kh.project.admin.reservationManage.model.vo;

import lombok.Data;

@Data
public class Dog {

	private int reservationNo;
	private int no;
	private String name;
	private String breed;
	private String gender;
	private int age;
	private String neuteredStatus;  // 중성화유무


}
