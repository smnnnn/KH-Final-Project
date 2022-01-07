package com.kh.project.admin.common.model.vo;

public class Pagination {

	private int page;               // 현재 페이지
	private int totalListCount;     // 총 게시글 수
	private int pageLimit;          // 화면 하단에 표시되는 페이지 목록 수
	private int listLimit;          // 한 페이지에 보여지는 게시글 수
	private int endPage;            // 화면의 마지막페이지
	private int startPage;          // 첫페이지
	private int maxPage;            // 마지막 페이지
	
	public Pagination(int page, int totalListCount, int pageLimit, int listLimit) {
		this.page = page;
		this.totalListCount = totalListCount;
		this.pageLimit = pageLimit;
		this.listLimit = listLimit;
		
		this.maxPage = (int)Math.ceil((double)totalListCount/listLimit);
		
		this.startPage = (page - 1) / pageLimit * pageLimit + 1;
		
		this.endPage = startPage + pageLimit - 1;
		
		if(this.maxPage < this.endPage) {
			this.endPage = this.maxPage;
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalListCount() {
		return totalListCount;
	}

	public void setTotalListCount(int totalListCount) {
		this.totalListCount = totalListCount;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getListLimit() {
		return listLimit;
	}

	public void setListLimit(int listLimit) {
		this.listLimit = listLimit;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	@Override
	public String toString() {
		return "Pagination [page=" + page + ", totalListCount=" + totalListCount + ", pageLimit=" + pageLimit
				+ ", listLimit=" + listLimit + ", endPage=" + endPage + ", startPage=" + startPage + ", maxPage="
				+ maxPage + "]";
	}

}
