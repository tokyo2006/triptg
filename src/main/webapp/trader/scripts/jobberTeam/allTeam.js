
Ext.onReady(function(){
	//get All unit Name for comboBox.
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init(); 	
	
	var cbsm;
	var objectGrid;
	var objectStore;
	
	new Ext.Viewport({
					layout: 'border',
					items: [initGrid(pageSize,'')]
				});
	
});

var pageSize = 60;

//初始化首页表格
function initGrid(pageSize,list){
	var searchText=new Ext.form.TextField({
		id: 'searchT',
		width: 110,
		emptyText: '输入开班名称'
	});
	
	var minDate = new Date();
	
	var startDateText=new Ext.form.DateField({
		width: 85,
		id:'startDateStr',
		name:'startDateStr',
		vtype: 'daterange',
		minValue:minDate,
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
	
	var searchBoxStore = new Ext.data.SimpleStore({
		fields:['displayValue','returnValue'],		
		data:[['无条件',0],['上架',1],['下架',2],['批发商特价',3],['批发商新品',4],['批发商推荐',5],['门户特价',6],['门户新品',7],['门户推荐',8]]
	});
	
	var searchSelect = new Ext.form.ComboBox({
			store: searchBoxStore,
			id:'searchSelect',
			name:'simSearch',
			hiddenName: 'simSearch',
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
	
	var searchBoxStore2 = new Ext.data.SimpleStore({
		fields:['returnValue','displayValue'],		
		proxy: new Ext.data.HttpProxy({
			url: 'getJobberInf.shtml',
			method: 'POST'
		})
	});
	
	var searchSelect2 = new Ext.form.ComboBox({
			store: searchBoxStore2,
			id:'searchSelect2',
			name:'userId',
			hiddenName: 'userId',
			valueField:'returnValue',
	        displayField:'displayValue',
	        typeAhead: true,
	        mode: 'remote',
			width:120,
			bodyStyle: 'padding:3px,margin-bottom:6px',
			triggerAction: 'all',
	        emptyText:'请选择批发商',
			loadingText:'正在加载请等待....',
			forceSelection:true,   
			selectOnFocus:true
		});
	
	var searchPagingToolbar = Ext.extend(Ext.PagingToolbar,{
		paramNames: {
			start: 'start',
			limit: 'limit',
			beginDate: 'beginDate',
			endDate: 'endDate',
			simSearch: 'simSearch',
			userId: 'userId',
			teamName: 'teamName',
			isJSpe: 'isJSpe',
			isJNew: 'isJNew',
			isJCom: 'isJCom',
			isSpe: 'isSpe',
			isNew: 'isNew',
			isCom: 'isCom',
			isNet: 'isNet'
		},
		
		doLoad: function(start,teamName, beginDate, endDate, simSearch, userId, isJSpe, isJNew, isJCom, isSpe, isNew, isCom, isNet){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.beginDate] = beginDate;
			o[pn.endDate] = endDate;			
			o[pn.simSearch] = simSearch;
			o[pn.userId] = userId;
			o[pn.teamName] = teamName;
			o[pn.isJSpe] = isJSpe;
			o[pn.isJNew] = isJNew;
			o[pn.isJCom] = isJCom;
			o[pn.isSpe] = isSpe;
			o[pn.isNew] = isNew;
			o[pn.isCom] = isCom;
			o[pn.isNet] = isNet;
			o[pn.limit] = this.pageSize;
			if (this.fireEvent('beforechange', this, o) !== false) {
				this.store.load({
					params: o
				});
			}
		},
		
		onClick: function(which){
			var beginDate, endDate;
			var startDateText = Ext.getCmp('startDateStr');
			var endDateText = Ext.getCmp('endDateStr');
			if (startDateText.getValue() === '') {
				beginDate = '';
			}
			else {
				beginDate = (new Date(Date.parse(startDateText.getValue()))).dateFormat("Y-m-d");
			}			
			if (endDateText.getValue() === '') {
				endDate = '';
			}
			else {
				endDate = (new Date(Date.parse(endDateText.getValue()))).dateFormat("Y-m-d");
			}
			
			var simSearch = Ext.getCmp('searchSelect').getValue();
			var userId = Ext.getCmp('searchSelect2').getValue();
			var teamName = Ext.getCmp('searchT').getValue();
			var comFPanel = Ext.getCmp('comFPanel');
			if (comFPanel) {
				var isJSpe, isJCom, isJNew, isSpe, isCom, isNew;
				if (comFPanel.findById('isJSpe').getValue()) {
					isJSpe = '1';
				}
				if (comFPanel.findById('isJNew').getValue()) {
					isJNew = '1';
				}
				if (comFPanel.findById('isJCom').getValue()) {
					isJCom = '1';
				}
				if (comFPanel.findById('isSpe').getValue()) {
					isSpe = '1';
				}
				if (comFPanel.findById('isNew').getValue()) {
					isNew = '1';
				}
				if (comFPanel.findById('isCom').getValue()) {
					isCom = '1';
				}
				var isNet = '';
				if (comFPanel.findById('radio1').getValue()) {
					isNet = 1;
				}
				else 
					if (comFPanel.findById('radio2').getValue()) {
						isNet = 0;
					}
			}
			
			var store = this.store;
			switch (which) {
				case "first":
					this.doLoad(0, teamName, beginDate, endDate, simSearch, userId, isJSpe, isJNew, isJCom, isSpe, isNew, isCom, isNet);
					break;
				case "prev":
					this.doLoad(Math.max(0, this.cursor - this.pageSize), teamName, beginDate, endDate, simSearch, userId, isJSpe, isJNew, isJCom, isSpe, isNew, isCom, isNet);
					break;
				case "next":
					this.doLoad((this.cursor + this.pageSize), teamName, beginDate, endDate, simSearch, userId, isJSpe, isJNew, isJCom, isSpe, isNew, isCom, isNet);
					break;
				case "last":
					var total = store.getTotalCount();
					var extra = total % this.pageSize;
					var lastStart = extra ? (total - extra) : total - this.pageSize;
					this.doLoad(lastStart, teamName, beginDate, endDate, simSearch, userId, isJSpe, isJNew, isJCom, isSpe, isNew, isCom, isNet);
					break;
				case "refresh":
					this.doLoad(this.cursor,'', '', '', '', '', '', '', '', '', '', '', '');
					break;
			}
		}
	});
	
   var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'teamId',
        fields: [
           	{name: 'teamId', type: 'string',mapping:'teamId'},
            {name: 'lineId', type: 'string',mapping:'line.lineId'},
            {name: 'teamName', type: 'string',mapping:'teamName'},
			{name: 'theme',mapping:'theme'},
			{name: 'picUrl',mapping:'picUrl'},
			{name: 'newproduct',mapping:'newproduct'},
			{name: 'recommend',mapping:'recommend'},
			{name: 'speical',mapping:'speical'},
			{name: 'flag', type: 'int',mapping:'flag'},
			{name: 'teamNum', type: 'string',mapping:'teamNum'},
            {name: 'hotelStar', type: 'string',mapping:'hotelStar'},
//          {name: 'startDateStr', type: 'string',mapping:'startDateStr'},
//			{name: 'endDateStr', type: 'string',mapping:'endDateStr'},
			{name: 'hotelPrice', type: 'int',mapping:'hotelPrice'},
			{name: 'ticketType', type: 'int',mapping:'ticketType'},
            {name: 'teamday', type: 'int',mapping:'teamday'},
			{name: 'teamType', type: 'int',mapping:'teamType'},
			{name: 'docUrl',mapping:'docUrl'},
			{name: 'isNet',mapping:'isNet'},
			{name: 'dateArea', type: 'string',mapping:'dateArea'},
            {name: 'manPriceList',mapping:'manPriceList'},
			{name: 'childPriceList',mapping:'childPriceList'},
			{name: 'total', type: 'int',mapping:'total'},			
			{name: 'book', type: 'int',mapping:'book'},
			{name: 'confirm', type: 'int',mapping:'confirm'},
			{name: 'sellNum', type: 'int',mapping:'sellNum'},
			{name: 'status', type: 'string',mapping:'status'},
			{name: 'busType', mapping:'busType'},	
			{name: 'themeColor', mapping:'themeColor'},	
			{name: 'youhui', mapping:'youhui'},
			{name: 'realDateStr', mapping:'realDateStr'},
			{name: 'pushDown', mapping:'pushDown'},		
			{name: 'jobberName', mapping:'jobberName'},				
			{name: 'areaName', type: 'string',mapping:'area.name'}
        ]
    });	 
	 

/*===============================中间部分 普通panel===========================================*/

	 objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllJobberTeam.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'teamName', direction: "ASC"},
            groupField:'teamName'
        });	
	 objectStore.load({params:{start:0,limit:pageSize}});
	
	 cbsm = new Ext.grid.CheckboxSelectionModel();
