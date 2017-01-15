
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
var nationId='',cityId='';
var addWin,updWin;

function initView(){
		
//	centerbody = new Ext.TabPanel({
//		id: 'centerBody',
//		region: 'center',
//		activeTab: 0,
//		resizeTabs: true, // turn on tab resizing
//		minTabWidth: 100,
//		deferredRender: false, //是否延迟加载
//		enableTabScroll: true,
//		plugins: new Ext.ux.TabCloseMenu(),
//		
//		items: initGrid()
//	});
	
	new Ext.Viewport({
		layout: 'border',
		items: initGrid()
	});
}

/**
 * 表格加载
 */
function initGrid(){
	var idName = new Array('name','pay','interview','term','cycle','advert','isTopic','ziliao');
	
	var areaPagingToolbar = Ext.extend(Ext.PagingToolbar, {
    	paramNames : {start: 'start', limit: 'limit',areaId: 'areaId'},
    	doLoad: function(start, areaId){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.nationId] = areaId;
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
		idProperty:'visaId',
        fields: [
            {name: 'visaId', mapping:'visaId'},
            {name: 'name', mapping:'name'},
			{name: 'nationName', mapping:'nation.name'},
			{name: 'cityName', mapping:'city.name'},
			{name: 'interview', mapping:'interview'},
			{name: 'term', mapping:'term'},
			{name: 'cycle', mapping:'cycle'},
			{name: 'pay', mapping:'pay'},
			{name: 'advert', mapping:'advert'},
			{name: 'hit', mapping:'hit'},
			{name: 'createDateStr', mapping:'createDateStr'},
			{name: 'writter', mapping:'writter'},
			{name: 'ziliao', mapping:'ziliao'},
			{name: 'isTopic', mapping:'isTopic'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllVisa.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'hit', direction: "DESC"}
        });
		
	objectStore.load({
		params: {
			nationId:areaId,
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
            {id:'visaId',header: '标识号', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'visaId'},
			{header: '名称', hideable:true, sortable: true, 
			dataIndex: 'name'},
			{header: '签证签证', hideable:true, sortable: true, 
			dataIndex: 'nationName'},
			{header: '办证城市', hideable:true, sortable: true, 
			dataIndex: 'cityName'},
			{header: '面试', hideable:true, sortable: true, renderer:Ext.util.Format.longStringRenderer,
			dataIndex: 'interview'},
			{header: '办证期限', hideable:true, sortable: true, renderer:Ext.util.Format.longStringRenderer,
			dataIndex: 'term'},
			{header: '办证周期', hideable:true, sortable: true, renderer:Ext.util.Format.longStringRenderer,
			dataIndex: 'cycle'},
			{header: '注意事项', hideable:true, sortable: true, renderer:Ext.util.Format.longStringRenderer,
			dataIndex: 'advert'},
			{header: '所需资料', hideable:true, sortable: true, renderer:Ext.util.Format.longStringRenderer,
			dataIndex: 'ziliao'},
			{header: '费用', hideable:true, sortable: true, dataIndex: 'pay'},
			{header: '是否热点', hideable:true, sortable: true, renderer:function(v,p,record){
				if(parseInt(v)===1){
					return '是';
				}
				else{
					return '<font color=\"#ee928f\">否</font>';
				}
			},
			dataIndex: 'isTopic'},
			{header: '填写者', hideable:true, sortable: true, dataIndex: 'writter'},
			{header: '创建日期', hideable:true, sortable: true, dataIndex: 'createDateStr'},
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
			refreshText:'刷新签证信息',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新签证信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
		},'-',{
			text: '添加',
			tooltip: '添加签证信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		},'-', {
			text: '修改',
			tooltip: '修改签证信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		},'-',{
			text: '删除',
			tooltip: '删除签证信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}		
		},'-',{
			text: '签证材料',
			tooltip: '添加签证所需材料',
			iconCls: 'option',
			handler: function(){
				updItem2(objectGrid.getBottomToolbar().cursor);
			}		
		},'-', {
			text: '查看',
			tooltip: '查看签证信息',
			iconCls: 'option',
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
//		title:'签证信息表',
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
		Ext.MessageBox.alert('修改操作', '请选择要修改的签证！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('visaId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改签证信息',
					id: 'updW',
					closable: true,
					width: 580,
					height: 415,
					plain: true,
					modal: true,
					layout: 'fit',
					listeners:{
						close:{
							fn:winClose,
							scope:this
						}
					},
					items: [fpanel]
				});
			}
			updWin.show();
			updWin.doLayout();
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条签证信息！');
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
						nationId:areaId,
						name:name,
						start: 0,
						limit: pageSize
					}
				});
}

