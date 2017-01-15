package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.SceneryCount;
import com.yeoou.tour.service.ICountSceneryService;

public class CountSceneryService extends HibernateEntityDao<SceneryCount> implements
		ICountSceneryService
{

	public SceneryCount getSceneryCountBySceneryId(String sceneryId)
	{
		// TODO Auto-generated method stub
		String hql = "from SceneryCount as sc where sc.sceneryId = ?";
		List<SceneryCount> scList = this.find(hql, new Object[]{sceneryId});
		if(scList.size()!=0) return scList.get(0);
		else return null;
	}

	public List<SceneryCount> getSceneryByMonthHit(int pageSize)
	{
		// TODO Auto-generated method stub
		String hql = "from SceneryCount as sc order by sc.monthHit";
		Page page = new Page();
		page = this.PageQuery(hql, 1, pageSize, new Object[]{});
		if(page!=null)
		{
			List<SceneryCount> scList = new ArrayList<SceneryCount>();
			scList = (List<SceneryCount>)page.getResult();
			if(scList.size()==0) return null;
			else return scList;
		}
		else return null;
	}

}
