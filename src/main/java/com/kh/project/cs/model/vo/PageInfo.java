package com.kh.project.cs.model.vo;

import org.apache.ibatis.type.Alias;

@Alias("pageInfo")
public class PageInfo {
	
	private int page;				// 요청하는 페이지
	private int listCount;			// 전체 게시글 수
	private int pageLimit;			// 하단에 보여질 페이지 목록 수
	private int boardLimit;			// 한 페이지에 보여질 게시글 최대 수
	private int maxPage;			// 전체 페이지에서 가장 마지막 페이지
	private int startPage;			// 하단에 보여질 페이지 목록 시작 값
	private int endPage;			// 하단에 보여질 페이지 목록 끝 값
	
	// 페이징 처리 계산에 필요한 값을 받아 start, end, maxPage 계산하여 값 설정하기
	public PageInfo(int page, int listCount, int pageLimit, int boardLimit) {
		this.page = page;
		this.listCount = listCount;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		
		// * maxPage : 전체 페이지에서 가장 마지막 페이지
		// 게시글 개수가 105개면 페이지 수는 자투리 5개 까지 한 페이지로 생각해서 11 페이지가 필요함
		// 전체 게시글 수 / 한 페이지에 보여질 개수 결과를 올림 처리
		this.maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		// * startPage : 하단에 보여질 페이지 목록 시작 값
		// 요청 page에서 pageLimit만큼을 나누고 다시 곱한 뒤 1을 더함
		// 5 / 10 * 10 + 1 => 1
		// 15 / 10 * 10 + 1 => 11
		// 25 / 10 * 10 + 1 => 21
		// 10, 20, 30, .. 의 경우 나누었을 때 몫이 하나 더 늘어남
		// 이를 방지하기 위해 page - 1을 함
		this.startPage = (page - 1) / pageLimit * pageLimit + 1;
		
		// * endPage : 하단에 보여질 페이지 목록 끝 값
		this.endPage =  startPage + pageLimit - 1;
		
		// 마지막 페이지 수가 총 페이지 수 보다 클 수 없으므로
		if(this.maxPage < this.endPage)
			this.endPage = this.maxPage;
		
	}

	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", listCount=" + listCount + ", pageLimit=" + pageLimit + ", boardLimit="
				+ boardLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}	
	
		
}