//	   cbsm.handleMouseDown = Ext.emptyFn; //只改变背景不选中

	 var rn = new Ext.grid.RowNumberer();	 
	 	 
     /*
      * 第二tab面板数据源，dataurl的请求带参数
      */
    objectGrid = new Ext.grid.GridPanel({
        ds: objectStore,
        columns: [
		     rn, //行号列 
		     cbsm, //CheckBox选择列, TMD有Ctrl和shift组合功能
            {id:'teamNum',header: '开班编号',hidden:true, hideable:true, sortable: true, 
			dataIndex: 'teamNum'},
			{header: '出发地', hidden:true, hideable:false,sortable: true,
			dataIndex: 'areaName'},
            {id:'teamName',header: '开班名称', hideable:false, sortable: true, width:260,renderer: function(v,p,record){
				var teamStr = '', picLink = '', picType, color = record.data.themeColor;
				
				var NP = new Array();
				NP = record.data.newproduct.split(',');
				var RC = new Array();
				RC = record.data.recommend.split(',');
				var SP = new Array();
				SP = record.data.speical.split(',');
				
				if (record.data.theme !== '') {
					picLink = '../../' + record.data.picUrl;
					picType = record.data.theme;
				}
				if (picLink !== '') {
					teamStr = '<img src=\"' + picLink + '\" height="12px" style="border:0;padding:0;margin:0;" />&nbsp;<font style="color:' + color + ';">[' + picType + ']</font>&nbsp;';
				}
				
				var prefix, postfix;
				if((parseInt(NP[0])+parseInt(RC[0])+parseInt(SP[0]))!==0){
					prefix='&nbsp;<font style="color:red;">[';
					
					if (parseInt(NP[0]) === 1) {
						prefix+='新品';
					}
					if(parseInt(RC[0]) === 1){
						if(parseInt(NP[0]) === 1){
							prefix+='+推荐';
						}
						else{
							prefix+='推荐';
						}
					}
					if(parseInt(SP[0]) === 1){
						if((parseInt(NP[0]) === 1)||(parseInt(NP[0]) === 1)){
							prefix+='+特价';
						}
						else{
							prefix+='特价';
						}
					}
					prefix+=']</font>&nbsp;';
					teamStr +=prefix;
				}
				
				teamStr += v;				
				
				if (record.data.youhui !== '') {
					picType = record.data.youhui;
					teamStr += '&nbsp;<font style="color:red;">[' + picType + ']</font>&nbsp;';
				}
				
				if((parseInt(NP[1])+parseInt(RC[1])+parseInt(SP[1]))!==0){
					postfix='&nbsp;<font style="color:blue;">[';
					
					if (parseInt(NP[1]) === 1) {
						postfix+='新品';
					}
					if(parseInt(RC[1]) === 1){
						if(parseInt(NP[1]) === 1){
							postfix+='+推荐';
						}
						else{
							postfix+='推荐';
						}
					}
					if(parseInt(SP[1]) === 1){
						if((parseInt(NP[1]) === 1)||(parseInt(NP[1]) === 1)){
							postfix+='+特价';
						}
						else{
							postfix+='特价';
						}
					}
					postfix+=']</font>&nbsp;';
					teamStr +=postfix;
				}
				
				p.attr = 'ext:qtitle=""';
				p.attr += ' ext:qtip="' + v + '"';
				return teamStr;
			},
			dataIndex: 'teamName'},
			{header: '住宿',hidden:true, hideable:false,sortable: true,renderer:function(v,p,record){
				return '<font style=\"color:#DBBB4D\">'+Ext.util.Format.hotelRenderer(v)+'</font>';
			},
			dataIndex: 'hotelStar'},
			{header: '开班日期',  hideable:true, sortable: true,width:100,
			dataIndex: 'dateArea'},
			{header: '类型', hidden:true, hideable:true,sortable: true,width:40,renderer:Ext.util.Format.teamtypeRenderer,
			dataIndex: 'teamType'},
			{header: '单房差',hidden:true, hideable:false, sortable: true,renderer:Ext.util.Format.moneyRenderer,
			dataIndex: 'hotelPrice'},
			{header: '交通',  hideable:true, sortable: true, renderer:function (v,p,record){
				if(parseInt(v)===3){
					return Ext.util.Format.busRenderer(record.data.busType);
				}
				else{
					return Ext.util.Format.ticketRenderer(v);
				}
			},width:60,
			dataIndex: 'ticketType'},
			{header: '天数', hideable:true, sortable: true,width:50,
			dataIndex: 'teamday'},
			{header: '价格', hideable:true, sortable: true, renderer: function(v,p,record){
				var price,mPrice,marketPrice,childPrice=record.data.childPriceList;
				p.attr =  'ext:qtitle="'  + "价格详情</br>" + '"';
				
				var priceStr='<table style=\'font-size:12px;\'><tr><td></td><td style=\'color:#CD522E;text-align:center;\'>门市价</td><td style=\'color:#9F9F9D;text-align:center;\'>返利</td></tr>';
//				priceStr+='<tr><td>成人:</td><td colspan=\'2\'></td></tr>';
				for (var i = 0; i < v.length; i++) {
					if (v[i].display) {
						marketPrice=v[i].marketPrice;
					}
					mPrice=v[i].marketPrice;
					price=v[i].price;
					priceStr+='<tr><td>'+v[i].desc+':</td><td style=\'color:#CD522E;padding:5px 0 5px 0;\'>￥'  + mPrice+'</td><td style=\'color:#9F9F9D;\'>￥'+(mPrice-price)+'</td></tr>';
				}
//				if(childPrice.length!==0){
//					priceStr+='<tr><td>儿童:</td><td colspan=\'2\'></td></tr>';
//				}
				for (var j = 0; j < childPrice.length; j++) {
					mPrice=childPrice[j].marketPrice;
					price=childPrice[j].price;
					priceStr+='<tr><td>'+childPrice[j].desc+':</td><td style=\'color:#CD522E;padding:5px 0 5px 0;\'>￥'  + mPrice+'</td><td style=\'color:#9F9F9D;\'>￥'+(mPrice-price)+'</td></tr>';
				}
				priceStr+='</table>';
				p.attr += ' ext:qtip="'+priceStr + '"';
				return Ext.util.Format.moneyRenderer(marketPrice);
				
			},width:80,
			dataIndex: 'manPriceList'},
			{header: '总数', hideable:true,sortable: true, width:50,
			dataIndex: 'total'},
			{header: '待定', hideable:true,  sortable: true,width:50,
			dataIndex: 'book'},
			{header: '确认',  hideable:true,  sortable: true,width:50,
			dataIndex: 'confirm'},
			{header: '剩余',  hideable:true,  sortable: true,width:50,render:function(v,p,record){
				return '<font color=\"red\">'+v+'</font>';
			},
			dataIndex: 'sellNum'},
			{header: '文档',  hideable:true, sortable: false,width:50,renderer: function(v, p, record){
				var htmlStr='';
				if(v!==''){
					htmlStr+= '<a href=\"../../' + v;
					htmlStr += '\" target="_blank"><img src=\"../../resources/images/share/yeoou/word.gif\"></img></a>';
				}
								
				return htmlStr;
			},
			dataIndex: 'docUrl'},
			{header: '状态',  hideable:true, sortable: true,renderer: function(v,p,record){
			if(Boolean(record.data.pushDown)===true&&v!=="已售完"){
				v='<font color=red>已截止</font>';
			}
            return ((v === "已售完" ) ? '<font color=red>' + v +' </font>' : v);  },
			dataIndex: 'status'},
			{header: '批发商',  hideable:true,  sortable: true,
			dataIndex: 'jobberName'},
			{header: '上架', hideable:true, sortable: false,width:40,renderer:function(v){
				if(parseInt(v)===1){
					return '上架';
				}
				else{
					return '<font color=red>下架</font>';
				}
			},
			dataIndex: 'isNet'},
			{header: '报名',  hideable:true, sortable: false,renderer: function(v){
            return ((Boolean(v) === false ) ?  '<a style="font-weight:blod;color:#478CB1;text-decoration:underline;" onclick="signUp()">'+'报名'+'</a>': '<font color=gray>' + '报名' +' </font>');  },
			dataIndex: 'pushDown'},
			{header: '报名日期',  hideable:true, sortable: true,
			dataIndex: 'realDateStr'}			
        ],
		sm: cbsm,
		selModel: new Ext.grid.RowSelectionModel({singleSelect:true}),//设置单行选中模式, 否则将无法显示数据   
		
		tbar:[{
				text: '刷新',
				tooltip: '刷新开班信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},'-',{
				text: '报名',
				tooltip: '填报一个开班',
				iconCls: 'add',
				handler: signUp
			},'-',{
				text: '订单查询',
				tooltip: '查询订单信息',
				iconCls: 'option',
				handler: queryOrder
			},'-',{
				text: '查看开班',
				tooltip: '查看完整开班信息',
				iconCls: 'printline',
				handler: viewItem
			},'-',{
			text: '开班操作',
			tooltip: '操作开班信息',
			iconCls: 'option',
			menu: new Ext.menu.Menu({
				id: 'menu1',
				items: [{
				text: '上架',
				tooltip: '上架开班信息',
//				iconCls: 'option',
				handler: function(){
					setUp(objectGrid.getBottomToolbar().cursor);
				}
			},{
				text: '下架',
				tooltip: '下架开班信息',
//				iconCls: 'option',
				handler: function(){
					setDown(objectGrid.getBottomToolbar().cursor);
				}
			},{
				text: '特价',
				tooltip: '设置开班信息为特价',
//				iconCls: 'option',
				handler: function(){
					setSpecial(objectGrid.getBottomToolbar().cursor);
				}
			},{
				text: '取消特价',
				tooltip: '取消开班特价信息',
//				iconCls: 'option',
				handler: function(){
					delSpecial(objectGrid.getBottomToolbar().cursor);
				}
			},{
				text: '新品',
				tooltip: '设置开班信息为新品',
//				iconCls: 'option',
				handler: function(){
					setNew(objectGrid.getBottomToolbar().cursor);
				}
			},{
				text: '取消新品',
				tooltip: '取消开班新品信息',
//				iconCls: 'option',
				handler: function(){
					delNew(objectGrid.getBottomToolbar().cursor);
				}
			},{
				text: '推荐',
				tooltip: '设置开班信息为推荐',
//				iconCls: 'option',
				handler: function(){
					setCommend(objectGrid.getBottomToolbar().cursor);
				}
			},{
				text: '取消推荐',
				tooltip: '取消开班推荐信息',
//				iconCls: 'option',
				handler: function(){
					delCommend(objectGrid.getBottomToolbar().cursor);
				}
			}]
			})
		},
					
			'->',
			searchText,startDateText,endDateText,searchSelect,searchSelect2,
			{
				text:'简单搜索',
				handler:function(){
					simpleSearch(searchText,startDateText,endDateText,searchSelect,searchSelect2,objectStore);
				}
			},'-',{
				text:'高级搜索',
				handler:function(){
					complicatedSearch(searchText,startDateText,endDateText,searchSelect2,objectStore);
				}
			}
		],

        view: new Ext.grid.GroupingView({
			forceFit:true,
            showGroupName: false,
            enableNoGroups:false, // REQUIRED!
            hideGroupedColumn: true,
			startCollapsed:true,
            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "条" : "条"]})'
        }),		
		
		bbar : new searchPagingToolbar({
			pageSize :pageSize,
			store : objectStore,
            displayInfo : true,
            displayMsg : '显示 {0}-{1}条 / 共 {2} 条',
            emptyMsg : "无数据。"
		}),
		
		listeners:{
			'rowdblclick':{
				fn:signUp,
				scope:this
			}
		},

        frame:true,
		loadMask: true,
		autoWidth:true,
		region:'center',
		id:'totalteamGrid',
		resizeTabs:true, // turn on tab resizing
		minTabWidth: 300,
		enableTabScroll:true,
		autoScroll: true,
        iconCls: 'icon-grid'
    });	
		
	return objectGrid;
}

