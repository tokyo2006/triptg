package com.yeoou.tour.service.impl;

import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.SceneryType;
import com.yeoou.tour.service.ISceneryTypeService;

public class SceneryTypeService extends HibernateEntityDao<SceneryType> implements
		ISceneryTypeService
{

	public Page getAllSceneryType(int start, int limit, String dir, String sort)
	{
		// TODO Auto-generated method stub
		String hql = "from SceneryType as sc order by sc.";
		hql = hql+sort+" "+dir;
		return this.PageExtQuery(hql, start, limit, new Object[]{});
	}

	public List<SceneryType> getSceneryTypeList(int flag)
	{
		// TODO Auto-generated method stub
		String hql = "from SceneryType as sc where sc.flag = ?";
		List<SceneryType> scList = this.find(hql, new Object[]{flag});
		if(scList.size()!=0) return scList;
		else return null;
	}

}
