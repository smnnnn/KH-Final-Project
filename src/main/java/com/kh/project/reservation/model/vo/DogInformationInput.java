package com.kh.project.reservation.model.vo;

/* 진료 받을 동물 정보(예약 시 입력 받은) */
public class DogInformationInput {

	private int reservation_no;			// 예약 번호
	private String in_dog_name;			// 반려견 이름
	private String in_breed;			// 견종
	private String in_dog_gender;		// 반려견 성별
	private int in_dog_age;				// 반려견 나이
	
	public DogInformationInput() {}

	public DogInformationInput(int reservation_no, String in_dog_name, String in_breed, String in_dog_gender,
			int in_dog_age) {
		super();
		this.reservation_no = reservation_no;
		this.in_dog_name = in_dog_name;
		this.in_breed = in_breed;
		this.in_dog_gender = in_dog_gender;
		this.in_dog_age = in_dog_age;
	}

	public int getReservation_no() {
		return reservation_no;
	}

	public void setReservation_no(int reservation_no) {
		this.reservation_no = reservation_no;
	}

	public String getIn_dog_name() {
		return in_dog_name;
	}

	public void setIn_dog_name(String in_dog_name) {
		this.in_dog_name = in_dog_name;
	}

	public String getIn_breed() {
		return in_breed;
	}

	public void setIn_breed(String in_breed) {
		this.in_breed = in_breed;
	}

	public String getIn_dog_gender() {
		return in_dog_gender;
	}

	public void setIn_dog_gender(String in_dog_gender) {
		this.in_dog_gender = in_dog_gender;
	}

	public int getIn_dog_age() {
		return in_dog_age;
	}

	public void setIn_dog_age(int in_dog_age) {
		this.in_dog_age = in_dog_age;
	}

	@Override
	public String toString() {
		return "DogInformationInput [reservation_no=" + reservation_no + ", in_dog_name=" + in_dog_name + ", in_breed="
				+ in_breed + ", in_dog_gender=" + in_dog_gender + ", in_dog_age=" + in_dog_age + "]";
	}

	
	
}
