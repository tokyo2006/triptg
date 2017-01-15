package com.yeoou.tour.model;

import java.util.Date;

import com.yeoou.common.model.Genericmodel;

public class DoorOrder extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String orderId;
	private int orderNo;
	private String orderUserId;
	private String customName;
	private String customMobile;
	private String customEmail;
	private String customList;
	private String customFax;
	private String customZip;
	private String customAddress;
	private String customRemark;
	private String assemble;
	private int status;
	private String orderOper;
	private String groupId;
	private String teamName;
	private BakTeam bakTeam;
	private int manNum;
	private int childNum;
	private Date startDate;
	private String startDateStr;
	private Date endDate;
	private String endDateStr;
	private Date orderDate;
	private String orderDateStr;
	private String manPriceList;
	private String childrenPriceList;
	private int allCost;
	private int shouldCost;
	private String dyjId;
	private int dyjPrice;
	private String flexId;
	private int isNew;
	public String getFlexId()
	{
		return flexId;
	}
	public void setFlexId(String flexId)
	{
		this.flexId = flexId;
	}
	public int getAllCost()
	{
		return allCost;
	}
	public void setAllCost(int allCost)
	{
		this.allCost = allCost;
	}
	public BakTeam getBakTeam()
	{
		return bakTeam;
	}
	public void setBakTeam(BakTeam bakTeam)
	{
		this.bakTeam = bakTeam;
	}
	public int getChildNum()
	{
		return childNum;
	}
	public void setChildNum(int childNum)
	{
		this.childNum = childNum;
	}
	public String getChildrenPriceList()
	{
		return childrenPriceList;
	}
	public void setChildrenPriceList(String childrenPriceList)
	{
		this.childrenPriceList = childrenPriceList;
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
	public String getCustomFax()
	{
		return customFax;
	}
	public void setCustomFax(String customFax)
	{
		this.customFax = customFax;
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
	public String getCustomList()
	{
		return customList;
	}
	public void setCustomList(String customList)
	{
		this.customList = customList;
	}
	public String getCustomRemark()
	{
		return customRemark;
	}
	public void setCustomRemark(String customRemark)
	{
		this.customRemark = customRemark;
	}
	public String getCustomZip()
	{
		return customZip;
	}
	public void setCustomZip(String customZip)
	{
		this.customZip = customZip;
	}
	public String getDyjId()
	{
		return dyjId;
	}
	public void setDyjId(String dyjId)
	{
		this.dyjId = dyjId;
	}
	public int getDyjPrice()
	{
		return dyjPrice;
	}
	public void setDyjPrice(int dyjPrice)
	{
		this.dyjPrice = dyjPrice;
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
	public String getGroupId()
	{
		return groupId;
	}
	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}
	public int getManNum()
	{
		return manNum;
	}
	public void setManNum(int manNum)
	{
		this.manNum = manNum;
	}
	
	public String getManPriceList()
	{
		return manPriceList;
	}
	public void setManPriceList(String manPriceList)
	{
		this.manPriceList = manPriceList;
	}
	public Date getOrderDate()
	{
		return orderDate;
	}
	public void setOrderDate(Date orderDate)
	{
		this.orderDate = orderDate;
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
	public String getOrderUserId()
	{
		return orderUserId;
	}
	public void setOrderUserId(String orderUserId)
	{
		this.orderUserId = orderUserId;
	}
	public int getShouldCost()
	{
		return shouldCost;
	}
	public void setShouldCost(int shouldCost)
	{
		this.shouldCost = shouldCost;
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
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public String getTeamName()
	{
		return teamName;
	}
	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}
	public int getOrderNo()
	{
		return orderNo;
	}
	public void setOrderNo(int orderNo)
	{
		this.orderNo = orderNo;
	}
	public String getOrderDateStr()
	{
		return orderDateStr;
	}
	public void setOrderDateStr(String orderDateStr)
	{
		this.orderDateStr = orderDateStr;
	}
	public String getAssemble()
	{
		return assemble;
	}
	public void setAssemble(String assemble)
	{
		this.assemble = assemble;
	}
	public int getIsNew()
	{
		return isNew;
	}
	public void setIsNew(int isNew)
	{
		this.isNew = isNew;
	}
}
