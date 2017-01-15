package com.yeoou.tour.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import com.yeoou.common.context.Global;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.DateUtils;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.utils.SerialNoUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.rbac.model.User;
import com.yeoou.rbac.service.IUserService;
import com.yeoou.tour.model.Air;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.Assemble;
import com.yeoou.tour.model.Bus;
import com.yeoou.tour.model.Company;
import com.yeoou.tour.model.Corporation;
import com.yeoou.tour.model.Line;
import com.yeoou.tour.model.ManPrice;
import com.yeoou.tour.model.Manager;
import com.yeoou.tour.model.Region;
import com.yeoou.tour.model.Ship;
import com.yeoou.tour.model.Team;
import com.yeoou.tour.model.TicketLog;
import com.yeoou.tour.model.Train;
import com.yeoou.tour.service.IAirService;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.IAssembleService;
import com.yeoou.tour.service.IBusService;
import com.yeoou.tour.service.ICompanyService;
import com.yeoou.tour.service.ICorporationService;
import com.yeoou.tour.service.IJobberService;
import com.yeoou.tour.service.ILineService;
import com.yeoou.tour.service.IShipService;
import com.yeoou.tour.service.ITeamService;
import com.yeoou.tour.service.ITicketLogService;
import com.yeoou.tour.service.ITrainService;
/**
 * <p>
 * Title: 后台开班计划业务操作模块
 * </p>
 * <p>
 * Description: 对开班计划进行相关业务操作
 * 新增开班计划信息，修改开班计划信息，删除开班计划信息<br/>
 * 浏览所有开班计划列表，搜索指定开班计划列表，模板开班，<br/>
 * 批量上架，下架，推荐，新品，特价
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class TeamAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private ITeamService teamService;
	private IUserService userService;
	private IAreaService areaService;
	private ITicketLogService ticketLogService;
	private ILineService lineService;
	private IAssembleService assembleService;
	private IAirService airService;
	private IBusService busService;
	private IShipService shipService;
	private ITrainService trainService;
	private ICorporationService corporationService;
	private ICompanyService companyService;
	private IJobberService jobberService;
	private String recommend = "";
	private String speical = "";
	private String newproduct = "";
	private String enable = "";
	private String assembleId;
	private String lineId;
	private String manPrice = "";
	private String startDateStr = "";
	private String endDateStr = "";
	private String weeks="";
	private String theme ="";
	private String picUrl = "";
	private String themeColor="";
	private String youhui = "";
	private String startTime = "";
	private int hotelPrice = 0;
	private int teamday = 0;
	private String isNet = "";
	private int flag;
	private String orderConfirm = "";
	private String content = "";
	private String hotelStar = "";
	private int pushDay = 0;
	private String planeBack = "";
	private String planeGo = "";
	private int planeTax = 0;
	private String childPrice = "";
	private String teamName = "";
	private int teamType = 0;
	private String urgentTel = "";
	private int ticketType = 0;
	private String jsonString = "";
	private int total = 0;
	private int busType = 0;
	private String airId;
	private String busId;
	private String shipId;
	private String trainId;
	private String teamId;
	private int limit = 15;
	private int start = 0;
	private String sort;
	private String dir;
	private String userId = "";
	private String isNew = "";
	private String isCom = "";
	private String isJCom = "";
	private int simSearch = 10;
	private String endDate = "";
	private String regionId = "";
	private String isJSpe = "";
	private String isSpe = "";
	private String isJNew = "";
	private String beginDate = "";
	private String delData;
	private String jsonData;
	private int operateType = 0;
	private String jobberName = "";
	private String lineRegionId = "";
	private String doorManPrice = "";
	private String doorChildPrice = "";
	private String assemble = "";
	private String node = "";
	private String comeTraffic;
	private int searchType = 0;
	private String correctStr = "";
	private int dyj = 0;
	
	/**
	 * 获取相关类别的行程列表
	 * @return
	 */
	public String getTeamLine()
	{
	    	////need acegi support userId;
			Manager manager = new Manager();
			manager = this.getManagerInfo();
	    	String bossId = manager.getBossId();
	    	List<Line> lineList = lineService.getLineByRegionId(bossId, lineRegionId);
	    	int length = lineList.size();
	    	StringBuilder sb = new StringBuilder("[");
	    	if(length!=0)
	    	{
	    		if(lineRegionId!=null)
		    	{
		    		for(int i=0;i<length-1;i++)
			    	{
			    		sb.append("[");
						sb.append("\'");
						sb.append(lineList.get(i).getLineId());
						sb.append("\'");
						sb.append(",");
						sb.append("\'");
						sb.append(lineList.get(i).getTitle());
						sb.append("\'");
						sb.append(",");
						sb.append("\'");
						sb.append(lineList.get(i).getSubTitle());
						sb.append("\'");
						sb.append(",");
						sb.append("\'");
						sb.append(lineList.get(i).getWriter());
						sb.append("\'");
						sb.append(",");
						sb.append("\'");
						sb.append(lineList.get(i).getStrDate());
						sb.append("\'");
						sb.append("]");
						sb.append(",");
			    	}
			    	sb.append("[");
					sb.append("\'");
					sb.append(lineList.get(length-1).getLineId());
					sb.append("\'");
					sb.append(",");
					sb.append("\'");
					sb.append(lineList.get(length-1).getTitle());
					sb.append("\'");
					sb.append(",");
					sb.append("\'");
					sb.append(lineList.get(length-1).getSubTitle());
					sb.append("\'");
					sb.append(",");
					sb.append("\'");
					sb.append(lineList.get(length-1).getWriter());
					sb.append("\'");
					sb.append(",");
					sb.append("\'");
					sb.append(lineList.get(length-1).getStrDate());
					sb.append("\'");
					sb.append("]");
					sb.append("]");
					jsonString = sb.toString();
				}
		    	else
		    	{
		    		sb.append("['0','']]");
		    		jsonString = sb.toString();
		    	}
	    	}
	    	else
	    	{
	    		sb.append("['0','无此区域线路']]");
	    		jsonString = sb.toString();
	    	}
	    	return SUCCESS;
	}
	/**
	 * 集合地点列表
	 * @return
	 */
	public String getAssemble()
	{
		//test//need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
    	String bossId = manager.getBossId();
		StringBuilder sb = new StringBuilder();
		String areaId = manager.getAreaId();
		if(areaId!=null)
		{
			List<Assemble> assembleList = assembleService.getAssemble(bossId, areaId);
			int length = assembleList.size();
			sb.append("[");
			for(int i=0;i<length-1;i++)
			{
				sb.append("[");
				sb.append("\'");
				sb.append(assembleList.get(i).getAssembleId());
				sb.append("\'");
				sb.append(",");
				sb.append("\'");
				sb.append(assembleList.get(i).getName());
				sb.append("\'");
				sb.append("]");
				sb.append(",");
			}
			sb.append("[");
			sb.append("\'");
			sb.append(assembleList.get(length-1).getAssembleId());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(assembleList.get(length-1).getName());
			sb.append("\'");
			sb.append("]");
			sb.append("]");
			jsonString = sb.toString();
		}
		else
		{
			sb.append("[['0','']]");
			jsonString = sb.toString();
		}
		return SUCCESS;
	}
	/**
	 * 机票列表
	 * @return
	 */
	public String getAirInf()
	{
		//test//need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
    	String bossId = manager.getBossId();
		List<Air> airList = airService.getAirByUserId(bossId);
		if(airList.size()!=0)
		{
			int length = airList.size();
			StringBuilder sb = new StringBuilder("[");
			for(int i=0;i<length-1;i++)
			{
				sb.append("[");
				sb.append("\'");
				sb.append(airList.get(i).getAirId());
				sb.append("\'");
				sb.append(",");
				sb.append("\'");
				sb.append(airList.get(i).getBeginDateStr()+"-"+airList.get(i).getName());
				sb.append("\'");
				sb.append(",");
				sb.append("\'");
				sb.append(airList.get(i).getTotal());
				sb.append("\'");
				sb.append(",");
				sb.append("\'");
				sb.append(airList.get(i).getConfirm());
				sb.append("\'");
				sb.append(",");
				sb.append("\'");
				sb.append(airList.get(i).getBeginDateStr());
				sb.append("\'");
				sb.append("]");
				sb.append(",");
				
			}
			sb.append("[");
			sb.append("\'");
			sb.append(airList.get(length-1).getAirId());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(airList.get(length-1).getBeginDateStr()+"-"+airList.get(length-1).getName());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(airList.get(length-1).getTotal());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(airList.get(length-1).getConfirm());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(airList.get(length-1).getBeginDateStr());
			sb.append("\'");
			sb.append("]");
			sb.append("]");
			jsonString = sb.toString();
		}
		else
		{
			jsonString = "[]";
		}
		return SUCCESS;
	}
	/**
	 * 车票列表
	 * @return
	 */
	public String getBusInf()
	{
		//test//need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
    	String bossId = manager.getBossId();
		List<Bus> busList= busService.getBusByUserId(bossId);
		int length = busList.size();
		StringBuilder sb = new StringBuilder("[");
		for(int i=0;i<length-1;i++)
		{
			sb.append("[");
			sb.append("\'");
			sb.append(busList.get(i).getBusId());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(busList.get(i).getBeginDateStr()+"-"+busList.get(i).getName());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(busList.get(i).getTotal());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(busList.get(i).getConfirm());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(busList.get(i).getBeginDateStr());
			sb.append("\'");
			sb.append("]");
			sb.append(",");
			
		}
		sb.append("[");
		sb.append("\'");
		sb.append(busList.get(length-1).getBusId());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(busList.get(length-1).getBeginDateStr()+"-"+busList.get(length-1).getName());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(busList.get(length-1).getTotal());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(busList.get(length-1).getConfirm());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(busList.get(length-1).getBeginDateStr());
		sb.append("\'");
		sb.append("]");
		sb.append("]");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 火车票列表
	 * @return
	 */
	public String getTrainInf()
	{
		//TEST//need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
    	String bossId = manager.getBossId();
		List<Train> trainList= trainService.getTrainByUserId(bossId);
		int length = trainList.size();
		StringBuilder sb = new StringBuilder("[");
		for(int i=0;i<length-1;i++)
		{
			sb.append("[");
			sb.append("\'");
			sb.append(trainList.get(i).getTrainId());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(trainList.get(i).getBeginDateStr()+"-"+trainList.get(i).getName());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(trainList.get(i).getTotal());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(trainList.get(i).getConfirm());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(trainList.get(i).getBeginDateStr());
			sb.append("\'");
			sb.append("]");
			sb.append(",");
			
		}
		sb.append("[");
		sb.append("\'");
		sb.append(trainList.get(length-1).getTrainId());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(trainList.get(length-1).getBeginDateStr()+"-"+trainList.get(length-1).getName());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(trainList.get(length-1).getName());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(trainList.get(length-1).getTotal());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(trainList.get(length-1).getConfirm());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(trainList.get(length-1).getBeginDateStr());
		sb.append("\'");
		sb.append("]");
		sb.append("]");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 船票列表
	 * @return
	 */
	public String getShipInf()
	{
		//test//need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
    	String bossId = manager.getBossId();
		List<Ship> shipList = shipService.getShipByUserId(bossId);
		int length = shipList.size();
		StringBuilder sb = new StringBuilder("[");
		for(int i=0;i<length-1;i++)
		{
			sb.append("[");
			sb.append("\'");
			sb.append(shipList.get(i).getShipId());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(shipList.get(i).getBeginDateStr()+"-"+shipList.get(i).getName());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(shipList.get(i).getTotal());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(shipList.get(i).getConfirm());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(shipList.get(i).getBeginDateStr());
			sb.append("\'");
			sb.append("]");
			sb.append(",");
			
		}
		sb.append("[");
		sb.append("\'");
		sb.append(shipList.get(length-1).getShipId());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(shipList.get(length-1).getBeginDateStr()+"-"+shipList.get(length-1).getName());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(shipList.get(length-1).getTotal());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(shipList.get(length-1).getConfirm());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(shipList.get(length-1).getBeginDateStr());
		sb.append("\'");
		sb.append("]");
		sb.append("]");

		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 批发商列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String jobberInfo()
	{
		List<Company> comList = new ArrayList<Company>();
		comList = (List<Company>)companyService.getAll();
		int length = comList.size();
		StringBuilder sb = new StringBuilder("[");
		for(int i=0;i<length-1;i++)
		{
			sb.append("[");
			sb.append("\'");
			sb.append(comList.get(i).getUserId());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(comList.get(i).getName());
			sb.append("\'");
			sb.append("]");
			sb.append(",");
			
		}
		sb.append("[");
		sb.append("\'");
		sb.append(comList.get(length-1).getUserId());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(comList.get(length-1).getName());
		sb.append("\'");
		sb.append("]");
		sb.append("]");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 添加开班计划列表
	 * @return
	 */
	public String addTeam()
	{
//		need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
    	String bossId = manager.getBossId();
		String areaId = manager.getAreaId();
		Line line = lineService.getLine(lineId);
		Corporation corp = this.getCorporationInfoByManager();
		List<Area> areas = new ArrayList<Area>(line.getAreas());
		List<Region> regions = new ArrayList<Region>(line.getRegions());
		String groupId = SerialNoUtils.getSerialNo()+bossId;
		Area area =(Area)areaService.get(areaId.trim());
		User user =(User) userService.get(bossId);
		Date today  = new Date();
		List<ManPrice> manPriceList = new ArrayList<ManPrice>();
		manPriceList = Team.getManPricePackeage(this.manPrice);
		int display = 0;
		int tuyou = 0;
		//设置显示价格
		if(manPriceList.size()!=0)
		{
			for(int k=0;k<manPriceList.size();k++)
			{
				if(manPriceList.get(k).getDisplay())
				{
					tuyou = manPriceList.get(k).getMarketPrice();//兔游价
					display = manPriceList.get(k).getPrice();
				}
			}
		}
		//设置日期规则（如果选择出发日期小于今天则设置为今天）
		Date startDate = DateUtils.parseDate(startDateStr);
		if(startDate.before(today))
		{
			startDateStr = DateUtils.formatDate(today);
		}
		Date endDate = DateUtils.parseDate(this.endDateStr);
		if(endDate.before(today))
		{
			endDateStr = DateUtils.formatDate(today);
		}
		//如果选择日期相同则直接返回错误
		if(startDateStr.equals(endDateStr))
		{
			jsonString = "{success:false}";
			return SUCCESS;
		}
		List<String> dateList = DateUtils.getDateStringByWeeks(startDateStr, endDateStr, weeks);
		int size = dateList.size();
		for(int l=0;l<size;l++)
		{
			Team team = new Team();
			TicketLog ticketLog = new TicketLog();
			team.setDisplayPrice(display);
			team.setDoorDisPrice(tuyou);
			team.setGroupId(groupId);
			team.setArea(area);
			team.setAreas(new HashSet<Area>(areas));
			if(this.speical.equals("1"))
			{
				team.setSpeical("1,0,0");
			}
			else
			{
				team.setSpeical("0,0,0");
			}
			if(theme.equals("无主题"))
			{
				team.setTheme("");
			}
			else
			{
				team.setTheme(theme);
			}			
			if(theme.equals("国庆"))
			{
				team.setPicUrl("resources/images/share/yeoou/guoqing.gif");
			}
			else if(theme.equals("中秋"))
			{
				team.setPicUrl("resources/images/share/yeoou/zhongqiu.gif");
			}
			else if(theme.equals("春节"))
			{
				team.setPicUrl("resources/images/share/yeoou/year.gif");
			}
			else 
			{
				team.setPicUrl("");
			}
			team.setThemeColor(themeColor);
			if(youhui.equals("无优惠措施"))
			{
				team.setYouhui("");
			}
			else
			{
				team.setYouhui(youhui);
			}
			team.setStartTime(startTime);
			team.setHotelPrice(hotelPrice);
			if(this.correctStr.equals("1"))
			{
				team.setCorrect(1);
			}
			else
			{
				team.setCorrect(0);
			}
			if(this.recommend.equals("1"))
			{
				team.setRecommend("1,0,0");
			}
			else
			{
				team.setRecommend("0,0,0");
			}
			team.setRegions(new HashSet<Region>(regions));
			team.setAssemble(assemble);
			team.setTeamday(teamday);
			if(isNet.equals("1"))
			{
				team.setIsNet(1);
			}
			else
			{
				team.setIsNet(0);
			}
			team.setFlag(flag);
			team.setOrderConfirm(orderConfirm);
			team.setDateType(weeks);
			team.setHotelContent(content);
			if(hotelStar.equals("不入住酒店"))hotelStar = "";
			team.setHotelStar(hotelStar);
			team.setPushDay(pushDay);
			team.setLine(line);
			if(newproduct.equals("1"))
			team.setNewproduct("1,0,0");
			else team.setNewproduct("0,0,0");
			team.setPlaneBack(planeBack);
			team.setPlaneGo(planeGo);
			team.setPlaneTax(planeTax);
			team.setManPrice(manPrice);
			team.setChildPrice(childPrice);
			team.setDoorManPrice(manPrice);
			team.setDoorChildPrice(childPrice);
			Date date = DateUtils.parseDate(startDateStr);
			team.setStartDate(date);
			team.setStartDateStr(startDateStr);
			date = DateUtils.parseDate(endDateStr);
			team.setEndDate(date);
			team.setEndDateStr(endDateStr);
			date = DateUtils.parseDate(dateList.get(l));
			team.setRealDate(date);
			team.setRealDateStr(dateList.get(l));
			team.setTeamName(teamName);
			String teamNum = SerialNoUtils.getSerialNo();
			team.setTeamNum(teamNum);
			team.setShadowNum(SerialNoUtils.getSerialNo());
			team.setTeamType(teamType);
			team.setUrgentTel(urgentTel);
			team.setUser(user);
			team.setDyj(dyj);
			team.setTicketType(ticketType);
			team.setComeTraffic(comeTraffic);
			switch(ticketType)
			{
				case 1:
					team.setTicketId(airId);
					team.setGoTraffic("飞机");
					Air air = new Air();
					air =(Air) airService.get(airId);
					ticketLog = ticketLogService.getTicketLogByIdAndDate(airId, dateList.get(l));
					if(ticketLog==null)
					{
						ticketLog = new TicketLog();
						ticketLog.setBook(0);
						ticketLog.setBusType(0);
						ticketLog.setConfirm(0);
						ticketLog.setDate(dateList.get(l));
						ticketLog.setName(air.getName());
						ticketLog.setSeats("");
						ticketLog.setSellNum(0);
						ticketLog.setStatus(Global.SALE_BOOK);
						ticketLog.setTicketId(airId);
						ticketLog.setTicketNo(air.getNumber());
						ticketLog.setTotal(air.getTotal());
						ticketLogService.save(ticketLog);
					}
					team.setTicketLog(ticketLog);
					break;
				case 2:
					team.setTicketId(trainId);
					team.setGoTraffic("火车");
					Train train = new Train();
					train = (Train)trainService.get(trainId);
					ticketLog = ticketLogService.getTicketLogByIdAndDate(trainId, dateList.get(l));
					if(ticketLog==null)
					{
						ticketLog = new TicketLog();
						ticketLog.setBook(0);
						ticketLog.setBusType(0);
						ticketLog.setConfirm(0);
						ticketLog.setDate(dateList.get(l));
						ticketLog.setName(train.getName());
						ticketLog.setSeats("");
						ticketLog.setSellNum(0);
						ticketLog.setStatus(Global.SALE_BOOK);
						ticketLog.setTicketId(trainId);
						ticketLog.setTicketNo(train.getNumber());
						ticketLog.setTotal(train.getTotal());
						ticketLogService.save(ticketLog);
					}
					team.setTicketLog(ticketLog);
					break;
				case 3:
					team.setTicketId(busId);
					team.setGoTraffic("汽车");
					Bus bus = new Bus();
					bus =(Bus) busService.get(busId);
					if(team.getFlag()!=1)
					{
						ticketLog = ticketLogService.getTicketLogByIdAndDate(busId, dateList.get(l));
						if(ticketLog==null)
						{
							ticketLog = new TicketLog();
							ticketLog.setBook(0);
							ticketLog.setBusType(bus.getBusType());
							ticketLog.setConfirm(0);
							ticketLog.setLogNo("");
							ticketLog.setDate(dateList.get(l));
							ticketLog.setName(bus.getName());
							ticketLog.setSellNum(0);
							ticketLog.setStatus(Global.SALE_BOOK);
							ticketLog.setTicketId(busId);
							ticketLog.setTicketNo(bus.getNumber());
							ticketLog.setTotal(bus.getTotal());
							ticketLogService.save(ticketLog);
						}
					}
					else
					{
						ticketLog = new TicketLog();
						ticketLog.setBook(0);
						ticketLog.setBusType(bus.getBusType());
						ticketLog.setConfirm(0);
						ticketLog.setDate(dateList.get(l));
						ticketLog.setName(bus.getName());
						ticketLog.setSellNum(0);
						ticketLog.setStatus(Global.SALE_BOOK);
						ticketLog.setTicketId(busId);
						ticketLog.setLogNo(teamNum);
						ticketLog.setTicketNo(bus.getNumber());
						ticketLog.setTotal(bus.getTotal());
						ticketLogService.save(ticketLog);
						ticketLog = ticketLogService.getTicketLogByLogNo(busId, teamNum);
					}
					team.setTicketLog(ticketLog);
					break;
				default:
					team.setTicketId(shipId);
					team.setGoTraffic("轮船");
					Ship ship = new Ship();
					ship = (Ship)shipService.get(shipId);
					ticketLog = ticketLogService.getTicketLogByIdAndDate(shipId, dateList.get(l));
					if(ticketLog==null)
					{
						ticketLog = new TicketLog();
						ticketLog.setBook(0);
						ticketLog.setBusType(0);
						ticketLog.setConfirm(0);
						ticketLog.setDate(dateList.get(l));
						ticketLog.setName(ship.getName());
						ticketLog.setSeats("");
						ticketLog.setSellNum(0);
						ticketLog.setStatus(Global.SALE_BOOK);
						ticketLog.setTicketId(shipId);
						ticketLog.setTicketNo(ship.getNumber());
						ticketLog.setTotal(ship.getTotal());
						ticketLogService.save(ticketLog);
					}
					team.setTicketLog(ticketLog);
					break;
			}
			team.setJobberName(corp.getName());
			teamService.save(team);
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 更新开班计划（不包括地域和类别）
	 * @return
	 */
	public String updateTeam()
	{
		Team team = new Team();
		team = (Team)teamService.get(teamId);
		team.setAssemble(assemble);
		List<ManPrice> manPriceList = new ArrayList<ManPrice>();
		manPriceList = Team.getManPricePackeage(this.manPrice);
		if(manPriceList.size()!=0)
		{
			for(int k=0;k<manPriceList.size();k++)
			{
				if(manPriceList.get(k).getDisplay())
				{
					team.setDisplayPrice(manPriceList.get(k).getPrice());//一般价
					team.setDoorDisPrice(manPriceList.get(k).getMarketPrice());//兔游价
				}
			}
		}
		String[] temp = team.getRecommend().split(",");
		if(this.recommend.equals("1"))
		{
			temp[0]="1";
			StringBuilder rt = new StringBuilder();
			for(int i=0;i<temp.length-1;i++)
			{
				rt.append(temp[i].trim());
				rt.append(",");
			}
			rt.append(temp[temp.length-1].trim());
			team.setRecommend(rt.toString());
		}
		else 
		{
			temp[0]="0";
			StringBuilder rt = new StringBuilder();
			for(int i=0;i<temp.length-1;i++)
			{
				rt.append(temp[i].trim());
				rt.append(",");
			}
			rt.append(temp[temp.length-1].trim());
			
			team.setRecommend(rt.toString());
		}
		if(isNet.equals("1"))
		{
			team.setIsNet(1);
		}
		else 
		{
			team.setIsNet(0);
		}
		team.setHotelContent(content);
		if(hotelStar.equals("不入住酒店"))hotelStar = "";
		team.setHotelStar(hotelStar);
		String[] temp1 = team.getNewproduct().split(",");
		if(this.correctStr.equals("1"))
		{
			team.setCorrect(1);
		}
		else
		{
			team.setCorrect(0);
		}
		if(newproduct.equals("1"))
		{
			temp1[0]="1";
			StringBuilder rt = new StringBuilder();
			for(int i=0;i<temp1.length-1;i++)
			{
				rt.append(temp1[i].trim());
				rt.append(",");
			}
			rt.append(temp1[temp1.length-1].trim());
			
			team.setNewproduct(rt.toString());
		}
		
		else 
		{
			temp1[0]="0";
			StringBuilder rt = new StringBuilder();
			for(int i=0;i<temp1.length-1;i++)
			{
				rt.append(temp1[i].trim());
				rt.append(",");
			}
			rt.append(temp1[temp1.length-1].trim());
			
			team.setNewproduct(rt.toString());
		}
		String[] temp2 = team.getSpeical().split(",");
		if(speical.equals("1"))
		{
			temp2[0]="1";
			StringBuilder rt = new StringBuilder();
			for(int i=0;i<temp2.length-1;i++)
			{
				rt.append(temp2[i].trim());
				rt.append(",");
			}
			rt.append(temp2[temp2.length-1].trim());
			
			team.setSpeical(rt.toString());
		}
		
		else 
		{
			temp2[0]="0";
			StringBuilder rt = new StringBuilder();
			for(int i=0;i<temp2.length-1;i++)
			{
				rt.append(temp2[i].trim());
				rt.append(",");
			}
			rt.append(temp2[temp2.length-1].trim());
			
			team.setSpeical(rt.toString());
		}
		team.setShadowNum(SerialNoUtils.getSerialNo());
		team.setHotelPrice(hotelPrice);
		team.setStartTime(startTime);
		team.setPlaneBack(planeBack);
		team.setPlaneGo(planeGo);
		team.setPlaneTax(planeTax);
		team.setOrderConfirm(orderConfirm);
		team.setDateType(weeks);
		team.setManPrice(manPrice);
		team.setDyj(dyj);
		team.setChildPrice(childPrice);
		team.setDoorManPrice(manPrice);
		team.setDoorChildPrice(childPrice);
		team.setDoorDisPrice(team.getDisplayPrice());
		team.setTeamName(teamName);
		team.setTeamType(teamType);
		team.setTeamday(teamday);
		team.setJobberName(jobberName );
		if(theme.equals("无主题"))
		{
			team.setTheme("");
		}
		else
		{
			team.setTheme(theme);
		}
		if(theme.equals("国庆"))
		{
			team.setPicUrl("resources/images/share/yeoou/guoqing.gif");
		}
		else if(theme.equals("中秋"))
		{
			team.setPicUrl("resources/images/share/yeoou/zhongqiu.gif");
		}
		else if(theme.equals("春节"))
		{
			team.setPicUrl("resources/images/share/yeoou/year.gif");
		}
		else 
		{
			team.setPicUrl("");
		}
		team.setThemeColor(themeColor);
		if(youhui.equals("无优惠措施"))
		{
			team.setYouhui("");
		}
		else
		{
			team.setYouhui(youhui);
		}
		team.setPushDay(pushDay);
		team.setUrgentTel(urgentTel);
		teamService.merge(team);
		jsonString="{success:true}";
		return SUCCESS;
	}
	/**
	 * @deprecated
	 * 复制批发商开班计划列表
	 * @return
	 */
	public String copyJobberTeam()
	{
//		need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		Team tempTeam = new Team();
		tempTeam = teamService.getTeam(teamId);
		List<Area> areas = new ArrayList<Area>(tempTeam.getAreas());
		List<Region> regions = new ArrayList<Region>(tempTeam.getRegions());
		Line line = new Line();
		line = tempTeam.getLine();
		String bossId = manager.getBossId();
		User user = new User();
		user = (User)userService.get(bossId);
		//设置日期规则
		Date today  = new Date();
		Date startDate = DateUtils.parseDate(startDateStr);
		if(startDate.before(today))
		{
			startDateStr = DateUtils.formatDate(today);
		}
		String groupId = SerialNoUtils.getSerialNo()+bossId;
		List<String> dateList = DateUtils.getDateStringByWeeks(startDateStr, endDateStr, weeks);
		int size = dateList.size();
		List<ManPrice> manPriceList = new ArrayList<ManPrice>();
		manPriceList = Team.getManPricePackeage(this.manPrice);
		int display = 0;
		if(manPriceList.size()!=0)
		{
			for(int k=0;k<manPriceList.size();k++)
			{
				if(manPriceList.get(k).getDisplay())
				{
					display = manPriceList.get(k).getMarketPrice();
				}
			}
		}
		for(int l=0;l<size;l++)
		{
			Team team = new Team();
			team.setArea(tempTeam.getArea());
			team.setGroupId(groupId);
			team.setAreas(new HashSet<Area>(areas));
			team.setDisplayPrice(display);
			if(this.speical.equals("1"))
			{
				team.setSpeical("1,0,0");
			}
			else
			{
				team.setSpeical("0,0,0");
			}
			if(theme.equals("无主题"))
			{
				team.setTheme("");
			}
			else
			{
				team.setTheme(theme);
			}
			if(theme.equals("国庆"))
			{
				team.setPicUrl("resources/images/share/yeoou/guoqing.gif");
			}
			else if(theme.equals("中秋"))
			{
				team.setPicUrl("resources/images/share/yeoou/zhongqiu.gif");
			}
			else if(theme.equals("春节"))
			{
				team.setPicUrl("resources/images/share/yeoou/year.gif");
			}
			else 
			{
				team.setPicUrl("");
			}
			team.setThemeColor(themeColor);
			if(youhui.equals("无优惠措施"))
			{
				team.setYouhui("");
			}
			else
			{
				team.setYouhui(youhui);
			}
			team.setStartTime(startTime);
			team.setPushDay(pushDay);
			team.setHotelPrice(hotelPrice);
			if(this.recommend.equals("1"))
			{
				team.setRecommend("1,0,0");
			}
			else 
			{
				team.setRecommend("0,0,0");
			}
			team.setRegions(new HashSet<Region>(regions));
			team.setAssemble(assemble);
			team.setTeamday(teamday);
			if(isNet.equals("1"))
			{
				team.setIsNet(1);
			}
			else 
			{
				team.setIsNet(0);
			}
			team.setFlag(flag);
			team.setOrderConfirm(orderConfirm);
			team.setDateType(weeks);
			team.setHotelContent(content);
			if(hotelStar.equals("不入住酒店"))hotelStar = "";
			team.setHotelStar(hotelStar);
			team.setLine(line);
			if(newproduct.equals("1"))
			{
				team.setNewproduct("1,0,0");
			}
			else
			{
				team.setNewproduct("0,0,0");
			}
			team.setPlaneBack(planeBack);
			team.setPlaneGo(planeGo);
			team.setPlaneTax(planeTax);
			team.setManPrice(manPrice);
			team.setChildPrice(childPrice);
			team.setDoorManPrice(manPrice);
			team.setDoorChildPrice(childPrice);
			team.setDoorDisPrice(display);
			@SuppressWarnings("unused")
			Date date = DateUtils.parseDate(startDateStr);
			team.setStartDate(date);
			team.setStartDateStr(startDateStr);
			date = DateUtils.parseDate(endDateStr);
			team.setEndDate(date);
			team.setEndDateStr(endDateStr);
			date = DateUtils.parseDate(dateList.get(l));
			team.setRealDate(date);
			team.setRealDateStr(dateList.get(l));
			team.setTeamName(teamName);
			String teamNum = SerialNoUtils.getSerialNo();
			team.setTeamNum(teamNum);
			team.setShadowNum(SerialNoUtils.getSerialNo());
			team.setTeamType(teamType);
			team.setUrgentTel(urgentTel);
			team.setUser(user);
			team.setTicketType(ticketType);
			team.setComeTraffic(comeTraffic);
			team.setDyj(dyj);
			TicketLog ticketLog = new TicketLog();
			switch(ticketType)
			{
				case 1:
					team.setTicketId(airId);
					team.setGoTraffic("飞机");
					Air air = new Air();
					air = (Air)airService.get(airId);
					ticketLog = ticketLogService.getTicketLogByIdAndDate(airId, dateList.get(l));
					if(ticketLog==null)
					{
						ticketLog = new TicketLog();
						ticketLog.setBook(0);
						ticketLog.setBusType(0);
						ticketLog.setConfirm(0);
						ticketLog.setDate(dateList.get(l));
						ticketLog.setName(air.getName());
						ticketLog.setSeats("");
						ticketLog.setSellNum(0);
						ticketLog.setStatus(Global.SALE_BOOK);
						ticketLog.setTicketId(airId);
						ticketLog.setTicketNo(air.getNumber());
						ticketLog.setTotal(air.getTotal());
						ticketLogService.save(ticketLog);
					}
					team.setTicketLog(ticketLog);
					break;
				case 2:
					team.setTicketId(trainId);
					team.setGoTraffic("火车");
					Train train = new Train();
					train =(Train) trainService.get(trainId);
					ticketLog = ticketLogService.getTicketLogByIdAndDate(trainId, dateList.get(l));
					if(ticketLog==null)
					{
						ticketLog = new TicketLog();
						ticketLog.setBook(0);
						ticketLog.setBusType(0);
						ticketLog.setConfirm(0);
						ticketLog.setDate(dateList.get(l));
						ticketLog.setName(train.getName());
						ticketLog.setSeats("");
						ticketLog.setSellNum(0);
						ticketLog.setStatus(Global.SALE_BOOK);
						ticketLog.setTicketId(trainId);
						ticketLog.setTicketNo(train.getNumber());
						ticketLog.setTotal(train.getTotal());
						ticketLogService.save(ticketLog);
					}
					team.setTicketLog(ticketLog);
					break;
				case 3:
					team.setTicketId(busId);
					team.setGoTraffic("汽车");
					Bus bus = new Bus();
					bus = (Bus)busService.get(busId);
					if(team.getFlag()!=1)
					{
						ticketLog = ticketLogService.getTicketLogByIdAndDate(busId, dateList.get(l));
						if(ticketLog==null)
						{
							ticketLog = new TicketLog();
							ticketLog.setBook(0);
							ticketLog.setBusType(bus.getBusType());
							ticketLog.setConfirm(0);
							ticketLog.setLogNo("");
							ticketLog.setDate(dateList.get(l));
							ticketLog.setName(bus.getName());
							ticketLog.setSellNum(0);
							ticketLog.setStatus(Global.SALE_BOOK);
							ticketLog.setTicketId(busId);
							ticketLog.setTicketNo(bus.getNumber());
							ticketLog.setTotal(bus.getTotal());
							ticketLogService.save(ticketLog);
						}
					}
					else
					{
						ticketLog = new TicketLog();
						ticketLog.setBook(0);
						ticketLog.setBusType(bus.getBusType());
						ticketLog.setConfirm(0);
						ticketLog.setDate(dateList.get(l));
						ticketLog.setName(bus.getName());
						ticketLog.setSellNum(0);
						ticketLog.setStatus(Global.SALE_BOOK);
						ticketLog.setTicketId(busId);
						ticketLog.setLogNo(teamNum);
						ticketLog.setTicketNo(bus.getNumber());
						ticketLog.setTotal(bus.getTotal());
						ticketLogService.save(ticketLog);
						ticketLog = ticketLogService.getTicketLogByLogNo(busId, teamNum);
					}
					team.setTicketLog(ticketLog);
					break;
				default:
					team.setTicketId(shipId);
				    team.setGoTraffic("轮船");
					Ship ship = new Ship();
					ship = (Ship)shipService.get(shipId);
					ticketLog = ticketLogService.getTicketLogByIdAndDate(shipId, dateList.get(l));
					if(ticketLog==null)
					{
						ticketLog = new TicketLog();
						ticketLog.setBook(0);
						ticketLog.setBusType(0);
						ticketLog.setConfirm(0);
						ticketLog.setDate(dateList.get(l));
						ticketLog.setName(ship.getName());
						ticketLog.setSeats("");
						ticketLog.setSellNum(0);
						ticketLog.setStatus(Global.SALE_BOOK);
						ticketLog.setTicketId(shipId);
						ticketLog.setTicketNo(ship.getNumber());
						ticketLog.setTotal(ship.getTotal());
						ticketLogService.save(ticketLog);
					}
					team.setTicketLog(ticketLog);
					break;
			}
			team.setJobberName(jobberName);
			teamService.save(team);
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 模板开班计划列表（在开班的同时模板开班也更新为最新的信息）
	 * @return
	 */
	public String templateTeam()
	{
		Team tempTeam = new Team();
		tempTeam = teamService.getTeam(teamId);
		Line line = new Line();
		line = lineService.getLine(lineId);
		List<Area> areas = new ArrayList<Area>(line.getAreas());
		List<Region> regions = new ArrayList<Region>(line.getRegions());
		User user = new User();
		user = tempTeam.getUser();
		//设置日期规则
		Date today  = new Date();
		Date startDate = DateUtils.parseDate(startDateStr);
		if(startDate.before(today))
		{
			startDateStr = DateUtils.formatDate(today);
		}
		Date endDate = DateUtils.parseDate(this.endDateStr);
		if(endDate.before(today))
		{
			endDateStr = DateUtils.formatDate(today);
		}
		if(startDateStr.equals(endDateStr))
		{
			jsonString = "{success:false}";
			return SUCCESS;
		}
		List<String> dateList = DateUtils.getDateStringByWeeks(startDateStr, endDateStr, weeks);
		int size = dateList.size();
		List<ManPrice> manPriceList = new ArrayList<ManPrice>();
		manPriceList = Team.getManPricePackeage(this.manPrice);
		int display = 0;
		int tuyou = 0;
		if(manPriceList.size()!=0)
		{
			for(int k=0;k<manPriceList.size();k++)
			{
				if(manPriceList.get(k).getDisplay())
				{
					tuyou = manPriceList.get(k).getMarketPrice();
					display = manPriceList.get(k).getPrice();
				}
			}
		}
		for(int l=0;l<size;l++)
		{
			Team team = new Team();
			team.setArea(tempTeam.getArea());
			team.setGroupId(tempTeam.getGroupId());
			team.setAreas(new HashSet<Area>(areas));
			team.setDisplayPrice(display);
			if(this.speical.equals("1"))
			{
				team.setSpeical("1,0,0");
			}
			else
			{
				team.setSpeical("0,0,0");
			}
			if(theme.equals("无主题"))
			{
				team.setTheme("");
			}
			else
			{
				team.setTheme(theme);
			}
			if(theme.equals("国庆"))
			{
				team.setPicUrl("resources/images/share/yeoou/guoqing.gif");
			}
			else if(theme.equals("中秋"))
			{
				team.setPicUrl("resources/images/share/yeoou/zhongqiu.gif");
			}
			else if(theme.equals("春节"))
			{
				team.setPicUrl("resources/images/share/yeoou/year.gif");
			}
			else 
			{
				team.setPicUrl("");
			}
			team.setThemeColor(themeColor);
			if(youhui.equals("无优惠措施"))
			{
				team.setYouhui("");
			}
			else
			{
				team.setYouhui(youhui);
			}
			team.setStartTime(startTime);
			team.setPushDay(pushDay);
			team.setHotelPrice(hotelPrice);
			if(this.correctStr.equals("1"))
			{
				team.setCorrect(1);
			}
			else
			{
				team.setCorrect(0);
			}
			if(this.recommend.equals("1"))
			{
				team.setRecommend("1,0,0");
			}
			else 
			{
				team.setRecommend("0,0,0");
			}
			team.setRegions(new HashSet<Region>(regions));
			team.setAssemble(assemble);
			team.setTeamday(teamday);
			if(isNet.equals("1"))
			{
				team.setIsNet(1);
			}
			else 
			{
				team.setIsNet(0);
			}
			team.setFlag(flag);
			team.setOrderConfirm(orderConfirm);
			team.setDateType(weeks);
			team.setHotelContent(content);
			if(hotelStar.equals("不入住酒店"))hotelStar = "";
			team.setHotelStar(hotelStar);
			team.setLine(line);
			if(newproduct.equals("1"))
			{
				team.setNewproduct("1,0,0");
			}
			else
			{
				team.setNewproduct("0,0,0");
			}
			team.setPlaneBack(planeBack);
			team.setPlaneGo(planeGo);
			team.setPlaneTax(planeTax);
			team.setManPrice(manPrice);
			team.setChildPrice(childPrice);
			team.setDoorManPrice(manPrice);
			team.setDoorChildPrice(childPrice);
			team.setDoorDisPrice(tuyou);
			team.setDisplayPrice(display);
			@SuppressWarnings("unused")
			Date date = DateUtils.parseDate(startDateStr);
			team.setStartDate(date);
			team.setStartDateStr(startDateStr);
			date = DateUtils.parseDate(endDateStr);
			team.setEndDate(date);
			team.setEndDateStr(endDateStr);
			date = DateUtils.parseDate(dateList.get(l));
			team.setRealDate(date);
			team.setRealDateStr(dateList.get(l));
			team.setTeamName(teamName);
			String teamNum = SerialNoUtils.getSerialNo();
			team.setTeamNum(teamNum);
			team.setShadowNum(SerialNoUtils.getSerialNo());
			team.setTeamType(teamType);
			team.setUrgentTel(urgentTel);
			team.setUser(user);
			team.setTicketType(ticketType);
			team.setComeTraffic(comeTraffic);
			team.setDyj(dyj);
			TicketLog ticketLog = new TicketLog();
			switch(ticketType)
			{
				case 1:
					team.setTicketId(airId);
					team.setGoTraffic("飞机");
					Air air = new Air();
					air = (Air)airService.get(airId);
					ticketLog = ticketLogService.getTicketLogByIdAndDate(airId, dateList.get(l));
					if(ticketLog==null)
					{
						ticketLog = new TicketLog();
						ticketLog.setBook(0);
						ticketLog.setBusType(0);
						ticketLog.setConfirm(0);
						ticketLog.setDate(dateList.get(l));
						ticketLog.setName(air.getName());
						ticketLog.setSeats("");
						ticketLog.setSellNum(0);
						ticketLog.setStatus(Global.SALE_BOOK);
						ticketLog.setTicketId(airId);
						ticketLog.setTicketNo(air.getNumber());
						ticketLog.setTotal(air.getTotal());
						ticketLogService.save(ticketLog);
					}
					team.setTicketLog(ticketLog);
					break;
				case 2:
					team.setTicketId(trainId);
					team.setGoTraffic("火车");
					Train train = new Train();
					train =(Train) trainService.get(trainId);
					ticketLog = ticketLogService.getTicketLogByIdAndDate(trainId, dateList.get(l));
					if(ticketLog==null)
					{
						ticketLog = new TicketLog();
						ticketLog.setBook(0);
						ticketLog.setBusType(0);
						ticketLog.setConfirm(0);
						ticketLog.setDate(dateList.get(l));
						ticketLog.setName(train.getName());
						ticketLog.setSeats("");
						ticketLog.setSellNum(0);
						ticketLog.setStatus(Global.SALE_BOOK);
						ticketLog.setTicketId(trainId);
						ticketLog.setTicketNo(train.getNumber());
						ticketLog.setTotal(train.getTotal());
						ticketLogService.save(ticketLog);
					}
					team.setTicketLog(ticketLog);
					break;
				case 3:
					team.setTicketId(busId);
					Bus bus = new Bus();
					bus = (Bus)busService.get(busId);
					team.setGoTraffic("汽车");
					if(team.getFlag()!=1)
					{
						ticketLog = ticketLogService.getTicketLogByIdAndDate(busId, dateList.get(l));
						if(ticketLog==null)
						{
							ticketLog = new TicketLog();
							ticketLog.setBook(0);
							ticketLog.setBusType(bus.getBusType());
							ticketLog.setConfirm(0);
							ticketLog.setLogNo("");
							ticketLog.setDate(dateList.get(l));
							ticketLog.setName(bus.getName());
							ticketLog.setSellNum(0);
							ticketLog.setStatus(Global.SALE_BOOK);
							ticketLog.setTicketId(busId);
							ticketLog.setTicketNo(bus.getNumber());
							ticketLog.setTotal(bus.getTotal());
							ticketLogService.save(ticketLog);
						}
					}
					else
					{
						ticketLog = new TicketLog();
						ticketLog.setBook(0);
						ticketLog.setBusType(bus.getBusType());
						ticketLog.setConfirm(0);
						ticketLog.setDate(dateList.get(l));
						ticketLog.setName(bus.getName());
						ticketLog.setSellNum(0);
						ticketLog.setStatus(Global.SALE_BOOK);
						ticketLog.setTicketId(busId);
						ticketLog.setLogNo(teamNum);
						ticketLog.setTicketNo(bus.getNumber());
						ticketLog.setTotal(bus.getTotal());
						ticketLogService.save(ticketLog);
						ticketLog = ticketLogService.getTicketLogByLogNo(busId, teamNum);
					}
					team.setTicketLog(ticketLog);
					break;
				default:
					team.setTicketId(shipId);
				    team.setGoTraffic("轮船");
					Ship ship = new Ship();
					ship = (Ship)shipService.get(shipId);
					ticketLog = ticketLogService.getTicketLogByIdAndDate(shipId, dateList.get(l));
					if(ticketLog==null)
					{
						ticketLog = new TicketLog();
						ticketLog.setBook(0);
						ticketLog.setBusType(0);
						ticketLog.setConfirm(0);
						ticketLog.setDate(dateList.get(l));
						ticketLog.setName(ship.getName());
						ticketLog.setSeats("");
						ticketLog.setSellNum(0);
						ticketLog.setStatus(Global.SALE_BOOK);
						ticketLog.setTicketId(shipId);
						ticketLog.setTicketNo(ship.getNumber());
						ticketLog.setTotal(ship.getTotal());
						ticketLogService.save(ticketLog);
					}
					team.setTicketLog(ticketLog);
					break;
			}
			team.setJobberName(jobberName);
			teamService.save(team);
		}
		//====================================================
		
		tempTeam.setAreas(new HashSet<Area>(areas));
		if(this.speical.equals("1"))
		{
			tempTeam.setSpeical("1,0,0");
		}
		else
		{
			tempTeam.setSpeical("0,0,0");
		}
		if(theme.equals("无主题"))
		{
			tempTeam.setTheme("");
		}
		else
		{
			tempTeam.setTheme(theme);
		}
		if(theme.equals("国庆"))
		{
			tempTeam.setPicUrl("resources/images/share/yeoou/guoqing.gif");
		}
		else if(theme.equals("中秋"))
		{
			tempTeam.setPicUrl("resources/images/share/yeoou/zhongqiu.gif");
		}
		else if(theme.equals("春节"))
		{
			tempTeam.setPicUrl("resources/images/share/yeoou/year.gif");
		}
		else 
		{
			tempTeam.setPicUrl("");
		}
		tempTeam.setThemeColor(themeColor);
		if(youhui.equals("无优惠措施"))
		{
			tempTeam.setYouhui("");
		}
		else
		{
			tempTeam.setYouhui(youhui);
		}
		tempTeam.setStartTime(startTime);
		tempTeam.setPushDay(pushDay);
		tempTeam.setHotelPrice(hotelPrice);
		if(this.recommend.equals("1"))
		{
			tempTeam.setRecommend("1,0,0");
		}
		else 
		{
			tempTeam.setRecommend("0,0,0");
		}
		if(this.correctStr.equals("1"))
		{
			tempTeam.setCorrect(1);
		}
		else
		{
			tempTeam.setCorrect(0);
		}
		tempTeam.setRegions(new HashSet<Region>(regions));
		tempTeam.setAssemble(assemble);
		tempTeam.setTeamday(teamday);
		tempTeam.setFlag(flag);
		tempTeam.setOrderConfirm(orderConfirm);
		tempTeam.setDateType(weeks);
		tempTeam.setHotelContent(content);
		if(hotelStar.equals("不入住酒店"))hotelStar = "";
		tempTeam.setHotelStar(hotelStar);
		tempTeam.setLine(line);
		if(newproduct.equals("1"))
		{
			tempTeam.setNewproduct("1,0,0");
		}
		else
		{
			tempTeam.setNewproduct("0,0,0");
		}
		tempTeam.setPlaneBack(planeBack);
		tempTeam.setPlaneGo(planeGo);
		tempTeam.setPlaneTax(planeTax);
		tempTeam.setManPrice(manPrice);
		tempTeam.setChildPrice(childPrice);
		tempTeam.setDoorManPrice(manPrice);
		tempTeam.setDoorChildPrice(childPrice);
		tempTeam.setDoorDisPrice(display);
		@SuppressWarnings("unused")
		Date date = DateUtils.parseDate(startDateStr);
		tempTeam.setStartDate(date);
		tempTeam.setStartDateStr(startDateStr);
		date = DateUtils.parseDate(endDateStr);
		tempTeam.setEndDate(date);
		tempTeam.setEndDateStr(endDateStr);
		tempTeam.setTeamName(teamName);
		tempTeam.setTeamType(teamType);
		tempTeam.setUrgentTel(urgentTel);
		tempTeam.setUser(user);
		tempTeam.setTicketType(ticketType);
		tempTeam.setComeTraffic(comeTraffic);
		tempTeam.setDyj(dyj);
		tempTeam.setJobberName(jobberName);
		teamService.merge(tempTeam);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * not use in WebSite
	 * 更新开班计划的游客数量
	 * @return
	 */
	public String updateTraffic()
	{
		Team team = new Team();
		team = (Team)teamService.get(teamId);
		TicketLog ticketLog = new TicketLog();
		ticketLog = team.getTicketLog();
		ticketLog.setTotal(total);
		if(team.getTicketType()==3)
		{
			ticketLog.setBusType(busType);
		}
		ticketLogService.merge(ticketLog);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 获取单一的开班计划
	 * @return
	 */
	public String getSingleTeam()
	{
		Team team = (Team)teamService.get(teamId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,");
		sb.append("team:");
		sb.append(jsonTool.getJsonString(team, null, new String[]{"class"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 获取开班计划列表（包含相关搜索功能）
	 * 搜索条件参见{@link ITeamService#getAllTeam(int, int, String, String, String, String, int, String, String, String, String, String, String, String, String, String, boolean, String, String, String)}
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllTeam()
	{
//		need acegi support userId;
		String subTitle = "";
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		Page page = new Page();
		userId = manager.getBossId();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		if(searchType ==0)
		{
			subTitle = "";
		}
		else
		{
			subTitle = teamName;
			teamName = "";
		}
		page = teamService.getAllTeam(start, limit, sort, dir, beginDate, endDate, simSearch, isNet, isNew, isJNew, isSpe, isJSpe, isCom, isJCom, teamName, node, false, userId,jobberName,subTitle);
		if(page!=null)
		{
			List<Team>teamList = (List<Team>)page.getResult();
			int size = teamList.size();
			for(int i=0;i<size;i++)
			{
				List<ManPrice> manPriceList = new ArrayList<ManPrice>();
				if(teamList.get(i).getDoc()!=null)
				{
					String[] docUrl = teamList.get(i).getDoc().getUrls().split("@@@");
					teamList.get(i).setDocUrl(docUrl[0].trim());
				}
				else
				{
					teamList.get(i).setDocUrl("");
				}
				teamList.get(i).setPushDown(DateUtils.isPushDown(teamList.get(i).getRealDate(), teamList.get(i).getPushDay()));
				teamList.get(i).setDateArea(DateUtils.formatDateTime("MM/dd", teamList.get(i).getRealDate())+" "+DateUtils.getWeekByDate(teamList.get(i).getRealDateStr(), Global.shortWeeks));
				manPriceList = Team.getManPricePackeage(teamList.get(i).getManPrice());
				teamList.get(i).setChildPriceList(Team.getChildPricePackeage(teamList.get(i).getChildPrice()));
				teamList.get(i).setManPriceList(manPriceList);
				if(manPriceList.size()!=0)
				{
					for(int k=0;k<manPriceList.size();k++)
					{
						if(manPriceList.get(k).getDisplay())
						{
							teamList.get(i).setDisplayPrice(manPriceList.get(k).getMarketPrice());
						}
					}
				}
				int remain = 0;
        		if(teamList.get(i).getTicketLog().getTotal()-teamList.get(i).getTicketLog().getConfirm()>0)
        		 remain= teamList.get(i).getTicketLog().getTotal()-teamList.get(i).getTicketLog().getConfirm();
        		if(remain==0)
        		{
        			teamList.get(i).setStatus(Global.SALE_OVER);
        		}
        		else
        		{
        			teamList.get(i).setStatus(Global.SALE_BOOK);
        		}
        		if(teamList.get(i).getTicketType()==3)
				{
	        		teamList.get(i).setBusType(teamList.get(i).getTicketLog().getBusType());
				}
        		teamList.get(i).setSellNum(remain);
        		teamList.get(i).setTotal(teamList.get(i).getTicketLog().getTotal());
        		teamList.get(i).setConfirm(teamList.get(i).getTicketLog().getConfirm());
        		teamList.get(i).setBook(teamList.get(i).getTicketLog().getBook());
        		
			}
			StringBuilder sb = new StringBuilder("{success:true,totalCount:");
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(teamList, new String[]{"manPriceList","childPriceList"}, null, null));
			sb.append("}");
			jsonString = sb.toString();
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	/**
	 * @deprecated
	 * 获取所有批发商开班计划包含复制搜索功能
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllJobberTeam()
	{
		Page page = new Page();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		page = teamService.getAllTeam(start, limit, sort, dir, beginDate, endDate, simSearch, isNet, isNew, isJNew, isSpe, isJSpe, isCom, isJCom, teamName, node, true, userId,jobberName,"");
		if(page!=null)
		{
			List<Team>teamList = (List<Team>)page.getResult();
			int size = teamList.size();
			for(int i=0;i<size;i++)
			{
				List<ManPrice> manPriceList = new ArrayList<ManPrice>();
				if(teamList.get(i).getDoc()!=null)
				{
					String[] docUrl = teamList.get(i).getDoc().getUrls().split("@@@");
					teamList.get(i).setDocUrl(docUrl[0].trim());
				}
				else
				{
					teamList.get(i).setDocUrl("");
				}
				teamList.get(i).setPushDown(DateUtils.isPushDown(teamList.get(i).getRealDate(), teamList.get(i).getPushDay()));
				teamList.get(i).setDateArea(DateUtils.formatDateTime("MM/dd", teamList.get(i).getRealDate())+" "+DateUtils.getWeekByDate(teamList.get(i).getRealDateStr(), Global.shortWeeks));
				manPriceList = Team.getManPricePackeage(teamList.get(i).getManPrice());
				teamList.get(i).setChildPriceList(Team.getChildPricePackeage(teamList.get(i).getChildPrice()));
				teamList.get(i).setManPriceList(manPriceList);
				if(manPriceList.size()!=0)
				{
					for(int k=0;k<manPriceList.size();k++)
					{
						if(manPriceList.get(k).getDisplay())
						{
							teamList.get(i).setDisplayPrice(manPriceList.get(k).getMarketPrice());
						}
					}
				}
				int remain = 0;
        		if(teamList.get(i).getTicketLog().getTotal()-teamList.get(i).getTicketLog().getConfirm()>0)
        		 remain= teamList.get(i).getTicketLog().getTotal()-teamList.get(i).getTicketLog().getConfirm();
        		if(remain==0)
        		{
        			teamList.get(i).setStatus(Global.SALE_OVER);
        		}
        		else
        		{
        			teamList.get(i).setStatus(Global.SALE_BOOK);
        		}
        		if(teamList.get(i).getTicketType()==3)
				{
	        		teamList.get(i).setBusType(teamList.get(i).getTicketLog().getBusType());
				}
        		teamList.get(i).setSellNum(remain);
        		teamList.get(i).setTotal(teamList.get(i).getTicketLog().getTotal());
        		teamList.get(i).setConfirm(teamList.get(i).getTicketLog().getConfirm());
        		teamList.get(i).setBook(teamList.get(i).getTicketLog().getBook());
        		Company com = new Company();
        		com = companyService.getCompanyByUserId(teamList.get(i).getUser().getUserId());
        		if(com!=null)
        		{
        			teamList.get(i).setJobberName(com.getName());
        		}
        		else
        		{
        			teamList.get(i).setJobberName("");
        		}
        		
			}
			StringBuilder sb = new StringBuilder("{success:true,totalCount:");
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(teamList, new String[]{"manPriceList","childPriceList"}, null, null));
			sb.append("}");
			jsonString = sb.toString();
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	/**
	 * 删除开班计划线路
	 * @return
	 */
	public String delTeam()
	{
//		删除相应的线路
		if(delData!=null)
		{
			if(this.delData.indexOf(",")<0)
			{
				teamService.removeById(delData.trim());
			}
			else
			{
				String[] id=delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					teamService.removeById(id[i].trim());
				}
			}
			jsonString="{success:true}";
		}
		else
		{
			jsonString="{success:false,msg:'没有选择团队信息'}";
		}
		return SUCCESS;
	}
	/**
	 * 批量更新价格信息
	 * @return
	 */
	public String updatePrice()
	{
		if(jsonData!=null)
		{
			if(jsonData.indexOf(",")<0)
			{
				int display = 0;
				int tuyou = 0;
				List<ManPrice> doorList = new ArrayList<ManPrice>();
				Team team = new Team();
				team = (Team)teamService.get(jsonData.trim());
				team.setDoorManPrice(manPrice);
				team.setDoorChildPrice(childPrice);
				doorList = Team.getManPricePackeage(manPrice);
				int size = doorList.size();
				for(int i=0;i<size;i++)
				{
					if(doorList.get(i).getDisplay())
					{
						tuyou = doorList.get(i).getMarketPrice();
						display = doorList.get(i).getPrice();
					}
				}
				team.setDoorDisPrice(tuyou);
				team.setDisplayPrice(display);
				teamService.merge(team);
				jsonString = "{success:true}";
			}
			else
			{
				String[] id = jsonData.split(",");
				for(int j=0;j<id.length;j++)
				{
					int display = 0;
					int tuyou = 0;
					List<ManPrice> doorList = new ArrayList<ManPrice>();
					Team team = new Team();
					team = (Team)teamService.get(id[j].trim());
					team.setDoorManPrice(manPrice);
					team.setDoorChildPrice(childPrice);
					doorList = Team.getManPricePackeage(manPrice);
					int size = doorList.size();
					for(int i=0;i<size;i++)
					{
						if(doorList.get(i).getDisplay())
						{
							tuyou = doorList.get(i).getMarketPrice();
							display = doorList.get(i).getPrice();
						}
					}
					team.setDoorDisPrice(tuyou);
					team.setDisplayPrice(display);
					teamService.merge(team);
					jsonString = "{success:true}";
				}
			}
		}
		else
		{
			jsonString = "{success:false,msg:'没有选择相应团队'}";
		}
		return SUCCESS;
	}
	/**
	 * 批量操作开班计划（上架，下架，新品，特价，推荐）
	 * @return
	 */
	public String operTeam()
	{
		if(jsonData!=null)
		{
			if(jsonData.indexOf(",")<0)
			{
				Team team = new Team();
				team = (Team)teamService.get(jsonData.trim());
				switch(this.operateType)
				{
					case 1:
						team.setIsNet(1);
						break;
					case 2:
						team.setIsNet(0);
						break;
					case 3:
						String[] temp = team.getSpeical().split(",");
						temp[0]="1";
						StringBuilder rt = new StringBuilder();
						for(int i=0;i<temp.length-1;i++)
						{
							rt.append(temp[i].trim());
							rt.append(",");
						}
						rt.append(temp[temp.length-1].trim());
						team.setSpeical(rt.toString());
						break;
					case 4:
						String[] temp1 = team.getSpeical().split(",");
						temp1[0]="0";
						StringBuilder rt1 = new StringBuilder();
						for(int i=0;i<temp1.length-1;i++)
						{
							rt1.append(temp1[i].trim());
							rt1.append(",");
						}
						rt1.append(temp1[temp1.length-1].trim());
						team.setSpeical(rt1.toString());
						break;
					case 5:
						String[] temp2 = team.getNewproduct().split(",");
						temp2[0] = "1";
						StringBuilder rt2 = new StringBuilder();
						for(int i=0;i<temp2.length-1;i++)
						{
							rt2.append(temp2[i].trim());
							rt2.append(",");
						}
						rt2.append(temp2[temp2.length-1].trim());
						team.setNewproduct(rt2.toString());
						break;
					case 6:
						String[] temp3 = team.getNewproduct().split(",");
						temp3[0] = "0";
						StringBuilder rt3 = new StringBuilder();
						for(int i=0;i<temp3.length-1;i++)
						{
							rt3.append(temp3[i].trim());
							rt3.append(",");
						}
						rt3.append(temp3[temp3.length-1].trim());
						team.setNewproduct(rt3.toString());
						break;
					case 7:
						String[] temp4 = team.getRecommend().split(",");
						temp4[0]="1";
						StringBuilder rt4 = new StringBuilder();
						for(int i=0;i<temp4.length-1;i++)
						{
							rt4.append(temp4[i].trim());
							rt4.append(",");
						}
						rt4.append(temp4[temp4.length-1].trim());
						team.setRecommend(rt4.toString());
						break;
					case 8:
						String[] temp5 = team.getRecommend().split(",");
						temp5[0]="0";
						StringBuilder rt5 = new StringBuilder();
						for(int i=0;i<temp5.length-1;i++)
						{
							rt5.append(temp5[i].trim());
							rt5.append(",");
						}
						rt5.append(temp5[temp5.length-1].trim());
						team.setRecommend(rt5.toString());
						break;
					default:
						break;
				}
				teamService.merge(team);
			}
			else
			{
				String[] id = jsonData.split(",");
				for(int j = 0;j<id.length;j++)
				{
					Team team = new Team();
					team = (Team)teamService.get(id[j].trim());
					switch(this.operateType)
					{
						case 1:
							team.setIsNet(1);
							break;
						case 2:
							team.setIsNet(0);
							break;
						case 3:
							String[] temp = team.getSpeical().split(",");
							temp[0]="1";
							StringBuilder rt = new StringBuilder();
							for(int i=0;i<temp.length-1;i++)
							{
								rt.append(temp[i].trim());
								rt.append(",");
							}
							rt.append(temp[temp.length-1].trim());
							team.setSpeical(rt.toString());
							break;
						case 4:
							String[] temp1 = team.getSpeical().split(",");
							temp1[0]="0";
							StringBuilder rt1 = new StringBuilder();
							for(int i=0;i<temp1.length-1;i++)
							{
								rt1.append(temp1[i].trim());
								rt1.append(",");
							}
							rt1.append(temp1[temp1.length-1].trim());
							team.setSpeical(rt1.toString());
							break;
						case 5:
							String[] temp2 = team.getNewproduct().split(",");
							temp2[0] = "1";
							StringBuilder rt2 = new StringBuilder();
							for(int i=0;i<temp2.length-1;i++)
							{
								rt2.append(temp2[i].trim());
								rt2.append(",");
							}
							rt2.append(temp2[temp2.length-1].trim());
							team.setNewproduct(rt2.toString());
							break;
						case 6:
							String[] temp3 = team.getNewproduct().split(",");
							temp3[0] = "0";
							StringBuilder rt3 = new StringBuilder();
							for(int i=0;i<temp3.length-1;i++)
							{
								rt3.append(temp3[i].trim());
								rt3.append(",");
							}
							rt3.append(temp3[temp3.length-1].trim());
							team.setNewproduct(rt3.toString());
							break;
						case 7:
							String[] temp4 = team.getRecommend().split(",");
							temp4[0]="1";
							StringBuilder rt4 = new StringBuilder();
							for(int i=0;i<temp4.length-1;i++)
							{
								rt4.append(temp4[i].trim());
								rt4.append(",");
							}
							rt4.append(temp4[temp4.length-1].trim());
							team.setRecommend(rt4.toString());
							break;
						case 8:
							String[] temp5 = team.getRecommend().split(",");
							temp5[0]="0";
							StringBuilder rt5 = new StringBuilder();
							for(int i=0;i<temp5.length-1;i++)
							{
								rt5.append(temp5[i].trim());
								rt5.append(",");
							}
							rt5.append(temp5[temp5.length-1].trim());
							team.setRecommend(rt5.toString());
							break;
						default:
							break;
					}
					teamService.merge(team);
				}
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}
	public String getAirId()
	{
		return airId;
	}
	public void setAirId(String airId)
	{
		this.airId = airId;
	}
	public IAirService getAirService()
	{
		return airService;
	}
	public void setAirService(IAirService airService)
	{
		this.airService = airService;
	}
	public IAreaService getAreaService()
	{
		return areaService;
	}
	public void setAreaService(IAreaService areaService)
	{
		this.areaService = areaService;
	}
	public String getAssembleId()
	{
		return assembleId;
	}
	public void setAssembleId(String assembleId)
	{
		this.assembleId = assembleId;
	}
	public IAssembleService getAssembleService()
	{
		return assembleService;
	}
	public void setAssembleService(IAssembleService assembleService)
	{
		this.assembleService = assembleService;
	}
	public String getBeginDate()
	{
		return beginDate;
	}
	public void setBeginDate(String beginDate)
	{
		this.beginDate = beginDate;
	}
	public String getBusId()
	{
		return busId;
	}
	public void setBusId(String busId)
	{
		this.busId = busId;
	}
	public IBusService getBusService()
	{
		return busService;
	}
	public void setBusService(IBusService busService)
	{
		this.busService = busService;
	}
	public int getBusType()
	{
		return busType;
	}
	public void setBusType(int busType)
	{
		this.busType = busType;
	}
	public String getChildPrice()
	{
		return childPrice;
	}
	public void setChildPrice(String childPrice)
	{
		this.childPrice = childPrice;
	}
	public ICompanyService getCompanyService()
	{
		return companyService;
	}
	public void setCompanyService(ICompanyService companyService)
	{
		this.companyService = companyService;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getDelData()
	{
		return delData;
	}
	public void setDelData(String delData)
	{
		this.delData = delData;
	}
	public String getDir()
	{
		return dir;
	}
	public void setDir(String dir)
	{
		this.dir = dir;
	}
	public String getEnable()
	{
		return enable;
	}
	public void setEnable(String enable)
	{
		this.enable = enable;
	}
	public String getEndDate()
	{
		return endDate;
	}
	public void setEndDate(String endDate)
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
	public int getFlag()
	{
		return flag;
	}
	public void setFlag(int flag)
	{
		this.flag = flag;
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
	public String getIsCom()
	{
		return isCom;
	}
	public void setIsCom(String isCom)
	{
		this.isCom = isCom;
	}
	public String getIsJCom()
	{
		return isJCom;
	}
	public void setIsJCom(String isJCom)
	{
		this.isJCom = isJCom;
	}
	public String getIsJNew()
	{
		return isJNew;
	}
	public void setIsJNew(String isJNew)
	{
		this.isJNew = isJNew;
	}
	public String getIsJSpe()
	{
		return isJSpe;
	}
	public void setIsJSpe(String isJSpe)
	{
		this.isJSpe = isJSpe;
	}
	public String getIsNet()
	{
		return isNet;
	}
	public void setIsNet(String isNet)
	{
		this.isNet = isNet;
	}
	public String getIsNew()
	{
		return isNew;
	}
	public void setIsNew(String isNew)
	{
		this.isNew = isNew;
	}
	public String getIsSpe()
	{
		return isSpe;
	}
	public void setIsSpe(String isSpe)
	{
		this.isSpe = isSpe;
	}
	public IJobberService getJobberService()
	{
		return jobberService;
	}
	public void setJobberService(IJobberService jobberService)
	{
		this.jobberService = jobberService;
	}
	public String getJsonData()
	{
		return jsonData;
	}
	public void setJsonData(String jsonData)
	{
		this.jsonData = jsonData;
	}
	public String getJsonString()
	{
		return jsonString;
	}
	public void setJsonString(String jsonString)
	{
		this.jsonString = jsonString;
	}
	public int getLimit()
	{
		return limit;
	}
	public void setLimit(int limit)
	{
		this.limit = limit;
	}
	public String getLineId()
	{
		return lineId;
	}
	public void setLineId(String lineId)
	{
		this.lineId = lineId;
	}
	public ILineService getLineService()
	{
		return lineService;
	}
	public void setLineService(ILineService lineService)
	{
		this.lineService = lineService;
	}
	public String getManPrice()
	{
		return manPrice;
	}
	public void setManPrice(String manPrice)
	{
		this.manPrice = manPrice;
	}
	public String getNewproduct()
	{
		return newproduct;
	}
	public void setNewproduct(String newproduct)
	{
		this.newproduct = newproduct;
	}
	public int getOperateType()
	{
		return operateType;
	}
	public void setOperateType(int operateType)
	{
		this.operateType = operateType;
	}
	public String getOrderConfirm()
	{
		return orderConfirm;
	}
	public void setOrderConfirm(String orderConfirm)
	{
		this.orderConfirm = orderConfirm;
	}
	public String getPicUrl()
	{
		return picUrl;
	}
	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
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
	public int getPushDay()
	{
		return pushDay;
	}
	public void setPushDay(int pushDay)
	{
		this.pushDay = pushDay;
	}
	public String getRecommend()
	{
		return recommend;
	}
	public void setRecommend(String recommend)
	{
		this.recommend = recommend;
	}
	public String getRegionId()
	{
		return regionId;
	}
	public void setRegionId(String regionId)
	{
		this.regionId = regionId;
	}
	public String getShipId()
	{
		return shipId;
	}
	public void setShipId(String shipId)
	{
		this.shipId = shipId;
	}
	public IShipService getShipService()
	{
		return shipService;
	}
	public void setShipService(IShipService shipService)
	{
		this.shipService = shipService;
	}
	public int getSimSearch()
	{
		return simSearch;
	}
	public void setSimSearch(int simSearch)
	{
		this.simSearch = simSearch;
	}
	public String getSort()
	{
		return sort;
	}
	public void setSort(String sort)
	{
		this.sort = sort;
	}
	public String getSpeical()
	{
		return speical;
	}
	public void setSpeical(String speical)
	{
		this.speical = speical;
	}
	public int getStart()
	{
		return start;
	}
	public void setStart(int start)
	{
		this.start = start;
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
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}
	public int getTeamday()
	{
		return teamday;
	}
	public void setTeamday(int teamday)
	{
		this.teamday = teamday;
	}
	public String getTeamId()
	{
		return teamId;
	}
	public void setTeamId(String teamId)
	{
		this.teamId = teamId;
	}
	public String getTeamName()
	{
		return teamName;
	}
	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}
	public ITeamService getTeamService()
	{
		return teamService;
	}
	public void setTeamService(ITeamService teamService)
	{
		this.teamService = teamService;
	}
	public int getTeamType()
	{
		return teamType;
	}
	public void setTeamType(int teamType)
	{
		this.teamType = teamType;
	}
	public String getTheme()
	{
		return theme;
	}
	public void setTheme(String theme)
	{
		this.theme = theme;
	}
	public String getThemeColor()
	{
		return themeColor;
	}
	public void setThemeColor(String themeColor)
	{
		this.themeColor = themeColor;
	}
	public ITicketLogService getTicketLogService()
	{
		return ticketLogService;
	}
	public void setTicketLogService(ITicketLogService ticketLogService)
	{
		this.ticketLogService = ticketLogService;
	}
	public int getTicketType()
	{
		return ticketType;
	}
	public void setTicketType(int ticketType)
	{
		this.ticketType = ticketType;
	}
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public String getTrainId()
	{
		return trainId;
	}
	public void setTrainId(String trainId)
	{
		this.trainId = trainId;
	}
	public ITrainService getTrainService()
	{
		return trainService;
	}
	public void setTrainService(ITrainService trainService)
	{
		this.trainService = trainService;
	}
	public String getUrgentTel()
	{
		return urgentTel;
	}
	public void setUrgentTel(String urgentTel)
	{
		this.urgentTel = urgentTel;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public IUserService getUserService()
	{
		return userService;
	}
	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}
	public String getWeeks()
	{
		return weeks;
	}
	public void setWeeks(String weeks)
	{
		this.weeks = weeks;
	}
	public String getYouhui()
	{
		return youhui;
	}
	public void setYouhui(String youhui)
	{
		this.youhui = youhui;
	}
	public String getJobberName()
	{
		return jobberName;
	}
	public void setJobberName(String jobberName)
	{
		this.jobberName = jobberName;
	}
	public String getDoorChildPrice()
	{
		return doorChildPrice;
	}
	public void setDoorChildPrice(String doorChildPrice)
	{
		this.doorChildPrice = doorChildPrice;
	}
	public String getDoorManPrice()
	{
		return doorManPrice;
	}
	public void setDoorManPrice(String doorManPrice)
	{
		this.doorManPrice = doorManPrice;
	}
	public String getLineRegionId()
	{
		return lineRegionId;
	}
	public void setLineRegionId(String lineRegionId)
	{
		this.lineRegionId = lineRegionId;
	}
	public void setAssemble(String assemble) {
		this.assemble = assemble;
	}
	public ICorporationService getCorporationService()
	{
		return corporationService;
	}
	public void setCorporationService(ICorporationService corporationService)
	{
		this.corporationService = corporationService;
	}
	public String getComeTraffic()
	{
		return comeTraffic;
	}
	public void setComeTraffic(String comeTraffic)
	{
		this.comeTraffic = comeTraffic;
	}
	public String getNode()
	{
		return node;
	}
	public void setNode(String node)
	{
		this.node = node;
	}
	public String getCorrectStr()
	{
		return correctStr;
	}
	public void setCorrectStr(String correctStr)
	{
		this.correctStr = correctStr;
	}
	public int getSearchType()
	{
		return searchType;
	}
	public void setSearchType(int searchType)
	{
		this.searchType = searchType;
	}
	public int getDyj()
	{
		return dyj;
	}
	public void setDyj(int dyj)
	{
		this.dyj = dyj;
	}
}
