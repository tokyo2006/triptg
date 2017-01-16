/********** 解决日历控件显示异常 **********/
Ext.override(Ext.menu.DateMenu, {
	render : function() {
		Ext.menu.DateMenu.superclass.render.call(this);
		if (Ext.isGecko || Ext.isSafari || Ext.isChrome) {
			this.picker.el.dom.childNodes[0].style.width = '178px';
			this.picker.el.dom.style.width = '178px';
		}
	}
});

if(Ext.util.Format){	
	Ext.apply(Ext.util.Format,{		
		moneyRenderer: function(v){
			v = (Math.round((v - 0) * 100)) / 100;
			v = (v == Math.floor(v)) ? v + ".00" : ((v * 10 == Math.floor(v * 10)) ? v + "0" : v);
			v = String(v);
			var ps = v.split('.');
			var whole = ps[0];
			var sub = ps[1] ? '.' + ps[1] : '.00';
			var r = /(\d+)(\d{3})/;
			while (r.test(whole)) {
				whole = whole.replace(r, '$1' + ',' + '$2');
			}
			v = whole + sub;
			if (v.charAt(0) == '-') {
				return '-￥' + v.substr(1);
			}
			
			return "￥" + v;
		}
	});
	
	Ext.apply(Ext.util.Format,{
		ticketRenderer: function(v){
			switch(v){
				case 1:
					return '飞机';
					break;
				case 2:
					return '火车';
					break;
				case 3:
					return '汽车';
					break;
				default:
					return '轮船';
					break;
			}
		}
	});
	
	Ext.apply(Ext.util.Format,{
		hotelRenderer: function(v){
			switch(v){
				case '不入住酒店':
					return '不入住酒店';
					break;
				case '★★':
					return '二星级';
					break;
				case '★★★':
					return '三星级';
					break;
				case '★★★★':
					return '四星级';
					break;
				case '★★★★★':
					return '五星级';
					break;
				case '☆☆':
					return '准二星级';
					break;
				case '☆☆☆':
					return '准三星级';
					break;
				case '☆☆☆☆':
					return '准四星级';
					break;
				case '☆☆☆☆☆':
					return '准五星级';
					break;
				case '农家':
					return '农家';
					break;
				case '自选':
					return '自选';
					break;
				default:
					return '其他';
					break;
			}			
		}
	});
	
	Ext.apply(Ext.util.Format,{
		sexRenderer: function(v){
			switch(v){
				case 1:
					return '女';
					break;
				default:
					return '男';
					break;
			}
		}			
	});
	
	Ext.apply(Ext.util.Format,{
		gatherRenderer: function(v, params){
			if (params === 0) {
				return '<font color="#4acc5c">未填写<font/>';
			}
			else {
				switch (v > 0) {
					case (true):
						return '<font color="#ee928f">未收齐<font/>';
						break;
					default:
						return '<font color="#3764a0">已收齐<font/>';
						break;
				}
			}
		}
	});
	
	Ext.apply(Ext.util.Format,{
		statusRenderer: function(v){
			switch(v){
				case 1:
					return '新订单';
					break;
				case 2:
					return '已确认，待合同';
					break;
				case 3:
					return '已合同，待付款';
					break;
				case 4:
					return '已付款成功';
					break;
				case 5:
					return '订单完成';
					break;
				default:
					return '订单失效';
					break;
			}
		}
	});
	
	Ext.apply(Ext.util.Format,{
		statusRenderer2: function(v){
			switch(v){
				case 1:
					return '<font color="#ff0000">新订单<font/>';
					break;
				case 2:
					return '<font color="#3764a0">已审核<font/>';
					break;
				case 3:
					return '<font color="#4acc5c">已出团<font/>';
					break;
				case 4:
					return '已返回';
					break;
				default:
					return '<font color="#f97e06">已作废<font/>';
					break;
			}
		}
	});
	
	Ext.apply(Ext.util.Format,{
		shopperStatusRenderer: function(v){
			switch(v){
				case 0:
					return '<font color="#ee928f">未审核<font/>';
					break;
				default:
					return '已审核';
					break;
			}
		}
	});
	
	Ext.apply(Ext.util.Format,{
		busRenderer: function(v){
			switch(v){
//				case 0:
//					return '自驾游';
//					break;
				case 9:
					return '11座';
					break;
				case 17:
					return '19座';
					break;
				case 25:
					return '25座';
					break;
				case 33:
					return '33座';
					break;
				case 45:
					return '45座';
					break;
				default:
					return '53座';
					break;
			}
		}
	});
	
	Ext.apply(Ext.util.Format,{
		teamtypeRenderer: function(v){
			switch(v){
				case 0:
					return '所有';
					break;
				case 1:
					return '个人';
					break;
				default:
					return '团队';
					break;
			}
		}			
	});
	
	Ext.apply(Ext.util.Format,{
		longStringRenderer: function(v){
			if (v && (v !== '')) {
				if (v.length > 50) {
					v = v.replace(/[^\u4E00-\u9FA5]/g, '');
					return v.substr(0, 20) + '...';
				}
				else {
					v = v.replace(/[^\u4E00-\u9FA5]/g, '');
					return v;
				}
			}
			else {
				return '空';
			}
		}			
	});
}

if (Ext.form.VTypes) {
	Ext.apply(Ext.form.VTypes, {
		password: function(val, field){
			if (field.initialPassField) {
				var pwd = Ext.getCmp(field.initialPassField);
				return (val == pwd.getValue());
			}
			return true;
		},
		
		passwordText: '两次输入密码不匹配'
	});
	
	Ext.apply(Ext.form.VTypes, {
	  posint: function(val, field) {
	  	var posint = /^\d+$/;　　//正整数
	    return posint.test(val);
	  },
	  posintText: '请填写正整数'
	});
	
	Ext.apply(Ext.form.VTypes, {
	  telCheck: function(val, field) {
		var phone = /^((([ ]+)?[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12}))+)((,([ ]+)?([+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})))+)?$/;
	    return phone.test(val);
	  },
	  telCheckText: '请填正确的电话号码'
	});
	
	Ext.apply(Ext.form.VTypes, {
	  IPCheck: function(val, field) {
		var IP = /^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/;
	    return IP.test(val);
	  },
	  IPCheckText: '请填正确的IPV4地址:000.000.000.000'
	});
	
	Ext.apply(Ext.form.VTypes, {
		daterange: function(val, field){
			var date = field.parseDate(val);
			
			// We need to force the picker to update values to recaluate the disabled dates display
			var dispUpd = function(picker){
				var ad = picker.activeDate;
				picker.activeDate = null;
				picker.update(ad);
			};
			
			if (field.startDateField) {
				var sd = Ext.getCmp(field.startDateField);
				sd.maxValue = date;
				if (sd.menu && sd.menu.picker) {
					sd.menu.picker.maxDate = date;
					dispUpd(sd.menu.picker);
				}
			}
			else 
				if (field.endDateField) {
					var ed = Ext.getCmp(field.endDateField);
					ed.minValue = date;
					if (ed.menu && ed.menu.picker) {
						ed.menu.picker.minDate = date;
						dispUpd(ed.menu.picker);
					}
				}
			/* Always return true since we're only using this vtype
		 * to set the min/max allowed values (these are tested
		 * for after the vtype test)
		 */
			return true;
		}
	});
}
