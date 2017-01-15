package com.yeoou.common.web;

import com.yeoou.common.web.BaseActionSupport;


/**
 * <p>
 * Title: PaginationActionSupport
 * </p>
 * <p>
 * Description: Pagination ActionSupport.
 * </p>
 * 
 * @author Wansong
 * @version 1.0
 * @created 2008-03-12
 */
public class PaginationActionSupport extends BaseActionSupport {
	private int pageNo = 1;
	
	private int pageSize = 3;



	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
