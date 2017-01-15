
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

var pageSize = 27;

//初始化首页表格
function initGrid(pageSize){
		
	var searchText=new Ext.form.TextField({
		id:'searchT',	
		width: 150,
		emptyText: '请输入搜索信息'
	});	
	var searchCheckbox=new Ext.form.Checkbox({
		inputValue: '1',
		id:'searchType',
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
			searchValue: 'title',
			searchType: 'searchType'
		},
		
		doLoad: function(start,searchValue,searchType){
			var o = {}, pn = this.paramNames;
			o[pn.start] = start;
			o[pn.limit] = this.pageSize;
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
					this.doLoad(0,searchValue,searchType);
					break;
				case "prev":
					this.doLoad(Math.max(0, this.cursor - this.pageSize),searchValue,searchType);
					break;
				case "next":
					this.doLoad((this.cursor + this.pageSize),searchValue,searchType);
					break;
				case "last":
					var total = store.getTotalCount();
					var extra = total % this.pageSize;
					var lastStart = extra ? (total - extra) : total - this.pageSize;
					this.doLoad(lastStart,searchValue,searchType);
					break;
				case "refresh":
					this.doLoad(this.cursor,'','');
					break;
			}
		}
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
            {name: 'strDate',type:'',dateFormat:'y-m-d',mapping:'strDate'}
        ]
    });	 
	 

/*===============================中间部分 普通panel===========================================*/

	 var objectStore = new Ext.data.GroupingStore({
            reader: objectReader,
			proxy: new Ext.data.HttpProxy({url: 'getAllLine.shtml',method:'POST'}),
			remoteSort: true,
            sortInfo:{field: 'strDate', direction: "DESC"},
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
            {id:'subTitle',header: "批发商",hideable:false, sortable: true,
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
			'-', 
			{
				text: '打印',
				tooltip: '打印行程信息',
				iconCls: 'printline',
				handler: function(){
				rowdblclick(objectGrid);
				}
			},		
			'->',
			searchCheckbox,searchText,
			{
				text:'行程搜索',
				handler:function(){
					search(searchCheckbox,searchText,objectStore,objectGrid.getBottomToolbar().cursor);
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
		searchType:searchType,
		title:query
	}});
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