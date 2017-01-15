package com.yeoou.tour.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.ContentPage;
import com.yeoou.tour.model.HelpInfo;
import com.yeoou.tour.model.News;
import com.yeoou.tour.model.NewsCount;
import com.yeoou.tour.model.NewsType;
import com.yeoou.tour.model.PicuterFour;
import com.yeoou.tour.model.Scenery;
import com.yeoou.tour.model.SceneryCount;
import com.yeoou.tour.model.SceneryPic;
import com.yeoou.tour.model.Team;
import com.yeoou.tour.model.TripContent;
import com.yeoou.tour.model.WebSite;
import com.yeoou.tour.service.ICountSceneryService;
import com.yeoou.tour.service.INewsCountService;
import com.yeoou.tour.service.INewsService;
import com.yeoou.tour.service.INewsTypeService;
import com.yeoou.tour.service.ISceneryPicService;
import com.yeoou.tour.service.ISceneryService;
import com.yeoou.tour.service.ITeamService;
import com.yeoou.tour.service.ITripContentService;
import com.yeoou.tour.service.IWebSiteService;
/**
 * <p>
 * Title: 前台相关新闻展示模块
 * </p>
 * <p>
 * Description: 页面类别显示相关新闻页面，帮助信息展示页面，相关信息详细展示页面<br/>
 * 订单第二步信息填写stepOne.shtml,订单确定信息页面addOrder.shtml<br/>
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class ClientNewsAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private INewsService newsService;
	private INewsTypeService newsTypeService;
	private INewsCountService newsCountService;
	private ICountSceneryService countSceneryService;
	private ISceneryService sceneryService;
	private ITeamService teamService;
	private ISceneryPicService sceneryPicService;
	private String typeId ="";
	private int searchType = 0;
	private News news;
	private List<News> newsList;
	private NewsType newsType;
	private int pageNo = 0;
	private int pageSize = 48;
	private String newsId = "";
	private ContentPage page;
	private int pageId = 1;
	private String content;
	private NewsCount ncnt;
	private int bannerFlag = 0;
	private List<HelpInfo> helpList = new ArrayList<HelpInfo>();
	private List<News> helpL1;
	private List<News> helpL2;
	private List<News> helpL3;
	private List<String> searchList = new ArrayList<String>();
	private IWebSiteService webSiteService;
	private List<TripContent> gg_ss;
	private WebSite webSite ;
	private ITripContentService tripContentService;
	private List<News> mjfl;
	private List<News> jczt;
	private List<News> gytt;
	private List<News> hotNews;
	private List<Team>  teamList ;
	private List<News> picHotNews;
	private List<Scenery> topTen;
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
	 * 10条点击量最高景点展示 8条点击量最高新闻展示 2条图片新闻展示<br/>
	 * 固定获取3类图片新闻信息每类4条
	 */
	public void getRightView()
	{
		
		List<SceneryCount> scntList = countSceneryService.getSceneryByMonthHit(4);
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
		
		List<NewsCount> ncntList = newsCountService.getNewsCountByHitForNum(8);
		picHotNews = newsService.getClientPicNews(2);
		if(ncntList!=null)
		{
			this.hotNews = new ArrayList<News>();
			for(int i=0;i<ncntList.size();i++)
			{
				News news = new News();
				news = (News)newsService.get(ncntList.get(i).getNewsId());
				hotNews.add(news);
			}
		}
		this.mjfl = newsService.getNewsByNewsTypeHavePic("402881ff1fe4f8bf011fe515bc0c0011", 4);
		this.jczt = newsService.getNewsByNewsTypeHavePic("f2d041b81fe688c0011fefbc1b1a019c", 4);
		this.gytt = newsService.getNewsByNewsTypeHavePic("f2d041b81fe688c0011fefc18a4601a3", 4);
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
	 * 获取相关类别新闻每页显示48条
	 * 包括网站信息（上部），推荐开班计划8条（下部）
	 * @return
	 */
	public String getNewsListByNewsType()
	{
		getBannaerInfo();
		getTuijianTeam();
		newsType = new NewsType();
		getRightView();
		newsType = (NewsType)newsTypeService.get(typeId);
		if(!newsType.getParent().equals(""))
		{
			newsList = newsService.getNewsByNewsType(typeId);
			if(newsList!=null)
			{
				int totalCount = newsList.size();
				if (totalCount % 48 == 0)
				{
					pageNo =   totalCount / pageSize;
				}
				else
				{
					pageNo =  totalCount / pageSize + 1;
				}
			}
		}
		else
		{
			newsList = newsService.getNewsByNewsTypeParent(typeId);
			if(newsList!=null)
			{
				int totalCount = newsList.size();
				if (totalCount % 48 == 0)
				{
					pageNo =   totalCount / pageSize;
				}
				else
				{
					pageNo =  totalCount / pageSize + 1;
				}
			}
		}
		return SUCCESS;
	}
	/**
	 * 帮助信息显示页面，如果传递新闻类别为父类则取出子类新闻进行补充
	 * 包括3个帮助信息列表（右部），网站信息（上部）
	 * @return
	 */
	public String getHelpInfo()
	{
		getBannaerInfo();
		this.helpL1 = newsService.getNewsByRemark("helpL1");
		this.helpL2 = newsService.getNewsByRemark("helpL2");
		this.helpL3 = newsService.getNewsByRemark("helpL3");
		newsType = (NewsType)newsTypeService.get(typeId);
		if(newsType.getParent().equals(""))
		{
			List<NewsType> nsList = new ArrayList<NewsType>();
			nsList = newsTypeService.getChildren(typeId);
			int size = nsList.size();
			for(int i=0;i<size;i++)
			{
				HelpInfo hi = new HelpInfo();
				List<News> nList = new ArrayList<News>();
				nList = newsService.getNewsByNewsType(nsList.get(i).getTypeId());
				hi.setNews(nList);
				hi.setNt(nsList.get(i));
				this.helpList.add(hi);
			}
		}
		else
		{
			newsList = newsService.getNewsByNewsType(typeId);
		}
		return SUCCESS;
	}
	/**
	 * 帮助信息详细介绍包括3条帮助信息组（右部），网站信息（上部）
	 * @return
	 */
	public String getSingleHelp()
	{
		getBannaerInfo();
		this.helpL1 = newsService.getNewsByRemark("helpL1");
		this.helpL2 = newsService.getNewsByRemark("helpL2");
		this.helpL3 = newsService.getNewsByRemark("helpL3");
		news = newsService.getNews(newsId);
		List<NewsType> nts = new ArrayList<NewsType>(news.getTypes());
	    for(int i=0;i<nts.size();i++)
	    {
	    	if(!nts.get(i).getParent().equals(""))
	    	{
	    		this.newsType = nts.get(i);
	    	}
	    }
		if(news!=null)
		{
			if(news.getContent()!=null)
			{
				page = new ContentPage(news.getContent());
				content = page.getResult(pageId);
			}
			else
			{
				page = null;
				content = "";
			}
		}
		return SUCCESS;
	}
	/**
	 * 普通信息详细新闻，包括8条推荐开班计划（下部），右边新闻显示（右部）{@link #getRightView()}<br/>
	 * 网站信息（上部）并对此新闻进行统计处理
	 * 
	 * @return
	 */
	public String getSingleNews()
	{
		getRightView();
		getTuijianTeam();
		newsType = new NewsType();
		getBannaerInfo();
		
		ncnt = new NewsCount();
		ncnt = newsCountService.getNewsCountByNewsId(newsId);
		if(ncnt==null)
		{
			ncnt = new NewsCount();
			int i=0;
			i++;
			Calendar cal = Calendar.getInstance();
			ncnt.setHit(i);
			ncnt.setMonthHit(i);
			ncnt.setWeekHit(i);
			ncnt.setMonth(cal.get(Calendar.MONTH));
			ncnt.setWeek(cal.get(Calendar.DAY_OF_WEEK));
			ncnt.setNewsId(newsId);
			newsCountService.save(ncnt);
		}
		else
		{
			Calendar cal = Calendar.getInstance();
			int i=ncnt.getHit();
			i++;
			ncnt.setHit(i);
			if(ncnt.getMonth()!=cal.get(Calendar.MONTH))
			{
				int j = 0;
				ncnt.setMonthHit(j);
				ncnt.setMonth(cal.get(Calendar.MONTH));
			}
			else
			{
				int j = ncnt.getMonthHit();
				j++;
				ncnt.setMonthHit(j);
			}
			if(ncnt.getWeek()!=cal.get(Calendar.DAY_OF_WEEK))
			{
				int j = ncnt.getWeekHit();
				j=j+1;
				ncnt.setWeekHit(j);
			}
			else
			{
				int j = 0;
				ncnt.setWeekHit(j);
			}
			newsCountService.merge(ncnt);
		}
		news = newsService.getNews(newsId);
		if(!typeId.equals(""))
		{
			newsType = (NewsType)newsTypeService.get(typeId);
		}
		else
		{
			List<NewsType> ntList = new ArrayList<NewsType>(news.getTypes());
			for(int i=0;i<ntList.size();i++)
			{
				if(!ntList.get(i).getParent().equals(""))
				{
					newsType = ntList.get(i);
					break;
				}
			}
		}
		if(news!=null)
		{
			if(news.getContent()!=null)
			{
				page = new ContentPage(news.getContent());
				content = page.getResult(pageId);
			}
			else
			{
				page = null;
				content = "";
			}
		}
		return SUCCESS;
	}
	public List<News> getNewsList()
	{
		return newsList;
	}
	public void setNewsList(List<News> newsList)
	{
		this.newsList = newsList;
	}
	public INewsService getNewsService()
	{
		return newsService;
	}
	public void setNewsService(INewsService newsService)
	{
		this.newsService = newsService;
	}
	public String getTypeId()
	{
		return typeId;
	}
	public void setTypeId(String typeId)
	{
		this.typeId = typeId;
	}
	public NewsType getNewsType()
	{
		return newsType;
	}
	public void setNewsType(NewsType newsType)
	{
		this.newsType = newsType;
	}
	public INewsTypeService getNewsTypeService()
	{
		return newsTypeService;
	}
	public void setNewsTypeService(INewsTypeService newsTypeService)
	{
		this.newsTypeService = newsTypeService;
	}
	public int getPageNo()
	{
		return pageNo;
	}
	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public News getNews()
	{
		return news;
	}
	public void setNews(News news)
	{
		this.news = news;
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
	public String getNewsId()
	{
		return newsId;
	}
	public void setNewsId(String newsId)
	{
		this.newsId = newsId;
	}
	public INewsCountService getNewsCountService()
	{
		return newsCountService;
	}
	public void setNewsCountService(INewsCountService newsCountService)
	{
		this.newsCountService = newsCountService;
	}
	public NewsCount getNcnt()
	{
		return ncnt;
	}
	public void setNcnt(NewsCount ncnt)
	{
		this.ncnt = ncnt;
	}
	public List<HelpInfo> getHelpList()
	{
		return helpList;
	}
	public void setHelpList(List<HelpInfo> helpList)
	{
		this.helpList = helpList;
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
	public List<News> getHelpL1()
	{
		return helpL1;
	}
	public void setHelpL1(List<News> helpL1)
	{
		this.helpL1 = helpL1;
	}
	public List<News> getHelpL2()
	{
		return helpL2;
	}
	public void setHelpL2(List<News> helpL2)
	{
		this.helpL2 = helpL2;
	}
	public List<News> getHelpL3()
	{
		return helpL3;
	}
	public void setHelpL3(List<News> helpL3)
	{
		this.helpL3 = helpL3;
	}
	public ICountSceneryService getCountSceneryService()
	{
		return countSceneryService;
	}
	public void setCountSceneryService(ICountSceneryService countSceneryService)
	{
		this.countSceneryService = countSceneryService;
	}
	public List<News> getGytt()
	{
		return gytt;
	}
	public void setGytt(List<News> gytt)
	{
		this.gytt = gytt;
	}
	public List<News> getHotNews()
	{
		return hotNews;
	}
	public void setHotNews(List<News> hotNews)
	{
		this.hotNews = hotNews;
	}
	public List<News> getJczt()
	{
		return jczt;
	}
	public void setJczt(List<News> jczt)
	{
		this.jczt = jczt;
	}
	public List<News> getMjfl()
	{
		return mjfl;
	}
	public void setMjfl(List<News> mjfl)
	{
		this.mjfl = mjfl;
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
	public List<Scenery> getTopTen()
	{
		return topTen;
	}
	public void setTopTen(List<Scenery> topTen)
	{
		this.topTen = topTen;
	}
	public List<News> getPicHotNews()
	{
		return picHotNews;
	}
	public void setPicHotNews(List<News> picHotNews)
	{
		this.picHotNews = picHotNews;
	}
	public List<Team> getTeamList() {
		return teamList;
	}
	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}
	public ITeamService getTeamService() {
		return teamService;
	}
	public void setTeamService(ITeamService teamService) {
		this.teamService = teamService;
	}
	public int getSearchType()
	{
		return searchType;
	}
	public void setSearchType(int searchType)
	{
		this.searchType = searchType;
	}
	
}
