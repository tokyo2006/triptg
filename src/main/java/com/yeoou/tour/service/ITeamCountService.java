package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.tour.model.TeamCount;
/**
 * 开班计划统计相关业务逻辑
 * @author kensin
 * @see ICountSceneryService
 *
 */
public interface ITeamCountService extends EntityDao
{
	public TeamCount getTeamCountByGroupId(String groupId);
	public List<TeamCount> getTeamCountByPage(int pagesize);
}
