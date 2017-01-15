/*
 * 
 */



Ext.onReady(function(){
	//get All unit Name for comboBox.
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init(); 
	var centerbody;
   
	doAjax();  	  
});

function doAjax() {
		Ext.Ajax.request({
			url : 'getRegionMenuByUser.shtml',
			method : 'POST',
			success: function ( result, request) { 
				initData(result);
			},
			failure: function ( result, request) { 
				Ext.MessageBox.alert('加载错误','菜单信息加载失败！'); 
				window.close();
			} 
		});
	}

function initData(result){
  var regionJosn = doJSON(result.responseText);
  if (regionJosn.success == true) {
		 initView();
  initMenu(regionJosn.menuRegion);
	}
	else {
		Ext.MessageBox.alert('加载失败', regionJosn.msg);
	}
}


/**
 * 
 * 初始化开班表格信息
 * 
 */

function initView(){
	
	//======================顶部菜单布局=========================================
	var header = new Ext.BoxComponent({ // raw
                    region:'north',
                    el: 'north',
                    height:32
                });
	
	centerbody =new Ext.TabPanel({
			region:'center',
            activeTab: 0,
            resizeTabs:true, // turn on tab resizing
			minTabWidth: 100,
            deferredRender:false,  //是否延迟加载
		    enableTabScroll:true,
            plugins: new Ext.ux.TabCloseMenu(),
			
			items:[{
				title: '所有开班信息',
				iconCls:'tabs',
				tabTip:'用户的所有开班',
				autoWidth:true,
				frame:true,
				autoLoad: {url: "tabFrameTeam.jsp?url="+'totalTeam.jsp', callback: this.initSearch, scope: this,  text: "Loading..."}		
			}]
		});

	new Ext.Viewport({
		     layout: 'border',
		     items: [
				 header,
				 centerbody
			 ]
		   });
}

function onItemClick(item)
{
	updateTab(item.id,item.text,'teamCenter.jsp?params='+item.id+'@@@'+item.stateId);
}
function addTab(id,tabTitle,targetUrl){
	centerbody.add({
		id:id,
		title: tabTitle,
		iconCls:'tabs',
		frame:true,
		tabTip:tabTitle,
		autoLoad: {url: "tabFrameTeam.jsp?url="+targetUrl, callback: this.initSearch, scope: this,  text: "Loading..."},
		closable:true
		}).show();
}
// 更新tab内容，如不存在就新建一个
function updateTab(tabId,title,url){
	var tab = centerbody.findById(tabId);
	if(!tab)
	{
		tab = addTab(tabId,title,url);
	}
		centerbody.setActiveTab(tab);
}





