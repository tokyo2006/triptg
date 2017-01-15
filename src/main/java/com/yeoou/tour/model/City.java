package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class City extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String cityId;
	private Area area;
	private String content;
	private String synopsis;
	private String gloze;
	private int hit;
	private int isTop;
	private int isZone;
	private String mapUrl;
	public String getMapUrl()
	{
		return mapUrl;
	}
	public void setMapUrl(String mapUrl)
	{
		this.mapUrl = mapUrl;
	}
	public int getIsZone()
	{
		return isZone;
	}
	public void setIsZone(int isZone)
	{
		this.isZone = isZone;
	}
	public int getHit()
	{
		return hit;
	}
	public void setHit(int hit)
	{
		this.hit = hit;
	}

	public int getIsTop()
	{
		return isTop;
	}
	public void setIsTop(int isTop)
	{
		this.isTop = isTop;
	}
	public String getCityId()
	{
		return cityId;
	}
	public void setCityId(String cityId)
	{
		this.cityId = cityId;
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
	public String getSynopsis()
	{
		return synopsis;
	}
	public void setSynopsis(String synopsis)
	{
		this.synopsis = synopsis;
	}
	public Area getArea()
	{
		return area;
	}
	public void setArea(Area area)
	{
		this.area = area;
	}
	
}
