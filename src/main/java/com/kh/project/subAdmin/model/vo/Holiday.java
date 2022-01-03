package com.kh.project.subAdmin.model.vo;

/* 휴무일 */
public class Holiday {

	private String day;		// 휴무 요일
	private int vno; 		// 의사 번호
	
	public Holiday() {}

	public Holiday(String day, int vno) {
		super();
		this.day = day;
		this.vno = vno;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getVno() {
		return vno;
	}

	public void setVno(int vno) {
		this.vno = vno;
	}

	@Override
	public String toString() {
		return "Holiday [day=" + day + ", vno=" + vno + "]";
	}
	
	
}
