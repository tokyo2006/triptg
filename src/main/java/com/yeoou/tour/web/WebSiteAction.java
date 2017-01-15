package com.yeoou.tour.web;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.utils.SerialNoUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.WebSite;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.ISceneryPicService;
import com.yeoou.tour.service.IWebSiteService;
/**
 * <p>
 * Title: 后台网站信息业务操作模块
 * </p>
 * <p>
 * Description: 对网站信息进行相关业务操作
 * 新增网站信息，修改网站信息，删除网站信息
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class WebSiteAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IWebSiteService webSiteService;
	private IAreaService areaService;
	private ISceneryPicService sceneryPicService;
	private String address = "";
	private String cjphone = "";
	private String domainName = "";
	private String gnphone = "";
	private String logo = "";
	private String siteName = "";
	private String title = "";
	private String zbphone = "";
	private String zjphone = "";
	private String smallLogo = "";
	private String jsonString = "";
	private String uploadFileName;
	private String uploadContentType;
	private String savePath;
	private File upload;
	private String areaId;
	private String siteId;
	private int start = 0;
	private int limit = 15;
	private String dir = "";
	private String sort = "";
	private String delData = "";
	private String bannercj = "";
	private String bannergn = "";
	private String bannerzb = "";
	private String bannerzyx = "";
	private String bannerLogo = "";
	private String desc="";
	private String keywords = "";

	/**
	 * 上传图片模块
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String uploadPic()
	{
		String fileName = "";
		String url = "";
		String fileType = "";
		if(!logo.equals(""))
		{
			sceneryPicService.delPicture(logo);
		}
		savePath = ServletActionContext.getRequest().getRealPath(savePath)+"\\"+"image"+"\\"+"logo";
		if(upload!=null)
		{
			fileType = uploadFileName.substring(uploadFileName.indexOf(".")+1, uploadFileName.length());
			fileName = SerialNoUtils.getSerialNo()+"."+fileType;
			url = "upload"+"/"+"image"+"/"+"logo"+"/"+fileName;
		}
		sceneryPicService.addPic(upload,savePath,fileName,fileType);
		jsonString = "{success:true,\"logo\":\""+url+"\"}";
		return SUCCESS;
	}
	/**
	 * 添加网站信息
	 * @return
	 */
	public String addWebSite()
	{
		WebSite webSite = new WebSite();
		Area area = new Area();
		area = (Area)areaService.get(areaId);
		webSite.setArea(area);
		webSite.setAddress(address);
		webSite.setCjphone(cjphone);
		webSite.setDomainName(domainName);
		webSite.setGnphone(gnphone);
		webSite.setLogo(logo);
		webSite.setBannercj(bannercj);
		webSite.setBannergn(bannergn);
		webSite.setBannerzb(bannerzb);
		webSite.setBannerzyx(bannerzyx);
		webSite.setBannerLogo(bannerLogo);
		webSite.setSmallLogo(smallLogo);
		webSite.setSiteName(siteName);
		webSite.setTitle(title);
		webSite.setZbphone(zbphone);
		webSite.setZjphone(zjphone);
		webSite.setDesc(desc);
		webSite.setKeywords(keywords);
		webSiteService.save(webSite);
		
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 获取网站单一信息
	 * @return
	 */
	public String getSingleWebSite()
	{
		WebSite webSite = new WebSite();
		webSite = (WebSite)webSiteService.get(siteId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
    	jsonString = "{success:true,totalCount:1,results:["+jsonTool.getJsonString(webSite, null, null, null)+"]}";
		return SUCCESS;
	}
	/**
	 * 更新网站信息内容
	 * @return
	 */
	public String updateWebSite()
	{
		WebSite webSite = new WebSite();
		webSite = (WebSite)webSiteService.get(siteId);
		Area area = new Area();
		area = (Area)areaService.get(areaId);
		webSite.setArea(area);
		webSite.setAddress(address);
		webSite.setCjphone(cjphone);
		webSite.setDomainName(domainName);
		webSite.setGnphone(gnphone);
		webSite.setLogo(logo);
		webSite.setBannercj(bannercj);
		webSite.setBannergn(bannergn);
		webSite.setBannerzb(bannerzb);
		webSite.setBannerzyx(bannerzyx);
		webSite.setSmallLogo(smallLogo);
		webSite.setBannerLogo(bannerLogo);
		webSite.setSiteName(siteName);
		webSite.setTitle(title);
		webSite.setZbphone(zbphone);
		webSite.setZjphone(zjphone);
		webSite.setDesc(desc);
		webSite.setKeywords(keywords);
		webSiteService.merge(webSite);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 后台显示网站信息列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllWebSite()
	{
		Page page = new Page();
		page = webSiteService.getAllWebSite(start, limit, dir, sort);
		if(page!=null)
		{
			List<WebSite> wbList = (List<WebSite>)page.getResult();
			this.setTotalCount(page.getTotalCount());
			FlexJsonUtils jsonTool = new FlexJsonUtils();
        	this.jsonString = "{success:true,totalCount:"+this.getTotalCount()+",results:"+jsonTool.getJsonString(wbList, null, null, null)+"}";
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	/**
	 * 删除网站信息
	 * @return
	 */
	public String delWebSite()
	{
		if(!delData.equals(""))
		{
			if(delData.indexOf(",")<0)
			{
				WebSite  wb = new WebSite();
				wb = (WebSite)webSiteService.get(delData.trim());
				sceneryPicService.delPicture(wb.getLogo());
				webSiteService.remove(wb);
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					WebSite  wb = new WebSite();
					wb = (WebSite)webSiteService.get(id[i].trim());
					sceneryPicService.delPicture(wb.getLogo());
					webSiteService.remove(wb);
				}
			}
			jsonString = "{success:true}";
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
	public int getLimit()
	{
		return limit;
	}
	public void setLimit(int limit)
	{
		this.limit = limit;
	}
	public ISceneryPicService getSceneryPicService()
	{
		return sceneryPicService;
	}
	public void setSceneryPicService(ISceneryPicService sceneryPicService)
	{
		this.sceneryPicService = sceneryPicService;
	}
	public String getSiteId()
	{
		return siteId;
	}
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
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
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
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
	public String getCjphone()
	{
		return cjphone;
	}
	public void setCjphone(String cjphone)
	{
		this.cjphone = cjphone;
	}
	public String getDomainName()
	{
		return domainName;
	}
	public void setDomainName(String domainName)
	{
		this.domainName = domainName;
	}
	public String getGnphone()
	{
		return gnphone;
	}
	public void setGnphone(String gnphone)
	{
		this.gnphone = gnphone;
	}
	public String getJsonString()
	{
		return jsonString;
	}
	public void setJsonString(String jsonString)
	{
		this.jsonString = jsonString;
	}
	public String getLogo()
	{
		return logo;
	}
	public void setLogo(String logo)
	{
		this.logo = logo;
	}
	public String getSavePath()
	{
		return savePath;
	}
	public void setSavePath(String savePath)
	{
		this.savePath = savePath;
	}
	public String getSiteName()
	{
		return siteName;
	}
	public void setSiteName(String siteName)
	{
		this.siteName = siteName;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public File getUpload()
	{
		return upload;
	}
	public void setUpload(File upload)
	{
		this.upload = upload;
	}
	public String getUploadContentType()
	{
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType)
	{
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName()
	{
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName)
	{
		this.uploadFileName = uploadFileName;
	}
	public IWebSiteService getWebSiteService()
	{
		return webSiteService;
	}
	public void setWebSiteService(IWebSiteService webSiteService)
	{
		this.webSiteService = webSiteService;
	}
	public String getZbphone()
	{
		return zbphone;
	}
	public void setZbphone(String zbphone)
	{
		this.zbphone = zbphone;
	}
	public String getZjphone()
	{
		return zjphone;
	}
	public void setZjphone(String zjphone)
	{
		this.zjphone = zjphone;
	}
	public String getDelData() {
		return delData;
	}
	public void setDelData(String delData) {
		this.delData = delData;
	}
	public String getBannercj()
	{
		return bannercj;
	}
	public void setBannercj(String bannercj)
	{
		this.bannercj = bannercj;
	}
	public String getBannergn()
	{
		return bannergn;
	}
	public void setBannergn(String bannergn)
	{
		this.bannergn = bannergn;
	}
	public String getBannerzb()
	{
		return bannerzb;
	}
	public void setBannerzb(String bannerzb)
	{
		this.bannerzb = bannerzb;
	}
	public String getBannerzyx()
	{
		return bannerzyx;
	}
	public void setBannerzyx(String bannerzyx)
	{
		this.bannerzyx = bannerzyx;
	}
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getKeywords()
	{
		return keywords;
	}
	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}
	public String getBannerLogo()
	{
		return bannerLogo;
	}
	public void setBannerLogo(String bannerLogo)
	{
		this.bannerLogo = bannerLogo;
	}
	public String getSmallLogo()
	{
		return smallLogo;
	}
	public void setSmallLogo(String smallLogo)
	{
		this.smallLogo = smallLogo;
	}
}
