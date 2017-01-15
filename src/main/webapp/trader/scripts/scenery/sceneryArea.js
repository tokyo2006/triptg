
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
	var idName = new Array('name','firstLetter','ticketPrice','level','typeId','isTop','synopsis','subTopic');
	
	var sceneryAreaPagingToolbar = Ext.extend(Ext.PagingToolbar, {
    	paramNames : {start: 'start', limit: 'limit',areaId: 'areaId'},
    	doLoad: function(start){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.areaId] = areaId;
			this.store.load({
				params: o
			});
		}
	});
	
	var searchText = new Ext.form.TextField({
		id: 'searchT',
		name: 'searchT',
		width: 80,
		emptyText: '请输入名称'
	});
	
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
			areaId:areaId,
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
		
		bbar: new sceneryAreaPagingToolbar({
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
			},'-',{
			text: '添加',
			tooltip: '添加景点信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		},'-', {
			text: '修改',
			tooltip: '修改景点信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '添加景点介绍',
			tooltip: '添加景点详细信息',
			iconCls: 'add',
			handler: function(){
				updateItem2(idName,objectGrid.getBottomToolbar().cursor);
			}
		},'-', {
			text: '添加景点图片',
			tooltip: '添加景点图片信息',
			iconCls: 'add',
			handler: function(){
				updateItem3(idName,objectGrid.getBottomToolbar().cursor);
			}
		},'-',{
			text: '删除',
			tooltip: '删除景点信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}		
		},'-', {
			text: '查看',
			tooltip: '查看景点信息',
			iconCls: 'option',
			handler: function(){
				viewItem(objectGrid.getBottomToolbar().cursor);
			}		
		},'-',{
			text: '生成关键字',
			tooltip: '批量生成景点关键字信息',
			iconCls: 'option',
			handler: addKeyword
		},'-', '-', searchText,{
			text: '搜索',
			handler: function(){
				search(searchText, objectStore);
			}
		}],
		
		frame: true,
//		title:'景点信息表',
		region: 'center',
		height: 150,
		autoWidth: true,
		animCollapse: false,
		trackMouseOver: true
	});
	return objectGrid;
}

