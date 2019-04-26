package com.neuedu.common;

import java.util.List;

import com.google.gson.Gson;

public class pageModel<T> {
public pageModel(int pageNo,int pageSize) {}
public pageModel() {}
private int pageNo;
	private int pageSize;
	private int totalPage;
	private int currentPage;//当前页
	private boolean hasbefore;//是否有上一页
	private boolean hasnext;//是否有下一页
	private List<T> data;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public boolean isHasbefore() {
		return hasbefore;
	}
	public void setHasbefore(boolean hasbefore) {
		this.hasbefore = hasbefore;
	}
	public boolean isHasnext() {
		return hasnext;
	}
	public void setHasnext(boolean hasnext) {
		this.hasnext = hasnext;
	}
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "pageModel [totalPage=" + totalPage + ", currentPage=" + currentPage + ", hasbefore=" + hasbefore
				+ ", hasnext=" + hasnext + ", data=" + data + "]";
	}
	
	public  String  obj2str() {
	   	 Gson gson=new Gson();
			String responseText=gson.toJson(this);
		    return responseText;
	 }
	
	
}
