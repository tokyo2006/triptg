var pltsPop=null;
var pltsoffsetX = 10;   
var pltsoffsetY = 15;  
var pltsPopbg="#FFFFEE"; 
var pltsPopfg="#CCCCD4"; 
var pltsTitle="";
document.write('<div id=pltsTipLayer style="display: none;position: absolute; z-index:10001"></div>');


<!-- 
var cnstat_ua = navigator.userAgent
var cnstat_ps = navigator.productSub 
var cnstat_dom = (document.getElementById)? 1:0
var cnstat_ie4 = (document.all&&!cnstat_dom)? 1:0
var cnstat_ie5 = (document.all&&cnstat_dom)? 1:0
var cnstat_nn4 =(navigator.appName.toLowerCase() == "netscape" && parseInt(navigator.appVersion) == 4)
var cnstat_nn6 = (cnstat_dom&&!cnstat_ie5)? 1:0
var cnstat_sNav = (cnstat_nn4||cnstat_nn6||cnstat_ie4||cnstat_ie5)? 1:0
var cnstat_cssFilters = ((cnstat_ua.indexOf("MSIE 5.5")>=0||cnstat_ua.indexOf("MSIE 6")>=0)&&cnstat_ua.indexOf("Opera")<0)? 1:0
var yeoou_Style=[],Text=[],cnstat_Count=0,cnstat_sbw=0,cnstat_move=0,cnstat_hs="",cnstat_mx,cnstat_my,cnstat_scl,cnstat_sct,cnstat_ww,cnstat_wh,cnstat_obj,cnstat_sl,cnstat_st,cnstat_ih,cnstat_iw,cnstat_vl,cnstat_hl,cnstat_sv,cnstat_evlh,cnstat_evlw,cnstat_tbody
var cnstat_HideTip = "eval(cnstat_obj+cnstat_sv+cnstat_hl+';'+cnstat_obj+cnstat_sl+'=0;'+cnstat_obj+cnstat_st+'=-800')"
var cnstat_doc_root = ((cnstat_ie5&&cnstat_ua.indexOf("Opera")<0||cnstat_ie4)&&document.compatMode=="CSS1Compat")? "document.documentElement":"document.body"
var cnstat_PX = (cnstat_nn6)? "px" :"" //此处px没有改为cnstat_px


if(cnstat_sNav) {
     window.onresize = cnstat_ReloadTip
     document.onmousemove = cnstat_MoveTip
     if(cnstat_nn4) document.captureEvents(Event.MOUSEMOVE) 
}    
if(cnstat_nn4||cnstat_nn6) {
     cnstat_mx = "e.pageX"
     cnstat_my = "e.pageY"
     cnstat_scl = "window.pageXOffset"
     cnstat_sct = "window.pageYOffset"    
     if(cnstat_nn4) {
         cnstat_obj = "document.cnstat_TipLayer."
         cnstat_sl = "left"
         cnstat_st = "top"
         cnstat_ih = "clip.height"
         cnstat_iw = "clip.width"
         cnstat_vl = "'show'"
         cnstat_hl = "'hide'"
         cnstat_sv = "visibility="
     }
     else cnstat_obj = "document.getElementById('cnstat_TipLayer')."
} 
if(cnstat_ie4||cnstat_ie5) {
     cnstat_obj = "cnstat_TipLayer."
     cnstat_mx = "event.x"
     cnstat_my = "event.y"
     cnstat_scl = "eval(cnstat_doc_root).scrollLeft"
     cnstat_sct = "eval(cnstat_doc_root).scrollTop"
     if(cnstat_ie5) {
         cnstat_mx = cnstat_mx+"+"+cnstat_scl 
         cnstat_my = cnstat_my+"+"+cnstat_sct
     }
}
if(cnstat_ie4||cnstat_dom){
     cnstat_sl = "style.left"
     cnstat_st = "style.top"
     cnstat_ih = "offsetHeight"
     cnstat_iw = "offsetWidth"
     cnstat_vl = "'visible'"
     cnstat_hl = "'hidden'"
     cnstat_sv = "style.visibility="
}
if(cnstat_ie4||cnstat_ie5||cnstat_ps>=20020823) {
     cnstat_ww = "eval(cnstat_doc_root).clientWidth"
     cnstat_wh = "eval(cnstat_doc_root).clientHeight"
}     
else { 
     cnstat_ww = "window.innerWidth"
     cnstat_wh = "window.innerHeight"
     cnstat_evlh = eval(cnstat_wh)
     cnstat_evlw = eval(cnstat_ww)
     cnstat_sbw=15
}    

