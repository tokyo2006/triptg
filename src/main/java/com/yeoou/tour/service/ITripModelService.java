package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.TreeNode;
import com.yeoou.tour.model.TripModel;
/**
 * 页面标签相关逻辑接口
 * @author kensin
 *
 */
public interface ITripModelService extends EntityDao
{
	/**
	 * 获取页面标签对象
	 * @param name	页面名称
	 * @return		页面标签对象
	 */
	public TripModel getTripModelByName(String name);
	/**
	 * 统计页面包含标签数量
	 * @param flagId	页面标识
	 * @return			标签数量
	 */
	public int getCountByTripFlag(String flagId);
	/**
	 * 获取页面标签列表
	 * @param flagId	页面标识
	 * @return			页面标签列表
	 */
	public List<TripModel> getTripModelByFlag(String flagId);
	/**
	 * @see IFlashTypeService#setFlashTypeTreeNodes(List)
	 */
	public List<TreeNode> setTripModelTreeNodes(List<TripModel> tripModelList);
	/**
	 * 后台显示页面标签列表
	 * @param start 		页数
	 * @param limit			一页包含数量
	 * @param sort			排序字段
	 * @param dir			排序方式
	 * @param flagId		页面标识
	 * @return				页面标签列表
	 */
	public Page getAllTripModel(int start ,int limit,String sort,String dir,String flagId);
}
