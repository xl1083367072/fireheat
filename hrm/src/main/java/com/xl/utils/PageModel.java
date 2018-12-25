package com.xl.utils;

import static com.xl.utils.HrmConstants.PAGE_DEFAULT_SIZE;

public class PageModel {

//	记录总条数
	private int recordCount;
//	当前页
	private int pageIndex;
//	每页显示记录条数
	private int pageSize = PAGE_DEFAULT_SIZE;
//	总页数
	private int totalSize;
	public int getRecordCount() {
//		若记录条数小于等于0，则置为0
		this.recordCount = this.recordCount <=0 ? 0:this.recordCount;
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageIndex() {
//		若当前页小于等于0，则置为首页
		this.pageIndex = this.pageIndex<=0 ? 1:this.pageIndex;
//		若当前页大于等于最大页数，则置为末页
		this.pageIndex = this.pageIndex>=this.getTotalSize() ? this.getTotalSize():this.pageIndex;
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		this.pageSize = this.pageSize<= PAGE_DEFAULT_SIZE ? PAGE_DEFAULT_SIZE:this.pageSize;
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalSize() {
		this.totalSize = (int) Math.ceil(this.recordCount*1.0/this.pageSize);
		return totalSize;
	}
//	获得当前页起始记录
	public int getFirstLimitParam() {
		return (this.getPageIndex()-1)*this.pageSize;
	}
}
