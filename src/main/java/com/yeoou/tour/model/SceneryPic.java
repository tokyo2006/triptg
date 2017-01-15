package com.yeoou.tour.model;

import java.util.HashSet;
import java.util.Set;

import com.yeoou.common.model.Genericmodel;

public class SceneryPic extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String pictureId;
	private String name;
	private String url;
	private String breviaryUrl;
	private String miniUrl;
	private Scenery scenery;
	private Set<Area> areas = new HashSet<Area>();
	public Set<Area> getAreas()
	{
		return areas;
	}
	public void setAreas(Set<Area> areas)
	{
		this.areas = areas;
	}
	public Scenery getScenery()
	{
		return scenery;
	}
	public void setScenery(Scenery scenery)
	{
		this.scenery = scenery;
	}
	public String getBreviaryUrl()
	{
		return breviaryUrl;
	}
	public void setBreviaryUrl(String breviaryUrl)
	{
		this.breviaryUrl = breviaryUrl;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getPictureId() {
		return pictureId;
	}
	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}
	public String getMiniUrl()
	{
		return miniUrl;
	}
	public void setMiniUrl(String miniUrl)
	{
		this.miniUrl = miniUrl;
	}
}
