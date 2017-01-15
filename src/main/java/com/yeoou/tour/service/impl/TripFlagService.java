package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.TreeNode;
import com.yeoou.tour.model.TripFlag;
import com.yeoou.tour.service.ITripFlagService;

public class TripFlagService extends HibernateEntityDao<TripFlag> implements ITripFlagService
{

	public TripFlag getTripFlagByName(String name)
	{
		// TODO Auto-generated method stub
		String hql = "from TripFlag as tf where tf.name = ?";
		List<TripFlag> tfList = this.find(hql, new Object[]{name});
		if(tfList.size()!=0) return tfList.get(0);
		else return null;
	}

	public Page getAllTripFlag(int start, int limit, String sort, String dir)
	{
		// TODO Auto-generated method stub
		String hql = "from TripFlag as tf order by tf.";
		hql = hql+sort;
		hql = hql+" "+dir;
		return this.PageExtQuery(hql, start, limit, new Object[]{});
	}

	public List<TreeNode> setTripFlagTreeNodes(List<TripFlag> flagList,String flag)
	{
		// TODO Auto-generated method stub
		int length = flagList.size();
		List<TreeNode> treenodes = new ArrayList<TreeNode>();
		for(int i=0;i<length;i++)
		{
			TreeNode treenodeleaf = new TreeNode();
			//-1是叶子
			if(flag.equals("-1"))
			{
				treenodeleaf.setCls("file");
				treenodeleaf.setLeaf(true);
			}
			else
			{
				treenodeleaf.setCls("floder");
				treenodeleaf.setLeaf(false);
			}
			treenodeleaf.setId(flagList.get(i).getId());
			treenodeleaf.setText(flagList.get(i).getTitle());
			treenodes.add(treenodeleaf);
		}
		return treenodes;
	}

}
