package com.yeoou.tour.model;

public class OrderPrice extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String desc;
	private int marketPrice;   //门市价
	private int price;         //同行价
	private int num;		   //人数
	private boolean bed;       //占床位
	private boolean seat;      //占座位
	private boolean ticket;    //含门票
	private boolean eat;       //含用餐
	private boolean pickup;    //加价
	public boolean getBed()
	{
		return bed;
	}
	public void setBed(boolean bed)
	{
		this.bed = bed;
	}
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public boolean getEat()
	{
		return eat;
	}
	public void setEat(boolean eat)
	{
		this.eat = eat;
	}
	public int getMarketPrice()
	{
		return marketPrice;
	}
	public void setMarketPrice(int marketPrice)
	{
		this.marketPrice = marketPrice;
	}
	public int getNum()
	{
		return num;
	}
	public void setNum(int num)
	{
		this.num = num;
	}
	public boolean getPickup()
	{
		return pickup;
	}
	public void setPickup(boolean pickup)
	{
		this.pickup = pickup;
	}
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
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
