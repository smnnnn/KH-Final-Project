package com.kh.project.reservation.model.vo;


/* 진료 과목 */
public class TreatmentType {
	
	private int tno;			// 진료 과목 번호
	private String tname;		// 진료 과목 명
	
	public TreatmentType(){}

	public TreatmentType(int tno, String tname) {
		super();
		this.tno = tno;
		this.tname = tname;
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

	@Override
	public String toString() {
		return "TreatmentType [tno=" + tno + ", tname=" + tname + "]";
	}
	
	

}
