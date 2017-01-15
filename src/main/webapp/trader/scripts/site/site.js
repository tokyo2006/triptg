
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	/*==============================全局变量t=============================================*/
	var fpanel,tabPanel,content1Panel,content2Panel,content3Panel,content4Panel;
	var objectGrid;
	var selectRecord;
	var cbsm;
	var objectStore,pickStore;
	var zb = 1,gn=1,cj=1,addr=1;
	initView();
});

var pageSize =27;
var addWin,updWin,fileUpWin;
var areaId='';

/**
 * 
 */
function initView(){
		
	/*
	 * 布局加载
	 */
	new Ext.Viewport({
		layout: 'border',
		items: initGrid()
	});
}

/**
 * 表格加载
 */
function initGrid(){
	var idName = new Array('siteName','logo','title','address','zbphone','gnphone','cjphone','zjphone','areaId','domainName','bannerzb','bannergn','bannerzyx','bannercj','bannerLogo','desc','keywords','smallLogo');
	/*
	 * 数据解析器
	 */
	var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'siteId',
        fields: [
            {name: 'siteId', mapping:'siteId'},
			{name: 'siteName', mapping:'siteName'},
			{name: 'logo', mapping:'logo'},
			{name: 'desc', mapping:'desc'},
			{name: 'keywords', mapping:'keywords'},
			{name: 'title', mapping:'title'},
			{name: 'address', mapping:'address'},			
			{name: 'zbphone', mapping:'zbphone'},
			{name: 'gnphone', mapping:'gnphone'},
			{name: 'cjphone', mapping:'cjphone'},
			{name: 'zjphone', mapping:'zjphone'},
			{name: 'bannerzb', mapping:'bannerzb'},
			{name: 'bannergn', mapping:'bannergn'},
			{name: 'bannerzyx', mapping:'bannerzyx'},
			{name: 'bannercj', mapping:'bannercj'},
			{name: 'bannerLogo', mapping:'bannerLogo'},
			{name: 'smallLogo', mapping:'smallLogo'},
			{name: 'areaName', mapping:'area.name'},
			{name: 'domainName', mapping:'domainName'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllSite.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'siteName', direction: 'DESC'}
        });
		
	objectStore.load({
		params: {
			start: 0,
			limit: pageSize
		}
	});
	
	cbsm = new Ext.grid.CheckboxSelectionModel();
	var rn = new Ext.grid.RowNumberer();
	
	/*
	 * 第二tab面板数据源，dataurl的请求带参数
	 */
	objectGrid = new Ext.grid.GridPanel({
		loadMask: true,
		ds: objectStore,
		columns: [
		     rn, //行号列 
		     cbsm, 
            {id:'siteId',header: '标识号', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'siteId'},
			{header: '站点标识', hideable:true, sortable: true, 
			dataIndex: 'siteName'},
			{header: 'logo图片', hideable:true, sortable: true, renderer:function(v,p,record){
				if(v===''||v===null){
					return '<font color=\"#ee928f\">无</font>';
				}
				else{
					return '有';
				}
			}, 
			dataIndex: 'logo'},
			{header: '网站标题', hideable:true, sortable: true, 
			dataIndex: 'title'},
			{header: '页头描述', hideable:true, sortable: true, 
			dataIndex: 'desc'},
			{header: '页头关键字', hideable:true, sortable: true, 
			dataIndex: 'keywords'},
			{header: '报名地址', hideable:true, sortable: true, renderer: function(v,p,record){
				var addrStr;
				if (v !== null) {
					p.attr = 'ext:qtitle="' + "地址详情</br>" + '"';
					
					var addrArray = new Array();
					var addrContentArray = new Array();
					addrArray = v.split('@@@');
					
					var addrStr = '<table style=\'font-size:12px;\'><tr><td style=\'color:#9F9F9D;\'>地址</td><td style=\'color:#9F9F9D;\'>超链接</td></tr>';
					for (var i = 0; i < addrArray.length; i++) {
						addrContentArray = addrArray[i].split('###');
						addrStr += '<tr><td style=\'color:#9F9F9D;padding:5px 10px 5px 0;\'>' + addrContentArray[0] + '</td><td style=\'color:#9F9F9D;padding:5px 10px 5px 0;\'>' + addrContentArray[1] + '</td></tr>';
						addrContentArray.length = 0;
					}
					addrStr += '</table>';
					p.attr += ' ext:qtip="' + addrStr + '"';
					
					return '有'+addrArray.length+'个地址';
				}
				else{
					return '<font color=\"#ee928f\">无</font>';
				}
			},
			dataIndex: 'address'},
			{header: '周边电话',  hidden:true, hideable:false, sortable: true, 
			dataIndex: 'zbphone'},
			{header: '长线电话',  hidden:true, hideable:false, sortable: true, 
			dataIndex: 'gnphone'},
			{header: '出境电话',  hidden:true, hideable:false, sortable: true, 
			dataIndex: 'cjphone'},
			{header: '总机电话', hideable:true, sortable: true, 
			dataIndex: 'zjphone'},
			{header: '周边游图片', hideable:true, sortable: true, renderer:function(v,p,record){
				if(v===''||v===null){
					return '<font color=\"#ee928f\">无</font>';
				}
				else{
					return '有';
				}
			}, 
			dataIndex: 'bannerzb'},
			{header: '国内游图片', hideable:true, sortable: true, renderer:function(v,p,record){
				if(v===''||v===null){
					return '<font color=\"#ee928f\">无</font>';
				}
				else{
					return '有';
				}
			}, 
			dataIndex: 'bannergn'},
			{header: '自由行图片', hideable:true, sortable: true, renderer:function(v,p,record){
				if(v===''||v===null){
					return '<font color=\"#ee928f\">无</font>';
				}
				else{
					return '有';
				}
			}, 
			dataIndex: 'bannerzyx'},
			{header: '出境游图片', hideable:true, sortable: true, renderer:function(v,p,record){
				if(v===''||v===null){
					return '<font color=\"#ee928f\">无</font>';
				}
				else{
					return '有';
				}
			}, 
			dataIndex: 'bannercj'},
			{header: 'banner小图', hideable:true, sortable: true, renderer:function(v,p,record){
				if(v===''||v===null){
					return '<font color=\"#ee928f\">无</font>';
				}
				else{
					return '有';
				}
			}, 
			dataIndex: 'bannerLogo'},
			{header: 'logo小图', hideable:true, sortable: true, renderer:function(v,p,record){
				if(v===''||v===null){
					return '<font color=\"#ee928f\">无</font>';
				}
				else{
					return '有';
				}
			}, 
			dataIndex: 'smallLogo'},
			{header: '地域', hideable:true, sortable: true, 
			dataIndex: 'areaName'},
			{header: '网址域名', hideable:true, sortable: true, 
			dataIndex: 'domainName'}
        ],
		sm: cbsm,
		
		view: new Ext.grid.GroupingView({
			forceFit: true,
			showGroupName: false,
			enableNoGroups: true, // REQUIRED!
			hideGroupedColumn: false,
			groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "条" : "条"]})'
		}),
		
		bbar: new Ext.PagingToolbar({
			pageSize: pageSize,
			store: objectStore,
			displayInfo: true,
			displayMsg: '显示 {0}-{1}条 / 共 {2} 条',
			refreshText:'刷新网站信息',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新网站信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
			text: '添加',
			tooltip: '添加网站信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		},'-', {
			text: '修改',
			tooltip: '修改网站信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '删除',
			tooltip: '删除网站信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}		
		}],
		
		frame: true,
