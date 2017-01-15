package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class Nation extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String nationId;
	private Area area;
	private String content;
	private String synopsis;
	private String gloze;
	private int hit;
	private int isTop;
	private String mapUrl;
	private String mapTopic;
	public String getMapTopic()
	{
		return mapTopic;
	}
	public void setMapTopic(String mapTopic)
	{
		this.mapTopic = mapTopic;
	}
	public String getMapUrl()
	{
		return mapUrl;
	}
	public void setMapUrl(String mapUrl)
	{
		this.mapUrl = mapUrl;
	}
	public Area getArea()
	{
		return area;
	}
	public void setArea(Area area)
	{
		this.area = area;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public String getGloze()
	{
		return gloze;
	}
	public void setGloze(String gloze)
	{
		this.gloze = gloze;
	}
	public int getHit()
	{
		return hit;
	}
	public void setHit(int hit)
	{
		this.hit = hit;
	}
	public String getNationId()
	{
		return nationId;
	}
	public void setNationId(String nationId)
	{
		this.nationId = nationId;
	}
	public String getSynopsis()
	{
		return synopsis;
	}
	public void setSynopsis(String synopsis)
	{
		this.synopsis = synopsis;
	}
	public int getIsTop()
	{
		return isTop;
	}
	public void setIsTop(int isTop)
	{
		this.isTop = isTop;
	}


}
