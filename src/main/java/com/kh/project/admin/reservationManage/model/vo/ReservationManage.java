package com.kh.project.admin.reservationManage.model.vo;

import java.sql.Date;

public class ReservationManage {
	
	private int reservationNo;           // 예약번호
	private int tNo;                     // 진료과목코드
	private String tName;                // 진료과목명
 	private String vName;                // 수의사
	private Date reservationDate;        // 예약일
	private String reservationTime;      // 예약시간
	private String userName;             // 예약자이름
	private String phone;                // 전화번호
	private int dogNo;					 // 견 번호
	private String symptom;              // 증상
	private String reservationStatus;    // 예약상태
	
	private Dog dog;                     // 견 정보
	
	public ReservationManage() {}

	public ReservationManage(int reservationNo, int tNo, String tName, String vName, Date reservationDate,
			String reservationTime, String userName, String phone, int dogNo, String symptom, String reservationStatus,
			Dog dog) {
		super();
		this.reservationNo = reservationNo;
		this.tNo = tNo;
		this.tName = tName;
		this.vName = vName;
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
		this.userName = userName;
		this.phone = phone;
		this.dogNo = dogNo;
		this.symptom = symptom;
		this.reservationStatus = reservationStatus;
		this.dog = dog;
	}

	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}

	public int gettNo() {
		return tNo;
	}

	public void settNo(int tNo) {
		this.tNo = tNo;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getDogNo() {
		return dogNo;
	}

	public void setDogNo(int dogNo) {
		this.dogNo = dogNo;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	@Override
	public String toString() {
		return "ReservationManage [reservationNo=" + reservationNo + ", tNo=" + tNo + ", tName=" + tName + ", vName="
				+ vName + ", reservationDate=" + reservationDate + ", reservationTime=" + reservationTime
				+ ", userName=" + userName + ", phone=" + phone + ", dogNo=" + dogNo + ", symptom=" + symptom
				+ ", reservationStatus=" + reservationStatus + ", dog=" + dog + "]";
	}


}
