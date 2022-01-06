package com.kh.project.hospital.model.vo;

public class MDeviceCategory {

	private int categoryCode;
	private String categoryName;
	
	public MDeviceCategory() {}

	public MDeviceCategory(int categoryCode, String categoryName) {
		super();
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "MDeviceCategory [categoryCode=" + categoryCode + ", categoryName=" + categoryName + "]";
	}
	
	
}