function signUp(){
   var selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('开班报名', '请选择要报名的开班！');		
	}
	else {
		if (cbsm.getCount() === 1) {
			if(selectRecord.get('status')=='可预定'){
			    if(Boolean(selectRecord.get('pushDown'))===false){
			        var teamId=selectRecord.get('teamId');
					window.open('getBookTeam/'+teamId+'.html');
			    }
				else{
				    Ext.MessageBox.alert('提示', '已超过预售天数！');
				}
			}
			else{
				Ext.MessageBox.alert('提示', '已售完，请选择未满开班！');
			}
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条开班！');
		}
	}
}

function setUp(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('开班上架操作', '未选择，请选择要上架的开班！');
	}
	else {
		Ext.MessageBox.confirm('确认上架', '你确认设置上架选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('teamId');
					if (i === 0) {
						jsonData = jsonData + ss;
					}
					else {
						jsonData = jsonData + "," + ss;
					}
				}				
			operateAjax(start,jsonData,1);				
			}
		});
	}
}

function setDown(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('开班下架操作', '未选择，请选择要下架的开班！');
	}
	else {
		Ext.MessageBox.confirm('确认下架', '你确认设置下架选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('teamId');
					if (i === 0) {
						jsonData = jsonData + ss;
					}
					else {
						jsonData = jsonData + "," + ss;
					}
				}				
			operateAjax(start,jsonData,2);				
			}
		});
	}
}

