package com.yeoou.tour.model;

import java.util.Date;

import com.yeoou.tour.model.Genericmodel;

public class Visa extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String visaId;
	private Area nation;
	private Area city;
	private String name;
	private String interview;
	private String term;
	private String cycle;
	private int pay;
	private String advert;
	private int isTopic;
	private int hit;
	private Date createDate;
	private String createDateStr;
	private String writter;
	private String ziliao;
	public String getZiliao()
	{
		return ziliao;
	}
	public void setZiliao(String ziliao)
	{
		this.ziliao = ziliao;
	}
	public String getAdvert()
	{
		return advert;
	}
	public void setAdvert(String advert)
	{
		this.advert = advert;
	}
	public Area getCity()
	{
		return city;
	}
	public void setCity(Area city)
	{
		this.city = city;
	}
	public Date getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	public String getCreateDateStr()
	{
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr)
	{
		this.createDateStr = createDateStr;
	}
	public String getCycle()
	{
		return cycle;
	}
	public void setCycle(String cycle)
	{
		this.cycle = cycle;
	}
	public int getHit()
	{
		return hit;
	}
	public void setHit(int hit)
	{
		this.hit = hit;
	}
	public String getInterview()
	{
		return interview;
	}
	public void setInterview(String interview)
	{
		this.interview = interview;
	}
	public int getIsTopic()
	{
		return isTopic;
	}
	public void setIsTopic(int isTopic)
	{
		this.isTopic = isTopic;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Area getNation()
	{
		return nation;
	}
	public void setNation(Area nation)
	{
		this.nation = nation;
	}
	public int getPay()
	{
		return pay;
	}
	public void setPay(int pay)
	{
		this.pay = pay;
	}
	public String getTerm()
	{
		return term;
	}
	public void setTerm(String term)
	{
		this.term = term;
	}
	public String getVisaId()
	{
		return visaId;
	}
	public void setVisaId(String visaId)
	{
		this.visaId = visaId;
	}
	public String getWritter()
	{
		return writter;
	}
	public void setWritter(String writter)
	{
		this.writter = writter;
	}
}
