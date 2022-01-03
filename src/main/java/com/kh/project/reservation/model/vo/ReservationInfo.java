package com.kh.project.reservation.model.vo;

import java.util.Date;

/* 진료 예약 정보 */
public class ReservationInfo {
	private int reservation_no;				// 예약 번호
	private Date reservation_date;			// 예약 날짜
	private String reservation_time;		// 예약 시간
	private String reservation_status;		// 예약 상태
	private String symptom;					// 증상 및 요청
	private int tno;						// 진료 과목 번호
	private int user_no;					// 회원 번호
	private int vno;						// 의료진 번호
	private int dog_no;						// 동물 번호
	
	public ReservationInfo (){}

	public ReservationInfo(int reservation_no, Date reservation_date, String reservation_time,
			String reservation_status, String symptom, int tno, int user_no, int vno, int dog_no) {
		super();
		this.reservation_no = reservation_no;
		this.reservation_date = reservation_date;
		this.reservation_time = reservation_time;
		this.reservation_status = reservation_status;
		this.symptom = symptom;
		this.tno = tno;
		this.user_no = user_no;
		this.vno = vno;
		this.dog_no = dog_no;
	}

	public int getReservation_no() {
		return reservation_no;
	}

	public void setReservation_no(int reservation_no) {
		this.reservation_no = reservation_no;
	}

	public Date getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}

	public String getReservation_time() {
		return reservation_time;
	}

	public void setReservation_time(String reservation_time) {
		this.reservation_time = reservation_time;
	}

	public String getReservation_status() {
		return reservation_status;
	}

	public void setReservation_status(String reservation_status) {
		this.reservation_status = reservation_status;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getVno() {
		return vno;
	}

	public void setVno(int vno) {
		this.vno = vno;
	}

	public int getDog_no() {
		return dog_no;
	}

	public void setDog_no(int dog_no) {
		this.dog_no = dog_no;
	}

	@Override
	public String toString() {
		return "ReservationInfo [reservation_no=" + reservation_no + ", reservation_date=" + reservation_date
				+ ", reservation_time=" + reservation_time + ", reservation_status=" + reservation_status + ", symptom="
				+ symptom + ", tno=" + tno + ", user_no=" + user_no + ", vno=" + vno + ", dog_no=" + dog_no + "]";
	}
	
	
}
