package com.yeoou.tour.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.StringUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.News;
import com.yeoou.tour.model.PicuterFour;
import com.yeoou.tour.model.Scenery;
import com.yeoou.tour.model.SceneryPic;
import com.yeoou.tour.model.Team;
import com.yeoou.tour.model.TripContent;
import com.yeoou.tour.model.Visa;
import com.yeoou.tour.model.VisaCity;
import com.yeoou.tour.model.WebSite;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.INewsService;
import com.yeoou.tour.service.ISceneryPicService;
import com.yeoou.tour.service.ISceneryService;
import com.yeoou.tour.service.ITeamService;
import com.yeoou.tour.service.ITripContentService;
import com.yeoou.tour.service.IVisaService;
import com.yeoou.tour.service.IWebSiteService;
/**
 * <p>
 * Title: 前台相关签证展示模块
 * </p>
 * <p>
 * Description: 以国家列表做为索引对显示详细签证信息
 * 签证索引页面visa.shtml,和签证详细页面visaDetail.shtml
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class ClientVisaAction extends BaseActionSupport
{

	private static final long serialVersionUID = 1L;
	private IAreaService areaService;
	private IVisaService visaService;
	private ITeamService teamService;
	private INewsService newsService;
	private ISceneryPicService sceneryPicService;
	private ISceneryService sceneryService;
	private List<Team> teamList;
	private List<VisaCity> visas = new ArrayList<VisaCity>();
	private List<Visa> tenVisas;
	private String visaId = "";
	private Visa visa;
	private Area area;
	private List<Area> areas;
	private String areaId="";
	private int bannerFlag = 7;
	private List<String> searchList = new ArrayList<String>();
	private IWebSiteService webSiteService;
	private List<TripContent> gg_ss;
	private WebSite webSite ;
	private int searchType = 0;
	private ITripContentService tripContentService;
	private List<Team> areaTeamList;
	private List<News> picNews;
	private List<News> rightNews;
	private List<News> rightHelps;
	private List<SceneryPic> rightPicList;
	private List<Scenery> rightScenreyList;
	/**
	 * @see ClientAreaAction#getRightPic(String)
	 * @param areaId
	 */
	public void getRightPic(String areaId)
	{
		List<SceneryPic> picList = new ArrayList<SceneryPic>();
		picList = sceneryPicService.getPicListByArea(areaId);
		this.rightPicList = new ArrayList<SceneryPic>();
		if(picList!=null)
		{
			int size = picList.size();
			if(size>3)
			{
				for(int i=0;i<4;i++)
				{
					rightPicList.add(picList.get(i));
				}
			}
			else
			{
				for(int i=0;i<size;i++)
				{
					rightPicList.add(picList.get(i));
				}
				Area area = new Area();
				area = (Area)areaService.get(areaId);
				List<SceneryPic> ppList = new ArrayList<SceneryPic>();
				int j=0;
				ppList = sceneryPicService.getPicListByArea(area.getParent());
				if(ppList!=null)
				{
					while(rightPicList.size()!=4)
					{
						if(j>ppList.size())break;
						for(int i=0;i<rightPicList.size();i++)
						{
							if(!rightPicList.get(i).getPictureId().equals(ppList.get(j).getPictureId()))
							{
								rightPicList.add(ppList.get(j));
							    if(rightPicList.size()==4) break;
							}
						}
						j++;
					}
				}
			}
		}
		else
		{
			Area area = new Area();
			area = (Area)areaService.get(areaId);
			List<SceneryPic> ppList = new ArrayList<SceneryPic>();
			int j=0;
			ppList = sceneryPicService.getPicListByArea(area.getParent());
			if(ppList!=null)
			{
				int size = ppList.size();
				if(size>3)
				{
					size = 4;
					for(int i=0;i<size;i++)
					{
						rightPicList.add(ppList.get(i));
					}
				}
				else
				{
					for(int i=0;i<size;i++)
					{
						rightPicList.add(ppList.get(i));
					}
					Area parea = new Area();
					parea = (Area)areaService.get(area.getParent());
					List<SceneryPic> pppList = new ArrayList<SceneryPic>();
					j=0;
					pppList = sceneryPicService.getPicListByArea(parea.getParent());
					if(pppList!=null)
					{
						while(rightPicList.size()!=4)
						{
							if(j>pppList.size())break;
							for(int i=0;i<rightPicList.size();i++)
							{
								if(!rightPicList.get(i).getPictureId().equals(pppList.get(j).getPictureId()))
								{
									rightPicList.add(pppList.get(j));
									if(rightPicList.size()==4) break;
								}
							}
							j++;
						}
					}
				}
			}
		}
	}
	/**
	 * @see ClientAreaAction#getRightScenery(String)
	 * @param areaId
	 */
	public void getRightScenery(String areaId)
	{

		this.rightScenreyList = new ArrayList<Scenery>();
		List<Scenery> sceneryList = new ArrayList<Scenery>();
		sceneryList = sceneryService.getSceneryByArea(areaId, 5);
		if(sceneryList!=null)
		{
			int size = sceneryList.size();
			if(size>3)
			{
				
				for(int i=0;i<4;i++)
				{
					List<SceneryPic> spList = new ArrayList<SceneryPic>();
					spList = sceneryPicService.getPicListByScenery(sceneryList.get(i).getSceneryId());
					if(spList!=null) sceneryList.get(i).setDisplayUrl(spList.get(0).getBreviaryUrl());
					else sceneryList.get(i).setDisplayUrl("public/20081205/image/scenerypic.gif");
					this.rightScenreyList.add(sceneryList.get(i));
				}
			}
			else
			{
				for(int i=0;i<size;i++)
				{
					rightScenreyList.add(sceneryList.get(i));
				}
				Area area = new Area();
				area = (Area)areaService.get(areaId);
				List<Scenery> ksceneryList  = sceneryService.getSceneryByArea(area.getParent(), 20);
				if(ksceneryList!=null)
				{
					int p = ksceneryList.size();
					for(int i=0;i<p;i++)
					{
						for(int j=0;j<sceneryList.size();j++)
						{
							if(sceneryList.get(j).getSceneryId().equals(ksceneryList.get(i).getSceneryId()))
							{
								ksceneryList.remove(ksceneryList.get(i));
								i=0;
								p--;
							}
						}
					}
					if(ksceneryList.size()>0)
					{
						for(int i=0;i<ksceneryList.size();i++)
						{
							rightScenreyList.add(ksceneryList.get(i));
							if(rightScenreyList.size()==4) break;
						}
						if(rightScenreyList.size()<4)
						{
							Area parea = new Area();
							parea = (Area)areaService.get(area.getParent());
							List<Scenery> lsceneryList  = sceneryService.getSceneryByArea(parea.getParent(), 20);
							for(int i=0;i<lsceneryList.size();i++)
							{
								rightScenreyList.add(lsceneryList.get(i));
								if(rightScenreyList.size()==4) break;
							}
						}
					}
					else
					{
						Area parea = new Area();
						parea = (Area)areaService.get(area.getParent());
						List<Scenery> lsceneryList  = sceneryService.getSceneryByArea(parea.getParent(), 20);
						for(int i=0;i<lsceneryList.size();i++)
						{
							rightScenreyList.add(lsceneryList.get(i));
							if(rightScenreyList.size()==4) break;
						}
					}
				}
			}
			
		}
		else
		{
			Area area = new Area();
			area = (Area)areaService.get(areaId);
			List<Scenery> ksceneryList  = sceneryService.getSceneryByArea(area.getParent(), 5);
			if(ksceneryList!=null)
			{
				int size = ksceneryList.size();
				if(size>3)
				{
					
					for(int i=0;i<4;i++)
					{
						List<SceneryPic> spList = new ArrayList<SceneryPic>();
						spList = sceneryPicService.getPicListByScenery(ksceneryList.get(i).getSceneryId());
						if(spList!=null) ksceneryList.get(i).setDisplayUrl(spList.get(0).getBreviaryUrl());
						else ksceneryList.get(i).setDisplayUrl("public/20081205/image/scenerypic.gif");
						this.rightScenreyList.add(ksceneryList.get(i));
					}
				}
				else
				{
					for(int i=0;i<size;i++)
					{
						rightScenreyList.add(ksceneryList.get(i));
					}
					Area xarea = new Area();
					xarea = (Area)areaService.get(area.getParent());
					List<Scenery> xsceneryList  = sceneryService.getSceneryByArea(xarea.getParent(), 20);
					if(xsceneryList!=null)
					{
						int p = xsceneryList.size();
						for(int i=0;i<p;i++)
						{
							for(int j=0;j<ksceneryList.size();j++)
							{
								if(ksceneryList.get(j).getSceneryId().equals(xsceneryList.get(i).getSceneryId()))
								{
									xsceneryList.remove(xsceneryList.get(i));
									i=0;
									p--;
								}
							}
						}
						if(xsceneryList.size()>0)
						{
							for(int i=0;i<xsceneryList.size();i++)
							{
								rightScenreyList.add(xsceneryList.get(i));
								if(rightScenreyList.size()==4) break;
							}
							if(rightScenreyList.size()<4)
							{
								Area parea = new Area();
								parea = (Area)areaService.get(xarea.getParent());
								List<Scenery> lsceneryList  = sceneryService.getSceneryByArea(parea.getParent(), 20);
								for(int i=0;i<lsceneryList.size();i++)
								{
									rightScenreyList.add(lsceneryList.get(i));
									if(rightScenreyList.size()==4) break;
								}
							}
						}
						else
						{
							Area parea = new Area();
							parea = (Area)areaService.get(xarea.getParent());
							List<Scenery> lsceneryList  = sceneryService.getSceneryByArea(parea.getParent(), 20);
							for(int i=0;i<lsceneryList.size();i++)
							{
								rightScenreyList.add(lsceneryList.get(i));
								if(rightScenreyList.size()==4) break;
							}
						}
					}
				}
			}
			else
			{
				Area parea = new Area();
				parea = (Area)areaService.get(area.getParent());
				List<Scenery> psceneryList  = sceneryService.getSceneryByArea(parea.getParent(), 5);
				if(psceneryList!=null)
				{
					for(int i=0;i<4;i++)
					{
						List<SceneryPic> spList = new ArrayList<SceneryPic>();
						spList = sceneryPicService.getPicListByScenery(psceneryList.get(i).getSceneryId());
						if(spList!=null) psceneryList.get(i).setDisplayUrl(spList.get(0).getBreviaryUrl());
						else psceneryList.get(i).setDisplayUrl("public/20081205/image/scenerypic.gif");
						this.rightScenreyList.add(psceneryList.get(i));
					}
					
				}
			}
		}
		for(int i=0;i<rightScenreyList.size();i++)
		{
			List<SceneryPic> spList = new ArrayList<SceneryPic>();
			spList = sceneryPicService.getPicListByScenery(rightScenreyList.get(i).getSceneryId());
			if(spList!=null) rightScenreyList.get(i).setDisplayUrl(spList.get(0).getBreviaryUrl());
			else rightScenreyList.get(i).setDisplayUrl("public/20081205/image/scenerypic.gif");
		}
	}
	/**
	 * @see ClientAreaAction#getClientRightNews()
	 *
	 */
	public void getClientRightNews()
	{
		this.picNews = newsService.getClientPicNews(2);
		this.rightNews = newsService.getNewsListForClient(8);
		this.rightHelps = newsService.getNewsByRemark("helpL1");
	}
	/**
	 * @see ClientAreaAction#getAreaTeam(String)
	 * @param areaId
	 */
	public void getAreaTeam(String areaId)
	{
		Area area = new Area();
		areaTeamList = new ArrayList<Team>();
		area  = (Area)areaService.get(areaId);
		if(area.getDepth()==2)
		{
			//如果是中国
			if(area.getIsChina()==1)
			{
				
				List<Team> tlist = teamService.getTeamByArea(area.getAreaId());
				if(tlist!=null)
				{
					for(int i=0;i<4;i++)
					{
						this.areaTeamList.add(tlist.get(i));
					}
				}
			}
			//如果是外国
			else
			{
				List<Team> tlist = teamService.getTeamByArea(area.getAreaId());
				//如果有线路
				if(tlist!=null)
				{
					int size = tlist.size();
					//如果满4条
					if(size>3)
					{
						for(int i=0;i<4;i++)
						{
							this.areaTeamList.add(tlist.get(i));
						}
					}
					//如果不满4条
					else
					{
						for(int i=0;i<size;i++)
						{
							this.areaTeamList.add(tlist.get(i));
						}
						int j=0;
						//取出境线路
						List<Team> tlist1 = teamService.getTeamByFlag(3);
						if(tlist1!=null)
						{
							while(areaTeamList.size()!=4)
							{
								if(j>tlist1.size())break;
								for(int i=0;i<areaTeamList.size();i++)
								{
									if(!areaTeamList.get(i).getGroupId().equals(tlist1.get(j).getGroupId()))
									{
										areaTeamList.add(tlist1.get(j));
										if(areaTeamList.size()==4) break;
									}
								}
								j++;
							}
						}
						
					}
				}
				else
				{
					//取出境线路
					List<Team> tlist1 = teamService.getTeamByFlag(3);
					if(tlist1!=null)
					{
						for(int i=0;i<4;i++)
						{
							this.areaTeamList.add(tlist1.get(i));
						}
					}
				}
			}
		}
		//国内省，国外市
		if(area.getDepth()==3)
		{
			if(area.getIsChina()==1)
			{
				List<Team> tlist = teamService.getTeamByArea(area.getAreaId());
				if(tlist!=null)
				{
					int size = tlist.size();
//					如果满4条
					if(size>3)
					{
						for(int i=0;i<4;i++)
						{
							this.areaTeamList.add(tlist.get(i));
						}
					}
					else
					{
						for(int i=0;i<tlist.size();i++)
						{
							this.areaTeamList.add(tlist.get(i));
						}
//						int j=0;
//						//取国家线路
//						List<Team> tlist1 = teamService.getTeamByArea(area.getParent());
//						if(tlist1!=null)
//						{
//							while(areaTeamList.size()!=4)
//							{
//								if(j>tlist1.size())break;
//								for(int i=0;i<areaTeamList.size();i++)
//								{
//									if(!areaTeamList.get(i).getGroupId().equals(tlist1.get(j).getGroupId()))
//									{
//										areaTeamList.add(tlist1.get(j));
//										if(areaTeamList.size()==4) break;
//									}
//								}
//								j++;
//							}
//						}
						
					}
				}
				else
				{
//					//取国家线路
//					List<Team> tlist1 = teamService.getTeamByArea(area.getParent());
//					if(tlist1!=null)
//					{
//						for(int i=0;i<4;i++)
//						{
//							this.areaTeamList.add(tlist1.get(i));
//						}
//					}
				}
			}
			else
			{
				//国外城市
				List<Team> tlist = teamService.getTeamByArea(area.getAreaId());
				if(tlist!=null)
				{
					int size = tlist.size();
					if(size>3)
					{
						for(int i=0;i<4;i++)
						{
							this.areaTeamList.add(tlist.get(i));
						}
					}
					else
					{
						for(int i=0;i<size;i++)
						{
							this.areaTeamList.add(tlist.get(i));
						}
						//取国家线路
						int j=0;
						List<Team> tlist2 = teamService.getTeamByArea(area.getParent());
						if(tlist2.size()>5)
						{
							while(areaTeamList.size()!=4)
							{
								if(j>tlist2.size())break;
								for(int i=0;i<areaTeamList.size();i++)
								{
									if(!areaTeamList.get(i).getGroupId().equals(tlist2.get(j).getGroupId()))
									{
										areaTeamList.add(tlist2.get(j));
										if(areaTeamList.size()==4) break;
									}
								}
								j++;
							}
						}
						else
						{
							//取出境线路
							List<Team> tlist1 = teamService.getTeamByFlag(3);
							if(tlist1!=null)
							{
								j=0;
								while(areaTeamList.size()!=4)
								{
									if(j>tlist1.size())break;
									for(int i=0;i<areaTeamList.size();i++)
									{
										if(!areaTeamList.get(i).getGroupId().equals(tlist1.get(j).getGroupId()))
										{
											areaTeamList.add(tlist1.get(j));
											if(areaTeamList.size()==4) break;
										}
									}
									j++;
								}
							}
						}
					}
				}
				else
				{
					//取国家线路
					List<Team> tlist2 = teamService.getTeamByArea(area.getParent());
					if(tlist2!=null)
					{
						int size = tlist2.size();
						if(size>3)
						{
							for(int i=0;i<4;i++)
							{
								this.areaTeamList.add(tlist2.get(i));
							}
						}
						else
						{
							for(int i=0;i<size;i++)
							{
								this.areaTeamList.add(tlist2.get(i));
							}
							//取出境线路
							List<Team> tlist1 = teamService.getTeamByFlag(3);
							int j=0;
							if(tlist1!=null)
							{
								while(areaTeamList.size()!=4)
								{
									if(j>tlist1.size())break;
									for(int i=0;i<areaTeamList.size();i++)
									{
										if(!areaTeamList.get(i).getGroupId().equals(tlist1.get(j).getGroupId()))
										{
											areaTeamList.add(tlist1.get(j));
											if(areaTeamList.size()==4) break;
										}
									}
									j++;
								}
							}
						}
					}
					else
					{
						//取出境线路
						List<Team> tlist1 = teamService.getTeamByFlag(3);
						if(tlist1!=null)
						{
							for(int i=0;i<4;i++)
							{
								this.areaTeamList.add(tlist1.get(i));
							}
						}
					}
				}
			}
		}
		//国内城市
		if(area.getDepth()==4)
		{
			List<Team> tlist = teamService.getTeamByArea(area.getAreaId());
			if(tlist!=null)
			{
				int size = tlist.size();
//				如果满4条
				if(size>3)
				{
					for(int i=0;i<4;i++)
					{
						this.areaTeamList.add(tlist.get(i));
					}
				}
				else
				{
					for(int i=0;i<tlist.size();i++)
					{
						this.areaTeamList.add(tlist.get(i));
					}
					int j=0;
					//取省份线路
					List<Team> tlist1 = teamService.getTeamByArea(area.getParent());
					if(tlist1!=null)
					{
						if(tlist1.size()>5)
						{
							while(areaTeamList.size()!=4)
							{
								if(j>tlist1.size())break;
								for(int i=0;i<areaTeamList.size();i++)
								{
									if(!areaTeamList.get(i).getGroupId().equals(tlist1.get(j).getGroupId()))
									{
										areaTeamList.add(tlist1.get(j));
										if(areaTeamList.size()==4) break;
									}
								}
								j++;
							}
						}
						else
						{
//							//去国家线路
//							Area parea = new Area();
//							parea = (Area)areaService.get(area.getParent());
//							j=0;
//							List<Team> tlist2 = teamService.getTeamByArea(parea.getParent());
//							if(tlist2!=null)
//							{
//								while(areaTeamList.size()!=4)
//								{
//									if(j>tlist2.size())break;
//									for(int i=0;i<areaTeamList.size();i++)
//									{
//										if(!areaTeamList.get(i).getGroupId().equals(tlist2.get(j).getGroupId()))
//										{
//											areaTeamList.add(tlist2.get(j));
//											if(areaTeamList.size()==4) break;
//										}
//									}
//									j++;
//								}
//							}
						}
					}
				}
			}
			else
			{
				//取省份线路
				List<Team> tlist1 = teamService.getTeamByArea(area.getParent());
				if(tlist1!=null)
				{
					int size = tlist1.size();
//					如果满4条
					if(size>3)
					{
						for(int i=0;i<4;i++)
						{
							this.areaTeamList.add(tlist1.get(i));
						}
					}
					else
					{
						for(int i=0;i<size;i++)
						{
							this.areaTeamList.add(tlist1.get(i));
						}
//						//取国家线路
//						Area parea = new Area();
//						parea = (Area)areaService.get(area.getParent());
//						int j=0;
//						List<Team> tlist2 = teamService.getTeamByArea(parea.getParent());
//						if(tlist2!=null)
//						{
//							while(areaTeamList.size()!=4)
//							{
//								if(j>tlist2.size())break;
//								for(int i=0;i<areaTeamList.size();i++)
//								{
//									if(!areaTeamList.get(i).getGroupId().equals(tlist2.get(j).getGroupId()))
//									{
//										areaTeamList.add(tlist2.get(j));
//										if(areaTeamList.size()==4) break;
//									}
//								}
//								j++;
//							}
//						}
					}
				}
				else
				{
//					//去国家线路
//					Area parea = new Area();
//					parea = (Area)areaService.get(area.getParent());
//					List<Team> tlist2 = teamService.getTeamByArea(parea.getParent());
//					if(tlist2!=null)
//					{
//						for(int i=0;i<4;i++)
//						{
//							areaTeamList.add(tlist2.get(i));
//						}
//					}
				}
			}
		}
		for(int i=0;i<areaTeamList.size();i++)
		{
			List<PicuterFour> picList = new ArrayList<PicuterFour>();
			picList = PicuterFour.unpackagePicFour(areaTeamList.get(i).getLine().getPicArea());
	    	if(picList!=null)
	    	{
	    		Random rnd = new Random();
	    		int l = picList.size();
	    		int o = rnd.nextInt(l);
	    		areaTeamList.get(i).setOrgUrl(picList.get(o).getBreUrl());
	    		areaTeamList.get(i).setBreUrl(picList.get(o).getUrl());
	    	}
	    	else
	    	{
	    		areaTeamList.get(i).setOrgUrl("public/20081205/image/scenerypic.gif");
	    		areaTeamList.get(i).setBreUrl("public/20081205/image/scenerypic.gif");
	    	}
		}
	}
	/**
	 * @see ClientAreaAction#getBannaerInfo()
	 * @return
	 */
	public String getBannaerInfo()
	{
		String areaId = this.getIPAddress();
		webSite = webSiteService.getWebSiteByArea(areaId);
		searchList.add("线路搜索");
		searchList.add("景点搜索");
		gg_ss = tripContentService.getTripContentList("", "gg_ss");
		return SUCCESS;
	}
	/**
	 * @see ClientAreaAction#getTuijianTeam()
	 *
	 */
	public void getTuijianTeam()
	{
		
		teamList = teamService.getTeamByTuijian(5);
		if(teamList!=null)
		{	
			int size = teamList.size();
			for(int i=0;i<size;i++)
			{
				List<PicuterFour> picList = new ArrayList<PicuterFour>();
				picList = PicuterFour.unpackagePicFour(teamList.get(i).getLine().getPicArea());
		    	if(picList!=null)
		    	{
		    		teamList.get(i).setOrgUrl(picList.get(0).getBreUrl());
		    		teamList.get(i).setBreUrl(picList.get(0).getUrl());
		    	}
		    	else
		    	{
		    		teamList.get(i).setOrgUrl("public/20081205/image/scenerypic.gif");
		    		teamList.get(i).setBreUrl("public/20081205/image/scenerypic.gif");
		    	}
			}
		}
	}

	/**
	 * 显示地域导航列表（中部），10条随机签证信息（右部），网站信息（右部）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllArea()
	{
		tenVisas = new ArrayList<Visa>();
		areas = new ArrayList<Area>();
		getBannaerInfo();
		areas = areaService.getChildren("402880e51a5122c2011a5123cb160001");
		int size = areas.size();
		for(int i=0;i<size;i++)
		{
			areas.get(i).setAreaList(areaService.getChildren(areas.get(i).getAreaId()));
		}
		List<Visa> visas = (List<Visa>)visaService.getAll();
		size = visas.size()-10;
		Random rd = new Random();
		int start = rd.nextInt(size);
		for(int i=start;i<start+10;i++)
		{
			this.tenVisas.add(visas.get(i));
		}
		return SUCCESS;
	}
	/**
	 * 获取签证详细信息（中部），相关签证类别（上部），推荐开班计划（下部）
	 * @return
	 */
	public String getVisabyNation()
	{
		getTuijianTeam();
		getClientRightNews();
		getBannaerInfo();
		List<Visa> vs = new ArrayList<Visa>();
		if(!areaId.equals(""))
		{
			getAreaTeam(areaId);
			getRightPic(areaId);
			getRightScenery(areaId);
			vs = visaService.getVisaByNation(areaId);
			if(vs!=null)
			{
				List<Visa> temp = new ArrayList<Visa>();
				int size = vs.size();
				if(size==1)
				{
					VisaCity vc = new VisaCity();
					vc.setAreaName(vs.get(0).getCity().getName());
					vc.setVisas(vs);
					visas.add(vc);
				}
				for(int i=0;i<size;i++)
				{
					for(int j=i+1;j<size;j++)
					{
						if(vs.get(i)!=null&&vs.get(j)!=null)
						{
							if(vs.get(i).getCity().getAreaId().equals(vs.get(j).getCity().getAreaId()))
							{
								vs.get(j).setInterview(StringUtils.HtmlEncode(vs.get(j).getInterview()));
								vs.get(j).setTerm(StringUtils.HtmlEncode(vs.get(j).getTerm()));
								vs.get(j).setCycle(StringUtils.HtmlEncode(vs.get(j).getCycle()));
								vs.get(j).setAdvert(StringUtils.HtmlEncode(vs.get(j).getAdvert()));
								temp.add(vs.get(j));
								vs.set(j, null);
							}
							if(j==size-1)
							{
								vs.get(i).setInterview(StringUtils.HtmlEncode(vs.get(i).getInterview()));
								vs.get(i).setTerm(StringUtils.HtmlEncode(vs.get(i).getTerm()));
								vs.get(i).setCycle(StringUtils.HtmlEncode(vs.get(i).getCycle()));
								vs.get(i).setAdvert(StringUtils.HtmlEncode(vs.get(i).getAdvert()));
								temp.add(vs.get(i));
								VisaCity vc = new VisaCity();
								vc.setVisas(temp);
								vc.setAreaName(vs.get(i).getCity().getName());
								visas.add(vc);
								temp = new ArrayList<Visa>();
							}
						}
					}
				}
			}
			else
			{
				visas = null;
			}
			area = (Area)areaService.get(areaId);
		}
		else
		{
			visa = (Visa)visaService.get(visaId);
			area = visa.getNation();
			getAreaTeam(area.getAreaId());
			getRightPic(area.getAreaId());
			getRightScenery(area.getAreaId());
			vs = visaService.getVisaByNation(visa.getNation().getAreaId());
			if(vs!=null)
			{
				List<Visa> temp = new ArrayList<Visa>();
				int size = vs.size();
				if(size==1)
				{
					VisaCity vc = new VisaCity();
					vc.setAreaName(vs.get(0).getCity().getName());
					vc.setVisas(vs);
					visas.add(vc);
				}
				for(int i=0;i<size;i++)
				{
					for(int j=i+1;j<size;j++)
					{
						if(vs.get(i)!=null&&vs.get(j)!=null)
						{
							if(vs.get(i).getCity().getAreaId().equals(vs.get(j).getCity().getAreaId()))
							{
								vs.get(j).setInterview(StringUtils.HtmlEncode(vs.get(j).getInterview()));
								vs.get(j).setTerm(StringUtils.HtmlEncode(vs.get(j).getTerm()));
								vs.get(j).setCycle(StringUtils.HtmlEncode(vs.get(j).getCycle()));
								vs.get(j).setAdvert(StringUtils.HtmlEncode(vs.get(j).getAdvert()));
								temp.add(vs.get(j));
								vs.set(j, null);
							}
							if(j==size-1)
							{
								vs.get(i).setInterview(StringUtils.HtmlEncode(vs.get(i).getInterview()));
								vs.get(i).setTerm(StringUtils.HtmlEncode(vs.get(i).getTerm()));
								vs.get(i).setCycle(StringUtils.HtmlEncode(vs.get(i).getCycle()));
								vs.get(i).setAdvert(StringUtils.HtmlEncode(vs.get(i).getAdvert()));
								temp.add(vs.get(i));
								VisaCity vc = new VisaCity();
								vc.setVisas(temp);
								vc.setAreaName(vs.get(i).getCity().getName());
								visas.add(vc);
								temp = new ArrayList<Visa>();
							}
						}
					}
				}
			}
			else
			{
				visas = null;
			}
		}
		if(visaId.equals(""))
		{
		    if(vs!=null)
			{
		    	visa = vs.get(0);
				visa.setInterview(StringUtils.HtmlEncode(visa.getInterview()));
				visa.setTerm(StringUtils.HtmlEncode(visa.getTerm()));
				visa.setCycle(StringUtils.HtmlEncode(visa.getCycle()));
				visa.setAdvert(StringUtils.HtmlEncode(visa.getAdvert()));
			}
		}
		else
		{
			visa = (Visa)visaService.get(visaId);
			visa.setInterview(StringUtils.HtmlEncode(visa.getInterview()));
			visa.setTerm(StringUtils.HtmlEncode(visa.getTerm()));
			visa.setCycle(StringUtils.HtmlEncode(visa.getCycle()));
			visa.setAdvert(StringUtils.HtmlEncode(visa.getAdvert()));
		}
		return SUCCESS;
	}
	public IAreaService getAreaService()
	{
		return areaService;
	}
	public void setAreaService(IAreaService areaService)
	{
		this.areaService = areaService;
	}
	public IVisaService getVisaService()
	{
		return visaService;
	}
	public void setVisaService(IVisaService visaService)
	{
		this.visaService = visaService;
	}
	public List<Area> getAreas()
	{
		return areas;
	}
	public void setAreas(List<Area> areas)
	{
		this.areas = areas;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public Visa getVisa()
	{
		return visa;
	}
	public void setVisa(Visa visa)
	{
		this.visa = visa;
	}
	public String getVisaId()
	{
		return visaId;
	}
	public void setVisaId(String visaId)
	{
		this.visaId = visaId;
	}
	public Area getArea()
	{
		return area;
	}
	public void setArea(Area area)
	{
		this.area = area;
	}
	public List<VisaCity> getVisas()
	{
		return visas;
	}
	public void setVisas(List<VisaCity> visas)
	{
		this.visas = visas;
	}
	public List<TripContent> getGg_ss()
	{
		return gg_ss;
	}
	public void setGg_ss(List<TripContent> gg_ss)
	{
		this.gg_ss = gg_ss;
	}
	public List<String> getSearchList()
	{
		return searchList;
	}
	public void setSearchList(List<String> searchList)
	{
		this.searchList = searchList;
	}
	public ITripContentService getTripContentService()
	{
		return tripContentService;
	}
	public void setTripContentService(ITripContentService tripContentService)
	{
		this.tripContentService = tripContentService;
	}
	public WebSite getWebSite()
	{
		return webSite;
	}
	public void setWebSite(WebSite webSite)
	{
		this.webSite = webSite;
	}
	public IWebSiteService getWebSiteService()
	{
		return webSiteService;
	}
	public void setWebSiteService(IWebSiteService webSiteService)
	{
		this.webSiteService = webSiteService;
	}
	public List<Team> getTeamList()
	{
		return teamList;
	}
	public void setTeamList(List<Team> teamList)
	{
		this.teamList = teamList;
	}
	public ITeamService getTeamService()
	{
		return teamService;
	}
	public void setTeamService(ITeamService teamService)
	{
		this.teamService = teamService;
	}
	public List<Team> getAreaTeamList()
	{
		return areaTeamList;
	}
	public void setAreaTeamList(List<Team> areaTeamList)
	{
		this.areaTeamList = areaTeamList;
	}
	public INewsService getNewsService()
	{
		return newsService;
	}
	public void setNewsService(INewsService newsService)
	{
		this.newsService = newsService;
	}
	public List<News> getPicNews()
	{
		return picNews;
	}
	public void setPicNews(List<News> picNews)
	{
		this.picNews = picNews;
	}
	public List<News> getRightHelps()
	{
		return rightHelps;
	}
	public void setRightHelps(List<News> rightHelps)
	{
		this.rightHelps = rightHelps;
	}
	public List<News> getRightNews()
	{
		return rightNews;
	}
	public void setRightNews(List<News> rightNews)
	{
		this.rightNews = rightNews;
	}
	public List<SceneryPic> getRightPicList()
	{
		return rightPicList;
	}
	public void setRightPicList(List<SceneryPic> rightPicList)
	{
		this.rightPicList = rightPicList;
	}
	public List<Scenery> getRightScenreyList()
	{
		return rightScenreyList;
	}
	public void setRightScenreyList(List<Scenery> rightScenreyList)
	{
		this.rightScenreyList = rightScenreyList;
	}
	public ISceneryPicService getSceneryPicService()
	{
		return sceneryPicService;
	}
	public void setSceneryPicService(ISceneryPicService sceneryPicService)
	{
		this.sceneryPicService = sceneryPicService;
	}
	public ISceneryService getSceneryService()
	{
		return sceneryService;
	}
	public void setSceneryService(ISceneryService sceneryService)
	{
		this.sceneryService = sceneryService;
	}
	public int getSearchType()
	{
		return searchType;
	}
	public void setSearchType(int searchType)
	{
		this.searchType = searchType;
	}
	public List<Visa> getTenVisas()
	{
		return tenVisas;
	}
	public void setTenVisas(List<Visa> tenVisas)
	{
		this.tenVisas = tenVisas;
	}
	public int getBannerFlag()
	{
		return bannerFlag;
	}
	public void setBannerFlag(int bannerFlag)
	{
		this.bannerFlag = bannerFlag;
	}
}
