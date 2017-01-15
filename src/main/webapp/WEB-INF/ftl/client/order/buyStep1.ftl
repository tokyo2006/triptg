<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<!--STATUS OK-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${team.teamName}(${team.area.name}出发)在线预定——要旅游 先兔游</title>
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/module-front4.css" rel="stylesheet" type="text/css" media="screen">

</head>
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
				<form action="step2.shtml" method="post" id="frmMain">
					<input id="teamId" name="teamId" value="${teamId}" type="hidden">
					<input id="manNum" name="manNum" value="#{manNum}" type="hidden">
					<input id="childNum" name="childNum" value="#{childNum}" type="hidden">
					<input id="total" name="total" value="#{total}" type="hidden">
					<input id="manPrice" name="manPrice" value="#{manPrice}" type="hidden">
					<input id="childPrice" name="childPrice" value="#{childPrice}" type="hidden">
					<input id="startDateStr" name="startDateStr" value="${startDateStr}" type="hidden">
					<input id="backDateStr" name="backDateStr" value="${backDateStr}" type="hidden">
					<div class="tab-wrapper">
						<h2><a name="howto"></a><span class="tab-wrapper-title"><span>&nbsp;&nbsp;&nbsp;&nbsp;线路预定&nbsp;&nbsp;&nbsp;&nbsp;</span></span></h2>
						<div class="my-info-step">
							<ul>
								<li class="selected"><span>1</span>填写订单信息</li>
								<li ><span>2</span>确认订单信息</li>
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
											<li><label>总&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</label><span class="price">${total}</span> 元&nbsp;&nbsp;(成人：${manNum}×${manPrice}元/人&nbsp;,&nbsp;儿童：${childNum}×${childPrice}元/人，标准不同价格会有所变化)</li>
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
										<a href="${base}/team/teamContent/${team.teamId}" target="_blank"><img src="${base}/${team.breUrl!""}">
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
															<input maxlength="40" name="customName" id="customName" size="13" altstr="游客姓名" type="text">
														</span>
													</li>
													<li>为了提供更好的服务和售后回访，请最好填写您的移动号码。</li>
													
													<li>
														<label class="k"><span class="nstar"></span>联系方式：</label>
														<span class="v">
															<span><input maxlength="20" name="customMobile" id="customMobile" size="13" type="text"></span><em></em>
														</span>
													</li>
													
													<li>
														<label class="k"><span class="nstar"></span>上车地点：</label>
														<span class="v">
															<span><@s.select name="assemble" headerKey="" headerValue="出发时间---接送地点---接送加价---接送方式" id="assemble" list="reAssList" listKey="compact" listValue ="compact" theme="simple"/></span><em></em>
														</span>
													</li>
													<li>
														<label class="k">电话传真：</label>
														<span class="v">
															<span><input maxlength="20" name="customFax" id="customFax" size="13" type="text"></span><em>如您填写传真号码，兔游网工作人员可以与您传真签订旅游合同</em>
														</span>
													</li>
                                                    <li>
														<label class="k">街道地址：</label>
														<span class="v">
															<span><textarea maxlength="400" datatype="text" altstr="街道地址" name="customAddress" id="customAddress" style="width:300px; height:50px"></textarea></span><em>苏州市内提供上门签单服务，如有需要请填写准确，谢谢</em>
														</span>
													
														
													</li>
                                                    <li>
														<label class="k">电子邮件：</label>
														<span class="v"><input maxlength="50" id="customEmail" name="customEmail" size="13" altstr="电子邮件"></span><em></em>
													</li>
													
												</ul>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<!-- //收货地址 -->
							<div class="my-publish-group">
								<h3>预定附加信息</h3>
								<div class="order" id="divOrder">
									<ul>
										
																		
										<li class="s1">
											<label class="k">给兔游留言：<br>(可不填)</label>
											<span class="v"><span><textarea errorstr="您输入的留言太多，请重新输入。" datatype="text" maxlength="200" name="customRemark"></textarea></span><em>选填。可以写下您想选择的线路标准，酒店，餐饮，接送等要求。</em></span>
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
<!-- {start:get user data ft -->
<script type="text/javascript">

//电话-手机验证
String.prototype.Trim = function() {
var m = this.match(/^\s*(\S+(\s+\S+)*)\s*$/);
return (m == null) ? "" : m[1];
}

String.prototype.isMobile = function() {
return (/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/.test(this.Trim()));
}

String.prototype.isTel = function()
{
    return (/^(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
}

function submitOrder(){
	if(document.getElementById("customName").value == ""){
		window.alert("请填入联系人姓名");
		document.getElementById("customName").focus();
		return false;
	}
	if(document.getElementById("assemble").value == ""){
		window.alert("请填入上车地点");
		document.getElementById("assemble").focus();
		return false;
	}
	if(document.getElementById("customMobile").value == ""){
		window.alert("请填入联系人手机号码");
		document.getElementById("customMobile").focus();
		return false;
	}
	var strTel = document.getElementById("customMobile").value;
	if (strTel.isMobile()||strTel.isTel()) { }
     else
            {
                alert("请输入正确的手机号码或电话号码\n\n例如:13972120243");
                document.getElementById("customMobile").focus();
                return false;
            }
    var strFax = document.getElementById("customFax").value;
    if(strFax!=""){
	if (strFax.isTel()) { }
     else
            {
                alert("请输入正确的电话传真号码");
                document.getElementById("customFax").focus();
                return false;
            }
    }
    var email = document.getElementById("customEmail").value;
	if(email == ""){
		
	}else{
		if(document.getElementById("customEmail").value.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/ ) == -1) {
			alert("邮箱格式有误，请检查。");
			document.getElementById("customMobile").focus();
			return false;
		}
	}        
    
	return true;
}


</script>
	
</div></body></html>