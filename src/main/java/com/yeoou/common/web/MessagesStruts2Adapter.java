package com.yeoou.common.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.views.jsp.TagUtils;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.core.Messages;
import org.extremecomponents.table.resource.TableResourceBundle;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocaleProvider;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.util.ValueStack;
import com.yeoou.common.web.MessagesStruts2Adapter;

/**
 * <p>
 * Title: MessagesStruts2Adapter
 * </p>
 * <p>
 * Description: extremecomponents make use of struts2 international message:
 * 1. Define a class Implemented "org.extremecomponents.table.core.Messages".
 * 2. Configure the implemented class in the extremecomponents configuration file.
 * </p>
 * 
 * @author kensin
 * @version 1.0
 * @created 2007-12-06
 */
public class MessagesStruts2Adapter implements Messages {
	/**
     * logger.
     */
    private static Log log = LogFactory.getLog(MessagesStruts2Adapter.class);

    private TextProvider textProvider = null;

    private Messages defaultMessages;

    public MessagesStruts2Adapter() {
        super();
        this.defaultMessages = new TableResourceBundle();
    }

    public void init(Context context, Locale locale) {
        // First, read the extremecomponents international file.
        this.defaultMessages.init(context, locale);

        if (context.getContextObject() instanceof PageContext) {
            PageContext pageContext = (PageContext) context.getContextObject();
            ValueStack stack = TagUtils.getStack(pageContext);
            for (Object o : stack.getRoot()) {
                if (o instanceof TextProvider) {
                    this.textProvider = (TextProvider) o;
                    break;
                }
            }
        }
    }

    public String getMessage(String code) {
        return getMessage(code, null);
    }

    public String getMessage(String code, Object[] args) {
    	// search the struts configuration file priority, then search the extremecomponent configuration file.
        List<Object> theArgs = null;
        if (args != null) {
            theArgs = new ArrayList<Object>();
            for (Object arg : args) {
                theArgs.add(arg);
            }
        }

        String message = null;
        if (this.textProvider != null) {
            message = this.textProvider.getText(code, null, theArgs);
        }
        if (message == null) {
            message = defaultMessages.getMessage(code, args);
        }

        return message;
    }
    
    /**
     * prefix/suffix for missing entries.
     */
    public static final String UNDEFINED_KEY = "???"; //$NON-NLS-1$

    /**
     * @see LocaleResolver#resolveLocale(HttpServletRequest)
     */
    public Locale resolveLocale(HttpServletRequest request) {

        Locale result = null;
        ValueStack stack = ActionContext.getContext().getValueStack();

        Iterator iterator = stack.getRoot().iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();

            if (o instanceof LocaleProvider) {
                LocaleProvider lp = (LocaleProvider) o;
                result = lp.getLocale();

                break;
            }
        }

        if (result == null) {
            log.debug("Missing LocalProvider actions, init locale to default");
            result = Locale.getDefault();
        }

        return result;
    }

    /**
     * @see I18nResourceProvider#getResource(String, String, Tag, javax.servlet.jsp.PageContext)
     */
    public String getResource(String resourceKey, String defaultValue, Tag tag, PageContext pageContext) {

        // if resourceKey isn't defined either, use defaultValue
        String key = (resourceKey != null) ? resourceKey : defaultValue;

        String message = null;
        ValueStack stack = TagUtils.getStack(pageContext);
        Iterator iterator = stack.getRoot().iterator();

        while (iterator.hasNext()) {
            Object o = iterator.next();

            if (o instanceof TextProvider) {
                TextProvider tp = (TextProvider) o;
                message = tp.getText(key, null, (String) null);

                break;
            }
        }

        // if user explicitely added a titleKey we guess this is an error
        if (message == null && resourceKey != null) {
            //log.debug(Messages.getString("Localization.missingkey", resourceKey)); //$NON-NLS-1$
            message = UNDEFINED_KEY + resourceKey + UNDEFINED_KEY;
        }

        return message;
    }
}

