package com.yeoou.tour.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yeoou.common.context.Global;
import com.yeoou.common.utils.DateUtils;
import com.yeoou.common.utils.SerialNoUtils;
import com.yeoou.common.utils.StringUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Assemble;
import com.yeoou.tour.model.AssembleContent;
import com.yeoou.tour.model.BakTeam;
import com.yeoou.tour.model.ChildPrice;
import com.yeoou.tour.model.DoorOrder;
import com.yeoou.tour.model.Line;
import com.yeoou.tour.model.PicuterFour;
import com.yeoou.tour.model.Team;
import com.yeoou.tour.model.TripContent;
import com.yeoou.tour.model.WebSite;
import com.yeoou.tour.service.IBakTeamService;
import com.yeoou.tour.service.IDoorOrderService;
import com.yeoou.tour.service.ITeamService;
import com.yeoou.tour.service.ITripContentService;
import com.yeoou.tour.service.IWebSiteService;
/**
 * <p>
 * Title: 前台开班计划订单操作模块
 * </p>
 * <p>
 * Description: 对订单的操作进行相关显示的页面，订单第一步信息填写stepOne.shtml<br/>
 * 订单第二步信息填写stepOne.shtml,订单确定信息页面addOrder.shtml<br/>
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class ClientDoorOrderAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IDoorOrderService doorOrderService;
	private ITeamService teamService;
	private IBakTeamService bakTeamService;
	private Team team;
	private int searchType = 0;
	private String teamId = "";
	private String startDateStr = "";
	private String backDateStr = "";
	private int manNum = 0;
	private int childNum=0;
	private int total = 0;
	private int manTotal = 0;
	private int childTotal = 0;
	private int manPrice = 0;
	private int childPrice = 0;
	private String customName = "";
	private String customMobile = "";
	private String customAddress = "";
	private String customEmail = "";
	private String customRemark = "";
	private String htmlCustomRemark= "";
	private String htmlCustomAddress = "";
	private String customFax="";
	private String customZip="";
	private String assemble = "";
	private String address = "";
	private String jiajia = "";
	private String shijian = "";
	private String jiesong = "";
	private WebSite webSite;
	private IWebSiteService webSiteService;
	private ITripContentService tripContentService;
	private List<String> searchList = new ArrayList<String>();
	private List<AssembleContent> reAssList;
	private List<TripContent> gg_ss;
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
	 * 订单信息填写第一步页面
	 * @return
	 */
	public String addOrderStepOne()
	{
		getBannaerInfo();
		this.team = (Team) teamService.getTeam(teamId );
		if(!team.getDoorChildPrice().equals(""))
		{
			team.setDoorChildPriceList(ChildPrice.getChildPricePackeage(team.getDoorChildPrice()));
			
			childPrice = team.getDoorChildPriceList().get(0).getMarketPrice();
		}
		List<PicuterFour> picList = new ArrayList<PicuterFour>();
		picList = PicuterFour.unpackagePicFour(team.getLine().getPicArea());
		if(picList!=null)
		{
			team.setOrgUrl(picList.get(0).getBreUrl());
			team.setBreUrl(picList.get(0).getUrl());
		}
		reAssList = Assemble.getContentPackeage(team.getAssemble());
		for(int i=0;i<reAssList.size();i++)
		{
			if(reAssList.get(i).getAsstime().equals(""))
			{
				reAssList.get(i).setAsstime("待定");
			}
			reAssList.get(i).setCompact(reAssList.get(i).getAsstime()+"---"+reAssList.get(i).getAddress()+"---"+reAssList.get(i).getPrice()+"---"+reAssList.get(i).getJiesong());
		}
		manPrice = team.getDoorDisPrice();
		manTotal = manPrice*manNum;
		if(childPrice!=0) childTotal = childNum*childPrice;
		else childTotal = childNum* manPrice;
		total = manTotal+childTotal;
		if(!startDateStr.equals(""))
		this.backDateStr = DateUtils.getBackDay(DateUtils.parseDate(startDateStr), team.getTeamday());
		
		return SUCCESS;
	}
	/**
	 * 订单信息填写第二步页面
	 * @return
	 */
	public String addOrderStepTwo()
	{
		getBannaerInfo();
		this.team = (Team) teamService.getTeam(teamId);
		List<PicuterFour> picList = new ArrayList<PicuterFour>();
		picList = PicuterFour.unpackagePicFour(team.getLine().getPicArea());
		if(picList!=null)
		{
			team.setOrgUrl(picList.get(0).getBreUrl());
			team.setBreUrl(picList.get(0).getUrl());
		}
		String[] temp = assemble.split("---");
		this.shijian = temp[0];
		this.address = temp[1];
		this.jiajia =  temp[2];
		this.jiesong = temp[3];
		this.htmlCustomRemark = StringUtils.HtmlEncode(this.customRemark);
		this.htmlCustomAddress = StringUtils.HtmlEncode(this.customAddress);
		return SUCCESS;
	}
	/**
	 * 确定新订单信息页面
	 * @return
	 */
	public String addOrder()
	{
		getBannaerInfo();
		Team team = new Team();
		DoorOrder order = new DoorOrder();
		BakTeam bakTeam = new BakTeam();
		team = (Team)teamService.get(teamId);
		Line line = new Line();
		Date date = new Date();
		Date orderDate = new Date();
		String orderDateStr = DateUtils.formatDate(date);
		orderDate = DateUtils.parseDate(orderDateStr);
		String id = SerialNoUtils.getSerialNo(orderDateStr);
		line = team.getLine();
		bakTeam = bakTeamService.getBakTeamByTeamId(teamId,team.getShadowNum());
		if(bakTeam==null)
		{
				bakTeam = new BakTeam();
				bakTeam.setContent(line.getContent());
				bakTeam.setShadowNum(team.getShadowNum());
				bakTeam.setFlag(team.getFlag());
				bakTeam.setTheme(team.getTheme());
				bakTeam.setPicUrl(team.getPicUrl());
				bakTeam.setDay(team.getTeamday());
				bakTeam.setFeature(line.getFeature());
				bakTeam.setFeeClude(line.getFeeClude());
				bakTeam.setFeeUnclude(line.getFeeUnclude());
				bakTeam.setHotelContent(team.getHotelContent());
				bakTeam.setHotelPrice(team.getHotelPrice());
				bakTeam.setHotelStar(team.getHotelStar());
				bakTeam.setPlaneBack(team.getPlaneBack());
				bakTeam.setPlaneGo(team.getPlaneGo());
				bakTeam.setPlaneTax(team.getPlaneTax());
				bakTeam.setChildPrice(team.getDoorChildPrice());
				bakTeam.setManPrice(team.getDoorManPrice());
				bakTeam.setPurchase(team.getLine().getPurchase());
				bakTeam.setRemark(line.getRemark());
				bakTeam.setSafe(line.getSafe());
				bakTeam.setSelfBuy(line.getSelfBuy());
				bakTeam.setRealDate(team.getRealDateStr());
				bakTeam.setTeamId(team.getTeamId());
				bakTeam.setTeamName(team.getTeamName());
				bakTeam.setTeamNo(team.getTeamNum());
				bakTeam.setTicketType(team.getTicketType());
				bakTeam.setUrgentTel(team.getUrgentTel());
				bakTeam.setUser(team.getUser());
				bakTeamService.save(bakTeam);
			}
			order.setManNum(manNum);
			order.setChildNum(childNum);
			order.setAllCost(this.total);
			order.setAssemble(assemble);
			order.setBakTeam(bakTeam);
			order.setFlexId(id);
			order.setCustomEmail(customEmail);
			order.setChildrenPriceList(team.getDoorChildPrice());
			order.setManPriceList(team.getDoorManPrice());
			order.setCustomAddress(this.customAddress);
			order.setCustomMobile(this.customMobile);
			order.setCustomName(this.customName);
			order.setCustomFax(customFax);
			order.setStatus(Global.DOORORDER_STATUS_NEWORDER);
			order.setGroupId(team.getGroupId());
			order.setCustomRemark(customRemark);
			order.setShouldCost(this.total);
			order.setTeamName(team.getTeamName());
			order.setOrderDate(orderDate);
			order.setOrderDateStr(orderDateStr);
			order.setCustomZip(customZip);
			order.setIsNew(1);
			if(!this.startDateStr.equals(""))
			{
				order.setStartDate(DateUtils.parseDate(this.startDateStr));
				order.setStartDateStr(this.startDateStr);
				order.setEndDate(DateUtils.parseDate(this.backDateStr));
				order.setEndDateStr(this.backDateStr);
			}
	        doorOrderService.save(order);
		return SUCCESS;
	}
	
	public String getAssemble()
	{
		return assemble;
	}
	public void setAssemble(String assemble)
	{
		this.assemble = assemble;
	}
	public IBakTeamService getBakTeamService()
	{
		return bakTeamService;
	}
	public void setBakTeamService(IBakTeamService bakTeamService)
	{
		this.bakTeamService = bakTeamService;
	}
	public String getCustomFax()
	{
		return customFax;
	}
	public void setCustomFax(String customFax)
	{
		this.customFax = customFax;
	}
	public String getCustomZip()
	{
		return customZip;
	}
	public void setCustomZip(String customZip)
	{
		this.customZip = customZip;
	}
	public String getHtmlCustomAddress()
	{
		return htmlCustomAddress;
	}
	public void setHtmlCustomAddress(String htmlCustomAddress)
	{
		this.htmlCustomAddress = htmlCustomAddress;
	}
	public String getHtmlCustomRemark()
	{
		return htmlCustomRemark;
	}
	public void setHtmlCustomRemark(String htmlCustomRemark)
	{
		this.htmlCustomRemark = htmlCustomRemark;
	}
	public List<AssembleContent> getReAssList()
	{
		return reAssList;
	}
	public void setReAssList(List<AssembleContent> reAssList)
	{
		this.reAssList = reAssList;
	}
	public String getCustomAddress()
	{
		return customAddress;
	}
	public void setCustomAddress(String customAddress)
	{
		this.customAddress = customAddress;
	}
	public String getCustomEmail()
	{
		return customEmail;
	}
	public void setCustomEmail(String customEmail)
	{
		this.customEmail = customEmail;
	}
	public String getCustomMobile()
	{
		return customMobile;
	}
	public void setCustomMobile(String customMobile)
	{
		this.customMobile = customMobile;
	}
	public String getCustomName()
	{
		return customName;
	}
	public void setCustomName(String customName)
	{
		this.customName = customName;
	}
	public String getCustomRemark()
	{
		return customRemark;
	}
	public void setCustomRemark(String customRemark)
	{
		this.customRemark = customRemark;
	}
	public IDoorOrderService getDoorOrderService()
	{
		return doorOrderService;
	}
	public void setDoorOrderService(IDoorOrderService doorOrderService)
	{
		this.doorOrderService = doorOrderService;
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
	public ITeamService getTeamService()
	{
		return teamService;
	}
	public void setTeamService(ITeamService teamService)
	{
		this.teamService = teamService;
	}
	public String getBackDateStr()
	{
		return backDateStr;
	}
	public void setBackDateStr(String backDateStr)
	{
		this.backDateStr = backDateStr;
	}
	public int getChildNum()
	{
		return childNum;
	}
	public void setChildNum(int childNum)
	{
		this.childNum = childNum;
	}
	public int getChildPrice()
	{
		return childPrice;
	}
	public void setChildPrice(int childPrice)
	{
		this.childPrice = childPrice;
	}
	public int getChildTotal()
	{
		return childTotal;
	}
	public void setChildTotal(int childTotal)
	{
		this.childTotal = childTotal;
	}
	public int getManNum()
	{
		return manNum;
	}
	public void setManNum(int manNum)
	{
		this.manNum = manNum;
	}
	public int getManPrice()
	{
		return manPrice;
	}
	public void setManPrice(int manPrice)
	{
		this.manPrice = manPrice;
	}
	public int getManTotal()
	{
		return manTotal;
	}
	public void setManTotal(int manTotal)
	{
		this.manTotal = manTotal;
	}
	public String getStartDateStr()
	{
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr)
	{
		this.startDateStr = startDateStr;
	}
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getJiajia()
	{
		return jiajia;
	}
	public void setJiajia(String jiajia)
	{
		this.jiajia = jiajia;
	}
	public String getShijian()
	{
		return shijian;
	}
	public void setShijian(String shijian)
	{
		this.shijian = shijian;
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
	public int getSearchType()
	{
		return searchType;
	}
	public void setSearchType(int searchType)
	{
		this.searchType = searchType;
	}
	public String getJiesong()
	{
		return jiesong;
	}
	public void setJiesong(String jiesong)
	{
		this.jiesong = jiesong;
	}
}
