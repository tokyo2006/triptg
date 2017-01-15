
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	Ext.Ajax.request({
		url: 'getSingleCity.shtml',
		method: 'POST',
		params: {cityId:cityId},
		success: function(result, request){
			initAttrPanel(result);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载城市信息失败！');
			window.close();
		}
	});
});

function initAttrPanel(result){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.city;
		
		var attrPanel = new Ext.Panel({
			renderTo: 'attrPanel',
			title: '城市信息内容',
			layout: 'form',
			bodyStyle: 'padding:10px',
			autoScroll: true,
			tiltle: name,
			defaults: {
				xtype: 'textarea',
				width: 230,
				anchor: '95%'
			},
			items: [{
				xtype: 'textfield',
				hidden: true,
				fieldLabel: '城市Id',
				hideLabel: true,
				labelSeparator: '',
				value: list.cityId,
				name: 'cityId',
				id: 'cityId'
			}, {
				xtype: 'checkbox',
				fieldLabel: '是否城市',
				inputValue: '1',
				checked: ((parseInt(list.isZone) === 1) ? true : false),
				name: 'isZone',
				id: 'isZone'
			}, {
				xtype: 'checkbox',
				fieldLabel: '是否热点推荐',
				inputValue: '1',
				checked: ((parseInt(list.isZone) === 1) ? true : false),
				name: 'isTop',
				id: 'isTop'
			}, {
				fieldLabel: '行政常识',
				value: list.gloze,
				name: 'gloze',
				id: 'gloze'
			}, {
				fieldLabel: '简要介绍',
				value: list.synopsis,
				name: 'synopsis',
				id: 'synopsis'
			}]
		});
		
		InsertHTML();
	}	
}

