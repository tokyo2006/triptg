package com.yeoou.tour.model;

public class ChildPrice extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String desc;
	private int marketPrice;
	private int price;
	private boolean bed;
	private boolean seat;
	private boolean ticket;
	private boolean eat;
	private boolean pickup;

	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
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
	public boolean getEat()
	{
		return eat;
	}
	public void setEat(boolean eat)
	{
		this.eat = eat;
	}
	public boolean getPickup()
	{
		return pickup;
	}
	public void setPickup(boolean pickup)
	{
		this.pickup = pickup;
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
