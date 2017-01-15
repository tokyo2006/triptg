package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;


public class PicGroup extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private List<ScPic> spList = new ArrayList<ScPic>();
	public List<ScPic> getSpList()
	{
		return spList;
	}
	public void setSpList(List<ScPic> spList)
	{
		this.spList = spList;
	}
	
}