function comCreate(idName,actions,start){
	var attributePanel,areaPanel,regionPanel,sortPanel;
	
	var pickStore = new Ext.data.SimpleStore({
		fields:['displayValue','returnValue'],		
		data:[['无',0],['A',1],['AA',2],['AAA',3],['AAAA',4],['AAAAA',5]]
	});
	
	var pickCombo= new Ext.form.ComboBox({
			store: pickStore,       //先将值选出组合为三个数组赋值，示例如下
			id:'levelSelect',
			name:idName[3],
			hiddenName: idName[3],
			valueField:'returnValue',
	        displayField:'displayValue',
			fieldLabel:'景点等级',
	        typeAhead: true,
	        mode: 'local',
			bodyStyle: 'padding:3px,margin-bottom:6px',
			triggerAction: 'all',
	        emptyText:'请选择一个景点等级',
			loadingText:'正在加载请等待....',
			forceSelection:true,   
			selectOnFocus:true
	});
	
/*	
	var pickStore = new Ext.data.SimpleStore({
		fields:['displayValue','returnValue'],		
		data:[['地域级',0],['周边游',1],['国内长线',2],['出境游',3],['自由行',4],['国内自由行',5]]
	});
	
	var pickCombo= new Ext.form.ComboBox({
			store: pickStore,       //先将值选出组合为三个数组赋值，示例如下
			id:'flagSelect',
			name:'flag',
			hiddenName: 'flag',
			valueField:'returnValue',
	        displayField:'displayValue',
			fieldLabel:'景点大类',
	        typeAhead: true,
	        mode: 'local',
			listeners:{
				select:function(){
					Ext.getCmp('typeSelect').setDisabled(false);
					Ext.getCmp('typeSelect').store.load({params:{flag:this.value}});
				}
			},
			bodyStyle: 'padding:3px,margin-bottom:6px',
			triggerAction: 'all',
	        emptyText:'请选择一个景点大类',
			loadingText:'正在加载请等待....',
			forceSelection:true,   
			selectOnFocus:true
	});	

	var pickStore2 = new Ext.data.SimpleStore({
		fields:['displayValue','returnValue'],
		proxy: new Ext.data.HttpProxy({
			url: 'getSceneryTypeInfo.shtml',
			method: 'POST'
		})
	});
	
	var pickCombo2= new Ext.form.ComboBox({
			store: pickStore2,       //先将值选出组合为三个数组赋值，示例如下
			id:'typeSelect',
			name:idName[4],
			hiddenName: idName[4],
			valueField:'returnValue',
	        displayField:'displayValue',
			fieldLabel:'类型选择',
	        typeAhead: true,
			disabled:true,
	        mode: 'local',
			bodyStyle: 'padding:3px,margin-bottom:6px',
			triggerAction: 'all',
	        emptyText:'请选择一个类型',
			loadingText:'正在加载请等待....',
			forceSelection:true,   
			selectOnFocus:true
	});*/
			
	attributePanel = new Ext.Panel({
		title: '景点信息',
		id: 'attrP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 300,
		defaults: {
			xtype: 'textfield',
			width: 230,
			anchor: '95%'
		},
		
		items: [{
			fieldLabel: '名称',
			allowBlank: false,
			name: idName[0],
			id: idName[0]			
		},{
			fieldLabel: '首字母',
			name: idName[1],
			id: idName[1]			
		},{
			fieldLabel: '景点标签',
			name: idName[7],
			id: idName[7]			
		},{
			xtype: 'textarea',
			height: 120,
			grow: false,
			fieldLabel: '景点票价',
			name: idName[2],
			id: idName[2]			
		},pickCombo,{
			fieldLabel: '是否置顶',
			xtype:'checkbox',
			checked:false,
			inputValue:'1',
			name: idName[5],
			id: idName[5]			
		},{
			xtype: 'textarea',
			fieldLabel: '简介',
			height: 120,
			grow: false,
			name: idName[6],
			id: idName[6]
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
			tbar: [{
				text: '删除所属区域',
				tooltip: '删除所属区域',
				iconCls: 'remove',
				handler: function(){
					var temp = Ext.getCmp('reAreaP');
					temp.body.update('未选择');
					reArea = '';
				}
			}],
			
			html:'未选择',
			collapsed: false,
			height: 280
			
		}]
	});
	
/*	regionPanel = new Ext.Panel({
		title: '景点属性选择',
		id: 'regionP',
		height: 280,
		layout: 'column',
		bodyStyle: 'padding:10px',
		autoHeight: true,
		autoScroll: true,
		collapsible: true,
		items: [{
			collapsible: true,
			title: '景点类别选择',
			xtype: 'treepanel',
			columnWidth: .4,
			bodyStyle: 'padding:3px,margin-right:5px',
			minWidth: 80,
			height: 280,
			autoScroll: true,
			split: true,
			loader: new Ext.tree.TreeLoader({
				dataUrl: 'getRegionByNode.shtml'
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
					clickRegionNode(n);
				},
				beforeload: function(node){
					this.loader.dataUrl = 'getRegionByNode.shtml';
				}
			}
		}, {
			xtype: 'panel',
			columnWidth: .02,
			border: false
		},{
			xtype: 'panel',
			id:'regions',
			title: '景点类型',
			bodyStyle: 'padding:10px 0 0 10px;',
			columnWidth: .58,
			tbar: [{
				text: '删除类型',
				tooltip: '删除类型',
				iconCls: 'remove',
				handler: function(){
					var temp = Ext.getCmp('regions');
					temp.body.update('未选择');
					reRegion.length = 0;
					regionHtml='';
				}
			}],
			
			html:'未选择',
			collapsed: false,
			height: 280
			
		}
		]
	});*/
	
	sortPanel = new Ext.Panel({
		title: '景点类别选择',
		id: 'sortP',
		renderTo: 'sortPanel',
		bodyStyle: 'padding:10px',
		layout: 'column',
		
		autoScroll: true,
		height: 300
	});
	
	tabPanel = new Ext.TabPanel({
		activeTab: 0,
		autoHeight: true,
		items: [attributePanel,areaPanel,sortPanel]
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
	
	var subSortPanel1=new Ext.Panel({
			title: '自然景观',
			id: 'nature',
			stateId: '-1',
			titleCollapse: true,
			collapsible: true,
			collapsed: true,
			columnWidth: .5,
			bodyStyle: 'margin-bottom:6px',
			listeners: {
				'expand': {
					fn: expandSort,
					scope: this
				}
			},
			layout: 'column'
		});
	var subSortPanel2=new Ext.Panel({
			title: '人文古迹',
			id: 'showplace',
			stateId: '-1',
			titleCollapse: true,
			collapsible: true,
			collapsed: true,
			columnWidth: .5,
			bodyStyle: 'margin-bottom:6px',
			listeners: {
				'expand': {
					fn: expandSort,
					scope: this
				}
			},
			layout: 'column'
		});

	if(actions!=='add'){
		updateAjax(fpanel,tabPanel,actions,idName,sortPanel);
	}
	else{
//		tabPanel.setActiveTab(sortPanel);
		sortPanel.add(subSortPanel1);
		sortPanel.add(subSortPanel2);
		sortPanel.doLayout();
	}	
}

function expandSort(item){
	if(item.stateId!==0)
	{
		sortAjax(item);
		item.stateId=0;
	}
}

function sortAjax(item){
	var flag, subSortPanel;
	if(item.id==='nature'){
		flag=0;
		subSortPanel= Ext.getCmp('nature');
	}
	else{
		flag=1;
		subSortPanel=Ext.getCmp('showplace');
	}
	Ext.Ajax.request({
		url: 'getSceneryTypeInfo.shtml',
		method: 'POST',
		params: {
			flag: flag
		},
		success: function(result, request){
			initSort(result, subSortPanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载类别失败！');
			window.close();
		}
	});
}

/**
 * 创建并加载城市数据
 * @param {Object} result
 * @param {Object} subSortPanel
 */
function initSort(result, subSortPanel){
	var resultJosn = doJSON(result.responseText);
	
	if (resultJosn.success == true) {
	var scList = resultJosn.scList;
	
	var childrenCityComList;
	var childcolumncout;
	var childcity;
	var childcityList = new Array();
	
	childcolumncout = parseInt(scList.length/2);
	if (scList.length % 2 == 0) {
		childcolumncout = childcolumncout;
	}
	else {
		childcolumncout = childcolumncout + 1;
	}
	
	
	for (var j = 0; j < scList.length; j++) {
	
		childcity = createChildCity(scList[j]);
		if (j < childcolumncout) {
			childcityList.push(childcity);
			if (j == (childcolumncout - 1)) {
				childrenCityComList = createChildCityList(childcityList);
				subSortPanel.add(childrenCityComList);
				childcityList.length = 0;
			}
		}
		else {
			childcityList.push(childcity);
			if (j == (scList.length - 1)) {
				childrenCityComList = createChildCityList(childcityList);
				subSortPanel.add(childrenCityComList);
				childcityList.length = 0;
			}
		}
	}
	subSortPanel.doLayout();
	}
	else {
		Ext.MessageBox.alert('加载失败', resultJosn.msg);
	}
}


/**
 * 创建并加载城市数据
 * @param {Object} result
 * @param {Object} subSortPanel
 */
function initUpdateSort(sortPanel, listOne, listTwo, subType){
	var subSortPanel3 = new Ext.Panel({
			title: '自然景观',
			id: 'nature',
			stateId: '-1',
			titleCollapse: true,
			collapsible: true,
			collapsed: true,
			columnWidth: .49,
			bodyStyle: 'margin-bottom:6px;',
			listeners: {
				'expand': {
					fn: expandSort,
					scope: this
				}
			},
			layout: 'column'
		});
	
	var subSortPanel4 = new Ext.Panel({
			title: '人文古迹',
			id: 'showplace',
			stateId: '-1',
			titleCollapse: true,
			collapsible: true,
			collapsed: true,
			columnWidth: .49,
			bodyStyle: 'margin-bottom:6px',
			listeners: {
				'expand': {
					fn: expandSort,
					scope: this
				}
			},
			layout: 'column'
		});
	if (subType === 0) {
		subSortPanel3.collapsed=false;
		subSortPanel4.collapsed=false;
		cityUpdateAjax(subSortPanel3, listOne);	
		cityUpdateAjax(subSortPanel4, listTwo);	
	}
	else 
		if (subType === 1) {
			subSortPanel3.collapsed=false;
			cityUpdateAjax(subSortPanel3, listOne);	
		}
		else 
			if (subType === 2) {
				subSortPanel4.collapsed=false;
				cityUpdateAjax(subSortPanel4, listTwo);
			}
	sortPanel.add(subSortPanel3);
	sortPanel.add(subSortPanel4);
	sortPanel.doLayout();
}

function cityUpdateAjax(cityPanel,userAreaList){
	var flag;
	if(cityPanel.id==='nature'){
		flag=0;
	}
	else{
		flag=1;
	}
	Ext.Ajax.request({
		url: 'getSceneryTypeInfo.shtml',
		method: 'POST',
		params: {
			flag: flag
		},
		success: function(result, request){
			initUpdateCity(result, cityPanel,userAreaList);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载线路失败！');
			window.close();
		}
	});
}

function initUpdateCity(result, cityPanel, userAreaList){
	var cityJosn = doJSON(result.responseText);
	
	if (cityJosn.success == true) {
	
		var cityList = cityJosn.scList;
		var childrenCityComList;
		var childcolumncout;
		var childcity;
		var childcityList = new Array();
		
		childcolumncout = parseInt(cityList.length / 2);
		if (cityList.length % 2 == 0) {
			childcolumncout = childcolumncout;
		}
		else {
			childcolumncout = childcolumncout + 1;
		}
		
		for (var j = 0; j < cityList.length; j++) {
		
			childcity = createUpdateChildCity(cityList[j], userAreaList);
			if (j < childcolumncout) {
				childcityList.push(childcity);
				if (j == (childcolumncout - 1)) {
					childrenCityComList = createChildCityList(childcityList);
					cityPanel.add(childrenCityComList);
					childcityList.length = 0;
				}
			}
			else {
				childcityList.push(childcity);
				if (j == (cityList.length - 1)) {
					childrenCityComList = createChildCityList(childcityList);
					cityPanel.add(childrenCityComList);
					childcityList.length = 0;
				}
			}
		}
		cityPanel.doLayout();
		cityPanel.stateId=0;
	}
	else {
		Ext.MessageBox.alert('加载失败', cityJosn.msg);
	}
}

function createChildCity(item){
	var cityTemp = new Ext.form.Checkbox({
		boxLabel: item.name,
		name: 'typeData',
		inputValue: item.typeId,
		checked: false,
		hideLabel: true
	})
	return cityTemp;
}

function createUpdateChildCity(city,sortList){
	if(sortList.length>0)
	{
		for(var i=0; i<sortList.length;i++)
		{
			if(sortList[i].typeId===city.typeId)
			{
				var cityTemp = new Ext.form.Checkbox({
        			boxLabel:city.name,
        			name:'typeData', 
        			inputValue: city.typeId,
        			checked: true,
        			hideLabel:true
				});
				return cityTemp;
			}
		}
	}
	var cityTemp = new Ext.form.Checkbox({
		boxLabel: city.name,
		name: 'typeData',
		inputValue: city.typeId,
		checked: false,
		hideLabel: true
	})
	return cityTemp;
}

function createChildCityList(childcityList){
	var childcityListtemp = {
		bodyStyle: 'padding:3px',
		columnWidth: .5,
		layout: 'form',
		border: false,
		items: childcityList
	};
	return childcityListtemp;
}

/////////////////////

function clickAreaNode(item){
	var temp = Ext.getCmp('reAreaP');
	if(reArea!==''){
		Ext.Msg.alert('提示','所属区域只能选择一个，请先删除再修改！');
	}
	else{
		reArea=item.id;
		temp.body.update(item.text);
	}
}

/*
function clickRegionNode(item){
	var temp = Ext.getCmp('regions');	
	var sameNum = 0;
	
	for (var i = 0; i < reRegion.length; i++) {
			if (item.id === reRegion[i]) {
				sameNum = 1;
				break;
			}
		}
	
	if (reRegion.length===0) {
		reRegion[0] = item.id;
		regionHtml = item.text;
		temp.body.update(regionHtml);
	}
	else 
		if (sameNum === 0) {
			reRegion[reRegion.length]=item.id;
			regionHtml += ',' + item.text;
			temp.body.update(regionHtml);
		}
	sameNum = 0;
}*/

function updateAjax(fpanel,tabPanel,Id,idName,sortPanel){
	Ext.Ajax.request({
		url: 'getSingleScenery.shtml',
		method: 'POST',
		params: {sceneryId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,tabPanel,idName,sortPanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载景点信息失败！');
			window.close();
		}
	});
}

function initUpdate(result, fpanel, tabPanel, idName, sortPanel){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.sc;
		
		fpanel.findById(idName[0]).setValue(list.name);
		fpanel.findById(idName[1]).setValue(list.firstLetter);
		fpanel.findById(idName[2]).setValue(list.ticketPrice);
		fpanel.findById('levelSelect').setValue(list.level);
		fpanel.findById(idName[5]).setValue(list.isTop);
		fpanel.findById(idName[6]).setValue(list.synopsis);
		fpanel.findById(idName[7]).setValue(list.subTopic);
		
		tabPanel.setActiveTab(fpanel.findById('areaP'));
		var temp, initDepth, initHtml;
		if (list.areas.length !== 0) {
			temp = Ext.getCmp('reAreaP');
			initDepth = list.areas[0].depth;
			initHtml = list.areas[0].name;
			reArea = list.areas[0].areaId;
			for (var i = 1; i < list.areas.length; i++) {
				if (list.areas[i].depth > initDepth) {
					initDepth = list.areas[i].depth;
					initHtml = list.areas[i].name;
					reArea = list.areas[i].areaId;
				}
			}
			temp.body.update(initHtml);
		}
		
//		tabPanel.setActiveTab(fpanel.findById('regionP'));
//		temp = Ext.getCmp('regions');
//		if (list.regions.length !== 0) {
//			temp = Ext.getCmp('regions');
//			initHtml = list.regions[0].name;
//			reRegion[0] = list.regions[0].regionId;
//			for (var i = 1; i < list.regions.length; i++) {
//				initHtml += ',' + list.regions[i].name;
//				reRegion[i] = list.regions[i].regionId;
//			}
//			temp.body.update(initHtml);
//			regionHtml=initHtml;
//		}
		
		tabPanel.setActiveTab(fpanel.findById('sortP'));
		if (list.types.length >= 0) {
			var subOne = 0, subTwo = 0, j = 0, k = 0, subSortPanel;
			var listOne = new Array(), listTwo = new Array();
			if (list.types.length > 0) {
				for (var i = 0; i < list.types.length; i++) {
					if (parseInt(list.types[i].flag) === 0) {
						listOne[j] = list.types[i];
						j++;
					}
					else {
						listTwo[k] = list.types[i];
						k++;
					}
				}
			}
			if ((listOne.length > 0) && (listTwo.length > 0)) {
				initUpdateSort(sortPanel, listOne, listTwo, 0);
			}
			else 
				if (listOne.length > 0) {
					initUpdateSort(sortPanel, listOne, '', 1);
				}
				else 
					if (listTwo.length > 0) {
						initUpdateSort(sortPanel, '', listTwo, 2);
					}
					else {
						initUpdateSort(sortPanel, '', '', 3);
					}
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
	if (reArea === '' ) {
		checkValue = false;
	}
	
	if (addWin) {
		urlPost = 'addScenery.shtml';
		paramsPost = {
			reArea: reArea
//			reRegion:reRegion.join(',')
		};
	}
	else 
		if (updWin) {
			urlPost = 'updateScenery.shtml';
			paramsPost = {
				sceneryId: selectRecord.get('sceneryId'),
//				reRegion:reRegion.join(','),
				reArea: reArea
			};
		}
		else {
			Ext.Msg.alert('信息', '提交窗口错误参数!');
			window.close();
		}
	
	//提交数据
	if (fpanel.form.isValid() && checkValue) {
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
						reArea='';
//						reRegion.length=0,regionHtml='';
						
						objectStore.load({
							params: {
								areaId:areaId,
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
	reArea='';
//	reRegion.length=0,regionHtml='';
}

function winClose(item){
	item.close();
	reArea='';
//	reRegion.length=0,regionHtml='';
}

function addItem(idName){
	addWin = Ext.getCmp('addW');
	
	if (!addWin) {
		comCreate(idName,'add',0);
		addWin = new Ext.Window({
			title: '添加景点信息',
			id: 'addW',
			closable: true,
			width: 580,
			height: 415,
			modal: true,
			layout: 'fit',
			listeners:{
						close:{
							fn:winClose,
							scope:this
						}
					},
			items: [fpanel]
		});
	}
	addWin.show();
	
	Ext.getCmp('nature').expand();
	Ext.getCmp('showplace').expand();
	
	if (areaId !== '') {
		reArea = areaId;
		Ext.Ajax.request({
			url: 'getSingleArea.shtml',
			method: 'POST',
			params: {
				areaId: areaId
			},
			success: function(result, request){
				var resultJosn = doJSON(result.responseText);
				if (resultJosn.success == true) {
					var list = resultJosn.area;
					Ext.getCmp('reAreaP').html=list.name;
				}
				else {
					Ext.MessageBox.alert('加载失败', resultJosn.msg);
				}
			},
			failure: function(result, request){
				Ext.MessageBox.alert('加载类别失败！');
				window.close();
			}
		});
	}
}

function updateItem(idName,start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的景点！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('sceneryId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改景点信息',
					id: 'updW',
					closable: true,
					width: 580,
					height: 415,
					plain: true,
					modal: true,
					layout: 'fit',
					listeners:{
						close:{
							fn:winClose,
							scope:this
						}
					},
					items: [fpanel]
				});
			}
			updWin.show();
			updWin.doLayout();
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条景点信息！');
		}
	}
}
function updateItem2(idName,start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要添加详细信息的景点！');
	}
	else {
		if (cbsm.getCount() == 1) {
			window.open('getSceContent.shtml?sceneryId='+selectRecord.get('sceneryId'));
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条景点信息！');
		}
	}
}

function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('景点信息删除操作', '未选择，请选择要删除的景点信息！');
	}
	else {
		Ext.MessageBox.confirm('确认景点删除', '你确认景点删除选择的数据吗？', function(btn){
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
		url: 'delScenery.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('景点信息删除操作', '删除景点信息成功！');
			}
			else
			{
				Ext.MessageBox.alert('删除操作', returnJosn.msg);
				objectStore.load({
					params: {
						areaId:areaId,
						start: start,
						limit: pageSize
					}
				});
			}
		},
		failure: function(result, request){
			Ext.MessageBox.alert('景点信息删除操作', '删除景点信息失败！');
			objectStore.load({
					params: {
						areaId:areaId,
						start: start,
						limit: pageSize,
						delData: jsonData
					}
				});
		}
	});
}

