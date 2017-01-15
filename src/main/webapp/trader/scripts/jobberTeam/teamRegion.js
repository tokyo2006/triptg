Ext.onReady(function(){
	//get All unit Name for comboBox.
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	/*==============================全局变量t=============================================*/
	var id = 1,im=1,ic=1;
	var tabPanel,busfpanel,fpanel,linkfpanel;
	var hotelPanel,childPricePanel,manPricePanel;
	var objectGrid;
	var selectRecord;
	var cbsm;
	var objectStore;
	var pickStore,teamThemeStore,themeColorStore,youhuiBoxstore,busModeBoxstore;
	
	initView();
});

var pageSize = 60;
var tplWin;
var linkWin;
var teamId;

var lineRegionId;
var docRegionId;
var oldNode;
var oldDocNode;
var seatName;
var themeColor;

var weeksName=new Array('everyday','Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday');

var trafficPanel;
var planeStore,trainStore,busStore,shipStore;
var travelType;	
var lineBoxstore;
var areaBoxstore;
var docBoxstore;
var assembleList;

var areaBoxstore,saleBoxstore,hotelStarBoxstore;
var pickTypeStore=new Ext.data.SimpleStore({
		fields: ['returnValue'],
		data: [['含接送'], ['只接不送'], ['只送不接']]
	});

/*==============================中间新tabPanel中包含树的west=============================================*/
function initView(){
		
	/*
	 * 布局加载
	 */
	new Ext.Viewport({
		layout: 'border',
		items: initGrid(node)
	});
}

/**
 * 表格加载
 */
