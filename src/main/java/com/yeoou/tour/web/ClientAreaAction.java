package com.yeoou.tour.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.yeoou.common.context.Global;
import com.yeoou.common.utils.StringUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.City;
import com.yeoou.tour.model.ContentPage;
import com.yeoou.tour.model.Continent;
import com.yeoou.tour.model.Nation;
import com.yeoou.tour.model.News;
import com.yeoou.tour.model.PicuterFour;
import com.yeoou.tour.model.Province;
import com.yeoou.tour.model.Scenery;
import com.yeoou.tour.model.SceneryPic;
import com.yeoou.tour.model.Team;
import com.yeoou.tour.model.TripContent;
import com.yeoou.tour.model.Visa;
import com.yeoou.tour.model.VisaCity;
import com.yeoou.tour.model.WebSite;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.ICityService;
import com.yeoou.tour.service.IContinentService;
import com.yeoou.tour.service.INationService;
import com.yeoou.tour.service.INewsService;
import com.yeoou.tour.service.IProvinceService;
import com.yeoou.tour.service.ISceneryPicService;
import com.yeoou.tour.service.ISceneryService;
import com.yeoou.tour.service.ITeamService;
import com.yeoou.tour.service.ITripContentService;
import com.yeoou.tour.service.IVisaService;
import com.yeoou.tour.service.IWebSiteService;
/**
 * <p>
 * Title: 前台地域显示显示模块
 * </p>
 * <p>
 * Description: 对地域进行详细的显示包括地图展示，国家城市详细说明<br/>
 * 包括城市页面cityDetail.shtml,省份页面provinceDetail.shtml,<br/>
 * 国家页面countryDetail.shtml,大洲页面continentDetail.shtml,<br/>
 * 
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class ClientAreaAction extends BaseActionSupport
{

	private static final long serialVersionUID = 1L;
	private IAreaService areaService;
	private ICityService cityService;
	private IVisaService visaService;
	private ITeamService teamService;
	private INewsService newsService;
	private List<VisaCity> visas = new ArrayList<VisaCity>();
	private IProvinceService provinceService;
	private ISceneryService sceneryService;
	private ISceneryPicService sceneryPicService;
	private INationService nationService;
	private IContinentService continentService;
	private City cityContent;
	private Province pro;
	private Nation nation;
	private Continent con;
	private Area country;
	private String content = "";
	private List<Area> directList;
	private List<Area> cityList;
	private List<Scenery> scList;
	private ContentPage page;
	private String cityId;
	private String areaId;
	private Area area;
	private Area province;
	private int pageId = 1;//页面页数
	private int bannerFlag = 8;
	private List<String> searchList = new ArrayList<String>();
	private IWebSiteService webSiteService;
	private List<Scenery> sceneryList;
	private List<Scenery> rightScenreyList;
	private List<SceneryPic> rightPicList;
	private List<TripContent> gg_ss;
	private WebSite webSite ;
	private String visaId = "";
	private Visa visa;
	private ITripContentService tripContentService;
	private List<Team> teamList;
	private List<Team> areaTeamList;
	private List<News> rightNews;
	private List<News> rightHelps;
	private List<News> picNews;
	private int searchType = 0;
	/**
	 * <p>
	 * 页面右边新闻显示模块
	 * 包括2张图片新闻展示
	 * 8条最新新闻列表
	 * 帮助信息展示
	 * </p>
	 */
	public void getClientRightNews()
	{
		this.picNews = newsService.getClientPicNews(2);
		this.rightNews = newsService.getNewsListForClient(8);
		this.rightHelps = newsService.getNewsByRemark("helpL1");
	}
	/**
	 * <p>
	 * 页面顶部信息展示
	 * 包括网站信息展示
	 * 搜索条件下拉框
	 * </p>
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
	 * <p>
	 * 根据地域标识获取相关开班计划（仅4条）页面右边显示<br/>
	 * 获取判定条件:<br/>
	 * 当地域为国家时取国家相关线路4条<br/>
	 * 如果不满4条如果是中国取国内线路补齐4条<br/>
	 * 如果是外国则取出境线路4条<br/>
	 * 当地域为省份时如果是中国则取4条线路信息<br/>
	 * 不满4条则在国内游取4条信息<br/>
	 * 如果不是中国则为外国城市，取4条城市线路信息<br/>
	 * 如果不满4条则取外国城市线路4条，如果还不满则从出境线路中取4条<br/>
	 * 当地域为城市信息时候取4条线路信息，如果不满则从省份当中去4条线路信息<br/>
	 * 如果依然不满4条则从国内游线路中取4条线路补充线路信息
	 * </p>
	 * @param areaId	地域标识
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
				
				List<Team> tlist = teamService.getTeamByFlag(Global.GUONEI);
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
						int j=0;
						//取国家线路
						List<Team> tlist1 = teamService.getTeamByFlag(Global.GUONEI);
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
					//取国家线路
					List<Team> tlist1 = teamService.getTeamByFlag(Global.GUONEI);
					if(tlist1!=null)
					{
						for(int i=0;i<4;i++)
						{
							this.areaTeamList.add(tlist1.get(i));
						}
					}
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
							List<Team> tlist2 = teamService.getTeamByFlag(Global.GUONEI);
							if(tlist2!=null)
							{
								for(int i=0;i<4;i++)
								{
									areaTeamList.add(tlist2.get(i));
								}
							}
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
						List<Team> tlist2 = teamService.getTeamByFlag(Global.GUONEI);
						if(tlist2!=null)
						{
							for(int i=0;i<4-size;i++)
							{
								areaTeamList.add(tlist2.get(i));
							}
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
					List<Team> tlist2 = teamService.getTeamByFlag(Global.GUONEI);
					if(tlist2!=null)
					{
						for(int i=0;i<4;i++)
						{
							areaTeamList.add(tlist2.get(i));
						}
					}
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
	 * 获取推荐线路信息（仅5条）
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
	 * 页面右边相关地域4张景点图片显示<br/>
	 * 获取地域相关的景点图片如果不满4张则回溯取得相关图片<br/>
	 * 如果不满4张继续回溯填充图片，一直回溯到国家取满图片信息<br/>
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
	 * 页面右边相关地域景点信息显示<br/>
	 * 如果地域相关景点信息不满4条则回溯查找景点信息并填满景点信息<br/>
	 * 如果不满4条则一直回溯到大洲景点<br/>
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
		for(int i=0;i<4;i++)
		{
			List<SceneryPic> spList = new ArrayList<SceneryPic>();
			spList = sceneryPicService.getPicListByScenery(rightScenreyList.get(i).getSceneryId());
			if(spList!=null) rightScenreyList.get(i).setDisplayUrl(spList.get(0).getBreviaryUrl());
			else rightScenreyList.get(i).setDisplayUrl("public/20081205/image/scenerypic.gif");
		}
	}
	/**
	 * 页面城市详细信息展示模块（包括网站信息（上部），页面导航(上部），推荐开班计划显示（4条）下部，<br/>
	 * 相关新闻显示信息（2张图片新闻，8条文字新闻，帮助信息）右部，相关地域开班计划（4条）右部，<br/>
	 * 地域相关景点信息展示（4张）右部
	 * 由于外国城市信息保存在省份信息内
	 * 则获取外国城市信息的时候用省份业务逻辑接口进行操作
	 * @return
	 */
	public String getCity()
	{
//		cityId="402880e51a512576011a512bc69b0005";
		getBannaerInfo();
		getTuijianTeam();
		getClientRightNews();
		getRightPic(cityId);
		getAreaTeam(cityId);
		Area area = (Area)areaService.get(cityId);
		areaId = cityId;
		getRightScenery(cityId);
		sceneryList = new ArrayList<Scenery>();
		sceneryList = sceneryService.getSceneryByArea(cityId, 5);
		if(area.getIsChina()!=1)
		{
			cityContent = new City();
			pro = provinceService.getProvinceByArea(cityId);
			cityContent.setArea(pro.getArea());
			cityContent.setContent(pro.getContent());
			cityContent.setGloze(pro.getGloze());
			cityContent.setHit(pro.getHit());
			cityContent.setIsTop(pro.getIsTop());
			cityContent.setMapUrl(pro.getMapUrl());
			
		}
		else
		{
			cityContent = cityService.getCityByArea(cityId);
		}
		
		if(cityContent!=null)
		{
			if(cityContent.getContent()!=null)
			{
				page = new ContentPage(cityContent.getContent());
				content = page.getResult(pageId);
			}
			cityContent.setGloze(StringUtils.HtmlEncode(cityContent.getGloze()));
			cityContent.setSynopsis(StringUtils.HtmlEncode(cityContent.getSynopsis()));
		}
		
		directList = areaService.getAreaList(cityId,1);
		province = (Area) areaService.get(cityContent.getArea().getParent());
		cityList = areaService.getChildren(cityContent.getArea().getParent());
		return SUCCESS;
	}
	/**
	 * 页面省份详细信息展示模块（包括网站信息（上部），页面导航(上部），推荐开班计划显示（4条）下部，<br/>
	 * 相关新闻显示信息（2张图片新闻，8条文字新闻，帮助信息）右部，相关地域开班计划（4条）右部，<br/>
	 * 地域相关景点信息展示（4张）右部
	 * @return
	 */
	public String getClientProvince()
	{
		getAreaTeam(areaId);
		getRightPic(areaId);
		getTuijianTeam();
		getClientRightNews();
		getRightScenery(areaId);
		this.pro = provinceService.getProvinceByArea(areaId);
		if(pro!=null)
		{
			if(pro.getContent()!=null)
			{
				page = new ContentPage(pro.getContent());
				content = page.getResult(pageId);
			}
		}
		pro.setGloze(StringUtils.HtmlEncode(pro.getGloze()));
		pro.setSynopsis(StringUtils.HtmlEncode(pro.getSynopsis()));
		directList = areaService.getAreaList(areaId,1);
		this.country = (Area) areaService.get(pro.getArea().getParent());
		cityList = areaService.getChildren(pro.getArea().getAreaId());
		sceneryList = new ArrayList<Scenery>();
		sceneryList = sceneryService.getSceneryByArea(areaId, 5);
		getBannaerInfo();
		return SUCCESS;
	}
	/**
	 * 页面大洲详（包括中国）细信息展示模块（包括网站信息（上部），<br/>
	 * 相关国家显示信息右部，相关景点展示（200）<br/>
	 * @return
	 */
	public String getClientContinent()
	{
		
		this.con = continentService.getContinentByArea(areaId);
		directList = areaService.getAreaList(areaId,1);
		cityList = areaService.getChildren(con.getArea().getAreaId());
		for(int i = 0;i<cityList.size();i++)
		{
			
			cityList.get(i).setAreaList(areaService.getChildren(cityList.get(i).getAreaId()));
			
		}
		sceneryList = new ArrayList<Scenery>();
		sceneryList = sceneryService.getSceneryByArea(areaId,200);
		getBannaerInfo();
		return SUCCESS;
	}
	/**
	 * 页面国家（不包括中国）详细信息展示模块（包括网站信息（上部），页面导航(上部），推荐开班计划显示（4条）下部，<br/>
	 * 相关新闻显示信息（2张图片新闻，8条文字新闻，帮助信息）右部，相关地域开班计划（4条）右部，<br/>
	 * 地域相关景点信息展示（4张）右部，相关签证信息展示<br/>
	 * @return
	 */
	public String getClientCountry()
	{
		
		nation = nationService.getNationByArea(this.areaId);
		directList = areaService.getAreaList(this.areaId,1);
		cityList = new ArrayList<Area>();
		
		getBannaerInfo();
		if(nation.getArea().getIsChina()==1)
		{
			List<Area> temp = new ArrayList<Area>();
			temp = areaService.getChildren(nation.getArea().getAreaId());
			for(int i=0;i<temp.size();i++)
			{
				temp.get(i).setAreaList(areaService.getChildren(temp.get(i).getAreaId()));
				if(temp.get(i).getName().equals("直辖市"))
				{
					cityList.add(temp.get(i));
				}
				else if(temp.get(i).getName().equals("特别行政区"))
				{
					cityList.add(temp.get(i));
				}
			}
			for(int i=0;i<temp.size();i++)
			{
				if(!temp.get(i).getName().equals("直辖市")&&!temp.get(i).getName().equals("特别行政区"))
				{
					cityList.add(temp.get(i));
				}
			}
			sceneryList = new ArrayList<Scenery>();
			sceneryList = sceneryService.getSceneryByArea(areaId,200);
			return "china";
		}
		else
		{
			getTuijianTeam();
			getAreaTeam(areaId);
			getRightPic(areaId);
			getRightScenery(areaId);
			getClientRightNews();
			sceneryList = new ArrayList<Scenery>();
			sceneryList = sceneryService.getSceneryByArea(areaId, 5);
			List<Visa> vs = new ArrayList<Visa>();
			if(!areaId.equals(""))
			{
				vs = visaService.getVisaByNation(areaId);
				if(vs!=null)
				{
					List<Visa> temp = new ArrayList<Visa>();
					int size = vs.size();
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
									vs.get(j).setZiliao(StringUtils.HtmlEncode(vs.get(j).getZiliao()));
									temp.add(vs.get(j));
									vs.set(j, null);
								}
								if(j==size-1)
								{
									vs.get(i).setInterview(StringUtils.HtmlEncode(vs.get(i).getInterview()));
									vs.get(i).setTerm(StringUtils.HtmlEncode(vs.get(i).getTerm()));
									vs.get(i).setCycle(StringUtils.HtmlEncode(vs.get(i).getCycle()));
									vs.get(i).setAdvert(StringUtils.HtmlEncode(vs.get(i).getAdvert()));
									vs.get(i).setZiliao(StringUtils.HtmlEncode(vs.get(i).getZiliao()));
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
				vs = visaService.getVisaByNation(visa.getNation().getAreaId());
				if(vs!=null)
				{
					List<Visa> temp = new ArrayList<Visa>();
					int size = vs.size();
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
									vs.get(j).setZiliao(StringUtils.HtmlEncode(vs.get(j).getZiliao()));
									temp.add(vs.get(j));
									vs.set(j, null);
								}
								if(j==size-1)
								{
									vs.get(i).setInterview(StringUtils.HtmlEncode(vs.get(i).getInterview()));
									vs.get(i).setTerm(StringUtils.HtmlEncode(vs.get(i).getTerm()));
									vs.get(i).setCycle(StringUtils.HtmlEncode(vs.get(i).getCycle()));
									vs.get(i).setAdvert(StringUtils.HtmlEncode(vs.get(i).getAdvert()));
									vs.get(i).setZiliao(StringUtils.HtmlEncode(vs.get(i).getZiliao()));
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
			if(nation!=null)
			{
				if(nation.getContent()!=null)
				{
					page = new ContentPage(nation.getContent());
					content = page.getResult(pageId);
				}
			}
			cityList = areaService.getChildren(nation.getArea().getAreaId());
			return "foreign";
		}
		
	}
	public List<Area> getCityList()
	{
		return cityList;
	}

	public void setCityList(List<Area> cityList)
	{
		this.cityList = cityList;
	}

	public Area getProvince()
	{
		return province;
	}

	public void setProvince(Area province)
	{
		this.province = province;
	}

	public City getCityContent()
	{
		return cityContent;
	}

	public void setCityContent(City cityContent)
	{
		this.cityContent = cityContent;
	}

	public Area getCountry()
	{
		return country;
	}

	public void setCountry(Area country)
	{
		this.country = country;
	}

	public Province getPro()
	{
		return pro;
	}

	public void setPro(Province pro)
	{
		this.pro = pro;
	}

	public String getAreaId()
	{
		return areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

	public IProvinceService getProvinceService()
	{
		return provinceService;
	}

	public void setProvinceService(IProvinceService provinceService)
	{
		this.provinceService = provinceService;
	}

	public Nation getNation()
	{
		return nation;
	}

	public void setNation(Nation nation)
	{
		this.nation = nation;
	}

	public INationService getNationService()
	{
		return nationService;
	}

	public void setNationService(INationService nationService)
	{
		this.nationService = nationService;
	}

	public ISceneryService getSceneryService()
	{
		return sceneryService;
	}

	public void setSceneryService(ISceneryService sceneryService)
	{
		this.sceneryService = sceneryService;
	}

	public List<Scenery> getScList()
	{
		return scList;
	}

	public void setScList(List<Scenery> scList)
	{
		this.scList = scList;
	}

	public Continent getCon()
	{
		return con;
	}

	public void setCon(Continent con)
	{
		this.con = con;
	}

	public IContinentService getContinentService()
	{
		return continentService;
	}

	public void setContinentService(IContinentService continentService)
	{
		this.continentService = continentService;
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
	public int getBannerFlag()
	{
		return bannerFlag;
	}

	public void setBannerFlag(int bannerFlag)
	{
		this.bannerFlag = bannerFlag;
	}

	public IAreaService getAreaService()
	{
		return areaService;
	}

	public void setAreaService(IAreaService areaService)
	{
		this.areaService = areaService;
	}

	public String getCityId()
	{
		return cityId;
	}
	public void setCityId(String cityId)
	{
		this.cityId = cityId;
	}

	public ICityService getCityService()
	{
		return cityService;
	}

	public void setCityService(ICityService cityService)
	{
		this.cityService = cityService;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public List<Area> getDirectList()
	{
		return directList;
	}

	public void setDirectList(List<Area> directList)
	{
		this.directList = directList;
	}

	public ContentPage getPage()
	{
		return page;
	}

	public void setPage(ContentPage page)
	{
		this.page = page;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}




	public Area getArea()
	{
		return area;
	}




	public void setArea(Area area)
	{
		this.area = area;
	}




	public List<Scenery> getRightScenreyList()
	{
		return rightScenreyList;
	}




	public void setRightScenreyList(List<Scenery> rightScenreyList)
	{
		this.rightScenreyList = rightScenreyList;
	}




	public List<Scenery> getSceneryList()
	{
		return sceneryList;
	}




	public void setSceneryList(List<Scenery> sceneryList)
	{
		this.sceneryList = sceneryList;
	}




	public ISceneryPicService getSceneryPicService()
	{
		return sceneryPicService;
	}




	public void setSceneryPicService(ISceneryPicService sceneryPicService)
	{
		this.sceneryPicService = sceneryPicService;
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




	public List<VisaCity> getVisas()
	{
		return visas;
	}




	public void setVisas(List<VisaCity> visas)
	{
		this.visas = visas;
	}




	public IVisaService getVisaService()
	{
		return visaService;
	}




	public void setVisaService(IVisaService visaService)
	{
		this.visaService = visaService;
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
	public List<SceneryPic> getRightPicList()
	{
		return rightPicList;
	}
	public void setRightPicList(List<SceneryPic> rightPicList)
	{
		this.rightPicList = rightPicList;
	}
	public INewsService getNewsService()
	{
		return newsService;
	}
	public void setNewsService(INewsService newsService)
	{
		this.newsService = newsService;
	}

	public List<News> getRightHelps()
	{
		return rightHelps;
	}
	public void setRightHelps(List<News> rightHelps)
	{
		this.rightHelps = rightHelps;
	}
	public int getSearchType()
	{
		return searchType;
	}
	public void setSearchType(int searchType)
	{
		this.searchType = searchType;
	}
	public List<News> getPicNews()
	{
		return picNews;
	}
	public void setPicNews(List<News> picNews)
	{
		this.picNews = picNews;
	}
	public List<News> getRightNews()
	{
		return rightNews;
	}
	public void setRightNews(List<News> rightNews)
	{
		this.rightNews = rightNews;
	}
	public List<Team> getAreaTeamList()
	{
		return areaTeamList;
	}
	public void setAreaTeamList(List<Team> areaTeamList)
	{
		this.areaTeamList = areaTeamList;
	}
}
