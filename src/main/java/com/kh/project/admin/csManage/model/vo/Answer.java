package com.kh.project.admin.csManage.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Answer {
	
	private int ano; 
	private String acontent; 
	private Date createDate;
	private Date modifyDate;
	private String status;
	private int qno; 
	
}