function setSpecial(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('开班特价操作', '未选择，请选择要特价的开班！');
	}
	else {
		Ext.MessageBox.confirm('确认特价', '你确认设置特价选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('teamId');
					if (i === 0) {
						jsonData = jsonData + ss;
					}
					else {
						jsonData = jsonData + "," + ss;
					}
				}				
			operateAjax(start,jsonData,3);				
			}
		});
	}
}

function delSpecial(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('开班特价操作', '未选择，请选择要特价的开班！');
	}
	else {
		Ext.MessageBox.confirm('确认特价', '你确认取消特价选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('teamId');
					if (i === 0) {
						jsonData = jsonData + ss;
					}
					else {
						jsonData = jsonData + "," + ss;
					}
				}				
			operateAjax(start,jsonData,4);				
			}
		});
	}
}


function setNew(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('开班特价操作', '未选择，请选择要特价的开班！');
	}
	else {
		Ext.MessageBox.confirm('确认特价', '你确认设置新品选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('teamId');
					if (i === 0) {
						jsonData = jsonData + ss;
					}
					else {
						jsonData = jsonData + "," + ss;
					}
				}				
			operateAjax(start,jsonData,5);				
			}
		});
	}
}

function delNew(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('开班特价操作', '未选择，请选择要特价的开班！');
	}
	else {
		Ext.MessageBox.confirm('确认特价', '你确认取消新品选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('teamId');
					if (i === 0) {
						jsonData = jsonData + ss;
					}
					else {
						jsonData = jsonData + "," + ss;
					}
				}				
			operateAjax(start,jsonData,6);				
			}
		});
	}
}

