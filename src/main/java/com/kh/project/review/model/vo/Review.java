package com.kh.project.review.model.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Review {
	
	private int rvno;						// 리뷰번호
	private String rvtitle;					// 제목
	private String rvcontent;				// 내용 얘는 스트링으로 바꿔야하는구나^^..
	private int rvhit;						// 조회수
	private Date createDate;				// 작성일
	private Date modifyDate;				// 수정일
	private String status;					// 글삭제여부
	private int tno;						// 진료과목번호
	private String tname;					// 진료과목명
	private int resNo;						// 예약번호
	private int userNo;						// 회원번호
	private String userId;					// 회원아이디
	
	private List<ReviewUpload> photoList;	// 사진첨부파일

	public Review() {}
}