//		title:'网站信息表',
		region: 'center',
		height: 150,
		autoWidth: true,
		animCollapse: false,
		trackMouseOver: true
	});
	return objectGrid;
}

function comCreate(idName,actions,start){
	var attributePanel,areaPanel,bannerPanel;
	
	attributePanel = new Ext.Panel({
		title:'网站属性',
		id: 'attrP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 300,
		defaults: {
			xtype: 'textfield',
			width: 230,
			allowBlank: false,
			anchor: '95%'
		},
		
		items: [{
			fieldLabel: '站点标示',
			name: idName[0],
			id: idName[0]
		},{
			xtype: 'panel',
			id: 'columnP0',
			layout: 'column',
			defaults: {
				xtype: 'panel',
				height: 24,
				border: false
			},
			border: false,
			height: 25,
			bodyStyle: 'padding:0 0 0 0; margin:0 0 0 0',
			items: [{
				columnWidth: .21,
				html: '<font style:\"font-size:24px;\">logo图片:</font>'
			}, {
				columnWidth: .64,
				items: [{
					xtype: 'textfield',
					width: 320,
//					allowBlank: false,
					disabled: true,
					anchor: '95%',
					name: idName[1],
					id: idName[1]
				}]
			}, {
				columnWidth: .15,
				items: [{
					xtype: 'button',
					text: '文件上传',
					name: 'clickBtn',
					id: 'clickBtn',
					handler: function(){
						fileUp(0)
					}
				}]
			}]
		},{
			fieldLabel: '网站标题',
			name: idName[2],
			id: idName[2]
		},{
			fieldLabel: '总机电话',
			allowBlank: true,
			name: idName[7],
			id: idName[7]
		},{
			fieldLabel: '网址域名',
			allowBlank: true,
			xtype:'textarea',
			name: idName[9],
			id: idName[9]
		},{
			fieldLabel: '页头描述',
			xtype:'textarea',
			name: idName[15],
			id: idName[15]
		},{
			fieldLabel: '页头关键字',
			xtype:'textarea',
			name: idName[16],
			id: idName[16]
		}]
	});
	
	bannerPanel=new Ext.Panel({
		title:'banner图片',
		id: 'bannerP',
		renderTo:'bannerDiv',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 300,
		defaults: {
			width: 540,
			xtype: 'panel'
		},
		
		items: [{
			xtype: 'panel',
			id: 'columnP1',
			layout: 'column',
			defaults: {
				xtype: 'panel',
				height: 24,
				border: false
			},
			border: false,
			height: 25,
			bodyStyle: 'padding:0 0 0 0; margin:0 0 0 0',
			items: [{
				columnWidth: .21,
				html: '<font style:\"font-size:24px;\">周边游图片:</font>'
			}, {
				columnWidth: .64,
				items: [{
					xtype: 'textfield',
					width: 320,
//					allowBlank: false,
					disabled: true,
					anchor: '95%',
					name: idName[10],
					id: idName[10]
				}]
			}, {
				columnWidth: .15,
				items: [{
					xtype: 'button',
					text: '文件上传',
					name: 'clickBtn',
					id: 'clickBtn',
					handler: function(){
						fileUp(1)
					}
				}]
			}]
		},{
			xtype: 'panel',
			id: 'columnP2',
			layout: 'column',
			defaults: {
				xtype: 'panel',
				height: 24,
				border: false
			},
			border: false,
			height: 25,
			bodyStyle: 'padding:0 0 0 0; margin:0 0 0 0',
			items: [{
				columnWidth: .21,
				html: '<font style:\"font-size:24px;\">国内游图片:</font>'
			}, {
				columnWidth: .64,
				items: [{
					xtype: 'textfield',
					width: 320,
//					allowBlank: false,
					disabled: true,
					anchor: '95%',
					name: idName[11],
					id: idName[11]
				}]
			}, {
				columnWidth: .15,
				items: [{
					xtype: 'button',
					text: '文件上传',
					name: 'clickBtn',
					id: 'clickBtn',
					handler: function(){
						fileUp(2)
					}
				}]
			}]
		},{
			xtype: 'panel',
			id: 'columnP3',
			layout: 'column',
			defaults: {
				xtype: 'panel',
				height: 24,
				border: false
			},
			border: false,
			height: 25,
			bodyStyle: 'padding:0 0 0 0; margin:0 0 0 0',
			items: [{
				columnWidth: .21,
				html: '<font style:\"font-size:24px;\">自由行图片:</font>'
			}, {
				columnWidth: .64,
				items: [{
					xtype: 'textfield',
					width: 320,
//					allowBlank: false,
					disabled: true,
					anchor: '95%',
					name: idName[12],
					id: idName[12]
				}]
			}, {
				columnWidth: .15,
				items: [{
					xtype: 'button',
					text: '文件上传',
					name: 'clickBtn',
					id: 'clickBtn',
					handler: function(){
						fileUp(3)
					}
				}]
			}]
		},{
			xtype: 'panel',
			id: 'columnP4',
			layout: 'column',
			defaults: {
				xtype: 'panel',
				height: 24,
				border: false
			},
			border: false,
			height: 25,
			bodyStyle: 'padding:0 0 0 0; margin:0 0 0 0',
			items: [{
				columnWidth: .21,
				html: '<font style:\"font-size:24px;\">出境游图片:</font>'
			}, {
				columnWidth: .64,
				items: [{
					xtype: 'textfield',
					width: 320,
//					allowBlank: false,
					disabled: true,
					anchor: '95%',
					name: idName[13],
					id: idName[13]
				}]
			}, {
				columnWidth: .15,
				items: [{
					xtype: 'button',
					text: '文件上传',
					name: 'clickBtn',
					id: 'clickBtn',
					handler: function(){
						fileUp(4)
					}
				}]
			}]
		},{
			xtype: 'panel',
			id: 'columnP5',
			layout: 'column',
			defaults: {
				xtype: 'panel',
				height: 24,
				border: false
			},
			border: false,
			height: 25,
			bodyStyle: 'padding:0 0 0 0; margin:0 0 0 0',
			items: [{
				columnWidth: .21,
				html: '<font style:\"font-size:24px;\">banner小图:</font>'
			}, {
				columnWidth: .64,
				items: [{
					xtype: 'textfield',
					width: 320,
//					allowBlank: false,
					disabled: true,
					anchor: '95%',
					name: idName[14],
					id: idName[14]
				}]
			}, {
				columnWidth: .15,
				items: [{
					xtype: 'button',
					text: '文件上传',
					name: 'clickBtn',
					id: 'clickBtn',
					handler: function(){
						fileUp(5)
					}
				}]
			}]
		},{
			xtype: 'panel',
			id: 'columnP6',
			layout: 'column',
			defaults: {
				xtype: 'panel',
				height: 24,
				border: false
			},
			border: false,
			height: 25,
			bodyStyle: 'padding:0 0 0 0; margin:0 0 0 0',
			items: [{
				columnWidth: .21,
				html: '<font style:\"font-size:24px;\">logo小图:</font>'
			}, {
				columnWidth: .64,
				items: [{
					xtype: 'textfield',
					width: 320,
//					allowBlank: false,
					disabled: true,
					anchor: '95%',
					name: idName[17],
					id: idName[17]
				}]
			}, {
				columnWidth: .15,
				items: [{
					xtype: 'button',
					text: '文件上传',
					name: 'clickBtn',
					id: 'clickBtn',
					handler: function(){
						fileUp(6)
					}
				}]
			}]
		}]
	});
	
	areaPanel = new Ext.Panel({
		title: '区域选择',
		id: 'areaP',
		height: 280,
		layout: 'column',
		bodyStyle: 'padding:10px',
		autoHeight: true,
		autoScroll: true,
		collapsible: true,
		items: [{
			collapsible: true,
			title: '区域选择',
			xtype: 'treepanel',
			columnWidth: .4,
			bodyStyle: 'padding:3px,margin-right:5px',
			minWidth: 80,
			height: 280,
			autoScroll: true,
			split: true,
			loader: new Ext.tree.TreeLoader({
				dataUrl: 'getAreaByNode.shtml'
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
				click: function(n){
					clickAreaNode(n);
				},
				beforeload: function(node){
					this.loader.dataUrl = 'getAreaByNode.shtml';
				}
			}
		}, {
			xtype: 'panel',
			columnWidth: .02,
			border: false
		},{
			xtype: 'panel',
			id:'areaIdP',
			title: '区域类型',
			bodyStyle: 'padding:10px 0 0 10px;',
			columnWidth: .58,
			tbar: [{
				text: '删除所属区域',
				tooltip: '删除所属区域',
				iconCls: 'remove',
				handler: function(){
					var temp = Ext.getCmp('areaIdP');
					temp.body.update('未选择');
					areaId = '';
				}
			}],
			
			html:'未选择',
			collapsed: false,
			height: 280
			
		}]
	});
	
	content1Panel = new Ext.Panel({
		title: '周边电话',
		id: 'content1P',
		layout: 'form',
		bodyStyle: 'padding:10px',
		height: 300,
		autoScroll: true,
		
		tbar: [{
			text: '添加电话',
			tooltip: '添加一个周边电话',
			handler: function(){
				addContent1();
			},
			iconCls: 'add'
		}, '-', {
			text: '删除电话',
			tooltip: '删除一个已有周边电话',
			handler: function(){
				delContent1();
			},
			iconCls: 'remove'
		}]
	});
	
	content2Panel = new Ext.Panel({
		title: '长线电话',
		id: 'content2P',
		layout: 'form',
		bodyStyle: 'padding:10px',
		height: 300,
		autoScroll: true,
		
		tbar: [{
			text: '添加电话',
			tooltip: '添加一个长线电话',
			handler: function(){
				addContent2();
			},
			iconCls: 'add'
		}, '-', {
			text: '删除电话',
			tooltip: '删除一个已有长线电话',
			handler: function(){
				delContent2();
			},
			iconCls: 'remove'
		}]
	});
	
	content3Panel = new Ext.Panel({
		title: '出境电话',
		id: 'content3P',
		layout: 'form',
		bodyStyle: 'padding:10px',
		height: 300,
		autoScroll: true,
		
		tbar: [{
			text: '添加电话',
			tooltip: '添加一个出境电话',
			handler: function(){
				addContent3();
			},
			iconCls: 'add'
		}, '-', {
			text: '删除电话',
			tooltip: '删除一个已有出境电话',
			handler: function(){
				delContent3();
			},
			iconCls: 'remove'
		}]
	});
	
	content4Panel = new Ext.Panel({
		title: '报名地址',
		id: 'content4P',
		layout: 'form',
		bodyStyle: 'padding:10px',
		height: 300,
		autoScroll: true,
		
		tbar: [{
			text: '添加地址',
			tooltip: '添加一个报名地址',
			handler: function(){
				addContent4();
			},
			iconCls: 'add'
		}, '-', {
			text: '删除地址',
			tooltip: '删除一个已有报名地址',
			handler: function(){
				delContent4();
			},
			iconCls: 'remove'
		}]
	});
	
	tabPanel = new Ext.TabPanel({
		activeTab: 0,
		autoHeight: true,
		items: [attributePanel,bannerPanel,areaPanel,content4Panel,content1Panel,content2Panel,content3Panel]
	});
	fpanel = new Ext.FormPanel({
		region: 'center',
		bodyStyle: 'padding:5px',
		collapsible: true,
		items: [tabPanel],
		
		buttons: [{
			text: '保存',
			handler: function(){
				save(fpanel,start);
			}
		}, {
			text: '重置',
			handler: function(){
				reset(fpanel,idName);
			}
		}, {
			text: '取消',
			handler: function(){
				cancel();
			}
		}]
	});
	
	if(actions!=='add'){
		updateAjax(fpanel,tabPanel,actions,idName);
	}
}

function clickAreaNode(item){
	var temp = Ext.getCmp('areaIdP');
	if(areaId!==''){
		Ext.Msg.alert('提示','所属区域只能选择一个，请先删除再修改！');
	}
	else{
		areaId=item.id;
		temp.body.update(item.text);
	}
}

function updateAjax(fpanel,tabPanel,Id,idName){
	Ext.Ajax.request({
		url: 'getSingleSite.shtml',
		method: 'POST',
		params: {siteId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,tabPanel,idName);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载网站信息失败！');
			window.close();
		}
	});
}

