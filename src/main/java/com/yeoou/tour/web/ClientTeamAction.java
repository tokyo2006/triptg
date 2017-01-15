package com.yeoou.tour.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import org.apache.struts2.ServletActionContext;
import com.yeoou.common.context.Global;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.CookieUtil;
import com.yeoou.common.utils.DateUtils;
import com.yeoou.common.utils.StringUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.rbac.model.User;
import com.yeoou.rbac.service.IUserService;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.Assemble;
import com.yeoou.tour.model.AssembleContent;
import com.yeoou.tour.model.Content;
import com.yeoou.tour.model.Line;
import com.yeoou.tour.model.Message;
import com.yeoou.tour.model.MyDay;
import com.yeoou.tour.model.MyWeek;
import com.yeoou.tour.model.News;
import com.yeoou.tour.model.PicuterFour;
import com.yeoou.tour.model.Region;
import com.yeoou.tour.model.Team;
import com.yeoou.tour.model.TeamCount;
import com.yeoou.tour.model.TeamSort;
import com.yeoou.tour.model.TripContent;
import com.yeoou.tour.model.WebSite;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.ILineService;
import com.yeoou.tour.service.IMessageService;
import com.yeoou.tour.service.INewsService;
import com.yeoou.tour.service.IRegionService;
import com.yeoou.tour.service.ITeamCountService;
import com.yeoou.tour.service.ITeamService;
import com.yeoou.tour.service.ITripContentService;
import com.yeoou.tour.service.IWebSiteService;
/**
 * <p>
 * Title:前台开班计划显示模块
 * </p>
 * <p>
 * Description: 包括开班计划页面s.shtml，开班计划详细页面teamContent.shtml<br/>
 * 在详细开班计划当中可以对开班计划进行留言
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class ClientTeamAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private ITeamService teamService;
	private IMessageService messageService;
	private IWebSiteService webSiteService;
	private ITeamCountService teamCountService;
	private IUserService userService;
	private IAreaService areaService;
	private ILineService lineService;
	private IRegionService regionService;
	private INewsService newsService;
	private String teamId;
	private Team team;
	private List<Team> teamList = new ArrayList<Team>();
	private List<Team> skimList = new ArrayList<Team>();
	private List<Content> conList = new ArrayList<Content>();
	private List<PicuterFour> pfList = new ArrayList<PicuterFour>();
	private List<News> rightHelps;
	private String errorMsg="";
	private List<AssembleContent> reAssList;
	private List<Content> returnLineContent;
	private Line line;
	private String backDate;
	private String dateType="";
	private String regionId = "";
	private int teamFlag = -1;
	private int day = -1;
	private String keyWord = "";
	private int dayStart = -1;
	private int dayEnd = -1;
	private int priceStart = -1;
	private int priceEnd = -1;
	private String youhui = "";
	private String sort = "";
	private int showType = 0;
	private int tuijian = -1;
	private int tejia = -1;
	private String sceneryId = "";
	private int page = 1;
	private int pageNo = 15;
	private Page publicPage;
	private int searchType = 0;
	private int today = 0;
	private String areaId = "";
	private List<Region> regionList;
	private String currDate = "";
	private int currMonth = 0;
	private int calMonth1 = 0;
	private int calMonth2 = 0;
	private String nextDate = "";
	private List<MyWeek> weekList = new ArrayList<MyWeek>();
	private List<MyWeek> nextWeekList = new ArrayList<MyWeek>();
	private List<Area> begionAreaList = new ArrayList<Area>();
	private List<TeamSort> teamSortList = new ArrayList<TeamSort>();
	private List<TeamSort> youhuiList = new ArrayList<TeamSort>();
	private List<String>  dateList = new ArrayList<String>();
	private List<Region> regions = new ArrayList<Region>();
	private List<Region> bannerRegions = new ArrayList<Region>();
	private List<Team> contactList;
	private Region parent;
	private int dayFlag=0;
	private Region region;
	private String jsonString = "";
	private TeamCount tcnt;
	private int bannerFlag = 0;
	private List<String> searchList = new ArrayList<String>();
	private List<TripContent> gg_ss;
	private WebSite webSite ;
	private String email = "";
	private String groupId = "";
	private String sendContain = "";
	private String teamName = "";
	private String sender = "";
	private List<Message> messageList = new ArrayList<Message>();
	private ITripContentService tripContentService;
	/**
	 * 对开班计划进行留言添加并动态显示其留言<br/>
	 * 仅显示10条留言回复信息
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String addMessage() throws UnsupportedEncodingException
	{
		Message message = new Message();
		message.setEmail(email);
		message.setGroupId(groupId);
		message.setSendContain(sendContain);
		message.setTitle(teamName);
		if(sender.equals(""))
		{
			message.setSender("匿名用户");
		}
		else
		{
			message.setSender(sender);
		}
		Date today = new Date();
		String todayStr = DateUtils.formatDateTime(today);
		today = DateUtils.parseDateTime(todayStr);
		message.setSdatetime(today);
		message.setSdatetimeStr(todayStr);
		User user = new User();
		user = userService.getUserByName(sender);
		if(user!=null)
		{
			message.setSenderId(user.getUserId());
		}
		messageService.save(message);
		messageList = new ArrayList<Message>();
		Page page = messageService.getMessagesByTeam(groupId, 1, 10, "sdatetime", "DESC");
		if(page.getPageSize()!=0)
		{
			messageList = (List<Message>)page.getResult();
			int size = 0;
			size = messageList.size();
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<size;i++)
			{
				messageList.get(i).setSendContain(StringUtils.HtmlEncode(messageList.get(i).getSendContain()));
				messageList.get(i).setSender(StringUtils.HtmlEncode(messageList.get(i).getSender()));
				messageList.get(i).setReciverContain(StringUtils.HtmlEncode(messageList.get(i).getReciverContain()));
				sb.append("<div class='small_class_list_t'>");
				sb.append("&nbsp;"+messageList.get(i).getSender()+"&nbsp;");
				sb.append("&nbsp;("+messageList.get(i).getSdatetimeStr()+")&nbsp;问&nbsp;：</div>");
				sb.append("<div style='padding: 5px 10px;'>");
				sb.append(messageList.get(i).getSendContain()+"</div>");
				if (messageList.get(i).getReciverContain()!=null)
				{
					sb.append("<div style='padding-left: 10px;' class='cgrey'>");
					sb.append("兔游客服："+messageList.get(i).getReciverContain()+"</div>");
				}
			}
			this.jsonString=sb.toString();
		}
		else
		{
			messageList = null;
			jsonString ="";
		}
		
		return SUCCESS;
	}
//	@SuppressWarnings("unchecked")
//	public String createRandCnt()
//	{
//		List<TeamCount> tcList = new ArrayList<TeamCount>();
//		tcList = (List<TeamCount>)teamCountService.getAll();
//		int size = tcList.size();
//		Random rd = new Random();
//		for(int i=0;i<size;i++)
//		{
//			int hit = rd.nextInt(25);
//			hit = hit + tcList.get(i).getHit();
//			tcList.get(i).setHit(hit);
//			teamCountService.merge(tcList.get(i));
//		}
//		return SUCCESS;
//	}
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
	 * 显示帮助信息
	 *
	 */
	public void getClientRightNews()
	{
		this.rightHelps = newsService.getNewsByRemark("helpL1");
	}
	/**
	 * 清除浏览过的开班计划信息
	 * @return
	 */
	public String clearCookie()
	{

		this.skimList = null;
		
		 Cookie cook = CookieUtil.getCookie(ServletActionContext.getRequest(), "yeoou");
		 if(cook!=null)
		 {
			 CookieUtil.deleteCookie(ServletActionContext.getRequest(), ServletActionContext.getResponse(), cook);
		 }
		 this.skimList = null;
		return SUCCESS;
	}
	/**
	 * 打印开班计划
	 * @return
	 */
	public String getPrintTeam()
	{
		if(teamId==null)
		{
			errorMsg="您无权查看该线路信息，请关闭创口";
			return ERROR;
		}
		this.getBannaerInfo();
		team = (Team)teamService.get(teamId);
		List<String> tempList = new ArrayList<String>();
		tempList=DateUtils.getDateStringByWeeks(team.getStartDateStr(), team.getEndDateStr(), team.getDateType());
		int size = tempList.size();
		dateList = new ArrayList<String>();
		for(int i=0;i<size;i++)
		{
			String dateStr = tempList.get(i)+"["+DateUtils.getWeekByDate(tempList.get(i), Global.otherDayOfWeeks)+"]";
			dateList.add(dateStr);
		}
		line = team.getLine();
		if(line.getFeature()==null)
		{
			line.setFeature("");
		}
		if(line.getFeeClude()==null)
		{
			line.setFeeClude("");
		}
		if(line.getFeeUnclude()==null)
		{
			line.setFeeUnclude("");
		}
		if(line.getRemark()==null)
		{
			line.setRemark("");
		}
		if(line.getPurchase()==null)
		{
			line.setPurchase("");
		}
		if(line.getSelfBuy()==null)
		{
			line.setSelfBuy("");
		}
		if(line.getSafe()==null)
		{
			line.setSafe("");
		}
		line.setFeature(StringUtils.HtmlEncode(line.getFeature()));
		line.setFeeClude(StringUtils.HtmlEncode(line.getFeeClude()));
		line.setFeeUnclude(StringUtils.HtmlEncode(line.getFeeUnclude()));
		line.setRemark(StringUtils.HtmlEncode(line.getRemark()));
		line.setPurchase(StringUtils.HtmlEncode(line.getPurchase()));
		line.setSelfBuy(StringUtils.HtmlEncode(line.getSelfBuy()));
		line.setSafe(StringUtils.HtmlEncode(line.getSafe()));
		returnLineContent=line.getContentPackeage(StringUtils.HtmlEncode(line.getContent()));
		return SUCCESS;
	}
	/**
	 * 开班计划旅游导航类别（上部）
	 * @return
	 */
	public String getAllRegion()
	{
		getBannaerInfo();
		getClientRightNews();
		String parentAreaId = "";
		if(areaId.equals(""))
		{
			parentAreaId = "402880e51a512576011a512b60fb0004";
		}
		else
		{
			Area area = new Area();
			area = (Area)areaService.get(areaId);
			if(area.getName().equals("北京")||area.getName().equals("重庆")||area.getName().equals("天津")||area.getName().equals("上海")||area.getName().equals("香港")||area.getName().equals("澳门"))
			{
				parentAreaId = area.getAreaId();
			}
			else
			{
				parentAreaId = area.getParent();
			}
			parentAreaId = areaId;
		}
		Region region = regionService.getRegionByAreaId(parentAreaId,1);
		regions = regionService.getRegionsByParent(region.getRegionId(),"flag",true);
		int size = regions.size();
//		long time1 = System.currentTimeMillis();
		for(int i=0;i<size;i++)
		{
			regions.get(i).setChildrenList(regionService.getChildren(regions.get(i).getRegionId()));
			int length = regions.get(i).getChildrenList().size();
			for(int j=0;j<length;j++)
			{
				List<Region> cList = new ArrayList<Region>();
				cList = regionService.getChildren(regions.get(i).getChildrenList().get(j).getRegionId());
				int t = cList.size();
				for(int k=0;k<t;k++)
				{
					cList.get(k).setLineCnt(teamService.getCountByRegionId(cList.get(k).getRegionId()));
				}
				regions.get(i).getChildrenList().get(j).setChildrenList(cList);
			}
		}
//		long time2 = System.currentTimeMillis();
//		long milltime = time2-time1;
//		System.out.println("##################\n");
//		System.out.println(milltime);
//		System.out.println("##################\n");
		return SUCCESS;
	}
	/**
	 * 显示所有开班计划列表（包括搜索结果）<br/>
	 * 网站信息，导航类别（上部），浏览过的开班计划列表（右部）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllTeam()
	{
		try
		{
			getBannaerInfo();
			this.bannerFlag = 9;
			String strTeam = "";
			List<String> temp1 = new ArrayList<String>();
			String parentAreaId = "";
			if(areaId.equals(""))
			{
				parentAreaId = "402880e51a512576011a512b60fb0004";
				areaId = this.getIPAddress();
			}
			else
			{
				Area area = new Area();
				area = (Area)areaService.get(areaId);
				if(area.getName().equals("北京")||area.getName().equals("重庆")||area.getName().equals("天津")||area.getName().equals("上海")||area.getName().equals("香港")||area.getName().equals("澳门"))
				{
					parentAreaId = area.getAreaId();
				}
				else
				{
					parentAreaId = area.getParent();
				}
				parentAreaId = areaId;
			}
			Area area = new Area();
			Map m = new HashMap();
			m.put(1, showType);
			m.put(2, teamFlag);
			m.put(3, this.day);
			m.put(4, this.dayEnd);
			m.put(5, this.dayStart);
			m.put(6, this.priceStart);
			m.put(7, this.priceEnd);
			m.put(8, tuijian);
			m.put(9, tejia);
			
			Class type = m.get(1).getClass();
			if(!type.getSimpleName().equals("Integer"))
			{
				showType = 0;
			}
			type = m.get(3).getClass();
			if(!type.getSimpleName().equals("Integer"))
			{
				day = -1;
			}
			type = m.get(4).getClass();
			if(!type.getSimpleName().equals("Integer"))
			{
				dayEnd = -1;
			}
			type = m.get(5).getClass();
			if(!type.getSimpleName().equals("Integer"))
			{
				dayStart = -1;
			}
			type = m.get(6).getClass();
			if(!type.getSimpleName().equals("Integer"))
			{
				priceStart = -1;
			}
			type = m.get(7).getClass();
			if(!type.getSimpleName().equals("Integer"))
			{
				priceEnd = -1;
			}
			type = m.get(8).getClass();
			if(!type.getSimpleName().equals("Integer"))
			{
				tuijian = -1;
			}
			type = m.get(9).getClass();
			if(!type.getSimpleName().equals("Integer"))
			{
				tejia = -1;
			}
			Class type2 = m.get(2).getClass();
			if(!type2.getSimpleName().equals("Integer")||teamFlag>5)
			{
				teamFlag = 1;
			}
			area = (Area)areaService.get(parentAreaId);
			this.begionAreaList.add(area);
			if(regionId.equals("") && teamFlag == -1 && day == -1 && keyWord.equals("") && dayStart == -1 && dayEnd == -1 && priceStart == -1 && priceEnd == -1 && youhui.equals("") && sort.equals("") && tuijian == -1 && tejia == -1 && sceneryId.equals(""))
			{
				return "all";
			}
				if(regionId.equals(""))
				{
					if(teamFlag!=-1)
					{
						Region region = regionService.getRegionByAreaId(parentAreaId,1);
						regions = regionService.getRegionsByParent(region.getRegionId(),"flag",true);
						this.bannerRegions = null;
						for(int i=0;i<regions.size();i++)
						{
							if(regions.get(i).getFlag()==teamFlag)
							{
								parent = regions.get(i);
							}
						}
						String parentId = parent.getRegionId();
						regionList = new ArrayList<Region>();
						regionList = regionService.getChildren(parentId);
						int size = regionList.size();
						
						for(int i=0;i<size;i++)
						{
							regionList.get(i).setLineCnt(teamService.getCountByRegionId(regionList.get(i).getRegionId()));
							List<Region> clist = new ArrayList<Region>();
							clist = regionService.getChildren(regionList.get(i).getRegionId());
							int t = clist.size();
							for(int j=0;j<t;j++)
							{
								clist.get(j).setLineCnt(teamService.getCountByRegionId(clist.get(j).getRegionId()));
							}
							regionList.get(i).setChildrenList(clist);
						}
					}

				}
				else
				{
					region = new Region();
					region = (Region)regionService.get(regionId);
					this.parent = (Region)regionService.get(region.getParent());
					bannerRegions = regionService.getRegionList(regionId, 3);
					teamFlag = region.getFlag();
					Region region = regionService.getRegionByAreaId(parentAreaId,1);
					regions = regionService.getRegionsByParent(region.getRegionId(),"flag",true);
					for(int i=0;i<regions.size();i++)
					{
						if(regions.get(i).getFlag()==teamFlag)
						{
							parent = regions.get(i);
						}
					}
					String parentId = parent.getRegionId();
					regionList = new ArrayList<Region>();
					regionList = regionService.getChildren(parentId);
					int size = regionList.size();
					for(int i=0;i<size;i++)
					{
						List<Region> clist = new ArrayList<Region>();
						clist = regionService.getChildren(regionList.get(i).getRegionId());
						int t = clist.size();
						for(int j=0;j<t;j++)
						{
							clist.get(j).setLineCnt(teamService.getCountByRegionId(clist.get(j).getRegionId()));
						}
						regionList.get(i).setChildrenList(clist);
					}					
				}
				
			String[] tempSort = {"",""};
////			TeamSort ts = new TeamSort();
////			ts.setName("按价格  从高到低");
////			ts.setSort("doorDisPrice@@@ASC");
////			teamSortList.add(ts);
////			ts = new TeamSort();
////			ts.setName("按价格  从低到高");
////			ts.setSort("doorDisPrice@@@DESC");
////			teamSortList.add(ts);
////			ts = new TeamSort();
////			ts.setName("按天数  从多到少");
////			ts.setSort("teamday@@@DESC");
////			teamSortList.add(ts);
////			ts = new TeamSort();
////			ts.setName("按天数  从少到多");
////			ts.setSort("teamday@@@ASC");
////			teamSortList.add(ts);
			if(sort.equals(""))
			{
				if(day!=-1)
				{
					tempSort[0]="teamday";
					tempSort[1]="ASC";
				}
				if(priceStart!=-1)
				{
					tempSort[0]="doorDisPrice";
					tempSort[1]="ASC";
				}
			}
			else
			{
				tempSort = sort.split("@@@");
			}
		    teamList = teamService.getAllClientTeam(day,dayFlag,areaId, tempSort[0], tempSort[1], regionId, teamFlag, keyWord, dayStart, dayEnd, priceStart, priceEnd, youhui, tuijian, tejia, sceneryId);
		    int size = teamList.size();
		    if(size == 0) 
		    {
		    	teamList=null;
		    }
		    else
		    {
			    for(int i=0;i<size;i++)
			    {
			    	teamList.get(i).setDateArea(DateUtils.formatDateTime("MM/dd", teamList.get(i).getRealDate())+" "+DateUtils.getWeekByDate(teamList.get(i).getRealDateStr(), Global.shortWeeks));
			    	teamList.get(i).setPushDown(DateUtils.isPushDown(teamList.get(i).getRealDate(), teamList.get(i).getPushDay()));
			    	pfList = PicuterFour.unpackagePicFour(teamList.get(i).getLine().getPicArea());
			    	if(pfList!=null)
			    	{
			    		teamList.get(i).setOrgUrl(pfList.get(0).getBreUrl());
			    		teamList.get(i).setBreUrl(pfList.get(0).getUrl());
			    	}
			    	if(!keyWord.equals(""))
			    	{
			    		String temp = teamList.get(i).getTeamName().replace(keyWord, "<font color=red>"+keyWord+"</font>");
			    		teamList.get(i).setTeamName(temp);
			    	}
			    }
		    }
		    Cookie cook = CookieUtil.getCookie(ServletActionContext.getRequest(), "yeoou");
		    if(cook==null)
		    {
		    	this.skimList=null;
		    }
		    else
		    {
		    	strTeam = cook.getValue();
				String[] tl = strTeam.split("###");
				for(int i=0;i<tl.length;i++)
				{
					//判断当中是否有teamId
					
						temp1.add(tl[i]);
						Team team = new Team();
						team = (Team)teamService.getTeam(tl[i].trim());
						if(team!=null)
						{
							List<PicuterFour> picList = new ArrayList<PicuterFour>();
							picList = PicuterFour.unpackagePicFour(team.getLine().getPicArea());
							if(picList!=null)
							{
								team.setOrgUrl(picList.get(0).getBreUrl());
								team.setBreUrl(picList.get(0).getUrl());
							}
							else
							{
								team.setOrgUrl("public/20081205/image/scenerypic.gif");
								team.setBreUrl("public/20081205/image/scenerypic.gif");
							}
							skimList.add(team);
						}
				}
				int lh = skimList.size();
				if(lh!=0)
				{
					for(int i=0;i<lh;i++)
					{
						for(int j=i+1;j<lh;j++)
						{
							if(skimList.get(i).getGroupId().equals(skimList.get(j).getGroupId()))
							{
								skimList.remove(i);
								lh = lh - 1;
								i = 0;
							}
						}
					}
				}
				else
				{
					skimList = null;
				}
		    }
			return SUCCESS;
		}
		catch(Exception e)
		{
			return ERROR;
		}
	}
	/**
	 * 构件开班计划的开班日历，如果是出境以截止日期和截止日期<br/>
	 * 的前一个月来构件日历
	 * @return
	 */
	public String getCanderTeam()
	{
		team = (Team)teamService.get(teamId);
		int cnt =0;
		teamList = teamService.getTeamByGroupId(team.getGroupId());
		//制作日历所需参数
		Calendar cal = Calendar.getInstance();
		currMonth = cal.get(Calendar.MONTH)+1;
		Calendar currCal = Calendar.getInstance();
		today = currCal.get(Calendar.DAY_OF_MONTH);
		if(team.getFlag()==3 || team.getFlag()==4)
		{
			Calendar tempCal = Calendar.getInstance();
			tempCal.setTime(team.getEndDate());
			if(currCal.get(Calendar.YEAR)==tempCal.get(Calendar.YEAR))
			{
				if(currCal.get(Calendar.MONTH)<tempCal.get(Calendar.MONTH))
				{
					int year = tempCal.get(Calendar.YEAR);
					int month = tempCal.get(Calendar.MONTH);
					if(month==0)
					{
						month = 12;
						year = year-1;
					}
					String preDate = year+Global.firstDayOfMonth[month-1];
					cal.setTime(DateUtils.parseDate(preDate));
				}
				else if(currCal.get(Calendar.MONTH)==tempCal.get(Calendar.MONTH))
				{
					cal.setTime(team.getEndDate());
				}
			}
			else
			{
				if(currCal.get(Calendar.MONTH)<tempCal.get(Calendar.MONTH)+12)
				{
					int year = tempCal.get(Calendar.YEAR);
					int month = tempCal.get(Calendar.MONTH);
					if(month==0)
					{
						month = 12;
						year = year-1;
					}
					String preDate = year+Global.firstDayOfMonth[month-1];
					cal.setTime(DateUtils.parseDate(preDate));
				}
				else if(currCal.get(Calendar.MONTH)==tempCal.get(Calendar.MONTH))
				{
					cal.setTime(team.getEndDate());
				}
			}
		}
		Calendar teamCal = Calendar.getInstance();
		//获取当前月份天数
		int days = Global.monthDays[cal.get(Calendar.MONTH)];
		//判断当前年份是否为闰年
		int year = cal.get(Calendar.YEAR);
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
		{
			if(cal.get(Calendar.MONTH)==1) days = 29;
		}
		this.calMonth1 = cal.get(Calendar.MONTH)+1;
		this.currDate = String.valueOf(cal.get(Calendar.YEAR))+"年"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月";
		//获取当月1号是星期几
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int weekOfFirstDay = cal.get(Calendar.DAY_OF_WEEK);
		List<MyDay> myDays = new ArrayList<MyDay>();
		for(int i=0;i<teamList.size();i++)
		{
			teamList.get(i).setPushDown(DateUtils.isPushDown(teamList.get(i).getRealDate(), teamList.get(i).getPushDay()));
		}
		//设置当前日期中有团队的团队信息
		for(int i=0;i<days;i++)
		{
			MyDay myDay = new MyDay();
			myDay.setDay(i+1);
			for(int j=0;j<teamList.size();j++)
			{
				teamCal.setTime(teamList.get(j).getRealDate());
				if(teamCal.get(Calendar.MONTH)==cal.get(Calendar.MONTH))
				{	
					int num= teamCal.get(Calendar.DAY_OF_MONTH)-1;
					if(i==num)
					{
						myDay.setTeam(teamList.get(j));
					}
				}
			}
			myDays.add(myDay);
		}
		//构件日历
		List<MyDay> tempDays = new ArrayList<MyDay>();
		for(int i=1;i<days+1;i++)
		{
			if(i<weekOfFirstDay)
			{
				MyDay nDay = null;
				tempDays.add(nDay);
				cnt++;
			}
			else
			{
				tempDays.add(myDays.get(i-cnt-1));
				if(i-cnt-1==0)
				{days=cnt+days;}
			}
			if(i%7==0)
			{
				MyWeek myWeek = new MyWeek();
				myWeek.setDays(tempDays);
				tempDays = new ArrayList<MyDay>();
				weekList.add(myWeek);
			}
			if(i==days&&days>28)
			{
				MyWeek myWeek = new MyWeek();
				myWeek.setDays(tempDays);
				tempDays = new ArrayList<MyDay>();
				weekList.add(myWeek);
			}
				
		}
		//构件下月日历
		int month = cal.get(Calendar.MONTH);
		if(month==11)
		{
			month = -1;
			year = year+1;
		}
		days = Global.monthDays[month+1];
	
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
		{
			if(cal.get(Calendar.MONTH)+1==1) days = 29;
		}
		this.calMonth2 =month+2;
		this.nextDate = String.valueOf(year)+"年"+String.valueOf(month+2)+"月";
		String nextDate = year+Global.firstDayOfMonth[month+1];
		cal.setTime(DateUtils.parseDate(nextDate));
		weekOfFirstDay =  cal.get(Calendar.DAY_OF_WEEK);
		myDays = new ArrayList<MyDay>();
		for(int i=0;i<days;i++)
		{
			MyDay myDay = new MyDay();
			myDay.setDay(i+1);
			
			for(int j=0;j<teamList.size();j++)
			{
				teamCal.setTime(teamList.get(j).getRealDate());
				if(teamCal.get(Calendar.MONTH)==cal.get(Calendar.MONTH))
				{	
					int num= teamCal.get(Calendar.DAY_OF_MONTH)-1;
					if(i==num)
					{
						myDay.setTeam(teamList.get(j));
					}
				}
			}
			myDays.add(myDay);
		}
		
		tempDays = new ArrayList<MyDay>();
		cnt =0;
		for(int i=1;i<days+1;i++)
		{
			if(i<weekOfFirstDay)
			{
				MyDay nDay = null;
				tempDays.add(nDay);
				cnt++;
			}
			else
			{
				tempDays.add(myDays.get(i-cnt-1));
				if(i-cnt-1==0)
				{days=cnt+days;}
			}
			if(i%7==0)
			{
				MyWeek myWeek = new MyWeek();
				myWeek.setDays(tempDays);
				tempDays = new ArrayList<MyDay>();
				nextWeekList.add(myWeek);
			}
			if(i==days&&days>28)
			{
				MyWeek myWeek = new MyWeek();
				myWeek.setDays(tempDays);
				tempDays = new ArrayList<MyDay>();
				nextWeekList.add(myWeek);
			}
				
		}
		
		//======================================================
		//      构件你的日历HTML语句 
		//      params: weekList       当前月  
		//              nextWeekList   下个月
		//      return: jsonString
		//======================================================
		StringBuilder sb = new StringBuilder();
		sb.append("<table id=left_");
		sb.append(teamId);
		sb.append(" class=\"tCal\" border=\"1\" cellpadding=\"2\" cellspacing=\"0\">");
		sb.append("<thead>");
		sb.append("<tr><td colspan=\"7\"><div style=\"position: relative; width: 100%;\"><span class=\"tCalMonth\">");
		sb.append(this.currDate);
		sb.append("</span></div></td></tr>");
		sb.append("<tr><th><font color=red>日</font></th><th>一</th><th>二</th><th>三</th><th>四</th><th>五</th><th>六</th></tr>");
		sb.append("</thead>");
		sb.append("<tbody>");
		for (int i=0;i<weekList.size();i++)
		{
			sb.append("<tr>");
			for(int j=0; j<weekList.get(i).getDays().size();j++)
			{
				if(weekList.get(i).getDays().get(j)!=null)
				{
					if(weekList.get(i).getDays().get(j).getTeam()!=null)
					{
						if((weekList.get(i).getDays().get(j).getDay()<today)&&(calMonth1==currMonth))
						{
							sb.append("<td class=\"tCalPassed tCalDay\"><span class=\"date_span\">");
							sb.append(weekList.get(i).getDays().get(j).getDay());
							sb.append("</span><br/><span class=\"price_span\">");
							sb.append(weekList.get(i).getDays().get(j).getTeam().getDoorDisPrice());
							sb.append("<span class=\"tCalYuan\">元</span></span></td>");
							
						}
						else if((weekList.get(i).getDays().get(j).getDay()==today)&&(calMonth1==currMonth))
						{
							sb.append("<td class=\"tCalToday tCalDay\"><span class=\"date_span\">");
							sb.append(weekList.get(i).getDays().get(j).getDay());
							sb.append("</span><br>今天</td>");
						}
						else
						{
							sb.append("<td class=\"tCalDay tCalPrice\">");
							sb.append("<span class=\"date_span\">");
							sb.append(weekList.get(i).getDays().get(j).getDay());
							sb.append("</span>");
							sb.append("<span class=\"site_span cyellow\"> </span><br>");
							sb.append("<span class=\"price_span\">");
							sb.append(weekList.get(i).getDays().get(j).getTeam().getDoorDisPrice());
							sb.append("<span class=\"tCalYuan\">元</span></span></a></td>");
							
						}
					}
					else
					{
						if((weekList.get(i).getDays().get(j).getDay()==today)&&(calMonth1==currMonth))
						{
							sb.append("<td class=\"tCalToday tCalDay\"><span class=\"date_span\">");
							sb.append(weekList.get(i).getDays().get(j).getDay());
							sb.append("</span><br>今天</td>");
						}
						else
						{
							sb.append("<td class=\"tCalPassed tCalDay\"><span class=\"date_span\">");
							sb.append(weekList.get(i).getDays().get(j).getDay());
							sb.append("</span><br>&nbsp;</td>");
						}
					}
				}
				else
				{
					sb.append("<td class=\"tCalPassed tCalDay\"><span class=\"date_span\">");
					sb.append("</span><br>&nbsp;</td>");
				}
			}
			sb.append("</tr>");
		}
		sb.append("</tbody>");
		sb.append("</table>");
		
		sb.append("<br/>");
		sb.append("<table id=right_");
		sb.append(teamId);
		sb.append(" class=\"tCal\" border=\"1\" cellpadding=\"2\" cellspacing=\"0\">");
		sb.append("<thead>");
		sb.append("<tr><td colspan=\"7\"><div style=\"position: relative; width: 100%;\"><span class=\"tCalMonth\">");
		sb.append(this.nextDate);
		sb.append("</span></div></td></tr>");
		sb.append("<tr><th><font color=red>日</font></th><th>一</th><th>二</th><th>三</th><th>四</th><th>五</th><th>六</th></tr>");
		sb.append("</thead>");
		sb.append("<tbody>");
		for (int i=0;i<nextWeekList.size();i++)
		{
			sb.append("<tr>");
			for(int j=0; j<nextWeekList.get(i).getDays().size();j++)
			{
				if(nextWeekList.get(i).getDays().get(j)!=null)
				{
					if(nextWeekList.get(i).getDays().get(j).getTeam()!=null)
					{
						if((nextWeekList.get(i).getDays().get(j).getDay()<today)&&(calMonth2==currMonth))
						{
							sb.append("<td class=\"tCalPassed tCalDay\"><span class=\"date_span\">");
							sb.append(nextWeekList.get(i).getDays().get(j).getDay());
							sb.append("</span><br/><span class=\"price_span\">");
							sb.append(nextWeekList.get(i).getDays().get(j).getTeam().getDoorDisPrice());
							sb.append("<span class=\"tCalYuan\">元</span></span></td>");
							
						}
						else if((nextWeekList.get(i).getDays().get(j).getDay()==today)&&(calMonth2==currMonth))
						{
							sb.append("<td class=\"tCalToday tCalDay\"><span class=\"date_span\">");
							sb.append(nextWeekList.get(i).getDays().get(j).getDay());
							sb.append("</span><br>今天</td>");
						}
						else
						{
							sb.append("<td class=\"tCalDay tCalPrice\">");
							sb.append("<span class=\"date_span\">");
							sb.append(nextWeekList.get(i).getDays().get(j).getDay());
							sb.append("</span>");
							sb.append("<span class=\"site_span cyellow\"> </span><br>");
							sb.append("<span class=\"price_span\">");
							sb.append(nextWeekList.get(i).getDays().get(j).getTeam().getDoorDisPrice());
							sb.append("<span class=\"tCalYuan\">元</span></span></a></td>");
							
						}
					}
					else
					{
						if((nextWeekList.get(i).getDays().get(j).getDay()==today)&&(calMonth2==currMonth))
						{
							sb.append("<td class=\"tCalToday tCalDay\"><span class=\"date_span\">");
							sb.append(nextWeekList.get(i).getDays().get(j).getDay());
							sb.append("</span><br>今天</td>");
						}
						else
						{
							sb.append("<td class=\"tCalPassed tCalDay\"><span class=\"date_span\">");
							sb.append(nextWeekList.get(i).getDays().get(j).getDay());
							sb.append("</span><br>&nbsp;</td>");
						}
					}
				}
				else
				{
					sb.append("<td class=\"tCalPassed tCalDay\"><span class=\"date_span\">");
					sb.append("</span><br>&nbsp;</td>");
				}
			}
			sb.append("</tr>");
		}
		sb.append("</tbody>");
		sb.append("</table>");
		jsonString=sb.toString();
		return SUCCESS;
	}
	/**
	 * 开班计划详细显示页面<br/>
	 * 包括网站信息（上部）留言列表（下部），对当前开班计划进行统计<br/>
	 * 显示地域相关的8条开班计划，录入此相关开班计划，构件开班日期日历
	 * @return
	 */
	public String getSingleTeam()
	{
		getBannaerInfo();
		
		List<String> temp = new ArrayList<String>();
		if(teamId==null)
		{
			errorMsg="您无权查看该线路信息，请关闭创口";
			return ERROR;
		}
		team = teamService.getTeam(teamId);
		messageList = new ArrayList<Message>();
		Page page = messageService.getMessagesByTeam(team.getGroupId(), 1, 10, "sdatetime", "DESC");
		if(page.getPageSize()!=0)
		{
			messageList = (List<Message>)page.getResult();
			int size = 0;
			size = messageList.size();
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<size;i++)
			{
				messageList.get(i).setSendContain(StringUtils.HtmlEncode(messageList.get(i).getSendContain()));
				messageList.get(i).setSender(StringUtils.HtmlEncode(messageList.get(i).getSender()));
				messageList.get(i).setReciverContain(StringUtils.HtmlEncode(messageList.get(i).getReciverContain()));
				sb.append("<div class='small_class_list_t'>");
				sb.append("&nbsp;"+messageList.get(i).getSender()+"&nbsp;");
				sb.append("&nbsp;("+messageList.get(i).getSdatetimeStr()+")&nbsp;问&nbsp;：</div>");
				sb.append("<div style='padding: 5px 10px;'>");
				sb.append(messageList.get(i).getSendContain()+"</div>");
				if (messageList.get(i).getReciverContain()!=null)
				{
					sb.append("<div style='padding-left: 10px;' class='cgrey'>");
					sb.append("兔游客服："+messageList.get(i).getReciverContain()+"</div>");
				}
			}
			this.jsonString=sb.toString();
		}
		else
		{
			messageList = null;
			jsonString ="";
		}
		tcnt = new TeamCount();
		tcnt = teamCountService.getTeamCountByGroupId(team.getGroupId());
		if(tcnt==null)
		{
			tcnt = new TeamCount();
			int i=0;
			i++;
			Calendar cal = Calendar.getInstance();
			tcnt.setHit(i);
			tcnt.setMonthHit(i);
			tcnt.setWeekHit(i);
			tcnt.setMonth(cal.get(Calendar.MONTH));
			tcnt.setWeek(cal.get(Calendar.DAY_OF_WEEK));
			tcnt.setGroupId(team.getGroupId());
			teamCountService.save(tcnt);
		}
		else
		{
			Calendar cal = Calendar.getInstance();
			int i=tcnt.getHit();
			i++;
			tcnt.setHit(i);
			if(tcnt.getMonth()!=cal.get(Calendar.MONTH))
			{
				int j = 0;
				tcnt.setMonthHit(j);
				tcnt.setMonth(cal.get(Calendar.MONTH));
			}
			else
			{
				int j = tcnt.getMonthHit();
				j++;
				tcnt.setMonthHit(j);
			}
			if(tcnt.getWeek()!=cal.get(Calendar.DAY_OF_WEEK))
			{
				int j = tcnt.getWeekHit();
				j=j+1;
				tcnt.setWeekHit(j);
			}
			else
			{
				int j = 0;
				tcnt.setWeekHit(j);
			}
			teamCountService.merge(tcnt);
		}
		List<Area> areaList = new ArrayList<Area>(team.getAreas());
		int sz = areaList.size();
		for(int i=0;i<sz;i++)
		{
			if(areaList.get(i).getDepth()==3)
			{
				areaList.remove(i);
				i=0;
				sz--;
			}
		}
		this.contactList = teamService.getContactList(areaList.get(0).getAreaId(),8);
		int l = contactList.size();
		for(int i=0;i<l;i++)
		{
			if(contactList.get(i).getTeamId().equals(teamId))
			{
				contactList.remove(i);
				l--;
				i=0;
			}
		}
		l=contactList.size();
		for(int i=0;i<l;i++)
		{
			pfList = PicuterFour.unpackagePicFour(contactList.get(i).getLine().getPicArea());
	    	if(pfList!=null)
	    	{
	    		contactList.get(i).setOrgUrl(pfList.get(0).getBreUrl());
	    		contactList.get(i).setBreUrl(pfList.get(0).getUrl());
	    	}
		}
		if(team==null)
		{
			errorMsg="您无权查看该线路信息，请关闭创口";
			return ERROR;
		}
		String parentAreaId = "";
		if(team.getArea().getName().equals("北京")||team.getArea().getName().equals("重庆")||team.getArea().getName().equals("天津")||team.getArea().getName().equals("上海")||team.getArea().getName().equals("香港")||team.getArea().getName().equals("澳门"))
		{
			parentAreaId = team.getArea().getAreaId();
		}
		else
		{
			parentAreaId = team.getArea().getParent();
		}
		this.webSite = webSiteService.getWebSiteByArea(team.getArea().getAreaId());
		if(webSite!=null)
		{
			if(!webSite.getAddress().equals(""))
			{
				webSite.setAddList(WebSite.unpackagePhoneInfo(webSite.getAddress()));
			}
			if(!webSite.getZbphone().equals(""))
			{
				webSite.setZbList(WebSite.unpackagePhoneInfo(webSite.getZbphone()));
			}
			if(!webSite.getGnphone().equals(""))
			{
				webSite.setGnList(WebSite.unpackagePhoneInfo(webSite.getGnphone()));
			}
			if(!webSite.getCjphone().equals(""))
			{
				webSite.setCjList(WebSite.unpackagePhoneInfo(webSite.getCjphone()));
			}
		}
		Region region = regionService.getRegionByAreaId(parentAreaId,1);
		regions = regionService.getRegionsByParent(region.getRegionId(),"flag",true);
		String strTeam = "";
		teamFlag = team.getFlag();
		Region tempRegion = new Region();
		if(teamFlag==-1)
		{
			tempRegion = (Region)regionService.get(regionId);
			for(int i=0;i<regions.size();i++)
			{
				if(regions.get(i).getFlag()==tempRegion.getFlag())
				{
					parent = regions.get(i);
				}
			}
			teamFlag = tempRegion.getFlag();
		}
		else
		{
			for(int i=0;i<regions.size();i++)
			{
				if(regions.get(i).getFlag()==teamFlag)
				{
					parent = regions.get(i);
				}
			}
			regionId = parent.getRegionId();
		}
		this.bannerRegions= regionService.getRegionsByParent(parent.getRegionId(),"headName",true);
		int length = bannerRegions.size();
		for(int i=0;i<length;i++)
		{
			bannerRegions.get(i).setChildrenList(regionService.getRegionsByParent(bannerRegions.get(i).getRegionId(),"headName",true));
		}
		Cookie cook = CookieUtil.getCookie(ServletActionContext.getRequest(), "yeoou");
		if(cook==null)
		{
			CookieUtil.setCookie(ServletActionContext.getRequest(), ServletActionContext.getResponse(), "yeoou", teamId+"###",31536000);
		}
		else
		{
			//取出list
			strTeam = cook.getValue();
			String[] tl = strTeam.split("###");
			for(int i=0;i<tl.length;i++)
			{
				//判断当中是否有teamId
				if(!tl[i].equals(teamId))
				{
					temp.add(tl[i]);
				}
			}
			//重新组合cookie参数
			String strTemp = "";
			for(int i=0;i<temp.size();i++)
			{
				strTemp = temp.get(i)+strTemp+"###";
			}
			strTeam = teamId+strTemp+"###";
			CookieUtil.setCookie(ServletActionContext.getRequest(), ServletActionContext.getResponse(), "yeoou", strTeam,31536000);
		}
		String contentHtml = StringUtils.SimpleHtmlEncode(team.getLine().getContentHtml());
		conList = Content.unpackageContent(contentHtml);
		List<Content>tempList = new ArrayList<Content>();
		tempList = Content.unpackageContentUnit(team.getLine().getPicUrl());
		pfList = PicuterFour.unpackagePicFour(team.getLine().getPicArea());
		if(tempList!=null)
		{	
			for(int i=0;i<tempList.size();i++)
			{
				if(tempList.get(i).getPuList()!=null)
				{
					conList.get(i).setPuList(tempList.get(i).getPuList());
				}
				
			}
		}
		teamList = teamService.getTeamByGroupId(team.getGroupId());
		
		reAssList = Assemble.getContentPackeage(team.getAssemble());
		if(team.getOrderConfirm()==null)
		{
			team.setOrderConfirm("");
		}
		team.setOrderConfirm(StringUtils.HtmlEncode(team.getOrderConfirm()));
		line = new Line();
		line = lineService.getLine(team.getLine().getLineId());
		if(line.getFeature()==null)
		{
			line.setFeature("");
		}
		if(line.getFeeClude()==null)
		{
			line.setFeeClude("");
		}
		if(line.getFeeUnclude()==null)
		{
			line.setFeeUnclude("");
		}
		if(line.getRemark()==null)
		{
			line.setRemark("");
		}
		if(line.getPurchase()==null)
		{
			line.setPurchase("");
		}
		if(line.getSelfBuy()==null)
		{
			line.setSelfBuy("");
		}
		if(line.getSafe()==null)
		{
			line.setSafe("");
		}
		line.setFeature(StringUtils.HtmlEncode(line.getFeature()));
		line.setFeeClude(StringUtils.HtmlEncode(line.getFeeClude()));
		line.setFeeUnclude(StringUtils.HtmlEncode(line.getFeeUnclude()));
		line.setRemark(StringUtils.HtmlEncode(line.getRemark()));
		line.setPurchase(StringUtils.HtmlEncode(line.getPurchase()));
		line.setSelfBuy(StringUtils.HtmlEncode(line.getSelfBuy()));
		line.setSafe(StringUtils.HtmlEncode(line.getSafe()));
		StringBuilder sb1 = new StringBuilder();
		backDate = DateUtils.getBackDay(team.getRealDate(), team.getTeamday());
		team.setDoorChildPriceList(Team.getChildPricePackeage(team.getDoorChildPrice()));
		team.setDoorManPriceList(Team.getManPricePackeage(team.getDoorManPrice()));
		sb1.append(DateUtils.getWeekByDate(team.getRealDateStr(), Global.otherDayOfWeeks));
		this.dateType = sb1.toString();
		dateList = DateUtils.getDateStringByWeeks(team.getStartDateStr(), team.getEndDateStr(), team.getDateType());
		//制作日历所需参数
		int cnt = 0;
		Calendar cal = Calendar.getInstance();
		Calendar currCal = Calendar.getInstance();
		currMonth = currCal.get(Calendar.MONTH)+1;
		today = currCal.get(Calendar.DAY_OF_MONTH);
		if(team.getFlag()==3 || team.getFlag()==4)
		{
			Calendar tempCal = Calendar.getInstance();
			tempCal.setTime(DateUtils.parseDate(team.getEndDateStr()));
			if(currCal.get(Calendar.YEAR)==tempCal.get(Calendar.YEAR))
			{
				if(currCal.get(Calendar.MONTH)<tempCal.get(Calendar.MONTH))
				{
					int year = tempCal.get(Calendar.YEAR);
					int month = tempCal.get(Calendar.MONTH);
					if(month==0)
					{
						month = 12;
						year = year-1;
					}
					String preDate = year+Global.firstDayOfMonth[month-1];
					cal.setTime(DateUtils.parseDate(preDate));
				}
				else if(currCal.get(Calendar.MONTH)==tempCal.get(Calendar.MONTH))
				{
					cal.setTime(DateUtils.parseDate(team.getEndDateStr()));
				}
			}
			else
			{
				if(currCal.get(Calendar.MONTH)<tempCal.get(Calendar.MONTH)+12)
				{
					int year = tempCal.get(Calendar.YEAR);
					int month = tempCal.get(Calendar.MONTH);
					if(month==0)
					{
						month = 12;
						year = year-1;
					}
					String preDate = year+Global.firstDayOfMonth[month-1];
					cal.setTime(DateUtils.parseDate(preDate));
				}
				else if(currCal.get(Calendar.MONTH)==tempCal.get(Calendar.MONTH))
				{
					cal.setTime(DateUtils.parseDate(team.getEndDateStr()));
				}
			}
		}
		Calendar teamCal = Calendar.getInstance();
		//获取当前月份天数
		int days = Global.monthDays[cal.get(Calendar.MONTH)];
		//判断当前年份是否为闰年
		int year = cal.get(Calendar.YEAR);
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
		{
			if(cal.get(Calendar.MONTH)==1) days = 29;
		}
		this.currDate = String.valueOf(cal.get(Calendar.YEAR))+"年"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月";
		this.calMonth1 = cal.get(Calendar.MONTH)+1;
		//获取当月1号是星期几
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int weekOfFirstDay = cal.get(Calendar.DAY_OF_WEEK);
		List<MyDay> myDays = new ArrayList<MyDay>();
		for(int i=0;i<teamList.size();i++)
		{
			teamList.get(i).setPushDown(DateUtils.isPushDown(teamList.get(i).getRealDate(), teamList.get(i).getPushDay()));
		}
		//设置当前日期中有团队的团队信息
		for(int i=0;i<days;i++)
		{
			MyDay myDay = new MyDay();
			myDay.setDay(i+1);
			for(int j=0;j<teamList.size();j++)
			{
				teamCal.setTime(teamList.get(j).getRealDate());
				if(teamCal.get(Calendar.MONTH)==cal.get(Calendar.MONTH))
				{	
					int num= teamCal.get(Calendar.DAY_OF_MONTH)-1;
					if(i==num)
					{
						String[] arrayDate = teamList.get(j).getRealDateStr().split("-");
						myDay.setCalYear(arrayDate[0]);
						myDay.setCalMonth(arrayDate[1]);
						myDay.setCalDay(arrayDate[2]);
						myDay.setTeam(teamList.get(j));
					}
				}
			}
			myDays.add(myDay);
		}
		//构件日历
		List<MyDay> tempDays = new ArrayList<MyDay>();
		for(int i=1;i<days+1;i++)
		{
			if(i<weekOfFirstDay)
			{
				MyDay nDay = null;
				tempDays.add(nDay);
				cnt++;
			}
			else
			{
				tempDays.add(myDays.get(i-cnt-1));
				if(i-cnt-1==0)
				{
					days = days+cnt;
				}
			}
			if(i%7==0)
			{
				MyWeek myWeek = new MyWeek();
				myWeek.setDays(tempDays);
				tempDays = new ArrayList<MyDay>();
				weekList.add(myWeek);
			}
			if(i==days&&days>28)
			{
				MyWeek myWeek = new MyWeek();
				myWeek.setDays(tempDays);
				tempDays = new ArrayList<MyDay>();
				weekList.add(myWeek);
			}
				
		}
		//构件下月日历
		int month = cal.get(Calendar.MONTH);
		if(month==11)
		{
			month = -1;
			year = year+1;
		}
		days = Global.monthDays[month+1];
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
		{
			if(cal.get(Calendar.MONTH)+1==1) days = 29;
		}
		this.calMonth2 = month+2;
		this.nextDate = String.valueOf(year)+"年"+String.valueOf(month+2)+"月";
		
		String nextDate = year+Global.firstDayOfMonth[month+1];
		cal.setTime(DateUtils.parseDate(nextDate));
		weekOfFirstDay =  cal.get(Calendar.DAY_OF_WEEK);
		myDays = new ArrayList<MyDay>();
		for(int i=0;i<days;i++)
		{
			MyDay myDay = new MyDay();
			myDay.setDay(i+1);
			
			for(int j=0;j<teamList.size();j++)
			{
				teamCal.setTime(teamList.get(j).getRealDate());
				if(teamCal.get(Calendar.MONTH)==cal.get(Calendar.MONTH))
				{	
					int num= teamCal.get(Calendar.DAY_OF_MONTH)-1;
					if(i==num)
					{
						String[] arrayDate = teamList.get(j).getRealDateStr().split("-");
						myDay.setCalYear(arrayDate[0]);
						myDay.setCalMonth(arrayDate[1]);
						myDay.setCalDay(arrayDate[2]);
						myDay.setTeam(teamList.get(j));
					}
				}
			}
			myDays.add(myDay);
		}
		cnt = 0;
		tempDays = new ArrayList<MyDay>();
		for(int i=1;i<days+1;i++)
		{
			if(i<weekOfFirstDay)
			{
				MyDay nDay = null;
				tempDays.add(nDay);
				cnt++;
			}
			else
			{
				tempDays.add(myDays.get(i-cnt-1));
				if(i-cnt-1==0)
				{
					days = days+cnt;
				}
			}
			if(i%7==0)
			{
				MyWeek myWeek = new MyWeek();
				myWeek.setDays(tempDays);
				tempDays = new ArrayList<MyDay>();
				nextWeekList.add(myWeek);
			}
			if(i==days&&days>28)
			{
				MyWeek myWeek = new MyWeek();
				myWeek.setDays(tempDays);
				tempDays = new ArrayList<MyDay>();
				nextWeekList.add(myWeek);
			}
				
		}
		return SUCCESS;
	}
	public Team getTeam()
	{
		return team;
	}
	public void setTeam(Team team) 
	{
		this.team = team;
	}
	public String getTeamId()
	{
		return teamId;
	}
	public void setTeamId(String teamId)
	{
		this.teamId = teamId;
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
	public List<Content> getConList()
	{
		return conList;
	}
	public void setConList(List<Content> conList)
	{
		this.conList = conList;
	}
	public List<String> getDateList()
	{
		return dateList;
	}
	public void setDateList(List<String> dateList)
	{
		this.dateList = dateList;
	}
	public String getErrorMsg()
	{
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}
	public Line getLine()
	{
		return line;
	}
	public void setLine(Line line)
	{
		this.line = line;
	}
	public List<PicuterFour> getPfList()
	{
		return pfList;
	}
	public void setPfList(List<PicuterFour> pfList)
	{
		this.pfList = pfList;
	}
	public List<AssembleContent> getReAssList()
	{
		return reAssList;
	}
	public void setReAssList(List<AssembleContent> reAssList)
	{
		this.reAssList = reAssList;
	}
	public String getBackDate()
	{
		return backDate;
	}
	public void setBackDate(String backDate)
	{
		this.backDate = backDate;
	}
	public String getDateType()
	{
		return dateType;
	}
	public void setDateType(String dateType)
	{
		this.dateType = dateType;
	}
	public int getDay()
	{
		return day;
	}
	public void setDay(int day)
	{
		this.day = day;
	}
	public int getDayEnd()
	{
		return dayEnd;
	}
	public void setDayEnd(int dayEnd)
	{
		this.dayEnd = dayEnd;
	}
	public int getDayStart()
	{
		return dayStart;
	}
	public void setDayStart(int dayStart)
	{
		this.dayStart = dayStart;
	}
	public int getTeamFlag()
	{
		return teamFlag;
	}
	public void setTeamFlag(int flag)
	{
		this.teamFlag = flag;
	}
	public String getKeyWord()
	{
		return keyWord;
	}
	public void setKeyWord(String keyWord)
	{
		this.keyWord = keyWord;
	}
	public int getPriceEnd()
	{
		return priceEnd;
	}
	public void setPriceEnd(int priceEnd)
	{
		this.priceEnd = priceEnd;
	}
	public int getPriceStart()
	{
		return priceStart;
	}
	public void setPriceStart(int priceStart)
	{
		this.priceStart = priceStart;
	}
	public String getRegionId()
	{
		return regionId;
	}
	public void setRegionId(String regionId)
	{
		this.regionId = regionId;
	}
	public String getSceneryId()
	{
		return sceneryId;
	}
	public void setSceneryId(String sceneryId)
	{
		this.sceneryId = sceneryId;
	}
	public int getShowType()
	{
		return showType;
	}
	public void setShowType(int showType)
	{
		this.showType = showType;
	}
	public String getSort()
	{
		return sort;
	}
	public void setSort(String sort)
	{
		this.sort = sort;
	}
	public int getTejia()
	{
		return tejia;
	}
	public void setTejia(int tejia)
	{
		this.tejia = tejia;
	}
	public int getTuijian()
	{
		return tuijian;
	}
	public void setTuijian(int tuijian)
	{
		this.tuijian = tuijian;
	}
	public String getYouhui()
	{
		return youhui;
	}
	public void setYouhui(String youhui)
	{
		this.youhui = youhui;
	}
	public int getPage()
	{
		return page;
	}
	public void setPage(int page)
	{
		this.page = page;
	}
	public int getPageNo()
	{
		return pageNo;
	}
	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}
	public Page getPublicPage()
	{
		return publicPage;
	}
	public void setPublicPage(Page publicPage)
	{
		this.publicPage = publicPage;
	}
	public List<Region> getRegionList()
	{
		return regionList;
	}
	public void setRegionList(List<Region> regionList)
	{
		this.regionList = regionList;
	}
	public IRegionService getRegionService()
	{
		return regionService;
	}
	public void setRegionService(IRegionService regionService)
	{
		this.regionService = regionService;
	}
	public List<TeamSort> getTeamSortList()
	{
		return teamSortList;
	}
	public void setTeamSortList(List<TeamSort> teamSortList)
	{
		this.teamSortList = teamSortList;
	}
	public List<TeamSort> getYouhuiList()
	{
		return youhuiList;
	}
	public void setYouhuiList(List<TeamSort> youhuiList)
	{
		this.youhuiList = youhuiList;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public String getCurrDate()
	{
		return currDate;
	}
	public void setCurrDate(String currDate)
	{
		this.currDate = currDate;
	}
	public String getNextDate()
	{
		return nextDate;
	}
	public void setNextDate(String nextDate)
	{
		this.nextDate = nextDate;
	}
	public List<MyWeek> getNextWeekList()
	{
		return nextWeekList;
	}
	public void setNextWeekList(List<MyWeek> nextWeekList)
	{
		this.nextWeekList = nextWeekList;
	}
	public List<Team> getSkimList()
	{
		return skimList;
	}
	public void setSkimList(List<Team> skimList)
	{
		this.skimList = skimList;
	}
	public List<MyWeek> getWeekList()
	{
		return weekList;
	}
	public void setWeekList(List<MyWeek> weekList)
	{
		this.weekList = weekList;
	}
	public int getToday()
	{
		return today;
	}
	public void setToday(int today)
	{
		this.today = today;
	}
	public List<Region> getBannerRegions()
	{
		return bannerRegions;
	}
	public void setBannerRegions(List<Region> bannerRegions)
	{
		this.bannerRegions = bannerRegions;
	}
	public Region getParent()
	{
		return parent;
	}
	public void setParent(Region parent)
	{
		this.parent = parent;
	}
	public List<Region> getRegions()
	{
		return regions;
	}
	public void setRegions(List<Region> regions)
	{
		this.regions = regions;
	}
	public ILineService getLineService()
	{
		return lineService;
	}
	public void setLineService(ILineService lineService)
	{
		this.lineService = lineService;
	}
	public Region getRegion()
	{
		return region;
	}
	public void setRegion(Region region)
	{
		this.region = region;
	}

	public IAreaService getAreaService()
	{
		return areaService;
	}
	public void setAreaService(IAreaService areaService)
	{
		this.areaService = areaService;
	}
	public List<Area> getBegionAreaList()
	{
		return begionAreaList;
	}
	public void setBegionAreaList(List<Area> begionAreaList)
	{
		this.begionAreaList = begionAreaList;
	}
	public String getJsonString()
	{
		return jsonString;
	}
	public void setJsonString(String jsonString)
	{
		this.jsonString = jsonString;
	}
	public int getDayFlag()
	{
		return dayFlag;
	}
	public void setDayFlag(int dayFlag)
	{
		this.dayFlag = dayFlag;
	}
	public IWebSiteService getWebSiteService()
	{
		return webSiteService;
	}
	public void setWebSiteService(IWebSiteService webSiteService)
	{
		this.webSiteService = webSiteService;
	}
	public WebSite getWebSite()
	{
		return webSite;
	}
	public void setWebSite(WebSite webSite)
	{
		this.webSite = webSite;
	}
	public List<Team> getContactList()
	{
		return contactList;
	}
	public void setContactList(List<Team> contactList)
	{
		this.contactList = contactList;
	}
	public ITeamCountService getTeamCountService()
	{
		return teamCountService;
	}
	public void setTeamCountService(ITeamCountService teamCountService)
	{
		this.teamCountService = teamCountService;
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
	public int getSearchType()
	{
		return searchType;
	}
	public void setSearchType(int searchType)
	{
		this.searchType = searchType;
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
	public List<Content> getReturnLineContent()
	{
		return returnLineContent;
	}
	public void setReturnLineContent(List<Content> returnLineContent)
	{
		this.returnLineContent = returnLineContent;
	}
	public int getCurrMonth()
	{
		return currMonth;
	}
	public void setCurrMonth(int currMonth)
	{
		this.currMonth = currMonth;
	}
	public int getCalMonth1()
	{
		return calMonth1;
	}
	public void setCalMonth1(int calMonth1)
	{
		this.calMonth1 = calMonth1;
	}
	public int getCalMonth2()
	{
		return calMonth2;
	}
	public void setCalMonth2(int calMonth2)
	{
		this.calMonth2 = calMonth2;
	}
	public IMessageService getMessageService()
	{
		return messageService;
	}
	public void setMessageService(IMessageService messageService)
	{
		this.messageService = messageService;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getGroupId()
	{
		return groupId;
	}
	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}
	public String getSendContain()
	{
		return sendContain;
	}
	public void setSendContain(String sendContain)
	{
		this.sendContain = sendContain;
	}
	public String getSender()
	{
		return sender;
	}
	public void setSender(String sender)
	{
		this.sender = sender;
	}
	public String getTeamName()
	{
		return teamName;
	}
	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}
	public IUserService getUserService()
	{
		return userService;
	}
	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}
	public List<Message> getMessageList()
	{
		return messageList;
	}
	public void setMessageList(List<Message> messageList)
	{
		this.messageList = messageList;
	}

	public int getBannerFlag()
	{
		return bannerFlag;
	}
	public void setBannerFlag(int bannerFlag)
	{
		this.bannerFlag = bannerFlag;
	}
	public TeamCount getTcnt()
	{
		return tcnt;
	}
	public void setTcnt(TeamCount tcnt)
	{
		this.tcnt = tcnt;
	}

	

}
