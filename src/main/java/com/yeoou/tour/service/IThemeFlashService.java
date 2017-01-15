package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.ThemeFlash;
/**
 * 主题flash相关业务逻辑接口
 * @author kensin
 *
 */
public interface IThemeFlashService extends EntityDao
{
	/**
	 * 后台获取主题FLASH相关列表
	 * @param start		页数
	 * @param limit		一页包含数量
	 * @param sort		排序字段
	 * @param dir		排序
	 * @param typeId	类别标识
	 * @return			主题flash列表
	 */
	public Page getAllThemeFlash(int start,int limit,String sort,String dir,String typeId);
	/**
	 * 获取flash类别相关的主题flash列表
	 * @param name		类别名称
	 * @return			主题flash列表
	 */
	public List<ThemeFlash> getThemeByFlashType(String name);
	/**
	 * 统计类别相关flash数量
	 * @param typeId	flash类别标识
	 * @return			flash数量
	 */
	public int getCountByFlashType(String typeId);
}
