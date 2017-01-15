
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	var contentPanel,userPanel,fpanel;
	var objectGrid;
	var selectRecord;
	var cbsm;
	var objectStore;
	var statusBoxstore;
	
	initView();
});

var pageSize = 27;
var managerId;
var addWin,updWin;

function initView(){
	new Ext.Viewport({
		layout: 'border',
		items: initGrid()
	});
}

function initGrid(){
	var idName = new Array('customName','customPhone_hao','customMobile','manNum','childNum','status','orderOper','allCost','shouldCost');
	
	var searchText=new Ext.form.TextField({
		id: 'searchT',
		width: 110,
		emptyText: '订单号'
	});
	
	var startDateText=new Ext.form.DateField({
		width: 85,
		id:'startDateStr',
		name:'startDateStr',
		vtype: 'daterange',
		endDateField: 'endDateStr',
		format: 'Y-m-d',
		emptyText: '起始日期'
	});
	
	var endDateText=new Ext.form.DateField({
		width: 85,
		vtype: 'daterange',
		id: 'endDateStr',
		name: 'endDateStr',
		startDateField: 'startDateStr',
		format: 'Y-m-d',
		emptyText: '结束日期'
	});
	
	statusBoxstore = new Ext.data.SimpleStore({
		fields: ['retrunValue','displayValue'],
		data:[[1,'新订单'],[2,'已审核'],[3,'已出团'],[4,'已返回'],[5,'已作废']]
	});
	
	var searchSelect = new Ext.form.ComboBox({
			store: statusBoxstore,
			id:'searchSelect',
			name:'status',
			hiddenName: 'status',
			valueField:'returnValue',
	        displayField:'displayValue',
	        typeAhead: true,
	        mode: 'local',
			width:90,
			bodyStyle: 'padding:3px,margin-bottom:6px',
			triggerAction: 'all',
	        emptyText:'请选择条件',
			loadingText:'正在加载请等待....',
			forceSelection:true,   
			selectOnFocus:true
		});
	
	var searchText2=new Ext.form.TextField({
		id: 'searchT2',
		width: 90,
		emptyText: '输入线路名称'
	});
	
	var searchText3=new Ext.form.TextField({
		id: 'searchT3',
		width: 90,
		emptyText: '输入用户名称'
	});
	
	var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'orderId',
        fields: [
            {name: 'orderId', mapping:'orderId'},
            {name: 'orderNo',mapping:'orderNo'},
			{name: 'orderUserId', mapping:'orderUserId'},
			{name: 'customName', mapping:'customName'},
			{name: 'customMobile', mapping:'customMobile'},
			{name: 'customFax', mapping:'customFax'},
			{name: 'customEmail', mapping:'customEmail'},
			{name: 'customZip', mapping:'customZip'},
			{name: 'customAddress', mapping:'customAddress'},
			{name: 'customRemark', mapping:'customRemark'},
			{name: 'status', mapping:'status'},
			{name: 'orderOper', mapping:'orderOper'},
			{name: 'teamName', mapping:'teamName'},
			{name: 'teamId', mapping:'bakTeam.teamId'},
			{name: 'manNum', mapping:'manNum'},
			{name: 'childNum', mapping:'childNum'},
			{name: 'startDateStr', mapping:'startDateStr'},
			{name: 'endDateStr', mapping:'endDateStr'},
			{name: 'orderDateStr', mapping:'orderDateStr'},
			{name: 'manPriceList', mapping:'manPriceList'},
			{name: 'allCost', mapping:'allCost'},
			{name: 'shouldCost', mapping:'shouldCost'},
			{name: 'dyjPrice', mapping:'dyjPrice'},
			{name: 'childrenPriceList', mapping:'childrenPriceList'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllOrder.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'orderDateStr', direction: "DESC"}
        });
	objectStore.load({
		params: {
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
		     rn,  
		     cbsm, 
            {id:'orderId',header: '订单标示', hidden:true, hideable:false, sortable: true, dataIndex: 'orderId'},
			{id:'orderNo',header: '订单编号', hidden:true, hideable:false, sortable: true, dataIndex: 'orderNo'},
			{id:'teamId',header: '开班Id', hidden:true, hideable:false, sortable: true, dataIndex: 'teamId'},
            {header: '用户姓名', hideable:true, sortable: true, dataIndex: 'customName'},
//			{header: '电话', hideable:true, sortable: true, dataIndex: 'customPhone_hao'},
			{header: '手机', hideable:true, sortable: true, dataIndex: 'customMobile'},
			{header: '用户评论',hidden:true, hideable:true, sortable: true, dataIndex: 'customRemark'},
			{header: '操作人员回复',hidden:true, hideable:true, sortable: true, dataIndex: 'orderOper'},
			{header: '订单状态', hideable:true, sortable: true, renderer:Ext.util.Format.statusRenderer2,dataIndex: 'status'},
			{header: '线路名', hideable:true, sortable: true, dataIndex: 'teamName'},
			{header: '成人', hideable:true, sortable: true, dataIndex: 'manNum'},
			{header: '儿童', hideable:true, sortable: true, dataIndex: 'childNum'},
			{header: '出发日期', hideable:true, sortable: true, dataIndex: 'startDateStr'},
			{header: '结束日期', hideable:true, sortable: true, dataIndex: 'endDateStr'},
			{header: '总体花费', hideable:true, sortable: true, dataIndex: 'allCost'},
			{header: '应收款项', hideable:true, sortable: true, dataIndex: 'shouldCost'},
			{header: '下单日期', hideable:true, sortable: true, dataIndex: 'orderDateStr'}
        ],
		sm: cbsm,
		
		view: new Ext.grid.GroupingView({
			forceFit: true,
			showGroupName: false,
			enableNoGroups: false, 
			hideGroupedColumn: true,
			groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "人" : "人"]})'
		}),
		bbar: new Ext.PagingToolbar({
			pageSize: pageSize,
			store: objectStore,
			displayInfo: true,
			displayMsg: '显示 {0}-{1}条 / 共 {2} 条',
			refreshText:'刷新订单',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新订单信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',
//			{
//			text: '添加',
//			tooltip: '添加订单信息',
//			iconCls: 'add',
//			handler: function(){
//				addItem(idName);
//			}
//		}, '-',
		 {
			text: '审核',
			tooltip: '审核订单信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '查看开班',
			tooltip: '查看订单开班信息',
			iconCls: 'option',
			handler: viewItem		
		},
		'->',
			searchText,'-',startDateText,'-',endDateText,'-',searchSelect,'-',searchText2,'-',searchText3,'-',
		{
				text:'搜索',
				handler:function(){
					search(searchText,startDateText,endDateText,searchSelect,searchText2,searchText3,objectStore,objectGrid.getBottomToolbar().cursor);
				}
			}
		],
		
//		listeners: {
//			'rowdblclick': {
//				fn: rowdblclick,
//				scope: this
//			}
//		},
		frame: true,
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
//		title: '订单信息',
		id: 'attrP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 300,
		defaults: {
			xtype: 'textfield',
			width: 230,
			allowBlank: false,
			anchor: '95%'
		},
		
		items: [{
			fieldLabel: '用户姓名',
			name: idName[0],
			id: idName[0]
		},
//		{
//			fieldLabel: '客户电话',
//			name: idName[1],
//			id: idName[1]
//		},
		{
			fieldLabel: '客户手机',
			name: idName[2],
			id: idName[2]
		},{
			fieldLabel: '成人数',
			name: idName[3],
			id: idName[3]
		},{
			fieldLabel: '儿童数',
			allowBlank: true,
			name: idName[4],
			id: idName[4]
		},{
			fieldLabel: '总体花费',
			disabled:true,
			name: idName[7],
			id: idName[7]
		},{
			fieldLabel: '应收款项',
			name: idName[8],
			id: idName[8]
		},{
			xtype: 'combo',
			fieldLabel: '订单状态',
			store: statusBoxstore,
			id: 'statusCombox',
			name: idName[5],
			hiddenName: idName[5],
			valueField: 'retrunValue',
			displayField: 'displayValue',
			mode: 'local',
			triggerAction: 'all',
			bodyStyle: 'padding:3px',
			emptyText: '请选择一个订单',
			loadingText: '正在加载请等待....',
			forceSelection: true, //强制只能选择，不让输入
			anchor: '95%'
		},{
			fieldLabel: '操作员回复',
			xtype:'textarea',
			height: 120,
			grow: false,
			allowBlank: true,
			name: idName[6],
			id: idName[6]
		}]
	});
		
