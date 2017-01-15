package com.yeoou.tour.model;

public class Stat extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String month;
	private int orderNum;
	private int customNum;
	private int shopperPrice ;
	private int jobberPrice ;
	private int price;
	public int getCustomNum()
	{
		return customNum;
	}
	public void setCustomNum(int customNum)
	{
		this.customNum = customNum;
	}
	public int getJobberPrice()
	{
		return jobberPrice;
	}
	public void setJobberPrice(int jobberPrice)
	{
		this.jobberPrice = jobberPrice;
	}
	public String getMonth()
	{
		return month;
	}
	public void setMonth(String month)
	{
		this.month = month;
	}
	public int getOrderNum()
	{
		return orderNum;
	}
	public void setOrderNum(int orderNum)
	{
		this.orderNum = orderNum;
	}
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	public int getShopperPrice()
	{
		return shopperPrice;
	}
	public void setShopperPrice(int shopperPrice)
	{
		this.shopperPrice = shopperPrice;
	}
}
