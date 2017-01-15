

Ext.onReady(function(){
		Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
		Ext.QuickTips.init();
	});
	var addWin, updWin,fileUpWin;

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

function fileUpSave(fpanel,type){
	var urlPost;
	var paramsPost;
		if (fileUpWin) {
			urlPost = 'uploadPic.shtml';
//			paramsPost = {
//				oldUrl: oldUrl
//			};
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
								initMap(returnJosn.mapUrl);
//								document.getElementById('mapId').src=returnJosn.mapUrl;
//								alert(document.getElementById('mapId').src);
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

function initMap(mapUrl){
	var mapPanel = Ext.getCmp('mapP');
	var mapUrlPanel = Ext.getCmp('mapUrlP');
	if (!mapPanel) {
		mapPanel = new Ext.Panel({
			renderTo: 'mapDiv',
			id: 'mapP',
			height: 250,
			width: 350,
			html: '无图片！'
		});
	}	
	
	if(mapUrl){
		var elementBody = document.createElement('img');
		elementBody.id='mapId';
		elementBody.src='../../'+mapUrl;
		elementBody.alt='地图图片';
		elementBody.height=250;
		elementBody.width=350;
		mapPanel.body.update(elementBody.outerHTML);
	}
	
	if (!mapUrlPanel) {
		mapUrlPanel = new Ext.Panel({
			renderTo: 'mapUrlDiv',
			id: 'mapUrlP',
			items:[{
				xtype: 'textfield',
				anchor: '95%',
				fieldLabel: '地图路径',
				name: 'mapUrl',
				value:mapUrl,
				id: 'mapUrl'
			}]
		});
	}
	else{
		mapUrlPanel.findById('mapUrl').setValue(mapUrl);
	}
	
	mapPanel.show();
	mapUrlPanel.show();
//	mapPanel.doLayout();
}
