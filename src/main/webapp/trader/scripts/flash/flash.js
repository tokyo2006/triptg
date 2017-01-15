
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
	var idName = new Array('title','subTitle','od','target','url','picUrl','breUrl','flashTypeId','name');
	
	var flashPagingToolbar = Ext.extend(Ext.PagingToolbar, {
    	paramNames : {start: 'start', limit: 'limit',flashTypeId: 'flashTypeId'},
    	doLoad: function(start){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.flashTypeId] = flashTypeId;
			this.store.load({
				params: o
			});
		}
	});
	
	var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'id',
        fields: [
            {name: 'id', mapping:'id'},
			{name: 'title', mapping:'title'},
			{name: 'name', mapping:'name'},
			{name: 'subTitle', mapping:'subTitle'},
            {name: 'od', mapping:'od'},
			{name: 'target', mapping:'target'},
			{name: 'url', mapping:'url'},
			{name: 'picUrl', mapping:'picUrl'},
			{name: 'breUrl', mapping:'breUrl'},
			{name: 'typeName', mapping:'flashType.title'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllFlash.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'name', direction: "DESC"}
        });
		
	objectStore.load({
		params: {
			flashTypeId:flashTypeId,
			start: 0,
			limit: pageSize
		}
	});
	
	cbsm = new Ext.grid.CheckboxSelectionModel();
	var rn = new Ext.grid.RowNumberer();
	
	objectGrid = new Ext.grid.GridPanel({
		loadMask: true,
		ds: objectStore,
		columns: [
		     rn, //行号列 
		     cbsm, 
            {id:'id',header: '标识号', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'id'},
			{header: '中文标题', hideable:true, sortable: true, 
			dataIndex: 'title'},
			{header: '标签', hideable:true, sortable: true, 
			dataIndex: 'subTitle'},
			{header: '名称', hideable:true, sortable: true, 
			dataIndex: 'name'},
			{header: '顺序', hideable:true, sortable: true, 
			dataIndex: 'od'},
			{header: '打开方式', hideable:true, sortable: true,renderer:function(v,p,record){
				if(v==='_self'){
					return '当前页';
				}
				else{
					return '新页面';
				}
			}, 
			dataIndex: 'target'},
			{header: '超链接', hideable:true, sortable: true, 
			dataIndex: 'url'},
			{header: '图片', hideable:true, sortable: true, renderer:function(v,p,record){
				if(v===''||v===null){
					return '<font color=\"#ee928f\">无</font>';
				}
				else{
					return '有';
				}
			},
			dataIndex: 'picUrl'},
			{header: '类别', hideable:true, sortable: true, 
			dataIndex: 'typeName'}
        ],
		sm: cbsm,
		
		view: new Ext.grid.GroupingView({
			forceFit: true,
			showGroupName: false,
			enableNoGroups: true, // REQUIRED!
			hideGroupedColumn: false,
			groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "条" : "条"]})'
		}),
		
		bbar: new flashPagingToolbar({
			pageSize: pageSize,
			store: objectStore,
			displayInfo: true,
			displayMsg: '显示 {0}-{1}条 / 共 {2} 条',
			refreshText:'刷新Flash信息',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新Flash信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
			text: '添加',
			tooltip: '添加Flash信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		},'-', {
			text: '修改',
			tooltip: '修改Flash信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '删除',
			tooltip: '删除Flash信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}		
		}],
		
		frame: true,
//		title:'Flash信息表',
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
			url: 'getFlashTypeInfo.shtml',
			method: 'POST'
		})
	});

	var pickCombo= new Ext.form.ComboBox({
		store: pickStore,
		id: 'flashTypeSelect',
		name: idName[7],
		hiddenName: idName[7],
		valueField: 'returnValue',
		displayField: 'displayValue',
		fieldLabel: 'Flash类别',
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
	var pickStore2= new Ext.data.SimpleStore({
		fields:['displayValue','returnValue'],
		data:[['当前页打开','_self'],['新开页面','_blank']]
	});
	
	var pickCombo2= new Ext.form.ComboBox({
		store: pickStore2,
		id: 'targetSelect',
		name: idName[3],
		hiddenName: idName[3],
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
	attributePanel = new Ext.Panel({
//		title:'Flash属性',
		id: 'attrP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 260,
		defaults: {
			xtype: 'textfield',
			width: 230,
//			allowBlank: false,
			anchor: '95%'
		},
		
		items: [{
			fieldLabel: 'Flash中文标题',
			name: idName[0],
			id: idName[0]
		},{
			fieldLabel: 'Flash标签',
			name: idName[1],
			id: idName[1]
		},{
			fieldLabel: 'Flash名称',
			name: idName[8],
			id: idName[8]
		},{
			fieldLabel: '顺序',
			name: idName[2],
			id: idName[2]
		},pickCombo2,{
			fieldLabel: '超链接',
			allowBlank: false,
			name: idName[4],
			id: idName[4]
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
				html: '<font style:\"font-size:24px;\">大图:</font>'
			}, {
				columnWidth: .64,
				items: [{
					xtype: 'textfield',
					width: 320,
					allowBlank: false,
					disabled: true,
					anchor: '95%',
					name: idName[5],
					id: idName[5]
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
			xtype: 'panel',
			id: 'columnP2',
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
				html: '<font style:\"font-size:24px;\">小图:</font>'
			}, {
				columnWidth: .64,
				items: [{
					xtype: 'textfield',
					width: 320,
					allowBlank: false,
					disabled: true,
					anchor: '95%',
					name: idName[6],
					id: idName[6]
				}]
			}, {
				columnWidth: .15,
				items: [{
					xtype: 'button',
					text: '文件上传',
					name: 'clickBtn',
					id: 'clickBtn',
					handler: function(){
						fileUp(1)
					}
				}]
			}]
		},pickCombo]
	});
	
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
				reset(fpanel,idName);
			}
		}, {
			text: '取消',
			handler: function(){
				cancel();
			}
		}]
	});
	
	if (actions !== 'add') {
		updateAjax(fpanel, actions, idName);
	}
}

