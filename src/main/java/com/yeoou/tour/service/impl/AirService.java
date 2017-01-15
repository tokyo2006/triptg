package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.service.IAirService;
import com.yeoou.tour.model.Air;

public class AirService extends HibernateEntityDao<Air> implements IAirService
{

	public int countTeamByAir(String airId)
	{
		// TODO Auto-generated method stub
		String hql="select count(*) from Team as team where team.assemble.assembleId = ?";
		Long result = (Long)this.find(hql, new Object[]{airId}).get(0);
		return result.intValue();
	}

	public Page getAllAir(String userId, String dir, String sort, int limit, int start, String name)
	{
		// TODO Auto-generated method stub
		List<Object> val = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("from Air as air where 1=1");
		if(!name.equals(""))
		{
			sb.append(" and air.name = ?");
			val.add(name);
		}
		if(!userId.equals(""))
		{
			sb.append(" and air.user.userId = ?");
			val.add(userId);
		}
		sb.append(" order by air.");
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

	public List<Air> getAirByUserId(String userId)
	{
		// TODO Auto-generated method stub
		String hql = "from Air as air where air.user.userId = ?";
		List<Air> airList = this.find(hql, new Object[]{userId});
		if(airList.size()!=0) return airList;
		return null;
	}

}
