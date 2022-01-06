package com.kh.project.subAdmin.model.vo;


/* 의료진 */
public class Veterinarian {

	private int vno;			// 의료진 번호
	private String vname;		// 의료진 이름
	private int tno;			// 진료 과목 번호
	private String profile;		// 약력 및 기본 정보
	private String career;		// 경력 사항
	private String status;		// 의료진 등록 상태
	private String phone;		// 연락처
	
	public Veterinarian() {}

	public Veterinarian(int vno, String vname, int tno, String profile, String career, String status, String phone) {
		super();
		this.vno = vno;
		this.vname = vname;
		this.tno = tno;
		this.profile = profile;
		this.career = career;
		this.status = status;
		this.phone = phone;
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

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Veterinarian [vno=" + vno + ", vname=" + vname + ", tno=" + tno + ", profile=" + profile + ", career="
				+ career + ", status=" + status + ", phone=" + phone + "]";
	}

	
	
}
