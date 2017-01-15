package com.yeoou.tour.web;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.yeoou.common.context.Global;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.model.MailInfo;
import com.yeoou.common.utils.MailUtils;
import com.yeoou.common.utils.StringUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.ContentPage;
import com.yeoou.tour.model.LetterOfScenery;
import com.yeoou.tour.model.News;
import com.yeoou.tour.model.PicuterFour;
import com.yeoou.tour.model.Scenery;
import com.yeoou.tour.model.SceneryCount;
import com.yeoou.tour.model.SceneryPic;
import com.yeoou.tour.model.SceneryType;
import com.yeoou.tour.model.Team;
import com.yeoou.tour.model.TripContent;
import com.yeoou.tour.model.WebSite;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.ICountSceneryService;
import com.yeoou.tour.service.INewsService;
import com.yeoou.tour.service.ISceneryPicService;
import com.yeoou.tour.service.ISceneryService;
import com.yeoou.tour.service.ISceneryTypeService;
import com.yeoou.tour.service.ITeamService;
import com.yeoou.tour.service.ITripContentService;
import com.yeoou.tour.service.IWebSiteService;
/**
 * <p>
 * Title: 前台相关景点展示模块
 * </p>
 * <p>
 * Description: 包括景点相关图片展示和地域相关图片展示<br/>
 * 景点详细信息页面sceneryDetail.shtml,和景点页面getAllScenery.shtml
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class ClientSceneryAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IAreaService areaService;
	private ISceneryService sceneryService;
	private ITeamService teamService;
	private List<Team> teamList;
	private ISceneryPicService sceneryPicService;
	private ISceneryTypeService sceneryTypeService;
	private ICountSceneryService countSceneryService;
	private INewsService newsService;
	private String sceneryId;
	private ContentPage contentPage;
	private int pageId = 1;
	private String content;
	private Scenery scenery;
	private Area province;
	private List<Area> cityList;
	private Area currentCity;
	private int showType = 1;
	private String letter = "";
	private String areaId = "";
	private String typeId = "";
	private String keyWord = "";
	private int searchType = 1;
	private List<LetterOfScenery> losList =new ArrayList<LetterOfScenery>();
	private List<SceneryType> renwenList;
	private List<SceneryType> ziranList;
	private List<Scenery> sc_a;
	private List<Scenery> sc_b;
	private List<Scenery> sc_c;
	private List<Scenery> sc_d;
	private List<Scenery> sc_e;
	private List<Scenery> sc_f;
	private List<Scenery> sc_g;
	private List<Scenery> sc_h;
	private List<Scenery> sc_i;
	private List<Scenery> sc_j;
	private List<Scenery> sc_k;
	private List<Scenery> sc_l;
	private List<Scenery> sc_m;
	private List<Scenery> sc_n;
	private List<Scenery> sc_o;
	private List<Scenery> sc_p;
	private List<Scenery> sc_q;
	private List<Scenery> sc_r;
	private List<Scenery> sc_s;
	private List<Scenery> sc_t;
	private List<Scenery> sc_u;
	private List<Scenery> sc_v;
	private List<Scenery> sc_w;
	private List<Scenery> sc_x;
	private List<Scenery> sc_y;
	private List<Scenery> sc_z;
	private List<Scenery> scList;
	private Area area;
	private int pageSize = 10;
	private int page = 1;
	private List<String> arrayLetter = new ArrayList<String>();
	private List<Area> directList;
	private Page publicPage;
	private SceneryCount scnt ;
	private int bannerFlag = 8;
	private List<String> searchList = new ArrayList<String>();
	private IWebSiteService webSiteService;
	private List<Team> areaTeamList;
	private List<TripContent> gg_ss;
	private List<SceneryPic> rightPicList;
	private WebSite webSite ;
	private List<Scenery> rightScenreyList;
	private ITripContentService tripContentService;
	private List<News> rightNews ;
	private List<News> rightHelps;
	private List<News> picNews;
	private List<Scenery> topTen;
	private String cityId="";
//	public String testEmail()
//	{
//		MailInfo mailInfo = new MailInfo();
//	      mailInfo.setMailServerHost("127.0.0.1");    
//	      mailInfo.setMailServerPort("25");    
//	      mailInfo.setValidate(true);    
//	      mailInfo.setUserName("admin");    
//	      mailInfo.setPassword("admin");//您的邮箱密码    
//	      mailInfo.setFromAddress("admin@tutu6.com");    
//	      mailInfo.setToAddress("rurounikexin@gmail.com");    
//	      mailInfo.setSubject("测试EMAIL发送");    
//	      mailInfo.setContent("发送的相关内容");    
//	         //这个类主要来发送邮件   
//	      MailUtils.sendHtmlMail(mailInfo);//发送html格式 
//		return SUCCESS;
//	}
	/**
	 * 网站信息模块
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
	 * 右边新闻模块包括（2条推荐图片新闻），8条最新新闻，帮助新闻一组
	 *
	 */
	public void getClientRightNews()
	{
		this.picNews = newsService.getClientPicNews(2);
		this.rightNews = newsService.getNewsListForClient(8);
		this.rightHelps = newsService.getNewsByRemark("helpL1");
	}
	/**
	 * 景点图片一组（4张）如果当前景点图片不够在相关地域中取图片进行补充
	 * @param areaId	地域参数
	 * @param spList	已有图片列表
	 */
	public void getRightPic(String areaId,List<SceneryPic> spList)
	{
		List<SceneryPic> picList = new ArrayList<SceneryPic>();
		picList = sceneryPicService.getPicListByArea(areaId);
		this.rightPicList = new ArrayList<SceneryPic>();
		if(spList!=null)
		{ 
			if(spList.size()>4)
			{
				for(int i=0;i<4;i++)
				{
					rightPicList.add(spList.get(i));
				}
			}
			else
			{
				for(int i=0;i<spList.size();i++)
				{
					rightPicList.add(spList.get(i));
				}
			}
		}
		if(rightPicList.size()==0)
		{
			if(picList!=null)//city
			{
				int size = picList.size();
				if(size>4)
				{
					size = 4;
					for(int i=0;i<size;i++)
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
					area = (Area)areaService.get(areaId);//province
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
				area = (Area)areaService.get(areaId);//province
				List<SceneryPic> ppList = new ArrayList<SceneryPic>();
				int j=0;
				ppList = sceneryPicService.getPicListByArea(area.getParent());
				if(ppList!=null)
				{
					int size = ppList.size();
					if(size>4)
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
							rightPicList.add(picList.get(i));
						}
						Area parea = new Area();
						parea = (Area)areaService.get(area.getParent());//country
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
									}
								}
								j++;
							}
						}
					}
				}
			}
		}
		else
		{
			if(rightPicList.size()<4)
			{
				int begin = 4-rightPicList.size();
				if(picList!=null)
				{
					int size = picList.size();
					if(size>4)
					{
						size = 4;
						for(int i=0;i<begin;i++)
						{
							rightPicList.add(picList.get(i));
						}
					}
					else
					{
						for(int i=0;i<begin;i++)
						{
							rightPicList.add(picList.get(i));
						}
						Area area = new Area();
						area = (Area)areaService.get(areaId);//province
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
						if(size>4)
						{
							size = 4;
							for(int i=0;i<size;i++)
							{
								rightPicList.add(ppList.get(i));
							}
						}
					}
				}
			}
		}
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
							List<Team> tlist2 = teamService.getTeamByFlag(Global.GUONEI);
							for(int i=0;i<4;i++)
							{
								areaTeamList.add(tlist2.get(i));
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
						List<Team> tlist2 = teamService.getTeamByFlag(Global.GUONEI);
						for(int i=0;i<4;i++)
						{
							areaTeamList.add(tlist2.get(i));
						}
					}
				}
				else
				{
//					//去国家线路
					List<Team> tlist2 = teamService.getTeamByFlag(Global.GUONEI);
					for(int i=0;i<4;i++)
					{
						areaTeamList.add(tlist2.get(i));
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
				size = 4;
				for(int i=0;i<size;i++)
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
					size = 4;
					for(int i=0;i<size;i++)
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
	 * 景点展示页面包括网站信息（上部），推荐开班计划（下部），景点列表显示<br/>
	 * 10个热门景点展示（左部）景点分类导航（左部），当以文字模式显示时以26字母<br/>
	 * 列表方式显示相关景点列表，当为文字模式且有首字母参数时显示与首字母相关景点<br/>
	 * 当为图片模式的时候以图片展示景点列表
	 * @return
	 */
	public String getAllScenery()
	{
		getBannaerInfo();
		getTuijianTeam();
		List<SceneryCount> scntList = countSceneryService.getSceneryByMonthHit(10);
		if(scntList!=null)
		{
			this.topTen = new ArrayList<Scenery>();
			for(int i=0;i<scntList.size();i++)
			{
				Scenery sc = new Scenery();
				sc = (Scenery) sceneryService.get(scntList.get(i).getSceneryId());
				List<SceneryPic> spList = new ArrayList<SceneryPic>();
				spList = this.sceneryPicService.getPicListByScenery(sc.getSceneryId());
				if(spList!=null) sc.setDisplayUrl(spList.get(0).getMiniUrl());
				else sc.setDisplayUrl("public/20081205/image/scenerypic.gif");
				this.topTen.add(sc);
			}
		}
		this.arrayLetter.add("A");
		this.arrayLetter.add("B");
		this.arrayLetter.add("C");
		this.arrayLetter.add("D");
		this.arrayLetter.add("E");
		this.arrayLetter.add("F");
		this.arrayLetter.add("G");
		this.arrayLetter.add("H");
		this.arrayLetter.add("I");
		this.arrayLetter.add("J");
		this.arrayLetter.add("K");
		this.arrayLetter.add("L");
		this.arrayLetter.add("M");
		this.arrayLetter.add("N");
		this.arrayLetter.add("O");
		this.arrayLetter.add("P");
		this.arrayLetter.add("Q");
		this.arrayLetter.add("R");
		this.arrayLetter.add("S");
		this.arrayLetter.add("T");
		this.arrayLetter.add("U");
		this.arrayLetter.add("V");
		this.arrayLetter.add("W");
		this.arrayLetter.add("X");
		this.arrayLetter.add("Y");
		this.arrayLetter.add("Z");
		
		this.renwenList = sceneryTypeService.getSceneryTypeList(Global.SCENERY_TYPE_RENWEN);
		this.ziranList = sceneryTypeService.getSceneryTypeList(Global.SCENERY_TYPE_ZIRAN);
		if(showType == 0 && letter.equals(""))
		{
			sc_a = sceneryService.getSceneryListByLetter("A",typeId,areaId);
			sc_b= sceneryService.getSceneryListByLetter("B",typeId,areaId);
			sc_c= sceneryService.getSceneryListByLetter("C",typeId,areaId);
			sc_d= sceneryService.getSceneryListByLetter("D",typeId,areaId);
			sc_e= sceneryService.getSceneryListByLetter("E",typeId,areaId);
			sc_f= sceneryService.getSceneryListByLetter("F",typeId,areaId);
			sc_g= sceneryService.getSceneryListByLetter("G",typeId,areaId);
			sc_h= sceneryService.getSceneryListByLetter("H",typeId,areaId);
			sc_i= sceneryService.getSceneryListByLetter("I",typeId,areaId);
			sc_j= sceneryService.getSceneryListByLetter("J",typeId,areaId);
			sc_k= sceneryService.getSceneryListByLetter("K",typeId,areaId);
			sc_l= sceneryService.getSceneryListByLetter("L",typeId,areaId);
			sc_m= sceneryService.getSceneryListByLetter("M",typeId,areaId);
			sc_n= sceneryService.getSceneryListByLetter("N",typeId,areaId);
			sc_o= sceneryService.getSceneryListByLetter("O",typeId,areaId);
			sc_p= sceneryService.getSceneryListByLetter("P",typeId,areaId);
			sc_q= sceneryService.getSceneryListByLetter("Q",typeId,areaId);
			sc_r= sceneryService.getSceneryListByLetter("R",typeId,areaId);
			sc_s= sceneryService.getSceneryListByLetter("S",typeId,areaId);
			sc_t= sceneryService.getSceneryListByLetter("T",typeId,areaId);
			sc_u= sceneryService.getSceneryListByLetter("U",typeId,areaId);
			sc_v= sceneryService.getSceneryListByLetter("V",typeId,areaId);
			sc_w= sceneryService.getSceneryListByLetter("W",typeId,areaId);
			sc_x= sceneryService.getSceneryListByLetter("X",typeId,areaId);
			sc_y= sceneryService.getSceneryListByLetter("Y",typeId,areaId);
			sc_z= sceneryService.getSceneryListByLetter("Z",typeId,areaId);
			LetterOfScenery lof = new LetterOfScenery();
			lof.setLetter("A");
			lof.setScList(sc_a);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("B");
			lof.setScList(sc_b);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("C");
			lof.setScList(sc_c);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("D");
			lof.setScList(sc_d);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("E");
			lof.setScList(sc_e);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("F");
			lof.setScList(sc_f);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("G");
			lof.setScList(sc_g);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("H");
			lof.setScList(sc_h);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("I");
			lof.setScList(sc_i);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("J");
			lof.setScList(sc_j);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("K");
			lof.setScList(sc_k);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("L");
			lof.setScList(sc_l);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("M");
			lof.setScList(sc_m);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("N");
			lof.setScList(sc_n);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("O");
			lof.setScList(sc_o);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("P");
			lof.setScList(sc_p);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("Q");
			lof.setScList(sc_q);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("R");
			lof.setScList(sc_r);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("S");
			lof.setScList(sc_s);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("T");
			lof.setScList(sc_t);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("U");
			lof.setScList(sc_u);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("V");
			lof.setScList(sc_v);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("W");
			lof.setScList(sc_w);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("X");
			lof.setScList(sc_x);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("Y");
			lof.setScList(sc_y);
			losList.add(lof);
			lof = new LetterOfScenery();
			lof.setLetter("Z");
			lof.setScList(sc_z);
			losList.add(lof);
		}
		else if(showType==0 &&!letter.equals(""))
		{
			this.scList = new ArrayList<Scenery>();
			scList = sceneryService.getSceneryByLetter(letter);
		}
		else
		{
			this.publicPage = new Page();
			publicPage = sceneryService.getAllClientScenery(letter, keyWord, areaId, typeId, page, pageSize);
			if(publicPage!=null)
			{
				this.scList = new ArrayList<Scenery>();
				scList = (List<Scenery>)publicPage.getResult();
				if(showType==1)
				{
					int size = scList.size();
					for(int i=0;i<size;i++)
					{
						List<SceneryPic> spList = new ArrayList<SceneryPic>();
						spList = this.sceneryPicService.getPicListByScenery(scList.get(i).getSceneryId());
						if(spList!=null) scList.get(i).setDisplayUrl(spList.get(0).getBreviaryUrl());
						else scList.get(i).setDisplayUrl("public/20081205/image/scenerypic.gif");
					}
				}
			}
		}
		if(areaId.equals(""))
		{
			if(scList.size()==1)
			{
				Scenery sc = new Scenery();
				sc = sceneryService.getSceneryInfo(scList.get(0).getSceneryId());
				List<Area> areas = new ArrayList<Area>(sc.getAreas());
				for(int i=0;i<areas.size();i++)
				{
					if(areas.get(i).getIsChina()==1)
					{
						if(areas.get(i).getDepth()==4)
						{
							areaId = areas.get(i).getAreaId();
						}
					}
					else
					{
						if(areas.get(i).getDepth()==3)
						{
							areaId = areas.get(i).getAreaId();
						}
					}
				}
				area = (Area)areaService.get(areaId);
				directList =areaService.getAreaList(areaId,1);
			}
			else
			{
				area = areaService.getAreaByName("亚洲");
				directList =areaService.getAreaList(this.area.getAreaId(),0);
			}
			
		}
		else
		{
			area = (Area)areaService.get(areaId);
			directList = areaService.getAreaList(this.areaId,1);
		}
		
		
		return SUCCESS;
	}
	/**
	 * 显示景点详细信息，包括网站信息（上部），推荐开班计划（下部），新闻组（右部）{@link #getRightNews()}<br/>
	 * 景点图片列表4张（右部）{@link #getRightScenery()},相关开班线路4条{@link #getAreaTeam()}<br/>
	 * 对景点进行统计，景点地域导航（上部）
	 * @return
	 */
	public String getSingleScenry()
	{
		getBannaerInfo();
		getTuijianTeam();
		getClientRightNews();
		scenery = sceneryService.getSceneryInfo(sceneryId);
		List<SceneryPic> rpList = sceneryPicService.getPicListByScenery(scenery.getSceneryId());
		List<Area> alist = new ArrayList<Area>(scenery.getAreas());
		for(int i=0;i<alist.size();i++)
		{
			if(alist.get(i).getIsChina()==1)
			{
				if(alist.get(i).getDepth()==4)
				{
					this.getAreaTeam(alist.get(i).getAreaId());
					this.getRightPic(alist.get(i).getAreaId(),rpList);
					getRightScenery(alist.get(i).getAreaId());
					break;
				}
			}
			else
			{
				if(alist.get(i).getDepth()==3)
				{
					this.getAreaTeam(alist.get(i).getAreaId());
					this.getRightPic(alist.get(i).getAreaId(),rpList);
					getRightScenery(alist.get(i).getAreaId());
					break;
				}
			}
		}
	    scnt = new SceneryCount();
		scnt = countSceneryService.getSceneryCountBySceneryId(sceneryId);
		if(scnt==null)
		{
			scnt = new SceneryCount();
			int i=0;
			i++;
			Calendar cal = Calendar.getInstance();
			scnt.setMonth(cal.get(Calendar.MONTH));
			scnt.setMonthHit(i);
			scnt.setWeek(cal.get(Calendar.DAY_OF_WEEK));
			scnt.setWeekHit(i);
			scnt.setHit(i);
			scnt.setSceneryId(sceneryId);
			countSceneryService.save(scnt);
		}
		else
		{
			Calendar cal = Calendar.getInstance();
			int i=scnt.getHit();
			i++;
			scnt.setHit(i);
			if(scnt.getMonth()!=cal.get(Calendar.MONTH))
			{
				int j = 0;
				scnt.setMonthHit(j);
				scnt.setMonth(cal.get(Calendar.MONTH));
			}
			else
			{
				int j = scnt.getMonthHit();
				j++;
				scnt.setMonthHit(j);
			}
			if(scnt.getWeek()!=cal.get(Calendar.DAY_OF_WEEK))
			{
				int j = scnt.getWeekHit();
				j=j+1;
				scnt.setWeekHit(j);
			}
			else
			{
				int j = 0;
				scnt.setWeekHit(j);
			}
			countSceneryService.merge(scnt);
		}
		if(scenery!=null)
		{
			if(scenery.getContent()!=null)
			{
				contentPage = new ContentPage(scenery.getContent());
				content = contentPage.getResult(pageId);
			}
			else
			{
				contentPage = null;
				content = "";
			}
			scenery.setTicketPrice(StringUtils.HtmlEncode(scenery.getTicketPrice()));
		}
		cityId = "";
		List<Area> areaList = new ArrayList<Area>(scenery.getAreas());
		int length = areaList.size();
		for(int i=0;i<length;i++)
		{
			if(areaList.get(i).getIsChina()==1)
			{
				if(areaList.get(i).getDepth()==4)
				{
					cityId = areaList.get(i).getAreaId();
					
				}
			}
			else
			{
				if(areaList.get(i).getDepth()==3)
				{
					cityId = areaList.get(i).getAreaId();
				}
			}
		}
		directList = areaService.getAreaList(cityId,1);
		currentCity = (Area)areaService.get(cityId);
		province = (Area) areaService.get(currentCity.getParent());
		cityList = areaService.getChildren(currentCity.getParent());
		return SUCCESS;
	}
	public Scenery getScenery()
	{
		return scenery;
	}
	public void setScenery(Scenery scenery)
	{
		this.scenery = scenery;
	}
	public IAreaService getAreaService()
	{
		return areaService;
	}
	public void setAreaService(IAreaService areaService)
	{
		this.areaService = areaService;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public int getPageId()
	{
		return pageId;
	}
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}
	public String getSceneryId()
	{
		return sceneryId;
	}
	public void setSceneryId(String sceneryId)
	{
		this.sceneryId = sceneryId;
	}
	public ISceneryService getSceneryService()
	{
		return sceneryService;
	}
	public void setSceneryService(ISceneryService sceneryService)
	{
		this.sceneryService = sceneryService;
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
	public Area getCurrentCity()
	{
		return currentCity;
	}
	public void setCurrentCity(Area currentCity)
	{
		this.currentCity = currentCity;
	}
	public List<Area> getDirectList()
	{
		return directList;
	}
	public void setDirectList(List<Area> directList)
	{
		this.directList = directList;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public List<String> getArrayLetter()
	{
		return arrayLetter;
	}
	public void setArrayLetter(List<String> arrayLetter)
	{
		this.arrayLetter = arrayLetter;
	}
	public ContentPage getContentPage()
	{
		return contentPage;
	}
	public void setContentPage(ContentPage contentPage)
	{
		this.contentPage = contentPage;
	}
	public String getKeyWord()
	{
		return keyWord;
	}
	public void setKeyWord(String keyWord)
	{
		this.keyWord = keyWord;
	}
	public String getLetter()
	{
		return letter;
	}
	public void setLetter(String letter)
	{
		this.letter = letter;
	}
	public int getPage()
	{
		return page;
	}
	public void setPage(int page)
	{
		this.page = page;
	}
	public Page getPublicPage()
	{
		return publicPage;
	}
	public void setPublicPage(Page publicPage)
	{
		this.publicPage = publicPage;
	}
	public List<SceneryType> getRenwenList()
	{
		return renwenList;
	}
	public void setRenwenList(List<SceneryType> renwenList)
	{
		this.renwenList = renwenList;
	}
	public List<Scenery> getSc_a()
	{
		return sc_a;
	}
	public void setSc_a(List<Scenery> sc_a)
	{
		this.sc_a = sc_a;
	}
	public List<Scenery> getSc_b()
	{
		return sc_b;
	}
	public void setSc_b(List<Scenery> sc_b)
	{
		this.sc_b = sc_b;
	}
	public List<Scenery> getSc_c()
	{
		return sc_c;
	}
	public void setSc_c(List<Scenery> sc_c)
	{
		this.sc_c = sc_c;
	}
	public List<Scenery> getSc_d()
	{
		return sc_d;
	}
	public void setSc_d(List<Scenery> sc_d)
	{
		this.sc_d = sc_d;
	}
	public List<Scenery> getSc_e()
	{
		return sc_e;
	}
	public void setSc_e(List<Scenery> sc_e)
	{
		this.sc_e = sc_e;
	}
	public List<Scenery> getSc_f()
	{
		return sc_f;
	}
	public void setSc_f(List<Scenery> sc_f)
	{
		this.sc_f = sc_f;
	}
	public List<Scenery> getSc_g()
	{
		return sc_g;
	}
	public void setSc_g(List<Scenery> sc_g)
	{
		this.sc_g = sc_g;
	}
	public List<Scenery> getSc_h()
	{
		return sc_h;
	}
	public void setSc_h(List<Scenery> sc_h)
	{
		this.sc_h = sc_h;
	}
	public List<Scenery> getSc_i()
	{
		return sc_i;
	}
	public void setSc_i(List<Scenery> sc_i)
	{
		this.sc_i = sc_i;
	}
	public List<Scenery> getSc_j()
	{
		return sc_j;
	}
	public void setSc_j(List<Scenery> sc_j)
	{
		this.sc_j = sc_j;
	}
	public List<Scenery> getSc_k()
	{
		return sc_k;
	}
	public void setSc_k(List<Scenery> sc_k)
	{
		this.sc_k = sc_k;
	}
	public List<Scenery> getSc_l()
	{
		return sc_l;
	}
	public void setSc_l(List<Scenery> sc_l)
	{
		this.sc_l = sc_l;
	}
	public List<Scenery> getSc_m()
	{
		return sc_m;
	}
	public void setSc_m(List<Scenery> sc_m)
	{
		this.sc_m = sc_m;
	}
	public List<Scenery> getSc_n()
	{
		return sc_n;
	}
	public void setSc_n(List<Scenery> sc_n)
	{
		this.sc_n = sc_n;
	}
	public List<Scenery> getSc_o()
	{
		return sc_o;
	}
	public void setSc_o(List<Scenery> sc_o)
	{
		this.sc_o = sc_o;
	}
	public List<Scenery> getSc_p()
	{
		return sc_p;
	}
	public void setSc_p(List<Scenery> sc_p)
	{
		this.sc_p = sc_p;
	}
	public List<Scenery> getSc_q()
	{
		return sc_q;
	}
	public void setSc_q(List<Scenery> sc_q)
	{
		this.sc_q = sc_q;
	}
	public List<Scenery> getSc_r()
	{
		return sc_r;
	}
	public void setSc_r(List<Scenery> sc_r)
	{
		this.sc_r = sc_r;
	}
	public List<Scenery> getSc_s()
	{
		return sc_s;
	}
	public void setSc_s(List<Scenery> sc_s)
	{
		this.sc_s = sc_s;
	}
	public List<Scenery> getSc_t()
	{
		return sc_t;
	}
	public void setSc_t(List<Scenery> sc_t)
	{
		this.sc_t = sc_t;
	}
	public List<Scenery> getSc_u()
	{
		return sc_u;
	}
	public void setSc_u(List<Scenery> sc_u)
	{
		this.sc_u = sc_u;
	}
	public List<Scenery> getSc_v()
	{
		return sc_v;
	}
	public void setSc_v(List<Scenery> sc_v)
	{
		this.sc_v = sc_v;
	}
	public List<Scenery> getSc_w()
	{
		return sc_w;
	}
	public void setSc_w(List<Scenery> sc_w)
	{
		this.sc_w = sc_w;
	}
	public List<Scenery> getSc_x()
	{
		return sc_x;
	}
	public void setSc_x(List<Scenery> sc_x)
	{
		this.sc_x = sc_x;
	}
	public List<Scenery> getSc_y()
	{
		return sc_y;
	}
	public void setSc_y(List<Scenery> sc_y)
	{
		this.sc_y = sc_y;
	}
	public List<Scenery> getSc_z()
	{
		return sc_z;
	}
	public void setSc_z(List<Scenery> sc_z)
	{
		this.sc_z = sc_z;
	}
	public ISceneryPicService getSceneryPicService()
	{
		return sceneryPicService;
	}
	public void setSceneryPicService(ISceneryPicService sceneryPicService)
	{
		this.sceneryPicService = sceneryPicService;
	}
	public ISceneryTypeService getSceneryTypeService()
	{
		return sceneryTypeService;
	}
	public void setSceneryTypeService(ISceneryTypeService sceneryTypeService)
	{
		this.sceneryTypeService = sceneryTypeService;
	}
	public List<Scenery> getScList()
	{
		return scList;
	}
	public void setScList(List<Scenery> scList)
	{
		this.scList = scList;
	}
	public int getShowType()
	{
		return showType;
	}
	public void setShowType(int showType)
	{
		this.showType = showType;
	}
	public String getTypeId()
	{
		return typeId;
	}
	public void setTypeId(String typeId)
	{
		this.typeId = typeId;
	}
	public List<SceneryType> getZiranList()
	{
		return ziranList;
	}
	public void setZiranList(List<SceneryType> ziranList)
	{
		this.ziranList = ziranList;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	public List<LetterOfScenery> getLosList()
	{
		return losList;
	}
	public void setLosList(List<LetterOfScenery> losList)
	{
		this.losList = losList;
	}
	public ICountSceneryService getCountSceneryService()
	{
		return countSceneryService;
	}
	public void setCountSceneryService(ICountSceneryService countSceneryService)
	{
		this.countSceneryService = countSceneryService;
	}
	public SceneryCount getScnt()
	{
		return scnt;
	}
	public void setScnt(SceneryCount scnt)
	{
		this.scnt = scnt;
	}
	public int getBannerFlag()
	{
		return bannerFlag;
	}
	public void setBannerFlag(int bannerFlag)
	{
		this.bannerFlag = bannerFlag;
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
	public INewsService getNewsService()
	{
		return newsService;
	}
	public void setNewsService(INewsService newsService)
	{
		this.newsService = newsService;
	}
	public void setRightNews(List<News> rightNews)
	{
		this.rightNews = rightNews;
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
	public int getSearchType()
	{
		return searchType;
	}
	public void setSearchType(int searchType)
	{
		this.searchType = searchType;
	}
	public Area getArea()
	{
		return area;
	}
	public void setArea(Area area)
	{
		this.area = area;
	}
	public List<Scenery> getTopTen()
	{
		return topTen;
	}
	public void setTopTen(List<Scenery> topTen)
	{
		this.topTen = topTen;
	}
	public String getCityId()
	{
		return cityId;
	}
	public void setCityId(String cityId)
	{
		this.cityId = cityId;
	}
	public List<News> getPicNews()
	{
		return picNews;
	}
	public void setPicNews(List<News> picNews)
	{
		this.picNews = picNews;
	}
	

}