function setCommend(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('开班推荐操作', '未选择，请选择要推荐的开班！');
	}
	else {
		Ext.MessageBox.confirm('确认推荐', '你确认设置推荐选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('teamId');
					if (i === 0) {
						jsonData = jsonData + ss;
					}
					else {
						jsonData = jsonData + "," + ss;
					}
				}				
			operateAjax(start,jsonData,7);				
			}
		});
	}
}

function delCommend(start){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('开班推荐操作', '未选择，请选择要推荐的开班！');
	}
	else {
		Ext.MessageBox.confirm('确认推荐', '你确认取消推荐选择的数据吗？', function(btn){
			if (btn == "yes") {
				var m = objectGrid.getSelections();
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('teamId');
					if (i === 0) {
						jsonData = jsonData + ss;
					}
					else {
						jsonData = jsonData + "," + ss;
					}
				}				
			operateAjax(start,jsonData,8);				
			}
		});
	}
}

function operateAjax(start,jsonData,type){
	Ext.Ajax.request({
		url: 'operateTeam.shtml',
		method: 'POST',
		params: {
			jsonData: jsonData,
			operateType:parseInt(type)
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success===true)
			{
				Ext.MessageBox.alert('开班批量操作', '批量修改开班信息成功！');
			}
			else
			{
				Ext.MessageBox.alert('开班批量操作', returnJosn.msg);
			}
			objectStore.load({
					params: {
						start: start,
						limit: pageSize
					}
				});
		},
		failure: function(result, request){
			Ext.MessageBox.alert('开班批量操作', doJSON(result.responseText).msg);
			objectStore.load({
					params: {
						start: start,
						limit: pageSize
					}
				});
		}
	});
}


