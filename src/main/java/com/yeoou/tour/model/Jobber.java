package com.yeoou.tour.model;

public class Jobber extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String jobberId;
	private String userId;
	private String name;
	private String idCard;
	private String mobile;
	private String phone;
	private String qq;
	private String msn;
	private String email;
	private String areaId;
	private String areaName;
	private String bossId;

	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
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
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getBossId() {
		return bossId;
	}
	public void setBossId(String bossId) {
		this.bossId = bossId;
	}
	public String getJobberId() {
		return jobberId;
	}
	public void setJobberId(String jobberId) {
		this.jobberId = jobberId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getIdCard()
	{
		return idCard;
	}
	public void setIdCard(String idCard)
	{
		this.idCard = idCard;
	}
	public String getAreaName()
	{
		return areaName;
	}
	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}


}
