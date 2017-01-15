
Ext.onReady(function(){
	//get All unit Name for comboBox.
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();	
	
	/*==============================全局变量t=============================================*/
	
	var objectGrid;
	var selectRecord;
	var cbsm;
	var objectStore;
	/*
      * 布局加载
      */
   new Ext.Viewport({
     layout: 'border',
     items: [initGrid(pageSize)]
      });	  
});

var pageSize = 20;

//初始化首页表格
function initGrid(pageSize){
	
	var searchBoxStore = new Ext.data.SimpleStore({
		fields:['returnValue','displayValue'],		
		proxy: new Ext.data.HttpProxy({
			url: 'getJobberInf.shtml',
			method: 'POST'
		})
	});
	
	var searchSelect = new Ext.form.ComboBox({
			store: searchBoxStore,
			id:'searchSelect',
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
			userId: 'userId',
			searchValue: 'searchValue'
		},
		
		doLoad: function(start,searchValue,userId){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
			o[pn.userId] = userId;
			o[pn.searchValue] = searchValue;
			if (this.fireEvent('beforechange', this, o) !== false) {
				this.store.load({
					params: o
				});
			}
		},
		
		onClick: function(which){
			var searchValue = Ext.getCmp('searchT').getValue();
			var userId = Ext.getCmp('searchSelect').getValue();
			var store = this.store;
			switch (which) {
				case "first":
					this.doLoad(0,searchValue,userId);
					break;
				case "prev":
					this.doLoad(Math.max(0, this.cursor - this.pageSize),searchValue,userId);
					break;
				case "next":
					this.doLoad((this.cursor + this.pageSize),searchValue,userId);
					break;
				case "last":
					var total = store.getTotalCount();
					var extra = total % this.pageSize;
					var lastStart = extra ? (total - extra) : total - this.pageSize;
					this.doLoad(lastStart,searchValue,userId);
					break;
				case "refresh":
					this.doLoad(this.cursor,'','');
					break;
			}
		}
	});
		
	var searchText=new Ext.form.TextField({
		id:'searchT',	
		width: 150,
		emptyText: '请输入行程名称信息'
	});	
 	 /*
	  * 第二数据解析器
	  */	 
   var objectReader = new Ext.data.JsonReader({
      	totalProperty: 'totalCount', 
		root: 'results', 
		successProperty:'success', 
		idProperty:'lineId',
        fields: [
            {name: 'lineId', type: 'string',mapping:'lineId'},
            {name: 'lineStar', type: 'int',mapping:'lineStar'},
            {name: 'title', type: 'string',mapping:'title'},
            {name: 'subTitle', type: 'string',mapping:'subTitle'},
            {name: 'feature', type: 'string',mapping:'feature'},
            {name: 'remark', type: 'string',mapping:'remark'},
			{name: 'delFlag', type: 'string',mapping:'delFlag'},
			{name: 'feeClude', type: 'string',mapping:'feeClude'},
			{name: 'feeUnclude', type: 'string',mapping:'feeUnclude'},
			{name: 'hit', type: 'int',mapping:'hit'},
			{name: 'purchase', type: 'string',mapping:'purchase'},
			{name: 'safe', type: 'string',mapping:'safe'},
			{name: 'selfBuy', type: 'string',mapping:'selfBuy'},
			{name: 'userId', type: 'string',mapping:'userId'},
			{name: 'writer', type: 'string',mapping:'writer'},
//			{name: 'strDate',type:'date',dateFormat:'y-m-d',mapping:'strDate'}
            {name: 'strDate',type:'',dateFormat:'y-m-d',mapping:'strDate'}
        ]
    });	 
	 

