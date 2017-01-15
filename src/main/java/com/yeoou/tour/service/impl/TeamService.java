package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.DateUtils;
import com.yeoou.tour.model.Team;
import com.yeoou.tour.service.ITeamService;

public class TeamService extends HibernateEntityDao<Team> implements ITeamService
{

	public Team getTeam(String teamId)
	{
		// TODO Auto-generated method stub
//		String hql = "from Team as team left outer join fetch team.areas left outer join fetch team.regions left outer join fetch team.line.types where team.teamId = ?";
		StringBuilder sb = new StringBuilder();
		sb.append("from Team as team left outer join fetch team.areas left outer join fetch team.regions left outer join fetch team.line.types where team.teamId = ?");
		List<Team> team= this.find(sb.toString(), new Object[]{teamId});
		if(team.size()!=0)return team.get(0);
		else return null;
	}
	public List<Team> getAllClientTeam(int day,int dayFlag,String areaId,String sort,String dir,String regionId,int flag,String keyWord,int dayStart,int dayEnd,int priceStart,int priceEnd,String youhui,int tuijian,int tejia, String sceneryId)
	{
		StringBuilder sb = new StringBuilder();
		int isNet = 1;
		List<Object> val = new ArrayList<Object>();
		if(!regionId.equals("")&&!sceneryId.equals(""))
		{
			sb.append("select distinct team from Team as team left outer join team.regions as region left outer join team.line.scenerys as sc where region.regionId = ? and sc.sceneryId = ? and team.isNet = ?");
			val.add(regionId);
			val.add(sceneryId);
			val.add(isNet);
		}
		else if(!regionId.equals("")&&sceneryId.equals(""))
		{
			sb.append("select distinct team from Team as team left outer join team.regions as region  where region.regionId = ?  and team.isNet = ?");
			val.add(regionId);
			val.add(isNet);
		}
		else if(!sceneryId.equals("")&&regionId.equals(""))
		{
			sb.append("select distinct team from Team as team left outer join team.line.scenerys as sc where sc.sceneryId = ? and team.isNet = ?");
			val.add(sceneryId);
			val.add(isNet);
		}
		else
		{
			sb.append("from Team as team where 1=1 and team.isNet = ?");
			val.add(isNet);
		}
		if(day!=-1)
		{
			if(dayFlag==1)
			{
				sb.append(" and team.teamday >= ?");
				val.add(day);
			}
			else
			{
				sb.append(" and team.teamday = ?");
				val.add(day);
			}
		}
		if(!areaId.equals(""))
		{
			sb.append(" and team.area.areaId = ?");
			val.add(areaId);
		}
		if(flag!=-1)
		{
			sb.append(" and team.flag = ?");
			val.add(flag);
		}
		if(!keyWord.equals(""))
		{
			keyWord = "%"+keyWord+"%";
			sb.append(" and team.teamName like ?");
			val.add(keyWord);
		}
		if(dayStart!=-1&&dayEnd!=-1)
		{
			sb.append(" and team.teamday >= ? and team.teamday <= ?");
			val.add(dayStart);
			val.add(dayEnd);
		}
		if(dayStart==-1&&dayEnd!=-1)
		{
			sb.append(" and team.teamday <= ?");
			val.add(dayEnd);
		}
		if(dayEnd==-1&&dayStart!=-1)
		{
			sb.append(" and team.teamday >= ?");
			val.add(dayStart);
		}
		if(priceStart!=-1&&priceEnd!=-1)
		{
			sb.append(" and team.doorDisPrice >= ? and team.doorDisPrice <= ?");
			val.add(priceStart);
			val.add(priceEnd);
		}
		if(priceStart==-1&&priceEnd!=-1)
		{
			sb.append(" and team.doorDisPrice <= ?");			
			val.add(priceEnd);
		}
		if(priceStart!=-1&&priceEnd==-1)
		{
			sb.append(" and team.doorDisPrice >= ?");
			val.add(priceStart);
		}
		if(!youhui.equals(""))
		{
			sb.append(" and team.youhui = ?");
			val.add(youhui);
		}
		if(tuijian!=-1)
		{
			String command = "1____";
			sb.append(" and team.recommend like ?");
			val.add(command);
		}
		Date today = new Date();
		today = DateUtils.parseDate(DateUtils.formatDate(today));
		sb.append(" and team.endDate > ?");
		val.add(today);
		if(tejia!=-1)
		{
			String special = "1____";
			sb.append(" and team.speical like ?");
			val.add(special);
		}
		sb.append(" group by team.groupId");
		if(sort.equals(""))
		{
			sb.append(" order by team.teamday ASC ,team.doorDisPrice ASC,team.realDate ASC");
		}
		else
		{
			sb.append(" order by team.");
			sb.append(sort);
			sb.append(" ");
			sb.append(dir);
			sb.append(",team.realDate ASC");
		}
		Object[] values = new Object[val.size()];
		for(int i=0;i<val.size();i++)
		{
			values[i] = val.get(i);
		}
		return this.find(sb.toString(), values);
	}
	public Page getAllTeam(int start,int limit,String sort,String dir,String beginDate,String endDate,int simSearch,String isNet,String isNew,String isJNew,String isSpe,String isJSpe,String isCom,String isJCom,String teamName,String regionId,boolean jobberType,String userId,String jobberName,String subTitle)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("");
		List<Object> val = new ArrayList<Object>();
		String newPro = "";
		String special = "";
		String command = "";
		int net = 0;
		if(regionId.equals(""))
		{
			sb.append("from Team as team where 1=1");
		}
		else
		{
			sb.append("select distinct team from Team as team left outer join team.regions as region where region.regionId = ?" );
			val.add(regionId);
			
		}
		if(simSearch==10)
		{
			if(!isNet.equals(""))
			{
				if(isNet.equals("0")) net = 0;
				else net = 1;
				sb.append(" and team.isNet = ?");
				val.add(net);
			}
			if(!isNew.equals(""))
			{
				 sb.append(" and team.newproduct like ?");
				 newPro = "1____";
				 val.add(newPro);
			}
			if(!isJNew.equals(""))
			{
				sb.append(" and team.newproduct like ?");
				newPro = "__1__";
				val.add(newPro);
			}
			if(!isSpe.equals(""))
			{
				special = "1____";
				sb.append(" and team.speical like ?");
				val.add(special);
			}
			if(!isJSpe.equals(""))
			{
				special = "__1__";
				sb.append(" and team.speical like ?");
				val.add(special);
			}
			if(!isCom.equals(""))
			{
				command = "1____";
				sb.append(" and team.recommend like ?");
				val.add(command);
			}
			if(!isJCom.equals(""))
			{
				command = "__1__";
				sb.append(" and team.recommend like ?");
				val.add(command);
			}
		}
		else
		{
			switch(simSearch)
			{
			  case 1:
				  net = 1;
				  sb.append(" and team.isNet = ?");
				  val.add(net);
				  break;
			  case 2:
				  net = 0;
				  sb.append(" and team.isNet = ?");
				  val.add(net);
				  break;
			  case 3:
				  special = "__1__";
				  sb.append(" and team.speical like ?");
				  val.add(special);
				  break;
			  case 4:
				  newPro = "__1__";
				  sb.append(" and team.newproduct like ?");
				  val.add(newPro);
				  break;
			  case 5:
				  command = "__1__";
				  sb.append(" and team.recommend like ?");
				  val.add(command);
				  break;
			  case 6:
				  special = "1____";
				  sb.append(" and team.speical like ?");
				  val.add(special);
				  break;
			  case 7:
				  newPro = "1____";
				  sb.append(" and team.newproduct like ?");
				  val.add(newPro);
				  break;
			  case 8:
				  command = "1____";
				  sb.append(" and team.recommend like ?");
				  val.add(command);
				  break;
			  default: break;
			}
		}
//		if(!jobberName.equals(""))
//		{
//			sb.append(" and team.jobberName = ?");
//			jobberName = "%"+jobberName+"%";
//			val.add(jobberName);
//		}
		if(!teamName.equals(""))
		{
			teamName = "%"+teamName+"%";
			sb.append(" and team.teamName like ?");
			val.add(teamName);
		}
		if(!subTitle.equals(""))
		{
			subTitle = "%"+subTitle+"%";
			sb.append(" and team.line.subTitle like ?");
			val.add(subTitle);
		}
		
