package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.model.Genericmodel;

public class VisaCity extends Genericmodel
{
	
	private static final long serialVersionUID = 1L;
	private String areaName;
	private List<Visa> visas = new ArrayList<Visa>();
	public String getAreaName()
	{
		return areaName;
	}
	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}
	public List<Visa> getVisas()
	{
		return visas;
	}
	public void setVisas(List<Visa> visas)
	{
		this.visas = visas;
	}
}
