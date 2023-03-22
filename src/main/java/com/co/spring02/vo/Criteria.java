package com.co.spring02.vo;

public class Criteria  {

	private int page;
	private int perPageNum;
	private int rowStart;
	private int rowEnd;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page < 1 ) {
			page  = 1;
		}
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}

	
	public int getRowStart() {
		rowStart = ((page - 1) * perPageNum) + 1;
		return rowStart;
	}
	public int getRowEnd() {
		//rowEnd = rowStart + perPageNum - 1;
		rowEnd = page * perPageNum;
		return rowEnd;
	}
	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd
				+ "]";
	}
	
	
}
