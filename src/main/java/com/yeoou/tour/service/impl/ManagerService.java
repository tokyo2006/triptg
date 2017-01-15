package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Manager;
import com.yeoou.tour.service.IManagerService;

public class ManagerService extends HibernateEntityDao<Manager> implements
		IManagerService
{

	public Page getAllManagerByBoss(String bossId, String name,int start, int limit, String sort, String dir)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("from Manager as manager where 1=1 and manager.bossId <> manager.userId");
		List<Object> val = new ArrayList<Object>();
		if(!bossId.equals(""))
		{
			sb.append(" and manager.bossId = ?");
			val.add(bossId);
		}
		if(!name.equals(""))
		{
			name = "%"+name+"%";
			sb.append(" and manager.name like ? ");
			val.add(name);
		}
		sb.append(" order by manager.");
		sb.append(sort);
		sb.append(" ");
		sb.append(dir);
		Object[] values = new Object[val.size()];
		for(int i=0;i<val.size();i++)
		{
			values[i] = val.get(i);
		}
		return this.PageExtQuery(sb.toString(), start, limit, values);
	}


	
}
