package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class MyDay extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private Team team;
	private int day;
	private String calMonth;
	private String calYear;
	private String calDay;
	public int getDay()
	{
		return day;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public Team getTeam()
	{
		return team;
	}

	public void setTeam(Team team)
	{
		this.team = team;
	}

	public String getCalDay()
	{
		return calDay;
	}

	public void setCalDay(String calDay)
	{
		this.calDay = calDay;
	}

	public String getCalMonth()
	{
		return calMonth;
	}

	public void setCalMonth(String calMonth)
	{
		this.calMonth = calMonth;
	}

	public String getCalYear()
	{
		return calYear;
	}

	public void setCalYear(String calYear)
	{
		this.calYear = calYear;
	}
}
