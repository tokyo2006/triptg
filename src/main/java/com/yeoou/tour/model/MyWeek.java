package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.model.Genericmodel;

public class MyWeek extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	List<MyDay> days = new ArrayList<MyDay>();
	public List<MyDay> getDays()
	{
		return days;
	}
	public void setDays(List<MyDay> days)
	{
		this.days = days;
	}

	
}
