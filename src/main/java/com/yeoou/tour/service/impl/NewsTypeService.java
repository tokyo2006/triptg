package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.NewsType;
import com.yeoou.tour.model.TreeNode;
import com.yeoou.tour.service.INewsTypeService;

public class NewsTypeService extends HibernateEntityDao<NewsType> implements
		INewsTypeService
{

	public Page getAllNewsType(String dir, String sort, int start, int limit,String typeId)
	{
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		String hql = "";
		if(!typeId.equals(""))
		{
			hql = "from NewsType as newsType where newsType.parent = ? order by newsType."+sort+" "+dir;
			return this.PageExtQuery(hql, start, limit, new Object[]{typeId});
		}
		else
		{
			hql = "from NewsType as newsType order by newsType."+sort+" "+dir;
			return this.PageExtQuery(hql, start, limit, new Object[]{});
		}
	}

	public List<NewsType> getChildren(String parent)
	{
		// TODO Auto-generated method stub
		String hql = "from NewsType as newsType where newsType.parent = ?";
		return this.find(hql, new Object[]{parent});
	}
	public NewsType getNewsTypeChildren(String parent)
	{
		String hql = "from NewsType as newsType where newsType.parent = ?";
		List<NewsType> ntList = this.find(hql, new Object[]{parent});
		NewsType nt = new NewsType();
		nt = this.get(parent);
		nt.setChildern(ntList);
		return nt;
	}
	public int getCountByNewsType(String typeId)
	{
		String hql="select Count(*) from News as news left outer join news.types as types where types.typeId = ?";
		Long result = (Long)this.find(hql, new Object[]{typeId}).get(0);
		return result.intValue();
	}
	public List<TreeNode> setTypeTreeNodes(List<NewsType> children)
	{
		List<TreeNode> treenodes = new ArrayList<TreeNode>();	
		 for(int i=0;i<children.size();i++){
				TreeNode treenodeleaf = new TreeNode();
				treenodeleaf.setId(children.get(i).getTypeId());
				treenodeleaf.setText(children.get(i).getName());
				
				if(children.get(i).getParent().equals("")){
					treenodeleaf.setCls("floder");
					treenodeleaf.setLeaf(false);
				}else{
					treenodeleaf.setCls("file");
					treenodeleaf.setLeaf(true);
				}
				treenodes.add(treenodeleaf);	
			}
			return treenodes;
	}
	public List<TreeNode> setTypeTreeNodes(NewsType children)
	{
		List<NewsType> areaChildren = new ArrayList<NewsType>();
		areaChildren.addAll(children.getChildern());
		 List<TreeNode> treenodes = new ArrayList<TreeNode>();	
		 for(int i=0;i<areaChildren.size();i++){
				TreeNode treenodeleaf = new TreeNode();
				treenodeleaf.setId(areaChildren.get(i).getTypeId());
				treenodeleaf.setText(areaChildren.get(i).getName());
				
				if(areaChildren.get(i).getParent().equals("")){
					treenodeleaf.setCls("floder");
					treenodeleaf.setLeaf(false);
				}else{
					treenodeleaf.setCls("file");
					treenodeleaf.setLeaf(true);
				}
				treenodes.add(treenodeleaf);	
			}
			return treenodes;
	}
}
