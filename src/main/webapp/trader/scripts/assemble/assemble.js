	

Ext.onReady(function(){
	//get All unit Name for comboBox.
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	/*==============================全局变量t=============================================*/
	var id = 1; 
	var contentPanel, fpanel;
	var objectGrid;
	var selectRecord;
	var cbsm;
	var objectStore;
	
	initView();
});

var pageSize = 27;
var addWin,updWin;

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
	var idName = new Array('name','timeType');   //'areaId','areaSelect');
	/*
	 * 数据解析器
	 */
	var assemblereader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'assembleId',
        fields: [
            {name: 'assembleId', type: 'string',mapping:'assembleId'},
            {name: 'name', type: 'string',mapping:'name'},
			{name: 'areaName', type: 'string',mapping:'area.name'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: assemblereader,
			proxy: new Ext.data.HttpProxy({url: 'getAllAssemble.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'assembleId', direction: "DESC"}
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
		     cbsm, //CheckBox选择列, TMD有Ctrl和shift组合功能
            {id:'assembleId',header: '接送地编号', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'assembleId'},
            {id:'name',header: '接送地名称', hideable:false, sortable: true,
			dataIndex: 'name'},
			{header: '出发地', hideable:true, sortable: true,
			dataIndex: 'areaName'}
        ],
		sm: cbsm,
		
		view: new Ext.grid.GroupingView({
			forceFit: true,
			showGroupName: false,
			enableNoGroups: false, // REQUIRED!
			hideGroupedColumn: true,
			groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "条" : "条"]})'
		}),
		
		bbar: new Ext.PagingToolbar({
			pageSize: pageSize,
			store: objectStore,
			displayInfo: true,
			displayMsg: '显示 {0}-{1}条 / 共 {2} 条',
			refreshText:'刷新接送地',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新线路信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
			text: '添加',
			tooltip: '添加接送地信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		}, '-', {
			text: '修改',
			tooltip: '修改接送地信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '删除',
			tooltip: '删除接送地信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}		
		}],
		
//		listeners: {
//			'rowdblclick': {
//				fn: rowdblclick,
//				scope: this
//			}
//		},
		frame: true,
		title:'出发集合地信息表',
		region: 'center',
		height: 150,
		autoWidth: true,
//		collapsible: true,
		animCollapse: false,
		trackMouseOver: true
	});
	return objectGrid;
}

function comCreate(idName,actions,start){
	var attributePanel;
		
	attributePanel = new Ext.Panel({
//		title: '接送地属性',
		id: 'attributeP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 50,
		defaults: {
			width: 230,
			anchor: '95%'
		},
		
		items: [{
			xtype: 'textfield',
			fieldLabel: '接送地标识名',
			name: idName[0],
			id: idName[0],
			allowBlank: false
		}
//		,{
//			fieldLabel: '时间准确否',
//			xtype: 'checkbox',
////			labelStyle:'width:150px;',
////			boxLabel: '帐号有效',
//			id: idName[1],
////			hideLabel: true,
//			checked:true,
////			labelSeparator: '',
//			name: idName[1],
//			inputValue: '1',
//			anchor: '95%'
//		}
		]
	});

/*	
	contentPanel = new Ext.Panel({
		title: '接送地信息',
		id: 'contentP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		height: 290,
		autoScroll: true,
		
		tbar: [{
			text: '添加接送地',
			tooltip: '添加接送地',
			handler: function(){
				addContent();
			},
			iconCls: 'add'
		}, '-', {
			text: '删除接送地',
			tooltip: '删除已有接送地',
			handler: function(){
				delContent();
			},
			iconCls: 'remove'
		}]
	});*/
	
//	tabpanel = new Ext.TabPanel({
//		activeTab: 0,
//		autoHeight: true,
//		items: [attributePanel, contentPanel]
//	});
	
	fpanel = new Ext.FormPanel({
		region: 'center',
		bodyStyle: 'padding:5px',
		collapsible: true,
		items: [attributePanel],
		
		buttons: [{
			text: '保存',
			handler: function(){
				save(fpanel,start);
			}
		}, {
			text: '重置',
			handler: function(){
				reset(fpanel);
			}
		}, {
			text: '取消',
			handler: function(){
				cancel();
			}
		}]
	});
	
	if(actions!='add'){
		updateAjax(fpanel,actions,idName);
	}
}

function updateAjax(fpanel,Id,idName){
	Ext.Ajax.request({
		url: 'getSingleAssemble.shtml',
		method: 'POST',
		params: {assembleId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,idName);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败！');
			window.close();
		}
	});
}

function initUpdate(result,fpanel,idName){
	var assembleList = doJSON(result.responseText);
	var list = assembleList.results;
	
	fpanel.findById(idName[0]).setValue(list[0].name);
/*	fpanel.findById(idName[1]).setValue(list[0].timeType);
	
//	Ext.get(idName[1]).dom.value=list[0].area.areaId;
//	Ext.get(idName[2]).dom.value=list[0].area.name;

	var ContentList = new Array();
	ContentList = list[0].content.split("###");
	if (ContentList.length > 0) {
		var Content = new Array();
		for (var i = 1; i <= ContentList.length; i++) {
			Content = ContentList[i - 1].split("@@@");
			id = i;
			addContent();
			contentPanel.findById('assAddress' + i).setValue(Content[0]);
			contentPanel.findById('assTime' + i).setValue(Content[1]);
			contentPanel.findById('assPrice' + i).setValue(Content[2]);
		}
	}*/
}

