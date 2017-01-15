package com.yeoou.tour.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.FileUtils;
import com.yeoou.common.utils.ImageUtils;
import com.yeoou.tour.model.SceneryPic;
import com.yeoou.tour.service.ISceneryPicService;

public class SceneryPicService extends HibernateEntityDao<SceneryPic> implements
		ISceneryPicService
{

	public void addPic(File upload, String savePath, String breSavePath, String fileName,String fileType)
	{
		if(upload!=null)
		{
			try
			{
				FileUtils.UploadFile(upload, savePath, fileName);
				
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ImageUtils imageUtils = new ImageUtils();
			try
			{
				imageUtils.saveImageAsJpg(upload, breSavePath, 300, 300,fileType);
				
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void addPic(SceneryPic pic, File upload, String savePath, String breSavePath,String miniSavePath,String fileName,String fileType)
	{
		// TODO Auto-generated method stub
		if(upload!=null)
		{
			try
			{
				FileUtils.UploadFile(upload, savePath, fileName);
				
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ImageUtils imageUtils = new ImageUtils();
			try
			{
				imageUtils.saveImageAsJpg(upload, breSavePath, 300, 300,fileType);
				imageUtils.saveImageAsJpg(upload, miniSavePath, 150, 150, fileType);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.save(pic);
		}
	}

	public Page getAllPic(String dir, String sort, int limit, int start, String name)
	{
		// TODO Auto-generated method stub
		List<Object> val = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("from SceneryPic as pic where 1=1");
		if(!name.equals(""))
		{
			sb.append(" and pic.name = ?");
			val.add(name);
		}
		sb.append(" order by pic.");
		sb.append(sort);
		sb.append(" ");
		sb.append(dir);
		Object[] values = new Object[val.size()];
		for(int i=0;i<val.size();i++)
		{
			values[i] = val.get(i);
		}
		return this.PageExtQuery(sb.toString(), start, limit, values);
	}
	private void delFile(String savePath)
	{
	    	File file = new File(savePath);
	    	if(file.exists())
	    	{
	    		FileUtils.removeFile(file);
	    	}
	    	
	}
	private void addFile(File file, String savePath, String fileName)
	{
		// TODO Auto-generated method stub
		try
		{
			FileUtils.UploadFile(file, savePath, fileName);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public void uploadPic(SceneryPic pic, String url, String breUrl, File upload, String savePath, String breSavePath, String fileName, String fileType)
//	{
//		// TODO Auto-generated method stub
//		//删除原始文件
//		if(upload!=null)
//		{
//			String delPath = ServletActionContext.getRequest().getRealPath(pic.getUrl());
//			String delBrePath = ServletActionContext.getRequest().getRealPath(pic.getBreviaryUrl());
//			this.delFile(delPath);
//			this.delFile(delBrePath);
//			this.addFile(upload, savePath, fileName);
//			ImageUtils imageUtils = new ImageUtils();
//			try
//			{
//				imageUtils.saveImageAsJpg(upload, breSavePath, 300, 300,fileType);
//				
//			} catch (Exception e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			pic.setUrl(url);
//			pic.setBreviaryUrl(breUrl);
//			
//		}
//		this.merge(pic);
//		
//	}

	public void delPic(String picId)
	{
		// TODO Auto-generated method stub
		SceneryPic pic = new SceneryPic();
		pic = (SceneryPic)this.get(picId);
		String delPath = ServletActionContext.getRequest().getRealPath(pic.getUrl());
		String delBrePath = ServletActionContext.getRequest().getRealPath(pic.getBreviaryUrl());
		String delMiniPath = ServletActionContext.getRequest().getRealPath(pic.getMiniUrl());
		this.delFile(delPath);
		this.delFile(delBrePath);
		this.delFile(delMiniPath);
		this.removeById(picId);
	}
	public void delPicture(String delPath)
	{
		String path = ServletActionContext.getRequest().getRealPath(delPath);
		this.delFile(path);
	}
	public SceneryPic getSceneryPic(String picId)
	{
		// TODO Auto-generated method stub
		String hql = "from SceneryPic as pic left outer join fetch pic.areas  where pic.pictureId = ?";
		List<SceneryPic> picList = this.find(hql, new Object[]{picId});
		if(picList.size()!=0) return picList.get(0);
		return null;
	}

	public List<SceneryPic> getPicListByScenery(String sceneryId)
	{
		// TODO Auto-generated method stub
		String hql = "from SceneryPic as pic where pic.scenery.sceneryId = ?";
		List<SceneryPic> picList = this.find(hql, new Object[]{sceneryId});
		if(picList.size()!=0) return picList;
		else return null;
	}
	public Page getClientPicListByArea(String areaId,int start,int pageNo)
	{
		String hql = "select pic from SceneryPic as pic left outer join pic.areas as area where area.areaId = ?";
		Page page = new Page();
		page = this.PageQuery(hql, start, pageNo, new Object[]{areaId});
		return page;
	}
	public List<SceneryPic> getPicListByArea(String areaId)
	{
		String hql = "select pic from SceneryPic as pic left outer join pic.areas as area where area.areaId = ?";
		List<SceneryPic> picList = this.find(hql, new Object[]{areaId});
		if(picList.size()!=0) return picList;
		else return null;
	}
	public void addPic(File upload, String savePath, String fileName, String fileType)
	{
		// TODO Auto-generated method stub
		if(upload!=null)
		{
			try
			{
				FileUtils.UploadFile(upload, savePath, fileName);
				
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public int getCountPicByScenery(String sceneryId)
	{
		// TODO Auto-generated method stub
		String hql = "select Count(*) from SceneryPic as pic where pic.scenery.sceneryId = ?";
		Long result = (Long)this.find(hql, new Object[]{sceneryId}).get(0);
		return result.intValue();
	}
//	public List<SceneryPic> getAllPic()
//	{
//		// TODO Auto-generated method stub
//		String hql = "select pic from SceneryPic as pic left outer join fetch pic.areas as areas";
//		return this.find(hql, new Object[]{});
//	}

}
