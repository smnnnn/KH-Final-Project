package com.kh.project.reservation.model.vo;

import org.springframework.format.annotation.DateTimeFormat;

import com.kh.project.subAdmin.model.vo.Holiday;

public class VeterinarianAndReservationInfo {
	private int vno;							// 의료진 번호
	private String vname;						// 의료진 이름
	private TreatmentType treatment;			// 진료 과목 번호, 과목 명
	private Holiday day;						// 휴무일
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private ReservationInfo reservationInfo;	// 예약 번호, 날짜, 시간, 상태

	public VeterinarianAndReservationInfo() {}

	public VeterinarianAndReservationInfo(int vno, String vname, TreatmentType treatment, Holiday day,
			ReservationInfo reservationInfo) {
		super();
		this.vno = vno;
		this.vname = vname;
		this.treatment = treatment;
		this.day = day;
		this.reservationInfo = reservationInfo;
	}

	public int getVno() {
		return vno;
	}

	public void setVno(int vno) {
		this.vno = vno;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public TreatmentType getTreatment() {
		return treatment;
	}

	public void setTreatment(TreatmentType treatment) {
		this.treatment = treatment;
	}

	public Holiday getDay() {
		return day;
	}

	public void setDay(Holiday day) {
		this.day = day;
	}

	public ReservationInfo getReservationInfo() {
		return reservationInfo;
	}

	public void setReservationInfo(ReservationInfo reservationInfo) {
		this.reservationInfo = reservationInfo;
	}

	@Override
	public String toString() {
		return "VeterinarianAndReservationInfo [vno=" + vno + ", vname=" + vname + ", treatment=" + treatment + ", day="
				+ day + ", reservationInfo=" + reservationInfo + "]";
	}

	
	
	
}