/*
function addContent(){
	contentPanel.add({
		xtype: 'fieldset',
		title: '第' + id + '出接送地',
		id: id + 'ass',
		defaults: {
			xtype: 'textfield',
			allowBlank: false,
			width: 230,
			anchor: '95%'
		},
		autoHeight: true,
		collapsed: false, //已经折叠否
		items: [{
			fieldLabel: '集合地',
			name: 'assAddress' + id,
			id: 'assAddress' + id
		}, {
			xtype:'timefield',
			fieldLabel: '集合时间',
			name: 'assTime' + id,
			id: 'assTime' + id,
			minValue: '0:00',
			maxValue: '23:45',
			format:'H:i',
			emptyText:'请选择一个出发时间',
			forceSelection:true
		}, {
			fieldLabel: '接送价格',
			name: 'assPrice' + id,
			id: 'assPrice' + id,
			vtype: 'posint'
		}]
	
	});
	id++;
	contentPanel.show();
	contentPanel.doLayout();
}

function delContent(){
	if (id > 1) {
		var tempfieldset = contentPanel.findById(--id + 'ass');
		contentPanel.remove(tempfieldset);
		tempfieldset.destroy();
	}
}*/

function save(fpanel,start){
	var urlPost;
	var paramsPost;
	
	/*
	var contentP = Ext.getCmp('contentP');
	var jArray = contentP.findByType('fieldset');
	var jNumber = jArray.length;
	var assemble = new Array();
	
	for (var i = 1; i <= jNumber; i++) {
		var fstemp = contentP.findById(i + 'ass');
		var ftArray = new Array();
		ftArray[0] = fstemp.findById('assAddress' + i).getValue();
		ftArray[1] = fstemp.findById('assTime' + i).getValue();
		ftArray[2] = fstemp.findById('assPrice' + i).getValue();
		
		var ftArraystring = ftArray.join("@@@");
		assemble[i - 1] = ftArraystring;
	}
	
	var content = assemble.join("###");*/
	
	if (addWin) {
		urlPost = 'addAssemble.shtml';
//		paramsPost = {
//			content: content
//		};
	}
	else 
		if (updWin) {
			urlPost = 'updateAssemble.shtml';
			paramsPost = {
//				content: content,
				assembleId: selectRecord.get('assembleId')
			};
		}
		else {
			Ext.Msg.alert('信息', '提交窗口错误参数!');
			window.close();
		}
	
	//提交数据
	if (fpanel.form.isValid()) {
//		if (isContentValid(contentP)) {
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
					Ext.MessageBox.alert('保存成功', '信息保存成功');
					if (addWin) {
						addWin.close();
						addWin = '';
					}
					if (updWin) {
						updWin.close();
						updWin = '';
					}
					
					objectStore.load({
						params: {
							start: start,
							limit: pageSize
						}
					});
				}
			});
//		}
//		else {
//			Ext.Msg.alert('信息', '行程内容必须填写完整!');
//		}
	}
	else {
		Ext.Msg.alert('信息', '请填写完成再提交!');
	}
}

/*
function isContentValid(contentP){
	if (contentP.findByType('textfield').length == 0) {
		return false;
	}
	for (var i = 0; i < contentP.findByType('textfield').length; i++) {
		if (contentP.findByType('textfield')[i].getValue() == "") {
			return false;
		}
	}
	for (var i = 0; i < contentP.findByType('textarea').length; i++) {
		if (contentP.findByType('textarea')[i].getValue() == "") {
			return false;
		}
	}
	return true;
}*/

function reset(fpanel){
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
}


function addItem(idName){
	addWin = Ext.getCmp('addW');
	
	id = 1;//window自带的close关闭用
	if (!addWin) {
		comCreate(idName,'add',0);
		addWin = new Ext.Window({
			title: '添加接送地',
			id: 'addW',
			closable: true,
			width: 580,
			height: 130,
			modal: true,
			layout: 'fit',
			items: [fpanel]
		});
	}
	addWin.show();
}

function updateItem(idName,start){
	id = 1;
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的接送地！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('assembleId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改接送地',
					id: 'updW',
					closable: true,
					width: 580,
					height: 130,
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
			Ext.MessageBox.alert('提示', '请选择一条接送地！');
		}
	}
}


function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('接送地删除操作', '未选择，请选择要删除的接送地！');
	}
	else {
		Ext.MessageBox.confirm('确认删除', '删除接送地将同时删除与该接送地相关的团队，你确认删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsondata = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('assembleId');
					if (i === 0) {
						jsondata = jsondata + ss;
					}
					else {
						jsondata = jsondata + "," + ss;
					}
					objectStore.remove(m[i]);
				}				
			delAjax(start,jsondata);				
			}
		});
	}
}

function delAjax(start,jsondata){
	Ext.Ajax.request({
		url: 'delAssemble.shtml',
		method: 'POST',
		params: {
			delData: jsondata
		},
		success: function(result, request){
			Ext.MessageBox.alert('接送地删除操作', '删除接送地成功！');
		},
		failure: function(result, request){
			Ext.MessageBox.alert('接送地删除操作', doJSON(result.responseText).msg);
			objectStore.load({
					params: {
						start: start,
						limit: pageSize,
						delData: jsondata
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