function initGrid(node){
	var idName = new Array('teamName','startDateStr', 'startTime', 'area', 'teamTypeCombox',
	 						 'orderConfirm','hotelStar',
							 'hotelPrice','teamday','urgentTel','planeTax','planeGo',
							'planeBack','lineSelectBox');

	MyPagingToolbar = Ext.extend(Ext.PagingToolbar, {
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
	
	var teamreader;
	
	teamreader = new Ext.data.JsonReader({
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
	
	objectStore = new Ext.data.GroupingStore({
            reader: teamreader,
			proxy: new Ext.data.HttpProxy({url: 'getAllJobberTeam.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'teamName', direction: "ASC"},
            groupField:'teamName'
        });
	objectStore.load({
		params: {
			start: 0,
			limit: pageSize,
			node: node
		}
	});
	
	cbsm = new Ext.grid.CheckboxSelectionModel();
	//		cbsm.handleMouseDown = Ext.emptyFn; //只改变背景不选中
	var rn = new Ext.grid.RowNumberer();
	
	var teamToolbar;
	if (parseInt(flag) === 1 || parseInt(flag) == 2) {
		teamToolbar = [{
			text: '刷新',
			tooltip: '刷新开班信息',
			iconCls: 'refresh',
			handler: function(){
				allRefresh(objectStore);
			}
		}, '-',{
			text: '开班编辑',
			tooltip: '开班编辑信息',
			iconCls: 'option',
			menu: new Ext.menu.Menu({
				id: 'menu1',
				items: [{
					text: '复制开班',
					tooltip: '模板开班',
					iconCls: 'option',
					handler: function(){
						templateTeam(idName,objectGrid.getBottomToolbar().cursor);
					}
				}]
			})
		}, '-',{
			text: '开班预订',
			tooltip: '开班预订信息',
			iconCls: 'option',
			menu: new Ext.menu.Menu({
				id: 'menu2',
				items: [{
					text: '下订单',
					tooltip: '填报一个线路',
					iconCls: 'add',
					handler: function(){
						signUp();
					}
				},{
					text: '订单查询',
					tooltip: '查询订单信息',
					iconCls: 'option',
					handler: function(){
						queryOrder();
					}
				}]
			})
		}, '-',{
			text: '打印',
			tooltip: '打印开班信息',
			iconCls: 'option',
			menu: new Ext.menu.Menu({
				id: 'menu3',
				items: [{
					text: '开班信息',
					tooltip: '查看完整开班信息',
					iconCls: 'printline',
					handler: function(){
						viewItem();
					}
				}]
			})
		},'-',{
			text: '开班操作',
			tooltip: '操作开班信息',
			iconCls: 'option',
			menu: new Ext.menu.Menu({
				id: 'menu4',
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
		}]
	}
	else {
		teamToolbar = [{
			text: '刷新',
			tooltip: '刷新开班信息',
			iconCls: 'refresh',
			handler: function(){
				allRefresh(objectStore);
			}
		}, '-',{
			text: '开班编辑',
			tooltip: '开班编辑信息',
			iconCls: 'option',
			menu: new Ext.menu.Menu({
				id: 'menu1',
				items: [{
					text: '复制开班',
					tooltip: '模板开班',
					iconCls: 'option',
					handler: function(){
						templateTeam(idName,objectGrid.getBottomToolbar().cursor);
					}
				}]
			})
		}, '-',{
			text: '开班预订',
			tooltip: '开班预订信息',
			iconCls: 'option',
			menu: new Ext.menu.Menu({
				id: 'menu2',
				items: [{
					text: '下订单',
					tooltip: '填报一个线路',
					iconCls: 'add',
					handler: function(){
						signUp();
					}
				},{
					text: '订单查询',
					tooltip: '查询订单信息',
					iconCls: 'option',
					handler: function(){
						queryOrder();
					}
				}]
			})
		}, '-',{
			text: '打印',
			tooltip: '打印开班信息',
			iconCls: 'option',
			menu: new Ext.menu.Menu({
				id: 'menu3',
				items: [{
					text: '开班信息',
					tooltip: '查看完整开班信息',
					iconCls: 'printline',
					handler: function(){
						viewItem();
					}
				}
//				,{
//					text: '行程信息',
//					tooltip: '查看开班行程信息',
//					iconCls: 'printline',
//					handler: function(){
//						linePrint();
//					}
//				}
				]
			})
		},'-',{
			text: '开班操作',
			tooltip: '操作开班信息',
			iconCls: 'option',
			menu: new Ext.menu.Menu({
				id: 'menu4',
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
		}]
	}
	
	/*
	 * 第二tab面板数据源，dataurl的请求带参数
	 */
	objectGrid = new Ext.grid.GridPanel({
		loadMask: true,
		ds: objectStore,
		id: node,
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
		
		view: new Ext.grid.GroupingView({
			forceFit: true,
			showGroupName: false,
			enableNoGroups: false, // REQUIRED!
			hideGroupedColumn: true,
			startCollapsed:true,
			groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "条" : "条"]})'
		}),
		bbar: new MyPagingToolbar({
			pageSize: pageSize,
			store: objectStore,
			displayInfo: true,
			displayMsg: '显示 {0}-{1}条 / 共 {2} 条',
			refreshText:'刷新线路',
			emptyMsg: "无数据。"
		}),
		tbar: teamToolbar,
		
		listeners: {
			'rowdblclick': {
				fn: viewItem,
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

//*===================操作函数区域
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

/**
 * 
 * @param {Object} idName
 */	
function comCreate(idName,actions,type,start){
	var attributePanel;
	var linePanel;
	var pickPanel;
	var propertyPanel;
	var freePlanePanel;
	
	areaBoxstore = new Ext.data.SimpleStore({
		fields: ['returnValue','displayValue'],
		proxy: new Ext.data.HttpProxy({
			url: 'getArea.shtml',
			method: 'POST'
		})
	});
	
	saleBoxstore =new Ext.data.SimpleStore({
		fields: ['returnValue','displayValue'],
		data:saledata
	});
	
	hotelStarBoxstore = new Ext.data.SimpleStore({
		fields:['hotelStarNum'],		
		data: hotelStar
	});
	
	
	attributePanel = new Ext.Panel({
		title: '出团信息',
		id:'attributeP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 290,
		defaults: {
			width: 230,
			anchor: '95%'
		},
		
		items: [{
			fieldLabel: '开班名称',
			allowBlank: false,
			xtype: 'textfield',
			id:idName[0],
			name:idName[0]
		}]
	});
	
	var dateFiled1=new Ext.form.DateField({
		fieldLabel: '开班日期',
		id: idName[1],
		name: idName[1],
		allowBlank: false,
		vtype: 'daterange',
		endDateField: 'endDateStr',
		format: 'Y-m-d'
	});
	
	var dateFiled2=new Ext.form.DateField({
		fieldLabel: '开班截止',
		id: 'endDateStr',
		name: 'endDateStr',
		allowBlank: false,
		vtype: 'daterange',
		startDateField: idName[1],
		format: 'Y-m-d'
	});
	
	var timeFiled1=new Ext.form.TimeField({
		fieldLabel: '出发时间',
		id: idName[2],
		name: idName[2],
		allowBlank: false,
		minValue: '0:00',
		maxValue: '23:45',
		format: 'H:i',
		emptyText: '请选择一个出发时间',
		forceSelection: true
	});
	
	var comBox1=new Ext.form.ComboBox({
		fieldLabel: '销售类型',
		store: saleBoxstore,
		id: idName[4],
		name: 'teamType',
		hiddenName: 'teamType',
		valueField: 'returnValue',
		displayField: 'displayValue',
		typeAhead: true,
		allowBlank: false,
		mode: 'local',
		triggerAction: 'all',
		bodyStyle: 'padding:3px',
		emptyText: '请选择一个销售类型',
		loadingText: '正在加载请等待....',
		forceSelection: true, //强制只能选择，不让输入
		selectOnFocus: true
	});
	
	var textArea1=new Ext.form.TextArea({
		fieldLabel: '订单预订信息',
		name: idName[5],
		id: idName[5],
		allowBlank: true,
		height: 100,
		grow: false,
		growMin: 100,
		anchor: '95%'
	});
	
	var endDateNum = new Ext.form.TextField({
		fieldLabel: '报名提前天数',
		allowBlank:false,
		name: 'pushDay',
		id: 'pushDay',
		vtype: 'posint'
		});
	
	var correctCheckBox = new Ext.form.Checkbox({
		fieldLabel:'开班日期准确',
		checked:true,
		inputValue:'1',
		name:'correctStr',
		id:'correctStr'
	});
	
	if(type!=='update'){
		attributePanel.add(dateFiled1);
		attributePanel.add(dateFiled2);
		attributePanel.add(endDateNum);
	}
	attributePanel.add(correctCheckBox);
	attributePanel.add(timeFiled1);
	attributePanel.add(comBox1);
	
	var testStr1={
			layout:'column',
			bodyStyle:'padding:10px 0 0 0',
        	border:false,
			items:[{
				columnWidth: .25,
				layout: 'form',
				labelWidth:20,
            	labelSeparator:'',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '线路推荐',
					id:'recommend',
					hideLabel:true,
					labelSeparator:'',
					name: 'recommend',
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .25,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '新品(国内9的倍数)',
					id:'newproduct',
					hideLabel:true,
					labelSeparator:'',
					name: 'newproduct',
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .25,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '特价(买一送一)',
					id:'speical',
					hideLabel:true,
					labelSeparator:'',
					name: 'speical',
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .25,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					id: 'isNet',
					boxLabel: '上架',
					hideLabel: true,
					checked: true,
					labelSeparator: '',
					name: 'isNet',
					inputValue: '1',
					anchor: '95%'
				}]
			}]
		};
		
	var testStr2={
			layout:'column',
			bodyStyle:'padding:10px 0 0 0',
        	border:false,
			items:[{
				columnWidth: .25,
				layout: 'form',
				labelWidth:20,
            	labelSeparator:'',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '天天开班',
					id: weeksName[0],
					hideLabel: true,
					labelSeparator: '',
					name: weeksName[0],
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .25,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '星期日',
					id:weeksName[1],
					hideLabel:true,
					labelSeparator:'',
					name: weeksName[1],
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .25,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '星期一',
					id:weeksName[2],
					hideLabel:true,
					labelSeparator:'',
					name: weeksName[2],
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .25,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '星期二',
					id:weeksName[3],
					hideLabel:true,
					labelSeparator:'',
					name: weeksName[3],
					inputValue: '1',
					anchor: '95%'
				}]
			}]
		};
		
	var testStr3={
			layout:'column',
			bodyStyle:'padding:10px 0 0 0',
        	border:false,
			items:[{
				columnWidth: .25,
				layout: 'form',
				labelWidth:20,
            	labelSeparator:'',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '星期三',
					id:weeksName[4],
					hideLabel:true,
					labelSeparator:'',
					name: weeksName[4],
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .25,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '星期四',
					id:weeksName[5],
					hideLabel:true,
					labelSeparator:'',
					name: weeksName[5],
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .25,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '星期五',
					id:weeksName[6],
					hideLabel:true,
					labelSeparator:'',
					name: weeksName[6],
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .25,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '星期六',
					id:weeksName[7],
					hideLabel:true,
					labelSeparator:'',
					name: weeksName[7],
					inputValue: '1',
					anchor: '95%'
				}]
			}]
		};
	
	attributePanel.add(testStr1);
	if(type!=='update'){
		attributePanel.add(testStr2);
		attributePanel.add(testStr3);
	}

	pickStore = new Ext.data.SimpleStore({
		fields:['returnValue','displayValue'],		
		proxy: new Ext.data.HttpProxy({
			url: 'getAssemble.shtml',
			method: 'POST'
		})
	});
	
	teamThemeStore=new Ext.data.SimpleStore({
		fields: ['returnValue','themeFlag'],
		data:themedata
	});
	
	themeColorStore=new Ext.data.SimpleStore({
		fields: ['returnValue','displayValue','themeColorFlag'],
		data:themeColorData
	});
	
	youhuiBoxstore=new Ext.data.SimpleStore({
		fields: ['returnValue'],
		data:[['无优惠措施'],['买一送一']]
	});
	
	var favourable=new Ext.form.ComboBox({
		fieldLabel: '优惠措施',
		store: youhuiBoxstore,
		id: 'youhuiSelect',
		name: 'youhui',
		displayField: 'returnValue',
		typeAhead: true,
		allowBlank:true,
		mode: 'local',
		triggerAction: 'all',
		bodyStyle: 'padding:3px',
		emptyText: '无优惠措施',
		loadingText: '正在加载请等待....',
		forceSelection: true, 
		selectOnFocus: true
	});
	
	propertyPanel = new Ext.Panel({
		title: '信息属性',
		id: 'propertyP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 290,
		defaults: {
			width: 230,
			anchor: '95%'
		},
		
		items: [{
			xtype: 'combo',
			fieldLabel: '酒店星级',
			store: hotelStarBoxstore,
			id:idName[6],
			name:idName[6],
			displayField:'hotelStarNum',
	        typeAhead: true,
	        mode: 'local',
	        triggerAction: 'all',
			bodyStyle: 'padding:3px',
	        emptyText:'不入住酒店',
			loadingText:'正在加载请等待....',
			forceSelection:true,   //强制只能选择，不让输入
	        selectOnFocus:true
		},{
			xtype: 'textfield',
			fieldLabel: '天数',
			allowBlank:false,
			name: idName[8],
			id: idName[8],
			vtype: 'posint'
		},{
			xtype: 'textfield',
			fieldLabel: '紧急电话',
			allowBlank:false,
			name: idName[9],
			id: idName[9],
			vtype: 'telCheck'
		},{
			xtype:'combo',
			store: teamThemeStore,       
			id:'themeSelect',
			name:'theme',
			hiddenName:'theme',
			valueField:'returnValue',
	        displayField:'returnValue',
			plugins:new Ext.ux.plugins.IconCombo(),
            iconClsField: 'themeFlag',
			fieldLabel:'主题特色',
			allowBlank:true,
	        typeAhead: true,
	        mode: 'local',
	        triggerAction: 'all',
			bodyStyle: 'padding:3px',
	        emptyText:'无主题',
			loadingText:'正在加载请等待....',
			forceSelection:true,   
	        selectOnFocus:true
		},{
			xtype:'combo',
			store: themeColorStore,      
			id:'themeColorSelect',
			name:'themeColor',
			hiddenName:'themeColor',
			valueField:'returnValue',
	        displayField:'displayValue',
			plugins:new Ext.ux.plugins.IconCombo(),
            iconClsField: 'themeColorFlag',
			fieldLabel:'主题颜色',
			allowBlank:true,
	        typeAhead: true,
	        mode: 'local',
	        triggerAction: 'all',
			bodyStyle: 'padding:3px',
	        emptyText:'无颜色',
			loadingText:'正在加载请等待....',
			forceSelection:true,   
	        selectOnFocus:true
		},favourable      
		]
	});
	
	propertyPanel.add(textArea1);
	
	if(parseInt(flag)!==2){
		seatName='占座位';
	}
	else{
		seatName='占票位';
	}
	
	pickPanel=new Ext.Panel({
		title: '接送地',
		id: 'pickP',
		layout: 'form',
		bodyStyle: 'padding:10px',
		height: 290,
		autoScroll: true
	});
	
	manPricePanel = new Ext.Panel({
			title: '成人价格',
			id: 'manPP',
			layout: 'form',
			bodyStyle: 'padding:10px',
			height: 290,
			autoScroll: true,
			
			tbar: [{
				text: '添加成人价',
				tooltip: '添加一个成人价格体系',
				handler: function(){
					addManPrice();
				},
				iconCls: 'add'
			}, '-', {
				text: '删除价格',
				tooltip: '删除一个成人价格体系',
				handler: function(){
					deleteManPrice();
				},
				iconCls: 'remove'
			}]
		});
	
	childPricePanel = new Ext.Panel({
			title: '儿童价格',
			id: 'childPP',
			layout: 'form',
			bodyStyle: 'padding:10px',
			height: 290,
			autoScroll: true,
			
			tbar: [{
				text: '添加儿童价',
				tooltip: '添加一个儿童价格体系',
				handler: function(){
					addChildPrice();
				},
				iconCls: 'add'
			}, '-', {
				text: '删除价格',
				tooltip: '删除一个儿童价格体系',
				handler: function(){
					deleteChildPrice();
				},
				iconCls: 'remove'
			}]
		});
	
		freePlanePanel = new Ext.Panel({
			title: '自由行航班',
			id: 'freePlanePanel',
			layout: 'form',
			bodyStyle: 'padding:10px',
			autoScroll: true,
			height: 290,
			defaults: {
				width: 230,
				anchor: '95%'
			},
			
			items: [{
				xtype: 'textfield',
				fieldLabel: '机票税',
				id: idName[10],
				name: idName[10],
				vtype: 'posint'
			},{
				xtype: 'textarea',
				fieldLabel: '参考航班去程',
				name: idName[11],
				id: idName[11],
				height: 100,
				grow: false,
				growMin: 100,
				anchor: '95%'
			},{
				xtype: 'textarea',
				fieldLabel: '参考航班回程',
				name: idName[12],
				id: idName[12],
				height: 100,
				grow: false,
				growMin: 100,
				anchor: '95%'
			}]
		});
		
		hotelPanel = new Ext.Panel({
			title: '酒店',
			id: 'hotelP',
			layout: 'form',
			bodyStyle: 'padding:10px',
			height: 290,
			autoScroll: true,
			
			tbar: [{
				text: '添加酒店',
				tooltip: '添加酒店',
				handler: function(){
					addContent();
				},
				iconCls: 'add'
			}, '-', {
				text: '删除酒店',
				tooltip: '删除已有酒店',
				handler: function(){
					deleteContent();
				},
				iconCls: 'remove'
			}]
		});
	
	lineBoxstore= new Ext.data.SimpleStore({
		fields: ['returnValue', 'displayValue', 'subTitle', 'createTime', 'writer'],
		proxy: new Ext.data.HttpProxy({
			url: 'getTeamLine.shtml',
			method: 'POST'
		})
	});
	
	linePanel = new Ext.Panel({
		title: '线路选择',
		id:'linePanel',
		height: 280,
		layout: 'column',
		bodyStyle: 'padding:10px',
		autoHeight: true,
		autoScroll: true,
		collapsible: true,
		items: [{
			collapsible: true,
			title: '类别选择',
			xtype: 'treepanel',
			columnWidth: .4,
			bodyStyle: 'padding:3px,margin-right:5px',
			minWidth: 80,
			height: 275,
			autoScroll: true,
			split: true,
			loader: new Ext.tree.TreeLoader({
				dataUrl: 'getChildrenByNode.shtml'
			}),
			root: new Ext.tree.AsyncTreeNode({
				expanded: true,
				id: regionId,
				text: '全分类',
				disabled: true,
				draggable: false
			}),
			rootVisible: false,
			listeners: {
				click: function(n){
					if (n.isLeaf()) {
						clickNode(n);
					}
					else {
						n.expand();
					}
				},
				beforeload: function(node){
					this.loader.dataUrl = 'getChildrenByNode.shtml';
				}
			}
		},{
			xtype: 'fieldset',
			title: '线路选择',
			bodyStyle: 'padding:10px 0 0 10px;',
			columnWidth: .6,
			defaults: {anchor: '95%'},
			collapsed: false, 
			height: 80,
			items: [{
				xtype: 'combo',
				store: lineBoxstore,
				id: idName[13],
				name: 'lineId',
				hiddenName: 'lineId',
				valueField: 'returnValue',
				displayField: 'displayValue',				
				typeAhead: true,
				hideLabel:true,
				labelSeparator:'',
				mode: 'remote',
				triggerAction: 'all',
				emptyText: '请选择一条线路',
				tpl: '<tpl for="."><div ext:qtip="{displayValue}---{subTitle}" class="x-combo-list-item">{displayValue}</div></tpl>',
				stateId: '0',
				allowBlank: false,
				loadingText: '正在加载请等待....',
				forceSelection: true, //强制只能选择，不让输入
				listeners: {
					'expand': {
						fn: lineExpand,
						scope: this
					},
					'select':{
							fn:lineSelect,
							scope:this
						}
				},
				selectOnFocus: true
			}]		
		}]
	});
	

	var radioPanel = new Ext.Panel({
		columnWidth:.2,
		html:'出行交通方式:'
	});
	
	var radioP1 = new Ext.Panel({
		columnWidth:.2,
		items:[
			new Ext.form.Radio({
				id: 'plane',
				name: 'ticketType',
				inputValue: '1',
				labelSeparator: '',
				listeners: {
					'check': {
						fn: showTravel,
						scope: this
					}
				},
				boxLabel: '飞机'
			})
		]
	});
	
	var radioP2 = new Ext.Panel({
		columnWidth:.2,
		items:[
			new Ext.form.Radio({
				id: 'train',
				name: 'ticketType',
				inputValue: '2',
				labelSeparator: '',
				listeners: {
					'check': {
						fn: showTravel,
						scope: this
					}
				},
				boxLabel: '火车'
			})
		]
	});
	
	var radioP3 = new Ext.Panel({
		columnWidth:.2,
		items:[
			new Ext.form.Radio({
				id: 'bus',
				name: 'ticketType',
				inputValue: '3',
				labelSeparator: '',
				listeners: {
					'check': {
						fn: showTravel,
						scope: this
					}
				},
				boxLabel: '汽车'
			})
		]
	});
	
	var radioP4 = new Ext.Panel({
		columnWidth:.2,
		items:[
			new Ext.form.Radio({
				id: 'ship',
				name: 'ticketType',
				inputValue: '4',
				labelSeparator: '',
				listeners: {
					'check': {
						fn: showTravel,
						scope: this
					}
				},
				boxLabel: '轮船'
			})
		]
	});

	trafficPanel = new Ext.Panel({
		title: '交通选择',
		renderTo:'vehicle',
		id:'vehicle',
		layout: 'form',
		height: 280,
		bodyStyle: 'padding:10px',
		items: [{
				xtype: 'combo',
				fieldLabel: '回程交通',
				store: comeTrafficBoxstore,
				id: 'comeTraffic',
				name: 'comeTraffic',
				displayField: 'returnValue',
				allowBlank: false,
				typeAhead: true,
				mode: 'local',
				triggerAction: 'all',
				bodyStyle: 'padding:3px',
				width: 400,
				emptyText: '请选择一个回程交通',
				loadingText: '正在加载请等待....',
				forceSelection: true, //强制只能选择，不让输入
				selectOnFocus: true
			},{
			layout:'column',
			border:false,
			bodyStyle:'margin-bottom:40',
			defaults:{border:false},
			width:500,
			items:[radioPanel,radioP1,radioP2,radioP3,radioP4]
		},{
			border:false,
			html:'请选择：'
		}]
	});

	tabPanel = new Ext.TabPanel({
		activeTab: 0,
		autoHeight: true,
		items: [
		attributePanel,
		propertyPanel,
		pickPanel,
		manPricePanel,
		childPricePanel,
		freePlanePanel,
		hotelPanel
		]
	});
	
	if(type!=='update'){
		tabPanel.add(linePanel);
		tabPanel.add(trafficPanel);
	}
	
	fpanel = new Ext.FormPanel({
		region: 'center',
		bodyStyle: 'padding:5px',
		collapsible: true,
		items: [tabPanel],
		
		buttons: [{
			text: '保存',
			handler: function(){
				save(fpanel,type,start);
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
	
	if(flag!='4'&&flag!='5'){
		tabPanel.remove(freePlanePanel);
		tabPanel.remove(hotelPanel);
	}
	
	pickAddPanel(fpanel,tabPanel);
	
	if(actions!='add'){
		teamUpdateAjax(fpanel,actions,idName,type);
	}
}

function pickAddPanel(fpanel,tabPanel){
	Ext.Ajax.request({
		url: 'getAssemble.shtml',
		method: 'POST',
		success: function(result, request){
			initAddPick(result,fpanel,tabPanel);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败！');
			window.close();
		}
	});
}

function initAddPick(result,fpanel,tabPanel){
	var pickP=fpanel.findById('pickP');
	tabPanel.setActiveTab(pickP);
	var list = doJSON(result.responseText);
	assembleList=list;
	var n,m,tempColumnP;

	if(parseInt((list.length)/2)===(list.length)/2){
		n=parseInt((list.length)/2);
	}
	else{
		n=parseInt((list.length)/2)+1;
	}

	for(var i=0;i<n;i++){
		tempColumnP = new Ext.Panel({
			layout: 'column',
			bodyStyle: 'padding:10px 0 0 0',
			border: false
		});
		if ((i ===(n - 1))&&((list.length)/2!==n)) {
			k = list.length-(n-1)*2;
		}
		else{
			k=2;
		}
		
		for (j = 0; j < k; j++) {
			m=i*2+j;
			tempColumnP.add({
				columnWidth: .5,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: list[m][1],
//					id: list[m],
					id:list[m][0],
					stateId:m,
					fieldLabel:m,
//					hideLabel: true,
//					labelSeparator: '',
					name: list[m][1],
					inputValue: list[m][1],
					listeners:{
						'check':pickClick
					}
				}]
			});
		}
		pickP.add(tempColumnP);
	}
	pickP.show();
	pickP.doLayout();
}

function pickClick(item){
	var pickP=Ext.getCmp('pickP');
	var subPickP = Ext.getCmp('subPickP'+item.stateId);
	if(item.checked===true){
		if (!subPickP) {
			subPickP = new Ext.Panel({
//				title: item.id+'接送地',
				id: 'subPickP'+item.stateId,
				layout: 'column',
				bodyStyle: 'padding:10px',
				autoScroll: true
			});
			var tripleP1 = new Ext.Panel({
				columnWidth:.12,
				layout:'form',
				border:false,
				html:'接送地-'+item.stateId+':'
			});
			var tripleP2 = new Ext.Panel({
				columnWidth: .22,
				layout: 'column',
				border:false,
				items: [{
					xtype: 'panel',
					border: false,
					columnWidth: .3,
					html: '<font style:\"font-size:24px;\">价格:</font>'
				},{
					xtype: 'panel',
					border: false,
					layout: 'form',
					columnWidth:.65,
					items: [{
						xtype: 'textfield',
						width:80,
						allowBlank: false,
						hideLabel: true,
						labelSeparator: '',
						name: item.id + 'p',
						id: item.id + 'p'
					}]
				}]
			});
			var tripleP3 = new Ext.Panel({
				columnWidth:.25,
				layout: 'column',
				border:false,
				items:[{
					xtype: 'panel',
					border: false,
					columnWidth: .3,
					html: '<font style:\"font-size:24px;\">时间:</font>'
				},{
					xtype: 'panel',
					border: false,
					layout: 'form',
					columnWidth: .65,
					items:[{
						xtype: 'timefield',
						hideLabel: true,
						labelSeparator: '',
						width: 90,
						name: item.id + 't',
						id: item.id + 't',
						minValue: '0:00',
						maxValue: '23:45',
						format: 'H:i',
						forceSelection: true
					}]
				}]
			});
			var tripleP4 = new Ext.Panel({
				columnWidth:.25,
				layout: 'column',
				border:false,
				items:[{
					xtype: 'panel',
					border: false,
					columnWidth: .3,
					html: '<font style:\"font-size:24px;\">类型:</font>'
				},{
					xtype: 'panel',
					border: false,
					layout: 'form',
					columnWidth: .65,
					items:[{
						xtype: 'combo',
						store: pickTypeStore,
						hideLabel: true,
						labelSeparator: '',
						width: 90,
						name: item.id + 'pickType',
						id: item.id + 'pickType',
						displayField: 'returnValue',
						typeAhead: true,
						allowBlank: false,
						value:'含接送',
						mode: 'local',
						triggerAction: 'all',
						bodyStyle: 'padding:3px',
						loadingText: '正在加载请等待....',
						forceSelection: true,
						selectOnFocus: true
					}]
				}]
			});
			var tripleP5=new Ext.Panel({
				columnWidth:.15,
				layout:'form',
				border:false,
				items:[{
					xtype: 'checkbox',
					boxLabel: '待定',
					id:item.id+'unsure',
					hideLabel: true,
					labelSeparator: '',
					name: item.id+'unsure',
					stateId:item.id,
					inputValue: item.id,
					listeners:{
						'check':unsureClick
					}
				}]
			});
			subPickP.add(tripleP1);
			subPickP.add(tripleP2);
			subPickP.add(tripleP3);
			subPickP.add(tripleP4);
			subPickP.add(tripleP5);
		}
		pickP.add(subPickP);
	}
	else{
		if(subPickP){
			pickP.remove(subPickP);
		}
	}	
	
	pickP.show();
	pickP.doLayout();
}

function unsureClick(item){
	if(item.checked){
		Ext.getCmp(item.stateId+'t').setValue('');
	}
}

function teamUpdateAjax(fpanel,Id,idName,type){
	Ext.Ajax.request({
		url: 'getSingleTeam.shtml',
		method: 'POST',
		params: {teamId:Id},
		success: function(result, request){
			initUpdateTeam(result,fpanel,idName,type);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败！');
			window.close();
		}
	});
}

function initUpdateTeam(result, fpanel, idName, type){
	var teamList = doJSON(result.responseText);
	if (teamList.success == true) {
	
		var list = teamList.team;
		
		fpanel.findById(idName[0]).setValue(list.teamName);
		if (type == 'add') {
			fpanel.findById(idName[1]).setValue(list.startDateStr);
			fpanel.findById('endDateStr').setValue(list.endDateStr);
			fpanel.findById('pushDay').setValue(list.pushDay);
		}
		
		fpanel.findById(idName[2]).setValue(list.startTime);
		fpanel.findById('correctStr').setValue(list.correct);
		fpanel.findById(idName[4]).setValue(list.teamType);
		fpanel.findById(idName[5]).setValue(list.orderConfirm);
		
		if (type == 'tpl') {
			fpanel.findById('pushDay').setValue(list.pushDay);
			var weeksList = list.dateType.split(',');
			if (weeksList[0] === '1') {
				fpanel.findById(weeksName[0]).setValue('1');
			}
			else {
				for (i = 1; i < 8; i++) {
					fpanel.findById(weeksName[i]).setValue(weeksList[i]);
				}
			}
		}
		
		var NP = new Array();
		NP = list.newproduct.split(',');
		fpanel.findById('newproduct').setValue(parseInt(NP[1]));
		
		var RC = new Array();
		RC = list.recommend.split(',');
//		if (RC[1] === '1') {
			fpanel.findById('recommend').setValue(parseInt(RC[1]));
//		}
		
		var SP = new Array();
		SP = list.speical.split(',');
//		if (SP[1] === '1') {
			fpanel.findById('speical').setValue(parseInt(SP[1]));
//		}
		
		fpanel.findById('isNet').setValue(list.isNet);
		
		fpanel.findById('propertyP').show();
		fpanel.findById(idName[6]).setValue(list.hotelStar);
		fpanel.findById(idName[8]).setValue(list.teamday);
		fpanel.findById(idName[9]).setValue(list.urgentTel);
		
//		Ext.get('assembleId').dom.value = list.assemble.assembleId;
//		Ext.get('assId').dom.value = list.assemble.name;
		
		fpanel.findById('themeSelect').setValue(list.theme);
		fpanel.findById('themeColorSelect').setValue(list.themeColor);
		fpanel.findById('youhuiSelect').setValue(list.youhui);
		
		fpanel.findById('pickP').show();
		var assembleArray = new Array();
		assembleArray = list.assemble.split('###');
		if (assembleArray.length > 0) {
			var subAArray = new Array();
			for (var i = 0; i < assembleArray.length; i++) {
				subAArray = assembleArray[i].split('@@@');
				for (j = 0; j < assembleList.length; j++) {
					if (subAArray[3] === assembleList[j][0]) {
						fpanel.findById(subAArray[3]).setValue(true);
						if(subAArray[1]===''){
							fpanel.findById(subAArray[3] + 'unsure').setValue(true);
						}
						else{
							fpanel.findById(subAArray[3] + 't').setValue(subAArray[1]);
						}
						fpanel.findById(subAArray[3] + 'p').setValue(subAArray[2]);
						fpanel.findById(subAArray[3] + 'pickType').setValue(subAArray[4]);
					}
				}
				subAArray.length = 0;
			}
		}
		
		var manPP = fpanel.findById('manPP');
		var manPriceList = new Array();
		manPriceList = list.manPrice.split("###");
		if (manPriceList.length > 0) {
			var manPC = new Array();
			for (var p = 1; p <= manPriceList.length; p++) {
				manPC = manPriceList[p - 1].split("@@@");
				im = p;
				addManPrice();
				manPP.findById('manDesc' + p).setValue(manPC[0]);
				manPP.findById('prman' + p).setValue(manPC[1]);
				manPP.findById('prmarman' + p).setValue(manPC[2]);
				manPP.findById('hotelMargin' + p).setValue(manPC[3]);
				manPP.findById('manBed' + p).setValue(manPC[4]);
				manPP.findById('manSeat' + p).setValue(manPC[5]);
				manPP.findById('manTicket' + p).setValue(manPC[6]);
				manPP.findById('manDinner' + p).setValue(manPC[7]);
				if (manPC[8] === 'true') {
					manPP.findById('showFirst' + p).setValue(true);
				}
			}
		}
		
		var childPP = fpanel.findById('childPP');
		var childPriceList = new Array();
		childPriceList = list.childPrice.split("###");
		if (childPriceList.length > 0) {
			if (childPriceList[0] !== '') {
				var childPC = new Array();
				for (var k = 1; k <= childPriceList.length; k++) {
					childPC = childPriceList[k - 1].split("@@@");
					ic = k;
					addChildPrice();
					childPP.findById('childDesc' + k).setValue(childPC[0]);
					childPP.findById('prchild' + k).setValue(childPC[1]);
					childPP.findById('prmarchild' + k).setValue(childPC[2]);
					childPP.findById('childSeat' + k).setValue(childPC[3]);
					childPP.findById('childBed' + k).setValue(childPC[4]);
					childPP.findById('childTicket' + k).setValue(childPC[5]);
					childPP.findById('childDinner' + k).setValue(childPC[6]);
					childPP.findById('childAssP' + k).setValue(childPC[7]);
				}
			}
		}
		
		var hotelP = fpanel.findById('hotelP');
		if (hotelP) {
		
			fpanel.findById(idName[10]).setValue(list.planeTax);
			fpanel.findById(idName[11]).setValue(list.planeGo);
			fpanel.findById(idName[12]).setValue(list.planeBack);
			
			var ContentList = new Array();
			ContentList = list.hotelContent.split("###");
			if (ContentList.length > 0) {
				var Content = new Array();
				for (var i = 1; i <= ContentList.length; i++) {
					Content = ContentList[i - 1].split("@@@");
					id = i;
					addContent();
					hotelP.findById('hotelTitle' + i).setValue(Content[0]);
					hotelP.findById('hotelStar' + i).setValue(Content[1]);
					hotelP.findById('hotelAddress' + i).setValue(Content[2]);
					hotelP.findById('hotelPrice' + i).setValue(Content[3]);
					hotelP.findById('hotelCustomerP' + i).setValue(Content[4]);
				}
			}
		}
		
		if (type == 'update') {
			tabPanel.remove(tabPanel.findById('vehicle'));
			tabPanel.remove(tabPanel.findById('linePanel'));
		}
		else 
			if (type == 'tpl') {
				fpanel.findById('linePanel').show();
				Ext.get('lineId').dom.value = list.line.lineId;
				Ext.get(idName[13]).dom.value = list.line.title;
			}
			else {
			
				fpanel.findById('linePanel').show();
				Ext.get('lineId').dom.value = list.line.lineId;
				Ext.get(idName[13]).dom.value = list.line.title;
				
				fpanel.findById('vehicle').show();
				if (list.ticketType === 1) {
					fpanel.findById('plane').setValue(true);
					Ext.get('airId').dom.value = list.ticketId;
					Ext.get('selectPlaneBox').dom.value = list.ticketLog.date + '-' + list.ticketLog.name;
				}
				else 
					if (list.ticketType === 2) {
						fpanel.findById('train').setValue(true);
						Ext.get('trainId').dom.value = list.ticketId;
						Ext.get('selectTrainBox').dom.value = list.ticketLog.date + '-' + list.ticketLog.name;
					}
					else 
						if (list.ticketType === 3) {
							fpanel.findById('bus').setValue(true);
							Ext.get('busId').dom.value = list.ticketId;
							Ext.get('selectBusBox').dom.value = list.ticketLog.date + '-' + list.ticketLog.name;
						}
						else {
							fpanel.findById('ship').setValue(true);
							Ext.get('shipId').dom.value = list.ticketId;
							Ext.get('selectShipBox').dom.value = list.ticketLog.date + '-' + list.ticketLog.name;
						}
			}
	}
	else {
		Ext.MessageBox.alert('加载失败', teamList.msg);
	}
}

function lineExpand(item){
	if (lineRegionId) {
		if (item.stateId === '0') {
			lineBoxstore.load({
				params: {
					lineRegionId: lineRegionId
				}
			});
			item.stateId = '1';
			oldNode=lineRegionId;
		}
		
		if(oldNode!=lineRegionId){
			lineBoxstore.removeAll();
			lineBoxstore.load({
				params: {
					lineRegionId: lineRegionId
				}
			});
			oldNode=lineRegionId;
		}
	}
	else{
		Ext.MessageBox.alert('线路加载', '请先选择一个类别！');
	}
}

function lineSelect(item){
	var tempSelectValue=item.getValue();
	var tempLineValue;
	if(tempSelectValue){
		var n=item.store.getCount();
		for(var i=0;i<n;i++){
			tempLineValue=item.store.getAt(i).get('returnValue');
			if(tempSelectValue===tempLineValue){
				tempSelectValue=item.store.getAt(i).get('displayValue');
				Ext.getCmp('teamName').setValue(tempSelectValue);
			}
		}
	}	
	tempSelectValue='';
}

function dayCheck(item){
	if(item.getValue()){
		for(i=0;i<weeksName.length;i++){
		propertyPanel.findById(weeksName[i]).setValue(true);
	}
	propertyPanel.doLayout();
	propertyPanel.show();
	}	
	else{
		for(i=1;i<weeksName.length;i++){
		propertyPanel.findById(weeksName[i]).isValid(true);
	}
	}
}

/*===========================接送函数==========================================*/
function pickExpand(item){
		if (item.stateId === '0') {
			item.stateId = '1';
		}
}
/*===========================接送函数 end==========================================*/
/*===========================出行方式函数==========================================*/
function showTravel(item,checked){
	if(item.checked){
		if(item.id=='plane'){
			if(trafficPanel.findById('selectBusBox')){
				trafficPanel.remove(trafficPanel.findById('selectBusBox'));
			}
			if(trafficPanel.findById('selectTrainBox')){
				trafficPanel.remove(trafficPanel.findById('selectTrainBox'));
			}
			if(trafficPanel.findById('selectShipBox')){
				trafficPanel.remove(trafficPanel.findById('selectShipBox'));
			}
			
			var selectPlaneBox=Ext.getCmp('selectPlaneBox');
			if (!selectPlaneBox) {
				selectPlaneBox=createPlaneBox();
			}
			
			travelType='plane';
			
			trafficPanel.add(selectPlaneBox);
			trafficPanel.doLayout();
		}
		
		else if(item.id=='train'){
			if(trafficPanel.findById('selectPlaneBox')){
				trafficPanel.remove(trafficPanel.findById('selectPlaneBox'));
			}
			if(trafficPanel.findById('selectBusBox')){
				trafficPanel.remove(trafficPanel.findById('selectBusBox'));
			}
			if(trafficPanel.findById('selectShipBox')){
				trafficPanel.remove(trafficPanel.findById('selectShipBox'));
			}
			
			var selectTrainBox=Ext.getCmp('selectTrainBox');
			if (!selectTrainBox) {
				selectTrainBox=createTrainBox();
			}
			
			travelType='train';
			
			trafficPanel.add(selectTrainBox);
			trafficPanel.doLayout();
		}
		else if (item.id=='bus'){
			if(trafficPanel.findById('selectPlaneBox')){
				trafficPanel.remove(trafficPanel.findById('selectPlaneBox'));
			}
			if(trafficPanel.findById('selectTrainBox')){
				trafficPanel.remove(trafficPanel.findById('selectTrainBox'));
			}
			if(trafficPanel.findById('selectShipBox')){
				trafficPanel.remove(trafficPanel.findById('selectShipBox'));
			}
			
			var selectBusBox=Ext.getCmp('selectBusBox');
			if (!selectBusBox) {
				selectBusBox=createBusBox();
			}
			
			travelType='bus';
			
			trafficPanel.add(selectBusBox);
			trafficPanel.doLayout();
		}
		else{
			if(trafficPanel.findById('selectPlaneBox')){
				trafficPanel.remove(trafficPanel.findById('selectPlaneBox'));
			}
			if(trafficPanel.findById('selectBusBox')){
				trafficPanel.remove(trafficPanel.findById('selectBusBox'));
			}
			if(trafficPanel.findById('selectTrainBox')){
				trafficPanel.remove(trafficPanel.findById('selectTrainBox'));
			}
			
			var selectShipBox=Ext.getCmp('selectShipBox');
			if (!selectShipBox) {
				selectShipBox=createShipBox();
			}
			
			travelType='ship';
			
			trafficPanel.add(selectShipBox);
			trafficPanel.doLayout();
		}
	}
}

function createShipBox(){	
	shipStore=new Ext.data.SimpleStore({
		fields: ['returnValue', 'displayValue', 'total', 'confirm','beginDate'],
		proxy: new Ext.data.HttpProxy({
			url: 'getShipInf.shtml',
			method: 'POST'
		})
	});	
	
	 var selectBox = new Ext.form.ComboBox({
		store: shipStore,       //先将值选出组合为三个数组赋值，示例如下
		id:'selectShipBox',
		name: 'shipId',
		hiddenName:'shipId',
		valueField:'returnValue',
		displayField: 'displayValue',
		stateId:'0',
		labelSeparator: '',
	    typeAhead: true,
	    mode: 'remote',
	    bodyStyle: 'padding-left:20px',
		triggerAction: 'all',
		bodyStyle: 'padding:3px',
	    emptyText:'请选择一个轮船信息',
		allowBlank:false,
		loadingText:'正在加载请等待....',
		forceSelection:true,   //强制只能选择，不让输入
	    listeners:{
			'expand':{
				fn:comboxExpand,
				scope:this
			}
		},
		anchor: '95%',
		selectOnFocus:true
	});
	return selectBox;
}

function createBusBox(){	
	busStore=new Ext.data.SimpleStore({
		fields: ['returnValue', 'displayValue', 'total', 'confirm','beginDate'],
		proxy: new Ext.data.HttpProxy({
			url: 'getBusInf.shtml',
			method: 'POST'
		})
	});	
	
	 var selectBox = new Ext.form.ComboBox({
		store: busStore,       //先将值选出组合为三个数组赋值，示例如下
		id:'selectBusBox',
		name: 'busId',
		hiddenName:'busId',
		valueField:'returnValue',
		displayField: 'displayValue',
		stateId:'0',
		labelSeparator: '',
	    typeAhead: true,
	    mode: 'remote',
	    bodyStyle: 'padding-left:20px',
		triggerAction: 'all',
		bodyStyle: 'padding:3px',
	    emptyText:'请选择一个汽车信息',
		allowBlank:false,
		loadingText:'正在加载请等待....',
		forceSelection:true,   //强制只能选择，不让输入
	    listeners:{
			'expand':{
				fn:comboxExpand,
				scope:this
			}
		},
		anchor: '95%',
		selectOnFocus:true
	});
	return selectBox;
}

function createTrainBox(){	
	trainStore=new Ext.data.SimpleStore({
		fields: ['returnValue', 'displayValue', 'total', 'confirm','beginDate'],
		proxy: new Ext.data.HttpProxy({
			url: 'getTrainInf.shtml',
			method: 'POST'
		})
	});	
	
	var selectBox = new Ext.form.ComboBox({
		store: trainStore,       //先将值选出组合为三个数组赋值，示例如下
		id:'selectTrainBox',
		name: 'trainId',
		hiddenName:'trainId',
		valueField:'returnValue',
		displayField: 'displayValue',
		stateId:'0',
		labelSeparator: '',
	    typeAhead: true,
		mode:'remote',
	    bodyStyle: 'padding-left:20px',
		triggerAction: 'all',
		bodyStyle: 'padding:3px',
	    emptyText:'请选择一个火车信息',
		allowBlank:false,
		loadingText:'正在加载请等待....',
		forceSelection:true,   //强制只能选择，不让输入
	    listeners:{
			'expand':{
				fn:comboxExpand,
				scope:this
			}
		},
		anchor: '95%',
		selectOnFocus:true
	});
	return selectBox;
}

function createPlaneBox(){	
	planeStore=new Ext.data.SimpleStore({
		fields: ['returnValue', 'displayValue', 'total', 'confirm','beginDate'],
		proxy: new Ext.data.HttpProxy({
			url: 'getAirInf.shtml',
			method: 'POST'
		})
	});	
	
	var selectBox = new Ext.form.ComboBox({
		store: planeStore,       
		id: 'selectPlaneBox',
		name: 'airId',
		hiddenName:'airId',
		valueField:'returnValue',
		displayField: 'displayValue',
		stateId:'0',		
		labelSeparator: '',
	    typeAhead: true,
		mode:'remote',
	    bodyStyle: 'padding-left:20px',
		triggerAction: 'all',
		bodyStyle: 'padding:3px',
	    emptyText:'请选择一个航班信息',
		allowBlank:false,
		loadingText:'正在加载请等待....',
		forceSelection:true,   //强制只能选择，不让输入
	    listeners:{
			'expand':{
				fn:comboxExpand,
				scope:this
			}
		},
		anchor: '95%',
		selectOnFocus:true
	});
	return selectBox;
}

function comboxExpand(item){
	if (item.id == 'selectPlaneBox') {
		if (item.stateId === '0') {
			planeStore.load();
			item.stateId = '1';
		}		
	}
	else 
		if (item.id == 'selectBusBox') {
			if (item.stateId === '0') {
				busStore.load();
				item.stateId = '1';
			}
		}
		else 
			if (item.id == 'selectTrainBox') {
				if (item.stateId === '0') {
					trainStore.load();
					item.stateId = '1';
				}
			}
			else{
				if (item.stateId === '0') {
					shipStore.load();
					item.stateId = '1';
				}
			}	
}

function clickNode(item){
	lineRegionId=item.id;	
	}

function panelreset(fpanel){
	fpanel.form.reset();
}

function cancel(){
	tplWin = Ext.getCmp('tplW');
	if (tplWin) {
		tplWin.close();
		tplWin='';
	}
}
	
	
/**
 * 表单保存
 * @param {Object} fpanel
 */
function save(fpanel,type,start){
	var urlPost;
	var paramsPost;
	var content,childPrice,childPrice;
	var priceCheck=false;
	var checkExpression = /^\d+$/;
	var Expression=true;
	var dateTyeCheck=false;
	var assembleCheck=false;
	
	fpanel.findById('attributeP').show();
	
	var hotelP = fpanel.findById('hotelP');
	if (hotelP) {
		fpanel.findById('hotelP').show();//显示panel
		var jArray = hotelP.findByType('fieldset');
		var jNumber = jArray.length;
		var hotel = new Array();
		
		for (var i = 1; i <= jNumber; i++) {
			var fstemp = hotelP.findById(i + 'hotel');
			var ftArray = new Array();
			ftArray[0] = fstemp.findById('hotelTitle' + i).getValue();
			ftArray[1] = fstemp.findById('hotelStar' + i).getValue();
			ftArray[2] = fstemp.findById('hotelAddress' + i).getValue();
			ftArray[3] = fstemp.findById('hotelPrice' + i).getValue();
			if(!checkExpression.test(ftArray[3])){
				Expression=false;
			}
			ftArray[4] = fstemp.findById('hotelCustomerP' + i).getValue();
			
			var ftArraystring = ftArray.join("@@@");
			hotel[i - 1] = ftArraystring;
		}
		
		content = hotel.join("###");
	}
	
	if (type !== 'update') {
		var proPanel = fpanel.findById('attributeP');
		var weeksList = new Array();
		if (proPanel.findById(weeksName[0]).getValue()) {
			weeksList[0] = 1;
			dateTyeCheck = true;
			for (var i = 1; i < 8; i++) {
				weeksList[i] = 0;
			}
		}
		else {
			weeksList[0] = 0;
			for (var i = 1; i < 8; i++) {
				if (proPanel.findById(weeksName[i]).getValue()) {
					weeksList[i] = 1;
					dateTyeCheck = true;
				}
				else {
					weeksList[i] = 0;
				}
			}
		}
		
		var dateType = weeksList.join(',');
	}
	
	fpanel.findById('propertyP').show();
	
	var pickP = fpanel.findById('pickP');
	var subArray=new Array();
	var assembleArray= new Array();
	var z=0,assemble;
	for (var j = 0; j < assembleList.length; j++) {
			if (pickP.findById(assembleList[j][0]).checked === true) {
				subArray[0]=assembleList[j][1];
				subArray[1]=pickP.findById(assembleList[j][0]+'t').getValue();
				if(pickP.findById(assembleList[j][0]+'p').getValue()===''){
					subArray[2]=0;
				}
				else{
					subArray[2]=pickP.findById(assembleList[j][0]+'p').getValue();
				}
				subArray[3]=assembleList[j][0];
				subArray[4]=pickP.findById(assembleList[j][0]+'pickType').getValue();
				subStr=subArray.join('@@@');
				assembleArray[z++]=subStr;
			}
		}
	assembleArray.length=z;
	if(assembleArray.length>0){
		assembleCheck=true;
	}
	assemble = assembleArray.join("###");
	
	var manPP = fpanel.findById('manPP');
	if(manPP){
		var manFS = manPP.findByType('fieldset');
		var manFSNumber=manFS.length;
		var manPriceArray = new Array();
		if(manFSNumber>0){
			for (var i = 1; i <= manFSNumber; i++) {
				var manFStemp = manPP.findById(i + 'manP');
				var manFtempArray = new Array();
				manFtempArray[0] = manFStemp.findById('manDesc' + i).getValue();
				manFtempArray[1] = manFStemp.findById('prman' + i).getValue();
				if (!checkExpression.test(manFtempArray[1])) {
					Expression = false;
				}
				manFtempArray[2] = manFStemp.findById('prmarman' + i).getValue();
				if (!checkExpression.test(manFtempArray[2])) {
					Expression = false;
				}
				manFtempArray[3] = manFStemp.findById('hotelMargin' + i).getValue();
				if (!checkExpression.test(manFtempArray[3])) {
					Expression = false;
				}
				manFtempArray[4] = manFStemp.findById('manBed' + i).getValue();;
				manFtempArray[5] = manFStemp.findById('manSeat' + i).getValue();
				manFtempArray[6] = manFStemp.findById('manTicket' + i).getValue();
				manFtempArray[7] = manFStemp.findById('manDinner' + i).getValue();
				manFtempArray[8] = manFStemp.findById('showFirst' + i).getValue();
				
				var manTempString = manFtempArray.join("@@@");
				manPriceArray[i - 1] = manTempString;
			}
			
			manPrice = manPriceArray.join('###');
			priceCheck=true;
		}
	}
	
	var childPP = fpanel.findById('childPP');
	if (childPP) {
		var childFS = childPP.findByType('fieldset');
		var childFSNumber = childFS.length;
		var childPriceArray = new Array();
		for (var i = 1; i <= childFSNumber; i++) {
			var childFStemp = childPP.findById(i + 'childP');
			var childFtempArray = new Array();
			childFtempArray[0] = childFStemp.findById('childDesc' + i).getValue();
			childFtempArray[1] = childFStemp.findById('prchild' + i).getValue();
			if (!checkExpression.test(childFtempArray[1])) {
					Expression = false;
				}
			childFtempArray[2] = childFStemp.findById('prmarchild' + i).getValue();
			if (!checkExpression.test(childFtempArray[2])) {
					Expression = false;
				}
			childFtempArray[3] = childFStemp.findById('childSeat' + i).getValue();
			childFtempArray[4] = childFStemp.findById('childBed' + i).getValue();
			childFtempArray[5] = childFStemp.findById('childTicket' + i).getValue();
			childFtempArray[6] = childFStemp.findById('childDinner' + i).getValue();
			childFtempArray[7] = childFStemp.findById('childAssP' + i).getValue();
			
			var childTempString = childFtempArray.join("@@@");
			childPriceArray[i - 1] = childTempString;
		}
		
		childPrice = childPriceArray.join('###');
	}
	
	if(fpanel.findById('linePanel')){
		fpanel.findById('linePanel').show();
	}
	
	if(fpanel.findById('vehicle')){
		fpanel.findById('comeTraffic').setValue(list.comeTraffic);
		fpanel.findById('vehicle').show();
	}
	
	if (tplWin) {
				urlPost = 'copyTeam.shtml';
				paramsPost = {
					content: content,
					weeks: dateType,
					manPrice: manPrice,
					childPrice: childPrice,
					assemble: assemble,
					flag: flag,
					teamId: selectRecord.get('teamId')
				}
			}
			else {
				Ext.Msg.alert('信息', '提交窗口错误参数!');
				window.close();
			}
	
	//提交数据
	if (fpanel.form.isValid()) {
		if (dateTyeCheck) {
				if (isContentValid(hotelP)) {
					if (priceCheck) {
						if (Expression) {
							this.disabled = true;
							
							fpanel.getForm().submit({
								url: urlPost,
								method: 'post',
								params: paramsPost,
								waitTitle: '请稍候',
								waitMsg: '开班正提交，请等待......',
								failure: function(fpanel, action){
									Ext.MessageBox.alert('保存失败', '开班保存未成功！');
									this.disabled = false;
								},
								success: function(fpanel, action){
									var returnJosn = action.result;
									if (returnJosn.success === true) {
										Ext.MessageBox.alert('保存成功', '开班保存成功');
										if (addwin) {
											addwin.close();
											addwin = '';
										}
										if (updwin) {
											updwin.close();
											updwin = '';
										}
										if (tplWin) {
											tplWin.close();
											tplWin = '';
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
							Ext.Msg.alert('信息', '请查看价格、酒店价格填写是否正确!');
						}
					}
					else {
						Ext.Msg.alert('信息', '请至少填写一种成人价格!');
					}
				}
				else {
					Ext.Msg.alert('信息', '酒店内容必须填写完整!');
				}
		}
		else{
			Ext.Msg.alert('信息', '必须选择一个开班类型!');
		}
	}
	else {
		Ext.Msg.alert('信息', '请填写完成再提交!');
	}
}

function isContentValid(hotelP){
	if (hotelP) {
		if (hotelP.findByType('textfield').length == 0) {
			return false;
		}
		for (var i = 0; i < hotelP.findByType('textfield').length; i++) {
			if (hotelP.findByType('textfield')[i].getValue() == "") {
				return false;
			}
		}
		for (var i = 0; i < hotelP.findByType('textarea').length; i++) {
			if (hotelP.findByType('textarea')[i].getValue() == "") {
				return false;
			}
		}
	}
	return true;
}

function addContent(){
	hotelPanel.add({
		xtype: 'fieldset',
		title: '第' + id + '家酒店',
		id: id + 'hotel',
		defaults: {
			xtype: 'textfield',
			allowBlank: false,
			width:230,
			anchor: '95%'
		},
		autoHeight: true,
		collapsed: false, //已经折叠否
		items: [{
			fieldLabel: '酒店名',
			name: 'hotelTitle' + id,
			id: 'hotelTitle' + id
		}, {
			xtype: 'combo',
			fieldLabel: '酒店星级',
			store: hotelStarBoxstore,
			name: 'hotelStar' + id,
			id: 'hotelStar' + id,
			displayField:'hotelStarNum',
	        typeAhead: true,
	        mode: 'local',
	        triggerAction: 'all',
			bodyStyle: 'padding:3px',
	        emptyText:'请选择一个酒店星级',
			allowBlank:false,
			loadingText:'正在加载请等待....',
			forceSelection:true,   
	        selectOnFocus:true
		}, {
			fieldLabel: '酒店地址',
			name: 'hotelAddress' + id,
			id: 'hotelAddress' + id
		}, {
			fieldLabel: '同行价格',
			name: 'hotelPrice' + id,
			id: 'hotelPrice' + id,
			vtype: 'posint'
		}, {
			fieldLabel: '直客价格',
			name: 'hotelCustomerP' + id,
			id: 'hotelCustomerP' + id,
			vtype: 'posint'
		}]
	
	});
	id++;
	hotelPanel.show();
	hotelPanel.doLayout();
}
	
function deleteContent(){
	if (id > 1) {
		var tempfieldset = hotelPanel.findById(--id + 'hotel');
		hotelPanel.remove(tempfieldset);
	}
}

function addManPrice(){
	manPricePanel.add({
		xtype: 'fieldset',
		title: '第' + im + '种成人价格体系',
		id: im + 'manP',
		defaults: {
			width:230,
			anchor: '95%'
		},
		autoHeight: true,
		collapsed: false, //已经折叠否
		items: [{
			xtype: 'textfield',
			fieldLabel: '描述',
			name: 'manDesc' + im,
			id: 'manDesc' + im
		}, {
			xtype: 'textfield',
			fieldLabel: '门市价',
			value:0,
			name: 'prman' + im,
			id: 'prman' + im,
			vtype: 'posint'
		}, {
			xtype: 'textfield',
			fieldLabel: '同行价',
			value:0,
			name: 'prmarman' + im,
			id: 'prmarman' + im,
			vtype: 'posint'
		}, {
			xtype: 'textfield',
			fieldLabel: '单房差',
			value:0,
			name: 'hotelMargin' + im,
			id: 'hotelMargin' + im,
			vtype: 'posint'
		},{
			layout:'column',
			bodyStyle:'padding:10px 0 0 0',
        	border:false,
			items:[{
				columnWidth: .25,
				layout: 'form',
				labelWidth:20,
            	labelSeparator:'',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '占床位',
					id: 'manBed'+im,
					hideLabel: true,
					readOnly:true,
					checked:true,
					labelSeparator: '',
					name: 'manBed'+im,
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .25,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: seatName,
					id:'manSeat'+im,
					hideLabel:true,
					checked:true,
					labelSeparator:'',
					name: 'manSeat'+im,
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .25,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '含门票',
					id:'manTicket'+im,
					hideLabel:true,
					checked:true,
					labelSeparator:'',
					name: 'manTicket'+im,
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .25,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '含用餐',
					id:'manDinner'+im,
					hideLabel:true,
					checked:true,
					labelSeparator:'',
					name: 'manDinner'+im,
					inputValue: '1',
					anchor: '95%'
				}]
			}]
		},{
			layout: 'form',
			border: false,
			items: [{
				xtype: 'radio',
				hideLabel: true,
				id: 'showFirst' + im,
				name: 'showFirst',
				inputValue: '1',
				labelSeparator: '',
				boxLabel: '显示首页价格'
			}]
		}]	
	});
	im++;
	manPricePanel.show();
	manPricePanel.doLayout();
}

function deleteManPrice(){
	if (im > 1) {
		var tempManFS = manPricePanel.findById(--im + 'manP');
		manPricePanel.remove(tempManFS);
	}
}
function addChildPrice(){
	childPricePanel.add({
		xtype: 'fieldset',
		title: '第' + ic + '种儿童价格体系',
		id: ic + 'childP',
		defaults: {
			width:230,
			anchor: '95%'
		},
		autoHeight: true,
		collapsed: false, //已经折叠否
		items: [{
			xtype: 'textfield',
			fieldLabel: '描述',
			name: 'childDesc' + ic,
			id: 'childDesc' + ic
		}, {
			xtype: 'textfield',
			fieldLabel: '门市价',
			value:0,
			name: 'prchild' + ic,
			id: 'prchild' + ic,
			vtype: 'posint'
		}, {
			xtype: 'textfield',
			fieldLabel: '同行价',
			value:0,
			name: 'prmarchild' + ic,
			id: 'prmarchild' + ic,
			vtype: 'posint'
		},{
			layout:'column',
			bodyStyle:'padding:10px 0 0 0',
        	border:false,
			items:[{
				columnWidth: .2,
				layout: 'form',
				labelWidth:20,
            	labelSeparator:'',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '占床位',
					id: 'childBed'+ic,
					hideLabel: true,
					labelSeparator: '',
					name: 'childBed'+ic,
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .2,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: seatName,
					id:'childSeat'+ic,
					hideLabel:true,
					checked:true,
					labelSeparator:'',
					name: 'childSeat'+ic,
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .2,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '含门票',
					id:'childTicket'+ic,
					hideLabel:true,
					labelSeparator:'',
					name: 'childTicket'+ic,
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .2,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '含用餐',
					id:'childDinner'+ic,
					hideLabel:true,
					labelSeparator:'',
					name: 'childDinner'+ic,
					inputValue: '1',
					anchor: '95%'
				}]
			},{
				columnWidth: .2,
				layout: 'form',
				border: false,
				items: [{
					xtype: 'checkbox',
					boxLabel: '适用接送价格',
					id:'childAssP'+ic,
					hideLabel:true,
					labelSeparator:'',
					name: 'childDinner'+ic,
					inputValue: '1',
					anchor: '95%'
				}]
			}]
		}]
	
	});
	ic++;
	childPricePanel.show();
	childPricePanel.doLayout();
}

function deleteChildPrice(){
	if (ic > 1) {
		var tempChildFS = childPricePanel.findById(--ic + 'childP');
		childPricePanel.remove(tempChildFS);
	}
}

function allRefresh(store){
	store.load({
		params: {
			start: 0,
			node:node,
			limit:pageSize
		}
	});
}

function queryOrder(){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('开班订单查询', '请选择需要查询的开班！');		
	}
	else {
		if (cbsm.getCount() == 1) {
				var teamId=selectRecord.get('teamId');
				orderInfAjax(teamId);
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条线路！');
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
			tempHtml = '<tr><td class="team_order_cls3" ><a href="#" style="cursor:pointer" onClick="showOrder(\'';
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

function showOrder(orderId,flag){
		window.open('../order/getPrintOrder/' + orderId+'.html');
}

function viewItem(){
	var	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (selectRecord) {
		if (cbsm.getCount() == 1) {
			teamId = selectRecord.get('teamId');
			window.open('../../team/teamContent/' + teamId);
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条开班打印！');
		}
	}
	else{
		Ext.MessageBox.alert('提示', '请选择一条开班打印！');
	}
}

function signUp(){
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('开班报名', '请选择要报名的开班！');
		
	}
	else {
		if (cbsm.getCount() == 1) {
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
				Ext.MessageBox.alert('提示', '已售完，请选择未满线路！');
			}
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条线路！');
		}
	}
}

function templateTeam(idName,start){
	id = 1,im=1,ic=1;
	selectRecord = objectGrid.getSelectionModel().getSelected();
	if (!selectRecord) {
		Ext.MessageBox.alert('修改操作', '请选择要模板开班的开班！');
	}
	else {
		if (cbsm.getCount() == 1) {
			tplWin = Ext.getCmp('tplW');
			comCreate(idName,selectRecord.get('teamId'),'tpl',start);
			
			if (!tplWin) {
				tplWin = new Ext.Window({
					title: '模板出团',
					id: 'tplW',
					closable: true,
					width: 620,
					height: 400,
					plain: true,
					modal: true,
					layout: 'fit',
					items: [fpanel]
				});
			}
			tplWin.show();
			tplWin.doLayout();
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条开班！');
		}
	}
}
