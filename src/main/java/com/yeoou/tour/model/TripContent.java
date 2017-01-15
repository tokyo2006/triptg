package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class TripContent extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String subTitle;
	private String picUrl;
	private String url;
	private String price;
	private String target;
	private int font;
	private String desrible;
	private TripModel tripModel;
	private Area area;
	private int shangjia;
	public Area getArea()
	{
		return area;
	}
	public void setArea(Area area)
	{
		this.area = area;
	}
	public String getDesrible()
	{
		return desrible;
	}
	public void setDesrible(String desrible)
	{
		this.desrible = desrible;
	}
	public int getFont()
	{
		return font;
	}
	public void setFont(int font)
	{
		this.font = font;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getPicUrl()
	{
		return picUrl;
	}
	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}
	public String getPrice()
	{
		return price;
	}
	public void setPrice(String price)
	{
		this.price = price;
	}
	public String getSubTitle()
	{
		return subTitle;
	}
	public void setSubTitle(String subTitle)
	{
		this.subTitle = subTitle;
	}
	public String getTarget()
	{
		return target;
	}
	public void setTarget(String target)
	{
		this.target = target;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public TripModel getTripModel()
	{
		return tripModel;
	}
	public void setTripModel(TripModel tripModel)
	{
		this.tripModel = tripModel;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public int getShangjia()
	{
		return shangjia;
	}
	public void setShangjia(int shangjia)
	{
		this.shangjia = shangjia;
	}
}
