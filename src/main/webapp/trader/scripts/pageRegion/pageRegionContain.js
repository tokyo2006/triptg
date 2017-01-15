/*
 * 
 */
	

Ext.onReady(function(){
	//get All unit Name for comboBox.
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	/*==============================全局变量t=============================================*/
	var centerbody;
	initView();
});

/*==============================中间新tabpanel中包含树的west=============================================*/
function initView(){

	centerbody = new Ext.TabPanel({
		region: 'center',
		id: 'center',
		resizeTabs: true, // turn on tab resizing
		minTabWidth: 80,
		TabWidth:100,
		enableTabScroll: true,
		activeTab: 0,
		autoScroll: true,
		plugins: new Ext.ux.TabCloseMenu(),
		items: [{
			title: '总页面类别信息',
			iconCls: 'icon-grid',
			tabTip: '总页面类别信息',
			autoWidth: true,
			frame: true,
			autoLoad: {
				url: 'tabFrame.jsp?url=' + 'pageRegion.jsp?params=',
				callback: this.initSearch,
				scope: this,
				text: "Loading..."
			}
		}]
	});
	
	
	var pageTree = new Ext.tree.TreePanel({
		region: 'west',
		collapsible: true,
		title: '页面选择',
		xtype: 'treepanel',
		width: 120,
		minWidth: 100,
		autoScroll: true,
		split: true,
		loader: new Ext.tree.TreeLoader({
			dataUrl: 'getPageByNode.shtml'
		}),
		root: new Ext.tree.AsyncTreeNode({
			expanded: true,
			id: '-1',
			text: '全分类',
			disabled: true,
			draggable: false
		}),
		rootVisible: false,
		listeners: {
			click: clickNode
		}
	});
	
	/*
	 * 为treeloader添加函数beforeload
	 */
	pageTree.on('beforeload', function(node){
		pageTree.loader.dataUrl = 'getPageByNode.shtml';
	});
	
	/*
	 * 布局加载
	 */
	new Ext.Viewport({
		layout: 'border',
		items: [pageTree, centerbody]
	});
}

function clickNode(item){
	updateTab(item.id, item.text, 'pageRegion.jsp?params=' + item.id );
}


function addTab(id, tabTitle, targetUrl){
	var sendTabTitle = encodeURIComponent(tabTitle);
	targetUrl+='@@@'+sendTabTitle;
	centerbody.add({
		id: id,
		title: tabTitle,
		iconCls: 'icon-grid',
		frame: true,
		tabTip: tabTitle,
		autoLoad: {
			url: "tabFrame.jsp?url=" + targetUrl,
			callback: this.initSearch,
			scope: this,
			text: "Loading..."
		},
		closable: true
	}).show();
}


// 更新tab内容，如不存在就新建一个
function updateTab(tabId, title, url){
	var tab = centerbody.findById(tabId);
	if (!tab) {
		tab = addTab(tabId, title, url);
	}
	centerbody.setActiveTab(tab);
}

	

