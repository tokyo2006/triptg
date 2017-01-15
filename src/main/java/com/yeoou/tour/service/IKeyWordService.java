package com.yeoou.tour.service;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.KeyWord;
/**
 * 关键字相关业务接口
 * @author kensin
 *
 */
public interface IKeyWordService extends EntityDao
{
	/**
	 * 后台显示关键字信息列表
	 * @param start 页数
	 * @param limit 一页显示字数
	 * @param dir   排序方式
	 * @param sort  排序字段
	 * @param name	关键字名称
	 * @return	关键字信息列表
	 */
	public Page getAllKeyWord(int start,int limit,String dir,String sort,String name);
	/**
	 * 获取关键字对象
	 * @param name  关键字对象名称
	 * @return		关键字对象
	 */
	public KeyWord getKeyWordByName(String name);
	/**
	 * 获取关键字对象
	 * @param sceneryId	关键字关联景点标识
	 * @return		    关键字关联对象
	 */
	public KeyWord getKeyWordBySceneryId(String sceneryId);
}
