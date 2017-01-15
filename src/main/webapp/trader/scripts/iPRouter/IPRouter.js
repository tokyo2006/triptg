Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	var fpanel,tabPanel;
	var objectGrid;
	var selectRecord;
	var cbsm;
	var objectStore;
	var centerbody;
	initView();
});

var pageSize =27;
var addWin,updWin;
var areaId='';

function initView(){
	
	new Ext.Viewport({
		layout: 'border',
		items: initGrid()
	});
}
/**
 * 表格加载
 */
function initGrid(){
	var idName = new Array('startIp', 'endIp', 'reUrl');
	
//	var searchText = new Ext.form.TextField({
//		id: 'searchT',
//		name: 'searchT',
//		width: 80,
//		emptyText: '请输入名称'
//	});
	
	var objectReader = new Ext.data.JsonReader({
		totalProperty: 'totalCount',
		root: 'results',
		successProperty: 'success',
		idProperty: 'ipId',
		fields: [{
			name: 'ipId',
			mapping: 'ipId'
		}, {
			name: 'startIpStr',
			mapping: 'startIpStr'
		}, {
			name: 'endIpStr',
			mapping: 'endIpStr'
		}, {
			name: 'reUrl',
			mapping: 'reUrl'
		}, {
			name: 'areaName',
			mapping: 'areaName'
		}]
	});
	
	objectStore = new Ext.data.GroupingStore({
		reader: objectReader,
		proxy: new Ext.data.HttpProxy({
			url: 'getAllIPRouter.shtml',
			method: 'POST'
		}),
		remoteSort: true,
		sortInfo: {
			field: 'ipId',
			direction: "DESC"
		}
	});
	
	objectStore.load({
		params: {
			start: 0,
			limit: pageSize
		}
	});
	
	cbsm = new Ext.grid.CheckboxSelectionModel();
	var rn = new Ext.grid.RowNumberer();
	
	objectGrid = new Ext.grid.GridPanel({
		loadMask: true,
		ds: objectStore,
		columns: [rn,
 			cbsm, {
			id: 'ipId',
			header: '标识号',
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'ipId'
		}, {
			header: '起始IP',
			hideable: true,
			sortable: true,
			dataIndex: 'startIpStr'
		}, {
			header: '结束IP',
			hideable: true,
			sortable: true,
			dataIndex: 'endIpStr'
		}, {
			header: '返回地址',
			hideable: true,
			sortable: true,
			dataIndex: 'reUrl'
		}, {
			header: '所属地区',
			hideable: true,
			sortable: false,
			dataIndex: 'areaName'
		}
//		, {
//			header: '是否国内',
//			hideable: true,
//			sortable: true,
//			renderer: function(v){
//					if (parseInt(v) === 1) {
//						return '是';
//					}
//					else {
//						return '<font color=red>否</font>';
//					}
//				},
//			dataIndex: 'isChina'
//		}, 
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
			refreshText: '刷新地域信息',
			emptyMsg: "无数据。"
		}),
		tbar: [{
			text: '刷新',
			tooltip: '刷新地域信息',
			iconCls: 'refresh',
			handler: function(){
				allRefresh(objectStore);
			}
		}, '-', {
			text: '添加',
			tooltip: '添加地域信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		}, '-', {
			text: '修改',
			tooltip: '修改地域信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName, objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '删除',
			tooltip: '删除地域信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}
		}
//		, '-', '-', searchText,{
//			text: '搜索',
//			handler: function(){
//				search(searchText, objectStore);
//			}
//		}
		],
		
		listeners:{
			'rowdblclick':function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		},
		
		frame: true,
		region: 'center',
//		height: 150,
		autoWidth: true,
		animCollapse: false,
		trackMouseOver: true
	});
	return objectGrid;
}