function initUpdate(result,fpanel,tabPanel,idName){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.results[0];
		
		fpanel.findById(idName[0]).setValue(list.siteName);
		fpanel.findById(idName[1]).setValue(list.logo);
		fpanel.findById(idName[2]).setValue(list.title);
		fpanel.findById(idName[7]).setValue(list.zjphone);
		fpanel.findById(idName[9]).setValue(list.domainName);
		fpanel.findById(idName[15]).setValue(list.desc);
		fpanel.findById(idName[16]).setValue(list.keywords);
		
		tabPanel.setActiveTab(fpanel.findById('bannerP'));
		fpanel.findById(idName[10]).setValue(list.bannerzb);
		fpanel.findById(idName[11]).setValue(list.bannergn);
		fpanel.findById(idName[12]).setValue(list.bannerzyx);
		fpanel.findById(idName[13]).setValue(list.bannercj);
		fpanel.findById(idName[14]).setValue(list.bannerLogo);
		fpanel.findById(idName[17]).setValue(list.smallLogo);
		
		tabPanel.setActiveTab(fpanel.findById('areaP'));
		if (list.area) {
			var temp = Ext.getCmp('areaIdP');
			temp.body.update(list.area.name);
			areaId = list.area.areaId;
		}
		
		var ContentList = new Array();
		var Content = new Array();
		if (list.zbphone) {
			ContentList = list.zbphone.split('@@@');
			if (ContentList.length > 0) {
				for (var i = 1; i <= ContentList.length; i++) {
					Content = ContentList[i-1].split('###');
					zb = i;
					addContent1();
					content1Panel.findById('zbT' + i).setValue(Content[0]);
					content1Panel.findById('zbD' + i).setValue(Content[1]);
				}
			}
		}
		
		ContentList.length = 0;
		Content.length = 0;
		if (list.gnphone) {
			ContentList = list.gnphone.split('@@@');
			if (ContentList.length > 0) {
				for (var j = 1; j <= ContentList.length; j++) {
					Content = ContentList[j - 1].split('###');
					gn = j;
					addContent2();
					content2Panel.findById('gnT' + j).setValue(Content[0]);
					content2Panel.findById('gnD' + j).setValue(Content[1]);
				}
			}
		}
		
		ContentList.length = 0;
		Content.length = 0;
		if (list.cjphone) {
			ContentList = list.cjphone.split('@@@');
			if (ContentList.length > 0) {
				for (var p = 1; p <= ContentList.length; p++) {
					Content = ContentList[p - 1].split('###');
					cj = p;
					addContent3();
					content3Panel.findById('cjT' + p).setValue(Content[0]);
					content3Panel.findById('cjD' + p).setValue(Content[1]);
				}
			}
		}
		
		ContentList.length = 0;
		Content.length = 0;
		if (list.address) {
			ContentList = list.address.split('@@@');
			if (ContentList.length > 0) {
				for (var m = 1; m<= ContentList.length; m++) {
					Content = ContentList[m - 1].split('###');
					addr = m;
					addContent4();
					content4Panel.findById('addrT' + m).setValue(Content[0]);
					content4Panel.findById('addrD' + m).setValue(Content[1]);
				}
			}
		}
	}	
}

