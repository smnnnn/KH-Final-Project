package com.kh.project.cs.model.vo;

import java.util.Date;

public class Answer {

	private int ANo;
	private String AContent;
	private Date createDate;
	private Object modifyDateLDate;
	private String status;
	private int QNo;
	
	Answer() {}

	public Answer(int aNo, String aContent, Date createDate, Object modifyDateLDate, String status, int qNo) {
		super();
		ANo = aNo;
		AContent = aContent;
		this.createDate = createDate;
		this.modifyDateLDate = modifyDateLDate;
		this.status = status;
		QNo = qNo;
	}

	public int getANo() {
		return ANo;
	}

	public void setANo(int aNo) {
		ANo = aNo;
	}

	public String getAContent() {
		return AContent;
	}

	public void setAContent(String aContent) {
		AContent = aContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Object getModifyDateLDate() {
		return modifyDateLDate;
	}

	public void setModifyDateLDate(Object modifyDateLDate) {
		this.modifyDateLDate = modifyDateLDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQNo() {
		return QNo;
	}

	public void setQNo(int qNo) {
		QNo = qNo;
	}

	@Override
	public String toString() {
		return "Answer [ANo=" + ANo + ", AContent=" + AContent + ", createDate=" + createDate + ", modifyDateLDate="
				+ modifyDateLDate + ", status=" + status + ", QNo=" + QNo + "]";
	}
	
	
}
