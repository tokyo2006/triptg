package com.yeoou.rbac.service.impl;



import java.io.Serializable;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.rbac.model.Group;
import com.yeoou.rbac.service.IGroupService;

public class GroupService extends HibernateEntityDao<Group> implements IGroupService
{

	public List<Group> getAllGroup() {
		String hql="from Group as group order by group.name";
 		return this.find(hql);
	}

	public Group getGroupById(Serializable groupId) {
		String hql="from Group as gr left outer join fetch gr.roles where gr.groupId=?";
		List<Group> group=this.find(hql,new Object[]{groupId});
		if(group.size()!=0)
		{
			return group.get(0);
		}
		else return null;
	}

	public Group getGroupByIdOnly(Serializable groupId) {
		String hql="from Group as group where group.groupId=?";
		List<Group> group=this.find(hql,new Object[]{groupId});
		if(group.size()!=0)
		{
			return group.get(0);
		}
		else return null;
	}
	public Group getGroupUserById(Serializable groupId) {
		String hql="from Group as gr left outer join fetch gr.users where gr.groupId=?";
		List<Group> group=this.find(hql,new Object[]{groupId});
		if(group.size()!=0)
		{
			return group.get(0);
		}
		else return null;
	}
}
