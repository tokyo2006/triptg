package com.yeoou.tour.model;

import java.util.Date;

import com.yeoou.rbac.model.User;

public class Account extends Genericmodel {
	private static final long serialVersionUID = 1L;
	private String accountId;
	private String areaId;
	private User user;
	private float accountIn;
	private float accountOut;
	private String remark;
	private Date createTime;
	private String createTimeStr;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public float getAccountIn() {
		return accountIn;
	}
	public void setAccountIn(float accountIn) {
		this.accountIn = accountIn;
	}
	public float getAccountOut() {
		return accountOut;
	}
	public void setAccountOut(float accountOut) {
		this.accountOut = accountOut;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

}
