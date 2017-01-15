
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
var addWin,updWin,fileUpWin;
var reArea='';

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
	var idName = new Array('title','origin','mark','promotion','hot','url','subTitle','picUrl');
	
	var searchText = new Ext.form.TextField({
		id: 'searchT',
		name: 'searchT',
		width: 120,
		emptyText: '请输入名称'
	});
	
	var typePagingToolbar = Ext.extend(Ext.PagingToolbar, {
		paramNames: {
			start: 'start',
			limit: 'limit',
			name: 'name',
			typeId:'typeId'
		},
		
		doLoad: function(start, typeId,name){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.typeId] = typeId;
			o[pn.name] = name;
			if (this.fireEvent('beforechange', this, o) !== false) {
				this.store.load({
					params: o
				});
			}
		},
		
		onClick: function(which){
			var name = Ext.getCmp('searchT').getValue();
			var store = this.store;
			switch (which) {
				case "first":
					this.doLoad(0, typeId,name);
					break;
				case "prev":
					this.doLoad(Math.max(0, this.cursor - this.pageSize), typeId,name);
					break;
				case "next":
					this.doLoad((this.cursor + this.pageSize), typeId,name);
					break;
				case "last":
					var total = store.getTotalCount();
					var extra = total % this.pageSize;
					var lastStart = extra ? (total - extra) : total - this.pageSize;
					this.doLoad(lastStart, typeId,name);
					break;
				case "refresh":
					this.doLoad(this.cursor,typeId,'');
					break;
			}
		}
	});
	
	/*
	 * 数据解析器
	 */
	var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'newsId',
        fields: [
            {name: 'newsId', mapping:'newsId'},
            {name: 'title', mapping:'title'},
			{name: 'subTitle', mapping:'subTitle'},
			{name: 'content', mapping:'content'},
			{name: 'publishDateStr', mapping:'publishDateStr'},
			{name: 'origin', mapping:'origin'},
			{name: 'hit', mapping:'hit'},
			{name: 'writer', mapping:'writer'},
			{name: 'picUrl', mapping:'picUrl'},
			{name: 'mark', mapping:'mark'},
			{name: 'promotion', mapping:'promotion'},
			{name: 'hot', mapping:'hot'},
			{name: 'url', mapping:'url'}
        ]
    });
	
	objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllNews.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'publishDateStr', direction: "DESC"}
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
			{id:'newsId',header: '标识号', hidden:true, hideable:false, sortable: true, 
			dataIndex: 'newsId'},
			{header: '标题', hideable:true, sortable: true, 
			dataIndex: 'title'},
			{header: '短标题', hideable:true, sortable: true, 
			dataIndex: 'subTitle'},
			{header: '新闻介绍', hideable:true, sortable: false, renderer:function(v,p,record){
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
			{header: '图片', hideable:true, sortable: true,renderer:function(v,p,record){
				if(v===''||v===null){
					return '<font color=\"#ee928f\">无</font>';
				}
				else{
					return '有';
				}
			}, 
			dataIndex: 'picUrl'},
			{header: '发布日期', hideable:true, sortable: true, 
			dataIndex: 'publishDateStr'},
			{header: '来源', hideable:true, sortable: true, 
			dataIndex: 'origin'},
			{header: '作者', hideable:true, sortable: true, 
			dataIndex: 'writer'},
			{header: '标签', hideable:true, sortable: true, 
			dataIndex: 'mark'},
			{header: '推荐', hideable:true, sortable: true, 
			dataIndex: 'promotion'},
			{header: '热点', hideable:true, sortable: true, 
			dataIndex: 'hot'},
			{header: '点击数', hideable:true, sortable: true, 
			dataIndex: 'hit'}
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
			refreshText:'刷新新闻信息',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新新闻信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
			text: '添加',
			tooltip: '添加新闻信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		},'-', {
			text: '修改',
			tooltip: '修改新闻信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '添加新闻介绍',
			tooltip: '添加新闻详细信息',
			iconCls: 'add',
			handler: function(){
				updateItem2(idName,objectGrid.getBottomToolbar().cursor);
			}
		},'-', {
			text: '删除',
			tooltip: '删除新闻信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}		
		},'-', {
			text: '查看',
			tooltip: '查看新闻信息',
			iconCls: 'option',
			handler: function(){
				viewItem(objectGrid.getBottomToolbar().cursor);
			}		
		},'-', '-', searchText,{
			text: '搜索',
			handler: function(){
				search(searchText, objectStore);
			}
		}],
		
		frame: true,
