package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.TreeNode;
import com.yeoou.tour.model.TripFlag;
/**
 * 页面相关业务接口
 * @author kensin
 *
 */
public interface ITripFlagService extends EntityDao
{
	/**
	 * 获取页面对象
	 * @param name		页面名称
	 * @return			页面对象
	 */
	public TripFlag getTripFlagByName(String name);
	/**
	 * @see IFlashTypeService#setFlashTypeTreeNodes(List)
	 */
	public List<TreeNode> setTripFlagTreeNodes(List<TripFlag> flagList,String flag);
	/**
	 * 后台显示页面列表
	 * @param start		页数
	 * @param limit		一页数量
	 * @param sort		排序字段
	 * @param dir		排序方式
	 * @return			页面列表
	 */
	public Page getAllTripFlag(int start ,int limit,String sort,String dir);
}
