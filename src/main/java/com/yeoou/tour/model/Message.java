package com.yeoou.tour.model;

import java.util.Date;

import com.yeoou.common.model.Genericmodel;

public class Message extends Genericmodel
{
	private static final long serialVersionUID = -7593904286561857302L;
	private String msgId;
	private String title;
	private String reciverContain;
	private String sendContain;
	private String reciver;
	private String sender;
	private String senderId;
	private String reciverId;
	private Date rdatetime;
	private Date sdatetime;
	private String email;
	private String groupId;
	private String rdatetimeStr;
	private String sdatetimeStr;
	private String teamName;
	public String getTeamName()
	{
		return teamName;
	}
	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getGroupId()
	{
		return groupId;
	}
	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}
	public String getMsgId()
	{
		return msgId;
	}
	public void setMsgId(String msgId)
	{
		this.msgId = msgId;
	}
	public Date getRdatetime()
	{
		return rdatetime;
	}
	public void setRdatetime(Date rdatetime)
	{
		this.rdatetime = rdatetime;
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
	public String getReciverId()
	{
		return reciverId;
	}
	public void setReciverId(String reciverId)
	{
		this.reciverId = reciverId;
	}
	public Date getSdatetime()
	{
		return sdatetime;
	}
	public void setSdatetime(Date sdatetime)
	{
		this.sdatetime = sdatetime;
	}
	public String getSendContain()
	{
		return sendContain;
	}
	public void setSendContain(String sendContain)
	{
		this.sendContain = sendContain;
	}
	public String getSender()
	{
		return sender;
	}
	public void setSender(String sender)
	{
		this.sender = sender;
	}
	public String getSenderId()
	{
		return senderId;
	}
	public void setSenderId(String senderId)
	{
		this.senderId = senderId;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getRdatetimeStr()
	{
		return rdatetimeStr;
	}
	public void setRdatetimeStr(String rdatetimeStr)
	{
		this.rdatetimeStr = rdatetimeStr;
	}
	public String getSdatetimeStr()
	{
		return sdatetimeStr;
	}
	public void setSdatetimeStr(String sdatetimeStr)
	{
		this.sdatetimeStr = sdatetimeStr;
	}
	@Override
	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((msgId == null) ? 0 : msgId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Message other = (Message) obj;
		if (msgId == null)
		{
			if (other.msgId != null)
				return false;
		} else if (!msgId.equals(other.msgId))
			return false;
		return true;
	}
	

}