function save(fpanel,start){
	var urlPost;
	var paramsPost;
	var checkValue=true;
	
	var content1P = Ext.getCmp('content1P');
	var FSArray = content1P.findByType('fieldset');
	var FSNumber = FSArray.length;
	var contentBody = new Array();
	var FSTemp,FTArrayStr;
	var FTArray = new Array();
	for(var j=1;j<=FSNumber;j++){
		FSTemp=content1P.findById(j+'zbphone');
		FTArray[0]=FSTemp.findById('zbT'+j).getValue();
		FTArray[1]=FSTemp.findById('zbD'+j).getValue();
		if(FTArray[0]===''){
			checkValue=false;
			break;
		};
		FTArrayStr= FTArray.join('###');
		contentBody[j-1] = FTArrayStr;
	}
	var content1 = contentBody.join('@@@');
	
	var content2P=Ext.getCmp('content2P');
	FSArray.length=0;
	FSArray=content2P.findByType('fieldset');
	FSNumber=FSArray.length;
	contentBody.length=0;
	FTArray.length=0;
	for(var k=1;k<=FSNumber;k++){
		FSTemp=content2P.findById(k+'gnphone');
		FTArray[0]=FSTemp.findById('gnT'+k).getValue();
		FTArray[1]=FSTemp.findById('gnD'+k).getValue();
		if(FTArray[0]===''){
			checkValue=false;
			break;
		};
		FTArrayStr= FTArray.join('###');
		contentBody[k-1] = FTArrayStr;
	}
	var content2 = contentBody.join('@@@');
	
	var content3P=Ext.getCmp('content3P');
	FSArray.length=0;
	FSArray=content3P.findByType('fieldset');
	FSNumber=FSArray.length;
	contentBody.length=0;
	FTArray.length=0;
	for(var p=1;p<=FSNumber;p++){
		FSTemp=content3P.findById(p+'cjphone');
		FTArray[0]=FSTemp.findById('cjT'+p).getValue();
		FTArray[1]=FSTemp.findById('cjD'+p).getValue();
		if(FTArray[0]===''){
			checkValue=false;
			break;
		};
		FTArrayStr= FTArray.join('###');
		contentBody[p-1] = FTArrayStr;
	}
	var content3 = contentBody.join('@@@');
	
	var content4P=Ext.getCmp('content4P');
	FSArray.length=0;
	FSArray=content4P.findByType('fieldset');
	FSNumber=FSArray.length;
	contentBody.length=0;
	FTArray.length=0;
	for(var m=1;m<=FSNumber;m++){
		FSTemp=content4P.findById(m+'address');
		FTArray[0]=FSTemp.findById('addrT'+m).getValue();
		FTArray[1]=FSTemp.findById('addrD'+m).getValue();
		if(FTArray[0]===''){
			checkValue=false;
			break;
		};
		FTArrayStr= FTArray.join('###');
		contentBody[m-1] = FTArrayStr;
	}
	var content4 = contentBody.join('@@@');
	
	fpanel.findById('logo').enable();
	fpanel.findById('bannerzb').enable();
	fpanel.findById('bannergn').enable();
	fpanel.findById('bannerzyx').enable();
	fpanel.findById('bannercj').enable();
	fpanel.findById('bannerLogo').enable();
	fpanel.findById('smallLogo').enable();
	
	if (addWin) {
		urlPost = 'addSite.shtml';
		paramsPost = {
			areaId:areaId,
			zbphone:content1,
			gnphone:content2,
			cjphone:content3,
			address:content4
		};
	}
	else 
		if (updWin) {
			urlPost = 'updateSite.shtml';
			paramsPost = {
				zbphone: content1,
				gnphone: content2,
				cjphone: content3,
				address:content4,
				areaId:areaId,
				siteId: selectRecord.get('siteId')
			};
		}
		else {
			Ext.Msg.alert('信息', '提交窗口错误参数!');
			window.close();
		}
	
	//提交数据
	if (fpanel.form.isValid()&&checkValue) {
		this.disabled = true;
		
		fpanel.getForm().submit({
			url: urlPost,
			method: 'post',
			params: paramsPost,
			waitTitle: '请稍候',
			waitMsg: '信息正提交，请等待......',
			failure: function(fpanel, action){
				Ext.MessageBox.alert('保存失败', '保存信息失败！');
				this.disabled = false;
				fpanel.findById('logo').disable();
				fpanel.findById('bannerzb').disable();
				fpanel.findById('bannergn').disable();
				fpanel.findById('bannerzyx').disable();
				fpanel.findById('bannercj').disable();
				fpanel.findById('bannerLogo').disable();
				fpanel.findById('smallLogo').disable();
			},
			success: function(fpanel, action){
			
				if (action.result === undefined) {
					Ext.MessageBox.alert('保存失败', '后台服务器错误!');
					this.disabled = false;
					fpanel.findById('logo').disable();
					fpanel.findById('bannerzb').disable();
					fpanel.findById('bannergn').disable();
					fpanel.findById('bannerzyx').disable();
					fpanel.findById('bannercj').disable();
					fpanel.findById('bannerLogo').disable();
					fpanel.findById('smallLogo').disable();
				}
				else {
					var returnJosn = action.result;
					if (returnJosn.success === true) {
						Ext.MessageBox.alert('保存成功', '信息保存成功');
						if (addWin) {
							addWin.close();
							addWin = '';
						}
						if (updWin) {
							updWin.close();
							updWin = '';
						}
						areaId='';
						objectStore.load({
							params: {
								start: start,
								limit: pageSize
							}
						});
					}
					else {
						Ext.MessageBox.alert('保存失败', returnJosn.msg);
						this.disabled = false;
					}
				}
			}
		});
	}
	else {
		fpanel.findById('logo').disable();
		fpanel.findById('bannerzb').disable();
		fpanel.findById('bannergn').disable();
		fpanel.findById('bannerzyx').disable();
		fpanel.findById('bannercj').disable();
		fpanel.findById('bannerLogo').disable();
		fpanel.findById('smallLogo').disable();
		Ext.Msg.alert('信息', '请填写完成再提交!');
	}
}

