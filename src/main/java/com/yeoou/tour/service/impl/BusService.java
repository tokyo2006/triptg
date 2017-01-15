package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.service.IBusService;
import com.yeoou.tour.model.Air;
import com.yeoou.tour.model.Bus;

public class BusService extends HibernateEntityDao<Bus> implements IBusService
{

	public int countTeamByBus(String busId)
	{
		String hql="select count(*) from Team as team where team.assemble.assembleId = ?";
		Long result = (Long)this.find(hql, new Object[]{busId}).get(0);
		return result.intValue();
	}

	public Page getAllBus(String userId, String dir, String sort, int limit, int start, String name)
	{
		// TODO Auto-generated method stub
		List<Object> val = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("from Bus as bus where 1=1");
		if(!name.equals(""))
		{
			sb.append(" and bus.name = ?");
			val.add(name);
		}
		if(!userId.equals(""))
		{
			sb.append(" and bus.user.userId = ?");
			val.add(userId);
		}
		sb.append(" order by bus.");
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

	public List<Bus> getBusByUserId(String userId)
	{
		// TODO Auto-generated method stub
		String hql = "from Bus as bus where bus.user.userId = ?";
		List<Bus> busList = this.find(hql, new Object[]{userId});
		if(busList.size()!=0) return busList;
		return null;
	}

}
