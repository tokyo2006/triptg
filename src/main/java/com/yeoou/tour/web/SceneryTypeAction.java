package com.yeoou.tour.web;

import java.util.List;

import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.SceneryType;
import com.yeoou.tour.service.ISceneryService;
import com.yeoou.tour.service.ISceneryTypeService;
/**
 * <p>
 * Title: 后台景点类别操作模块
 * </p>
 * <p>
 * Description: 添加景点类别信息，更新景点类别信息，删除景点类别信息，<br/>
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class SceneryTypeAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private ISceneryTypeService sceneryTypeService;
	private String jsonString = "";
	private String dir;
	private String sort;
	private int limit = 0;
	private int start = 0;
	private String typeId;
	private String name;
	private int flag = 0;
	private String desc = "";
	private String delData;
	private ISceneryService sceneryService;
	@SuppressWarnings("unchecked")
	/**
	 * 获取所有景点类别列表
	 */
	public String getAllSceneryType()
	{
		Page page = new Page();
		page = sceneryTypeService.getAllSceneryType(start, limit, dir, sort);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,totalCount:");
		if(page!=null)
		{
			List<SceneryType> scList = (List<SceneryType>)page.getResult();
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(scList, null, new String[]{"class"}, null));
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
	 * 获取单一景点类别信息
	 * @return
	 */
	public String getSingleSceneryType()
	{
		SceneryType sc = (SceneryType)sceneryTypeService.get(typeId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder();
		sb.append("{success:true,sceneryType:");
		sb.append(jsonTool.getJsonString(sc, null, new String[]{"class"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 添加景点类别信息
	 * @return
	 */
	public String addSceneryType()
	{
		SceneryType sc = new SceneryType();
		sc.setFlag(flag);
		sc.setName(name);
		sc.setDesc(desc);
		sceneryTypeService.save(sc);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 更新景点类别信息
	 * @return
	 */
	public String updateSceneryType()
	{
		SceneryType sc = new SceneryType();
		sc = (SceneryType)sceneryTypeService.get(typeId);
		sc.setFlag(flag);
		sc.setName(name);
		sc.setDesc(desc);
		sceneryTypeService.save(sc);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 删除景点类别信息（如果有此类别景点不允许删除）
	 * @return
	 */
	public String delSceneryType()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				
				int result = sceneryService.getCountSceneryByType(delData.trim());
				if(result!=0)
				{
					jsonString="{success:false,msg:'此类别有关联景点不允许删除!'}";
					return SUCCESS;
				}
				else
				{
					sceneryTypeService.removeById(delData.trim());
					jsonString="{success:true}";
				}
			}
			else
			{
				String[] id= this.delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					int result = sceneryService.getCountSceneryByType(id[i].trim());
					if(result!=0)
					{
						jsonString="{success:false,msg:'此类别有关联景点不允许删除!'}";
						return SUCCESS;
					}
					else
					{
						sceneryTypeService.removeById(id[i].trim());
						jsonString="{success:true}";
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public String getDir()
	{
		return dir;
	}
	public void setDir(String dir)
	{
		this.dir = dir;
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
	public ISceneryTypeService getSceneryTypeService()
	{
		return sceneryTypeService;
	}
	public void setSceneryTypeService(ISceneryTypeService sceneryTypeService)
	{
		this.sceneryTypeService = sceneryTypeService;
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
	public String getDelData()
	{
		return delData;
	}
	public void setDelData(String delData)
	{
		this.delData = delData;
	}
	public int getFlag()
	{
		return flag;
	}
	public void setFlag(int flag)
	{
		this.flag = flag;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public ISceneryService getSceneryService()
	{
		return sceneryService;
	}
	public void setSceneryService(ISceneryService sceneryService)
	{
		this.sceneryService = sceneryService;
	}
	public String getTypeId()
	{
		return typeId;
	}
	public void setTypeId(String typeId)
	{
		this.typeId = typeId;
	}
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	
}
