	

Ext.onReady(function(){
	//get All unit Name for comboBox.
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	var fpanel;
	var objectGrid;
	var selectRecord;
	var cbsm;
	var objectStore;
	
	initView();
});

var pageSize = 27;
var addWin,updWin;

function initView(){
		
	new Ext.Viewport({
		layout: 'border',
		items: initGrid()
	});
}

function initGrid(){
	var idName = new Array('title','sender','sendContain','reciverContain');  
	
	var assemblereader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'msgId',
        fields: [
            {name: 'msgId',mapping:'msgId'},
            {name: 'title',mapping:'title'},
			{name: 'reciverContain',mapping:'reciverContain'},
			{name: 'sendContain',mapping:'sendContain'},
			{name: 'reciver',mapping:'reciver'},
			{name: 'sender',mapping:'sender'},
			{name: 'senderId',mapping:'senderId'},
			{name: 'reciverId',mapping:'reciverId'},
			{name: 'email',mapping:'email'},
			{name: 'rdatetimeStr',mapping:'rdatetimeStr'},
			{name: 'sdatetimeStr',mapping:'sdatetimeStr'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: assemblereader,
			proxy: new Ext.data.HttpProxy({url: 'getAllComment.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'sdatetimeStr', direction: "DESC"}
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
            {id:'msgId',header: '留言标识', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'msgId'},
            {id:'title',header: '标题', hideable:false, sortable: true,
			dataIndex: 'title'},
			{id:'sendContain',header: '留言内容', hideable:false, sortable: true,
			dataIndex: 'sendContain'},
			{id:'sender',header: '提交者', hideable:false, sortable: true,
			dataIndex: 'sender'},
			{id:'sdatetimeStr',header: '提交时间', hideable:false, sortable: true,
			dataIndex: 'sdatetimeStr'},
			{id:'reciverContain',header: '留言回复', hideable:false, sortable: true,renderer:function(v,p,record){
				if((v==='')||(v===null)){
					return '<font color="#ff0000">未回复<font/>';
				}
				else{
					return v;
				}
			},
			dataIndex: 'reciverContain'},
			{id:'reciver',header: '回复者', hideable:false, sortable: true,
			dataIndex: 'reciver'},
			{id:'email',header: '邮件地址', hideable:false, sortable: true,
			dataIndex: 'email'}
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
			refreshText:'刷新留言',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新留言信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',
//			{
//			text: '添加',
//			tooltip: '添加留言信息',
//			iconCls: 'add',
//			handler: function(){
//				addItem(idName);
//			}
//		}, '-', 
		{
			text: '回复留言',
			tooltip: '回复留言信息',
			iconCls: 'option',
			handler: function(){
				updItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '删除',
			tooltip: '删除留言信息',
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
//		title:'留言信息表',
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
//		title: '留言属性',
		id: 'attrP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height:285,
		defaults: {
			width: 230,
			xtype: 'textarea',
			anchor: '95%'
		},
		
		items: [{
			xtype: 'textfield',
			fieldLabel: '留言标题',
			name: idName[0],
			id: idName[0]
		},{
			xtype: 'textfield',
			fieldLabel: '留言提交人',
			name: idName[1],
			id: idName[1]
		},{
			fieldLabel: '留言内容',
			height:100,
			name: idName[2],
			id: idName[2]
		},{
			fieldLabel: '留言回复',
			height:100,
			name: idName[3],
			id: idName[3]
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
		title: '留言信息',
		id: 'contentP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		height: 290,
		autoScroll: true,
		
		tbar: [{
			text: '添加留言',
			tooltip: '添加留言',
			handler: function(){
				addContent();
			},
			iconCls: 'add'
		}, '-', {
			text: '删除留言',
			tooltip: '删除已有留言',
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
		url: 'getSingleComment.shtml',
		method: 'POST',
		params: {msgId:Id},
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
	var list = strList.message;
	
	fpanel.findById(idName[0]).setValue(list.title);
	fpanel.findById(idName[1]).setValue(list.sender);
	fpanel.findById(idName[2]).setValue(list.sendContain);
	fpanel.findById(idName[3]).setValue(list.reciverContain);
	
}

function save(fpanel,start){
	var urlPost;
	var paramsPost;	
	
//	if (addWin) {
//		urlPost = 'addComment.shtml';
//	}
//	else 
	if (updWin) {
		urlPost = 'updComment.shtml';
		paramsPost = {
			msgId: selectRecord.get('msgId')
		};
	}
	else {
		Ext.Msg.alert('信息', '提交窗口错误参数!');
		window.close();
	}
	
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
	
//	id = 1;
	if (!addWin) {
		comCreate(idName,'add',0);
		addWin = new Ext.Window({
			title: '添加留言',
			id: 'addW',
			closable: true,
			width: 560,
			height: 365,
			modal: true,
			layout: 'fit',
			items: [fpanel]
		});
	}
	addWin.show();
}

function updItem(idName,start){
//	id = 1;
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('回复操作', '请选择要回复的留言！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('msgId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '回复留言',
					id: 'updW',
					closable: true,
					width: 560,
					height: 365,
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
			Ext.MessageBox.alert('提示', '请选择一条留言！');
		}
	}
}


function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('留言删除操作', '未选择，请选择要删除的留言！');
	}
	else {
		Ext.MessageBox.confirm('确认删除', '你确认删除选择的数据吗？', function(btn){
			if (btn === "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('msgId');
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
		url: 'delComment.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			Ext.MessageBox.alert('留言删除操作', '删除留言成功！');
		},
		failure: function(result, request){
			Ext.MessageBox.alert('留言删除操作', doJSON(result.responseText).msg);
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