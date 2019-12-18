package com.cyb.blogserver.common;

public class Pagenation {

	private int pageSize = 5;
	
	private int pageIndex = 1;
	
	private int pageCount;
	
	private Object pageDatas;
	
	private int offset = 0;
	
	private long dataCount;
	
	public boolean searcha = true;

	public Pagenation(int pageSize, int pageIndex) {
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize <= 0 || pageSize >= 50) {
			pageSize = 10;
		}
		this.pageSize = pageSize;
	}
	
	public int getPageIndex() {

		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		if(pageIndex <= 1) {
			pageIndex = 1;
		}
		this.pageIndex = pageIndex;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Object getPageDatas() {
		return pageDatas;
	}

	public void setPageDatas(Object pageDatas) {
		this.pageDatas = pageDatas;
	}

	public int getOffset() {
		offset = (pageIndex-1)*pageSize;
		return offset;
	}

	public long getDataCount() {
		return dataCount;
	}

	public void setDataCount(long dataCount) {
		this.dataCount = dataCount;
		if(dataCount%pageSize==0) {
			this.pageCount = (int) (dataCount/pageSize);
		}else {
			this.pageCount = (int) (dataCount/pageSize)+1;
		}
		searcha = pageIndex <= pageCount;
	}
}
