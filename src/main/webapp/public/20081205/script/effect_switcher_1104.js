if (!window.Switcher)
  var Switcher = new Object();


Switcher = {
	autoSwitchTimer: null,
	CurTab:1,
	autoSwitchTimeIntval:3000,
	showTab: function(args) {
		
		if (!args.CurElm) { return false ;}
		if (!args.Total) { return false ;}
		if (!args.TabupStyle) { return false ;}
		if (!args.ListupStyle) { return false ;}
		if (!args.Symbol) { return false ;}
		for (j=1;j<=args.Total;j++ ) {
			if (j==args.CurElm)	{
				//alert(args.TabupStyle) ;
				$(args.Symbol+"_tab_"+args.CurElm).addClassName(args.TabupStyle) ;
				$(args.Symbol+"_list_"+args.CurElm).addClassName(args.ListupStyle) ;
				
			}
			else {
				$(args.Symbol+"_tab_"+j).removeClassName(args.TabupStyle) ;
				$(args.Symbol+"_list_"+j).removeClassName(args.ListupStyle) ;
			}
		}
		if (args.autoSwitch) {
			var argsstr =	'Total:'+args.Total+','+
							'TabupStyle:"'+args.TabupStyle+'",'+
							'ListupStyle:"'+args.ListupStyle+'",'+
							'Symbol:"'+args.Symbol+'",'+
							'autoSwitch:true';
			$(args.Symbol+"_list_"+args.CurElm).onmouseover = new Function("Switcher.pauseSwitch();") ;  
			$(args.Symbol+"_list_"+args.CurElm).onmouseout = new Function("Switcher.goonSwitch({"+argsstr+"});") ;
			this.autoSwitch(args) ;
		}
	},
	initTab: function(args) {
		//if (!args.CurElm) { return false ;}
		if (!args.Total) { return false ;}
		if (!args.TabupStyle) { return false ;}
		if (!args.ListupStyle) { return false ;}
		if (!args.Symbol) { return false ;}
		if (args.TimeIntval){
			this.autoSwitchTimeIntval = args.TimeIntval ;
		}
		this.CurTab = 1 ;
		for (i=1;i<=args.Total;i++ ) {
			
			var MyCurElm = i ;
			FunctionStr = "Switcher.showTab({	"+
									"CurElm:'"+MyCurElm+"',"+
									"Total:'"+args.Total+"',"+
									"TabupStyle:'"+args.TabupStyle+"',"+
									"ListupStyle:'"+args.ListupStyle+"',"+
									"Symbol:'"+args.Symbol+"'} );" ;
			$(args.Symbol+"_tab_"+i).onmouseover = new Function(FunctionStr) ;
		}
		if (args.autoSwitch) {
			this.autoSwitch(args) ;
		}
	},
	autoSwitch: function(args) {
		this.CurTab ++ ;
		if (this.CurTab > args.Total) {
			this.CurTab = 1 ;
		}
		var argsstr =			'Total:'+args.Total+','+
								'CurElm:'+this.CurTab+','+
								'TabupStyle:"'+args.TabupStyle+'",'+
								'ListupStyle:"'+args.ListupStyle+'",'+
								'Symbol:"'+args.Symbol+'",'+
								'autoSwitch:true';
		//alert(argsstr) ;
		this.autoSwitchTimer = setTimeout("Switcher.showTab({"+argsstr+"});", this.autoSwitchTimeIntval);
	},
	pauseSwitch: function(args) {
		clearTimeout(this.autoSwitchTimer);
	},
	goonSwitch: function(args) {
		clearTimeout(this.autoSwitchTimer);
		this.autoSwitch(args) ;
	}
}


if (!window.MyFlashSwitch)
  var FlashSwitch = new Object();




