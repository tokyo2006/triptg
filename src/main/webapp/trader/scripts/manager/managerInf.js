

//var companyInf;
var jobTabPanel, JobFpanel;
var jobberWin;
var jobIdName;

function showManagerInf(){
	jobberWin = Ext.getCmp('jobberW');
	
	if (!jobberWin) {
		comJobCreate();
		
		Ext.Ajax.request({
			url: 'manager/getSelfManagerInfo.shtml',
			method: 'POST',
			success: function(result, request){
				initUpdateJob(result, JobFpanel);
			},
			failure: function(result, request){
				Ext.MessageBox.alert('加载失败！');
				window.close();
			}
		});
		
		jobberWin = new Ext.Window({
			title: '显示用户信息',
			id: 'jobberW',
			closable: true,
			width: 500,
			height: 340,
			modal: true,
			layout: 'fit',
			items: [JobFpanel]
		});
	}
	jobberWin.show();
}

function initUpdateJob(result, JobFpanel){
	var JobComList = doJSON(result.responseText);
	
	if (JobComList.success == true) {
		var JobList = JobComList.jobber;
	
	JobFpanel.findById(jobIdName[0]).setValue(JobList.mobile);
	JobFpanel.findById(jobIdName[1]).setValue(JobList.phone);
	JobFpanel.findById(jobIdName[2]).setValue(JobList.email);
	JobFpanel.findById(jobIdName[3]).setValue(JobList.qq);
	JobFpanel.findById(jobIdName[4]).setValue(JobList.msn);
	}
	else {
		Ext.MessageBox.alert('加载失败', JobComList.msg);
	}	
}

function comJobCreate(){
	jobIdName = new Array('mobile','phone','email','qq','msn','oldPassword','password','repassword');
	
	var JobAttrPanel;
		
	JobAttrPanel = new Ext.Panel({
		id: 'JobAttrPanel',
		layout: 'form',
		bodyStyle: 'padding:10px',
		autoScroll: true,
		height: 250,
		defaults: {
			width: 230,
			xtype: 'textfield',
			allowBlank: false,
			anchor: '95%'
		},
		
		items: [{
			fieldLabel: '手机信息',
			name: jobIdName[0],
			id: jobIdName[0],
			maxLength:96
		},{
			fieldLabel: '电话信息',
			name: jobIdName[1],
			id: jobIdName[1],
			maxLength:96
		},{
			fieldLabel: '电子邮件',
			name: jobIdName[2],
			id: jobIdName[2],
			vtype:'email',
			maxLength:96
		},{
			fieldLabel: 'QQ号码',
			name: jobIdName[3],
			id: jobIdName[3],
			maxLength:96
		},{
			fieldLabel: 'MSN号码',
			name: jobIdName[4],
			id: jobIdName[4],
			vtype:'email',
			maxLength:96
		},{
			fieldLabel: '原密码',
			name: jobIdName[5],
			id: jobIdName[5],
			allowBlank: true,
			inputType: 'password',
			maxLength:96
		},{
			fieldLabel: '新密码',
			name: jobIdName[6],
			id: jobIdName[6],
			allowBlank: true,
			inputType: 'password',
			maxLength:96
		},{
			fieldLabel: '重新输入密码',
			name: jobIdName[7],
			id: jobIdName[7],
			allowBlank: true,
			inputType: 'password',
			vtype: 'password',
        	initialPassField: 'password',
			maxLength:96
		}]
	});
	
	JobFpanel = new Ext.FormPanel({
		region: 'center',
		bodyStyle: 'padding:5px',
		collapsible: true,
		items: [JobAttrPanel],
		
		buttons: [{
			text: '保存',
			handler: function(){
				jobSave(JobFpanel);
			}
		},{
			text: '取消',
			handler: function(){
				jobCancel();
			}
		}]
	});
}

function jobSave(JobFpanel){
	var urlPost;
	var paramsPost;
	
 	if (jobberWin) {
			urlPost = 'manager/updateSelfInfo.shtml';
			paramsPost = {
			};
		}
		else {
			Ext.Msg.alert('信息', '提交窗口错误参数!');
			window.close();
		}
	
	//提交数据
	if (JobFpanel.form.isValid()) {
		this.disabled = true;
		
		JobFpanel.getForm().submit({
			url: urlPost,
			method: 'post',
			params: paramsPost,
			waitTitle: '请稍候',
			waitMsg: '信息正提交，请等待......',
			failure: function(JobFpanel, action){
				Ext.MessageBox.alert('保存失败', '保存未成功！');
				this.disabled = false;
			},
			success: function(JobFpanel, action){				
				var returnJosn = action.result;
				if (returnJosn.success === true) {
					Ext.MessageBox.alert('保存成功', '信息保存成功');					
					if (jobberWin) {
						jobberWin.close();
					}
					jobberWin = '';
				}
				else {
					Ext.MessageBox.alert('保存失败', returnJosn.msg);
					this.disabled = false;
				}				
			}
		});
	}
	else {
		Ext.Msg.alert('信息', '请填写完成再提交!');
	}
}

//function showWrong(result){
//	var JobComList = doJSON(result.responseText);
//	var JobList = JobComList.message;
//	alert(JobList);
//}

/**
 * 窗口关闭
 */	
function jobCancel(){
	jobberWin = Ext.getCmp('jobberW');
	
	if (jobberWin) {
		jobberWin.close();
		jobberWin='';
	}
}