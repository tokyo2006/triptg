Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	/*==============================全局变量t=============================================*/
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
var parent=areaId,outParentText=title;
var depth;

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
	var idName = new Array('name', 'headName', 'orderBy', 'ip', 'isChina','isZhiXia','isXinZhen');
	
	var areaPagingToolbar = Ext.extend(Ext.PagingToolbar, {
		paramNames: {
			start: 'start',
			limit: 'limit',
			depth: 'depth',
			areaId:'areaId'
		},
		
		doLoad: function(start, depth,areaId){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.depth] = depth;
			o[pn.areaId] = areaId;
			if (this.fireEvent('beforechange', this, o) !== false) {
				this.store.load({
					params: o
				});
			}
		},
		
		onClick: function(which){
			depth = Ext.getCmp('searchS').getValue();
			var store = this.store;
			switch (which) {
				case "first":
					this.doLoad(0, depth,areaId);
					break;
				case "prev":
					this.doLoad(Math.max(0, this.cursor - this.pageSize), depth,areaId);
					break;
				case "next":
					this.doLoad((this.cursor + this.pageSize), depth,areaId);
					break;
				case "last":
					var total = store.getTotalCount();
					var extra = total % this.pageSize;
					var lastStart = extra ? (total - extra) : total - this.pageSize;
					this.doLoad(lastStart, depth,areaId);
					break;
				case "refresh":
					this.doLoad(this.cursor, '',areaId);
					break;
			}
		}
	});
	
	var searchBoxStore = new Ext.data.SimpleStore({
		fields: ['displayValue', 'returnValue'],
		data: [['不限', 0], ['城市', 4], ['省份', 3], ['国家', 2], ['大洲', 1]]
	});
	
	var searchText = new Ext.form.TextField({
		id: 'searchT',
		name: 'searchT',
		width: 80,
		emptyText: '请输入名称'
	});
	
	var searchSelect = new Ext.form.ComboBox({
		store: searchBoxStore,
		id: 'searchS',
		name: 'depth',
		hiddenName: 'depth',
		valueField: 'returnValue',
		displayField: 'displayValue',
		typeAhead: true,
		mode: 'local',
		width: 120,
		bodyStyle: 'padding:3px,margin-bottom:6px',
		triggerAction: 'all',
		emptyText: '请选择地域级别',
		loadingText: '正在加载请等待....',
		forceSelection: true,
		selectOnFocus: true
	});
	/*
	 * 数据解析器
	 */
	var objectReader = new Ext.data.JsonReader({
		totalProperty: 'totalCount',
		root: 'results',
		successProperty: 'success',
		idProperty: 'areaId',
		fields: [{
			name: 'areaId',
			mapping: 'areaId'
		}, {
			name: 'name',
			mapping: 'name'
		}, {
			name: 'orderBy',
			mapping: 'orderBy'
		}, {
			name: 'headName',
			mapping: 'headName'
		}, {
			name: 'depth',
			mapping: 'depth'
		}, {
			name: 'isChina',
			mapping: 'isChina'
		}, {
			name: 'isZhixia',
			mapping: 'isZhixia'
		}, {
			name: 'isXinzhen',
			mapping: 'isXinzhen'
		}, {
			name: 'ip',
			mapping: 'ip'
		}]
	});
	
	objectStore = new Ext.data.GroupingStore({
		reader: objectReader,
		proxy: new Ext.data.HttpProxy({
			url: 'getAllArea.shtml',
			method: 'POST'
		}),
		remoteSort: true,
		sortInfo: {
			field: 'depth',
			direction: "DESC"
		}
	});
	
	objectStore.load({
		params: {
			areaId:areaId,
			start: 0,
			limit: pageSize
		}
	});
	
	depth = searchSelect.getValue();
	if (depth === 0) {
		depth = '';
	}
	
	cbsm = new Ext.grid.CheckboxSelectionModel();
	var rn = new Ext.grid.RowNumberer();
	
	/*
	 * 第二tab面板数据源，dataurl的请求带参数
	 */
	objectGrid = new Ext.grid.GridPanel({
		loadMask: true,
		ds: objectStore,
		columns: [rn, //行号列 
 cbsm, {
			id: 'areaId',
			header: '标识号',
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'areaId'
		}, {
			header: '名称',
			hideable: true,
			sortable: true,
			dataIndex: 'name'
		}, {
			header: '排序标识',
			hideable: true,
			sortable: true,
			dataIndex: 'orderBy'
		}, {
			header: '拼音缩写',
			hideable: true,
			sortable: true,
			dataIndex: 'headName'
		}, {
			header: '是否国内',
			hideable: true,
			sortable: true,
			renderer: function(v){
					if (parseInt(v) === 1) {
						return '是';
					}
					else {
						return '<font color=red>否</font>';
					}
				},
			dataIndex: 'isChina'
		},  {
			header: '是否直辖市',
			hideable: true,
			sortable: false,
			renderer: function(v){
					if (parseInt(v) === 1) {
						return '是';
					}
					else {
						return '<font color=red>否</font>';
					}
				},
			dataIndex: 'isZhiXia'
		}, {
			header: '是行政区',
			hideable: true,
			sortable: false,
			renderer: function(v){
					if (parseInt(v) === 1) {
						return '是';
					}
					else {
						return '<font color=red>否</font>';
					}
				},
			dataIndex: 'isXinZhen'
		},{
			header: 'IP地段',
			hideable: true,
			sortable: true,
			dataIndex: 'ip'
		}, {
			header: '排序深度',
			hideable: true,
			sortable: true,
			dataIndex: 'depth'
		}],
		sm: cbsm,
		
		view: new Ext.grid.GroupingView({
			forceFit: true,
			showGroupName: false,
			enableNoGroups: true, // REQUIRED!
			hideGroupedColumn: false,
			groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "条" : "条"]})'
		}),
		
		bbar: new areaPagingToolbar({
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
				allRefresh(objectStore,areaId);
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
		}, '-', '-', searchText, '-', searchSelect, {
			text: '搜索',
			handler: function(){
				search(searchText, searchSelect, objectStore);
			}
		}],
		
		listeners:{
			'rowdblclick':function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		},
		
		frame: true,
