package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.tour.model.NewsCount;
/**
 * 新闻统计相关业务接口
 * @author kensin
 * @see ICountSceneryService
 */
public interface INewsCountService extends EntityDao
{
	public NewsCount getNewsCountByNewsId(String newsId);
	public List<NewsCount> getNewsCountByHitForNum(int pageSize);
}
