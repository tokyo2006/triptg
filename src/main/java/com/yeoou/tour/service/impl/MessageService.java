package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Message;
import com.yeoou.tour.service.IMessageService;

public class MessageService extends HibernateEntityDao<Message> implements
		IMessageService
{

	public Page getAllMessage(String reciver, int start, int limit, String dir, String sort)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("from Message as msg where 1=1");
		List<Object> val = new ArrayList<Object>();
		if(!reciver.equals(""))
		{
			reciver = "%"+reciver+"%";
			sb.append(" and msg.reicver like ?");
			val.add(reciver);
		}
		if(sort.equals("rdatetimeStr"))
		{
			sort = "rdatetime";
		}
		if(sort.equals("sdatetimeStr"))
		{
			sort = "sdatetime";
		}
		sb.append(" order by msg.");
		sb.append(sort);
		sb.append(" ");
		sb.append(dir);
		Object[] values = new Object[val.size()];
		for(int i=0;i<val.size();i++)
		{
			values[i]=val.get(i);
		}
		return this.PageExtQuery(sb.toString(), start, limit, values);
	}

	public Page getMessagesByTeam(String groupId, int start, int pageNo, String sort, String dir)
	{
		// TODO Auto-generated method stub
		String hql = "from Message as msg where msg.groupId = ?";
		hql = hql + "order by msg."+sort+" "+dir;
		return this.PageQuery(hql, start, pageNo, new Object[]{groupId});
	}



	

}
