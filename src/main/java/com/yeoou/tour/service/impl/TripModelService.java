package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.TreeNode;
import com.yeoou.tour.model.TripModel;
import com.yeoou.tour.service.ITripModelService;

public class TripModelService extends HibernateEntityDao<TripModel> implements ITripModelService
{

	public TripModel getTripModelByName(String name)
	{
		// TODO Auto-generated method stub
		String hql = "from TripModel as tm where tm.name = ?";
		List<TripModel> tmList = this.find(hql, new Object[]{name});
		if(tmList.size()!=0) return tmList.get(0);
		else return null;
	}
	public int getCountByTripFlag(String flagId)
	{
		String hql = "select count(*) from TripModel as tm where tm.tripFlag.id = ?";
		Long result = (Long)this.find(hql, new Object[]{flagId}).get(0);
		return result.intValue();
	}
	public List<TripModel> getTripModelByFlag(String flagId)
	{
		// TODO Auto-generated method stub
		String hql = "from TripModel as tm where tm.tripFlag.id = ?";
		List<TripModel> tmList = this.find(hql, new Object[]{flagId});
		if(tmList.size()!=0)return tmList;
		else return null;
	}
	public Page getAllTripModel(int start, int limit, String sort, String dir,String flagId)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("from TripModel as tm where 1=1");
		List<Object> val = new ArrayList<Object>();
		if(!flagId.equals(""))
		{
			sb.append(" and tm.tripFlag.id = ?");
			val.add(flagId);
		}
		sb.append(" order by tm.");
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
	public List<TreeNode> setTripModelTreeNodes(List<TripModel> tripModelList)
	{
		// TODO Auto-generated method stub
		int length = tripModelList.size();
		List<TreeNode> treenodes = new ArrayList<TreeNode>();
		for(int i=0;i<length;i++)
		{
			TreeNode treenodeleaf = new TreeNode();
			treenodeleaf.setCls("file");
			treenodeleaf.setLeaf(true);
			treenodeleaf.setId(tripModelList.get(i).getClassId());
			treenodeleaf.setText(tripModelList.get(i).getTitle());
			treenodes.add(treenodeleaf);
		}
		return treenodes;
	}
}
