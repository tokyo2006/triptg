package com.yeoou.tour.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Region;
import com.yeoou.tour.model.RegionLation;
import com.yeoou.tour.model.TreeNode;
import com.yeoou.tour.service.IRegionService;

public class RegionService extends HibernateEntityDao<Region> implements IRegionService
{

	public Page getAllRegion(int start, int limit, String dir, String sort, String name, int depth,int flag,String node)
	{
		// TODO Auto-generated method stub
		List<Object> val = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("from Region as region where 1=1");
		if(!name.equals(""))
		{
			sb.append(" and region.name = ?");
			val.add(name);
		}
		if(depth!=10)
		{
			sb.append(" and region.depth = ?");
			val.add(depth);
		}
		if(flag!=10)
		{
			sb.append(" and region.flag = ?");
			val.add(flag);
		}
		if(!node.equals(""))
		{
			sb.append(" and region.parent = ?");
			val.add(node);
		}
		sb.append(" order by region.");
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
	public Region getRegionChildren(String regionId)
	{
		// TODO Auto-generated method stub
		Region region = new Region();
		region = (Region)this.get(regionId);
		String hql = "from Region as region where region.parent = ? order by region.name ASC";
		List<Region> regionList = this.find(hql, new Object[]{regionId});
		region.setChildren(new HashSet<Region>(regionList));
		return region;
	}

	@SuppressWarnings("unchecked")
	public List<Region> getChildren(String regionId)
	{
		// TODO Auto-generated method stub
		String hql = "from Region as region where region.parent = ? order by region.name ASC";
		List<Region> regionList = this.find(hql, new Object[]{regionId});
		if(regionList.size()!=0) return regionList;
		else return null;
	}

	public List<TreeNode> setRegionTreeNodes(Region children)
	{
		// TODO Auto-generated method stub
		List<Region> regionChildren = new ArrayList<Region>();
		regionChildren.addAll(children.getChildren());
		
	    List<TreeNode> treenodes = new ArrayList<TreeNode>();		
        	    
		for(int i=0;i<regionChildren.size();i++){
			TreeNode treenodeleaf = new TreeNode();
			treenodeleaf.setId(regionChildren.get(i).getRegionId());
			treenodeleaf.setText(regionChildren.get(i).getName());
			List<Region> al = this.getChildren(regionChildren.get(i).getRegionId());
			if(al!=null){
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

	public List<Region> getChildren(Serializable userId, int depth)
	{
		// TODO Auto-generated method stub
		String hql = "select proxyRegion.region from RegionLation as proxyRegion where proxyRegion.user.userId = ? and proxyRegion.region.depth=?";
	    List<Region> parentList = this.find(hql, new Object[]{userId,depth});
	    hql = "select proxyRegion.region from RegionLation as proxyRegion where proxyRegion.user.userId = ? and proxyRegion.region.parent = ?";
	    for(int i =0;i<parentList.size();i++)
	    {
	    	parentList.get(i).setChildren(new HashSet<Region>(this.find(hql, new Object[]{userId,parentList.get(i).getRegionId()})));
	    }
		return parentList;
	}
	@SuppressWarnings("unchecked")
	public Region getChildren(Serializable userId, Serializable regionId)
	{
		// TODO Auto-generated method stub
		String hql = "select proxyRegion.region from RegionLation as proxyRegion where proxyRegion.user.userId = ? and proxyRegion.region.regionId=?";
		List<Region> regions = this.find(hql, new Object[]{userId,regionId});
		Region region = new Region();
		region = regions.get(0);
		hql = "select proxyRegion.region from RegionLation as proxyRegion where proxyRegion.user.userId = ? and proxyRegion.region.parent=?";
		region.setChildren(new HashSet<Region>(this.find(hql, new Object[]{userId,region.getRegionId()})));
		return region;
	}

	@SuppressWarnings("unchecked")
	public Region getRegionByAreaId(String areaId,int depth)
	{
		// TODO Auto-generated method stub
		String hql = "from Region as rl where rl.area.areaId = ? and rl.depth = ?";
		List<Region> regionList= this.find(hql, new Object[]{areaId,depth});
		hql = " from Region as region where region.parent = ?";
		regionList.get(0).setChildren(new HashSet<Region>(this.find(hql, new Object[]{regionList.get(0).getRegionId()})));
		if(regionList.size()!=0) return regionList.get(0);
		else return null;
	}
	public List<Region> getRegionList(String regionId,int depth)
	{
		List<Region> regionList = new ArrayList<Region>();
		Region region = new Region();
		region = (Region)this.get(regionId);
		regionList.add(region);
		while(region.getDepth()>depth)
		{
			String parent = region.getParent();
			region = new Region();
			region = (Region)this.get(parent);
			regionList.add(region);
		}
		List<Region> result = new ArrayList<Region>();
		for(int i=regionList.size()-1;i>=0;i--)
		{
			result.add(regionList.get(i));
		}
		return result;
	}
	public List<Region> getRegionsByParent(String parentId, String orderBy, boolean isAsc)
	{
		// TODO Auto-generated method stub
		String hql = "from Region as rl where  rl.parent = ? order by rl.";
		if(isAsc)
		{
			hql += orderBy+" ASC";
		}
		else
		{
			hql += orderBy+" DESC";
		}
		List<Region> regionList = this.find(hql, new Object[]{parentId});
		hql = " from Region as region where region.parent = ?";
	    for(int i =0;i<regionList.size();i++)
	    {
	    	regionList.get(i).setChildren(new HashSet<Region>(this.find(hql, new Object[]{regionList.get(i).getRegionId()})));
	    }
		if(regionList.size()!=0) return regionList;
		else return null;
	}
}
