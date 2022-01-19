package com.kh.project.admin.adminManage.model.vo;

import lombok.Data;

@Data
public class DashBoard {
	
	private int newMemberCnt;   // 신규회원수 (어제날짜 가입회원)
	private int secessionCnt;   // 탈퇴회원수
	private int totalMemberCnt; // 총회원수 (탈퇴회원수 제외)
	private int internalCnt;    // 내과예약수
	private int surgeryCnt;     // 외과예약수
	private int ophthalCnt;     // 안과예약수
	private int orthoCnt;       // 정형외과예약수
	private int newCSCnt;       // 새 문의수 (어제날짜 문의)
	private int noAnswerCnt;    // 답변대기수
	
}
