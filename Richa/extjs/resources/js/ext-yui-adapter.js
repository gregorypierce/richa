/*
 * Ext JS Library 1.0 Beta 2
 * Copyright(c) 2006-2007, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://www.extjs.com/license
 */

Ext={};window["undefined"]=window["undefined"];Ext.apply=function(o,c,_3){if(_3){Ext.apply(o,_3);}if(o&&c&&typeof c=="object"){for(var p in c){o[p]=c[p];}}return o;};(function(){var _5=0;var ua=navigator.userAgent.toLowerCase();var _7=document.compatMode=="CSS1Compat",_8=ua.indexOf("opera")>-1,_9=ua.indexOf("webkit")>-1,_a=ua.indexOf("msie")>-1,_b=ua.indexOf("msie 7")>-1,_c=!_9&&ua.indexOf("gecko")>-1,_d=_a&&!_7,_e=(ua.indexOf("windows")!=-1||ua.indexOf("win32")!=-1),_f=(ua.indexOf("macintosh")!=-1||ua.indexOf("mac os x")!=-1);if(_a&&!_b){try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}}Ext.apply(Ext,{isStrict:_7,SSL_SECURE_URL:"javascript:false",BLANK_IMAGE_URL:"http:/"+"/extjs.com/s.gif",emptyFn:function(){},applyIf:function(o,c){if(o&&c){for(var p in c){if(typeof o[p]=="undefined"){o[p]=c[p];}}}return o;},id:function(el,_14){_14=_14||"ext-gen";el=Ext.getDom(el);var id=_14+(++_5);return el?(el.id?el.id:(el.id=id)):id;},extend:function(){var io=function(o){for(var m in o){this[m]=o[m];}};return function(sc,sp,_1b){var F=function(){},scp,spp=sp.prototype;F.prototype=spp;scp=sc.prototype=new F();scp.constructor=sc;sc.superclass=spp;if(spp.constructor==Object.prototype.constructor){spp.constructor=sp;}sc.override=function(o){Ext.override(sc,o);};scp.override=io;Ext.override(sc,_1b);return sc;};}(),override:function(_20,_21){if(_21){var p=_20.prototype;for(var _23 in _21){p[_23]=_21[_23];}}},namespace:function(){var a=arguments,o=null,i,j,d,rt;for(i=0;i<a.length;++i){d=a[i].split(".");rt=d[0];eval("if (typeof "+rt+" == \"undefined\"){"+rt+" = {};} o = "+rt+";");for(j=1;j<d.length;++j){o[d[j]]=o[d[j]]||{};o=o[d[j]];}}},urlEncode:function(o){if(!o){return "";}var obj={},buf=[],key,_2e,i=0;for(key in o){_2e=typeof o[key];if(_2e!="function"){if(typeof o[key].length=="undefined"||_2e=="string"){obj[key]=[o[key]];}else{obj[key]=o[key];}}}for(key in obj){for(i=0;i<obj[key].length;i++){buf.push(encodeURIComponent(key),"=",encodeURIComponent(obj[key][i]),"&");}}buf.pop();return buf.join("");},urlDecode:function(_30,_31){if(!_30||!_30.length){return {};}var obj={};var _33=_30.split("&");var _34,_35,_36;for(var i=0;i<_33.length;i++){_34=_33[i].split("=");_35=_34[0];_36=_34[1];if(_31!==true){if(typeof obj[_35]=="undefined"){obj[_35]=_36;}else{if(typeof obj[_35]=="string"){obj[_35]=[obj[_35]];obj[_35].push(_36);}else{obj[_35].push(_36);}}}else{obj[_35]=_36;}}return obj;},each:function(_38,fn,_3a){if(typeof _38.length=="undefined"||typeof _38=="string"){_38=[_38];}for(var i=0,len=_38.length;i<len;i++){if(fn.call(_3a||_38[i],_38[i],i,_38)===false){return i;}}},combine:function(){var as=arguments,l=as.length,r=[];for(var i=0;i<l;i++){var a=as[i];if(a instanceof Array){r=r.concat(a);}else{if(a.length!==undefined&&!a.substr){r=r.concat(Array.prototype.slice.call(a,0));}else{r.push(a);}}}return r;},escapeRe:function(s){return s.replace(/([.*+?^${}()|[\]\/\\])/g,"\\$1");},callback:function(cb,_44,_45,_46){if(typeof cb=="function"){if(_46){cb.defer(_46,_44,_45||[]);}else{cb.apply(_44,_45||[]);}}},getDom:function(el){if(!el){return null;}return el.dom?el.dom:(typeof el=="string"?document.getElementById(el):el);},num:function(v,_49){if(typeof v!="number"){return _49;}return v;},isOpera:_8,isSafari:_9,isIE:_a,isIE7:_b,isGecko:_c,isBorderBox:_d,isWindows:_e,isMac:_f,useShims:((_a&&!_b)||(_c&&_f))});})();Ext.namespace("Ext","Ext.util","Ext.grid","Ext.dd","Ext.tree","Ext.data","Ext.form","Ext.menu","Ext.state","Ext.lib");Ext.apply(Function.prototype,{createCallback:function(){var _4a=arguments;var _4b=this;return function(){return _4b.apply(window,_4a);};},createDelegate:function(obj,_4d,_4e){var _4f=this;return function(){var _50=_4d||arguments;if(_4e===true){_50=Array.prototype.slice.call(arguments,0);_50=_50.concat(_4d);}else{if(typeof _4e=="number"){_50=Array.prototype.slice.call(arguments,0);var _51=[_4e,0].concat(_4d);Array.prototype.splice.apply(_50,_51);}}return _4f.apply(obj||window,_50);};},defer:function(_52,obj,_54,_55){var fn=this.createDelegate(obj,_54,_55);if(_52){return setTimeout(fn,_52);}fn();return 0;},createSequence:function(fcn,_58){if(typeof fcn!="function"){return this;}var _59=this;return function(){var _5a=_59.apply(this||window,arguments);fcn.apply(_58||this||window,arguments);return _5a;};},createInterceptor:function(fcn,_5c){if(typeof fcn!="function"){return this;}var _5d=this;return function(){fcn.target=this;fcn.method=_5d;if(fcn.apply(_5c||this||window,arguments)===false){return;}return _5d.apply(this||window,arguments);};}});Ext.applyIf(String,{escape:function(_5e){return _5e.replace(/('|\\)/g,"\\$1");},leftPad:function(val,_60,ch){var _62=new String(val);if(ch==null){ch=" ";}while(_62.length<_60){_62=ch+_62;}return _62;},format:function(_63){var _64=Array.prototype.slice.call(arguments,1);return _63.replace(/\{(\d+)\}/g,function(m,i){return _64[i];});}});String.prototype.toggle=function(_67,_68){return this==_67?_68:_67;};Ext.applyIf(Number.prototype,{constrain:function(min,max){return Math.min(Math.max(this,min),max);}});Ext.applyIf(Array.prototype,{indexOf:function(o){for(var i=0,len=this.length;i<len;i++){if(this[i]==o){return i;}}return -1;},remove:function(o){var _6f=this.indexOf(o);if(_6f!=-1){this.splice(_6f,1);}}});Date.prototype.getElapsed=function(_70){return Math.abs((_70||new Date()).getTime()-this.getTime());};

if(typeof YAHOO=="undefined"){throw "Unable to load Ext, core YUI utilities (yahoo, dom, event) not found.";}(function(){var E=YAHOO.util.Event;var D=YAHOO.util.Dom;var CN=YAHOO.util.Connect;var ES=YAHOO.util.Easing;var A=YAHOO.util.Anim;var _6;Ext.lib.Dom={getViewWidth:function(_7){return _7?D.getDocumentWidth():D.getViewportWidth();},getViewHeight:function(_8){return _8?D.getDocumentHeight():D.getViewportHeight();},isAncestor:function(_9,_a){return D.isAncestor(_9,_a);},getRegion:function(el){return D.getRegion(el);},getY:function(el){return this.getXY(el)[1];},getX:function(el){return this.getXY(el)[0];},getXY:function(el){var p,pe,b,_12,bd=document.body;el=Ext.getDom(el);if(el.getBoundingClientRect){b=el.getBoundingClientRect();_12=fly(document).getScroll();return [b.left+_12.left,b.top+_12.top];}else{var x=el.offsetLeft,y=el.offsetTop;p=el.offsetParent;var _16=false;if(p!=el){while(p){x+=p.offsetLeft;y+=p.offsetTop;if(Ext.isSafari&&!_16&&fly(p).getStyle("position")=="absolute"){_16=true;}if(Ext.isGecko){pe=fly(p);var bt=parseInt(pe.getStyle("borderTopWidth"),10)||0;var bl=parseInt(pe.getStyle("borderLeftWidth"),10)||0;x+=bl;y+=bt;if(p!=el&&pe.getStyle("overflow")!="visible"){x+=bl;y+=bt;}}p=p.offsetParent;}}if(Ext.isSafari&&(_16||fly(el).getStyle("position")=="absolute")){x-=bd.offsetLeft;y-=bd.offsetTop;}}p=el.parentNode;while(p&&p!=bd){if(!Ext.isOpera||(Ext.isOpera&&p.tagName!="TR"&&fly(p).getStyle("display")!="inline")){x-=p.scrollLeft;y-=p.scrollTop;}p=p.parentNode;}return [x,y];},setXY:function(el,xy){el=Ext.fly(el,"_setXY");el.position();var pts=el.translatePoints(xy);if(xy[0]!==false){el.dom.style.left=pts.left+"px";}if(xy[1]!==false){el.dom.style.top=pts.top+"px";}},setX:function(el,x){this.setXY(el,[x,false]);},setY:function(el,y){this.setXY(el,[false,y]);}};Ext.lib.Event={getPageX:function(e){return E.getPageX(e.browserEvent||e);},getPageY:function(e){return E.getPageY(e.browserEvent||e);},getXY:function(e){return E.getXY(e.browserEvent||e);},getTarget:function(e){return E.getTarget(e.browserEvent||e);},getRelatedTarget:function(e){return E.getRelatedTarget(e.browserEvent||e);},on:function(el,_26,fn,_28,_29){E.on(el,_26,fn,_28,_29);},un:function(el,_2b,fn){E.removeListener(el,_2b,fn);},purgeElement:function(el){E.purgeElement(el);},preventDefault:function(e){E.preventDefault(e.browserEvent||e);},stopPropagation:function(e){E.stopPropagation(e.browserEvent||e);},stopEvent:function(e){E.stopEvent(e.browserEvent||e);},onAvailable:function(el,fn,_33,_34){return E.onAvailable(el,fn,_33,_34);}};Ext.lib.Ajax={request:function(_35,uri,cb,_38){return CN.asyncRequest(_35,uri,cb,_38);},formRequest:function(_39,uri,cb,_3c,_3d,_3e){CN.setForm(_39,_3d,_3e);return CN.asyncRequest("POST",uri,cb,_3c);},isCallInProgress:function(_3f){return CN.isCallInProgress(_3f);},abort:function(_40){return CN.abort(_40);},serializeForm:function(_41){var d=CN.setForm(_41.dom||_41);CN.resetFormState();return d;}};Ext.lib.Region=YAHOO.util.Region;Ext.lib.Point=YAHOO.util.Point;Ext.lib.Anim={scroll:function(el,_44,_45,_46,cb,_48){this.run(el,_44,_45,_46,cb,_48,YAHOO.util.Scroll);},motion:function(el,_4a,_4b,_4c,cb,_4e){this.run(el,_4a,_4b,_4c,cb,_4e,YAHOO.util.Motion);},color:function(el,_50,_51,_52,cb,_54){this.run(el,_50,_51,_52,cb,_54,YAHOO.util.ColorAnim);},run:function(el,_56,_57,_58,cb,_5a,_5b){_5b=_5b||YAHOO.util.Anim;if(typeof _58=="string"){_58=YAHOO.util.Easing[_58];}var _5c=new _5b(el,_56,_57,_58);_5c.animateX(function(){Ext.callback(cb,_5a);});return _5c;}};function fly(el){if(!_6){_6=new Ext.Element.Flyweight();}_6.dom=el;return _6;}if(Ext.isIE){YAHOO.util.Event.on(window,"unload",function(){var p=Function.prototype;delete p.createSequence;delete p.defer;delete p.createDelegate;delete p.createCallback;delete p.createInterceptor;});}if(YAHOO.util.Anim){YAHOO.util.Anim.prototype.animateX=function(_5f,_60){var f=function(){this.onComplete.unsubscribe(f);if(typeof _5f=="function"){_5f.call(_60||this,this);}};this.onComplete.subscribe(f,this,true);this.animate();};}if(YAHOO.util.DragDrop&&Ext.dd.DragDrop){YAHOO.util.DragDrop.defaultPadding=Ext.dd.DragDrop.defaultPadding;YAHOO.util.DragDrop.constrainTo=Ext.dd.DragDrop.constrainTo;}YAHOO.util.Dom.getXY=function(el){var f=function(el){return Ext.lib.Dom.getXY(el);};return YAHOO.util.Dom.batch(el,f,YAHOO.util.Dom,true);};if(YAHOO.util.AnimMgr){YAHOO.util.AnimMgr.fps=1000;}YAHOO.util.Region.prototype.adjust=function(t,l,b,r){this.top+=t;this.left+=l;this.right+=r;this.bottom+=b;return this;};})();
