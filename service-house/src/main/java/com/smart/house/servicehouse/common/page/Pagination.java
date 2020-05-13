package com.smart.house.servicehouse.common.page;

import com.google.common.collect.Lists;

import java.util.List;

public class Pagination {
	private int pageNum;  //当前页
	private int pageSize; //每页大小
	private Integer totalCount;  //查询总数
	private List<Integer> pages = Lists.newArrayList();  //保存页码总数

	public Pagination(Integer pageSize,Integer pageNum,Integer totalCount) {
	   this.totalCount = totalCount;
	   this.pageNum = pageNum;
	   this.pageSize = pageSize;
	   for(int i=1;i<=pageNum;i++){
		   pages.add(i);
	   }
	   //分页总数
	   Integer pageCount = totalCount/pageSize + ((totalCount % pageSize == 0 ) ? 0: 1);
	   if (pageCount > pageNum) {
		  for(int i= pageNum + 1; i<= pageCount ;i ++){
			  pages.add(i);
		  }
	   }
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}
}
