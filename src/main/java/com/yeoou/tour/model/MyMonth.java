package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.model.Genericmodel;

public class MyMonth extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private List<MyWeek> weeks = new ArrayList<MyWeek>();
	public List<MyWeek> getWeeks()
	{
		return weeks;
	}
	public void setWeeks(List<MyWeek> weeks)
	{
		this.weeks = weeks;
	}
}
