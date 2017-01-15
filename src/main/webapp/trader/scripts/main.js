 Ext.onReady(function(){
 
 	Ext.BLANK_IMAGE_URL = '../resources/ext/resources/images/default/s.gif';
 	Ext.QuickTips.init();
 	
 	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	
	var actions;
	var maincenter,lefter;
	var viewData = new Array();

	initView();	
 });

var turn;
var turn2;

function initView(){	
	var con = new Ext.Panel({
    	frame:true,
    	title: '基本设置',
		id:'id0',
    	collapsible:true,
		collapsed: true,
    	contentEl:'config',
    	titleCollapse: true
    });
    
    var areaManage = new Ext.Panel({
		frame:true,
		title: '地域管理',
		id:'id1',
		collapsible:true,
		collapsed: true,
		contentEl:'areaManage',
		titleCollapse: true
	});
    
     var regionManage = new Ext.Panel({
		frame:true,
		title: '类别管理',
		id:'id2',
		collapsible:true,
		collapsed: true,
		contentEl:'regionManage',
		titleCollapse: true
	});
	   var sceneryManage = new Ext.Panel({
		frame:true,
		title: '景点管理',
		id:'id3',
		collapsible:true,
		collapsed: true,
		contentEl:'sceneryManage',
		titleCollapse: true
	});	
	var visaManage = new Ext.Panel({
		frame:true,
		title: '签证管理',
		id:'id4',
		collapsible:true,
		collapsed: true,
		contentEl:'visaManage',
		titleCollapse: true
	});
	var ticketManage = new Ext.Panel({
		frame:true,
		title: '票务管理',
		id:'id5',
		collapsible:true,
		collapsed: true,
		contentEl:'ticketManage',
		titleCollapse: true
	});
	var tripManage = new Ext.Panel({
		frame:true,
		title: '旅游管理',
		id:'id6',
		collapsible:true,
		collapsed: true,
		contentEl:'tripManage',
		titleCollapse: true
	});
	var newsManage = new Ext.Panel({
		frame:true,
		title: '新闻管理',
		id:'id7',
		collapsible:true,
		collapsed: true,
		contentEl:'newsManage',
		titleCollapse: true
	});
	var shopManage = new Ext.Panel({
		frame:true,
		title: '门市管理',
		id:'id8',
		collapsible:true,
		collapsed: true,
		contentEl:'shopManage',
		titleCollapse: true
	});
	var adminMange = new Ext.Panel({
		frame:true,
		title: '管理员管理',
		id:'id9',
		collapsible:true,
		collapsed: true,
		contentEl:'managerMange',
		titleCollapse: true
	});
	var pageMange = new Ext.Panel({
		frame:true,
		title: '首页管理',
		id:'id10',
		collapsible:true,
		collapsed: true,
		contentEl:'pageMange',
		titleCollapse: true
	});
	var flashMange = new Ext.Panel({
		frame:true,
		title: 'Flash管理',
		id:'id11',
		collapsible:true,
		collapsed: true,
		contentEl:'flashMange',
		titleCollapse: true
	});
	var orderMange = new Ext.Panel({
		frame:true,
		title: '订单管理',
		id:'id12',
		collapsible:true,
		collapsed: true,
		contentEl:'orderMange',
		titleCollapse: true
	});
	var commentMange = new Ext.Panel({
		frame:true,
		title: '留言管理',
		id:'id13',
		collapsible:true,
		collapsed: true,
		contentEl:'commentMange',
		titleCollapse: true
	});		
    
    lefter = new Ext.Panel({
    	id:'action-panel',
    	region:'west',
		autoScroll:true,
    	title:'门户系统管理面板',
		split:true,
		width: 150,
		minSize: 150,
		maxSize: 400,
		collapsible: true,
		frame:true,
		margins:'0 0 2 5',
    	
    	items: [con,areaManage,regionManage,sceneryManage,visaManage,ticketManage,tripManage,orderMange,commentMange,newsManage,shopManage,adminMange,flashMange,pageMange]
    });
	
//======================底部菜单布局=========================================
	var footer = new Ext.Panel({
                    region:'south',
                    contentEl: 'south',
                    split:false,
                    height: 0,
                    collapsible: false,
                    title:'<p style="text-align:center">Copyright 2006-2009 苏ICP证B2-20050071 备案序号：苏ICP备05051631号 技术支持：斯派德网络</p>',
                    margins:'0 0 0 0'
                });
//=========================================================================
	   
//===================中间内容====================================================
	 var content1 = new Ext.Panel({
					    id:'tab1',
                        contentEl:'center1',
                        title: '欢迎你',
						iconCls: 'icon-by-category',
						autoLoad: {url: 'tabFrame.jsp?url=mainPage/default.jsp', callback: this.initSearch, scope: this},
                        autoScroll:true
                    });
					
	   maincenter = new Ext.TabPanel({
                    region:'center',
					margins:'0 5 2 0',
					resizeTabs:true, // turn on tab resizing
					minTabWidth: 100,
					defaults: {autoScroll:true},
                    deferredRender:false,  //是否延迟加载
		            enableTabScroll:true,
					plugins: new Ext.ux.TabCloseMenu(),
                    activeTab:0,
                    items:[content1]
                });
	   //======================================================================

	   //========================布局输出=======================================
       var viewport = new Ext.Viewport({
            layout:'border',
            items:[ 
					footer, 
				 	lefter,
					maincenter
             ]
        });

		actions = {
			'comBtn': function(){
				showCompany();
			},
			'siteBtn': function(){
				updateTab('tabSite', Ext.getDom('siteBtn').innerHTML, 'site/default.jsp','');
			},
			'ipRouterBtn': function(){
				updateTab('tabIpRouter', Ext.getDom('ipRouterBtn').innerHTML, 'iPRouter/default.jsp','');
			},
			'areaBtn': function(){
				updateTab('tabArea', Ext.getDom('areaBtn').innerHTML, 'area/default.jsp','');
			},
			'continentBtn': function(){
				updateTab('tabContinent', Ext.getDom('continentBtn').innerHTML, 'continent/default.jsp','');
			},
			'nationBtn': function(){
				updateTab('tabNation', Ext.getDom('nationBtn').innerHTML, 'nation/default.jsp','');
			},
			'provinceBtn': function(){
				updateTab('tabProvince', Ext.getDom('provinceBtn').innerHTML, 'province/default.jsp','');
			},
			'cityBtn': function(){
				updateTab('tabCity', Ext.getDom('cityBtn').innerHTML, 'city/default.jsp','');
			},
			'regionBtn': function(){
				updateTab('tabRegion', Ext.getDom('regionBtn').innerHTML, 'region/default.jsp','');
			},
			'planeBtn': function(){
				updateTab('tabPlane', Ext.getDom('planeBtn').innerHTML, 'ticket/default3.jsp','');
			},
			'busBtn': function(){
				updateTab('tabBus', Ext.getDom('busBtn').innerHTML, 'ticket/default.jsp','');
			},
			'trainBtn': function(){
				updateTab('tabTrain', Ext.getDom('trainBtn').innerHTML, 'ticket/default2.jsp','');
			},
			'shipBtn': function(){
				updateTab('tabShip', Ext.getDom('shipBtn').innerHTML, 'ticket/default4.jsp','');
			},
			'assembleBtn': function(){
				updateTab('tabAssemble', Ext.getDom('assembleBtn').innerHTML, 'assemble/default.jsp','');
			},
			'jobberLineBtn': function(){
				updateTab('tabJobberLine', Ext.getDom('jobberLineBtn').innerHTML, 'jobberLine/default.jsp','');
			},
			'lineBtn': function(){
				updateTab('tabLine', Ext.getDom('lineBtn').innerHTML, 'line/default.jsp','');
			},
			'jobberTeamBtn': function(){
				updateTab('tabJobberTeam', Ext.getDom('jobberTeamBtn').innerHTML, 'jobberTeam/default.jsp','');
			},
			'teamBtn': function(){
				updateTab('tabTeam', Ext.getDom('teamBtn').innerHTML, 'team/default.jsp','');
			},
			'orderBtn': function(){
				updateTab('tabOrder', Ext.getDom('orderBtn').innerHTML, 'order/default.jsp','');
			},
			'commentBtn': function(){
				updateTab('tabComment', Ext.getDom('commentBtn').innerHTML, 'comment/default.jsp','');
			},
			'newsTypeBtn': function(){
				updateTab('tabNewsType', Ext.getDom('newsTypeBtn').innerHTML, 'newsType/default.jsp','');
			},
			'newsBtn': function(){
				updateTab('tabNews', Ext.getDom('newsBtn').innerHTML, 'news/default.jsp','');
			},
			'managerInfBtn': function(){
				showManagerInf();
			},
			'managerBtn': function(){
				updateTab('tabManager', Ext.getDom('managerBtn').innerHTML, 'manager/default.jsp','');
			},
//			'shopBtn': function(){
//				updateTab('tabShopper', Ext.getDom('shopBtn').innerHTML, 'shopper/default.jsp','');
//			},
//			'agencyBtn': function(){
//				document.getElementById('agencyBtn').href='http://localhost:8080/travel/jobber/index.jsp';
//				Ext.getDom('agencyBtn').target="_blank";
//			},
			'keyBtn': function(){
				updateTab('tabKey', Ext.getDom('keyBtn').innerHTML, 'key/default.jsp','');
			},
			'sceneryTypeBtn': function(){
				updateTab('tabSceneryType', Ext.getDom('sceneryTypeBtn').innerHTML, 'sceneryType/default.jsp','');
			},
			'sceneryBtn': function(){
				updateTab('tabScenery', Ext.getDom('sceneryBtn').innerHTML, 'scenery/default.jsp','');
			},
			'sceneryRegionBtn': function(){
				updateTab('tabSceneryRegion', Ext.getDom('sceneryRegionBtn').innerHTML, 'sceneryRegion/default.jsp','');
			},
			'visaBtn': function(){
				updateTab('tabVisa', Ext.getDom('visaBtn').innerHTML, 'visa/default.jsp','');
			},
			'flashTypeBtn': function(){
				updateTab('tabFlashType', Ext.getDom('flashTypeBtn').innerHTML, 'flashType/default.jsp','');
			},
			'flashBtn': function(){
				updateTab('tabFlash', Ext.getDom('flashBtn').innerHTML, 'flash/default.jsp','');
			},
			'pageBtn': function(){
				updateTab('tabPage', Ext.getDom('pageBtn').innerHTML, 'page/default.jsp','');
			},
			'pageRegionBtn': function(){
				updateTab('tabPageRegion', Ext.getDom('pageRegionBtn').innerHTML, 'pageRegion/default.jsp','');
			},
			'pageContentBtn': function(){
				updateTab('tabPageContent', Ext.getDom('pageContentBtn').innerHTML, 'pageContent/default.jsp','');
			},
			'orderStat': function(){
				updateTab('taborderStat', Ext.getDom('orderStat').innerHTML, 'order/count.jsp','');
			}
		};
		
//	layoutPage();
    // body初始化后，viewport setup过后才能执行下面的代码
    lefter.body.on('mousedown', doAction, null, {delegate:'a'});

	//得到数组
//	initViewData(lefter);
	setTurn();//delete if the initViewData is active
}

