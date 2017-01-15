function BBAjax(A){this.options={url:"",method:"post",async:true,user:"",pwd:"",contentType:"application/x-www-form-urlencoded",charset:"UTF-8",requestHeaders:{"x-baidu-ie":"utf-8","x-baidu-rf":"json"},data:"",useLock:0,sendImdtly:0,timeout:30000};Object.extendJson(this.options,A);this._initialize();}BBAjax.Versions=["MSXML2.XMLHttp.6.0","MSXML2.XMLHttp.3.0","MSXML2.XMLHttp.5.0","MSXML2.XMLHttp.4.0","Msxml2.XMLHTTP","MSXML.XMLHttp","Microsoft.XMLHTTP"];BBAjax.Delay=950;BBAjax.prototype._initialize=function(){this._locked=0;this._completed=0;this.isTimeout=0;this.aborted=0;this.requester=BBAjax.getRequestObj(this.options.useRequestPool);if(!this.requester){alert("非常抱歉！初始化请求失败：#"+BBAjax.Versions.length);return ;}var B=this.options;var A=this;if(B.async){this.requester.onreadystatechange=function(){var C=A.requester.readyState;if(C==4){A._onComplete();}};}this.dispose=function(){if(A){A.requester=null;A=null;}};if(B.sendImdtly){this.send();}BBEvent.observe(window,"unload",this.dispose);};BBAjax.getRequestObj=function(){if(window.ActiveXObject){while(BBAjax.Versions.length>0){try{return new ActiveXObject(BBAjax.Versions[0]);}catch(A){BBAjax.Versions.shift();continue;}}}else{if(window.XMLHttpRequest){return new XMLHttpRequest();}}return null;};BBAjax.prototype._setRequestHeader=function(){var D={};D["Content-type"]=this.options.contentType+(this.options.charset?"; charset="+this.options.charset:"");var C=this.options.requestHeaders;if(typeof (C)=="object"){Object.extendJson(D,C);}else{if(C&&typeof (C.push)=="function"){for(var B=0,A=C.length;B<A;B+=2){D[C[B]]=C[B+1];}}}for(var B in D){this.requester.setRequestHeader(B,D[B]);}};BBAjax.prototype._onComplete=function(){var A=this.requester;var B=this.options;this._completed=1;if(B.useLock){this._locked=0;}if(this._timer){clearTimeout(this._timer);}if(B.oncomplete){B.oncomplete.call(this,A,"Complete");}if(this.aborted){return ;}if(B.onsuccess&&this.isSuccess()){B.onsuccess.call(this,A,"Success");}else{if(B.onerror&&!this.isSuccess()){B.onerror.call(this,A,"Error");}}};BBAjax.prototype.send=function(A,D,B){try{var E=this.options;if(E.useLock&&this._locked){return ;}else{if(!E.useLock&&this.inProcess()){this.cancel();setTimeout(function(){_self.send(A,D,B);},0);return ;}}A=A||E.url;D=D||E.method;B=B||E.data;if(typeof (B)=="object"){B=this._getURLString(B);}if(B&&D.toLowerCase()=="get"){A=A.indexOf("?")!=-1?A+"&"+B:A+"?"+B;}if(E.user==""&&E.pwd==""){this.requester.open(D.toUpperCase(),A,E.async);}else{this.requester.open(D.toUpperCase(),A,E.async,E.user,E.pwd);}this._setRequestHeader();this.isTimeout=0;if(E.useLock){this._locked=1;}this._completed=0;this.aborted=0;if(E.timeout>0){this._checkTimeout(E.timeout);}if(E.onsend){E.onsend.call(this);}BBAjax.lastPost={data:B,url:A,method:D};if(typeof (B)!="undefined"&&D.toLowerCase()=="post"){if(B==""){this.options.requestHeaders["Content-Length"]=0;B=" ";}this.requester.send(B);}else{this.requester.send(null);}}catch(C){alert("非常抱歉！请求失败:"+C);}};BBAjax.prototype.get=function(A,B){this.send(A,"get",B);};BBAjax.prototype.post=function(A,B){this.send(A,"post",B);};BBAjax.prototype.sendForm=function(C,B,E,A){var F=Dom.formURIEncode(C,A);var D=this.options.data;if(typeof (D)=="object"){D=this._getURLString(D);}if(D!=""){D+="&";}this.send(B||C.action,E||C.method,D+F);};BBAjax.getResponseText=function(A,D,E,C){var B=new BBAjax({url:A,method:D,data:E,sendImdtly:1,async:(typeof C!="undefined")});return B.requester.responseText;};BBAjax.prototype.cancel=function(){if(!this.requester){return false;}if(this.inProcess()){this.aborted=1;this.requester.abort();if(!this._completed){this._onComplete.call(this,this.requester,"aborted");}if(this.options.onabort){this.options.onabort(this.requester);}return true;}return false;};BBAjax.prototype._checkTimeout=function(B){var A=this;this._timer=setTimeout(function(){if(A.requester&&!A._completed){A.isTimeout=1;A.requester.abort();if(!A._completed){A._onComplete.call(A,A.requester,"timeout");}A._completed=1;}},B);};BBAjax.prototype.isSuccess=function(){if(this.isTimeout){return false;}var A=this.requester.status;return !A||(A>=200&&A<300);};BBAjax.prototype.inProcess=function(){var A=this.requester.readyState;return A>0&&A<4;};BBAjax.prototype._getURLString=function(B){var D=[];for(var C in B){if(B[C]==null){continue;}if(B[C].constructor==Array){for(var A=0;A<B[C].length;A++){D.push(C+"="+encodeURIComponent(B[C][A]));}}else{D.push(C+"="+encodeURIComponent(B[C]));}}return D.join("&");};BBAjax.opResults=function(Results,url){if(!Results){return false;}if(Results.replace(/(\n|\r)+/g,"").trim().substr(0,1)!="{"){return Results;}else{try{eval("var status="+Results);}catch(e){alert("返回出错，返回的内容为：\n"+Results);BB.Console.log("json返回出错！");return{err:"inter"};}}status.isop=true;switch(status.err){case"ok":if(url!=false){if(url){setTimeout("window.location='"+url+"'",BBAjax.Delay);}else{if(status.url==null){setTimeout("window.location = window.location.href;window.location.reload(true);",BBAjax.Delay);}else{setTimeout("window.location='"+status.url+"'",BBAjax.Delay);}}}break;case"input":if(status.err_desc=="format"){for(var i in status.fields){var tempEl=document.getElementsByName(i);try{if(tempEl.length>0){var unfound=true;if(tempEl.length==1){BBValid.vldErrorFun(tempEl[0],"您输入的内容格式不对或超长，请检查是否包含特殊字符。",true);unfound=false;}else{for(var x=0;x<tempEl.length;x++){if(status.fields[i]==tempEl[x].value.encode4Html()){BBValid.vldErrorFun(tempEl[x],"您输入的内容格式不对或超长，请检查是否包含特殊字符。",true);unfound=false;break;}}}if(unfound){alert("您输入的内容格式不对或超长，请检查是否包含特殊字符。");}}else{alert("您输入的内容格式不对或超长，请检查是否包含特殊字符。");}}catch(e){alert("您输入的内容格式不对或超长，请检查是否包含特殊字符。");}BB.Console.log("post input format",status.di);}}else{if(status.err_desc=="antispam"){alert("您提交的内容包含敏感词汇，请检查后重新提交！");BB.Console.log("post antispam",status.di);}else{status.isop=false;}}break;case"fake":window.location="/static/error/?di="+status.di;break;case"antispam":alert("您提交的内容包含敏感词汇，请检查后重新提交！");BB.Console.log("post antispam",status.di);break;case"maintain":alert("系统正忙，请您稍后再试！");BB.Console.log("post maintain",status.di);break;case"inter":alert("系统正忙，请您稍后再试！");BB.Console.log("post inter",status.di);break;case"unknow":alert("系统正忙，请您稍后再试！");BB.Console.log("post unknow",status.di);break;case"expires":alert("系统正忙，请您稍后再试！");BB.Console.log("post expires",status.di);setTimeout("window.location = window.location.href;window.location.reload(true);",1);break;case"forbid":alert("很抱歉，您没有权限进行此操作。");BB.Console.log("post forbid",status.di);break;case"nopower":window.location="/static/error/nopower.html";break;case"login":User.Login.show();User.Login.hint("您需要登录后才能继续刚才的操作");BB.Console.log("post login",status.di);break;default:status.isop=false;}return status;};