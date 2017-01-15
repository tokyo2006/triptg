package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.News;
/**
 * 新闻相关业务接口
 * @author kensin
 *
 */
public interface INewsService extends EntityDao
{
	/**
	 * 后台显示新闻列表
	 * @param dir		排序方式
	 * @param sort		排序列表
	 * @param start		页数
	 * @param limit		一页包含数量
	 * @param areaId	地域标识
	 * @param typeId	类别标识
	 * @param title		标题
	 * @return			新闻列表
	 */
	public Page getAllPage(String dir, String sort, int start, int limit,String areaId,String typeId,String title);
	/**
	 * 获取新闻详细信息
	 * @param newsId	新闻标识
	 * @return			新闻对象
	 */
	public News getNews(String newsId);
	/**
	 * 获取新闻列表
	 * @param parent	父类标识
	 * @return			所有父类的新闻
	 */
	public List<News> getNewsByNewsTypeParent(String parent);
	/**
	 * 获取新闻列表
	 * @param typeId	类别标识
	 * @return			此类别所有新闻
	 */
	public List<News> getNewsByNewsType(String typeId);
	/**
	 * 获取新闻列表
	 * @param typeId	类别标识
	 * @param pageSize	获取数量
	 * @return			此类别新闻
	 */
	public List<News> getNewsByNewsTypeForNum(String typeId,int pageSize);
	/**
	 * 获取新闻列表
	 * @param parentId	父类标识
	 * @param pageSize	获取数量
	 * @return			此父类新闻
	 */
	public List<News> getNewsByNewsTypeParentNum(String parentId,int pageSize);
	/**
	 * 获取新闻列表
	 * @param remark	新闻标签
	 * @return			新闻列表
	 */
	public List<News> getNewsByRemark(String remark);
	/**
	 * 获取新闻列表
	 * @param num	获取数量
	 * @return		新闻列表
	 */
	public List<News> getNewsListForClient(int num);
	/**
	 * 获取图片新闻列表
	 * @param num	获取数量
	 * @return		新闻列表
	 */
	public List<News> getClientPicNews(int num);
	/**
	 * 获取新闻列表
	 * @param typeId		新闻类别标识
	 * @param pageSize		新闻数量
	 * @return				新闻列表
	 */
	public List<News> getNewsByNewsTypeHavePic(String typeId,int pageSize);
}
