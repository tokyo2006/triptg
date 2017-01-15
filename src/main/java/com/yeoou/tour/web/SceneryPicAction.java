package com.yeoou.tour.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.Scenery;
import com.yeoou.tour.model.SceneryPic;
import com.yeoou.tour.service.ISceneryPicService;
import com.yeoou.tour.service.ISceneryService;
/**
 * @deprecated
 * 景点图片相关图片的操作（已取消）
 * @author kensin
 *
 */
public class SceneryPicAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private String savePath;
	private File upload;
	private ISceneryPicService sceneryPicService;
	private ISceneryService sceneryService;
	private String uploadFileName;
	private String uploadContentType;
	private String name = "";
	private String picId;
	private String sceneryId;
	private String dir;
	private String sort;
	private String delData;
	private int start = 0;
	private int limit = 15;
	private String url;
	private String brUrl;
	private String areaId;
	private String jsonString = "";
	@SuppressWarnings("deprecation")
	public String addPic()
	{
//		String fileName = "";
//		String url = "";
//		String brUrl = "";
//		String fileType = "";
//		savePath = ServletActionContext.getRequest().getRealPath(savePath)+"\\"+"image";
//		if(upload!=null)
//		{
//			fileType = uploadFileName.substring(uploadFileName.indexOf(".")+1, uploadFileName.length());
//			fileName = name+"."+fileType;
//			url = "upload"+"/"+"image"+"/"+fileName;
//			brUrl = "upload"+"/"+"image"+"/"+"bre"+fileName;
//		}
//		String breSavePath = savePath + "\\"+"bre"+fileName;
		SceneryPic pic = new SceneryPic();
		Scenery sc = new Scenery();
		sc = sceneryService.getSceneryInfo(sceneryId);
		Set<Area> areas = new HashSet<Area>();
		areas = sc.getAreas();
		pic.setAreas(areas);
		pic.setScenery(sc);
		pic.setName(name);
		pic.setBreviaryUrl(brUrl);
		pic.setUrl(url);
//		sceneryPicService.addPic(pic,upload,savePath,breSavePath,fileName,fileType);
		sceneryPicService.save(pic);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	public String uploadPic()
	{
		String fileName = "";
		String url = "";
		String brUrl = "";
		String fileType = "";
		String miniUrl = "";
		savePath = ServletActionContext.getRequest().getRealPath(savePath)+"\\"+"image"+"\\"+"scenery";
		if(upload!=null)
		{
			fileType = uploadFileName.substring(uploadFileName.indexOf(".")+1, uploadFileName.length());
			fileName = name+"."+fileType;
			url = "upload"+"/"+"image"+"/"+"scenery"+"/"+fileName;
			brUrl = "upload"+"/"+"image"+"/"+"scenery"+"/"+"bre"+fileName;
			miniUrl = "upload"+"/"+"image"+"/"+"scenery"+"/"+"mini"+fileName;
		}
		String breSavePath = savePath + "\\"+"bre"+fileName;
		sceneryPicService.addPic(upload,savePath,breSavePath,fileName,fileType);
		jsonString = "{success:true,\"brUrl\":\""+brUrl+"\","+"\"url\":\""+url+"\"}";
		return SUCCESS;
	}
	public String getSinglePic()
	{
		SceneryPic pic = new SceneryPic();
		pic = sceneryPicService.getSceneryPic(picId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,pic:");
		sb.append(jsonTool.getJsonString(pic, new String[]{"areas"}, new String[]{"class","areas.class"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	@SuppressWarnings("deprecation")
	public String updatePic()
	{
		SceneryPic pic = new SceneryPic();
		pic = sceneryPicService.getSceneryPic(picId);
//		String fileName = "";
//		String url = "";
//		String brUrl = "";
//		String fileType = "";
//		savePath = ServletActionContext.getRequest().getRealPath(savePath)+"\\"+"image";
//		if(upload!=null)
//		{
//			fileType = uploadFileName.substring(uploadFileName.indexOf(".")+1, uploadFileName.length());
//			fileName = name+"."+fileType;
//			url = "upload"+"/"+"image"+"/"+fileName;
//			brUrl = "upload"+"/"+"image"+"/"+"bre"+fileName;
//			
//		}
//		String breSavePath = savePath + "\\"+"bre"+fileName;
		pic.setName(name);
		Scenery sc = new Scenery();
		sc = sceneryService.getSceneryInfo(sceneryId);
		Set<Area> areas = new HashSet<Area>();
		areas = sc.getAreas();
		pic.setAreas(areas);
		pic.setScenery(sc);
		sceneryPicService.merge(pic);
//		sceneryPicService.uploadPic(pic, url, brUrl, upload, savePath, breSavePath, fileName, fileType);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	public String delPic()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				sceneryPicService.delPic(delData.trim());
			}
			else
			{
				String[] id= this.delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					sceneryPicService.delPic(id[i].trim());
				}
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}
	public String getSceneryInfo()
	{
		List<Scenery> scList = new ArrayList<Scenery>();
		scList = sceneryService.getSceneryByArea(areaId);
		if(scList!=null)
    	{
			int length = scList.size();
	    	if(length!=0)
	    	{
	    		StringBuilder sb = new StringBuilder("[");
	    		for(int i=0;i<length-1;i++)
	    		{
	    			sb.append("[");
					sb.append("\'");
					sb.append(scList.get(i).getName());
					sb.append("\'");
					sb.append(",");
					sb.append("\'");
					sb.append(scList.get(i).getSceneryId());
					sb.append("\'");
					sb.append("]");
					sb.append(",");
	    		}
	    		sb.append("[");
				sb.append("\'");
				sb.append(scList.get(length-1).getName());
				sb.append("\'");
				sb.append(",");
				sb.append("\'");
				sb.append(scList.get(length-1).getSceneryId());
				sb.append("\'");
				sb.append("]");
				sb.append("]");
				jsonString = sb.toString();
	    	}
	    	else
	    	{
	    		jsonString = "[]";
	    	}
    	}
		else
		{
			jsonString = "[]";
		}
    	return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String getAllPic()
	{
		Page page = new Page();
		page = sceneryPicService.getAllPic(dir, sort, limit, start, name);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,totalCount:");
		if(page!=null)
		{
			List<SceneryPic> picList = (List<SceneryPic>)page.getResult();
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(picList, null, new String[]{"class"}, null));
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
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPicId()
	{
		return picId;
	}
	public void setPicId(String picId)
	{
		this.picId = picId;
	}
	public String getSavePath()
	{
		return savePath;
	}
	public void setSavePath(String savePath)
	{
		this.savePath = savePath;
	}
	public ISceneryPicService getSceneryPicService()
	{
		return sceneryPicService;
	}
	public void setSceneryPicService(ISceneryPicService sceneryPicService)
	{
		this.sceneryPicService = sceneryPicService;
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
	public String getDelData()
	{
		return delData;
	}
	public void setDelData(String delData)
	{
		this.delData = delData;
	}
	public String getSceneryId()
	{
		return sceneryId;
	}
	public void setSceneryId(String sceneryId)
	{
		this.sceneryId = sceneryId;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public ISceneryService getSceneryService()
	{
		return sceneryService;
	}
	public void setSceneryService(ISceneryService sceneryService)
	{
		this.sceneryService = sceneryService;
	}
	public String getBrUrl()
	{
		return brUrl;
	}
	public void setBrUrl(String brUrl)
	{
		this.brUrl = brUrl;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
}
