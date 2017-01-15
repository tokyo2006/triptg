package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

public class ScenerysOfType
{
	private SceneryType st;
	private List<Scenery> scList =  new ArrayList<Scenery>();
	public List<Scenery> getScList()
	{
		return scList;
	}
	public void setScList(List<Scenery> scList)
	{
		this.scList = scList;
	}
	public SceneryType getSt()
	{
		return st;
	}
	public void setSt(SceneryType st)
	{
		this.st = st;
	}
	
}
