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
			title: '总类别信息表',
			iconCls: 'icon-grid',
			tabTip: '总类别信息',
			autoWidth: true,
			frame: true,
			autoLoad: {
				url: 'tabFrame.jsp?url=' + 'region.jsp?params=',
				callback: this.initSearch,
				scope: this,
				text: "Loading..."
			}
		}]
	});
	
	var regionTree = new Ext.tree.TreePanel({
		region: 'west',
		collapsible: true,
		title: '类别选择',
		xtype: 'treepanel',
		width: 140,
		minWidth: 100,
		autoScroll: true,
		split: true,
		loader: new Ext.tree.TreeLoader({
			dataUrl: 'getRegionByNode.shtml'
		}),
		root: new Ext.tree.AsyncTreeNode({
			expanded: true,
			id: '402880e71a7fabda011a7faf12fa0001',
			text: '全分类',
			disabled: true,
			draggable: false
		}),
//		rootVisible: false,
		listeners: {
			click: function(n){
				if (n.getDepth()<4) {
					clickNode(n);
					n.expand();
				}
			}
		}
	});
	
	regionTree.on('beforeload', function(node){
		regionTree.loader.dataUrl = 'getRegionByNode.shtml';
	});
	
	/*
	 * 布局加载
	 */
	new Ext.Viewport({
		layout: 'border',
		items: [regionTree, centerbody]
	});
}

function clickNode(item){
	updateTab(item.id, item.text, 'region.jsp?params=' + item.id );
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

	

