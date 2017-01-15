package com.yeoou.tour.model;

public class OrderCount extends Genericmodel
{
	
	private static final long serialVersionUID = 1L;
	private int newOrder;
	private int affirmed;
	private int compact;
	private int payment;
	private int finish;
	private int disable;
	private int day;
	private int week;
	private int month;
	private int total;
	public int getAffirmed()
	{
		return affirmed;
	}
	public void setAffirmed(int affirmed)
	{
		this.affirmed = affirmed;
	}
	public int getCompact()
	{
		return compact;
	}
	public void setCompact(int compact)
	{
		this.compact = compact;
	}
	public int getDay()
	{
		return day;
	}
	public void setDay(int day)
	{
		this.day = day;
	}
	public int getDisable()
	{
		return disable;
	}
	public void setDisable(int disable)
	{
		this.disable = disable;
	}
	public int getFinish()
	{
		return finish;
	}
	public void setFinish(int finish)
	{
		this.finish = finish;
	}
	public int getMonth()
	{
		return month;
	}
	public void setMonth(int month)
	{
		this.month = month;
	}
	public int getNewOrder()
	{
		return newOrder;
	}
	public void setNewOrder(int newOrder)
	{
		this.newOrder = newOrder;
	}
	public int getPayment()
	{
		return payment;
	}
	public void setPayment(int payment)
	{
		this.payment = payment;
	}
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public int getWeek()
	{
		return week;
	}
	public void setWeek(int week)
	{
		this.week = week;
	}
	
}
