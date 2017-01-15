package com.yeoou.tour.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yeoou.rbac.model.User;

public class Document extends Genericmodel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -634285430836861660L;
	private String docId;
	private String title;
	private String content;
	private String urls;
	private User user;
	private Area area;
	private String docName;
	private Date createTime;
	private String createTimeStr;
	private int num;
	private Set<Region> regions = new HashSet<Region>();
	public Set<Region> getRegions()
	{
		return regions;
	}
	public void setRegions(Set<Region> regions)
	{
		this.regions = regions;
	}
	public int getNum()
	{
		return num;
	}
	public void setNum(int num)
	{
		this.num = num;
	}
	public Area getArea()
	{
		return area;
	}
	public void setArea(Area area)
	{
		this.area = area;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Date getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
	public String getCreateTimeStr()
	{
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr)
	{
		this.createTimeStr = createTimeStr;
	}
	public String getDocId()
	{
		return docId;
	}
	public void setDocId(String docId)
	{
		this.docId = docId;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getUrls()
	{
		return urls;
	}
	public void setUrls(String urls)
	{
		this.urls = urls;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
}
