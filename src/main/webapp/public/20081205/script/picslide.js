var Slide=function(){return this.init.apply(this,arguments);};Slide.prototype=(function(){var play=function(){if(!this.autoPlay||this.playIntervalId){return ;}var me=this;(function f(){me.playIntervalId=setTimeout(function(){var t=me.picSelIndex+1;t=t>me.itemSum-1?0:t;me.setSel(t);f();},me.playInterval);})();};var stop=function(){if(!this.autoPlay){return ;}if(this.playIntervalId){clearTimeout(this.playIntervalId);this.playIntervalId=null;}};return{init:function(op){op=op||{};this.slideContainer=op.container||Dom.getElementsByClassName("slider",document.body)[0];this.pics=Dom.getArray(op.pics||Dom.getElementsByClassName("img",this.slideContainer));this.pages=Dom.getArray(op.pages||this.slideContainer.getElementsByTagName("li"));this.itemSum=this.pics.length;this.eventType=op.eventType||"click";this.autoPlay=op.autoPlay==false?false:true;this.playInterval=op.interval||3000;this.picSelIndex=0;this.playIntervalId=null;this.isrun=false;},initSlide:function(){this.pics.each(function(el){Dom.hide(el);});Dom.show(this.pics[this.picSelIndex]);},run:function(){if(this.pages.length<1){return ;}this.initSlide();var me=this;for(var i=0;i<this.itemSum;i++){with({ii:i}){BBEvent.observe(this.pages[i],this.eventType,function(){var change=function(){stop.call(me);me.setSel(ii);};var tempid=null;if("mouseover"==me.eventType){tempid=setTimeout(change,me.isrun?400:100);BBEvent.observe(me.pages[ii],"mouseout",function(){clearTimeout(tempid);});}else{change();}});}}this.pages[0].className="selected";play.call(this);BBEvent.observe(this.slideContainer,"mouseover",function(e,el){e=e||window.event;var relateEl=e.relatedTarget||e.fromElement||document.body;if(!Dom.contains(me.slideContainer,relateEl)){stop.call(me);}});BBEvent.observe(this.slideContainer,"mouseout",function(e,el){e=e||window.event;var relateEl=e.relatedTarget||e.toElement||document.body;if(!Dom.contains(me.slideContainer,relateEl)){play.call(me);}});},setSel:function(i){var sel=this.picSelIndex;if(sel==i){return ;}this.isrun=true;CustEvent.fireEvent(this,"picBeforeChange",i);this.pages[sel].className="";Dom.hide(this.pics[sel]);sel=this.picSelIndex=i;Dom.show(this.pics[sel]);CustEvent.fireEvent(this,"picChange",i);this.pages[sel].className="selected";this.isrun=false;}};})();var FadeSlide=function(A){this.$super(A);}.$extends(Slide);Object.extendJson(FadeSlide.prototype,{setSel:function(B){var D=this.picSelIndex;if(D==B){return ;}this.isrun=true;CustEvent.fireEvent(this,"picBeforeChange",B);Dom.setStyle(this.pics[D],"opacity","1");Dom.hide(this.pics[D]);this.pages[D].className="";D=this.picSelIndex=B;Dom.show(this.pics[D]);this.pages[D].className="selected";CustEvent.fireEvent(this,"picChange",B);var C=this;var A=new CssEffect(this.pics[D],400,{initialCss:{opacity:0.3},alterableCss:{opacity:[0.3,1]}});A.onsuspend=function(){Dom.setStyle(C.pics[B],"opacity","1");C.isrun=false;};A.play();}});var ScrollSlide=function(A){this.$super(A);}.$extends(Slide);Object.extendJson(ScrollSlide.prototype,{initSlide:function(){this.pics.each(function(B){Dom.setStyle(B,"display","block");});this.picContainer=Dom.parentNode(this.pics[0],"div");this.picContainer.scrollTop=0;var A=this;BBEvent.observe(window,"beforeunload",function(){A.pics.each(function(C,B){A.picSelIndex!=B&&Dom.setStyle(C,"display","none");});A.picContainer.scrollTop=0;});},setSel:function(C){var F=this.picSelIndex;if(F==C){return ;}this.isrun=true;this.pages[F].className="";var B=this.picContainer.offsetHeight*(F);var E=this.picContainer.offsetHeight*(C-F);F=this.picSelIndex=C;this.pages[F].className="selected";CustEvent.fireEvent(this,"picChange",C);var D=this;var A=new BBEffect($("test"),function(G){D.picContainer.scrollTop=parseInt(B+(-E*((t=G-1)*t*t*t)+E));},400);A.onsuspend=function(){D.isrun=false;};A.play();}});var PicSlide=function(A){if(!BBEffect){A.effect="";}switch(A.effect){case"fade":return new FadeSlide(A);case"scroll":return new ScrollSlide(A);default:return new Slide(A);}};