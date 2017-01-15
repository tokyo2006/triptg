package com.yeoou.tour.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yeoou.common.model.Genericmodel;

public class News extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String newsId;
	private String title;
	private String subTitle;
	private String content;
	private String publishDateStr;
	private Date publishDate;
	private String origin;
	private int hit;
	private String picUrl;
	private String writer;
	private String mark;
	private int promotion;
	private int hot;
	private Set<NewsType> types =  new HashSet<NewsType>();
	private Set<Area> areas = new HashSet<Area>();
	private String url;
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public int getHit()
	{
		return hit;
	}
	public void setHit(int hit)
	{
		this.hit = hit;
	}
	public int getHot()
	{
		return hot;
	}
	public void setHot(int hot)
	{
		this.hot = hot;
	}
	public String getMark()
	{
		return mark;
	}
	public void setMark(String mark)
	{
		this.mark = mark;
	}
	public String getNewsId()
	{
		return newsId;
	}
	public void setNewsId(String newsId)
	{
		this.newsId = newsId;
	}
	public String getOrigin()
	{
		return origin;
	}
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	public int getPromotion()
	{
		return promotion;
	}
	public void setPromotion(int promotion)
	{
		this.promotion = promotion;
	}
	public Date getPublishDate()
	{
		return publishDate;
	}
	public void setPublishDate(Date publishDate)
	{
		this.publishDate = publishDate;
	}
	public String getPublishDateStr()
	{
		return publishDateStr;
	}
	public void setPublishDateStr(String publishDateStr)
	{
		this.publishDateStr = publishDateStr;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getWriter()
	{
		return writer;
	}
	public void setWriter(String writer)
	{
		this.writer = writer;
	}
	public Set<NewsType> getTypes()
	{
		return types;
	}
	public void setTypes(Set<NewsType> types)
	{
		this.types = types;
	}
	public Set<Area> getAreas()
	{
		return areas;
	}
	public void setAreas(Set<Area> areas)
	{
		this.areas = areas;
	}
	public String getSubTitle()
	{
		return subTitle;
	}
	public void setSubTitle(String subTitle)
	{
		this.subTitle = subTitle;
	}
	public String getPicUrl()
	{
		return picUrl;
	}
	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}
}
