package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yeoou.common.model.Genericmodel;

public class Scenery extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String sceneryId;
	private String name;
	private int allHits;
	private int weekHits;
	private int monthHits;
	private String htmlFileData;
	private int isUpdate;
	private int isTop;
	private String ticketPrice;
	private String content;
	private String synopsis;
	private int level;
	private String firstLetter;
	private String subTopic;
	private String displayUrl;
	private List<SceneryPic>picList = new ArrayList<SceneryPic>();
	private Set<SceneryType> types = new HashSet<SceneryType>();
	private Set<Region> regions = new HashSet<Region>();
	private Set<Area> areas = new HashSet<Area>();
	
	public List<SceneryPic> getPicList()
	{
		return picList;
	}
	public void setPicList(List<SceneryPic> picList)
	{
		this.picList = picList;
	}
	public Set<Area> getAreas()
	{
		return areas;
	}
	public void setAreas(Set<Area> areas)
	{
		this.areas = areas;
	}
	public Set<Region> getRegions()
	{
		return regions;
	}
	public void setRegions(Set<Region> regions)
	{
		this.regions = regions;
	}
	public int getAllHits()
	{
		return allHits;
	}
	public void setAllHits(int allHits)
	{
		this.allHits = allHits;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getFirstLetter()
	{
		return firstLetter;
	}
	public void setFirstLetter(String firstLetter)
	{
		this.firstLetter = firstLetter;
	}
	public String getHtmlFileData()
	{
		return htmlFileData;
	}
	public void setHtmlFileData(String htmlFileData)
	{
		this.htmlFileData = htmlFileData;
	}
	public int getIsTop()
	{
		return isTop;
	}
	public void setIsTop(int isTop)
	{
		this.isTop = isTop;
	}
	public int getIsUpdate()
	{
		return isUpdate;
	}
	public void setIsUpdate(int isUpdate)
	{
		this.isUpdate = isUpdate;
	}
	public int getLevel()
	{
		return level;
	}
	public void setLevel(int level)
	{
		this.level = level;
	}
	public int getMonthHits()
	{
		return monthHits;
	}
	public void setMonthHits(int monthHits)
	{
		this.monthHits = monthHits;
	}
	public String getSceneryId()
	{
		return sceneryId;
	}
	public void setSceneryId(String sceneryId)
	{
		this.sceneryId = sceneryId;
	}
	public String getSynopsis()
	{
		return synopsis;
	}
	public void setSynopsis(String synopsis)
	{
		this.synopsis = synopsis;
	}

	public String getTicketPrice()
	{
		return ticketPrice;
	}
	public void setTicketPrice(String ticketPrice)
	{
		this.ticketPrice = ticketPrice;
	}
	public int getWeekHits()
	{
		return weekHits;
	}
	public void setWeekHits(int weekHits)
	{
		this.weekHits = weekHits;
	}
	public Set<SceneryType> getTypes()
	{
		return types;
	}
	public void setTypes(Set<SceneryType> types)
	{
		this.types = types;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSubTopic()
	{
		return subTopic;
	}
	public void setSubTopic(String subTopic)
	{
		this.subTopic = subTopic;
	}
	public String getDisplayUrl()
	{
		return displayUrl;
	}
	public void setDisplayUrl(String displayUrl)
	{
		this.displayUrl = displayUrl;
	}
	@Override
	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((sceneryId == null) ? 0 : sceneryId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Scenery other = (Scenery) obj;
		if (sceneryId == null)
		{
			if (other.sceneryId != null)
				return false;
		} else if (!sceneryId.equals(other.sceneryId))
			return false;
		return true;
	}
	
}
