package com.yeoou.tour.model;

import java.util.HashSet;
import java.util.Set;

import com.yeoou.common.model.Genericmodel;

public class Corporation extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String comId;
	private String name;
	private String desc;
	private String logoUrl;
	private String phone;
	private String manager;
	private String address;
	private String email;
	private String qq;
	private String webSite;
	private String msn;
	private String userId;
	private Set<Region> regions = new HashSet<Region>(); 
	private Set<Area> areas = new HashSet<Area>();
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public Set<Area> getAreas()
	{
		return areas;
	}
	public void setAreas(Set<Area> areas)
	{
		this.areas = areas;
	}
	public String getComId()
	{
		return comId;
	}
	public void setComId(String comId)
	{
		this.comId = comId;
	}
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getLogoUrl()
	{
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl)
	{
		this.logoUrl = logoUrl;
	}
	public String getManager()
	{
		return manager;
	}
	public void setManager(String manager)
	{
		this.manager = manager;
	}
	public String getMsn()
	{
		return msn;
	}
	public void setMsn(String msn)
	{
		this.msn = msn;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getQq()
	{
		return qq;
	}
	public void setQq(String qq)
	{
		this.qq = qq;
	}
	public Set<Region> getRegions()
	{
		return regions;
	}
	public void setRegions(Set<Region> regions)
	{
		this.regions = regions;
	}
	public String getWebSite()
	{
		return webSite;
	}
	public void setWebSite(String webSite)
	{
		this.webSite = webSite;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	} 
}
