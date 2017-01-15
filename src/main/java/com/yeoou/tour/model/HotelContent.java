package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

public class HotelContent extends Genericmodel
{
	private String hotelName;
	private String hotelStar;
	private String address;
	private String price;
	private String prMarket;
	private String compact;
	public static  List<HotelContent> getContentPackeage(String content)
	{
		List<HotelContent> contentList = new ArrayList<HotelContent>();
		
		if(!content.equals(""))
		{
			if(content.indexOf("###")<0)
			{
				HotelContent temp = new HotelContent();
				String[] team = content.split("@@@");
				temp.setHotelName(team[0].trim());
				temp.setHotelStar(team[1].trim());
				temp.setAddress(team[2].trim());
				temp.setPrice(team[3].trim());
				temp.setPrMarket(team[4].trim());
				contentList.add(temp);
			}
			else
			{
				String[] con = content.split("###");
				for(int i=0;i<con.length;i++)
				{
					HotelContent temp = new HotelContent();
					String[] team = con[i].split("@@@");
					temp.setHotelName(team[0].trim());
					temp.setHotelStar(team[1].trim());
					temp.setAddress(team[2].trim());
					temp.setPrice(team[3].trim());
					temp.setPrMarket(team[4].trim());
					contentList.add(temp);
				}
			}
		}
		else
		{
			contentList= null;
		}
		return contentList;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getHotelName()
	{
		return hotelName;
	}
	public void setHotelName(String hotelName)
	{
		this.hotelName = hotelName;
	}
	public String getHotelStar()
	{
		return hotelStar;
	}
	public void setHotelStar(String hotelStar)
	{
		this.hotelStar = hotelStar;
	}
	public String getPrice()
	{
		return price;
	}
	public void setPrice(String price)
	{
		this.price = price;
	}
	public String getPrMarket()
	{
		return prMarket;
	}
	public void setPrMarket(String prMarket)
	{
		this.prMarket = prMarket;
	}
	public String getCompact()
	{
		return compact;
	}
	public void setCompact(String compact)
	{
		this.compact = compact;
	}
	
}
