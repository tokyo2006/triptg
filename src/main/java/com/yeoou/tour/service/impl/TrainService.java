package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Bus;
import com.yeoou.tour.model.Train;
import com.yeoou.tour.service.ITrainService;

public class TrainService extends HibernateEntityDao<Train> implements ITrainService
{

	public int countTeamByTrain(String trainId)
	{
		// TODO Auto-generated method stub
		String hql="select count(*) from Team as team where team.assemble.assembleId = ?";
		Long result = (Long)this.find(hql, new Object[]{trainId}).get(0);
		return result.intValue();
	}

	public Page getAllTrain(String userId, String dir, String sort, int limit, int start, String name)
	{
		// TODO Auto-generated method stub
		List<Object> val = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("from Train as train where 1=1");
		if(!name.equals(""))
		{
			sb.append(" and train.name = ?");
			val.add(name);
		}
		if(!userId.equals(""))
		{
			sb.append(" and train.user.userId = ?");
			val.add(userId);
		}
		sb.append(" order by train.");
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

	@SuppressWarnings("unchecked")
	public List<Train> getTrainByUserId(String userId)
	{
		// TODO Auto-generated method stub
		String hql = "from Train as train where train.user.userId = ?";
		List<Train> trainList = this.find(hql, new Object[]{userId});
		if(trainList.size()!=0) return trainList;
		return null;
	}

}
