
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
var addWin, updWin,fileUpWin;
var areaId='';

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
	var idName = new Array('title','subTitle','picUrl','url','price','target','font','desrible','areaId','shangjia');
	
	var pageContentPagingToolbar = Ext.extend(Ext.PagingToolbar, {
    	paramNames : {start: 'start', limit: 'limit',classId: 'classId'},
    	doLoad: function(start){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.classId] = classId;
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
		successProperty:'success', 
		idProperty:'id',
        fields: [
            {name: 'id', mapping:'id'},
			{name: 'title', mapping:'title'},
			{name: 'subTitle', mapping:'subTitle'},
			{name: 'picUrl', mapping:'picUrl'},
			{name: 'url', mapping:'url'},
			{name: 'price', mapping:'price'},
			{name: 'target', mapping:'target'},
			{name: 'font', mapping:'font'},
			{name: 'regionTitle', mapping:'tripModel.title'},
			{name: 'desrible', mapping:'desrible'},
			{name: 'shangjia', mapping:'shangjia'},
			{name: 'areaName', mapping:'area'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllPageContent.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'title', direction: "DESC"}
        });
		
	objectStore.load({
		params: {
			classId:classId,
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
            {id:'id',header: '标识号', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'id'},
			{header: '标题', hideable:true, sortable: true, 
			dataIndex: 'title'},
			{header: '标签', hideable:true, sortable: true, 
			dataIndex: 'subTitle'},
			{header: '图片url', hideable:true, sortable: true,renderer:function(v,p,record){
				if(v===''||v===null){
					return '<font color=\"#ee928f\">无</font>';
				}
				else{
					return '有';
				}
			}, 
			dataIndex: 'picUrl'},
			{header: '超链接', hideable:true, sortable: true, 
			dataIndex: 'url'},
			{header: '价格', hideable:true, sortable: true, 
			dataIndex: 'price'},
			{header: '打开方式', hideable:true, sortable: true,renderer:function(v,p,record){
				if(v==='_self'){
					return '当前页';
				}
				else{
					return '新页面';
				}
			},
			dataIndex: 'target'},
			{header: '所属类别', hideable:true, sortable: true, 
			dataIndex: 'regionTitle'},
			{header: '所属地域', hideable:true, sortable: false, renderer:function(v,p,record){
				if(v){
					return v.name;
				}
				else{
					return '未选择';
				}
			},
			dataIndex: 'areaName'},
			{header: '加粗', hideable:true, sortable: true, 
			dataIndex: 'font'},
			{header: '上架', hideable:true, sortable: true, renderer:function(v,p,record){
				if(parseInt(v)===0){
					return '<font color="#ff0000">下架<font/>';
				}
				else{
					return '上架';
				}
			},
			dataIndex: 'shangjia'},
			{header: '描述', hideable:true, sortable: true, 
			dataIndex: 'desrible'}		
        ],
		sm: cbsm,
		
		view: new Ext.grid.GroupingView({
			forceFit: true,
			showGroupName: false,
			enableNoGroups: true, // REQUIRED!
			hideGroupedColumn: false,
			groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "条" : "条"]})'
		}),
		
		bbar: new pageContentPagingToolbar({
			pageSize: pageSize,
			store: objectStore,
			displayInfo: true,
			displayMsg: '显示 {0}-{1}条 / 共 {2} 条',
			refreshText:'刷新页面信息',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新页面信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
			text: '添加',
			tooltip: '添加页面信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		},'-', {
			text: '修改',
			tooltip: '修改页面信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '删除',
			tooltip: '删除页面信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}		
		}],
		
		frame: true,
		region: 'center',
		height: 150,
		autoWidth: true,
		animCollapse: false,
		trackMouseOver: true
	});
	return objectGrid;
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

