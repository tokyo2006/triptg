
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
	var idName = new Array('name','flag');
	/*
	 * 数据解析器
	 */
	var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'pictureId',
        fields: [
            {name: 'pictureId', mapping:'pictureId'},
            {name: 'name', mapping:'name'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllPic.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'name', direction: "DESC"}
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
            {id:'pictureId',header: '标识号', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'pictureId'},
			{header: '名称', hideable:true, sortable: true, 
			dataIndex: 'name'}
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
			refreshText:'刷新图片信息',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新图片信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
			text: '添加',
			tooltip: '添加图片信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		},'-', {
			text: '修改',
			tooltip: '修改图片信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '删除',
			tooltip: '删除图片信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}		
		}],
		
		frame: true,
		title:'图片信息表',
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
		title:'图片属性',
		id: 'attrP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 150,
		defaults: {
			xtype: 'textfield',
			width: 230,
			allowBlank: false,
			anchor: '95%'
		},
		
		items: [{
			fieldLabel: '图片名称',
			name: idName[0],
			id: idName[0]
		},{
			fieldLabel: '图片',
			name: 'upload',
			inputType: 'file',
			id: idName[1]
		}]
	});
	
	var pickStore= new Ext.data.SimpleStore({
		fields:['displayValue','returnValue'],
		proxy: new Ext.data.HttpProxy({
			url: 'getSceneryInfo.shtml',
			method: 'POST'
		})
	});
	
	var pickCombo= new Ext.form.ComboBox({
		store: pickStore,
		id: 'scenerySelect',
		name: 'sceneryId',
		hiddenName: 'sceneryId',
		valueField: 'returnValue',
		displayField: 'displayValue',
		fieldLabel: '类型选择',
		allowBlank: false,
		hideLabel: true,
		labelSeparator: '',
		typeAhead: true,
		disabled: true,
		mode: 'local',
		anchor: '95%',
		bodyStyle: 'padding:3px,margin-bottom:6px',
		triggerAction: 'all',
		emptyText: '请选择一个类型',
		loadingText: '正在加载请等待....',
		forceSelection: true,
		selectOnFocus: true
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
					if (n.isLeaf()) {
						clickAreaNode(n);
					}
					else {
						n.expand();
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
			xtype: 'fieldset',
			id:'reAreaP',
			title: '区域类型',
			bodyStyle: 'padding:10px 0 0 10px;',
			columnWidth: .58,
			items:[pickCombo],
			collapsed: false,
			height: 270
			
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
		fileUpload: true,
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
	Ext.getCmp('scenerySelect').setDisabled(false);
	Ext.getCmp('scenerySelect').store.reload({params:{areaId:item.id}});
}

function updateAjax(fpanel,tabPanel,Id,idName){
	Ext.Ajax.request({
		url: 'getSinglePic.shtml',
		method: 'POST',
		params: {picId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,tabPanel,idName);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载图片信息失败！');
			window.close();
		}
	});
}

function initUpdate(result,fpanel,tabPanel,idName){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.pic;
		
		fpanel.findById(idName[0]).setValue(list.name);
		tabPanel.setActiveTab(fpanel.findById('areaP'));
		Ext.getCmp('scenerySelect').setDisabled(false);
		Ext.get('sceneryId').dom.value = list.scenery.sceneryId;
		Ext.get('scenerySelect').dom.value = list.scenery.name;
		
		var initDepth;
		initDepth = list.areas[0].depth;
		reArea = list.areas[0].areaId;
		for (var i = 1; i < list.areas.length; i++) {
			if (list.areas[i].depth > initDepth) {
				initDepth = list.areas[i].depth;
				reArea = list.areas[i].areaId;
			}
		}
		
		Ext.getCmp('scenerySelect').store.load({
			params: {
				areaId: reArea
			}
		});
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
		urlPost = 'addPic.shtml';
	}
	else 
		if (updWin) {
			urlPost = 'updatePic.shtml';
			paramsPost = {
				picId: selectRecord.get('pictureId')
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
}

function addItem(idName){
	addWin = Ext.getCmp('addW');
	
	if (!addWin) {
		comCreate(idName,'add','');
		addWin = new Ext.Window({
			title: '添加图片信息',
			id: 'addW',
			closable: true,
			width: 580,
			height: 400,
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
		Ext.MessageBox.alert('修改操作', '请选择要修改的图片！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('pictureId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改图片信息',
					id: 'updW',
					closable: true,
					width: 580,
					height: 400,
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
			Ext.MessageBox.alert('提示', '请选择一条图片信息！');
		}
	}
}


function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('图片信息删除操作', '未选择，请选择要删除的图片信息！');
	}
	else {
		Ext.MessageBox.confirm('确认图片删除', '你确认图片删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('pictureId');
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
		url: 'delPic.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('图片信息删除操作', '删除图片信息成功！');
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
			Ext.MessageBox.alert('图片信息删除操作', '删除图片信息失败！');
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