package com.kh.project.admin.reservationManage.model.vo;

public class Dog {

	private int reservationNo;
	private int no;
	private String name;
	private String breed;
	private String gender;
	private int age;
	
	public Dog() {}

	public Dog(int reservationNo, int no, String name, String breed, String gender, int age) {
		super();
		this.reservationNo = reservationNo;
		this.no = no;
		this.name = name;
		this.breed = breed;
		this.gender = gender;
		this.age = age;
	}

	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Dog [reservationNo=" + reservationNo + ", no=" + no + ", name=" + name + ", breed=" + breed
				+ ", gender=" + gender + ", age=" + age + "]";
	}
	
}