function reset(fpanel,idName){
	fpanel.form.reset();
}

function cancel(){
	addWin = Ext.getCmp('addW');
	updWin = Ext.getCmp('updW');
	
	if (addWin) {
		addWin.close();
		addWin='';
	}
	if (updWin) {
		updWin.close();
		updWin='';
	}
	areaId='';
}

function addContent1(){
	content1Panel.add({
		xtype: 'fieldset',
		title: '第'+zb + '个电话',
		id: zb + 'zbphone',
		defaults: {
			xtype: 'textfield',
			width: 230,
			anchor: '95%'
		},
		autoHeight: true,
		collapsed: false, //已经折叠否
		items: [{
			fieldLabel: '电话',
			allowBlank: false,
			name: 'zbT' + zb,
			id: 'zbT' + zb
		},{
			fieldLabel: '描述',
			name: 'zbD' + zb,
			id: 'zbD' + zb
		}]	
	});
	zb++;
	content1Panel.show();
	content1Panel.doLayout();
}

function delContent1(){
	if (zb > 1) {
		var tempfieldset = content1Panel.findById(--zb + 'zbphone');
		content1Panel.remove(tempfieldset);
	}
}

function addContent2(){
	content2Panel.add({
		xtype: 'fieldset',
		title: '第'+gn + '个电话',
		id: gn + 'gnphone',
		defaults: {
			xtype: 'textfield',
			width: 230,
			anchor: '95%'
		},
		autoHeight: true,
		collapsed: false, //已经折叠否
		items: [{
			fieldLabel: '电话',
			allowBlank: false,
			name: 'gnT' + gn,
			id: 'gnT' + gn
		},{
			fieldLabel: '描述',
			name: 'gnD' + gn,
			id: 'gnD' + gn
		}]		
	});
	gn++;
	content2Panel.show();
	content2Panel.doLayout();
}

