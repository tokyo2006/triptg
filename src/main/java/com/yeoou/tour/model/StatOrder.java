package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

public class StatOrder extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String bookDate;
	private int total;
	private List<Order> orderList = new ArrayList<Order>();
	private String week;
	private float accountReceivable;
	private float accountFact;
	private int effectTotal;
	public String getBookDate() {
		return bookDate;
	}
	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public float getAccountFact()
	{
		return accountFact;
	}
	public void setAccountFact(float accountFact)
	{
		this.accountFact = accountFact;
	}
	public float getAccountReceivable()
	{
		return accountReceivable;
	}
	public void setAccountReceivable(float accountReceivable)
	{
		this.accountReceivable = accountReceivable;
	}
	public int getEffectTotal()
	{
		return effectTotal;
	}
	public void setEffectTotal(int effectTotal)
	{
		this.effectTotal = effectTotal;
	}
}
