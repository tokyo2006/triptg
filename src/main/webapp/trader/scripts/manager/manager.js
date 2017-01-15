
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	var contentPanel,userPanel,tabPanel,fpanel;
	var objectGrid;
	var selectRecord;
	var cbsm;
	var objectStore;
	var areaBoxstore;
	
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
	var idName = new Array('name','areaId','areaSelect','idCard','mobile','phone','qq','msn','email','userName','password','repassword','valid');
	
	var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'managerId',
        fields: [
            {name: 'managerId', mapping:'managerId'},
            {name: 'name',mapping:'name'},
			{name: 'areaName', mapping:'areaName'},
			{name: 'userId', mapping:'userId'},
			{name: 'idCard', mapping:'idCard'},
			{name: 'mobile', mapping:'mobile'},
			{name: 'phone', mapping:'phone'},
			{name: 'qq', mapping:'qq'},
			{name: 'msn', mapping:'msn'},
			{name: 'email', mapping:'email'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllManager.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'name', direction: "DESC"},
            groupField:'areaName'
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
            {id:'managerId',header: '用户标示', hidden:true, hideable:false, sortable: true, dataIndex: 'managerId'},
			{id:'userId',header: '用户编号', hidden:true, hideable:false, sortable: true, dataIndex: 'userId'},
            {header: '姓名', hideable:false, sortable: true, dataIndex: 'name'},
			{header: '手机', hideable:true, sortable: true, dataIndex: 'mobile'},
			{header: '电话', hideable:true, sortable: true, dataIndex: 'phone'},
			{header: '地区', hideable:true, sortable: true, dataIndex: 'areaName'},
			{header: '证件号码', hidden:true,hideable:true, sortable: true, dataIndex: 'idCard'},
			{header: 'QQ', hidden:true,hideable:true, sortable: true, dataIndex: 'qq'},
			{header: 'MSN', hidden:true,hideable:true, sortable: true, dataIndex: 'msn'},
			{header: '电子邮件', hidden:true,hideable:true, sortable: true, dataIndex: 'email'}
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
			displayMsg: '显示 {0}-{1}人 / 共 {2} 人',
			refreshText:'刷新人员',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新线路信息',
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
		}, '-', {
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
	
	areaBoxstore = new Ext.data.SimpleStore({
		fields: ['retrunValue','displayValue'],
		proxy: new Ext.data.HttpProxy({
			url: 'getAreaByCorpId.shtml',
			method: 'POST'
		})
	});
		
	attributePanel = new Ext.Panel({
		title: '管理员信息',
		id: 'attrP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 270,
		defaults: {
			xtype: 'textfield',
			width: 230,
			anchor: '95%'
		},
		
		items: [{
			fieldLabel: '姓名',
			name: idName[0],
			id: idName[0],
			maxLength:16,
			allowBlank: false
		},{
			fieldLabel: '身份证号码',
			name: idName[3],
			id: idName[3],
			maxLength:32
		},{
			fieldLabel: '手机',
			name: idName[4],
			id: idName[4],
			maxLength:32
		},{
			fieldLabel: '电话',
			name: idName[5],
			id: idName[5],
			maxLength:32
		},{
			fieldLabel: 'Qq',
			name: idName[6],
			id: idName[6],
			maxLength:32
		},{
			fieldLabel: 'Msn',
			name: idName[7],
			id: idName[7],
			vtype:'email',
			maxLength:32
		},{
			fieldLabel: 'Email',
			name: idName[8],
			id: idName[8],
			vtype:'email',
			maxLength:32
		}]
	});
	
	rolePanel = new Ext.Panel({
		title: '权限选择',
		id: 'role',
		bodyStyle: 'padding:10px',
		layout: 'column',
		autoScroll: true,
		height: 270
	});
	
	if(actions==="add")
	{
		userPanel = new Ext.Panel({
			title: '用户信息',
			id: 'userP',
			layout: 'form',
			bodyStyle: 'padding:10px',
			autoScroll: true,
			height: 270,
			defaults: {
				width: 230,
				allowBlank: false,
				anchor: '95%'
			},
			
			items: [{
				xtype: 'textfield',
				vtype: 'alphanum',
				fieldLabel: '用户名',
				name: idName[9],
				id: idName[9],
				maxLength: 64
			}, {
				xtype: 'textfield',
				vtype: 'alphanum',
				fieldLabel: '用户密码',
				name: idName[10],
				id: idName[10],
				inputType: 'password',
				maxLength: 64
			}, {
				xtype: 'textfield',
				fieldLabel: '确认密码',
				name: idName[11],
				id: idName[11],
				inputType: 'password',
				vtype: 'password',
				initialPassField: 'password',
				maxLength: 64
			}, {
				xtype: 'combo',
				fieldLabel: '地区',
				store: areaBoxstore,
				id: idName[2],
				name: idName[1],
				hiddenName: idName[1],
				valueField: 'retrunValue',
				displayField: 'displayValue',
				mode: 'remote',
				triggerAction: 'all',
				bodyStyle: 'padding:3px',
				emptyText: '请选择一个地区',
				loadingText: '正在加载请等待....',
				forceSelection: true, //强制只能选择，不让输入
				stateId: 0,
				listeners: {
					'expand': {
						fn: areaExpand,
						scope: this
					}
				},
				anchor: '95%'
			}, {
				xtype: 'checkbox',
				fieldLabel: '是否有效',
				id: idName[12],
				checked: true,
				labelSeparator: '',
				name: idName[12],
				inputValue: '1'
			}]
		});
	}
	else
	{
		userPanel = new Ext.Panel({
			title: '用户信息',
			id: 'userP',
			layout: 'form',
			bodyStyle: 'padding:10px',
			autoScroll: true,
			height: 270,
			defaults: {
				width: 230,
				anchor: '95%'
			},
		
			items: [{
				xtype: 'textfield',
				vtype:'alphanum',
				allowBlank: true,
				fieldLabel: '用户密码',
				name: idName[10],
				id: idName[10],
				inputType: 'password',
				maxLength:64
			},{
				xtype: 'textfield',
				allowBlank: true,
				fieldLabel: '确认密码',
				name: idName[11],
				id: idName[11],
				inputType: 'password',
				vtype: 'password',
        		initialPassField: 'password',
				maxLength:64
			},{
			xtype: 'combo',
			fieldLabel: '地区',
			store: areaBoxstore,
			id: idName[2],
			name: idName[1],
			hiddenName:idName[1],
			valueField:'retrunValue',
			displayField: 'displayValue',
			mode: 'remote',
			triggerAction: 'all',
			bodyStyle: 'padding:3px',
			emptyText: '请选择一个地区',
			allowBlank: false,
			loadingText: '正在加载请等待....',
			forceSelection: true, //强制只能选择，不让输入
			stateId:0,
			listeners: {
				'expand': {
					fn: areaExpand,
					scope: this
				}
			},
			anchor: '95%'
		}, {
				xtype: 'checkbox',
				fieldLabel: '是否有效',
				id: idName[12],
				checked: true,
				labelSeparator: '',
				name: idName[12],
				inputValue: '1'
			}]
		});
	}
	
	
	tabPanel = new Ext.TabPanel({
		activeTab: 0,
		autoHeight: true,
		items: [attributePanel,userPanel,rolePanel]
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
				reset(fpanel);
			}
		}, {
			text: '取消',
			handler: function(){
				cancel();
			}
		}]
	});
	
	if(actions==='add'){
		roleAjax(rolePanel);
	}
	else
	{
		roleUpdateAjax(idName,rolePanel,actions,tabPanel);
	}
}