function simpleSearch(searchText, startDateText, endDateText, searchSelect,searchSelect2, store){
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
	var teamName = searchText.getValue();
	var simpleSearchType = searchSelect.getValue();
	var userId = searchSelect2.getValue();
	query = teamName.trim();
	store.load({
		params: {
			beginDate: beginDate,
			endDate: endDate,
			simSearch: simpleSearchType,
			userId:userId,
			start: 0,
			limit: pageSize,
			teamName: teamName
		}
	});
}

function complicatedSearch(searchText,startDateText,endDateText,searchSelect2,objectStore){
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
	var teamName = searchText.getValue();
	var userId = searchSelect2.getValue();
	var simpSearch = '';
	teamName = teamName.trim();
	comSearchItem(beginDate,endDate,simSearch,userId,teamName);
}

function allRefresh(store){
	store.load({
		params: {
			start: 0,
			limit:pageSize
		}
	});
}

//function rowdblclick(grid){
//	var	selectRecord = grid.getSelectionModel().getSelected();
//	if (selectRecord) {
//		if (cbsm.getCount() == 1) {
//			teamId=selectRecord.get('teamId');
//			alert(teamId);
////			window.open('print.jsp?lineId='+lineId);
//		}
//		else {
//			Ext.MessageBox.alert('提示', '请选择一条开班打印！');
//		}
//	}
//}

function queryOrder(){
	var selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('开班订单查询', '请选择需要查询的开班！');		
	}
	else {
		if (cbsm.getCount() == 1) {
				var teamId=selectRecord.get('teamId');
				orderInfAjax(teamId);
//				initTeamOrder(0);
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条开班！');
		}
	}
}
function orderInfAjax(Id){
	Ext.Ajax.request({
		url: 'getOrdersByTeam.shtml',
		method: 'POST',
		params: {teamId:Id},
		success: function(result, request){
			initTeamOrder(result);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败！');
			window.close();
		}
	});
}

function initTeamOrder(result){
	var teamOrderList = doJSON(result.responseText);
	if (teamOrderList.success === true) {
		teamOrderW = Ext.getCmp('teamOW');
		var orderPanel = comOrderCreate(teamOrderList.statOrderList);
		
		if (!teamOrderW) {
			teamOrderW = new Ext.Window({
				title: '查看开班订单信息',
				id: 'teamOW',
				closable: true,
				width: 580,
				height: 400,
				modal: true,
				layout: 'fit',
				items: [orderPanel]
			});
		}
		teamOrderW.show();
	}
	else {
		Ext.MessageBox.alert('加载失败', teamOrderList.msg);
	}
}

function comOrderCreate(statOrderList){
	
	var teamOrderHtml= initTOHtml(statOrderList);
	
	orderPanel = new Ext.Panel({
		frame:true,
		autoScroll:true,
		items:[{
			html:teamOrderHtml
		}]
	});
	
	return orderPanel;
}

