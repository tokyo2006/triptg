
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	/*==============================全局变量t=============================================*/
	var id = 1; //行程内容id
	var contentPanel,userPanel, tabPanel,fpanel;
	var objectGrid;
	var selectRecord;
	var cbsm;
	var objectStore;
	
	initView();
});

var pageSize = 20;
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
	var idName = new Array('name','phone','qq','msn','userName','password','repassword','valid');
	/*
	 * 数据解析器
	 */
	var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'managerId',
        fields: [
            {name: 'managerId', mapping:'managerId'},
            {name: 'name', mapping:'name'},
			{name: 'phone', mapping:'phone'},
			{name: 'qq', mapping:'qq'},
			{name: 'msn', mapping:'msn'},
			{name: 'userId', mapping:'userId'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllManager.shtml',method:'POST'}),
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
            {id:'managerId',header: '用户标识', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'managerId'},
			{header: '姓名', hideable:true, sortable: true, 
			dataIndex: 'name'},
			{header: '电话', hideable:true, sortable: true, 
			dataIndex: 'phone'},
			{header: 'QQ号码', hideable:true, sortable: true, 
			dataIndex: 'qq'},
			{header: 'MSN号码', hideable:true, sortable: true, 
			dataIndex: 'msn'}
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
			refreshText:'刷新管理员',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新管理员信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
			text: '添加',
			tooltip: '添加管理员信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		},'-', {
			text: '修改',
			tooltip: '修改管理员信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '删除',
			tooltip: '删除管理员信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}		
		}],
		
		frame: true,
		title:'管理员信息表',
		region: 'center',
		height: 150,
		autoWidth: true,
		animCollapse: false,
		trackMouseOver: true
	});
	return objectGrid;
}

