package com.yeoou.tour.web;

import java.util.List;

import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.KeyWord;
import com.yeoou.tour.service.IKeyWordService;
import com.yeoou.tour.service.ISceneryService;
/**
 * @deprecated
 * <p>
 * 标题: 后台关键字相关操作
 * </p>
 * <p>
 * 描述: 添加关键字，删除关键字，更新关键字信息
 * </p>
 * 
 * @author kensin
 * @version 1.0
 * @created 2008-12-16
 */
public class KeyWordAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IKeyWordService keyWordService;
	private ISceneryService sceneryService;
	private int start = 0;
	private int limit = 0;
	private String dir;
	private String sort;
	private String kpId;
	private String name = "";
	private String url = "";
	private String jsonString = "";
	private String delData;
	public String getAllKeyWord()
	{
		Page page =new Page();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,totalCount:");
		page = keyWordService.getAllKeyWord(start, limit, dir, sort, name);
		if(page!=null)
		{
			List<KeyWord> keyWordList = (List<KeyWord>)page.getResult();
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(keyWordList, null, new String[]{"class"}, null));
			sb.append("}");
			jsonString = sb.toString();
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	public String addKeyWord()
	{
		KeyWord kp = new KeyWord();
		kp.setName(name);
		kp.setUrl(url);
		keyWordService.save(kp);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	public String updateKeyWord()
	{
		KeyWord kp = new KeyWord();
		kp = (KeyWord)keyWordService.get(kpId);
		kp.setName(name);
		kp.setUrl(url);
		keyWordService.merge(kp);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	public String getSingleKeyWord()
	{
		KeyWord kp = new KeyWord();
		kp = (KeyWord)keyWordService.get(kpId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder();
		sb.append("{success:true,keyWord:");
		sb.append(jsonTool.getJsonString(kp, null, new String[]{"class"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	public String delKeyWord()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				
					keyWordService.removeById(delData.trim());
					jsonString="{success:true}";
			}
			else
			{
				String[] id= this.delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					keyWordService.removeById(id[i].trim());
					jsonString="{success:true}";
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
	public IKeyWordService getKeyWordService()
	{
		return keyWordService;
	}
	public void setKeyWordService(IKeyWordService keyWordService)
	{
		this.keyWordService = keyWordService;
	}
	public String getKpId()
	{
		return kpId;
	}
	public void setKpId(String kpId)
	{
		this.kpId = kpId;
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
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getDelData()
	{
		return delData;
	}
	public void setDelData(String delData)
	{
		this.delData = delData;
	}
	public ISceneryService getSceneryService()
	{
		return sceneryService;
	}
	public void setSceneryService(ISceneryService sceneryService)
	{
		this.sceneryService = sceneryService;
	}
}