function updateAjax(fpanel,Id,idName){
	Ext.Ajax.request({
		url: 'getSingleFlash.shtml',
		method: 'POST',
		params: {flashId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,idName);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载Flash信息失败！');
			window.close();
		}
	});
}

function initUpdate(result,fpanel,idName){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.results[0];
		
		fpanel.findById(idName[0]).setValue(list.title);
		fpanel.findById(idName[1]).setValue(list.subTitle);
		fpanel.findById(idName[2]).setValue(list.od);
		fpanel.findById('targetSelect').setValue(list.target);
		fpanel.findById(idName[4]).setValue(list.url);
		fpanel.findById(idName[5]).setValue(list.picUrl);
		fpanel.findById(idName[6]).setValue(list.breUrl);
		Ext.get('flashTypeSelect').dom.value=list.flashType.title;
		Ext.get(idName[7]).dom.value=list.flashType.id;
		fpanel.findById(idName[8]).setValue(list.name);
	}	
}

function save(fpanel,start){
	var urlPost;
	var paramsPost;
	
	if (addWin) {
		urlPost = 'addFlash.shtml';
	}
	else 
		if (updWin) {
			urlPost = 'updateFlash.shtml';
			paramsPost = {
				flashId: selectRecord.get('id')
			};
		}
		else {
			Ext.Msg.alert('信息', '提交窗口错误参数!');
			window.close();
		}
	fpanel.findById('picUrl').enable();
	fpanel.findById('breUrl').enable();
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
				fpanel.findById('picUrl').disable();
				fpanel.findById('breUrl').disable();
			},
			success: function(fpanel, action){
			
				if (action.result === undefined) {
					Ext.MessageBox.alert('保存失败', '后台服务器错误!');
					this.disabled = false;
					fpanel.findById('picUrl').disable();
					fpanel.findById('breUrl').disable();
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
								flashTypeId:flashTypeId,
								start: start,
								limit: pageSize
							}
						});
					}
					else {
						Ext.MessageBox.alert('保存失败', returnJosn.msg);
						this.disabled = false;
						fpanel.findById('picUrl').disable();
						fpanel.findById('breUrl').disable();
					}
				}
			}
		});
	}
	else {
		fpanel.findById('picUrl').disable();
		fpanel.findById('breUrl').disable();
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
		comCreate(idName, 'add', '');
		addWin = new Ext.Window({
			title: '添加Flash信息',
			id: 'addW',
			closable: true,
			width: 580,
			height: 340,
			modal: true,
			layout: 'fit',
			items: [fpanel]
		});
	}
	addWin.show();
	if (flashTypeId !== '') {
		Ext.get('flashTypeSelect').dom.value = flashTypeTitle;
		Ext.get(idName[7]).dom.value = flashTypeId;
	}
}

function updateItem(idName,start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的Flash！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('id'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改Flash信息',
					id: 'updW',
					closable: true,
					width: 580,
					height: 340,
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
			Ext.MessageBox.alert('提示', '请选择一条Flash信息！');
		}
	}
}

function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('Flash信息删除操作', '未选择，请选择要删除的Flash信息！');
	}
	else {
		Ext.MessageBox.confirm('确认Flash删除', '你确认Flash删除选择的数据吗？', function(btn){
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
		url: 'delFlash.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('Flash信息删除操作', '删除Flash信息成功！');
			}
			else
			{
				Ext.MessageBox.alert('删除操作', returnJosn.msg);
				objectStore.load({
					params: {
						flashTypeId:flashTypeId,
						start: start,
						limit: pageSize
					}
				});
			}
		},
		failure: function(result, request){
			Ext.MessageBox.alert('Flash信息删除操作', '删除Flash信息失败！');
			objectStore.load({
					params: {
						flashTypeId:flashTypeId,
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
			flashTypeId:flashTypeId,
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
	var oldUrl = '',breUrl='';
	if (type === 0) {
		oldUrl = Ext.getCmp('picUrl').getValue();
	}
	else{
		oldUrl = Ext.getCmp('breUrl').getValue();
	}
	if (fileUpWin) {
		urlPost = 'uploadFlashPic.shtml';
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
							Ext.getCmp('picUrl').setValue(returnJosn.picUrl);
						}
						else{
							Ext.getCmp('breUrl').setValue(returnJosn.picUrl);
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