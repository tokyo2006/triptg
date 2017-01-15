
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
var reArea='';
//reRegion=new Array(),regionHtml='';

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
	var idName = new Array();
	
	var delBtn,updBtn;
	if(node===''){
		delBtn = {};	
		updBtn = {};
	}
	else{
		delBtn={
			text: '删除关联',
			tooltip: '删除关联信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}
		};
		updBtn={
			text: '添加关联',
			tooltip: '关联景点信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		};
	}
	
	var regionPagingToolbar = Ext.extend(Ext.PagingToolbar, {
    	paramNames : {start: 'start', limit: 'limit',node: 'node'},
    	doLoad: function(start){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.node] = node;
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
		idProperty:'sceneryId',
        fields: [
            {name: 'sceneryId', mapping:'sceneryId'},
            {name: 'name', mapping:'name'},
			{name: 'firstLetter', mapping:'firstLetter'},
			{name: 'subTopic', mapping:'subTopic'},
			{name: 'content', mapping:'content'},
			{name: 'synopsis', mapping:'synopsis'},
			{name: 'ticketPrice', mapping:'ticketPrice'},
			{name: 'level', mapping:'level'},
			{name: 'isTop', mapping:'isTop'},
			{name: 'isUpdate', mapping:'isUpdate'},
			{name: 'weekHits', mapping:'weekHits'},
			{name: 'monthHits', mapping:'monthHits'},
			{name: 'allHits', mapping:'allHits'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllScenery.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'level', direction: "DESC"}
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
            {id:'sceneryId',header: '标识号', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'sceneryId'},
			{header: '名称', hideable:true, sortable: true, 
			dataIndex: 'name'},
			{header: '首字母', hideable:true, sortable: true, 
			dataIndex: 'firstLetter'},
			{header: '景点标签', hideable:true, sortable: true, 
			dataIndex: 'subTopic'},
			{header: '景点介绍', hideable:true, sortable: false, renderer:function(v,p,record){
				if(v&&(v!=='')){
					if (v.length > 50) {
						v=v.replace(/[^\u4E00-\u9FA5]/g,'');
						return v.substr(0, 20) + '...';
					}
					else {
						return v;
					}
				}
				else{
					return '空';
				}
			},
			dataIndex: 'content'},
			{header: '简介', hideable:true, sortable: true, 
			dataIndex: 'synopsis'},
			{header: '景点价格', hideable:true, sortable: true, 
			dataIndex: 'ticketPrice'},
			{header: '景点等级', hideable:true, sortable: true, 
			dataIndex: 'level'},
			{header: '是否置顶', hideable:true, sortable: true, 
			dataIndex: 'isTop'},
			{header: '是否更新', hideable:true, sortable: true, 
			dataIndex: 'isUpdate'},
			{header: '周点击', hideable:true, sortable: true, 
			dataIndex: 'weekHits'},
			{header: '月点击', hideable:true, sortable: true, 
			dataIndex: 'monthHits'},
			{header: '总点击', hideable:true, sortable: true, 
			dataIndex: 'allHits'}
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
			refreshText:'刷新景点信息',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新景点信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',updBtn,'-',delBtn],
		
		frame: true,
		region: 'center',
		height: 150,
		autoWidth: true,
		animCollapse: false,
		trackMouseOver: true
	});
	return objectGrid;
}

function comCreate(idName,start){
	var areaPanel;
	
	var pickStore = new Ext.data.SimpleStore({
		fields:['displayValue','returnValue'],	
		proxy: new Ext.data.HttpProxy({
			url: 'getSceneryInfo.shtml',
			method: 'POST'
		})
	});
	
	var pickCombo= new Ext.form.ComboBox({
			store: pickStore,       //先将值选出组合为三个数组赋值，示例如下
			id:'scenerySelect',
			name:'sceneryId',
			hiddenName: 'sceneryId',
			valueField:'returnValue',
	        displayField:'displayValue',
	        typeAhead: true,
			disabled:true,
	        mode: 'local',
			bodyStyle: 'padding:3px,margin-bottom:6px',
			triggerAction: 'all',
	        emptyText:'请选择一个景点',
			loadingText:'正在加载请等待....',
			forceSelection:true,   
			selectOnFocus:true,
			width:240,
			anchor: '95%'
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
//					if (n.isLeaf()) {
						clickAreaNode(n);
//					}
//					else {
//						n.expand();
//					}
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
			title: '区域类型',
			bodyStyle: 'padding:10px 0 0 10px;',
			columnWidth: .58,
			items:[pickCombo],
			collapsed: false,
			height: 280
			
		}]
	});
	
	tabPanel = new Ext.TabPanel({
		activeTab: 0,
		autoHeight: true,
		items: [areaPanel]
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
	
}


function clickAreaNode(item){
	
	Ext.getCmp('scenerySelect').setDisabled(false);
	Ext.getCmp('scenerySelect').store.load({params:{areaId:item.id}});
}

function save(fpanel,start){
	var urlPost;
	var paramsPost;
	
	if (updWin) {
			urlPost = 'addSceneryRegion.shtml';
			paramsPost = {
				node: node
			};
		}
		else {
			Ext.Msg.alert('信息', '提交窗口错误参数!');
			window.close();
		}
	
	//提交数据
	if (fpanel.form.isValid() ) {
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
						if (updWin) {
							updWin.close();
							updWin = '';
						}
						
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
	updWin = Ext.getCmp('updW');
	if (updWin) {
		updWin.close();
		updWin='';
	}
}

function winClose(item){
	item.close();
}


function updateItem(idName, start){
	updWin = Ext.getCmp('updW');
	comCreate(idName, start);
	
	if (!updWin) {
		updWin = new Ext.Window({
			title: '关联景点信息',
			id: 'updW',
			closable: true,
			width: 580,
			height: 415,
			plain: true,
			modal: true,
			layout: 'fit',
			listeners: {
				close: {
					fn: winClose,
					scope: this
				}
			},
			items: [fpanel]
		});
	}
	updWin.show();
	updWin.doLayout();
}

function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('景点关联删除操作', '未选择，请选择要删除的景点关联信息！');
	}
	else {
		Ext.MessageBox.confirm('确认景点关联删除', '你确认删除选择的景点关联数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('sceneryId');
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
		url: 'updateSceneryRegion.shtml',
		method: 'POST',
		params: {
			node:node,
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('景点关联信息删除操作', '删除景点关联信息成功！');
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
			Ext.MessageBox.alert('景点关联信息删除操作', '删除景点关联信息失败！');
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