function save(fpanel,start){
	var urlPost;
	var paramsPost;
	var checkValue = true;
	if (nationId === '' ||cityId==='') {
		checkValue = false;
	}
	
	if (addWin) {
		urlPost = 'addVisa.shtml';
		paramsPost = {
			nationId: nationId,
			cityId:cityId
		};
	}
	else 
		if (updWin) {
			urlPost = 'updateVisa.shtml';
			paramsPost = {
				visaId: selectRecord.get('visaId'),
				cityId:cityId,
				nationId: nationId
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
						nationId='',cityId='';
						
						objectStore.load({
							params: {
								nationId:areaId,
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
	nationId='',cityId='';
}

function winClose(item){
	item.close();
	nationId='',cityId='';
}

function addItem(idName){
	addWin = Ext.getCmp('addW');
	
	if (!addWin) {
		comCreate(idName,'add',0);
		addWin = new Ext.Window({
			title: '添加签证信息',
			id: 'addW',
			closable: true,
			width: 580,
			height: 415,
			modal: true,
			layout: 'fit',
			listeners:{
						close:{
							fn:winClose,
							scope:this
						}
					},
			items: [fpanel]
		});
	}
	addWin.show();
	
	if (areaId !== '') {
		nationId = areaId;
		Ext.Ajax.request({
			url: 'getSingleArea.shtml',
			method: 'POST',
			params: {
				areaId: areaId
			},
			success: function(result, request){
				var resultJosn = doJSON(result.responseText);
				if (resultJosn.success == true) {
					var list = resultJosn.area;
					Ext.getCmp('reAreaP').html=list.name;
				}
				else {
					Ext.MessageBox.alert('加载失败', resultJosn.msg);
				}
			},
			failure: function(result, request){
				Ext.MessageBox.alert('加载类别失败！');
				window.close();
			}
		});
	}
}

function updateItem(idName,start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的签证！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('visaId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改签证信息',
					id: 'updW',
					closable: true,
					width: 580,
					height: 415,
					plain: true,
					modal: true,
					layout: 'fit',
					listeners:{
						close:{
							fn:winClose,
							scope:this
						}
					},
					items: [fpanel]
				});
			}
			updWin.show();
			updWin.doLayout();
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条签证信息！');
		}
	}
}

function comCreate(idName,actions,start){
	var attributePanel,areaPanel,cityPanel;
	
	attributePanel = new Ext.Panel({
		title: '签证信息',
		id: 'attrP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 300,
		defaults: {
			xtype: 'textfield',
			width: 230,
			anchor: '95%'
		},
		
		items: [{
			fieldLabel: '名称',
			allowBlank: false,
			name: idName[0],
			id: idName[0]			
		},{
			fieldLabel: '签证费用',
			name: idName[1],
			id: idName[1]
		},{
			xtype: 'textarea',
			fieldLabel: '是否面试',
			height: 120,
			grow: false,
			name: idName[2],
			id: idName[2]			
		},{
			xtype: 'textarea',
			height: 120,
			grow: false,
			fieldLabel: '签证期限',
			name: idName[3],
			id: idName[3]			
		}
//		,{
//			xtype: 'textarea',
//			height: 120,
//			grow: false,
//			fieldLabel: '办证周期',
//			name: idName[4],
//			id: idName[4]			
//		},{
//			xtype: 'textarea',
//			fieldLabel: '注意事项',
//			height: 120,
//			grow: false,
//			name: idName[5],
//			id: idName[5]
//		},{
//			xtype:'checkbox',
//			fieldLabel: '热点',
//			checked:false,
//			inputValue:'1',
//			name: idName[6],
//			id: idName[6]			
//		}
		]
	});
	
	attributePanel2 = new Ext.Panel({
		title: '签证信息2',
		id: 'attrP2',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 300,
		defaults: {
			xtype: 'textfield',
			width: 230,
			anchor: '95%'
		},
		
		items: [{
			xtype: 'textarea',
			height: 120,
			grow: false,
			fieldLabel: '办证周期',
			name: idName[4],
			id: idName[4]			
		},{
			xtype: 'textarea',
			fieldLabel: '注意事项',
			height: 120,
			grow: false,
			name: idName[5],
			id: idName[5]
		},{
			xtype:'checkbox',
			fieldLabel: '是否热点',
			checked:false,
			inputValue:'1',
			name: idName[6],
			id: idName[6]
		}]
	});
	
//	attributePanel3 = new Ext.Panel({
//		title: '签证信息3',
//		id: 'attrP3',
//		layout: 'form',
//		bodyStyle: 'padding:10px',
//		autoScroll: true,
//		height: 300,
//		defaults: {
//			xtype: 'textfield',
//			width: 230,
//			anchor: '95%'
//		},
//		
//		items: [{
//			xtype: 'textarea',
//			height: 120,
//			grow: false,
//			fieldLabel: '所需资料',
//			name: idName[7],
//			id: idName[7]			
//		},{
//			xtype:'checkbox',
//			fieldLabel: '是否热点',
//			checked:false,
//			inputValue:'1',
//			name: idName[6],
//			id: idName[6]			
//		}]
//	});
	
	areaPanel = new Ext.Panel({
		title: '签证国家',
		id: 'areaP',
		height: 280,
		layout: 'column',
		bodyStyle: 'padding:10px',
		autoHeight: true,
		autoScroll: true,
		collapsible: true,
		items: [{
			collapsible: true,
			title: '签证国家',
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
					if ((!n.isLeaf()&&(n.getDepth()===2))) {
						clickAreaNode(n);
					}
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
			id:'reAreaP',
			title: '签证国家',
			bodyStyle: 'padding:10px 0 0 10px;',
			columnWidth: .58,
			tbar: [{
				text: '删除签证国家',
				tooltip: '删除签证国家',
				iconCls: 'remove',
				handler: function(){
					var temp = Ext.getCmp('reAreaP');
					temp.body.update('未选择');
					nationId = '';
				}
			}],
			
			html:'未选择',
			collapsed: false,
			height: 280
			
		}]
	});
	
	cityPanel = new Ext.Panel({
		title: '办证城市',
		id: 'cityP',
		height: 280,
		layout: 'column',
		bodyStyle: 'padding:10px',
		autoHeight: true,
		autoScroll: true,
		collapsible: true,
		items: [{
			collapsible: true,
			title: '办证城市',
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
				id: '402880e51a512576011a512a74590003',
				text: '中国',
				disabled: true,
				draggable: false
			}),
			rootVisible: false,
			listeners: {
				click: function(n){
					if (n.getDepth()===2) {
						clickCityNode(n);
					}
					else{
						if(!n.isLeaf()){
							n.expand();
						}
					}
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
			id:'reCityP',
			title: '办证城市',
			bodyStyle: 'padding:10px 0 0 10px;',
			columnWidth: .58,
			tbar: [{
				text: '删除办证城市',
				tooltip: '删除办证城市',
				iconCls: 'remove',
				handler: function(){
					var temp = Ext.getCmp('reCityP');
					temp.body.update('未选择');
					cityId = '';
				}
			}],
			
			html:'未选择',
			collapsed: false,
			height: 280
			
		}]
	});
	
	tabPanel = new Ext.TabPanel({
		activeTab: 0,
		autoHeight: true,
		items: [attributePanel,attributePanel2,areaPanel,cityPanel]
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
	var temp = Ext.getCmp('reAreaP');
	if(nationId!==''){
		Ext.Msg.alert('提示','签证国家只能选择一个，请先删除再修改！');
	}
	else{
		nationId=item.id;
		temp.body.update(item.text);
	}
}

function clickCityNode(item){
	var temp = Ext.getCmp('reCityP');
	if(cityId!==''){
		Ext.Msg.alert('提示','办证城市只能选择一个，请先删除再修改！');
	}
	else{
		cityId=item.id;
		temp.body.update(item.text);
	}
}

function updateAjax(fpanel,tabPanel,Id,idName){
	Ext.Ajax.request({
		url: 'getSingleVisa.shtml',
		method: 'POST',
		params: {visaId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,tabPanel,idName);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载签证信息失败！');
			window.close();
		}
	});
}

function initUpdate(result, fpanel, tabPanel, idName){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.results[0];
		
		fpanel.findById(idName[0]).setValue(list.name);
		fpanel.findById(idName[1]).setValue(list.pay);
		fpanel.findById(idName[2]).setValue(list.interview);
		fpanel.findById(idName[3]).setValue(list.term);
		
		tabPanel.setActiveTab(fpanel.findById('attrP2'));
		fpanel.findById(idName[4]).setValue(list.cycle);
		fpanel.findById(idName[5]).setValue(list.advert);
		
//		tabPanel.setActiveTab(fpanel.findById('attrP3'));
		fpanel.findById(idName[6]).setValue(list.isTopic);
//		fpanel.findById(idName[7]).setValue(list.ziliao);
		
		tabPanel.setActiveTab(fpanel.findById('areaP'));
		var temp, initDepth, initHtml;
		if (list.nation!== 0) {
			temp = Ext.getCmp('reAreaP');
			initHtml = list.nation.name;
			nationId = list.nation.areaId;
			temp.body.update(initHtml);
		}
		initHtml='';
		
		tabPanel.setActiveTab(fpanel.findById('cityP'));
		if (list.city!== 0) {
			temp = Ext.getCmp('reCityP');
			initHtml = list.city.name;
			cityId = list.city.areaId;
			temp.body.update(initHtml);
		}
		initHtml='';
	}
}

function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('签证信息删除操作', '未选择，请选择要删除的签证信息！');
	}
	else {
		Ext.MessageBox.confirm('确认签证删除', '你确认签证删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('visaId');
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
		url: 'delVisa.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('签证信息删除操作', '删除签证信息成功！');
			}
			else
			{
				Ext.MessageBox.alert('删除操作', returnJosn.msg);
				objectStore.load({
					params: {
						nationId:areaId,
						start: start,
						limit: pageSize
					}
				});
			}
		},
		failure: function(result, request){
			Ext.MessageBox.alert('签证信息删除操作', '删除签证信息失败！');
			objectStore.load({
					params: {
						nationId:areaId,
						start: start,
						limit: pageSize,
						delData: jsonData
					}
				});
		}
	});
}

function viewItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('查看操作', '请选择要查看的签证！');
	}
	else {
		if (cbsm.getCount() == 1) {
			window.open('../../visa/visaDetail/' + selectRecord.get('visaId')+'.html');
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条签证信息！');
		}
	}
}

function updItem2(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('添加操作', '请选择要添加所需材料的签证！');
	}
	else {
		if (cbsm.getCount() == 1) {
			window.open('../../trader/visa/getSingleVisa2.shtml?visaId=' + selectRecord.get('visaId'));
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条签证信息！');
		}
	}
}