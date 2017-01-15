
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	/*==============================全局变量t=============================================*/
	var fpanel,tabPanel;
	var objectGrid;
	var selectRecord;
	var cbsm;
	var objectStore,pickStore;
	var centerbody;
	initView();
});

var pageSize =27;
var addWin,updWin;
var depth,flag;
var parentId=node,outParentText=title, areaId='';
function initView(){
		
	/*
	 * 布局加载
	 */
	new Ext.Viewport({
		layout: 'border',
		items: initGrid()
	});
}
function initGrid(){
	var idName = new Array('name','fullName','headName','orderBy','flag');
	
	var searchText = new Ext.form.TextField({
		id: 'searchT',
		name: 'searchT',
		width: 80,
		emptyText: '请输入名称'
	});
	
	var searchBoxStore = new Ext.data.SimpleStore({
		fields: [ 'returnValue'],
		data: [['不限'], [1], [2], [3], [4]]
	});
	var searchSelect = new Ext.form.ComboBox({
		store: searchBoxStore,
		id: 'searchS',
		name: 'depth',
		valueField: 'returnValue',
		displayField: 'returnValue',
		typeAhead: true,
		mode: 'local',
		width: 80,
		bodyStyle: 'padding:3px,margin-bottom:6px',
		triggerAction: 'all',
		emptyText: '类别深度',
		loadingText: '正在加载请等待....',
		forceSelection: true,
		selectOnFocus: true
	});
	var searchBoxStore2 = new Ext.data.SimpleStore({
		fields: ['displayValue', 'returnValue'],
		data: [['不限', 10],['地域类', 0], ['周边游', 1], ['国内长线', 2], ['出境游', 3], ['自由行', 4], ['国内自由行', 5]]
	});
	var searchSelect2 = new Ext.form.ComboBox({
		store: searchBoxStore2,
		id: 'searchS2',
		name: 'flag',
		hiddenName: 'flag',
		valueField: 'returnValue',
		displayField: 'displayValue',
		typeAhead: true,
		mode: 'local',
		width: 120,
		bodyStyle: 'padding:3px,margin-bottom:6px',
		triggerAction: 'all',
		emptyText: '请选择类别属性',
		loadingText: '正在加载请等待....',
		forceSelection: true,
		selectOnFocus: true
	});
	
	var regionPagingToolbar = Ext.extend(Ext.PagingToolbar, {
		paramNames: {
			start: 'start',
			limit: 'limit',
			depth: 'depth',
			flag: 'flag',
			node:'node'
		},
		
		doLoad: function(start, depth,flag,node){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.depth] = depth;
			o[pn.flag] = flag;
			o[pn.node] = node;
			if (this.fireEvent('beforechange', this, o) !== false) {
				this.store.load({
					params: o
				});
			}
		},
		
		onClick: function(which){
			depth = Ext.getCmp('searchS').getValue();
			flag = Ext.getCmp('searchS2').getValue();
			var store = this.store;
			switch (which) {
				case "first":
					this.doLoad(0, depth,flag,node);
					break;
				case "prev":
					this.doLoad(Math.max(0, this.cursor - this.pageSize), depth,flag,node);
					break;
				case "next":
					this.doLoad((this.cursor + this.pageSize), depth,flag,node);
					break;
				case "last":
					var total = store.getTotalCount();
					var extra = total % this.pageSize;
					var lastStart = extra ? (total - extra) : total - this.pageSize;
					this.doLoad(lastStart, depth,flag,node);
					break;
				case "refresh":
					this.doLoad(this.cursor, '','',node);
					break;
			}
		}
	});
	
	var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'regionId',
        fields: [
            {name: 'regionId', mapping:'regionId'},
            {name: 'name', mapping:'name'},
			{name: 'orderBy', mapping:'orderBy'},
			{name: 'fullName', mapping:'fullName'},
			{name: 'headName', mapping:'headName'},
			{name: 'depth', mapping:'depth'},
			{name: 'flag', mapping:'flag'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllRegion.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'depth', direction: "DESC"}
        });
		
	objectStore.load({
		params: {
			node:node,
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
            {id:'regionId',header: '标识号', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'regionId'},
			{header: '名称', hideable:true, sortable: true, 
			dataIndex: 'name'},
			{header: '排序标识', hideable:true, sortable: true, 
			dataIndex: 'orderBy'},
			{header: '拼音缩写', hideable:true, sortable: true, 
			dataIndex: 'headName'},
			{header: '拼音全称', hideable:true, sortable: true, 
			dataIndex: 'fullName'},
			{header: '类别属性', hideable:true, sortable: true, 
			dataIndex: 'flag'},
			{header: '排序深度', hideable:true, sortable: true, 
			dataIndex: 'depth'}
        ],
		sm: cbsm,
		
		view: new Ext.grid.GroupingView({
			forceFit: true,
			showGroupName: false,
			enableNoGroups: true, // REQUIRED!
			hideGroupedColumn: false,
			groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "条" : "条"]})'
		}),
		
		bbar: new regionPagingToolbar({
			pageSize: pageSize,
			store: objectStore,
			displayInfo: true,
			displayMsg: '显示 {0}-{1}条 / 共 {2} 条',
			refreshText:'刷新类别信息',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新类别信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
			text: '添加',
			tooltip: '添加类别信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		},'-', {
			text: '修改',
			tooltip: '修改类别信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '删除',
			tooltip: '删除类别信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}		
		}, '-', '-', searchText,'-',searchSelect2,'-',searchSelect, {
			text: '搜索',
			handler: function(){
				search(searchText,searchSelect,searchSelect2, objectStore);
			}
		}],
		
		frame: true,