function cnstat_applyCssFilter(){
     if(cnstat_cssFilters&&FiltersEnabled) { 
         var cnstat_dx = " progid:DXImageTransform.Microsoft."
         cnstat_TipLayer.style.filter = "revealTrans()"+cnstat_dx+"Fade(Overlap=1.00 enabled=0)"+cnstat_dx+"Inset(enabled=0)"+cnstat_dx+"Iris(irisstyle=PLUS,motion=in enabled=0)"+cnstat_dx+"Iris(irisstyle=PLUS,motion=out enabled=0)"+cnstat_dx+"Iris(irisstyle=DIAMOND,motion=in enabled=0)"+cnstat_dx+"Iris(irisstyle=DIAMOND,motion=out enabled=0)"+cnstat_dx+"Iris(irisstyle=CROSS,motion=in enabled=0)"+cnstat_dx+"Iris(irisstyle=CROSS,motion=out enabled=0)"+cnstat_dx+"Iris(irisstyle=STAR,motion=in enabled=0)"+cnstat_dx+"Iris(irisstyle=STAR,motion=out enabled=0)"+cnstat_dx+"RadialWipe(wipestyle=CLOCK enabled=0)"+cnstat_dx+"RadialWipe(wipestyle=WEDGE enabled=0)"+cnstat_dx+"RadialWipe(wipestyle=RADIAL enabled=0)"+cnstat_dx+"Pixelate(MaxSquare=35,enabled=0)"+cnstat_dx+"Slide(slidestyle=HIDE,Bands=25 enabled=0)"+cnstat_dx+"Slide(slidestyle=PUSH,Bands=25 enabled=0)"+cnstat_dx+"Slide(slidestyle=SWAP,Bands=25 enabled=0)"+cnstat_dx+"Spiral(GridSizeX=16,GridSizeY=16 enabled=0)"+cnstat_dx+"Stretch(stretchstyle=HIDE enabled=0)"+cnstat_dx+"Stretch(stretchstyle=PUSH enabled=0)"+cnstat_dx+"Stretch(stretchstyle=SPIN enabled=0)"+cnstat_dx+"Wheel(spokes=16 enabled=0)"+cnstat_dx+"GradientWipe(GradientSize=1.00,wipestyle=0,motion=forward enabled=0)"+cnstat_dx+"GradientWipe(GradientSize=1.00,wipestyle=0,motion=reverse enabled=0)"+cnstat_dx+"GradientWipe(GradientSize=1.00,wipestyle=1,motion=forward enabled=0)"+cnstat_dx+"GradientWipe(GradientSize=1.00,wipestyle=1,motion=reverse enabled=0)"+cnstat_dx+"Zigzag(GridSizeX=8,GridSizeY=8 enabled=0)"+cnstat_dx+"Alpha(enabled=0)"+cnstat_dx+"Dropshadow(OffX=3,OffY=3,Positive=true,enabled=0)"+cnstat_dx+"Shadow(strength=3,direction=135,enabled=0)"
     }
}

