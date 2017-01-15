package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Bus;
import com.yeoou.tour.model.Ship;
import com.yeoou.tour.service.IShipService;

public class ShipService extends HibernateEntityDao<Ship> implements IShipService
{

	public int countTeamByShip(String shipId)
	{
		// TODO Auto-generated method stub
		String hql="select count(*) from Team as team where team.assemble.assembleId = ?";
		Long result = (Long)this.find(hql, new Object[]{shipId}).get(0);
		return result.intValue();
	}

	public Page getAllShip(String userId, String dir, String sort, int limit, int start, String name)
	{
		// TODO Auto-generated method stub
		List<Object> val = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("from Ship as ship where 1=1");
		if(!name.equals(""))
		{
			sb.append(" and ship.name = ?");
			val.add(name);
		}
		if(!userId.equals(""))
		{
			sb.append(" and ship.user.userId = ?");
			val.add(userId);
		}
		sb.append(" order by ship.");
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

	public List<Ship> getShipByUserId(String userId)
	{
		// TODO Auto-generated method stub
		String hql = "from Ship as ship where ship.user.userId = ?";
		List<Ship> shipList = this.find(hql, new Object[]{userId});
		if(shipList.size()!=0) return shipList;
		return null;
	}

}