//	tabPanel = new Ext.TabPanel({
//		activeTab: 0,
//		autoHeight: true,
//		items: [attributePanel]
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
	
	if(actions!=='add'){
		updateAjax(fpanel,actions,idName);
	}
}

function updateAjax(fpanel,Id,idName){
	Ext.Ajax.request({
		url: 'getSingleOrder.shtml',
		method: 'POST',
		params: {orderId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,idName);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载订单信息失败！');
			window.close();
		}
	});
}

function initUpdate(result,fpanel,idName){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.doorOrder;
		
		fpanel.findById(idName[0]).setValue(list.customName);
//		fpanel.findById(idName[1]).setValue(list.customPhone_hao);
		fpanel.findById(idName[2]).setValue(list.customMobile);
		fpanel.findById(idName[3]).setValue(list.manNum);
		fpanel.findById(idName[4]).setValue(list.childNum);
		fpanel.findById('statusCombox').setValue(list.status);
		fpanel.findById(idName[6]).setValue(list.orderOper);
		fpanel.findById(idName[7]).setValue(list.allCost);
		fpanel.findById(idName[8]).setValue(list.shouldCost);
	}
}


function save(fpanel,start){
	var urlPost;
	var paramsPost;
	addWin = Ext.getCmp('addW');
	updWin = Ext.getCmp('updW');
	if (addWin) {
		urlPost = 'addOrder.shtml';
//		paramsPost = {};
	}
	else if (updWin) {
			urlPost = 'updateOrder.shtml';
			paramsPost = {
				orderId: selectRecord.get('orderId')
			};
	}
	else {
			Ext.Msg.alert('信息', '提交窗口错误参数!');
			window.close();
	}
	fpanel.findById('allCost').enable();
	
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
					fpanel.findById(idName[7]).disable();
					this.disabled = false;
				},
				success: function(fpanel, action){
				
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
						fpanel.findById('allCost').disable();
						Ext.MessageBox.alert('保存失败', returnJosn.msg);
						this.disabled = false;
					}
				}
			});
	}
	else {
		fpanel.findById('allCost').disable();
		Ext.Msg.alert('信息', '请填写完成再提交!');
	}
}

