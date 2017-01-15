package com.yeoou.tour.web;

import java.util.Date;
import java.util.List;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.DateUtils;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.Manager;
import com.yeoou.tour.model.Visa;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.IVisaService;
/**
 * <p>
 * Title: 后台签证业务操作模块
 * </p>
 * <p>
 * Description: 对签证进行相关业务操作
 * 新增签证信息，修改签证信息，删除签证信息
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class VisaAction extends BaseActionSupport
{

	private static final long serialVersionUID = 1L;
	private IVisaService visaService;
	private IAreaService areaService;
	private int start = 0;
	private int limit = 15;
	private String sort;
	private String dir;
	private String jsonString = "";
	private String cityId = "";
	private String nationId = "";
	private String isTopic = "";
	private String advert = "";
	private String cycle = "";
	private String interview = "";
	private String name = "";
	private int pay = 0;
	private String term = "";
	private String visaId = "";
	private String delData;
	private String ziliao="";
	private Visa visaContent;

	/**
	 * 更新签证资料信息
	 * @return
	 */
	public String updateZiliao()
	{
		visaContent = (Visa)visaService.get(visaId);
		visaContent.setZiliao(ziliao);
		visaService.merge(visaContent);
		jsonString = "修改数据成功！";
		return SUCCESS;
	}
	/**
	 * fck编辑器获取签证资料信息
	 * @return
	 */
	public String getSingleVisa2()
	{
		visaContent = (Visa)visaService.get(visaId);
		return SUCCESS;
	}
	/**
	 * 添加签证信息
	 * @return
	 */
	public String addVisa()
	{
		//writter need acegi;
		Visa visa = new Visa();
		Date date = new Date();
		Area city = new Area();
		Area nation = new Area();
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		city = (Area)areaService.get(cityId);
		nation = (Area)areaService.get(nationId);
		visa.setAdvert(advert);
		visa.setCity(city);
		visa.setCreateDate(DateUtils.parseDate(DateUtils.formatDate(date)));
		visa.setCreateDateStr(DateUtils.formatDate(date));
		visa.setCycle(cycle);
		visa.setInterview(interview);
		if(isTopic.equals("false"))
		{
			visa.setIsTopic(0);
		}
		else
		{
			visa.setIsTopic(1);
		}
		visa.setName(name);
		visa.setNation(nation);
		visa.setPay(pay);
		visa.setTerm(term);
		visa.setWritter(manager.getName());
		visaService.save(visa);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 更新签证信息
	 * @return
	 */
	public String updateVisa()
	{
		Visa visa = new Visa();
		visa = (Visa)visaService.get(visaId);
		Date date = new Date();
		Area city = new Area();
		Area nation = new Area();
		city = (Area)areaService.get(cityId);
		nation = (Area)areaService.get(nationId);
		visa.setAdvert(advert);
		visa.setCity(city);
		visa.setCreateDate(DateUtils.parseDate(DateUtils.formatDate(date)));
		visa.setCreateDateStr(DateUtils.formatDate(date));
		visa.setCycle(cycle);
		visa.setInterview(interview);
		if(isTopic.equals("false"))
		{
			visa.setIsTopic(0);
		}
		else
		{
			visa.setIsTopic(1);
		}
		visa.setName(name);
		visa.setNation(nation);
		visa.setPay(pay);
		visa.setTerm(term);
		visa.setWritter("test");
		visaService.merge(visa);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 后台显示签证信息列表
	 * @return
	 */
	public String getAllVisa()
	{
		Page page = new Page();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,totalCount:");
		page = visaService.getAllVisa(sort, dir, start, limit, name, nationId);
		if(page!=null)
		{
			List<Visa> visaList = (List<Visa>)page.getResult();
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(visaList, null, new String[]{"class"}, null));
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
	 * 获取签证单一信息
	 * @return
	 */
	public String getSingleVisa()
	{
		Visa visa = new Visa();
		visa = (Visa)visaService.get(visaId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
    	jsonString = "{success:true,totalCount:1,results:["+jsonTool.getJsonString(visa, null, null, null)+"]}";
		return SUCCESS;
	}
	/**
	 * 删除签证信息
	 * @return
	 */
	public String delVisa()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				visaService.removeById(delData.trim());
				jsonString = "{success:true}";
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					visaService.removeById(id[i].trim());
				}
				jsonString = "{success:true}";
			}
		}
		return SUCCESS;
	}
	public String getAdvert()
	{
		return advert;
	}
	public void setAdvert(String advert)
	{
		this.advert = advert;
	}
	public IAreaService getAreaService()
	{
		return areaService;
	}
	public void setAreaService(IAreaService areaService)
	{
		this.areaService = areaService;
	}
	public String getCityId()
	{
		return cityId;
	}
	public void setCityId(String cityId)
	{
		this.cityId = cityId;
	}
	public String getCycle()
	{
		return cycle;
	}
	public void setCycle(String cycle)
	{
		this.cycle = cycle;
	}
	public String getDir()
	{
		return dir;
	}
	public void setDir(String dir)
	{
		this.dir = dir;
	}
	public String getInterview()
	{
		return interview;
	}
	public void setInterview(String interview)
	{
		this.interview = interview;
	}
	public String getIsTopic()
	{
		return isTopic;
	}
	public void setIsTopic(String isTopic)
	{
		this.isTopic = isTopic;
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
	public String getNationId()
	{
		return nationId;
	}
	public void setNationId(String nationId)
	{
		this.nationId = nationId;
	}
	public int getPay()
	{
		return pay;
	}
	public void setPay(int pay)
	{
		this.pay = pay;
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
	public String getTerm()
	{
		return term;
	}
	public void setTerm(String term)
	{
		this.term = term;
	}
	public IVisaService getVisaService()
	{
		return visaService;
	}
	public void setVisaService(IVisaService visaService)
	{
		this.visaService = visaService;
	}
	public Visa getVisaContent()
	{
		return visaContent;
	}
	public void setVisaContent(Visa visaContent)
	{
		this.visaContent = visaContent;
	}
	public String getZiliao()
	{
		return ziliao;
	}
	public void setZiliao(String ziliao)
	{
		this.ziliao = ziliao;
	}
	public String getDelData()
	{
		return delData;
	}
	public void setDelData(String delData)
	{
		this.delData = delData;
	}
	public String getVisaId()
	{
		return visaId;
	}
	public void setVisaId(String visaId)
	{
		this.visaId = visaId;
	}
}