/*===============================中间部分 普通panel===========================================*/

	 objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllJobberLine.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'writer', direction: "ASC"},
            groupField:'strDate'
        });	
	 objectStore.load({params:{start:0,limit:pageSize}});
	
	 cbsm = new Ext.grid.CheckboxSelectionModel();

	 var rn = new Ext.grid.RowNumberer();	 
	 
     /*
      * 第二tab面板数据源，dataurl的请求带参数
      */
    objectGrid = new Ext.grid.GridPanel({
        ds: objectStore,
        columns: [
		     rn, //行号列 
		     cbsm, //CheckBox选择列, TMD有Ctrl和shift组合功能
            {id:'title',header: "行程名称", hideable:true, sortable: true, 
			dataIndex: 'title'},
            {id:'subTitle',header: "名称简写",hidden:true, hideable:false, sortable: true,
			dataIndex: 'subTitle'},
			{header: "行程填写者", hideable:true, sortable: true,
			dataIndex: 'writer'},
			{header: "点击率",hidden:true,  hideable:false, sortable: true,
			dataIndex: 'hit'},
			{header: "行程星级",hidden:true, hideable:false, sortable: true,
			dataIndex: 'lineStar'},
			{header: "lineId", hidden:true, hideable:false, sortable: true,
			dataIndex: 'lineId'},
			{header: "备注", hidden:true, hideable:false, sortable: true,
			dataIndex: 'remark'},
			{header: "delFlag", hidden:true, hideable:false, sortable: true,
			dataIndex: 'delFlag'},
			{header: "feeClude", hidden:true, hideable:false, sortable: true,
			dataIndex: 'feeClude'},
			{header: "feeUnclude", hidden:true, hideable:false, sortable: true,
			dataIndex: 'feeUnclude'},
			{header: "purchase", hidden:true, hideable:false,  sortable: true,
			dataIndex: 'purchase'},
			{header: "创建时间", hidden:false, hideable:false,sortable: true, //renderer: Ext.util.Format.dateRenderer('y年m月d日'),
			dataIndex: 'strDate'},
			{header: "行程特色",  hidden:true, hideable:false, sortable: true,
			dataIndex: 'feature'}
        ],
		sm: cbsm,
		selModel: new Ext.grid.RowSelectionModel({singleSelect:true}),//设置单行选中模式, 否则将无法显示数据   
		
		tbar:[
			{
				text: '刷新',
				tooltip: '刷新行程信息',
				iconCls: 'refresh',
				handler: function(){
					allRefresh(objectStore);
				}
			},
			'-',{
			text: '复制',
			tooltip: '复制行程信息',
			iconCls: 'copy',
			handler: function(){
				copyItem(objectGrid.getBottomToolbar().cursor);
			}
		}, '-', 
			{
				text: '打印',
				tooltip: '打印行程信息',
				iconCls: 'printline',
				handler: function(){
				rowdblclick(objectGrid);
				}
			},		
			'->',searchSelect,'-',
			searchText,
			{
				text:'行程搜索',
				handler:function(){
					search(searchText,objectStore,objectGrid.getBottomToolbar().cursor,searchSelect);
				}
			}
		],

        view: new Ext.grid.GroupingView({
			forceFit:true,
            showGroupName: false,
            enableNoGroups:false, // REQUIRED!
            hideGroupedColumn: true,
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
				fn:rowdblclick,
				scope:this
			}
		},

        frame:true,
		loadMask: true,
		autoWidth:true,
		region:'center',
		id:'totalobjectGrid',
		resizeTabs:true, // turn on tab resizing
		minTabWidth: 300,
		enableTabScroll:true,
		autoScroll: true,
        iconCls: 'icon-grid'
    });	
		
	return objectGrid;
}

function search(searchText,store,start,searchSelect){
	var query = searchText.getValue();
	var userId = searchSelect.getValue();
	store.load({params:{
		start: start,
		limit: pageSize,
		userId:userId,
		title:query
	}});
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
				var jsonData = "";
				for (var i = 0; i < m.length; i++) {
					var ss = m[i].get('lineId');
					if (i === 0) {
						jsonData = jsonData + ss;
					}
					else {
						jsonData = jsonData + "," + ss;
					}
				}
				copyAjax(start,jsonData);
			}
		});
	}
}

function copyAjax(start,jsonData){
	Ext.Ajax.request({
		url: 'copyLine.shtml',
		method: 'POST',
		params: {
			copyData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success===true)
			{
				Ext.MessageBox.alert('行程复制操作', '复制行程成功！');
			objectStore.load({
					params: {
						start: start,
						limit: pageSize
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
			limit:pageSize
		}
	});
}

function rowdblclick(grid){
	selectRecord = grid.getSelectionModel().getSelected();
	if (selectRecord) {
		if (cbsm.getCount() == 1) {
			lineId=selectRecord.get('lineId');
			window.open('printSingelLine/lineId='+lineId+'.html');
		}
		else {
			Ext.MessageBox.alert('提示', '请选择一条行程打印！');
		}
	}
	else{
		Ext.MessageBox.alert('提示', '请选择一条行程打印！');
	}
}