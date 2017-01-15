package com.yeoou.components.acegi.intercept.web;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.intercept.web.AbstractFilterInvocationDefinitionSource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import com.yeoou.components.acegi.AuthenticationHelper;
import com.yeoou.components.acegi.cache.AcegiCacheManager;
import com.yeoou.components.acegi.resource.Resource;
import com.yeoou.components.acegi.resource.ResourceDetails;

/**
 * 在resourceCache中获取当前调用方法相对应类型为 Url 的Resouce实例
 * lookupAttributes(String url) 方法被{@link org.acegisecurity.intercept.web.FilterSecurityInterceptor} 调用
 * useAntPath 是否选用ant path的匹配模式
 * protectAllResource 是否默认情况下所有的资源都需要受保护
 * convertUrlToLowercaseBeforeComparison 是否需要把Url转为小写后再进行比较
 * @author cac
 */
public class CacheBaseUrlDefinitionSource extends
		AbstractFilterInvocationDefinitionSource {

	private boolean convertUrlToLowercaseBeforeComparison = false;
	
	private boolean useAntPath = true;
	
	private boolean protectAllResource = false;
	
	private final PathMatcher pathMatcher = new AntPathMatcher();
	
	private final PatternMatcher matcher = new Perl5Matcher();
	
	private AcegiCacheManager acegiCacheManager;
	
	public void setAcegiCacheManager(AcegiCacheManager acegiCacheManager) {
		this.acegiCacheManager = acegiCacheManager;
	}

	/**
	 * 返回当前URL对应的权限
	 * @see org.acegisecurity.intercept.web.AbstractFilterInvocationDefinitionSource#lookupAttributes(java.lang.String)
	 */
	@Override
	public ConfigAttributeDefinition lookupAttributes(String url) {

		url = preDealUrl(url,isUseAntPath(),isConvertUrlToLowercaseBeforeComparison());

		List<String> urls = acegiCacheManager.getResourcesByType(Resource.RESOURCE_TYPE_URL);
		if(urls==null) urls.add("/**");
		//倒叙排序所有获取的url
		orderUrls(urls);

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Iterator iterator = urls.iterator(); iterator.hasNext();) {
			String resString = (String) iterator.next();		
			//以首先匹配成功的资源的权限作为当前Url的权限
			if (isResourceMatch(isUseAntPath(),url,resString)) {
				ResourceDetails urlResource = acegiCacheManager.getResourceFromCache(resString);
				CollectionUtils.addAll(authorities, urlResource.getAuthorities());
				break;
			}
		}

		return AuthenticationHelper.getCadByAuthorities(authorities, isProtectAllResource());
	}

	/**
	 * 把url资源按倒序排序
	 * @param urls
	 */
	private void orderUrls(List<String> urls) {
		Collections.sort(urls);
		Collections.reverse(urls);
	}
	
	/**
	 * 根据是否使用UseAntPath和是否字符小写化来预先格式化url
	 * @param url
	 * @param isUseAntPath
	 * @param isToLowercase
	 * @return
	 */
	private String preDealUrl(String url, boolean isUseAntPath,boolean isToLowercase){
		if (isUseAntPath) {
			// Strip anything after a question mark symbol, as per SEC-161.
			int firstQuestionMarkIndex = url.lastIndexOf("?");

			if (firstQuestionMarkIndex != -1) {
				url = url.substring(0, firstQuestionMarkIndex);
			}
		}
		if (isToLowercase) {
			url = url.toLowerCase();
		}
		return url;
	}
	
	
	
	/**
	 * 查看当前url和资源中的url是否匹配
	 * @param isUseAntPath
	 * @param runningUrl
	 * @param rescUrl
	 * @return
	 */
	private boolean isResourceMatch(Boolean isUseAntPath,String runningUrl, String rescUrl){
		if (isUseAntPath) {
			return pathMatcher.match(rescUrl, runningUrl);
		} else {
			Pattern compiledPattern;
			Perl5Compiler compiler = new Perl5Compiler();
			try {
				compiledPattern = compiler.compile(rescUrl, Perl5Compiler.READ_ONLY_MASK);
			} catch (MalformedPatternException mpe) {
				throw new IllegalArgumentException("Malformed regular expression: " + rescUrl, mpe);
			}
			return matcher.matches(runningUrl, compiledPattern);
		}
	}

	public Iterator getConfigAttributeDefinitions() {
		return null;
	}

	
	//---------getters and setters---------------------
	public void setConvertUrlToLowercaseBeforeComparison(
			boolean convertUrlToLowercaseBeforeComparison) {
		this.convertUrlToLowercaseBeforeComparison = convertUrlToLowercaseBeforeComparison;
	}

	public boolean isConvertUrlToLowercaseBeforeComparison() {
		return convertUrlToLowercaseBeforeComparison;
	}

	public boolean isUseAntPath() {
		return useAntPath;
	}

	public void setUseAntPath(boolean useAntPath) {
		this.useAntPath = useAntPath;
	}

	public void setProtectAllResource(boolean protectAllResource) {
		this.protectAllResource = protectAllResource;
	}

	public boolean isProtectAllResource() {
		return protectAllResource;
	}

}
