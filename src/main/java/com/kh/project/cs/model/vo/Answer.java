package com.kh.project.cs.model.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Answer {

	private int ano; 
	private String acontent; 
	@JsonFormat(pattern = "yyyy.MM.dd HH:mm")
	private Date createDate;
	@JsonFormat(pattern = "yyyy.MM.dd HH:mm")
	private Date modifyDate;
	private String status;
	private int qno; 
	private int acount;
	
}
