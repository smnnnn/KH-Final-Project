package com.kh.project.cs.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class QABoard {

	private int qno;//
	private String qtitle;//
	private int qhit;//
	private Date createDate;
	private Date modifyDate;
	private String qcontent;//
	private String status;
	private String secretStatus;
	private String userName;
	private String categoryName;
	private Answer answer;
	private int userNo;
	private int categoryNo;
	private String userId;
	
	private int acount; //답변여부
}
