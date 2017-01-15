
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	/*==============================全局变量t=============================================*/
	var fpanel,tabPanel;
	var objectGrid;
	var selectRecord;
	var cbsm;
	var objectStore,pickStore;
	initView();
});

var pageSize =27;
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
	var idName = new Array('title','name','tripFlagId','remark');
	
	var pageRegionPagingToolbar = Ext.extend(Ext.PagingToolbar, {
    	paramNames : {start: 'start', limit: 'limit',flagId: 'flagId'},
    	doLoad: function(start){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.flagId] = flagId;
			this.store.load({
				params: o
			});
		}
	});
	
	var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'classId',
        fields: [
            {name: 'classId', mapping:'classId'},
			{name: 'title', mapping:'title'},
            {name: 'name', mapping:'name'},
			{name: 'remark', mapping:'remark'},
			{name: 'flagName', mapping:'tripFlag.title'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllPageRegion.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'name', direction: "DESC"}
        });
		
	objectStore.load({
		params: {
			flagId:flagId,
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
            {id:'classId',header: '标识号', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'classId'},
			{header: '中文标题', hideable:true, sortable: true, 
			dataIndex: 'title'},
			{header: '名称', hideable:true, sortable: true, 
			dataIndex: 'name'},
			{header: '备注', hideable:true, sortable: true, 
			dataIndex: 'remark'},
			{header: '所属类别', hideable:true, sortable: true, 
			dataIndex: 'flagName'}
        ],
		sm: cbsm,
		
		view: new Ext.grid.GroupingView({
			forceFit: true,
			showGroupName: false,
			enableNoGroups: true, // REQUIRED!
			hideGroupedColumn: false,
			groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "条" : "条"]})'
		}),
		
		bbar: new pageRegionPagingToolbar({
			pageSize: pageSize,
			store: objectStore,
			displayInfo: true,
			displayMsg: '显示 {0}-{1}条 / 共 {2} 条',
			refreshText:'刷新页面类别信息',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新页面类别信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
			text: '添加',
			tooltip: '添加页面类别信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		},'-', {
			text: '修改',
			tooltip: '修改页面类别信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '删除',
			tooltip: '删除页面类别信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}		
		}],
		
		frame: true,
//		title:'页面类别信息表',
		region: 'center',
		height: 150,
		autoWidth: true,
		animCollapse: false,
		trackMouseOver: true
	});
	return objectGrid;
}

function comCreate(idName,actions,start){
	var attributePanel;
	
	var pickStore= new Ext.data.SimpleStore({
		fields:['returnValue','displayValue'],
		proxy: new Ext.data.HttpProxy({
			url: 'getTripFlagInfo.shtml',
			method: 'POST'
		})
	});
	
	var pickCombo= new Ext.form.ComboBox({
		store: pickStore,
		id: 'tripFlagSelect',
		name: 'tripFlagId',
		hiddenName: 'tripFlagId',
		valueField: 'returnValue',
		displayField: 'displayValue',
		fieldLabel: '类型类别',
		allowBlank: false,
//		hideLabel: true,
//		labelSeparator: '',
		typeAhead: true,
//		disabled: true,
		mode: 'remote',
		anchor: '95%',
		bodyStyle: 'padding:3px,margin-bottom:6px',
		triggerAction: 'all',
		emptyText: '请选择一个类型',
		loadingText: '正在加载请等待....',
		forceSelection: true,
		selectOnFocus: true
	});
	
	attributePanel = new Ext.Panel({
//		title:'页面类别属性',
		id: 'attrP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 170,
		defaults: {
			xtype: 'textfield',
			width: 230,
			allowBlank: false,
			anchor: '95%'
		},
		
		items: [{
			fieldLabel: '页面类别标题',
			name: idName[0],
			id: idName[0]
		},{
			fieldLabel: '页面类别名称',
			name: idName[1],
			id: idName[1]
		},{
			fieldLabel: '类别备注',
			xtype:'textarea',
			allowBlank: true,
			name: idName[3],
			id: idName[3]
		},pickCombo]
	});
	
	fpanel = new Ext.FormPanel({
		region: 'center',
		fileUpload: true,
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
		updateAjax(fpanel,actions,idName);
	}	
}

function updateAjax(fpanel,Id,idName){
	Ext.Ajax.request({
		url: 'getSinglePageRegion.shtml',
		method: 'POST',
		params: {tripModelId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,idName);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载页面类别信息失败！');
			window.close();
		}
	});
}

function initUpdate(result,fpanel,idName){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.results[0];
		
		fpanel.findById(idName[0]).setValue(list.title);
		fpanel.findById(idName[1]).setValue(list.name);
		fpanel.findById(idName[3]).setValue(list.remark);
		Ext.get('tripFlagSelect').dom.value=list.tripFlag.title;
		Ext.get(idName[2]).dom.value=list.tripFlag.id;
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
		urlPost = 'addPageRegion.shtml';
	}
	else 
		if (updWin) {
			urlPost = 'updatePageRegion.shtml';
			paramsPost = {
				tripModelId: selectRecord.get('classId')
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
				Ext.MessageBox.alert('保存失败', '输入的英文名称已经存在，请重新输入！');
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
						objectStore.load({
							params: {
								flagId:flagId,
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
}

function addItem(idName){
	addWin = Ext.getCmp('addW');
	
	if (!addWin) {
		comCreate(idName,'add','');
		addWin = new Ext.Window({
			title: '添加页面类别信息',
			id: 'addW',
			closable: true,
			width: 580,
			height: 250,
			modal: true,
			layout: 'fit',
			items: [fpanel]
		});
	}
	addWin.show();
	if (flagId !== '') {
		Ext.get('tripFlagSelect').dom.value = flagTitle;
		Ext.get('tripFlagId').dom.value = flagId;
	}
}

function updateItem(idName,start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的页面类别！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('classId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改页面类别信息',
					id: 'updW',
					closable: true,
					width: 580,
					height: 250,
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
			Ext.MessageBox.alert('提示', '请选择一条页面类别信息！');
		}
	}
}

function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('页面类别信息删除操作', '未选择，请选择要删除的页面类别信息！');
	}
	else {
		Ext.MessageBox.confirm('确认页面类别删除', '你确认页面类别删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('classId');
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
		url: 'delPageRegion.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('页面类别信息删除操作', '删除页面类别信息成功！');
			}
			else
			{
				Ext.MessageBox.alert('删除操作', returnJosn.msg);
				objectStore.load({
					params: {
						flagId:flagId,
						start: start,
						limit: pageSize
					}
				});
			}
		},
		failure: function(result, request){
			Ext.MessageBox.alert('页面类别信息删除操作', '删除页面类别信息失败！');
			objectStore.load({
					params: {
						flagId:flagId,
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
			flagId:flagId,
			start: 0,
			limit:pageSize
		}
	});
}