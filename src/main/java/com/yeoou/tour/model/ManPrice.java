package com.yeoou.tour.model;

public class ManPrice extends Genericmodel
{
	
	private static final long serialVersionUID = 1L;
	private String desc;
	private int marketPrice;   //门市价
	private int price;         //同行价
	private int hotelPrice;    //单方差
	private boolean bed;       //占床位
	private boolean seat;      //占座位
	private boolean ticket;    //含门票
	private boolean eat;       //含用餐
	private boolean display;   //显示前台价格
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public int getHotelPrice()
	{
		return hotelPrice;
	}
	public void setHotelPrice(int hotelPrice)
	{
		this.hotelPrice = hotelPrice;
	}
	public int getMarketPrice()
	{
		return marketPrice;
	}
	public void setMarketPrice(int marketPrice)
	{
		this.marketPrice = marketPrice;
	}
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	public boolean getBed()
	{
		return bed;
	}
	public void setBed(boolean bed)
	{
		this.bed = bed;
	}
	public boolean getDisplay()
	{
		return display;
	}
	public void setDisplay(boolean display)
	{
		this.display = display;
	}
	public boolean getEat()
	{
		return eat;
	}
	public void setEat(boolean eat)
	{
		this.eat = eat;
	}
	public boolean getSeat()
	{
		return seat;
	}
	public void setSeat(boolean seat)
	{
		this.seat = seat;
	}
	public boolean getTicket()
	{
		return ticket;
	}
	public void setTicket(boolean ticket)
	{
		this.ticket = ticket;
	}

	
}
