package com.yeoou.tour.web;

import java.util.List;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.rbac.model.User;
import com.yeoou.rbac.service.IUserService;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.Assemble;
import com.yeoou.tour.model.Manager;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.IAssembleService;


/**
 * <p>
 * Title: 后台集合上车地点操作模块
 * </p>
 * <p>
 * Description: 对集合地点进行相关业务操作
 * 新增集合地点，修改集合地点，删除集合地点
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class AssembleAction extends BaseActionSupport
{

	private static final long serialVersionUID = 1L;
	private IUserService userService;
	private IAreaService areaService;
	private IAssembleService assembleService;
	private String content="";
	private String areaId;
	private String delData;
	private String name="";
	private String assembleId;
	private String jsonString;
	private String dir;
	private String sort;
	private int start;
	private int limit;
	private int timeType;
	public String getArea()
	{
		return SUCCESS;
	}
	/**
	 * 更新集合地点信息
	 * @return
	 */
	public String updateAssemble()
	{
//		need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		String areaId = manager.getAreaId();
		Area area = new Area();
		area = (Area)areaService.get(areaId);
		Assemble assemble = new Assemble();
		assemble = (Assemble)assembleService.get(assembleId);
		assemble.setArea(area);
		assemble.setName(name);
		assembleService.merge(assemble);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 获取单一集合地点信息
	 * @return
	 */
	public String getSingleAssemble()
	{
		Assemble assemble = new Assemble();
		assemble =(Assemble)assembleService.get(assembleId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
    	jsonString = "{success:true,totalCount:1,results:["+jsonTool.getJsonString(assemble, null, new String[]{"class","user.class","area.class"}, null)+"]}";
		return SUCCESS;
	}
	/**
	 * 删除集合地点信息
	 * @return
	 */
	public String delAssemble()
	{
		if(delData!=null)
		{
			if(this.delData.indexOf(",")<0)
			{
					assembleService.removeById(delData.trim());
			}
			else
			{
				String[] id= this.delData.split(",");
				for(int i=0;i<id.length;i++)
				{
						assembleService.removeById(id[i].trim());
				}
			}
			jsonString="{success:true}";
		}
		return SUCCESS;
	}
	/**
	 * 后台显示所有集合地点信息
	 * @return
	 */
	public String getAllAssemble()
	{
//		need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		String userId = manager.getBossId();
		String areaId = manager.getAreaId();
		Page page = new Page();
		page = assembleService.getAllAssemble(userId, areaId, start, limit, dir, sort);
		if(page!=null)
		{
			this.setTotalCount(page.getTotalCount());
			List<Assemble> assembleList = (List<Assemble>)page.getResult();
			FlexJsonUtils jsonTool = new FlexJsonUtils();
			jsonString = "{success:true,totalCount:"+this.getTotalCount()+",results:"+jsonTool.getJsonString(assembleList, null, new String[]{"class","user","user.class","area.class"}, null)+"}";
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	/**
	 * 添加集合地点信息
	 * @return
	 */
	public String addAssemble()
	{
//		need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		Area area = new Area();
		String areaId = manager.getAreaId();
		area = (Area)areaService.get(areaId);
		User user = new User();
		user = userService.getUserById(manager.getBossId());//userId
		Assemble assemble = new Assemble();
		assemble.setArea(area);
		assemble.setName(name);
		assemble.setUser(user);
		assembleService.save(assemble);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	public String getJsonString()
	{
		return jsonString;
	}
	public void setJsonString(String jsonString)
	{
		this.jsonString = jsonString;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
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

	public String getSort()
	{
		return sort;
	}
	public void setSort(String sort)
	{
		this.sort = sort;
	}
	public int getLimit()
	{
		return limit;
	}
	public void setLimit(int limit)
	{
		this.limit = limit;
	}
	public int getStart()
	{
		return start;
	}
	public void setStart(int start)
	{
		this.start = start;
	}
	public String getAssembleId()
	{
		return assembleId;
	}
	public void setAssembleId(String assembleId)
	{
		this.assembleId = assembleId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public IUserService getUserService()
	{
		return userService;
	}
	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}
	public int getTimeType()
	{
		return timeType;
	}
	public void setTimeType(int timeType)
	{
		this.timeType = timeType;
	}
	public IAreaService getAreaService() {
		return areaService;
	}
	public void setAreaService(IAreaService areaService) {
		this.areaService = areaService;
	}
	public IAssembleService getAssembleService() {
		return assembleService;
	}
	public void setAssembleService(IAssembleService assembleService) {
		this.assembleService = assembleService;
	}

	
}
