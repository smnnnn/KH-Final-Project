package com.kh.project.admin.csManage.model.vo;

import lombok.Data;

@Data	
public class SortAndSearch {

	private int sortCategory;    // 카테고리별로 정렬
	private int answerStatus;    // 답변여부별로 정렬
	private int searchCategory;  // 검색조건
	private String searchKeyword;  // 검색어
	
}
