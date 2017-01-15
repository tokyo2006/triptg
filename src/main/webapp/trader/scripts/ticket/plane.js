
Ext.onReady(function(){
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

var pageSize = 15;
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
	var idName = new Array('name','number','begin','destination','company','beginDateStr','begintime','price','total');   
	/*
	 * 数据解析器
	 */
	var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'airId',
        fields: [
            {name: 'airId', type: 'string',mapping:'airId'},
                         {name:'airId'},
                         {name:'number'},
                         {name:'name'},
                         {name:'begin'},
                         {name:'destination'},
                         {name:'begintime'},
                         {name:'company'},
                         {name:'total'},
                         {name:'confirm'},
                         {name:'remain'},
                         {name:'price'},
                         {name:'beginDateStr'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
		reader: objectReader,
		proxy: new Ext.data.HttpProxy({
			url: 'getAllPlane.shtml',
			method: 'POST'
		}),
		remoteSort: true,
		sortInfo: {
			field: 'beginDateStr',
			direction: "DESC"
		},
		groupField: 'beginDateStr'
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
		     rn, 
		     cbsm, 
            {id:'airId',header: '飞机标识', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'airId'},{
			header: "机票信息标识",
			sortable: true,
			dataIndex: 'name'
		}, {
			header: "机号",
			sortable: true,
			dataIndex: 'number'
		}, {
			header: "出发城市",
			sortable: true,
			dataIndex: 'begin'
		}, {
			header: "抵达城市",
			sortable: true,
			dataIndex: 'destination'
		}, {
			header: "航空公司",
			sortable: true,
			dataIndex: 'company'
		}, {
			header: "价格",
			sortable: true,
			dataIndex: 'price',
			renderer:  Ext.util.Format.moneyRenderer
		},{
			header: "总数",
			sortable: true,
			dataIndex: 'total'
		}, {
			header: "已定",
			sortable: true,
			dataIndex: 'confirm'
		}, {
			header: "待定",
			sortable: true,
			dataIndex: 'booked'
		}, {
			header: "剩余",
			sortable: false,
			dataIndex: 'remain'
		},{
			header: "状态",
			sortable: false,
			dataIndex: 'status',
			renderer: function(v){
            return ((v === "已售完" ) ? '<font color=red>' + v +' </font>' : v);
            
        }
		},{
			header: "出发日期",
			sortable: true,
			dataIndex: 'beginDateStr'
		}],
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
			refreshText:'刷新机票信息',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新机票信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
			text: '添加',
			tooltip: '添加机票信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		}, '-', {
			text: '修改',
			tooltip: '修改机票信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '删除',
			tooltip: '删除机票信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}		
		}],
		
		frame: true,
		title:'机票信息表',
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
	
	attributePanel = new Ext.Panel({
		id: 'attributeP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 270,
		defaults: {
			width: 230,
			xtype: 'textfield',
			allowBlank: false,
			anchor: '95%'
		},
	
		items: [{			
			fieldLabel: '机票信息名',
			name: idName[0],
			id: idName[0]
		},{			
			fieldLabel: '机号',
			name: idName[1],
			id: idName[1]
		},{			
			fieldLabel: '出发城市',
			name: idName[2],
			id: idName[2]
		},{			
			fieldLabel: '抵达城市',
			name: idName[3],
			id: idName[3]
		},{			
			fieldLabel: '航空公司',
			name: idName[4],
			id: idName[4]
		},{	
			xtype:'datefield',		
			fieldLabel: '起飞日期',
			format: 'Y-m-d',
			name: idName[5],
			id: idName[5]
		},{	
			xtype:'timefield',			
			fieldLabel: '起飞时间',
			name: idName[6],
			id: idName[6],
			minValue: '0:00',
			maxValue: '23:45',
			format: 'H:i',
			forceSelection: true
		},{			
			fieldLabel: '价格',
			name: idName[7],
			id: idName[7],
			vtype: 'posint'
		},{			
			fieldLabel: '总数',
			name: idName[8],
			id: idName[8],
			vtype: 'posint'
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
		url: 'getSinglePlane.shtml',
		method: 'POST',
		params: {airId:Id},
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
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.results;
		
		fpanel.findById(idName[0]).setValue(list[0].name);
		fpanel.findById(idName[1]).setValue(list[0].number);
		fpanel.findById(idName[2]).setValue(list[0].begin);
		fpanel.findById(idName[3]).setValue(list[0].destination);
		fpanel.findById(idName[4]).setValue(list[0].company);
		fpanel.findById(idName[5]).setValue(list[0].beginDateStr);
		fpanel.findById(idName[6]).setValue(list[0].begintime);
		fpanel.findById(idName[7]).setValue(list[0].price);
		fpanel.findById(idName[8]).setValue(list[0].total);
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
		urlPost = 'addPlane.shtml';
	}
	else 
		if (updWin) {
			urlPost = 'updatePlane.shtml';
			paramsPost = {
				airId: selectRecord.get('airId')
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
	}
	else {
		Ext.Msg.alert('信息', '请填写完成再提交!');
	}
}

/**
 * 表单重置
 * @param {Object} fpanel
 */
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
	
	if (!addWin) {
		comCreate(idName,'add',0);
		addWin = new Ext.Window({
			title: '添加机票信息',
			id: 'addW',
			closable: true,
			width: 580,
			height: 350,
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
		Ext.MessageBox.alert('修改操作', '请选择要修改的机票信息！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('airId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改机票信息',
					id: 'updW',
					closable: true,
					width: 580,
					height: 350,
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
			Ext.MessageBox.alert('提示', '请选择一条机票信息！');
		}
	}
}


function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('机票信息删除操作', '未选择，请选择要删除的机票信息！');
	}
	else {
		Ext.MessageBox.confirm('确认删除', '删除机票信息将同时删除与该机票信息相关的团队，你确认删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('airId');
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
		url: 'delPlane.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('机票信息删除操作', '删除机票信息成功！');
			}
			else
			{
				Ext.MessageBox.alert('机票信息删除操作', returnJosn.msg);
				ds.load({
					params: {
						start: start,
						limit: pageSize
					}
				});
			}
		},
		failure: function(result, request){
			Ext.MessageBox.alert('机票信息删除操作', doJSON(result.responseText).msg);
			objectStore.load({
					params: {
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
			start: 0,
			limit:pageSize
		}
	});
}