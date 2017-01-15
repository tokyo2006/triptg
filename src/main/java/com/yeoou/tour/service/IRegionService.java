
package com.yeoou.tour.service;

import java.io.Serializable;
import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Region;
import com.yeoou.tour.model.RegionLation;
import com.yeoou.tour.model.TreeNode;
/**
 * 类别相关业务接口
 * @author kensin
 *
 */
public interface IRegionService extends EntityDao
{
	/**
	 * 获取父类为regionId的子类信息列表
	 * @param regionId	类别标识
	 * @return			类别列表
	 */
	public List<Region> getChildren(String regionId);
	/**
	 * 获取树形列表{@link IFlashTypeService#setFlashTypeTreeNodes(List)}
	 */
	public List<TreeNode> setRegionTreeNodes(Region children);
	/**
	 * 获取一定深度的类别列表（包含儿子）
	 * @param userId	用户标识
	 * @param depth		类别深度
	 * @return			返回用户所属的类别列表（深度一定）
	 */
	public List<Region> getChildren(Serializable userId, int depth);
	/**
	 * 获取类别对象（包含儿子）
	 * @param regionId	类别标识
	 * @return			类别对象
	 */
	public Region getRegionChildren(String regionId);
	/**
	 * 获取与地域相关的类别对象（包含儿子）
	 * @param areaId 	地域标识
	 * @param depth		深度
	 * @return			类别对象
	 */
	public Region getRegionByAreaId(String areaId,int depth);
	/**
	 * @see IAreaService#getAreaList(String, int)
	 */
	public List<Region> getRegionList(String regionId,int depth);
	/**
	 * 获取用户所属的类别对象（包含儿子）
	 * @param userId		用户标识
	 * @param regionId		类别标识
	 * @return				类别对象
	 */
	public Region getChildren(Serializable userId, Serializable regionId);
	/**
	 * 后台显示类别信息包括相关搜索条件
	 * @param start		页数
	 * @param limit		一页包含数量
	 * @param dir		排序方式
	 * @param sort		排序字段
	 * @param name		名称（搜索条件）
	 * @param depth		深度（搜索条件）
	 * @param flag		标志（搜索条件）
	 * @param node		节点（树形节点）
	 * @return			类别列表
	 */
	public Page getAllRegion(int start,int limit,String dir,String sort,String name,int depth,int flag,String node);
	/**
	 * 获取排序后的子类列表
	 * @param parentId	父类标识
	 * @param orderBy	排序字段
	 * @param isAsc		排序方式
	 * @return			类别列表
	 */
	public List<Region> getRegionsByParent(String parentId, String orderBy, boolean isAsc);
}
