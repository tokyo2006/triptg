
Ext.onReady(function(){
	//get All unit Name for comboBox.
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	/*==============================全局变量t=============================================*/
	var id = 1; //行程内容id
	var contentPanel, fpanel;
	var objectGrid;
	var addWin, updWin;
	var selectRecord;
	var cbsm;
	var objectStore;
	
	initView(pageSize);
});

var pageSize = 27;
var addWin,updWin;
var lineId;
var themeBoxstore;

/*==============================中间新tabpanel中包含树的west=============================================*/
function initView(pageSize){
//	var titleString = '';
	
	objectGrid = initGrid(pageSize, node);
	
	/*
	 * 布局加载
	 */
	new Ext.Viewport({
		layout: 'border',
		items: [objectGrid]
	});
}




/**
 * 表格加载
 */
function initGrid(pageSize, node){
	var idName = new Array('title', 'subTitle', 'feeClude', 'feeUnclude', 'remark', 'journey', 'feature', 'purchase', 'selfBuy', 'safe');

	var searchText=new Ext.form.TextField({
		id:'searchT',	
		width: 150,
		emptyText: '请输入搜索信息'
	});	
	var searchCheckbox=new Ext.form.Checkbox({
		inputValue: '1',
		width:60,
		boxLabel: '批发商',
		hideLabel: true,
		labelSeparator: '',
		name: 'searchType',
		id: 'searchType'
	});
	
	var searchPagingToolbar = Ext.extend(Ext.PagingToolbar,{
		paramNames: {
			start: 'start',
			limit: 'limit',
			node: 'node',
			searchValue: 'title',
			searchType: 'searchType'
		},
		
		doLoad: function(start,searchValue,searchType,node){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.node] = node;
			o[pn.searchValue] = searchValue;
			o[pn.searchType] = searchType;
			if (this.fireEvent('beforechange', this, o) !== false) {
				this.store.load({
					params: o
				});
			}
		},
		
		onClick: function(which){
			var searchType=Ext.getCmp('searchType').getValue();
			var searchValue = Ext.getCmp('searchT').getValue();
			if (searchType) {
				searchType = 1;
			}
			else {
				searchType = 0;
			}
			var store = this.store;
			switch (which) {
				case "first":
					this.doLoad(0,searchValue,searchType,node);
					break;
				case "prev":
					this.doLoad(Math.max(0, this.cursor - this.pageSize),searchValue,searchType,node);
					break;
				case "next":
					this.doLoad((this.cursor + this.pageSize),searchValue,searchType,node);
					break;
				case "last":
					var total = store.getTotalCount();
					var extra = total % this.pageSize;
					var lastStart = extra ? (total - extra) : total - this.pageSize;
					this.doLoad(lastStart,searchValue,searchType,node);
					break;
				case "refresh":
					this.doLoad(this.cursor,'','',node);
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
		successProperty: 'success',
		idProperty: 'lineId',
		fields: [{
			name: 'lineId',
			type: 'string',
			mapping: 'lineId'
		}, {
			name: 'lineStar',
			type: 'int',
			mapping: 'lineStar'
		}, {
			name: 'title',
			type: 'string',
			mapping: 'title'
		}, {
			name: 'subTitle',
			type: 'string',
			mapping: 'subTitle'
		}, {
			name: 'feature',
			type: 'string',
			mapping: 'feature'
		}, {
			name: 'remark',
			type: 'string',
			mapping: 'remark'
		}, {
			name: 'delFlag',
			type: 'string',
			mapping: 'delFlag'
		}, {
			name: 'feeClude',
			type: 'string',
			mapping: 'feeClude'
		}, {
			name: 'feeUnclude',
			type: 'string',
			mapping: 'feeUnclude'
		}, {
			name: 'hit',
			type: 'int',
			mapping: 'hit'
		}, {
			name: 'purchase',
			type: 'string',
			mapping: 'purchase'
		}, {
			name: 'safe',
			type: 'string',
			mapping: 'safe'
		}, {
			name: 'selfBuy',
			type: 'string',
			mapping: 'selfBuy'
		}, {
			name: 'userId',
			type: 'string',
			mapping: 'userId'
		}, {
			name: 'writer',
			type: 'string',
			mapping: 'writer'
		}, {
			name: 'strDate',
			dateFormat: 'y-m-d',
			mapping: 'strDate'
		}]
	});
	
	objectStore = new Ext.data.GroupingStore({
		reader: objectReader,
		proxy: new Ext.data.HttpProxy({
			url: 'getAllLine.shtml',
			method: 'POST'
		}),
		remoteSort: true,
		sortInfo: {
			field: 'strDate',
			direction: "DESC"
		},
		groupField: 'strDate'
	});
	objectStore.load({
		params: {
			start: 0,
			limit: pageSize,
			node: node
		}
	});
	
	cbsm = new Ext.grid.CheckboxSelectionModel();
//	cbsm.handleMouseDown = Ext.emptyFn; //只改变背景不选中
	var rn = new Ext.grid.RowNumberer();
	
	
	/*
	 * 第二tab面板数据源，dataurl的请求带参数
	 */
	objectGrid = new Ext.grid.GridPanel({
		loadMask: true,
		ds: objectStore,
		id: node,
		columns: [rn, //行号列 
 				  cbsm, //CheckBox选择列,
		{
			id: 'title',
			header: "行程名称",
			hideable: true,
			sortable: true,
			dataIndex: 'title'
		}, {
			id: 'subTitle',
			header: "批发商",
			hideable: false,
			sortable: true,
			dataIndex: 'subTitle'
		}, {
			header: "行程填写者",
			hideable: true,
			sortable: true,
			dataIndex: 'writer'
		}, {
			header: "点击率",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'hit'
		}, {
			header: "行程星级",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'lineStar'
		}, {
			header: "lineId",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'lineId'
		}, {
			header: "备注",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'remark'
		}, {
			header: "delFlag",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'delFlag'
		}, {
			header: "feeClude",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'feeClude'
		}, {
			header: "feeUnclude",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'feeUnclude'
		}, {
			header: "purchase",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'purchase'
		}, {
			header: "创建时间",
			hidden: true,
			hideable: false,
			sortable: true, //renderer: Ext.util.Format.dateRenderer('y年m月d日'),
			dataIndex: 'strDate'
		}, {
			header: "行程特色",
			hidden: true,
			hideable: false,
			sortable: true,
			dataIndex: 'feature'
		}],
		sm: cbsm,
		
		view: new Ext.grid.GroupingView({
			forceFit: true,
			showGroupName: false,
			enableNoGroups: false, // REQUIRED!
			hideGroupedColumn: true,
			groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "条" : "条"]})'
		}),
		bbar: new searchPagingToolbar({
			pageSize: pageSize,
			store: objectStore,
			displayInfo: true,
			displayMsg: '显示 {0}-{1}条 / 共 {2} 条',
			refreshText:'刷新行程',
			emptyMsg: "无数据。"
		}),
		tbar: [{
				text: '刷新',
				tooltip: '刷新行程信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
			text: '添加',
			tooltip: '添加行程信息',
			iconCls: 'add',
			handler: function(){
				addItem(idName);
			}
		}, '-', {
			text: '修改',
			tooltip: '修改行程信息',
			iconCls: 'option',
			handler: function(){
				updateItem(idName,objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '删除',
			tooltip: '删除行程信息',
			iconCls: 'remove',
			handler: function(){
				delItem(objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '复制',
			tooltip: '复制行程信息',
			iconCls: 'copy',
			handler: function(){
				copyItem(objectGrid.getBottomToolbar().cursor);
			}
		}, '-', {
			text: '打印',
			tooltip: '打印行程信息',
			iconCls: 'printline',
			handler: function(){
				rowdblclick(objectGrid);
			}
		},'->',
		searchCheckbox,searchText,
		{
			text: '行程搜索',
			handler: function(){
				search(searchCheckbox, searchText, objectStore, objectGrid.getBottomToolbar().cursor);
			}
		}],
		
		listeners: {
			'rowdblclick': {
				fn: rowdblclick,
				scope: this
			}
		},
		frame: true,
		region: 'center',
		height: 150,
		autoWidth: true,
		collapsible: true,
		animCollapse: false,
		trackMouseOver: true
	});
	return objectGrid;
}

function rowdblclick(grid){
	var	selectRecord = grid.getSelectionModel().getSelected();
	if (selectRecord) {
		if (cbsm.getCount() == 1) {
			lineId=selectRecord.get('lineId');
			window.open('printSingelLine/'+lineId+'.html');
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条行程打印！');
		}
	}
	else{
		Ext.MessageBox.alert('提示', '请选择一条行程打印！');
	}
}

		///*==========================================================================*/
		////CURD函数
		///*==========================================================================*/
/*===========================添、改函数公用,创建frompanel及内容=============================*/
/**
 * 
 * @param {Object} idName
 */	
function comCreate(idName,actions,start){
	var attributePanel;
	var featurePanel;
	var customerActPanel;
	var safePanel;
	var areaPanel;
	var regionPanel;
	var tabpanel;
	
	themeBoxstore = new Ext.data.SimpleStore({
		fields:['displayValue'],		
		data: lineTheme
	});
	
	attributePanel = new Ext.Panel({
		title: '行程属性',
		id:'attrP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 300,
		defaults: {
			width: 230,
			anchor: '95%'
		},
		
		items: [{
			xtype: 'textfield',
			fieldLabel: '行程名称',
			allowBlank: false,
			name: idName[0],
			id: idName[0]
		}, {
			xtype: 'textfield',
			fieldLabel: '短标题',
			name: idName[1],
			id: idName[1],
			allowBlank: false
		}, {
			xtype: 'combo',
			fieldLabel: '行程主题',
			name: 'theme',
			id: 'theme',
			displayField:'displayValue',
			store: themeBoxstore,
	        typeAhead: true,
	        mode: 'local',
	        triggerAction: 'all',
			bodyStyle: 'padding:3px',
	        emptyText:'无主题',
			loadingText:'正在加载请等待....',
			forceSelection:true,   //强制只能选择，不让输入
	        selectOnFocus:true
		}, {
			xtype: 'textarea',
			fieldLabel: '价格包含',
			height: 80,
			grow: false,
			name: idName[2],
			id: idName[2]
		}, {
			xtype: 'textarea',
			fieldLabel: '价格不包含',
			height: 80,
			grow: false,
			name: idName[3],
			id: idName[3]
		}, {
			xtype: 'textarea',
			fieldLabel: '备注',
			height: 100,
			grow: false,
			name: idName[4],
			id: idName[4]
		}]
	});
	
	contentPanel = new Ext.Panel({
		title: '行程',
		id: idName[5],
		layout: 'form',
		bodyStyle: 'padding:10px',
		height: 300,
		autoScroll: true,
		
		tbar: [{
			text: '添加行程',
			tooltip: '添加行程',
			handler: function(){
				addcontext();
			},
			iconCls: 'add'
		}, '-', {
			text: '删除行程',
			tooltip: '删除已有行程',
			handler: function(){
				deletecontext();
			},
			iconCls: 'remove'
		}]
	});
	
	featurePanel = new Ext.Panel({
		title: '行程特色',
		id:'featurePanel',
		height: 300,
		layout: 'form',
		bodyStyle: 'padding:10px',
		defaults: {
			xtype: 'textarea',
			grow: false,
			growMin: 230,
			anchor: '95%'
		},
		autoHeight: true,
		items: {
			fieldLabel: '行程特色',
			height: 230,
			name: idName[6],
			id: idName[6]
		}
	});
	
	customerActPanel = new Ext.Panel({
		cls: 'x-plain',
		id:'customerActPanel',
		title: '用户自费',
		bodyStyle: 'padding:10px',
		layout: 'form',
		height: 300,
		defaults: {
			xtype: 'textarea',
			grow: false,
			growMin: 130,
			anchor: '95%'
		},
		autoHeight: true,
		items: [{
			name: idName[7],
			id: idName[7],
			height: 140,
			fieldLabel: '购物活动'
		}, {
			fieldLabel: '自费活动',
			height: 140,
			name: idName[8],
			id: idName[8]
		}]
	});
	
	safePanel = new Ext.Panel({
		cls: 'x-plain',
		id:'safePanel',
		title: '安全守则',
		layout: 'form',
		height: 300,
		bodyStyle: 'padding:10px',
		defaults: {
			xtype: 'textarea',
			grow: false,
			growMin: 230,
			anchor: '95%'
		},
		autoHeight: true,
		items: [{
			name: idName[9],
			id: idName[9],
			height: 260,
			fieldLabel: '安全守则'
		}]
	});
	
	areaPanel = new Ext.Panel({
		title: '途径城市选择',
		renderTo:'areaPanel',
		id: 'area',
		bodyStyle: 'padding:10px',
		layout: 'column',
		autoScroll: true,
		height: 300
	});
	
	regionPanel = new Ext.Panel({
		title: '行程类别选择',
		id: 'region',
		renderTo:'regionPanel',
		bodyStyle: 'padding:10px',
		layout: 'column',
		autoScroll: true,
		height: 300
	});
	
	tabpanel = new Ext.TabPanel({
		activeTab: 0,
		autoHeight: true,
		items: [attributePanel, contentPanel, featurePanel, customerActPanel, safePanel, areaPanel, regionPanel]
	});
	
	fpanel = new Ext.FormPanel({
		region: 'center',
		bodyStyle: 'padding:5px',
		collapsible: true,
		items: [tabpanel],
		
		buttons: [{
			text: '保存',
			handler: function(){
				save(fpanel,start);
			}
		}, {
			text: '重置',
			handler: function(){
				panelreset(fpanel);
			}
		}, {
			text: '取消',
			handler: function(){
				cancel();
			}
		}]
	});
	if(actions=="add")
	{
		areaRegionAjax(areaPanel, regionPanel);
	}
	else
	{
		areaRegionUpdateAjax(idName,areaPanel, regionPanel,actions);
	}
	
}

function panelreset(fpanel){
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
	
	
/**
 * 表单保存
 * @param {Object} fpanel
 */
function save(fpanel,start){
	var urlPost;
	var paramsPost;
	
	//组装数据
	var journey = Ext.getCmp('journey');
	var jArray = journey.findByType('fieldset');
	var jNumber = jArray.length;
	var Journey = new Array();
	
	for (var i = 1; i <= jNumber; i++) {
		var fstemp = journey.findById(i + 'day');
		var ftArray = new Array();
		ftArray[0] = fstemp.findById('contentTitle' + i).getValue();
		ftArray[1] = fstemp.findById('meal' + i).getValue();
		ftArray[2] = fstemp.findById('room' + i).getValue();
		ftArray[3] = fstemp.findById('contentText' + i).getValue();
		
		var ftArraystring = ftArray.join("@@@");
		Journey[i - 1] = ftArraystring;
	}
	
	var content = Journey.join("###");
	
	if (addWin) {
		urlPost = 'addLine.shtml';
		paramsPost = {
			content: content,
			reregion: regionId
		};
	}
	else if (updWin) {
			fpanel.findById('area').show();
			fpanel.findById('region').show();
			urlPost = 'updateLine.shtml';
			paramsPost = {
				content: content,
				reregion: regionId,
				lineId: lineId
			};
	}
	else {
			Ext.Msg.alert('信息', '提交窗口错误参数!');
			window.close();
	}
	
	//提交数据
	if (fpanel.form.isValid()) {
		if (isRegionValid(fpanel.findById('region'))) {
			if (isRegionValid(fpanel.findById('area'))) {
				if (isContentValid(journey)) {
					this.disabled = true;
					
					fpanel.getForm().submit({
						url: urlPost,
						method: 'post',
						params: paramsPost,
						waitTitle: '请稍候',
						waitMsg: '行程正提交，请等待......',
						failure: function(fpanel, action){
							Ext.MessageBox.alert('保存失败', '行程保存未成功！');
							this.disabled = false;
						},
						success: function(fpanel, action){
							var returnJosn = action.result;
							if (returnJosn.success === true) {
								Ext.MessageBox.alert('保存成功', '行程保存成功');
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
										limit: pageSize,
										node: node
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
				Ext.Msg.alert('信息', '请必须选择一个途径城市!');
			}
		}
		else {
			Ext.Msg.alert('信息', '请必须选择一个行程类别!');
		}
	}
	else {
		Ext.Msg.alert('信息', '请填写完成再提交!');
	}
}

function isContentValid(journey){
	if (journey.findByType('textfield').length == 0) {
		return false;
	}
	for (var i = 0; i < journey.findByType('textfield').length; i++) {
		if (journey.findByType('textfield')[i].getValue() == "") {
			return false;
		}
	}
	for (var i = 0; i < journey.findByType('textarea').length; i++) {
		if (journey.findByType('textarea')[i].getValue() == "") {
			return false;
		}
	}
	return true;
}

function isAreaValid(areaPanel){
	if (areaPanel.findByType('checkbox').length == 0) {
		return false;
	}
	for (var i = 0; i < areaPanel.findByType('checkbox').length; i++) {
		if (areaPanel.findByType('checkbox')[i].checked == true) {
			return true;
		}
		if (i == areaPanel.findByType('checkbox').length - 1) {
			return false;
		}
	}
}

function isRegionValid(regionPanel){
	if (regionPanel.findByType('checkbox').length == 0) {
		return false;
	}
	for (var i = 0; i < regionPanel.findByType('checkbox').length; i++) {
		if (regionPanel.findByType('checkbox')[i].checked == true) {
			return true;
		}
		if (i == regionPanel.findByType('checkbox').length - 1) {
			return false;
		}
	}
}


/**
 * 添加修改时初始化城市面板取值函数
 * @param {Object} fpanel
 * @param {Object} areaPanel
 */
function areaRegionAjax(areaPanel, regionPanel){
	Ext.Ajax.request({
		url: 'getProvinceandregion.shtml',
		method: 'POST',
		params: {
			flag: flag,
			regionId: regionId
		},
		success: function(result, request){
			initAreaandRegion(result, areaPanel, regionPanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载类别失败！');
			window.close();
		}
	});
}


/**
 * 更新修改时初始化城市面板取值函数
 * @param {Object} fpanel
 * @param {Object} areaPanel
 */
function areaRegionUpdateAjax(idName,areaPanel, regionPanel,lineId){
	Ext.Ajax.request({
		url: 'getSingleLine.shtml',
		method: 'POST',
		params: {
			flag: flag,
			regionId: regionId,
			lineId:lineId
		},
		success: function(result, request){
			initUpdateAreaandRegion(result, idName,areaPanel, regionPanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载行程失败！');
			window.close();
		}
	});
}

function initAreaandRegion(result, areaPanel, regionPanel){
	var areaRgionJosn = doJSON(result.responseText);
	if (areaRgionJosn.success == true) {
		initArea(areaRgionJosn.areaList, areaPanel);
		initRegion(areaRgionJosn.regionList, regionPanel);
	}
	else {
		Ext.MessageBox.alert('加载失败', areaRgionJosn.msg);
	}
}

function initUpdateAreaandRegion(result, idName,areaPanel,regionPanel){
	var resultJosn = doJSON(result.responseText);
	if (resultJosn.success == true) {
		initupdateItem(resultJosn.line, idName);
	initUpdateArea(resultJosn.areaList,resultJosn.line.areas,areaPanel);
	initUpdateRegion(resultJosn.regionList,resultJosn.line.regions,regionPanel);
	}
	else {
		Ext.MessageBox.alert('加载失败', resultJosn.msg);
	}
}

function initupdateItem(line, idName){
	fpanel.findById(idName[0]).setValue(line.title);
	fpanel.findById(idName[1]).setValue(line.subTitle);
	fpanel.findById(idName[2]).setValue(line.feeClude);
	fpanel.findById(idName[3]).setValue(line.feeUnclude);
	fpanel.findById(idName[4]).setValue(line.remark);
	fpanel.findById('theme').setValue(line.theme);
	fpanel.findById('featurePanel').show();
	fpanel.findById(idName[6]).setValue(line.feature);
	fpanel.findById('customerActPanel').show();
	fpanel.findById(idName[7]).setValue(line.purchase);
	fpanel.findById(idName[8]).setValue(line.selfBuy);
	fpanel.findById('safePanel').show();
	fpanel.findById(idName[9]).setValue(line.safe);
	lineId = line.lineId;
	var JourneyList = new Array();
	JourneyList = line.content.split("###");
	if(JourneyList.length>0){
		var Journey = new Array();
		for (var i = 1; i <= JourneyList.length; i++) {
		Journey = JourneyList[i - 1].split("@@@");
		id = i;
		addcontext();
		contentPanel.findById('contentTitle' + i).setValue(Journey[0]);
		contentPanel.findById('meal' + i).setValue(Journey[1]);
		contentPanel.findById('room' + i).setValue(Journey[2]);
		contentPanel.findById('contentText' + i).setValue(Journey[3]);
	}
	}
}

/**
 * 初始化添加窗口城市面板
 * @param {Object} areaList
 * @param {Object} fpanel
 */ 
function initArea(areaList, areaPanel){
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
		if (j < childcolumncout) {
			childareaList.push(childarea);
			if (j == (childcolumncout - 1)) {
				childrenAreaComList = createChildAreaList(childareaList);
				areaPanel.add(childrenAreaComList);
				childareaList.length = 0;
			}
		}
		else {
			childareaList.push(childarea);
			if (j == (areaList.length - 1)) {
				childrenAreaComList = createChildAreaList(childareaList);
				areaPanel.add(childrenAreaComList);
				childareaList.length = 0;
			}
		}
	}
}


/**
 * 初始化修改窗口城市面板
 * @param {Object} areaList
 * @param {Object} fpanel
 */ 
function initUpdateArea(areaList,userAreaList, areaPanel){
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
			if ((childarea.id === userAreaList[i].parent) || ((childarea.id==='402880e51a512576011a512a33590002')&&(userAreaList[i].parent === 'ba5390b81eb07211011eb3e7c356002d'))) {
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
				areaPanel.add(childrenAreaComList);
				childareaList.length = 0;
			}
		}
		else {
			childareaList.push(childarea);
			if (j == (areaList.length - 1)) {
				childrenAreaComList = createChildAreaList(childareaList);
				areaPanel.add(childrenAreaComList);
				childareaList.length = 0;
			}
		}
	}
	
	areaPanel.doLayout();
}


function createChildArea(area){
	var areaTemp = new Ext.Panel({
		title: area.name,
		id: area.areaId,
		stateId: '-1',
		titleCollapse:true,
		collapsible: true,
		collapsed: true,
		bodyStyle: 'margin-bottom:6px',
		listeners: {
			'expand': {
				fn: expandCity,
				scope: this
			}
		},
		layout: 'column'
	})
	return areaTemp;
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


function expandCity(item){
	if(item.stateId!==0)
	{
		cityAjax(item);
		item.stateId=0;
	}	
}

/**
 * 初始化添加窗口的大行政区
 * @param {Object} cityPanel
 */
function cityAjax(cityPanel){
	var areaId = cityPanel.id;
	Ext.Ajax.request({
		url: 'getCityByProvince.shtml',
		method: 'POST',
		params: {
			areaId: areaId
		},
		success: function(result, request){
			initCity(result, cityPanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载行程失败！');
			window.close();
		}
	});
}


/**
 * 初始化更新窗口的大行政区
 * @param {Object} cityPanel
 */
function cityUpdateAjax(cityPanel,userAreaList){
	var areaId = cityPanel.id;
	Ext.Ajax.request({
		url: 'getCityByProvince.shtml',
		method: 'POST',
		params: {
			areaId: areaId
		},
		success: function(result, request){
			initUpdateCity(result, cityPanel,userAreaList);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载行程失败！');
			window.close();
		}
	});
}

/**
 * 创建并加载城市数据
 * @param {Object} result
 * @param {Object} cityPanel
 */
function initCity(result, cityPanel){
	var cityJosn = doJSON(result.responseText);
	
	if (cityJosn.success == true) {
	
	
	var cityList = cityJosn.cityList;
	
	var childrenCityComList;
	var childcolumncout;
	var childcity;
	var childcityList = new Array();
	
	childcolumncout = parseInt(cityList.length/2);
	if (cityList.length % 2 == 0) {
		childcolumncout = childcolumncout;
	}
	else {
		childcolumncout = childcolumncout + 1;
	}
	
	
	for (var j = 0; j < cityList.length; j++) {
	
		childcity = createChildCity(cityList[j]);
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
	}
	else {
		Ext.MessageBox.alert('加载失败', cityJosn.msg);
	}
}


/**
 * 创建并加载城市数据
 * @param {Object} result
 * @param {Object} cityPanel
 */
function initUpdateCity(result, cityPanel, userAreaList){
	var cityJosn = doJSON(result.responseText);
	
	if (cityJosn.success == true) {
	
		var cityList = cityJosn.cityList;
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
	}
	else {
		Ext.MessageBox.alert('加载失败', cityJosn.msg);
	}
}


function createChildCity(city){
	var cityTemp = new Ext.form.Checkbox({
		boxLabel: city.name,
		name: 'recityList',
		inputValue: city.areaId + "@@@" + city.parent,
		checked: false,
		hideLabel: true
	})
	return cityTemp;
}

function createUpdateChildCity(city,userAreaList){
	if(userAreaList.length>0)
	{
		for(var i=0; i<userAreaList.length;i++)
		{
			if(userAreaList[i].areaId==city.areaId)
			{
				var cityTemp = new Ext.form.Checkbox({
        			boxLabel:city.name,
        			name:'recityList', 
        			inputValue: city.areaId + "@@@" + city.parent,
        			checked: true,
        			hideLabel:true
				});
				return cityTemp;
			}
		}
	}
	var cityTemp = new Ext.form.Checkbox({
		boxLabel: city.name,
		name: 'recityList',
		inputValue: city.areaId + "@@@" + city.parent,
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

/**
 * 初始化添加窗口大类别面板
 * @param {Object} regionList
 * @param {Object} regionPanel
 */ 
function initRegion(regionList, regionPanel){
	var childrenRegionComList;
	var childcolumncout;
	var childregion;
	var childregionList = new Array();
	
	childcolumncout = parseInt(regionList.length / 2);
	if (regionList.length % 2 == 0) {
		childcolumncout = childcolumncout;
	}
	else {
		childcolumncout = childcolumncout + 1;
	}
	
	
	for (var j = 0; j < regionList.length; j++) {
	
		childregion = createParentRegion(regionList[j]);
		if (j < childcolumncout) {
			childregionList.push(childregion);
			if (j == (childcolumncout - 1)) {
				childrenRegionComList = createChildRegionList(childregionList);
				regionPanel.add(childrenRegionComList);
				childregionList.length = 0;
			}
		}
		else {
			childregionList.push(childregion);
			if (j == (regionList.length - 1)) {
				childrenRegionComList = createChildRegionList(childregionList);
				regionPanel.add(childrenRegionComList);
				childregionList.length = 0;
			}
		}
	}
}

/**
 * 初始化修改窗口大类别面板
 * @param {Object} regionList
 * @param {Object} regionPanel
 */ 
function initUpdateRegion(regionList,userRegionList, regionPanel){
	var childrenRegionComList;
	var childcolumncout;
	var childregion;
	var childregionList = new Array();
	
	childcolumncout = parseInt(regionList.length / 2);
	if (regionList.length % 2 == 0) {
		childcolumncout = childcolumncout;
	}
	else {
		childcolumncout = childcolumncout + 1;
	}
	
	
	for (var j = 0; j < regionList.length; j++) {
	
		childregion = createParentRegion(regionList[j]);
		
		for (i=0 ; i<userRegionList.length; i++)
		{
			if(childregion.id==userRegionList[i].parent)
			{
				childregion.collapsed=false;
				regionChildUpdateAjax(childregion,userRegionList);
				childregion.stateId=0;
				break;
			}
		}
		
		if (j < childcolumncout) {
			childregionList.push(childregion);
			if (j == (childcolumncout - 1)) {
				childrenRegionComList = createChildRegionList(childregionList);
				regionPanel.add(childrenRegionComList);
				childregionList.length = 0;
			}
		}
		else {
			childregionList.push(childregion);
			if (j == (regionList.length - 1)) {
				childrenRegionComList = createChildRegionList(childregionList);
				regionPanel.add(childrenRegionComList);
				childregionList.length = 0;
			}
		}
	}
	
	regionPanel.doLayout();
}


function createParentRegion(region){
	var regionTemp = new Ext.Panel({
		title: region.name,
		id: region.regionId,
		stateId: '-1',
		titleCollapse:true,
		collapsible: true,
		collapsed: true,
		bodyStyle: 'margin-bottom:6px',
		listeners: {
			'expand': {
				fn: expandRegion,
				scope: this
			}
		},
		layout: 'column'
	})
	return regionTemp;
}

function createChildRegionList(childregionList){
	var childregionListtemp = {
		bodyStyle: 'padding:3px',
		columnWidth: .49,
		layout: 'form',
		border: false,
		items: childregionList
	};
	return childregionListtemp;
}

function expandRegion(item){
	if (item.stateId !== 0) {
		regionChildAjax(item);
		item.stateId = 0;
	}
}

/**
 * 初始化添加窗口子类别
 * @param {Object} regionChildPanel
 */
function regionChildAjax(regionChildPanel){
	var regionId = regionChildPanel.id;
	Ext.Ajax.request({
		url: 'getChildrenRegion.shtml',
		method: 'POST',
		params: {
			regionId: regionId
		},
		success: function(result, request){
			initRegionChild(result, regionChildPanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载行程失败！');
			window.close();
		}
	});
}


/**
 * 初始化修改窗口子类别
 * @param {Object} regionChildPanel
 */
function regionChildUpdateAjax(regionChildPanel, userRegionList){
	var regionId = regionChildPanel.id;
	Ext.Ajax.request({
		url: 'getChildrenRegion.shtml',
		method: 'POST',
		params: {
			regionId: regionId
		},
		success: function(result, request){
			initRegionUpdateChild(result, regionChildPanel, userRegionList);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载行程失败！');
			window.close();
		}
	});
}

/**
 * 创建并加载添加窗口子类别数据
 * @param {Object} result
 * @param {Object} regionChildPanel
 */
function initRegionChild(result, regionChildPanel, userRegionList){
	var regionJosn = doJSON(result.responseText);
	if (regionJosn.success == true) {
	var regionList = regionJosn.regionList;
	
	var childrenRegionComList;
	var childcolumncout;
	var childregion;
	var childregionList = new Array();
	
	childcolumncout = parseInt(regionList.length / 2);
	if (regionList.length % 2 == 0) {
		childcolumncout = childcolumncout;
	}
	else {
		childcolumncout = childcolumncout + 1;
	}
	
	
	for (var j = 0; j < regionList.length; j++) {
	
		childregion = createChildRegion(regionList[j]);
		if (j < childcolumncout) {
			childregionList.push(childregion);
			if (j == (childcolumncout - 1)) {
				childrenRegionComList = createChildRegionList(childregionList);
				regionChildPanel.add(childrenRegionComList);
				childregionList.length = 0;
			}
		}
		else {
			childregionList.push(childregion);
			if (j == (regionList.length - 1)) {
				childrenRegionComList = createChildRegionList(childregionList);
				regionChildPanel.add(childrenRegionComList);
				childregionList.length = 0;
			}
		}
	}
	regionChildPanel.doLayout();
	}
	else {
		Ext.MessageBox.alert('加载失败', regionJosn.msg);
	}
}

/**
 * 创建并加载更新窗口子类别数据
 * @param {Object} result
 * @param {Object} regionChildPanel
 */
function initRegionUpdateChild(result, regionChildPanel, userRegionList){
	var regionJosn = doJSON(result.responseText);
	if (regionJosn.success == true) {
	var regionList = regionJosn.regionList;
	
	var childrenRegionComList;
	var childcolumncout;
	var childregion;
	var childregionList = new Array();
	
	childcolumncout = parseInt(regionList.length / 2);
	if (regionList.length % 2 == 0) {
		childcolumncout = childcolumncout;
	}
	else {
		childcolumncout = childcolumncout + 1;
	}
	
	
	for (var j = 0; j < regionList.length; j++) {
	
		childregion = createChildUpdateRegion(regionList[j],userRegionList);
		if (j < childcolumncout) {
			childregionList.push(childregion);
			if (j == (childcolumncout - 1)) {
				childrenRegionComList = createChildRegionList(childregionList);
				regionChildPanel.add(childrenRegionComList);
				childregionList.length = 0;
			}
		}
		else {
			childregionList.push(childregion);
			if (j == (regionList.length - 1)) {
				childrenRegionComList = createChildRegionList(childregionList);
				regionChildPanel.add(childrenRegionComList);
				childregionList.length = 0;
			}
		}
	}
	regionChildPanel.doLayout();
	}
	else {
		Ext.MessageBox.alert('加载失败', regionJosn.msg);
	}
}

function createChildRegion(region){
	var regionTemp = new Ext.form.Checkbox({
		boxLabel: region.name,
		name: 'reregionList',
		inputValue: region.regionId + "@@@" + region.parent,
		checked: false,
		hideLabel: true
	})
	return regionTemp;
}

function createChildUpdateRegion(region,userRegionList){
	if(userRegionList.length>0)
	{
		for(var i=0; i<userRegionList.length;i++)
		{
			if(userRegionList[i].regionId==region.regionId)
			{
				var regionTemp = new Ext.form.Checkbox({
        			boxLabel:region.name,
        			name:'reregionList', 
        			inputValue: region.regionId + "@@@" + region.parent,
        			checked: true,
        			hideLabel:true
				});
				return regionTemp;
			}
		}
	}
	var regionTemp = new Ext.form.Checkbox({
		boxLabel: region.name,
		name: 'reregionList',
		inputValue: region.regionId + "@@@" + region.parent,
		checked: false,
		hideLabel: true
	})
	return regionTemp;
}

function createChildRegionList(childregionList){
	var childregionListtemp = {
		bodyStyle: 'padding:3px',
		columnWidth: .49,
		layout: 'form',
		border: false,
		items: childregionList
	};
	return childregionListtemp;
}

/**
 * 添加行程函数
 */
function addcontext(){

	contentPanel.add({
		xtype: 'fieldset',
		title: '第' + id + '天行程',
		id: id + 'day',
		defaults: {
			width:230,
			anchor: '95%'
		},
		autoHeight: true,
		collapsed: false, //已经折叠否
		items: [{
			xtype: 'textfield',
			fieldLabel: '行程标题',
			name: 'contentTitle' + id,
			id: 'contentTitle' + id,
			allowBlank: false
		}, {
			xtype: 'textfield',
			fieldLabel: '餐饮',
			emptyText: '没有请填写：不含',
			name: 'meal' + id,
			id: 'meal' + id,
			allowBlank: false
		}, {
			xtype: 'textfield',
			fieldLabel: '住宿',
			emptyText: '没有请填写：不含',
			name: 'room' + id,
			id: 'room' + id,
			allowBlank: false
		}, {
			xtype: 'textarea',
			fieldLabel: '行程内容',
			grow: true,
//			height:200,
			growMin: 130,
			growMax: 200,
			name: 'contentText' + id,
			id: 'contentText' + id,
			allowBlank: false
		}]
	
	});
	id++;
	contentPanel.show();
	contentPanel.doLayout();
}
	

/**
 * 删除行程函数
 * @param {Object} contentPanel
 */
function deletecontext(){
	if (id > 1) {
		var tempfieldset = contentPanel.findById(--id + 'day');
		contentPanel.remove(tempfieldset);
		tempfieldset.destroy();
	}
}
	
/*===========================添加函数块=============================*/
function addItem(idName){
	addWin = Ext.getCmp('addLW');
	
	id = 1;//window自带的close关闭用
	if (!addWin) {
		comCreate(idName,"add",'');
		addWin = new Ext.Window({
			title: '添加行程',
			id: 'addW',
			closable: true,
			width: 580,
			height: 400,
			modal: true,
			layout: 'fit',
			items: [fpanel]
		});
	}
	
	updWin = '';
	addWin.show();
}
/*===========================添加完成=============================*/

/*===========================修改函数块=============================*/
function updateItem(idName,start){
	id = 1;
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要修改的行程！');
		
	}
	else {
		if (cbsm.getCount() == 1) {
			updWin = Ext.getCmp('updW');
			comCreate(idName,selectRecord.get('lineId'),start);
			if (!updWin) {
				updWin = new Ext.Window({
					title: '修改行程',
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
			addWin = '';
			updWin.show();
			updWin.doLayout();
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条行程！');
		}
	}
}


/*===========================修改完成=============================*/

/*===========================删除函数块=============================*/
function delItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('行程删除操作', '未选择，请选择要删除的行程！');
	}
	else {
		Ext.MessageBox.confirm('确认删除', '删除行程将同时删除与该行程相关的团队，你确认删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsondata = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('lineId');
					if (i === 0) {
						jsondata = jsondata + ss;
					}
					else {
						jsondata = jsondata + "," + ss;
					}
					objectStore.remove(m[i]);
				}				
			delAjax(start,jsondata);				
			}
		});
	}
}

function delAjax(start,jsondata){
	Ext.Ajax.request({
		url: 'delLine.shtml',
		method: 'POST',
		params: {
			delData: jsondata
		},
		success: function(result, request){			
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('行程删除操作', '删除行程成功！');
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
			Ext.MessageBox.alert('行程删除操作', '删除行程失败！');
			objectStore.load({
					params: {
						start: start,
						limit: pageSize,
						node: node
					}
				});
		}
	});
}

function copyItem(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('行程复制操作', '未选择，请选择要复制的行程！');
	}
	else {
		Ext.MessageBox.confirm('确认复制', '你确认复制选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsondata = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('lineId');
					if (i === 0) {
						jsondata = jsondata + ss;
					}
					else {
						jsondata = jsondata + "," + ss;
					}
				}
				copyAjax(start,jsondata);
			}
		});
	}
}

function copyAjax(start,jsondata){
	Ext.Ajax.request({
		url: 'copyLineList.shtml',
		method: 'POST',
		params: {
			copyData: jsondata
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('行程复制操作', '复制行程成功！');
			objectStore.load({
					params: {
						start: start,
						limit: pageSize,
						node: node
					}
				});
			}
			else
			{
				Ext.MessageBox.alert('行程复制操作', returnJosn.msg);
			}
		},
		failure: function(result, request){
			Ext.MessageBox.alert('行程复制操作', '复制行程失败！');
		}
	});
}

function allRefresh(store){
	store.load({
		params: {
			start: 0,
			node: node,
			limit:pageSize
		}
	});
}

function search(searchCheckbox,searchText,store,start){
	var searchType=searchCheckbox.getValue();
	var query = searchText.getValue();
	
	if(searchType){
		searchType=1;
	}else{
		searchType=0;
	}
	
	store.load({params:{
		start: start,
		limit: pageSize,
		node:node,
		searchType:searchType,
		title:query
	}});
}