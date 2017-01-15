package com.yeoou.tour.model;

import java.util.Date;

import com.yeoou.rbac.model.User;

public class Order extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String orderId;
	private int orderNo;
	private Shopper shopper;
	private String areaId;
	private String areaName;
	private User user;
	private String teamId;
	private String teamName;
	private String teamNo;
	private BakTeam bakTeam;
	private String flexId;
	private String customList;
	private int hideCost;
	private int shouldCost;
	private int allCost;
	private int man;
	private int children;
	private String assemble;
	private float hotelNum;
	private String customName;
	private String idCard;
	private String customMobile;
	private String customAddress;
	private String orderRemark;
	private String orderOper;
	private String operJobberName;
	private String hotelContent;
	private int jobberDel;
	private int shopperDel;
	private String driver;
	private String receive;
	private String seats;
	private String zip;
	private int status;
	private Date orderDate;
	private String dateStr;
	private int hideCustom;
	private String assembleTime;
	private int roomAddPrice;
	private int roomAddCnt;
	private String customPhone;
	private String memberName;
	private String linkMan;
	private String fax;
	private String createDateStr;
	private Date createDate;
	private String mobile;
	private String phone;
	private String msn;
	private int shopperStatus;
	private int total;
	private int needcarmans;
	private String orderPriceList;
	private String hotelPriceList;
	private float accountReceivable;
	private float accountFact;
	private int price;
	private String statusStr;
	public String getStatusStr()
	{
		return statusStr;
	}
	public void setStatusStr(String statusStr)
	{
		this.statusStr = statusStr;
	}
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public String getAssembleTime()
	{
		return assembleTime;
	}
	public void setAssembleTime(String assembleTime)
	{
		this.assembleTime = assembleTime;
	}
	public int getAllCost()
	{
		return allCost;
	}
	public void setAllCost(int allCost)
	{
		this.allCost = allCost;
	}
	public String getAssemble()
	{
		return assemble;
	}
	public void setAssemble(String assemble)
	{
		this.assemble = assemble;
	}
	public BakTeam getBakTeam()
	{
		return bakTeam;
	}
	public void setBakTeam(BakTeam bakTeam)
	{
		this.bakTeam = bakTeam;
	}
	public String getCustomList()
	{
		return customList;
	}
	public void setCustomList(String customList)
	{
		this.customList = customList;
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
	public String getDriver()
	{
		return driver;
	}
	public void setDriver(String driver)
	{
		this.driver = driver;
	}
	public int getHideCost()
	{
		return hideCost;
	}
	public void setHideCost(int hideCost)
	{
		this.hideCost = hideCost;
	}

	public String getIdCard()
	{
		return idCard;
	}
	public void setIdCard(String idCard)
	{
		this.idCard = idCard;
	}

	public int getJobberDel()
	{
		return jobberDel;
	}
	public void setJobberDel(int jobberDel)
	{
		this.jobberDel = jobberDel;
	}

	public String getOperJobberName()
	{
		return operJobberName;
	}
	public void setOperJobberName(String operJobberName)
	{
		this.operJobberName = operJobberName;
	}
	public String getOrderId()
	{
		return orderId;
	}
	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}
	public String getOrderOper()
	{
		return orderOper;
	}
	public void setOrderOper(String orderOper)
	{
		this.orderOper = orderOper;
	}
	public String getOrderRemark()
	{
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark)
	{
		this.orderRemark = orderRemark;
	}
	public String getReceive()
	{
		return receive;
	}
	public void setReceive(String receive)
	{
		this.receive = receive;
	}
	public Shopper getShopper()
	{
		return shopper;
	}
	public void setShopper(Shopper shopper)
	{
		this.shopper = shopper;
	}
	public int getShopperDel()
	{
		return shopperDel;
	}
	public void setShopperDel(int shopperDel)
	{
		this.shopperDel = shopperDel;
	}
	public int getShouldCost()
	{
		return shouldCost;
	}
	public void setShouldCost(int shouldCost)
	{
		this.shouldCost = shouldCost;
	}
	public String getTeamName()
	{
		return teamName;
	}
	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}
	public String getTeamNo()
	{
		return teamNo;
	}
	public void setTeamNo(String teamNo)
	{
		this.teamNo = teamNo;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public String getDateStr()
	{
		return dateStr;
	}
	public void setDateStr(String dateStr)
	{
		this.dateStr = dateStr;
	}

	public Date getOrderDate()
	{
		return orderDate;
	}
	public void setOrderDate(Date orderDate)
	{
		this.orderDate = orderDate;
	}
	public String getSeats()
	{
		return seats;
	}
	public void setSeats(String seats)
	{
		this.seats = seats;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public String getTeamId()
	{
		return teamId;
	}
	public void setTeamId(String teamId)
	{
		this.teamId = teamId;
	}
	public String getCustomAddress()
	{
		return customAddress;
	}
	public void setCustomAddress(String customAddress)
	{
		this.customAddress = customAddress;
	}
	public String getZip()
	{
		return zip;
	}
	public void setZip(String zip)
	{
		this.zip = zip;
	}
	public String getCustomPhone()
	{
		return customPhone;
	}
	public void setCustomPhone(String customPhone)
	{
		this.customPhone = customPhone;
	}
	public String getFax()
	{
		return fax;
	}
	public void setFax(String fax)
	{
		this.fax = fax;
	}
	public String getLinkMan()
	{
		return linkMan;
	}
	public void setLinkMan(String linkMan)
	{
		this.linkMan = linkMan;
	}
	public String getMemberName()
	{
		return memberName;
	}
	public void setMemberName(String memberName)
	{
		this.memberName = memberName;
	}
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	public String getMsn()
	{
		return msn;
	}
	public void setMsn(String msn)
	{
		this.msn = msn;
	}
	public int getRoomAddCnt()
	{
		return roomAddCnt;
	}

	public float getHotelNum()
	{
		return hotelNum;
	}
	public void setHotelNum(float hotelNum)
	{
		this.hotelNum = hotelNum;
	}
	public int getRoomAddPrice()
	{
		return roomAddPrice;
	}
	public void setRoomAddCnt(int roomAddCnt)
	{
		this.roomAddCnt = roomAddCnt;
	}
	public void setRoomAddPrice(int roomAddPrice)
	{
		this.roomAddPrice = roomAddPrice;
	}
	public int getHideCustom()
	{
		return hideCustom;
	}
	public void setHideCustom(int hideCustom)
	{
		this.hideCustom = hideCustom;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getHotelContent()
	{
		return hotelContent;
	}
	public void setHotelContent(String hotelContent)
	{
		this.hotelContent = hotelContent;
	}
	public Date getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	public String getCreateDateStr()
	{
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr)
	{
		this.createDateStr = createDateStr;
	}
	public float getAccountFact()
	{
		return accountFact;
	}
	public void setAccountFact(float accountFact)
	{
		this.accountFact = accountFact;
	}
	public float getAccountReceivable()
	{
		return accountReceivable;
	}
	public void setAccountReceivable(float accountReceivable)
	{
		this.accountReceivable = accountReceivable;
	}
	public int getShopperStatus()
	{
		return shopperStatus;
	}
	public void setShopperStatus(int shopperStatus)
	{
		this.shopperStatus = shopperStatus;
	}
	public String getAreaName()
	{
		return areaName;
	}
	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}
	public String getHotelPriceList()
	{
		return hotelPriceList;
	}
	public void setHotelPriceList(String hotelPriceList)
	{
		this.hotelPriceList = hotelPriceList;
	}
	public int getNeedcarmans()
	{
		return needcarmans;
	}
	public void setNeedcarmans(int needcarmans)
	{
		this.needcarmans = needcarmans;
	}
	public String getOrderPriceList()
	{
		return orderPriceList;
	}
	public void setOrderPriceList(String orderPriceList)
	{
		this.orderPriceList = orderPriceList;
	}
	public int getChildren()
	{
		return children;
	}
	public void setChildren(int children)
	{
		this.children = children;
	}
	public int getMan()
	{
		return man;
	}
	public void setMan(int man)
	{
		this.man = man;
	}
	public int getOrderNo()
	{
		return orderNo;
	}
	public void setOrderNo(int orderNo)
	{
		this.orderNo = orderNo;
	}
	public String getFlexId()
	{
		return flexId;
	}
	public void setFlexId(String flexId)
	{
		this.flexId = flexId;
	}
	
}
