package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.model.Genericmodel;

public class LetterOfScenery extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String letter;
	private List<Scenery> scList =  new ArrayList<Scenery>();
	public String getLetter()
	{
		return letter;
	}
	public void setLetter(String letter)
	{
		this.letter = letter;
	}
	public List<Scenery> getScList()
	{
		return scList;
	}
	public void setScList(List<Scenery> scList)
	{
		this.scList = scList;
	}
}
