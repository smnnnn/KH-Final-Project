package com.kh.project.hospital.model.vo;

import java.util.Date;

public class MedicalDevice {

	private int deviceNo;
	private String deviceName;
	private String deviceModel;
	private String deviceContent;
	private Date createDate;
	private Date modifyDate;
	private String status;
	private int categoryCode;
	
//	private MDeviceCategory mdeviceCategory;      // 의료장비 카테고리
	private MDeviceFile mdeviceFile;              // 의료장비 파일
	
	public MedicalDevice() {}

	public MedicalDevice(int deviceNo, String deviceName, String deviceModel, String deviceContent, Date createDate,
			Date modifyDate, String status, int categoryCode, MDeviceFile mdeviceFile) {
		super();
		this.deviceNo = deviceNo;
		this.deviceName = deviceName;
		this.deviceModel = deviceModel;
		this.deviceContent = deviceContent;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
		this.categoryCode = categoryCode;
		this.mdeviceFile = mdeviceFile;
	}

	public int getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(int deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceContent() {
		return deviceContent;
	}

	public void setDeviceContent(String deviceContent) {
		this.deviceContent = deviceContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public MDeviceFile getMdeviceFile() {
		return mdeviceFile;
	}

	public void setMdeviceFile(MDeviceFile mdeviceFile) {
		this.mdeviceFile = mdeviceFile;
	}

	@Override
	public String toString() {
		return "MedicalDevice [deviceNo=" + deviceNo + ", deviceName=" + deviceName + ", deviceModel=" + deviceModel
				+ ", deviceContent=" + deviceContent + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", status=" + status + ", categoryCode=" + categoryCode + ", mdeviceFile=" + mdeviceFile + "]";
	}


}
