package com.yeoou.tour.service;

import java.io.File;
import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.SceneryPic;
/**
 * 景点图片相关业务接口
 * @author kensin
 *
 */
public interface ISceneryPicService extends EntityDao
{
	/**
	 * 添加相关景点图片信息
	 * @param pic			景点图片对象
	 * @param upload		图像文件
	 * @param savePath		原始图像保存路径
	 * @param breSavePath	缩略图像保存路径 (300×300)
	 * @param miniSavePath	迷你图像保存路径 (150×150)	
	 * @param fileName		文件名称
	 * @param fileType		文件类型
	 */
	public void addPic(SceneryPic pic ,File upload,String savePath,String breSavePath,String miniSavePath,String fileName,String fileType);
	/**
	 * 添加图片信息
	 * @param upload		图像文件
	 * @param savePath		原图保存路径
	 * @param breSavePath	缩略图保存路径
	 * @param fileName		文件名称
	 * @param fileType		文件类型
	 */
	public void addPic(File upload, String savePath, String breSavePath, String fileName,String fileType);
	/**
	 * 添加图片信息
	 * @param upload		图像文件
	 * @param savePath		保存路径
	 * @param fileName		文件名称
	 * @param fileType		文件类型
	 */
	public void addPic(File upload, String savePath, String fileName,String fileType);
	/**
	 * 后台显示景点图片列表
	 * @param dir		排序方式
	 * @param sort		排序字段
	 * @param limit		一页数量
	 * @param start		页数
	 * @param name		景点名称（搜索条件）
	 * @return	景点图片列表
	 */
	public Page getAllPic(String dir,String sort,int limit,int start,String name);
	/**
	 * 获取景点图片对象
	 * @param picId  景点图片标识
	 * @return		 景点图片对象
	 */
	public SceneryPic getSceneryPic(String picId);
	/**
	 * 获取景点相关的景点图片列表
	 * @param sceneryId		景点标识
	 * @return				景点相关的图片列表
	 */
	public List<SceneryPic> getPicListByScenery(String sceneryId);
	/**
	 * 统计景点包含景点图片数量
	 * @param sceneryId		景点标识
	 * @return				图片数量
	 */
	public int getCountPicByScenery(String sceneryId);
	/**
	 * 获取地域包含图片列表
	 * @param areaId		地域标识
	 * @return				地域相关图片列表
	 */
	public List<SceneryPic> getPicListByArea(String areaId);
	/**
	 * 删除景点图片
	 * @param delPath	图片路径
	 */
	public void delPicture(String delPath);
	/**
	 * 删除景点图片对象
	 * @param picId		景点图片标识
	 */
	public void delPic(String picId);
	/**
	 * 获取一定数量的地域包含图片列表
	 * @param areaId	地域标识
	 * @param start		页数
	 * @param pageNo	数量
	 * @return			景点图片对象
	 */
	public Page getClientPicListByArea(String areaId,int start,int pageNo);
}
