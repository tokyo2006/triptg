package com.yeoou.rbac.service;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.rbac.model.Role;

public interface IRoleService extends EntityDao
{
	public Role getRoleById(String roleId);
}