function areaExpand(item){
	if(item.stateId===0){
		areaBoxstore.load();
		item.stateId=1;
	}	
}

function roleAjax(rolePanel){
	Ext.Ajax.request({
		url: 'getRoles.shtml',
		method: 'POST',
		success: function(result, request){
			initRole(result, rolePanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载角色失败！');
			window.close();
		}
	});
}

/**
 * 修改时初始化角色面板取值函数
 * 
 */
function roleUpdateAjax(idName,rolePanel,managerId,tabPanel){
	Ext.Ajax.request({
		url: 'getSingleManager.shtml',
		method: 'POST',
		params: {
			managerId: managerId
		},
		success: function(result, request){
			initUpdateManager(result, idName, rolePanel,tabPanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载角色失败！');
			window.close();
		}
	});
}

function initRole(result, rolePanel){
	var roleJosn = doJSON(result.responseText);
	var roleList = roleJosn.roleList;
	
	var childrenRoleComList;
	var childcolumncout;
	var childRole;
	var childroleList = new Array();
	
	childcolumncout = parseInt(roleList.length/2);
	if (roleList.length % 2 == 0) {
		childcolumncout = childcolumncout;
	}
	else {
		childcolumncout = childcolumncout + 1;
	}
	
	
	for (var j = 0; j < roleList.length; j++) {
	
		childRole = createChildRole(roleList[j]);
		if (j < childcolumncout) {
			childroleList.push(childRole);
			if (j == (childcolumncout - 1)) {
				childrenRoleComList = createChildRoleList(childroleList);
				rolePanel.add(childrenRoleComList);
				childroleList.length = 0;
			}
		}
		else {
			childroleList.push(childRole);
			if (j == (roleList.length - 1)) {
				childrenRoleComList = createChildRoleList(childroleList);
				rolePanel.add(childrenRoleComList);
				childroleList.length = 0;
			}
		}
	}
	rolePanel.doLayout();
}


function createChildRole(role){
	var roleTemp = new Ext.form.Checkbox({
		boxLabel: role.desc,
		name: 'reroleList',
		inputValue: role.roleId,
		checked: false,
		hideLabel: true
	})
	return roleTemp;
}


function createChildRoleList(childroleList){
	var childroleListtemp = {
		bodyStyle: 'padding:3px',
		columnWidth: .5,
		layout: 'form',
		border: false,
		items: childroleList
	};
	return childroleListtemp;
}

function initUpdateManager(result, idName, rolePanel,tabPanel){
	var resultJosn = doJSON(result.responseText);
	initUpdateUser(resultJosn.manager,resultJosn.user.valid, idName,tabPanel);
	initUpdateRole(resultJosn.roleList,resultJosn.user.roles,rolePanel);
}

function initUpdateUser(manager,valid, idName,tabPanel){
	managerId = manager.managerId;
	fpanel.findById(idName[0]).setValue(manager.name);
	fpanel.findById(idName[3]).setValue(manager.idCard);
	fpanel.findById(idName[4]).setValue(manager.mobile);
	fpanel.findById(idName[5]).setValue(manager.phone);
	fpanel.findById(idName[6]).setValue(manager.qq);
	fpanel.findById(idName[7]).setValue(manager.msn);
	fpanel.findById(idName[8]).setValue(manager.email);
	fpanel.findById(idName[12]).setValue(parseInt(valid));
	
	tabPanel.setActiveTab(fpanel.findById('userP'));
	Ext.get(idName[1]).dom.value=manager.areaId;
	Ext.get(idName[2]).dom.value=manager.areaName;
//	if(valid=='1')
//	{
//		var validCheck={
//				xtype: 'checkbox',
//				fieldLabel: '是否有效',
//				id:idName[12],
//				checked:true,
//				labelSeparator:'',
//				name: idName[12],
//				inputValue: '1'
//			};
//	}
//	else
//	{
//		var validCheck={
//				xtype: 'checkbox',
//				fieldLabel: '是否有效',
//				id:idName[12],
//				checked:false,
//				labelSeparator:'',
//				name: idName[12],
//				inputValue: '1'
//			};
//	}
//	fpanel.findById('userP').add(validCheck);
	fpanel.findById('userP').doLayout();
}

function initUpdateRole(roleList,userRoleList, rolePanel){
	var childrenRoleComList;
	var childcolumncout;
	var childRole;
	var childroleList = new Array();
	
	childcolumncout = parseInt(roleList.length/2);
	if (roleList.length % 2 == 0) {
		childcolumncout = childcolumncout;
	}
	else {
		childcolumncout = childcolumncout + 1;
	}
	
	for (var j = 0; j < roleList.length; j++) {
	
		childRole = createChildUpdateRole(roleList[j],userRoleList);
		if (j < childcolumncout) {
			childroleList.push(childRole);
			if (j == (childcolumncout - 1)) {
				childrenRoleComList = createChildRoleList(childroleList);
				rolePanel.add(childrenRoleComList);
				childroleList.length = 0;
			}
		}
		else {
			childroleList.push(childRole);
			if (j == (roleList.length - 1)) {
				childrenRoleComList = createChildRoleList(childroleList);
				rolePanel.add(childrenRoleComList);
				childroleList.length = 0;
			}
		}
	}
	rolePanel.doLayout();
}

function createChildRole(role){
	var roleTemp = new Ext.form.Checkbox({
		boxLabel: role.desc,
		name: 'reroleList',
		inputValue: role.roleId,
		checked: false,
		hideLabel: true
	});
	return roleTemp;
}

function createChildUpdateRole(role,userRoleList){
	if(userRoleList.length>0)
	{
		for(var i=0; i<userRoleList.length;i++)
		{
			if(userRoleList[i].roleId===role.roleId)
			{
				var roleTemp = new Ext.form.Checkbox({
        			boxLabel:role.desc,
        			name:'reroleList', 
        			inputValue: role.roleId,
        			checked: true,
        			hideLabel:true
				});
				return roleTemp;
			}
		}
	}
	var roleTemp = new Ext.form.Checkbox({
		boxLabel: role.desc,
		name: 'reroleList',
		inputValue: role.roleId,
		checked: false,
		hideLabel: true
	});
	return roleTemp;
}

function save(fpanel,start){

	var urlPost;
	var paramsPost;
	addWin = Ext.getCmp('addW');
	updWin = Ext.getCmp('updW');
	if (addWin) {
		urlPost = 'addManager.shtml';
//		paramsPost = {};
	}
	else if (updWin) {
			fpanel.findById('attrP').show();
			fpanel.findById('role').show();
			urlPost = 'updateManager.shtml';
			paramsPost = {
				managerId: managerId
			};
	}
	else {
			Ext.Msg.alert('信息', '提交窗口错误参数!');
			window.close();
	}
	
	if (fpanel.form.isValid()) {
		if (isRoleValid(fpanel.findById('role'))) {
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
			});
		}
		else {
			Ext.Msg.alert('信息', '行程内容必须填写完整!');
		}
	}
	else {
		Ext.Msg.alert('信息', '请填写完成再提交!');
	}
}

