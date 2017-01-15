package com.yeoou.tour.service.impl;

import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.tour.model.BakTeam;
import com.yeoou.tour.service.IBakTeamService;

public class BakTeamService extends HibernateEntityDao<BakTeam> implements
		IBakTeamService
{

	public BakTeam getBakTeamByTeamId(String teamId, String shadowNum)
	{
		// TODO Auto-generated method stub
		String hql = "from BakTeam as bakTeam where bakTeam.teamId=? and bakTeam.shadowNum=?";
		List<BakTeam> bakTeam = this.find(hql, new Object[]{teamId,shadowNum});
		if(bakTeam.size()!=0)
		{
			return bakTeam.get(0);
		}
		else return null;
	}

}