function delContent2(){
	if (gn > 1) {
		var tempfieldset = content2Panel.findById(--gn + 'gnphone');
		content2Panel.remove(tempfieldset);
	}
}

function addContent3(){
	content3Panel.add({
		xtype: 'fieldset',
		title: '第'+cj + '个电话',
		id: cj + 'cjphone',
		defaults: {
			xtype: 'textfield',
			width: 230,
			anchor: '95%'
		},
		autoHeight: true,
		collapsed: false, //已经折叠否
		items: [{
			fieldLabel: '电话',
			allowBlank: false,
			name: 'cjT' + cj,
			id: 'cjT' + cj
		},{
			fieldLabel: '描述',
			name: 'cjD' + cj,
			id: 'cjD' + cj
		}]		
	});
	cj++;	
	content3Panel.show();
	content3Panel.doLayout();
}

function delContent3(){
	if (cj > 1) {
		var tempfieldset = content3Panel.findById(--cj + 'cjphone');
		content3Panel.remove(tempfieldset);
	}
}

function addContent4(){
	content4Panel.add({
		xtype: 'fieldset',
		title: '第'+addr + '个报名地址',
		id: addr + 'address',
		defaults: {
			xtype: 'textfield',
			width: 230,
			anchor: '95%'
		},
		autoHeight: true,
		collapsed: false, //已经折叠否
		items: [{
			fieldLabel: '地址',
			allowBlank: false,
			name: 'addrT' + addr,
			id: 'addrT' + addr
		},{
			fieldLabel: '超链接',
			name: 'addrD' + addr,
			id: 'addrD' + addr
		}]		
	});
	addr++;	
	content4Panel.show();
	content4Panel.doLayout();
}

