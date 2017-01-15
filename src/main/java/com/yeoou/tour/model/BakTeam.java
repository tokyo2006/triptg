package com.yeoou.tour.model;

import java.util.List;

import com.yeoou.rbac.model.User;

public class BakTeam extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String bakTeamId ;
	private String teamId;
	private String dateArea;
	private String shadowNum;
	private String teamNo;
	private String teamName;
	private int flag;
	private int ticketType;
	private String ticketId;
	private int day;
	private TicketLog ticketLog;
	private List<ManPrice> manPriceList;
	private List<ChildPrice> childPriceList;
	private String manPrice;
	private String childPrice;
	private User user;
	private String realDate;
	private String UrgentTel;
	private int hotelPrice;
	private String hotelStar;
	private String theme;
	private int total;
	private int book;
	private int confirm;
	private String picUrl;
	private String planeGo;
	private String planeBack;
	private int planeTax;
	private String hotelContent;
	private String feeClude;
	private String feeUnclude;
	private String remark;
	private String feature;
	private String safe;
	private String selfBuy;
	private String purchase;
	private String content;

	public String getBakTeamId()
	{
		return bakTeamId;
	}
	public void setBakTeamId(String bakTeamId)
	{
		this.bakTeamId = bakTeamId;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public int getDay()
	{
		return day;
	}
	public void setDay(int day)
	{
		this.day = day;
	}
	public String getFeature()
	{
		return feature;
	}
	public void setFeature(String feature)
	{
		this.feature = feature;
	}
	public String getFeeClude()
	{
		return feeClude;
	}
	public void setFeeClude(String feeClude)
	{
		this.feeClude = feeClude;
	}
	public String getFeeUnclude()
	{
		return feeUnclude;
	}
	public void setFeeUnclude(String feeUnclude)
	{
		this.feeUnclude = feeUnclude;
	}
	public String getHotelContent()
	{
		return hotelContent;
	}
	public void setHotelContent(String hotelContent)
	{
		this.hotelContent = hotelContent;
	}

	public String getHotelStar()
	{
		return hotelStar;
	}
	public void setHotelStar(String hotelStar)
	{
		this.hotelStar = hotelStar;
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
	public String getPurchase()
	{
		return purchase;
	}
	public void setPurchase(String purchase)
	{
		this.purchase = purchase;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	public String getSafe()
	{
		return safe;
	}
	public void setSafe(String safe)
	{
		this.safe = safe;
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
	public String getTeamNo()
	{
		return teamNo;
	}
	public void setTeamNo(String teamNo)
	{
		this.teamNo = teamNo;
	}
	public int getTicketType()
	{
		return ticketType;
	}
	public void setTicketType(int ticketType)
	{
		this.ticketType = ticketType;
	}
	public String getUrgentTel()
	{
		return UrgentTel;
	}
	public void setUrgentTel(String urgentTel)
	{
		UrgentTel = urgentTel;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public String getSelfBuy()
	{
		return selfBuy;
	}
	public void setSelfBuy(String selfBuy)
	{
		this.selfBuy = selfBuy;
	}
	public int getHotelPrice()
	{
		return hotelPrice;
	}
	public void setHotelPrice(int hotelPrice)
	{
		this.hotelPrice = hotelPrice;
	}
	public int getPlaneTax()
	{
		return planeTax;
	}
	public void setPlaneTax(int planeTax)
	{
		this.planeTax = planeTax;
	}
	public int getFlag()
	{
		return flag;
	}
	public void setFlag(int flag)
	{
		this.flag = flag;
	}
	public String getTicketId()
	{
		return ticketId;
	}
	public void setTicketId(String ticketId)
	{
		this.ticketId = ticketId;
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
	public String getShadowNum()
	{
		return shadowNum;
	}
	public void setShadowNum(String shadowNum)
	{
		this.shadowNum = shadowNum;
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
	public String getTheme()
	{
		return theme;
	}
	public void setTheme(String theme)
	{
		this.theme = theme;
	}
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public TicketLog getTicketLog()
	{
		return ticketLog;
	}
	public void setTicketLog(TicketLog ticketLog)
	{
		this.ticketLog = ticketLog;
	}
	public String getRealDate()
	{
		return realDate;
	}
	public void setRealDate(String realDate)
	{
		this.realDate = realDate;
	}
	public String getDateArea()
	{
		return dateArea;
	}
	public void setDateArea(String dateArea)
	{
		this.dateArea = dateArea;
	}
	
}
