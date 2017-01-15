package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.TeamCount;
import com.yeoou.tour.service.ITeamCountService;

public class TeamCountService extends HibernateEntityDao<TeamCount> implements
		ITeamCountService
{
	public TeamCount getTeamCountByGroupId(String groupId)
	{
		String hql = "from TeamCount as tc where tc.groupId = ?";
		List<TeamCount> tc = this.find(hql, new Object[]{groupId});
		if(tc.size()!=0) return tc.get(0);
		else return null;
	}

	public List<TeamCount> getTeamCountByPage(int pagesize)
	{
		// TODO Auto-generated method stub
		String hql = "from TeamCount as tc order by hit DESC";
		@SuppressWarnings("unused")
		Page page = new Page();
		page = this.PageQuery(hql, 1, pagesize, new Object[]{});
		if(page!=null)
		{
			List<TeamCount> tcList = new ArrayList<TeamCount>();
			tcList = (List<TeamCount>)page.getResult();
			if(tcList.size()==0) return null;
			else return tcList;
		}
		else
		{
			return null;
		}
		
	}
}