function comCreate(idName,actions,start){
	var attributePanel,areaPanel;
	var pickStore= new Ext.data.SimpleStore({
		fields:['displayValue','returnValue'],
		data:[['粗体',1],['其他',2]]
	});
	
	var pickCombo= new Ext.form.ComboBox({
		store: pickStore,
		id: 'fontSelect',
		name: idName[6],
		hiddenName: idName[6],
		valueField: 'returnValue',
		displayField: 'displayValue',
		fieldLabel: '字型选择',
		allowBlank: false,
		typeAhead: true,
		mode: 'local',
		value:'2',
		anchor: '95%',
		bodyStyle: 'padding:3px,margin-bottom:6px',
		triggerAction: 'all',
		emptyText: '请选择一个字型',
		loadingText: '正在加载请等待....',
		forceSelection: true,
		selectOnFocus: true
	});	
	var pickStore2= new Ext.data.SimpleStore({
		fields:['displayValue','returnValue'],
		data:[['当前页打开','_self'],['新开页面','_blank']]
	});
	
	var pickCombo2= new Ext.form.ComboBox({
		store: pickStore2,
		id: 'targetSelect',
		name: idName[5],
		hiddenName: idName[5],
		valueField: 'returnValue',
		displayField: 'displayValue',
		fieldLabel: '页面打开方式',
		allowBlank: false,
		typeAhead: true,
		mode: 'local',
		value:'_blank',
		anchor: '95%',
		bodyStyle: 'padding:3px,margin-bottom:6px',
		triggerAction: 'all',
		emptyText: '请选择一个方式',
		loadingText: '正在加载请等待....',
		forceSelection: true,
		selectOnFocus: true
	});
	
	var pickStore3= new Ext.data.SimpleStore({
		fields:['returnValue','displayValue'],
		proxy: new Ext.data.HttpProxy({
			url: 'getPageRegionInfo.shtml',
			method: 'POST'
		})
	});
	
	var pickCombo3= new Ext.form.ComboBox({
		store: pickStore3,
		id: 'regionSelect',
		name: 'tripModelId',
		hiddenName: 'tripModelId',
		valueField: 'returnValue',
		displayField: 'displayValue',
		fieldLabel: '页面类别',
		allowBlank: false,
//		hideLabel: true,
//		labelSeparator: '',
		typeAhead: true,
//		disabled: true,
		mode: 'remote',
		anchor: '95%',
		bodyStyle: 'padding:3px,margin-bottom:6px',
		triggerAction: 'all',
		emptyText: '请选择一个类别',
		loadingText: '正在加载请等待....',
		forceSelection: true,
		selectOnFocus: true
	});
				
	attributePanel = new Ext.Panel({
		id: 'attrP',
		title:'内容属性',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 320,
		defaults: {
			xtype: 'textfield',
			width: 230,
			allowBlank: false,
			anchor: '95%'
		},
		
		items: [{
			fieldLabel: '页面标题',
			name: idName[0],
			id: idName[0]
		},{
			fieldLabel: '页面标签',
			allowBlank: true,
			name: idName[1],
			id: idName[1]
		},{
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
				html: '<font style:\"font-size:24px;\">图片:</font>'
			}, {
				columnWidth: .64,
				items: [{
					xtype: 'textfield',
					width: 320,
//					allowBlank: false,
					disabled: true,
					anchor: '95%',
					name: idName[2],
					id: idName[2]
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
			fieldLabel: '超链接',
			name: idName[3],
			id: idName[3]
		},{
			xtype: 'checkbox',
			fieldLabel: '上架',
			id: idName[9],
			checked: true,
			labelSeparator: '',
			name: idName[9],
			inputValue: '1'
		},{
			fieldLabel: '价格',
			allowBlank: true,
			name: idName[4],
			id: idName[4]
		},pickCombo,pickCombo2,pickCombo3,{
			xtype:'textarea',
			fieldLabel: '描述',
			allowBlank: true,
			name: idName[7],
			id: idName[7]
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

function updateAjax(fpanel,tabPanel,Id,idName){
	Ext.Ajax.request({
		url: 'getSinglePageContent.shtml',
		method: 'POST',
		params: {tripContentId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,tabPanel,idName);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载页面信息失败！');
			window.close();
		}
	});
}

function initUpdate(result,fpanel,tabPanel,idName){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.results[0];
		
		fpanel.findById(idName[0]).setValue(list.title);
		fpanel.findById(idName[1]).setValue(list.subTitle);
		fpanel.findById(idName[2]).setValue(list.picUrl);
		fpanel.findById(idName[3]).setValue(list.url);
		fpanel.findById(idName[4]).setValue(list.price);
		fpanel.findById('targetSelect').setValue(list.target);
		fpanel.findById('fontSelect').setValue(list.font);
		fpanel.findById(idName[7]).setValue(list.desrible);
		fpanel.findById(idName[9]).setValue(list.shangjia);
		Ext.get('regionSelect').dom.value = list.tripModel.title;
		Ext.get('tripModelId').dom.value = list.tripModel.classId;
		
		tabPanel.setActiveTab(fpanel.findById('areaP'));
		if (list.area) {
			var temp = Ext.getCmp('areaIdP');
			temp.body.update(list.area.name);
			areaId = list.area.areaId;
		}
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
//	if (areaId === '') {
//		checkValue = false;
//	}
	
	fpanel.findById('picUrl').enable();
	if (addWin) {
		urlPost = 'addPageContent.shtml';
		paramsPost = {
			areaId: areaId,
			classId: classId
		};
	}
	else 
		if (updWin) {
			urlPost = 'updatePageContent.shtml';
			paramsPost = {
				areaId: areaId,
				tripContentId: selectRecord.get('id'),
				classId:classId
			};
		}
		else {
			Ext.Msg.alert('信息', '提交窗口错误参数!');
			window.close();
		}
	//提交数据
	if (fpanel.form.isValid()&& checkValue) {
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
						areaId='';
						objectStore.load({
							params: {
								classId:classId,
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
	areaId='';
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
			title: '添加页面信息',
			id: 'addW',
			closable: true,
			width: 580,
			height: 425,
			modal: true,
			layout: 'fit',
			items: [fpanel]
		});
	}
	addWin.show();
	if (classId !== '') {
		Ext.get('regionSelect').dom.value = regionTitle;
		Ext.get('tripModelId').dom.value = classId;
	}
}

function updateItem(idName,start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的页面！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('id'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改页面信息',
					id: 'updW',
					closable: true,
					width: 580,
					height: 425,
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
			Ext.MessageBox.alert('提示', '请选择一条页面信息！');
		}
	}
}

function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('页面信息删除操作', '未选择，请选择要删除的页面信息！');
	}
	else {
		Ext.MessageBox.confirm('确认页面删除', '你确认页面删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('id');
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
		url: 'delPageContent.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('页面信息删除操作', '删除页面信息成功！');
			}
			else
			{
				Ext.MessageBox.alert('删除操作', returnJosn.msg);
				objectStore.load({
					params: {
						classId:classId,
						start: start,
						limit: pageSize
					}
				});
			}
		},
		failure: function(result, request){
			Ext.MessageBox.alert('页面信息删除操作', '删除页面信息失败！');
			objectStore.load({
					params: {
						classId:classId,
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
			classId:classId,
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
		oldUrl = Ext.getCmp('picUrl').getValue();
	}
	if (fileUpWin) {
		urlPost = 'uploadPic.shtml';
		paramsPost = {
			picUrl: oldUrl
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
							Ext.getCmp('picUrl').setValue(returnJosn.url);
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