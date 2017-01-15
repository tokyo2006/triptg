package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.FlashType;
import com.yeoou.tour.model.TreeNode;
import com.yeoou.tour.service.IFlashTypeService;

public class FlashTypeService extends HibernateEntityDao<FlashType> implements
		IFlashTypeService
{

	@SuppressWarnings("unchecked")
	public FlashType getFlashTypeByName(String name)
	{
		// TODO Auto-generated method stub
		String hql = "from FlashType as ft where ft.name = ?";
		List<FlashType> ft = this.find(hql, new Object[]{name});
		if(ft.size()!= 0) return ft.get(0);
		else return null;
	}

	public Page getAllFlashType(int start, int limit, String dir, String sort)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("from FlashType as ft where 1=1");
		sb.append("order by ft.");
		sb.append(sort);
		sb.append(" ");
		sb.append(dir);
		return this.PageExtQuery(sb.toString(), start, limit, new Object[]{});
	}

	public List<TreeNode> setFlashTypeTreeNodes(List<FlashType> typeList)
	{
		// TODO Auto-generated method stub
		int length = typeList.size();
		List<TreeNode> treenodes = new ArrayList<TreeNode>();
		for(int i=0;i<length;i++)
		{
			TreeNode treenodeleaf = new TreeNode();
			treenodeleaf.setCls("file");
			treenodeleaf.setLeaf(true);
			treenodeleaf.setId(typeList.get(i).getId());
			treenodeleaf.setText(typeList.get(i).getTitle());
			treenodes.add(treenodeleaf);
		}
		return treenodes;
	}
	
}
