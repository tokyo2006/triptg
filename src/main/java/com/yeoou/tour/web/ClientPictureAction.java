package com.yeoou.tour.web;

import java.util.List;
import java.util.Random;

import com.yeoou.common.dao.support.Page;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.Scenery;
import com.yeoou.tour.model.SceneryPic;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.ISceneryPicService;
import com.yeoou.tour.service.ISceneryService;
/**
 * <p>
 * Title: 前台相关图片展示模块
 * </p>
 * <p>
 * Description: 包括景点相关图片展示和地域相关图片展示
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class ClientPictureAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private ISceneryPicService sceneryPicService;
	private ISceneryService sceneryService;
	private Scenery sc;
	private IAreaService areaService;
	private String areaId = "";
	private String sceneryId = "";
	private List<SceneryPic> picList;
	private Area area;
	public Area getArea()
	{
		return area;
	}
	public void setArea(Area area)
	{
		this.area = area;
	}
	public IAreaService getAreaService()
	{
		return areaService;
	}
	public void setAreaService(IAreaService areaService)
	{
		this.areaService = areaService;
	}
	/**
	 * 地域相关图片展示
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAreaPicture()
	{
		Page page = new Page();
		area = (Area)areaService.get(areaId);
		page = sceneryPicService.getClientPicListByArea(areaId, 1, 20);
		if(page!=null)
		{
			if(page.getTotalCount()!=0)
			{
				Random rd = new Random();
				Long totalPage = page.getTotalPageCount();
				int start = 1;
				if(totalPage.intValue()==1) 
				{
					start = 1;
				}
				else
				{
					start = rd.nextInt(totalPage.intValue()-1)+1;
				}
				page = new Page();
				page =  sceneryPicService.getClientPicListByArea(areaId, start, 20);
				picList = (List<SceneryPic>)page.getResult();
			}
		}
		return SUCCESS;
	}
	/**
	 * 景点图片相关展示
	 * @return
	 */
	public String getSceneryPictrue()
	{
		
		picList = sceneryPicService.getPicListByScenery(sceneryId);
		sc = (Scenery)sceneryService.get(sceneryId);
		return SUCCESS;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public List<SceneryPic> getPicList()
	{
		return picList;
	}
	public void setPicList(List<SceneryPic> picList)
	{
		this.picList = picList;
	}
	public String getSceneryId()
	{
		return sceneryId;
	}
	public void setSceneryId(String sceneryId)
	{
		this.sceneryId = sceneryId;
	}
	public ISceneryPicService getSceneryPicService()
	{
		return sceneryPicService;
	}
	public void setSceneryPicService(ISceneryPicService sceneryPicService)
	{
		this.sceneryPicService = sceneryPicService;
	}
	public Scenery getSc()
	{
		return sc;
	}
	public void setSc(Scenery sc)
	{
		this.sc = sc;
	}
	public ISceneryService getSceneryService()
	{
		return sceneryService;
	}
	public void setSceneryService(ISceneryService sceneryService)
	{
		this.sceneryService = sceneryService;
	}
}
