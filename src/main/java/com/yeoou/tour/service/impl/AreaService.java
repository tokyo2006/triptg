package com.yeoou.tour.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.FileUtils;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.City;
import com.yeoou.tour.model.Continent;
import com.yeoou.tour.model.Nation;
import com.yeoou.tour.model.Province;
import com.yeoou.tour.model.TreeNode;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.ICityService;
import com.yeoou.tour.service.IContinentService;
import com.yeoou.tour.service.INationService;
import com.yeoou.tour.service.IProvinceService;

public class AreaService extends HibernateEntityDao<Area> implements IAreaService
{
	public final String Query_All = "From Area where 1=1" ;
	private IContinentService continentService;
	private INationService nationService;
	private IProvinceService provinceService;
	private ICityService cityService;
	public ICityService getCityService()
	{
		return cityService;
	}
	public void setCityService(ICityService cityService)
	{
		this.cityService = cityService;
	}
	public IContinentService getContinentService()
	{
		return continentService;
	}
	public void setContinentService(IContinentService continentService)
	{
		this.continentService = continentService;
	}
	public INationService getNationService()
	{
		return nationService;
	}
	public void setNationService(INationService nationService)
	{
		this.nationService = nationService;
	}
	public IProvinceService getProvinceService()
	{
		return provinceService;
	}
	public void setProvinceService(IProvinceService provinceService)
	{
		this.provinceService = provinceService;
	}
	public List<Area> getChildren(String areaId)
	{
		// TODO Auto-generated method stub
		String hql = "from Area as area where area.parent = ? order by area.headName ASC";
		List<Area> areaList = this.find(hql, new Object[]{areaId});
		if(areaList.size()!=0) return areaList;
		else return null;
	}
	public Area getAreaChildren(String areaId)
	{
		Area area  = new Area();
		area = (Area)this.get(areaId);
		String hql = "from Area as area where area.parent = ?";
		List<Area> areaList = this.find(hql, new Object[]{areaId});
		area.setChildren(new HashSet<Area>(areaList));
		return area;
	}
	public List<TreeNode> setAreaTreeNodes(Area children)
	{
		// TODO Auto-generated method stub
		List<Area> areaChildren = new ArrayList<Area>();
		areaChildren.addAll(children.getChildren());
	    List<TreeNode> treenodes = new ArrayList<TreeNode>();		
		for(int i=0;i<areaChildren.size();i++){
			TreeNode treenodeleaf = new TreeNode();
			treenodeleaf.setId(areaChildren.get(i).getAreaId());
			treenodeleaf.setText(areaChildren.get(i).getName());
			List<Area> al = this.getChildren(areaChildren.get(i).getAreaId());
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
	public List<TreeNode> setAreaTreeNodes(Area children,int depth)
	{
		List<Area> areaChildren = new ArrayList<Area>();
		areaChildren.addAll(children.getChildren());
		List<TreeNode> treenodes = new ArrayList<TreeNode>();
		for(int i=0;i<areaChildren.size();i++){
			TreeNode treenodeleaf = new TreeNode();
			treenodeleaf.setId(areaChildren.get(i).getAreaId());
			treenodeleaf.setText(areaChildren.get(i).getName());
			if(areaChildren.get(i).getDepth()==depth)
			{
				treenodeleaf.setCls("file");
				treenodeleaf.setLeaf(true);
			}
			else
			{
				treenodeleaf.setCls("floder");
				treenodeleaf.setLeaf(false);
			}
			treenodes.add(treenodeleaf);
		}
		return treenodes;
	}
	public Page getAllArea(int start, int limit, String dir, String sort, String name, int depth,String areaId)
	{
		// TODO Auto-generated method stub
		List<Object> val = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("from Area as area where 1=1");
		if(!name.equals(""))
		{
			name = "%"+name+"%";
			sb.append(" and area.name like ?");
			val.add(name);
		}
		if(!areaId.equals(""))
		{
			sb.append(" and area.parent = ?");
			val.add(areaId);
		}
		if(depth!=10)
		{
			sb.append(" and area.depth = ?");
			val.add(depth);
		}
		sb.append(" order by area.");
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
	public void addArea(Area area)
	{
		this.save(area);
		switch(area.getDepth())
		{
			case 1:
				Continent con = new Continent();
				con.setArea(area);
				continentService.save(con);
				break;
			case 2:
				Nation nt = new Nation();
				nt.setArea(area);
				nationService.save(nt);
				break;
			case 3:
				Province pro = new Province();
				pro.setArea(area);
				provinceService.save(pro);
				break;
			case 4:
				City city = new City();
				city.setArea(area);
				cityService.save(city);
				break;
			case 5:
				City zone = new City();
				zone.setArea(area);
				cityService.save(zone);
				break;
			default:break;
		}
	}
	public void delArea(String areaId)
	{
		// TODO Auto-generated method stub
		Area area =  new Area();
		area = (Area)this.get(areaId);
		switch(area.getDepth())
		{
			case 1:
				Continent con = new Continent();
				con = continentService.getContinentByArea(areaId);
				continentService.removeById(con.getContinentId());
				break;
			case 2:
				Nation nt = new Nation();
				nt = nationService.getNationByArea(areaId);
				nationService.removeById(nt.getNationId());
				break;
			case 3:
				Province pro = new Province();
				pro = provinceService.getProvinceByArea(areaId);
				provinceService.removeById(pro.getProvinceId());
				break;
			case 4:
				City city = new City();
				city = cityService.getCityByArea(areaId);
				cityService.removeById(city.getCityId());
				break;
			case 5:
				City zone = new City();
				zone = cityService.getCityByArea(areaId);
				cityService.removeById(zone.getCityId());
				break;
			default:break;
		}
		this.removeById(areaId);
	}
	public List<Area> getAreaByDepth(int depth,int isChina)
	{
		// TODO Auto-generated method stub
		String hql="from Area as area where area.depth=? and area.isChina=? order by area.headName";
		List<Area> areaList = this.find(hql, new Object[]{depth,isChina});
		return areaList;
	}
	public List<Area> getAreaByDepth(int depth)
	{
		String hql="from Area as area where area.depth=? order by area.headName";
		List<Area> areaList = this.find(hql, new Object[]{depth});
		return areaList;
	}
	public List<Area> getChinaCity()
	{
		// TODO Auto-generated method stub
		return this.getAreaByDepth(4,1);
	}
	public List<Area> getCountry()
	{
		// TODO Auto-generated method stub
		return this.getAreaByDepth(2);
	}
	public List<Area> getProvince()
	{
		// TODO Auto-generated method stub
		return this.getAreaByDepth(3,1);
	}
	public List<Area> getState()
	{
		// TODO Auto-generated method stub
		return this.getAreaByDepth(1);
	}
	public Area getAreaByName(String name)
	{
		// TODO Auto-generated method stub
		Area area = new Area();
		StringBuilder  hqlSb = new StringBuilder(Query_All);
		hqlSb.append("And Name='").append(name).append("'");
		List<Area> areaList = this.find(hqlSb.toString());
		if(areaList.size()!=0)
		{
			area = areaList.get(0);
		}
		else
		{
			area = null;
		}
		return area;
	}
	
	@SuppressWarnings("unchecked")
	public Area getAreaByName(String name, int depth)
	{
		// TODO Auto-generated method stub
		String hql = "from Area as area where area.name=? and area.depth=?";
		List<Area> areaList = this.find(hql, new Object[]{name,depth});
		if(areaList.size()!=0) return areaList.get(0);
		else return null;
	}
	public List<Area> getAreaList(String areaId,int depth)
	{
		// TODO Auto-generated method stub
		List<Area> areaList = new ArrayList<Area>();
		Area area = new Area();
		area = (Area)this.get(areaId);
		areaList.add(area);
		while(area.getDepth()>depth)//2
		{
			String parentId = area.getParent();
			area = new Area();
			area = (Area)this.get(parentId);
			areaList.add(area);
		}
		List<Area> result = new ArrayList<Area>();
		for(int i=areaList.size()-1;i>=0;i--)
		{
			result.add(areaList.get(i));
		}
		return result;
	}
	public void addPic(File upload, String savePath,String fileName)
	{
		// TODO Auto-generated method stub
		if(upload!=null)
		{
			try
			{
				FileUtils.UploadFile(upload, savePath, fileName);
				
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	


}
