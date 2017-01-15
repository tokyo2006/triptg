
Ext.onReady(function(){
	//get All unit Name for comboBox.
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	/*==============================全局变量t=============================================*/
	var id = 1; //行程内容id
	var contentPanel, fpanel;
	var objectGrid;
	var addWin, updWin;
	var selectRecord;
	var cbsm;
	var objectStore;
	
	initView(pageSize);
});

var pageSize = 15;
var addWin,updWin;
var lineId;
var themeBoxstore;

/*==============================中间新tabpanel中包含树的west=============================================*/
function initView(pageSize){
//	var titleString = '';
	
	objectGrid = initGrid(pageSize, node);
	
	/*
	 * 布局加载
	 */
	new Ext.Viewport({
		layout: 'border',
		items: [objectGrid]
	});
}




/**
 * 表格加载
 */
function initGrid(pageSize, node){
	var idName = new Array('title', 'subTitle', 'feeClude', 'feeUnclude', 'remark', 'journey', 'feature', 'purchase', 'selfBuy', 'safe');

	MyPagingToolbar = Ext.extend(Ext.PagingToolbar, {
    	paramNames : {start: 'start', limit: 'limit',node: 'node'},
    	doLoad: function(start){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.node] = node;
			this.store.load({
				params: o
			});
		}
	});
	
	/*
	 * 数据解析器
	 */
	var objectReader = new Ext.data.JsonReader({
		totalProperty: 'totalCount',
		root: 'results',
		successProperty: 'success',
		idProperty: 'lineId',
		fields: [{
			name: 'lineId',
			type: 'string',
			mapping: 'lineId'
		}, {
			name: 'lineStar',
			type: 'int',
			mapping: 'lineStar'
		}, {
			name: 'title',
			type: 'string',
			mapping: 'title'
		}, {
			name: 'subTitle',
			type: 'string',
			mapping: 'subTitle'
		}, {
			name: 'feature',
			type: 'string',
			mapping: 'feature'
		}, {
			name: 'remark',
			type: 'string',
			mapping: 'remark'
		}, {
			name: 'delFlag',
			type: 'string',
			mapping: 'delFlag'
		}, {
			name: 'feeClude',
			type: 'string',
			mapping: 'feeClude'
		}, {
			name: 'feeUnclude',
			type: 'string',
			mapping: 'feeUnclude'
		}, {
			name: 'hit',
			type: 'int',
			mapping: 'hit'
		}, {
			name: 'purchase',
			type: 'string',
			mapping: 'purchase'
		}, {
			name: 'safe',
			type: 'string',
			mapping: 'safe'
		}, {
			name: 'selfBuy',
			type: 'string',
			mapping: 'selfBuy'
		}, {
			name: 'userId',
			type: 'string',
			mapping: 'userId'
		}, {
			name: 'writer',
			type: 'string',
			mapping: 'writer'
		}, {
			name: 'strDate',
			dateFormat: 'y-m-d',
			mapping: 'strDate'
		}]
	});
	
	objectStore = new Ext.data.GroupingStore({
		reader: objectReader,
		proxy: new Ext.data.HttpProxy({
			url: 'getAllJobberLine.shtml',
			method: 'POST'
		}),
		remoteSort: true,
		sortInfo: {
			field: 'writer',
			direction: "ASC"
		},
		groupField: 'strDate'
	});
	objectStore.load({
		params: {
			start: 0,
			limit: pageSize,
			node: node
		}
	});
	
	cbsm = new Ext.grid.CheckboxSelectionModel();
	//		cbsm.handleMouseDown = Ext.emptyFn; //只改变背景不选中
	var rn = new Ext.grid.RowNumberer();
	
	
	/*
	 * 第二tab面板数据源，dataurl的请求带参数
	 */
	objectGrid = new Ext.grid.GridPanel({
		loadMask: true,
		ds: objectStore,
		id: node,
		columns: [rn, //行号列 
 				  cbsm, //CheckBox选择列,
		{
			id: 'title',
			header: "行程名称",
			hideable: true,
			sortable: true,
			dataIndex: 'title'
		}, {
			id: 'subTitle',
			header: "名称简写",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'subTitle'
		}, {
			header: "行程填写者",
			hideable: true,
			sortable: true,
			dataIndex: 'writer'
		}, {
			header: "点击率",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'hit'
		}, {
			header: "行程星级",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'lineStar'
		}, {
			header: "lineId",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'lineId'
		}, {
			header: "备注",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'remark'
		}, {
			header: "delFlag",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'delFlag'
		}, {
			header: "feeClude",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'feeClude'
		}, {
			header: "feeUnclude",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'feeUnclude'
		}, {
			header: "purchase",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'purchase'
		}, {
			header: "创建时间",
			hidden: true,
			hideable: false,
			sortable: true, //renderer: Ext.util.Format.dateRenderer('y年m月d日'),
			dataIndex: 'strDate'
		}, {
			header: "行程特色",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'feature'
		}],
		sm: cbsm,
		
		view: new Ext.grid.GroupingView({
			forceFit: true,
			showGroupName: false,
			enableNoGroups: false, // REQUIRED!
			hideGroupedColumn: true,
			groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "条" : "条"]})'
		}),
		bbar: new MyPagingToolbar({
			pageSize: pageSize,
			store: objectStore,
			displayInfo: true,
			displayMsg: '显示 {0}-{1}条 / 共 {2} 条',
			refreshText:'刷新行程',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新行程信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
			text: '复制',
			tooltip: '复制行程信息',
			iconCls: 'copy',
			handler: function(){
				copyItem(objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '打印',
			tooltip: '打印行程信息',
			iconCls: 'printline',
			handler: function(){
				rowdblclick(objectGrid);
			}
		}],
		
		listeners: {
			'rowdblclick': {
				fn: rowdblclick,
				scope: this
			}
		},
		frame: true,
		region: 'center',
		height: 150,
		autoWidth: true,
		collapsible: true,
		animCollapse: false,
		trackMouseOver: true
	});
	return objectGrid;
}

function rowdblclick(grid){
	var	selectRecord = grid.getSelectionModel().getSelected();
	if (selectRecord) {
		if (cbsm.getCount() == 1) {
			lineId=selectRecord.get('lineId');
			window.open('printSingelLine/'+lineId+'.html');
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条行程打印！');
		}
	}
	else{
		Ext.MessageBox.alert('提示', '请选择一条行程打印！');
	}
}

function copyItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('行程复制操作', '未选择，请选择要复制的行程！');
	}
	else {
		Ext.MessageBox.confirm('确认复制', '你确认复制选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsondata = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('lineId');
					if (i === 0) {
						jsondata = jsondata + ss;
					}
					else {
						jsondata = jsondata + "," + ss;
					}
				}
				copyAjax(start,jsondata);
			}
		});
	}
}

function copyAjax(start,jsondata){
	Ext.Ajax.request({
		url: 'copyLine.shtml',
		method: 'POST',
		params: {
			copyData: jsondata
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('行程复制操作', '复制行程成功！');
			objectStore.load({
					params: {
						start: start,
						limit: pageSize,
						node: node
					}
				});
			}
			else
			{
				Ext.MessageBox.alert('行程复制操作', returnJosn.msg);
			}
		},
		failure: function(result, request){
			Ext.MessageBox.alert('行程复制操作', '复制行程失败！');
		}
	});
}

function allRefresh(store){
	store.load({
		params: {
			start: 0,
			node: node,
			limit:pageSize
		}
	});
}