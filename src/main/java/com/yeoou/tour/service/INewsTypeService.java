package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.NewsType;
import com.yeoou.tour.model.TreeNode;
/**
 * 新闻类别相关业务接口
 * @author kensin
 *
 */
public interface INewsTypeService extends EntityDao
{
	/**
	 * 后台显示新闻类别列表
	 * @param dir		排序方式
	 * @param sort		排序字段
	 * @param start		页数
	 * @param limit		一页包含数量
	 * @param typeId	类别标识
	 * @return			新闻类别列表
	 */
	public Page getAllNewsType(String dir,String sort,int start ,int limit,String typeId);
	/**
	 * 获取父类的子类类别列表
	 * @param parent	父类标识
	 * @return			返回类别列表
	 */
	public List<NewsType> getChildren(String parent);
	/**
	 * 返回父类类别对象（包含子类信息）
	 * @param parent	父类标识
	 * @return			父类对象
	 */
	public NewsType getNewsTypeChildren(String parent);
	/**
	 * 统计所属类别的新闻数量
	 * @param typeId	类别标识
	 * @return			新闻数量
	 */
	public int getCountByNewsType(String typeId);
	/**
	 * 参见{@link IFlashTypeService#setFlashTypeTreeNodes(List)}
	 */
	public List<TreeNode> setTypeTreeNodes(NewsType children);
	/**
	 * 参见{@link IFlashTypeService#setFlashTypeTreeNodes(List)}
	 */
	public List<TreeNode> setTypeTreeNodes(List<NewsType> children);
}
