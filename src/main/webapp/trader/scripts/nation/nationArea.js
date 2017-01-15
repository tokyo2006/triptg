
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	/*==============================全局变量t=============================================*/
	var objectGrid;
	var selectRecord;
	var cbsm;
	var objectStore;
	var centerbody;
	initView();
});

var pageSize = 27;

/**
 * 
 */
function initView(){
		
	centerbody = new Ext.TabPanel({
		id: 'centerBody',
		region: 'center',
		activeTab: 0,
		resizeTabs: true, // turn on tab resizing
		minTabWidth: 100,
		deferredRender: false, //是否延迟加载
		enableTabScroll: true,
		plugins: new Ext.ux.TabCloseMenu(),
		
		items: initGrid()
	});
	
	new Ext.Viewport({
		layout: 'border',
		items: centerbody
	});
}

/**
 * 表格加载
 */
function initGrid(){
	var idName = new Array('name','headName','orderBy','ip','isChina','mapFocus');
	
	var areaPagingToolbar = Ext.extend(Ext.PagingToolbar, {
    	paramNames : {start: 'start', limit: 'limit',areaId: 'areaId'},
    	doLoad: function(start, areaId){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.areaId] = areaId;
			if (this.fireEvent('beforechange', this, o) !== false) {
				this.store.load({
					params: o
				});
			}
		},
		
		onClick: function(which){
			var store = this.store;
			switch (which) {
				case "first":
					this.doLoad(0, areaId);
					break;
				case "prev":
					this.doLoad(Math.max(0, this.cursor - this.pageSize), areaId);
					break;
				case "next":
					this.doLoad((this.cursor + this.pageSize), areaId);
					break;
				case "last":
					var total = store.getTotalCount();
					var extra = total % this.pageSize;
					var lastStart = extra ? (total - extra) : total - this.pageSize;
					this.doLoad(lastStart, areaId);
					break;
				case "refresh":
					this.doLoad(this.cursor, '');
					break;
			}
		}
	});
	
	var searchText = new Ext.form.TextField({
		id: 'searchT',
		name: 'searchT',
		width: 80,
		emptyText: '请输入名称'
	});
	
	/*
	 * 数据解析器
	 */
	var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'nationId',
        fields: [
            {name: 'nationId', mapping:'nationId'},
            {name: 'name', mapping:'area.name'},
			{name: 'areaId', mapping:'area.areaId'},
			{name: 'content', mapping:'content'},
			{name: 'synopsis', mapping:'synopsis'},
			{name: 'gloze', mapping:'gloze'},
			{name: 'hit', mapping:'hit'},
			{name: 'mapUrl', mapping:'mapUrl'},
			{name: 'mapTopic', mapping:'mapTopic'},
			{name: 'isTop', mapping:'isTop'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllNation.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'hit', direction: "DESC"}
        });
		
	objectStore.load({
		params: {
			areaId:areaId,
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
            {id:'nationId',header: '标识号', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'nationId'},
			{id:'areaId',header: '地域标识号', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'areaId'},
			{header: '名称', hideable:true, sortable: true, 
			dataIndex: 'name'},
			{header: '排序标识', hideable:true, sortable: true, 
			dataIndex: 'orderBy'},
			{header: '详细介绍', hideable:true, sortable: true, renderer:Ext.util.Format.longStringRenderer,
			dataIndex: 'content'},
			{header: '简介', hideable:true, sortable: true, renderer:Ext.util.Format.longStringRenderer,
			dataIndex: 'synopsis'},
			{header: '热点介绍', hideable:true, sortable: true, renderer:Ext.util.Format.longStringRenderer,
			dataIndex: 'mapTopic'},
			{header: '概况', hideable:true, sortable: true, renderer:Ext.util.Format.longStringRenderer,
			dataIndex: 'gloze'},
			{header: '地图', hideable:true, sortable: true, renderer:function(v,p,record){
				if(v===''||v===null){
					return '<font color=\"#ee928f\">无</font>';
				}
				else{
					return '有';
				}
			},
			dataIndex: 'mapUrl'},
			{header: '是否热点', hideable:true, sortable: true, renderer:function(v,p,record){
				if(parseInt(v)===1){
					return '是';
				}
				else{
					return '<font color=\"#ee928f\">否</font>';
				}
			},
			dataIndex: 'isTop'},
			{header: '点击数', hideable:true, sortable: true, 
			dataIndex: 'hit'}
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
			refreshText:'刷新国家信息',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新国家信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
		},'-', {
			text: '修改',
			tooltip: '修改国家信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		},  '-', {
			text: '查看',
			tooltip: '查看国家信息',
			iconCls: 'add',
			handler: function(){
				viewItem(objectGrid.getBottomToolbar().cursor);
			}
		},'-', '-',searchText, {
			text: '搜索',
			handler: function(){
				search(searchText,objectStore);
			}
		}],
		
		listeners:{
			'rowdblclick':function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		},
		
		frame: true,
		title:'国家信息表',
		region: 'center',
		height: 150,
		autoWidth: true,
		animCollapse: false,
		trackMouseOver: true
	});
	return objectGrid;
}

function updateItem(idName,start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的国家！');
	}
	else {
		if (cbsm.getCount() == 1) {			
			updateTab(selectRecord.get('nationId'),selectRecord.get('name'),'getSingleNation.shtml?nationId='+selectRecord.get('nationId'));
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条国家信息！');
		}
	}
}

function allRefresh(store){
	store.load({
		params: {
			areaId:areaId,
			start: 0,
			limit:pageSize
		}
	});
}

function search(searchText,objectStore){
	var name=searchText.getValue();
	if(name==='输入名称'){
		name='';
	}
	objectStore.load({
					params: {
						areaId:areaId,
						name:name,
						start: 0,
						limit: pageSize
					}
				});
}

function addTab(id, tabTitle, targetUrl){
	centerbody.add({
		id: id,
		title: tabTitle,
		iconCls: 'icon-grid',
		frame: true,
		tabTip: tabTitle,
		autoScroll:true,
		autoLoad: {
			url: 'tabFrameInner.jsp?url='+targetUrl,
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

function viewItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('查看操作', '请选择要查看的国家！');
	}
	else {
		if (cbsm.getCount() == 1) {
			window.open('../../destination/countryDetail/' + selectRecord.get('areaId')+'.html');
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条国家信息！');
		}
	}
}