function addTab(id, tabTitle, targetUrl,classId){
	if(classId!==''){
		targetUrl+='?params='+classId;
	}
	maincenter.add({
		id: id,
		title: tabTitle,
		iconCls: 'icon-by-category',
		autoLoad: {
			url: "tabFrame.jsp?url=" + targetUrl,
			callback: this.initSearch,
			scope: this
		},
		frame: true,
		closable: true
	}).show();
}
		
function updateTab(tabId, title, url,classId){
	var tab = maincenter.getItem(tabId);
	if (!tab) {
		tab = addTab(tabId, title, url,classId);
	}
	maincenter.setActiveTab(tab);
}
		
function doAction(e, t){
	e.stopEvent();
	if(typeof (actions[t.id])!=='undefined'){
		actions[t.id]();
	}
//	alert(typeof actions[t.id]);
	
}
		
function initViewData(lefter){
	
	setTurn();
	
	Ext.Ajax.request({
			url: 'auths.shtml',
			method: 'POST',
			success: function(result, request){
				leftPanelShow(result, lefter);
			},
			failure: function(result, request){
				Ext.MessageBox.alert('加载失败！');
				window.close();
			}
		});
}

function leftPanelShow(result,lefter){
	var leftList = doJSON(result.responseText);
	var vd = leftList.auths;
	var vn = new Array();
	vn=['id0','id1','id2','id3','id4','id5','id6','id7','cityInfo','airportInfo','id8','id4'];
	
	if(parseInt(vd[7])===0){
		hideTag(vn[0]);
	}
		
	if(vd[5]==='0'){
		hideTag(vn[1]);
	}
	
	if(vd[3]==='0'){
		hideTag(vn[2]);
	}
	
	if(vd[4]==='0'){
		hideTag(vn[3]);
	}
	if(vd[6]==='0'){
		hideTag(vn[5]);
	}
	if(vd[2]==='0'){
		hideTag(vn[6]);
	}
	if(vd[8]==='0'){
		hideTag(vn[7]);
	}
	if(vd[0]==='0'){
		hideTag(vn[8]);
	}
	if(vd[1]==='0'){
		hideTag(vn[9]);
	} 
	
	if(vd[1]==='0'&&vd[0]==='0'){
		hideTag(vn[11]);
	}
	
	if(vd[9]==='0'){
		hideTag(vn[10]);
	}	
}		

