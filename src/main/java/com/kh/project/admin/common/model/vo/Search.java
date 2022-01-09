package com.kh.project.admin.common.model.vo;

import lombok.Data;

@Data
public class Search {
	
	private int searchCategory;
	private String searchKeyword;
	private int startRow;
	private int endRow;
	private int sort;

}
