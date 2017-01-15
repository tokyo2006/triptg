String.prototype.Trim = function()
{
	return this.replace(/(^\s*)|(\s*$)/g, "");  
}

/*duhu add start*/
function $N() {
	return document.getElementsByName(arguments[0]);
}

//全角字符转换为半角字符  适用于文本框中对数字的替换"onblur = this.value = this.value.ToDbc()" by duhu 2007.10.25
String.prototype.ToDbc = function() { 
	var result=''; 
	for(var i=0;i<this.length;i++) {
		if (this.charCodeAt(i)> 65280 && this.charCodeAt(i) < 65375){
			result += String.fromCharCode(this.charCodeAt(i)-65248); 
		}else if (this.charCodeAt(i) == 12288 ){
			result += String.fromCharCode(32);
		}else{	
			result += String.fromCharCode(this.charCodeAt(i));
		}
	}
 	return result; 
} 

//针对Element的扩展
Object.extend(Element, {
	setWidth: function(element,w) {
	   	element = $(element);
		element.style.width = w +"px";
	},
	setHeight: function(element,h) {
   		element = $(element);
    		element.style.height = h +"px";
	},
	setTop: function(element,t) {
	   	element = $(element);
		element.style.top = t +"px";
	},
	setSrc: function(element,src) {
		element = $(element);
		element.src = src; 
	}
});

//验证CheckBoxList中是否有选中的项  
function ValCkList(id){
	var la = $(id).getElementsByTagName("input");
	var ck = 0;
	for (var i = 0;i<la.length;i++){
		if (la[i].checked == true){
			return true;
			break;
		}
	}
	return false;
}
function AddEvtForCkList(id,f){
	var la = $(id).getElementsByTagName("input");
	var ck = 0;
	for (var i = 0;i<la.length;i++){
		Event.observe(la[i], 'click', function(){
			if (typeof(f) == "function")
				f();
			else
				return;
		});
	}
}

/*duhu add end*/

function mcl(show, div, btn, over, padd)
{
	var w3c=(document.getElementById)? true:false;
	var ns6=(w3c && (navigator.appName=="Netscape"))? true: false;
	var objdiv = gid(div);
	var objbtn = gid(btn);
	if (objdiv && objbtn)
	{
		var browser = new Browser();
		if (show)
		{
			objdiv.style.display = "block";
			if (browser.isIE && over)
			{
				var allselect = gname("select");
				for (var i=0; i<allselect.length; i++)
				{
					allselect[i].style.visibility = "hidden";
				}
			}
			var nLt=0;
			var nTp=0;
			var offsetParent = objbtn;
			while (offsetParent!=null && offsetParent!=document.body) {
				
				nLt+=parseInt(offsetParent.offsetLeft);
				nTp+=parseInt(offsetParent.offsetTop);
				/*if(!ns6){
					parseInt(offsetParent.currentStyle.borderLeftWidth)>0?nLt+=parseInt(offsetParent.currentStyle.borderLeftWidth):"";
					parseInt(offsetParent.currentStyle.borderTopWidth)>0?nTp+=parseInt(offsetParent.currentStyle.borderTopWidth):"";
				}*/
				offsetParent=offsetParent.offsetParent;
			}
			objdiv.style.top = (nTp + objbtn.offsetHeight - 1) + "px";
			objdiv.style.left = (nLt - (padd?0:1)) + "px";
		}
		else
		{
			objdiv.style.display = "none";
			if (browser.isIE && over)
			{
				var allselect = gname("select");
				for (var i=0; i<allselect.length; i++)
				{
					allselect[i].style.visibility = "visible";
				}
			}
		}
	}
}

function Browser(){var ua, s, i;this.isIE = false;this.isNS = false;this.isOP = false;this.isSF = false;ua = navigator.userAgent.toLowerCase();s = "opera";if ((i = ua.indexOf(s)) >= 0){this.isOP = true;return;}s = "msie";if ((i = ua.indexOf(s)) >= 0) {this.isIE = true;return;}s = "netscape6/";if ((i = ua.indexOf(s)) >= 0) {this.isNS = true;return;}s = "gecko";if ((i = ua.indexOf(s)) >= 0) {this.isNS = true;return;}s = "safari";if ((i = ua.indexOf(s)) >= 0) {this.isSF = true;return;}}
function gid(id){return document.getElementById?document.getElementById(id):null;}
function gname(name){return document.getElementsByTagName?document.getElementsByTagName(name):new Array()}

//获取并返回与 cookieName 同名的 cookie 名称，允许大小写不同
//如果不存在这样的 cookie，就返回 cookieName
function ResponseCookies_GetCookieName(cookieName)
{
    var lowerCookieName = cookieName.toLowerCase();
    var cookieStr = document.cookie;
    
    if (cookieStr == "")
    {
        return cookieName;
    }
    
    var cookieArr = cookieStr.split("; ");
    var pos = -1;
    for (var i=0; i<cookieArr.length; i++)
    {
        pos = cookieArr[i].indexOf("=");
        if (pos > 0)
        {
            if (cookieArr[i].substring(0, pos).toLowerCase() == lowerCookieName)
            {
                return cookieArr[i].substring(0, pos);
            }
        }
    }
    
    return cookieName;
}

//写入 cookie
//不区分 cookieName 的大小写
//不考虑子键
function ResponseCookies(cookieName, cookieValue, expires)
{
    if (expires)
    {
        //指定了 expires
        document.cookie = ResponseCookies_GetCookieName(cookieName) + "=" + escape(cookieValue) + "; expires=" + expires.toGMTString();
    }
    else
    {
        document.cookie = ResponseCookies_GetCookieName(cookieName) + "=" + escape(cookieValue);
    }
}

//获取并返回 cookie 值
//不区分 cookieName 的大小写
//dfltValue 为默认返回值
//不考虑子键
function RequestCookies(cookieName, dfltValue)
{
    var lowerCookieName = cookieName.toLowerCase();
    var cookieStr = document.cookie;
    
    if (cookieStr == "")
    {
        return dfltValue;
    }
    
    var cookieArr = cookieStr.split("; ");
    var pos = -1;
    for (var i=0; i<cookieArr.length; i++)
    {
        pos = cookieArr[i].indexOf("=");
        if (pos > 0)
        {
            if (cookieArr[i].substring(0, pos).toLowerCase() == lowerCookieName)
            {
                return unescape(cookieArr[i].substring(pos+1, cookieArr[i].length));
            }
        }
    }
    
    return dfltValue;
}

//线路分组联动菜单  线路类型，控件名称
function ChanageLineGroup(type,gcid){
	var url = '../Distributor/Line_AjaxCall.aspx';
	var pars = 'action=ChangeLineGroup&type=' + type + '&gcid=' + gcid + '&s='+Math.random();
	var myAjax = new Ajax.Request( url, {
		method: 'get', 
		parameters: pars, 
		onFailure: function(s){alert(s.responseText)},
		onComplete: function(s){
			eval(s.responseText);
		}
	});
}
var myGlobalHandlers = {
	onCreate: function(){
		$("SystemAjaxWorking").show();
		//alert($("SystemAjaxWorking").innerHTML);
	},
	onComplete: function() {
		if(Ajax.activeRequestCount == 0){
			$("SystemAjaxWorking").hide();
		}
	}
};

//table头的单击事件，隐藏/显示下面的信息,报价页面用
function hiddentblbyHead(hiddenid,tdid)
{
    if(!$(hiddenid).visible())
    {
        $(hiddenid).show();
        $(tdid).className="head_sec_close";
    }
    else
    {
        $(hiddenid).hide();
        $(tdid).className="head_sec_open";
    }
}