			if(!beginDate.equals(""))
			{
				Date bdate = new Date();
				bdate = DateUtils.parseDate(beginDate);
				sb.append(" and team.realDate >= ?");
				val.add(bdate);
			}
			if(!endDate.equals(""))
			{
				Date edate = new Date();
				edate = DateUtils.parseDate(endDate);
				sb.append(" and team.realDate <= ?");
				val.add(edate);
			}
		if(!userId.equals(""))
		{
			sb.append(" and team.user.userId = ?");
			val.add(userId);
		}
		
		if(sort.equals("realDateStr"))
		{
			sort = "realDate";
		}
		sb.append(" order by team.");
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

	public List<Team> getTeamByGroupId(String groupId)
	{
		
		String hql = "from Team as team where team.groupId=? and team.endDate > ?";
		Date today = new Date();
		today = DateUtils.parseDate(DateUtils.formatDate(today));
		return  this.find(hql, new Object[]{groupId,today});
	}

	public List<Team> getContactList(String areaId,int num)
	{
		String hql = "select distinct team from Team as team left outer join team.areas as area where area.areaId = ? and team.isNet = ? group by team.groupId";
		Page page = new Page();
		int isNet = 1;
		page = this.PageQuery(hql, 1, num, new Object[]{areaId,isNet});
		List<Team> teamList = new ArrayList<Team>();
		if(page !=null)
		{
		    teamList = (List<Team>)page.getResult();
		}
		else
		{
			teamList = null;
		}
		return  teamList;
	}
	public int getCountByRegionId(String regionId)
	{
		// TODO Auto-generated method stub
		int isNet = 1;
		Date date = new Date();
		date = DateUtils.parseDate(DateUtils.formatDate(date));
		String hql = "select Count(*) from Team as team left outer join team.regions as regions where regions.regionId = ? and team.isNet = ? and team.endDate > ?";
		Long result = (Long)this.find(hql, new Object[]{regionId,isNet,date}).get(0);
		return result.intValue();
	}
	public List<Team> getTeamByTuijian(int num)
	{
		// TODO Auto-generated method stub
		int isNet = 1;
		String command = "1____";
		List<Team> result = new ArrayList<Team>();
		String hql = "select distinct team from Team as team where team.recommend like ? and team.isNet = ? group by team.groupId order by team.realDate DESC";
		List<Team> teamList = this.find(hql, new Object[]{command,isNet});
		int size = teamList.size();
		if(size==0) return null;
		if(size>5)
		{
			for(int i=0;i<num;i++)
			{
				result.add(teamList.get(i));
			}
		}
		else
		{
			command = "0____";
			for(int i=0;i<size;i++)
			{
				result.add(teamList.get(i));
			}
			hql = "select distinct team  from Team as team where team.recommend like ? and team.isNet = ? group by team.groupId order by team.realDate DESC";
			List<Team> tempList = this.find(hql, new Object[]{command,isNet});
			int j=0;
			while(result.size()!=num)
			{
				if(j>tempList.size())break;
				result.add(tempList.get(j));
				j++;
			}
		}
		return result;
	}
	public List<Team> getTeamByArea(String areaId)
	{
		// TODO Auto-generated method stub
		int isNet = 1;
		String hql = "select distinct team from Team as team left outer join team.areas as areas where areas.areaId = ? and team.isNet = ? group by team.groupId order by team.realDate DESC";
		List<Team> teamList = this.find(hql, new Object[]{areaId,isNet});
		if(teamList.size()!=0) return teamList;
		else return null;
	}
	public List<Team> getTeamByFlag(int flag)
	{
		int isNet = 1;
		String hql = "select distinct team from Team as team  where team.flag = ? and team.isNet = ? group by team.groupId order by team.realDate DESC";
		List<Team> teamList = this.find(hql, new Object[]{flag,isNet});
		if(teamList.size()!=0) return teamList;
		else return null;
	}
	
	public List<Team> getTeamByTejia(int flag,String areaId)
	{
		int isNet = 1;
		String speical = "1____";
		Date today = new Date();
		today = DateUtils.parseDate(DateUtils.formatDate(today));
		String hql = "select distinct team from Team as team  where team.newproduct like ? and team.flag = ? and team.isNet = ?  and team.endDate > ? group by team.groupId order by team.realDate DESC";
		List<Team> teamList = this.find(hql, new Object[]{speical,flag,isNet,today});
		if(teamList.size()!=0) return teamList;
		else return null;
	}
	public List<Team> getTeamListByHit(String groupId) 
	{
		// TODO Auto-generated method stub
		int isNet = 1;
		String hql = "select distinct team from Team as team  where team.isNet = ? and team.groupId = ? group by team.groupId order by team.realDate DESC";
		List<Team> teamList = this.find(hql, new Object[]{isNet,groupId});
		if(teamList.size()!=0) return teamList;
		else return null;
	}
}
