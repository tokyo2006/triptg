package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.SceneryType;
/**
 * 景点类别相关业务接口
 * @author kensin
 *
 */
public interface ISceneryTypeService extends EntityDao
{
	/**
	 * 获取景点类别列表
	 * @param start		页数
	 * @param limit		一页包含数量
	 * @param dir		排序方式
	 * @param sort		排序字段
	 * @return			景点类别列表
	 */
	public Page getAllSceneryType(int start,int limit,String dir,String sort);
	/**
	 * 根据景点类别标识获取相关景点类表列表
	 * @param flag		景点类别标识
	 * @return			景点类别列表
	 */
	public List<SceneryType> getSceneryTypeList(int flag);
}
