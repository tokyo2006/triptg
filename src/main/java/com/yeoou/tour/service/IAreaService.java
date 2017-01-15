package com.yeoou.tour.service;


import java.io.File;
import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.TreeNode;
/**
 * 地域相关业务接口
 * @author kensin
 *
 */
public interface IAreaService extends EntityDao
{
	/**
	 * 获取地域Area标识为areaId的儿子列表
	 * @param areaId
	 * @return 返回儿子列表
	 */
	public List<Area> getChildren(String areaId);
	/**
	 * 拼装EXT树形结构
	 * @param children
	 * @return
	 */
	public List<TreeNode> setAreaTreeNodes(Area children);
	public List<TreeNode> setAreaTreeNodes(Area children,int depth);
	/**
	 * 获取Area的儿子并返回Area对象
	 * @param areaId
	 * @return
	 */
	public Area getAreaChildren(String areaId);
	/**
	 * 显示地域信息
	 * @param start
	 * @param limit
	 * @param dir
	 * @param sort
	 * @param name	（搜索条件）
	 * @param depth	（搜索条件）
	 * @return
	 */
	public Page getAllArea(int start,int limit,String dir,String sort,String name,int depth,String areaId);
	/**
	 * 删除地域（并删除相关的洲，国家，省份（国外是城市），城市）
	 * @param areaId	地域标识
	 */
	public void delArea(String areaId);
	/**
	 * 添加地域信息（并添加相关的洲，国家，省份（国外是城市），城市）
	 * @param area		地域对象
	 */
	public void addArea(Area area);
	/**
	 * 获取中国省份信息列表
	 * @return	地域列表
	 */
	public List<Area> getProvince();
	/**
	 * 获取中国城市列表
	 * @return	地域列表
	 */
	public List<Area> getChinaCity();
	/**
	 * 获取国家信息列表
	 * @return 地域列表
	 */
	public List<Area> getCountry();
	/**
	 * 获取洲信息
	 * @return 地域列表
	 */
	public List<Area> getState();
	/**
	 * 根据名称获取相关地域对象
	 * @param name	地域名称
	 * @return		地域对象
	 */
	public Area getAreaByName(String name);
	/**
	 * 回溯到深度为depth的地域对象并组成列表返回
	 * @param areaId	查找对象标识
	 * @param depth		回溯深度
	 * @return			地域列表
	 */
	public List<Area> getAreaList(String areaId,int depth);
	/**
	 * 通过名称和深度查找地域信息
	 * @param	name	地域名称
	 * @param	depth	深度
	 * @return	地域对象
	 */
	public Area getAreaByName(String name, int depth);
	/**
	 * 添加图片信息
	 * @param upload	上传文件
	 * @param savePath	保存路径
	 * @param fileName	文件名
	 */
	public void addPic(File upload,String savePath,String fileName);
}
