package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.tour.model.SceneryCount;
/**
 * 景点统计相关业务接口
 * @author kensin
 *
 */
public interface ICountSceneryService extends EntityDao
{
	/**
	 * 景点相关统计信息
	 * @param sceneryId		景点标识
	 * @return	景点统计相关信息
	 */
	public SceneryCount getSceneryCountBySceneryId(String sceneryId);
	/**
	 * 获取一定数量的景点统计信息
	 * @param pageSize	获取的数量
	 * @return 统计列表
	 */
	public List<SceneryCount> getSceneryByMonthHit(int pageSize);
}
