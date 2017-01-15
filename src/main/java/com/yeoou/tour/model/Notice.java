package com.yeoou.tour.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Notice extends Genericmodel
{
	private static final long serialVersionUID = 1229586219643369386L;
	private String noticeId;
	private String name;
	private String content;
	private String docUrl;
	private String docName;
	private String userId;
	private String noticeColor;
	private int noticeType;
	private Date addDate;
	private List<Doc> docs;
	private Set<Area> areas = new HashSet<Area>();
	private String addDateStr;
	public Date getAddDate()
	{
		return addDate;
	}
	public void setAddDate(Date addDate)
	{
		this.addDate = addDate;
	}
	public String getAddDateStr()
	{
		return addDateStr;
	}
	public void setAddDateStr(String addDateStr)
	{
		this.addDateStr = addDateStr;
	}

	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getDocUrl()
	{
		return docUrl;
	}
	public void setDocUrl(String docUrl)
	{
		this.docUrl = docUrl;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getNoticeId()
	{
		return noticeId;
	}
	public void setNoticeId(String noticeId)
	{
		this.noticeId = noticeId;
	}

	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public Set<Area> getAreas()
	{
		return areas;
	}
	public void setAreas(Set<Area> areas)
	{
		this.areas = areas;
	}
	public String getDocName()
	{
		return docName;
	}
	public void setDocName(String docName)
	{
		this.docName = docName;
	}
	public String getNoticeColor()
	{
		return noticeColor;
	}
	public void setNoticeColor(String noticeColor)
	{
		this.noticeColor = noticeColor;
	}
	public int getNoticeType()
	{
		return noticeType;
	}
	public void setNoticeType(int noticeType)
	{
		this.noticeType = noticeType;
	}
	public List<Doc> getDocs()
	{
		return docs;
	}
	public void setDocs(List<Doc> docs)
	{
		this.docs = docs;
	}

}
