
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	/*==============================全局变量t=============================================*/
	var fpanel;
	var objectGrid;
	var selectRecord;
	var cbsm;
	var objectStore,pickStore;
	initView();
});

var pageSize =27;
var addWin,updWin;
var parent=typeId,outParentText=title;

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
	var idName = new Array('name','parent');
	
	var typePagingToolbar = Ext.extend(Ext.PagingToolbar, {
		paramNames: {
			start: 'start',
			limit: 'limit',
			typeId:'typeId'
		},
		
		doLoad: function(start, typeId){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.typeId] = typeId;
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
					this.doLoad(0, typeId);
					break;
				case "prev":
					this.doLoad(Math.max(0, this.cursor - this.pageSize), typeId);
					break;
				case "next":
					this.doLoad((this.cursor + this.pageSize), typeId);
					break;
				case "last":
					var total = store.getTotalCount();
					var extra = total % this.pageSize;
					var lastStart = extra ? (total - extra) : total - this.pageSize;
					this.doLoad(lastStart, typeId);
					break;
				case "refresh":
					this.doLoad(this.cursor,typeId);
					break;
			}
		}
	});
	
	var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'typeId',
        fields: [
            {name: 'typeId', mapping:'typeId'},
            {name: 'name', mapping:'name'},
			{name: 'desc', mapping:'desc'},
			{name: 'parentName', mapping:'pnt.name'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllNewsType.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'name', direction: "DESC"}
        });
		
	objectStore.load({
		params: {
			typeId:typeId,
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
            {id:'typeId',header: '标识号', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'typeId'},
			{header: '名称', hideable:true, sortable: true, 
			dataIndex: 'name'},
			{header: '描述', hideable:true, sortable: true, 
			dataIndex: 'desc'},
			{header: '父类型', hideable:true, sortable: true,
			dataIndex: 'parentName'}
        ],
		sm: cbsm,
		
		view: new Ext.grid.GroupingView({
			forceFit: true,
			showGroupName: false,
			enableNoGroups: true, // REQUIRED!
			hideGroupedColumn: false,
			groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "条" : "条"]})'
		}),
		
		bbar: new typePagingToolbar({
			pageSize: pageSize,
			store: objectStore,
			displayInfo: true,
			displayMsg: '显示 {0}-{1}条 / 共 {2} 条',
			refreshText:'刷新新闻类别信息',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新新闻类别信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
			text: '添加',
			tooltip: '添加新闻类别信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		},'-', {
			text: '修改',
			tooltip: '修改新闻类别信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '删除',
			tooltip: '删除新闻类别信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}		
		}],
		
		frame: true,
//		title:'新闻类别信息表',
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
	pickStore = new Ext.data.SimpleStore({
		fields:['returnValue','displayValue'],		
		proxy: new Ext.data.HttpProxy({
			url: 'getNewsInfo.shtml',
			method: 'POST'
		})
	});
	
	var pickCombo= new Ext.form.ComboBox({
			store: pickStore,       //先将值选出组合为三个数组赋值，示例如下
			id:'parentSelect',
			name:idName[1],
			hiddenName: idName[1],
			valueField:'returnValue',
	        displayField:'displayValue',
			fieldLabel:'类别父类选择',
	        typeAhead: true,
			disabled:true,
			allowBlank: true,
	        mode: 'local',
			bodyStyle: 'padding:3px,margin-bottom:6px',
			triggerAction: 'all',
	        emptyText:'请选择一个类别类型',
			loadingText:'正在加载请等待....',
			forceSelection:true,   
			selectOnFocus:true
	});	
				
	attributePanel = new Ext.Panel({
		id: 'attrP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 170,
		defaults: {
			xtype: 'textfield',
			width: 230,
//			allowBlank: false,
			anchor: '95%'
		},
		
		items: [{
			fieldLabel: '名称',
			allowBlank: false,
			name: idName[0],
			id: idName[0]			
		},{
			fieldLabel: '是否子类',
			listeners:{
				'check':{
					fn:combClick,
					scope:this
				}
			},
			xtype:'checkbox',
			name: 'parentType',
			id: 'parentType'			
		},pickCombo,{
			xtype:'textarea',
			fieldLabel: '描述',
			name: 'desc',
			id: 'desc'			
		}]
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
	
	if(actions!=='add'){
		updateAjax(fpanel,actions,idName);
	}
}

function combClick(item){
	if(item.checked){
		pickStore.reload();
		Ext.getCmp('parentSelect').enable();
	}
	else{
		Ext.getCmp('parentSelect').disable();
	}
}

function updateAjax(fpanel,Id,idName){
	Ext.Ajax.request({
		url: 'getSingleNewsType.shtml',
		method: 'POST',
		params: {typeId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,idName);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载新闻类别信息失败！');
			window.close();
		}
	});
}

function initUpdate(result,fpanel,idName){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.newsType;
		
		fpanel.findById(idName[0]).setValue(list.name);
		fpanel.findById('desc').setValue(list.desc);
		if(list.parent!==''){
			fpanel.findById('parentType').setValue(true);
			Ext.get('parentSelect').dom.value=strList.parent.name;
			Ext.get('parent').dom.value=strList.parent.typeId;
//			fpanel.findById('parentSelect').setValue(strList.parent.typeId);
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
	
	if (addWin) {
		urlPost = 'addNewsType.shtml';
	}
	else 
		if (updWin) {
			urlPost = 'updateNewsType.shtml';
			paramsPost = {
				typeId: selectRecord.get('typeId')
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
						parent='';
						objectStore.load({
							params: {
								typeId:typeId,
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
			title: '添加新闻类别信息',
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
	
	if(parent!==''){
		parent = typeId;
		fpanel.findById('parentType').setValue(true);
		Ext.get('parentSelect').dom.value=outParentText;
		Ext.get('parent').dom.value=parent;
	}
}

function updateItem(idName,start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的新闻类别！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('typeId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改新闻类别信息',
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
			Ext.MessageBox.alert('提示', '请选择一条新闻类别信息！');
		}
	}
}


function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('新闻类别信息删除操作', '未选择，请选择要删除的新闻类别信息！');
	}
	else {
		Ext.MessageBox.confirm('确认新闻类别删除', '你确认新闻类别删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('typeId');
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
		url: 'delNewsType.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('新闻类别信息删除操作', '删除新闻类别信息成功！');
			}
			else
			{
				Ext.MessageBox.alert('删除操作', returnJosn.msg);
				objectStore.load({
					params: {
						typeId:typeId,
						start: start,
						limit: pageSize
					}
				});
			}
		},
		failure: function(result, request){
			Ext.MessageBox.alert('新闻类别信息删除操作', '删除新闻类别信息失败！');
			objectStore.load({
					params: {
						typeId:typeId,
						start: start,
						limit: pageSize
					}
				});
		}
	});
}

function allRefresh(store){
	store.load({
		params: {
			typeId:typeId,
			start: 0,
			limit:pageSize
		}
	});
}