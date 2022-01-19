package com.kh.project.review.model.vo;

import lombok.Data;

@Data
public class ReviewUpload {
	
	private int fileNo;					// 파일번호
	private int rvno;					// 리뷰번호
	private String filePath;			// 파일 저장 경로
	private String originName;			// 파일 업로드 시의 원본 파일명
	private String changedName;			// 서버 저장 시의 변경 파일명
	private String status;				// 삭제 여부
	
	private String deletedName;			// 삭제 시 파일명
}
