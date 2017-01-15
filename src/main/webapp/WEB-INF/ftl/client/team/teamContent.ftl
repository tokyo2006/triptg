<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<!--STATUS OK-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META content="${base}/${webSite.desc}" name=description>
<META content="${base}/${webSite.keywords}" name=keywords>
<title>${team.teamName}——要旅游 先兔游</title>
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/teamfront.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/module-shop3.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/team.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/theme3.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/route_calendar.css" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript">
//<![CDATA[
function resizeImg(img) {
	if (img.offsetWidth > img.height) {
		img.width = img.height;
		img.removeAttribute('height');
	}
	
	
}
//]]>
</script>
</head><body id="item-detail">
<#include "*/simpleHead.ftl">
<#include "*/search.ftl">
<#include "*/footer.ftl">
<div id="doc3">
	<div id="hd">
<!-- {start:global master header -->
<noscript><p class="nojs">您的浏览器已经禁用了脚本，这会影响您正常使用本站的功能。</p></noscript>
<@simpleHead />

<!-- }end:global master header --></div>
	<div id="bd">
				
		<!-- final nav -->
		<div class="final-nav">
										
						<a href="${base}/team/go.shtml">所有分类</a>&nbsp;&gt;&nbsp;
						
						<#if teamFlag==1>
							<a href="${base}/team/s.shtml?teamFlag=${teamFlag}">周边短线</a>&nbsp;&gt;&nbsp;
						<#elseif teamFlag==2>
							<a href="${base}/team/s.shtml?teamFlag=${teamFlag}" >国内长线</a>&nbsp;&gt;&nbsp;
						<#elseif teamFlag==3>
							<a href="${base}/team/s.shtml?teamFlag=${teamFlag}">出境旅游</a>&nbsp;&gt;&nbsp;
						<#elseif teamFlag==4>
							<a href="${base}/team/s.shtml?teamFlag=${teamFlag}" >自由行</a>&nbsp;&gt;&nbsp;
						</#if>
						
						线路详情
		</div>
		<!-- //final nav -->

		<!-- shop header -->
				<!-- {start:shop header -->


<div class="shop-header">

<div class="shop-header-mantle" style="background: transparent url<#if teamFlag==1>(${base}/${webSite.bannerzb})<#elseif teamFlag==2>(${base}/${webSite.bannergn})<#elseif teamFlag==3>(${base}/${webSite.bannercj})<#else>(${base}/${webSite.bannerzyx})</#if> repeat scroll 0% 0%; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;">
	<h1 class="invisible">坚持打造中国最阳光最安全旅游！</h1>	
</div>
<div class="shop-header-nav">
<ul class="cls">
		
		
		
		<li class="selected"><span class="inner-cls"><a>线路详情</a></span></li>
		
		
</ul>
<div class="hollow"></div>
</div>
</div>

<!-- }end:shop header -->
		<!-- //shop header -->

		<!-- ida-module -->
		<div class="ida-module cls">
			<!-- ida-main -->
			<div class="ida-main">
				<!-- final display -->
				<div class="final-display">
					<!-- final-mantle -->
					<div class="final-mantle cls">
						<div class="left">

							<div class="row cls">
								<h1>${team.teamName}</h1>
							</div>

							<div class="row cls">

								<!-- slider -->
								<div class="slider" id="PicContainer">
									<div class="cls">
										<div style="position: relative; display: none;" class="show_bigpic_div">
											<div class="show_bigpic"></div>
												<a class="show_bigpiclink" href="#"></a>
											</div>
											<#if pfList??>
											<#list pfList as bigPic>
												<#if bigPic_index=0>
												<a href="#"><img class="bigimg" src="${base}/public/20081205/image/blank.gif" style="background-image: url(${base}/${bigPic.breUrl}); opacity: 1"></a>
												<#else>
												<a href="#"><img class="bigimg" src="${base}/public/20081205/image/blank.gif" style="background-image: url(${base}/${bigPic.breUrl}); opacity: 1; display: none;"></a>
												</#if>
											</#list>
											</#if>
										</div>
										
										<ul>
										<#if pfList??>
										<#list pfList as smallPic>
											<#if smallPic_index=0>
											<li class="selected"><div class="img"><img src="${base}/${smallPic.breUrl}" onload="resizeImg(this);" height="46"><img src="${base}/public/20081205/image/blank.gif" class="blank"></div></li>
											<#else>
											<li class=""><div class="img"><img src="${base}/${smallPic.breUrl}" onload="resizeImg(this);" height="46"><img src="${base}/public/20081205/image/blank.gif" class="blank"></div></li>
											</#if>
										</#list>
										</#if>
										</ul>
								</div>
								<!-- //slider -->

								<!-- bid -->
								<div class="bid">
									<ul>
										<li class="first s1">
											<label class="k">价格：</label>
											<span class="v">
												<span class="price">${team.doorDisPrice!""}.<small>00</small></span>元起
												<span class="fee"><a href="#price">查看详细价格</a></span>
											</span>
										</li>
										<li class="s2">
											<label class="k">交通：</label>
											<span class="v"><span class="fee">${team.goTraffic}&nbsp;往&nbsp;&nbsp;/&nbsp;&nbsp;<#if team.comeTraffic??>${team.comeTraffic}<#else>自选</#if>&nbsp;返</span>
											</span>
										</li>
										<li class="gobid">
												<a href="#order" class="btn-bid">立即购买</a>
												<a href="${base}/team/getPrintTeam/${team.teamId!""}" target="_blank" class="add_cart_link btn-cart">打印该行程</a>
										</li>
										
										<li>
											<label class="k">出游天数：</label>
											<span class="v"><span class="day">${team.teamday}</span>&nbsp;天</span>
										</li>
										<li>
											<label class="k">出发城市：</label>
											<span class="v">${team.area.name}</span>
										</li>
										<li>
											<label class="k">发团日期：</label>
											<#if team.correct==1>
											<span class="v"><a href="#startData">点击查看</a></span>
											<#else>
											<span class="v">开班日期视具体团队人数而定，请来电资讯。</span>
											</#if>
										</li>
										<li>
											<label class="k">提前报名：</label>
											<span class="v">请尽量提前<span class="day">${team.pushDay}</span>天以上报名</span>
										</li>
										<li>
											<label class="k">浏&nbsp;&nbsp;览&nbsp;&nbsp;量：</label>
											<span class="v"><span id="page_view"><strong>${tcnt.hit}</strong>&nbsp;次</span></span>
										</li>
									</ul>
									
									<div class="bfb">
										<p class="bfb-txt"><span>
										<font color="blue"><u>市内可上门服务</u></font>，兔游让旅行更阳光</span></p>
										<strong>兔游旅游流程</strong>
										<p class="bfb-prc">
										订购线路&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单确认&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签订合同付款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开心出行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回家后回访</p>

									</div>
								</div>
								<!-- //bid -->

							</div>

						</div>
						<div class="right"><div class="inner"></div></div>
					</div>
					<!-- //final-mantle -->

				</div>
				<!-- //final display -->

				<!-- {{{{start:my mantle tab -->
				<div class="final-tab">
					<ul class="cls">
						<li class="first selected"><a href="#detail">线路详情</a></li>
						<li><a href="#price">价格明细</a></li>
						<li><a href="#tip">温馨提醒</a></li>
						<li><a href="#order">我要预定</a></li>
						<li><a href="#comment">在线留言</a></li>
						<li><a href="#recom">推荐线路</a></li>
						<li><a href="#howto">签约付款</a></li>
					</ul>
					<div class="my-mantle-tab-bot"></div>
				</div>
				<!-- {{{{start:my mantle tab -->
				<#if line.types??>
				<div id="item-desc"><!-- param -->
				
	<div class="final-param">
		<ul class="cls">
			<li>游玩标签：<#list line.types as typeContent>${typeContent.name}<#if typeContent_has_next>，</#if></#list></li>
		</ul>
	</div>
<!-- //param -->


</div>
</#if>
<!-- content -->

 <!--出发日期与价格 -->
<div id="tourCalendar">
	<h2 class="route_t_green"><a name="startData"></a><img src="${base}/public/20081205/image/tourCalendarSample.gif">出发日期与价格 <span>(以下位置及价格信息仅供参考，具体情况请电询，旺季位置及价格变化较快，请您谅解)</span></h2>
	<div class="clear">
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody><tr>
	<td id="left_calendar" align="left" valign="top">
	<div id="show_cal1">
	<table class="tCal" border="1" cellpadding="2" cellspacing="0">
	<thead>
	<tr><td colspan="7"><div style="position: relative; width: 100%;"><span class="tCalMonth">${currDate!""}</span></div>
	</td>
	</tr>
	<tr><th><font color=red>日</font></th><th>一</th><th>二</th><th>三</th><th>四</th><th>五</th><th>六</th></tr>
	</thead>
	<tbody>
	<#list weekList as week>
	<tr>
	<#list week.days as days>
	<#if days??>
		<#if (days.team??)&&(team.correct!=0)>
			<#if (days.day<today)&&(calMonth1==currMonth)>
			<td class="tCalPassed tCalDay"><span class="date_span">${days.day}</span><br/><span class="price_span">${days.team.doorDisPrice}<span class="tCalYuan">元</span></span></td>
			<#elseif (days.day=today)&&(calMonth1==currMonth)>
			<td class="tCalToday tCalDay"><span class="date_span">${days.day}</span><br>今天</td>
			<#else>
			<td class="tCalDay tCalPrice">
			<a href="javascript:void(0)" title="报名" class="calendar_bg">
			<span class="date_span">${days.day}</span>
			<span class="site_span cyellow"> </span><br>
			<span class="price_span">${days.team.doorDisPrice}<span class="tCalYuan">元</span></span></a></td>
			</#if>
		<#else>
			<#if days.day=today&&(calMonth1==currMonth)>
			<td class="tCalToday tCalDay"><span class="date_span">${days.day}</span><br>今天</td>
			<#else>
			<td class="tCalPassed tCalDay"><span class="date_span">${days.day}</span><br>&nbsp;</td>
			</#if>
		</#if>
	<#else>
		<td>&nbsp;</td>
	</#if>
	
	</#list>
	</tr>
	</#list>
	
	</tbody></table></div></td>
	
	<td width="20"></td>
	<td id="right_calendar" align="right" valign="top">
	<div id="show_cal2">
	<table class="tCal" border="1" cellpadding="2" cellspacing="0">
	<thead><tr><td colspan="7">
	<div style="position: relative;">	<span class="tCalMonth">${nextDate!""}</span> </div></td></tr>
	<tr><th><font color=red>日</font></th><th>一</th><th>二</th><th>三</th><th>四</th><th>五</th><th>六</th></tr></thead>
	<tbody>
	
   <#list nextWeekList as week>
	<tr>
	<#list week.days as days>
	<#if days??>
		<#if (days.team??)&&(team.correct!=0)>
			
			<#if (days.day<today)&&(calMonth2==currMonth)>
			<td class="tCalPassed tCalDay"><span class="date_span">${days.day}</span><br/><span class="price_span">${days.team.doorDisPrice}<span class="tCalYuan">元</span></span></td>
			<#elseif (days.day=today)&&(calMonth2==currMonth)>
			<td class="tCalToday tCalDay"><span class="date_span">${days.day}</span><br>今天</td>
			<#else>
			<td class="tCalDay tCalPrice">
			<a href="javascript:void(0)" title="报名" class="calendar_bg">
			<span class="date_span">${days.day}</span>
			<span class="site_span cyellow"> </span><br>
			<span class="price_span">${days.team.doorDisPrice}<span class="tCalYuan">元</span></span></a></td>
			</#if>
		<#else>
			
			<#if days.day=today&&(calMonth2==currMonth)>
			<td class="tCalToday tCalDay"><span class="date_span">${days.day}</span><br>今天</td>
			<#else>
			<td class="tCalPassed tCalDay"><span class="date_span">${days.day}</span><br>&nbsp;</td>
			</#if>
		</#if>
	<#else>
		<td class="tCalPassed tCalDay"><span class="date_span"></span><br>&nbsp;</td>
	</#if>
	
	</#list>
	</tr>
	</#list>
	
	
	
	
	</tbody></table></div></td>
	
	</tr></tbody></table>
	</div>
</div>
						
<div class="des-wrapper">
	<h2><a name="detail"></a>
	<span class="des-wrapper-title"><span>&nbsp;&nbsp;&nbsp;&nbsp;线路详情&nbsp;&nbsp;&nbsp;&nbsp;</span></span></h2>
	<span class="backtop"><a href="#">回到页首</a></span>
</div>
		
<div class="des-wrapper-content">
	<div class="des-wrapper-wrap">
		<#if line.feature??>	
		<#if line.feature!="">							
			<DIV class=tourSection>
				<H2>线路特色</H2>
					<DIV class=tourSectionContent>
						<P>	${line.feature!""}<BR></P>
					</DIV>
			</DIV>								
		</#if>
		</#if>								
	<DIV class=tourSection id=tourPlan>
		<H2>行程</H2>
			<DIV class=tourSectionContent>
				<#list conList as content>
				<H3>第${content_index+1}天</H3>
				<P class=tourPlanCity>${content.title}</P>
				<#if content.puList?size!=0>
				<DIV class="tourPlanPlacesList clear">
					<#list content.puList as scePic>

						<DIV class=route_view_module>
						
						<div class="scePic">
						<img src="${base}/public/20081205/image/blank.gif" class="blank">
											<a target='_blank' href="${base}/scenery/sceneryDetail.shtml?sceneryId=${scePic.id!""}">
											<img onload="resizeImg(this);" height=94 src="${base}/${scePic.breUrl}"></a>
											<img src="${base}/public/20081205/image/blank.gif" class="blank">
						</div>
							
						<DIV class=placename><A class=cdblue href="${base}/scenery/sceneryDetail.shtml?sceneryId=${scePic.id!""}" target=_blank>${scePic.name}</A></DIV></DIV>

					</#list>
				</DIV>
				</#if>
				<DIV class=tourPlanContent>
				<P>
				<P>${content.trips}</P>
				<P class=tourPlanFood>餐饮：${content.eating}</P>
				<P class=tourPlanHotel>住宿：${content.living}</P>
				</P>
				</DIV>
				</#list>
				<P class=tar style="COLOR: #999">以上行程、交通仅供参考，最终确认以所签协议为准。</P>
			</DIV>
	</DIV>	
	</div>
</div>
<!-- //content -->



<div class="price-wrapper">
	<h2><a name="price"></a>
	<span class="price-wrapper-title"><span>&nbsp;&nbsp;&nbsp;&nbsp;价格明细&nbsp;&nbsp;&nbsp;&nbsp;</span></span></h2>
	<span class="backtop"><a href="#">回到页首</a></span>
</div>
		
<div class="price-wrapper-content">
	<div class="price-wrapper-wrap final-priceList">
		<#if line.feeClude??>	
		<#if line.feeClude!="">							
			<DIV class=tourSection>
				<H2>报价包含</H2>
					<DIV class=tourSectionContent>
						<P>	${line.feeClude!""}<BR></P>
					</DIV>
			</DIV>								
		</#if>
		</#if>	
		<#if line.feeUnclude??>	
		<#if line.feeUnclude!="">							
			<DIV class=tourSection>
				<H2>报价不含</H2>
					<DIV class=tourSectionContent>
						<P>	${line.feeUnclude!""}<BR></P>
					</DIV>
			</DIV>								
		</#if>
		</#if>	
		<#if line.selfBuy??>	
		<#if line.selfBuy!="">							
			<DIV class=tourSection>
				<H2>自费项目</H2>
					<DIV class=tourSectionContent>
						<P>	${line.selfBuy!""}<BR></P>
					</DIV>
			</DIV>								
		</#if>
		</#if>	
						
			<DIV class=tourSection>
				<H2>详细价格</H2>
				
				
				<div class="table">
		<table>
			<tbody><tr>
				<th>价格类型</th>
				<th>价格</th>
				<th>门票</th>
				<th>用餐</th>
				<#if (team.ticketType==1)>
				<th>机票</td>
				<#else>
				<th>占座</td>
				</#if>
				<th>占床</th>
				<th>接送加价</th>
			</tr>
			<#list team.doorManPriceList as manPriceList>
			<tr class="first">
			<td class="s1">(成人价)${manPriceList.desc}</td>
			<td>￥${manPriceList.marketPrice}&nbsp;元</td>
			<td><#if manPriceList.ticket==true>
				<font color = "green">√</font>
			<#else>
				<font color = "red">×</font>
			</#if>
			</td>
			<td><#if manPriceList.eat==true>
				<font color = "green">√</font>
			<#else>
				<font color = "red">×</font>
			</#if>
			</td>
			<td><#if manPriceList.seat==true>
				<font color = "green">√</font>
			<#else>
				<font color = "red">×</font>
			</#if>
			</td>
			
			<td><#if manPriceList.bed==true><font color = "green">√</font>
			<#else><font color = "red">×</font>
			</#if>
			</td>
			<td><font color = "green">√</font></td>
			</tr>
			</#list>
			
			<#list team.doorChildPriceList as childPriceList>
			<tr class="first">
			<td class="s1">(儿童价)${childPriceList.desc}</td>
			<td>￥${childPriceList.marketPrice}&nbsp;元</td>
			<td><#if childPriceList.ticket==true>
				<font color = "green">√</font>
			<#else>
				<font color = "red">×</font>
			</#if>
			</td>
			<td><#if childPriceList.eat==true>
				<font color = "green">√</font>
			<#else>
				<font color = "red">×</font>
			</#if>
			</td>
			<td><#if childPriceList.seat==true>
				<font color = "green">√</font>
			<#else>
				<font color = "red">×</font>
			</#if>
			</td>
			
			<td><#if childPriceList.bed==true><font color = "green">√</font>
			<#else><font color = "red">×</font>
			</#if>
			</td>
			<td>
			<#if childPriceList.pickup==true>
				<font color = "green">√</font>
			<#else>
				<font color = "red">×</font>
			</#if></td>
			</tr>
			</#list>
				
					</tbody></table>
		</div>
					
			</DIV>								
							
			<DIV class=tourSection>
				<H2>集合地点</H2>
					<DIV class=tourSectionContent>
						<div class="table">
		<table>
			<tbody>
			<tr>
				<th>上车地点</th>
				<th>接送加价</th>
				<th>上车时间</th>
			</tr>
			<#list reAssList as ass>
			<tr class="first">
			<td align="left">${ass.address!""}<#if ass.jiesong!="含接送">&nbsp;(${ass.jiesong!""})</#if></td>
			<td>￥${ass.price!""}&nbsp;元</td>
			<td><#if ass.asstime!="">${ass.asstime!""}<#else>待定</#if></td>
			</tr>
			</#list>
			
			
				
					</tbody></table>
		</div>
					</DIV>
			</DIV>								
		
		
	</div>
</div>

<div class="tip-wrapper">
	<h2><a name="tip"></a>
	<span class="tip-wrapper-title"><span>&nbsp;&nbsp;&nbsp;&nbsp;温馨提醒&nbsp;&nbsp;&nbsp;&nbsp;</span></span></h2>
	<span class="backtop"><a href="#">回到页首</a></span>
</div>
		
<div class="tip-wrapper-content">
	<div class="tip-wrapper-wrap">
		
		<#if line.purchase??>	
		<#if line.purchase!="">							
			<DIV class=tourSection>
				<H2>购物信息</H2>
					<DIV class=tourSectionContent>
						<P>	${line.purchase!""}<BR></P>
					</DIV>
			</DIV>								
		</#if>
		</#if>	
		
		<#if line.remark??>	
		<#if line.remark!="">							
			<DIV class=tourSection>
				<H2>旅游备注</H2>
					<DIV class=tourSectionContent>
						<P>	${line.remark!""}<BR></P>
					</DIV>
			</DIV>								
		</#if>
		</#if>	
						
		<#if line.safe??>	
		<#if line.safe!="">							
			<DIV class=tourSection>
				<H2>安全守则</H2>
					<DIV class=tourSectionContent>
						<P>	${line.safe!""}<BR></P>
					</DIV>
			</DIV>								
		</#if>
		</#if>							
								
		
		
	</div>
</div>

			

									

				
					
					
					<!-- {{{start:预定线路 -->
					<div>
						<div class="tab-wrapper">
							<h2><a name="order"></a><span class="tab-wrapper-title"><span>&nbsp;&nbsp;&nbsp;&nbsp;预定线路&nbsp;&nbsp;&nbsp;&nbsp;</span></span></h2>
							<span class="backtop"><a href="#">回到页首</a></span>
						</div>
						<div class="tab-wrapper-content .final-order">
							<div class="tab-wrapper-wrap">

								
								<div>
	<div class="table">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tbody><tr>
    <td class="reserveWrap"  valign="top" width="280"> 
		<h5>电话预订</h5>
		<div class="reserveInfo" style="text-align: center;">
		<p style="font-size: 16px; font-family: simhei; margin-top: 1em; margin-bottom: 5px;">预订请拨<br/><span style="font-family: Arial;">
		
		<#switch teamFlag>
			<#case 1>
			<#list webSite.zbList as phone>
   			${phone.remark}
   			
   			<#if phone_index==1>
   			<#break>
   			</#if>
   			<#if phone_has_next><br></#if>
			</#list>
    		<#break>
    		
    		<#case 2>
			<#list webSite.gnList as phone>
   			${phone.remark}
   			<#if phone_index==1>
   			<#break>
   			</#if>
   			<#if phone_has_next><br></#if>
			</#list>
    		<#break>
    		
    		<#case 3>
			<#list webSite.cjList as phone>
   			${phone.remark}
   			<#if phone_index==1>
   			<#break>
   			</#if>
   			<#if phone_has_next><br></#if>
			</#list>
    		<#break>
    		
    		<#case 4>
			<#list webSite.cjList as phone>
   			${phone.remark}
   			<#if phone_index==1>
   			<#break>
   			</#if>
   			<#if phone_has_next><br></#if>
			</#list>
    		<#break>
		</#switch>
		</span></p>

		<p style="color: rgb(102, 102, 102);">周一至周五 9:00~20:30<br>周六、周日 9:00~18:00</p>
		</div>
	</td>
	<td width="20"> </td>
    <td class="reserveWrap" valign="top" width="525">
		<h5>网上预订</h5>

		<div class="reserveInfo"> 
		<form action="${base}/buy/step1.shtml" method="post" id="form_order" name="form_order" onsubmit="return false;">
		<input type="hidden" name="teamId" id="thisTeamId" value="${team.teamId}" />

				<table width="95%" cellspacing="10">

					<tbody><tr>
						<td colspan="4">
						<img src="${base}/public/20081205/image/reserve_step1.gif">
						</td>
					</tr>
					<tr>
					<#if team.correct==1>
						<td width="60" align="right">出发日期</td>
						<td colspan="3">
						<input id="begindate_page" name="startDateStr" onclick="show_plan();" value="yyyy-mm-dd" readonly="readonly" size="10" class="text" type="text"> <span style="color: red;">*</span>
						</td>
					<#else>
					<td colspan="4" align="center">
					该线路日期待定，请填写出游人数，兔游网客服会尽快给您答复。
					
					</td>
					</#if>
					</tr>
					<tr>
						<td align="right">成人数</td>
						<td width="80"><input id="manNum" name="manNum" maxlength="4" value="" size="5" class="text" type="text"> <span style="color: red;">*</span></td>
						<td width="60" align="right">儿童数</td>

						<td><input id="childNum" name="childNum" value="" maxlength="4" size="5" class="text" type="text"></td>
					</tr>

					<tr>
						<td>&nbsp;</td>
						<td colspan="3"><input onclick="view_order();" src="${base}/public/20081205/image/reserve_btn_next.gif" type="image"></td>
					</tr>

				</tbody></table>
				</form>
		</div>
	 </td>
  </tr>
 </tbody></table>
			</div>
</div>

							</div>
						</div>
					</div>
					<!-- }}}end:预定线路 -->
					
					
					
					<!-- {{{start:在线旅游 -->
					<div>
						<div class="tab-wrapper">
							<h2><a name="comment"></a><span class="tab-wrapper-title"><span>&nbsp;&nbsp;&nbsp;&nbsp;在线留言&nbsp;&nbsp;&nbsp;&nbsp;</span></span></h2>
							<span class="backtop"><a href="#">回到页首</a></span>
						</div>
						<div class="tab-wrapper-content .final-order">
						<div class="tab-wrapper-wrap">
						<div class="tourSectionContent">

<div id="small_class_list" class="clear mb5">
	<#if messageList??>
	<#list messageList as message>
	<div class="small_class_list_t">&nbsp;${message.sender!""}&nbsp;(${message.sdatetimeStr!""})&nbsp;问&nbsp;：</div>
	<div style="padding: 5px 10px;">${message.sendContain!""}</div>
	<div style="padding-left: 10px;" class="cgrey">兔游客服：<#if message.reciverContain??><#if message.reciverContain!="">${message.reciverContain!""}<#else>请等待兔游客服答复。</#if></#if></div>
	</#list>
	</#if>
</div>



    <br>
	
    <table width="98%" border="0" cellpadding="0" cellspacing="5">
      	  <tbody><tr>
        <td colspan="2" style="color:#5c5c5c;">您好，如对此条线路有任何问题，您可以在此留言，兔游网工作人员会在一个工作日内对您的问题进行解答，谢谢。</td>
      </tr>
	  	  	
      <tr>
        <td width="80" align="right" height="35">您的称呼：</td>
        <td align="left"><input class="text" name="senders" id="senders" maxlength="20" size="43" value="" type="text"></td>
      </tr>
	   
      <tr>
        <td width="80" valign="top" align="right">问题内容：</td>
        <td align="left"><textarea class="text" name="sendContain" cols="60" rows="6" id="sendContain"></textarea> </td>
      </tr>

		<tr>
        <td width="80" align="right" height="35">您的email：</td>
        <td align="left"><input class="text" name="email" id="email" maxlength="20" size="43" value="" type="text">&nbsp;(兔游客服人员会将答案发送到您的邮箱)</td>
      </tr>
	     
	  <tr>
        <td>&nbsp;</td>
        <td align="left"><input name="messageSubmit" id="messageSubmit" value="提问" onclick="addMessage()" type="button"></td>
      </tr>

    </tbody></table>
     
	</div></div></div>
					</div>
					<!-- }}}end:在线留言 -->
					
					
				
				<div><div class="tab-wrapper">
	<h2><a name="recom"></a><span class="tab-wrapper-title"><span>兔游推荐线路</span></span></h2>
	<span class="backtop"><a href="#">回到页首</a></span>
</div>
<#if conList??>

<!-- {{{start:推荐线路 -->
<div class="tab-wrapper-content final-recommend" id="recommend-zone">
	<div class="tab-wrapper-wrap">
		<ul class="cls">
		<#list contactList as conTeam>
			<li>
				<p style="position: relative;" class="img"><a href="${base}/team/teamContent.shtml?teamId=${conTeam.teamId}" target="_blank">
				<img src="${base}/public/20081205/image/blank.gif"  style="background: transparent url(${base}/${conTeam.orgUrl!""}) no-repeat scroll center center; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;" width="150" height="150">
				</a></p>
				<p class="title"><a href="${base}/team/teamContent.shtml?teamId=${conTeam.teamId}" target="_blank" title="${conTeam.teamName}"><#if conTeam.teamName?length lt 24 >${conTeam.teamName}<#else>${conTeam.teamName[0..22]}...</#if></a></p>
				<p class="price">兔游价：${conTeam.doorDisPrice}.00</p>
			</li>
		</#list>
		</ul>

		


	</div>
</div></div>
<!-- {{{end:推荐线路 -->
</#if>
<!-- {{{start:签约付款 -->
				<div id="howto-zone">

<div class="tab-wrapper">
	<h2><a name="howto"></a><span class="tab-wrapper-title"><span>&nbsp;&nbsp;&nbsp;&nbsp;签约付款&nbsp;&nbsp;&nbsp;&nbsp;</span></span></h2>
	<span class="backtop"><a href="#">回到页首</a></span>
</div>
<div class="tab-wrapper-content final-howto">
	<div class="tab-wrapper-wrap">

		<div class="map">&nbsp;</div>
		
		<div class="txt">
		  <p><a href="http://www.tutu6.com/homePage/helpContent/402881e41fd4eb52011fd59cd1050014.html" target="_blank">如何付款，请查看我们的开户银行，选择您方便的付款方式。</a></p>
		</div>

		<div class="bank">
			<p>您可以通过以下网络银行来给充值或付款：</p>
			<ul class="cls">
				<li class="s1"><a href="http://www.tutu6.com/homePage/helpContent/f2d041b82051836e01205b28504d0530.html#17" target="_blank">如何开通</a></li>
				<li class="s2"><a href="http://www.tutu6.com/homePage/helpContent/f2d041b82051836e01205b28504d0530.html#18" target="_blank">如何开通</a></li>
				<li class="s3"><a href="http://www.tutu6.com/homePage/helpContent/f2d041b82051836e01205b28504d0530.html#19" target="_blank">如何开通</a></li>
				<li class="s5"><a href="http://www.tutu6.com/homePage/helpContent/f2d041b82051836e01205b28504d0530.html#20" target="_blank">如何开通</a></li>
				<li class="s9"><a href="http://www.tutu6.com/homePage/helpContent/f2d041b82051836e01205b28504d0530.html" target="_blank">如何开通</a></li>
			</ul>
		</div>

	</div>

</div>
</div>
<!-- }}}end:签约付款 -->
			</div>
			<!-- //ida-main -->
			<!-- ida-side -->
			<div class="ida-side">
				<div class="inner cls">

					<!-- {start:联系方式 -->
<div class="shop-profile cls">
	<span class="x1"><span class="x1a"></span></span>
	<span class="x2"><span class="x2a"></span></span>

	<div class="mod-content">
	<div class="bd">
		<h4><a href="${base}/help/tel.html" target="_blank">预定电话</a></h4>
		<ul>
		<#switch teamFlag>
			<#case 1>
			<#list webSite.zbList as phone>
   			<li>
				<label class="ka">${phone.phone}：</label>
				<span class="va"><strong>${phone.remark}</strong>&nbsp;&nbsp;
				</span>
			</li>
			</#list>
    		<#break>
    		
    		<#case 2>
			<#list webSite.gnList as phone>
   			<li>
				<label class="ka">${phone.phone}：</label>
				<span class="va"><strong>${phone.remark}</strong>&nbsp;&nbsp;
				</span>
			</li>
			</#list>
    		<#break>
    		
    		<#case 3>
			<#list webSite.cjList as phone>
   			<li>
				<label class="k">${phone.phone}：</label>
				<span class="v"><strong>${phone.remark}</strong>&nbsp;&nbsp;
				</span>
			</li>
			</#list>
    		<#break>
    		
    		<#case 4>
			<#list webSite.cjList as phone>
   			<li>
				<label class="k">${phone.phone}：</label>
				<span class="v"><strong>${phone.remark}</strong>&nbsp;&nbsp;
				</span>
			</li>
			</#list>
    		<#break>
    		
		</#switch>
			<li>
				<label class="k">国内旅游：</label>
				<span class="v"><strong><a href=http://float2006.tq.cn/static.jsp?uin=8629286&ltype=0 target=_blank><img src=http://float2006.tq.cn/staticimg.jsp?uin=8629286&style=1 border='0'></a></strong>
				</span>
			</li>
			<li>
				<label class="k">出境旅游：</label>
				<span class="v"><strong><a href=http://float2006.tq.cn/static.jsp?uin=8629282&ltype=0 target=_blank><img src=http://float2006.tq.cn/staticimg.jsp?uin=8629282&style=1 border='0'></a></strong>
				</span>
			</li>
			<li>
				<label class="k">企业团队：</label>
				<span class="v"><strong><a href=http://float2006.tq.cn/static.jsp?uin=8629288&ltype=0 target=_blank><img src=http://float2006.tq.cn/staticimg.jsp?uin=8629288&style=1 border='0'></a></strong>
				</span>
			</li>
			<li>
				<label class="k">客服qq：</label>
				<span class="v"><strong><A target=blank href="http://wpa.qq.com/msgrd?V=1&Uin=1005038884&Site=客服qq&Menu=yes"><IMG border=0 SRC="http://wpa.qq.com/pa?p=1:1005038884:1" alt=客服qq align=absmiddle></A></strong>
				</span>
			</li>
			<li>
				<label class="k">客服qq：</label>
				<span class="v"><strong><A target=blank href="http://wpa.qq.com/msgrd?V=1&Uin=1143582424&Site=客服qq&Menu=yes"><IMG border=0 SRC="http://wpa.qq.com/pa?p=1:1143582424:1" alt=客服qq align=absmiddle></A></strong>
				</span>
			</li>
			<li>
				<label class="k">客服qq：</label>
				<span class="v"><strong><A target=blank href="http://wpa.qq.com/msgrd?V=1&Uin=1012211763&Site=客服qq&Menu=yes"><IMG border=0 SRC="http://wpa.qq.com/pa?p=1:1012211763:1" alt=客服qq align=absmiddle></A></strong>
				</span>
			</li>
			<li>
				<label class="k">国内Msn：</label>
				<span class="v"><strong><a href="msnim:chat?contact=tutu6_01@live.cn" target=blank><IMG alt=Msn交流 src="${base}/public/20081205/image/msn.gif" border=0></a></strong>
				</span>
			</li>
			<li>
				<label class="k">出境Msn：</label>
				<span class="v"><strong><a href="msnim:chat?contact=tutu6_02@live.cn" target=blank><IMG alt=Msn交流 src="${base}/public/20081205/image/msn.gif" border=0></a></strong>
				</span>
			</li>
			<li>
				<label class="k">团队Msn：</label>
				<span class="v"><strong><a href="msnim:chat?contact=tutu6_03@live.cn" target=blank><IMG alt=Msn交流 src="${base}/public/20081205/image/msn.gif" border=0></a></strong>
				</span>
			</li>
		</ul>
	</div>
	
	
	<div class="bd">
		<h4><a href="${base}/help/tel.html" target="_blank">报名地点</a></h4>
		<ul>
		
			<#list webSite.addList as add>
   			<li>
				
				<span class="v"><font color="blue">${add.remark}</font>&nbsp;&nbsp;
				</span>
			</li>
			</#list>
		</ul>
	</div>
	
	<div class="ft"></div>
</div>
</div>
<!-- }end:联系方式 --><div id="item-side">

<!-- 线路分类 -->
	<div class="ida-category">
		<h2>

		<#if teamFlag==1>
		<a href="http://www.tutu6.com/team/s.shtml?teamFlag=1&showType=1" target="_blank">全部短线旅游</a>
		<#elseif teamFlag==2>
		<a href="http://www.tutu6.com/team/s.shtml?teamFlag=2&showType=1" target="_blank">全部长线旅游</a>
		<#elseif teamFlag==3>
		<a href="http://www.tutu6.com/team/s.shtml?teamFlag=3&showType=1" target="_blank">全部出境旅游</a>
		<#elseif teamFlag==4>
		<a href="http://www.tutu6.com/team/s.shtml?teamFlag=4&showType=1" target="_blank">全部自由行</a>
		</#if>
		</h2>
		<#list bannerRegions as region>
		<div class="item">
			<h3>
				
				<a target="_blank" href="${base}/team/s.shtml?regionId=${region.regionId}" titile="${region.name}">${region.name}</a>
				<ul>
				<#list region.childrenList as childRegion>
				<li><a target="_blank" href="${base}/team/s.shtml?regionId=${childRegion.regionId}">${childRegion.name}</a></li>
				</#list>
				</ul>
			</h3>
		</div>
		</#list>
	</div>
<!-- //线路分类 --></div>

				</div>
			</div>
			<!-- //ida-side -->
		</div>
		<!-- //ida-module -->

	<div id="searchbar-zone">
<!-- {{start:header search -->
<div class="pheader pheader-bot">
	<span class="xl"></span><span class="xr"></span>

		<!-- search -->
		<@search />
		<!-- //search -->
		
</div>
<!-- }}end:header search --></div>
</div>

<!-- footer -->
	<@footer />
	<!-- //footer -->


<div id="CalendarLeft" style="display: none; position: absolute; left: 671px; top: 4442px; width:300px">
<table class="tCal" border="1" cellpadding="2" cellspacing="0">
	<thead>
	<tr><td colspan="7"><div style="position: relative;"><span class="tCalMonth">${currDate!""}<img src="${base}/public/20081205/image/cross.gif" alt="关闭" title="关闭" style="cursor: pointer;" onclick="hidden_plan();"></span> <span class="tCalFlipFw" onclick="next_plan_cal();">${nextDate!""}►</span></div>
	</td>
	</tr>
	<tr><th><font color=red>日</font></th><th>一</th><th>二</th><th>三</th><th>四</th><th>五</th><th>六</th></tr>
	</thead>
	<tbody>
	<#list weekList as week>
	<tr>
	<#list week.days as days>
	<#if days??>
		<#if (days.team??)&&(team.correct!=0)>
			<#if (days.day<today)&&(calMonth1==currMonth)>
			<td class="tCalPassed tCalDay"><span class="date_span">${days.day}</span><br/><span class="price_span">${days.team.doorDisPrice}<span class="tCalYuan">元</span></span></td>
			<#elseif (days.day=today)&&(calMonth1==currMonth)>
			<td class="tCalToday tCalDay"><span class="date_span">${days.day}</span><br>今天</td>
			<#else>
			<td class="tCalDay tCalPrice">
			<a href="javascript:void(0)" onclick="close_plan_calendar(${days.calYear},${days.calMonth},${days.calDay},'${days.team.teamId}');" title="报名" class="calendar_bg">
			<span class="date_span">${days.day}</span>
			<span class="site_span cyellow"> </span><br>
			<span class="price_span">${days.team.doorDisPrice}<span class="tCalYuan">元</span></span></a></td>
			</#if>
		<#else>
			<#if (days.day=today)&&(calMonth1==currMonth)>
			<td class="tCalToday tCalDay"><span class="date_span">${days.day}</span><br>今天</td>
			<#else>
			<td class="tCalPassed tCalDay"><span class="date_span">${days.day}</span><br>&nbsp;</td>
			</#if>
		</#if>
	<#else>
		<td>&nbsp;</td>
	</#if>
	
	</#list>
	</tr>
	</#list>
	
	</tbody></table>
</div>
<div id="CalendarRight" style="display: none; position: absolute; left: 671px; top: 4442px;  width:300px">
<table class="tCal" border="1" cellpadding="2" cellspacing="0">
	<thead><tr><td colspan="7">
	<div style="position: relative; width: 100%;"><span class="tCalFlipBw" onclick="past_plan_cal();">◄ ${nextDate!""}</span> <span class="tCalMonth">${nextDate!""} <img src="${base}/public/20081205/image/cross.gif" alt="关闭" title="关闭" style="cursor: pointer;" onclick="hidden_plan();"></span></div></td></tr>
	<tr><th><font color=red>日</font></th><th>一</th><th>二</th><th>三</th><th>四</th><th>五</th><th>六</th></tr></thead>
	<tbody>
	
   <#list nextWeekList as week>
	<tr>
	<#list week.days as days>
	<#if days??>
		<#if (days.team??)&&(team.correct!=0)>
			<#if (days.day<today)&&(calMonth2==currMonth)>
			<td class="tCalPassed tCalDay"><span class="date_span">${days.day}</span><br/><span class="price_span">${days.team.doorDisPrice}<span class="tCalYuan">元</span></span></td>
			<#elseif (days.day=today)&&(calMonth2==currMonth)>
			<td class="tCalToday tCalDay"><span class="date_span">${days.day}</span><br>今天</td>
			<#else>
			<td class="tCalDay tCalPrice">
			<a href="javascript:void(0)" onclick="close_plan_calendar(${days.calYear},${days.calMonth},${days.calDay},'${days.team.teamId}');" title="报名" class="calendar_bg">
			<span class="date_span">${days.day}</span>
			<span class="site_span cyellow"> </span><br>
			<span class="price_span">${days.team.doorDisPrice}<span class="tCalYuan">元</span></span></a></td>
			</#if>
		<#else>
			<#if (days.day=today)&&(calMonth2==currMonth)>
			<td class="tCalToday tCalDay"><span class="date_span">${days.day}</span><br>今天</td>
			<#else>
			<td class="tCalPassed tCalDay"><span class="date_span">${days.day}</span><br>&nbsp;</td>
			</#if>
		</#if>
	<#else>
		<td>&nbsp;</td>
	</#if>
	
	</#list>
	</tr>
	</#list>
	
	
	
	
	</tbody></table>
</div>



<!-- }end:get user data ft --></div>

<script type="text/javascript" src="${base}/public/20081205/script/core.js"></script>
<script type="text/javascript">
var site={
	img_upload_url:'http://imgup.mall.baidu.com/_u/uppic?k=1',
	img_url:'http://img.mall.baidu.com/cccccc',
	error_url:"/static/error/",
	webim_url:"http://web.im.baidu.com"
	 
}
</script>
<script type="text/javascript" src="${base}/public/20081205/script/panel.js"></script>

<script type="text/javascript" src="${base}/public/20081205/script/BBEffect.js"></script>
<script type="text/javascript" src="${base}/public/20081205/script/picslide.js"></script>
<script type="text/javascript">
function BBAjax(A){this.options={url:"",method:"post",async:true,user:"",pwd:"",contentType:"application/x-www-form-urlencoded",charset:"UTF-8",requestHeaders:{"x-baidu-ie":"utf-8","x-baidu-rf":"json"},data:"",useLock:0,sendImdtly:0,timeout:30000};Object.extendJson(this.options,A);this._initialize();}BBAjax.Versions=["MSXML2.XMLHttp.6.0","MSXML2.XMLHttp.3.0","MSXML2.XMLHttp.5.0","MSXML2.XMLHttp.4.0","Msxml2.XMLHTTP","MSXML.XMLHttp","Microsoft.XMLHTTP"];BBAjax.Delay=950;BBAjax.prototype._initialize=function(){this._locked=0;this._completed=0;this.isTimeout=0;this.aborted=0;this.requester=BBAjax.getRequestObj(this.options.useRequestPool);if(!this.requester){alert("�ǳ���Ǹ����ʼ������ʧ�ܣ�#"+BBAjax.Versions.length);return ;}var B=this.options;var A=this;if(B.async){this.requester.onreadystatechange=function(){var C=A.requester.readyState;if(C==4){A._onComplete();}};}this.dispose=function(){if(A){A.requester=null;A=null;}};if(B.sendImdtly){this.send();}BBEvent.observe(window,"unload",this.dispose);};BBAjax.getRequestObj=function(){if(window.ActiveXObject){while(BBAjax.Versions.length>0){try{return new ActiveXObject(BBAjax.Versions[0]);}catch(A){BBAjax.Versions.shift();continue;}}}else{if(window.XMLHttpRequest){return new XMLHttpRequest();}}return null;};BBAjax.prototype._setRequestHeader=function(){var D={};D["Content-type"]=this.options.contentType+(this.options.charset?"; charset="+this.options.charset:"");var C=this.options.requestHeaders;if(typeof (C)=="object"){Object.extendJson(D,C);}else{if(C&&typeof (C.push)=="function"){for(var B=0,A=C.length;B<A;B+=2){D[C[B]]=C[B+1];}}}for(var B in D){this.requester.setRequestHeader(B,D[B]);}};BBAjax.prototype._onComplete=function(){var A=this.requester;var B=this.options;this._completed=1;if(B.useLock){this._locked=0;}if(this._timer){clearTimeout(this._timer);}if(B.oncomplete){B.oncomplete.call(this,A,"Complete");}if(this.aborted){return ;}if(B.onsuccess&&this.isSuccess()){B.onsuccess.call(this,A,"Success");}else{if(B.onerror&&!this.isSuccess()){B.onerror.call(this,A,"Error");}}};BBAjax.prototype.send=function(A,D,B){try{var E=this.options;if(E.useLock&&this._locked){return ;}else{if(!E.useLock&&this.inProcess()){this.cancel();setTimeout(function(){_self.send(A,D,B);},0);return ;}}A=A||E.url;D=D||E.method;B=B||E.data;if(typeof (B)=="object"){B=this._getURLString(B);}if(B&&D.toLowerCase()=="get"){A=A.indexOf("?")!=-1?A+"&"+B:A+"?"+B;}if(E.user==""&&E.pwd==""){this.requester.open(D.toUpperCase(),A,E.async);}else{this.requester.open(D.toUpperCase(),A,E.async,E.user,E.pwd);}this._setRequestHeader();this.isTimeout=0;if(E.useLock){this._locked=1;}this._completed=0;this.aborted=0;if(E.timeout>0){this._checkTimeout(E.timeout);}if(E.onsend){E.onsend.call(this);}BBAjax.lastPost={data:B,url:A,method:D};if(typeof (B)!="undefined"&&D.toLowerCase()=="post"){if(B==""){this.options.requestHeaders["Content-Length"]=0;B=" ";}this.requester.send(B);}else{this.requester.send(null);}}catch(C){alert("�ǳ���Ǹ������ʧ��:"+C);}};BBAjax.prototype.get=function(A,B){this.send(A,"get",B);};BBAjax.prototype.post=function(A,B){this.send(A,"post",B);};BBAjax.prototype.sendForm=function(C,B,E,A){var F=Dom.formURIEncode(C,A);var D=this.options.data;if(typeof (D)=="object"){D=this._getURLString(D);}if(D!=""){D+="&";}this.send(B||C.action,E||C.method,D+F);};BBAjax.getResponseText=function(A,D,E,C){var B=new BBAjax({url:A,method:D,data:E,sendImdtly:1,async:(typeof C!="undefined")});return B.requester.responseText;};BBAjax.prototype.cancel=function(){if(!this.requester){return false;}if(this.inProcess()){this.aborted=1;this.requester.abort();if(!this._completed){this._onComplete.call(this,this.requester,"aborted");}if(this.options.onabort){this.options.onabort(this.requester);}return true;}return false;};BBAjax.prototype._checkTimeout=function(B){var A=this;this._timer=setTimeout(function(){if(A.requester&&!A._completed){A.isTimeout=1;A.requester.abort();if(!A._completed){A._onComplete.call(A,A.requester,"timeout");}A._completed=1;}},B);};BBAjax.prototype.isSuccess=function(){if(this.isTimeout){return false;}var A=this.requester.status;return !A||(A>=200&&A<300);};BBAjax.prototype.inProcess=function(){var A=this.requester.readyState;return A>0&&A<4;};BBAjax.prototype._getURLString=function(B){var D=[];for(var C in B){if(B[C]==null){continue;}if(B[C].constructor==Array){for(var A=0;A<B[C].length;A++){D.push(C+"="+encodeURIComponent(B[C][A]));}}else{D.push(C+"="+encodeURIComponent(B[C]));}}return D.join("&");};BBAjax.opResults=function(Results,url){if(!Results){return false;}if(Results.replace(/(\n|\r)+/g,"").trim().substr(0,1)!="{"){return Results;}else{try{eval("var status="+Results);}catch(e){alert("���س��?���ص�����Ϊ��\n"+Results);BB.Console.log("json���س��?");return{err:"inter"};}}status.isop=true;switch(status.err){case"ok":if(url!=false){if(url){setTimeout("window.location='"+url+"'",BBAjax.Delay);}else{if(status.url==null){setTimeout("window.location = window.location.href;window.location.reload(true);",BBAjax.Delay);}else{setTimeout("window.location='"+status.url+"'",BBAjax.Delay);}}}break;case"input":if(status.err_desc=="format"){for(var i in status.fields){var tempEl=document.getElementsByName(i);try{if(tempEl.length>0){var unfound=true;if(tempEl.length==1){BBValid.vldErrorFun(tempEl[0],"����������ݸ�ʽ���Ի򳬳��������Ƿ�������ַ�",true);unfound=false;}else{for(var x=0;x<tempEl.length;x++){if(status.fields[i]==tempEl[x].value.encode4Html()){BBValid.vldErrorFun(tempEl[x],"����������ݸ�ʽ���Ի򳬳��������Ƿ�������ַ�",true);unfound=false;break;}}}if(unfound){alert("����������ݸ�ʽ���Ի򳬳��������Ƿ�������ַ�");}}else{alert("����������ݸ�ʽ���Ի򳬳��������Ƿ�������ַ�");}}catch(e){alert("����������ݸ�ʽ���Ի򳬳��������Ƿ�������ַ�");}BB.Console.log("post input format",status.di);}}else{if(status.err_desc=="antispam"){alert("���ύ�����ݰ����дʻ㣬����������ύ��");BB.Console.log("post antispam",status.di);}else{status.isop=false;}}break;case"fake":window.location="/static/error/?di="+status.di;break;case"antispam":alert("���ύ�����ݰ����дʻ㣬����������ύ��");BB.Console.log("post antispam",status.di);break;case"maintain":alert("ϵͳ��æ�������Ժ����ԣ�");BB.Console.log("post maintain",status.di);break;case"inter":alert("ϵͳ��æ�������Ժ����ԣ�");BB.Console.log("post inter",status.di);break;case"unknow":alert("ϵͳ��æ�������Ժ����ԣ�");BB.Console.log("post unknow",status.di);break;case"expires":alert("����Ѿ����ڣ�");BB.Console.log("post expires",status.di);setTimeout("window.location = window.location.href;window.location.reload(true);",1);break;case"forbid":alert("�ܱ�Ǹ����û��Ȩ�޽��д˲�����");BB.Console.log("post forbid",status.di);break;case"nopower":window.location="/static/error/nopower.html";break;case"login":User.Login.show();User.Login.hint("����Ҫ��¼����ܼ���ղŵĲ���");BB.Console.log("post login",status.di);break;default:status.isop=false;}return status;};
</script>
<script type="text/javascript" src="${base}/public/20081205/script/cache.2"></script>

<script type="text/javascript" language="javascript">

	
function load(u, fn) {
	var hasQ = u.indexOf('?') >= 0;
	new BBAjax({
		url: u + (hasQ ? '&' : '?') + 'randon=' + (new Date() * 1),
		method: "get",
		charset:"UTF-8",
		onsuccess: function(o) {
			fn(o);
		
		}
	}).send();
}

function trim(mystr){
while ((mystr.indexOf(" ")==0) && (mystr.length>1)){
mystr=mystr.substring(1,mystr.length);
}//去除前面空格
while ((mystr.lastIndexOf(" ")==mystr.length-1)&&(mystr.length>1)){
mystr=mystr.substring(0,mystr.length-1);
}//去除后面空格
if (mystr==" "){
mystr="";
}
return mystr;
}


function addMessage(){
	var sendContain = trim(document.getElementById("sendContain").value);
	var senders = trim(document.getElementById("senders").value);
	var email= trim(document.getElementById("email").value);
	
	if (senders=="")
	{
		alert("请填写您的称呼。");
		return false;
	}
	if (sendContain=="")
	{
		alert("请填写您的提问内容。");
		return false;
	}
	if(email == ""){
		
		
	}else{
		if(document.getElementById("email").value.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/ ) == -1) {
			alert("邮箱格式有误，请检查。");
			document.getElementById("email").focus();
			return false;
		}
	} 
	
	$('messageSubmit').style.display='none';
	load(encodeURI('${base}/team/addMessage.shtml?teamName=${team.teamName}&groupId=${team.groupId}&sendContain=' + sendContain + '&sender=' + senders  + '&email=' + email), function(o) {
		
		var retrunDateStr = BBAjax.opResults(o.responseText, false);
		
		$('small_class_list').innerHTML = retrunDateStr;
		alert("提交内容成功，请等待兔游工作人员答复，谢谢。");
		$('senders').value="";
		$('sendContain').value="";
		$('messageSubmit').style.display='block';
	});
}	
       
        
      
        
       </script>

<script type="text/javascript">

function next_plan_cal(){
	var o=document.getElementById("begindate_page");
	x=o.offsetLeft;
	y=o.offsetTop;
	while(o=o.offsetParent)
	{
		x+=o.offsetLeft;
		y+=o.offsetTop;
	}
	document.getElementById("CalendarRight").style.left = x+0+"px";
	document.getElementById("CalendarRight").style.top = y+20+"px";
	document.getElementById("CalendarLeft").style.display='none';
	document.getElementById("CalendarRight").style.display='block';
}
function past_plan_cal(){
	var o=document.getElementById("begindate_page");
	x=o.offsetLeft;
	y=o.offsetTop;
	while(o=o.offsetParent)
	{
		x+=o.offsetLeft;
		y+=o.offsetTop;
	}
	document.getElementById("CalendarLeft").style.left = x+0+"px";
	document.getElementById("CalendarLeft").style.top = y+20+"px";
	document.getElementById("CalendarRight").style.display='none';
	document.getElementById("CalendarLeft").style.display='block';
}

function close_plan_calendar(year,month,day,thisteamId){
	if((month + "").length == 1){
		month = "0"+month;
	}
	if((day + "").length == 1){
		day = "0"+day;
	}
	var date = year+'-'+month+'-'+day;
	document.getElementById("CalendarRight").style.display='none';
	document.getElementById("CalendarLeft").style.display='none';
	document.getElementById("begindate_page").value = date;
	document.getElementById("thisTeamId").value = thisteamId;
}
function show_plan(){
	var o=document.getElementById("begindate_page");
	x=o.offsetLeft;
	y=o.offsetTop;
	while(o=o.offsetParent)
	{
		x+=o.offsetLeft;
		y+=o.offsetTop;
	}
	document.getElementById("CalendarLeft").style.left = x+0+"px";
	document.getElementById("CalendarLeft").style.top = y+20+"px";
	document.getElementById("CalendarLeft").style.display='block';
}
function hidden_plan(){
	document.getElementById("CalendarRight").style.display='none';
	document.getElementById("CalendarLeft").style.display='none';
}
function view_order(){
	if(check1()){
		document.getElementById('form_order').submit();
	}
}
function check1(){
	var user_num = document.getElementById("manNum").value;
	var children_num = document.getElementById("childNum").value;
	if(children_num == ''){
		children_num = 0;
	}
	var re1 = /^[0-9]{1,2}/;
	if(user_num =='') {
		alert("请填写出行人数。");
		return false;
	}
	if(!re1.test(user_num)){
		alert("请输入正确的出行人数，请填写数字。");
		return false;
	}
	if(!re1.test(children_num)){
		alert("请输入正确的出行人数，请填写数字。");
		return false;
	}
	if(Number(user_num) + Number(children_num) >= 100) {
		alert("人数超出范围。");
		return false;
	}
	<#if team.correct==1>
	var begindate = document.getElementById('begindate_page').value;
	var re2 = /^[0-9]{4,4}-[0-9]{2,2}-[0-9]{2,2}/;
	if((begindate == '点击选择') || (begindate == '')){
		alert('请选择出发日期');
		return false;
	}
	if(!re2.test(begindate)){
		alert("出发日期不正确，\r\n格式：2007-10-01");
		return false;
	}
	</#if>
	else {
		return true;
	}
}


//<![CDATA[

function myObserve(el, eName, fun){
	el = $(el);
	el && BBEvent.observe(el, eName, fun);
}

	var range = 6;
	var elements = Dom.getElementsByClassName('img', "recommend-zone");
	for (var i = 0; i < elements.length; i++) {
		new JumpObj(elements[i], range);
	}


// Starting the picture slider.
var op = {
	container: $("PicContainer"),
	pics:Dom.getElementsByClassName("bigimg",$("PicContainer")),
	autoPlay: false,
	eventType: "mouseover",
	effect: "fade"
};
var picSlide = new PicSlide(op);
if(picSlide.pics.length>0){
	var showBigpicDiv = Dom.getElementsByClassName("cls",$("PicContainer"))[0];
	var showBigpic = Dom.getElementsByClassName("show_bigpic_div",$("PicContainer"))[0];
	var showBigpicLink = Dom.getElementsByClassName("show_bigpiclink",showBigpic)[0];
	showBigpicLink.href=picSlide.pics[0].parentNode.href;
	CustEvent.observe(picSlide,"picChange",function(i){
		showBigpicLink.href=picSlide.pics[i].parentNode.href;
	});
	BBEvent.observe(showBigpicDiv,"mouseover",function(e){
		Dom.show(showBigpic);
	});
	BBEvent.observe(showBigpicDiv,"mouseout",function(){
		Dom.hide(showBigpic);
	});
}
picSlide.run();






function JumpObj(elem, range, startFunc, endFunc) {

	var curMax = range = range || 6;
    startFunc = startFunc || function(){};
    endFunc = endFunc || function(){};
    var drct = 0;
    var step = 1;

    init();

    function init() { elem.style.position = 'relative';active() }
    function active() { elem.onmouseover = function(e) {if(!drct)jump()} }
    function deactive() { elem.onmouseover = null }

	function jump() {
		var t = parseInt(elem.style.top);
        if (!drct) motionStart();
        else {
            var nextTop = t - step * drct;
            if (nextTop >= -curMax && nextTop <= 0) elem.style.top = nextTop + 'px';
            else if(nextTop < -curMax) drct = -1;
            else {
                var nextMax = curMax / 2;
                if (nextMax < 1) {motionOver();return;}
                curMax = nextMax;
                drct = 1;
            }
        }
        setTimeout(function(){jump()}, 200 / (curMax+3) + drct * 3);
	}

    function motionStart() {
        startFunc.apply(this);
        elem.style.top='0';
        drct = 1;
    }

    function motionOver() {
        endFunc.apply(this);
        curMax = range;
        drct = 0;
        elem.style.top = '0';
    }

	this.jump = jump;
    this.active = active;
    this.deactive = deactive;
}


//]]>
</script>

	


</body></html>