//		title:title ,
//		id:id,
		region: 'center',
		height: 150,
		autoWidth: true,
		animCollapse: false,
		trackMouseOver: true
	});
	return objectGrid;
}

function comCreate(idName,actions,start){
	var attributePanel,areaPanel;
			
	attributePanel = new Ext.Panel({
		title: '地域信息',
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
			fieldLabel: '名称',
			name: idName[0],
			id: idName[0]			
		},{
			fieldLabel: '拼音',
			name: idName[1],
			id: idName[1]			
		},{
			fieldLabel: '排序',
			allowBlank: true,
			name: idName[2],
			id: idName[2]			
		},{
			fieldLabel: 'IP地址标识',
			allowBlank: true,
			name: idName[3],
			id: idName[3]			
		},{
			xtype:'panel',
			id:'columnP1',
			layout:'column',
			border:false,
			height:25,
			bodyStyle:'padding:0 0 0 0; margin:0 0 0 0',
			items:[{
				xtype:'panel',
				height:24,
				border:false,
				columnWidth:.22,
				html:'<font style:\"font-size:24px;\">是否国内:</font>'
			},{
				xtype:'panel',
				columnWidth:.39,
				border:false,
				items:[{
					xtype:'radio',
					boxLabel: '国内',
					name: idName[4],
					id: 'radio0',
					inputValue: 1,
					checked: true
				}]
			},{
				xtype:'panel',
				columnWidth:.39,
				border:false,
				items:[{
					xtype:'radio',
					boxLabel: '国外',
					name: idName[4],
					id: 'radio1',
					inputValue: 0
				}]
			}]
		},{
			xtype:'panel',
			id:'columnP2',
			layout:'column',
			border:false,
			height:25,
			bodyStyle:'padding:0 0 0 0; margin:0 0 0 0',
			items:[{
				xtype:'panel',
				height:24,
				border:false,
				columnWidth:.22,
				html:'<font style:\"font-size:24px;\">是否行政区:</font>'
			},{
				xtype:'panel',
				columnWidth:.39,
				border:false,
				items:[{
					xtype:'radio',
					boxLabel: '是',
					name: idName[5],
					id: 'radio2',
					inputValue: 1
				}]
			},{
				xtype:'panel',
				columnWidth:.39,
				border:false,
				items:[{
					xtype:'radio',
					boxLabel: '不是',
					name: idName[5],
					id: 'radio3',
					inputValue: 0,
					checked: true
				}]
			}]
		},{
			xtype:'panel',
			id:'columnP3',
			layout:'column',
			border:false,
			height:25,
			bodyStyle:'padding:0 0 0 0; margin:0 0 0 0',
			items:[{
				xtype:'panel',
				height:24,
				border:false,
				columnWidth:.22,
				html:'<font style:\"font-size:24px;\">是否直辖市:</font>'
			},{
				xtype:'panel',
				columnWidth:.39,
				border:false,
				items:[{
					xtype:'radio',
					boxLabel: '是',
					name: idName[6],
					id: 'radio4',
					inputValue: 1
				}]
			},{
				xtype:'panel',
				columnWidth:.39,
				border:false,
				items:[{
					xtype:'radio',
					boxLabel: '不是',
					name: idName[6],
					id: 'radio5',
					inputValue: 0,
					checked: true
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
				},
				checkchange:function(node, checked) {   
                    node.expand();   
                    node.attributes.checked = checked;   
                    node.eachChild(function(child) {   
                        child.ui.toggleCheck(checked);   
                        child.attributes.checked = checked;   
                        child.fireEvent('checkchange', child, checked);   
                    });   
                }
			}
		}, {
			xtype: 'panel',
			columnWidth: .02,
			border: false
		},{
			xtype: 'panel',
			id:'parentArea',
			title: '区域类型',
			bodyStyle: 'padding:10px 0 0 10px;',
			columnWidth: .58,
			tbar: [{
				text: '删除父节点',
				tooltip: '删除父地域',
				iconCls: 'remove',
				handler: function(){
					var temp = Ext.getCmp('parentArea');
					temp.body.update('未选择');
					parent = '';
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
	else{
		parent = areaId;
		fpanel.findById('parentArea').html=outParentText;
	}
}

function clickAreaNode(item){
	var temp = Ext.getCmp('parentArea');
	if(parent!==''){
		Ext.Msg.alert('提示','所属父区域只能选择一个，请先删除再修改！');
	}
	else{
		parent=item.id;
		temp.body.update(item.text);
	}
}

function updateAjax(fpanel,tabPanel,Id,idName){
	Ext.Ajax.request({
		url: 'getSingleArea.shtml',
		method: 'POST',
		params: {areaId:Id},
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
		var list = strList.area;
		
		fpanel.findById(idName[0]).setValue(list.name);
		fpanel.findById(idName[1]).setValue(list.headName);
		fpanel.findById(idName[2]).setValue(list.orderBy);
		fpanel.findById(idName[3]).setValue(list.ip);
		
		if(parseInt(list.isChina)===0){
			fpanel.findById('radio0').setValue(false);
			fpanel.findById('radio1').setValue(true);
		}
		
		if(parseInt(list.isZhiXia)===1){
			fpanel.findById('radio2').setValue(false);
			fpanel.findById('radio3').setValue(true);
		}
		
		if(parseInt(list.isXinZhen)===1){
			fpanel.findById('radio4').setValue(false);
			fpanel.findById('radio5').setValue(true);
		}
		
		tabPanel.setActiveTab(fpanel.findById('areaP'));
		var temp = Ext.getCmp('parentArea');
		temp.body.update(strList.parea.name);
		parent=strList.parea.areaId;
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
	if (parent === '') {
		checkValue = false;
	}
	
	if (addWin) {
		urlPost = 'addArea.shtml';
		paramsPost = {
			parent: parent
		};
	}
	else 
		if (updWin) {
			urlPost = 'updateArea.shtml';
			paramsPost = {
				areaId: selectRecord.get('areaId'),
				parent: parent
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
						parent='';
						objectStore.load({
							params: {
								areaId:areaId,
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
	parent='';
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
			comCreate(idName,selectRecord.get('areaId'),start);

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
					var ss = m[i].get('areaId');
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
		url: 'delArea.shtml',
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
						areaId:areaId,
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
						areaId:areaId,
						start: start,
						limit: pageSize,
						delData: jsonData
					}
				});
		}
	});
}

function allRefresh(store,areaId){
	store.load({
		params: {
			areaId:areaId,
			start: 0,
			limit:pageSize
		}
	});
}

function search(searchText,searchSelect,objectStore){
	var name=searchText.getValue();
	if(name==='请输入名称'){
		name='';
	}
	var depth=searchSelect.getValue();
	if(depth===0){
		depth='';
	}
	objectStore.load({
					params: {
						areaId:areaId,
						depth:depth,
						name:name,
						start: 0,
						limit: pageSize
					}
				});
}