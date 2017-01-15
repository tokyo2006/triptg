package com.yeoou.tour.web;

import java.util.Date;
import java.util.List;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.DateUtils;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Manager;
import com.yeoou.tour.model.Message;
import com.yeoou.tour.service.IMessageService;
import com.yeoou.tour.service.ITeamService;
/**
 * <p>
 * Title: 后台留言相关展示操作模块
 * </p>
 * <p>
 * Description: 删除留言，更新留言操作
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class MessageAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IMessageService messageService;
	private ITeamService teamService;
	private int start = 0;
	private int limit = 15;
	private String sort = "";
	private String dir = "";
	private String reciver = "";
	private String jsonString = "";
	private String msgId = "";
	private String reciverContain = "";
	private String delData;
	/**
	 * 显示留言信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllMessage()
	{
		Page page = new Page();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,totalCount:");
		page = messageService.getAllMessage(reciver, start, limit, dir, sort);
		if(page!=null)
		{
			List<Message> msgList = (List<Message>)page.getResult();
//			int size = msgList.size();
//			for(int i=0;i<size;i++)
//			{
//				List<Team> team = teamService.getTeamByGroupId(msgList.get(i).getGroupId());
//				if(team!=null)
//				{
//					msgList.get(i).setTeamName(team.get(0).getTeamName());
//				}
//				else
//				{
//					msgList.get(i).setTeamName("此线路已经删除!");
//				}
//			}
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(msgList, null, new String[]{"class"}, null));
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
	 * 获取单一留言信息（供更新模块使用）
	 * @return
	 */
	public String getSingleMessage()
	{
		Message msg = new Message();
		msg = (Message)messageService.get(msgId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder();
		sb.append("{success:true,message:");
		sb.append(jsonTool.getJsonString(msg, null, new String[]{"class"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 更新留言操作
	 * @return
	 */
	public String updateMessage()
	{
		Message msg = new Message();
		msg = (Message)messageService.get(msgId);
		msg.setReciverContain(reciverContain );
		Manager manager = this.getManagerInfo();
		msg.setReciver(manager.getName());
	    msg.setReciverId(manager.getUserId());
	    Date today = new Date();
	    String todayStr = "";
	    todayStr = DateUtils.formatDateTime(today);
	    today = DateUtils.parseDateTime(todayStr);
	    msg.setRdatetime(today);
	    msg.setRdatetimeStr(todayStr);
	    messageService.merge(msg);
	    jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 删除留言操作
	 * @return
	 */
	public String delMessage()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				messageService.removeById(delData.trim());
				jsonString = "{success:true}";
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					messageService.removeById(id[i].trim());
				}
				jsonString = "{success:true}";
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
	public int getLimit()
	{
		return limit;
	}
	public void setLimit(int limit)
	{
		this.limit = limit;
	}
	public IMessageService getMessageService()
	{
		return messageService;
	}
	public void setMessageService(IMessageService messageService)
	{
		this.messageService = messageService;
	}
	public String getMsgId()
	{
		return msgId;
	}
	public void setMsgId(String msgId)
	{
		this.msgId = msgId;
	}
	public String getReciver()
	{
		return reciver;
	}
	public void setReciver(String reciver)
	{
		this.reciver = reciver;
	}
	public String getReciverContain()
	{
		return reciverContain;
	}
	public void setReciverContain(String reciverContain)
	{
		this.reciverContain = reciverContain;
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
	public String getDelData()
	{
		return delData;
	}
	public void setDelData(String delData)
	{
		this.delData = delData;
	}
	public ITeamService getTeamService()
	{
		return teamService;
	}
	public void setTeamService(ITeamService teamService)
	{
		this.teamService = teamService;
	}
}
