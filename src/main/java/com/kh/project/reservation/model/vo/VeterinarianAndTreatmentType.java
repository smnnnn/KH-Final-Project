package com.kh.project.reservation.model.vo;

public class VeterinarianAndTreatmentType {

	private int vno;			// 의료진 번호
	private String vname;		// 의료진 이름
	private TreatmentType tno;			// 진료 과목 번호
	private String status;		// 의료진 등록 상태

	public VeterinarianAndTreatmentType() {}

	public VeterinarianAndTreatmentType(int vno, String vname, TreatmentType tno, String status) {
		super();
		this.vno = vno;
		this.vname = vname;
		this.tno = tno;
		this.status = status;
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

	public TreatmentType getTno() {
		return tno;
	}

	public void setTno(TreatmentType tno) {
		this.tno = tno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "VeterinarianAndTreatmentType [vno=" + vno + ", vname=" + vname + ", tno=" + tno + ", status=" + status
				+ "]";
	};
	
	
}
