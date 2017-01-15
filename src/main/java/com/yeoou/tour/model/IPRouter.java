package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class IPRouter extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String ipId;
	private long startIp;
	private String startIpStr;
	private String endIpStr;
	private long endIp;
	private String areaId;
	private String reUrl;
	private String areaName;
	public String getAreaName()
	{
		return areaName;
	}
	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public long getEndIp()
	{
		return endIp;
	}
	public void setEndIp(long endIp)
	{
		this.endIp = endIp;
	}
	public String getIpId()
	{
		return ipId;
	}
	public void setIpId(String ipId)
	{
		this.ipId = ipId;
	}
	public String getReUrl()
	{
		return reUrl;
	}
	public void setReUrl(String reUrl)
	{
		this.reUrl = reUrl;
	}
	public long getStartIp()
	{
		return startIp;
	}
	public void setStartIp(long startIp)
	{
		this.startIp = startIp;
	}
	public String getEndIpStr()
	{
		return endIpStr;
	}
	public void setEndIpStr(String endIpStr)
	{
		this.endIpStr = endIpStr;
	}
	public String getStartIpStr()
	{
		return startIpStr;
	}
	public void setStartIpStr(String startIpStr)
	{
		this.startIpStr = startIpStr;
	}
}