function comCreate(idName,actions,start){
	var attributePanel,areaPanel;
			
	attributePanel = new Ext.Panel({
		title: 'IP转向信息',
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
			fieldLabel: '起始IP',
			vtype:'IPCheck',
			name: idName[0],
			id: idName[0]			
		},{
			fieldLabel: '结束IP',
			vtype:'IPCheck',
			name: idName[1],
			id: idName[1]			
		},{
			fieldLabel: '返回地址',
			allowBlank: true,
			name: idName[2],
			id: idName[2]			
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
			title: 'IP所属地区',
			bodyStyle: 'padding:10px 0 0 10px;',
			columnWidth: .58,
			tbar: [{
				text: '删除地区信息',
				tooltip: '删除地区信息',
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
			
		}
		]
	});
	
	tabPanel = new Ext.TabPanel({
		activeTab: 0,
		autoHeight: true,
		items: [attributePanel,areaPanel]
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
		Ext.Msg.alert('提示','所属父区域只能选择一个，请先删除再修改！');
	}
	else{
		areaId=item.id;
		temp.body.update(item.text);
	}
}

function updateAjax(fpanel,tabPanel,Id,idName){
	Ext.Ajax.request({
		url: 'getSingleIPRouter.shtml',
		method: 'POST',
		params: {ipId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,tabPanel,idName);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载地域信息失败！');
			window.close();
		}
	});
}

function initUpdate(result,fpanel,tabPanel,idName){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.iprouter;
		
		fpanel.findById(idName[0]).setValue(list.startIpStr);
		fpanel.findById(idName[1]).setValue(list.endIpStr);
		fpanel.findById(idName[2]).setValue(list.reUrl);
		
		tabPanel.setActiveTab(fpanel.findById('areaP'));
		var temp = Ext.getCmp('areaIdP');
		temp.body.update(strList.area.name);
		areaId=strList.area.areaId;
		fpanel.findById('areaP').doLayout();
	}	
}

/**
 * 表单保存
 * @param {Object} fpanel
 */
function save(fpanel,start){
	var urlPost;
	var paramsPost;
	var checkValue = true;
	if (areaId === '') {
		checkValue = false;
	}
	
	if (addWin) {
		urlPost = 'addIPRouter.shtml';
		paramsPost = {
				areaId: areaId
		};
	}
	else 
		if (updWin) {
			urlPost = 'updateIPRouter.shtml';
			paramsPost = {
				ipId: selectRecord.get('ipId'),
				areaId:areaId
			};
		}
		else {
			Ext.Msg.alert('信息', '提交窗口错误参数!');
			window.close();
		}
	
	//提交数据
	if (fpanel.form.isValid() && checkValue) {
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
		Ext.Msg.alert('信息', '请填写完成再提交!');
	}
}

/**
 * 表单重置
 * @param {Object} fpanel
 */
function reset(fpanel,idName){
	fpanel.form.reset();
}

/**
 * 窗口关闭
 */	
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

function addItem(idName){
	addWin = Ext.getCmp('addW');
	
	if (!addWin) {
		comCreate(idName,'add',0);
		addWin = new Ext.Window({
			title: '添加地域信息',
			id: 'addW',
			closable: true,
			width: 580,
			height: 415,
			modal: true,
			layout: 'fit',
			items: [fpanel]
		});
	}
	addWin.show();
}

function updateItem(idName,start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的地域！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('ipId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改地域信息',
					id: 'updW',
					closable: true,
					width: 580,
					height: 415,
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
			Ext.MessageBox.alert('提示', '请选择一条地域信息！');
		}
	}
}


function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('地域信息删除操作', '未选择，请选择要删除的地域信息！');
	}
	else {
		Ext.MessageBox.confirm('确认地域删除', '你确认地域删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('ipId');
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
		url: 'delIPRouter.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('地域信息删除操作', '删除地域信息成功！');
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
			Ext.MessageBox.alert('地域信息删除操作', '删除地域信息失败！');
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

//function search(searchText,objectStore){
//	var name=searchText.getValue();
//	if(name==='请输入名称'){
//		name='';
//	}
//	
//	objectStore.load({
//					params: {
//						name:name,
//						start: 0,
//						limit: pageSize
//					}
//				});
//}