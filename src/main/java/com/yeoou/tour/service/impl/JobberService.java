package com.yeoou.tour.service.impl;

import java.io.Serializable;
import java.util.List;
import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.tour.service.IJobberService;
import com.yeoou.tour.model.Jobber;
public class JobberService extends HibernateEntityDao<Jobber> implements IJobberService
{
	public Jobber getJobberByUserId(Serializable userId) 
	{
		String hql="from Jobber as jobb where jobb.userId=?";
		List<Jobber> jobber = this.find(hql, new Object[]{userId});
		if(jobber.size()!=0)
		{
			return jobber.get(0);
		}
		else return null;
	}
	public List<Jobber> getAllJobber()
	{
		String hql = "from Jobber as jobber";
		List<Jobber> jobber = this.find(hql);
		if(jobber.size()!=0)
		{
			return jobber;
		}
		else return null;
	}
}
