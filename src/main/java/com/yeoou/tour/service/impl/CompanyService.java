package com.yeoou.tour.service.impl;

import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.tour.service.ICompanyService;
import com.yeoou.tour.model.Company;

public class CompanyService extends HibernateEntityDao<Company> implements
		ICompanyService
{
	public Company getCompanyByUserId(String userId)
	{
		String hql="from Company as com where com.userId=?";
		List<Company> com = this.find(hql, new Object[]{userId});
		if(com.size()!=0)
		{
			return com.get(0);
		}
		else return null;
	}
}
