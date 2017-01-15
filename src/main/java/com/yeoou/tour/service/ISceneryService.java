package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Scenery;
/**
 * 景点相关业务接口
 * @author kensin
 *
 */
public interface ISceneryService extends EntityDao
{
	/**
	 * 统计相关景点类别的景点数量
	 * @param typeId		景点类别标识
	 * @return				景点数量
	 */
	public int getCountSceneryByType(String typeId);
	/**
	 * 获取一定数量的相关类别的景点列表
	 * @param typeId	景点类别标识
	 * @param pageSize	景点数量
	 * @return			景点列表
	 */
	public List<Scenery> getSceneryByType(String typeId,int pageSize);
	/**
	 * 获取景点信息的详细内容
	 * @param sceneryId		景点标识
	 * @return				景点对象
	 */
	public Scenery getSceneryInfo(String sceneryId);
	/**
	 * 获取与地域相关的景点信息列表
	 * @param areaId		地域标识
	 * @return				景点列表
	 */
	public List<Scenery> getSceneryByArea(String areaId);
	/**
	 * 获取名称已定的景点对象
	 * @param name		景点名称
	 * @return			景点对象
	 */
	public Scenery getSceneryByName(String name);
	/**
	 * 后台显示景点列表（带搜索条件）
	 * @param letter		首字母（搜索条件）
	 * @param typeId		类别标识（搜索条件）
	 * @param areaId		地域标识（搜索条件）
	 * @return				景点列表
	 */
	public List<Scenery> getSceneryListByLetter(String letter,String typeId,String areaId);
	/**
	 * 前台显示景点列表
	 * @param letter		首字母（搜索条件）
	 * @param keyWord		景点名称（搜索条件）
	 * @param areaId		地域标识（搜索条件）
	 * @param typeId		类别标识（搜索条件）
	 * @param start			页数
	 * @param pageSize		数量
	 * @return				景点列表
	 */
	public Page getAllClientScenery(String letter,String keyWord,String areaId,String typeId,int start,int pageSize);
	/**
	 * 后台景点显示列表
	 * @param dir			排序方式
	 * @param sort			排序字段
	 * @param start			页数
	 * @param limit			一页数量
	 * @param name			景点名称（搜索条件）
	 * @param areaId		地域标识
	 * @param regionId		类别标识
	 * @return				后台景点列表
	 */
	public Page getAllScenery(String dir,String sort,int start,int limit,String name,String areaId,String regionId);
	/**
	 * 获取首字母相关的景点列表
	 * @param letter	首字母
	 * @return			景点相关列表
	 */
	public List<Scenery> getSceneryByLetter(String letter);
	/**
	 *  获取与地域相关的景点信息列表
	 * @param areaId	地域标识
	 * @param pageSize	数量
	 * @return			景点列表
	 */
	public List<Scenery> getSceneryByArea(String areaId,int pageSize);
}
