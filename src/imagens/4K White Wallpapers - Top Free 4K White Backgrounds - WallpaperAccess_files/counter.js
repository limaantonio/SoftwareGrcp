var _statcounter=function(_1){var _2=parseInt(sc_project,10);var _3=_2==9560334||_2==6709687||_2==9879613||_2==4124138||_2==204609||_2==10776808;try{var _4;if(typeof _1==="undefined"){_4={}}else{if(_1.record_pageview){_4=_1.get_pending_tags()}else{_4=_1}}var _5=true;var _6=false;if(typeof performance!=="undefined"){try{_6=Math.round(performance.now())}catch(ex){_6=false}}var _7=0;var _8=0;var _9=-1;var _a=0;var _b="";var _c="";var _d="";var _e="";var _f="";var _10="https";var _11="statcounter.com";var _12="c";var _13="StatCounter - Free Web Tracker and Counter";var _14="";var _15=0;var _16="";var _17="&u1=za";var _18="cookie";if(window.sc_client_storage){_18=window.sc_client_storage}if(typeof window.sc_first_party_cookie!="undefined"&&sc_first_party_cookie=="0"){_18="disabled"}if(window.sc_invisible){if(window.sc_invisible==1){_a=1}}if(window.sc_click_stat){_9=window.sc_click_stat}var _19=""+document.location;var _1a=new RegExp("^https","i");if(_19.match(_1a)){_10="https"}if(window.sc_local){_b=sc_local}else{if(_9==-1){_9=1}_b=_10+"://"+_12+"."+_11+"/"}_c=_b;if(window.sc_text){_b+="text.php?"}else{_b+="t.php?"}if(window.sc_project){if(sc_project=="4135125"||sc_project=="6169619"||sc_project=="6222332"||sc_project=="5106510"||sc_project=="6311399"||sc_project=="6320092"||sc_project=="5291656"||sc_project=="7324465"||sc_project=="6640020"||sc_project=="4629288"||sc_project=="1480088"||sc_project=="2447031"){if(Math.floor(Math.random()*6)!=1){_8=1}}_b+="sc_project="+sc_project}else{if(window.usr){_b+="usr="+usr}else{_7=1}}if(window.sc_remove_link){_d="";_e=""}else{_d="<a class=\"statcounter\" href=\"http://www."+_11+"\" target=\"_blank\">";_e="</a>"}if(window.sc_security){_f=sc_security}if(_1b){_1b++}else{var _1b=1}var _2=parseInt(sc_project,10);var _1c=new Date();var _1d=Math.floor(_1c/86400000);var _1e=_1d-17869;var _1f=11200000-(_1e*12223);var _20=9000000;if(_1f<_20){_1f=_20}var _21=_2==4344864||_2==4124138||_2==204609||_2>_1f;var _22=_2==204609;function get_referer(){var _23=""+document.referrer;try{_23=""+parent.document.referrer}catch(ex){_23=""+document.referrer}if(typeof sc_referer_scr08!=="undefined"){_23=sc_referer_scr08}return _23}var api=function(){};api.push=function(_25){_4=[_25]};var _26=get_referer();var _27=0;var _28="";api.inject_script=function(url,_2a){if(url===undefined||!url.match(/^https?:\/\/(?:[^\/]+\.)?statcounter\.com/)){return}var scr=document.createElement("script");scr.type="text/javascript";scr.async=true;if(_2a){scr.onload=_2a}scr.src=url;var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(scr,s)};function get_jg_rr_url_params(){if(_28==""){return "&jg=&rr="}return _28}function get_page_title(){var _2d=""+document.title;_2d=_2d.substring(0,300);if(encodeURIComponent){_2d=encodeURIComponent(_2d)}else{_2d=escape(_2d)}return _2d}function get_page_url(){var _2e=""+document.location;_2e=_2e.substring(0,300);_2e=escape(_2e);return _2e}function get_screen_width(){return screen.width}function get_screen_height(){return screen.height}function get_performance_url_params(){if(_27==0){var _2f="";var _30="";try{if(typeof performance!=="undefined"){var _31=Math.round(performance.now());_2f="&sc_rum_e_s="+_6+"&sc_rum_e_e="+_31;var _32=_31-_6;_30=get_performance_tags(_32)}}catch(ex){_2f="";_30=""}return _2f+_30}return ""}function get_performance_tags(_33){var _34="";if(_22&&typeof performance!=="undefined"){var _35=document.getElementById("sc-ttfb-bd");var _36="-1";if(_35){_36=_35.textContent}var _37=performance.timing.responseStart-performance.timing.connectStart;var _38=document.getElementById("sc-perf-wh");var _39=0;if(_38){_39=_38.textContent}var _3a=document.getElementById("sc-perf-pn");var _3b=0;if(_3a){_3b=_3a.textContent}var _3c=document.getElementById("sc-perf-db");var _3d=0;if(_3c){_3d=_3c.textContent}_34="&sc_ev_scperf_js_exec="+_33+"&sc_ev_scperf_ttfb_frontend="+_37+"&sc_ev_scperf_ttfb_backend="+_36+"&sc_ev_scperf_ws="+_39+"&sc_ev_scperf_pn="+_3b+"&sc_ev_scperf_db="+_3d}return _34}function strip_tags(_3e,_3f){_3f=(((_3f||"")+"").toLowerCase().match(/<[a-z][a-z0-9]*>/g)||[]).join("");var _40=/<\/?([a-z][a-z0-9]*)\b[^>]*>/gi,_41=/<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;return _3e.replace(_41,"").replace(_40,function($0,$1){return _3f.indexOf("<"+$1.toLowerCase()+">")>-1?$0:""})}function sanitise_tags(_44){for(i=0;i<_44.length;i++){_44[i]=(""+_44[i]).trim()}return _44}function validate_tags(_45){var _46=10;var _47=1;var _48=300;var _49=[];if(!(_45.length%2==0)){_49.push("Every tag must have a name and value.")}else{if(_45.length/2>_46){_49.push("No more than "+_46+" tags can be passed - "+_45.length/2+" passed.")}for(i=0;i<_45.length;i++){var _4a=(""+_45[i]).length;if(_4a<_47||_4a>_48){_49.push("Tag names and values must be between "+_47+" and "+_48+" characters in length ('"+_45[i]+"' is "+_45[i].length+" characters long).")}}for(i=0;i<_45.length;i++){if(strip_tags(""+_45[i])!=""+_45[i]){_49.push("Tag names and values may not contain HTML tags.")}}}if(_49.length!=0){for(i=0;i<_49.length;i++){}return false}return true}function get_tag_string(_4b){function _4c(obj,_4e){var _4f=obj.__proto__||obj.constructor.prototype;return (_4e in obj)&&(!(_4e in _4f)||_4f[_4e]!==obj[_4e])}if(Object.prototype.hasOwnProperty){var _4c=function(obj,_51){return obj.hasOwnProperty(_51)}}var _52="";if(_4c(_4b,"tags")&&typeof _4b.tags==="object"){var _53=[];for(var tag in _4b.tags){_53[_53.length]=tag;_53[_53.length]=_4b.tags[tag]}if(validate_tags(_53)){_53=sanitise_tags(_53);for(i=0;i<_53.length;i=i+2){_52+="&sc_ev_"+encodeURIComponent(_53[i])+"="+encodeURIComponent(_53[i+1])}}}return _52}var _55=[];var _56=256;var _57=6;var _58=52;var _59=Math.pow(_56,_57),_5a=Math.pow(2,_58),_5b=_5a*2,_5c=_56-1;var _5d;var _5e=function(_5f,_60){var key=[];var _62=mixkey(flatten(_60?[_5f,tostring(_55)]:0 in arguments?_5f:autoseed(),3),key);var _63=new ARC4(key);mixkey(tostring(_63.S),_55);_5d=function(){var n=_63.g(_57),d=_59,x=0;while(n<_5a){n=(n+x)*_56;d*=_56;x=_63.g(1)}while(n>=_5b){n/=2;d/=2;x>>>=1}return (n+x)/d};return _62};function ARC4(key){var t,_69=key.length,me=this,i=0,j=me.i=me.j=0,s=me.S=[];if(!_69){key=[_69++]}while(i<_56){s[i]=i++}for(i=0;i<_56;i++){s[i]=s[j=_5c&(j+key[i%_69]+(t=s[i]))];s[j]=t}(me.g=function(_6e){var t,r=0,i=me.i,j=me.j,s=me.S;while(_6e--){t=s[i=_5c&(i+1)];r=r*_56+s[_5c&((s[i]=s[j=_5c&(j+t)])+(s[j]=t))]}me.i=i;me.j=j;return r})(_56)}function flatten(obj,_72){var _73=[],typ=(typeof obj)[0],_75;if(_72&&typ=="o"){for(_75 in obj){try{_73.push(flatten(obj[_75],_72-1))}catch(e){}}}return (_73.length?_73:typ=="s"?obj:obj+"\x00")}function mixkey(_76,key){var _78=_76+"",_79,j=0;while(j<_78.length){key[_5c&j]=_5c&((_79^=key[_5c&j]*19)+_78.charCodeAt(j++))}return tostring(key)}function autoseed(_7b){try{window.crypto.getRandomValues(_7b=new Uint8Array(_56));return tostring(_7b)}catch(e){return [+new Date,window,window.navigator.plugins,window.screen,tostring(_55)]}}function tostring(a){return String.fromCharCode.apply(0,a)}mixkey(Math.random(),_55);function detectBrowserFeatures(){var _7d=[];var i;var _7f;var _80={pdf:"application/pdf",qt:"video/quicktime",realp:"audio/x-pn-realaudio-plugin",wma:"application/x-mplayer2",dir:"application/x-director",fla:"application/x-shockwave-flash",java:"application/x-java-vm",gears:"application/x-googlegears",ag:"application/x-silverlight"};var _81=(new RegExp("Mac OS X.*Safari/")).test(navigator.userAgent)?window.devicePixelRatio||1:1;if(!((new RegExp("MSIE")).test(navigator.userAgent))){if(navigator.mimeTypes&&navigator.mimeTypes.length){for(i in _80){if(Object.prototype.hasOwnProperty.call(_80,i)){_7f=navigator.mimeTypes[_80[i]];_7d.push((_7f&&_7f.enabledPlugin)?"1":"0")}}}if(typeof navigator.javaEnabled!=="unknown"&&typeof navigator.javaEnabled!=="undefined"&&navigator.javaEnabled()){_7d.push("java")}if(typeof window.GearsFactory==="function"){_7d.push("gears")}}_7d.push(screen.width*_81+"x"+screen.height*_81);return _7d.join("")}function generate_uuid(_82){var now=new Date();var _84=false;if(_82===undefined){_82=32;if(_84){_82=36}}var _85=Math.round(now.getTime()/1000)+now.getMilliseconds();var _86=(navigator.userAgent||"")+(navigator.platform||"")+detectBrowserFeatures()+now.getTimezoneOffset()+window.innerWidth+window.innerHeight+window.screen.colorDepth+document.URL+_85;_5e(_86);var _87="0123456789ABCDEF".split(""),_88=new Array(_82),rnd=0,r;for(var i=0;i<_82;i++){if(_84&&(i==8||i==13||i==18||i==23)){_88[i]="-"}else{if((i==12&&!_84)||(i==14&&_84)){_88[i]="4"}else{if((i==13&&!_84)||(i==15&&_84)){_88[i]="F"}else{if(rnd<=2){rnd=33554432+(_5d()*16777216)|0}r=rnd&15;rnd=rnd>>4;_88[i]=_87[(i==19)?(r&3)|8:r]}}}}return _88.join("")}var _8c;if(typeof window.sc_cookie_domain=="undefined"){_8c=document.location.host.replace(/^www\./,"")}else{_8c=window.sc_cookie_domain}if(_8c.substring(0,1)!="."){_8c="."+_8c}function _localStorageAvailable(){var _8d=false;if("localStorage" in window){try{_8d=window["localStorage"]!==null}catch(e){if(!e.name||e.name.toLowerCase().replace(/_/g,"").substring(0,16)!=="quotaexceedederr"){if(!e.number||parseInt(e.number,10)!==-2147024891){throw e}}}}return _8d}function _setLocalStorage(_8e,_8f,_90){if(_localStorageAvailable()){try{if(_8e==="is_visitor_unique"){localStorage.setItem("statcounter.com/localstorage/",_8f)}else{localStorage.setItem("statcounter_"+_8e,_8f)}}catch(e){if(!e.name||e.name.toLowerCase().replace(/_/g,"").substring(0,16)!=="quotaexceedederr"){if(!e.number||parseInt(e.number,10)!==-2147024891){throw e}}return false}return true}return false}function setLocal(_91,_92,_93,_94,_95){if(typeof _92==="string"){_92=[_92]}if(_94===undefined){_94=""}if(_95===undefined){_95=30}var _96=false;if(_18=="localStorage"){_96=_setLocalStorage(_91,_94+_92.join("-"),_93);if(!_96){_96=_writeCookie(_91,_94+_92.join("-"),_93)}else{if(_readCookie(_91)!==null){_removeCookie(_91,_93)}}}else{var _97=_92.slice(0,_95).join("-");_96=_writeCookie(_91,_94+_97,_93);if(!_96){_96=_setLocalStorage(_91,_94+_92.join("-"),_93)}else{if(_92.length>_95){_setLocalStorage(_91,"mx"+_92.slice(_95).join("-"),_93)}else{_removeLocalStorage(_91)}}}return _96}function getLocal(_98){var val=null;if(_localStorageAvailable()){if(_98==="is_visitor_unique"){val=localStorage.getItem("statcounter.com/localstorage/")}else{val=localStorage.getItem("statcounter_"+_98)}}if(_18=="localStorage"&&val!==null&&val.substring(0,2)=="rx"){return val}var _9a=_readCookie(_98);if(val!==null){if(_9a===null&&val.substring(0,2)=="rx"){return val}else{if(_9a!==null&&val.substring(0,2)=="mx"){_9a+="-"+val.substring(2)}}}return _9a}function _removeLocalStorage(_9b){if(_localStorageAvailable()){if(_9b==="is_visitor_unique"){localStorage.removeItem("statcounter.com/localstorage/")}localStorage.removeItem("statcounter_"+_9b)}}function removeLocal(_9c,_9d){_removeLocalStorage(_9c);if(_readCookie(_9c)){_removeCookie(_9c,_9d)}}function _readCookie(_9e){var _9f="sc_"+_9e+"=";var ret=null;if(document.cookie){var ca=document.cookie.split(";");for(var i=0;i<ca.length;i++){var c=ca[i];while(c.charAt(0)==" "){c=c.substring(1,c.length)}if(c.indexOf(_9f)==0){ret=c.substring(_9f.length,c.length)}}}return ret}function _writeCookie(_a4,_a5,_a6,_a7){var _a8=false;if(_a7===undefined){_a8=1000*60*60*24*365*2}else{if(_a7!=="session"){_a8=1000*_a7}}var _a9="";if(_a8!==false){var _aa=new Date();_aa.setTime(_aa.getTime()+_a8);_a9="; expires="+_aa.toGMTString()}var _ab=3050;if(_a5.length>_ab-50&&_a5.substring(0,_ab).indexOf("-")!==-1){_a5=_a5.substring(0,_a5.substring(0,_ab).lastIndexOf("-"))}document.cookie="sc_"+_a4+"="+_a5+_a9+"; domain="+_a6+"; path=/";var rc=_readCookie(_a4);if(rc!==null&&rc===_a5){return true}else{return false}}function _removeCookie(_ad,_ae){if(document.location.host=="www"+_ae){document.cookie="sc_"+_ad+"=; expires=Thu, 01 Jan 1970 00:00:01 GMT; domain=.www"+_ae+"; path=/"}document.cookie="sc_"+_ad+"=; expires=Thu, 01 Jan 1970 00:00:01 GMT; domain="+_ae+"; path=/"}var _af=(function(){var _b0=false;return function(){var _b1=api.is_recording();if(_b0||_b1===false){return}var _b2="statcounter.com";var _b3="/counter/recorder.js";for(var i=0;i!=_b1.length;i++){if(_b1[i].length==5&&_b1[i].substring(0,3)=="dev"){_b3="/counter/recorder_uncompressed.js";_b2=_b1[i]+"."+_b2;break}else{if(_b1[i]=="statreplay"){_b2=_b1[i]+".com"}}}_b2+=_b3;_b2=("https:"==document.location.protocol?"https://":"http://")+_b2;api.inject_script(_b2);_b0=true}}());api.record_pageview=function(_b5){if(typeof _b5==="undefined"){_b5=_4}_4={};_28="";var _b6="";var _b7={"google":null,"bing":["q"],"search.yahoo":null,"m.yahoo":null,"m2.yahoo":null,"baidu":["wd","word"],"yandex":["text"],"ya.ru":["text"],"haosou":["q"],"so.com":["q"],"360.cn":["q"],"360sou":["q"],"aol":["query","q"],"duckduckgo":null,"ask.com":["q","QUERYT"],"mail.ru":["words"],"sogou":["q","query"]};var _b8={"fb":["facebook.com","fb.me"],"pi":["pinterest.com"],"tw":["twitter.com","t.co"],"ln":["linkedin.com"],"in":["instagram.com"],"rd":["reddit.com"],"tb":["tumblr.com"],"st":["stumbleupon.com"],"yt":["youtube.com"],"gp":["plus.google.com","plus.url.google.com"]};function check_root_domains_match(a,b){var _bb=a.split(".");var _bc=b.split(".");var _bd=Math.min(_bb.length,_bc.length);var _be=2;if(_bb.length>1&&((_bb[_bb.length-2].length<=3&&_bb[_bb.length-1] in {"at":1,"au":1,"br":1,"es":1,"hu":1,"il":1,"nz":1,"tr":1,"uk":1,"us":1,"za":1})||_bb[_bb.length-1]=="kr"||_bb[_bb.length-1]=="ru"||(_bb[_bb.length-1]=="au"&&_bb[_bb.length-2] in {"csiro":1})||(_bb[_bb.length-1]=="at"&&_bb[_bb.length-2] in {"priv":1})||(_bb[_bb.length-1]=="fr"&&_bb[_bb.length-2] in {"avocat":1,"aeroport":1,"veterinaire":1})||(_bb[_bb.length-1]=="hu"&&_bb[_bb.length-2] in {"film":1,"lakas":1,"ingatlan":1,"sport":1,"hotel":1})||(_bb[_bb.length-1]=="nz"&&_bb[_bb.length-2] in {"geek":1,"kiwi":1,"maori":1,"school":1,"govt":1,"health":1,"parliament":1})||(_bb[_bb.length-1]=="il"&&_bb[_bb.length-2] in {"muni":1})||(_bb[_bb.length-1]=="za"&&_bb[_bb.length-2] in {"school":1})||(_bb[_bb.length-1]=="tr"&&_bb[_bb.length-2] in {"name":1})||(_bb[_bb.length-1]=="uk"&&_bb[_bb.length-2] in {"police":1}))){_be=3}for(var i=1;i<=_bd;i++){if(_bb[_bb.length-i]!=_bc[_bc.length-i]){return false}if(i>=_be){return true}}return _bb.length==_bc.length}function classify_referrer(r){if(r==""){return "d"}var _c1=r.split("/")[2].replace(/^www\./,"");var _c2=document.location.host.replace(/^www\./,"");if(_21){if(_c2==_c1){return "internal"}if(check_root_domains_match(_c1,_c2)){return "internal"}}if(r.search(/\bgoogle\..*\?.*adurl=http/)!==-1){return "p"}var _c3=["utm_source=bing","?gclid=","&gclid=","utm_medium=cpc","utm_medium=paid-media","utm_medium=ppc"];for(var i=0;i<_c3.length;i++){if(document.location.search.indexOf(_c3[i])!==-1){return "p"}}var _c5=["utm_medium=email"];for(var i=0;i<_c5.length;i++){if(document.location.search.indexOf(_c5[i])!==-1){return "e"}}if(!_21){if(_c2==_c1){return "internal"}}for(var _c6 in _b7){if(_c1.replace(_c6,"#").split(".").indexOf("#")!==-1){if(_b7[_c6]===null){return _c6}for(var i=0;i<_b7[_c6].length;i++){var _c7=_b7[_c6][i];if(r.indexOf("?"+_c7+"=")!==-1||r.indexOf("&"+_c7+"=")!==-1){return _c6}}}}for(var _c8 in _b8){for(var i=0;i<_b8[_c8].length;i++){var _c6=_b8[_c8][i];if(_c1.replace(_c6,"#").split(".").indexOf("#")!==-1){return _c8}}}return _c1}function categorize_class(cls){if(cls=="d"||cls=="p"||cls=="e"||cls=="internal"){return cls}if(cls in _b7){return "o"}if(cls in _b8){return "s"}return "r"}if(_8!=1){if(document.webkitVisibilityState!="prerender"){_15=0}else{_15=1;document.addEventListener("webkitvisibilitychange",function(evt){if(_15==1){_15=2;sc_send_data()}else{return}},false)}}if(!_21){var _cb=classify_referrer(_26);var _cc=categorize_class(_cb);if(_cb!="internal"){_b6="&rcat="+_cc+"&rdom="+_cb}}var _cd=Math.round((new Date()).getTime()/1000);if(_18!="disabled"){if(_21){var _cb=classify_referrer(_26);var _cc=categorize_class(_cb);if(_cb!="internal"){_b6="&rcat="+_cc+"&rdom="+_cb}}try{var _ce=JSON.parse(localStorage.getItem("sc_medium_source"));if(_ce==null){_ce={}}var _cf=null;var _d0=null;var _d1=null;var msl=0;for(var k in _ce){if(_cf===null||_ce[k]>_ce[_cf]){_cf=k}var _d4=categorize_class(k);if(_cc==_d4&&(_d0===null||_ce[k]>_ce[_d0])){_d0=k}if(_d4=="r"&&(_d1===null||_ce[k]<_ce[_d1])){_d1=k}msl+=1}if(msl>30&&_d1!==null){delete _ce[_d1]}var _d5="";if(sessionStorage.getItem("statcounter_bounce")){sessionStorage.removeItem("statcounter_bounce");_d5="&bb=0"}var _d6=30;if(!_21){_d6=15}if(_cb=="d"&&_cf!==null&&_cf!="d"&&(_cd-_ce[_cf])<60*_d6){_cb="internal"}if(_21){if(sessionStorage.getItem("statcounter_session")&&(_cd-parseInt(sessionStorage.getItem("statcounter_session"),10))<60*30){_cb="internal"}sessionStorage.setItem("statcounter_session",_cd)}if(!_21){if(_cc=="r"&&sessionStorage.getItem("statcounter_exit_domain")==_cb){_cb="internal"}}if(_cb=="internal"){if(_cf!==null){_b6="&rcat="+categorize_class(_cf)+"&rdomo="+_cf;_b6+="&rdomg="+(_cd-_ce[_cf]);_ce[_cf]=_cd}}else{var _d7=false;if(_cb in _ce){if(_cb==_cf){_b6=_b6.replace("rdom=","rdomo=")}_b6+="&rdomg="+(_cd-_ce[_cb]);if(_cd-_ce[_cb]<60*30){_d7=true}}else{_b6+="&rdomg=new"}if(_d5==""&&!_d7){sessionStorage.setItem("statcounter_bounce","1");_d5="&bb=1"}if(_d0!==null&&(!(_cb in _ce)||_cb!=_d0)){_b6+="&rcatg="+(_cd-_ce[_d0])}_ce[_cb]=_cd}_b6+=_d5;try{localStorage.setItem("sc_medium_source",JSON.stringify(_ce))}catch(maybe_not_enough_space){if(_21){_b6=""}}}catch(e){if(_21){_b6=""}}if(_2==10227105){try{var _d8=new Image();_d8.src="https://statcounter.com/feedback/?email=javascript@statcounter.com&page_url="+encodeURIComponent(document.location.protocol+"//"+document.location.host+document.location.pathname+document.location.search+document.location.hash)+"&name=Auto%20JS&feedback_username=statcounter&pid="+sc_project+"&fake_post&user_company&feedback=consistent%20uniques%20js%20exception:%20"+encodeURIComponent(localStorage.getItem("statcounter.com/localstorage/")+":::"+_readCookie("is_visitor_unique"))}catch(ignore){}}var _d9="1.1.1.1.1.1.1.1.1";var _da="is_visitor_unique";try{var _db=getLocal(_da)}catch(e){var _db=false;_16=".ex"}var _dc=[];var _dd=[];if(_db&&_db.substring(0,2)=="rx"){removeLocal(_da,_8c);var _de=_db.substring(2);_dc=_de.split("-");var _df=false;var _e0=false;for(var i=0;i<_dc.length;i++){var _e2=_dc[i].split(".");if(_e2[0]==sc_project){_df=true;var _e3=parseInt(_e2[1],10);var _e4=[30,60,120,180,360,720,1440,2880,10080];var _e5=[];var _e6=2;if(_e2[2].length==32){_16="."+_e2[2];_e6=3}else{_16=_e0}for(var ir=0;ir<_e4.length;ir++){var _e8=parseInt(_e2[ir+_e6],10);if(isNaN(_e8)){_e8=1}_e5.push(_e8)}_28+="&jg="+(_cd-_e3);for(var ir=0;ir<_e4.length;ir++){if(_b6.indexOf("rdom=")!==-1){_e5[ir]++}else{if(_cd>(_e3+60*_e4[ir])){_e5[ir]++}}}_28+="&rr="+_e5.join(".");_dd.push(sc_project+"."+_cd+_16+"."+_e5.join("."))}else{_dd.push(_dc[i]);if(i==0&&_e2[2].length==32&&_16==""){_16="."+_e2[2]}}if(i==0){_e0=_16}}if(!_df){if(_dd.length==0&&_16==""){_16="."+generate_uuid()}_dd.push(sc_project+"."+_cd+_16+"."+_d9);_28+="&jg=new&rr="+_d9}_dd.sort(function(a,b){return parseInt(b.split(".")[1],10)-parseInt(a.split(".")[1],10)});for(var iv=1;iv<_dd.length;iv++){_dd[iv]=_dd[iv].replace("."+_dd[0].split(".")[2]+".",".")}var _ec=setLocal(_da,_dd,_8c,"rx",3);if(!_ec){}}else{if(_16!=".ex"){_16="."+generate_uuid();_dd=[sc_project+"."+_cd+_16+"."+_d9];var _ed=setLocal(_da,_dd,_8c,"rx",3);if(_ed){_28+="&jg=new&rr="+_d9}else{_16=".na"}}}if(_16!=""){_17="&u1="+_16.substring(1)}}else{}var _ee="";if(Object.prototype.toString.call(_b5)==="[object Array]"){var _ef=_b5.length;if(_ef>=1){_ee=get_tag_string(_b5[0])}}var _f0=_b;_f0+="&java=1&security="+_f+_17;if(typeof performance!=="undefined"){try{var _f1=performance.getEntriesByType("resource");for(var i=0;i<_f1.length;i++){var _f2=_f1[i];if(_f2.name.includes("statcounter.com/counter/counter.js")||_f2.name.includes("statcounter.com/counter/counter_test.js")){var _f3=_f2.responseEnd-_f2.fetchStart;_f0+="&sc_rum_f_s="+Math.round(_f2.requestStart)+"&sc_rum_f_e="+Math.round(_f2.responseEnd);break}}}catch(e){}}var _f4=_28+"&resolution="+get_screen_width()+"&h="+get_screen_height()+"&camefrom="+escape(_26.substring(0,600))+"&u="+get_page_url()+"&t="+get_page_title()+_b6+"&sc_snum="+_1b+_ee+"&sess=723a38";if(window.sc_counter_width&&window.sc_counter_height){_14=" width=\""+sc_counter_width+"\" height=\""+sc_counter_height+"\""}if(window.sc_remove_alt){_13=""}if(_7==1){document.writeln("Code corrupted. Insert fresh copy.")}else{if(_8==1){}else{sc_send_data()}}function sc_send_data(){if(_a==1||_15==2){_f4+="&p="+_15+"&invisible=1";var _f5=false;if(_16!=""&&typeof JSON=="object"&&JSON&&typeof JSON.stringify=="function"&&"sessionStorage" in window){_f5=true}var _f6=false;if(_f5){try{var _f7=sessionStorage.getItem("statcounter_pending");if(!_f7){var _f8={}}else{try{var _f8=JSON.parse(_f7)}catch(ignore){var _f8={}}}if(_f8[sc_project]===undefined){_f8[sc_project]={}}var now=new Date().getTime();_f8[sc_project][now]=_f4;while(true){_f7=JSON.stringify(_f8);if(_f7=="{}"){sessionStorage.removeItem("statcounter_pending");break}var _fa=_f7.split(/:.{20}/).length-1;if(_fa<20){var _fb=true;try{sessionStorage.setItem("statcounter_pending",_f7)}catch(e){if(!e.name||e.name.toLowerCase().replace(/_/g,"").substring(0,16)!=="quotaexceedederr"){throw e}_fb=false}if(_fb){break}}var _fc=false;var _fd=false;var _fe=false;for(var _ff in _f8){for(var _100 in _f8[_ff]){var _101=/jg=(\d+)/.exec(_f8[_ff][_100]);if(_101!==null){var _102=parseInt(_101[1])}else{var _102=false}if(_fc===false||(_102!==false&&_102<_fc)){if(_102!==false){_fc=_102}_fd=_ff;_fe=_100}}}if(_fe===false){break}delete _f8[_fd][_fe];if(JSON.stringify(_f8[_fd])=="{}"){delete _f8[_fd]}}for(var ts in _f8[sc_project]){(function(_104,_105){var _106=_f8[_105][_104];if(!navigator.sendBeacon){var _107=new Image();_107.onload=function(){var _108=JSON.parse(sessionStorage.getItem("statcounter_pending"));if(_108[_105]!==undefined){delete _108[_105][_104];if(JSON.stringify(_108[_105])=="{}"){delete _108[_105]}}var _109=JSON.stringify(_108);if(_109=="{}"){sessionStorage.removeItem("statcounter_pending")}else{sessionStorage.setItem("statcounter_pending",_109)}};if(_104!=now){_106+="&pg="+Math.round((now-_104)/1000)}else{_f6=true}_107.src=_f0+get_performance_url_params()+"&sc_random="+Math.random()+_106}else{if(_104!=now){_106+="&pg="+Math.round((now-_104)/1000)}else{_f6=true}var _10a=_f0+get_performance_url_params()+"&sc_random="+Math.random()+_106;navigator.sendBeacon(_10a,"");if(_f8[_105]!==undefined){delete _f8[_105][_104];if(JSON.stringify(_f8[_105])=="{}"){delete _f8[_105]}}var _10b=JSON.stringify(_f8);if(_10b=="{}"){sessionStorage.removeItem("statcounter_pending")}else{sessionStorage.setItem("statcounter_pending",_10b)}}})(parseInt(ts,10),sc_project)}}catch(e){if(_3){if(typeof encodeURIComponent!="function"){encodeURIComponent=function(s){return escape(s)}}var _10d="";for(var prop in e){_10d+="e["+prop+"]: "+e[prop]+"\n"}_10d+="unique_returning: "+_28+"\n";_10d+="uuid: "+_16+"\n";_10d+="toString(): "+" value: ["+e.toString()+"]\n";var _10f=new Image();_10f.src="https://statcounter.com/feedback/?email=javascript@statcounter.com&page_url="+encodeURIComponent(document.location.protocol+"//"+document.location.host+document.location.pathname+document.location.search+document.location.hash)+"&name=Auto%20JS&feedback_username=statcounter&pid="+sc_project+"&fake_post&user_company&feedback=pending%20exception:%20"+encodeURIComponent(_10d)}}}if(!_f5||!_f6){if(!navigator.sendBeacon){var _110=new Image();_110.src=_f0+get_performance_url_params()+"&sc_random="+Math.random()+_f4}else{var _111=_f0+get_performance_url_params()+"&sc_random="+Math.random()+_f4;navigator.sendBeacon(_111,"")}}}else{var _112=_f0+get_performance_url_params()+"&sc_random="+Math.random()+_f4+"&p="+_15;_112=_112.replace(/&/g,"&amp;");if(window.sc_text){document.writeln("<scr"+"ipt"+" src="+_112+"&amp;text="+sc_text+"></scr"+"ipt>")}else{document.writeln("<span class=\"statcounter\">"+_d+"<img src=\""+_112+"\" alt=\""+_13+"\" border=\"0\""+_14+">"+_e+"</span>")}}}_26=get_page_url();_af();_27++};function sc_process_anchor(_113){if(_113.onmousedown){var _114=_113.onmousedown;var s=_114.toString().split("\n").join(" ");var bs=s.indexOf("{");var head=s.substr(0,bs);var ps=head.indexOf("(");var pe=head.indexOf(")");var _11a=head.substring(ps+1,pe);var _11b=_11a.split(",");var body=s.substr(bs+1,s.length-bs-2);var _11d="_statcounter.clickstat_call(this,'"+_c+"');";var _11e=_11d+body;var _11f="new Function(";var _120="";var _121="";for(var sc_i=0;sc_i<_11b.length;sc_i++){_120=_121+"'"+_11b[sc_i]+"'";_121=","}if(_121==","){_120+=","}_11e=_11e.replace(/'/g,"\\'");var _123="'"+_11e+"');";var _124=_11f+_120+_123;_113.onmousedown=eval(_124)}else{_113.onmousedown=new Function("event","_statcounter.clickstat_call(this,'"+_c+"');return true;")}}function sc_none(){return}function sc_delay(){if(window.sc_click_stat){var d=window.sc_click_stat}else{var d=0}var n=new Date();var t=n.getTime()+d;while(n.getTime()<t){var n=new Date()}}function sc_clickstat_call(_128,_129){var _12a=window.sc_project;var _12b=_f;var _12c="7z|aac|avi|csv|doc|docx|exe|flv|gif|gz|jpe?g|js|mp(3|4|e?g)|mov|pdf|phps|png|ppt|rar|sit|tar|torrent|txt|wma|wmv|xls|xlsx|xml|zip";if(typeof (window.sc_download_type)=="string"){_12c=window.sc_download_type}var _12d="https?|ftp|telnet|ssh|ssl|mailto|spotify|tel";var _12e="ac|co|gov|ltd|me|mod|net|nic|nhs|org|plc|police|sch|com";var dl=new RegExp("\\.("+_12c+")$","i");var lnk=new RegExp("^("+_12d+"):","i");var _131=new RegExp("^("+_12e+")$","i");var _132=location.host.replace(/^www\./i,"");var _133=_132.split(".");var _134=_133.pop();var _135=_133.pop();if(_131.test(_135)){_134=_135+"."+_134;_135=_133.pop()}_134=_135+"."+_134;var _136="^https?://(.*)("+_134+"|webcache.googleusercontent.com)";var _137=new RegExp(_136,"i");if(_128){var _138=0;if(lnk.test(_128)){if((_137.test(_128))){if(dl.test(_128)){_138=1}else{if(window.sc_exit_link_detect&&new RegExp(sc_exit_link_detect,"i").test(_128)){_138=2}else{if(_9==2){_138=2}}}}else{_138=2}}if(_138!=0){var _139=escape(_128);if(_139.length>0){if(!_21){if(_138==2&&_18!="disabled"){try{sessionStorage.setItem("statcounter_exit_domain",_139.split("/")[2].replace(/^www\./,""))}catch(ignore){}}}var _13a=_129+"click.gif?sc_project="+_12a+"&security="+_12b+"&c="+_139+"&m="+_138+"&u="+get_page_url()+"&t="+get_page_title()+"&sess=723a38&rand="+Math.random()+_17+get_jg_rr_url_params();var _13b=new Image();_13b.onload=sc_none;_13b.src=_13a;sc_delay()}}}}var _13c="googlesyndication.com|ypn-js.overture.com|ypn-js.ysm.yahoo.com|googleads.g.doubleclick.net";var _13d="^aswift_[0-9]+$";var _13e;var _13f;var _140;var _141;function sc_adsense_click(_142){var _143=window.sc_project;var _144=_f;if(_142.src.match(_13c)){var _145=escape(_142.src)}else{var _145=escape("Google Adsense "+_142.width+"x"+_142.height)}var _146=_c+"click.gif?sc_project="+_143+"&security="+_144+"&c="+_145+"&m=2&u="+get_page_url()+"&t="+get_page_title()+"&sess=723a38&rand="+Math.random()+_17+get_jg_rr_url_params();if(!navigator.sendBeacon){var i=new Image();i.src=_146;sc_delay()}else{navigator.sendBeacon(_146,"")}}function sc_adsense_init(){var _148=/iPad|iPhone|iPod/.test(navigator.userAgent)&&!window.MSStream;var _149=/Firefox/.test(navigator.userAgent)&&/Android/.test(navigator.userAgent);if(_5&&(_148||_149)){var el=document.getElementsByTagName("iframe");for(var i=0;i<el.length;i++){if(el[i].id.substring(0,6)=="aswift"){el[i].addEventListener("mouseenter",function(e){sc_adsense_click(this)})}}}else{if(document.all&&typeof window.opera=="undefined"){var el=document.getElementsByTagName("iframe");for(var i=0;i<el.length;i++){if(el[i].src.match(_13c)||el[i].id.match(_13d)){el[i].onfocus=function(){sc_adsense_click(this)}}}}else{if(typeof window.addEventListener!="undefined"){var _14d="unload";if(_5){_14d="beforeunload";focus();window.addEventListener("blur",function(){var _14e=document.activeElement;_140=_14e;_141=new Date().getTime()})}window.addEventListener(_14d,sc_exitpage,false);window.addEventListener("mousemove",sc_getmouse,true)}}}}function sc_getmouse(e){if(typeof e.pageX=="number"){_13e=e.pageX;_13f=e.pageY}else{if(typeof e.clientX=="number"){_13e=e.clientX;_13f=e.clientY;if(document.body&&(document.body.scrollLeft||document.body.scrollTop)){_13e+=document.body.scrollLeft;_13f+=document.body.scrollTop}else{if(document.documentElement&&(document.documentElement.scrollLeft||document.documentElement.scrollTop)){_13e+=document.documentElement.scrollLeft;_13f+=document.documentElement.scrollTop}}}}}function sc_findy(obj){var y=0;while(obj){y+=obj.offsetTop;obj=obj.offsetParent}return (y)}function sc_findx(obj){var x=0;while(obj){x+=obj.offsetLeft;obj=obj.offsetParent}return (x)}function sc_exitpage(e){var ad=document.getElementsByTagName("iframe");if(typeof _13e!="undefined"){for(var i=0;i<ad.length;i++){var _157=sc_findx(ad[i]);var _158=sc_findy(ad[i]);var adW=parseInt(_157,10)+parseInt(ad[i].width,10)+15;var adH=parseInt(_158,10)+parseInt(ad[i].height,10)+10;var _15b=(_13e>(_157-10)&&_13e<adW);var _15c=(_13f>(_158-10)&&_13f<adH);if(_15c&&_15b){if(ad[i].src.match(_13c)||ad[i].id.match(_13d)){sc_adsense_click(ad[i])}}}}if(_5){if(typeof _140!="undefined"&&_140.id.substring(0,6)=="aswift"){var _15d=new Date().getTime()-_141;if(_15d<300){sc_adsense_click(_140)}}}}var _15e=0;function initiate_click_detection(){if(_9>0){if(_15e!=1){_15e=1;if(document.getElementsByTagName){var _15f=document.getElementsByTagName("a");var _160=document.getElementsByTagName("area");for(var i=0;i<_15f.length;i++){var _162=_15f[i];sc_process_anchor(_162)}for(var i=0;i<_160.length;i++){var _162=_160[i];if(typeof _162.hasAttribute==="function"&&_162.hasAttribute("href")){sc_process_anchor(_162)}}}if(typeof window.addEventListener!="undefined"){window.addEventListener("load",sc_adsense_init,false)}else{if(typeof document.addEventListener!="undefined"){document.addEventListener("load",sc_adsense_init,false)}else{if(typeof window.attachEvent!="undefined"){window.attachEvent("onload",sc_adsense_init)}else{if(typeof window.onload=="function"){var _163=onload;window.onload=function(){_163();sc_adsense_init()}}else{window.onload=sc_adsense_init}}}}}}}initiate_click_detection();api.get_session=function(){try{var _164=_readCookie("session_id")}catch(e){var _164=false}if(!_164){_164=generate_uuid(16);var ok=_writeCookie("session_id",_164,_8c,"session");if(!ok){return false}}return _16+"."+_164};api.get_tab_session=function(){var _166=false;try{_166=sessionStorage.getItem("statcounter_tab_session");if(!_166){_166=generate_uuid(16);try{sessionStorage.setItem("statcounter_tab_session",_166)}catch(e){_166=false}}}catch(e){_166=false}return _166};api.record=function(val){sessionStorage.setItem("sc-record",val);_af()};api.is_recording=function(){try{return sessionStorage.getItem("sc-record").split("-")}catch(ignore){return false}};api.clickstat_call=sc_clickstat_call;api.get_pending_tags=function(){return _4};return api}catch(e){if(_3){if(typeof encodeURIComponent!="function"){encodeURIComponent=function(s){return escape(s)}}var _169="";for(var prop in e){_169+="property: "+prop+" value: ["+e[prop]+"]\n"}_169+="toString(): "+" value: ["+e.toString()+"]\n";var _16b=new Image();_16b.src="https://statcounter.com/feedback/?email=javascript@statcounter.com&page_url="+encodeURIComponent(document.location.protocol+"//"+document.location.host+document.location.pathname+document.location.search+document.location.hash)+"&name=Auto%20JS&feedback_username=statcounter&pid="+sc_project+"&fake_post&user_company&feedback=consistent%20uniques%20js%20exception:%20"+encodeURIComponent(_169)}_28="";_17="&u1=f2"}}(_statcounter);_statcounter.record_pageview();