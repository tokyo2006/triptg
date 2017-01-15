package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Visa;
/**
 * 签证相关业务接口
 * @author kensin
 *
 */
public interface IVisaService extends EntityDao
{
	/**
	 * 后台显示签证列表
	 * @param sort	排序字段
	 * @param dir	排序方式
	 * @param start	页数
	 * @param limit	一页数量
	 * @param name	名称（搜索条件）
	 * @param nationId	地域标识
	 * @return
	 */
	public Page getAllVisa(String sort,String dir,int start,int limit,String name,String nationId);
	/**
	 * 根据国家标识获取签证列表
	 * @param areaId	地域标识
	 * @return			签证列表
	 */
	public List<Visa> getVisaByNation(String areaId);
	/**
	 * 获取签证列表
	 * @param start	页数
	 * @param limit 一页数量
	 * @return	签证列表
	 */
	public Page getVisaList(int start,int limit);
	/**
	 * 获取一定数量的签证信息
	 * @param pageSize	数量
	 * @return			签证信息
	 */
	public List<Visa> getVisaForNum(int pageSize);
}
