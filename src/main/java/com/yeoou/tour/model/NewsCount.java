package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class NewsCount extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String countId;
	private String newsId;
	private int hit;
	private int monthHit;
	private int month;
	private int weekHit;
	private int week;
	public String getCountId()
	{
		return countId;
	}
	public void setCountId(String countId)
	{
		this.countId = countId;
	}
	public int getHit()
	{
		return hit;
	}
	public void setHit(int hit)
	{
		this.hit = hit;
	}
	public int getMonth()
	{
		return month;
	}
	public void setMonth(int month)
	{
		this.month = month;
	}
	public int getMonthHit()
	{
		return monthHit;
	}
	public void setMonthHit(int monthHit)
	{
		this.monthHit = monthHit;
	}
	public String getNewsId()
	{
		return newsId;
	}
	public void setNewsId(String newsId)
	{
		this.newsId = newsId;
	}
	public int getWeek()
	{
		return week;
	}
	public void setWeek(int week)
	{
		this.week = week;
	}
	public int getWeekHit()
	{
		return weekHit;
	}
	public void setWeekHit(int weekHit)
	{
		this.weekHit = weekHit;
	}
}
