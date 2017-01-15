package com.yeoou.tour.model;

public class Ticket
{
	private String name;
	private int total;
	private int book;
	private int confirm;
	private String ticketNo;
	private String status;
	private int busType;
	private int sellNum;

	
	public int getSellNum()
	{
		return sellNum;
	}
	public void setSellNum(int sellNum)
	{
		this.sellNum = sellNum;
	}
	public int getBusType()
	{
		return busType;
	}
	public void setBusType(int busType)
	{
		this.busType = busType;
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
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public String getTicketNo()
	{
		return ticketNo;
	}
	public void setTicketNo(String ticketNo)
	{
		this.ticketNo = ticketNo;
	}
	
}
