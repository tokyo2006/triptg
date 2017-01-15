package com.yeoou.common.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.FilterDroplistCell;
import org.extremecomponents.table.core.TableModel;

public class ExactMatchFilterDroplistCell extends FilterDroplistCell {
	private static Log logger = LogFactory.getLog(FilterDroplistCell.class);
	
    protected List getFilterDropList(TableModel model, Column column) {
        List droplist = new ArrayList();
        String filterValue = model.getLimit().getFilterSet().getFilterValue(column.getProperty());
        
        logger.info(filterValue);
        
        Collection beans = model.getCollectionOfBeans();
        for (Iterator iter = beans.iterator(); iter.hasNext();) {
            Object bean = iter.next();
            try {
                String propertyValue = BeanUtils.getProperty(bean, column.getProperty());
                logger.info(propertyValue);
                if (StringUtils.isNotBlank(filterValue) && !propertyValue.equals(filterValue)) {
                    continue;
                }
            } catch (Exception e) {
                logger.debug("Problems getting the droplist.", e);
            }
        }

        Collections.sort(droplist);

        return droplist;
    }
}
