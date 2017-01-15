package com.yeoou.tour.web;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.NewsType;
import com.yeoou.tour.model.TreeNode;
import com.yeoou.tour.service.INewsTypeService;
/**
 * <p>
 * Title: 后台新闻类别操作模块
 * </p>
 * <p>
 * Description: 添加新闻类别，更新新闻类别，删除新闻操作<br/>
 * 新闻类别范围父类和子类两层仅仅只有2层类别
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class NewsTypeAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private INewsTypeService newsTypeService;
	private String typeId = "";
	private String name;
	private String desc = "";
	private String jsonString = "";
	private String sort = "";
	private String dir = "";
	private int start = 0;
	private String node="";
	private int limit = 15;
	private String parent="";
	private String delData ;
	/**
	 * 添加新闻类别
	 * @return
	 */
	public String addNewsType()
	{
		NewsType nt = new NewsType();
		nt.setDesc(desc);
		nt.setName(name);
		nt.setParent(parent);
		newsTypeService.save(nt);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 获取新闻信息类别列表
	 * @return
	 */
	public String getNewsInfo()
	{
		List<NewsType> ntList = new ArrayList<NewsType>();
		ntList = newsTypeService.getChildren("");
		int length = ntList.size();
		if(length != 0)
		{
			StringBuilder sb = new StringBuilder("[");
			for(int i=0;i<length-1;i++)
			{
				sb.append("[");
				sb.append("\'");
				sb.append(ntList.get(i).getTypeId());
				sb.append("\'");
				sb.append(",");
				sb.append("\'");
				sb.append(ntList.get(i).getName());
				sb.append("\'");
				sb.append("]");
				sb.append(",");
			}
			sb.append("[");
			sb.append("\'");
			sb.append(ntList.get(length-1).getTypeId());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(ntList.get(length-1).getName());
			sb.append("\'");
			sb.append("]");
			sb.append("]");
			jsonString = sb.toString();
		}
		else
		{
			jsonString = "[]";
		}
		return SUCCESS;
	}
	/**
	 * 更新新闻类别信息
	 * @return
	 */
	public String updateNewsType()
	{
		NewsType nt = new NewsType();
		nt = (NewsType)newsTypeService.get(typeId);
		nt.setDesc(desc);
		nt.setName(name);
		nt.setParent(parent);
		newsTypeService.merge(nt);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 获取单一新闻类别信息
	 * @return
	 */
	public String getSingleNewsType()
	{
		NewsType nt = new NewsType();
		NewsType pnt = new NewsType();
		nt = (NewsType)newsTypeService.get(typeId);
		if(!nt.getParent().equals(""))
		{
			pnt = (NewsType)newsTypeService.get(nt.getParent());
		}
		else
		{
			pnt = null;
		}
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,newsType:");
		sb.append(jsonTool.getJsonString(nt, null, null, null));
		sb.append(",parent:");
		sb.append(jsonTool.getJsonString(pnt, null, null, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 构件树形菜单
	 * @return
	 */
	public String getTypeByNode()
	{
		List<TreeNode> childlist = new ArrayList<TreeNode>();
		NewsType nt = new NewsType();
		if(node.equals("-1"))
		{
			List<NewsType> ntList = newsTypeService.getChildren("");
			childlist=newsTypeService.setTypeTreeNodes(ntList);
		}
		else
		{
			nt = (NewsType)newsTypeService.getNewsTypeChildren(node.trim());
			childlist=newsTypeService.setTypeTreeNodes(nt);
		}
		JSONArray jsarray = JSONArray.fromObject(childlist);
		jsonString=jsarray.toString();
		return SUCCESS;
	}
	/**
	 * 删除新闻类别
	 * @return
	 */
	public String deleteNewsType()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				List<NewsType> nt = newsTypeService.getChildren(delData.trim());
				if(nt.size()!=0)
				{
					jsonString = "{success:false,msg:'有子类，不允许删除'}";
				}
				else
				{
					int result = newsTypeService.getCountByNewsType(delData.trim());
					if(result!=0)
					{
						jsonString = "{success:false,msg:'有新闻，不允许删除'}";
					}
					else
					{
						newsTypeService.removeById(delData.trim());
						jsonString = "{success:true}";
					}
				}
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					List<NewsType> nt = newsTypeService.getChildren(id[i].trim());
					if(nt.size()!=0)
					{
						jsonString = "{success:false,msg:'有子类，不允许删除'}";
					}
					else
					{
						int result = newsTypeService.getCountByNewsType(id[i].trim());
						if(result!=0)
						{
							jsonString = "{success:false,msg:'有新闻，不允许删除'}";
						}
						else
						{
							newsTypeService.removeById(id[i].trim());
							jsonString = "{success:true}";
						}
					}
				}
			}
		}
		return SUCCESS;
	}
	/**
	 * 获取所有新闻类别列表
	 * @return
	 */
	public String getAllNewsType()
	{
		Page page = new Page();
		page = newsTypeService.getAllNewsType(dir, sort, start, limit,typeId);
		if(page!=null)
		{
			List<NewsType> traderList=(List<NewsType>)page.getResult();
			for(int i=0;i<traderList.size();i++)
			{
				NewsType nt = new NewsType();
				if(!traderList.get(i).getParent().equals(""))
				{
					nt = (NewsType)newsTypeService.get(traderList.get(i).getParent());
				}
				else
				{
					nt.setName("无父类别");
				}
				traderList.get(i).setPnt(nt);
			}
			StringBuilder sb = new StringBuilder("{success:true,totalCount:");
			sb.append(page.getTotalCount());
			sb.append(",results:");
			FlexJsonUtils jsonTool = new FlexJsonUtils();
			sb.append(jsonTool.getJsonString(traderList, null, new String[]{"class"}, null));
			sb.append("}");
			jsonString = sb.toString();
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
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

	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public INewsTypeService getNewsTypeService()
	{
		return newsTypeService;
	}
	public void setNewsTypeService(INewsTypeService newsTypeService)
	{
		this.newsTypeService = newsTypeService;
	}
	public String getTypeId()
	{
		return typeId;
	}
	public void setTypeId(String typeId)
	{
		this.typeId = typeId;
	}
	public String getParent()
	{
		return parent;
	}
	public void setParent(String parent)
	{
		this.parent = parent;
	}
	public String getDelData()
	{
		return delData;
	}
	public void setDelData(String delData)
	{
		this.delData = delData;
	}
	public String getNode()
	{
		return node;
	}
	public void setNode(String node)
	{
		this.node = node;
	}
	
}
