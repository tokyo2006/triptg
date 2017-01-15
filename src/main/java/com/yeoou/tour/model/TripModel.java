package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class TripModel extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String classId;
	private String name;
	private String title;
	private TripFlag tripFlag;
	private String remark;
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	public String getClassId()
	{
		return classId;
	}
	public void setClassId(String classId)
	{
		this.classId = classId;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public TripFlag getTripFlag()
	{
		return tripFlag;
	}
	public void setTripFlag(TripFlag tripFlag)
	{
		this.tripFlag = tripFlag;
	}
}