function reset(fpanel){
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
/*
function addItem(idName){
	addWin = Ext.getCmp('addW');
	
	if (!addWin) {
		comCreate(idName,'add','');
		addWin = new Ext.Window({
			title: '添加订单',
			id: 'addW',
			closable: true,
			width: 580,
			height: 380,
			modal: true,
			layout: 'fit',
			items: [fpanel]
		});
	}
	addWin.show();
}*/

function updateItem(idName,start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的订单！');
	}
	else {
		if (cbsm.getCount() === 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('orderId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改订单',
					id: 'updW',
					closable: true,
					width: 580,
					height: 380,
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
			Ext.MessageBox.alert('提示', '请选择一位订单！');
		}
	}
}
/*
function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('订单删除操作', '未选择，请选择要删除的管理人员！');
	}
	else {
		Ext.MessageBox.confirm('确认删除', '你确认删除选择的数据吗？', function(btn){
			if (btn === "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('orderId');
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
		url: 'delOrder.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = Ext.util.JSON.decode(result.responseText); 
			if(returnJosn.success===true)
			{
				Ext.MessageBox.alert('删除操作', '删除订单成功！');
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
			Ext.MessageBox.alert('删除操作', '删除订单失败！');
			objectStore.load({
					params: {
						start: start,
						limit: pageSize,
						delData: jsonData
					}
				});
		}
	});
}*/

function allRefresh(objectStore){
	objectStore.load({
		params: {
			start: 0,
			limit:pageSize
		}
	});
}

function search(searchText,startDateText,endDateText,searchSelect,searchText2,searchText3,objectStore,start){
	var orderNo=searchText.getValue();
	if(orderNo==='订单号'){
		orderNo='';
	}
	var beginDate,endDate;
	if(startDateText.getValue()===''){
		beginDate='';
	}
	else{
		beginDate = (new Date(Date.parse(startDateText.getValue()))).dateFormat("Y-m-d");
	}
	
	if(endDateText.getValue()===''){
		endDate='';
	}
	else{
		endDate = (new Date(Date.parse(endDateText.getValue()))).dateFormat("Y-m-d");
	}
	
	var teamName = searchText2.getValue();
	var status = searchSelect.getValue();
	var customName = searchText3.getValue();
	objectStore.load({
					params: {
						orderNo:orderNo,
						beginDate:beginDate,
						endDate:endDate,
						teamName:teamName,
						status:status,
						customName:customName,
						start: start,
						limit: pageSize
					}
				});
}

function viewItem(){
	var	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (selectRecord) {
		if (cbsm.getCount() == 1) {
			teamId=selectRecord.get('teamId');
            window.open('../../team/teamContent/'+teamId);
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条开班查看！');
		}
	}
	else{
		Ext.MessageBox.alert('提示', '请选择一条开班查看！');
	}
}