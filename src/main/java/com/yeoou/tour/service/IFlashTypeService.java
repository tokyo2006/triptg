package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.FlashType;
import com.yeoou.tour.model.TreeNode;
/**
 * Flash类别相关业务接口
 * @author kensin
 *
 */
public interface IFlashTypeService extends EntityDao
{
	/**
	 * 获取指定flash类别对象
	 * @param name	类别名
	 * @return		flash类别对象
	 */
	public FlashType getFlashTypeByName(String name);
	/**
	 * 设置flash类别树（仅用于ExtJS树型显示）
	 * @param typeList	flash类别列表
	 * @return	树形节点列表
	 */
	public List<TreeNode> setFlashTypeTreeNodes(List<FlashType> typeList);
	/**
	 * 后台显示flash类别列表
	 * @param start		页数
	 * @param limit		一页包含数量
	 * @param dir		排序方式
	 * @param sort		排序字段
	 * @return			flash类别列表
	 */
	public Page getAllFlashType(int start,int limit,String dir,String sort);
}
