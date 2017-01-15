package com.yeoou.tour.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * discription: 所有模型的父类
 * @author kensin
 *
 */
public class Genericmodel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8077335328262653627L;

	public Genericmodel()
	{
	}
	public static List<ManPrice> getManPricePackeage(String manPrice)
	{
		List<ManPrice> manPriceList = new ArrayList<ManPrice>();
		if(!manPrice.equals(""))
		{
			if(manPrice.indexOf("###")<0)
			{
				ManPrice temp = new ManPrice();
				String[] item = manPrice.split("@@@");
				temp.setDesc(item[0].trim());
				temp.setMarketPrice(Integer.valueOf(item[1].trim()));
				temp.setPrice(Integer.valueOf(item[2].trim()));
				temp.setHotelPrice(Integer.valueOf(item[3].trim()));
				temp.setBed(Boolean.valueOf(item[4].trim()));
				temp.setSeat(Boolean.valueOf(item[5].trim()));
				temp.setTicket(Boolean.valueOf(item[6].trim()));
				temp.setEat(Boolean.valueOf(item[7].trim()));
				temp.setDisplay(Boolean.valueOf(item[8].trim()));
				manPriceList.add(temp);
			}
			else
			{
				String[] con = manPrice.split("###");
				for(int i=0;i<con.length;i++)
				{
					ManPrice temp = new ManPrice();
					String[] item =con[i].split("@@@");
					temp.setDesc(item[0].trim());
					temp.setMarketPrice(Integer.valueOf(item[1].trim()));
					temp.setPrice(Integer.valueOf(item[2].trim()));
					temp.setHotelPrice(Integer.valueOf(item[3].trim()));
					temp.setBed(Boolean.valueOf(item[4].trim()));
					temp.setSeat(Boolean.valueOf(item[5].trim()));
					temp.setTicket(Boolean.valueOf(item[6].trim()));
					temp.setEat(Boolean.valueOf(item[7].trim()));
					temp.setDisplay(Boolean.valueOf(item[8].trim()));
					manPriceList.add(temp);
				}
			}
		}
		return manPriceList;
	}
	public static List<OrderPrice> getOrderPriceList(String orderPrice)
	{
		List<OrderPrice> orderPriceList = new ArrayList<OrderPrice>();
		if(!orderPrice.equals(""))
		{
			if(orderPrice.indexOf("@@@")<0)
			{
				OrderPrice temp = new OrderPrice();
				String[] item = orderPrice.split("###");
				temp.setDesc(item[0].trim());
				temp.setMarketPrice(Integer.valueOf(item[1].trim()));
				temp.setPrice(Integer.valueOf(item[2].trim()));
				temp.setNum(Integer.valueOf(item[3].trim()));
				temp.setTicket(Boolean.valueOf(item[4].trim()));
				temp.setEat(Boolean.valueOf(item[5].trim()));
				temp.setSeat(Boolean.valueOf(item[6].trim()));
				temp.setBed(Boolean.valueOf(item[7].trim()));
				temp.setPickup(Boolean.valueOf(item[8].trim()));
				orderPriceList.add(temp);
			}
			else
			{
				String[] con = orderPrice.split("@@@");
				for(int i=0;i<con.length;i++)
				{
					OrderPrice temp = new OrderPrice();
					String[] item = con[i].split("###");
					temp.setDesc(item[0].trim());
					temp.setMarketPrice(Integer.valueOf(item[1].trim()));
					temp.setPrice(Integer.valueOf(item[2].trim()));
					temp.setNum(Integer.valueOf(item[3].trim()));
					temp.setTicket(Boolean.valueOf(item[4]));
					temp.setEat(Boolean.valueOf(item[5]));
					temp.setSeat(Boolean.valueOf(item[6]));
					temp.setBed(Boolean.valueOf(item[7]));
					temp.setPickup(Boolean.valueOf(item[8]));
					orderPriceList.add(temp);
				}
			}
			
		}
		else return new ArrayList<OrderPrice>();
		return orderPriceList;
	}
	public static List<HotelPrice> getHotelPricePackeage(String hotelPrice)
	{
		List<HotelPrice> hotelPriceList = new ArrayList<HotelPrice>();
		if(!hotelPrice.equals(""))
		{
			if(hotelPrice.indexOf("@@@")<0)
			{
				HotelPrice temp = new HotelPrice();
				String[] item = hotelPrice.split("###");
				temp.setDesc(item[0].trim());
				temp.setPrice(Integer.valueOf(item[1].trim()));
				temp.setNum(Integer.valueOf(item[2].trim()));
				hotelPriceList.add(temp);
			}
			else
			{
				String[] con = hotelPrice.split("@@@");
				for(int i=0;i<con.length;i++)
				{
					HotelPrice temp = new HotelPrice();
					String[] item = con[i].split("###");
					temp.setDesc(item[0].trim());
					temp.setPrice(Integer.valueOf(item[1].trim()));
					temp.setNum(Integer.valueOf(item[2].trim()));
					hotelPriceList.add(temp);
				}
			}
		}
		else return  new ArrayList<HotelPrice>();
		return hotelPriceList;
	}
	public static List<ChildPrice> getChildPricePackeage(String childPrice)
	{
		List<ChildPrice> childPriceList = new ArrayList<ChildPrice>();
		if(!childPrice.equals(""))
		{
			if(childPrice.indexOf("###")<0)
			{
				ChildPrice temp = new ChildPrice();
				String[] item = childPrice.split("@@@");
				temp.setDesc(item[0].trim());
				temp.setMarketPrice(Integer.valueOf(item[1].trim()));
				temp.setPrice(Integer.valueOf(item[2].trim()));
				temp.setSeat(Boolean.valueOf(item[3].trim()));
				temp.setBed(Boolean.valueOf(item[4].trim()));
				temp.setTicket(Boolean.valueOf(item[5].trim()));
				temp.setEat(Boolean.valueOf(item[6].trim()));
				temp.setPickup(Boolean.valueOf(item[7].trim()));
				childPriceList.add(temp);
			}
			else
			{
				String[] con = childPrice.split("###");
				for(int i=0;i<con.length;i++)
				{
					ChildPrice temp = new ChildPrice();
					String[] item = con[i].split("@@@");
					temp.setDesc(item[0].trim());
					temp.setMarketPrice(Integer.valueOf(item[1].trim()));
					temp.setPrice(Integer.valueOf(item[2].trim()));
					temp.setSeat(Boolean.valueOf(item[3].trim()));
					temp.setBed(Boolean.valueOf(item[4].trim()));
					temp.setTicket(Boolean.valueOf(item[5].trim()));
					temp.setEat(Boolean.valueOf(item[6].trim()));
					temp.setPickup(Boolean.valueOf(item[7].trim()));
					childPriceList.add(temp);
				}
			}
		}
		else return new ArrayList<ChildPrice>();
		return childPriceList;
	}
   
}
