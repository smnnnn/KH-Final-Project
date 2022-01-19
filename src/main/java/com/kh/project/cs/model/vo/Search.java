package com.kh.project.cs.model.vo;

import org.apache.ibatis.type.Alias;

@Alias("Search") 
public class Search {

	private String searchCondition; //검색조건
	private String searchValue; //검색어
	
	public Search() {}

	public Search(String searchCondition, String searchValue) {
		super();
		this.searchCondition = searchCondition;
		this.searchValue = searchValue;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public String toString() {
		return "Search [searchCondition=" + searchCondition + ", searchValue=" + searchValue + "]";
	}
	
	
}
