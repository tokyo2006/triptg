package com.yeoou.common.utils;

import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
/**
 * 本地化工具，提供国际化支持封装
 * @author kensin
 *
 */
public class LocaleUtils
{
    private Locale current;
    
	public void setCurrent(Locale current)
	{
		this.current = current;
	}

	public Map<String, Locale> getLocales()
	{
		Map<String, Locale> locales =new Hashtable<String, Locale>();
		ResourceBundle bundle = ResourceBundle.getBundle("i18n.MyResource",current);
		
        locales.put(bundle.getString("usen"), Locale.US);
        locales.put(bundle.getString("zhcn"), Locale.CHINA);
        return locales;
	}
}
