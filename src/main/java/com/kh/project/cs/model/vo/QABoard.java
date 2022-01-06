package com.kh.project.cs.model.vo;

import java.util.Date;

public class QABoard {

	private int QNo;
	private String QTitle;
	private int QHit;
	private Date createDate;
	private Date modifyDate;
	private String QContent;
	private String status;
	private String secretStatus;
	private String userName;
	private String categoryName;
	private Answer answer;
	private int userNo;
	private int categoryNo;
	
	private String userId;
	
	QABoard() {}
	
	

	public QABoard(int qNo, String qTitle, int qHit, Date createDate, Date modifyDate, String qContent, String status,
			String secretStatus, String userName, String categoryName, Answer answer, int userNo, int categoryNo) {
		super();
		QNo = qNo;
		QTitle = qTitle;
		QHit = qHit;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		QContent = qContent;
		this.status = status;
		this.secretStatus = secretStatus;
		this.userName = userName;
		this.categoryName = categoryName;
		this.answer = answer;
		this.userNo = userNo;
		this.categoryNo = categoryNo;
	}



	public QABoard(int qNo, String qTitle, int qHit, Date createDate, Date modifyDate, String qContent, String status,
			String secretStatus, String userName, String categoryName) {
		super();
		QNo = qNo;
		QTitle = qTitle;
		QHit = qHit;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		QContent = qContent;
		this.status = status;
		this.secretStatus = secretStatus;
		this.userName = userName;
		this.categoryName = categoryName;
	}



	public QABoard(int qNo, String qTitle, int qHit, Date createDate, Date modifyDate, String qContent, String status,
			String secretStatus, String userName, String categoryName, Answer answer) {
		super();
		QNo = qNo;
		QTitle = qTitle;
		QHit = qHit;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		QContent = qContent;
		this.status = status;
		this.secretStatus = secretStatus;
		this.userName = userName;
		this.categoryName = categoryName;
		this.answer = answer;
	}
	

	public QABoard(int qNo, String qTitle, int qHit, Date createDate, Date modifyDate, String qContent, String status,
			String secretStatus, String userName, String categoryName, Answer answer, int userNo, int categoryNo,
			String userId) {
		super();
		QNo = qNo;
		QTitle = qTitle;
		QHit = qHit;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		QContent = qContent;
		this.status = status;
		this.secretStatus = secretStatus;
		this.userName = userName;
		this.categoryName = categoryName;
		this.answer = answer;
		this.userNo = userNo;
		this.categoryNo = categoryNo;
		this.userId = userId;
	}

	public int getQNo() {
		return QNo;
	}

	public void setQNo(int qNo) {
		QNo = qNo;
	}

	public String getQTitle() {
		return QTitle;
	}

	public void setQTitle(String qTitle) {
		QTitle = qTitle;
	}

	public int getQHit() {
		return QHit;
	}

	public void setQHit(int qHit) {
		QHit = qHit;
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

	public String getQContent() {
		return QContent;
	}

	public void setQContent(String qContent) {
		QContent = qContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSecretStatus() {
		return secretStatus;
	}

	public void setSecretStatus(String secretStatus) {
		this.secretStatus = secretStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	
	public int getUserNo() {
		return userNo;
	}



	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}



	public int getCategoryNo() {
		return categoryNo;
	}



	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}


	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "QABoard [QNo=" + QNo + ", QTitle=" + QTitle + ", QHit=" + QHit + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", QContent=" + QContent + ", status=" + status + ", secretStatus="
				+ secretStatus + ", userName=" + userName + ", categoryName=" + categoryName + ", answer=" + answer
				+ ", userNo=" + userNo + ", categoryNo=" + categoryNo + ", userId=" + userId + "]";
	}

	
}
