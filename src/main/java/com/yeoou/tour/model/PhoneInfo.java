package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class PhoneInfo extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String remark;
	private String phone;
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
}
