package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.model.Genericmodel;

public class ContentPage extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private int pageCnt = 0;
	private int currPage = 0;
	private List<String> strList = new ArrayList<String>();
	public ContentPage(String content)
	{
		if(content!=null||!content.equals(""))
		{
			String[] page = content.split("<div style=\"page-break-after: always\"><span style=\"display: none\">&nbsp;</span></div>");
			this.pageCnt = page.length;
			for(int i=0;i<pageCnt;i++)
			{
				strList.add(page[i]);
			}
		}
		else
		{
			strList = null;
		}
	}
	public String getResult(int currPage)
	{
		if(strList!=null)
		{
			this.currPage = currPage;
			return strList.get(currPage-1);
		}
		else
		{
			return "";
		}
	}
	public int getPageCnt()
	{
		return pageCnt;
	}
	public List<String> getStrList()
	{
		return strList;
	}
	public void setStrList(List<String> strList)
	{
		this.strList = strList;
	}
	/**
	 * 该页是否有下一页.
	 */
	public boolean hasNextPage() {
		return (this.currPage < this.pageCnt);
	}

	/**
	 * 该页是否有上一页.
	 */
	public boolean hasPreviousPage() {
		return this.currPage > 1;
	}
}