function delContent4(){
	if (addr > 1) {
		var tempfieldset = content4Panel.findById(--addr + 'address');
		content4Panel.remove(tempfieldset);
	}
}

function addItem(idName){
	addWin = Ext.getCmp('addW');
	
	zb = 1,gn=1,cj=1,addr=1;
	if (!addWin) {
		comCreate(idName,'add','');
		addWin = new Ext.Window({
			title: '添加网站信息',
			id: 'addW',
			closable: true,
			width: 580,
			height: 410,
			modal: true,
			layout: 'fit',
			items: [fpanel]
		});
	}
	addWin.show();
}

function updateItem(idName,start){
	zb = 1,gn=1,cj=1,addr=1;
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的网站！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('siteId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改网站信息',
					id: 'updW',
					closable: true,
					width: 580,
					height: 410,
					plain: true,
					modal: true,
					layout: 'fit',
					items: [fpanel]
				});
			}
			updWin.show();
			updWin.doLayout();
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条网站信息！');
		}
	}
}

function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('网站信息删除操作', '未选择，请选择要删除的网站信息！');
	}
	else {
		Ext.MessageBox.confirm('确认网站删除', '你确认网站删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('siteId');
					if (i === 0) {
						jsonData = jsonData + ss;
					}
					else {
						jsonData = jsonData + "," + ss;
					}
					objectStore.remove(m[i]);
				}				
			delAjax(start,jsonData);				
			}
		});
	}
}

