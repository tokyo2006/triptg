package com.yeoou.common.web;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.jmesa.core.filter.FilterMatcher;
import org.jmesa.core.filter.FilterMatcherMap;
import org.jmesa.core.filter.MatcherKey;
import org.jmesa.web.WebContext;
import org.jmesa.web.WebContextSupport;

import com.yeoou.common.utils.MessageUtils;

/**
 * <p>
 * Title: EntityWithValidFieldFilterMatcher
 * </p>
 * <p>
 * Description: Used to filter out values with the valid field.
 * </p>
 * 
 * @author kensin
 * @version 1.0
 * @created 2007-12-06
 */
public class EntityWithValidFieldFilterMatcher implements FilterMatcherMap , WebContextSupport{

    private WebContext webContext;

    public WebContext getWebContext() {
        return webContext;
    }
    public void setWebContext(WebContext webContext) {
        this.webContext = webContext;
    }
    public Map<MatcherKey, FilterMatcher> getFilterMatchers() {
    	Locale locale = null;
    	String str = MessageUtils.findValue("object.valid", locale);
    	
    	String str1 = MessageUtils.findValue("object.valid", (PageContext)webContext.getBackingObject());
    	
        Map<MatcherKey, FilterMatcher> filterMatcherMap = new HashMap<MatcherKey, FilterMatcher>();
        filterMatcherMap.put(new MatcherKey(String.class, "valid"), new StatusFilterMatcher(this.webContext));
        return filterMatcherMap;
    }
    
    private class StatusFilterMatcher implements FilterMatcher, WebContextSupport {
        private WebContext webContext;

        public StatusFilterMatcher() {
            // default constructor
        }

        public StatusFilterMatcher(WebContext webContext) {
            this.webContext = webContext;
        }

        public WebContext getWebContext() {
            return webContext;
        }

        public void setWebContext(WebContext webContext) {
            this.webContext = webContext;
        }
        public boolean evaluate(Object itemValue, String filterValue){
            if (itemValue == null) {
                return false;
            }
            String itemString = "";
            Locale locale = null;
            if (webContext != null) {
                locale = webContext.getLocale();
            }
            if((Boolean)itemValue){
                //itemString = WebUtils.getMessageString("object.valid", null, "default", locale);
            	itemString = MessageUtils.findValue("object.valid", locale);
            }else{
                itemString = MessageUtils.findValue("object.invalid", locale);
            }
                
            itemString =  itemString.toLowerCase();
            String filter = String.valueOf(filterValue).toLowerCase();
            
            //We use startswith instead of contains so that the filter "active" doesn't match "inactive"
            if (itemString.startsWith(filter)) {
                return true;
            }

            return false;
        }
    }

}
