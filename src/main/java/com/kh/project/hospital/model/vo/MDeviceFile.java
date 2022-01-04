package com.kh.project.hospital.model.vo;

public class MDeviceFile {

	private int dfileNo;
	private String dchangeName;
	private String dfilePath;
	private int deviceNo;
	private String deletedName;
	
	public MDeviceFile() {}

	public MDeviceFile(int dfileNo, String dchangeName, String dfilePath, int deviceNo, String deletedName) {
		super();
		this.dfileNo = dfileNo;
		this.dchangeName = dchangeName;
		this.dfilePath = dfilePath;
		this.deviceNo = deviceNo;
		this.deletedName = deletedName;
	}

	public int getDfileNo() {
		return dfileNo;
	}

	public void setDfileNo(int dfileNo) {
		this.dfileNo = dfileNo;
	}

	public String getDchangeName() {
		return dchangeName;
	}

	public void setDchangeName(String dchangeName) {
		this.dchangeName = dchangeName;
	}

	public String getDfilePath() {
		return dfilePath;
	}

	public void setDfilePath(String dfilePath) {
		this.dfilePath = dfilePath;
	}

	public int getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(int deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeletedName() {
		return deletedName;
	}

	public void setDeletedName(String deletedName) {
		this.deletedName = deletedName;
	}

	@Override
	public String toString() {
		return "MDeviceFile [dfileNo=" + dfileNo + ", dchangeName=" + dchangeName + ", dfilePath=" + dfilePath
				+ ", deviceNo=" + deviceNo + ", deletedName=" + deletedName + "]";
	}

	
}
