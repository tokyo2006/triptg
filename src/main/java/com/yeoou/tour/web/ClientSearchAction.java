package com.yeoou.tour.web;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.yeoou.common.web.BaseActionSupport;
/**
 * <p>
 * Title: 前台搜索模块
 * </p>
 * <p>
 * Description: 对用户输入的关键字进行搜索，搜索方式由用户选择：1.按景点，2.按开班计划
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class ClientSearchAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private int searchType = 0;
	private String teamUrl = "";
	private String keyWord = "";
	private String keyWord2 = "";
	private String sceneryUrl = "";
	/**
	 * 搜索相关处理模块
	 * @return
	 */
	public String getSearchPath()
	{
		
		try
		{
			keyWord = keyWord.trim();
			
			if(!keyWord2.equals(""))
			{
				keyWord2 = keyWord2.trim();
				keyWord2 = java.net.URLEncoder.encode(keyWord2, "UTF-8");
				sceneryUrl = "scenery/sceneryList.shtml?keyWord="+keyWord2;
				ServletActionContext.getResponse().sendRedirect(sceneryUrl);
				return null;
			}
			keyWord = java.net.URLEncoder.encode(keyWord, "UTF-8");
			teamUrl = "team/s.shtml?keyWord="+keyWord;
			sceneryUrl = "scenery/sceneryList.shtml?keyWord="+keyWord;
			if(searchType==0)
			{
				ServletActionContext.getResponse().sendRedirect(teamUrl);
			}
			else
			{
				ServletActionContext.getResponse().sendRedirect(sceneryUrl);
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getSceneryUrl()
	{
		return sceneryUrl;
	}
	public void setSceneryUrl(String sceneryUrl)
	{
		this.sceneryUrl = sceneryUrl;
	}
	public int getSearchType()
	{
		return searchType;
	}
	public void setSearchType(int searchType)
	{
		this.searchType = searchType;
	}
	public String getTeamUrl()
	{
		return teamUrl;
	}
	public void setTeamUrl(String teamUrl)
	{
		this.teamUrl = teamUrl;
	}
	public String getKeyWord()
	{
		return keyWord;
	}
	public void setKeyWord(String keyWord)
	{
		this.keyWord = keyWord;
	}
	public String getKeyWord2()
	{
		return keyWord2;
	}
	public void setKeyWord2(String keyWord2)
	{
		this.keyWord2 = keyWord2;
	}
}
