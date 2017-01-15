package com.yeoou.tour.web;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.CookieUtil;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.utils.SerialNoUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.FlashType;
import com.yeoou.tour.model.ThemeFlash;
import com.yeoou.tour.model.TreeNode;
import com.yeoou.tour.model.TripContent;
import com.yeoou.tour.model.TripFlag;
import com.yeoou.tour.model.TripModel;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.IFlashTypeService;
import com.yeoou.tour.service.ISceneryPicService;
import com.yeoou.tour.service.IThemeFlashService;
import com.yeoou.tour.service.ITripContentService;
import com.yeoou.tour.service.ITripFlagService;
import com.yeoou.tour.service.ITripModelService;
/**
 * <p>
 * Title: 前台页面内容相关操作模块
 * </p>
 * <p>
 * Description: 对页面模块进行添加，删除，修改，<br/>
 * 对页面标签进行添加，修改，删除，对页面内容进行<br/>
 * 添加，修改，删除，对主题flash类别进行添加，修改,<br/>
 * 删除，对主题flash进行添加，修改，删除
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class TripConfigAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private ITripModelService tripModelService;
	private ITripFlagService tripFlagService;
	private ITripContentService tripContentService;
	private IThemeFlashService themeFlashService;
	private IFlashTypeService flashTypeService;
	private ISceneryPicService sceneryPicService;
	private IAreaService areaService;
	private int start = 0;
	private int limit = 15;
	private String dir = "";
	private String sort = "";
	private String jsonString = "";
	private String tripFlagId;
	private String tripModelId;
	private String name="";
	private String delData;
	private String title = "";
	private int font = 0;
	private String picUrl = "";
	private String desrible = "";
	private String price = "";
	private String subTitle = "";
	private String target = "_blank";
	private String url = "";
	private String tripContentId = "";
	private String classId = "";
	private String flashTypeId = "";
	private String uploadFileName;
	private String uploadContentType;
	private String savePath;
	private File upload;
	private String breUrl = "";
	private int od = 0;
	private String flashId = "";
	private String flagId = "";
	private String node="-1";
	private String areaId = "";
	private String remark = "";
	private String shangjia = "";
	private String jsonData;
	private int operateType = 0;
	
	/**
	 * 构件extjs页面树形结构
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getTripFlagByNode()
	{
		List<TripFlag> tf = new ArrayList<TripFlag>();
		tf = (List<TripFlag>)tripFlagService.getAll();
		List<TreeNode> childlist = new ArrayList<TreeNode>();
		childlist = tripFlagService.setTripFlagTreeNodes(tf,node);
		JSONArray jsarray = JSONArray.fromObject(childlist);
		jsonString=jsarray.toString();
		return SUCCESS;
	}
	/**
	 * 构件extjs页面flash类别数形结构
	 * @return
	 */
	public String getFlashTypeByNode()
	{
		List<FlashType> ft = new ArrayList<FlashType>();
		ft = (List<FlashType>)flashTypeService.getAll();
		List<TreeNode> childlist = new ArrayList<TreeNode>();
		childlist = flashTypeService.setFlashTypeTreeNodes(ft);
		JSONArray jsarray = JSONArray.fromObject(childlist);
		jsonString=jsarray.toString();
		return SUCCESS;
	}
	/**
	 * 上传图片模块
	 * @return
	 */
	public String uploadPic()
	{
		String fileName = "";
		String url = "";
		String fileType = "";
		if(!picUrl.equals(""))
		{
			sceneryPicService.delPicture(picUrl);
		}
		savePath = ServletActionContext.getRequest().getRealPath(savePath)+"\\"+"image"+"\\"+"tripContent";
		if(upload!=null)
		{
			fileType = uploadFileName.substring(uploadFileName.indexOf(".")+1, uploadFileName.length());
			fileName = SerialNoUtils.getSerialNo()+"."+fileType;
			url = "upload"+"/"+"image"+"/"+"tripContent"+"/"+fileName;
		}
		sceneryPicService.addPic(upload,savePath,fileName,fileType);
		jsonString = "{success:true,\"url\":\""+url+"\"}";
		return SUCCESS;
	}
	/**
	 * 上传flash图片模块
	 * @return
	 */
	public String uploadFlashPic()
	{
		
		String fileName = "";
		String url = "";
		String fileType = "";
		String bUrl = "";
		if(!picUrl.equals(""))
		{
			sceneryPicService.delPicture(picUrl);
		}
		savePath = ServletActionContext.getRequest().getRealPath(savePath)+"\\"+"image"+"\\"+"flash";
		
		if(upload!=null)
		{
			fileType = uploadFileName.substring(uploadFileName.indexOf(".")+1, uploadFileName.length());
			fileName = SerialNoUtils.getSerialNo()+"."+fileType;
			url = "upload"+"/"+"image"+"/"+"flash"+"/"+fileName;
		}
		sceneryPicService.addPic(upload, savePath, fileName, fileType);
		jsonString = "{success:true,\"picUrl\":\""+url+"\""+"}";
		return SUCCESS;
	}
	/**
	 * 选择页面列表显示模块
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getPageList()
	{
		List<TripFlag> pageList = new ArrayList<TripFlag>();
		pageList = (List<TripFlag>)tripFlagService.getAll();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		if(pageList.size()!=0)
		{
			jsonString = "{success:true,pageList:"+jsonTool.getJsonString(pageList, null, null, null)+"}";
		}
		else
		{
			jsonString = "{success:true,pageList:[]}";
		}
		return SUCCESS;
	}
	/**
	 * 构件页面树形结构
	 * @return
	 */
	public String getPageSortList()
	{
		if(node.equals("-1"))
		{
			List<TripFlag> tf = new ArrayList<TripFlag>();
			tf = (List<TripFlag>)tripFlagService.getAll();
			List<TreeNode> childlist = new ArrayList<TreeNode>();
			childlist = tripFlagService.setTripFlagTreeNodes(tf,"1");
			JSONArray jsarray = JSONArray.fromObject(childlist);
			jsonString=jsarray.toString();
		}
		else
		{
			List<TripModel> tmList = new ArrayList<TripModel>();
			tmList = tripModelService.getTripModelByFlag(node);
			List<TreeNode> childlist = new ArrayList<TreeNode>();
			if(tmList==null) tmList = new ArrayList<TripModel>();
			childlist = tripModelService.setTripModelTreeNodes(tmList);
			JSONArray jsarray = JSONArray.fromObject(childlist);
			jsonString=jsarray.toString();
		}
		return SUCCESS;
	}
	/**
	 * 页面标签列表显示
	 * @return
	 */
	public String getAllTripModel()
	{
		Page page = new Page();
		page = tripModelService.getAllTripModel(start, limit, sort, dir,flagId);
		if(page!=null)
		{
			List<TripModel> tmList = (List<TripModel>)page.getResult();
			this.setTotalCount(page.getTotalCount());
			FlexJsonUtils jsonTool = new FlexJsonUtils();
        	this.jsonString = "{success:true,totalCount:"+this.getTotalCount()+",results:"+jsonTool.getJsonString(tmList, null, null, null)+"}";
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	/**
	 * 单一页面标签信息
	 * @return
	 */
	public String getSingleTripModel()
	{
		TripModel tm = new TripModel();
		tm = (TripModel)tripModelService.get(tripModelId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
    	jsonString = "{success:true,totalCount:1,results:["+jsonTool.getJsonString(tm, null, null, null)+"]}";
		return SUCCESS;
	}
	/**
	 * 添加页面标签信息
	 * @return
	 */
	public String addTripModel()
	{
		TripModel tripModel  = new TripModel();
		TripFlag tripFlag = new TripFlag();
		tripFlag = (TripFlag)tripFlagService.get(tripFlagId);
		if(tripModelService.getTripModelByName(name)==null)
		{
			tripModel.setName(name);
			tripModel.setTitle(title);
			tripModel.setRemark(remark );
			tripModel.setTripFlag(tripFlag);
			tripModelService.save(tripModel);
			jsonString = "{success:true}";
		}
		else
		{
			jsonString = "{success:false,msg:'输入的英文名称已经存在，请重新输入'}";
		}
		return SUCCESS;
	}
	/**
	 * 更新页面标签信息
	 * @return
	 */
	public String updateTripModel()
	{
		TripModel tripModel  = new TripModel();
		TripFlag tripFlag = new TripFlag();
		tripModel = (TripModel)tripModelService.get(tripModelId);
		TripModel comp = new TripModel();
		comp = tripModelService.getTripModelByName(name);
		if(comp!=null&&!comp.getClassId().equals(tripModel.getClassId()))
		{
			jsonString = "{success:false,msg:'输入的英文名称已经存在，请重新输入'}";
			return SUCCESS;
		}
		tripFlag = (TripFlag)tripFlagService.get(tripFlagId);
		tripModel.setName(name);
		tripModel.setTitle(title);
		tripModel.setRemark(remark);
		tripModel.setTripFlag(tripFlag);
		tripModelService.merge(tripModel);
		jsonString = "{success:true}";
		
		return SUCCESS;
	}
	/**
	 * 删除页面标签信息（如果页面标签有页面内容则不允许删除）
	 * @return
	 */
	public String delTripModel()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				TripModel tm = new TripModel();
				tm = (TripModel)tripModelService.get(delData.trim());
				int result = tripContentService.getCountByTripModel(tm.getClassId());
				if(result==0)
				{
					tripModelService.remove(tm);
					jsonString = "{success:true}";
				}
				else
				{
					jsonString="{success:false,msg:'此模块在使用中不允许删除！'}";
				}
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					TripModel tm = new TripModel();
					tm = (TripModel)tripModelService.get(id[i].trim());
					int result = tripContentService.getCountByTripModel(tm.getClassId());
					if(result==0)
					{
						tripModelService.remove(tm);
						jsonString = "{success:true}";
					}
					else
					{
						jsonString="{success:false,msg:'此模块在使用中不允许删除！'}";
					}
				}
			}
		}
		return SUCCESS;
	}
	/**
	 * 后台显示页面列表
	 * @return
	 */
	public String getAllTripFlag()
	{
		Page page = new Page();
		page = tripFlagService.getAllTripFlag(start, limit, sort, dir);
		if(page!=null)
		{
			List<TripFlag> tmList = (List<TripFlag>)page.getResult();
			this.setTotalCount(page.getTotalCount());
			FlexJsonUtils jsonTool = new FlexJsonUtils();
        	this.jsonString = "{success:true,totalCount:"+this.getTotalCount()+",results:"+jsonTool.getJsonString(tmList, null, null, null)+"}";
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	/**
	 * 单一页面信息
	 * @return
	 */
	public String getSingelTripFlag()
	{
		TripFlag tf = new TripFlag();
		tf = (TripFlag)tripFlagService.get(tripFlagId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
    	jsonString = "{success:true,totalCount:1,results:["+jsonTool.getJsonString(tf, null, null, null)+"]}";
		return SUCCESS;
	}
	/**
	 * 添加页面信息
	 * @return
	 */
	public String addTripFlag()
	{
		TripFlag tripFlag = new TripFlag();
			tripFlag.setName(name);
			tripFlag.setTitle(title);
			tripFlagService.save(tripFlag);
			jsonString="{success:true}";
		
		return SUCCESS;
	}
	/**
	 * 更新页面信息
	 * @return
	 */
	public String updateTripFlag()
	{
		TripFlag tripFlag = new TripFlag();
		tripFlag = (TripFlag)tripFlagService.get(tripFlagId);
		
			tripFlag.setName(name);
			tripFlag.setTitle(title);
			tripFlagService.save(tripFlag);
			jsonString="{success:true}";
		
		return SUCCESS;
	}
	/**
	 * 删除页面信息如果页面有页面标签则不允许删除
	 * @return
	 */
	public String delTripFlag()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				TripFlag  tf = new TripFlag();
				tf = (TripFlag)tripFlagService.get(delData.trim());
				int result = tripModelService.getCountByTripFlag(tf.getId());
				if(result == 0)
				{
					tripFlagService.remove(tf);
					jsonString="{success:true}";
				}
				else
				{
					jsonString="{success:false,msg:'此页面在使用中不允许删除！'}";
				}
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					TripFlag  tf = new TripFlag();
					tf = (TripFlag)tripFlagService.get(id[i].trim());
					int result = tripModelService.getCountByTripFlag(tf.getId());
					if(result == 0)
					{
						tripFlagService.remove(tf);
						jsonString="{success:true}";
					}
					else
					{
						jsonString="{success:false,msg:'此页面在使用中不允许删除！'}";
					}
				}
			}
		}
		return SUCCESS;
	}
	/**
	 * 获取页面选择列表
	 * @return
	 */
	public String getTripFlagInfo()
	{
		List<TripFlag> tfList = new ArrayList<TripFlag>();
		tfList = (List<TripFlag>)tripFlagService.getAll();
		if(tfList.size()!=0)
		{
			int length = tfList.size();
			StringBuilder sb = new StringBuilder("[");
			for(int i=0;i<length-1;i++)
			{
				sb.append("[");
				sb.append("\'");
				sb.append(tfList.get(i).getId());
				sb.append("\'");
				sb.append(",");
				sb.append("\'");
				sb.append(tfList.get(i).getTitle());
				sb.append("\'");
				sb.append("]");
				sb.append(",");
			}
			sb.append("[");
			sb.append("\'");
			sb.append(tfList.get(length-1).getId());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(tfList.get(length-1).getTitle());
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
	 * 获取页面标签列表信息
	 * @return
	 */
	public String getTripModelInfo()
	{
		List<TripModel> tfList = new ArrayList<TripModel>();
		tfList = (List<TripModel>)tripModelService.getAll();
		if(tfList.size()!=0)
		{
			int length = tfList.size();
			StringBuilder sb = new StringBuilder("[");
			for(int i=0;i<length-1;i++)
			{
				sb.append("[");
				sb.append("\'");
				sb.append(tfList.get(i).getClassId());
				sb.append("\'");
				sb.append(",");
				sb.append("\'");
				sb.append(tfList.get(i).getTitle());
				sb.append("\'");
				sb.append("]");
				sb.append(",");
			}
			sb.append("[");
			sb.append("\'");
			sb.append(tfList.get(length-1).getClassId());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(tfList.get(length-1).getTitle());
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
	 * 页面内容列表显示
	 * @return
	 */
	public String getAllTripContent()
	{
		Page page = new Page();
		page = tripContentService.getAllTripContent(start, limit, sort, dir,classId);
		if(page!=null)
		{
			List<TripContent> tcList = (List<TripContent>)page.getResult();
			this.setTotalCount(page.getTotalCount());
			FlexJsonUtils jsonTool = new FlexJsonUtils();
        	this.jsonString = "{success:true,totalCount:"+this.getTotalCount()+",results:"+jsonTool.getJsonString(tcList, null, null, null)+"}";
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	/**
	 * 单一页面内容信息
	 * @return
	 */
	public String getSingleTripContent()
	{
		TripContent tc = new TripContent();
		tc = (TripContent) tripContentService.get(this.tripContentId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
    	jsonString = "{success:true,totalCount:1,results:["+jsonTool.getJsonString(tc, null, null, null)+"]}";
		return SUCCESS;
	}

	/**
	 * 页面内容上架下架批量操作
	 * @return
	 */
	public String operateTripContent()
	{
		if(jsonData!=null)
		{
			if(jsonData.indexOf(",")<0)
			{
				TripContent tc = new TripContent();
				tc = (TripContent)tripContentService.get(jsonData.trim());
				if(this.operateType==1)
				{
					tc.setShangjia(1);
				}
				else
				{
					tc.setShangjia(0);
				}
				tripContentService.merge(tc);
				jsonString = "{success:true}";
			}
			else
			{
				String[] id = jsonData.split(",");
				for(int i=0;i<id.length;i++)
				{
					TripContent tc = new TripContent();
					tc = (TripContent)tripContentService.get(id[i].trim());
					if(this.operateType==1)
					{
						tc.setShangjia(1);
					}
					else
					{
						tc.setShangjia(0);
					}
					tripContentService.merge(tc);
				}
				jsonString = "{success:true}";
			}
		}
		return SUCCESS;
	}
	/**
	 * 添加页面内容
	 * @return
	 */
	public String addTripContent()
	{
		TripContent tc = new TripContent();
		Area area = new Area();
		if(!areaId.equals(""))
		{
			area = (Area)areaService.get(areaId);
		}
		else
		{
			area = null;
		}
		TripModel tm = (TripModel)tripModelService.get(tripModelId);
		tc.setDesrible(desrible);
		if(shangjia.equals("1"))
		{
			tc.setShangjia(1);
		}
		else
		{
			tc.setShangjia(0);
		}
		tc.setFont(font);
		tc.setArea(area);
		tc.setPicUrl(picUrl);
		tc.setPrice(price);
		tc.setSubTitle(subTitle);
		tc.setTarget(target);
		tc.setTitle(title);
		tc.setTripModel(tm);
		tc.setUrl(url);
		tripContentService.save(tc);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 更新页面内容
	 * @return
	 */
	public String updateTripContent()
	{
		TripContent tc = new TripContent();
		tc = (TripContent)tripContentService.get(tripContentId);
		TripModel tm = (TripModel)tripModelService.get(tripModelId);
		Area area = new Area();
		if(!areaId.equals(""))
		{
			area = (Area)areaService.get(areaId);
		}
		else
		{
			area = null;
		}
		if(shangjia.equals("1"))
		{
			tc.setShangjia(1);
		}
		else
		{
			tc.setShangjia(0);
		}
		tc.setDesrible(desrible);
		tc.setFont(font);
		tc.setArea(area);
		tc.setPicUrl(picUrl);
		tc.setPrice(price);
		tc.setSubTitle(subTitle);
		tc.setTarget(target);
		tc.setTitle(title);
		tc.setTripModel(tm);
		tc.setUrl(url);
		tripContentService.merge(tc);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 删除页面内容
	 * @return
	 */
	public String delTripContent()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				TripContent tc = new TripContent();
				tc = (TripContent)tripContentService.get(delData.trim());
				sceneryPicService.delPicture(tc.getPicUrl());
				tripContentService.removeById(delData.trim());
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					TripContent tc = new TripContent();
					tc = (TripContent)tripContentService.get(id[i].trim());
					sceneryPicService.delPicture(tc.getPicUrl());
					tripContentService.removeById(id[i].trim());
				}
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 添加flash类别
	 * @return
	 */
	public String addFlashType()
	{
		FlashType  ft = new FlashType();
		if(flashTypeService.getFlashTypeByName(name)==null)
		{
			ft.setName(name);
			ft.setTitle(title);
			ft.setRemark(remark);
			flashTypeService.save(ft);
			jsonString = "{success:true}";
		}
		else
		{
			jsonString = "{success:false,msg:'输入的英文已经存在，请重新输入'}";
		}
		return SUCCESS;
	}
	/**
	 * 获取flash类别信息列表
	 * @return
	 */
	public String getFlashTypeInfo()
	{
		List<FlashType> tfList = new ArrayList<FlashType>();
		tfList = (List<FlashType>)flashTypeService.getAll();
		if(tfList.size()!=0)
		{
			int length = tfList.size();
			StringBuilder sb = new StringBuilder("[");
			for(int i=0;i<length-1;i++)
			{
				sb.append("[");
				sb.append("\'");
				sb.append(tfList.get(i).getId());
				sb.append("\'");
				sb.append(",");
				sb.append("\'");
				sb.append(tfList.get(i).getTitle());
				sb.append("\'");
				sb.append("]");
				sb.append(",");
			}
			sb.append("[");
			sb.append("\'");
			sb.append(tfList.get(length-1).getId());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(tfList.get(length-1).getTitle());
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
	 * 单一flash类别信息
	 * @return
	 */
	public String getSingleFlashType()
	{
		FlashType ft = new FlashType();
		ft = (FlashType)flashTypeService.get(flashTypeId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
    	jsonString = "{success:true,totalCount:1,results:["+jsonTool.getJsonString(ft, null, null, null)+"]}";
		return SUCCESS;
	}
	/**
	 * 更新flash类别信息
	 * @return
	 */
	public String updateFlashType()
	{
		FlashType  ft = new FlashType();
		ft = (FlashType)flashTypeService.get(this.flashTypeId);
		FlashType comp = new FlashType();
		comp = flashTypeService.getFlashTypeByName(name);
		if(comp!=null&&!comp.getId().equals(ft.getId()))
		{
			jsonString = "{success:false,msg:'输入的英文已经存在，请重新输入'}";
			return SUCCESS;
		}
		ft.setName(name);
		ft.setTitle(title);
		ft.setRemark(remark);
		flashTypeService.save(ft);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 后台显示flash类型列表
	 * @return
	 */
	public String getAllFlashType()
	{
		Page page = new Page();
		page = flashTypeService.getAllFlashType(start, limit, dir, sort);
		if(page!=null)
		{
			List<FlashType> ftList = (List<FlashType>)page.getResult();
			this.setTotalCount(page.getTotalCount());
			FlexJsonUtils jsonTool = new FlexJsonUtils();
        	this.jsonString = "{success:true,totalCount:"+this.getTotalCount()+",results:"+jsonTool.getJsonString(ftList, null, null, null)+"}";
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	/**
	 * 删除flash类别信息如果有此类别的flash则不允许删除
	 * @return
	 */
	public String delFlashType()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				FlashType ft = new FlashType();
				ft = (FlashType)flashTypeService.get(delData.trim());
				int result = tripContentService.getCountByTripModel(ft.getId());
				if(result==0)
				{
					flashTypeService.remove(ft);
					jsonString = "{success:true}";
				}
				else
				{
					jsonString="{success:false,msg:'此模块在使用中不允许删除！'}";
				}
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					FlashType ft = new FlashType();
					ft = (FlashType)flashTypeService.get(id[i].trim());
					int result = tripContentService.getCountByTripModel(ft.getId());
					if(result==0)
					{
						flashTypeService.remove(ft);
						jsonString = "{success:true}";
					}
					else
					{
						jsonString="{success:false,msg:'此模块在使用中不允许删除！'}";
					}
				}
			}
		}
		return SUCCESS;
	}
	/**
	 * 添加主题flash
	 * @return
	 */
	public String addThemeFlash()
	{
		ThemeFlash tf = new ThemeFlash();
		FlashType ft = new FlashType();
		ft = (FlashType)flashTypeService.get(this.flashTypeId);
		tf.setBreUrl(breUrl);
		tf.setFlashType(ft);
		tf.setName(name);
		tf.setOd(od);
		tf.setPicUrl(picUrl);
		tf.setSubTitle(subTitle);
		tf.setTarget(target);
		tf.setTitle(title);
		tf.setUrl(url);
		this.themeFlashService.save(tf);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 获取单一主题flash信息
	 * @return
	 */
	public String getSingleThemeFlash()
	{
		ThemeFlash tf = new ThemeFlash();
		tf = (ThemeFlash)themeFlashService.get(flashId );
		FlexJsonUtils jsonTool = new FlexJsonUtils();
    	jsonString = "{success:true,totalCount:1,results:["+jsonTool.getJsonString(tf, null, null, null)+"]}";
		return SUCCESS;
	}
	/**
	 * 后台显示主题flash列表
	 * @return
	 */
	public String getAllThemeFlash()
	{
		Page page = themeFlashService.getAllThemeFlash(start, limit, sort, dir,this.flashTypeId);
		if(page!=null)
		{
			List<ThemeFlash> tfList = (List<ThemeFlash>)page.getResult();
			this.setTotalCount(page.getTotalCount());
			FlexJsonUtils jsonTool = new FlexJsonUtils();
        	this.jsonString = "{success:true,totalCount:"+this.getTotalCount()+",results:"+jsonTool.getJsonString(tfList, null, null, null)+"}";
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	/**
	 * 更新主题flash信息
	 * @return
	 */
	public String updateThemeFlash()
	{
		ThemeFlash tf = new ThemeFlash();
		tf = (ThemeFlash)themeFlashService.get(flashId);
		FlashType ft = new FlashType();
		ft = (FlashType)flashTypeService.get(this.flashTypeId);
		tf.setBreUrl(breUrl);
		tf.setFlashType(ft);
		tf.setName(name);
		tf.setOd(od);
		tf.setPicUrl(picUrl);
		tf.setSubTitle(subTitle);
		tf.setTarget(target);
		tf.setTitle(title);
		tf.setUrl(url);
		this.themeFlashService.merge(tf);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 删除主题flash信息
	 * @return
	 */
	public String delThemeFlash()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				ThemeFlash tf = new ThemeFlash();
				tf = (ThemeFlash)themeFlashService.get(delData.trim());
				sceneryPicService.delPicture(tf.getPicUrl());
				sceneryPicService.delPicture(tf.getBreUrl());
				themeFlashService.removeById(delData.trim());
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					ThemeFlash tf = new ThemeFlash();
					tf = (ThemeFlash)themeFlashService.get(id[i].trim());
					sceneryPicService.delPicture(tf.getPicUrl());
					sceneryPicService.delPicture(tf.getBreUrl());
					themeFlashService.removeById(id[i].trim());
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
	public IThemeFlashService getThemeFlashService()
	{
		return themeFlashService;
	}
	public void setThemeFlashService(IThemeFlashService themeFlashService)
	{
		this.themeFlashService = themeFlashService;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public ITripContentService getTripContentService()
	{
		return tripContentService;
	}
	public void setTripContentService(ITripContentService tripContentService)
	{
		this.tripContentService = tripContentService;
	}
	public String getTripFlagId()
	{
		return tripFlagId;
	}
	public void setTripFlagId(String tripFlagId)
	{
		this.tripFlagId = tripFlagId;
	}
	public ITripFlagService getTripFlagService()
	{
		return tripFlagService;
	}
	public void setTripFlagService(ITripFlagService tripFlagService)
	{
		this.tripFlagService = tripFlagService;
	}
	public String getTripModelId()
	{
		return tripModelId;
	}
	public void setTripModelId(String tripModelId)
	{
		this.tripModelId = tripModelId;
	}
	public ITripModelService getTripModelService()
	{
		return tripModelService;
	}
	public void setTripModelService(ITripModelService tripModelService)
	{
		this.tripModelService = tripModelService;
	}
	public String getDelData()
	{
		return delData;
	}
	public void setDelData(String delData)
	{
		this.delData = delData;
	}
	public String getDesrible()
	{
		return desrible;
	}
	public void setDesrible(String desrible)
	{
		this.desrible = desrible;
	}
	
	public int getFont()
	{
		return font;
	}
	public void setFont(int font)
	{
		this.font = font;
	}
	public String getPicUrl()
	{
		return picUrl;
	}
	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}
	public String getPrice()
	{
		return price;
	}
	public void setPrice(String price)
	{
		this.price = price;
	}
	public String getSubTitle()
	{
		return subTitle;
	}
	public void setSubTitle(String subTitle)
	{
		this.subTitle = subTitle;
	}
	public String getTarget()
	{
		return target;
	}
	public void setTarget(String target)
	{
		this.target = target;
	}
	public String getTripContentId()
	{
		return tripContentId;
	}
	public void setTripContentId(String tripContentId)
	{
		this.tripContentId = tripContentId;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getClassId()
	{
		return classId;
	}
	public void setClassId(String classId)
	{
		this.classId = classId;
	}
	public IFlashTypeService getFlashTypeService()
	{
		return flashTypeService;
	}
	public void setFlashTypeService(IFlashTypeService flashTypeService)
	{
		this.flashTypeService = flashTypeService;
	}
	public String getSavePath()
	{
		return savePath;
	}
	public void setSavePath(String savePath)
	{
		this.savePath = savePath;
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
	public ISceneryPicService getSceneryPicService()
	{
		return sceneryPicService;
	}
	public void setSceneryPicService(ISceneryPicService sceneryPicService)
	{
		this.sceneryPicService = sceneryPicService;
	}
	public String getFlashTypeId()
	{
		return flashTypeId;
	}
	public void setFlashTypeId(String flashTypeId)
	{
		this.flashTypeId = flashTypeId;
	}
	public String getBreUrl()
	{
		return breUrl;
	}
	public void setBreUrl(String breUrl)
	{
		this.breUrl = breUrl;
	}
	public String getFlagId()
	{
		return flagId;
	}
	public void setFlagId(String flagId)
	{
		this.flagId = flagId;
	}
	public String getFlashId()
	{
		return flashId;
	}
	public void setFlashId(String flashId)
	{
		this.flashId = flashId;
	}
	public String getNode()
	{
		return node;
	}
	public void setNode(String node)
	{
		this.node = node;
	}
	public int getOd()
	{
		return od;
	}
	public void setOd(int od)
	{
		this.od = od;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getShangjia()
	{
		return shangjia;
	}
	public void setShangjia(String shangjia)
	{
		this.shangjia = shangjia;
	}
	public String getJsonData()
	{
		return jsonData;
	}
	public void setJsonData(String jsonData)
	{
		this.jsonData = jsonData;
	}
	public int getOperateType()
	{
		return operateType;
	}
	public void setOperateType(int operateType)
	{
		this.operateType = operateType;
	}
}
