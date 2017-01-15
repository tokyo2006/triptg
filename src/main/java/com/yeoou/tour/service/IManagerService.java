package com.yeoou.tour.service;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Manager;
/**
 * 门户管理员相关业务接口
 * @author kensin
 *
 */
public interface IManagerService extends EntityDao
{
	/**
	 * 后台显示管理员列表（spider不显示）
	 * @param bossId	超级管理员标识
	 * @param name		名称	（搜索条件）
	 * @param start		页数
	 * @param limit		一页包含数量
	 * @param sort		排序字段
	 * @param dir		排序方式
	 * @return			管理员列表
	 */
	public Page getAllManagerByBoss(String bossId,String name,int start,int limit,String sort,String dir);
}
