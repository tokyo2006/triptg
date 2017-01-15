package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yeoou.rbac.model.User;

public class Team extends Genericmodel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4874095624527959932L;
	private String teamId;
	private String dateArea;
	private String teamName;
	private String teamNum;
	private String shadowNum;
	private int sellNum;
	private String status;
	private String theme;
	private int total;
	private int book;
	private int confirm;
	private String picUrl;
	private int teamday;
	private Line line;
	private List<ManPrice> manPriceList;
	private List<ChildPrice>childPriceList;
	private String manPrice;
	private String childPrice;
	private int displayPrice;
	private List<ManPrice> doorManPriceList;
	private List<ChildPrice> doorChildPriceList;
	private String doorManPrice;
	private String doorChildPrice;
	private int doorDisPrice;
	private String groupId;
	private User user;
	private Date realDate;
	private String realDateStr;
	private Date startDate;
	private Area area;
	private Date endDate;
	private String endDateStr;
	private String dateType;
	private String startDateStr;
	private int busType;
	private int hotelPrice;
	private String docUrl;
	private String hotelStar;
	private String urgentTel;
	private String startTime;
	private int teamType;
	private String recommend;
	private String newproduct;
	private String speical;
	private boolean enable;
	private boolean pushDown;
	private boolean market;
	private int ticketType;
	private String ticketId;
	private int flag;
	private int pushDay;
	private boolean shop;
	private String planeGo;
	private String planeBack;
	private int planeTax;
	private String orderConfirm;
	private String hotelContent;
	private TicketLog ticketLog;
	private String assemble;
	private String youhui;
	private String jobberName;
	private int isNet;
	private String themeColor;
	private Set<Area> areas = new HashSet<Area>();
	private Set<Region> regions = new HashSet<Region>();
	private List<MyWeek> weekList = new ArrayList<MyWeek>();
	private List<MyWeek> nextWeekList = new ArrayList<MyWeek>();
	private Document doc;
	private int hasNextMonth = 0;
	private String breUrl;
	private String orgUrl;
	private String comeTraffic;
	private String goTraffic;
	private int dyj;
	private int correct;
	public int getCorrect()
	{
		return correct;
	}
	public void setCorrect(int correct)
	{
		this.correct = correct;
	}
	public int getDyj()
	{
		return dyj;
	}
	public void setDyj(int dyj)
	{
		this.dyj = dyj;
	}
	public String getBreUrl()
	{
		return breUrl;
	}
	public void setBreUrl(String breUrl)
	{
		this.breUrl = breUrl;
	}
	public String getOrgUrl()
	{
		return orgUrl;
	}
	public void setOrgUrl(String orgUrl)
	{
		this.orgUrl = orgUrl;
	}
	public Area getArea()
	{
		return area;
	}
	public void setArea(Area area)
	{
		this.area = area;
	}

	public boolean getEnable()
	{
		return enable;
	}
	public void setEnable(boolean enable)
	{
		this.enable = enable;
	}
	public int getFlag()
	{
		return flag;
	}
	public void setFlag(int flag)
	{
		this.flag = flag;
	}
	public String getHotelContent()
	{
		return hotelContent;
	}
	public void setHotelContent(String hotelContent)
	{
		this.hotelContent = hotelContent;
	}
	public int getHotelPrice()
	{
		return hotelPrice;
	}
	public void setHotelPrice(int hotelPrice)
	{
		this.hotelPrice = hotelPrice;
	}
	public String getHotelStar()
	{
		return hotelStar;
	}
	public void setHotelStar(String hotelStar)
	{
		this.hotelStar = hotelStar;
	}
	public Line getLine()
	{
		return line;
	}
	public void setLine(Line line)
	{
		this.line = line;
	}
	public boolean getMarket()
	{
		return market;
	}
	public void setMarket(boolean market)
	{
		this.market = market;
	}
	public String getNewproduct()
	{
		return newproduct;
	}
	public void setNewproduct(String newproduct)
	{
		this.newproduct = newproduct;
	}
	public String getPlaneBack()
	{
		return planeBack;
	}
	public void setPlaneBack(String planeBack)
	{
		this.planeBack = planeBack;
	}
	public String getPlaneGo()
	{
		return planeGo;
	}
	public void setPlaneGo(String planeGo)
	{
		this.planeGo = planeGo;
	}
	public int getPlaneTax()
	{
		return planeTax;
	}
	public void setPlaneTax(int planeTax)
	{
		this.planeTax = planeTax;
	}

	public String getRecommend()
	{
		return recommend;
	}
	public void setRecommend(String recommand)
	{
		this.recommend = recommand;
	}
	public boolean getShop()
	{
		return shop;
	}
	public void setShop(boolean shop)
	{
		this.shop = shop;
	}
	public String getSpeical()
	{
		return speical;
	}
	public void setSpeical(String speical)
	{
		this.speical = speical;
	}
	public Date getStartDate()
	{
		return startDate;
	}
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
	public String getStartDateStr()
	{
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr)
	{
		this.startDateStr = startDateStr;
	}
	public String getStartTime()
	{
		return startTime;
	}
	public void setStartTime(String starttime)
	{
		this.startTime = starttime;
	}
	public String getTeamId()
	{
		return teamId;
	}
	public void setTeamId(String teamId)
	{
		this.teamId = teamId;
	}
	public String getTeamNum()
	{
		return teamNum;
	}
	public void setTeamNum(String teamNum)
	{
		this.teamNum = teamNum;
	}
	public int getTeamType()
	{
		return teamType;
	}
	public void setTeamType(int teamType)
	{
		this.teamType = teamType;
	}
	public String getUrgentTel()
	{
		return urgentTel;
	}
	public void setUrgentTel(String urgentTel)
	{
		this.urgentTel = urgentTel;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public String getTicketId()
	{
		return ticketId;
	}
	public void setTicketId(String ticketId)
	{
		this.ticketId = ticketId;
	}
	public int getTicketType()
	{
		return ticketType;
	}
	public void setTicketType(int ticketType)
	{
		this.ticketType = ticketType;
	}
	
	public String getAssemble()
	{
		return assemble;
	}
	public void setAssemble(String assemble)
	{
		this.assemble = assemble;
	}
	public String getTeamName()
	{
		return teamName;
	}
	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}
	public Set<Area> getAreas()
	{
		return areas;
	}
	public void setAreas(Set<Area> areas)
	{
		this.areas = areas;
	}
	public int getTeamday()
	{
		return teamday;
	}
	public void setTeamday(int teamday)
	{
		this.teamday = teamday;
	}
	public Document getDoc()
	{
		return doc;
	}
	public void setDoc(Document doc)
	{
		this.doc = doc;
	}
	public String getDateType()
	{
		return dateType;
	}
	public void setDateType(String dateType)
	{
		this.dateType = dateType;
	}
	public Date getEndDate()
	{
		return endDate;
	}
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
	public String getEndDateStr()
	{
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr)
	{
		this.endDateStr = endDateStr;
	}
	public String getOrderConfirm()
	{
		return orderConfirm;
	}
	public void setOrderConfirm(String orderConfirm)
	{
		this.orderConfirm = orderConfirm;
	}
	public String getDateArea()
	{
		return dateArea;
	}
	public void setDateArea(String dateArea)
	{
		this.dateArea = dateArea;
	}
	public String getChildPrice()
	{
		return childPrice;
	}
	public void setChildPrice(String childPrice)
	{
		this.childPrice = childPrice;
	}
	public String getManPrice()
	{
		return manPrice;
	}
	public void setManPrice(String manPrice)
	{
		this.manPrice = manPrice;
	}
	public List<ChildPrice> getChildPriceList()
	{
		return childPriceList;
	}
	public void setChildPriceList(List<ChildPrice> childPriceList)
	{
		this.childPriceList = childPriceList;
	}
	public List<ManPrice> getManPriceList()
	{
		return manPriceList;
	}
	public void setManPriceList(List<ManPrice> manPriceList)
	{
		this.manPriceList = manPriceList;
	}
	public int getDisplayPrice()
	{
		return displayPrice;
	}
	public void setDisplayPrice(int displayPrice)
	{
		this.displayPrice = displayPrice;
	}
	public String getShadowNum()
	{
		return shadowNum;
	}
	public void setShadowNum(String shadowNum)
	{
		this.shadowNum = shadowNum;
	}
	public String getTheme()
	{
		return theme;
	}
	public void setTheme(String theme)
	{
		this.theme = theme;
	}
	public int getBook()
	{
		return book;
	}
	public void setBook(int book)
	{
		this.book = book;
	}
	public int getConfirm()
	{
		return confirm;
	}
	public void setConfirm(int confirm)
	{
		this.confirm = confirm;
	}
	public String getPicUrl()
	{
		return picUrl;
	}
	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public int getSellNum() {
		return sellNum;
	}
	public void setSellNum(int sellNum) {
		this.sellNum = sellNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getBusType() {
		return busType;
	}
	public void setBusType(int busType) {
		this.busType = busType;
	}
	public String getDocUrl() {
		return docUrl;
	}
	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}
	public Date getRealDate()
	{
		return realDate;
	}
	public void setRealDate(Date realDate)
	{
		this.realDate = realDate;
	}
	public TicketLog getTicketLog()
	{
		return ticketLog;
	}
	public void setTicketLog(TicketLog ticketLog)
	{
		this.ticketLog = ticketLog;
	}
	public String getRealDateStr()
	{
		return realDateStr;
	}
	public void setRealDateStr(String realDateStr)
	{
		this.realDateStr = realDateStr;
	}
	public String getThemeColor()
	{
		return themeColor;
	}
	public void setThemeColor(String themeColor)
	{
		this.themeColor = themeColor;
	}
	public String getYouhui()
	{
		return youhui;
	}
	public void setYouhui(String youhui)
	{
		this.youhui = youhui;
	}
	public String getGroupId()
	{
		return groupId;
	}
	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}
	
	public Set<Region> getRegions()
	{
		return regions;
	}
	public void setRegions(Set<Region> regions)
	{
		this.regions = regions;
	}
	public int getPushDay()
	{
		return pushDay;
	}
	public void setPushDay(int pushDay)
	{
		this.pushDay = pushDay;
	}
	public boolean getPushDown()
	{
		return pushDown;
	}
	public void setPushDown(boolean pushDown)
	{
		this.pushDown = pushDown;
	}
	public int getIsNet()
	{
		return isNet;
	}
	public void setIsNet(int isNet)
	{
		this.isNet = isNet;
	}
	public String getJobberName()
	{
		return jobberName;
	}
	public void setJobberName(String jobberName)
	{
		this.jobberName = jobberName;
	}
	public int getDoorDisPrice()
	{
		return doorDisPrice;
	}
	public void setDoorDisPrice(int doorDisPrice)
	{
		this.doorDisPrice = doorDisPrice;
	}
	public List<ManPrice> getDoorManPriceList()
	{
		return doorManPriceList;
	}
	public void setDoorManPriceList(List<ManPrice> doorManPriceList)
	{
		this.doorManPriceList = doorManPriceList;
	}
	public List<ChildPrice> getDoorChildPriceList()
	{
		return doorChildPriceList;
	}
	public void setDoorChildPriceList(List<ChildPrice> doorChildPriceList)
	{
		this.doorChildPriceList = doorChildPriceList;
	}
	public String getDoorManPrice()
	{
		return doorManPrice;
	}
	public void setDoorManPrice(String doorManPrice)
	{
		this.doorManPrice = doorManPrice;
	}
	public void setDoorChildPrice(String doorChildPrice)
	{
		this.doorChildPrice = doorChildPrice;
	}
	public String getDoorChildPrice() {
		return doorChildPrice;
	}
	public String getComeTraffic()
	{
		return comeTraffic;
	}
	public void setComeTraffic(String comeTraffic)
	{
		this.comeTraffic = comeTraffic;
	}
	public String getGoTraffic()
	{
		return goTraffic;
	}
	public void setGoTraffic(String goTraffic)
	{
		this.goTraffic = goTraffic;
	}
	public List<MyWeek> getNextWeekList()
	{
		return nextWeekList;
	}
	public void setNextWeekList(List<MyWeek> nextWeekList)
	{
		this.nextWeekList = nextWeekList;
	}
	public List<MyWeek> getWeekList()
	{
		return weekList;
	}
	public void setWeekList(List<MyWeek> weekList)
	{
		this.weekList = weekList;
	}
	public int getHasNextMonth()
	{
		return hasNextMonth;
	}
	public void setHasNextMonth(int hasNextMonth)
	{
		this.hasNextMonth = hasNextMonth;
	}

}
