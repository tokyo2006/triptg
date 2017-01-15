package com.yeoou.tour.web;


import java.util.List;

import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.utils.StringUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.IPRouter;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.IIPRouterService;
/**
 * <p>
 * Title: 后台IP地域跳转操作模块
 * </p>
 * <p>
 * Description:后台IP地域跳转设置，添加IP地域映射表，<br/>
 * 修改IP地域跳转映射表，删除IP地域跳转映射表
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class IPRouterAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IIPRouterService iprouterService;
	private IAreaService areaService;
	private String areaId = "";
	private String endIp = "";
	private String reUrl = "";
	private String startIp = "";
	private String jsonString="";
	private String ipId = "";
	private String delData="";
	private int start = 0;
	private int limit = 15;
	private String dir = "";
	private String sort = "";
	/**
	 * 添加IP地域映射信息
	 * @return
	 */
	public String addIPRouter()
	{
		IPRouter iprouter = new IPRouter();
		iprouter.setAreaId(areaId);
		iprouter.setEndIp(StringUtils.getIPStringToLong(endIp));
		iprouter.setEndIpStr(endIp);
		iprouter.setStartIpStr(startIp);
		iprouter.setReUrl(reUrl);
		iprouter.setStartIp(StringUtils.getIPStringToLong(startIp));
		iprouterService.save(iprouter);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 获取单一IP地域映射信息
	 * @return
	 */
	public String getSingleIPRouter()
	{
		IPRouter iprouter = new IPRouter();
		Area area = new Area();
		iprouter = (IPRouter)iprouterService.get(ipId );
		area = (Area)areaService.get(iprouter.getAreaId());
		FlexJsonUtils jsonTool = new FlexJsonUtils();
    	jsonString = "{success:true,iprouter:"+jsonTool.getJsonString(iprouter, null, new String[]{"class","user"}, null)+",area:"+jsonTool.getJsonString(area, null, new String[]{"class"}, null)+"}";
		return SUCCESS;
	}
	/**
	 * 更新IP地域映射信息
	 * @return
	 */
	public String updateIPRouter()
	{
		IPRouter iprouter = new IPRouter();
		iprouter = (IPRouter)iprouterService.get(ipId );
		iprouter.setAreaId(areaId);
		iprouter.setEndIp(StringUtils.getIPStringToLong(endIp));
		iprouter.setEndIpStr(endIp);
		iprouter.setStartIpStr(startIp);
		iprouter.setReUrl(reUrl);
		iprouter.setStartIp(StringUtils.getIPStringToLong(startIp));
		iprouterService.merge(iprouter);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 删除IP地域映射信息
	 * @return
	 */
	public String delIPRouter()
	{
		if(!delData.equals(""))
		{
			if(delData.indexOf(",")<0)
			{
				iprouterService.removeById(delData.trim());
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					iprouterService.removeById(id[i].trim());
				}
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 显示IP地域跳转列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllIPRouter()
	{
		Page page = new Page();
		page = iprouterService.getAllIPRouter(start, limit, sort, dir);
		if(page!=null)
		{
			this.setTotalCount(page.getTotalCount());
			@SuppressWarnings("unused")
			List<IPRouter> ipList = (List<IPRouter>)page.getResult();
			int size = ipList.size();
			for(int i=0;i<size;i++)
			{
				Area area = new Area();
				area = (Area)areaService.get(ipList.get(i).getAreaId());
				ipList.get(i).setAreaName(area.getName());
			}
			FlexJsonUtils jsonTool = new FlexJsonUtils();
			jsonString = "{success:true,totalCount:"+this.getTotalCount()+",results:"+jsonTool.getJsonString(ipList, null, new String[]{"class","user","user.class","area.class"}, null)+"}";
			
		}
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
	public String getDir()
	{
		return dir;
	}
	public void setDir(String dir)
	{
		this.dir = dir;
	}
	public String getEndIp()
	{
		return endIp;
	}
	public void setEndIp(String endIp)
	{
		this.endIp = endIp;
	}
	public String getIpId()
	{
		return ipId;
	}
	public void setIpId(String ipId)
	{
		this.ipId = ipId;
	}
	public IIPRouterService getIprouterService()
	{
		return iprouterService;
	}
	public void setIprouterService(IIPRouterService iprouterService)
	{
		this.iprouterService = iprouterService;
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
	public String getReUrl()
	{
		return reUrl;
	}
	public void setReUrl(String reUrl)
	{
		this.reUrl = reUrl;
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
	public String getStartIp()
	{
		return startIp;
	}
	public void setStartIp(String startIp)
	{
		this.startIp = startIp;
	}
	
}