function showPirce(cnstat_t,cnstat_c,cnstat_s) {
   //cnstat_c=cnstat_c.replace(/ /g,"<br>");
   if(cnstat_sNav) {
       if(cnstat_s.length<25) {
         var ErrorNotice = "DHTML TIP MESSAGE VERSION 1.2 ERROR NOTICE. "
         if(cnstat_t.length<2&&cnstat_s.length<25) alert(ErrorNotice+"It looks like you removed an entry or more from the Style Array and Text Array of this tip. Their should be 25 entries in every Style Array even though empty and 2 in every Text Array. You defined only "+cnstat_s.length+" entries in the Style Array and "+cnstat_t.length+" entry in the Text Array. This tip won'cnstat_t be viewed to avoid errors")
         else if(cnstat_t.length<2) alert(ErrorNotice+"It looks like you removed an entry or more from the Text Array of this tip. Their should be 2 entries in every Text Array. You defined only "+cnstat_t.length+" entry. This tip won'cnstat_t be viewed to avoid errors.")
         else if(cnstat_s.length<25) alert(ErrorNotice+"It looks like you removed an entry or more from the Style Array of this tip. Their should be 25 entries in every Style Array even though empty. You defined only "+cnstat_s.length+" entries. This tip won'cnstat_t be viewed to avoid errors.")
      }
       else {
         var cnstat_ab = "" ;var cnstat_ap = ""
         var cnstat_titCol = (cnstat_s[0])? "COLOR='"+cnstat_s[0]+"'" : ""
         var cnstat_txtCol = (cnstat_s[1])? "COLOR='"+cnstat_s[1]+"'" : ""
         var cnstat_titBgCol = (cnstat_s[2])? "BGCOLOR='"+cnstat_s[2]+"'" : ""
         var cnstat_txtBgCol = (cnstat_s[3])? "BGCOLOR='"+cnstat_s[3]+"'" : ""
         var cnstat_titBgImg = (cnstat_s[4])? "BACKGROUND='"+cnstat_s[4]+"'" : ""    
         var cnstat_txtBgImg = (cnstat_s[5])? "BACKGROUND='"+cnstat_s[5]+"'" : ""
         var cnstat_titTxtAli = (cnstat_s[6] && cnstat_s[6].toLowerCase()!="left")? "ALIGN='"+cnstat_s[6]+"'" : ""
         var cnstat_txtTxtAli = (cnstat_s[7] && cnstat_s[7].toLowerCase()!="left")? "ALIGN='"+cnstat_s[7]+"'" : ""   
         var add_height = (cnstat_s[15])? "HEIGHT='"+cnstat_s[15]+"'" : ""
         if(!cnstat_s[8])   cnstat_s[8] = "Verdana,Arial,Helvetica"
         if(!cnstat_s[9])   cnstat_s[9] = "Verdana,Arial,Helvetica"                    
         if(!cnstat_s[12]) cnstat_s[12] = 1
         if(!cnstat_s[13]) cnstat_s[13] = 1
         if(!cnstat_s[14]) cnstat_s[14] = ""
         if(!cnstat_s[16]) cnstat_s[16] = 0
         if(!cnstat_s[17]) cnstat_s[17] = 0
         if(!cnstat_s[18]) cnstat_s[18] = 10
         if(!cnstat_s[19]) cnstat_s[19] = 10
         cnstat_hs = cnstat_s[11].toLowerCase() 
         if(cnstat_ps==20001108){
         if(cnstat_s[2]) cnstat_ab="STYLE='border:"+cnstat_s[16]+"px solid"+" "+cnstat_s[2]+"'"
         cnstat_ap="STYLE='padding:"+cnstat_s[17]+"px "+cnstat_s[17]+"px "+cnstat_s[17]+"px "+cnstat_s[17]+"px'"}
         var cnstat_closeLink=(cnstat_hs=="sticky")? "<TD ALIGN='right'><FONT SIZE='"+cnstat_s[12]+"' FACE='"+cnstat_s[8]+"'><A HREF='javascript:void(0)' ONCLICK='cnstat_stickyhide()' STYLE='text-decoration:none;color:"+cnstat_s[0]+"'><B>Close</B></A></FONT></TD>":""
         var cnstat_title=(cnstat_t||cnstat_hs=="sticky")? "<TABLE WIDTH='100%' BORDER='0' CELLPADDING='0' CELLSPACING='0'><TR><TD height=20 "+cnstat_titTxtAli+" style='padding-left:3px;table-layout:fixed;word-break:break-all'><FONT FACE='"+cnstat_s[8]+"' "+cnstat_titCol+">"+cnstat_t+"</FONT></TD>"+cnstat_closeLink+"</TR></TABLE>" : ""
         var cnstat_txt="<TABLE "+cnstat_titBgImg+" "+cnstat_ab+" WIDTH='"+cnstat_s[14]+"' BORDER='0' CELLPADDING='"+cnstat_s[16]+"' CELLSPACING='0' "+cnstat_titBgCol+" ><TR><TD>"+cnstat_title+"<TABLE WIDTH='100%' "+add_height+" BORDER='0' CELLPADDING='"+cnstat_s[17]+"' CELLSPACING='0' "+cnstat_txtBgCol+" "+cnstat_txtBgImg+"><TR><TD "+cnstat_txtTxtAli+" "+cnstat_ap+" VALIGN='top' style='padding-top:5px;padding-left:3px;padding-right:3px;padding-bottom:3px;table-layout:fixed;word-break:break-all'><FONT   FACE='"+cnstat_s[9]+"' "+cnstat_txtCol +">"+cnstat_c+"</FONT></TD></TR></TABLE></TD></TR></TABLE>"
         if(cnstat_nn4) {
             with(eval(cnstat_obj+"document")) {
                 open()
                 write(cnstat_txt)
                 close()
             }
         }
         else eval(cnstat_obj+"innerHTML=cnstat_txt")
         cnstat_tbody = {
             Pos:cnstat_s[10].toLowerCase(), 
             Xpos:cnstat_s[18],
             Ypos:cnstat_s[19], 
             Transition:cnstat_s[20],
             Duration:cnstat_s[21], 
             Alpha:cnstat_s[22],
             ShadowType:cnstat_s[23].toLowerCase(),
             ShadowColor:cnstat_s[24],
             Width:parseInt(eval(cnstat_obj+cnstat_iw)+3+cnstat_sbw)
         }
         if(cnstat_ie4) { 
             cnstat_TipLayer.style.width = cnstat_s[14]
              cnstat_tbody.Width = cnstat_s[14]
         }
         cnstat_Count=0    
         cnstat_move=1
       }
   }
}