function initTOHtml(statOrderList){
	var k,p;
	k=statOrderList.length;
	var tempOrders;
	var t='<table  width="100%" border="0" cellpadding="0" cellspacing="0" align="center">';
	var tempHtml;
	
	tempHtml = '<tr><td colspan="4"><hr /></td></tr>';
	t += tempHtml;
	
	for (i = 0; i < k; i++) {	
		tempHtml = '<tr><td class="team_order_cls3" ><img src="../../resources/images/jobber/yeoou/collapsed.gif" id="';
		tempHtml +='img'+i+'" title="展开" style="cursor:pointer"  onclick="changeShow(\'';
		tempHtml +=i;
		tempHtml += '\')" />&nbsp;&nbsp;';
		tempHtml += statOrderList[i].bookDate;
		tempHtml += '(' + statOrderList[i].week + ')';
		tempHtml += '</td>';
		tempHtml += '<td class="team_order_cls1">总人数：' + statOrderList[i].total + '</td>';
		tempHtml += '<td class="team_order_cls1">有效人数：' + statOrderList[i].effectTotal + '</td>';
		tempHtml += '<td class="team_order_cls1">总金额：' + statOrderList[i].accountReceivable + '</td>';
		tempHtml += '</tr>';
		t += tempHtml;
		
		tempHtml='<tr><td colspan="4"><table id="tab';
		tempHtml +=i;
		tempHtml +='"  width="100%" border="0" style="display:none" cellpadding="0" cellspacing="0" align="center">';
		t += tempHtml;
		
		tempHtml = '<tr><td class="team_order_cls4" >订单号</td>';
		tempHtml += '<td class="team_order_cls2">下单人</td>';
		tempHtml += '<td class="team_order_cls2">人数(个)</td>';
		tempHtml += '<td class="team_order_cls2">金额(元)</td>';
		tempHtml += '</tr>';
		t += tempHtml;
		
		tempOrders = statOrderList[i].orderList;
		p = tempOrders.length;
		for (j = 0; j < p; j++) {
			tempHtml = '<tr><td class="team_order_cls3" ><a href="#" style="cursor:pointer" onClick="ShowOrder(\'';
			tempHtml +=tempOrders[j].orderId;
			tempHtml +='\',\'';
			tempHtml +=tempOrders[j].bakTeam.flag;
			tempHtml+='\') ">' + tempOrders[j].orderNo +'</a></td>';
			tempHtml += '<td class="team_order_cls1">' + tempOrders[j].shopper.shopName+'---'+tempOrders[j].shopper.name+ '</td>';
			tempHtml += '<td class="team_order_cls1">' + tempOrders[j].total;
			
			if(tempOrders[j].shopperStatus===0){
				tempHtml +='(未定)';
			}
			else{
				tempHtml +='(已定)';
			}
			
			tempHtml +='</td>';
			tempHtml += '<td class="team_order_cls1">';
			
			if(tempOrders[j].accountReceivable===0){
				tempHtml +='未审核';
			}
			else{
				tempHtml +='￥' + tempOrders[j].accountReceivable;
			}
			tempHtml += '</td>';
			
			tempHtml += '</tr>';
			t += tempHtml;
		}
		
		tempHtml = '</table></td></tr>';
		t += tempHtml;
  
  		tempHtml = '<tr><td colspan="4"><hr /></td></tr>';
		t += tempHtml;
	}
	
	t+='</table>';
	return t;
}

function changeShow(i){
	
	var img =document.getElementById('img'+i);
	var tab=document.getElementById('tab'+i);
	
	if(img.title==='展开'){		
		tab.style.display='';
		img.title='合并';
		img.src='../../resources/images/jobber/yeoou/expand.gif';
	}
	else{
		tab.style.display='none';
		img.title='展开';
		img.src='../../resources/images/jobber/yeoou/collapsed.gif';
	}	
}

function ShowOrder(orderId,flag){
		window.open('../order/getPrintOrder/' + orderId+'.html');
}


function viewItem(){
	var	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (selectRecord) {
		if (cbsm.getCount() == 1) {
			teamId=selectRecord.get('teamId');
//			if(selectRecord.get('flag')<4)
//			{
//					window.open('getPrintTeam.shtml?teamId='+teamId);
//				}
//				else{
//					window.open('getPrintFreeTeam.shtml?teamId='+teamId);
//				}
            window.open('../../team/teamContent/'+teamId);

		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条开班打印！');
		}
	}
	else{
		Ext.MessageBox.alert('提示', '请选择一条开班打印！');
	}
}

/*
function linePrint(){
	var	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (selectRecord) {
		if (cbsm.getCount() == 1) {
			lineId=selectRecord.get('lineId');
			teamId=selectRecord.get('teamId');
			if(selectRecord.get('flag')<4){
					window.open('getPrintLine.shtml?lineId='+lineId);
				}
				else{
					window.open('getPrintFreeLine.shtml?teamId='+teamId);
				}
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条开班打印！');
		}
	}
	else{
		Ext.MessageBox.alert('提示', '请选择一条开班打印！');
	}
}*/

