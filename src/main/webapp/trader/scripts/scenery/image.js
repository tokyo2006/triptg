
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '../../resources/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	
	checkBrowse();
	initView();
	var containPanel;
});

var delArray = new Array();
var picNum =1;
var fileUpWin;
var browseType;

function checkBrowse(){
	var Sys = {};
	var ua = navigator.userAgent.toLowerCase();
	var s;
	(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
	
	//以下进行测试
	if (Sys.ie) 
//		document.write('IE: ' + Sys.ie);
		browseType='IE';
	if (Sys.firefox) 
//		document.write('Firefox: ' + Sys.firefox);
		browseType='Firefox';
	if (Sys.chrome) 
//		document.write('Chrome: ' + Sys.chrome);
		browseType='Chrome';
	if (Sys.opera) 
//		document.write('Opera: ' + Sys.opera);
		browseType='Opera';
	if (Sys.safari) 
//		document.write('Safari: ' + Sys.safari);
		browseType='Safari';
}

function initView(){
	
	var barButton=new Ext.Button({
			text:'完成',
			tooltip: '完成修改图片信息',
			iconCls: 'option',
			handler:finish
		});
	containPanel = new Ext.Panel({
		frame: true,
		title:'图片信息表',
		id:'containP',
		renderTo:'containDiv',
//		height: 150,
		width:800,
		tbar: [{
				text: '刷新',
				tooltip: '刷新图片信息',
				iconCls: 'refresh',
				handler: allRefresh
			},'-',{
			text: '添加',
			tooltip: '添加图片信息',
			iconCls: 'add',
			handler: addItem
		},
		 '-', {
			text: '删除',
			tooltip: '删除图片信息',
			iconCls: 'remove',
			handler: delItem
		}],
		
		html:'',
//		items:[{}],
		buttons: [{
			text: '完成',
			handler: finish
		}]
//		bbar:[barButton]
	});
	
	Ext.Ajax.request({
		url: 'getSingleScenery.shtml',
		method: 'POST',
		params: {sceneryId:sceneryId},
		success: function(result, request){
			var strList = doJSON(result.responseText);
			if (strList.success === true) {
				document.title=strList.sc.name+'图片修改！'
			}
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载信息失败！');
			window.close();
		}
	});
	
	Ext.Ajax.request({
		url: 'getSenceryPic.shtml',
		method: 'POST',
		params: {sceneryId:sceneryId},
		success: function(result, request){
			initUpdate(result);
		},
		failure: function(result, request){
			Ext.MessageBox.alert('加载失败','加载图片信息失败！');
			window.close();
		}
	});
}

function initUpdate(result){
	var strList = doJSON(result.responseText);
	if (strList.success === true) {
		var list = strList.picList;
		var tempColPanel,colNum,tempPic,p=0,barCheckbox,emptyPanel;
		
		if(parseInt(list.length%3)===0){
			colNum=list.length/3;
		}
		else{
			colNum=list.length/3+1;
		}
		for (var i = 0; i < colNum; i++) {
			tempColPanel = new Ext.Panel({
				border: false,
				bodyStyle:'margin:10px;',
				id: 'colP' + i,
				layout: 'column'
			});
			for (var j = 0; j < 3; j++) {
				p = j + (i * 3);
				if (p < list.length) {
					
					barCheckbox = new Ext.form.Checkbox({
						id: 'checkBox' + p,
						name: 'delData',
						boxLabel: '选择',
						hideLabel: true,
						labelSeparator: '',
						stateId:list[p].pictureId,
						listeners:{
							check:{
								fn:delCheck,
								scope:this
							}
						}
					});
					
					tempPic = new Ext.Panel({
						title: list[p].name,
						border: true,
						id: 'pic' + p,
						columnWidth: .3,
						bbar: [barCheckbox, '-', '-', {
							text: '修改',
							id:list[p].pictureId,
							stateId:p,
							tooltip: '更改该图片',
							iconCls: 'option',
							handler: updateItem
						}],
//						html:''
						html: '<img id=\"img'+p+'\" src=\"../../'+list[p].breviaryUrl+'\" alt=\"'+list[p].name+'\" />'
					});
					
//					var elementBody = document.createElement('img');
//					elementBody.id = 'img' + p;
//					elementBody.src = '../../' + list[p].breviaryUrl;
//					elementBody.alt = list[p].name;
//					tempPic.html = elementBody.outerHTML;
					if (j !== 0) {
						emptyPanel = new Ext.Panel({
							border: false,
							columnWidth: .03,
							html: ''
						});
						tempColPanel.add(emptyPanel);
					}
					tempColPanel.add(tempPic);
					picNum = p;
				}
			}
			containPanel.add(tempColPanel);
		}
		containPanel.show();
		containPanel.doLayout();
	}	
}

function updateItem(item){
	fileUp(1,item);
}

function delCheck(item){
	var ln = delArray.length;
	if(item.checked){
		delArray[ln]=item.stateId;
	}
	else{
		for(var i=0;i<ln;i++){
			if(item.stateId===delArray[i]){
				while(i<(ln-1)){
					delArray[i]=delArray[i+1];
				}
				delArray.length-=1;
				break;
			}
		}
	}
}

function finish(){
	window.close();
}

function addItem(){
	fileUp(0,'');
}

function delItem(){
	if (delArray.length > 0) {
		var jsonData = delArray.join(',');
		Ext.MessageBox.confirm('确认景点删除', '你确认景点删除选择的数据吗？', function(btn){
			if (btn == "yes") {
				delAjax(jsonData);
			}
		});
	}
	else{
		Ext.Msg.alert('删除操作','请选择需要删除的图片！');
	}
}

function delAjax(jsonData){
	Ext.Ajax.request({
		url: 'delPic.shtml',
		method: 'POST',
		params: {
			delData: jsonData
		},
		success: function(result, request){
			var returnJosn = doJSON(result.responseText); 
			if(returnJosn.success==true)
			{
				Ext.MessageBox.alert('图片信息删除操作', '删除图片信息成功！');
				window.location.reload();
			}
			else
			{
				Ext.MessageBox.alert('删除操作', returnJosn.msg);
			}
		},
		failure: function(result, request){
			Ext.MessageBox.alert('图片信息删除操作', '删除图片信息失败！');
		}
	});
}

function allRefresh(){
	window.location.reload();
}

function fileUp(type,item){
	
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
			fieldLabel: '文件名称',
			name: 'name',
			id: 'name'
		},{
			fieldLabel: '文件路径',
			name: 'upload',
			inputType: 'file',
			id: 'fileUpId'
		}],
		
		buttons: [{
			text: '保存',
			handler:function(){
				fileUpSave(fpanel2,type,item);
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
					height: 150,
					plain: true,
					modal: true,
					layout: 'fit',
					items: [fpanel2]
				});
			}
			fileUpWin.show();
			fileUpWin.doLayout();
}

function fileUpSave(fpanel,type,item){
	var urlPost;
	var paramsPost;
	var pictureId='';
	if(type!==0){
		pictureId=item.id;
	}
	if (fileUpWin) {
		urlPost = 'addPic.shtml';
		paramsPost = {
			sceneryId: sceneryId,
			pictureId: pictureId
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
								window.location.reload();
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

/**
 * 窗口关闭
 */	
function fileUpCancel(){
	fileUpWin = Ext.getCmp('fileUpW');
	
	if (fileUpWin) {
		fileUpWin.close();
		fileUpWin='';
	}
}