function cnstat_MoveTip(e) {
     if(cnstat_move) {
         var cnstat_X,cnstat_Y,cnstat_MouseX = eval(cnstat_mx),cnstat_MouseY = eval(cnstat_my); cnstat_tbody.Height = parseInt(eval(cnstat_obj+cnstat_ih)+3)
         cnstat_tbody.wiw = parseInt(eval(cnstat_ww+"+"+cnstat_scl)); cnstat_tbody.wih = parseInt(eval(cnstat_wh+"+"+cnstat_sct))
         switch(cnstat_tbody.Pos) {
             case "left" : cnstat_X=cnstat_MouseX-cnstat_tbody.Width-cnstat_tbody.Xpos; cnstat_Y=cnstat_MouseY+cnstat_tbody.Ypos; break
             case "center": cnstat_X=cnstat_MouseX-(cnstat_tbody.Width/2); cnstat_Y=cnstat_MouseY+cnstat_tbody.Ypos; break
             case "float": cnstat_X=cnstat_tbody.Xpos+eval(cnstat_scl); cnstat_Y=cnstat_tbody.Ypos+eval(cnstat_sct); break    
             case "fixed": cnstat_X=cnstat_tbody.Xpos; cnstat_Y=cnstat_tbody.Ypos; break        
             default: cnstat_X=cnstat_MouseX+cnstat_tbody.Xpos; cnstat_Y=cnstat_MouseY+cnstat_tbody.Ypos
         }

         if(cnstat_tbody.wiw<cnstat_tbody.Width+cnstat_X) cnstat_X = cnstat_tbody.wiw-cnstat_tbody.Width
         if(cnstat_tbody.wih<cnstat_tbody.Height+cnstat_Y+cnstat_sbw) {
             if(cnstat_tbody.Pos=="float"||cnstat_tbody.Pos=="fixed") cnstat_Y = cnstat_tbody.wih-cnstat_tbody.Height-cnstat_sbw
             else cnstat_Y = cnstat_MouseY-cnstat_tbody.Height
         }
         if(cnstat_X<0) cnstat_X=0 
         eval(cnstat_obj+cnstat_sl+"=cnstat_X+cnstat_PX;"+cnstat_obj+cnstat_st+"=cnstat_Y+cnstat_PX")
         cnstat_ViewTip()
     }
}

