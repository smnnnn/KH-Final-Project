package com.kh.project.reservation.model.vo;

public class VeterinarianAndTreatmentType {

	private int vno;							// 의료진 번호
	private String vname;						// 의료진 이름
	private int tno;							// 진료 과목 번호
	private String tname;						// 진료 과목 이름
	private String status;						// 의료진 등록 상태
	private ReservationInfo reservationInfo;	// 예약 정보
	private String day;						// 의료진 휴무일

	public VeterinarianAndTreatmentType() {}

	public VeterinarianAndTreatmentType(int vno, String vname, int tno, String tname, String status,
			ReservationInfo reservationInfo, String day) {
		super();
		this.vno = vno;
		this.vname = vname;
		this.tno = tno;
		this.tname = tname;
		this.status = status;
		this.reservationInfo = reservationInfo;
		this.day = day;
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

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ReservationInfo getReservationInfo() {
		return reservationInfo;
	}

	public void setReservationInfo(ReservationInfo reservationInfo) {
		this.reservationInfo = reservationInfo;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "VeterinarianAndTreatmentType [vno=" + vno + ", vname=" + vname + ", tno=" + tno + ", tname=" + tname
				+ ", status=" + status + ", reservationInfo=" + reservationInfo + ", day=" + day + "]";
	}

	
	
}
