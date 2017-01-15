package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Scenery;
import com.yeoou.tour.service.ISceneryService;

public class SceneryService extends HibernateEntityDao<Scenery> implements ISceneryService
{

	public int getCountSceneryByType(String typeId)
	{
		// TODO Auto-generated method stub
		String hql = "select count(*) from Scenery as sc left outer join sc.types as types where types.typeId = ?";
		Long result = (Long) this.find(hql, new Object[]{typeId}).get(0);
		return result.intValue();
	}

	@SuppressWarnings("unchecked")
	public Scenery getSceneryInfo(String sceneryId)
	{
		// TODO Auto-generated method stub
		String hql = "from Scenery as sc left outer join fetch sc.areas left outer join fetch sc.regions left outer join fetch sc.types where sc.sceneryId = ?";
		List<Scenery> scList = this.find(hql, new Object[]{sceneryId});
		if(scList.size()!=0) return scList.get(0);
		else return null;
	}
	@SuppressWarnings("unchecked")
	public List<Scenery> getSceneryByArea(String areaId)
	{
		// TODO Auto-generated method stub
		String hql ="select distinct sc from Scenery as sc left outer join sc.areas as area where area.areaId = ?";
		List<Scenery> scList = this.find(hql, new Object[]{areaId});
		if(scList.size()!=0) return scList;
		else return null;
	}
	public List<Scenery> getSceneryByArea(String areaId,int pageSize)
	{
		String hql ="select distinct sc from Scenery as sc left outer join sc.areas as area where area.areaId = ?";
		Page page = new Page();
		page = this.PageQuery(hql, 1, pageSize, new Object[]{areaId});
		if(page==null) return null;
		else
		{
			List<Scenery> scList = new ArrayList<Scenery>();
			scList = (List<Scenery>)page.getResult();
			if(scList.size()==0) return null;
			else return scList;
		}
	}
	
	public Page getAllScenery(String dir, String sort, int start, int limit, String name,String areaId,String regionId)
	{
//		 TODO Auto-generated method stub
		List<Object> val = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder();
		if(areaId.equals("")&&regionId.equals(""))
		{
			sb.append("from Scenery as sc where 1=1");
		}
		else if(regionId.equals("")&&!areaId.equals(""))
		{
			sb.append("select distinct sc from Scenery as sc left outer join sc.areas as areas where areas.areaId = ?");
			val.add(areaId);
		}
		else if(!regionId.equals("")&&areaId.equals(""))
		{
			sb.append("select distinct sc from Scenery as sc left outer join sc.regions as regions where regions.regionId = ?");
			val.add(regionId);
		}
		if(!name.equals(""))
		{
			sb.append(" and sc.name like ?");
			name = "%"+name+"%";
			val.add(name);
		}
		sb.append(" order by sc.");
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

	public Scenery getSceneryByName(String name)
	{
		// TODO Auto-generated method stub
		String hql = "from Scenery as sc where sc.name = ?";
		List<Scenery> scList = this.find(hql, new Object[]{name});
		if(scList.size()!=0) return scList.get(0);
		else return null;
	}


	public Page getAllClientScenery(String letter,String keyWord,String areaId,String typeId,int start,int pageSize)
	{
		StringBuilder sb = new StringBuilder("");
		List<Object> val = new ArrayList<Object>();
		if(areaId.equals("")&&typeId.equals(""))
		{
			sb.append("from Scenery as sc where 1=1");
		}
		else if(!areaId.equals("")&&typeId.equals(""))
		{
			sb.append("select distinct sc from Scenery as sc left outer join sc.areas as areas where areas.areaId = ?");
			val.add(areaId);
		}
		else if(areaId.equals("")&&!typeId.equals(""))
		{
			sb.append("select distinct sc from Scenery as sc left outer join sc.types as types where types.typeId = ?");
			val.add(typeId);
		}
		else if(!areaId.equals("")&&!typeId.equals(""))
		{
			sb.append("select distinct sc from Scenery as sc left outer join sc.types as types left outer join sc.areas as areas where types.typeId = ? and areas.areaId = ?");
			val.add(typeId);
			val.add(areaId);
		}
		if(!letter.equals(""))
		{
			sb.append(" and sc.firstLetter = ?");
			val.add(letter);
		}
		if(!keyWord.equals(""))
		{
			keyWord = "%"+keyWord+"%";
			sb.append(" and sc.name like ?");
			val.add(keyWord);
		}
		sb.append(" order by sc.firstLetter ASC");
		Object[] values = new Object[val.size()];
		for(int i=0;i<val.size();i++)
		{
			values[i] = val.get(i);
		}
		return this.PageQuery(sb.toString(), start, pageSize, values);
	}
	@SuppressWarnings("unchecked")
	public List<Scenery> getSceneryListByLetter(String letter,String typeId,String areaId)
	{
		// TODO Auto-generated method stub
		String hql = "from Scenery as sc where sc.firstLetter = ?";
		Page page = new Page();
		if(typeId.equals("")&&areaId.equals(""))
		{
			page = this.PageQuery(hql, 1, 20, new Object[]{letter});
		}
		else if(!typeId.equals("")&&areaId.equals(""))
		{
			hql = "select distinct sc from Scenery as sc left outer join sc.types as types where types.typeId = ? and sc.firstLetter = ?";
			page = this.PageQuery(hql, 1, 20, new Object[]{typeId,letter});
		}
		else if(!typeId.equals("")&&!areaId.equals(""))
		{
			hql = "select distinct sc from Scenery as sc left outer join sc.types as types left outer join sc.areas as areas where types.typeId = ? and sc.firstLetter = ? and areas.areaId = ?";
			page = this.PageQuery(hql, 1, 20, new Object[]{typeId,letter,areaId});
		}
		else if(typeId.equals("")&&!areaId.equals(""))
		{
			hql = "select distinct sc from Scenery as sc left outer join sc.areas as areas where sc.firstLetter = ? and areas.areaId = ?";
			page = this.PageQuery(hql, 1, 20, new Object[]{letter,areaId});
		}
		if(page!=null)
		{
			List<Scenery> scList =(List<Scenery>)page.getResult();
			return scList;
		}
		else
		{
			return null;
		}
	}
	public List<Scenery> getSceneryByLetter(String letter)
	{
		String hql = "from Scenery as sc where sc.firstLetter = ? order by sc.firstLetter ASC";
		List<Scenery> scList =this.find(hql, new Object[]{letter});
		if(scList.size()!=0) return scList;
		else return null;
	}

	public List<Scenery> getSceneryByType(String typeId, int pageSize)
	{
		// TODO Auto-generated method stub
		String hql = "select distinct sc from Scenery as sc left outer join sc.types as types where types.typeId = ? ";
		Page page = new Page();
		page = this.PageQuery(hql, 1, pageSize, new Object[]{typeId});
		if(page!=null)
		{
			List<Scenery> scList = new ArrayList<Scenery>();
			scList = (List<Scenery>)page.getResult();
			if(scList.size()==0) return null;
			else return scList;
		}
		else return null;
	}

}
