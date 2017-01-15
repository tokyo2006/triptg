var _bd_mdm=".baidu.com";var _bd_utk_src="http://utk.baidu.com/usv/ur.sv";var _bd_utk_an=50000000;var _bd_utk_sn="x2733ae3e8655fd33974bbb89";var nav=navigator;
function _bd_utk_getdate( nodays){
	var UTCstring;
	Today = new Date();
	nomilli=Date.parse(Today);
	Today.setTime(nomilli+nodays*24*60*60*1000);
	UTCstring = Today.toUTCString();
	return UTCstring;
}
function _bd_utk_getcookie(cookiename) {
 var cookiestring=""+document.cookie;
 var index1=cookiestring.indexOf(cookiename);
 if (index1==-1 || cookiename=="") return ""; 
 var index2=cookiestring.indexOf(';',index1);
 if (index2==-1) index2=cookiestring.length; 
 return unescape(cookiestring.substring(index1+cookiename.length+1,index2));
}
function _bd_utk_setcookie(name,value,duration){
	cookiestring=name+"="+escape(value)+";expires="+_bd_utk_getdate(duration)+";path=/;domain="+_bd_mdm;
	document.cookie=cookiestring;
	if(!_bd_utk_getcookie(name)){
		return false;
	}
	else{
		return true;
	}
}
function bd_utk_cvt(r){
	if(r){
		var VCT=new Array(4);
		VCT[0]=/'/g;
		VCT[1]=/\&/g;
		VCT[2]=/\#/g;
		VCT[3]=/\?/g;
		var VCR=new Array(4);
		VCR[0]="%27";
		VCR[1]="%26";
		VCR[2]="%23";
		VCR[3]="%3F";
		for(var i=0;i<4;i++){
			r=r.replace(VCT[i],VCR[i]);
		}
	}
	return r;
}	
function _bd_utk_getsc(){
	var s=screen.width*10000+screen.height;
	if(isNaN(s)) return '-';
	return s;
}
function _bd_utk_getos(ua){
    var _os='';
    var _ver='';
    var _i; 
    if((_i=ua.lastIndexOf('windows'))>-1){
        _os='w';
        var _s=ua.indexOf(';',_i);
        if(_s==-1)_s=ua.indexOf(')',_i);
        var _v=ua.substring(_s,_s-3);
        if(_v=='5.1') _ver='_xp';   
        else if(_v=='5.0') _ver='_2k';
        else if(_v=='5.2') _ver='_2k3';
        else if(_v=='6.0') _ver='_vta';
        else if(_v==' ce') _ver='_ce';
        else if(_v==' 98') _ver='_98';
        else if(_v==' me') _ver='_me';
        else if(_v==' 95') _ver='_95';
        else _ver='_-';
    }else if(ua.indexOf('mac os')>-1||ua.indexOf('mac_power')>-1||ua.indexOf('macintosh')>-1) _os='ap';
    else if(ua.indexOf('x11')>-1||ua.indexOf('linux')>-1||ua.indexOf('unix')>-1) _os='x';
    else _os='-';
    return _os+_ver;
}
function _bd_utk_getbsver(_pos,_ua){
    var _p=_pos;
    var _ua_len=_ua.length;
    var _max=_ua_len-_pos>9?_pos+10:_ua_len-1;
    var _ver='';
    var _flag=false;
    while(_p<=_max){
        var _uch=_ua.charAt(_p);
        if((!isNaN(_uch)||_uch=='.')&&_uch!=' '){ 
            if(!_flag) _flag=true;
            _ver+=_uch;
        }else if(_flag) break;
        _p++;
    }
    return _ver;
}
function _bd_utk_getbs(ua){
    var _bs='';
    var _ver='';
    var _i; 
    if((_i=ua.indexOf('msie'))>-1){
        _bs='ie';
        _ver=_bd_utk_getbsver(_i+4,ua);
    }else if((_i=ua.indexOf('firefox'))>-1){
        _bs='ff';
        _ver=_bd_utk_getbsver(_i+7,ua);
    }else if((_i=ua.indexOf('opera'))>-1){
        _bs='op';
        _ver=_bd_utk_getbsver(_i+5,ua);
    }else if((_i=ua.indexOf('safari'))>-1){
        _bs='sfr';
        _ver=_bd_utk_getbsver(_i+6,ua);
    }else if(ua.indexOf('gecko')>-1){
        _bs='gk';
    }else if(ua.indexOf('openwave')>-1){
        _bs='ow';
    }else _bs='-';
    if(_bs=='-') return '-';    
    if(_ver.length==0)_ver='-';
    return _bs+'_'+_ver;
}
function _bd_utk_getla(){
	var _l;
	if(nav.systemLanguage) _l=nav.systemLanguage;
	else if(nav.browserLanguage) _l=nav.browserLanguage;
	else if(nav.language) _l=nav.language;
	else if(nav.userLanguage) _l=nav.userLanguage;
	else _l='-';
	return _l.toLowerCase();
}
var _bd_utk_ref=bd_utk_cvt(window.document.referrer);
if(!_bd_utk_ref){
	_bd_utk_ref="-";
}
var _bd_utk_dvt = 0;
var _bd_cookie_dvt=_bd_utk_getcookie("BD_UTK_DVT");
var cookieEnabled=(nav.cookieEnabled)?true:false;
if (typeof nav.cookieEnabled=="undefined" && !cookieEnabled){ 
	document.cookie="testcookie";
	cookieEnabled=(document.cookie.indexOf("testcookie")!=-1)?true:false;
}
if(_bd_cookie_dvt && _bd_cookie_dvt != "" && _bd_cookie_dvt == "1"){
	_bd_utk_dvt=0;
	_bd_utk_setcookie("BD_UTK_DVT","1",365);
}else{
	if(cookieEnabled && _bd_utk_setcookie("BD_UTK_DVT","1",365)){
		_bd_cookie_dvt=_bd_utk_getcookie("BD_UTK_DVT");
    	if(_bd_cookie_dvt && _bd_cookie_dvt == "1"){
	        _bd_utk_dvt=1;
		}
	}
}
var _bd_utk_dvt_str = "";
if(_bd_utk_dvt ==1 ) _bd_utk_dvt_str="&fdvt=1";
var _bd_utk_ua=nav.userAgent.toLowerCase();
var _bd_utk_os=_bd_utk_getos(_bd_utk_ua);
var _bd_utk_bs=_bd_utk_getbs(_bd_utk_ua);
var _bd_utk_sc=_bd_utk_getsc();
var _bd_utk_la=_bd_utk_getla();
var _bd_utk_ck=cookieEnabled?'1':'0';
var _bd_utk_url="<script type='text/javascript' src='"+_bd_utk_src+"?&an="+_bd_utk_an+"&sn="+_bd_utk_sn+"&rf="+_bd_utk_ref+_bd_utk_dvt_str+"&os="+_bd_utk_os+"&bs="+_bd_utk_bs+"&sc="+_bd_utk_sc+"&la="+_bd_utk_la+"&ck="+_bd_utk_ck+"'>\<\/script>";
document.write(_bd_utk_url);
