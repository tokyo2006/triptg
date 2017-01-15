function toggleCategory(c_id){
	Element.toggle("child"+c_id);
}

function xmlTransform(_xml,_xsl,_divId){
	// Load XML
	var xml_obj = new ActiveXObject("Microsoft.XMLDOM");
	xml_obj.async = false;
	xml_obj.load(_xml);
	// Load XSL
	var xsl_obj = new ActiveXObject("Microsoft.XMLDOM");
	xsl_obj.async = false;
	xsl_obj.load(_xsl);
	// Transform
	//alert(xml_obj.transformNode(xsl_obj));
	$(_divId).innerHTML = xml_obj.transformNode(xsl_obj);
}

function goLogin(obj_href) {
	obj_href.href="login.html?url="+encodeURIComponent(location.href);
}

function goLogout(obj_href) {
	obj_href.href="logout.jspx?url="+encodeURIComponent(location.href);
}

function getPageLocation(pageIndex) {
    var _url = location.href;
    var _n = _url.lastIndexOf("?");
    if(_n==(-1))
        return _url + "?page=" + pageIndex;
    var query = _url.substring(_n+1);
    var pairs = query.split("&");
    var newQuery = "";
    for(var i=0; i<pairs.length; i++) {
        var pos = pairs[i].indexOf("=");
        if(pos==-1) continue;
        var argname = pairs[i].substring(0,pos);
        if(argname=="page") continue;
        newQuery = newQuery + pairs[i] + "&";
    }
    return _url.substring(0,_n+1) + newQuery + "page=" + pageIndex;
}

function getParam(_param) {
	var query = location.search.substring(1);
	var pairs = query.split("&");
	for(var i=0;i<pairs.length;i++) {
		var pos = pairs[i].indexOf("=");
		if(pos==-1)continue;
		var argname=pairs[i].substring(0,pos);
		if(argname==_param){
			var value=pairs[i].substring(pos+1);
			return decodeURIComponent(value);
		}
	}
	return null;
}

function setCookie(name,value){
	var argv = setCookie.arguments;
   var argc = setCookie.arguments.length;
   var expires = (argc > 2) ? argv[2] : null;
   expires = new Date();
   expires.setDate(expires.getDate()+(24*60*60*365));
   var path = (argc > 3) ? argv[3] : null;
   var domain = (argc > 4) ? argv[4] : null;
   var secure = (argc > 5) ? argv[5] : false;
   document.cookie = name + "=" + escape (value) +
        ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
        ((path == null) ? "" : ("; path=" + path)) +
        ((domain == null) ? "" : ("; domain=" + domain)) +
        ((secure == true) ? "; secure" : "");
}

function getCookie(name){
   var arg = name + "=";
   var alen = arg.length;
   var clen = document.cookie.length;
   var i = 0;
   while (i < clen) 
      {
      var j = i + alen;
      if (document.cookie.substring(i, j) == arg)
         return getCookieVal (j);
      i = document.cookie.indexOf(" ", i) + 1;
      if (i == 0) break; 
      }
   return null;

}

function getCookieVal (offset) 
   {
   var endstr = document.cookie.indexOf (";", offset);
   if (endstr == -1)
      endstr = document.cookie.length;
   return unescape(document.cookie.substring(offset, endstr));
   }
   
 function deleteCookie(name){
   var exp = new Date();
   exp.setTime (exp.getTime() - 1000000000); 
   var cval = getCookie (name);
   document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString();
   }

function go2map(city, loc){
	window.open("http://bendi.google.com/clocal?hl=zh-CN&lr=&newwindow=1&q="+encodeURIComponent(loc)+"&near="+encodeURIComponent(city)+"&btnG=%E6%90%9C%E7%B4%A2&sc=1");
}

function setCookie(c_name,value){
	var exdate=new Date();
	exdate.setDate(365);
	document.cookie=c_name+"="+escape(value)+";expires="+exdate;
}

function getCookie(c_name){
	if(document.cookie.length>0){
		c_start=document.cookie.indexOf(c_name + "=");
		if (c_start!=-1){
			c_start=c_start+c_name.length+1;
			c_end=document.cookie.indexOf(";",c_start);
			if (c_end==-1)
				c_end=document.cookie.length;
			return unescape(document.cookie.substring(c_start,c_end));
		} 
	}
	return null;
}

function hoverTabButton(tdObj){
	tdObj.style.backgroundColor="#dedeef";
	tdObj.style.color="#ff0000";
}

function normalTabButton(tdObj){
	tdObj.style.backgroundColor="#dedeef";
	tdObj.style.color="#0061de";
}

function hoverPage(tdObj){
	tdObj.style.backgroundColor="#62A2D2";
	tdObj.style.color="#ffffff";
}

function normalPage(tdObj){
	tdObj.style.backgroundColor="#ffffff";
	tdObj.style.color="#0061de";
}

function openCart(cartUrl){
	window.open(cartUrl, "lxf_LiVeCaRt", "height=400,width=680,toolbar=no,location=yes,scrollbars=yes,status=yes");
}

function fakepwd(pwd){
	var s=""
	for(var i=0;i<pwd.length;i++)
  		s=s+"#";
	return s;
}
