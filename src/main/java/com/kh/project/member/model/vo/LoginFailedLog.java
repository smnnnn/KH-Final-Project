package com.kh.project.member.model.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginFailedLog {
	
	private int no;
	private int memberNo;
	private Timestamp dateTime;
	private String ip;
	

	
	
	
	

}
