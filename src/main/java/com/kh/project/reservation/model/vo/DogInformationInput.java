package com.kh.project.reservation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 진료 받을 동물 정보(예약 시 입력 받은) */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DogInformationInput {

	private int reservation_no;			// 예약 번호
	private String in_dog_name;			// 반려견 이름
	private String in_breed;			// 견종
	private String in_dog_gender;		// 반려견 성별
	private int in_dog_age;				// 반려견 나이
	
	
}
