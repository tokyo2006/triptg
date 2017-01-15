<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<!--STATUS OK-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${team.teamName}(${team.area.name}出发)在线预定——要旅游 先兔游</title>
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/module-front4.css" rel="stylesheet" type="text/css" media="screen">

</head><div FirebugVersion="1.3.3" style="display: none;" id="_firebugConsole"></div>
<body>
<#include "*/simpleHead.ftl">
<#include "*/footer.ftl">
<div id="doc3" class="bb-sc">
	<div id="hd">
		<!-- {start:global master header -->
		<noscript><p class="nojs">您的浏览器已经禁用了脚本，这会影响您正常使用本站的功能。</p></noscript>
		<@simpleHead />
		<!-- }end:global master header -->	</div>
	<div id="bd">
		<div id="bb-main">
			<div class="bb-c">
				<form action="confirm.shtml" method="post" id="frmMain">
					<input id="teamId" name="teamId" value="${teamId}" type="hidden">
					<input id="manNum" name="manNum" value="#{manNum}" type="hidden">
					<input id="childNum" name="childNum" value="#{childNum}" type="hidden">
					<input id="total" name="total" value="#{total}" type="hidden">
					<input id="manPrice" name="manPrice" value="#{manPrice}" type="hidden">
					<input id="childPrice" name="childPrice" value="#{childPrice}" type="hidden">
					<input id="startDateStr" name="startDateStr" value="${startDateStr}" type="hidden">
					<input id="backDateStr" name="backDateStr" value="${backDateStr}" type="hidden">
					<input id="customName" name="customName" value="${customName}" type="hidden">
					<input id="customMobile" name="customMobile" value="${customMobile}" type="hidden">
					<input id="customFax" name="customFax" value="${customFax}" type="hidden">
					<input id="customAddress" name="customAddress" value="${customAddress}" type="hidden">
					<input id="customEmail" name="customEmail" value="${customEmail}" type="hidden">
					<input id="customRemark" name="customRemark" value="${customRemark}" type="hidden">
					<input id="doorManPrice" name="doorManPrice" value="${team.doorManPrice}" type="hidden">
					<input id="assemble" name="assemble" value="${assemble}" type="hidden">
					<input id="doorChildPrice" name="doorChildPrice" value="${team.doorChildPrice}" type="hidden">
					<@s.token/>
					<div class="tab-wrapper">
						<h2><span class="tab-wrapper-title"><span>&nbsp;&nbsp;&nbsp;&nbsp;购买商品&nbsp;&nbsp;&nbsp;&nbsp;</span></span></h2>
						<div class="my-info-step">
							<ul>
								<li ><span>1</span>填写订单信息</li>
								<li class="selected"><span>2</span>确认订单信息</li>
								<li><span>3</span>预定成功</li>
								
							</ul>
						</div>
					</div>
					<div class="tab-wrapper-content final-buy">
						<div class="tab-wrapper-wrap">
							<div class="my-publish-group">
								<h3>确认线路信息</h3>
								<div class="info">
									<div class="intro">
										<div class="inner">
											<ul>
											<li><label>线路名称：</label><a href="${base}/team/teamContent/${team.teamId}" target="_blank">${team.teamName}</a></li>
											<li><label>总&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</label><span class="price">${total}</span> 元&nbsp;&nbsp;(成人：${manNum}×${manPrice}元/人，儿童：${childNum}×${childPrice}元/人，标准不同价格会有所变化)</li>
<li><label>价格说明：</label>以上价格为电脑自动计算，可能出现一些误差，如买一送一，接送价格未计算，标准不同导致实际价格有所出入，请察看<a href="${base}/team/teamContent/${team.teamId}#price" target="_blank">线路详细价格标准</a>核对，兔游网操作人员也会为您核对最终价格。</li>
											<#if startDateStr!="">
											<li><label>出发日期：</label>${startDateStr}&nbsp;&nbsp;&nbsp;&nbsp;<label>返回日期：</label>${backDateStr}</li>
                                            <#else>
                                            <li>出发日期在您下单后我们会联系您为您尽快确定下来。</li>
                                            </#if>
                                           
											<li><label>游客人数：</label>成人：${manNum}人&nbsp;&nbsp;&nbsp;&nbsp;儿童：${childNum}人&nbsp;&nbsp;&nbsp;&nbsp;出&nbsp;&nbsp;发&nbsp;&nbsp;地：${team.area.name}</li>
											
                                           
											</ul>
										</div>
									</div>
									<div class="img">
										<a href="${base}/team/teamContent/${team.teamId}.html" target="_blank"><img src="${base}/${team.breUrl!""}">
										</a>
									</div>							
								</div>
							</div>
							<div class="my-publish-group">
																																<!--普通-->
								<h3>确认您的联系方式</h3>
								<div class="address">
																		<ul>
										<li id="addr" class="selected selected">
											
											
											<div class="form">
												<ul>
													
													
													<li>
														<label class="k"><span class="nstar"></span>游客姓名：</label>
														<span class="v">
															${customName}
														</span>
													</li>
													<li>为了提供更好的服务和售后回访，请最好填写您的移动号码。</li>
													
													<li>
														<label class="k"><span class="nstar"></span>联系方式：</label>
														<span class="v">
															<span>${customMobile}</span><em></em>
														</span>
													</li>
													 <li>
                                            			<label class="k"><span class="nstar"></span>上车地点：</label>
														<span class="v">
															<span>${address}&nbsp;&nbsp;上车时间：${shijian}&nbsp;&nbsp;接送加价：${jiajia}元/人（${jiesong!""}）<#if jiajia!="0">&nbsp;&nbsp;<font color="green">(接送费用另算,不包含在总价中。)</font></#if></span><em></em>
														</span>
													</li>
													<li>
														<label class="k">电话传真：</label>
														<span class="v">
															<span>${customFax}</span><em></em>
														</span>
													</li>
                                                    <li>
														<label class="k">街道地址：</label>
														<span class="v">
															<span>${htmlCustomAddress}</textarea></span>
														</span>
													
														
													</li>
                                                    <li>
														<label class="k">电子邮件：</label>
														<span class="v">${customEmail}</span><em></em>
													</li>
													
												</ul>
											</div>
										</li>
									</ul>
								</div>
															</div>
							<!-- //地址 -->
							<div class="my-publish-group">
								<h3>预定附加信息</h3>
								<div class="order" id="divOrder">
									<ul>
										
																		
										<li class="s1">
											<label class="k">给兔游留言：</label>
											<span class="v"><span><#if customRemark!="">${htmlCustomRemark}<#else>您没有填写特殊要求。</#if></span><em></em></span>
										</li>
									</ul>
								</div>
							</div>	
							
							

						</div>
					</div>
					<!-- }}}end:购买商品 -->

					<div class="final-buy-btn">
						<button type="submit" id="btnSubmit" onclick="javascript:return submitOrder();">确认购买，下订单</button>
					</div>

					<div style="text-align: center; color: rgb(153, 153, 153); margin-bottom: 50px;">
						点击“确认购买，下订单”按钮表示您将确认购买并同意兔游网操作人员与您联系，确认后兔游网工作人员会尽快与您核实相应线路行程与相关事项。
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- footer -->
	<@footer />
	<!-- //footer -->


	
</div></body></html>