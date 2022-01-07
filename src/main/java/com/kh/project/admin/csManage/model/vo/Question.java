package com.kh.project.admin.csManage.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Question {

	private int qno;
	private String qtitle;
	private int qhit;
	private Date createDate;
	private Date modifyDate;
	private String qcontent;
	private String status;
	private String secretStatus;
	private String userName;
	private String categoryName;
	private Answer answer;
	private int userNo;
	private int categoryNo;
	private String userId;
	
}