//		title:'新闻信息表',
		region: 'center',
		height: 150,
		autoWidth: true,
		animCollapse: false,
		trackMouseOver: true
	});
	return objectGrid;
}

function comCreate(idName,actions,start){
	var attributePanel,areaPanel,sortPanel;
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
			fieldLabel:'新闻大类',
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
	        emptyText:'请选择一个新闻大类',
			loadingText:'正在加载请等待....',
			forceSelection:true,   
			selectOnFocus:true
	});	

	var pickStore = new Ext.data.SimpleStore({
		fields:['displayValue','returnValue'],
		proxy: new Ext.data.HttpProxy({
			url: 'getNewsTypeInfo.shtml',
			method: 'POST'
		})
	});
	
	var pickCombo= new Ext.form.ComboBox({
			store: pickStore,       //先将值选出组合为三个数组赋值，示例如下
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
		title: '新闻信息',
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
			fieldLabel: '名称',
			name: idName[0],
			id: idName[0]			
		},{
			fieldLabel: '短标题',
			allowBlank: true,
			maxLength:10,
			name: idName[6],
			id: idName[6]			
		},{
			fieldLabel: '来源',
			allowBlank: true,
			name: idName[1],
			id: idName[1]			
		},{
			fieldLabel: '新闻标签',
			allowBlank: true,
			name: idName[2],
			id: idName[2]			
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
					allowBlank: true,
					disabled: true,
					anchor: '95%',
					name: idName[7],
					id: idName[7]
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
			fieldLabel: '是否推荐',
			xtype:'checkbox',
			inputValue:'1',
			checked:false,
			name: idName[3],
			id: idName[3]			
		},{
			fieldLabel: '是否热点',
			xtype:'checkbox',
			checked:false,
			inputValue:'1',
			name: idName[4],
			id: idName[4]			
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
					if (n.isLeaf()) {
						clickAreaNode(n);
					}
					else {
						n.expand();
					}
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
		}
		]
	});
	
	sortPanel = new Ext.Panel({
		title: '新闻类别选择',
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
	
//	var subSortPanel1=new Ext.Panel({
//			title: '旅游新闻',
//			id: 'tourNews',
//			stateId: '-1',
//			titleCollapse: true,
//			collapsible: true,
//			collapsed: true,
//			columnWidth: .5,
//			bodyStyle: 'margin-bottom:6px',
//			listeners: {
//				'expand': {
//					fn: expandSort,
//					scope: this
//				}
//			},
//			layout: 'column'
//		});
//	var subSortPanel2=new Ext.Panel({
//			title: '美食新闻',
//			id: 'foodNews',
//			stateId: '-1',
//			titleCollapse: true,
//			collapsible: true,
//			collapsed: true,
//			columnWidth: .5,
//			bodyStyle: 'margin-bottom:6px',
//			listeners: {
//				'expand': {
//					fn: expandSort,
//					scope: this
//				}
//			},
//			layout: 'column'
//		});

	
	
	if(actions=='add'){
		sortAddAjax(sortPanel);
	}
	else{
		updateAjax(fpanel,tabPanel,actions,idName,sortPanel);
	}	
}

function sortAddAjax(sortPanel){
	Ext.Ajax.request({
		url: 'getNewsTypeParent.shtml',
		method: 'POST',
		success: function(result, request){
			initSortP(result, sortPanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载类别失败！');
			window.close();
		}
	});
}

function initSortP(result, sortPanel){
	var resultJosn = doJSON(result.responseText);
	
	if (resultJosn.success == true) {
		var strList = resultJosn.ntList;
		var childrenAreaComList;
		var childcolumncout;
		var childarea;
		var childareaList = new Array();
		
		childcolumncout = parseInt(strList.length / 2);
		if (strList.length % 2 == 0) {
			childcolumncout = childcolumncout;
		}
		else {
			childcolumncout = childcolumncout + 1;
		}
		for (var j = 0; j < strList.length; j++) {
		
			childarea = createChildArea(strList[j]);
			if (j < childcolumncout) {
				childareaList.push(childarea);
				if (j == (childcolumncout - 1)) {
					childrenAreaComList = createChildAreaList(childareaList);
					sortPanel.add(childrenAreaComList);
					childareaList.length = 0;
				}
			}
			else {
				childareaList.push(childarea);
				if (j == (strList.length - 1)) {
					childrenAreaComList = createChildAreaList(childareaList);
					sortPanel.add(childrenAreaComList);
					childareaList.length = 0;
				}
			}
		}
		
		sortPanel.doLayout();
	}
	else {
		Ext.MessageBox.alert('加载失败', resultJosn.msg);
	}	
}

function createChildArea(subject){
	var subjectTemp = new Ext.Panel({
		title: subject.name,
		id: subject.typeId,
		stateId: '-1',
		titleCollapse:true,
		collapsible: true,
		collapsed: true,
		bodyStyle: 'margin-bottom:6px',
		listeners: {
			'expand': {
				fn: expandSort,
				scope: this
			}
		},
		layout: 'column'
	})
	return subjectTemp;
}

function createChildAreaList(childareaList){
	var childareaListtemp = {
		bodyStyle: 'padding:3px',
		columnWidth: .49,
		layout: 'form',
		border: false,
		items: childareaList
	};
	return childareaListtemp;
}

function expandSort(item){
	if(item.stateId!==0)
	{
		sortSubAjax(item);
		item.stateId=0;
	}
}

function sortSubAjax(subSortPanel){
	Ext.Ajax.request({
		url: 'getNewsTypeInfo.shtml',
		method: 'POST',
		params:{
			parent:subSortPanel.id
		},
		success: function(result, request){
			initSubSort(result,subSortPanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载类别失败！');
			window.close();
		}
	});
}

function initSubSort(result,subSortPanel){
	var resultJosn = doJSON(result.responseText);
	
	if (resultJosn.success == true) {
	var strList = resultJosn.ntList;
	
	var childrenCityComList;
	var childcolumncout;
	var childcity;
	var childcityList = new Array();
	
	childcolumncout = parseInt(strList.length/2);
	if (strList.length % 2 == 0) {
		childcolumncout = childcolumncout;
	}
	else {
		childcolumncout = childcolumncout + 1;
	}
	
	
	for (var j = 0; j < strList.length; j++) {
	
		childcity = createChildCity(strList[j]);
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
			if (j == (strList.length - 1)) {
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

function sortUpdateAjax(sortPanel,types){
	Ext.Ajax.request({
		url: 'getNewsTypeParent.shtml',
		method: 'POST',
		success: function(result, request){
			initUpdateSort(result,types, sortPanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载类别失败！');
			window.close();
		}
	});
}

function initUpdateSort(result,userAreaList, sortPanel){
	var resultJosn = doJSON(result.responseText);
	
	if (resultJosn.success == true) {
	var areaList=resultJosn.ntList; 
	var childrenAreaComList;
	var childcolumncout;
	var childarea;
	var childareaList = new Array();
	
	childcolumncout = parseInt(areaList.length / 2);
	if (areaList.length % 2 == 0) {
		childcolumncout = childcolumncout;
	}
	else {
		childcolumncout = childcolumncout + 1;
	}
	
	for (var j = 0; j < areaList.length; j++) {
		childarea = createChildArea(areaList[j]);
		for (i = 0; i < userAreaList.length; i++) {
			if ((childarea.id === userAreaList[i].parent)) {
				childarea.collapsed = false;
				cityUpdateAjax(childarea, userAreaList);
				childarea.stateId = 0;
				break;
			}
		}
		
		if (j < childcolumncout) {
			childareaList.push(childarea);
			if (j == (childcolumncout - 1)) {
				childrenAreaComList = createChildAreaList(childareaList);
				sortPanel.add(childrenAreaComList);
				childareaList.length = 0;
			}
		}
		else {
			childareaList.push(childarea);
			if (j == (areaList.length - 1)) {
				childrenAreaComList = createChildAreaList(childareaList);
				sortPanel.add(childrenAreaComList);
				childareaList.length = 0;
			}
		}
	}
	sortPanel.doLayout();
	}
	else {
		Ext.MessageBox.alert('加载失败', resultJosn.msg);
	}
}

function cityUpdateAjax(subSortPanel,userAreaList){
	Ext.Ajax.request({
		url: 'getNewsTypeInfo.shtml',
		method: 'POST',
		params:{
			parent:subSortPanel.id
		},
		success: function(result, request){
			initUpdateSubSort(result,subSortPanel,userAreaList);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载类别失败！');
			window.close();
		}
	});
}

function initUpdateSubSort(result, cityPanel, userAreaList){
	var cityJosn = doJSON(result.responseText);
	
	if (cityJosn.success == true) {
	
		var cityList = cityJosn.ntList;
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
	var cityTemp;
	if(typeId==item.typeId){
		cityTemp = new Ext.form.Checkbox({
		boxLabel: item.name,
//		id:item.typeId,
		name: 'typeData',
		inputValue: item.typeId,
		checked: true,
		hideLabel: true
	});
	}
	else{
		cityTemp = new Ext.form.Checkbox({
		boxLabel: item.name,
//		id:item.typeId,
		name: 'typeData',
		inputValue: item.typeId,
		checked: false,
		hideLabel: true
	});
	}
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
	});
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
}

function updateAjax(fpanel,tabPanel,Id,idName,sortPanel){
	Ext.Ajax.request({
		url: 'getSingleNews.shtml',
		method: 'POST',
		params: {newsId:Id},
		success: function(result, request){
			initUpdate(result,fpanel,tabPanel,idName,sortPanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载新闻信息失败！');
			window.close();
		}
	});
}

function initUpdate(result, fpanel, tabPanel, idName, sortPanel){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.news;
		
		fpanel.findById(idName[0]).setValue(list.title);
		fpanel.findById(idName[1]).setValue(list.origin);
		fpanel.findById(idName[2]).setValue(list.mark);
		fpanel.findById(idName[3]).setValue(list.promotion);
		fpanel.findById(idName[4]).setValue(list.hot);
		fpanel.findById(idName[6]).setValue(list.subTitle);
		fpanel.findById(idName[7]).setValue(list.picUrl);
		
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
		
		tabPanel.setActiveTab(fpanel.findById('sortP'));
		sortUpdateAjax(fpanel.findById('sortP'),list.types);
	}
}

/**
 * 表单保存
 * @param {Object} fpanel
 */
function save(fpanel,start){
	var urlPost;
	var paramsPost;
	var checkValue = false;
	
	checkValue=isTypeValid(fpanel.findById('sortP'));
	
	fpanel.findById('picUrl').enable();
	if (addWin) {
		urlPost = 'addNews.shtml';
		paramsPost = {
			reArea: reArea
		};
	}
	else 
		if (updWin) {
			urlPost = 'updateNews.shtml';
			paramsPost = {
				newsId: selectRecord.get('newsId'),
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
				fpanel.findById('picUrl').disable();
				this.disabled = false;
			},
			success: function(fpanel, action){
			
				if (action.result === undefined) {
					Ext.MessageBox.alert('保存失败', '后台服务器错误!');
					fpanel.findById('picUrl').disable();
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
						fpanel.findById('picUrl').disable();
						this.disabled = false;
					}
				}
			}
		});
	}
	else {
		fpanel.findById('picUrl').disable();
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
	reArea='';
}

function winClose(item){
	item.close();
	reArea='';
}

function addItem(idName){
	addWin = Ext.getCmp('addW');
	
	if (!addWin) {
		comCreate(idName,'add',0);
		addWin = new Ext.Window({
			title: '添加新闻信息',
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
}

function updateItem(idName,start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的新闻！');
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('newsId'),start);

			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改新闻信息',
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
			Ext.MessageBox.alert('提示', '请选择一条新闻信息！');
		}
	}
}

function updateItem2(idName,start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要添加详细信息的新闻！');
	}
	else {
		if (cbsm.getCount() == 1) {
			window.open('getContent.shtml?newsId='+selectRecord.get('newsId'));
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条新闻信息！');
		}
	}
}

function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('新闻信息删除操作', '未选择，请选择要删除的新闻信息！');
	}
	else {
		Ext.MessageBox.confirm('确认新闻删除', '你确认新闻删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('newsId');
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
		url: 'delNews.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('新闻信息删除操作', '删除新闻信息成功！');
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
			Ext.MessageBox.alert('新闻信息删除操作', '删除新闻信息失败！');
			objectStore.load({
					params: {
						typeId:typeId,
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
			typeId:typeId,
			start: 0,
			limit:pageSize
		}
	});
}

function search(searchText,objectStore){
	var name=searchText.getValue();
	if(name==='请输入名称'){
		name='';
	}
	objectStore.load({
					params: {
						typeId:typeId,
						title:name,
						start: 0,
						limit: pageSize
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
							Ext.getCmp('picUrl').setValue(returnJosn.picUrl);
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

function isTypeValid(sortPanel){
	if (sortPanel.findByType('checkbox').length === 0) {
		return false;
	}
	for (var i = 0; i < sortPanel.findByType('checkbox').length; i++) {
		if (sortPanel.findByType('checkbox')[i].checked === true) {
			return true;
			break;
		}
		if (i === sortPanel.findByType('checkbox').length - 1) {
			return false;
		}
	}
}

function viewItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('查看操作', '请选择要查看的新闻！');
	}
	else {
		if (cbsm.getCount() == 1) {
			window.open('../../news/content/' + selectRecord.get('newsId')+'.html');
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条新闻信息！');
		}
	}
}