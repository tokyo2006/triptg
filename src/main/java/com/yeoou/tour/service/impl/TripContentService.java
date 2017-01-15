package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.TripContent;
import com.yeoou.tour.service.ITripContentService;

public class TripContentService extends HibernateEntityDao<TripContent> implements
		ITripContentService
{
	public int getCountByTripModel(String tripModelId)
	{
		String hql = "select count(*) from TripContent as tc where tc.tripModel.classId = ?";
		Long result = (Long)this.find(hql, new Object[]{tripModelId}).get(0);
		return result.intValue();
	}

	public Page getAllTripContent(int start, int limit, String sort, String dir,String classId)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		List<Object> val = new ArrayList<Object>();
		sb.append("from TripContent as tc where 1=1");
		if(!classId.equals(""))
		{
			sb.append(" and tc.tripModel.classId = ?");
			val.add(classId);
		}
		sb.append(" order by tc.");
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

	public List<TripContent> getTripContentList(String areaId, String name)
	{
		// TODO Auto-generated method stub
		String hql = "";
		int shangjia = 1;
		if(!areaId.equals(""))
		{
			hql = "from TripContent as tc where tc.tripModel.name = ? and tc.area.areaId = ? and tc.shangjia = ?";
			return this.find(hql, new Object[]{name,areaId,shangjia});
		}
		else
		{
			hql = "from TripContent as tc where tc.tripModel.name = ? and tc.shangjia = ?";
			return this.find(hql, new Object[]{name,shangjia});
		}
		
	}
	
}