function comSearchItem(beginDate,endDate,simSearch,userId,teamName){
	var comFPanel = new Ext.FormPanel({
		region: 'center',
		id:'comFPanel',
		bodyStyle: 'padding:5px',
		collapsible: true,
		items: [{
			xtype:'panel',
			id:'columnP1',
			layout:'column',
			border:false,
			height:25,
			bodyStyle:'padding:0 0 0 0; margin:0 0 0 0',
			items:[{
				xtype:'panel',
				height:24,
				border:false,
				columnWidth:.22,
				html:'<font style:\"font-size:24px;\">批发商条件:</font>'
			},{
				xtype:'panel',
				columnWidth:.26,
				border:false,
				items:[{
					xtype: 'checkbox',
					boxLabel: '批发商特价',
					id: 'isJSpe',
					hideLabel: true,
					labelSeparator: '',
					name: 'isJSpe',
					inputValue: '1'
				}]
			},{
				xtype:'panel',
				columnWidth:.26,
				border:false,
				items:[{
					xtype: 'checkbox',
					boxLabel: '批发商新品',
					id: 'isJNew',
					hideLabel: true,
					labelSeparator: '',
					name: 'isJNew',
					inputValue: '1'
				}]
			},{
				xtype:'panel',
				columnWidth:.26,
				border:false,
				items:[{
					xtype: 'checkbox',
					boxLabel: '批发商推荐',
					id: 'isJCom',
					hideLabel: true,
					labelSeparator: '',
					name: 'isJCom',
					inputValue: '1'
				}]
			}]
		},{
			xtype:'panel',
			id:'columnP2',
			layout:'column',
			border:false,
			height:25,
			bodyStyle:'padding:0 0 0 0; margin:0 0 0 0',
			items:[{
				xtype:'panel',
				height:24,
				border:false,
				columnWidth:.22,
				html:'<font style:\"font-size:24px;\">门户条件:</font>'
			},{
				xtype:'panel',
				columnWidth:.26,
				border:false,
				items:[{
					xtype: 'checkbox',
					boxLabel: '门户特价',
					id: 'isSpe',
					hideLabel: true,
					labelSeparator: '',
					name: 'isSpe',
					inputValue: '1'
				}]
			},{
				xtype:'panel',
				columnWidth:.26,
				border:false,
				items:[{
					xtype: 'checkbox',
					boxLabel: '门户新品',
					id: 'isNew',
					hideLabel: true,
					labelSeparator: '',
					name: 'isNew',
					inputValue: '1'
				}]
			},{
				xtype:'panel',
				columnWidth:.26,
				border:false,
				items:[{
					xtype: 'checkbox',
					boxLabel: '门户推荐',
					id: 'isCom',
					hideLabel: true,
					labelSeparator: '',
					name: 'isCom',
					inputValue: '1'
				}]
			}]
		},{
			xtype:'panel',
			id:'columnP3',
			layout:'column',
			border:false,
			height:25,
			bodyStyle:'padding:0 0 0 0; margin:0 0 0 0',
			items:[{
				xtype:'panel',
				height:24,
				border:false,
				columnWidth:.22,
				html:'<font style:\"font-size:24px;\">上、下架信息:</font>'
			},{
				xtype:'panel',
				columnWidth:.26,
				border:false,
				items:[{
					xtype:'radio',
					boxLabel: '上架',
					name: 'isNet',
					id: 'radio1',
					inputValue: 1
				}]
			},{
				xtype:'panel',
				columnWidth:.26,
				border:false,
				items:[{
					xtype:'radio',
					boxLabel: '下架',
					name: 'isNet',
					id: 'radio2',
					inputValue:0
				}]
			},{
				xtype:'panel',
				columnWidth:.26,
				border:false,
				items:[{
					xtype:'radio',
					boxLabel: '全显示',
					name: 'isNet',
					id: 'radio3',
					inputValue:2,
					checked: true
				}]
			}]
		}],
		
		buttons: [{
			text: '确定',
			handler: function(){
				searchOk(comFPanel,beginDate,endDate,simSearch,userId,teamName);
			}
		}, {
			text: '取消',
			handler: function(){
				searchCancel();
			}
		}]
	});
	
	var comWin = Ext.getCmp('comW');
	if(!comWin){
		comWin = new Ext.Window({
					title: '高级搜索窗口',
					id: 'comW',
					closable: true,
					width: 520,
					height: 170,
					plain: true,
					modal: true,
					layout: 'fit',
					items:[comFPanel]
				});
	}	
	comWin.show();
}

function searchOk(comFPanel,beginDate,endDate,simSearch,userId,teamName){
	var comWin = Ext.getCmp('comW');
	var isJSpe,isJCom,isJNew,isSpe,isCom,isNew;
	if(comFPanel.findById('isJSpe').getValue()){
		isJSpe='1';
	}
	if(comFPanel.findById('isJNew').getValue()){
		isJNew='1';
	}
	if(comFPanel.findById('isJCom').getValue()){
		isJCom='1';
	}
	if(comFPanel.findById('isSpe').getValue()){
		isSpe='1';
	}
	if(comFPanel.findById('isNew').getValue()){
		isNew='1';
	}
	if(comFPanel.findById('isCom').getValue()){
		isCom='1';
	}
	
	var isNet='';
	if(comFPanel.findById('radio1').getValue()){
		isNet='1';
	}
	else if(comFPanel.findById('radio2').getValue()){
		isNet='0';
	}
	
	objectStore.load({
		params: {
			beginDate: beginDate,
			endDate: endDate,
			simSearch: simSearch,
			userId: userId,
			teamName: teamName,
			isJSpe: isJSpe,
			isJNew: isJNew,
			isJCom: isJCom,
			isSpe: isSpe,
			isNew: isNew,
			isCom: isCom,
			isNet: isNet,
			start: 0,
			limit: pageSize
		}
	});	
	comWin.hide();
}

function searchCancel(){
	var comWin = Ext.getCmp('comW');
	
	if (comWin) {
		comWin.hide();
	}
}
