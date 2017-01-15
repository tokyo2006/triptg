﻿<#-- 分页 -->
<#macro pagination>
     <#if (publicPage.totalCount == 0)>
        <table border="0" align="center" cellpadding="3" cellspacing="3">
            <tr><td align="center"><font color="#ff0000"><strong>没有可显示的项目</strong></font></td></tr>
        </table>
    <#else>
        <#assign p_count = publicPage.getTotalPageCount() >
        <#assign p_index = publicPage.getCurrentPageNo() >
        <table border="0" align="center" cellpadding="3" cellspacing="3">
            <tr>
                <td class="pageNonClickable">共<b>${publicPage.totalCount}</b>条记录，<b>共#{p_count}</b>页：</td>
                <#-- 是否显示"上一页": -->
                <#if (publicPage.hasPreviousPage()) >
                <#assign p_tmp = (p_index - 1) >
                    <td class="pageClickable" onMouseOver="hoverPage(this)" onMouseOut="normalPage(this)" onClick="location.assign(getPageLocation(${p_tmp}))">&nbsp;上一页&nbsp;</td>
                <#else>
                    <td class="pageUnclickable">&nbsp;首页&nbsp;</td>
                </#if>
                <#-- 如果前面页数过多,显示"...": -->
                <#if (p_index>5) >
                	<#assign p_prevPages = (p_index - 5) >
                	<#assign p_start = (p_index - 3) >
                	<td class="pageClickable" onMouseOver="hoverPage(this)" onMouseOut="normalPage(this)" onClick="location.assign(getPageLocation(1))">&nbsp;1&nbsp;</td>
                    <td class="pageUnclickable">&nbsp;…&nbsp;</td>
                <#else>
                	<#assign p_start = 1 >
                </#if>
                <#-- 显示当前页附近的页 -->
                <#assign p_end = (p_index + 5) >
                <#if (p_end > p_count)>
                	<#assign p_end = p_count >
                </#if>
                <#list p_start..p_end as i>
                
                    <#if (i==p_index) >
                        <td class="pageNonClickable"><b><font color="#FF0084">${i}</font></b></td>
                    <#else>
                        <td class="pageClickable" onMouseOver="hoverPage(this)" onMouseOut="normalPage(this)" onClick="location.assign(getPageLocation(${i}))">&nbsp;${i}&nbsp;</td>
                    </#if>
                </#list>
                <#-- 如果后面页数过多,显示"...": -->
                <#if (p_end < p_count)>
                	<#assign p_nextPages = (p_end + 1) >
                	<#if (p_nextPages != p_count)>
                    <td class="pageUnclickable">&nbsp;…&nbsp;</td>
                    </#if>
                    <#if p_end<p_count>
                    <td class="pageClickable" onMouseOver="hoverPage(this)" onMouseOut="normalPage(this)" onClick="location.assign(getPageLocation(${p_nextPages}))">&nbsp;${p_count}&nbsp;</td>
                    </#if>
                </#if>
                <#-- 显示"下一页": -->
                <#if (publicPage.hasNextPage())>
                	<#assign p_tmp = (p_index + 1) >
                    <td class="pageClickable" onMouseOver="hoverPage(this)" onMouseOut="normalPage(this)" onClick="location.assign(getPageLocation(${p_tmp}))">&nbsp;下一页&nbsp;</td>
                <#else>
                    <td class="pageUnclickable">&nbsp;末页&nbsp;</td>
                </#if>
            </tr>
        </table>
    </#if> 
</#macro>