function comCreate(idName,actions,type){
	var attributePanel,rolePanel;
		
	attributePanel = new Ext.Panel({
		title: '管理员信息',
		id: 'attrP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 290,
		defaults: {
			xtype: 'textfield',
			width: 230,
			allowBlank: false,
			anchor: '95%'
		},
		
		items: [{
			fieldLabel: '姓名',
			name: idName[0],
			id: idName[0]			
		},{
			fieldLabel: '电话',
			name: idName[1],
			id: idName[1],
			vtype: 'telCheck'			
		},{
			fieldLabel: 'QQ',
			name: idName[2],
			id: idName[2]			
		},{
			fieldLabel: 'MSN',
			name: idName[3],
			id: idName[3],
			vtype:'email'			
		}]
	});
	
	userPanel = new Ext.Panel({
		title:'用户属性',
		id: 'userP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 290,
		defaults: {
			xtype: 'textfield',
			width: 230,
			allowBlank: true,
			anchor: '95%'
		},
		
		items:[{
			fieldLabel: '用户名',
			name: idName[4],
			id: idName[4],
			vtype:'alphanum',
			maxLength:96
		},{
			fieldLabel: '用户密码',
			name: idName[5],
			id: idName[5],
			vtype:'alphanum',
			inputType: 'password',
			maxLength:96
		},{
			fieldLabel: '确认密码',
			name: idName[6],
			id: idName[6],
			inputType: 'password',
			vtype: 'password',
        	initialPassField: 'password',
			maxLength:96
		},{
			xtype: 'checkbox',
			boxLabel: '帐号有效',
			id: idName[7],
			hideLabel: true,
			checked:true,
			labelSeparator: '',
			name: idName[7],
			inputValue: '1',
			anchor: '95%'
		}]
	});
	
	rolePanel = new Ext.Panel({
		title: '权限选择',
		id: 'roleP',
		bodyStyle: 'padding:10px',
		layout: 'column',
		autoScroll: true,
		height: 290
	});
	
	tabPanel = new Ext.TabPanel({
		activeTab: 0,
		autoHeight: true,
		items: [attributePanel,userPanel,rolePanel]
	});
	
	fpanel = new Ext.FormPanel({
		region: 'center',
		bodyStyle: 'padding:5px',
		fileUpload: true,
		collapsible: true,
		items: [tabPanel],
		
		buttons: [{
			text: '保存',
			handler: function(){
				save(fpanel);
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
	
	roleAjax(fpanel);
	
	if(actions!=='add'){
		userPanel.remove(fpanel.findById(idName[4]));		
		updateAjax(fpanel,actions,idName);
	}
}

function updateAjax(fpanel,Id,idName){
	Ext.Ajax.request({
		url: 'getSingleManager.shtml',
		method: 'POST',
		params: {managerId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,idName);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载管理员信息失败！');
			window.close();
		}
	});
}

function initUpdate(result,fpanel,idName){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.manager;
		
		fpanel.findById(idName[0]).setValue(list.name);
		fpanel.findById(idName[1]).setValue(list.phone);
		fpanel.findById(idName[2]).setValue(list.qq);
		fpanel.findById(idName[3]).setValue(list.msn);
		
		tabPanel.setActiveTab(userPanel);
		if (parseInt(list.valid)=== 0) {
			userPanel.findById(idName[7]).setValue(false);
			userPanel.doLayout();
		}
		
		tabPanel.setActiveTab(fpanel.findById('roleP'));
		var userRoleList = strList.user.roles;
		var tempCheckbox;
		for(var j=0; j<userRoleList.length;j++){
			if (userRoleList[j].name !== 'ROLE_MANAGER_LOGIN') {
				tempCheckbox = fpanel.findById(userRoleList[j].roleId);
				if (tempCheckbox !== '') {
					tempCheckbox.setValue(true);
				}
				tempCheckbox = '';
			}
		}
	}	
}

function roleAjax(fpanel){
	Ext.Ajax.request({
		url:'getRoles.shtml',
		method:'POST',
		success: function(result, request){
			initRole(result,fpanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载角色信息失败！');
			window.close();
		}
	});
}

function initRole(result,fpanel){
	var roleJosn = doJSON(result.responseText);
	var roleList = roleJosn.roleList;
	var rolePanel = fpanel.findById('roleP');
	
	var childRole;
	var childPanel;
	var childRoleArray= new Array();
	var roleNum=parseInt(roleList.length/2);
	if((roleList.length%2)!==0){
		roleNum+=1;
	}
	for(var i=0;i<roleList.length;i++){
		if(i<roleNum){
			childRole=createCheckbox(roleList[i]);
			childRoleArray.push(childRole);
			if(i===(roleNum-1)){
				childPanel = createChildPanel(childRoleArray);
				rolePanel.add(childPanel);
				childRoleArray.length=0;
			}
		}
		else{
			childRole=createCheckbox(roleList[i]);
			childRoleArray.push(childRole);
			if(i===(roleList.length-1)){
				childPanel = createChildPanel(childRoleArray);
				rolePanel.add(childPanel);
				childRoleArray.length=0;
			}
		}
	}
	rolePanel.doLayout();
}

function createCheckbox(item){
	var roleTemp = new Ext.form.Checkbox({
		boxLabel: item.desc,
		name: 'reroleList',
		id:item.roleId,
		inputValue: item.roleId,
		checked: false,
		hideLabel: true
	})
	return roleTemp;
}

function createChildPanel(childRoleArray){
	var childPanelTemp = {
		bodyStyle: 'padding:3px',
		columnWidth: .5,
		layout: 'form',
		border: false,
		items: childRoleArray
	};
	return childPanelTemp;
}

/**
 * 表单保存
 * @param {Object} fpanel
 */
function save(fpanel){
	var urlPost;
	var paramsPost;	
	
	if (addWin) {
		urlPost = 'addManager.shtml';
	}
	else 
		if (updWin) {
			urlPost = 'updateManager.shtml';
			paramsPost = {
				managerId: selectRecord.get('managerId')
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
				Ext.MessageBox.alert('保存失败', '信息保存未成功');
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
								start: 0,
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
			title: '添加管理员信息',
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
		Ext.MessageBox.alert('修改操作', '请选择要修改的管理员！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('managerId'),'');

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改管理员信息',
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
			Ext.MessageBox.alert('提示', '请选择一条管理员信息！');
		}
	}
}


function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('管理员信息删除操作', '未选择，请选择要删除的管理员信息！');
	}
	else {
		Ext.MessageBox.confirm('确认管理员信息删除', '你确认删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('managerId');
					if (i === 0) {
						jsonData = jsonData + ss;
					}
					else {
						jsonData = jsonData + "," + ss;
					}
					objectStore.remove(m[i]);
				}
				delAjax(start, jsonData);
			}
		});
	}
}

function delAjax(start,jsonData){
	Ext.Ajax.request({
		url: 'delManager.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('管理员信息删除操作', '删除管理员信息成功！');
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
			Ext.MessageBox.alert('管理员信息删除操作', '删除管理员信息失败！');
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