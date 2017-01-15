package com.yeoou.tour.model;

import java.util.Date;

import com.yeoou.rbac.model.User;


/**
*
* <p>
* 标题：机票实体
* </p>
* <p>
* 描述: 关于机票信息的内容
* </p>
* 
* @author kensin
* @version 1.0
* @created 2008-4-1
*/
public class Air extends Genericmodel
{
	private static final long serialVersionUID = -6786951741643225765L;
	private String airId;
	private String number;
	private String name;
	private String begin;
	private String destination;
	private String begintime;
    private String company;
    private User user;
    private int total;
    private int confirm;
    private int price;
    private int booked;
    private Date beginDate;
    private String beginDateStr;
    private int remain;
    private String status;
	public int getRemain()
	{
		return remain;
	}
	public void setRemain(int remain)
	{
		this.remain = remain;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public String getCompany()
	{
		return company;
	}
	public void setCompany(String company)
	{
		this.company = company;
	}

	public Air()
	{	
	}

	public String getAirId()
	{
		return airId;
	}
	
	public void setAirId(String airId)
	{
		this.airId = airId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getBegin()
	{
		return begin;
	}

	public void setBegin(String begin)
	{
		this.begin = begin;
	}

	public String getDestination()
	{
		return destination;
	}
	
	public void setDestination(String end)
	{
		this.destination = end;
	}
	
	public String getBegintime()
	{
		return begintime;
	}
	
	public void setBegintime(String begintime)
	{
		this.begintime = begintime;
	}
	public Date getBeginDate()
	{
		return beginDate;
	}
	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
	}
	public String getBeginDateStr()
	{
		return beginDateStr;
	}
	public void setBeginDateStr(String beginDateStr)
	{
		this.beginDateStr = beginDateStr;
	}
	public int getBooked()
	{
		return booked;
	}
	public void setBooked(int booked)
	{
		this.booked = booked;
	}
	public int getConfirm()
	{
		return confirm;
	}
	public void setConfirm(int confirm)
	{
		this.confirm = confirm;
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
	public String getNumber()
	{
		return number;
	}
	public void setNumber(String number)
	{
		this.number = number;
	}
}