FlashSwitch = {
	AutoFlashTimer: null ,
	CurScreen: 0,
	MaxScreen:0,
	TimeIntval:3000,
	SwitchImage:{},
	SwitchData:{},
	SwitchTitleContainers:{},
	PageNavContainer:{},
	init: function(args) {
		var SwitchContainer = $(args.Container) ;
		if (args.TimeIntval) {
			this.TimeIntval = args.TimeIntval ;
		}
		this.SwitchData = args.Data ;
		for (i=0;i<this.SwitchData.length ;i++ ) {
			var MyImgContainer = document.createElement("a") ;
			MyImgContainer.href = this.SwitchData[i]["link"] ;
			MyImgContainer.title = this.SwitchData[i]["title"] ;
			MyImgContainer.target = '_blank';
			MyImgContainer.blockid = this.SwitchData[i]["blockid"] ;
			MyImgContainer.innerHTML = '<img src="'+this.SwitchData[i]["img"]+'">' ;
			SwitchContainer.appendChild(MyImgContainer) ;
		}
		SwitchContainer.onmouseover = function() {FlashSwitch.pauseSwitch();}
		SwitchContainer.onmouseout = function() {FlashSwitch.goonSwitch();}
		this.SwitchImage = SwitchContainer.getElementsByTagName("img") ;
		this.SwitchTitleContainers = args.Titles ;
		this.MaxScreen = this.SwitchImage.length ;
		this.PageNavContainer = $(args.PageNav) ;
		this.goSwitch(this.CurScreen) ;
		this.AutoFlashTimer = setTimeout('FlashSwitch.goAutoSwitch();', this.TimeIntval);
		
	},
	goSwitch: function(screen) {
		if (screen >= this.MaxScreen) {
			screen = 0 ;
		}
		//alert(screen) ;
		var ImageTmp = this.SwitchImage ;
		for (i=0;i<this.MaxScreen;i++) {
			ImageTmp[i].style.display = "none" ;
		}
		ImageTmp[screen].style.display = "block" ;
		this.showSwitchNav(screen) ;
		if (this.SwitchTitleContainers) {
			this.showSwitchTitles(screen) ;
		}
		this.CurScreen = screen ;

	},
	goAutoSwitch: function() {
		this.goSwitch(this.CurScreen+1);
		this.AutoFlashTimer = setTimeout('FlashSwitch.goAutoSwitch();', this.TimeIntval);
	},
	showSwitchNav: function(screen) {
		this.PageNavContainer.innerHTML ="" ;
		var PageNavInnerHTML = "" ;
		for (i=1;i<=this.MaxScreen ;i++ ) {
			if ((i - 1) == screen ) {
				PageNavInnerHTML += "<a href='javascript://' class='currA'>"+i+"</a>" ;
			}else {
				PageNavInnerHTML += "<a href='javascript://' onclick='FlashSwitch.goManSwitch("+(i-1)+")'>"+i+"</a>" ;
			}
		}
		this.PageNavContainer.onmouseover = function() {FlashSwitch.pauseSwitch();}
		this.PageNavContainer.onmouseout = function() {FlashSwitch.goonSwitch();}
		this.PageNavContainer.innerHTML =PageNavInnerHTML ;
	},
	showSwitchTitles: function(screen) {
		for (i=0;i<this.SwitchTitleContainers.length ;i++ ) {
			$("SwitchTitle_"+this.SwitchTitleContainers[i]).onmouseover = function() {FlashSwitch.pauseSwitch();}
			$("SwitchTitle_"+this.SwitchTitleContainers[i]).onmouseout = function() {FlashSwitch.goonSwitch();}
			$("SwitchTitle_"+this.SwitchTitleContainers[i]).innerHTML = '<a href="'+this.SwitchData[screen]['link']+'" title="'+
				this.SwitchData[screen][this.SwitchTitleContainers[i]]+'" target="_blank">'+
				this.SwitchData[screen][this.SwitchTitleContainers[i]]+
				'</a>' ;
		}
	},
	pauseSwitch: function() {
		clearTimeout(this.AutoFlashTimer);
	},
	goonSwitch: function() {
		clearTimeout(this.AutoFlashTimer);
		this.AutoFlashTimer = setTimeout('FlashSwitch.goAutoSwitch();', this.TimeIntval);
	},
	goManSwitch: function(screen) {
		clearTimeout(this.AutoFlashTimer);
		this.CurScreen = screen - 1 ;
		this.goAutoSwitch();
	}
}


if (!window.XLEffect)
  var XLEffect = new Object();

XLEffect.randomStyles = function(args) {
	if (!args.Container) { return false ;}
	if (!args.Styles) { return false ;}
	if (!args.Elm) { return false ;}
	var MyElms = $(args.Container).getElementsByTagName(args.Elm) ;
	for (i=0;i<MyElms.length ;i++ ) {
		var myrand  = parseInt(Math.random() * (args.Styles.length + 1) + 1 ) - 1 ;
		if (myrand > 0) {
			MyElms[i].className = args.Styles[(myrand-1)] ;
		}
	}
}