//		title:title,
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
	var attributePanel,areaPanel,regionPanel;
	pickStore = new Ext.data.SimpleStore({
		fields:['displayValue','returnValue'],		
		data:[['地域级',0],['周边游',1],['国内长线',2],['出境游',3],['自由行',4],['国内自由行',5]]
	});
	
	var pickCombo= new Ext.form.ComboBox({
			store: pickStore,       //先将值选出组合为三个数组赋值，示例如下
			id:'flagSelect',
			name:idName[4],
			hiddenName: idName[4],
			valueField:'returnValue',
	        displayField:'displayValue',
			fieldLabel:'类型属性选择',
	        typeAhead: true,
	        mode: 'local',
			bodyStyle: 'padding:3px,margin-bottom:6px',
			triggerAction: 'all',
	        emptyText:'请选择一个类型属性',
			loadingText:'正在加载请等待....',
			forceSelection:true,   
			selectOnFocus:true
	});	
			
	attributePanel = new Ext.Panel({
		title: '类别信息',
		id: 'attrP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 155,
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
			fieldLabel: '拼音全写',
			allowBlank: true,
			name: idName[1],
			id: idName[1]			
		},{
			fieldLabel: '拼音缩写',
			name: idName[2],
			id: idName[2]			
		},{
			fieldLabel: '排序',
			allowBlank: true,
			name: idName[3],
			id: idName[3]			
		},pickCombo]
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
	
	regionPanel = new Ext.Panel({
		title: '父类别选择',
		id: 'regionP',
		height: 280,
		layout: 'column',
		bodyStyle: 'padding:10px',
		autoHeight: true,
		autoScroll: true,
		collapsible: true,
		items: [{
			collapsible: true,
			title: '父类别选择',
			xtype: 'treepanel',
			columnWidth: .4,
			bodyStyle: 'padding:3px,margin-right:5px',
			minWidth: 80,
			height: 280,
			autoScroll: true,
			split: true,
			loader: new Ext.tree.TreeLoader({
				dataUrl: 'getRegionByNode.shtml'
			}),
			root: new Ext.tree.AsyncTreeNode({
				expanded: true,
				id: '402880e71a7fabda011a7faf12fa0001',
				text: '全分类',
//				disabled: true,
				draggable: false
			}),
			rootVisible: true,
			listeners: {
				click: function(n){
					clickRegionNode(n);
				},
				beforeload: function(node){
					this.loader.dataUrl = 'getRegionByNode.shtml';
				}
			}
		}, {
			xtype: 'panel',
			columnWidth: .02,
			border: false
		},{
			xtype: 'panel',
			id:'parentRegion',
			title: '父类别类型',
			bodyStyle: 'padding:10px 0 0 10px;',
			columnWidth: .58,
			tbar: [{
				text: '删除父节点',
				tooltip: '删除父类别',
				iconCls: 'remove',
				handler: function(){
					var temp = Ext.getCmp('parentRegion');
					temp.body.update('未选择');
					parentId = '';
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
		items: [attributePanel,areaPanel,regionPanel]
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
		fpanel.findById('parentRegion').html=outParentText;
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

function clickRegionNode(item){
	var temp = Ext.getCmp('parentRegion');
	if(parentId!==''){
		Ext.Msg.alert('提示','所属父类别只能选择一个，请先删除再修改！');
	}
	else{
		parentId=item.id;
		temp.body.update(item.text);
	}
}

function updateAjax(fpanel,tabPanel,Id,idName){
	Ext.Ajax.request({
		url: 'getSingleRegion.shtml',
		method: 'POST',
		params: {regionId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,tabPanel,idName);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载类别信息失败！');
			window.close();
		}
	});
}

function initUpdate(result,fpanel,tabPanel,idName){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.region;
		
		fpanel.findById(idName[0]).setValue(list.name);
		fpanel.findById(idName[1]).setValue(list.fullName);
		fpanel.findById(idName[2]).setValue(list.headName);
		fpanel.findById(idName[3]).setValue(list.orderBy);
		fpanel.findById('flagSelect').setValue(list.flag);
		
		tabPanel.setActiveTab(fpanel.findById('areaP'));
		if (list.area) {
			var temp = Ext.getCmp('areaIdP');
			temp.body.update(list.area.name);
			areaId = list.area.areaId;
		}
//		fpanel.findById('areaP').doLayout();
		
		tabPanel.setActiveTab(fpanel.findById('regionP'));
		temp = Ext.getCmp('parentRegion');
		temp.body.update(strList.pregion.name);
		parentId = strList.pregion.regionId;
//		fpanel.findById('regionP').doLayout();
	}	
}

/**
 * 表单保存
 * @param {Object} fpanel
 */
function save(fpanel,start){
	var urlPost;
	var paramsPost;
	
	if (addWin) {
		urlPost = 'addRegion.shtml';
		paramsPost = {
			areaId: areaId,
			parentId:parentId
		};
	}
	else 
		if (updWin) {
			urlPost = 'updateRegion.shtml';
			paramsPost = {
				regionId: selectRecord.get('regionId'),
				parentId:parentId,
				areaId: areaId
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
								node:node,
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
			title: '添加类别信息',
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
		Ext.MessageBox.alert('修改操作', '请选择要修改的类别！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('regionId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改类别信息',
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
			Ext.MessageBox.alert('提示', '请选择一条类别信息！');
		}
	}
}


function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('类别信息删除操作', '未选择，请选择要删除的类别信息！');
	}
	else {
		Ext.MessageBox.confirm('确认类别删除', '你确认类别删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('regionId');
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
		url: 'delRegion.shtml',
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
						node:node,
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
						node:node,
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
			node:node,
			start: 0,
			limit:pageSize
		}
	});
}

function search(searchText,searchSelect,searchSelect2,objectStore){
	var name=searchText.getValue();
	if(name==='请输入名称'){
		name='';
	}
	var depth=searchSelect.getValue();
	if(depth==='不限'||depth===''){
		depth=10;
	}
	var flag=searchSelect2.getValue();
	if(flag===''){
		flag=10;
	}
	objectStore.load({
					params: {
						node:node,
						depth:depth,
						flag:flag,
						name:name,
						start: 0,
						limit: pageSize
					}
				});
}