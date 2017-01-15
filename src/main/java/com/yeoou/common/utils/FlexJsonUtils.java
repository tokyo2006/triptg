package com.yeoou.common.utils;

import java.util.List;

import flexjson.JSONSerializer;

/**
 * <p>
 * Title: 实体转换为JSON数据
 * </p>
 * <p>
 * Description: 可以对数据模型进行自定义的深度转换
 * <code>
 * FlexJsonUtils jsontool = new FlexJsonUtils();
 * jsonRegion = jsontool.getJsonString(region, new String[]{"children","children.children"}, new String[]{"area","children.area","children.children.area","class","children.class","children.children.class","parent.parent","children.parent.parent","children.children.parent.parent","parent.area","children.parent.area","children.children.parent.area"}, "region");
 * </code>
 * 当include属性phoneNumbers的时候JSON转换的时候会将其转换为包含phoneNumbers的JSON数据
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2008-01-10
 */
public  class FlexJsonUtils
{
	/**
	 * 转换JSON数据
	 * @param o         需要转换的对象
	 * @param include   需要显示的属性
	 * @param exclude   需要隐藏的属性
	 * @param title     标题
	 * @return          转换完成的json数据
	 */
	public  String getJsonString(Object o,String[] include,String[] exclude,String title)
	{
		String jsonString=null;
		JSONSerializer jsonSerializer = new JSONSerializer();

		if(title!=null&&!"".equals(title))	{
			jsonSerializer.rootName(title);
		}

		if((include == null)&&(exclude!=null))
		{
			jsonString =   jsonSerializer.exclude(exclude).serialize(o);
		}
		else if((include == null)&&(exclude==null))
		{
			jsonString =   jsonSerializer.serialize(o);
		}
		else if((include != null)&&(exclude==null))
		{
			jsonString =   jsonSerializer.include(include).serialize(o);
		}
		else
		{
			jsonString =   jsonSerializer.exclude(exclude).include(include).serialize(o);
		}
		return jsonString;
	}
	/**
	 * 
	 * @param list		需要转换的列表
	 * @param include   需要显示的属性
	 * @param exclude   需要隐藏的属性
	 * @param title		标题
	 * @return			转换完成的json数据
	 */
	public  String getJsonString(List list,String[] include,String[] exclude,String title)
	{
		
		StringBuilder sb = new StringBuilder("[");
		if(list!=null)
		{
			int length = list.size();
			if(length!=0)
			{
				for(int i=0;i<length-1;i++)
				{
					sb.append(this.getJsonString(list.get(i), include, exclude, title));
					sb.append(",");
				}
				sb.append(this.getJsonString(list.get(length-1), include, exclude, title));
			sb.append("]");
			}
			else
			{
				return "[]";
			}
		}
		else
		{
			sb.append("]");
		}
		return  sb.toString();
	}
}
