package com.kh.project.reservation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DogInformation {
	
	private int dogNo;
	private String dogName;
	private int dogAge;
	private String breed;
	private String dogGender;
	private int userNo;
	

}
