
var BB = {
	VERSION: "core-mini.2008.11.10",
	JSPATH: (function(){
		var A = document.getElementsByTagName("script");
		return A[A.length - 1].src.replace(/\/[^\/]+$/, "/") + "../";
	})(),
	Browser: (function(){
		var ua = window.navigator.userAgent.toLowerCase();
		var b = {
			platform: window.navigator.platform,
			msie: /msie/.test(ua) && !/opera/.test(ua),
			opera: /opera/.test(ua),
			safari: /webkit/.test(ua) && !/chrome/.test(ua),
			firefox: /firefox/.test(ua),
			chrome: /chrome/.test(ua)
		};
		var vMark = "";
		for (var i in b) {
			if (b[i]) {
				vMark = i;
			}
		}
		if (b.safari) {
			vMark = "version";
		}
		b.version = (ua.match(eval("/(?:" + vMark + ")[\\/: ]([\\d.]+)/")) || [])[1];
		b.ie = b.msie;
		b.ie6 = b.msie && parseInt(b.version) == 6;
		try {
			b.maxthon = b.msie && !!external.max_version;
		} 
		catch (e) {
			b.maxthon = false;
		}
		return b;
	})()
};
if (BB.Browser.ie) {
	try {
		document.execCommand("BackgroundImageCache", false, true);
	} 
	catch (e) {
	}
}
window.Browser = BB.Browser;
Function.prototype.$ban = function(A, C, B, F){
	F = F || 1000;
	var E = this;
	var D = 0;
	return function(){
		var H = 0;
		switch (D) {
			case 0:
				D = 1;
				if (A) {
					A.apply(this, arguments);
				}
				H = E.apply(this, arguments);
				setTimeout(function(){
					D = 0;
					if (B) {
						B.apply(this, arguments);
					}
				}, F);
				break;
			case 1:
				if (C) {
					H = C.apply(this, arguments);
				}
				break;
		}
		return H;
	};
};
Function.prototype.$bind = function(B){
	var C = this;
	var A = function(){
		C.apply(B, arguments);
	};
	A.$unbind = function(){
		return C;
	};
	return A;
};
Function.prototype.$extends = function(D){
	var C = this;
	var B = function(){
		var E = this;
		this.$super = function(){
			return D.apply(E, arguments);
		};
		var F = C.apply(this, arguments);
		this.constructor = C;
		this.$class = B;
		this.$super = null;
		return F;
	};
	B.$superClass = D;
	var A = function(){
	};
	A.prototype = D.prototype;
	B.prototype = new A;
	return B;
};
Function.K = function(){
};
Object.extendJson = function(B, D, A){
	(A != null) || (A = true);
	for (var C in D) {
		if (A || typeof(B[C]) == "undefined") {
			B[C] = D[C];
		}
	}
	return B;
};
Object.keys = function(C){
	var A = [];
	for (var B in C) {
		A.push(B);
	}
	return A;
};
Object.values = function(C){
	var A = [];
	for (var B in C) {
		A.push(C[B]);
	}
	return A;
};
var G = $ = function(A){
	if ("string" == typeof(A)) {
		return document.getElementById(A);
	}
	else {
		return A;
	}
};
Object.extendJson(Array.prototype, {
	any: function(C){
		C = C || Function.K;
		var A = Array.prototype.slice.call(arguments, 1);
		for (var B = 0; B < this.length; B++) {
			if (C.apply(this, [this[B], B].concat(A))) {
				return true;
			}
		}
		return false;
	},
	each: function(E){
		E = E || Function.K;
		var A = [];
		var B = Array.prototype.slice.call(arguments, 1);
		for (var D = 0; D < this.length; D++) {
			var C = E.apply(this, [this[D], D].concat(B));
			if (C != null) {
				A.push(C);
			}
		}
		return A;
	},
	all: function(C){
		C = C || Function.K;
		var A = Array.prototype.slice.call(arguments, 1);
		for (var B = 0; B < this.length; B++) {
			if (!C.apply(this, [this[B], B].concat(A))) {
				return false;
			}
		}
		return true;
	},
	indexOf: function(C){
		var A = -1;
		for (var B = 0; B < this.length; B++) {
			if (this[B] === C) {
				A = B;
				break;
			}
		}
		return A;
	},
	contains: function(A){
		return (this.indexOf(A) >= 0);
	},
	insertAt: function(A, B){
		return this.splice(A, 0, B);
	},
	removeAt: function(A){
		return this.splice(A, 1);
	},
	remove: function(B){
		var A = this.indexOf(B);
		A >= 0 && this.removeAt(A);
		return A;
	}
}, false);
Object.toArray = Array.toArray = function(D){
	if (!D) {
		return [];
	}
	if (D.toArray) {
		return D.toArray();
	}
	else {
		var B = [];
		for (var A = 0, C = D.length; A < C; A++) {
			B.push(D[A]);
		}
		return B;
	}
};
Array.map = function(C, B){
	var D = {};
	for (var E = 0, A = C.length; E < A; E++) {
		D[C[E]] = B[E];
	}
	return D;
};
Date.prototype.format = function(B){
	B = B || "yyyy-MM-dd";
	var C = {
		"M+": this.getMonth() + 1,
		"d+": this.getDate(),
		"h+": this.getHours(),
		"m+": this.getMinutes(),
		"s+": this.getSeconds(),
		"q+": Math.floor((this.getMonth() + 3) / 3),
		S: this.getMilliseconds()
	};
	if (/(y+)/.test(B)) {
		B = B.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for (var A in C) {
		if (new RegExp("(" + A + ")").test(B)) {
			B = B.replace(RegExp.$1, RegExp.$1.length == 1 ? C[A] : ("00" + C[A]).substr(("" + C[A]).length));
		}
	}
	return B;
};
Object.extendJson(String.prototype, {
	trim: function(){
		return this.replace(/(^[\s\t\xa0\u3000]+)|([\u3000\xa0\s\t]+$)/g, "");
	},
	format: function(){
		if (arguments.length == 0) {
			return this;
		}
		for (var B = this, A = 0; A < arguments.length; A++) {
			B = B.replace(new RegExp("\\{" + A + "\\}", "g"), arguments[A]);
		}
		return B;
	},
	camelize: function(){
		return this.replace(/\-(\w)/ig, function(B, A){
			return A.toUpperCase();
		});
	}
}, false);
window.Node || (window.Node = {});
var Dom = {
	getArray: function(D){
		D = $(D);
		if (!D) {
			return [];
		}
		if (D.constructor == Array) {
			return D;
		}
		if ((D.length != undefined && Dom.isElement(D)) || Dom.isElement(D)) {
			return [D];
		}
		var B = [];
		for (var C = 0, A = D.length; C < A; C++) {
			B.push(D[C]);
		}
		return B;
	},
	isElement: function(A){
		return !!(A && A.nodeType == 1);
	},
	outerHTML: function(A){
		A = $(A);
		if (!Dom.isElement(A)) {
			throw new Error("Arguments el is not a Element");
		}
		return A.outerHTML ? A.outerHTML : document.createElement("div").appendChild(A.cloneNode(true)).parentNode.innerHTML;
	},
	getStyle: function(C, A){
		C = $(C);
		if (!Dom.isElement(C)) {
			throw new Error("Arguments is not a Element");
		}
		var D = C.style[A];
		if (!D) {
			if (document.defaultView && document.defaultView.getComputedStyle) {
				var B = document.defaultView.getComputedStyle(C, null);
				D = B ? B.getPropertyValue(A) : null;
			}
			else {
				if (C.currentStyle) {
					D = C.currentStyle[A.camelize()];
				}
			}
		}
		if ((D == "auto") &&["width","height"] .contains(A) && (Dom.getStyle(C, "display") != "none")) {
			D = C["offset" + A.capitalize()] + "px";
		}
		if (window.opera &&["left","top","right","bottom"] .contains(A)) {
			if (Dom.getStyle(C, "position") == "static") {
				D = "auto";
			}
		}
		if (A == "opacity") {
			if (D) {
				return parseInt(D);
			}
			if (D = (Dom.getStyle(C, "filter") || "").match(/alpha\(opacity=(.*)\)/)) {
				if (D[1]) {
					return parseInt(D[1]) / 100;
				}
			}
			return 1;
		}
		return D == "auto" ? null : D;
	},
	borderWidth: function(A){
		A = $(A);
		return [parseInt(Dom.getStyle(A, "border-top-width"), 10) || 0, parseInt(Dom.getStyle(A, "border-right-width"), 10) || 0, parseInt(Dom.getStyle(A, "border-bottom-width"), 10) || 0, parseInt(Dom.getStyle(A, "border-left-width"), 10) || 0];
	},
	paddingWidth: function(A){
		A = $(A);
		return [parseInt(Dom.getStyle(A, "padding-top"), 10) || 0, parseInt(Dom.getStyle(A, "padding-right"), 10) || 0, parseInt(Dom.getStyle(A, "padding-bottom"), 10) || 0, parseInt(Dom.getStyle(A, "padding-left"), 10) || 0];
	},
	marginWidth: function(A){
		A = $(A);
		return [parseInt(Dom.getStyle(A, "margin-top"), 10) || 0, parseInt(Dom.getStyle(A, "margin-right"), 10) || 0, parseInt(Dom.getStyle(A, "margin-bottom"), 10) || 0, parseInt(Dom.getStyle(A, "margin-left"), 10) || 0];
	},
	setStyle: function(A, C, B){
		if (typeof C == "string") {
			C = Array.map([C], [B]);
		}
		Dom.getArray(A).each(function(D){
			if (!Dom.isElement(D)) {
				return;
			}
			for (var E in C) {
				if ("opacity" == E && !!window.ActiveXObject) {
					D.style.filter = "alpha(opacity=" + (C[E] * 100) + ")";
				}
				else {
					D.style[E.camelize()] = C[E];
				}
			}
		});
	},
	setFullscreen: function(D, F){
		var E = Dom.getDocRect(F);
		var A, H, B, C;
		A = H = 0;
		B = E.scrollWidth;
		C = E.scrollHeight;
		Dom.getArray(D).each(function(I){
			Dom.setRect(I, A, H, B, C);
		});
	},
	setCenter: function(D, B, C){
		var E = Dom.getDocRect();
		var A = parseInt((E.width - B) / 2) + E.scrollX;
		var F = parseInt((E.height - C) / 2) + E.scrollY;
		if (A <= E.scrollX) {
			A = E.scrollX;
		}
		if (F <= E.scrollY) {
			F = E.scrollY;
		}
		Dom.getArray(D).each(function(H){
			Dom.setRect(H, A, F);
		});
	},
	setXY: function(C, B, D, A){
		B = parseInt(B, 10);
		D = parseInt(D, 10);
		Dom.getArray(C).each(function(H){
			if (!Dom.isElement(H)) {
				return;
			}
			var E = Dom.getXY(H);
			if (!E) {
				return;
			}
			var F = Dom.getStyle(H, "display");
			var J = Dom.getStyle(H, "position");
			Dom.setStyle(H, "display", "block");
			var I = [parseInt(Dom.getStyle(H, "left"), 10), parseInt(Dom.getStyle(H, "top"), 10)];
			if (isNaN(I[0])) {
				I[0] = (J == "relative") ? 0 : H.offsetLeft;
			}
			if (isNaN(I[1])) {
				I[1] = (J == "relative") ? 0 : H.offsetTop;
			}
			if (!isNaN(B)) {
				H.style.left = B + "px";
			}
			if (!isNaN(D)) {
				H.style.top = D + "px";
			}
			Dom.setStyle(H, "display", F);
		});
	},
	setSize: function(C, A, B){
		A = parseInt(A, 10);
		B = parseInt(B, 10);
		if (isNaN(A) && isNaN(B)) {
			return;
		}
		Dom.getArray(C).each(function(E){
			if (!E) {
				return;
			}
			var D = Dom.getStyle(E, "display");
			Dom.setStyle(E, {
				display: "block"
			});
			if (!isNaN(A)) {
				Dom.setStyle(E, {
					width: A + "px"
				});
			}
			if (!isNaN(B)) {
				Dom.setStyle(E, {
					height: B + "px"
				});
			}
			Dom.setStyle(E, {
				display: D
			});
		});
	},
	setRect: function(D, A, E, B, C){
		A = parseInt(A, 10);
		E = parseInt(E, 10);
		B = parseInt(B, 10);
		C = parseInt(C, 10);
		if (!isNaN(A) || !isNaN(E)) {
			Dom.setXY(D, A, E);
		}
		if (!isNaN(B) || !isNaN(C)) {
			Dom.setSize(D, B, C);
		}
	},
	createElement: function(B, E, C){
		if (typeof E == "string") {
			E = Array.map([E], [C]);
		}
		if (BB.Browser.ie && !!E.name) {
			B = "<" + B + ' name="' + E.name + '">';
		}
		var A = document.createElement(B);
		for (var D in E) {
			A[D] = E[D];
		}
		return A;
	},
	appendElement: function(A, B){
		B = $(B);
		A = $(A);
		Dom.getArray(B).each(function(C){
			A.appendChild(C);
		});
	},
	nextSibling: function(A){
		A = $(A);
		while (A && (A = A.nextSibling)) {
			if (Dom.isElement(A)) {
				return A;
			}
		}
		return null;
	},
	previousSibling: function(A){
		A = $(A);
		while (A && (A = A.previousSibling)) {
			if (Dom.isElement(A)) {
				return A;
			}
		}
		return null;
	},
	getElementsByClassName: function(D, C){
		C = $(C) || document;
		var B = C.getElementsByTagName("*");
		var A = [];
		B = Dom.getArray(B);
		B.each(function(E){
			if (Dom.hasClassName(E, D)) {
				A.push(E);
			}
		});
		return A;
	},
	contains: function(A, B){
		return A.contains ? A != B && A.contains(B) : !!(A.compareDocumentPosition(B) & 16);
	},
	getDocRect: function(I){
		var I = I || window.document;
		var B = function(J){
			return parseInt(J) || 0;
		};
		var H = window.pageXOffset || Math.max(B(I.documentElement.scrollLeft), B(I.body.scrollLeft));
		var F = window.pageYOffset || Math.max(B(I.documentElement.scrollTop), 0);
		var E = Math.max(B(I.documentElement.clientWidth), 0);
		var A = Math.max(B(I.documentElement.clientHeight), 0);
		var D = Math.max(B(I.documentElement.scrollHeight), B(I.body.offsetHeight));
		var C = Math.max(B(I.documentElement.scrollWidth), B(I.body.offsetWidth));
		if ((!I.compatMode || I.compatMode == "CSS1Compat") && !/opera/i.test(window.navigator.userAgent) && I.documentElement && I.documentElement.clientHeight) {
			A = I.documentElement.clientHeight;
			widht = I.documentElement.clientWidth;
		}
		else {
			if (I.body && I.body.clientHeight) {
				A = I.body.clientHeight;
				E = I.body.clientWidth;
			}
			else {
				if (wnd.innerWidth && wnd.innerHeight && I.width) {
					A = wnd.innerHeight;
					E = wnd.innerWidth;
					if (I.height > A) {
						A -= 16;
					}
					if (I.width > E) {
						E -= 16;
					}
				}
			}
		}
		if (/webkit/i.test(window.navigator.userAgent)) {
			D = Math.max(D, B(I.body.scrollHeight));
		}
		D = A > D ? A : D;
		C = E > C ? E : C;
		return {
			scrollX: H,
			scrollY: F,
			width: E,
			height: A,
			scrollHeight: D,
			scrollWidth: C
		};
	},
	getXY: (function(){
		if (document.documentElement.getBoundingClientRect && !!window.ActiveXObject) {
			return function(C){
				var E = C.getBoundingClientRect();
				var D = Dom.getDocRect(C.ownerDocument);
				var A = E.left - 2 + D.scrollX, I = E.top - 2 + D.scrollY;
				try {
					var F = C.ownerDocument.parentWindow.frameElement || null;
					if (F) {
						var H = 2 - (F.frameBorder || 1) * 2;
						A += H;
						I += H;
					}
				} 
				catch (B) {
				}
				return [A, I];
			};
		}
		else {
			return function(E){
				var F = [E.offsetLeft, E.offsetTop];
				var B = E.offsetParent;
				var D = {
					HYPHEN: /(-[a-z])/i,
					ROOT_TAG: /^body|html$/i
				};
				var C = /safari/i.test(window.navigator.userAgent);
				var A = (C && Dom.getStyle(E, "position") == "absolute" && E.offsetParent == E.ownerDocument.body);
				if (B != E) {
					while (B) {
						F[0] += B.offsetLeft;
						F[1] += B.offsetTop;
						if (!A && C && Dom.getStyle(B, "position") == "absolute") {
							A = true;
						}
						B = B.offsetParent;
					}
				}
				if (A) {
					F[0] -= E.ownerDocument.body.offsetLeft;
					F[1] -= E.ownerDocument.body.offsetTop;
				}
				B = E.parentNode;
				while (B.tagName && !D.ROOT_TAG.test(B.tagName)) {
					if (B.scrollTop || B.scrollLeft) {
						if (!D.OP_SCROLL.test(Dom.getStyle(B, "display"))) {
							if (!isOpera || Dom.getStyle(B, "overflow") !== "visible") {
								F[0] -= B.scrollLeft;
								F[1] -= B.scrollTop;
							}
						}
					}
					B = B.parentNode;
				}
				return F;
			};
		}
	})(),
	parentNode: function(B, A){
		B = $(B);
		A = A.toLowerCase();
		if (!A) {
			return B.parentNode;
		}
		while (B && (B = B.parentNode)) {
			if (A == (B.tagName.toLowerCase())) {
				return B;
			}
		}
		return null;
	},
	getFirstChild: function(A){
		var B = A.firstChild;
		return Dom.isElement(B) ? B : Dom.nextSibling(B);
	},
	getAbsolutePosition: function(D){
		D = $(D);
		var E = Dom.getXY(D);
		var A = E[0];
		var F = E[1];
		var B = D.offsetWidth;
		var C = D.offsetHeight;
		return {
			width: B,
			height: C,
			left: A,
			top: F,
			bottom: F + C,
			right: A + B
		};
	},
	getAbsoluteRect: function(A){
		return Dom.getAbsolutePosition(A);
	},
	rectContains: function(B, A){
		return (B.left <= A.left && B.right >= A.right && B.top <= A.top && B.bottom >= A.bottom);
	},
	rectIntersect: function(F, E){
		var C = Math.max(F.top, E.top);
		var D = Math.min(F.right, E.right);
		var A = Math.min(F.bottom, E.bottom);
		var B = Math.max(F.left, E.left);
		if (A >= C && D >= B) {
			return new Math.Rectangle(C, D, A, B);
		}
		else {
			return null;
		}
	},
	clearPosition: function(A){
		Dom.getArray(A).each(function(B){
			B.style.top = B.style.left = B.style.bottom = B.style.right = "";
		});
	},
	recursivelyCollect: function(A, C){
		A = $(A);
		var B = [];
		while (A = A[C]) {
			if (Dom.isElement(A)) {
				B.push(A);
			}
		}
		return B;
	},
	pluckWhiteNode: function(B){
		var A = [];
		Dom.getArray(B).each(function(C){
			if (Dom.isElement(C)) {
				A.push(C);
			}
		});
		return A;
	},
	insertAdjacentHTML: function(A, D, C){
		if (A.insertAdjacentHTML) {
			A.insertAdjacentHTML(D, C);
		}
		else {
			var E;
			var B = A.ownerDocument.createRange();
			switch (String(D).toLowerCase()) {
				case "beforebegin":
					B.setStartBefore(A);
					E = B.createContextualFragment(C);
					A.parentNode.insertBefore(E, A);
					break;
				case "afterbegin":
					B.selectNodeContents(A);
					B.collapse(true);
					E = B.createContextualFragment(C);
					A.insertBefore(E, A.firstChild);
					break;
				case "beforeend":
					B.selectNodeContents(A);
					B.collapse(false);
					E = B.createContextualFragment(C);
					A.appendChild(E);
					break;
				case "afterend":
					B.setStartAfter(A);
					E = B.createContextualFragment(C);
					A.parentNode.insertBefore(E, A.nextSibling);
					break;
			}
		}
	},
	show: function(A, B){
		Dom.getArray(A).each(function(C){
			C.style.display = B || "";
		});
	},
	hide: function(A){
		Dom.getArray(A).each(function(B){
			B.style.display = "none";
		});
	},
	isVisible: function(A){
		return $(A).style.display != "none";
	},
	removeNode: function(A){
		Dom.getArray(A).each(function(B){
			if (B && B.parentNode) {
				B.parentNode.removeChild(B);
			}
		});
	},
	hasClassName: function(A, B){
		return new RegExp("(?:^|\\s)" + B + "(?:\\s|$)", "i").test(A.className);
	},
	addClassName: function(A, B){
		Dom.getArray(A).each(function(C){
			if (!C) {
				return;
			}
			if (Dom.hasClassName(C, B)) {
				return C;
			}
			C.className = (C.className + " " + B).trim();
		});
	},
	removeClassName: function(A, B){
		Dom.getArray(A).each(function(C){
			if (!Dom.hasClassName(C, B)) {
				return C;
			}
			C.className = C.className.replace(new RegExp("(?:\\s|^)" + B + "(?:\\s|$)", "i"), " ").trim();
		});
	},
	replaceClassName: function(C, B, A){
		Dom.getArray(C).each(function(D){
			if (B.trim() != "") {
				Dom.removeClassName(D, B);
			}
			if (A.trim() != "") {
				Dom.addClassName(D, A);
			}
		});
	},
	formURIEncode: function(I, J){
		var F = I.elements;
		var C = "," + (J || "") + ",";
		var K = [];
		for (var H = 0; H < F.length; H++) {
			var D = F[H];
			var B = D.name;
			if (D.disabled || !B || C.indexOf("," + B + ",") > -1) {
				continue;
			}
			switch (D.type) {
				case "text":
				case "hidden":
				case "password":
				case "textarea":
					K.push(B + "=" + encodeURIComponent(D.value));
					break;
				case "radio":
				case "checkbox":
					if (D.checked) {
						K.push(B + "=" + encodeURIComponent(D.value));
					}
					break;
				case "select-one":
					if (D.selectedIndex > -1) {
						K.push(B + "=" + encodeURIComponent(D.value));
					}
					break;
				case "select-multiple":
					var A = D.options;
					for (var E = 0; E < A.length; E++) {
						if (A[E].selected) {
							K.push(B + "=" + encodeURIComponent(A[E].value));
						}
					}
					break;
			}
		}
		return K.join("&");
	}
};
Math.Rectangle = function(B, C, D, A){
	this.top = C;
	this.right = D;
	this.bottom = A;
	this.left = B;
	return this;
};
Math.Rectangle.prototype = {
	contains: function(A){
		return (A.left >= this.left && A.right <= this.right && A.top >= this.top && A.bottom <= this.bottom);
	},
	intersect: function(E){
		var C = Math.max(this.top, E.top);
		var D = Math.min(this.right, E.right);
		var A = Math.min(this.bottom, E.bottom);
		var B = Math.max(this.left, E.left);
		if (A >= C && D >= B) {
			return new Math.Rectangle(C, D, A, B);
		}
		else {
			return null;
		}
	},
	getRegion: function(E){
		var F = Dom.getXY(E);
		var C = F[1];
		var B = F[0];
		var A = E.offsetWidth + B;
		var D = E.offsetHeight + C;
		return new Dom.Region(B, C, A, D);
	}
};
if (!document.DOMLoaded) {
	document.DOMLoaded = function(C){
		var D, B = false;
		function A(){
			if (!B) {
				B = true;
				if (D) {
					clearInterval(D);
				}
				if (C) {
					C();
				}
			}
		}
		if (document.addEventListener) {
			if (/webkit/i.test(window.navigator.userAgent)) {
				D = window.setInterval(function(){
					if (/loaded|complete/.test(document.readyState)) {
						A();
					}
				}, 10);
				BBEvent.observe(window, "load", A);
			}
			else {
				document.addEventListener("DOMContentLoaded", A, false);
			}
		}
		else {
			D = window.setInterval(function(){
				try {
					document.body.doScroll("left");
					A();
				} 
				catch (E) {
				}
			}, 10);
		}
	};
}
if (!window.BBEvent) {
	var BBEvent = {};
}
BBEvent.target = function(A){
	A = A || window.event;
	return A.target || A.srcElement;
};
BBEvent.isLeftClick = function(A){
	A = A || window.event;
	return (((A.which) && (A.which == 1)) || ((A.button) && (A.button == 1)));
};
BBEvent.pageX = function(A){
	A = A || window.event;
	return A.pageX || (A.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft));
};
BBEvent.pageY = function(A){
	A = A || window.event;
	return A.pageY || (A.clientY + (document.documentElement.scrollTop || document.body.scrollTop));
};
BBEvent.pagePosition = function(A){
	A = A || window.event;
	return {
		x: BBEvent.pageX(A),
		y: BBEvent.pageY(A)
	};
};
BBEvent.layerX = function(A){
	A = A || window.event;
	return A.layerX || A.offsetX;
};
BBEvent.layerY = function(A){
	A = A || window.event;
	return A.layerY || A.offsetY;
};
BBEvent.layerPosition = function(A){
	A = A || window.event;
	return {
		x: BBEvent.layerX(A),
		y: BBEvent.layerY(A)
	};
};
BBEvent.preventDefault = function(A){
	A = A || window.event;
	if (A.preventDefault) {
		A.preventDefault();
	}
	else {
		A.returnValue = false;
	}
};
BBEvent.stopPropagation = function(A){
	A = A || window.event;
	if (A.stopPropagation) {
		A.stopPropagation();
	}
	else {
		A.cancelBubble = true;
	}
};
BBEvent.observe = function(D, C, B, E, A){
	C = C.replace(/^on/g, "");
	var D = $(D);
	A = A || false;
	if (E) {
		B = B.$ban(null, function(F){
			BBEvent.preventDefault(F);
		}, null, E);
	}
	if (C == "keypress" && (Browser.gecko || Browser.safari)) {
		C = "keydown";
	}
	if (D.addEventListener) {
		D.addEventListener(C, B, A);
	}
	else {
		if (D.attachEvent) {
			D.attachEvent("on" + C, B);
		}
	}
};
BBEvent.stopObserving = function(D, C, B, A){
	C = C.replace(/^on/g, "");
	var D = $(D);
	A = A || false;
	if (C == "keypress" && (Browser.gecko || Browser.safari)) {
		C = "keydown";
	}
	if (D.removeEventListener) {
		D.removeEventListener(C, B, A);
	}
	else {
		if (D.detachEvent) {
			D.detachEvent("on" + C, B);
		}
	}
};
BBEvent.fireEvent = function(B, C){
	C = C.replace(/^on/g, "");
	B = $(B);
	if (B.fireEvent) {
		B.fireEvent("on" + C);
	}
	else {
		if (C == "keypress" && (Browser.gecko || Browser.safari)) {
			C = "keydown";
		}
		if (C.indexOf("mouse") > -1 || ",click,dblclick".indexOf("," + C) > -1) {
			var A = B.ownerDocument.createEvent("MouseEvents");
			A.initMouseEvent(C, true, true, B.ownerDocument.defaultView, 1, 0, 0, 0, 0, false, false, false, false, 0, null);
		}
		else {
			var A = B.ownerDocument.createEvent("Events");
			A.initEvent(C, true, true, B.ownerDocument.defaultView);
		}
		B.dispatchEvent(A);
	}
};
if (!window.CustEvent) {
	var CustEvent = {};
}
CustEvent._srcObjs = [];
CustEvent._registeredEvents = [];
CustEvent.observe = function(D, F, C){
	F = F.toLowerCase().replace(/^on/g, "");
	var A = CustEvent._srcObjs.indexOf(D);
	if (A > -1) {
		var B = CustEvent._registeredEvents[A];
		var E = B[F];
		if (E) {
			E.remove(C);
			E.push(C);
		}
		else {
			B[F] = [C];
		}
	}
	else {
		CustEvent._srcObjs.push(D);
		var B = {};
		B[F] = [C];
		CustEvent._registeredEvents.push(B);
	}
};
CustEvent.stopObserving = function(F, H, C){
	H = H.toLowerCase().replace(/^on/g, "");
	var A = CustEvent._srcObjs.indexOf(F);
	if (A < 0) {
		return;
	}
	var B = CustEvent._registeredEvents[A];
	if (B[H]) {
		if (C) {
			B[H].remove(C);
		}
		else {
			B[H].length = 0;
		}
	}
	var E = false;
	for (var D in B) {
		if (B[D] && B[D].length > 0) {
			E = true;
			break;
		}
	}
	if (!E) {
		CustEvent._srcObjs.removeAt(A);
		CustEvent._registeredEvents.removeAt(A);
	}
};
CustEvent.fireEvent = function(F, H){
	var D = Array.toArray(arguments);
	D.splice(0, 2);
	D.push(H);
	H = H.toLowerCase().replace(/^on/g, "");
	if (F["on" + H]) {
		F["on" + H].apply(F, D);
	}
	var A = CustEvent._srcObjs.indexOf(F);
	if (A < 0) {
		return;
	}
	var C = CustEvent._registeredEvents[A];
	var B = C[H];
	if (B) {
		for (var E = 0; E < B.length; E++) {
			B[E].apply(F, D);
		}
	}
};
var BBEffect = function(E, F, H, G){
	this.srcObj = E;
	this.eftFun = F;
	this.dur = H;
	this.opts = G;
	this.isPlaying = false;
	this._startDate = 0;
	this._costDur = null;
	return this;
};
BBEffect._interval = window.setInterval("BBEffect._playAll()", 28);
BBEffect._playingObjs = [];
BBEffect._playAll = function(){
	for (var E = 0; E < BBEffect._playingObjs.length; E++) {
		var D = BBEffect._playingObjs[E];
		var F = (new Date() - D._startDate) / D.dur;
		if (F >= 1) {
			F = 1;
			D.eftFun(1);
			CustEvent.fireEvent(D, "onplay");
			CustEvent.fireEvent(D, "onsuspend");
			D._costDur = null;
			D.isPlaying = false;
			BBEffect._playingObjs.splice(E, 1);
			E--;
		}
		else {
			D.eftFun(F);
			CustEvent.fireEvent(D, "onplay");
		}
	}
};
BBEffect.prototype.play = function(){
	var B = BBEffect._playingObjs.indexOf(this);
	if (B > -1) {
		this.stop();
	}
	this._startDate = new Date();
	BBEffect._playingObjs.push(this);
	CustEvent.fireEvent(this, "onbeforeplay");
	this.isPlaying = true;
};
BBEffect.prototype.stop = function(){
	var B = BBEffect._playingObjs.indexOf(this);
	if (B > -1) {
		BBEffect._playingObjs.splice(B, 1);
	}
	this._startDate = 0;
	this._costDur = null;
	this.isPlaying = false;
	CustEvent.fireEvent(this, "onstop");
};
BBEffect.prototype.suspend = function(){
	var B = BBEffect._playingObjs.indexOf(this);
	if (B > -1) {
		BBEffect._playingObjs.splice(B, 1);
	}
	this.eftFun(1);
	CustEvent.fireEvent(this, "onsuspend");
	this.isPlaying = false;
};
BBEffect.prototype.pause = function(){
	var B = BBEffect._playingObjs.indexOf(this);
	if (B > -1) {
		this._costDur = new Date() - this._startDate;
		BBEffect._playingObjs.splice(B, 1);
		CustEvent.fireEvent(this, "onpause");
	}
};
BBEffect.prototype.resume = function(){
	var B = BBEffect._playingObjs.indexOf(this);
	if (B < 0) {
		if (this._costDur == null) {
			return;
		}
		this._startDate = new Date() - this._costDur;
		BBEffect._playingObjs.push(this);
		CustEvent.fireEvent(this, "onresume");
	}
};
BBEffect.prototype.reset = function(){
	this._startDate = new Date();
	this.costDur = null;
	this.eftFun(0);
	CustEvent.fireEvent(this, "onreset");
};
function CssEffect(E, H, G){
	var F = this.efObj = new BBEffect(E, CssEffect.simpleEftFun, H, G);
	F.onbeforeplay = CssEffect.onbeforeplay;
	F.onsuspend = CssEffect.onsuspend;
	return F;
}
CssEffect.simpleEftFun = function(N){
	var K = this.opts.alterableCss;
	if (K) {
		for (var L in K) {
			var T = K[L];
			if (T[2] == null) {
				T[2] = 0;
			}
			if (T[3] == null) {
				T[3] = 1;
			}
			if (N < T[2]) {
				continue;
			}
			var O = Math.min(1, (N - T[2]) / (T[3] - T[2]));
			if (T[0].constructor == Number) {
				Dom.setStyle(this.srcObj, L, T[0] + O * (T[1] - T[0]));
			}
			else {
				if (T[0].indexOf("px") > -1) {
					if (T[1] == null) {
						if (L == "top" || L == "left" || L == "width" || L == "height") {
							T[1] = Dom.getAbsoluteRect(this.srcObj)[L] + "px";
						}
					}
					Dom.setStyle(this.srcObj, L, (parseInt(T[1]) + O * (parseInt(T[0]) - parseInt(T[1]))).toFixed(0) + "px");
				}
				else {
					if (T[0].indexOf("#") > -1) {
						var P = T[1].match(/\w\w/g);
						var Q = T[0].match(/\w\w/g);
						var S = [];
						for (var M = 0; M < 3; M++) {
							var R = Math.round(parseInt(P[M], 16) + O * (parseInt(Q[M], 16) - parseInt(P[M], 16)));
							S[M] = R > 15 ? R.toString(16) : "0" + R.toString(16);
						}
						Dom.setStyle(this.srcObj, L, "#" + S.join(""));
					}
				}
			}
		}
	}
};
CssEffect.onbeforeplay = function(){
	var D = this.opts.initialCss;
	if (D) {
		for (var C in D) {
			Dom.setStyle(this.srcObj, C, D[C]);
		}
	}
};
CssEffect.onsuspend = function(){
	var D = this.opts.finalCss;
	if (D) {
		for (var C in D) {
			Dom.setStyle(this.srcObj, C, D[C]);
		}
	}
};
BBEffect.changeDisplay = function(P, W, X, Q, U){
	var Z = P.style;
	if (W == null) {
		W = (Z.display == "none");
	}
	if (!X) {
		Z.display = W ? "" : "none";
		return;
	}
	var a = P._changeDisplayEf;
	if (a && a.isPlaying) {
		a.suspend();
	}
	if (W == (Z.display != "none")) {
		return;
	}
	if (W && Z.display == "none") {
		Z.display = "";
	}
	var Y = Dom.borderWidth(P);
	var O = Dom.paddingWidth(P);
	var V = {
		display: "block"
	};
	var R = {};
	var b = {
		display: (W ? "block" : "none")
	};
	if (X & 1) {
		if (W) {
			V.width = "2px";
		}
		var S = P.offsetWidth - Y[1] - Y[3] - O[1] - O[3];
		R.width = W ? [S + "px", "2px"] : ["2px", S + "px"];
		b.width = Z.width;
	}
	if (X & 2) {
		if (W) {
			V.height = "2px";
		}
		var T = P.offsetHeight - Y[0] - Y[0] - O[0] - O[2];
		R.height = W ? [T + "px", "2px"] : ["2px", T + "px"];
		b.height = Z.height;
	}
	if (X & 4) {
		R.opacity = W ? [0, 1] : [1, 0];
		b.filter = null;
	}
	if (X & 8) {
		var T = P.offsetHeight - Y[0] - Y[0] - O[0] - O[2];
		if (W) {
			V.height = "2px";
			V.opacity = 0;
		}
		R.height = W ? [T + "px", "2px", 0, 0.6] : ["2px", T + "px", 0.4, 1];
		R.opacity = W ? [0, 1, 0.4, 1] : [1, 0, 0, 0.6];
		b.height = Z.height;
		b.filter = null;
	}
	if (W) {
		Z.display = "none";
	}
	a = new CssEffect(P, Q || 500, {
		initialCss: V,
		alterableCss: R,
		finalCss: b
	});
	if (U) {
		CustEvent.observe(a, "onsuspend", function(){
			U.call(P, W);
			CustEvent.stopObserving(this, "onsuspend");
		});
	}
	P._changeDisplayEf = a;
	a.play();
};
BBEffect.toggleDisplay = function(G, H, E, F){
	BBEffect.changeDisplay(G, null, H, E, F);
};
BBEffect.shine4Error = function(E, F){
	var D = new CssEffect(E, F || 500, {
		initialCss: {
			backgroundColor: "#FFF"
		},
		alterableCss: {
			backgroundColor: ["#ff3333", "#FFFFFF"]
		},
		finalCss: {
			backgroundColor: ""
		}
	});
	D.play();
};
function Panel(D, C){
	if (!document.body) {
		throw new Error(["Panel", "create error", "Document body not found or not loaded!"]);
	}
	return this._initPanel.apply(this, arguments);
}
Panel.prototype = (function(){
	var D = 100;
	var C = 0;
	return {
		useIframe: null,
		draggable: false,
		resizable: false,
		panelRect: {},
		PANEL_CLASS: "panel",
		IFRAME_CLASS: "panel-iframe",
		INNER_CLASS: "panel-content",
		_panel: null,
		_visible: false,
		_inner: null,
		_iframe: null,
		_initialized: false,
		_initPanel: function(A, B){
			if (this._initialized) {
				return this;
			}
			if (Dom.isElement(A)) {
				this.renderPanel(A);
				Object.extendJson(this, B ||
				{});
			}
			else {
				Object.extendJson(this, A ||
				{});
				this._createPanel();
			}
			this.setPanelVisible(false);
			this._initialized = true;
			if (!this._panel.id) {
				this._panel.id = "BAIDU_PANEL_" + (C++);
			}
			return this;
		},
		_createPanel: function(){
			this._panel = Dom.createElement("div", "className", this.PANEL_CLASS);
			this._inner = Dom.createElement("div", "className", this.INNER_CLASS);
			this._panel.appendChild(this._inner);
			if (this.useIframe) {
				this._createIframe();
			}
			document.body.appendChild(this._panel);
		},
		setStyle: function(A, B){
			Dom.setStyle(this._panel, A, B);
			return this;
		},
		addClassName: function(A){
			Dom.addClassName([this._panel, this._iframe], A);
		},
		resetClassName: function(){
			this._panel.className = this.PANEL_CLASS;
		},
		getPanel: function(){
			return this._panel;
		},
		getIframe: function(){
			return this._iframe;
		},
		_createIframe: function(){
			this._iframe = Dom.createElement("iframe", {
				className: this.IFRAME_CLASS,
				frameBorder: 0,
				src: BB.JSPATH + "util/panel/blank.html"
			});
			this._panel.appendChild(this._iframe);
		},
		saveRect: function(H, A, G, B){
			H = parseInt(H, 10);
			A = parseInt(A, 10);
			G = parseInt(G, 10);
			B = parseInt(B, 10);
			if (!isNaN(H)) {
				this.panelRect.left = H;
			}
			if (!isNaN(A)) {
				this.panelRect.top = A;
			}
			if (!isNaN(G)) {
				this.panelRect.width = G;
			}
			if (!isNaN(B)) {
				this.panelRect.height = B;
			}
			return this.panelRect;
		},
		setPanelRect: function(H, A, G, B){
			Dom.setRect(this._panel, H, A, G, B);
			if (B == null && G != null) {
				B = this.getPanelAutoHeight(G);
			}
			this.saveRect(H, A, G, B);
			return this;
		},
		setPanelSize: function(B, A){
			this.saveRect(null, null, B, A);
			Dom.setSize([this._panel], B, A);
			return this;
		},
		setPanelXY: function(B, A){
			this.saveRect(B, A);
			Dom.setXY(this._panel, B, A);
			return this;
		},
		setPanelFullscreen: function(){
			Dom.setFullscreen(this._panel);
			return this;
		},
		getPanelAutoHeight: function(M){
			var N = this._panel;
			var K = Dom.getStyle(N, "display");
			Dom.setStyle(N, "width", M + "px");
			if (K == "none") {
				Dom.setStyle(N, "display", "block");
			}
			var A = Dom.paddingWidth(N);
			var J = Dom.borderWidth(N);
			var L = A[0] + A[2] + J[0] + J[2];
			var B = N.offsetHeight - L;
			Dom.setStyle(N, "display", K);
			return B;
		},
		setPanelCenter: function(B, A){
			if (!isNaN(parseInt(B))) {
				A = this.getPanelAutoHeight(B);
			}
			this.saveRect(null, null, B, A);
			Dom.setCenter([this._panel], B, A);
			return this;
		},
		appendToPanel: function(A){
			this._panel.appendChild(A);
		},
		appendToContent: function(A){
			this._inner.appendChild(A);
		},
		showPanel: function(J, A, I, B, H){
			H = $(H);
			if (Dom.isElement(H)) {
				J = parseInt(J, 10) || 0;
				A = parseInt(A, 10) || 0;
				p = Dom.getAbsolutePosition(H);
				J += p.left;
				A += p.top;
			}
			this.setPanelRect.apply(this, arguments);
			this.setPanelVisible(true);
		},
		autoAdjustPanelPosition: function(){
			var A = Dom.getAbsolutePosition(this._panel);
			var F = Dom.borderWidth(this._panel);
			var B = Dom.getDocRect();
			if (A.left < B.scrollX) {
				this.setStyle({
					left: B.scrollX + "px"
				});
			}
			if (A.top < B.scrollY) {
				this.setStyle({
					top: B.scrollY + "px"
				});
			}
			if (A.right > B.width + B.scrollX) {
				this.setStyle({
					left: (B.width + B.scrollX - A.width - F[1] - F[3]) + "px"
				});
			}
			if (A.bottom > B.height + B.scrollY) {
				this.setStyle({
					top: (B.height + B.scrollY - A.height - F[0] - F[2]) + "px"
				});
			}
		},
		setPanelVisible: function(B, A){
			B = (B === false ? "none" : "block");
			if ("block" === B && !A) {
				Dom.setStyle(this._panel, "zIndex", D++);
			}
			Dom.setStyle(this._panel, "display", B);
		},
		hidePanel: function(){
			this.setPanelVisible(false);
		},
		renderPanel: function(B){
			var A = Dom.getElementsByClassName(this.INNER_CLASS, B);
			if (!A || !Dom.isElement(A[0])) {
				throw new Error(["Panel", "render error", "Panel inner is not a HTMLElement!"]);
			}
			this._panel = B;
			this._inner = A[0];
			return this;
		},
		detectPanel: function(){
			if (!this._panel || !!!$(this._panel.id)) {
				alert('�ܱ�Ǹ��������޷���ʹ�á�\n�������������������"�������9�ع���"���볢�Թرոù��ܺ����ԡ�\n\n��ιر�"�������9�ع���"����İ�������FAQ��');
				this.dispose();
				return false;
			}
			return true;
		},
		disposePanel: function(){
			Dom.removeNode(this._iframe);
			Dom.removeNode(this._inner);
			Dom.removeNode(this._panel);
			this._iframe = this._inner = this._panel = null;
			if ("function" == typeof CollectGarbage) {
				CollectGarbage();
			}
		},
		detect: function(){
			return this.detectPanel();
		},
		show: function(){
			if (!this.detect()) {
				return;
			}
			this.showPanel();
			if (!this.detect()) {
				return;
			}
		},
		render: function(){
			this.renderPanel.apply(this, arguments);
		},
		contains: function(A){
			return Dom.contains(this._panel, A);
		},
		hide: function(){
			return this.hidePanel();
		},
		dispose: function(){
			return this.disposePanel();
		},
		isVisible: function(){
			return this._visible;
		}
	};
})();
function BasePopup(D, C){
	this.initPopup.apply(this, arguments);
	return this;
}
BasePopup.prototype = (function(){
	return {
		center: false,
		shadow: false,
		cue: false,
		corner: false,
		resizable: false,
		draggable: false,
		buttons: null,
		header: true,
		body: true,
		footer: true,
		className: "panel-t2",
		width: null,
		height: null,
		left: null,
		top: null,
		useIframe: null,
		close: false,
		useEscKey: false,
		autoPosition: true,
		caption: "",
		content: "",
		_popup: null,
		_header: null,
		_body: null,
		_footer: null,
		_close: null,
		_resize: null,
		_shadow: null,
		_cue: null,
		_oDD: null,
		_corners: [],
		_initialized: false,
		_timer: null,
		_delay: 300,
		_visible: false,
		BD_CLASS: "bd",
		FT_CLASS: "ft",
		HD_CLASS: "hd",
		SHADOW_CLASS: "shadow",
		CLOSE_CLASS: "close",
		RESIZE_CLASS: "resize",
		initPopup: function(D, C){
			if (Dom.isElement(D)) {
				this.render(D);
				Object.extendJson(this, C ||
				{});
			}
			else {
				this.applyConfig(D);
			}
			this._addPopupEvent();
			this._initialized = true;
			return this;
		},
		getPopup: function(){
			return this._popup._panel;
		},
		_addPopupEvent: function(){
			var H = this;
			var E = function(){
				H._timer = setTimeout(function(){
					clearTimeout(H._timer);
					if (!H.isVisible()) {
						return;
					}
					CustEvent.fireEvent(H, "windowresize");
				}, H._delay);
			};
			var G = function(A){
				A = window.event || A;
				keycode = A.keyCode || A.which;
				if (keycode == 27 && H.useEscKey) {
					H.hide();
				}
			};
			var F = function(A){
				H.dispose();
			};
			if (!BB.Browser.mozilla) {
				BBEvent.observe(window, "unload", F);
			}
			BBEvent.observe(window, "resize", E);
			BBEvent.observe(document, "keydown", G);
		},
		removeElements: function(){
			Dom.removeNode([this._corners[0], this._corners[1], this._resize, this._shadow, this._cue, this._close]);
			if (this._footer) {
				this._footer.innerHTML = "";
			}
		},
		createContainer: function(){
			if (this._initialized == false) {
				if (this.useIframe != null) {
					useIframe = this.useIframe;
				}
				else {
					useIframe = BB.Browser.ie && BB.Browser.version < 7;
				}
				this._popup = new Panel({
					useIframe: useIframe
				});
				this._popup.setStyle("position", "absolute");
				if (this.header) {
					this._header = Dom.createElement("div", "className", this.HD_CLASS);
					this._popup.appendToContent(this._header);
				}
				if (this.body) {
					this._body = Dom.createElement("div", "className", this.BD_CLASS);
					this._popup.appendToContent(this._body);
				}
				if (this.footer) {
					this._footer = Dom.createElement("div", "className", this.FT_CLASS);
					this._popup.appendToContent(this._footer);
				}
			}
			this._popup.resetClassName();
			this._popup.addClassName(this.className);
		},
		appendToBody: function(B){
			this._body.appendChild(B);
		},
		appendToHeader: function(B){
			this._header.appendChild(B);
		},
		appendToFooter: function(B){
			this._footer.appendChild(B);
		},
		initDraggable: function(){
		},
		applyConfig: function(D){
			this.removeElements();
			Object.extendJson(this, D);
			this.createContainer();
			this.createButtons();
			this.createCorners();
			this.createDirArrow();
			this.createShadow();
			this.createCloseBtn();
			if (this.caption) {
				this.setCaption(this.caption);
			}
			if (this.content) {
				this.setContent(this.content);
			}
			try {
				this.createResizable();
				this.initDraggable();
			} 
			catch (C) {
				CustEvent.fireEvent(this, "exception", {
					message: "BasePopup",
					description: C.message + "\n DragDrop.js not loaded"
				});
			}
			return this;
		},
		createButtons: function(){
			if (this.buttons && this._footer) {
				var E = this.buttons;
				var D = E.length;
				for (var F = 0; F < D; F++) {
					Dom.appendElement(this._footer, Dom.createElement("button", E[F]));
				}
			}
		},
		createResizable: function(){
			if (this.resizable) {
				var B = this;
				this._resize = Dom.createElement("span", "className", this.RESIZE_CLASS);
				this._popup.appendToPanel(this._resize);
				this._resize.onmouseover = function(){
					this.style.cursor = "se-resize";
				};
				this._rsz = new DDProxy({
					sourceEl: this._popup.getPanel(),
					handleEl: this._resize
				});
				this._rsz.resizeSize = true;
				CustEvent.observe(this._rsz, "dragStart", function(){
					B._rsz.minWdith = B._popup.panelRect.width;
				});
				CustEvent.observe(this._rsz, "dragEnd", function(){
				});
			}
		},
		createCloseBtn: function(){
			if (this.close) {
				var C = this;
				this._close = Dom.createElement("span", "className", this.CLOSE_CLASS);
				var D = function(){
					C.hide();
				};
				BBEvent.observe(this._close, "click", D);
				this._popup.appendToPanel(this._close);
			}
		},
		createShadow: function(){
			if (this.shadow) {
				this._shadow = Dom.createElement("span", "className", "sd");
				this._popup.appendToPanel(this._shadow);
			}
		},
		createCorners: function(){
			if (this.corner) {
				this._corners[0] = Dom.createElement("span", "className", "co1");
				this._corners[1] = Dom.createElement("span", "className", "co2");
				this._corners[0].innerHTML = this._corners[1].innerHTML = "<span></span>";
				this._popup.appendToPanel(this._corners[0]);
				this._popup.appendToPanel(this._corners[1]);
			}
		},
		createDirArrow: function(){
			if (this.cue) {
				this._cue = Dom.createElement("span", "className", "cue");
				this._popup.appendToPanel(this._cue);
			}
		},
		setContent: function(B){
			if (typeof B == "object") {
				this._body.innerHTML = "";
				this.appendToBody(B);
			}
			else {
				this._body.innerHTML = B;
			}
			return this._body;
		},
		setCaption: function(B){
			if (this._header) {
				this._header.innerHTML = "<h3>" + B + "</h3>";
			}
		},
		setHeader: function(B){
			this.setCaption(B);
		},
		setFooter: function(B){
			if (this._footer) {
				this._footer.innerHTML = B;
			}
		},
		setPopupCenter: function(C, D){
			this._popup.setPanelCenter(C, D);
			return this._popup;
		},
		showPopup: function(F, G, J, H, I){
			F = F || this.left;
			G = G || this.top;
			J = J || this.width;
			H = H || this.height;
			this._popup.showPanel(F, G, J, H, I);
			if (this.center && !Dom.isElement(I)) {
				F = F || J || this._popup.panelRect.width;
				G = G || H || this._popup.panelRect.height;
				this.setPopupCenter(F, G);
			}
			if (!this.center && this.autoPosition) {
				this.adjustPosition();
			}
			if (isNaN(parseInt(J, 10))) {
				J = this._popup._panel.offsetWidth;
				Dom.setStyle(this._shadow, "width", J);
			}
			return this._popup;
		},
		renderPopup: function(L){
			this._popup = new Panel(L);
			this._popup.setStyle("position", "absolute");
			var Q = this;
			var M = parseInt(Dom.getStyle(L, "width"), 10) || 0;
			if (M) {
				this.width = M;
			}
			var P = Dom.getElementsByClassName(this.SHADOW_CLASS, L);
			var K = Dom.getElementsByClassName(this.HD_CLASS, L);
			var J = Dom.getElementsByClassName(this.BD_CLASS, L);
			var O = Dom.getElementsByClassName(this.FT_CLASS, L);
			var N = Dom.getElementsByClassName(this.CLOSE_CLASS, L);
			if (!J) {
				throw new Error(["Popup", "render error", "Panel body HTMLElement can not be rendered!"]);
			}
			this._shadow = P ? P[0] : null;
			this._header = K ? K[0] : null;
			this._body = J ? J[0] : null;
			this._footer = O ? O[0] : null;
			this._close = N ? N[0] : null;
			if (this._close) {
				var R = function(){
					Q.hide();
				};
				BBEvent.observe(this._close, "click", R);
			}
			return this;
		},
		hidePopup: function(){
			this._popup.hidePanel();
			return this._popup;
		},
		getRect: function(){
			return this._popup.panelRect;
		},
		getBounds: function(){
			return this.getRect();
		},
		setPopupRect: function(){
			return this._popup.setPanelRect.apply(this._popup, arguments);
		},
		adjustPosition: function(){
			this._popup.autoAdjustPanelPosition();
		},
		disposePopup: function(){
			Dom.removeNode([this._header, this._body, this._footer, this._cue, this._shadow, this._resize, this._close, this._corners[0], this._corners[1]]);
			if (this._popup) {
				this._popup.disposePanel();
			}
			this._header = this._body = this._footer = null;
			this._cue = this._shadow = this._resize = this._close = null;
			if ("function" == typeof CollectGarbage) {
				CollectGarbage();
			}
		},
		detectPopup: function(){
			var B = this._popup.detectPanel();
			if (!B) {
				this.dispose();
			}
			return B;
		},
		show: function(){
			if (!this.detect()) {
				return;
			}
			CustEvent.fireEvent(this, "beforeshow");
			this.showPopup.apply(this, arguments);
			this._visible = true;
			CustEvent.fireEvent(this, "aftershow");
			if (!this.detect()) {
				return;
			}
		},
		detect: function(){
			return this.detectPopup();
		},
		hide: function(){
			if (!this.detect()) {
				return;
			}
			CustEvent.fireEvent(this, "beforehide");
			this.hidePopup();
			this._visible = false;
			CustEvent.fireEvent(this, "afterhide");
			if (!this.detect()) {
				return;
			}
		},
		contains: function(B){
			return this._popup.contains(B);
		},
		isVisible: function(){
			return this._visible;
		},
		render: function(){
			return this.renderPopup.apply(this, arguments);
		},
		dispose: function(){
			this.disposePopup();
		}
	};
})();
var LayerPopup = function(B){
	this.$super.apply(this, arguments);
	this._addLayerPopupListener();
	if (this.timeoutListener) {
		this._initTimeoutListener();
	}
	return this;
}
.$extends(BasePopup);
Object.extendJson(LayerPopup.prototype, (function(){
	return {
		hideTimeout: 500,
		showTimeout: 500,
		hideTimer: null,
		timeoutListener: false,
		_isInitTimeoutLsr: false,
		showTimer: null,
		_addLayerPopupListener: function(){
			var C = this;
			var D = function(A){
				A = window.event || A;
				var B = BBEvent.target(A);
				if (!C._popup.contains(B) && C.isVisible()) {
					if (C.isVisible()) {
						CustEvent.fireEvent(C, "blur");
						C.hide();
					}
				}
			};
			CustEvent.observe(this, "aftershow", function(){
				BBEvent.stopObserving(document, "mousedown", D);
				BBEvent.stopObserving(document, "keyup", D);
				BBEvent.observe(document, "mousedown", D);
				BBEvent.observe(document, "keyup", D);
			});
		},
		_initTimeoutListener: function(){
			if (!this._isInitTimeoutLsr) {
				var B = this;
				BBEvent.observe(this.getPopup(), "mouseover", function(){
					CustEvent.fireEvent(B, "aftermouseover");
					B.clearAllTimeout();
				});
				BBEvent.observe(this.getPopup(), "mouseout", function(){
					CustEvent.fireEvent(B, "aftermouseout");
					B.delayHide();
				});
				this._isInitTimeoutLsr = true;
			}
		},
		delayHide: function(D){
			this._initTimeoutListener();
			this.hideTimeout = parseInt(D, 10) || this.hideTimeout;
			this.clearAllTimeout();
			var C = this;
			this.hideTimer = setTimeout(function(){
				C.hide();
			}, this.hideTimeout);
		},
		delayShow: function(){
			this._initTimeoutListener();
			var C = this;
			var D = arguments;
			this.clearAllTimeout();
			this.showTimer = setTimeout(function(){
				C.show.apply(C, D);
			}, this.showTimeout);
		},
		clearAllTimeout: function(){
			clearTimeout(this.hideTimer);
			clearTimeout(this.showTimer);
		},
		show: function(){
			if (!this.detect()) {
				return;
			}
			this.clearAllTimeout();
			CustEvent.fireEvent(this, "beforeshow");
			this.showPopup.apply(this, arguments);
			this._visible = true;
			CustEvent.fireEvent(this, "aftershow");
			if (!this.detect()) {
				return;
			}
		},
		hide: function(){
			this.clearAllTimeout();
			CustEvent.fireEvent(this, "beforehide");
			this.hidePopup();
			this._visible = false;
			CustEvent.fireEvent(this, "afterhide");
		}
	};
})());
var Dialog = function(B){
	B = B ||
	{};
	this._initDialog(B);
	return this;
}
.$extends(BasePopup);
Object.extendJson(Dialog.prototype, {
	close: true,
	modal: true,
	fixedMaskFs: true,
	center: true,
	shadow: true,
	useEscKey: true,
	header: true,
	body: true,
	footer: true,
	content: "",
	caption: "",
	_mask: null,
	MASK_CLASS: "mask",
	_initDialog: function(E){
		var F = false;
		this._mask = new Panel({
			useIframe: !!window.ActiveXObject
		});
		this._mask.addClassName(this.MASK_CLASS);
		this._mask.setStyle("position", "absolute");
		E.className = E.className || "panel-t1";
		E.useIframe = E.useIframe || false;
		this.$super(E);
		this._addPopupEvent();
		var D = this;
		CustEvent.observe(this, "windowresize", function(){
			if (D.fixedMaskFs) {
				D.setMaskFullscreen();
			}
		});
		return this;
	},
	setMaskFullscreen: function(){
		this._mask.setPanelFullscreen();
	},
	setDialogCenter: function(F, G, J, H, I){
		this.setPopupCenter.apply(this, arguments);
	},
	showDialog: function(F, G, J, H, I){
		if (this.modal) {
			this._mask.showPanel();
		}
		else {
			this._mask.hidePanel();
		}
		this.showPopup(F, G, J, H, I);
		if (this.fixedMaskFs) {
			this._mask.setPanelFullscreen();
		}
		return this;
	},
	setDialogRect: function(){
		return this.setPopupRect.apply(this, arguments);
	},
	showMask: function(){
		this._mask.showPanel.apply(this._mask, arguments);
		return this._mask;
	},
	hideMask: function(){
		this._mask.hidePanel();
		return this._mask;
	},
	hideDialog: function(){
		this.hidePopup();
		this.hideMask();
	},
	show: function(){
		if (!this.detect()) {
			return;
		}
		CustEvent.fireEvent(this, "beforeshow");
		this.showDialog.apply(this, arguments);
		this._visible = true;
		CustEvent.fireEvent(this, "aftershow");
		if (!this.detect()) {
			return;
		}
	},
	hide: function(){
		CustEvent.fireEvent(this, "beforehide");
		this.hideDialog();
		this._visible = false;
		CustEvent.fireEvent(this, "afterhide");
	},
	dispose: function(){
		this.disposePopup();
		this._mask.dispose();
	}
});
function TabViewGroup(B){
	if (!B.tabNode) {
		throw new Error(["TabViewGroup", "constructor", "tabNode must be a object"]);
	}
	this.tabNode = B.tabNode;
	this.disabled = B.disabled || false;
	this.contentNode = B.contentNode;
	return this;
}
TabViewGroup.castGroup = function(C){
	try {
		if (C.constructor != TabViewGroup) {
			C = new TabViewGroup(C);
		}
		return C;
	} 
	catch (D) {
		throw new Error(["TabViewGroup", "cast", "Cast TabViewGroup error"]);
	}
};
function TabView(){
	this.oGroups = [];
	this.length = 0;
	this.TAB_ACTIVE_CN = "selected";
	this.TAB_DEACTIVE_CN = "unselected";
	this.CONTENT_ACTIVE_CN = "selected";
	this.CONTENT_DEACTIVE_CN = "unselected";
	this.events = ["click"];
	this.current = {};
	this.maxTab = 10;
	this.preventDefault = true;
	this.stopPropagation = true;
	return this._constructor.apply(this, arguments);
}
TabView.prototype = (function(){
	var B = CustEvent;
	return {
		_constructor: function(A, E, F){
			if (Dom.isElement(A) && !Dom.isElement(E)) {
				this.tabParent = Dom.getFirstChild(A);
				this.contentParent = A.getElementsByTagName("div")[0];
				Object.extendJson(this, E ||
				{});
				if (!Dom.isElement(this.tabParent)) {
					throw new Error(["TabView", "constructor", "Tab parentNode is invalid!"]);
				}
				if (!Dom.isElement(this.contentParent)) {
					throw new Error(["TabView", "constructor", "content parentNode is invalid!"]);
				}
				this.renderTabView();
			}
			else {
				Object.extendJson(this, A ||
				{});
			}
			return this;
		},
		_setActive: function(A){
			var G = this.oGroups;
			var H = G.length;
			if (G[A].disabled) {
				return;
			}
			for (var F = 0; F < H; F++) {
				if (F == A) {
					if (this.current.oGroup != G[F]) {
						if (this.current.oGroup) {
							B.fireEvent(this, "deactive", this.current.oGroup, this.current.n);
						}
						B.fireEvent(this, "active", G[F], F);
					}
					this.current = {};
					this.current.oGroup = G[F];
					this.current.n = A;
					Dom.replaceClassName(G[F].tabNode, this.TAB_DEACTIVE_CN, this.TAB_ACTIVE_CN);
					Dom.replaceClassName(G[F].contentNode, this.CONTENT_DEACTIVE_CN, this.CONTENT_ACTIVE_CN);
				}
				else {
					Dom.replaceClassName(G[F].tabNode, this.TAB_ACTIVE_CN, this.TAB_DEACTIVE_CN);
					Dom.replaceClassName(G[F].contentNode, this.CONTENT_ACTIVE_CN, this.CONTENT_DEACTIVE_CN);
				}
			}
		},
		_insertGroup: function(A, E){
			var F = this.oGroups;
			if (A <= 0) {
				return [E].concat(F);
			}
			return F.slice(0, A + 1).concat(E, F.slice(A + 1));
		},
		_addEventListener: function(A){
			if (this.events.constructor == String) {
				this.events = [this.events];
			}
			var G = this.events;
			var J = this;
			for (var H = 0, I = G.length; H < I; H++) {
				A.tabNode["on" + G[H]] = function(C){
					var D = this;
					var E = J.queryTabIndex(D);
					if (E != null) {
						J.setActiveTab(E);
					}
					if (J.preventDefault) {
						BBEvent.preventDefault(C);
					}
					if (J.stopPropagation) {
						BBEvent.stopPropagation(C);
					}
				};
			}
		},
		queryTabIndex: function(A){
			if (!A) {
				return null;
			}
			var G = this.oGroups;
			var H = G.length;
			for (var F = 0; F < H; F++) {
				if (G[F].tabNode == A) {
					return F;
				}
			}
			return null;
		},
		renderTabView: function(){
			if (!this.tabParent) {
				throw new Error(["Tabview", "renderTabView", "Tab parentNode is invalid!"]);
			}
			var G = this.getChildNodesByTagName(this.tabParent, "li");
			var A = this.getChildNodesByTagName(this.contentParent, "div");
			var H = 0;
			var J = G.length;
			for (var I = 0; I < J; I++) {
				this.addTab({
					tabNode: G[I],
					contentNode: A[I]
				});
				if (Dom.hasClassName(G[I], this.TAB_ACTIVE_CN)) {
					H = I;
				}
			}
			this.setActiveTab(H);
			return this;
		},
		getChildNodesByTagName: function(A, J){
			if (!A) {
				return [];
			}
			var K = Dom.pluckWhiteNode(A.childNodes);
			var L = K.length;
			var H = [];
			J = J.toLowerCase();
			for (var I = 0; I < L; I++) {
				if (K[I].tagName.toLowerCase() == J) {
					H.push(K[I]);
				}
			}
			return H;
		},
		insertTab: function(A, D){
			D = TabViewGroup.castGroup(D);
			if (this.length >= this.maxTab) {
				return;
			}
			this.oGroups = this._insertGroup(A, D);
			this._addEventListener(D);
			this.length++;
			return this;
		},
		removeTab: function(A){
			if (!this.oGroups[A]) {
				return null;
			}
			Dom.removeNode(this.oGroups[A].tabNode);
			Dom.removeNode(this.oGroups[A].contentNode);
			this.oGroups[A].tabNode = null;
			this.oGroups[A].contentNode = null;
			this.oGroups.splice(A, 1);
			this.length--;
			if (this.current.n == A) {
				this.setActiveTab(A - 1 < 0 ? 0 : A - 1);
			}
			return this;
		},
		addTab: function(A){
			this.insertTab(this.length, A);
			return this;
		},
		setActiveTab: function(A){
			if (A > this.length - 1) {
				return false;
			}
			this._setActive(A);
			return this;
		},
		disabledTab: function(A){
			this.oGroups[A].disabled = true;
		}
	};
})();
var Slide = function(){
	return this.init.apply(this, arguments);
};
Slide.prototype = (function(){
	var play = function(){
		if (!this.autoPlay || this.playIntervalId) {
			return;
		}
		var me = this;
		(function f(){
			me.playIntervalId = setTimeout(function(){
				var t = me.picSelIndex + 1;
				t = t > me.itemSum - 1 ? 0 : t;
				me.setSel(t);
				f();
			}, me.playInterval);
		})();
	};
	var stop = function(){
		if (!this.autoPlay) {
			return;
		}
		if (this.playIntervalId) {
			clearTimeout(this.playIntervalId);
			this.playIntervalId = null;
		}
	};
	return {
		init: function(op){
			op = op ||
			{};
			this.slideContainer = op.container || Dom.getElementsByClassName("slider", document.body)[0];
			this.pics = Dom.getArray(op.pics || Dom.getElementsByClassName("img", this.slideContainer));
			this.pages = Dom.getArray(op.pages || this.slideContainer.getElementsByTagName("li"));
			this.itemSum = this.pics.length;
			this.eventType = op.eventType || "click";
			this.autoPlay = op.autoPlay == false ? false : true;
			this.playInterval = op.interval || 3000;
			this.picSelIndex = 0;
			this.playIntervalId = null;
			this.isrun = false;
		},
		initSlide: function(){
			this.pics.each(function(el){
				Dom.hide(el);
			});
			Dom.show(this.pics[this.picSelIndex]);
		},
		run: function(){
			if (this.pages.length < 1) {
				return;
			}
			this.initSlide();
			var me = this;
			for (var i = 0; i < this.itemSum; i++) {
				with ({
					ii: i
				}) {
					BBEvent.observe(this.pages[i], this.eventType, function(){
						var change = function(){
							stop.call(me);
							me.setSel(ii);
						};
						var tempid = null;
						if ("mouseover" == me.eventType) {
							tempid = setTimeout(change, me.isrun ? 400 : 100);
							BBEvent.observe(me.pages[ii], "mouseout", function(){
								clearTimeout(tempid);
							});
						}
						else {
							change();
						}
					});
				}
			}
			this.pages[0].className = "selected";
			play.call(this);
			BBEvent.observe(this.slideContainer, "mouseover", function(e, el){
				e = e || window.event;
				var relateEl = e.relatedTarget || e.fromElement || document.body;
				if (!Dom.contains(me.slideContainer, relateEl)) {
					stop.call(me);
				}
			});
			BBEvent.observe(this.slideContainer, "mouseout", function(e, el){
				e = e || window.event;
				var relateEl = e.relatedTarget || e.toElement || document.body;
				if (!Dom.contains(me.slideContainer, relateEl)) {
					play.call(me);
				}
			});
		},
		setSel: function(i){
			var sel = this.picSelIndex;
			if (sel == i) {
				return;
			}
			this.isrun = true;
			CustEvent.fireEvent(this, "picBeforeChange", i);
			this.pages[sel].className = "";
			Dom.hide(this.pics[sel]);
			sel = this.picSelIndex = i;
			Dom.show(this.pics[sel]);
			CustEvent.fireEvent(this, "picChange", i);
			this.pages[sel].className = "selected";
			this.isrun = false;
		}
	};
})();
var FadeSlide = function(A){
	this.$super(A);
}
.$extends(Slide);
Object.extendJson(FadeSlide.prototype, {
	setSel: function(B){
		var D = this.picSelIndex;
		if (D == B) {
			return;
		}
		this.isrun = true;
		CustEvent.fireEvent(this, "picBeforeChange", B);
		Dom.setStyle(this.pics[D], "opacity", "1");
		Dom.hide(this.pics[D]);
		this.pages[D].className = "";
		D = this.picSelIndex = B;
		Dom.show(this.pics[D]);
		this.pages[D].className = "selected";
		CustEvent.fireEvent(this, "picChange", B);
		var C = this;
		var A = new CssEffect(this.pics[D], 400, {
			initialCss: {
				opacity: 0.3
			},
			alterableCss: {
				opacity: [0.3, 1]
			}
		});
		A.onsuspend = function(){
			Dom.setStyle(C.pics[B], "opacity", "1");
			C.isrun = false;
		};
		A.play();
	}
});
var ScrollSlide = function(A){
	this.$super(A);
}
.$extends(Slide);
Object.extendJson(ScrollSlide.prototype, {
	initSlide: function(){
		this.pics.each(function(B){
			Dom.setStyle(B, "display", "block");
		});
		this.picContainer = Dom.parentNode(this.pics[0], "div");
		this.picContainer.scrollTop = 0;
		var A = this;
		BBEvent.observe(window, "beforeunload", function(){
			A.pics.each(function(C, B){
				A.picSelIndex != B && Dom.setStyle(C, "display", "none");
			});
			A.picContainer.scrollTop = 0;
		});
	},
	setSel: function(D){
		var F = this.picSelIndex;
		if (F == D) {
			return;
		}
		this.isrun = true;
		this.pages[F].className = "";
		var C = this.picContainer.offsetHeight * (F);
		var A = this.picContainer.offsetHeight * (D - F);
		F = this.picSelIndex = D;
		this.pages[F].className = "selected";
		CustEvent.fireEvent(this, "picChange", D);
		var E = this;
		var B = new BBEffect($("test"), function(G){
			E.picContainer.scrollTop = parseInt(C + (-A * ((t = G - 1) * t * t * t) + A));
		}, 400);
		B.onsuspend = function(){
			E.isrun = false;
		};
		B.play();
	}
});
var PicSlide = function(A){
	if (!BBEffect) {
		A.effect = "";
	}
	switch (A.effect) {
		case "fade":
			return new FadeSlide(A);
		case "scroll":
			return new ScrollSlide(A);
		default:
			return new Slide(A);
	}
};
var BBoard = {
	render: function(J, K, L, P, M){
		var N = $(J);
		var K = $(K);
		var L = $(L);
		if (!N) {
			return;
		}
		if (typeof(P) == "undefined" || !P) {
			P = 3000;
		}
		this.effect = M || "fade";
		var I = this;
		this.pairs = [];
		this.curIndex = 0;
		var O = Dom.pluckWhiteNode(N.childNodes);
		this.total = O.length;
		if (this.total < 2) {
			return;
		}
		Array.toArray(O).each(function(B, A){
			I.pairs.push({
				b: B
			});
		});
		Dom.getArray([N, K, L]).each(function(A){
			BBEvent.observe(A, "mouseover", function(){
				if (I.itv) {
					clearInterval(I.itv);
				}
			});
			BBEvent.observe(A, "mouseout", function(){
				I.itv = setInterval(function(){
					I.roll();
				}, P);
			});
		});
		BBEvent.observe(K, "click", function(A){
			I.roll(I.curIndex - 1);
			BBEvent.preventDefault(A || event);
		});
		BBEvent.observe(L, "click", function(A){
			I.roll();
			BBEvent.preventDefault(A || event);
		});
		this.itv = setInterval(function(){
			I.roll();
		}, P);
	},
	roll: function(D){
		if (typeof(D) == "undefined") {
			D = this.curIndex + 1;
		}
		D = D < 0 ? this.total - 1 : D;
		D = D > this.total - 1 ? 0 : D;
		var F = this.pairs[this.curIndex];
		this.curIndex = D;
		var E = this.pairs[this.curIndex];
		this.hide(F.b, E.b);
	},
	hide: function(E, F){
		switch (this.effect) {
			case "fade":
				var D = this;
				BBEffect.changeDisplay(E, false, 4, 350, function(){
					Dom.hide(E);
					D.show(F);
				});
				break;
			default:
				Dom.hide(E);
				this.show(F);
		}
	},
	show: function(B){
		switch (this.effect) {
			case "fade":
				BBEffect.changeDisplay(B, true, 4, 150);
				break;
			default:
				Dom.show(B);
		}
	}
};

