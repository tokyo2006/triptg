<#-- header -->
<#macro simpleHead>
<div class="gm-master cls">
<div class="gm-master-logo"><a href="${base}"><img src="${base}/${webSite.smallLogo!""}" alt="兔游旅行网"></a></div>
<div class="gm-master-search">
<div class="gm-master-search-sc"><a href="http://www.tutu6.com/" target="_blank">首页</a>&nbsp;|&nbsp;<a href="http://www.tutu6.com/team/s.shtml?teamFlag=1&showType=1" target="_blank">线路超市</a>&nbsp;|&nbsp;<a href="${base}/indexZb.html" target="_blank">周边游</a>&nbsp;|&nbsp;<a href="${base}/indexGny.html" target="_blank">国内游</a>&nbsp;|&nbsp;<a href="${base}/indexCjy.html" target="_blank">出境游</a>&nbsp;|&nbsp;<a href="${base}/indexZyx.html" target="_blank">自由行</a>&nbsp;|&nbsp;<a href="${base}/indexQy.html" target="_blank">企业团队</a>&nbsp;|&nbsp;<a href="${base}/visa/visaList.shtml" target="_blank">签证</a>&nbsp;|&nbsp;<a href="${base}/indexCsjd.html" target="_blank">城市景点</a></div>

	<form method="get" target="_blank" action="${base}/team/s.shtml" onsubmit="return globalSearch()">
			<fieldset>
			<legend>兔游一下</legend>
			
			
			<input id="mall_search_input" class="" size="24" name="keyWord" placeholder="输入线路名"  type="search"><button type="submit">兔游一下</button>

			</fieldset>
			<script type="text/javascript">
			//(function(){
				if(window.navigator.userAgent.indexOf("WebKit")== -1){
					document.getElementById('mall_search_input').className="";
					document.getElementById('mall_search_input').value="输入线路名";
					document.getElementById('mall_search_input').onfocus=function(){
						if(this.value=='输入线路名'){this.value='';this.className='focus';}
					}
					document.getElementById('mall_search_input').onblur=function(){
						if(this.value==''){this.value='输入线路名';this.className='';}
					}
				}
			//})()
				function globalSearch(){
					if(document.getElementById('mall_search_input').value==""||document.getElementById('mall_search_input').value=="输入线路名"){
						document.getElementById('mall_search_input').focus()
						return false;
					}
				}
			</script>
		</form></div>
	
	<div class="gm-master-ac"><div class="gm-master-dashboard">
		
			
		<span class="more" onmouseover="this.getElementsByTagName('span')[0].style.display='block';" onmouseout="this.getElementsByTagName('span')[0].style.display='none';">更多服务
			<span>
				<a href="javascript:void(0)" class="moreplc">更多服务</a>
				<a target="_blank" href="${base}/">兔游首页</a>
				<a target="_blank" href="${base}/homePage/help/402881e41fcf8690011fcf8b3c390001.html">帮助中心</a>
			</span>
		</span>
	</div>
	<div class="gm-master-nav">
		<span class="cl"></span>
		<span class="cr"></span>
		在线咨询：
<a href=http://float2006.tq.cn/static.jsp?uin=8629286&ltype=0 target=_blank>国内旅游<img src=http://float2006.tq.cn/staticimg.jsp?uin=8629286&style=1 border='0'></a>&nbsp;&nbsp;
<a href=http://float2006.tq.cn/static.jsp?uin=8629282&ltype=0 target=_blank>出境旅游<img src=http://float2006.tq.cn/staticimg.jsp?uin=8629282&style=1 border='0'></a>&nbsp;&nbsp;
<a href=http://float2006.tq.cn/static.jsp?uin=8629288&ltype=0 target=_blank>企业团队<img src=http://float2006.tq.cn/staticimg.jsp?uin=8629288&style=1 border='0'></a>&nbsp;
	</div>
	</div>
</div>
</#macro>