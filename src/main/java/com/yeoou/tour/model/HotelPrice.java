package com.yeoou.tour.model;

public class HotelPrice extends Genericmodel
{

	private static final long serialVersionUID = 1L;
	private String desc;
	private int price;
	private int num;
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public int getNum()
	{
		return num;
	}
	public void setNum(int num)
	{
		this.num = num;
	}
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
}
