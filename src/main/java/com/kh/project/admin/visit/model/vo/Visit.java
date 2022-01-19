package com.kh.project.admin.visit.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Visit {
	
	private int visitId;
	private String visitIp;
	private Date visitTime;
	private String visitAgent;  //브라우저정보

}