function delAjax(start,jsonData){
	Ext.Ajax.request({
		url: 'delSite.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('网站信息删除操作', '删除网站信息成功！');
			}
			else
			{
				Ext.MessageBox.alert('删除操作', returnJosn.msg);
				objectStore.load({
					params: {
						start: start,
						limit: pageSize
					}
				});
			}
		},
		failure: function(result, request){
			Ext.MessageBox.alert('网站信息删除操作', '删除网站信息失败！');
			objectStore.load({
					params: {
						start: start,
						limit: pageSize,
						delData: jsonData
					}
				});
		}
	});
}

function allRefresh(store){
	store.load({
		params: {
			start: 0,
			limit:pageSize
		}
	});
}

function fileUp(type){
	
	fpanel2 = new Ext.FormPanel({
		region: 'center',
		height:100,
		fileUpload: true,
		bodyStyle: 'padding:5px',
		collapsible: true,
		defaults: {
				xtype: 'textfield',
				width: 230,
				allowBlank: false,
				anchor: '95%'
			},
		items: [{
			fieldLabel: '文件路径',
				name: 'upload',
				inputType: 'file',
				id: 'fileUpId'
		}],
		
		buttons: [{
			text: '保存',
			handler:function(){
				fileUpSave(fpanel2,type);
			} 
		}, {
			text: '取消',
			handler: fileUpCancel
		}]
	});
	
	if (!fileUpWin) {
				fileUpWin = new Ext.Window({
					title: '文件上传信息',
					id: 'fileUpW',
					closable: true,
					width: 580,
					height: 120,
					plain: true,
					modal: true,
					layout: 'fit',
					items: [fpanel2]
				});
			}
			fileUpWin.show();
			fileUpWin.doLayout();
}

function fileUpSave(fpanel, type){
	var urlPost;
	var paramsPost;
	var oldUrl = '';
	if (type === 0) {
		oldUrl = Ext.getCmp('logo').getValue();
	}
	else if(type === 1){
		oldUrl = Ext.getCmp('bannerzb').getValue();
	}
	else if(type === 2){
		oldUrl = Ext.getCmp('bannergn').getValue();
	}
	else if(type === 3){
		oldUrl = Ext.getCmp('bannerzyx').getValue();
	}
	else if(type === 4){
		oldUrl = Ext.getCmp('bannercj').getValue();
	}
	else if(type === 5){
		oldUrl = Ext.getCmp('bannerLogo').getValue();
	}
	else if(type === 6){
		oldUrl = Ext.getCmp('smallLogo').getValue();
	}
	if (fileUpWin) {
		urlPost = 'uploadPic.shtml';
		paramsPost = {
			logo: oldUrl
		};
	}
	else {
		Ext.Msg.alert('信息', '提交窗口错误参数!');
		window.close();
	}
	
	//提交数据
	if (fpanel.form.isValid()) {
		this.disabled = true;
		
		fpanel.getForm().submit({
			url: urlPost,
			method: 'post',
			params: paramsPost,
			waitTitle: '请稍候',
			waitMsg: '信息正提交，请等待......',
			failure: function(fpanel, action){
				Ext.MessageBox.alert('保存失败', '信息保存未成功！');
				this.disabled = false;
			},
			success: function(fpanel, action){
			
				if (action.result === undefined) {
					Ext.MessageBox.alert('保存失败', '后台服务器错误!');
					this.disabled = false;
				}
				else {
					var returnJosn = action.result;
					if (returnJosn.success === true) {
						if (fileUpWin) {
							fileUpWin.close();
							fileUpWin = '';
						}
						if (type === 0) {
							Ext.getCmp('logo').setValue(returnJosn.logo);
						}
						else if(type === 1){
							Ext.getCmp('bannerzb').setValue(returnJosn.logo);
						}
						else if(type === 2){
							Ext.getCmp('bannergn').setValue(returnJosn.logo);
						}
						else if(type === 3){
							Ext.getCmp('bannerzyx').setValue(returnJosn.logo);
						}
						else if(type === 4){
							Ext.getCmp('bannercj').setValue(returnJosn.logo);
						}
						else if(type === 5){
							Ext.getCmp('bannerLogo').setValue(returnJosn.logo);
						}
						else if(type === 6){
							Ext.getCmp('smallLogo').setValue(returnJosn.logo);
						}
						Ext.MessageBox.alert('保存成功', '信息保存成功');
					}
					else {
						Ext.MessageBox.alert('保存失败', returnJosn.msg);
						this.disabled = false;
					}
				}
			}
		});
	}
	else {
		Ext.Msg.alert('信息', '请选择本地图片!');
	}
}

function fileUpCancel(){
	fileUpWin = Ext.getCmp('fileUpW');
	
	if (fileUpWin) {
		fileUpWin.close();
		fileUpWin='';
	}
}