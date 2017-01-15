package com.yeoou.tour.web;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.Region;
import com.yeoou.tour.model.TreeNode;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.IRegionService;
/**
 * <p>
 * Title: 后台旅游类别操作模块
 * </p>
 * <p>
 * Description: 添加旅游类别，更新旅游类别，删除旅游操作<br/>
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class RegionAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IRegionService regionService;
	private IAreaService areaService;
	private String regionId;
	private int start = 0;
	private int limit =15;
	private String dir = "";
	private String sort ="";
	private String name = "";
	private int depth = 10;
	private int flag = 10;
	private String jsonString ="";
	private String parentId;
	private String areaId="";
	private String fullName = "";
	private int orderBy = 0;
	private String headName = "";
	private String delData = "";
	private String node = "";
	/**
	 * 显示旅游类别列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllRegion()
	{
		Page page = new Page();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,totalCount:");
		page = regionService.getAllRegion(start, limit, dir, sort, name, depth, flag,node);
		if(page!=null)
		{
			List<Region> regionList = (List<Region>)page.getResult();
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(regionList, null, new String[]{"class"}, null));
			sb.append("}");
			jsonString = sb.toString();
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	/**
	 * 添加旅游类别
	 * @return
	 */
	public String addRegion()
	{
		Region region = new Region();
		Region parent = new Region();
		Area area = new Area();
		parent = (Region)regionService.get(parentId);
		if(!areaId.equals(""))
		{
			area = (Area)areaService.get(areaId);
			region.setArea(area);
		}
		else
		{
			region.setArea(null);
		}
		region.setDepth(parent.getDepth()+1);
		region.setFlag(flag);
		region.setFullName(fullName);
		region.setHeadName(headName);
		region.setName(name);
		region.setOrderBy(orderBy);
		region.setParent(parent.getRegionId());
		regionService.save(region);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 获取单一的旅游类别信息
	 * @return
	 */
	public String getSingleRegion()
	{
		Region region = new Region();
		Region pregion = new Region();
		region = (Region) regionService.get(regionId);
		pregion = (Region)regionService.get(region.getParent());
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder();
		sb.append("{success:true,region:");
		sb.append(jsonTool.getJsonString(region, null, new String[]{"class"}, null));
		sb.append(",pregion:");
		sb.append(jsonTool.getJsonString(pregion, null, new String[]{"class"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 更新旅游类别信息
	 * @return
	 */
	public String updateRegion()
	{
		Region region = new Region();
		Region parent = new Region();
		Area area = new Area();
		if(!areaId.equals(""))
		{
			area = (Area)areaService.get(areaId);
			region.setArea(area);
		}
		else
		{
			region.setArea(null);
		}
		region = (Region)regionService.get(regionId);
		parent = (Region)regionService.get(parentId);
		region.setDepth(parent.getDepth()+1);
		region.setFlag(flag);
		region.setFullName(fullName);
		region.setHeadName(headName);
		region.setName(name);
		region.setOrderBy(orderBy);
		region.setParent(parent.getRegionId());
		regionService.merge(region);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 删除旅游类别（如果有子类则不允许删除）
	 * @return
	 */
	public String delRegion()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				
				List<Region> al = regionService.getChildren(delData.trim());
				if(al!=null)
				{
					jsonString="{success:false,msg:'此地点有关联地点不允许删除!'}";
					return SUCCESS;
				}
				else
				{
					regionService.removeById(delData.trim());
					jsonString="{success:true}";
				}
			}
			else
			{
				String[] id= this.delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					List<Region> al = regionService.getChildren(id[i].trim());
					if(al!=null)
					{
						jsonString="{success:false,msg:'此地点有关联地点不允许删除!'}";
						return SUCCESS;
					}
					else
					{
						regionService.removeById(id[i].trim());
						jsonString="{success:true}";
					}
				}
			}
		}
		return SUCCESS;
	}
	/**
	 * 构件旅游类别树形菜单
	 * @return
	 */
	public String getRegionChildrenByNode()
	{
		List<TreeNode> childlist = new ArrayList<TreeNode>();
		Region region = new Region();
		if(node.equals("-1"))
		{
			region = regionService.getRegionChildren("402880e71a7fabda011a7faf12fa0001");
			childlist = regionService.setRegionTreeNodes(region);
		}
		else
		{
			region = regionService.getRegionChildren(node.trim());
			childlist = regionService.setRegionTreeNodes(region);
		}
		
		JSONArray jsarray = JSONArray.fromObject(childlist);
		jsonString=jsarray.toString();
		return SUCCESS;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public IAreaService getAreaService()
	{
		return areaService;
	}
	public void setAreaService(IAreaService areaService)
	{
		this.areaService = areaService;
	}
	public String getDelData()
	{
		return delData;
	}
	public void setDelData(String delData)
	{
		this.delData = delData;
	}
	public int getDepth()
	{
		return depth;
	}
	public void setDepth(int depth)
	{
		this.depth = depth;
	}
	public String getDir()
	{
		return dir;
	}
	public void setDir(String dir)
	{
		this.dir = dir;
	}
	public int getFlag()
	{
		return flag;
	}
	public void setFlag(int flag)
	{
		this.flag = flag;
	}
	public String getFullName()
	{
		return fullName;
	}
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}
	public String getHeadName()
	{
		return headName;
	}
	public void setHeadName(String headName)
	{
		this.headName = headName;
	}
	public String getJsonString()
	{
		return jsonString;
	}
	public void setJsonString(String jsonString)
	{
		this.jsonString = jsonString;
	}
	public int getLimit()
	{
		return limit;
	}
	public void setLimit(int limit)
	{
		this.limit = limit;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getNode()
	{
		return node;
	}
	public void setNode(String node)
	{
		this.node = node;
	}
	public int getOrderBy()
	{
		return orderBy;
	}
	public void setOrderBy(int orderBy)
	{
		this.orderBy = orderBy;
	}
	public String getParentId()
	{
		return parentId;
	}
	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}
	public String getRegionId()
	{
		return regionId;
	}
	public void setRegionId(String regionId)
	{
		this.regionId = regionId;
	}
	public IRegionService getRegionService()
	{
		return regionService;
	}
	public void setRegionService(IRegionService regionService)
	{
		this.regionService = regionService;
	}
	public String getSort()
	{
		return sort;
	}
	public void setSort(String sort)
	{
		this.sort = sort;
	}
	public int getStart()
	{
		return start;
	}
	public void setStart(int start)
	{
		this.start = start;
	}
}