function hideTag(id){
	 document.getElementById(id).style.display='none';
}
/*
function layoutPage(){
	var browseType = checkBrowse();
	
	Ext.Ajax.request({
		url: 'getPageList.shtml',
		method: 'POST',
		success: function(result, request){
			initPagePanel(result, browseType);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载类别失败！');
		}
	});
	
}

function checkBrowse(){
	var browseType;
	var Sys = {};
	var ua = navigator.userAgent.toLowerCase();
	var s;
	(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
	
	//以下进行测试
	if (Sys.ie) 
//		document.write('IE: ' + Sys.ie);
		browseType='IE';
	if (Sys.firefox) 
//		document.write('Firefox: ' + Sys.firefox);
		browseType='Firefox';
	if (Sys.chrome) 
//		document.write('Chrome: ' + Sys.chrome);
		browseType='Chrome';
	if (Sys.opera) 
//		document.write('Opera: ' + Sys.opera);
		browseType='Opera';
	if (Sys.safari) 
//		document.write('Safari: ' + Sys.safari);
		browseType='Safari';
	return browseType;	
}

function initPagePanel(result, browseType){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.pageList;
		var paramId;
		for (var i = 0; i < list.length; i++) {
			pageSortAjax(list[i].id,browseType,list[i].title);
		}
				
	}
}

function pageSortAjax(classId,browseType,sortTitle){
	Ext.Ajax.request({
				url: 'getPageSortList.shtml',
				method: 'POST',
				params:{
					classId:classId
				},
				success: function(result, request){
					initLi(result, browseType,sortTitle,classId);
				},
				failure: function(result, request){
					Ext.MessageBox.alert('加载类别失败！');
				}
			});
}

function initLi(result, browseType,sortTitle,classId){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var subList = strList.pageSortList;
		if (subList.length > 0) {
			var pagePanelMange = new Ext.Panel({
				frame: true,
				title: sortTitle,
				id: classId,
				collapsible: true,
				titleCollapse: true,
				collapsed: false
			});
			var tempUl = document.createElement('ul');
			var tempLi, tempA, tempImg;
			
			for (var j = 0; j < subList.length; j++) {
				tempImg = document.createElement('img');
				tempImg.src = '../resources/ext/resources/images/yeoou/s.gif';
				tempImg.className = 'icon-edit';
				
				tempLi = document.createElement('li');
				tempA = document.createElement('a');
				if (browseType === 'IE') {
					tempA.href = '#';
					tempA.innerHTML = subList[j].title;
					tempA.setAttribute('onclick', 'updateTab(\'tabPageContent' + subList[j].name + '\',\'' + subList[j].title + '\',\'pageContent/default.jsp\',\'' + subList[j].classId + '\');');
					tempLi.innerHTML = tempImg.outerHTML + tempA.outerHTML;
					tempUl.appendChild(tempLi);
				}
				else {//现在只测试了firefox
					tempLi.appendChild(tempImg);
					tempA.href = '#';
					tempA.appendChild(document.createTextNode(subList[j].title));
					tempA.setAttribute('onclick', 'updateTab(\'tabPageContent' + subList[j].name + '\',\'' + subList[j].title + '\',\'pageContent/default.jsp\',\'' + subList[j].classId + '\');');
					tempLi.appendChild(tempA);
					tempUl.appendChild(tempLi);
				}
				tempLi = '', tempA = '';
			}
			
			subList.length = 0;
			pagePanelMange.add(tempUl);
			tempUl = '';
			pagePanelMange.collapsed=true;
			lefter.add(pagePanelMange);
			lefter.doLayout();
			pagePanelMange = '';
		}
	}
}
*/

function setTurn(){
	window.clearInterval(turn);
	turn= window.setInterval(getOrderMsg,1000*60);
}

function getOrderMsg(){
	Ext.Ajax.request({
			url: 'getOrderNews.shtml',
			method: 'POST',
			success: function(result, request){
				initOrderMsg(result);
			},
			failure: function(result, request){
				Ext.MessageBox.alert('订单提示','订单提示加载失败！');
			}
		});
}

function initOrderMsg(result){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var nonDispose;
		var newCount = strList.newCount;
		if(parseInt(newCount)!==0){
			nonDispose = strList.nonDispose;
			document.getElementById("undoNum").innerHTML=nonDispose;
			document.getElementById("newNum").innerHTML=newCount;
			var tempVisibility=document.getElementById("msgDiv").style.visibility;
			if(tempVisibility==='hidden'||tempVisibility===''){
				document.title='您有'+newCount+'条新订单！';
				getMsg();
				turn2=window.setInterval(changeTitle,1000);
			}
		}
	}	
}

function changeTitle(){
	if ((document.title !== '订单提示！')&&(document.title!== '旅游分销平台欢迎您！')) {
		oldTitle = document.title;
		document.title = '订单提示！';
	}
	else {
		document.title = oldTitle;
	}
}
