package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.service.INewsService;
import com.yeoou.tour.model.*;

public class NewsService extends HibernateEntityDao<News> implements INewsService
{

	public Page getAllPage(String dir, String sort, int start, int limit,String areaId,String typeId,String title)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		List<Object> val = new ArrayList<Object>();
		if(areaId.equals("")&&typeId.equals(""))
		{
			sb.append("from News as news where 1=1");
		}
		else if(typeId.equals("")&&!areaId.equals(""))
		{
			sb.append("select distinct news from News as news left outer join news.areas as area where area.areaId = ?");
			val.add(areaId);
		}
		else if(areaId.equals("")&&!typeId.equals(""))
		{
			sb.append("select distinct news from News as news left outer join news.types as types where types.typeId = ?");
			val.add(typeId);
		}
		else if(!areaId.equals("")&&!typeId.equals(""))
		{
			sb.append("select distinct news from News as news left outer join news.types as newstype left outer join news.areas as area where newstype.typeId = ? and area.areaId = ?");
			val.add(typeId);
			val.add(areaId);
		}
		if(!title.equals(""))
		{
			title = "%"+title+"%";
			sb.append(" and news.title like ?");
			val.add(title);
		}
		sb.append(" order by");
		sb.append(" news.");
		if(sort.equals("publishDateStr"))
		{
			sort = "publishDate";
		}
		sb.append(sort);
		sb.append(" ");
		sb.append(dir);
		Object[] values = new Object[val.size()];
		for(int i=0;i<val.size();i++)
		{
			values[i] = val.get(i);
		}
		return this.PageExtQuery(sb.toString(), start, limit, values);
	}
	@SuppressWarnings("unchecked")
	public News getNews(String newsId)
	{
		// TODO Auto-generated method stub
		String hql = "from News as news left outer join fetch news.areas as area left outer join fetch news.types as newstypes where news.newsId = ?";
		List<News> ns = this.find(hql, new Object[]{newsId});
		if(ns.size()!=0) return ns.get(0);
		else return null;
	}
	@SuppressWarnings("unchecked")
	public List<News> getNewsByNewsType(String typeId)
	{
		// TODO Auto-generated method stub
		String hql="select distinct news from News as news left outer join news.types as types where types.typeId = ?";
		List<News> ns = this.find(hql, new Object[]{typeId});
		if(ns.size()!=0) return ns;
		else return null;
	}
	public List<News> getNewsByRemark(String remark)
	{
		// TODO Auto-generated method stub
		String hql = "from News as news where news.mark = ?";
		List<News> ns = this.find(hql, new Object[]{remark});
		if(ns.size()!=0) return ns;
		else return null;
	}
	@SuppressWarnings("unchecked")
	public List<News> getNewsListForClient(int num)
	{
		// TODO Auto-generated method stub
		String hql = "from News as news where news.picUrl = ? order by news.publishDate DESC";
		Page page = new Page();
		String picUrl = "";
		page = this.PageQuery(hql, 1, num, new Object[]{picUrl});
		if(page==null) return null;
		else
		{
			List<News> newList = (List<News>)page.getResult();
			if(newList.size()==0) return null;
			else return newList;
		}
	}
	public List<News> getClientPicNews(int num)
	{
		// TODO Auto-generated method stub
		String hql = "from News as news where news.picUrl <> ? and news.promotion = ? order by news.publishDate DESC";
		@SuppressWarnings("unused")
		String picUrl = "";
		int promotion = 1;
		Page page = new Page();
		page = this.PageQuery(hql, 1, num, new Object[]{picUrl,promotion});
		if(page==null) return null;
		else
		{
			List<News> newList = (List<News>)page.getResult();
			if(newList.size()==0) return null;
			else return newList;
		}
	}
	public List<News> getNewsByNewsTypeForNum(String typeId, int pageSize) {
		// TODO Auto-generated method stub
		String hql="select distinct news from News as news left outer join news.types as types where types.typeId = ? order by news.publishDate DESC";
		Page  page = new Page();
		page = this.PageQuery(hql, 1, pageSize, new Object[]{typeId});
		if(page!=null)
		{
			List<News> newsList = new ArrayList<News>();
			newsList = (List<News>)page.getResult();
			if(newsList.size()==0) return null;
			else return newsList;
		}
		else return null;
	}
	public List<News> getNewsByNewsTypeParentNum(String parentId, int pageSize) {
		// TODO Auto-generated method stub
		String hql="select distinct news from News as news left outer join news.types as types where types.parent = ? order by news.publishDate DESC";
		Page  page = new Page();
		page = this.PageQuery(hql, 1, pageSize, new Object[]{parentId});
		if(page!=null)
		{
			List<News> newsList = new ArrayList<News>();
			newsList = (List<News>)page.getResult();
			if(newsList.size()==0) return null;
			else return newsList;
		}
		else return null;
	}
	public List<News> getNewsByNewsTypeHavePic(String typeId,int pageSize)
	{
		String hql="select distinct news from News as news left outer join news.types as types where types.typeId = ? and news.picUrl <> ? order by news.publishDate DESC";
		Page  page = new Page();
		String pic = "";
		page = this.PageQuery(hql, 1, pageSize, new Object[]{typeId,pic});
		if(page!=null)
		{
			List<News> newsList = new ArrayList<News>();
			newsList = (List<News>)page.getResult();
			if(newsList.size()==0) return null;
			else return newsList;
		}
		else return null;
	}
	public List<News> getNewsByNewsTypeParent(String parent)
	{
		// TODO Auto-generated method stub
		String hql="select distinct news from News as news left outer join news.types as types where types.parent = ? order by news.publishDate DESC";
		List<News> newList = this.find(hql, new Object[]{parent});
		if(newList.size()!=0) return newList;
		else return null;
	}
}
