package com.yeoou.rbac.service;

import java.io.Serializable;
import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.rbac.model.Group;

public interface IGroupService extends EntityDao
{
	

	public List<Group> getAllGroup();
	public Group getGroupById(Serializable groupId);
	public Group getGroupUserById(Serializable groupId);
	public Group getGroupByIdOnly(Serializable groupId);
}