function isRoleValid(rolePanel){
	for (var i = 0; i < rolePanel.findByType('checkbox').length; i++) {
		if (rolePanel.findByType('checkbox')[i].checked) {
			return true;
		}
		if (i == rolePanel.findByType('checkbox').length - 1) {
			return false;
		}
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

function addItem(idName){
	addWin = Ext.getCmp('addW');
	
	if (!addWin) {
		comCreate(idName,'add','');
		addWin = new Ext.Window({
			title: '添加管理员',
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
}

function updateItem(idName,start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的管理员！');
	}
	else {
		if (cbsm.getCount() === 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('managerId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改管理员',
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
			Ext.MessageBox.alert('提示', '请选择一位管理员！');
		}
	}
}

function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('管理员删除操作', '未选择，请选择要删除的管理人员！');
	}
	else {
		Ext.MessageBox.confirm('确认删除', '你确认删除选择的数据吗？', function(btn){
			if (btn === "yes") {
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
			delAjax(start,jsonData);				
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
			var returnJosn = Ext.util.JSON.decode(result.responseText); 
			if(returnJosn.success===true)
			{
				Ext.MessageBox.alert('删除操作', '删除管理员成功！');
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
			Ext.MessageBox.alert('删除操作', '删除管理员失败！');
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

function allRefresh(objectStore){
	objectStore.load({
		params: {
			start: 0,
			limit:pageSize
		}
	});
}