function cnstat_ViewTip() {
       cnstat_Count++
     if(cnstat_Count == 1) {
         if(cnstat_cssFilters&&FiltersEnabled) {    
             for(Index=28; Index<31; Index++) { cnstat_TipLayer.filters[Index].enabled = 0 }
             for(cnstat_s=0; cnstat_s<28; cnstat_s++) { if(cnstat_TipLayer.filters[cnstat_s].status == 2) cnstat_TipLayer.filters[cnstat_s].stop() }
             if(cnstat_tbody.Transition == 51) cnstat_tbody.Transition = parseInt(Math.random()*50)
             var cnstat_applyTrans = (cnstat_tbody.Transition>-1&&cnstat_tbody.Transition<24&&cnstat_tbody.Duration>0)? 1:0
             var cnstat_advFilters = (cnstat_tbody.Transition>23&&cnstat_tbody.Transition<51&&cnstat_tbody.Duration>0)? 1:0
             var cnstat_which = (cnstat_applyTrans)?0:(cnstat_advFilters)? cnstat_tbody.Transition-23:0 
             if(cnstat_tbody.Alpha>0&&cnstat_tbody.Alpha<100) {
                   cnstat_TipLayer.filters[28].enabled = 1
                   cnstat_TipLayer.filters[28].opacity = cnstat_tbody.Alpha
             }
             if(cnstat_tbody.ShadowColor&&cnstat_tbody.ShadowType == "simple") {
                   cnstat_TipLayer.filters[29].enabled = 1
                   cnstat_TipLayer.filters[29].color = cnstat_tbody.ShadowColor
             }
             else if(cnstat_tbody.ShadowColor&&cnstat_tbody.ShadowType == "complex") {
                   cnstat_TipLayer.filters[30].enabled = 1
                   cnstat_TipLayer.filters[30].color = cnstat_tbody.ShadowColor
             }
             if(cnstat_applyTrans||cnstat_advFilters) {
                 eval(cnstat_obj+cnstat_sv+cnstat_hl)
                   if(cnstat_applyTrans) cnstat_TipLayer.filters[0].transition = cnstat_tbody.Transition
                   cnstat_TipLayer.filters[cnstat_which].duration = cnstat_tbody.Duration 
                   cnstat_TipLayer.filters[cnstat_which].apply()
             }
         }
          eval(cnstat_obj+cnstat_sv+cnstat_vl)
         if(cnstat_cssFilters&&FiltersEnabled&&(cnstat_applyTrans||cnstat_advFilters)) cnstat_TipLayer.filters[cnstat_which].play()
         if(cnstat_hs == "sticky") cnstat_move=0
       }
}

function cnstat_stickyhide() {
     eval(cnstat_HideTip)
}

function cnstat_ReloadTip() {
      if(cnstat_nn4&&(cnstat_evlw!=eval(cnstat_ww)||cnstat_evlh!=eval(cnstat_wh))) location.reload()
      else if(cnstat_hs == "sticky") eval(cnstat_HideTip)
}

function hidePrice() {
     if(cnstat_sNav) {
         if(cnstat_hs!="keep") {
             cnstat_move=0; 
             if(cnstat_hs!="sticky") eval(cnstat_HideTip)
         }    
     } 
}

document.write('<DIV id=cnstat_TipLayer style="Z-INDEX: 1000; VISIBILITY: hidden; POSITION: absolute; TOP: -1000px"></DIV>');
var FiltersEnabled = 0;
yeoou_Style[1]=["black","black","#CCCCD4","#F2F3F7","","","","","","","left","","","",0,"",2,2,10,10,51,0.5,75,"simple","orange"];
yeoou_Style[2]=["white","black","black","white","","","right","","Impact","cursive","center","",3,5,400,150,5,20,10,0,50,1,80,"complex","gray"];
cnstat_applyCssFilter();
//-->