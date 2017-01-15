
	function observe(C, B, A){
		C = $(C);
		C && BBEvent.observe(C, B, A);
	}
function myObserve(el, eName, fun){
	el = $(el);
	el && BBEvent.observe(el, eName, fun);
}	
	
function load(u, fn) {
	var hasQ = u.indexOf('?') >= 0;
	new BBAjax({
		url: u + (hasQ ? '&' : '?') + 'randon=' + (new Date() * 1),
		method: "get",
		onsuccess: function(o) {
			fn(o);
		}
	}).send();
}
	
observe("sort", "change", function(C){
		var B = BBEvent.target(C);
		if (B.value != "") {
			var A = B.getAttribute("url_pre");
			if (A != "") {
				A += "&sort=" + B.value;
				window.location = A;
			}
			else {
				alert("url_pre not definded");
			}
		}
	});

	var popPic = null;
	var popPicTimeout = null;
	var POP_PIC_DELAY = 200;
	observe("data-table", "mouseover", function(B){
		var A = BBEvent.target(B);
		if (Dom.hasClassName(A.parentNode.parentNode.parentNode, "c2")) {
			
			if (!popPic) {
				var C = '<div class="temp200808012845" id="thumnail"><img id="pop-img"></div>';
				popPic = new LayerPopup({
					shadow: true,
					center: false,
					header: false,
					className: "panel-t5 panel-t5-lt",
					footer: false,
					cue: true,
					autoPosition: true,
					content: C
				});
				BBEvent.observe($("thumnail"), "mouseover", function(D){
					clearTimeout(popPicTimeout);
				});
				BBEvent.observe($("thumnail"), "mouseout", function(D){
					popPicTimeout = setTimeout(function(){
						popPic.hide();
					}, POP_PIC_DELAY);
				});
				BBEvent.observe(popPic._cue, "mouseover", function(D){
					clearTimeout(popPicTimeout);
				});
				BBEvent.observe(popPic._cue, "mouseout", function(D){
					popPicTimeout = setTimeout(function(){
						popPic.hide();
					}, POP_PIC_DELAY);
				});
			}
			$("pop-img").src = "http://www.tutu6.com/public/20081205/image/ind_loading1.gif";
			popPicTimeout = setTimeout(function(){
				$("pop-img").src = A.src.replace("/sabpic/", "/abpic/");
				popPic.show(83, 0, null, null, A.parentNode.parentNode);
				var F = Dom.getAbsolutePosition(popPic.getPopup());
				var G = Dom.getAbsolutePosition(A);
				var D = Dom.getAbsolutePosition(popPic._cue);
				var E = G.top - F.top + 10;
				if (E < 0) {
					E = 0;
				}
				if (E > F.height - D.height) {
					E = F.height - D.height;
				}
				popPic._cue.style.top = E + "px";
			}, POP_PIC_DELAY);
		}
	});
	observe("data-table", "mouseout", function(B){
		var A = BBEvent.target(B);
		if (Dom.hasClassName(A.parentNode.parentNode.parentNode, "c2")) {
			clearTimeout(popPicTimeout);
			popPicTimeout = setTimeout(function(){
				if (popPic) {
					popPic.hide();
				}
			}, POP_PIC_DELAY);
		}
	});
	
	var site_url = 'http://localhost:8080/spilder';
	var datePic = null;
	var daPic = null;
	var datePicTimeout = null;
	var POP_DATE_DELAY = 200;
	var str="";
	observe("data-table", "click", function(B){
	
	
	
		var A = BBEvent.target(B);
		if (Dom.hasClassName(A, "seller")) {
		
		str=A.id;
		if(document.getElementById("teamdate"+str)==null)
		{
		if (!daPic) {
		var C = '<div class="temp2008080" id="wd">加载中...</div>';
				daPic = new LayerPopup({
					shadow: true,
					center: false,
					header: false,
					className: "panel-t5 panel-t5-lt",
					footer: false,
					cue: true,
					autoPosition: true,
					content: C
				});
		}
		daPic.show(83, 0, null, null, A);
				
		load('getCanderTeam.shtml?teamId=' + str, function(o) {
		var retrunDateStr = BBAjax.opResults(o.responseText, false);		
				daPic.hide();
				var C = '<div id="teamdate'+str+'" class="temp2008080"></div>';
				datePic = new LayerPopup({
					shadow: true,
					center: false,
					header: false,
					className: "panel-t5 panel-t5-lt",
					footer: false,
					cue: true,
					autoPosition: true,
					content: C
				});
				
				BBEvent.observe($("teamdate"+str), "mouseover", function(D){
					clearTimeout(datePicTimeout);
				});
				BBEvent.observe($("teamdate"+str), "mouseout", function(D){
					datePicTimeout = setTimeout(function(){
						datePic.hide();
					}, POP_DATE_DELAY);
				});
				BBEvent.observe(datePic._cue, "mouseover", function(D){
					clearTimeout(datePicTimeout);
				});
				BBEvent.observe(datePic._cue, "mouseout", function(D){
					datePicTimeout = setTimeout(function(){
						datePic.hide();
					}, POP_DATE_DELAY);
				});
			
			datePicTimeout = setTimeout(function(){
				$("teamdate"+str).innerHTML=retrunDateStr
				datePic.show(83, 0, null, null, A);
				var F = Dom.getAbsolutePosition(datePic.getPopup());
				var G = Dom.getAbsolutePosition(A);
				var D = Dom.getAbsolutePosition(datePic._cue);
				var E = G.top - F.top + 10;
				if (E < 0) {
					E = 0;
				}
				if (E > F.height - D.height) {
					E = F.height - D.height;
				}
				datePic._cue.style.top = E + "px";
			}, POP_DATE_DELAY);
			});
		}
		else
		{
			var C = '<div id="teamdate'+str+'" class="temp2008080">'+$("teamdate"+str).innerHTML+'</div>';
				datePic = new LayerPopup({
					shadow: true,
					center: false,
					header: false,
					className: "panel-t5 panel-t5-lt",
					footer: false,
					cue: true,
					autoPosition: true,
					content: C
				});
				
				BBEvent.observe($("teamdate"+str), "mouseover", function(D){
					clearTimeout(datePicTimeout);
				});
				BBEvent.observe($("teamdate"+str), "mouseout", function(D){
					datePicTimeout = setTimeout(function(){
						datePic.hide();
					}, POP_DATE_DELAY);
				});
				BBEvent.observe(datePic._cue, "mouseover", function(D){
					clearTimeout(datePicTimeout);
				});
				BBEvent.observe(datePic._cue, "mouseout", function(D){
					datePicTimeout = setTimeout(function(){
						datePic.hide();
					}, POP_DATE_DELAY);
				});
				BBEvent.observe($("left_"+str), "mouseover", function(D){
					clearTimeout(datePicTimeout);
				});
				BBEvent.observe($("left_"+str), "mouseout", function(D){
					datePicTimeout = setTimeout(function(){
						datePic.hide();
					}, POP_DATE_DELAY);
				});
				
			
			datePicTimeout = setTimeout(function(){
			
				datePic.show(83, 0, null, null, A);
				var F = Dom.getAbsolutePosition(datePic.getPopup());
				var G = Dom.getAbsolutePosition(A);
				var D = Dom.getAbsolutePosition(datePic._cue);
				var E = G.top - F.top + 10;
				if (E < 0) {
					E = 0;
				}
				if (E > F.height - D.height) {
					E = F.height - D.height;
				}
				datePic._cue.style.top = E + "px";
			}, POP_DATE_DELAY);
		}	
		}
		
	});
	observe("data-table", "mouseout", function(B){
		var A = BBEvent.target(B);
		if (Dom.hasClassName(A, "seller")) {
			clearTimeout(datePicTimeout);
			datePicTimeout = setTimeout(function(){
				if (datePic) {
					datePic.hide();
				}
			}, POP_DATE_DELAY);
		}
	});
	
	
	
	observe("data-table", "click", function(F){
		var E = BBEvent.target(F);
		if (Dom.hasClassName(E, "btn-im-online") || Dom.hasClassName(E, "btn-im-offline") || Dom.hasClassName(E, "btn-im-online2") || Dom.hasClassName(E, "btn-im-offline2")) {
			BBEvent.preventDefault(F);
			var C = E.getAttribute("account").trim();
			if (C) {
				var B = "";
				var A = "";
				var D = E.getAttribute("iid");
				if (D) {
					A = D;
				}
				startBaiduHi("c2cmsg", B, C, {
					method: "ccscene",
					iid: A
				});
			}
		}
	});
	
	observe("clear_history", "click", function(B){
		load('removeHistoryTeam.shtml', function(o) {
		var C = $("item_navigation_history");
		var A = C.parentNode;
		A.removeChild(C);
		A.innerHTML = '<p style="padding:20px 10px;text-align:center;color:#666;">您已清空最近浏览过的线路</p>';
		$("clear_history").style.display = "none";
		
		});
	});
	function checkMansNumber(obj)
			{
				var vNumber=obj.value;
				if(vNumber!="")
				{
				    var reg = /^[0-9]+$/;
    				
				    if(!reg.test(vNumber))
				    {
					    alert("应填写整数！");
					    obj.value=0;
					    obj.focus();
					    return ;
				    }
				}
			}
	observe("priceStart", "keyup", function(B){
		if(!checkMansNumber($("priceStart")))
		{价格请填写正整数}
	});
	observe("priceEnd", "keyup", function(B){
		if(!checkMansNumber($("priceEnd")))
		{价格请填写正整数}
	});
	