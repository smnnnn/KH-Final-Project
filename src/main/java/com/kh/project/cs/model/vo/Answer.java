package com.kh.project.cs.model.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Answer {

	private int ano; 
	private String acontent; 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd. HH:mm", timezone = "Asia/Seoul")
	private Date createDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd. HH:mm", timezone = "Asia/Seoul")
	private Date modifyDate;
	private String status;
	private int qno; 
	private int acount;
	
}
