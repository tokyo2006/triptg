<#-- banner -->
<#macro banner>


<div class="pheader">
		<span class="xl"></span><span class="xr"></span>
			<!-- {{start:header tab -->
						<div class="header-tab">
			<ul>
			<#if bannerFlag==1>
			<li class="first current"><a href="${base}/index.html">首页</a></li>
			<#elseif bannerFlag==9>
			<li class="first" style="background:none;"><a href="${base}/index.html">首页</a></li>
			<#else>
			<li class="first"><a href="${base}/index.html">首页</a></li>
			</#if>
			<#if bannerFlag==9>
			<LI class="current"><A href="${base}/team/s.shtml?teamFlag=1&showType=1">线路超市</A><b></b></LI>
			<#elseif bannerFlag==2>
			<LI style="background:none;"><A href="${base}/team/s.shtml?teamFlag=1&showType=1">线路超市</A><b></b></LI>
			<#else>
			<LI><A href="${base}/team/s.shtml?teamFlag=1&showType=1">线路超市</A><b></b></LI>
			</#if>
			<#if bannerFlag==2>
			<LI class="current"><A href="${base}/indexZb.html">周边旅游</A> </LI>
			<#elseif bannerFlag==3>
			<LI style="background:none;"><A href="${base}/indexZb.html">周边旅游</A> </LI>
			<#else>
			<LI><A href="${base}/indexZb.html">周边旅游</A> </LI>
			</#if>
			<#if bannerFlag==3>
			<LI class="current"><A href="${base}/indexGny.html">国内旅游</A> </LI>
			<#elseif bannerFlag==4>
			<LI style="background:none;"><A href="${base}/indexGny.html">国内旅游</A> </LI>
			<#else>
			<LI><A href="${base}/indexGny.html">国内旅游</A> </LI>
			</#if>
			<#if bannerFlag==4>
			<LI class="current"><A href="${base}/indexCjy.html">出境度假</A> </LI>
			<#elseif bannerFlag==5>
			<LI style="background:none;"><A href="${base}/indexCjy.html">出境度假</A></LI>
			<#else>
			<LI><A href="${base}/indexCjy.html">出境度假</A> </LI>
			</#if>	
			<#if bannerFlag==5>
			<LI class="current"><A href="${base}/indexZyx.html">自由行</A> </LI>
			<#elseif bannerFlag==6>
			<LI style="background:none;"><A href="${base}/indexZyx.html">自由行</A></LI>
			<#else>
			<LI><A href="${base}/indexZyx.html">自由行</A> </LI>
			</#if>	
			<#if bannerFlag==6>
			<LI class="current"><A href="${base}/indexQy.html">企业团队</A> </LI>
			
			<#else>
			<LI><A href="${base}/indexQy.html">企业团队</A> </LI>
			</#if>	
			<#if bannerFlag==7>
			<LI><A href="http://bbs.tutu6.com/bbs">社区</A><b></b></LI>
			<#elseif bannerFlag==8>
			<LI style="background:none;"><A href="http://bbs.tutu6.com/bbs">社区</A><b></b></LI>
			<#else>
			<LI><A href="http://bbs.tutu6.com/bbs">社区</A><b></b></LI>
			</#if>	
			<#if bannerFlag==8>
			<LI class="current"><A href="${base}/indexCsjd.html">城市景点</A></LI>
			<#else>
			<LI><A href="${base}/indexCsjd.html">城市景点</A></LI>
			</#if>	
				
			</ul>
			
			</div>
			<!-- }}end:header tab -->

			<!-- {{start:header search -->
			<div class="header-search">

	<!-- {{start:small ad -->
<STYLE>
				.ad200807245896214 {position:absolute;top:28px;left:2px;font-size:12px;white-space:nowrap;}
				.ad200807245896214 img {float:left;vertical-align:middle;background:url("${base}/${webSite.bannerLogo}") 0 0; _background:none; _filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, src="${base}/${webSite.bannerLogo}");}
				.ad200807245896214 a {color:#ddfcb7;text-decoration:none;}
				.ad200807245896214 a:hover {color:#ddfcb7;text-decoration:underline;}
				</STYLE>

				<div class="ad200807245896214"><a href="#" target="_blank"><img src="${base}/${webSite.bannerLogo}" width="168" height="71"></a></div>			
<!-- }}end:small ad -->
								
				<!-- search -->
				<div class="inner">
				<fieldset>
				<legend>兔游一下</legend>
					<form id="search-form-bottom" action="${base}/search.shtml">
						
						<div class="s1">
							<span>
							<select name="searchType">
								<#if (searchType??)&&(searchType==1)>
								<OPTION value=0 >线路搜索</OPTION> 
								<OPTION value=1 selected>景点搜索</OPTION> 	
								<#else>
								<OPTION value=0 selected>线路搜索</OPTION> 
								<OPTION value=1>景点搜索</OPTION> 
								</#if>
							</select>
							</span>
						</div>

						<div class="s2">
							<input id="keyWord" name="keyWord" value="" accesskey="s" type="text">
						</div>

						<div class="s4">
						<button type="submit">兔游一下</button>
						</div>

						<div class="s5">
							<a href="${base}/team/go.shtml" target="_blank">线路分类</a><br><a href="http://www.tutu6.com/visa/visaList.shtml" target="_blank">各国签证</a>
						</div>
					
					</form>
				</fieldset>
				</div>
				<!-- //search -->

			</div>
			<!-- }}end:header search -->
			
			<!-- {{start:hot word -->
<div class="header-words">
	<div class="inner">
	<strong>热门搜索：</strong> 
	<#if gg_ss??>
	<#list gg_ss as hotSight>
	
	<A href="${base}/${hotSight.url}" <#if hotSight.subTitle!="">class=highlight</#if> target=_blank>${hotSight.title}</A><#if hotSight_has_next>&nbsp;&nbsp;</#if>
	</#list>
	</#if>
	</div>
	<span class="bc"><span class="bcr"></span></span>
</div>
<!-- }}end:hot word -->

		</div>
		
</#macro>
		