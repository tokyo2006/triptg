package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class Department extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String siteId;
	private String userId;
	private String manager;
	private String phone;
	private String email;
	private String mobile;
	private String address;
	private String fax;
	private String qq;
	private String msn;
	private String areaId;
	private String fullName;
	private String licenseNo;
	private String tripLicenseNo;
	private String account;
	private Corporation corp;
	private String areaName;
	private String userName;
	public String getAccount()
	{
		return account;
	}
	public void setAccount(String account)
	{
		this.account = account;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public String getAreaName()
	{
		return areaName;
	}
	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}
	
	public Corporation getCorp()
	{
		return corp;
	}
	public void setCorp(Corporation corp)
	{
		this.corp = corp;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getFax()
	{
		return fax;
	}
	public void setFax(String fax)
	{
		this.fax = fax;
	}
	public String getFullName()
	{
		return fullName;
	}
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}
	public String getLicenseNo()
	{
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo)
	{
		this.licenseNo = licenseNo;
	}
	public String getManager()
	{
		return manager;
	}
	public void setManager(String manager)
	{
		this.manager = manager;
	}
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	public String getMsn()
	{
		return msn;
	}
	public void setMsn(String msn)
	{
		this.msn = msn;
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
	public String getSiteId()
	{
		return siteId;
	}
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}
	public String getTripLicenseNo()
	{
		return tripLicenseNo;
	}
	public void setTripLicenseNo(String tripLicenseNo)
	{
		this.tripLicenseNo = tripLicenseNo;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
}
