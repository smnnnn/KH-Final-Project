package com.kh.project.member.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.kh.project.reservation.model.vo.TreatmentType;
import com.kh.project.subAdmin.model.vo.Veterinarian;

public class ReservationSelect {
	private int reservation_no;				// 예약 번호
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date reservation_date;			// 예약 날짜
	private String reservation_time;		// 예약 시간
	private String reservation_status;		// 예약 상태
	private Member user_id;					// 회원 번호
	private String vname;				// 의료진 이름
	private String tname;			// 진료 과목 이름
	
	public ReservationSelect() {}

	public ReservationSelect(int reservation_no, Date reservation_date, String reservation_time, String reservation_status, Member user_id,
			String vname, String tname) {
		super();
		this.reservation_no = reservation_no;
		this.reservation_date = reservation_date;
		this.reservation_time = reservation_time;
		this.reservation_status = reservation_status;
		this.user_id = user_id;
		this.vname = vname;
		this.tname = tname;
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

	public Member getUser_id() {
		return user_id;
	}

	public void setUser_id(Member user_id) {
		this.user_id = user_id;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	@Override
	public String toString() {
		return "ReservationSelect [reservation_no=" + reservation_no + ", reservation_date=" + reservation_date
				+ ", reservation_time=" + reservation_time + ", reservation_status=" + reservation_status + ", user_id="
				+ user_id + ", vname=" + vname + ", tname=" + tname + "]";
	}

	
	
	
}