function addKeyword(){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('景点关键字信息操作', '未选择，请选择要添加关键字的景点信息！');
	}
	else {
		Ext.MessageBox.confirm('确认添加景点关键字信息', '你确认添加景点关键字信息吗？', function(btn){
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
				}
			addKeywordAjax(jsonData);				
			}
		});
	}
}

function addKeywordAjax(jsonData){
	Ext.Ajax.request({
		url: 'addKeyWordList.shtml',
		method: 'POST',
		params: {
			kpData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('添加景点关键字信息操作', '添加景点关键字信息成功！');
			}
			else
			{
				Ext.MessageBox.alert('添加景点关键字信息操作', returnJosn.msg);
			}
		},
		failure: function(result, request){
			Ext.MessageBox.alert('添加景点关键字信息操作', '删除景点信息失败！');
		}
	});
}


function allRefresh(store){
	store.load({
		params: {
			areaId:areaId,
			start: 0,
			limit:pageSize
		}
	});
}

function updateItem3(idName, start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改图片操作', '请选择要修改图片的景点！');
	}
	else {
		if (cbsm.getCount() == 1) {
			window.open('picture.jsp?sceneryId=' + selectRecord.get('sceneryId'));
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条景点信息！');
		}
	}
}

function viewItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('查看操作', '请选择要查看的景点！');
	}
	else {
		if (cbsm.getCount() == 1) {
			window.open('../../scenery/sceneryDetail/' + selectRecord.get('sceneryId')+'.html');
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条景点信息！');
		}
	}
}

function search(searchText,objectStore){
	var name=searchText.getValue();
	if(name==='请输入名称'){
		name='';
	}
	objectStore.load({
					params: {
						areaId:areaId,
						name:name,
						start: 0,
						limit: pageSize
					}
				});
}