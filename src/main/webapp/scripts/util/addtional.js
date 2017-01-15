
function addTab(id,tabTitle,targetUrl){
	centerbody = Ext.getCmp('centerBody');
	centerbody.add({
		id:id,
		title: tabTitle,
		iconCls:'tabs',
		frame:true,
		tabTip:tabTitle,
		autoLoad: {url: "tabFrame.jsp?url="+targetUrl, callback: this.initSearch, scope: this,  text: "Loading..."},
		closable:true
		}).show();
}

function updateTab(tabId,title,url){
	var tab = Ext.getCmp('centerBody').findById(tabId);
	if(!tab)
	{
		tab = addTab(tabId,title,url);
	}
		centerbody.setActiveTab(tab);
}