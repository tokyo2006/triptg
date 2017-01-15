package com.yeoou.tour.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.DateUtils;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.utils.SerialNoUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.Manager;
import com.yeoou.tour.model.News;
import com.yeoou.tour.model.NewsCount;
import com.yeoou.tour.model.NewsType;
import com.yeoou.tour.model.Scenery;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.INewsCountService;
import com.yeoou.tour.service.INewsService;
import com.yeoou.tour.service.INewsTypeService;
import com.yeoou.tour.service.ISceneryPicService;
/**
 * <p>
 * Title: 后台新闻相关展示操作模块
 * </p>
 * <p>
 * Description: 添加新闻，更新新闻，删除新闻操作
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class NewsAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private INewsService newsService;
	private ISceneryPicService sceneryPicService;
	private INewsTypeService newsTypeService;
	private INewsCountService newsCountService;
	private IAreaService areaService;
	private String dir = "";
	private String sort = "";
	private int start = 0;
	private int limit = 0;
	private String jsonString ="";
	private String content = "";
	private int hot = 0;
	private String mark = "";
	private String origin = "";
	private int promotion = 0;
	private String subTitle = "";
	private String title = "";
	private String typeData = "";
	private String newsId = "";
	private String reArea = "";
	private String areaId = "";
	private String parent = "";
	private String typeId = "";
	private String picUrl = "";
	private String delData;
	private int flag = 0;
	private News news;
	private String uploadFileName;
	private String uploadContentType;
	private String savePath;
	private File upload;
	/**
	 * 上传新闻图片模块
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String uploadPic()
	{
		String fileName = "";
		String url = "";
		String fileType = "";
		if(!picUrl.equals(""))
		{
			sceneryPicService.delPicture(picUrl);
		}
		savePath = ServletActionContext.getRequest().getRealPath(savePath)+"\\"+"image"+"\\"+"news";
		if(upload!=null)
		{
			fileType = uploadFileName.substring(uploadFileName.indexOf(".")+1, uploadFileName.length());
			fileName = SerialNoUtils.getSerialNo()+"."+fileType;
			url = "upload"+"/"+"image"+"/"+"news"+"/"+fileName;
		}
		sceneryPicService.addPic(upload,savePath,fileName,fileType);
		jsonString = "{success:true,\"picUrl\":\""+url+"\"}";
		return SUCCESS;
	}
	/**
	 * 添加新闻信息
	 * @return
	 */
	public String addNews()
	{
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		List<Area> areaList = new ArrayList<Area>();
		List<String> parentIdList = new ArrayList<String>();
		int p=0;
		String tempParentId;
		Area tempA = new Area();
		Area tempParentA = new Area();
		parentIdList = new ArrayList<String>();
		if(!reArea.equals("")){
//			if(reArea.indexOf(",")<0){
				String[] tempStr = reArea.split(",");
				for(int i=0;i<tempStr.length;i++){
					tempA=(Area)areaService.get(tempStr[i].trim());
					tempParentA = (Area)areaService.get(tempA.getParent());
					while(tempParentA.getDepth()>1){
						tempParentId = tempParentA.getAreaId();
						for(int h=0;h<(parentIdList.size());h++){
								if(parentIdList.get(h).equals(tempParentId)){
									p = 1;
									break;
								}
						}
						for(int h=0;h<(tempStr.length);h++){
							if(tempStr[h].equals(tempParentId)){
								p = 1;
								break;
							}
						}
						if(p==0){
							parentIdList.add(tempParentId);
						}
						tempParentA = (Area)areaService.get(tempParentA.getParent());
						p=0;
					}
					areaList.add(tempA);
				}
				for(int i=0;i<parentIdList.size();i++){
					tempA=(Area)areaService.get(parentIdList.get(i));
					areaList.add(tempA);
				}
//			}
		}
		List<NewsType> ntList = new ArrayList<NewsType>();
		NewsType tempN = new NewsType();
		parentIdList = new ArrayList<String>();
		if(!typeData.equals(""))
		{
				String[] tempStr = typeData.split(",");
				for(int i=0;i<tempStr.length;i++)
				{
					tempN=(NewsType)newsTypeService.get(tempStr[i].trim());
					parentIdList.add(tempN.getParent());
					ntList.add(tempN);
				}
				int l = parentIdList.size();
				for(int i=0;i<l;i++)
				{
					for(int j=i+1;j<l;j++)
					{
						if(parentIdList.get(i).equals(parentIdList.get(j)))
						{
							parentIdList.remove(i);
							l--;
							i=0;
						}
					}
				}
				for(int i=0;i<parentIdList.size();i++)
				{
					tempN=(NewsType)newsTypeService.get(parentIdList.get(i).trim());
					ntList.add(tempN);
				}
		}
		News news = new News();
		news.setAreas(new HashSet<Area>(areaList));
		news.setTypes(new HashSet<NewsType>(ntList));
		news.setHot(hot);
		news.setMark(mark);
		news.setPicUrl(picUrl);
		news.setOrigin(origin);
		news.setPromotion(promotion);
		Date date = new Date();
		news.setPublishDate(DateUtils.parseDate(DateUtils.formatDate(date)));
		news.setPublishDateStr(DateUtils.formatDate(date));
		news.setSubTitle(subTitle);
		news.setTitle(title);
		news.setWriter(manager.getName());
		newsService.save(news);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 获取单一新闻信息
	 * @return
	 */
	public String getSingleNews()
	{
		News news = new News();
		news = newsService.getNews(newsId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,news:");
		sb.append(jsonTool.getJsonString(news, new String []{"areas","types"}, new String[]{"class","areas.class","types.class"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 更新新闻信息
	 * @return
	 */
	public String updateNews()
	{
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		List<Area> areaList = new ArrayList<Area>();
		List<String> parentIdList = new ArrayList<String>();
		int p=0;
		String tempParentId;
		Area tempA = new Area();
		Area tempParentA = new Area();
		parentIdList = new ArrayList<String>();
		if(!reArea.equals("")){
//			if(reArea.indexOf(",")<0){
				String[] tempStr = reArea.split(",");
				for(int i=0;i<tempStr.length;i++){
					tempA=(Area)areaService.get(tempStr[i].trim());
					tempParentA = (Area)areaService.get(tempA.getParent());
					while(tempParentA.getDepth()>1){
						tempParentId = tempParentA.getAreaId();
						for(int h=0;h<(parentIdList.size());h++){
								if(parentIdList.get(h).equals(tempParentId)){
									p = 1;
									break;
								}
						}
						for(int h=0;h<(tempStr.length);h++){
							if(tempStr[h].equals(tempParentId)){
								p = 1;
								break;
							}
						}
						if(p==0){
							parentIdList.add(tempParentId);
						}
						tempParentA = (Area)areaService.get(tempParentA.getParent());
						p=0;
					}
					areaList.add(tempA);
				}
				for(int i=0;i<parentIdList.size();i++){
					tempA=(Area)areaService.get(parentIdList.get(i));
					areaList.add(tempA);
				}
//			}
		}
		List<NewsType> ntList = new ArrayList<NewsType>();
		NewsType tempN = new NewsType();
		parentIdList = new ArrayList<String>();
		if(!typeData.equals(""))
		{
				String[] tempStr = typeData.split(",");
				for(int i=0;i<tempStr.length;i++)
				{
					tempN=(NewsType)newsTypeService.get(tempStr[i].trim());
					parentIdList.add(tempN.getParent());
					ntList.add(tempN);
				}
				int l = parentIdList.size();
				for(int i=0;i<l;i++)
				{
					for(int j=i+1;j<l;j++)
					{
						if(parentIdList.get(i).equals(parentIdList.get(j)))
						{
							parentIdList.remove(i);
							l--;
							i=0;
						}
					}
				}
				for(int i=0;i<parentIdList.size();i++)
				{
					tempN=(NewsType)newsTypeService.get(parentIdList.get(i));
					ntList.add(tempN);
				}
		}
		News news = new News();
		news = (News)newsService.get(newsId);
		news.setAreas(new HashSet<Area>(areaList));
		news.setTypes(new HashSet<NewsType>(ntList));
		news.setHot(hot);
		news.setMark(mark);
		news.setOrigin(origin);
		news.setPicUrl(picUrl);
		news.setPromotion(promotion);
		Date date = new Date();
		news.setPublishDate(DateUtils.parseDate(DateUtils.formatDate(date)));
		news.setPublishDateStr(DateUtils.formatDate(date));
		news.setSubTitle(subTitle);
		news.setTitle(title);
		news.setWriter(manager.getName());
		newsService.merge(news);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 获取新闻列表（包括搜索）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllNews()
	{
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,totalCount:");
		Page page = new Page();
		page = newsService.getAllPage(dir, sort, start, limit, areaId, typeId, title);
		if(page!=null)
		{
			List<News> nsList = (List<News>)page.getResult();
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(nsList, null, new String[]{"class"}, null));
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
	 * 获取新闻的父类类别
	 * @return
	 */
	public String getNewsTypeParent()
	{
		List<NewsType> ntList = new ArrayList<NewsType>();
		ntList = newsTypeService.getChildren("");
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,ntList:");
		sb.append(jsonTool.getJsonString(ntList, null, new String[]{"class"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 获取类别列表信息
	 * @return
	 */
	public String getNewsTypeInfo()
	{
		List<NewsType> ntList = new ArrayList<NewsType>();
		ntList = newsTypeService.getChildren(parent);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,ntList:");
		sb.append(jsonTool.getJsonString(ntList, null, new String[]{"class"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 更新新闻内容详细信息
	 * @return
	 */
	public String updateContent()
	{
		news = (News)newsService.get(newsId);
		news.setContent(content);
		newsService.merge(news);
		jsonString = "修改成功！";
		return SUCCESS;
	}
	/**
	 * 获取新闻内容信息
	 * @return
	 */
	public String getNewsContent()
	{
		news = (News)newsService.get(newsId);
		return SUCCESS;
	}
	/**
	 * 删除新闻信息
	 * @return
	 */
	public String delNews()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				NewsCount nc = new NewsCount();
				nc = newsCountService.getNewsCountByNewsId(delData.trim());
				if(nc!=null)
				{
					newsCountService.removeById(nc.getCountId());
				}
				newsService.removeById(delData.trim());
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					NewsCount nc = new NewsCount();
					nc = newsCountService.getNewsCountByNewsId(id[i].trim());
					if(nc!=null)
					{
						newsCountService.removeById(nc.getCountId());
					}
					newsService.removeById(id[i].trim());
				}
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}
	public IAreaService getAreaService()
	{
		return areaService;
	}
	public void setAreaService(IAreaService areaService)
	{
		this.areaService = areaService;
	}
	public String getNewsId()
	{
		return newsId;
	}
	public void setNewsId(String newsId)
	{
		this.newsId = newsId;
	}
	public INewsTypeService getNewsTypeService()
	{
		return newsTypeService;
	}
	public void setNewsTypeService(INewsTypeService newsTypeService)
	{
		this.newsTypeService = newsTypeService;
	}
	public String getReArea()
	{
		return reArea;
	}
	public void setReArea(String reArea)
	{
		this.reArea = reArea;
	}
	public String getTypeData()
	{
		return typeData;
	}
	public void setTypeData(String typeData)
	{
		this.typeData = typeData;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getDir()
	{
		return dir;
	}
	public void setDir(String dir)
	{
		this.dir = dir;
	}
	public int getHot()
	{
		return hot;
	}
	public void setHot(int hot)
	{
		this.hot = hot;
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
	public String getMark()
	{
		return mark;
	}
	public void setMark(String mark)
	{
		this.mark = mark;
	}
	public INewsService getNewsService()
	{
		return newsService;
	}
	public void setNewsService(INewsService newsService)
	{
		this.newsService = newsService;
	}
	public String getOrigin()
	{
		return origin;
	}
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	public int getPromotion()
	{
		return promotion;
	}
	public void setPromotion(int promotion)
	{
		this.promotion = promotion;
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
	public String getSubTitle()
	{
		return subTitle;
	}
	public void setSubTitle(String subTitle)
	{
		this.subTitle = subTitle;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public int getFlag()
	{
		return flag;
	}
	public void setFlag(int flag)
	{
		this.flag = flag;
	}
	public News getNews()
	{
		return news;
	}
	public void setNews(News news)
	{
		this.news = news;
	}
	public String getTypeId()
	{
		return typeId;
	}
	public void setTypeId(String typeId)
	{
		this.typeId = typeId;
	}
	public String getDelData() {
		return delData;
	}
	public void setDelData(String delData) {
		this.delData = delData;
	}
	public String getParent()
	{
		return parent;
	}
	public void setParent(String parent)
	{
		this.parent = parent;
	}
	public String getPicUrl()
	{
		return picUrl;
	}
	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
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
	public INewsCountService getNewsCountService()
	{
		return newsCountService;
	}
	public void setNewsCountService(INewsCountService newsCountService)
	{
		this.newsCountService = newsCountService;
	}
}
