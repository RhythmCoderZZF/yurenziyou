var __wxAppData = {};
var __wxRoute;
var __wxRouteBegin;
var __wxAppCode__ = {};
var global = {};
var __wxAppCurrentFile__;
if(typeof __WXML_GLOBAL__ !== 'undefined'){
  delete __WXML_GLOBAL__.ops_cached//remove ops_cached(v8 下会有 cache)
}
// var Component = Component || function() {};
// var definePlugin = definePlugin || function() {};
// var requirePlugin = requirePlugin || function() {};
// var Behavior = Behavior || function() {};
var $gwx;
  
/*v0.5vv_20190312_syb_scopedata*/global.__wcc_version__='v0.5vv_20190312_syb_scopedata';global.__wcc_version_info__={"customComponents":true,"fixZeroRpx":true,"propValueDeepCopy":false};
var $gwxc
var $gaic={}
$gwx=function(path,global){
if(typeof global === 'undefined') global={};if(typeof __WXML_GLOBAL__ === 'undefined') {__WXML_GLOBAL__={};
}__WXML_GLOBAL__.modules = __WXML_GLOBAL__.modules || {};
function _(a,b){if(typeof(b)!='undefined')a.children.push(b);}
function _v(k){if(typeof(k)!='undefined')return {tag:'virtual','wxKey':k,children:[]};return {tag:'virtual',children:[]};}
function _n(tag){$gwxc++;if($gwxc>=16000){throw 'Dom limit exceeded, please check if there\'s any mistake you\'ve made.'};return {tag:'wx-'+tag,attr:{},children:[],n:[],raw:{},generics:{}}}
function _p(a,b){b&&a.properities.push(b);}
function _s(scope,env,key){return typeof(scope[key])!='undefined'?scope[key]:env[key]}
function _wp(m){console.warn("WXMLRT_$gwx:"+m)}
function _wl(tname,prefix){_wp(prefix+':-1:-1:-1: Template `' + tname + '` is being called recursively, will be stop.')}
$gwn=console.warn;
$gwl=console.log;
function $gwh()
{
function x()
{
}
x.prototype = 
{
hn: function( obj, all )
{
if( typeof(obj) == 'object' )
{
var cnt=0;
var any1=false,any2=false;
for(var x in obj)
{
any1=any1|x==='__value__';
any2=any2|x==='__wxspec__';
cnt++;
if(cnt>2)break;
}
return cnt == 2 && any1 && any2 && ( all || obj.__wxspec__ !== 'm' || this.hn(obj.__value__) === 'h' ) ? "h" : "n";
}
return "n";
},
nh: function( obj, special )
{
return { __value__: obj, __wxspec__: special ? special : true }
},
rv: function( obj )
{
return this.hn(obj,true)==='n'?obj:this.rv(obj.__value__);
},
hm: function( obj )
{
if( typeof(obj) == 'object' )
{
var cnt=0;
var any1=false,any2=false;
for(var x in obj)
{
any1=any1|x==='__value__';
any2=any2|x==='__wxspec__';
cnt++;
if(cnt>2)break;
}
return cnt == 2 && any1 && any2 && (obj.__wxspec__ === 'm' || this.hm(obj.__value__) );
}
return false;
}
}
return new x;
}
wh=$gwh();
function $gstack(s){
var tmp=s.split('\n '+' '+' '+' ');
for(var i=0;i<tmp.length;++i){
if(0==i) continue;
if(")"===tmp[i][tmp[i].length-1])
tmp[i]=tmp[i].replace(/\s\(.*\)$/,"");
else
tmp[i]="at anonymous function";
}
return tmp.join('\n '+' '+' '+' ');
}
function $gwrt( should_pass_type_info )
{
function ArithmeticEv( ops, e, s, g, o )
{
var _f = false;
var rop = ops[0][1];
var _a,_b,_c,_d, _aa, _bb;
switch( rop )
{
case '?:':
_a = rev( ops[1], e, s, g, o, _f );
_c = should_pass_type_info && ( wh.hn(_a) === 'h' );
_d = wh.rv( _a ) ? rev( ops[2], e, s, g, o, _f ) : rev( ops[3], e, s, g, o, _f );
_d = _c && wh.hn( _d ) === 'n' ? wh.nh( _d, 'c' ) : _d;
return _d;
break;
case '&&':
_a = rev( ops[1], e, s, g, o, _f );
_c = should_pass_type_info && ( wh.hn(_a) === 'h' );
_d = wh.rv( _a ) ? rev( ops[2], e, s, g, o, _f ) : wh.rv( _a );
_d = _c && wh.hn( _d ) === 'n' ? wh.nh( _d, 'c' ) : _d;
return _d;
break;
case '||':
_a = rev( ops[1], e, s, g, o, _f );
_c = should_pass_type_info && ( wh.hn(_a) === 'h' );
_d = wh.rv( _a ) ? wh.rv(_a) : rev( ops[2], e, s, g, o, _f );
_d = _c && wh.hn( _d ) === 'n' ? wh.nh( _d, 'c' ) : _d;
return _d;
break;
case '+':
case '*':
case '/':
case '%':
case '|':
case '^':
case '&':
case '===':
case '==':
case '!=':
case '!==':
case '>=':
case '<=':
case '>':
case '<':
case '<<':
case '>>':
_a = rev( ops[1], e, s, g, o, _f );
_b = rev( ops[2], e, s, g, o, _f );
_c = should_pass_type_info && (wh.hn( _a ) === 'h' || wh.hn( _b ) === 'h');
switch( rop )
{
case '+':
_d = wh.rv( _a ) + wh.rv( _b );
break;
case '*':
_d = wh.rv( _a ) * wh.rv( _b );
break;
case '/':
_d = wh.rv( _a ) / wh.rv( _b );
break;
case '%':
_d = wh.rv( _a ) % wh.rv( _b );
break;
case '|':
_d = wh.rv( _a ) | wh.rv( _b );
break;
case '^':
_d = wh.rv( _a ) ^ wh.rv( _b );
break;
case '&':
_d = wh.rv( _a ) & wh.rv( _b );
break;
case '===':
_d = wh.rv( _a ) === wh.rv( _b );
break;
case '==':
_d = wh.rv( _a ) == wh.rv( _b );
break;
case '!=':
_d = wh.rv( _a ) != wh.rv( _b );
break;
case '!==':
_d = wh.rv( _a ) !== wh.rv( _b );
break;
case '>=':
_d = wh.rv( _a ) >= wh.rv( _b );
break;
case '<=':
_d = wh.rv( _a ) <= wh.rv( _b );
break;
case '>':
_d = wh.rv( _a ) > wh.rv( _b );
break;
case '<':
_d = wh.rv( _a ) < wh.rv( _b );
break;
case '<<':
_d = wh.rv( _a ) << wh.rv( _b );
break;
case '>>':
_d = wh.rv( _a ) >> wh.rv( _b );
break;
default:
break;
}
return _c ? wh.nh( _d, "c" ) : _d;
break;
case '-':
_a = ops.length === 3 ? rev( ops[1], e, s, g, o, _f ) : 0;
_b = ops.length === 3 ? rev( ops[2], e, s, g, o, _f ) : rev( ops[1], e, s, g, o, _f );
_c = should_pass_type_info && (wh.hn( _a ) === 'h' || wh.hn( _b ) === 'h');
_d = _c ? wh.rv( _a ) - wh.rv( _b ) : _a - _b;
return _c ? wh.nh( _d, "c" ) : _d;
break;
case '!':
_a = rev( ops[1], e, s, g, o, _f );
_c = should_pass_type_info && (wh.hn( _a ) == 'h');
_d = !wh.rv(_a);
return _c ? wh.nh( _d, "c" ) : _d;
case '~':
_a = rev( ops[1], e, s, g, o, _f );
_c = should_pass_type_info && (wh.hn( _a ) == 'h');
_d = ~wh.rv(_a);
return _c ? wh.nh( _d, "c" ) : _d;
default:
$gwn('unrecognized op' + rop );
}
}
function rev( ops, e, s, g, o, newap )
{
var op = ops[0];
var _f = false;
if ( typeof newap !== "undefined" ) o.ap = newap;
if( typeof(op)==='object' )
{
var vop=op[0];
var _a, _aa, _b, _bb, _c, _d, _s, _e, _ta, _tb, _td;
switch(vop)
{
case 2:
return ArithmeticEv(ops,e,s,g,o);
break;
case 4: 
return rev( ops[1], e, s, g, o, _f );
break;
case 5: 
switch( ops.length )
{
case 2: 
_a = rev( ops[1],e,s,g,o,_f );
return should_pass_type_info?[_a]:[wh.rv(_a)];
return [_a];
break;
case 1: 
return [];
break;
default:
_a = rev( ops[1],e,s,g,o,_f );
_b = rev( ops[2],e,s,g,o,_f );
_a.push( 
should_pass_type_info ?
_b :
wh.rv( _b )
);
return _a;
break;
}
break;
case 6:
_a = rev(ops[1],e,s,g,o);
var ap = o.ap;
_ta = wh.hn(_a)==='h';
_aa = _ta ? wh.rv(_a) : _a;
o.is_affected |= _ta;
if( should_pass_type_info )
{
if( _aa===null || typeof(_aa) === 'undefined' )
{
return _ta ? wh.nh(undefined, 'e') : undefined;
}
_b = rev(ops[2],e,s,g,o,_f);
_tb = wh.hn(_b) === 'h';
_bb = _tb ? wh.rv(_b) : _b;
o.ap = ap;
o.is_affected |= _tb;
if( _bb===null || typeof(_bb) === 'undefined' || 
_bb === "__proto__" || _bb === "prototype" || _bb === "caller" ) 
{
return (_ta || _tb) ? wh.nh(undefined, 'e') : undefined;
}
_d = _aa[_bb];
if ( typeof _d === 'function' && !ap ) _d = undefined;
_td = wh.hn(_d)==='h';
o.is_affected |= _td;
return (_ta || _tb) ? (_td ? _d : wh.nh(_d, 'e')) : _d;
}
else
{
if( _aa===null || typeof(_aa) === 'undefined' )
{
return undefined;
}
_b = rev(ops[2],e,s,g,o,_f);
_tb = wh.hn(_b) === 'h';
_bb = _tb ? wh.rv(_b) : _b;
o.ap = ap;
o.is_affected |= _tb;
if( _bb===null || typeof(_bb) === 'undefined' || 
_bb === "__proto__" || _bb === "prototype" || _bb === "caller" ) 
{
return undefined;
}
_d = _aa[_bb];
if ( typeof _d === 'function' && !ap ) _d = undefined;
_td = wh.hn(_d)==='h';
o.is_affected |= _td;
return _td ? wh.rv(_d) : _d;
}
case 7: 
switch(ops[1][0])
{
case 11:
o.is_affected |= wh.hn(g)==='h';
return g;
case 3:
_s = wh.rv( s );
_e = wh.rv( e );
_b = ops[1][1];
if (g && g.f && g.f.hasOwnProperty(_b) )
{
_a = g.f;
o.ap = true;
}
else
{
_a = _s && _s.hasOwnProperty(_b) ? 
s : (_e && _e.hasOwnProperty(_b) ? e : undefined );
}
if( should_pass_type_info )
{
if( _a )
{
_ta = wh.hn(_a) === 'h';
_aa = _ta ? wh.rv( _a ) : _a;
_d = _aa[_b];
_td = wh.hn(_d) === 'h';
o.is_affected |= _ta || _td;
_d = _ta && !_td ? wh.nh(_d,'e') : _d;
return _d;
}
}
else
{
if( _a )
{
_ta = wh.hn(_a) === 'h';
_aa = _ta ? wh.rv( _a ) : _a;
_d = _aa[_b];
_td = wh.hn(_d) === 'h';
o.is_affected |= _ta || _td;
return wh.rv(_d);
}
}
return undefined;
}
break;
case 8: 
_a = {};
_a[ops[1]] = rev(ops[2],e,s,g,o,_f);
return _a;
break;
case 9: 
_a = rev(ops[1],e,s,g,o,_f);
_b = rev(ops[2],e,s,g,o,_f);
function merge( _a, _b, _ow )
{
var ka, _bbk;
_ta = wh.hn(_a)==='h';
_tb = wh.hn(_b)==='h';
_aa = wh.rv(_a);
_bb = wh.rv(_b);
for(var k in _bb)
{
if ( _ow || !_aa.hasOwnProperty(k) )
{
_aa[k] = should_pass_type_info ? (_tb ? wh.nh(_bb[k],'e') : _bb[k]) : wh.rv(_bb[k]);
}
}
return _a;
}
var _c = _a
var _ow = true
if ( typeof(ops[1][0]) === "object" && ops[1][0][0] === 10 ) {
_a = _b
_b = _c
_ow = false
}
if ( typeof(ops[1][0]) === "object" && ops[1][0][0] === 10 ) {
var _r = {}
return merge( merge( _r, _a, _ow ), _b, _ow );
}
else
return merge( _a, _b, _ow );
break;
case 10:
_a = rev(ops[1],e,s,g,o,_f);
_a = should_pass_type_info ? _a : wh.rv( _a );
return _a ;
break;
case 12:
var _r;
_a = rev(ops[1],e,s,g,o);
if ( !o.ap )
{
return should_pass_type_info && wh.hn(_a)==='h' ? wh.nh( _r, 'f' ) : _r;
}
var ap = o.ap;
_b = rev(ops[2],e,s,g,o,_f);
o.ap = ap;
_ta = wh.hn(_a)==='h';
_tb = _ca(_b);
_aa = wh.rv(_a);	
_bb = wh.rv(_b); snap_bb=$gdc(_bb,"nv_");
try{
_r = typeof _aa === "function" ? $gdc(_aa.apply(null, snap_bb)) : undefined;
} catch (e){
e.message = e.message.replace(/nv_/g,"");
e.stack = e.stack.substring(0,e.stack.indexOf("\n", e.stack.lastIndexOf("at nv_")));
e.stack = e.stack.replace(/\snv_/g," "); 
e.stack = $gstack(e.stack);	
if(g.debugInfo)
{
e.stack += "\n "+" "+" "+" at "+g.debugInfo[0]+":"+g.debugInfo[1]+":"+g.debugInfo[2];
console.error(e);
}
_r = undefined;
}
return should_pass_type_info && (_tb || _ta) ? wh.nh( _r, 'f' ) : _r;
}
}
else
{
if( op === 3 || op === 1) return ops[1];
else if( op === 11 ) 
{
var _a='';
for( var i = 1 ; i < ops.length ; i++ )
{
var xp = wh.rv(rev(ops[i],e,s,g,o,_f));
_a += typeof(xp) === 'undefined' ? '' : xp;
}
return _a;
}
}
}
function wrapper( ops, e, s, g, o, newap )
{
if( ops[0] == '11182016' )
{
g.debugInfo = ops[2];
return rev( ops[1], e, s, g, o, newap );
}
else
{
g.debugInfo = null;
return rev( ops, e, s, g, o, newap );
}
}
return wrapper;
}
gra=$gwrt(true); 
grb=$gwrt(false); 
function TestTest( expr, ops, e,s,g, expect_a, expect_b, expect_affected )
{
{
var o = {is_affected:false};
var a = gra( ops, e,s,g, o );
if( JSON.stringify(a) != JSON.stringify( expect_a )
|| o.is_affected != expect_affected )
{
console.warn( "A. " + expr + " get result " + JSON.stringify(a) + ", " + o.is_affected + ", but " + JSON.stringify( expect_a ) + ", " + expect_affected + " is expected" );
}
}
{
var o = {is_affected:false};
var a = grb( ops, e,s,g, o );
if( JSON.stringify(a) != JSON.stringify( expect_b )
|| o.is_affected != expect_affected )
{
console.warn( "B. " + expr + " get result " + JSON.stringify(a) + ", " + o.is_affected + ", but " + JSON.stringify( expect_b ) + ", " + expect_affected + " is expected" );
}
}
}

function wfor( to_iter, func, env, _s, global, father, itemname, indexname, keyname )
{
var _n = wh.hn( to_iter ) === 'n'; 
var scope = wh.rv( _s ); 
var has_old_item = scope.hasOwnProperty(itemname);
var has_old_index = scope.hasOwnProperty(indexname);
var old_item = scope[itemname];
var old_index = scope[indexname];
var full = Object.prototype.toString.call(wh.rv(to_iter));
var type = full[8]; 
if( type === 'N' && full[10] === 'l' ) type = 'X'; 
var _y;
if( _n )
{
if( type === 'A' ) 
{
var r_iter_item;
for( var i = 0 ; i < to_iter.length ; i++ )
{
scope[itemname] = to_iter[i];
scope[indexname] = _n ? i : wh.nh(i, 'h');
r_iter_item = wh.rv(to_iter[i]);
var key = keyname && r_iter_item ? (keyname==="*this" ? r_iter_item : wh.rv(r_iter_item[keyname])) : undefined;
_y = _v(key);
_(father,_y);
func( env, scope, _y, global );
}
}
else if( type === 'O' ) 
{
var i = 0;
var r_iter_item;
for( var k in to_iter )
{
scope[itemname] = to_iter[k];
scope[indexname] = _n ? k : wh.nh(k, 'h');
r_iter_item = wh.rv(to_iter[k]);
var key = keyname && r_iter_item ? (keyname==="*this" ? r_iter_item : wh.rv(r_iter_item[keyname])) : undefined;
_y = _v(key);
_(father,_y);
func( env,scope,_y,global );
i++;
}
}
else if( type === 'S' ) 
{
for( var i = 0 ; i < to_iter.length ; i++ )
{
scope[itemname] = to_iter[i];
scope[indexname] = _n ? i : wh.nh(i, 'h');
_y = _v( to_iter[i] + i );
_(father,_y);
func( env,scope,_y,global );
}
}
else if( type === 'N' ) 
{
for( var i = 0 ; i < to_iter ; i++ )
{
scope[itemname] = i;
scope[indexname] = _n ? i : wh.nh(i, 'h');
_y = _v( i );
_(father,_y);
func(env,scope,_y,global);
}
}
else
{
}
}
else
{
var r_to_iter = wh.rv(to_iter);
var r_iter_item, iter_item;
if( type === 'A' ) 
{
for( var i = 0 ; i < r_to_iter.length ; i++ )
{
iter_item = r_to_iter[i];
iter_item = wh.hn(iter_item)==='n' ? wh.nh(iter_item,'h') : iter_item;
r_iter_item = wh.rv( iter_item );
scope[itemname] = iter_item
scope[indexname] = _n ? i : wh.nh(i, 'h');
var key = keyname && r_iter_item ? (keyname==="*this" ? r_iter_item : wh.rv(r_iter_item[keyname])) : undefined;
_y = _v(key);
_(father,_y);
func( env, scope, _y, global );
}
}
else if( type === 'O' ) 
{
var i=0;
for( var k in r_to_iter )
{
iter_item = r_to_iter[k];
iter_item = wh.hn(iter_item)==='n'? wh.nh(iter_item,'h') : iter_item;
r_iter_item = wh.rv( iter_item );
scope[itemname] = iter_item;
scope[indexname] = _n ? k : wh.nh(k, 'h');
var key = keyname && r_iter_item ? (keyname==="*this" ? r_iter_item : wh.rv(r_iter_item[keyname])) : undefined;
_y=_v(key);
_(father,_y);
func( env, scope, _y, global );
i++
}
}
else if( type === 'S' ) 
{
for( var i = 0 ; i < r_to_iter.length ; i++ )
{
iter_item = wh.nh(r_to_iter[i],'h');
scope[itemname] = iter_item;
scope[indexname] = _n ? i : wh.nh(i, 'h');
_y = _v( to_iter[i] + i );
_(father,_y);
func( env, scope, _y, global );
}
}
else if( type === 'N' ) 
{
for( var i = 0 ; i < r_to_iter ; i++ )
{
iter_item = wh.nh(i,'h');
scope[itemname] = iter_item;
scope[indexname]= _n ? i : wh.nh(i,'h');
_y = _v( i );
_(father,_y);
func(env,scope,_y,global);
}
}
else
{
}
}
if(has_old_item)
{
scope[itemname]=old_item;
}
else
{
delete scope[itemname];
}
if(has_old_index)
{
scope[indexname]=old_index;
}
else
{
delete scope[indexname];
}
}

function _ca(o)
{ 
if ( wh.hn(o) == 'h' ) return true;
if ( typeof o !== "object" ) return false;
for(var i in o){ 
if ( o.hasOwnProperty(i) ){
if (_ca(o[i])) return true;
}
}
return false;
}
function _da( node, attrname, opindex, raw, o )
{
var isaffected = false;
var value = $gdc( raw, "", 2 );
if ( o.ap && value && value.constructor===Function ) 
{
attrname = "$wxs:" + attrname; 
node.attr["$gdc"] = $gdc;
}
if ( o.is_affected || _ca(raw) ) 
{
node.n.push( attrname );
node.raw[attrname] = raw;
}
node.attr[attrname] = value;
}
function _r( node, attrname, opindex, env, scope, global ) 
{
global.opindex=opindex;
var o = {}, _env;
var a = grb( z[opindex], env, scope, global, o );
_da( node, attrname, opindex, a, o );
}
function _rz( z, node, attrname, opindex, env, scope, global ) 
{
global.opindex=opindex;
var o = {}, _env;
var a = grb( z[opindex], env, scope, global, o );
_da( node, attrname, opindex, a, o );
}
function _o( opindex, env, scope, global )
{
global.opindex=opindex;
var nothing = {};
var r = grb( z[opindex], env, scope, global, nothing );
return (r&&r.constructor===Function) ? undefined : r;
}
function _oz( z, opindex, env, scope, global )
{
global.opindex=opindex;
var nothing = {};
var r = grb( z[opindex], env, scope, global, nothing );
return (r&&r.constructor===Function) ? undefined : r;
}
function _1( opindex, env, scope, global, o )
{
var o = o || {};
global.opindex=opindex;
return gra( z[opindex], env, scope, global, o );
}
function _1z( z, opindex, env, scope, global, o )
{
var o = o || {};
global.opindex=opindex;
return gra( z[opindex], env, scope, global, o );
}
function _2( opindex, func, env, scope, global, father, itemname, indexname, keyname )
{
var o = {};
var to_iter = _1( opindex, env, scope, global );
wfor( to_iter, func, env, scope, global, father, itemname, indexname, keyname );
}
function _2z( z, opindex, func, env, scope, global, father, itemname, indexname, keyname )
{
var o = {};
var to_iter = _1z( z, opindex, env, scope, global );
wfor( to_iter, func, env, scope, global, father, itemname, indexname, keyname );
}


function _m(tag,attrs,generics,env,scope,global)
{
var tmp=_n(tag);
var base=0;
for(var i = 0 ; i < attrs.length ; i+=2 )
{
if(base+attrs[i+1]<0)
{
tmp.attr[attrs[i]]=true;
}
else
{
_r(tmp,attrs[i],base+attrs[i+1],env,scope,global);
if(base===0)base=attrs[i+1];
}
}
for(var i=0;i<generics.length;i+=2)
{
if(base+generics[i+1]<0)
{
tmp.generics[generics[i]]="";
}
else
{
var $t=grb(z[base+generics[i+1]],env,scope,global);
if ($t!="") $t="wx-"+$t;
tmp.generics[generics[i]]=$t;
if(base===0)base=generics[i+1];
}
}
return tmp;
}
function _mz(z,tag,attrs,generics,env,scope,global)
{
var tmp=_n(tag);
var base=0;
for(var i = 0 ; i < attrs.length ; i+=2 )
{
if(base+attrs[i+1]<0)
{
tmp.attr[attrs[i]]=true;
}
else
{
_rz(z, tmp,attrs[i],base+attrs[i+1],env,scope,global);
if(base===0)base=attrs[i+1];
}
}
for(var i=0;i<generics.length;i+=2)
{
if(base+generics[i+1]<0)
{
tmp.generics[generics[i]]="";
}
else
{
var $t=grb(z[base+generics[i+1]],env,scope,global);
if ($t!="") $t="wx-"+$t;
tmp.generics[generics[i]]=$t;
if(base===0)base=generics[i+1];
}
}
return tmp;
}

var nf_init=function(){
if(typeof __WXML_GLOBAL__==="undefined"||undefined===__WXML_GLOBAL__.wxs_nf_init){
nf_init_Object();nf_init_Function();nf_init_Array();nf_init_String();nf_init_Boolean();nf_init_Number();nf_init_Math();nf_init_Date();nf_init_RegExp();
}
if(typeof __WXML_GLOBAL__!=="undefined") __WXML_GLOBAL__.wxs_nf_init=true;
};
var nf_init_Object=function(){
Object.defineProperty(Object.prototype,"nv_constructor",{writable:true,value:"Object"})
Object.defineProperty(Object.prototype,"nv_toString",{writable:true,value:function(){return "[object Object]"}})
}
var nf_init_Function=function(){
Object.defineProperty(Function.prototype,"nv_constructor",{writable:true,value:"Function"})
Object.defineProperty(Function.prototype,"nv_length",{get:function(){return this.length;},set:function(){}});
Object.defineProperty(Function.prototype,"nv_toString",{writable:true,value:function(){return "[function Function]"}})
}
var nf_init_Array=function(){
Object.defineProperty(Array.prototype,"nv_toString",{writable:true,value:function(){return this.nv_join();}})
Object.defineProperty(Array.prototype,"nv_join",{writable:true,value:function(s){
s=undefined==s?',':s;
var r="";
for(var i=0;i<this.length;++i){
if(0!=i) r+=s;
if(null==this[i]||undefined==this[i]) r+='';	
else if(typeof this[i]=='function') r+=this[i].nv_toString();
else if(typeof this[i]=='object'&&this[i].nv_constructor==="Array") r+=this[i].nv_join();
else r+=this[i].toString();
}
return r;
}})
Object.defineProperty(Array.prototype,"nv_constructor",{writable:true,value:"Array"})
Object.defineProperty(Array.prototype,"nv_concat",{writable:true,value:Array.prototype.concat})
Object.defineProperty(Array.prototype,"nv_pop",{writable:true,value:Array.prototype.pop})
Object.defineProperty(Array.prototype,"nv_push",{writable:true,value:Array.prototype.push})
Object.defineProperty(Array.prototype,"nv_reverse",{writable:true,value:Array.prototype.reverse})
Object.defineProperty(Array.prototype,"nv_shift",{writable:true,value:Array.prototype.shift})
Object.defineProperty(Array.prototype,"nv_slice",{writable:true,value:Array.prototype.slice})
Object.defineProperty(Array.prototype,"nv_sort",{writable:true,value:Array.prototype.sort})
Object.defineProperty(Array.prototype,"nv_splice",{writable:true,value:Array.prototype.splice})
Object.defineProperty(Array.prototype,"nv_unshift",{writable:true,value:Array.prototype.unshift})
Object.defineProperty(Array.prototype,"nv_indexOf",{writable:true,value:Array.prototype.indexOf})
Object.defineProperty(Array.prototype,"nv_lastIndexOf",{writable:true,value:Array.prototype.lastIndexOf})
Object.defineProperty(Array.prototype,"nv_every",{writable:true,value:Array.prototype.every})
Object.defineProperty(Array.prototype,"nv_some",{writable:true,value:Array.prototype.some})
Object.defineProperty(Array.prototype,"nv_forEach",{writable:true,value:Array.prototype.forEach})
Object.defineProperty(Array.prototype,"nv_map",{writable:true,value:Array.prototype.map})
Object.defineProperty(Array.prototype,"nv_filter",{writable:true,value:Array.prototype.filter})
Object.defineProperty(Array.prototype,"nv_reduce",{writable:true,value:Array.prototype.reduce})
Object.defineProperty(Array.prototype,"nv_reduceRight",{writable:true,value:Array.prototype.reduceRight})
Object.defineProperty(Array.prototype,"nv_length",{get:function(){return this.length;},set:function(value){this.length=value;}});
}
var nf_init_String=function(){
Object.defineProperty(String.prototype,"nv_constructor",{writable:true,value:"String"})
Object.defineProperty(String.prototype,"nv_toString",{writable:true,value:String.prototype.toString})
Object.defineProperty(String.prototype,"nv_valueOf",{writable:true,value:String.prototype.valueOf})
Object.defineProperty(String.prototype,"nv_charAt",{writable:true,value:String.prototype.charAt})
Object.defineProperty(String.prototype,"nv_charCodeAt",{writable:true,value:String.prototype.charCodeAt})
Object.defineProperty(String.prototype,"nv_concat",{writable:true,value:String.prototype.concat})
Object.defineProperty(String.prototype,"nv_indexOf",{writable:true,value:String.prototype.indexOf})
Object.defineProperty(String.prototype,"nv_lastIndexOf",{writable:true,value:String.prototype.lastIndexOf})
Object.defineProperty(String.prototype,"nv_localeCompare",{writable:true,value:String.prototype.localeCompare})
Object.defineProperty(String.prototype,"nv_match",{writable:true,value:String.prototype.match})
Object.defineProperty(String.prototype,"nv_replace",{writable:true,value:String.prototype.replace})
Object.defineProperty(String.prototype,"nv_search",{writable:true,value:String.prototype.search})
Object.defineProperty(String.prototype,"nv_slice",{writable:true,value:String.prototype.slice})
Object.defineProperty(String.prototype,"nv_split",{writable:true,value:String.prototype.split})
Object.defineProperty(String.prototype,"nv_substring",{writable:true,value:String.prototype.substring})
Object.defineProperty(String.prototype,"nv_toLowerCase",{writable:true,value:String.prototype.toLowerCase})
Object.defineProperty(String.prototype,"nv_toLocaleLowerCase",{writable:true,value:String.prototype.toLocaleLowerCase})
Object.defineProperty(String.prototype,"nv_toUpperCase",{writable:true,value:String.prototype.toUpperCase})
Object.defineProperty(String.prototype,"nv_toLocaleUpperCase",{writable:true,value:String.prototype.toLocaleUpperCase})
Object.defineProperty(String.prototype,"nv_trim",{writable:true,value:String.prototype.trim})
Object.defineProperty(String.prototype,"nv_length",{get:function(){return this.length;},set:function(value){this.length=value;}});
}
var nf_init_Boolean=function(){
Object.defineProperty(Boolean.prototype,"nv_constructor",{writable:true,value:"Boolean"})
Object.defineProperty(Boolean.prototype,"nv_toString",{writable:true,value:Boolean.prototype.toString})
Object.defineProperty(Boolean.prototype,"nv_valueOf",{writable:true,value:Boolean.prototype.valueOf})
}
var nf_init_Number=function(){
Object.defineProperty(Number,"nv_MAX_VALUE",{writable:false,value:Number.MAX_VALUE})
Object.defineProperty(Number,"nv_MIN_VALUE",{writable:false,value:Number.MIN_VALUE})
Object.defineProperty(Number,"nv_NEGATIVE_INFINITY",{writable:false,value:Number.NEGATIVE_INFINITY})
Object.defineProperty(Number,"nv_POSITIVE_INFINITY",{writable:false,value:Number.POSITIVE_INFINITY})
Object.defineProperty(Number.prototype,"nv_constructor",{writable:true,value:"Number"})
Object.defineProperty(Number.prototype,"nv_toString",{writable:true,value:Number.prototype.toString})
Object.defineProperty(Number.prototype,"nv_toLocaleString",{writable:true,value:Number.prototype.toLocaleString})
Object.defineProperty(Number.prototype,"nv_valueOf",{writable:true,value:Number.prototype.valueOf})
Object.defineProperty(Number.prototype,"nv_toFixed",{writable:true,value:Number.prototype.toFixed})
Object.defineProperty(Number.prototype,"nv_toExponential",{writable:true,value:Number.prototype.toExponential})
Object.defineProperty(Number.prototype,"nv_toPrecision",{writable:true,value:Number.prototype.toPrecision})
}
var nf_init_Math=function(){
Object.defineProperty(Math,"nv_E",{writable:false,value:Math.E})
Object.defineProperty(Math,"nv_LN10",{writable:false,value:Math.LN10})
Object.defineProperty(Math,"nv_LN2",{writable:false,value:Math.LN2})
Object.defineProperty(Math,"nv_LOG2E",{writable:false,value:Math.LOG2E})
Object.defineProperty(Math,"nv_LOG10E",{writable:false,value:Math.LOG10E})
Object.defineProperty(Math,"nv_PI",{writable:false,value:Math.PI})
Object.defineProperty(Math,"nv_SQRT1_2",{writable:false,value:Math.SQRT1_2})
Object.defineProperty(Math,"nv_SQRT2",{writable:false,value:Math.SQRT2})
Object.defineProperty(Math,"nv_abs",{writable:false,value:Math.abs})
Object.defineProperty(Math,"nv_acos",{writable:false,value:Math.acos})
Object.defineProperty(Math,"nv_asin",{writable:false,value:Math.asin})
Object.defineProperty(Math,"nv_atan",{writable:false,value:Math.atan})
Object.defineProperty(Math,"nv_atan2",{writable:false,value:Math.atan2})
Object.defineProperty(Math,"nv_ceil",{writable:false,value:Math.ceil})
Object.defineProperty(Math,"nv_cos",{writable:false,value:Math.cos})
Object.defineProperty(Math,"nv_exp",{writable:false,value:Math.exp})
Object.defineProperty(Math,"nv_floor",{writable:false,value:Math.floor})
Object.defineProperty(Math,"nv_log",{writable:false,value:Math.log})
Object.defineProperty(Math,"nv_max",{writable:false,value:Math.max})
Object.defineProperty(Math,"nv_min",{writable:false,value:Math.min})
Object.defineProperty(Math,"nv_pow",{writable:false,value:Math.pow})
Object.defineProperty(Math,"nv_random",{writable:false,value:Math.random})
Object.defineProperty(Math,"nv_round",{writable:false,value:Math.round})
Object.defineProperty(Math,"nv_sin",{writable:false,value:Math.sin})
Object.defineProperty(Math,"nv_sqrt",{writable:false,value:Math.sqrt})
Object.defineProperty(Math,"nv_tan",{writable:false,value:Math.tan})
}
var nf_init_Date=function(){
Object.defineProperty(Date.prototype,"nv_constructor",{writable:true,value:"Date"})
Object.defineProperty(Date,"nv_parse",{writable:true,value:Date.parse})
Object.defineProperty(Date,"nv_UTC",{writable:true,value:Date.UTC})
Object.defineProperty(Date,"nv_now",{writable:true,value:Date.now})
Object.defineProperty(Date.prototype,"nv_toString",{writable:true,value:Date.prototype.toString})
Object.defineProperty(Date.prototype,"nv_toDateString",{writable:true,value:Date.prototype.toDateString})
Object.defineProperty(Date.prototype,"nv_toTimeString",{writable:true,value:Date.prototype.toTimeString})
Object.defineProperty(Date.prototype,"nv_toLocaleString",{writable:true,value:Date.prototype.toLocaleString})
Object.defineProperty(Date.prototype,"nv_toLocaleDateString",{writable:true,value:Date.prototype.toLocaleDateString})
Object.defineProperty(Date.prototype,"nv_toLocaleTimeString",{writable:true,value:Date.prototype.toLocaleTimeString})
Object.defineProperty(Date.prototype,"nv_valueOf",{writable:true,value:Date.prototype.valueOf})
Object.defineProperty(Date.prototype,"nv_getTime",{writable:true,value:Date.prototype.getTime})
Object.defineProperty(Date.prototype,"nv_getFullYear",{writable:true,value:Date.prototype.getFullYear})
Object.defineProperty(Date.prototype,"nv_getUTCFullYear",{writable:true,value:Date.prototype.getUTCFullYear})
Object.defineProperty(Date.prototype,"nv_getMonth",{writable:true,value:Date.prototype.getMonth})
Object.defineProperty(Date.prototype,"nv_getUTCMonth",{writable:true,value:Date.prototype.getUTCMonth})
Object.defineProperty(Date.prototype,"nv_getDate",{writable:true,value:Date.prototype.getDate})
Object.defineProperty(Date.prototype,"nv_getUTCDate",{writable:true,value:Date.prototype.getUTCDate})
Object.defineProperty(Date.prototype,"nv_getDay",{writable:true,value:Date.prototype.getDay})
Object.defineProperty(Date.prototype,"nv_getUTCDay",{writable:true,value:Date.prototype.getUTCDay})
Object.defineProperty(Date.prototype,"nv_getHours",{writable:true,value:Date.prototype.getHours})
Object.defineProperty(Date.prototype,"nv_getUTCHours",{writable:true,value:Date.prototype.getUTCHours})
Object.defineProperty(Date.prototype,"nv_getMinutes",{writable:true,value:Date.prototype.getMinutes})
Object.defineProperty(Date.prototype,"nv_getUTCMinutes",{writable:true,value:Date.prototype.getUTCMinutes})
Object.defineProperty(Date.prototype,"nv_getSeconds",{writable:true,value:Date.prototype.getSeconds})
Object.defineProperty(Date.prototype,"nv_getUTCSeconds",{writable:true,value:Date.prototype.getUTCSeconds})
Object.defineProperty(Date.prototype,"nv_getMilliseconds",{writable:true,value:Date.prototype.getMilliseconds})
Object.defineProperty(Date.prototype,"nv_getUTCMilliseconds",{writable:true,value:Date.prototype.getUTCMilliseconds})
Object.defineProperty(Date.prototype,"nv_getTimezoneOffset",{writable:true,value:Date.prototype.getTimezoneOffset})
Object.defineProperty(Date.prototype,"nv_setTime",{writable:true,value:Date.prototype.setTime})
Object.defineProperty(Date.prototype,"nv_setMilliseconds",{writable:true,value:Date.prototype.setMilliseconds})
Object.defineProperty(Date.prototype,"nv_setUTCMilliseconds",{writable:true,value:Date.prototype.setUTCMilliseconds})
Object.defineProperty(Date.prototype,"nv_setSeconds",{writable:true,value:Date.prototype.setSeconds})
Object.defineProperty(Date.prototype,"nv_setUTCSeconds",{writable:true,value:Date.prototype.setUTCSeconds})
Object.defineProperty(Date.prototype,"nv_setMinutes",{writable:true,value:Date.prototype.setMinutes})
Object.defineProperty(Date.prototype,"nv_setUTCMinutes",{writable:true,value:Date.prototype.setUTCMinutes})
Object.defineProperty(Date.prototype,"nv_setHours",{writable:true,value:Date.prototype.setHours})
Object.defineProperty(Date.prototype,"nv_setUTCHours",{writable:true,value:Date.prototype.setUTCHours})
Object.defineProperty(Date.prototype,"nv_setDate",{writable:true,value:Date.prototype.setDate})
Object.defineProperty(Date.prototype,"nv_setUTCDate",{writable:true,value:Date.prototype.setUTCDate})
Object.defineProperty(Date.prototype,"nv_setMonth",{writable:true,value:Date.prototype.setMonth})
Object.defineProperty(Date.prototype,"nv_setUTCMonth",{writable:true,value:Date.prototype.setUTCMonth})
Object.defineProperty(Date.prototype,"nv_setFullYear",{writable:true,value:Date.prototype.setFullYear})
Object.defineProperty(Date.prototype,"nv_setUTCFullYear",{writable:true,value:Date.prototype.setUTCFullYear})
Object.defineProperty(Date.prototype,"nv_toUTCString",{writable:true,value:Date.prototype.toUTCString})
Object.defineProperty(Date.prototype,"nv_toISOString",{writable:true,value:Date.prototype.toISOString})
Object.defineProperty(Date.prototype,"nv_toJSON",{writable:true,value:Date.prototype.toJSON})
}
var nf_init_RegExp=function(){
Object.defineProperty(RegExp.prototype,"nv_constructor",{writable:true,value:"RegExp"})
Object.defineProperty(RegExp.prototype,"nv_exec",{writable:true,value:RegExp.prototype.exec})
Object.defineProperty(RegExp.prototype,"nv_test",{writable:true,value:RegExp.prototype.test})
Object.defineProperty(RegExp.prototype,"nv_toString",{writable:true,value:RegExp.prototype.toString})
Object.defineProperty(RegExp.prototype,"nv_source",{get:function(){return this.source;},set:function(){}});
Object.defineProperty(RegExp.prototype,"nv_global",{get:function(){return this.global;},set:function(){}});
Object.defineProperty(RegExp.prototype,"nv_ignoreCase",{get:function(){return this.ignoreCase;},set:function(){}});
Object.defineProperty(RegExp.prototype,"nv_multiline",{get:function(){return this.multiline;},set:function(){}});
Object.defineProperty(RegExp.prototype,"nv_lastIndex",{get:function(){return this.lastIndex;},set:function(v){this.lastIndex=v;}});
}
nf_init();
var nv_getDate=function(){var args=Array.prototype.slice.call(arguments);args.unshift(Date);return new(Function.prototype.bind.apply(Date, args));}
var nv_getRegExp=function(){var args=Array.prototype.slice.call(arguments);args.unshift(RegExp);return new(Function.prototype.bind.apply(RegExp, args));}
var nv_console={}
nv_console.nv_log=function(){var res="WXSRT:";for(var i=0;i<arguments.length;++i)res+=arguments[i]+" ";console.log(res);}
var nv_parseInt = parseInt, nv_parseFloat = parseFloat, nv_isNaN = isNaN, nv_isFinite = isFinite, nv_decodeURI = decodeURI, nv_decodeURIComponent = decodeURIComponent, nv_encodeURI = encodeURI, nv_encodeURIComponent = encodeURIComponent;
function $gdc(o,p,r) {
o=wh.rv(o);
if(o===null||o===undefined) return o;
if(o.constructor===String||o.constructor===Boolean||o.constructor===Number) return o;
if(o.constructor===Object){
var copy={};
for(var k in o)
if(o.hasOwnProperty(k))
if(undefined===p) copy[k.substring(3)]=$gdc(o[k],p,r);
else copy[p+k]=$gdc(o[k],p,r);
return copy;
}
if(o.constructor===Array){
var copy=[];
for(var i=0;i<o.length;i++) copy.push($gdc(o[i],p,r));
return copy;
}
if(o.constructor===Date){
var copy=new Date();
copy.setTime(o.getTime());
return copy;
}
if(o.constructor===RegExp){
var f="";
if(o.global) f+="g";
if(o.ignoreCase) f+="i";
if(o.multiline) f+="m";
return (new RegExp(o.source,f));
}
if(r&&o.constructor===Function){
if ( r == 1 ) return $gdc(o(),undefined, 2);
if ( r == 2 ) return o;
}
return null;
}
var nv_JSON={}
nv_JSON.nv_stringify=function(o){
JSON.stringify(o);
return JSON.stringify($gdc(o));
}
nv_JSON.nv_parse=function(o){
if(o===undefined) return undefined;
var t=JSON.parse(o);
return $gdc(t,'nv_');
}

function _af(p, a, c){
p.extraAttr = {"t_action": a, "t_cid": c};
}

function _ai(i,p,e,me,r,c){var x=_grp(p,e,me);if(x)i.push(x);else{i.push('');_wp(me+':import:'+r+':'+c+': Path `'+p+'` not found from `'+me+'`.')}}
function _grp(p,e,me){if(p[0]!='/'){var mepart=me.split('/');mepart.pop();var ppart=p.split('/');for(var i=0;i<ppart.length;i++){if( ppart[i]=='..')mepart.pop();else if(!ppart[i]||ppart[i]=='.')continue;else mepart.push(ppart[i]);}p=mepart.join('/');}if(me[0]=='.'&&p[0]=='/')p='.'+p;if(e[p])return p;if(e[p+'.wxml'])return p+'.wxml';}
function _gd(p,c,e,d){if(!c)return;if(d[p][c])return d[p][c];for(var x=e[p].i.length-1;x>=0;x--){if(e[p].i[x]&&d[e[p].i[x]][c])return d[e[p].i[x]][c]};for(var x=e[p].ti.length-1;x>=0;x--){var q=_grp(e[p].ti[x],e,p);if(q&&d[q][c])return d[q][c]}var ii=_gapi(e,p);for(var x=0;x<ii.length;x++){if(ii[x]&&d[ii[x]][c])return d[ii[x]][c]}for(var k=e[p].j.length-1;k>=0;k--)if(e[p].j[k]){for(var q=e[e[p].j[k]].ti.length-1;q>=0;q--){var pp=_grp(e[e[p].j[k]].ti[q],e,p);if(pp&&d[pp][c]){return d[pp][c]}}}}
function _gapi(e,p){if(!p)return [];if($gaic[p]){return $gaic[p]};var ret=[],q=[],h=0,t=0,put={},visited={};q.push(p);visited[p]=true;t++;while(h<t){var a=q[h++];for(var i=0;i<e[a].ic.length;i++){var nd=e[a].ic[i];var np=_grp(nd,e,a);if(np&&!visited[np]){visited[np]=true;q.push(np);t++;}}for(var i=0;a!=p&&i<e[a].ti.length;i++){var ni=e[a].ti[i];var nm=_grp(ni,e,a);if(nm&&!put[nm]){put[nm]=true;ret.push(nm);}}}$gaic[p]=ret;return ret;}
var $ixc={};function _ic(p,ent,me,e,s,r,gg){var x=_grp(p,ent,me);ent[me].j.push(x);if(x){if($ixc[x]){_wp('-1:include:-1:-1: `'+p+'` is being included in a loop, will be stop.');return;}$ixc[x]=true;try{ent[x].f(e,s,r,gg)}catch(e){}$ixc[x]=false;}else{_wp(me+':include:-1:-1: Included path `'+p+'` not found from `'+me+'`.')}}
function _w(tn,f,line,c){_wp(f+':template:'+line+':'+c+': Template `'+tn+'` not found.');}function _ev(dom){var changed=false;delete dom.properities;delete dom.n;if(dom.children){do{changed=false;var newch = [];for(var i=0;i<dom.children.length;i++){var ch=dom.children[i];if( ch.tag=='virtual'){changed=true;for(var j=0;ch.children&&j<ch.children.length;j++){newch.push(ch.children[j]);}}else { newch.push(ch); } } dom.children = newch; }while(changed);for(var i=0;i<dom.children.length;i++){_ev(dom.children[i]);}} return dom; }
function _tsd( root )
{
if( root.tag == "wx-wx-scope" ) 
{
root.tag = "virtual";
root.wxCkey = "11";
root['wxScopeData'] = root.attr['wx:scope-data'];
delete root.n;
delete root.raw;
delete root.generics;
delete root.attr;
}
for( var i = 0 ; root.children && i < root.children.length ; i++ )
{
_tsd( root.children[i] );
}
return root;
}

var e_={}
if(typeof(global.entrys)==='undefined')global.entrys={};e_=global.entrys;
var d_={}
if(typeof(global.defines)==='undefined')global.defines={};d_=global.defines;
var f_={}
if(typeof(global.modules)==='undefined')global.modules={};f_=global.modules || {};
var p_={}
__WXML_GLOBAL__.ops_cached = __WXML_GLOBAL__.ops_cached || {}
__WXML_GLOBAL__.ops_set = __WXML_GLOBAL__.ops_set || {};
__WXML_GLOBAL__.ops_init = __WXML_GLOBAL__.ops_init || {};
var z=__WXML_GLOBAL__.ops_set.$gwx || [];
function gz$gwx_1(){
if( __WXML_GLOBAL__.ops_cached.$gwx_1)return __WXML_GLOBAL__.ops_cached.$gwx_1
__WXML_GLOBAL__.ops_cached.$gwx_1=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_1);return __WXML_GLOBAL__.ops_cached.$gwx_1
}
function gz$gwx_2(){
if( __WXML_GLOBAL__.ops_cached.$gwx_2)return __WXML_GLOBAL__.ops_cached.$gwx_2
__WXML_GLOBAL__.ops_cached.$gwx_2=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
Z([[7],[3,'canvasId']])
})(__WXML_GLOBAL__.ops_cached.$gwx_2);return __WXML_GLOBAL__.ops_cached.$gwx_2
}
function gz$gwx_3(){
if( __WXML_GLOBAL__.ops_cached.$gwx_3)return __WXML_GLOBAL__.ops_cached.$gwx_3
__WXML_GLOBAL__.ops_cached.$gwx_3=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_3);return __WXML_GLOBAL__.ops_cached.$gwx_3
}
function gz$gwx_4(){
if( __WXML_GLOBAL__.ops_cached.$gwx_4)return __WXML_GLOBAL__.ops_cached.$gwx_4
__WXML_GLOBAL__.ops_cached.$gwx_4=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_4);return __WXML_GLOBAL__.ops_cached.$gwx_4
}
function gz$gwx_5(){
if( __WXML_GLOBAL__.ops_cached.$gwx_5)return __WXML_GLOBAL__.ops_cached.$gwx_5
__WXML_GLOBAL__.ops_cached.$gwx_5=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_5);return __WXML_GLOBAL__.ops_cached.$gwx_5
}
function gz$gwx_6(){
if( __WXML_GLOBAL__.ops_cached.$gwx_6)return __WXML_GLOBAL__.ops_cached.$gwx_6
__WXML_GLOBAL__.ops_cached.$gwx_6=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
Z([3,'week'])
Z([3,'weeks'])
Z([[6],[[7],[3,'canlender']],[3,'weeks']])
Z(z[0])
Z([3,'index'])
Z([3,'day'])
Z([[7],[3,'weeks']])
Z(z[4])
Z([3,'__e'])
Z([[4],[[5],[[5],[[5],[[5],[[5],[[5],[[5],[[5],[1,'uni-calender__date']],[[2,'?:'],[[2,'||'],[[2,'!=='],[[6],[[7],[3,'canlender']],[3,'month']],[[6],[[7],[3,'day']],[3,'month']]],[[6],[[7],[3,'day']],[3,'disable']]],[1,'uni-calender__disable'],[1,'']]],[[2,'?:'],[[2,'&&'],[[2,'&&'],[[2,'||'],[[2,'||'],[[2,'&&'],[[2,'=='],[[6],[[7],[3,'day']],[3,'date']],[[6],[[7],[3,'canlender']],[3,'date']]],[[2,'!'],[[6],[[7],[3,'day']],[3,'checked']]]],[[6],[[7],[3,'day']],[3,'multipleBegin']]],[[6],[[7],[3,'day']],[3,'multipleEnd']]],[[2,'=='],[[6],[[7],[3,'canlender']],[3,'month']],[[6],[[7],[3,'day']],[3,'month']]]],[[2,'!'],[[6],[[7],[3,'day']],[3,'disable']]]],[1,'uni-calender__date-current'],[1,'']]],[[2,'?:'],[[7],[3,'lunar']],[1,'uni-calender__lunar'],[1,'']]],[[2,'?:'],[[2,'!'],[[6],[[7],[3,'day']],[3,'isDay']]],[1,'uni-calender__active'],[1,'']]],[[2,'?:'],[[6],[[7],[3,'day']],[3,'isDay']],[1,'uni-calender__is-day'],[1,'']]],[[2,'?:'],[[2,'||'],[[6],[[7],[3,'day']],[3,'multipleBegin']],[[6],[[7],[3,'day']],[3,'multipleEnd']]],[1,'uni-calender__multiple'],[1,'']]],[[2,'?:'],[[6],[[7],[3,'day']],[3,'checked']],[1,'uni-calender__multiple-box'],[1,'']]]])
Z([[4],[[5],[[4],[[5],[[5],[1,'tap']],[[4],[[5],[[4],[[5],[[5],[[5],[1,'selectDays']],[[4],[[5],[[5],[[5],[[5],[[5],[[7],[3,'week']]],[[7],[3,'index']]],[[2,'==='],[[6],[[7],[3,'canlender']],[3,'month']],[[6],[[7],[3,'day']],[3,'month']]]],[1,'$0']],[1,'$1']]]],[[4],[[5],[[5],[[4],[[5],[[5],[[4],[[5],[[5],[[5],[1,'canlender.weeks']],[1,'']],[[7],[3,'week']]]]],[[4],[[5],[[5],[[5],[[5],[1,'']],[1,'']],[[7],[3,'index']]],[1,'disable']]]]]],[1,'canlender.lunar']]]]]]]]]]])
Z([3,'uni-calender__circle-box'])
Z([[7],[3,'lunar']])
Z([[6],[[7],[3,'day']],[3,'have']])
Z([[2,'&&'],[[6],[[7],[3,'day']],[3,'have']],[[2,'!'],[[7],[3,'lunar']]]])
})(__WXML_GLOBAL__.ops_cached.$gwx_6);return __WXML_GLOBAL__.ops_cached.$gwx_6
}
function gz$gwx_7(){
if( __WXML_GLOBAL__.ops_cached.$gwx_7)return __WXML_GLOBAL__.ops_cached.$gwx_7
__WXML_GLOBAL__.ops_cached.$gwx_7=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
Z([[2,'&&'],[[7],[3,'maskShow']],[[2,'!'],[[7],[3,'insert']]]])
Z([[2,'||'],[[7],[3,'maskShow']],[[7],[3,'insert']]])
Z([[4],[[5],[[5],[[5],[1,'uni-calendar__box']],[[2,'?:'],[[7],[3,'aniMaskShow']],[1,'ani-calendar-show'],[1,'']]],[[2,'?:'],[[7],[3,'insert']],[1,'uni-calendar__static'],[1,'']]]])
Z([[2,'!'],[[7],[3,'insert']]])
Z([3,'uni-calenda__content'])
Z([[7],[3,'isLunar']])
Z([3,'__l'])
Z([3,'__e'])
Z([[7],[3,'canlender']])
Z([[4],[[5],[[4],[[5],[[5],[1,'^selectDays']],[[4],[[5],[[4],[[5],[1,'selectDays']]]]]]]]])
Z(z[5])
Z([3,'1'])
})(__WXML_GLOBAL__.ops_cached.$gwx_7);return __WXML_GLOBAL__.ops_cached.$gwx_7
}
function gz$gwx_8(){
if( __WXML_GLOBAL__.ops_cached.$gwx_8)return __WXML_GLOBAL__.ops_cached.$gwx_8
__WXML_GLOBAL__.ops_cached.$gwx_8=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_8);return __WXML_GLOBAL__.ops_cached.$gwx_8
}
function gz$gwx_9(){
if( __WXML_GLOBAL__.ops_cached.$gwx_9)return __WXML_GLOBAL__.ops_cached.$gwx_9
__WXML_GLOBAL__.ops_cached.$gwx_9=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_9);return __WXML_GLOBAL__.ops_cached.$gwx_9
}
function gz$gwx_10(){
if( __WXML_GLOBAL__.ops_cached.$gwx_10)return __WXML_GLOBAL__.ops_cached.$gwx_10
__WXML_GLOBAL__.ops_cached.$gwx_10=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_10);return __WXML_GLOBAL__.ops_cached.$gwx_10
}
function gz$gwx_11(){
if( __WXML_GLOBAL__.ops_cached.$gwx_11)return __WXML_GLOBAL__.ops_cached.$gwx_11
__WXML_GLOBAL__.ops_cached.$gwx_11=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
Z([3,'date-detail'])
Z([3,'__e'])
Z([[4],[[5],[[4],[[5],[[5],[1,'tap']],[[4],[[5],[[4],[[5],[[5],[1,'e1']],[[4],[[5],[1,'$event']]]]]]]]]]])
Z([3,'__l'])
Z(z[1])
Z(z[1])
Z(z[1])
Z([[4],[[5],[[5],[[5],[[4],[[5],[[5],[1,'^showchange']],[[4],[[5],[[4],[[5],[1,'showchange2']]]]]]]],[[4],[[5],[[5],[1,'^change']],[[4],[[5],[[4],[[5],[1,'bindChange2']]]]]]]],[[4],[[5],[[5],[1,'^cancel']],[[4],[[5],[[4],[[5],[1,'bindCancel2']]]]]]]]])
Z([3,'2200-12-31'])
Z([[7],[3,'isShow']])
Z([3,'1900-01-01'])
Z([[7],[3,'value']])
Z([3,'1'])
Z([[2,'=='],[[6],[[6],[[7],[3,'detailInfo']],[3,'checkDteailVOs']],[3,'length']],[1,0]])
Z(z[3])
Z(z[1])
Z(z[1])
Z([[7],[3,'productList']])
Z([[4],[[5],[[5],[[4],[[5],[[5],[1,'^isShow2']],[[4],[[5],[[4],[[5],[1,'colse']]]]]]]],[[4],[[5],[[5],[1,'^submit']],[[4],[[5],[[4],[[5],[1,'submit']]]]]]]]])
Z([[7],[3,'isShow2']])
Z([3,'2'])
})(__WXML_GLOBAL__.ops_cached.$gwx_11);return __WXML_GLOBAL__.ops_cached.$gwx_11
}
function gz$gwx_12(){
if( __WXML_GLOBAL__.ops_cached.$gwx_12)return __WXML_GLOBAL__.ops_cached.$gwx_12
__WXML_GLOBAL__.ops_cached.$gwx_12=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_12);return __WXML_GLOBAL__.ops_cached.$gwx_12
}
function gz$gwx_13(){
if( __WXML_GLOBAL__.ops_cached.$gwx_13)return __WXML_GLOBAL__.ops_cached.$gwx_13
__WXML_GLOBAL__.ops_cached.$gwx_13=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_13);return __WXML_GLOBAL__.ops_cached.$gwx_13
}
function gz$gwx_14(){
if( __WXML_GLOBAL__.ops_cached.$gwx_14)return __WXML_GLOBAL__.ops_cached.$gwx_14
__WXML_GLOBAL__.ops_cached.$gwx_14=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
Z([3,'__l'])
Z([3,'__e'])
Z(z[1])
Z(z[1])
Z([[4],[[5],[[5],[[5],[[4],[[5],[[5],[1,'^showchange']],[[4],[[5],[[4],[[5],[1,'showchange']]]]]]]],[[4],[[5],[[5],[1,'^change']],[[4],[[5],[[4],[[5],[1,'bindChange']]]]]]]],[[4],[[5],[[5],[1,'^cancel']],[[4],[[5],[[4],[[5],[1,'bindCancel']]]]]]]]])
Z([3,'2200-12-31'])
Z([[7],[3,'isShow']])
Z([3,'1900-01-01'])
Z([[7],[3,'value']])
Z([3,'1'])
})(__WXML_GLOBAL__.ops_cached.$gwx_14);return __WXML_GLOBAL__.ops_cached.$gwx_14
}
function gz$gwx_15(){
if( __WXML_GLOBAL__.ops_cached.$gwx_15)return __WXML_GLOBAL__.ops_cached.$gwx_15
__WXML_GLOBAL__.ops_cached.$gwx_15=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_15);return __WXML_GLOBAL__.ops_cached.$gwx_15
}
function gz$gwx_16(){
if( __WXML_GLOBAL__.ops_cached.$gwx_16)return __WXML_GLOBAL__.ops_cached.$gwx_16
__WXML_GLOBAL__.ops_cached.$gwx_16=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
Z([3,'comment'])
Z([3,'__l'])
Z([3,'__e'])
Z([[4],[[5],[[4],[[5],[[5],[1,'^choose']],[[4],[[5],[[4],[[5],[1,'choose']]]]]]]]])
Z([3,'1'])
Z([3,'conatainer'])
Z([[2,'!='],[[7],[3,'tagList']],[1,'']])
Z([[2,'=='],[[7],[3,'commentList']],[1,'']])
})(__WXML_GLOBAL__.ops_cached.$gwx_16);return __WXML_GLOBAL__.ops_cached.$gwx_16
}
function gz$gwx_17(){
if( __WXML_GLOBAL__.ops_cached.$gwx_17)return __WXML_GLOBAL__.ops_cached.$gwx_17
__WXML_GLOBAL__.ops_cached.$gwx_17=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_17);return __WXML_GLOBAL__.ops_cached.$gwx_17
}
function gz$gwx_18(){
if( __WXML_GLOBAL__.ops_cached.$gwx_18)return __WXML_GLOBAL__.ops_cached.$gwx_18
__WXML_GLOBAL__.ops_cached.$gwx_18=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_18);return __WXML_GLOBAL__.ops_cached.$gwx_18
}
function gz$gwx_19(){
if( __WXML_GLOBAL__.ops_cached.$gwx_19)return __WXML_GLOBAL__.ops_cached.$gwx_19
__WXML_GLOBAL__.ops_cached.$gwx_19=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_19);return __WXML_GLOBAL__.ops_cached.$gwx_19
}
function gz$gwx_20(){
if( __WXML_GLOBAL__.ops_cached.$gwx_20)return __WXML_GLOBAL__.ops_cached.$gwx_20
__WXML_GLOBAL__.ops_cached.$gwx_20=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
Z([3,'__e'])
Z([3,'choose-date'])
Z([[4],[[5],[[4],[[5],[[5],[1,'tap']],[[4],[[5],[[4],[[5],[[5],[1,'e0']],[[4],[[5],[1,'$event']]]]]]]]]]])
Z([3,'__l'])
Z(z[0])
Z([3,'vue-ref'])
Z([[4],[[5],[[4],[[5],[[5],[1,'^confirm']],[[4],[[5],[[4],[[5],[1,'confirm']]]]]]]]])
Z([3,'calendar'])
Z([[6],[[7],[3,'this']],[3,'uniCalendarendDate']])
Z([1,false])
Z([1,true])
Z(z[10])
Z([[6],[[7],[3,'this']],[3,'uniCalendarStartDate']])
Z([3,'1'])
})(__WXML_GLOBAL__.ops_cached.$gwx_20);return __WXML_GLOBAL__.ops_cached.$gwx_20
}
function gz$gwx_21(){
if( __WXML_GLOBAL__.ops_cached.$gwx_21)return __WXML_GLOBAL__.ops_cached.$gwx_21
__WXML_GLOBAL__.ops_cached.$gwx_21=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
Z([3,'__i0__'])
Z([3,'item'])
Z([[6],[[7],[3,'$root']],[3,'l0']])
Z([3,'__e'])
Z([3,'problem-item'])
Z([[4],[[5],[[4],[[5],[[5],[1,'tap']],[[4],[[5],[[4],[[5],[[5],[[5],[1,'toDetail']],[[4],[[5],[1,'$0']]]],[[4],[[5],[[4],[[5],[[4],[[5],[[5],[[5],[1,'problemList.result']],[1,'']],[[7],[3,'__i0__']]]]]]]]]]]]]]]])
Z([3,'answer-content'])
Z([[2,'>'],[[6],[[6],[[7],[3,'item']],[3,'$orig']],[3,'answerNum']],[1,0]])
Z([[2,'&&'],[[2,'>'],[[6],[[6],[[7],[3,'item']],[3,'$orig']],[3,'answerNum']],[1,0]],[[2,'=='],[[6],[[6],[[6],[[7],[3,'item']],[3,'$orig']],[3,'answers']],[3,'mchFlag']],[1,1]]])
Z([[2,'=='],[[6],[[6],[[7],[3,'item']],[3,'$orig']],[3,'answerNum']],[1,0]])
Z(z[7])
})(__WXML_GLOBAL__.ops_cached.$gwx_21);return __WXML_GLOBAL__.ops_cached.$gwx_21
}
function gz$gwx_22(){
if( __WXML_GLOBAL__.ops_cached.$gwx_22)return __WXML_GLOBAL__.ops_cached.$gwx_22
__WXML_GLOBAL__.ops_cached.$gwx_22=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_22);return __WXML_GLOBAL__.ops_cached.$gwx_22
}
function gz$gwx_23(){
if( __WXML_GLOBAL__.ops_cached.$gwx_23)return __WXML_GLOBAL__.ops_cached.$gwx_23
__WXML_GLOBAL__.ops_cached.$gwx_23=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
Z([[7],[3,'isMask']])
})(__WXML_GLOBAL__.ops_cached.$gwx_23);return __WXML_GLOBAL__.ops_cached.$gwx_23
}
function gz$gwx_24(){
if( __WXML_GLOBAL__.ops_cached.$gwx_24)return __WXML_GLOBAL__.ops_cached.$gwx_24
__WXML_GLOBAL__.ops_cached.$gwx_24=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
Z([3,'__i0__'])
Z([3,'item'])
Z([[7],[3,'venueList']])
Z([[7],[3,'showPoint']])
})(__WXML_GLOBAL__.ops_cached.$gwx_24);return __WXML_GLOBAL__.ops_cached.$gwx_24
}
function gz$gwx_25(){
if( __WXML_GLOBAL__.ops_cached.$gwx_25)return __WXML_GLOBAL__.ops_cached.$gwx_25
__WXML_GLOBAL__.ops_cached.$gwx_25=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
Z([3,'__l'])
Z([3,'1'])
})(__WXML_GLOBAL__.ops_cached.$gwx_25);return __WXML_GLOBAL__.ops_cached.$gwx_25
}
function gz$gwx_26(){
if( __WXML_GLOBAL__.ops_cached.$gwx_26)return __WXML_GLOBAL__.ops_cached.$gwx_26
__WXML_GLOBAL__.ops_cached.$gwx_26=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
Z([3,'__l'])
Z([[7],[3,'curStep']])
Z([3,'1'])
})(__WXML_GLOBAL__.ops_cached.$gwx_26);return __WXML_GLOBAL__.ops_cached.$gwx_26
}
function gz$gwx_27(){
if( __WXML_GLOBAL__.ops_cached.$gwx_27)return __WXML_GLOBAL__.ops_cached.$gwx_27
__WXML_GLOBAL__.ops_cached.$gwx_27=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
Z([3,'__l'])
Z([[7],[3,'curStep']])
Z([3,'1'])
})(__WXML_GLOBAL__.ops_cached.$gwx_27);return __WXML_GLOBAL__.ops_cached.$gwx_27
}
function gz$gwx_28(){
if( __WXML_GLOBAL__.ops_cached.$gwx_28)return __WXML_GLOBAL__.ops_cached.$gwx_28
__WXML_GLOBAL__.ops_cached.$gwx_28=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_28);return __WXML_GLOBAL__.ops_cached.$gwx_28
}
function gz$gwx_29(){
if( __WXML_GLOBAL__.ops_cached.$gwx_29)return __WXML_GLOBAL__.ops_cached.$gwx_29
__WXML_GLOBAL__.ops_cached.$gwx_29=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_29);return __WXML_GLOBAL__.ops_cached.$gwx_29
}
function gz$gwx_30(){
if( __WXML_GLOBAL__.ops_cached.$gwx_30)return __WXML_GLOBAL__.ops_cached.$gwx_30
__WXML_GLOBAL__.ops_cached.$gwx_30=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_30);return __WXML_GLOBAL__.ops_cached.$gwx_30
}
function gz$gwx_31(){
if( __WXML_GLOBAL__.ops_cached.$gwx_31)return __WXML_GLOBAL__.ops_cached.$gwx_31
__WXML_GLOBAL__.ops_cached.$gwx_31=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_31);return __WXML_GLOBAL__.ops_cached.$gwx_31
}
function gz$gwx_32(){
if( __WXML_GLOBAL__.ops_cached.$gwx_32)return __WXML_GLOBAL__.ops_cached.$gwx_32
__WXML_GLOBAL__.ops_cached.$gwx_32=[];
(function(z){var a=11;function Z(ops){z.push(ops)}
})(__WXML_GLOBAL__.ops_cached.$gwx_32);return __WXML_GLOBAL__.ops_cached.$gwx_32
}
__WXML_GLOBAL__.ops_set.$gwx=z;
__WXML_GLOBAL__.ops_init.$gwx=true;
var nv_require=function(){var nnm={};var nom={};return function(n){return function(){if(!nnm[n]) return undefined;try{if(!nom[n])nom[n]=nnm[n]();return nom[n];}catch(e){e.message=e.message.replace(/nv_/g,'');var tmp = e.stack.substring(0,e.stack.lastIndexOf(n));e.stack = tmp.substring(0,tmp.lastIndexOf('\n'));e.stack = e.stack.replace(/\snv_/g,' ');e.stack = $gstack(e.stack);e.stack += '\n    at ' + n.substring(2);console.error(e);}
}}}()
var x=['./components/forget-header.wxml','./components/mpvue-echarts/src/echarts.wxml','./components/mutiChoose.wxml','./components/range-dtpicker/range-dtpicker.wxml','./components/tabNav.wxml','./components/uni-calendar/uni-calendar-item.wxml','./components/uni-calendar/uni-calendar.wxml','./pages/checking/cancelReg.wxml','./pages/checking/checking.wxml','./pages/checking/detail.wxml','./pages/checking/detail2.wxml','./pages/checking/historyList.wxml','./pages/checking/historyList2.wxml','./pages/checking/search.wxml','./pages/data/calendar.wxml','./pages/data/comment.wxml','./pages/data/commentDetail.wxml','./pages/data/commentDetail2.wxml','./pages/data/commentDetail3.wxml','./pages/data/data.wxml','./pages/data/problem.wxml','./pages/data/replay.wxml','./pages/data/scenic.wxml','./pages/index/index.wxml','./pages/login/forget.wxml','./pages/login/forget1.wxml','./pages/login/forget2.wxml','./pages/login/login.wxml','./pages/new/new.wxml','./pages/person/edit.wxml','./pages/person/manage.wxml','./pages/person/person.wxml'];d_[x[0]]={}
var m0=function(e,s,r,gg){
var z=gz$gwx_1()
return r
}
e_[x[0]]={f:m0,j:[],i:[],ti:[],ic:[]}
d_[x[1]]={}
var m1=function(e,s,r,gg){
var z=gz$gwx_2()
var xC=_v()
_(r,xC)
if(_oz(z,0,e,s,gg)){xC.wxVkey=1
}
xC.wxXCkey=1
return r
}
e_[x[1]]={f:m1,j:[],i:[],ti:[],ic:[]}
d_[x[2]]={}
var m2=function(e,s,r,gg){
var z=gz$gwx_3()
return r
}
e_[x[2]]={f:m2,j:[],i:[],ti:[],ic:[]}
d_[x[3]]={}
var m3=function(e,s,r,gg){
var z=gz$gwx_4()
return r
}
e_[x[3]]={f:m3,j:[],i:[],ti:[],ic:[]}
d_[x[4]]={}
var m4=function(e,s,r,gg){
var z=gz$gwx_5()
return r
}
e_[x[4]]={f:m4,j:[],i:[],ti:[],ic:[]}
d_[x[5]]={}
var m5=function(e,s,r,gg){
var z=gz$gwx_6()
var oH=_v()
_(r,oH)
var cI=function(lK,oJ,aL,gg){
var eN=_v()
_(aL,eN)
var bO=function(xQ,oP,oR,gg){
var cT=_mz(z,'view',['bindtap',8,'class',1,'data-event-opts',2],[],xQ,oP,gg)
var hU=_n('view')
_rz(z,hU,'class',11,xQ,oP,gg)
var oV=_v()
_(hU,oV)
if(_oz(z,12,xQ,oP,gg)){oV.wxVkey=1
}
var cW=_v()
_(hU,cW)
if(_oz(z,13,xQ,oP,gg)){cW.wxVkey=1
}
var oX=_v()
_(hU,oX)
if(_oz(z,14,xQ,oP,gg)){oX.wxVkey=1
}
oV.wxXCkey=1
cW.wxXCkey=1
oX.wxXCkey=1
_(cT,hU)
_(oR,cT)
return oR
}
eN.wxXCkey=2
_2z(z,6,bO,lK,oJ,gg,eN,'day','index','index')
return aL
}
oH.wxXCkey=2
_2z(z,2,cI,e,s,gg,oH,'weeks','week','week')
return r
}
e_[x[5]]={f:m5,j:[],i:[],ti:[],ic:[]}
d_[x[6]]={}
var m6=function(e,s,r,gg){
var z=gz$gwx_7()
var aZ=_n('view')
var t1=_v()
_(aZ,t1)
if(_oz(z,0,e,s,gg)){t1.wxVkey=1
}
var e2=_v()
_(aZ,e2)
if(_oz(z,1,e,s,gg)){e2.wxVkey=1
var b3=_n('view')
_rz(z,b3,'class',2,e,s,gg)
var o4=_v()
_(b3,o4)
if(_oz(z,3,e,s,gg)){o4.wxVkey=1
}
var x5=_n('view')
_rz(z,x5,'class',4,e,s,gg)
var o6=_v()
_(x5,o6)
if(_oz(z,5,e,s,gg)){o6.wxVkey=1
}
var f7=_mz(z,'uni-calendar-item',['bind:__l',6,'bind:selectDays',1,'canlender',2,'data-event-opts',3,'lunar',4,'vueId',5],[],e,s,gg)
_(x5,f7)
o6.wxXCkey=1
_(b3,x5)
o4.wxXCkey=1
_(e2,b3)
}
t1.wxXCkey=1
e2.wxXCkey=1
e2.wxXCkey=3
_(r,aZ)
return r
}
e_[x[6]]={f:m6,j:[],i:[],ti:[],ic:[]}
d_[x[7]]={}
var m7=function(e,s,r,gg){
var z=gz$gwx_8()
return r
}
e_[x[7]]={f:m7,j:[],i:[],ti:[],ic:[]}
d_[x[8]]={}
var m8=function(e,s,r,gg){
var z=gz$gwx_9()
return r
}
e_[x[8]]={f:m8,j:[],i:[],ti:[],ic:[]}
d_[x[9]]={}
var m9=function(e,s,r,gg){
var z=gz$gwx_10()
return r
}
e_[x[9]]={f:m9,j:[],i:[],ti:[],ic:[]}
d_[x[10]]={}
var m10=function(e,s,r,gg){
var z=gz$gwx_11()
var oBB=_n('view')
_rz(z,oBB,'class',0,e,s,gg)
var aDB=_mz(z,'view',['bindtap',1,'data-event-opts',1],[],e,s,gg)
var tEB=_mz(z,'range-date-pick',['bind:__l',3,'bind:cancel',1,'bind:change',2,'bind:showchange',3,'data-event-opts',4,'end',5,'show',6,'start',7,'value',8,'vueId',9],[],e,s,gg)
_(aDB,tEB)
_(oBB,aDB)
var lCB=_v()
_(oBB,lCB)
if(_oz(z,13,e,s,gg)){lCB.wxVkey=1
}
var eFB=_mz(z,'choos-type',['bind:__l',14,'bind:isShow2',1,'bind:submit',2,'chooseList',3,'data-event-opts',4,'isShow2',5,'vueId',6],[],e,s,gg)
_(oBB,eFB)
lCB.wxXCkey=1
_(r,oBB)
return r
}
e_[x[10]]={f:m10,j:[],i:[],ti:[],ic:[]}
d_[x[11]]={}
var m11=function(e,s,r,gg){
var z=gz$gwx_12()
return r
}
e_[x[11]]={f:m11,j:[],i:[],ti:[],ic:[]}
d_[x[12]]={}
var m12=function(e,s,r,gg){
var z=gz$gwx_13()
return r
}
e_[x[12]]={f:m12,j:[],i:[],ti:[],ic:[]}
d_[x[13]]={}
var m13=function(e,s,r,gg){
var z=gz$gwx_14()
var oJB=_mz(z,'range-date-pick',['bind:__l',0,'bind:cancel',1,'bind:change',1,'bind:showchange',2,'data-event-opts',3,'end',4,'show',5,'start',6,'value',7,'vueId',8],[],e,s,gg)
_(r,oJB)
return r
}
e_[x[13]]={f:m13,j:[],i:[],ti:[],ic:[]}
d_[x[14]]={}
var m14=function(e,s,r,gg){
var z=gz$gwx_15()
return r
}
e_[x[14]]={f:m14,j:[],i:[],ti:[],ic:[]}
d_[x[15]]={}
var m15=function(e,s,r,gg){
var z=gz$gwx_16()
var hMB=_n('view')
_rz(z,hMB,'class',0,e,s,gg)
var oNB=_mz(z,'tab-nav',['bind:__l',1,'bind:choose',1,'data-event-opts',2,'vueId',3],[],e,s,gg)
_(hMB,oNB)
var cOB=_n('view')
_rz(z,cOB,'class',5,e,s,gg)
var oPB=_v()
_(cOB,oPB)
if(_oz(z,6,e,s,gg)){oPB.wxVkey=1
}
var lQB=_v()
_(cOB,lQB)
if(_oz(z,7,e,s,gg)){lQB.wxVkey=1
}
oPB.wxXCkey=1
lQB.wxXCkey=1
_(hMB,cOB)
_(r,hMB)
return r
}
e_[x[15]]={f:m15,j:[],i:[],ti:[],ic:[]}
d_[x[16]]={}
var m16=function(e,s,r,gg){
var z=gz$gwx_17()
return r
}
e_[x[16]]={f:m16,j:[],i:[],ti:[],ic:[]}
d_[x[17]]={}
var m17=function(e,s,r,gg){
var z=gz$gwx_18()
return r
}
e_[x[17]]={f:m17,j:[],i:[],ti:[],ic:[]}
d_[x[18]]={}
var m18=function(e,s,r,gg){
var z=gz$gwx_19()
return r
}
e_[x[18]]={f:m18,j:[],i:[],ti:[],ic:[]}
d_[x[19]]={}
var m19=function(e,s,r,gg){
var z=gz$gwx_20()
var oVB=_mz(z,'view',['bindtap',0,'class',1,'data-event-opts',1],[],e,s,gg)
var xWB=_mz(z,'uni-calendar',['bind:__l',3,'bind:confirm',1,'class',2,'data-event-opts',3,'data-ref',4,'endDate',5,'insert',6,'lunar',7,'range',8,'startDate',9,'vueId',10],[],e,s,gg)
_(oVB,xWB)
_(r,oVB)
return r
}
e_[x[19]]={f:m19,j:[],i:[],ti:[],ic:[]}
d_[x[20]]={}
var m20=function(e,s,r,gg){
var z=gz$gwx_21()
var fYB=_v()
_(r,fYB)
var cZB=function(o2B,h1B,c3B,gg){
var l5B=_mz(z,'view',['bindtap',3,'class',1,'data-event-opts',2],[],o2B,h1B,gg)
var e8B=_n('view')
_rz(z,e8B,'class',6,o2B,h1B,gg)
var b9B=_v()
_(e8B,b9B)
if(_oz(z,7,o2B,h1B,gg)){b9B.wxVkey=1
}
var o0B=_v()
_(e8B,o0B)
if(_oz(z,8,o2B,h1B,gg)){o0B.wxVkey=1
}
b9B.wxXCkey=1
o0B.wxXCkey=1
_(l5B,e8B)
var a6B=_v()
_(l5B,a6B)
if(_oz(z,9,o2B,h1B,gg)){a6B.wxVkey=1
}
var t7B=_v()
_(l5B,t7B)
if(_oz(z,10,o2B,h1B,gg)){t7B.wxVkey=1
}
a6B.wxXCkey=1
t7B.wxXCkey=1
_(c3B,l5B)
return c3B
}
fYB.wxXCkey=2
_2z(z,2,cZB,e,s,gg,fYB,'item','__i0__','')
return r
}
e_[x[20]]={f:m20,j:[],i:[],ti:[],ic:[]}
d_[x[21]]={}
var m21=function(e,s,r,gg){
var z=gz$gwx_22()
return r
}
e_[x[21]]={f:m21,j:[],i:[],ti:[],ic:[]}
d_[x[22]]={}
var m22=function(e,s,r,gg){
var z=gz$gwx_23()
var fCC=_v()
_(r,fCC)
if(_oz(z,0,e,s,gg)){fCC.wxVkey=1
}
fCC.wxXCkey=1
return r
}
e_[x[22]]={f:m22,j:[],i:[],ti:[],ic:[]}
d_[x[23]]={}
var m23=function(e,s,r,gg){
var z=gz$gwx_24()
var hEC=_v()
_(r,hEC)
var oFC=function(oHC,cGC,lIC,gg){
var tKC=_v()
_(lIC,tKC)
if(_oz(z,3,oHC,cGC,gg)){tKC.wxVkey=1
}
tKC.wxXCkey=1
return lIC
}
hEC.wxXCkey=2
_2z(z,2,oFC,e,s,gg,hEC,'item','__i0__','')
return r
}
e_[x[23]]={f:m23,j:[],i:[],ti:[],ic:[]}
d_[x[24]]={}
var m24=function(e,s,r,gg){
var z=gz$gwx_25()
var bMC=_mz(z,'forget',['bind:__l',0,'vueId',1],[],e,s,gg)
_(r,bMC)
return r
}
e_[x[24]]={f:m24,j:[],i:[],ti:[],ic:[]}
d_[x[25]]={}
var m25=function(e,s,r,gg){
var z=gz$gwx_26()
var xOC=_mz(z,'forget',['bind:__l',0,'curStep',1,'vueId',1],[],e,s,gg)
_(r,xOC)
return r
}
e_[x[25]]={f:m25,j:[],i:[],ti:[],ic:[]}
d_[x[26]]={}
var m26=function(e,s,r,gg){
var z=gz$gwx_27()
var fQC=_mz(z,'forget',['bind:__l',0,'curStep',1,'vueId',1],[],e,s,gg)
_(r,fQC)
return r
}
e_[x[26]]={f:m26,j:[],i:[],ti:[],ic:[]}
d_[x[27]]={}
var m27=function(e,s,r,gg){
var z=gz$gwx_28()
return r
}
e_[x[27]]={f:m27,j:[],i:[],ti:[],ic:[]}
d_[x[28]]={}
var m28=function(e,s,r,gg){
var z=gz$gwx_29()
return r
}
e_[x[28]]={f:m28,j:[],i:[],ti:[],ic:[]}
d_[x[29]]={}
var m29=function(e,s,r,gg){
var z=gz$gwx_30()
return r
}
e_[x[29]]={f:m29,j:[],i:[],ti:[],ic:[]}
d_[x[30]]={}
var m30=function(e,s,r,gg){
var z=gz$gwx_31()
return r
}
e_[x[30]]={f:m30,j:[],i:[],ti:[],ic:[]}
d_[x[31]]={}
var m31=function(e,s,r,gg){
var z=gz$gwx_32()
return r
}
e_[x[31]]={f:m31,j:[],i:[],ti:[],ic:[]}
if(path&&e_[path]){
return function(env,dd,global){$gwxc=0;var root={"tag":"wx-page"};root.children=[]
var main=e_[path].f
if (typeof global==="undefined")global={};global.f=$gdc(f_[path],"",1);
try{
main(env,{},root,global);
_tsd(root)
}catch(err){
console.log(err)
}
return root;
}
}
}



__wxAppCode__['app.json']={"pages":["pages/login/login","pages/data/calendar","pages/index/index","pages/login/forget","pages/login/forget1","pages/login/forget2","pages/checking/checking","pages/checking/historyList","pages/checking/historyList2","pages/data/data","pages/data/comment","pages/data/replay","pages/data/commentDetail","pages/data/commentDetail2","pages/data/commentDetail3","pages/data/problem","pages/data/scenic","pages/new/new","pages/person/person","pages/checking/search","pages/checking/cancelReg","pages/checking/detail","pages/checking/detail2","pages/person/manage","pages/person/edit"],"window":{"navigationBarTextStyle":"black","navigationBarTitleText":"海洋世界","navigationBarBackgroundColor":"#F8F8F8","backgroundColor":"#F8F8F8"},"tabBar":{"color":"#7F7F7F","selectedColor":"#00CE9F","borderStyle":"black","backgroundColor":"#ffffff","list":[{"pagePath":"pages/index/index","iconPath":"static/img/home.png","selectedIconPath":"static/img/home1.png","text":"首页"},{"pagePath":"pages/data/data","iconPath":"static/img/shop.png","selectedIconPath":"static/img/shop1.png","text":"数据"},{"pagePath":"pages/person/person","iconPath":"static/img/user.png","selectedIconPath":"static/img/user1.png","text":"我的"}]},"nvueCompiler":"weex","splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"ocean","compilerVersion":"2.1.3","usingComponents":{}};
__wxAppCode__['app.wxml']=$gwx('./app.wxml');

__wxAppCode__['components/forget-header.json']={"usingComponents":{},"component":true};
__wxAppCode__['components/forget-header.wxml']=$gwx('./components/forget-header.wxml');

__wxAppCode__['components/mpvue-echarts/src/echarts.json']={"usingComponents":{},"component":true};
__wxAppCode__['components/mpvue-echarts/src/echarts.wxml']=$gwx('./components/mpvue-echarts/src/echarts.wxml');

__wxAppCode__['components/mutiChoose.json']={"usingComponents":{},"component":true};
__wxAppCode__['components/mutiChoose.wxml']=$gwx('./components/mutiChoose.wxml');

__wxAppCode__['components/range-dtpicker/range-dtpicker.json']={"usingComponents":{},"component":true};
__wxAppCode__['components/range-dtpicker/range-dtpicker.wxml']=$gwx('./components/range-dtpicker/range-dtpicker.wxml');

__wxAppCode__['components/tabNav.json']={"usingComponents":{},"component":true};
__wxAppCode__['components/tabNav.wxml']=$gwx('./components/tabNav.wxml');

__wxAppCode__['components/uni-calendar/uni-calendar-item.json']={"usingComponents":{},"component":true};
__wxAppCode__['components/uni-calendar/uni-calendar-item.wxml']=$gwx('./components/uni-calendar/uni-calendar-item.wxml');

__wxAppCode__['components/uni-calendar/uni-calendar.json']={"usingComponents":{"uni-calendar-item":"/components/uni-calendar/uni-calendar-item"},"component":true};
__wxAppCode__['components/uni-calendar/uni-calendar.wxml']=$gwx('./components/uni-calendar/uni-calendar.wxml');

__wxAppCode__['pages/checking/cancelReg.json']={"navigationBarTitleText":"撤销验证","navigationBarTextStyle":"black","navigationBarBackgroundColor":"#fff","usingComponents":{}};
__wxAppCode__['pages/checking/cancelReg.wxml']=$gwx('./pages/checking/cancelReg.wxml');

__wxAppCode__['pages/checking/checking.json']={"navigationBarTitleText":"验证历史","navigationBarTextStyle":"black","navigationBarBackgroundColor":"#fff","usingComponents":{}};
__wxAppCode__['pages/checking/checking.wxml']=$gwx('./pages/checking/checking.wxml');

__wxAppCode__['pages/checking/detail.json']={"navigationBarTitleText":"日期详情","navigationBarTextStyle":"black","navigationBarBackgroundColor":"#fff","usingComponents":{}};
__wxAppCode__['pages/checking/detail.wxml']=$gwx('./pages/checking/detail.wxml');

__wxAppCode__['pages/checking/detail2.json']={"navigationBarTitleText":"详情","navigationBarTextStyle":"black","navigationBarBackgroundColor":"#fff","usingComponents":{"choos-type":"/components/mutiChoose","range-date-pick":"/components/range-dtpicker/range-dtpicker"}};
__wxAppCode__['pages/checking/detail2.wxml']=$gwx('./pages/checking/detail2.wxml');

__wxAppCode__['pages/checking/historyList.json']={"navigationBarTitleText":"验证历史","navigationBarTextStyle":"black","navigationBarBackgroundColor":"#fff","usingComponents":{}};
__wxAppCode__['pages/checking/historyList.wxml']=$gwx('./pages/checking/historyList.wxml');

__wxAppCode__['pages/checking/historyList2.json']={"navigationBarTitleText":"验证历史","navigationBarTextStyle":"black","navigationBarBackgroundColor":"#fff","usingComponents":{}};
__wxAppCode__['pages/checking/historyList2.wxml']=$gwx('./pages/checking/historyList2.wxml');

__wxAppCode__['pages/checking/search.json']={"navigationBarTitleText":"验证历史","navigationBarTextStyle":"black","navigationBarBackgroundColor":"#fff","usingComponents":{"range-date-pick":"/components/range-dtpicker/range-dtpicker"}};
__wxAppCode__['pages/checking/search.wxml']=$gwx('./pages/checking/search.wxml');

__wxAppCode__['pages/data/calendar.json']={"navigationBarTitleText":"日历","navigationBarBackgroundColor":"#fff","usingComponents":{}};
__wxAppCode__['pages/data/calendar.wxml']=$gwx('./pages/data/calendar.wxml');

__wxAppCode__['pages/data/comment.json']={"navigationBarTitleText":"评论","navigationBarBackgroundColor":"#fff","enablePullDownRefresh":true,"usingComponents":{"tab-nav":"/components/tabNav"}};
__wxAppCode__['pages/data/comment.wxml']=$gwx('./pages/data/comment.wxml');

__wxAppCode__['pages/data/commentDetail.json']={"navigationBarTitleText":"评价详情","navigationBarBackgroundColor":"#fff","usingComponents":{}};
__wxAppCode__['pages/data/commentDetail.wxml']=$gwx('./pages/data/commentDetail.wxml');

__wxAppCode__['pages/data/commentDetail2.json']={"navigationBarTitleText":"评价详情","navigationBarBackgroundColor":"#fff","usingComponents":{}};
__wxAppCode__['pages/data/commentDetail2.wxml']=$gwx('./pages/data/commentDetail2.wxml');

__wxAppCode__['pages/data/commentDetail3.json']={"navigationBarTitleText":"评价详情","navigationBarBackgroundColor":"#fff","usingComponents":{}};
__wxAppCode__['pages/data/commentDetail3.wxml']=$gwx('./pages/data/commentDetail3.wxml');

__wxAppCode__['pages/data/data.json']={"navigationBarTitleText":"数据","navigationBarBackgroundColor":"#fff","usingComponents":{"mpvue-echarts":"/components/mpvue-echarts/src/echarts","uni-calendar":"/components/uni-calendar/uni-calendar"}};
__wxAppCode__['pages/data/data.wxml']=$gwx('./pages/data/data.wxml');

__wxAppCode__['pages/data/problem.json']={"navigationBarTitleText":"问答","navigationBarBackgroundColor":"#fff","usingComponents":{}};
__wxAppCode__['pages/data/problem.wxml']=$gwx('./pages/data/problem.wxml');

__wxAppCode__['pages/data/replay.json']={"navigationBarTitleText":"我要回复","navigationBarBackgroundColor":"#fff","usingComponents":{}};
__wxAppCode__['pages/data/replay.wxml']=$gwx('./pages/data/replay.wxml');

__wxAppCode__['pages/data/scenic.json']={"navigationBarTitleText":"景区","navigationBarBackgroundColor":"#fff","usingComponents":{}};
__wxAppCode__['pages/data/scenic.wxml']=$gwx('./pages/data/scenic.wxml');

__wxAppCode__['pages/index/index.json']={"navigationBarTitleText":"详情","navigationBarTextStyle":"black","navigationBarBackgroundColor":"#fff","titleNView":{"buttons":[{"type":"none","text":"商户","float":"right"}]},"usingComponents":{}};
__wxAppCode__['pages/index/index.wxml']=$gwx('./pages/index/index.wxml');

__wxAppCode__['pages/login/forget.json']={"navigationBarTitleText":"忘记密码","navigationBarTextStyle":"white","navigationBarBackgroundColor":"#00094A","usingComponents":{"forget":"/components/forget-header"}};
__wxAppCode__['pages/login/forget.wxml']=$gwx('./pages/login/forget.wxml');

__wxAppCode__['pages/login/forget1.json']={"navigationBarTitleText":"忘记密码","navigationBarTextStyle":"white","navigationBarBackgroundColor":"#00094A","usingComponents":{"forget":"/components/forget-header"}};
__wxAppCode__['pages/login/forget1.wxml']=$gwx('./pages/login/forget1.wxml');

__wxAppCode__['pages/login/forget2.json']={"navigationBarTitleText":"忘记密码","navigationBarTextStyle":"white","navigationBarBackgroundColor":"#00094A","usingComponents":{"forget":"/components/forget-header"}};
__wxAppCode__['pages/login/forget2.wxml']=$gwx('./pages/login/forget2.wxml');

__wxAppCode__['pages/login/login.json']={"titleNView":false,"usingComponents":{}};
__wxAppCode__['pages/login/login.wxml']=$gwx('./pages/login/login.wxml');

__wxAppCode__['pages/new/new.json']={"usingComponents":{}};
__wxAppCode__['pages/new/new.wxml']=$gwx('./pages/new/new.wxml');

__wxAppCode__['pages/person/edit.json']={"navigationBarTitleText":"员工管理","navigationBarTextStyle":"black","navigationBarBackgroundColor":"#fff","backgroundColor":"#F8F8F8","titleNView":{"buttons":[{"type":"none","text":"保存","float":"right"}]},"usingComponents":{}};
__wxAppCode__['pages/person/edit.wxml']=$gwx('./pages/person/edit.wxml');

__wxAppCode__['pages/person/manage.json']={"navigationBarTitleText":"员工管理","navigationBarTextStyle":"black","navigationBarBackgroundColor":"#fff","backgroundColor":"#F8F8F8","usingComponents":{}};
__wxAppCode__['pages/person/manage.wxml']=$gwx('./pages/person/manage.wxml');

__wxAppCode__['pages/person/person.json']={"navigationBarTitleText":"我的","navigationBarBackgroundColor":"#fff","usingComponents":{}};
__wxAppCode__['pages/person/person.wxml']=$gwx('./pages/person/person.wxml');



define('common/main.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["common/main"],{"30ef":function(n,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var u={onLaunch:function(){},onShow:function(){},onHide:function(){}};t.default=u},8204:function(n,t,o){"use strict";var u=o("ffb9"),e=o.n(u);e.a},"8dc1":function(n,t,o){"use strict";o.r(t);var u=o("30ef"),e=o.n(u);for(var f in u)"default"!==f&&function(n){o.d(t,n,function(){return u[n]})}(f);t["default"]=e.a},b3ad:function(n,t,o){"use strict";o.r(t);var u=o("8dc1");for(var e in u)"default"!==e&&function(n){o.d(t,n,function(){return u[n]})}(e);o("8204");var f,c,a=o("2877"),r=Object(a["a"])(u["default"],f,c,!1,null,null,null);t["default"]=r.exports},ffb9:function(n,t,o){}},[["6492","common/runtime","common/vendor"]]]);
});
define('common/runtime.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
"use strict";

(function (e) {
  function n(n) {
    for (var r, o, u = n[0], i = n[1], s = n[2], l = 0, p = []; l < u.length; l++) {
      o = u[l], a[o] && p.push(a[o][0]), a[o] = 0;
    }

    for (r in i) {
      Object.prototype.hasOwnProperty.call(i, r) && (e[r] = i[r]);
    }

    m && m(n);

    while (p.length) {
      p.shift()();
    }

    return c.push.apply(c, s || []), t();
  }

  function t() {
    for (var e, n = 0; n < c.length; n++) {
      for (var t = c[n], r = !0, o = 1; o < t.length; o++) {
        var u = t[o];
        0 !== a[u] && (r = !1);
      }

      r && (c.splice(n--, 1), e = i(i.s = t[0]));
    }

    return e;
  }

  var r = {},
      o = {
    "common/runtime": 0
  },
      a = {
    "common/runtime": 0
  },
      c = [];

  function u(e) {
    return i.p + "" + e + ".js";
  }

  function i(n) {
    if (r[n]) return r[n].exports;
    var t = r[n] = {
      i: n,
      l: !1,
      exports: {}
    };
    return e[n].call(t.exports, t, t.exports, i), t.l = !0, t.exports;
  }

  i.e = function (e) {
    var n = [],
        t = {
      "components/forget-header": 1,
      "components/mpvue-echarts/src/echarts": 1,
      "components/uni-calendar/uni-calendar": 1,
      "components/tabNav": 1,
      "components/range-dtpicker/range-dtpicker": 1,
      "components/mutiChoose": 1,
      "components/uni-calendar/uni-calendar-item": 1
    };
    o[e] ? n.push(o[e]) : 0 !== o[e] && t[e] && n.push(o[e] = new Promise(function (n, t) {
      for (var r = ({
        "components/forget-header": "components/forget-header",
        "components/mpvue-echarts/src/echarts": "components/mpvue-echarts/src/echarts",
        "components/uni-calendar/uni-calendar": "components/uni-calendar/uni-calendar",
        "components/tabNav": "components/tabNav",
        "components/range-dtpicker/range-dtpicker": "components/range-dtpicker/range-dtpicker",
        "components/mutiChoose": "components/mutiChoose",
        "components/uni-calendar/uni-calendar-item": "components/uni-calendar/uni-calendar-item"
      }[e] || e) + ".wxss", a = i.p + r, c = document.getElementsByTagName("link"), u = 0; u < c.length; u++) {
        var s = c[u],
            l = s.getAttribute("data-href") || s.getAttribute("href");
        if ("stylesheet" === s.rel && (l === r || l === a)) return n();
      }

      var p = document.getElementsByTagName("style");

      for (u = 0; u < p.length; u++) {
        s = p[u], l = s.getAttribute("data-href");
        if (l === r || l === a) return n();
      }

      var m = document.createElement("link");
      m.rel = "stylesheet", m.type = "text/css", m.onload = n, m.onerror = function (n) {
        var r = n && n.target && n.target.src || a,
            c = new Error("Loading CSS chunk " + e + " failed.\n(" + r + ")");
        c.request = r, delete o[e], m.parentNode.removeChild(m), t(c);
      }, m.href = a;
      var d = document.getElementsByTagName("head")[0];
      d.appendChild(m);
    }).then(function () {
      o[e] = 0;
    }));
    var r = a[e];
    if (0 !== r) if (r) n.push(r[2]);else {
      var c = new Promise(function (n, t) {
        r = a[e] = [n, t];
      });
      n.push(r[2] = c);
      var s,
          l = document.createElement("script");
      l.charset = "utf-8", l.timeout = 120, i.nc && l.setAttribute("nonce", i.nc), l.src = u(e), s = function s(n) {
        l.onerror = l.onload = null, clearTimeout(p);
        var t = a[e];

        if (0 !== t) {
          if (t) {
            var r = n && ("load" === n.type ? "missing" : n.type),
                o = n && n.target && n.target.src,
                c = new Error("Loading chunk " + e + " failed.\n(" + r + ": " + o + ")");
            c.type = r, c.request = o, t[1](c);
          }

          a[e] = void 0;
        }
      };
      var p = setTimeout(function () {
        s({
          type: "timeout",
          target: l
        });
      }, 12e4);
      l.onerror = l.onload = s, document.head.appendChild(l);
    }
    return Promise.all(n);
  }, i.m = e, i.c = r, i.d = function (e, n, t) {
    i.o(e, n) || Object.defineProperty(e, n, {
      enumerable: !0,
      get: t
    });
  }, i.r = function (e) {
    "undefined" !== typeof Symbol && Symbol.toStringTag && Object.defineProperty(e, Symbol.toStringTag, {
      value: "Module"
    }), Object.defineProperty(e, "__esModule", {
      value: !0
    });
  }, i.t = function (e, n) {
    if (1 & n && (e = i(e)), 8 & n) return e;
    if (4 & n && "object" === typeof e && e && e.__esModule) return e;
    var t = Object.create(null);
    if (i.r(t), Object.defineProperty(t, "default", {
      enumerable: !0,
      value: e
    }), 2 & n && "string" != typeof e) for (var r in e) {
      i.d(t, r, function (n) {
        return e[n];
      }.bind(null, r));
    }
    return t;
  }, i.n = function (e) {
    var n = e && e.__esModule ? function () {
      return e["default"];
    } : function () {
      return e;
    };
    return i.d(n, "a", n), n;
  }, i.o = function (e, n) {
    return Object.prototype.hasOwnProperty.call(e, n);
  }, i.p = "/", i.oe = function (e) {
    throw console.error(e), e;
  };
  var s = global["webpackJsonp"] = global["webpackJsonp"] || [],
      l = s.push.bind(s);
  s.push = n, s = s.slice();

  for (var p = 0; p < s.length; p++) {
    n(s[p]);
  }

  var m = l;
  t();
})([]);
});
define('common/vendor.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["common/vendor"],{"0742":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("d249"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},"08f3":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("8ec4"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},"0f02":function(t,e,n){"use strict";!function(t,n){n(e)}(0,function(t,e,n){function i(t,e){"createCanvas"===t&&(Lc=null),Ic[t]=e}function r(t){if(null==t||"object"!=typeof t)return t;var e=t,n=Sc.call(t);if("[object Array]"===n){if(!N(t)){e=[];for(var i=0,o=t.length;i<o;i++)e[i]=r(t[i])}}else if(wc[n]){if(!N(t)){var a=t.constructor;if(t.constructor.from)e=a.from(t);else{e=new a(t.length);for(i=0,o=t.length;i<o;i++)e[i]=r(t[i])}}}else if(!bc[n]&&!N(t)&&!T(t))for(var s in e={},t)t.hasOwnProperty(s)&&(e[s]=r(t[s]));return e}function o(t,e,n){if(!S(e)||!S(t))return n?r(e):t;for(var i in e)if(e.hasOwnProperty(i)){var a=t[i],s=e[i];!S(s)||!S(a)||x(s)||x(a)||T(s)||T(a)||M(s)||M(a)||N(s)||N(a)?!n&&i in t||(t[i]=r(e[i],!0)):o(a,s,n)}return t}function a(t,e){for(var n=t[0],i=1,r=t.length;i<r;i++)n=o(n,t[i],e);return n}function s(t,e){for(var n in e)e.hasOwnProperty(n)&&(t[n]=e[n]);return t}function l(t,e,n){for(var i in e)e.hasOwnProperty(i)&&(n?null!=e[i]:null==t[i])&&(t[i]=e[i]);return t}function c(){return Lc||(Lc=Dc().getContext("2d")),Lc}function u(t,e){if(t){if(t.indexOf)return t.indexOf(e);for(var n=0,i=t.length;n<i;n++)if(t[n]===e)return n}return-1}function h(t,e){function n(){}var i=t.prototype;for(var r in n.prototype=e.prototype,t.prototype=new n,i)t.prototype[r]=i[r];t.prototype.constructor=t,t.superClass=e}function f(t,e,n){l(t="prototype"in t?t.prototype:t,e="prototype"in e?e.prototype:e,n)}function d(t){if(t)return"string"!=typeof t&&"number"==typeof t.length}function p(t,e,n){if(t&&e)if(t.forEach&&t.forEach===Ac)t.forEach(e,n);else if(t.length===+t.length)for(var i=0,r=t.length;i<r;i++)e.call(n,t[i],i,t);else for(var o in t)t.hasOwnProperty(o)&&e.call(n,t[o],o,t)}function g(t,e,n){if(t&&e){if(t.map&&t.map===Pc)return t.map(e,n);for(var i=[],r=0,o=t.length;r<o;r++)i.push(e.call(n,t[r],r,t));return i}}function v(t,e,n,i){if(t&&e){if(t.reduce&&t.reduce===Cc)return t.reduce(e,n,i);for(var r=0,o=t.length;r<o;r++)n=e.call(i,n,t[r],r,t);return n}}function m(t,e,n){if(t&&e){if(t.filter&&t.filter===Tc)return t.filter(e,n);for(var i=[],r=0,o=t.length;r<o;r++)e.call(n,t[r],r,t)&&i.push(t[r]);return i}}function y(t,e){var n=kc.call(arguments,2);return function(){return t.apply(e,n.concat(kc.call(arguments)))}}function _(t){var e=kc.call(arguments,1);return function(){return t.apply(this,e.concat(kc.call(arguments)))}}function x(t){return"[object Array]"===Sc.call(t)}function b(t){return"function"==typeof t}function w(t){return"[object String]"===Sc.call(t)}function S(t){var e=typeof t;return"function"===e||!!t&&"object"==e}function M(t){return!!bc[Sc.call(t)]}function A(t){return!!wc[Sc.call(t)]}function T(t){return"object"==typeof t&&"number"==typeof t.nodeType&&"object"==typeof t.ownerDocument}function k(t){return t!==t}function P(t){for(var e=0,n=arguments.length;e<n;e++)if(null!=arguments[e])return arguments[e]}function C(t,e){return null!=t?t:e}function I(t,e,n){return null!=t?t:null!=e?e:n}function D(){return Function.call.apply(kc,arguments)}function L(t){if("number"==typeof t)return[t,t,t,t];var e=t.length;return 2===e?[t[0],t[1],t[0],t[1]]:3===e?[t[0],t[1],t[2],t[1]]:t}function O(t,e){if(!t)throw new Error(e)}function E(t){return null==t?null:"function"==typeof t.trim?t.trim():t.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g,"")}function R(t){t[Oc]=!0}function N(t){return t[Oc]}function z(t){function e(t,e){n?i.set(t,e):i.set(e,t)}var n=x(t),i=this;t instanceof z?t.each(e):t&&p(t,e)}function F(t){return new z(t)}function B(){}function $(t,e){var n=new Ec(2);return null==t&&(t=0),null==e&&(e=0),n[0]=t,n[1]=e,n}function H(t){var e=new Ec(2);return e[0]=t[0],e[1]=t[1],e}function W(t,e,n){return t[0]=e[0]+n[0],t[1]=e[1]+n[1],t}function V(t,e,n){return t[0]=e[0]-n[0],t[1]=e[1]-n[1],t}function j(t){return Math.sqrt(G(t))}function G(t){return t[0]*t[0]+t[1]*t[1]}function U(t,e,n){return t[0]=e[0]*n,t[1]=e[1]*n,t}function X(t,e){var n=j(e);return 0===n?(t[0]=0,t[1]=0):(t[0]=e[0]/n,t[1]=e[1]/n),t}function q(t,e){return Math.sqrt((t[0]-e[0])*(t[0]-e[0])+(t[1]-e[1])*(t[1]-e[1]))}function Y(t,e,n){var i=e[0],r=e[1];return t[0]=n[0]*i+n[2]*r+n[4],t[1]=n[1]*i+n[3]*r+n[5],t}function Z(t,e,n){return t[0]=Math.min(e[0],n[0]),t[1]=Math.min(e[1],n[1]),t}function K(t,e,n){return t[0]=Math.max(e[0],n[0]),t[1]=Math.max(e[1],n[1]),t}function J(){this.on("mousedown",this._dragStart,this),this.on("mousemove",this._drag,this),this.on("mouseup",this._dragEnd,this),this.on("globalout",this._dragEnd,this)}function Q(t,e){return{target:t,topTarget:e&&e.topTarget}}function tt(t,e,n){return{type:t,event:n,target:e.target,topTarget:e.topTarget,cancelBubble:!1,offsetX:n.zrX,offsetY:n.zrY,gestureEvent:n.gestureEvent,pinchX:n.pinchX,pinchY:n.pinchY,pinchScale:n.pinchScale,wheelDelta:n.zrDelta,zrByTouch:n.zrByTouch,which:n.which}}function et(){}function nt(t,e,n){if(t[t.rectHover?"rectContain":"contain"](e,n)){for(var i,r=t;r;){if(r.clipPath&&!r.clipPath.contain(e,n))return!1;r.silent&&(i=!0),r=r.parent}return!i||Bc}return!1}function it(){var t=new Wc(6);return rt(t),t}function rt(t){return t[0]=1,t[1]=0,t[2]=0,t[3]=1,t[4]=0,t[5]=0,t}function ot(t,e){return t[0]=e[0],t[1]=e[1],t[2]=e[2],t[3]=e[3],t[4]=e[4],t[5]=e[5],t}function at(t,e,n){var i=e[0]*n[0]+e[2]*n[1],r=e[1]*n[0]+e[3]*n[1],o=e[0]*n[2]+e[2]*n[3],a=e[1]*n[2]+e[3]*n[3],s=e[0]*n[4]+e[2]*n[5]+e[4],l=e[1]*n[4]+e[3]*n[5]+e[5];return t[0]=i,t[1]=r,t[2]=o,t[3]=a,t[4]=s,t[5]=l,t}function st(t,e,n){return t[0]=e[0],t[1]=e[1],t[2]=e[2],t[3]=e[3],t[4]=e[4]+n[0],t[5]=e[5]+n[1],t}function lt(t,e,n){var i=e[0],r=e[2],o=e[4],a=e[1],s=e[3],l=e[5],c=Math.sin(n),u=Math.cos(n);return t[0]=i*u+a*c,t[1]=-i*c+a*u,t[2]=r*u+s*c,t[3]=-r*c+u*s,t[4]=u*o+c*l,t[5]=u*l-c*o,t}function ct(t,e,n){var i=n[0],r=n[1];return t[0]=e[0]*i,t[1]=e[1]*r,t[2]=e[2]*i,t[3]=e[3]*r,t[4]=e[4]*i,t[5]=e[5]*r,t}function ut(t,e){var n=e[0],i=e[2],r=e[4],o=e[1],a=e[3],s=e[5],l=n*a-o*i;return l?(l=1/l,t[0]=a*l,t[1]=-o*l,t[2]=-i*l,t[3]=n*l,t[4]=(i*s-a*r)*l,t[5]=(o*r-n*s)*l,t):null}function ht(t){return t>jc||t<-jc}function ft(t){this._target=t.target,this._life=t.life||1e3,this._delay=t.delay||0,this._initialized=!1,this.loop=null!=t.loop&&t.loop,this.gap=t.gap||0,this.easing=t.easing||"Linear",this.onframe=t.onframe,this.ondestroy=t.ondestroy,this.onrestart=t.onrestart,this._pausedTime=0,this._paused=!1}function dt(t){return(t=Math.round(t))<0?0:t>255?255:t}function pt(t){return t<0?0:t>1?1:t}function gt(t){return dt(t.length&&"%"===t.charAt(t.length-1)?parseFloat(t)/100*255:parseInt(t,10))}function vt(t){return pt(t.length&&"%"===t.charAt(t.length-1)?parseFloat(t)/100:parseFloat(t))}function mt(t,e,n){return n<0?n+=1:n>1&&(n-=1),6*n<1?t+(e-t)*n*6:2*n<1?e:3*n<2?t+(e-t)*(2/3-n)*6:t}function yt(t,e,n,i,r){return t[0]=e,t[1]=n,t[2]=i,t[3]=r,t}function _t(t,e){return t[0]=e[0],t[1]=e[1],t[2]=e[2],t[3]=e[3],t}function xt(t,e){nu&&_t(nu,e),nu=eu.put(t,nu||e.slice())}function bt(t,e){if(t){e=e||[];var n=eu.get(t);if(n)return _t(e,n);var i=(t+="").replace(/ /g,"").toLowerCase();if(i in tu)return _t(e,tu[i]),xt(t,e),e;if("#"!==i.charAt(0)){var r=i.indexOf("("),o=i.indexOf(")");if(-1!==r&&o+1===i.length){var a=i.substr(0,r),s=i.substr(r+1,o-(r+1)).split(","),l=1;switch(a){case"rgba":if(4!==s.length)return void yt(e,0,0,0,1);l=vt(s.pop());case"rgb":return 3!==s.length?void yt(e,0,0,0,1):(yt(e,gt(s[0]),gt(s[1]),gt(s[2]),l),xt(t,e),e);case"hsla":return 4!==s.length?void yt(e,0,0,0,1):(s[3]=vt(s[3]),wt(s,e),xt(t,e),e);case"hsl":return 3!==s.length?void yt(e,0,0,0,1):(wt(s,e),xt(t,e),e);default:return}}yt(e,0,0,0,1)}else{if(4===i.length)return(c=parseInt(i.substr(1),16))>=0&&c<=4095?(yt(e,(3840&c)>>4|(3840&c)>>8,240&c|(240&c)>>4,15&c|(15&c)<<4,1),xt(t,e),e):void yt(e,0,0,0,1);if(7===i.length){var c=parseInt(i.substr(1),16);return c>=0&&c<=16777215?(yt(e,(16711680&c)>>16,(65280&c)>>8,255&c,1),xt(t,e),e):void yt(e,0,0,0,1)}}}}function wt(t,e){var n=(parseFloat(t[0])%360+360)%360/360,i=vt(t[1]),r=vt(t[2]),o=r<=.5?r*(i+1):r+i-r*i,a=2*r-o;return e=e||[],yt(e,dt(255*mt(a,o,n+1/3)),dt(255*mt(a,o,n)),dt(255*mt(a,o,n-1/3)),1),4===t.length&&(e[3]=t[3]),e}function St(t,e){var n=bt(t);if(n){for(var i=0;i<3;i++)n[i]=e<0?n[i]*(1-e)|0:(255-n[i])*e+n[i]|0,n[i]>255?n[i]=255:t[i]<0&&(n[i]=0);return Mt(n,4===n.length?"rgba":"rgb")}}function Mt(t,e){if(t&&t.length){var n=t[0]+","+t[1]+","+t[2];return"rgba"!==e&&"hsva"!==e&&"hsla"!==e||(n+=","+t[3]),e+"("+n+")"}}function At(t,e){return t[e]}function Tt(t,e,n){t[e]=n}function kt(t,e,n){return(e-t)*n+t}function Pt(t,e,n){return n>.5?e:t}function Ct(t,e,n,i,r){var o=t.length;if(1==r)for(s=0;s<o;s++)i[s]=kt(t[s],e[s],n);else for(var a=o&&t[0].length,s=0;s<o;s++)for(var l=0;l<a;l++)i[s][l]=kt(t[s][l],e[s][l],n)}function It(t,e,n){var i=t.length,r=e.length;if(i!==r)if(i>r)t.length=r;else for(a=i;a<r;a++)t.push(1===n?e[a]:iu.call(e[a]));for(var o=t[0]&&t[0].length,a=0;a<t.length;a++)if(1===n)isNaN(t[a])&&(t[a]=e[a]);else for(var s=0;s<o;s++)isNaN(t[a][s])&&(t[a][s]=e[a][s])}function Dt(t,e,n){if(t===e)return!0;var i=t.length;if(i!==e.length)return!1;if(1===n){for(o=0;o<i;o++)if(t[o]!==e[o])return!1}else for(var r=t[0].length,o=0;o<i;o++)for(var a=0;a<r;a++)if(t[o][a]!==e[o][a])return!1;return!0}function Lt(t,e,n,i,r,o,a,s,l){var c=t.length;if(1==l)for(h=0;h<c;h++)s[h]=Ot(t[h],e[h],n[h],i[h],r,o,a);else for(var u=t[0].length,h=0;h<c;h++)for(var f=0;f<u;f++)s[h][f]=Ot(t[h][f],e[h][f],n[h][f],i[h][f],r,o,a)}function Ot(t,e,n,i,r,o,a){var s=.5*(n-t),l=.5*(i-e);return(2*(e-n)+s+l)*a+(-3*(e-n)-2*s-l)*o+s*r+e}function Et(t){if(d(t)){var e=t.length;if(d(t[0])){for(var n=[],i=0;i<e;i++)n.push(iu.call(t[i]));return n}return iu.call(t)}return t}function Rt(t){return t[0]=Math.floor(t[0]),t[1]=Math.floor(t[1]),t[2]=Math.floor(t[2]),"rgba("+t.join(",")+")"}function Nt(t){var e=t[t.length-1].value;return d(e&&e[0])?2:1}function zt(t,e,n,i,r,o){var a=t._getter,s=t._setter,l="spline"===e,c=i.length;if(c){var u,h=d(i[0].value),f=!1,p=!1,g=h?Nt(i):0;i.sort(function(t,e){return t.time-e.time}),u=i[c-1].time;for(var v=[],m=[],y=i[0].value,_=!0,x=0;x<c;x++){v.push(i[x].time/u);var b=i[x].value;if(h&&Dt(b,y,g)||!h&&b===y||(_=!1),y=b,"string"==typeof b){var w=bt(b);w?(b=w,f=!0):p=!0}m.push(b)}if(o||!_){var S=m[c-1];for(x=0;x<c-1;x++)h?It(m[x],S,g):!isNaN(m[x])||isNaN(S)||p||f||(m[x]=S);h&&It(a(t._target,r),S,g);var M,A,T,k,P,C=0,I=0;if(f)var D=[0,0,0,0];var L=new ft({target:t._target,life:u,loop:t._loop,delay:t._delay,onframe:function(t,e){var n;if(e<0)n=0;else if(e<I){for(n=Math.min(C+1,c-1);n>=0&&!(v[n]<=e);n--);n=Math.min(n,c-2)}else{for(n=C;n<c&&!(v[n]>e);n++);n=Math.min(n-1,c-2)}C=n,I=e;var i=v[n+1]-v[n];if(0!==i)if(M=(e-v[n])/i,l)if(T=m[n],A=m[0===n?n:n-1],k=m[n>c-2?c-1:n+1],P=m[n>c-3?c-1:n+2],h)Lt(A,T,k,P,M,M*M,M*M*M,a(t,r),g);else{if(f)o=Lt(A,T,k,P,M,M*M,M*M*M,D,1),o=Rt(D);else{if(p)return Pt(T,k,M);o=Ot(A,T,k,P,M,M*M,M*M*M)}s(t,r,o)}else if(h)Ct(m[n],m[n+1],M,a(t,r),g);else{var o;if(f)Ct(m[n],m[n+1],M,D,1),o=Rt(D);else{if(p)return Pt(m[n],m[n+1],M);o=kt(m[n],m[n+1],M)}s(t,r,o)}},ondestroy:n});return e&&"spline"!==e&&(L.easing=e),L}}}function Ft(t,e,n,i){n<0&&(t+=n,n=-n),i<0&&(e+=i,i=-i),this.x=t,this.y=e,this.width=n,this.height=i}function Bt(t){for(var e=0;t>=gu;)e|=1&t,t>>=1;return t+e}function $t(t,e,n,i){var r=e+1;if(r===n)return 1;if(i(t[r++],t[e])<0){for(;r<n&&i(t[r],t[r-1])<0;)r++;Ht(t,e,r)}else for(;r<n&&i(t[r],t[r-1])>=0;)r++;return r-e}function Ht(t,e,n){for(n--;e<n;){var i=t[e];t[e++]=t[n],t[n--]=i}}function Wt(t,e,n,i,r){for(i===e&&i++;i<n;i++){for(var o,a=t[i],s=e,l=i;s<l;)r(a,t[o=s+l>>>1])<0?l=o:s=o+1;var c=i-s;switch(c){case 3:t[s+3]=t[s+2];case 2:t[s+2]=t[s+1];case 1:t[s+1]=t[s];break;default:for(;c>0;)t[s+c]=t[s+c-1],c--}t[s]=a}}function Vt(t,e,n,i,r,o){var a=0,s=0,l=1;if(o(t,e[n+r])>0){for(s=i-r;l<s&&o(t,e[n+r+l])>0;)a=l,(l=1+(l<<1))<=0&&(l=s);l>s&&(l=s),a+=r,l+=r}else{for(s=r+1;l<s&&o(t,e[n+r-l])<=0;)a=l,(l=1+(l<<1))<=0&&(l=s);l>s&&(l=s);var c=a;a=r-l,l=r-c}for(a++;a<l;){var u=a+(l-a>>>1);o(t,e[n+u])>0?a=u+1:l=u}return l}function jt(t,e,n,i,r,o){var a=0,s=0,l=1;if(o(t,e[n+r])<0){for(s=r+1;l<s&&o(t,e[n+r-l])<0;)a=l,(l=1+(l<<1))<=0&&(l=s);l>s&&(l=s);var c=a;a=r-l,l=r-c}else{for(s=i-r;l<s&&o(t,e[n+r+l])>=0;)a=l,(l=1+(l<<1))<=0&&(l=s);l>s&&(l=s),a+=r,l+=r}for(a++;a<l;){var u=a+(l-a>>>1);o(t,e[n+u])<0?l=u:a=u+1}return l}function Gt(t,e){function n(n){var s=o[n],c=a[n],u=o[n+1],h=a[n+1];a[n]=c+h,n===l-3&&(o[n+1]=o[n+2],a[n+1]=a[n+2]),l--;var f=jt(t[u],t,s,c,0,e);s+=f,0!==(c-=f)&&0!==(h=Vt(t[s+c-1],t,u,h,h-1,e))&&(c<=h?i(s,c,u,h):r(s,c,u,h))}function i(n,i,r,o){var a=0;for(a=0;a<i;a++)c[a]=t[n+a];var l=0,u=r,h=n;if(t[h++]=t[u++],0!=--o)if(1!==i){for(var f,d,p,g=s;;){f=0,d=0,p=!1;do{if(e(t[u],c[l])<0){if(t[h++]=t[u++],d++,f=0,0==--o){p=!0;break}}else if(t[h++]=c[l++],f++,d=0,1==--i){p=!0;break}}while((f|d)<g);if(p)break;do{if(0!==(f=jt(t[u],c,l,i,0,e))){for(a=0;a<f;a++)t[h+a]=c[l+a];if(h+=f,l+=f,(i-=f)<=1){p=!0;break}}if(t[h++]=t[u++],0==--o){p=!0;break}if(0!==(d=Vt(c[l],t,u,o,0,e))){for(a=0;a<d;a++)t[h+a]=t[u+a];if(h+=d,u+=d,0===(o-=d)){p=!0;break}}if(t[h++]=c[l++],1==--i){p=!0;break}g--}while(f>=vu||d>=vu);if(p)break;g<0&&(g=0),g+=2}if((s=g)<1&&(s=1),1===i){for(a=0;a<o;a++)t[h+a]=t[u+a];t[h+o]=c[l]}else{if(0===i)throw new Error;for(a=0;a<i;a++)t[h+a]=c[l+a]}}else{for(a=0;a<o;a++)t[h+a]=t[u+a];t[h+o]=c[l]}else for(a=0;a<i;a++)t[h+a]=c[l+a]}function r(n,i,r,o){var a=0;for(a=0;a<o;a++)c[a]=t[r+a];var l=n+i-1,u=o-1,h=r+o-1,f=0,d=0;if(t[h--]=t[l--],0!=--i)if(1!==o){for(var p=s;;){var g=0,v=0,m=!1;do{if(e(c[u],t[l])<0){if(t[h--]=t[l--],g++,v=0,0==--i){m=!0;break}}else if(t[h--]=c[u--],v++,g=0,1==--o){m=!0;break}}while((g|v)<p);if(m)break;do{if(0!=(g=i-jt(c[u],t,n,i,i-1,e))){for(i-=g,d=1+(h-=g),f=1+(l-=g),a=g-1;a>=0;a--)t[d+a]=t[f+a];if(0===i){m=!0;break}}if(t[h--]=c[u--],1==--o){m=!0;break}if(0!=(v=o-Vt(t[l],c,0,o,o-1,e))){for(o-=v,d=1+(h-=v),f=1+(u-=v),a=0;a<v;a++)t[d+a]=c[f+a];if(o<=1){m=!0;break}}if(t[h--]=t[l--],0==--i){m=!0;break}p--}while(g>=vu||v>=vu);if(m)break;p<0&&(p=0),p+=2}if((s=p)<1&&(s=1),1===o){for(d=1+(h-=i),f=1+(l-=i),a=i-1;a>=0;a--)t[d+a]=t[f+a];t[h]=c[u]}else{if(0===o)throw new Error;for(f=h-(o-1),a=0;a<o;a++)t[f+a]=c[a]}}else{for(d=1+(h-=i),f=1+(l-=i),a=i-1;a>=0;a--)t[d+a]=t[f+a];t[h]=c[u]}else for(f=h-(o-1),a=0;a<o;a++)t[f+a]=c[a]}var o,a,s=vu,l=0,c=[];o=[],a=[],this.mergeRuns=function(){for(;l>1;){var t=l-2;if(t>=1&&a[t-1]<=a[t]+a[t+1]||t>=2&&a[t-2]<=a[t]+a[t-1])a[t-1]<a[t+1]&&t--;else if(a[t]>a[t+1])break;n(t)}},this.forceMergeRuns=function(){for(;l>1;){var t=l-2;t>0&&a[t-1]<a[t+1]&&t--,n(t)}},this.pushRun=function(t,e){o[l]=t,a[l]=e,l+=1}}function Ut(t,e,n,i){n||(n=0),i||(i=t.length);var r=i-n;if(!(r<2)){var o=0;if(r<gu)return o=$t(t,n,i,e),void Wt(t,n,i,n+o,e);var a=new Gt(t,e),s=Bt(r);do{if((o=$t(t,n,i,e))<s){var l=r;l>s&&(l=s),Wt(t,n,n+l,n+o,e),o=l}a.pushRun(n,o),a.mergeRuns(),r-=o,n+=o}while(0!==r);a.forceMergeRuns()}}function Xt(t,e){return t.zlevel===e.zlevel?t.z===e.z?t.z2-e.z2:t.z-e.z:t.zlevel-e.zlevel}function qt(t,e,n){var i=null==e.x?0:e.x,r=null==e.x2?1:e.x2,o=null==e.y?0:e.y,a=null==e.y2?0:e.y2;return e.global||(i=i*n.width+n.x,r=r*n.width+n.x,o=o*n.height+n.y,a=a*n.height+n.y),i=isNaN(i)?0:i,r=isNaN(r)?1:r,o=isNaN(o)?0:o,a=isNaN(a)?0:a,t.createLinearGradient(i,o,r,a)}function Yt(t,e,n){var i=n.width,r=n.height,o=Math.min(i,r),a=null==e.x?.5:e.x,s=null==e.y?.5:e.y,l=null==e.r?.5:e.r;return e.global||(a=a*i+n.x,s=s*r+n.y,l*=o),t.createRadialGradient(a,s,0,a,s,l)}function Zt(){return!1}function Kt(t,e,n){var i=Dc(),r=e.getWidth(),o=e.getHeight(),a=i.style;return a&&(a.position="absolute",a.left=0,a.top=0,a.width=r+"px",a.height=o+"px",i.setAttribute("data-zr-dom-id",t)),i.width=r*n,i.height=o*n,i}function Jt(t){if("string"==typeof t){var e=Pu.get(t);return e&&e.image}return t}function Qt(t,e,n,i,r){if(t){if("string"==typeof t){if(e&&e.__zrImageSrc===t||!n)return e;var o=Pu.get(t),a={hostEl:n,cb:i,cbPayload:r};return o?!ee(e=o.image)&&o.pending.push(a):(!e&&(e=new Image),e.onload=te,Pu.put(t,e.__cachedImgObj={image:e,pending:[a]}),e.src=e.__zrImageSrc=t),e}return t}return e}function te(){var t=this.__cachedImgObj;this.onload=this.__cachedImgObj=null;for(var e=0;e<t.pending.length;e++){var n=t.pending[e],i=n.cb;i&&i(this,n.cbPayload),n.hostEl.dirty()}t.pending.length=0}function ee(t){return t&&t.width&&t.height}function ne(t,e){var n=t+":"+(e=e||Ou);if(Cu[n])return Cu[n];for(var i=(t+"").split("\n"),r=0,o=0,a=i.length;o<a;o++)r=Math.max(pe(i[o],e).width,r);return Iu>Du&&(Iu=0,Cu={}),Iu++,Cu[n]=r,r}function ie(t,e,n,i,r,o,a){return o?oe(t,e,n,i,r,o,a):re(t,e,n,i,r,a)}function re(t,e,n,i,r,o){var a=ge(t,e,r,o),s=ne(t,e);r&&(s+=r[1]+r[3]);var l=a.outerHeight,c=new Ft(ae(0,s,n),se(0,l,i),s,l);return c.lineHeight=a.lineHeight,c}function oe(t,e,n,i,r,o,a){var s=ve(t,{rich:o,truncate:a,font:e,textAlign:n,textPadding:r}),l=s.outerWidth,c=s.outerHeight;return new Ft(ae(0,l,n),se(0,c,i),l,c)}function ae(t,e,n){return"right"===n?t-=e:"center"===n&&(t-=e/2),t}function se(t,e,n){return"middle"===n?t-=e/2:"bottom"===n&&(t-=e),t}function le(t,e,n){var i=e.x,r=e.y,o=e.height,a=e.width,s=o/2,l="left",c="top";switch(t){case"left":i-=n,r+=s,l="right",c="middle";break;case"right":i+=n+a,r+=s,c="middle";break;case"top":i+=a/2,r-=n,l="center",c="bottom";break;case"bottom":i+=a/2,r+=o+n,l="center";break;case"inside":i+=a/2,r+=s,l="center",c="middle";break;case"insideLeft":i+=n,r+=s,c="middle";break;case"insideRight":i+=a-n,r+=s,l="right",c="middle";break;case"insideTop":i+=a/2,r+=n,l="center";break;case"insideBottom":i+=a/2,r+=o-n,l="center",c="bottom";break;case"insideTopLeft":i+=n,r+=n;break;case"insideTopRight":i+=a-n,r+=n,l="right";break;case"insideBottomLeft":i+=n,r+=o-n,c="bottom";break;case"insideBottomRight":i+=a-n,r+=o-n,l="right",c="bottom"}return{x:i,y:r,textAlign:l,textVerticalAlign:c}}function ce(t,e,n,i,r){if(!e)return"";var o=(t+"").split("\n");r=ue(e,n,i,r);for(var a=0,s=o.length;a<s;a++)o[a]=he(o[a],r);return o.join("\n")}function ue(t,e,n,i){(i=s({},i)).font=e;n=C(n,"...");i.maxIterations=C(i.maxIterations,2);var r=i.minChar=C(i.minChar,0);i.cnCharWidth=ne("国",e);var o=i.ascCharWidth=ne("a",e);i.placeholder=C(i.placeholder,"");for(var a=t=Math.max(0,t-1),l=0;l<r&&a>=o;l++)a-=o;var c=ne(n);return c>a&&(n="",c=0),a=t-c,i.ellipsis=n,i.ellipsisWidth=c,i.contentWidth=a,i.containerWidth=t,i}function he(t,e){var n=e.containerWidth,i=e.font,r=e.contentWidth;if(!n)return"";var o=ne(t,i);if(o<=n)return t;for(var a=0;;a++){if(o<=r||a>=e.maxIterations){t+=e.ellipsis;break}var s=0===a?fe(t,r,e.ascCharWidth,e.cnCharWidth):o>0?Math.floor(t.length*r/o):0;o=ne(t=t.substr(0,s),i)}return""===t&&(t=e.placeholder),t}function fe(t,e,n,i){for(var r=0,o=0,a=t.length;o<a&&r<e;o++){var s=t.charCodeAt(o);r+=0<=s&&s<=127?n:i}return o}function de(t){return ne("国",t)}function pe(t,e){return Eu.measureText(t,e)}function ge(t,e,n,i){null!=t&&(t+="");var r=de(e),o=t?t.split("\n"):[],a=o.length*r,s=a;if(n&&(s+=n[0]+n[2]),t&&i){var l=i.outerHeight,c=i.outerWidth;if(null!=l&&s>l)t="",o=[];else if(null!=c)for(var u=ue(c-(n?n[1]+n[3]:0),e,i.ellipsis,{minChar:i.minChar,placeholder:i.placeholder}),h=0,f=o.length;h<f;h++)o[h]=he(o[h],u)}return{lines:o,height:a,outerHeight:s,lineHeight:r}}function ve(t,e){var n={lines:[],width:0,height:0};if(null!=t&&(t+=""),!t)return n;for(var i,r=Lu.lastIndex=0;null!=(i=Lu.exec(t));){var o=i.index;o>r&&me(n,t.substring(r,o)),me(n,i[2],i[1]),r=Lu.lastIndex}r<t.length&&me(n,t.substring(r,t.length));var a=n.lines,s=0,l=0,c=[],u=e.textPadding,h=e.truncate,f=h&&h.outerWidth,d=h&&h.outerHeight;for(u&&(null!=f&&(f-=u[1]+u[3]),null!=d&&(d-=u[0]+u[2])),P=0;P<a.length;P++){for(var p=a[P],g=0,v=0,m=0;m<p.tokens.length;m++){var y=(D=p.tokens[m]).styleName&&e.rich[D.styleName]||{},_=D.textPadding=y.textPadding,x=D.font=y.font||e.font,b=D.textHeight=C(y.textHeight,de(x));if(_&&(b+=_[0]+_[2]),D.height=b,D.lineHeight=I(y.textLineHeight,e.textLineHeight,b),D.textAlign=y&&y.textAlign||e.textAlign,D.textVerticalAlign=y&&y.textVerticalAlign||"middle",null!=d&&s+D.lineHeight>d)return{lines:[],width:0,height:0};D.textWidth=ne(D.text,x);var w=y.textWidth,S=null==w||"auto"===w;if("string"==typeof w&&"%"===w.charAt(w.length-1))D.percentWidth=w,c.push(D),w=0;else{if(S){w=D.textWidth;var M=y.textBackgroundColor,A=M&&M.image;A&&ee(A=Jt(A))&&(w=Math.max(w,A.width*b/A.height))}var T=_?_[1]+_[3]:0;w+=T;var k=null!=f?f-v:null;null!=k&&k<w&&(!S||k<T?(D.text="",D.textWidth=w=0):(D.text=ce(D.text,k-T,x,h.ellipsis,{minChar:h.minChar}),D.textWidth=ne(D.text,x),w=D.textWidth+T))}v+=D.width=w,y&&(g=Math.max(g,D.lineHeight))}p.width=v,p.lineHeight=g,s+=g,l=Math.max(l,v)}n.outerWidth=n.width=C(e.textWidth,l),n.outerHeight=n.height=C(e.textHeight,s),u&&(n.outerWidth+=u[1]+u[3],n.outerHeight+=u[0]+u[2]);for(var P=0;P<c.length;P++){var D=c[P],L=D.percentWidth;D.width=parseInt(L,10)/100*l}return n}function me(t,e,n){for(var i=""===e,r=e.split("\n"),o=t.lines,a=0;a<r.length;a++){var s=r[a],l={styleName:n,text:s,isLineHolder:!s&&!i};if(a)o.push({tokens:[l]});else{var c=(o[o.length-1]||(o[0]={tokens:[]})).tokens,u=c.length;1===u&&c[0].isLineHolder?c[0]=l:(s||!u||i)&&c.push(l)}}}function ye(t){var e=(t.fontSize||t.fontFamily)&&[t.fontStyle,t.fontWeight,(t.fontSize||12)+"px",t.fontFamily||"sans-serif"].join(" ");return e&&E(e)||t.textFont||t.font}function _e(t,e){var n,i,r,o,a,s=e.x,l=e.y,c=e.width,u=e.height,h=e.r;c<0&&(s+=c,c=-c),u<0&&(l+=u,u=-u),"number"==typeof h?n=i=r=o=h:h instanceof Array?1===h.length?n=i=r=o=h[0]:2===h.length?(n=r=h[0],i=o=h[1]):3===h.length?(n=h[0],i=o=h[1],r=h[2]):(n=h[0],i=h[1],r=h[2],o=h[3]):n=i=r=o=0,n+i>c&&(n*=c/(a=n+i),i*=c/a),r+o>c&&(r*=c/(a=r+o),o*=c/a),i+r>u&&(i*=u/(a=i+r),r*=u/a),n+o>u&&(n*=u/(a=n+o),o*=u/a),t.moveTo(s+n,l),t.lineTo(s+c-i,l),0!==i&&t.arc(s+c-i,l+i,i,-Math.PI/2,0),t.lineTo(s+c,l+u-r),0!==r&&t.arc(s+c-r,l+u-r,r,0,Math.PI/2),t.lineTo(s+o,l+u),0!==o&&t.arc(s+o,l+u-o,o,Math.PI/2,Math.PI),t.lineTo(s,l+n),0!==n&&t.arc(s+n,l+n,n,Math.PI,1.5*Math.PI)}function xe(t){return be(t),p(t.rich,be),t}function be(t){if(t){t.font=ye(t);var e=t.textAlign;"middle"===e&&(e="center"),t.textAlign=null==e||Ru[e]?e:"left";var n=t.textVerticalAlign||t.textBaseline;"center"===n&&(n="middle"),t.textVerticalAlign=null==n||Nu[n]?n:"top",t.textPadding&&(t.textPadding=L(t.textPadding))}}function we(t,e,n,i,r){i.rich?Me(t,e,n,i,r):Se(t,e,n,i,r)}function Se(t,e,n,i,r){var o=Le(e,"font",i.font||Ou),a=i.textPadding,s=t.__textCotentBlock;s&&!t.__dirty||(s=t.__textCotentBlock=ge(n,o,a,i.truncate));var l=s.outerHeight,c=s.lines,u=s.lineHeight,h=De(l,i,r),f=h.baseX,d=h.baseY,p=h.textAlign,g=h.textVerticalAlign;Te(e,i,r,f,d);var v=se(d,l,g),m=f,y=v,_=Pe(i);if(_||a){var x=ne(n,o);a&&(x+=a[1]+a[3]);var b=ae(f,x,p);_&&Ce(t,e,i,b,v,x,l),a&&(m=Ne(f,p,a),y+=a[0])}Le(e,"textAlign",p||"left"),Le(e,"textBaseline","middle"),Le(e,"shadowBlur",i.textShadowBlur||0),Le(e,"shadowColor",i.textShadowColor||"transparent"),Le(e,"shadowOffsetX",i.textShadowOffsetX||0),Le(e,"shadowOffsetY",i.textShadowOffsetY||0),y+=u/2;var w=i.textStrokeWidth,S=Oe(i.textStroke,w),M=Ee(i.textFill);S&&(Le(e,"lineWidth",w),Le(e,"strokeStyle",S)),M&&Le(e,"fillStyle",M);for(var A=0;A<c.length;A++)S&&e.strokeText(c[A],m,y),M&&e.fillText(c[A],m,y),y+=u}function Me(t,e,n,i,r){var o=t.__textCotentBlock;o&&!t.__dirty||(o=t.__textCotentBlock=ve(n,i)),Ae(t,e,o,i,r)}function Ae(t,e,n,i,r){var o=n.width,a=n.outerWidth,s=n.outerHeight,l=i.textPadding,c=De(s,i,r),u=c.baseX,h=c.baseY,f=c.textAlign,d=c.textVerticalAlign;Te(e,i,r,u,h);var p=ae(u,a,f),g=se(h,s,d),v=p,m=g;l&&(v+=l[3],m+=l[0]);var y=v+o;Pe(i)&&Ce(t,e,i,p,g,a,s);for(var _=0;_<n.lines.length;_++){for(var x,b=n.lines[_],w=b.tokens,S=w.length,M=b.lineHeight,A=b.width,T=0,k=v,P=y,C=S-1;T<S&&(!(x=w[T]).textAlign||"left"===x.textAlign);)ke(t,e,x,i,M,m,k,"left"),A-=x.width,k+=x.width,T++;for(;C>=0&&"right"===(x=w[C]).textAlign;)ke(t,e,x,i,M,m,P,"right"),A-=x.width,P-=x.width,C--;for(k+=(o-(k-v)-(y-P)-A)/2;T<=C;)ke(t,e,x=w[T],i,M,m,k+x.width/2,"center"),k+=x.width,T++;m+=M}}function Te(t,e,n,i,r){if(n&&e.textRotation){var o=e.textOrigin;"center"===o?(i=n.width/2+n.x,r=n.height/2+n.y):o&&(i=o[0]+n.x,r=o[1]+n.y),t.translate(i,r),t.rotate(-e.textRotation),t.translate(-i,-r)}}function ke(t,e,n,i,r,o,a,s){var l=i.rich[n.styleName]||{},c=n.textVerticalAlign,u=o+r/2;"top"===c?u=o+n.height/2:"bottom"===c&&(u=o+r-n.height/2),!n.isLineHolder&&Pe(l)&&Ce(t,e,l,"right"===s?a-n.width:"center"===s?a-n.width/2:a,u-n.height/2,n.width,n.height);var h=n.textPadding;h&&(a=Ne(a,s,h),u-=n.height/2-h[2]-n.textHeight/2),Le(e,"shadowBlur",I(l.textShadowBlur,i.textShadowBlur,0)),Le(e,"shadowColor",l.textShadowColor||i.textShadowColor||"transparent"),Le(e,"shadowOffsetX",I(l.textShadowOffsetX,i.textShadowOffsetX,0)),Le(e,"shadowOffsetY",I(l.textShadowOffsetY,i.textShadowOffsetY,0)),Le(e,"textAlign",s),Le(e,"textBaseline","middle"),Le(e,"font",n.font||Ou);var f=Oe(l.textStroke||i.textStroke,p),d=Ee(l.textFill||i.textFill),p=C(l.textStrokeWidth,i.textStrokeWidth);f&&(Le(e,"lineWidth",p),Le(e,"strokeStyle",f),e.strokeText(n.text,a,u)),d&&(Le(e,"fillStyle",d),e.fillText(n.text,a,u))}function Pe(t){return t.textBackgroundColor||t.textBorderWidth&&t.textBorderColor}function Ce(t,e,n,i,r,o,a){var s=n.textBackgroundColor,l=n.textBorderWidth,c=n.textBorderColor,u=w(s);if(Le(e,"shadowBlur",n.textBoxShadowBlur||0),Le(e,"shadowColor",n.textBoxShadowColor||"transparent"),Le(e,"shadowOffsetX",n.textBoxShadowOffsetX||0),Le(e,"shadowOffsetY",n.textBoxShadowOffsetY||0),u||l&&c){e.beginPath();var h=n.textBorderRadius;h?_e(e,{x:i,y:r,width:o,height:a,r:h}):e.rect(i,r,o,a),e.closePath()}if(u)Le(e,"fillStyle",s),e.fill();else if(S(s)){var f=s.image;(f=Qt(f,null,t,Ie,s))&&ee(f)&&e.drawImage(f,i,r,o,a)}l&&c&&(Le(e,"lineWidth",l),Le(e,"strokeStyle",c),e.stroke())}function Ie(t,e){e.image=t}function De(t,e,n){var i=e.x||0,r=e.y||0,o=e.textAlign,a=e.textVerticalAlign;if(n){var s=e.textPosition;if(s instanceof Array)i=n.x+Re(s[0],n.width),r=n.y+Re(s[1],n.height);else{var l=le(s,n,e.textDistance);i=l.x,r=l.y,o=o||l.textAlign,a=a||l.textVerticalAlign}var c=e.textOffset;c&&(i+=c[0],r+=c[1])}return{baseX:i,baseY:r,textAlign:o,textVerticalAlign:a}}function Le(t,e,n){return t[e]=_u(t,e,n),t[e]}function Oe(t,e){return null==t||e<=0||"transparent"===t||"none"===t?null:t.image||t.colorStops?"#000":t}function Ee(t){return null==t||"none"===t?null:t.image||t.colorStops?"#000":t}function Re(t,e){return"string"==typeof t?t.lastIndexOf("%")>=0?parseFloat(t)/100*e:parseFloat(t):t}function Ne(t,e,n){return"right"===e?t-n[1]:"center"===e?t+n[3]/2-n[1]/2:t+n[3]}function ze(t,e){return null!=t&&(t||e.textBackgroundColor||e.textBorderWidth&&e.textBorderColor||e.textPadding)}function Fe(t){for(var e in t=t||{},uu.call(this,t),t)t.hasOwnProperty(e)&&"style"!==e&&(this[e]=t[e]);this.style=new bu(t.style,this),this._rect=null,this.__clipPaths=[]}function Be(t){Fe.call(this,t)}function $e(t){return parseInt(t,10)}function He(t){return!!t&&(!!t.__builtin__||"function"==typeof t.resize&&"function"==typeof t.refresh)}function We(t,e,n){return Bu.copy(t.getBoundingRect()),t.transform&&Bu.applyTransform(t.transform),$u.width=e,$u.height=n,!Bu.intersect($u)}function Ve(t,e){if(t==e)return!1;if(!t||!e||t.length!==e.length)return!0;for(var n=0;n<t.length;n++)if(t[n]!==e[n])return!0}function je(t,e){for(var n=0;n<t.length;n++){var i=t[n];i.setTransform(e),e.beginPath(),i.buildPath(e,i.shape),e.clip(),i.restoreTransform(e)}}function Ge(t,e){var i=n.createElement("div");return i.style.cssText=["position:relative","overflow:hidden","width:"+t+"px","height:"+e+"px","padding:0","margin:0","border-width:0"].join(";")+";",i}function Ue(t){return t.getBoundingClientRect?t.getBoundingClientRect():{left:0,top:0}}function Xe(t,e,n,i){return n=n||{},i||!xc.canvasSupported?qe(t,e,n):xc.browser.firefox&&null!=e.layerX&&e.layerX!==e.offsetX?(n.zrX=e.layerX,n.zrY=e.layerY):null!=e.offsetX?(n.zrX=e.offsetX,n.zrY=e.offsetY):qe(t,e,n),n}function qe(t,e,n){var i=Ue(t);n.zrX=e.clientX-i.left,n.zrY=e.clientY-i.top}function Ye(t,n,i){if(null!=(n=n||e.event).zrX)return n;var r=n.type;if(r&&r.indexOf("touch")>=0){var o="touchend"!=r?n.targetTouches[0]:n.changedTouches[0];o&&Xe(t,o,n,i)}else Xe(t,n,n,i),n.zrDelta=n.wheelDelta?n.wheelDelta/120:-(n.detail||0)/3;var a=n.button;return null==n.which&&void 0!==a&&Vu.test(n.type)&&(n.which=1&a?1:2&a?3:4&a?2:0),n}function Ze(t,e,n){Wu?t.addEventListener(e,n):t.attachEvent("on"+e,n)}function Ke(t,e,n){Wu?t.removeEventListener(e,n):t.detachEvent("on"+e,n)}function Je(t){var e=t[1][0]-t[0][0],n=t[1][1]-t[0][1];return Math.sqrt(e*e+n*n)}function Qe(t){return[(t[0][0]+t[1][0])/2,(t[0][1]+t[1][1])/2]}function tn(t){return"mousewheel"===t&&xc.browser.firefox?"DOMMouseScroll":t}function en(t,e,n){var i=t._gestureMgr;"start"===n&&i.clear();var r=i.recognize(e,t.handler.findHover(e.zrX,e.zrY,null).target,t.dom);if("end"===n&&i.clear(),r){var o=r.type;e.gestureEvent=o,t.handler.dispatchToElement({target:r.target},o,r.event)}}function nn(t){t._touching=!0,clearTimeout(t._touchTimer),t._touchTimer=setTimeout(function(){t._touching=!1},700)}function rn(t){var e=t.pointerType;return"pen"===e||"touch"===e}function on(t){function e(t,e){return function(){if(!e._touching)return t.apply(e,arguments)}}p(qu,function(e){t._handlers[e]=y(Ku[e],t)}),p(Zu,function(e){t._handlers[e]=y(Ku[e],t)}),p(Xu,function(n){t._handlers[n]=e(Ku[n],t)})}function an(t){function e(e,n){p(e,function(e){Ze(t,tn(e),n._handlers[e])},n)}Fc.call(this),this.dom=t,this._touching=!1,this._touchTimer,this._gestureMgr=new Gu,this._handlers={},on(this),xc.pointerEventsSupported?e(Zu,this):(xc.touchEventsSupported&&e(qu,this),e(Xu,this))}function sn(t,e){return new eh(_c(),t,e)}function ln(t){return t instanceof Array?t:null==t?[]:[t]}function cn(t,e,n){if(t){t[e]=t[e]||{},t.emphasis=t.emphasis||{},t.emphasis[e]=t.emphasis[e]||{};for(var i=0,r=n.length;i<r;i++){var o=n[i];!t.emphasis[e].hasOwnProperty(o)&&t[e].hasOwnProperty(o)&&(t.emphasis[e][o]=t[e][o])}}}function un(t){return!ih(t)||rh(t)||t instanceof Date?t:t.value}function hn(t){return ih(t)&&!(t instanceof Array)}function fn(t,e){e=(e||[]).slice();var n=g(t||[],function(t,e){return{exist:t}});return nh(e,function(t,i){if(ih(t)){for(r=0;r<n.length;r++)if(!n[r].option&&null!=t.id&&n[r].exist.id===t.id+"")return n[r].option=t,void(e[i]=null);for(var r=0;r<n.length;r++){var o=n[r].exist;if(!(n[r].option||null!=o.id&&null!=t.id||null==t.name||gn(t)||gn(o)||o.name!==t.name+""))return n[r].option=t,void(e[i]=null)}}}),nh(e,function(t,e){if(ih(t)){for(var i=0;i<n.length;i++){var r=n[i].exist;if(!n[i].option&&!gn(r)&&null==t.id){n[i].option=t;break}}i>=n.length&&n.push({option:t})}}),n}function dn(t){var e=F();nh(t,function(t,n){var i=t.exist;i&&e.set(i.id,t)}),nh(t,function(t,n){var i=t.option;O(!i||null==i.id||!e.get(i.id)||e.get(i.id)===t,"id duplicates: "+(i&&i.id)),i&&null!=i.id&&e.set(i.id,t),!t.keyInfo&&(t.keyInfo={})}),nh(t,function(t,n){var i=t.exist,r=t.option,o=t.keyInfo;if(ih(r)){if(o.name=null!=r.name?r.name+"":i?i.name:oh+n,i)o.id=i.id;else if(null!=r.id)o.id=r.id+"";else{var a=0;do{o.id="\0"+o.name+"\0"+a++}while(e.get(o.id))}e.set(o.id,t)}})}function pn(t){var e=t.name;return!(!e||!e.indexOf(oh))}function gn(t){return ih(t)&&t.id&&0===(t.id+"").indexOf("\0_ec_\0")}function vn(t,e){return null!=e.dataIndexInside?e.dataIndexInside:null!=e.dataIndex?x(e.dataIndex)?g(e.dataIndex,function(e){return t.indexOfRawIndex(e)}):t.indexOfRawIndex(e.dataIndex):null!=e.name?x(e.name)?g(e.name,function(e){return t.indexOfName(e)}):t.indexOfName(e.name):void 0}function mn(){var t="__\0ec_inner_"+sh+++"_"+Math.random().toFixed(5);return function(e){return e[t]||(e[t]={})}}function yn(t,e,n){if(w(e)){var i={};i[e+"Index"]=0,e=i}var r=n&&n.defaultMainType;!r||_n(e,r+"Index")||_n(e,r+"Id")||_n(e,r+"Name")||(e[r+"Index"]=0);var o={};return nh(e,function(i,r){i=e[r];if("dataIndex"!==r&&"dataIndexInside"!==r){var a=r.match(/^(\w+)(Index|Id|Name)$/)||[],s=a[1],l=(a[2]||"").toLowerCase();if(!(!s||!l||null==i||"index"===l&&"none"===i||n&&n.includeMainTypes&&u(n.includeMainTypes,s)<0)){var c={mainType:s};"index"===l&&"all"===i||(c[l]=i);var h=t.queryComponents(c);o[s+"Models"]=h,o[s+"Model"]=h[0]}}else o[r]=i}),o}function _n(t,e){return t&&t.hasOwnProperty(e)}function xn(t,e,n){t.setAttribute?t.setAttribute(e,n):t[e]=n}function bn(t,e){return t.getAttribute?t.getAttribute(e):t[e]}function wn(t){var e={main:"",sub:""};return t&&(t=t.split(lh),e.main=t[0]||"",e.sub=t[1]||""),e}function Sn(t){O(/^[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)?$/.test(t),'componentType "'+t+'" illegal')}function Mn(t,e){t.$constructor=t,t.extend=function(t){var e=this,n=function(){t.$constructor?t.$constructor.apply(this,arguments):e.apply(this,arguments)};return s(n.prototype,t),n.extend=this.extend,n.superCall=Tn,n.superApply=kn,h(n,this),n.superClass=e,n}}function An(t){var e=["__\0is_clz",uh++,Math.random().toFixed(3)].join("_");t.prototype[e]=!0,t.isInstance=function(t){return!(!t||!t[e])}}function Tn(t,e){var n=D(arguments,2);return this.superClass.prototype[e].apply(t,n)}function kn(t,e,n){return this.superClass.prototype[e].apply(t,n)}function Pn(t,e){function n(t){var e=i[t.main];return e&&e[ch]||((e=i[t.main]={})[ch]=!0),e}e=e||{};var i={};if(t.registerClass=function(t,e){return e&&(Sn(e),(e=wn(e)).sub?e.sub!==ch&&(n(e)[e.sub]=t):i[e.main]=t),t},t.getClass=function(t,e,n){var r=i[t];if(r&&r[ch]&&(r=e?r[e]:null),n&&!r)throw new Error(e?"Component "+t+"."+(e||"")+" not exists. Load it first.":t+".type should be specified.");return r},t.getClassesByMainType=function(t){t=wn(t);var e=[],n=i[t.main];return n&&n[ch]?p(n,function(t,n){n!==ch&&e.push(t)}):e.push(n),e},t.hasClass=function(t){return t=wn(t),!!i[t.main]},t.getAllClassMainTypes=function(){var t=[];return p(i,function(e,n){t.push(n)}),t},t.hasSubTypes=function(t){t=wn(t);var e=i[t.main];return e&&e[ch]},t.parseClassType=wn,e.registerWhenExtend){var r=t.extend;r&&(t.extend=function(e){var n=r.call(this,e);return t.registerClass(n,e.type)})}return t}function Cn(t){return t>-yh&&t<yh}function In(t){return t>yh||t<-yh}function Dn(t,e,n,i,r){var o=1-r;return o*o*(o*t+3*r*e)+r*r*(r*i+3*o*n)}function Ln(t,e,n,i,r){var o=1-r;return 3*(((e-t)*o+2*(n-e)*r)*o+(i-n)*r*r)}function On(t,e,n,i,r,o){var a=i+3*(e-n)-t,s=3*(n-2*e+t),l=3*(e-t),c=t-r,u=s*s-3*a*l,h=s*l-9*a*c,f=l*l-3*s*c,d=0;if(Cn(u)&&Cn(h))Cn(s)?o[0]=0:(M=-l/s)>=0&&M<=1&&(o[d++]=M);else{var p=h*h-4*u*f;if(Cn(p)){var g=h/u,v=-g/2;(M=-s/a+g)>=0&&M<=1&&(o[d++]=M),v>=0&&v<=1&&(o[d++]=v)}else if(p>0){var m=mh(p),y=u*s+1.5*a*(-h+m),_=u*s+1.5*a*(-h-m);(M=(-s-((y=y<0?-vh(-y,bh):vh(y,bh))+(_=_<0?-vh(-_,bh):vh(_,bh))))/(3*a))>=0&&M<=1&&(o[d++]=M)}else{var x=(2*u*s-3*a*h)/(2*mh(u*u*u)),b=Math.acos(x)/3,w=mh(u),S=Math.cos(b),M=(-s-2*w*S)/(3*a),A=(v=(-s+w*(S+xh*Math.sin(b)))/(3*a),(-s+w*(S-xh*Math.sin(b)))/(3*a));M>=0&&M<=1&&(o[d++]=M),v>=0&&v<=1&&(o[d++]=v),A>=0&&A<=1&&(o[d++]=A)}}return d}function En(t,e,n,i,r){var o=6*n-12*e+6*t,a=9*e+3*i-3*t-9*n,s=3*e-3*t,l=0;if(Cn(a))In(o)&&(h=-s/o)>=0&&h<=1&&(r[l++]=h);else{var c=o*o-4*a*s;if(Cn(c))r[0]=-o/(2*a);else if(c>0){var u=mh(c),h=(-o+u)/(2*a),f=(-o-u)/(2*a);h>=0&&h<=1&&(r[l++]=h),f>=0&&f<=1&&(r[l++]=f)}}return l}function Rn(t,e,n,i,r,o){var a=(e-t)*r+t,s=(n-e)*r+e,l=(i-n)*r+n,c=(s-a)*r+a,u=(l-s)*r+s,h=(u-c)*r+c;o[0]=t,o[1]=a,o[2]=c,o[3]=h,o[4]=h,o[5]=u,o[6]=l,o[7]=i}function Nn(t,e,n,i,r,o,a,s,l,c,u){var h,f,d,p,g,v=.005,m=1/0;wh[0]=l,wh[1]=c;for(var y=0;y<1;y+=.05)Sh[0]=Dn(t,n,r,a,y),Sh[1]=Dn(e,i,o,s,y),(p=Nc(wh,Sh))<m&&(h=y,m=p);m=1/0;for(var _=0;_<32&&!(v<_h);_++)f=h-v,d=h+v,Sh[0]=Dn(t,n,r,a,f),Sh[1]=Dn(e,i,o,s,f),p=Nc(Sh,wh),f>=0&&p<m?(h=f,m=p):(Mh[0]=Dn(t,n,r,a,d),Mh[1]=Dn(e,i,o,s,d),g=Nc(Mh,wh),d<=1&&g<m?(h=d,m=g):v*=.5);return u&&(u[0]=Dn(t,n,r,a,h),u[1]=Dn(e,i,o,s,h)),mh(m)}function zn(t,e,n,i){var r=1-i;return r*(r*t+2*i*e)+i*i*n}function Fn(t,e,n,i){return 2*((1-i)*(e-t)+i*(n-e))}function Bn(t,e,n,i,r){var o=t-2*e+n,a=2*(e-t),s=t-i,l=0;if(Cn(o))In(a)&&(h=-s/a)>=0&&h<=1&&(r[l++]=h);else{var c=a*a-4*o*s;if(Cn(c))(h=-a/(2*o))>=0&&h<=1&&(r[l++]=h);else if(c>0){var u=mh(c),h=(-a+u)/(2*o),f=(-a-u)/(2*o);h>=0&&h<=1&&(r[l++]=h),f>=0&&f<=1&&(r[l++]=f)}}return l}function $n(t,e,n){var i=t+n-2*e;return 0===i?.5:(t-e)/i}function Hn(t,e,n,i,r){var o=(e-t)*i+t,a=(n-e)*i+e,s=(a-o)*i+o;r[0]=t,r[1]=o,r[2]=s,r[3]=s,r[4]=a,r[5]=n}function Wn(t,e,n,i,r,o,a,s,l){var c,u=.005,h=1/0;wh[0]=a,wh[1]=s;for(var f=0;f<1;f+=.05)Sh[0]=zn(t,n,r,f),Sh[1]=zn(e,i,o,f),(v=Nc(wh,Sh))<h&&(c=f,h=v);h=1/0;for(var d=0;d<32&&!(u<_h);d++){var p=c-u,g=c+u;Sh[0]=zn(t,n,r,p),Sh[1]=zn(e,i,o,p);var v=Nc(Sh,wh);if(p>=0&&v<h)c=p,h=v;else{Mh[0]=zn(t,n,r,g),Mh[1]=zn(e,i,o,g);var m=Nc(Mh,wh);g<=1&&m<h?(c=g,h=m):u*=.5}}return l&&(l[0]=zn(t,n,r,c),l[1]=zn(e,i,o,c)),mh(h)}function Vn(t,e,n,i,r,o){r[0]=Ah(t,n),r[1]=Ah(e,i),o[0]=Th(t,n),o[1]=Th(e,i)}function jn(t,e,n,i,r,o,a,s,l,c){var u,h=En,f=Dn,d=h(t,n,r,a,Oh);for(l[0]=1/0,l[1]=1/0,c[0]=-1/0,c[1]=-1/0,u=0;u<d;u++){var p=f(t,n,r,a,Oh[u]);l[0]=Ah(p,l[0]),c[0]=Th(p,c[0])}for(d=h(e,i,o,s,Eh),u=0;u<d;u++){var g=f(e,i,o,s,Eh[u]);l[1]=Ah(g,l[1]),c[1]=Th(g,c[1])}l[0]=Ah(t,l[0]),c[0]=Th(t,c[0]),l[0]=Ah(a,l[0]),c[0]=Th(a,c[0]),l[1]=Ah(e,l[1]),c[1]=Th(e,c[1]),l[1]=Ah(s,l[1]),c[1]=Th(s,c[1])}function Gn(t,e,n,i,r,o,a,s){var l=$n,c=zn,u=Th(Ah(l(t,n,r),1),0),h=Th(Ah(l(e,i,o),1),0),f=c(t,n,r,u),d=c(e,i,o,h);a[0]=Ah(t,r,f),a[1]=Ah(e,o,d),s[0]=Th(t,r,f),s[1]=Th(e,o,d)}function Un(t,e,n,i,r,o,a,s,l){var c=Z,u=K,h=Math.abs(r-o);if(h%Ch<1e-4&&h>1e-4)return s[0]=t-n,s[1]=e-i,l[0]=t+n,void(l[1]=e+i);if(Ih[0]=Ph(r)*n+t,Ih[1]=kh(r)*i+e,Dh[0]=Ph(o)*n+t,Dh[1]=kh(o)*i+e,c(s,Ih,Dh),u(l,Ih,Dh),(r%=Ch)<0&&(r+=Ch),(o%=Ch)<0&&(o+=Ch),r>o&&!a?o+=Ch:r<o&&a&&(r+=Ch),a){var f=o;o=r,r=f}for(var d=0;d<o;d+=Math.PI/2)d>r&&(Lh[0]=Ph(d)*n+t,Lh[1]=kh(d)*i+e,c(s,Lh,s),u(l,Lh,l))}function Xn(t,e,n,i,r,o,a){if(0===r)return!1;var s=r,l=0;if(a>e+s&&a>i+s||a<e-s&&a<i-s||o>t+s&&o>n+s||o<t-s&&o<n-s)return!1;if(t===n)return Math.abs(o-t)<=s/2;var c=(l=(e-i)/(t-n))*o-a+(t*i-n*e)/(t-n);return c*c/(l*l+1)<=s/2*s/2}function qn(t,e,n,i,r,o,a,s,l,c,u){if(0===l)return!1;var h=l;return!(u>e+h&&u>i+h&&u>o+h&&u>s+h||u<e-h&&u<i-h&&u<o-h&&u<s-h||c>t+h&&c>n+h&&c>r+h&&c>a+h||c<t-h&&c<n-h&&c<r-h&&c<a-h)&&Nn(t,e,n,i,r,o,a,s,c,u,null)<=h/2}function Yn(t,e,n,i,r,o,a,s,l){if(0===a)return!1;var c=a;return!(l>e+c&&l>i+c&&l>o+c||l<e-c&&l<i-c&&l<o-c||s>t+c&&s>n+c&&s>r+c||s<t-c&&s<n-c&&s<r-c)&&Wn(t,e,n,i,r,o,s,l,null)<=c/2}function Zn(t){return(t%=qh)<0&&(t+=qh),t}function Kn(t,e,n,i,r,o,a,s,l){if(0===a)return!1;var c=a;s-=t,l-=e;var u=Math.sqrt(s*s+l*l);if(u-c>n||u+c<n)return!1;if(Math.abs(i-r)%Yh<1e-4)return!0;if(o){var h=i;i=Zn(r),r=Zn(h)}else i=Zn(i),r=Zn(r);i>r&&(r+=Yh);var f=Math.atan2(l,s);return f<0&&(f+=Yh),f>=i&&f<=r||f+Yh>=i&&f+Yh<=r}function Jn(t,e,n,i,r,o){if(o>e&&o>i||o<e&&o<i)return 0;if(i===e)return 0;var a=i<e?1:-1,s=(o-e)/(i-e);1!==s&&0!==s||(a=i<e?.5:-.5);var l=s*(n-t)+t;return l===r?1/0:l>r?a:0}function Qn(t,e){return Math.abs(t-e)<Jh}function ti(){var t=tf[0];tf[0]=tf[1],tf[1]=t}function ei(t,e,n,i,r,o,a,s,l,c){if(c>e&&c>i&&c>o&&c>s||c<e&&c<i&&c<o&&c<s)return 0;var u=On(e,i,o,s,c,Qh);if(0===u)return 0;for(var h,f,d=0,p=-1,g=0;g<u;g++){var v=Qh[g],m=0===v||1===v?.5:1;Dn(t,n,r,a,v)<l||(p<0&&(p=En(e,i,o,s,tf),tf[1]<tf[0]&&p>1&&ti(),h=Dn(e,i,o,s,tf[0]),p>1&&(f=Dn(e,i,o,s,tf[1]))),2==p?v<tf[0]?d+=h<e?m:-m:v<tf[1]?d+=f<h?m:-m:d+=s<f?m:-m:v<tf[0]?d+=h<e?m:-m:d+=s<h?m:-m)}return d}function ni(t,e,n,i,r,o,a,s){if(s>e&&s>i&&s>o||s<e&&s<i&&s<o)return 0;var l=Bn(e,i,o,s,Qh);if(0===l)return 0;var c=$n(e,i,o);if(c>=0&&c<=1){for(var u=0,h=zn(e,i,o,c),f=0;f<l;f++)d=0===Qh[f]||1===Qh[f]?.5:1,(p=zn(t,n,r,Qh[f]))<a||(Qh[f]<c?u+=h<e?d:-d:u+=o<h?d:-d);return u}var d=0===Qh[0]||1===Qh[0]?.5:1,p=zn(t,n,r,Qh[0]);return p<a?0:o<e?d:-d}function ii(t,e,n,i,r,o,a,s){if((s-=e)>n||s<-n)return 0;c=Math.sqrt(n*n-s*s),Qh[0]=-c,Qh[1]=c;var l=Math.abs(i-r);if(l<1e-4)return 0;if(l%Kh<1e-4)return i=0,r=Kh,p=o?1:-1,a>=Qh[0]+t&&a<=Qh[1]+t?p:0;if(o){var c=i;i=Zn(r),r=Zn(c)}else i=Zn(i),r=Zn(r);i>r&&(r+=Kh);for(var u=0,h=0;h<2;h++){var f=Qh[h];if(f+t>a){var d=Math.atan2(s,f),p=o?1:-1;d<0&&(d=Kh+d),(d>=i&&d<=r||d+Kh>=i&&d+Kh<=r)&&(d>Math.PI/2&&d<1.5*Math.PI&&(p=-p),u+=p)}}return u}function ri(t,e,n,i,r){for(var o=0,a=0,s=0,l=0,c=0,u=0;u<t.length;){var h=t[u++];switch(h===Zh.M&&u>1&&(n||(o+=Jn(a,s,l,c,i,r))),1==u&&(l=a=t[u],c=s=t[u+1]),h){case Zh.M:a=l=t[u++],s=c=t[u++];break;case Zh.L:if(n){if(Xn(a,s,t[u],t[u+1],e,i,r))return!0}else o+=Jn(a,s,t[u],t[u+1],i,r)||0;a=t[u++],s=t[u++];break;case Zh.C:if(n){if(qn(a,s,t[u++],t[u++],t[u++],t[u++],t[u],t[u+1],e,i,r))return!0}else o+=ei(a,s,t[u++],t[u++],t[u++],t[u++],t[u],t[u+1],i,r)||0;a=t[u++],s=t[u++];break;case Zh.Q:if(n){if(Yn(a,s,t[u++],t[u++],t[u],t[u+1],e,i,r))return!0}else o+=ni(a,s,t[u++],t[u++],t[u],t[u+1],i,r)||0;a=t[u++],s=t[u++];break;case Zh.A:var f=t[u++],d=t[u++],p=t[u++],g=t[u++],v=t[u++],m=t[u++],y=(t[u++],1-t[u++]),_=Math.cos(v)*p+f,x=Math.sin(v)*g+d;u>1?o+=Jn(a,s,_,x,i,r):(l=_,c=x);var b=(i-f)*g/p+f;if(n){if(Kn(f,d,g,v,v+m,y,e,b,r))return!0}else o+=ii(f,d,g,v,v+m,y,b,r);a=Math.cos(v+m)*p+f,s=Math.sin(v+m)*g+d;break;case Zh.R:l=a=t[u++],c=s=t[u++];_=l+t[u++],x=c+t[u++];if(n){if(Xn(l,c,_,c,e,i,r)||Xn(_,c,_,x,e,i,r)||Xn(_,x,l,x,e,i,r)||Xn(l,x,l,c,e,i,r))return!0}else o+=Jn(_,c,_,x,i,r),o+=Jn(l,x,l,c,i,r);break;case Zh.Z:if(n){if(Xn(a,s,l,c,e,i,r))return!0}else o+=Jn(a,s,l,c,i,r);a=l,s=c}}return n||Qn(s,c)||(o+=Jn(a,s,l,c,i,r)||0),0!==o}function oi(t,e,n){return ri(t,0,!1,e,n)}function ai(t,e,n,i){return ri(t,e,!0,n,i)}function si(t){Fe.call(this,t),this.path=null}function li(t,e,n,i,r,o,a,s,l,c,u){var h=l*(pf/180),f=df(h)*(t-n)/2+ff(h)*(e-i)/2,d=-1*ff(h)*(t-n)/2+df(h)*(e-i)/2,p=f*f/(a*a)+d*d/(s*s);p>1&&(a*=hf(p),s*=hf(p));var g=(r===o?-1:1)*hf((a*a*(s*s)-a*a*(d*d)-s*s*(f*f))/(a*a*(d*d)+s*s*(f*f)))||0,v=g*a*d/s,m=g*-s*f/a,y=(t+n)/2+df(h)*v-ff(h)*m,_=(e+i)/2+ff(h)*v+df(h)*m,x=mf([1,0],[(f-v)/a,(d-m)/s]),b=[(f-v)/a,(d-m)/s],w=[(-1*f-v)/a,(-1*d-m)/s],S=mf(b,w);vf(b,w)<=-1&&(S=pf),vf(b,w)>=1&&(S=0),0===o&&S>0&&(S-=2*pf),1===o&&S<0&&(S+=2*pf),u.addData(c,y,_,a,s,x,S,h,o)}function ci(t){if(!t)return[];var e,n=t.replace(/-/g," -").replace(/  /g," ").replace(/ /g,",").replace(/,,/g,",");for(e=0;e<uf.length;e++)n=n.replace(new RegExp(uf[e],"g"),"|"+uf[e]);var i,r=n.split("|"),o=0,a=0,s=new Xh,l=Xh.CMD;for(e=1;e<r.length;e++){var c,u=r[e],h=u.charAt(0),f=0,d=u.slice(1).replace(/e,-/g,"e-").split(",");d.length>0&&""===d[0]&&d.shift();for(var p=0;p<d.length;p++)d[p]=parseFloat(d[p]);for(;f<d.length&&!isNaN(d[f])&&!isNaN(d[0]);){var g,v,m,y,_,x,b,w=o,S=a;switch(h){case"l":o+=d[f++],a+=d[f++],c=l.L,s.addData(c,o,a);break;case"L":o=d[f++],a=d[f++],c=l.L,s.addData(c,o,a);break;case"m":o+=d[f++],a+=d[f++],c=l.M,s.addData(c,o,a),h="l";break;case"M":o=d[f++],a=d[f++],c=l.M,s.addData(c,o,a),h="L";break;case"h":o+=d[f++],c=l.L,s.addData(c,o,a);break;case"H":o=d[f++],c=l.L,s.addData(c,o,a);break;case"v":a+=d[f++],c=l.L,s.addData(c,o,a);break;case"V":a=d[f++],c=l.L,s.addData(c,o,a);break;case"C":c=l.C,s.addData(c,d[f++],d[f++],d[f++],d[f++],d[f++],d[f++]),o=d[f-2],a=d[f-1];break;case"c":c=l.C,s.addData(c,d[f++]+o,d[f++]+a,d[f++]+o,d[f++]+a,d[f++]+o,d[f++]+a),o+=d[f-2],a+=d[f-1];break;case"S":g=o,v=a;var M=s.len(),A=s.data;i===l.C&&(g+=o-A[M-4],v+=a-A[M-3]),c=l.C,w=d[f++],S=d[f++],o=d[f++],a=d[f++],s.addData(c,g,v,w,S,o,a);break;case"s":g=o,v=a;M=s.len(),A=s.data;i===l.C&&(g+=o-A[M-4],v+=a-A[M-3]),c=l.C,w=o+d[f++],S=a+d[f++],o+=d[f++],a+=d[f++],s.addData(c,g,v,w,S,o,a);break;case"Q":w=d[f++],S=d[f++],o=d[f++],a=d[f++],c=l.Q,s.addData(c,w,S,o,a);break;case"q":w=d[f++]+o,S=d[f++]+a,o+=d[f++],a+=d[f++],c=l.Q,s.addData(c,w,S,o,a);break;case"T":g=o,v=a;M=s.len(),A=s.data;i===l.Q&&(g+=o-A[M-4],v+=a-A[M-3]),o=d[f++],a=d[f++],c=l.Q,s.addData(c,g,v,o,a);break;case"t":g=o,v=a;M=s.len(),A=s.data;i===l.Q&&(g+=o-A[M-4],v+=a-A[M-3]),o+=d[f++],a+=d[f++],c=l.Q,s.addData(c,g,v,o,a);break;case"A":m=d[f++],y=d[f++],_=d[f++],x=d[f++],b=d[f++],li(w=o,S=a,o=d[f++],a=d[f++],x,b,m,y,_,c=l.A,s);break;case"a":m=d[f++],y=d[f++],_=d[f++],x=d[f++],b=d[f++],li(w=o,S=a,o+=d[f++],a+=d[f++],x,b,m,y,_,c=l.A,s)}}"z"!==h&&"Z"!==h||(c=l.Z,s.addData(c)),i=c}return s.toStatic(),s}function ui(t,e){var n=ci(t);return e=e||{},e.buildPath=function(t){if(t.setData)t.setData(n.data),(e=t.getContext())&&t.rebuildPath(e);else{var e=t;n.rebuildPath(e)}},e.applyTransform=function(t){cf(n,t),this.dirty(!0)},e}function hi(t,e){return new si(ui(t,e))}function fi(t,e){return si.extend(ui(t,e))}function di(t,e,n,i,r,o,a){var s=.5*(n-t),l=.5*(i-e);return(2*(e-n)+s+l)*a+(-3*(e-n)-2*s-l)*o+s*r+e}function pi(t,e,n){var i=e.points,r=e.smooth;if(i&&i.length>=2){if(r&&"spline"!==r){var o=Af(i,r,n,e.smoothConstraint);t.moveTo(i[0][0],i[0][1]);for(var a=i.length,s=0;s<(n?a:a-1);s++){var l=o[2*s],c=o[2*s+1],u=i[(s+1)%a];t.bezierCurveTo(l[0],l[1],c[0],c[1],u[0],u[1])}}else{"spline"===r&&(i=Mf(i,n)),t.moveTo(i[0][0],i[0][1]);s=1;for(var h=i.length;s<h;s++)t.lineTo(i[s][0],i[s][1])}n&&t.closePath()}}function gi(t,e,n){var i=t.cpx2,r=t.cpy2;return null===i||null===r?[(n?Ln:Dn)(t.x1,t.cpx1,t.cpx2,t.x2,e),(n?Ln:Dn)(t.y1,t.cpy1,t.cpy2,t.y2,e)]:[(n?Fn:zn)(t.x1,t.cpx1,t.x2,e),(n?Fn:zn)(t.y1,t.cpy1,t.y2,e)]}function vi(t){Fe.call(this,t),this._displayables=[],this._temporaryDisplayables=[],this._cursor=0,this.notClear=!0}function mi(t){return si.extend(t)}function yi(t,e,n,i){var r=hi(t,e),o=r.getBoundingRect();return n&&("center"===i&&(n=xi(n,o)),bi(r,n)),r}function _i(t,e,n){var i=new Be({style:{image:t,x:e.x,y:e.y,width:e.width,height:e.height},onload:function(t){if("center"===n){var r={width:t.width,height:t.height};i.setStyle(xi(e,r))}}});return i}function xi(t,e){var n,i=e.width/e.height,r=t.height*i;return n=r<=t.width?t.height:(r=t.width)/i,{x:t.x+t.width/2-r/2,y:t.y+t.height/2-n/2,width:r,height:n}}function bi(t,e){if(t.applyTransform){var n=t.getBoundingRect().calculateTransform(e);t.applyTransform(n)}}function wi(t){var e=t.shape,n=t.style.lineWidth;return Ff(2*e.x1)===Ff(2*e.x2)&&(e.x1=e.x2=Si(e.x1,n,!0)),Ff(2*e.y1)===Ff(2*e.y2)&&(e.y1=e.y2=Si(e.y1,n,!0)),t}function Si(t,e,n){var i=Ff(2*t);return(i+Ff(e))%2==0?i/2:(i+(n?1:-1))/2}function Mi(t){return null!=t&&"none"!=t}function Ai(t){return"string"==typeof t?St(t,-.1):t}function Ti(t){if(t.__hoverStlDirty){var e=t.style.stroke,n=t.style.fill,i=t.__hoverStl;i.fill=i.fill||(Mi(n)?Ai(n):null),i.stroke=i.stroke||(Mi(e)?Ai(e):null);var r={};for(var o in i)null!=i[o]&&(r[o]=t.style[o]);t.__normalStl=r,t.__hoverStlDirty=!1}}function ki(t){if(!t.__isHover){if(Ti(t),t.useHoverLayer)t.__zr&&t.__zr.addHover(t,t.__hoverStl);else{var e=t.style,n=e.insideRollbackOpt;n&&ji(e),e.extendFrom(t.__hoverStl),n&&(Vi(e,e.insideOriginalTextPosition,n),null==e.textFill&&(e.textFill=n.autoColor)),t.dirty(!1),t.z2+=1}t.__isHover=!0}}function Pi(t){if(t.__isHover){var e=t.__normalStl;t.useHoverLayer?t.__zr&&t.__zr.removeHover(t):(e&&t.setStyle(e),t.z2-=1),t.__isHover=!1}}function Ci(t){"group"===t.type?t.traverse(function(t){"group"!==t.type&&ki(t)}):ki(t)}function Ii(t){"group"===t.type?t.traverse(function(t){"group"!==t.type&&Pi(t)}):Pi(t)}function Di(t,e){t.__hoverStl=t.hoverStyle||e||{},t.__hoverStlDirty=!0,t.__isHover&&Ti(t)}function Li(t){this.__hoverSilentOnTouch&&t.zrByTouch||!this.__isEmphasis&&Ci(this)}function Oi(t){this.__hoverSilentOnTouch&&t.zrByTouch||!this.__isEmphasis&&Ii(this)}function Ei(){this.__isEmphasis=!0,Ci(this)}function Ri(){this.__isEmphasis=!1,Ii(this)}function Ni(t,e,n){t.__hoverSilentOnTouch=n&&n.hoverSilentOnTouch,"group"===t.type?t.traverse(function(t){"group"!==t.type&&Di(t,e)}):Di(t,e),t.on("mouseover",Li).on("mouseout",Oi),t.on("emphasis",Ei).on("normal",Ri)}function zi(t,e,n,i,r,o,a){var s,l=(r=r||Hf).labelFetcher,c=r.labelDataIndex,u=r.labelDimIndex,h=n.getShallow("show"),f=i.getShallow("show");(h||f)&&(l&&(s=l.getFormattedLabel(c,"normal",null,u)),null==s&&(s=b(r.defaultText)?r.defaultText(c,r):r.defaultText));var d=h?s:null,p=f?C(l?l.getFormattedLabel(c,"emphasis",null,u):null,s):null;null==d&&null==p||(Fi(t,n,o,r),Fi(e,i,a,r,!0)),t.text=d,e.text=p}function Fi(t,e,n,i,r){return Bi(t,e,i,r),n&&s(t,n),t.host&&t.host.dirty&&t.host.dirty(!1),t}function Bi(t,e,n,i){if((n=n||Hf).isRectText){var r=e.getShallow("position")||(i?null:"inside");"outside"===r&&(r="top"),t.textPosition=r,t.textOffset=e.getShallow("offset");var o=e.getShallow("rotate");null!=o&&(o*=Math.PI/180),t.textRotation=o,t.textDistance=C(e.getShallow("distance"),i?null:5)}var a,s=e.ecModel,l=s&&s.option.textStyle,c=$i(e);if(c)for(var u in a={},c)if(c.hasOwnProperty(u)){var h=e.getModel(["rich",u]);Hi(a[u]={},h,l,n,i)}return t.rich=a,Hi(t,e,l,n,i,!0),n.forceRich&&!n.textStyle&&(n.textStyle={}),t}function $i(t){for(var e;t&&t!==t.ecModel;){var n=(t.option||Hf).rich;if(n)for(var i in e=e||{},n)n.hasOwnProperty(i)&&(e[i]=1);t=t.parentModel}return e}function Hi(t,e,n,i,r,o){if(n=!r&&n||Hf,t.textFill=Wi(e.getShallow("color"),i)||n.color,t.textStroke=Wi(e.getShallow("textBorderColor"),i)||n.textBorderColor,t.textStrokeWidth=C(e.getShallow("textBorderWidth"),n.textBorderWidth),!r){if(o){var a=t.textPosition;t.insideRollback=Vi(t,a,i),t.insideOriginalTextPosition=a,t.insideRollbackOpt=i}null==t.textFill&&(t.textFill=i.autoColor)}t.fontStyle=e.getShallow("fontStyle")||n.fontStyle,t.fontWeight=e.getShallow("fontWeight")||n.fontWeight,t.fontSize=e.getShallow("fontSize")||n.fontSize,t.fontFamily=e.getShallow("fontFamily")||n.fontFamily,t.textAlign=e.getShallow("align"),t.textVerticalAlign=e.getShallow("verticalAlign")||e.getShallow("baseline"),t.textLineHeight=e.getShallow("lineHeight"),t.textWidth=e.getShallow("width"),t.textHeight=e.getShallow("height"),t.textTag=e.getShallow("tag"),o&&i.disableBox||(t.textBackgroundColor=Wi(e.getShallow("backgroundColor"),i),t.textPadding=e.getShallow("padding"),t.textBorderColor=Wi(e.getShallow("borderColor"),i),t.textBorderWidth=e.getShallow("borderWidth"),t.textBorderRadius=e.getShallow("borderRadius"),t.textBoxShadowColor=e.getShallow("shadowColor"),t.textBoxShadowBlur=e.getShallow("shadowBlur"),t.textBoxShadowOffsetX=e.getShallow("shadowOffsetX"),t.textBoxShadowOffsetY=e.getShallow("shadowOffsetY")),t.textShadowColor=e.getShallow("textShadowColor")||n.textShadowColor,t.textShadowBlur=e.getShallow("textShadowBlur")||n.textShadowBlur,t.textShadowOffsetX=e.getShallow("textShadowOffsetX")||n.textShadowOffsetX,t.textShadowOffsetY=e.getShallow("textShadowOffsetY")||n.textShadowOffsetY}function Wi(t,e){return"auto"!==t?t:e&&e.autoColor?e.autoColor:null}function Vi(t,e,n){var i,r=n.useInsideStyle;return null==t.textFill&&!1!==r&&(!0===r||n.isRectText&&e&&"string"==typeof e&&e.indexOf("inside")>=0)&&(i={textFill:null,textStroke:t.textStroke,textStrokeWidth:t.textStrokeWidth},t.textFill="#fff",null==t.textStroke&&(t.textStroke=n.autoColor,null==t.textStrokeWidth&&(t.textStrokeWidth=2))),i}function ji(t){var e=t.insideRollback;e&&(t.textFill=e.textFill,t.textStroke=e.textStroke,t.textStrokeWidth=e.textStrokeWidth)}function Gi(t,e){var n=e||e.getModel("textStyle");return E([t.fontStyle||n&&n.getShallow("fontStyle")||"",t.fontWeight||n&&n.getShallow("fontWeight")||"",(t.fontSize||n&&n.getShallow("fontSize")||12)+"px",t.fontFamily||n&&n.getShallow("fontFamily")||"sans-serif"].join(" "))}function Ui(t,e,n,i,r,o){if("function"==typeof r&&(o=r,r=null),i&&i.isAnimationEnabled()){var a=t?"Update":"",s=i.getShallow("animationDuration"+a),l=i.getShallow("animationEasing"+a),c=i.getShallow("animationDelay"+a);"function"==typeof c&&(c=c(r,i.getAnimationDelayParams?i.getAnimationDelayParams(e,r):null)),"function"==typeof s&&(s=s(r)),s>0?e.animateTo(n,s,c||0,l,o,!!o):(e.stopAnimation(),e.attr(n),o&&o())}else e.stopAnimation(),e.attr(n),o&&o()}function Xi(t,e,n,i,r){Ui(!0,t,e,n,i,r)}function qi(t,e,n,i,r){Ui(!1,t,e,n,i,r)}function Yi(t,e,n){return e&&!d(e)&&(e=Gc.getLocalTransform(e)),n&&(e=ut([],e)),Y([],t,e)}function Zi(t,e,n,i){function r(t){var e={position:H(t.position),rotation:t.rotation};return t.shape&&(e.shape=s({},t.shape)),e}if(t&&e){var o=function(t){var e={};return t.traverse(function(t){!t.isGroup&&t.anid&&(e[t.anid]=t)}),e}(t);e.traverse(function(t){if(!t.isGroup&&t.anid){var e=o[t.anid];if(e){var i=r(t);t.attr(r(e)),Xi(t,i,n,t.dataIndex)}}})}}function Ki(t,e,n){this.parentModel=e,this.ecModel=n,this.option=t}function Ji(t,e,n){for(var i=0;i<e.length&&(!e[i]||null!=(t=t&&"object"==typeof t?t[e[i]]:null));i++);return null==t&&n&&(t=n.get(e)),t}function Qi(t,e){var n=qf(t).getParent;return n?n.call(t,e):t.parentModel}function tr(t){return[t||"",Yf++,Math.random().toFixed(5)].join("_")}function er(t){return t.replace(/^\s+/,"").replace(/\s+$/,"")}function nr(t,e,n,i){var r=e[1]-e[0],o=n[1]-n[0];if(0===r)return 0===o?n[0]:(n[0]+n[1])/2;if(i)if(r>0){if(t<=e[0])return n[0];if(t>=e[1])return n[1]}else{if(t>=e[0])return n[0];if(t<=e[1])return n[1]}else{if(t===e[0])return n[0];if(t===e[1])return n[1]}return(t-e[0])/r*o+n[0]}function ir(t,e){switch(t){case"center":case"middle":t="50%";break;case"left":case"top":t="0%";break;case"right":case"bottom":t="100%"}return"string"==typeof t?er(t).match(/%$/)?parseFloat(t)/100*e:parseFloat(t):null==t?NaN:+t}function rr(t,e,n){return null==e&&(e=10),e=Math.min(Math.max(0,e),20),t=(+t).toFixed(e),n?t:+t}function or(t){var e=t.toString(),n=e.indexOf("e");if(n>0){var i=+e.slice(n+1);return i<0?-i:0}var r=e.indexOf(".");return r<0?0:e.length-1-r}function ar(t,e){var n=Math.log,i=Math.LN10,r=Math.floor(n(t[1]-t[0])/i),o=Math.round(n(Math.abs(e[1]-e[0]))/i),a=Math.min(Math.max(-r+o,0),20);return isFinite(a)?a:20}function sr(t,e,n){if(!t[e])return 0;var i=v(t,function(t,e){return t+(isNaN(e)?0:e)},0);if(0===i)return 0;for(var r=Math.pow(10,n),o=g(t,function(t){return(isNaN(t)?0:t)/i*r*100}),a=100*r,s=g(o,function(t){return Math.floor(t)}),l=v(s,function(t,e){return t+e},0),c=g(o,function(t,e){return t-s[e]});l<a;){for(var u=Number.NEGATIVE_INFINITY,h=null,f=0,d=c.length;f<d;++f)c[f]>u&&(u=c[f],h=f);++s[h],c[h]=0,++l}return s[e]/r}function lr(t){var e=2*Math.PI;return(t%e+e)%e}function cr(t){return t>-Zf&&t<Zf}function ur(t){if(t instanceof Date)return t;if("string"==typeof t){var e=Kf.exec(t);if(!e)return new Date(NaN);if(e[8]){var n=+e[4]||0;return"Z"!==e[8].toUpperCase()&&(n-=e[8].slice(0,3)),new Date(Date.UTC(+e[1],+(e[2]||1)-1,+e[3]||1,n,+(e[5]||0),+e[6]||0,+e[7]||0))}return new Date(+e[1],+(e[2]||1)-1,+e[3]||1,+e[4]||0,+(e[5]||0),+e[6]||0,+e[7]||0)}return null==t?new Date(NaN):new Date(Math.round(t))}function hr(t){return Math.pow(10,fr(t))}function fr(t){return Math.floor(Math.log(t)/Math.LN10)}function dr(t,e){var n,i=fr(t),r=Math.pow(10,i),o=t/r;return n=e?o<1.5?1:o<2.5?2:o<4?3:o<7?5:10:o<1?1:o<2?2:o<3?3:o<5?5:10,t=n*r,i>=-20?+t.toFixed(i<0?-i:0):t}function pr(t){return isNaN(t)?"-":(t=(t+"").split("."))[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g,"$1,")+(t.length>1?"."+t[1]:"")}function gr(t){return null==t?"":(t+"").replace(Qf,function(t,e){return td[e]})}function vr(t,e,n){x(e)||(e=[e]);var i=e.length;if(!i)return"";for(var r=e[0].$vars||[],o=0;o<r.length;o++){var a=ed[o];t=t.replace(nd(a),nd(a,0))}for(var s=0;s<i;s++)for(var l=0;l<r.length;l++){var c=e[s][r[l]];t=t.replace(nd(ed[l],s),n?gr(c):c)}return t}function mr(t,e){var n=(t=w(t)?{color:t,extraCssText:e}:t||{}).color,i=t.type;e=t.extraCssText;return n?"subItem"===i?'<span style="display:inline-block;vertical-align:middle;margin-right:8upx;margin-left:3upx;border-radius:4upx;width:4upx;height:4upx;background-color:'+gr(n)+";"+(e||"")+'"></span>':'<span style="display:inline-block;margin-right:5upx;border-radius:10upx;width:10upx;height:10upx;background-color:'+gr(n)+";"+(e||"")+'"></span>':""}function yr(t,e){return t+="","0000".substr(0,e-t.length)+t}function _r(t,e,n){"week"!==t&&"month"!==t&&"quarter"!==t&&"half-year"!==t&&"year"!==t||(t="MM-dd\nyyyy");var i=ur(e),r=n?"UTC":"",o=i["get"+r+"FullYear"](),a=i["get"+r+"Month"]()+1,s=i["get"+r+"Date"](),l=i["get"+r+"Hours"](),c=i["get"+r+"Minutes"](),u=i["get"+r+"Seconds"](),h=i["get"+r+"Milliseconds"]();return t.replace("MM",yr(a,2)).replace("M",a).replace("yyyy",o).replace("yy",o%100).replace("dd",yr(s,2)).replace("d",s).replace("hh",yr(l,2)).replace("h",l).replace("mm",yr(c,2)).replace("m",c).replace("ss",yr(u,2)).replace("s",u).replace("SSS",yr(h,3))}function xr(t,e,n,i,r){var o=0,a=0;null==i&&(i=1/0),null==r&&(r=1/0);var s=0;e.eachChild(function(l,c){var u,h,f=l.position,d=l.getBoundingRect(),p=e.childAt(c+1),g=p&&p.getBoundingRect();if("horizontal"===t){var v=d.width+(g?-g.x+d.x:0);(u=o+v)>i||l.newline?(o=0,u=v,a+=s+n,s=d.height):s=Math.max(s,d.height)}else{var m=d.height+(g?-g.y+d.y:0);(h=a+m)>r||l.newline?(o+=s+n,a=0,h=m,s=d.width):s=Math.max(s,d.width)}l.newline||(f[0]=o,f[1]=a,"horizontal"===t?o=u+n:a=h+n)})}function br(t,e,n){n=Jf(n||0);var i=e.width,r=e.height,o=ir(t.left,i),a=ir(t.top,r),s=ir(t.right,i),l=ir(t.bottom,r),c=ir(t.width,i),u=ir(t.height,r),h=n[2]+n[0],f=n[1]+n[3],d=t.aspect;switch(isNaN(c)&&(c=i-s-f-o),isNaN(u)&&(u=r-l-h-a),null!=d&&(isNaN(c)&&isNaN(u)&&(d>i/r?c=.8*i:u=.8*r),isNaN(c)&&(c=d*u),isNaN(u)&&(u=c/d)),isNaN(o)&&(o=i-s-c-f),isNaN(a)&&(a=r-l-u-h),t.left||t.right){case"center":o=i/2-c/2-n[3];break;case"right":o=i-c-f}switch(t.top||t.bottom){case"middle":case"center":a=r/2-u/2-n[0];break;case"bottom":a=r-u-h}o=o||0,a=a||0,isNaN(c)&&(c=i-f-o-(s||0)),isNaN(u)&&(u=r-h-a-(l||0));var p=new Ft(o+n[3],a+n[0],c,u);return p.margin=n,p}function wr(t,e,n){function i(n,i){var a={},l=0,c={},u=0;if(rd(n,function(e){c[e]=t[e]}),rd(n,function(t){r(e,t)&&(a[t]=c[t]=e[t]),o(a,t)&&l++,o(c,t)&&u++}),s[i])return o(e,n[1])?c[n[2]]=null:o(e,n[2])&&(c[n[1]]=null),c;if(2!==u&&l){if(l>=2)return a;for(var h=0;h<n.length;h++){var f=n[h];if(!r(a,f)&&r(t,f)){a[f]=t[f];break}}return a}return c}function r(t,e){return t.hasOwnProperty(e)}function o(t,e){return null!=t[e]&&"auto"!==t[e]}function a(t,e,n){rd(t,function(t){e[t]=n[t]})}!S(n)&&(n={});var s=n.ignoreSize;!x(s)&&(s=[s,s]);var l=i(ad[0],0),c=i(ad[1],1);a(ad[0],t,l),a(ad[1],t,c)}function Sr(t){return Mr({},t)}function Mr(t,e){return e&&t&&rd(od,function(n){e.hasOwnProperty(n)&&(t[n]=e[n])}),t}function Ar(t,e){for(var n=t.length,i=0;i<n;i++)if(t[i].length>e)return t[i];return t[n-1]}function Tr(t){var e=t.get("coordinateSystem"),n={coordSysName:e,coordSysDims:[],axisMap:F(),categoryAxisMap:F()},i=pd[e];if(i)return i(t,n,n.axisMap,n.categoryAxisMap),n}function kr(t){return"category"===t.get("type")}function Pr(t){this.fromDataset=t.fromDataset,this.data=t.data||(t.sourceFormat===yd?{}:[]),this.sourceFormat=t.sourceFormat||_d,this.seriesLayoutBy=t.seriesLayoutBy||bd,this.dimensionsDefine=t.dimensionsDefine,this.encodeDefine=t.encodeDefine&&F(t.encodeDefine),this.startIndex=t.startIndex||0,this.dimensionsDetectCount=t.dimensionsDetectCount}function Cr(t){var e=t.option.source,n=_d;if(A(e))n=xd;else if(x(e))for(var i=0,r=e.length;i<r;i++){var o=e[i];if(null!=o){if(x(o)){n=vd;break}if(S(o)){n=md;break}}}else if(S(e)){for(var a in e)if(e.hasOwnProperty(a)&&d(e[a])){n=yd;break}}else if(null!=e)throw new Error("Invalid data");Sd(t).sourceFormat=n}function Ir(t){return Sd(t).source}function Dr(t){Sd(t).datasetMap=F()}function Lr(t){var e=t.option,n=e.data,i=A(n)?xd:gd,r=!1,o=e.seriesLayoutBy,a=e.sourceHeader,s=e.dimensions,l=Fr(t);if(l){var c=l.option;n=c.source,i=Sd(l).sourceFormat,r=!0,o=o||c.seriesLayoutBy,null==a&&(a=c.sourceHeader),s=s||c.dimensions}var u=Or(n,i,o,a,s),h=e.encode;!h&&l&&(h=zr(t,l,n,i,o,u)),Sd(t).source=new Pr({data:n,fromDataset:r,seriesLayoutBy:o,sourceFormat:i,dimensionsDefine:u.dimensionsDefine,startIndex:u.startIndex,dimensionsDetectCount:u.dimensionsDetectCount,encodeDefine:h})}function Or(t,e,n,i,r){if(!t)return{dimensionsDefine:Er(r)};var o,a,s,l;if(e===vd)"auto"===i||null==i?Rr(function(t){null!=t&&"-"!==t&&(w(t)?null==a&&(a=1):a=0)},n,t,10):a=i?1:0,r||1!==a||(r=[],Rr(function(t,e){r[e]=null!=t?t:""},n,t)),o=r?r.length:n===wd?t.length:t[0]?t[0].length:null;else if(e===md)r||(r=Nr(t),s=!0);else if(e===yd)r||(r=[],s=!0,p(t,function(t,e){r.push(e)}));else if(e===gd){var c=un(t[0]);o=x(c)&&c.length||1}return s&&p(r,function(t,e){"name"===(S(t)?t.name:t)&&(l=e)}),{startIndex:a,dimensionsDefine:Er(r),dimensionsDetectCount:o,potentialNameDimIndex:l}}function Er(t){if(t){var e=F();return g(t,function(t,n){if(null==(t=s({},S(t)?t:{name:t})).name)return t;t.name+="",null==t.displayName&&(t.displayName=t.name);var i=e.get(t.name);return i?t.name+="-"+i.count++:e.set(t.name,{count:1}),t})}}function Rr(t,e,n,i){if(null==i&&(i=1/0),e===wd)for(o=0;o<n.length&&o<i;o++)t(n[o]?n[o][0]:null,o);else for(var r=n[0]||[],o=0;o<r.length&&o<i;o++)t(r[o],o)}function Nr(t){for(var e,n=0;n<t.length&&!(e=t[n++]););if(e){var i=[];return p(e,function(t,e){i.push(e)}),i}}function zr(t,e,n,i,r,o){var a=Tr(t),s={},l=[],c=[],u=t.subType,h=F(["pie","map","funnel"]),f=F(["line","bar","pictorialBar","scatter","effectScatter","candlestick","boxplot"]);if(a&&null!=f.get(u)){var d=t.ecModel,g=Sd(d).datasetMap,v=e.uid+"_"+r,m=g.get(v)||g.set(v,{categoryWayDim:1,valueWayDim:0});p(a.coordSysDims,function(t){if(null==a.firstCategoryDimIndex)e=m.valueWayDim++,s[t]=e,c.push(e);else if(a.categoryAxisMap.get(t))s[t]=0,l.push(0);else{var e=m.categoryWayDim++;s[t]=e,c.push(e)}})}else if(null!=h.get(u)){for(var y,_=0;_<5&&null==y;_++)$r(n,i,r,o.dimensionsDefine,o.startIndex,_)||(y=_);if(null!=y){s.value=y;var x=o.potentialNameDimIndex||Math.max(y-1,0);c.push(x),l.push(x)}}return l.length&&(s.itemName=l),c.length&&(s.seriesName=c),s}function Fr(t){var e=t.option;if(!e.data)return t.ecModel.getComponent("dataset",e.datasetIndex||0)}function Br(t,e){return $r(t.data,t.sourceFormat,t.seriesLayoutBy,t.dimensionsDefine,t.startIndex,e)}function $r(t,e,n,i,r,o){function a(t){return(null==t||!isFinite(t)||""===t)&&(!(!w(t)||"-"===t)||void 0)}var s,l;if(A(t))return!1;if(i&&(l=S(l=i[o])?l.name:l),e===vd)if(n===wd){for(var c=t[o],u=0;u<(c||[]).length&&u<5;u++)if(null!=(s=a(c[r+u])))return s}else for(u=0;u<t.length&&u<5;u++){var h=t[r+u];if(h&&null!=(s=a(h[o])))return s}else if(e===md){if(!l)return;for(u=0;u<t.length&&u<5;u++)if((f=t[u])&&null!=(s=a(f[l])))return s}else if(e===yd){if(!l)return;if(!(c=t[l])||A(c))return!1;for(u=0;u<c.length&&u<5;u++)if(null!=(s=a(c[u])))return s}else if(e===gd)for(u=0;u<t.length&&u<5;u++){var f=t[u],d=un(f);if(!x(d))return!1;if(null!=(s=a(d[o])))return s}return!1}function Hr(t,e){if(e){var n=e.seiresIndex,i=e.seriesId,r=e.seriesName;return null!=n&&t.componentIndex!==n||null!=i&&t.id!==i||null!=r&&t.name!==r}}function Wr(t,e){var n=t.color&&!t.colorLayer;p(e,function(e,i){"colorLayer"===i&&n||cd.hasClass(i)||("object"==typeof e?t[i]=t[i]?o(t[i],e,!1):r(e):null==t[i]&&(t[i]=e))})}function Vr(t){t=t,this.option={},this.option[Md]=1,this._componentsMap=F({series:[]}),this._seriesIndices,this._seriesIndicesMap,Wr(t,this._theme.option),o(t,hd,!1),this.mergeOption(t)}function jr(t,e){x(e)||(e=e?[e]:[]);var n={};return p(e,function(e){n[e]=(t.get(e)||[]).slice()}),n}function Gr(t,e,n){return e.type?e.type:n?n.subType:cd.determineSubType(t,e)}function Ur(t,e){t._seriesIndicesMap=F(t._seriesIndices=g(e,function(t){return t.componentIndex})||[])}function Xr(t,e){return e.hasOwnProperty("subType")?m(t,function(t){return t.subType===e.subType}):t}function qr(t){p(Td,function(e){this[e]=y(t[e],t)},this)}function Yr(){this._coordinateSystems=[]}function Zr(t){this._api=t,this._timelineOptions=[],this._mediaList=[],this._mediaDefault,this._currentMediaIndices=[],this._optionBackup,this._newBaseOption}function Kr(t,e,n){var i,r,o=[],a=[],s=t.timeline;if(t.baseOption&&(r=t.baseOption),(s||t.options)&&(r=r||{},o=(t.options||[]).slice()),t.media){r=r||{};var l=t.media;Pd(l,function(t){t&&t.option&&(t.query?a.push(t):i||(i=t))})}return r||(r=t),r.timeline||(r.timeline=s),Pd([r].concat(o).concat(g(a,function(t){return t.option})),function(t){Pd(e,function(e){e(t,n)})}),{baseOption:r,timelineOptions:o,mediaDefault:i,mediaList:a}}function Jr(t,e,n){var i={width:e,height:n,aspectratio:e/n},r=!0;return p(t,function(t,e){var n=e.match(Ld);if(n&&n[1]&&n[2]){var o=n[1],a=n[2].toLowerCase();Qr(i[a],t,o)||(r=!1)}}),r}function Qr(t,e,n){return"min"===n?t>=e:"max"===n?t<=e:t===e}function to(t,e){return t.join(",")===e.join(",")}function eo(t,e){Pd(e=e||{},function(e,n){if(null!=e){var i=t[n];if(cd.hasClass(n)){e=ln(e);var r=fn(i=ln(i),e);t[n]=Id(r,function(t){return t.option&&t.exist?Dd(t.exist,t.option,!0):t.exist||t.option})}else t[n]=Dd(i,e,!0)}})}function no(t){var e=t&&t.itemStyle;if(e)for(var n=0,i=Rd.length;n<i;n++){var r=Rd[n],a=e.normal,s=e.emphasis;a&&a[r]&&(t[r]=t[r]||{},t[r].normal?o(t[r].normal,a[r]):t[r].normal=a[r],a[r]=null),s&&s[r]&&(t[r]=t[r]||{},t[r].emphasis?o(t[r].emphasis,s[r]):t[r].emphasis=s[r],s[r]=null)}}function io(t,e,n){if(t&&t[e]&&(t[e].normal||t[e].emphasis)){var i=t[e].normal,r=t[e].emphasis;i&&(n?(t[e].normal=t[e].emphasis=null,l(t[e],i)):t[e]=i),r&&(t.emphasis=t.emphasis||{},t.emphasis[e]=r)}}function ro(t){io(t,"itemStyle"),io(t,"lineStyle"),io(t,"areaStyle"),io(t,"label"),io(t,"labelLine"),io(t,"upperLabel"),io(t,"edgeLabel")}function oo(t,e){var n=Ed(t)&&t[e],i=Ed(n)&&n.textStyle;if(i)for(var r=0,o=ah.length;r<o;r++){e=ah[r];i.hasOwnProperty(e)&&(n[e]=i[e])}}function ao(t){t&&(ro(t),oo(t,"label"),t.emphasis&&oo(t.emphasis,"label"))}function so(t){if(Ed(t)){no(t),ro(t),oo(t,"label"),oo(t,"upperLabel"),oo(t,"edgeLabel"),t.emphasis&&(oo(t.emphasis,"label"),oo(t.emphasis,"upperLabel"),oo(t.emphasis,"edgeLabel"));var e=t.markPoint;e&&(no(e),ao(e));var n=t.markLine;n&&(no(n),ao(n));var i=t.markArea;i&&ao(i);var r=t.data;if("graph"===t.type){r=r||t.nodes;var o=t.links||t.edges;if(o&&!A(o))for(s=0;s<o.length;s++)ao(o[s]);p(t.categories,function(t){ro(t)})}if(r&&!A(r))for(s=0;s<r.length;s++)ao(r[s]);if((e=t.markPoint)&&e.data)for(var a=e.data,s=0;s<a.length;s++)ao(a[s]);if((n=t.markLine)&&n.data){var l=n.data;for(s=0;s<l.length;s++)x(l[s])?(ao(l[s][0]),ao(l[s][1])):ao(l[s])}"gauge"===t.type?(oo(t,"axisLabel"),oo(t,"title"),oo(t,"detail")):"treemap"===t.type?(io(t.breadcrumb,"itemStyle"),p(t.levels,function(t){ro(t)})):"tree"===t.type&&ro(t.leaves)}}function lo(t){return x(t)?t:t?[t]:[]}function co(t){return(x(t)?t[0]:t)||{}}function uo(t,e){e=e.split(",");for(var n=t,i=0;i<e.length&&null!=(n=n&&n[e[i]]);i++);return n}function ho(t,e,n,i){e=e.split(",");for(var r,o=t,a=0;a<e.length-1;a++)null==o[r=e[a]]&&(o[r]={}),o=o[r];(i||null==o[e[a]])&&(o[e[a]]=n)}function fo(t){p(zd,function(e){e[0]in t&&!(e[1]in t)&&(t[e[1]]=t[e[0]])})}function po(t){p(t,function(e,n){var i=[],r=[NaN,NaN],o=[e.stackResultDimension,e.stackedOverDimension],a=e.data,s=e.isStackedByIndex,l=a.map(o,function(o,l,c){var u,h,f=a.get(e.stackedDimension,c);if(isNaN(f))return r;s?h=a.getRawIndex(c):u=a.get(e.stackedByDimension,c);for(var d=NaN,p=n-1;p>=0;p--){var g=t[p];if(s||(h=g.data.rawIndexOf(g.stackedByDimension,u)),h>=0){var v=g.data.getByRawIndex(g.stackResultDimension,h);if(f>=0&&v>0||f<=0&&v<0){f+=v,d=v;break}}}return i[0]=f,i[1]=d,i});a.hostModel.setData(l),e.data=l})}function go(t,e){Pr.isInstance(t)||(t=Pr.seriesDataToSource(t)),this._source=t;var n=this._data=t.data,i=t.sourceFormat;i===xd&&(this._offset=0,this._dimSize=e,this._data=n),s(this,Hd[i===vd?i+"_"+t.seriesLayoutBy:i])}function vo(){return this._data.length}function mo(t){return this._data[t]}function yo(t){for(var e=0;e<t.length;e++)this._data.push(t[e])}function _o(t,e,n,i){return null!=n?t[n]:t}function xo(t,e,n,i){return bo(t[i],this._dimensionInfos[e])}function bo(t,e){var n=e&&e.type;if("ordinal"===n){var i=e&&e.ordinalMeta;return i?i.parseAndCollect(t):t}return"time"===n&&"number"!=typeof t&&null!=t&&"-"!==t&&(t=+ur(t)),null==t||""===t?NaN:+t}function wo(t,e,n){if(t){var i=t.getRawDataItem(e);if(null!=i){var r,o,a=t.getProvider().getSource().sourceFormat,s=t.getDimensionInfo(n);return s&&(r=s.name,o=s.index),Wd[a](i,e,o,r)}}}function So(t,e,n){if(t){var i=t.getProvider().getSource().sourceFormat;if(i===gd||i===md){var r=t.getRawDataItem(e);return i!==gd||S(r)||(r=null),r?r[n]:void 0}}}function Mo(t){return new Ao(t)}function Ao(t){t=t||{},this._reset=t.reset,this._plan=t.plan,this._count=t.count,this._onDirty=t.onDirty,this._dirty=!0,this.context}function To(t,e,n,i,r,o){Xd.reset(n,i,r,o),t._callingProgress=e,t._callingProgress({start:n,end:i,count:i-n,next:Xd.next},t.context)}function ko(t,e){var n,i;t._dueIndex=t._outputDueEnd=t._dueEnd=0,t._settedOutputEnd=null,!e&&t._reset&&((n=t._reset(t.context))&&n.progress&&(i=n.forceFirstProgress,n=n.progress),x(n)&&!n.length&&(n=null)),t._progress=n,t._modBy=t._modDataCount=null;var r=t._downstream;return r&&r.dirty(),i}function Po(t){var e=t.name;pn(t)||(t.name=Co(t)||e)}function Co(t){var e=t.getRawData(),n=[];return p(e.mapDimension("seriesName",!0),function(t){var i=e.getDimensionInfo(t);i.displayName&&n.push(i.displayName)}),n.join(" ")}function Io(t){return t.model.getRawData().count()}function Do(t){var e=t.model;return e.setData(e.getRawData().cloneShallow()),Lo}function Lo(t,e){t.end>e.outputData.count()&&e.model.getRawData().cloneShallow(e.outputData)}function Oo(t,e){p(t.CHANGABLE_METHODS,function(n){t.wrapMethod(n,_(Eo,e))})}function Eo(t){var e=Ro(t);e&&e.setOutputEnd(this.count())}function Ro(t){var e=(t.ecModel||{}).scheduler,n=e&&e.getPipeline(t.uid);if(n){var i=n.currentTask;if(i){var r=i.agentStubMap;r&&(i=r.get(t.uid))}return i}}function No(){this.group=new pu,this.uid=tr("viewChart"),this.renderTask=Mo({plan:Bo,reset:$o}),this.renderTask.context={view:this}}function zo(t,e){if(t&&(t.trigger(e),"group"===t.type))for(var n=0;n<t.childCount();n++)zo(t.childAt(n),e)}function Fo(t,e,n){var i=vn(t,e);null!=i?p(ln(i),function(e){zo(t.getItemGraphicEl(e),n)}):t.eachItemGraphicEl(function(t){zo(t,n)})}function Bo(t){return tp(t.model)}function $o(t){var e=t.model,n=t.ecModel,i=t.api,r=t.payload,o=e.pipelineContext.progressiveRender,a=t.view,s=r&&Qd(r).updateMethod,l=o?"incrementalPrepareRender":s&&a[s]?s:"render";return"render"!==l&&a[l](e,n,i,r),np[l]}function Ho(t,e,n){function i(){u=(new Date).getTime(),h=null,t.apply(a,s||[])}var r,o,a,s,l,c=0,u=0,h=null;e=e||0;var f=function(){r=(new Date).getTime(),a=this,s=arguments;var t=l||e,f=l||n;l=null,o=r-(f?c:u)-t,clearTimeout(h),f?h=setTimeout(i,t):o>=0?i():h=setTimeout(i,-o),c=r};return f.clear=function(){h&&(clearTimeout(h),h=null)},f.debounceNextCall=function(t){l=t},f}function Wo(t,e,n,i){this.ecInstance=t,this.api=e,this.unfinished;n=this._dataProcessorHandlers=n.slice(),i=this._visualHandlers=i.slice();this._allHandlers=n.concat(i),this._stageTaskMap=F()}function Vo(t,e,n,i,r){function o(t,e){return t.setDirty&&(!t.dirtyMap||t.dirtyMap.get(e.__pipeline.id))}var a;r=r||{},p(e,function(e,s){if(!r.visualType||r.visualType===e.visualType){var l=t._stageTaskMap.get(e.uid),c=l.seriesTaskMap,u=l.overallTask;if(u){var h,f=u.agentStubMap;f.each(function(t){o(r,t)&&(t.dirty(),h=!0)}),h&&u.dirty(),lp(u,i);var d=t.getPerformArgs(u,r.block);f.each(function(t){t.perform(d)}),a|=u.perform(d)}else c&&c.each(function(s,l){o(r,s)&&s.dirty();var c=t.getPerformArgs(s,r.block);c.skip=!e.performRawSeries&&n.isSeriesFiltered(s.context.model),lp(s,i),a|=s.perform(c)})}}),t.unfinished|=a}function jo(t,e,n,i,r){function o(n){var o=n.uid,s=a.get(o)||a.set(o,Mo({plan:Zo,reset:Ko,count:Qo}));s.context={model:n,ecModel:i,api:r,useClearVisual:e.isVisual&&!e.isLayout,plan:e.plan,reset:e.reset,scheduler:t},ta(t,n,s)}var a=n.seriesTaskMap||(n.seriesTaskMap=F()),s=e.seriesType,l=e.getTargetSeries;e.createOnAllSeries?i.eachRawSeries(o):s?i.eachRawSeriesByType(s,o):l&&l(i,r).each(o);var c=t._pipelineMap;a.each(function(t,e){c.get(e)||(t.dispose(),a.removeKey(e))})}function Go(t,e,n,i,r){function o(e){var n=e.uid,i=s.get(n);i||(i=s.set(n,Mo({reset:Xo,onDirty:Yo})),a.dirty()),i.context={model:e,overallProgress:u,modifyOutputEnd:h},i.agent=a,i.__block=u,ta(t,e,i)}var a=n.overallTask=n.overallTask||Mo({reset:Uo});a.context={ecModel:i,api:r,overallReset:e.overallReset,scheduler:t};var s=a.agentStubMap=a.agentStubMap||F(),l=e.seriesType,c=e.getTargetSeries,u=!0,h=e.modifyOutputEnd;l?i.eachRawSeriesByType(l,o):c?c(i,r).each(o):(u=!1,p(i.getSeries(),o));var f=t._pipelineMap;s.each(function(t,e){f.get(e)||(t.dispose(),a.dirty(),s.removeKey(e))})}function Uo(t){t.overallReset(t.ecModel,t.api,t.payload)}function Xo(t,e){return t.overallProgress&&qo}function qo(){this.agent.dirty(),this.getDownstream().dirty()}function Yo(){this.agent&&this.agent.dirty()}function Zo(t){return t.plan&&t.plan(t.model,t.ecModel,t.api,t.payload)}function Ko(t){t.useClearVisual&&t.data.clearAllVisual();var e=t.resetDefines=ln(t.reset(t.model,t.ecModel,t.api,t.payload));return e.length>1?g(e,function(t,e){return Jo(e)}):cp}function Jo(t){return function(e,n){var i=n.data,r=n.resetDefines[t];if(r&&r.dataEach)for(var o=e.start;o<e.end;o++)r.dataEach(i,o);else r&&r.progress&&r.progress(e,i)}}function Qo(t){return t.data.count()}function ta(t,e,n){var i=e.uid,r=t._pipelineMap.get(i);!r.head&&(r.head=n),r.tail&&r.tail.pipe(n),r.tail=n,n.__idxInPipeline=r.count++,n.__pipeline=r}function ea(t){up=null;try{t(hp,fp)}catch(t){}return up}function na(t,e){for(var n in e.prototype)t[n]=B}function ia(t){return function(e,n,i){e=e&&e.toLowerCase(),Fc.prototype[t].call(this,e,n,i)}}function ra(){Fc.call(this)}function oa(t,e,n){function i(t,e){return t.__prio-e.__prio}n=n||{},"string"==typeof e&&(e=Bp[e]),this.id,this.group,this._dom=t;var o=this._zr=sn(t,{renderer:n.renderer||"canvas",devicePixelRatio:n.devicePixelRatio,width:n.width,height:n.height});this._throttledZrFlush=Ho(y(o.flush,o),17),(e=r(e))&&Bd(e,!0),this._theme=e,this._chartsViews=[],this._chartsMap={},this._componentsViews=[],this._componentsMap={},this._coordSysMgr=new Yr;var a=this._api=Sa(this);Ut(Fp,i),Ut(Rp,i),this._scheduler=new Wo(this,a,Rp,Fp),Fc.call(this),this._messageCenter=new ra,this._initEvents(),this.resize=y(this.resize,this),this._pendingActions=[],o.animation.on("frame",this._onframe,this),da(o,this),R(this)}function aa(t,e,n){var i,r=this._model,o=this._coordSysMgr.getCoordinateSystems();e=yn(r,e);for(var a=0;a<o.length;a++){var s=o[a];if(s[t]&&null!=(i=s[t](r,e,n)))return i}}function sa(t){var e=t._model,n=t._scheduler;n.restorePipelines(e),n.prepareStageTasks(),pa(t,"component",e,n),pa(t,"chart",e,n),n.plan()}function la(t,e,n,i,r){function o(i){i&&i.__alive&&i[e]&&i[e](i.__model,a,t._api,n)}var a=t._model;if(i){var s={};s[i+"Id"]=n[i+"Id"],s[i+"Index"]=n[i+"Index"],s[i+"Name"]=n[i+"Name"];var l={mainType:i,query:s};r&&(l.subType=r);var c=n.excludeSeriesId;null!=c&&(c=F(ln(c))),a&&a.eachComponent(l,function(e){c&&null!=c.get(e.id)||o(t["series"===i?"_chartsMap":"_componentsMap"][e.__viewId])},t)}else yp(t._componentsViews.concat(t._chartsViews),o)}function ca(t,e){var n=t._chartsMap,i=t._scheduler;e.eachSeries(function(t){i.updateStreamModes(t,n[t.__viewId])})}function ua(t,e){var n=t.type,i=t.escapeConnect,r=Op[n],o=r.actionInfo,a=(o.update||"update").split(":"),c=a.pop();a=null!=a[0]&&bp(a[0]),this[kp]=!0;var u=[t],h=!1;t.batch&&(h=!0,u=g(t.batch,function(e){return e=l(s({},e),t),e.batch=null,e}));var f,d=[],p="highlight"===n||"downplay"===n;yp(u,function(t){f=r.action(t,this._model,this._api),(f=f||s({},t)).type=o.event||f.type,d.push(f),p?la(this,c,t,"series"):a&&la(this,c,t,a.main,a.sub)},this),"none"===c||p||a||(this[Pp]?(sa(this),Dp.update.call(this,t),this[Pp]=!1):Dp[c].call(this,t)),f=h?{type:o.event||n,escapeConnect:i,batch:d}:d[0],this[kp]=!1,!e&&this._messageCenter.trigger(f.type,f)}function ha(t){for(var e=this._pendingActions;e.length;){var n=e.shift();ua.call(this,n,t)}}function fa(t){!t&&this.trigger("updated")}function da(t,e){t.on("rendered",function(){e.trigger("rendered"),!t.animation.isFinished()||e[Pp]||e._scheduler.unfinished||e._pendingActions.length||e.trigger("finished")})}function pa(t,e,n,i){function r(t){var e="_ec_"+t.id+"_"+t.type,r=s[e];if(!r){var u=bp(t.type);(r=new(o?Zd.getClass(u.main,u.sub):No.getClass(u.sub))).init(n,c),s[e]=r,a.push(r),l.add(r.group)}t.__viewId=r.__id=e,r.__alive=!0,r.__model=t,r.group.__ecComponentInfo={mainType:t.mainType,index:t.componentIndex},!o&&i.prepareView(r,t,n,c)}for(var o="component"===e,a=o?t._componentsViews:t._chartsViews,s=o?t._componentsMap:t._chartsMap,l=t._zr,c=t._api,u=0;u<a.length;u++)a[u].__alive=!1;for(o?n.eachComponent(function(t,e){"series"!==t&&r(e)}):n.eachSeries(r),u=0;u<a.length;){var h=a[u];h.__alive?u++:(!o&&h.renderTask.dispose(),l.remove(h.group),h.dispose(n,c),a.splice(u,1),delete s[h.__id],h.__id=h.group.__ecComponentInfo=null)}}function ga(t){t.clearColorPalette(),t.eachSeries(function(t){t.clearColorPalette()})}function va(t,e,n,i){ma(t,e,n,i),yp(t._chartsViews,function(t){t.__alive=!1}),ya(t,e,n,i),yp(t._chartsViews,function(t){t.__alive||t.remove(e,n)})}function ma(t,e,n,i,r){yp(r||t._componentsViews,function(t){var r=t.__model;t.render(r,e,n,i),wa(r,t)})}function ya(t,e,n,i,r){var o,a=t._scheduler;e.eachSeries(function(e){var n=t._chartsMap[e.__viewId];n.__alive=!0;var s=n.renderTask;a.updatePayload(s,i),r&&r.get(e.uid)&&s.dirty(),o|=s.perform(a.getPerformArgs(s)),n.group.silent=!!e.get("silent"),wa(e,n),ba(e,n)}),a.unfinished|=o,xa(t._zr,e),op(t._zr.dom,e)}function _a(t,e){yp(zp,function(n){n(t,e)})}function xa(t,e){var n=t.storage,i=0;n.traverse(function(t){t.isGroup||i++}),i>e.get("hoverLayerThreshold")&&!xc.node&&n.traverse(function(t){t.isGroup||(t.useHoverLayer=!0)})}function ba(t,e){var n=t.get("blendMode")||null;e.group.traverse(function(t){t.isGroup||t.style.blend!==n&&t.setStyle("blend",n),t.eachPendingDisplayable&&t.eachPendingDisplayable(function(t){t.setStyle("blend",n)})})}function wa(t,e){var n=t.get("z"),i=t.get("zlevel");e.group.traverse(function(t){"group"!==t.type&&(null!=n&&(t.z=n),null!=i&&(t.zlevel=i))})}function Sa(t){var e=t._coordSysMgr;return s(new qr(t),{getCoordinateSystems:y(e.getCoordinateSystems,e),getComponentByElement:function(e){for(;e;){var n=e.__ecComponentInfo;if(null!=n)return t._model.getComponent(n.mainType,n.index);e=e.parent}}})}function Ma(t){function e(t,e){for(var i=0;i<t.length;i++)t[i][n]=e}var n="__connectUpdateStatus";yp(Ep,function(i,r){t._messageCenter.on(r,function(i){if(Wp[t.group]&&0!==t[n]){if(i&&i.escapeConnect)return;var r=t.makeActionFromEvent(i),o=[];yp(Hp,function(e){e!==t&&e.group===t.group&&o.push(e)}),e(o,0),yp(o,function(t){1!==t[n]&&t.dispatchAction(r)}),e(o,2)}})})}function Aa(t){Wp[t]=!1}function Ta(t){return Hp[bn(t,Gp)]}function ka(t,e){Bp[t]=e}function Pa(t){Np.push(t)}function Ca(t,e){Oa(Rp,t,e,Sp)}function Ia(t,e,n){"function"==typeof e&&(n=e,e="");var i=xp(t)?t.type:[t,t={event:e}][0];t.event=(t.event||i).toLowerCase(),e=t.event,mp(Cp.test(i)&&Cp.test(e)),Op[i]||(Op[i]={action:n,actionInfo:t}),Ep[e]=i}function Da(t,e){Oa(Fp,t,e,Mp,"layout")}function La(t,e){Oa(Fp,t,e,Ap,"visual")}function Oa(t,e,n,i,r){(_p(e)||xp(e))&&(n=e,e=i);var o=Wo.wrapStageHandler(n,r);return o.__prio=e,o.__raw=n,t.push(o),o}function Ea(t,e){$p[t]=e}function Ra(t){return Zd.extend(t)}function Na(t){return Yd.extend(t)}function za(t){return No.extend(t)}function Fa(t){return t}function Ba(t,e,n,i,r){this._old=t,this._new=e,this._oldKeyGetter=n||Fa,this._newKeyGetter=i||Fa,this.context=r}function $a(t,e,n,i,r){for(var o=0;o<t.length;o++){var a="_ec_"+r[i](t[o],o),s=e[a];null==s?(n.push(a),e[a]=o):(s.length||(e[a]=s=[s]),s.push(o))}}function Ha(t){var e={},n=e.encode={},i=F(),r=[],o=[];p(t.dimensions,function(e){var a=t.getDimensionInfo(e),s=a.coordDim;if(s){var l=n[s];n.hasOwnProperty(s)||(l=n[s]=[]),l[a.coordDimIndex]=e,a.isExtraCoord||(i.set(s,1),Va(a.type)&&(r[0]=e)),a.defaultTooltip&&o.push(e)}Yp.each(function(t,e){var i=n[e];n.hasOwnProperty(e)||(i=n[e]=[]);var r=a.otherDims[e];null!=r&&!1!==r&&(i[r]=a.name)})});var a=[],s={};i.each(function(t,e){var i=n[e];s[e]=i[0],a=a.concat(i)}),e.dataDimsOnCoord=a,e.encodeFirstDimNotExtra=s;var l=n.label;l&&l.length&&(r=l.slice());var c=n.tooltip;return c&&c.length?o=c.slice():o.length||(o=r.slice()),n.defaultedLabel=r,n.defaultedTooltip=o,e}function Wa(t){return"category"===t?"ordinal":"time"===t?"time":"float"}function Va(t){return!("ordinal"===t||"time"===t)}function ja(t){return t._rawCount>65535?Qp:tg}function Ga(t){var e=t.constructor;return e===Array?t.slice():new e(t)}function Ua(t,e){p(eg.concat(e.__wrappedMethods||[]),function(n){e.hasOwnProperty(n)&&(t[n]=e[n])}),t.__wrappedMethods=e.__wrappedMethods,p(ng,function(n){t[n]=r(e[n])}),t._calculationInfo=s(e._calculationInfo)}function Xa(t){var e=t._invertedIndicesMap;p(e,function(n,i){var r=t._dimensionInfos[i].ordinalMeta;if(r){for(n=e[i]=new Qp(r.categories.length),o=0;o<n.length;o++)n[o]=NaN;for(var o=0;o<t._count;o++)n[t.get(i,o)]=o}})}function qa(t,e,n){var i;if(null!=e){var r=t._chunkSize,o=Math.floor(n/r),a=n%r,s=t.dimensions[e],l=t._storage[s][o];if(l){i=l[a];var c=t._dimensionInfos[s].ordinalMeta;c&&c.categories.length&&(i=c.categories[i])}}return i}function Ya(t){return t}function Za(t){return t<this._count&&t>=0?this._indices[t]:-1}function Ka(t,e){var n=t._idList[e];return null==n&&(n=qa(t,t._idDimIdx,e)),null==n&&(n=Kp+e),n}function Ja(t){return x(t)||(t=[t]),t}function Qa(t,e){var n=t.dimensions,i=new ig(g(n,t.getDimensionInfo,t),t.hostModel);Ua(i,t);for(var r=i._storage={},o=t._storage,a=0;a<n.length;a++){var s=n[a];o[s]&&(u(e,s)>=0?(r[s]=ts(o[s]),i._rawExtent[s]=es(),i._extent[s]=null):r[s]=o[s])}return i}function ts(t){for(var e=new Array(t.length),n=0;n<t.length;n++)e[n]=Ga(t[n]);return e}function es(){return[1/0,-1/0]}function ns(t,e,n){function i(t,e,n){null!=Yp.get(e)?t.otherDims[e]=n:(t.coordDim=e,t.coordDimIndex=n,u.set(e,!0))}Pr.isInstance(e)||(e=Pr.seriesDataToSource(e)),n=n||{},t=(t||[]).slice();for(var o=(n.dimsDef||[]).slice(),a=F(n.encodeDef),c=F(),u=F(),h=[],f=is(e,t,o,n.dimCount),d=0;d<f;d++){var g=o[d]=s({},S(o[d])?o[d]:{name:o[d]}),v=g.name,m=h[d]={otherDims:{}};null!=v&&null==c.get(v)&&(m.name=m.displayName=v,c.set(v,d)),null!=g.type&&(m.type=g.type),null!=g.displayName&&(m.displayName=g.displayName)}a.each(function(t,e){t=ln(t).slice();var n=a.set(e,[]);p(t,function(t,r){w(t)&&(t=c.get(t)),null!=t&&t<f&&(n[r]=t,i(h[t],e,r))})});var y=0;p(t,function(t,e){var n,o,s;if(w(t))n=t,t={};else{n=t.name;var c=t.ordinalMeta;t.ordinalMeta=null,(t=r(t)).ordinalMeta=c,o=t.dimsDef,s=t.otherDims,t.name=t.coordDim=t.coordDimIndex=t.dimsDef=t.otherDims=null}var u=ln(a.get(n));if(!u.length)for(var f=0;f<(o&&o.length||1);f++){for(;y<h.length&&null!=h[y].coordDim;)y++;y<h.length&&u.push(y++)}p(u,function(e,r){var a=h[e];if(i(l(a,t),n,r),null==a.name&&o){var c=o[r];!S(c)&&(c={name:c}),a.name=a.displayName=c.name,a.defaultTooltip=c.defaultTooltip}s&&l(a.otherDims,s)})});var _=n.generateCoord,x=n.generateCoordCount,b=null!=x;x=_?x||1:0;for(var M=_||"value",A=0;A<f;A++)null==(m=h[A]=h[A]||{}).coordDim&&(m.coordDim=rs(M,u,b),m.coordDimIndex=0,(!_||x<=0)&&(m.isExtraCoord=!0),x--),null==m.name&&(m.name=rs(m.coordDim,c)),null==m.type&&Br(e,A,m.name)&&(m.type="ordinal");return h}function is(t,e,n,i){var r=Math.max(t.dimensionsDetectCount||1,e.length,n.length,i||0);return p(e,function(t){var e=t.dimsDef;e&&(r=Math.max(r,e.length))}),r}function rs(t,e,n){if(n||null!=e.get(t)){for(var i=0;null!=e.get(t+i);)i++;t+=i}return e.set(t,!0),t}function os(t,e,n){var i,r,o,a,s=(n=n||{}).byIndex,l=n.stackedCoordDimension,c=!(!t||!t.get("stack"));if(p(e,function(t,n){w(t)&&(e[n]=t={name:t}),c&&!t.isExtraCoord&&(s||i||!t.ordinalMeta||(i=t),r||"ordinal"===t.type||"time"===t.type||l&&l!==t.coordDim||(r=t))}),!r||s||i||(s=!0),r){o="__\0ecstackresult",a="__\0ecstackedover",i&&(i.createInvertedIndices=!0);var u=r.coordDim,h=r.type,f=0;p(e,function(t){t.coordDim===u&&f++}),e.push({name:o,coordDim:u,coordDimIndex:f,type:h,isExtraCoord:!0,isCalculationCoord:!0}),f++,e.push({name:a,coordDim:a,coordDimIndex:f,type:h,isExtraCoord:!0,isCalculationCoord:!0})}return{stackedDimension:r&&r.name,stackedByDimension:i&&i.name,isStackedByIndex:s,stackedOverDimension:a,stackResultDimension:o}}function as(t,e){return!!e&&e===t.getCalculationInfo("stackedDimension")}function ss(t,e){return as(t,e)?t.getCalculationInfo("stackResultDimension"):e}function ls(t,e,n){n=n||{},Pr.isInstance(t)||(t=Pr.seriesDataToSource(t));var i,r=e.get("coordinateSystem"),o=Yr.get(r),a=Tr(e);a&&(i=g(a.coordSysDims,function(t){var e={name:t},n=a.axisMap.get(t);if(n){var i=n.get("type");e.type=Wa(i)}return e})),i||(i=o&&(o.getDimensionsInfo?o.getDimensionsInfo():o.dimensions.slice())||["x","y"]);var s,l,c=ag(t,{coordDimensions:i,generateCoord:n.generateCoord});a&&p(c,function(t,e){var n=t.coordDim,i=a.categoryAxisMap.get(n);i&&(null==s&&(s=e),t.ordinalMeta=i.getOrdinalMeta()),null!=t.otherDims.itemName&&(l=!0)}),l||null==s||(c[s].otherDims.itemName=0);var u=os(e,c),h=new ig(c,e);h.setCalculationInfo(u);var f=null!=s&&cs(t)?function(t,e,n,i){return i===s?n:this.defaultDimValueGetter(t,e,n,i)}:null;return h.hasItemOption=!1,h.initData(t,null,f),h}function cs(t){if(t.sourceFormat===gd){var e=us(t.data||[]);return null!=e&&!x(un(e))}}function us(t){for(var e=0;e<t.length&&null==t[e];)e++;return t[e]}function hs(t,e){if("image"!==this.type){var n=this.style,i=this.shape;i&&"line"===i.symbolType?n.stroke=t:this.__isEmptyBrush?(n.stroke=t,n.fill=e||"#fff"):(n.fill&&(n.fill=t),n.stroke&&(n.stroke=t)),this.dirty(!1)}}function fs(t,e,n,i,r,o,a){var s,l=0===t.indexOf("empty");return l&&(t=t.substr(5,1).toLowerCase()+t.substr(6)),s=0===t.indexOf("image://")?_i(t.slice(8),new Ft(e,n,i,r),a?"center":"cover"):0===t.indexOf("path://")?yi(t.slice(7),{},new Ft(e,n,i,r),a?"center":"cover"):new dg({shape:{symbolType:t,x:e,y:n,width:i,height:r}}),s.__isEmptyBrush=l,s.setColor=hs,s.setColor(o),s}function ds(t,e){var n=t.mapDimension("defaultedLabel",!0),i=n.length;if(1===i)return wo(t,e,n[0]);if(i){for(var r=[],o=0;o<n.length;o++){var a=wo(t,e,n[o]);r.push(a)}return r.join(" ")}}function ps(t,e,n){pu.call(this),this.updateData(t,e,n)}function gs(t){return[t[0]/2,t[1]/2]}function vs(t,e){this.parent.drift(t,e)}function ms(t){this.group=new pu,this._symbolCtor=t||ps}function ys(t,e,n,i){return e&&!isNaN(e[0])&&!isNaN(e[1])&&!(i.isIgnore&&i.isIgnore(n))&&!(i.clipShape&&!i.clipShape.contain(e[0],e[1]))&&"none"!==t.getItemVisual(n,"symbol")}function _s(t){return null==t||S(t)||(t={isIgnore:t}),t||{}}function xs(t){var e=t.hostModel;return{itemStyle:e.getModel("itemStyle").getItemStyle(["color"]),hoverItemStyle:e.getModel("emphasis.itemStyle").getItemStyle(),symbolRotate:e.get("symbolRotate"),symbolOffset:e.get("symbolOffset"),hoverAnimation:e.get("hoverAnimation"),labelModel:e.getModel("label"),hoverLabelModel:e.getModel("emphasis.label"),cursorStyle:e.get("cursor")}}function bs(t,e,n){var i,r=t.getBaseAxis(),o=t.getOtherAxis(r),a=ws(o,n),s=r.dim,l=o.dim,c=e.mapDimension(l),u=e.mapDimension(s),h="x"===l||"radius"===l?1:0,f=g(t.dimensions,function(t){return e.mapDimension(t)}),d=e.getCalculationInfo("stackResultDimension");return(i|=as(e,f[0]))&&(f[0]=d),(i|=as(e,f[1]))&&(f[1]=d),{dataDimsForPoint:f,valueStart:a,valueAxisDim:l,baseAxisDim:s,stacked:!!i,valueDim:c,baseDim:u,baseDataOffset:h,stackedOverDimension:e.getCalculationInfo("stackedOverDimension")}}function ws(t,e){var n=0,i=t.scale.getExtent();return"start"===e?n=i[0]:"end"===e?n=i[1]:i[0]>0?n=i[0]:i[1]<0&&(n=i[1]),n}function Ss(t,e,n,i){var r=NaN;t.stacked&&(r=n.get(n.getCalculationInfo("stackedOverDimension"),i)),isNaN(r)&&(r=t.valueStart);var o=t.baseDataOffset,a=[];return a[o]=n.get(t.baseDim,i),a[1-o]=r,e.dataToPoint(a)}function Ms(t,e){var n=[];return e.diff(t).add(function(t){n.push({cmd:"+",idx:t})}).update(function(t,e){n.push({cmd:"=",idx:e,idx1:t})}).remove(function(t){n.push({cmd:"-",idx:t})}).execute(),n}function As(t){return isNaN(t[0])||isNaN(t[1])}function Ts(t,e,n,i,r,o,a,s,l,c,u){return"none"!==c&&c?ks.apply(this,arguments):Ps.apply(this,arguments)}function ks(t,e,n,i,r,o,a,s,l,c,u){for(var h=0,f=n,d=0;d<i;d++){var p=e[f];if(f>=r||f<0)break;if(As(p)){if(u){f+=o;continue}break}if(f===n)t[o>0?"moveTo":"lineTo"](p[0],p[1]);else if(l>0){var g=e[h],v="y"===c?1:0,m=(p[v]-g[v])*l;Ag(kg,g),kg[v]=g[v]+m,Ag(Pg,p),Pg[v]=p[v]-m,t.bezierCurveTo(kg[0],kg[1],Pg[0],Pg[1],p[0],p[1])}else t.lineTo(p[0],p[1]);h=f,f+=o}return d}function Ps(t,e,n,i,r,o,a,s,l,c,u){for(var h=0,f=n,d=0;d<i;d++){var p=e[f];if(f>=r||f<0)break;if(As(p)){if(u){f+=o;continue}break}if(f===n)t[o>0?"moveTo":"lineTo"](p[0],p[1]),Ag(kg,p);else if(l>0){var g=f+o,v=e[g];if(u)for(;v&&As(e[g]);)v=e[g+=o];var m=.5,y=e[h];if(!(v=e[g])||As(v))Ag(Pg,p);else{var _,x;if(As(v)&&!u&&(v=p),V(Tg,v,y),"x"===c||"y"===c){var b="x"===c?0:1;_=Math.abs(p[b]-y[b]),x=Math.abs(p[b]-v[b])}else _=Rc(p,y),x=Rc(p,v);Mg(Pg,p,Tg,-l*(1-(m=x/(x+_))))}wg(kg,kg,s),Sg(kg,kg,a),wg(Pg,Pg,s),Sg(Pg,Pg,a),t.bezierCurveTo(kg[0],kg[1],Pg[0],Pg[1],p[0],p[1]),Mg(kg,p,Tg,l*m)}else t.lineTo(p[0],p[1]);h=f,f+=o}return d}function Cs(t,e){var n=[1/0,1/0],i=[-1/0,-1/0];if(e)for(var r=0;r<t.length;r++){var o=t[r];o[0]<n[0]&&(n[0]=o[0]),o[1]<n[1]&&(n[1]=o[1]),o[0]>i[0]&&(i[0]=o[0]),o[1]>i[1]&&(i[1]=o[1])}return{min:e?n:i,max:e?i:n}}function Is(t,e){if(t.length===e.length){for(var n=0;n<t.length;n++){var i=t[n],r=e[n];if(i[0]!==r[0]||i[1]!==r[1])return}return!0}}function Ds(t){return"number"==typeof t?t:t?.5:0}function Ls(t){var e=t.getGlobalExtent();if(t.onBand){var n=t.getBandWidth()/2-1,i=e[1]>e[0]?1:-1;e[0]+=i*n,e[1]-=i*n}return e}function Os(t,e,n){if(!n.valueDim)return[];for(var i=[],r=0,o=e.count();r<o;r++)i.push(Ss(n,t,e,r));return i}function Es(t,e,n,i){var r=Ls(t.getAxis("x")),o=Ls(t.getAxis("y")),a=t.getBaseAxis().isHorizontal(),s=Math.min(r[0],r[1]),l=Math.min(o[0],o[1]),c=Math.max(r[0],r[1])-s,u=Math.max(o[0],o[1])-l;if(n)s-=.5,c+=.5,l-=.5,u+=.5;else{var h=i.get("lineStyle.width")||2,f=i.get("clipOverflow")?h/2:Math.max(c,u);a?(l-=f,u+=2*f):(s-=f,c+=2*f)}var d=new Pf({shape:{x:s,y:l,width:c,height:u}});return e&&(d.shape[a?"width":"height"]=0,qi(d,{shape:{width:c,height:u}},i)),d}function Rs(t,e,n,i){var r=t.getAngleAxis(),o=t.getRadiusAxis().getExtent().slice();o[0]>o[1]&&o.reverse();var a=r.getExtent(),s=Math.PI/180;n&&(o[0]-=.5,o[1]+=.5);var l=new wf({shape:{cx:rr(t.cx,1),cy:rr(t.cy,1),r0:rr(o[0],1),r:rr(o[1],1),startAngle:-a[0]*s,endAngle:-a[1]*s,clockwise:r.inverse}});return e&&(l.shape.endAngle=-a[0]*s,qi(l,{shape:{endAngle:-a[1]*s}},i)),l}function Ns(t,e,n,i){return"polar"===t.type?Rs(t,e,n,i):Es(t,e,n,i)}function zs(t,e,n){for(var i=e.getBaseAxis(),r="x"===i.dim||"radius"===i.dim?0:1,o=[],a=0;a<t.length-1;a++){var s=t[a+1],l=t[a];o.push(l);var c=[];switch(n){case"end":c[r]=s[r],c[1-r]=l[1-r],o.push(c);break;case"middle":var u=(l[r]+s[r])/2,h=[];c[r]=h[r]=u,c[1-r]=l[1-r],h[1-r]=s[1-r],o.push(c),o.push(h);break;default:c[r]=l[r],c[1-r]=s[1-r],o.push(c)}}return t[a]&&o.push(t[a]),o}function Fs(t,e){var n=t.getVisual("visualMeta");if(n&&n.length&&t.count()&&"cartesian2d"===e.type){for(var i,r,o=n.length-1;o>=0;o--){var a=n[o].dimension,s=t.dimensions[a],l=t.getDimensionInfo(s);if("x"===(i=l&&l.coordDim)||"y"===i){r=n[o];break}}if(r){var c=e.getAxis(i),u=g(r.stops,function(t){return{coord:c.toGlobalCoord(c.dataToCoord(t.value)),color:t.color}}),h=u.length,f=r.outerColors.slice();h&&u[0].coord>u[h-1].coord&&(u.reverse(),f.reverse());var d=u[0].coord-10,v=u[h-1].coord+10,m=v-d;if(m<.001)return"transparent";p(u,function(t){t.offset=(t.coord-d)/m}),u.push({offset:h?u[h-1].offset:.5,color:f[1]||"transparent"}),u.unshift({offset:h?u[0].offset:.5,color:f[0]||"transparent"});var y=new Rf(0,0,0,0,u,!0);return y[i]=d,y[i+"2"]=v,y}}}function Bs(t,e,n){var i=t.get("showAllSymbol"),r="auto"===i;if(!i||r){var o=n.getAxesByScale("ordinal")[0];if(o&&(!r||!$s(o,e))){var a=e.mapDimension(o.dim),s={};return p(o.getViewLabels(),function(t){s[t.tickValue]=1}),function(t){return!s.hasOwnProperty(e.get(a,t))}}}}function $s(t,e){var n=t.getExtent(),i=Math.abs(n[1]-n[0])/t.scale.count();isNaN(i)&&(i=0);for(var r=e.count(),o=Math.max(1,Math.round(r/5)),a=0;a<r;a+=o)if(1.5*ps.getSymbolSize(e,a)[t.isHorizontal()?1:0]>i)return!1;return!0}function Hs(t){this._setting=t||{},this._extent=[1/0,-1/0],this._interval=0,this.init&&this.init.apply(this,arguments)}function Ws(t){this.categories=t.categories||[],this._needCollect=t.needCollect,this._deduplication=t.deduplication,this._map}function Vs(t){return t._map||(t._map=F(t.categories))}function js(t){return S(t)&&null!=t.value?t.value:t+""}function Gs(t,e,n,i){var r={},o=t[1]-t[0],a=r.interval=dr(o/e,!0);null!=n&&a<n&&(a=r.interval=n),null!=i&&a>i&&(a=r.interval=i);var s=r.intervalPrecision=Us(a);return qs(r.niceTickExtent=[Ng(Math.ceil(t[0]/a)*a,s),Ng(Math.floor(t[1]/a)*a,s)],t),r}function Us(t){return or(t)+2}function Xs(t,e,n){t[e]=Math.max(Math.min(t[e],n[1]),n[0])}function qs(t,e){!isFinite(t[0])&&(t[0]=e[0]),!isFinite(t[1])&&(t[1]=e[1]),Xs(t,0,e),Xs(t,1,e),t[0]>t[1]&&(t[0]=t[1])}function Ys(t,e,n,i){var r=[];if(!t)return r;e[0]<n[0]&&r.push(e[0]);for(var o=n[0];o<=n[1]&&(r.push(o),(o=Ng(o+t,i))!==r[r.length-1]);)if(r.length>1e4)return[];return e[1]>(r.length?r[r.length-1]:n[1])&&r.push(e[1]),r}function Zs(t){return t.get("stack")||Bg+t.seriesIndex}function Ks(t){return t.dim+t.index}function Js(t,e){var n=[];return e.eachSeriesByType(t,function(t){nl(t)&&!il(t)&&n.push(t)}),n}function Qs(t){var e=[];return p(t,function(t){var n=t.getData(),i=t.coordinateSystem.getBaseAxis(),r=i.getExtent(),o="category"===i.type?i.getBandWidth():Math.abs(r[1]-r[0])/n.count(),a=ir(t.get("barWidth"),o),s=ir(t.get("barMaxWidth"),o),l=t.get("barGap"),c=t.get("barCategoryGap");e.push({bandWidth:o,barWidth:a,barMaxWidth:s,barGap:l,barCategoryGap:c,axisKey:Ks(i),stackId:Zs(t)})}),tl(e)}function tl(t){var e={};p(t,function(t,n){var i=t.axisKey,r=t.bandWidth,o=e[i]||{bandWidth:r,remainedWidth:r,autoWidthCount:0,categoryGap:"20%",gap:"30%",stacks:{}},a=o.stacks;e[i]=o;var s=t.stackId;a[s]||o.autoWidthCount++,a[s]=a[s]||{width:0,maxWidth:0};var l=t.barWidth;l&&!a[s].width&&(a[s].width=l,l=Math.min(o.remainedWidth,l),o.remainedWidth-=l);var c=t.barMaxWidth;c&&(a[s].maxWidth=c);var u=t.barGap;null!=u&&(o.gap=u);var h=t.barCategoryGap;null!=h&&(o.categoryGap=h)});var n={};return p(e,function(t,e){n[e]={};var i=t.stacks,r=t.bandWidth,o=ir(t.categoryGap,r),a=ir(t.gap,1),s=t.remainedWidth,l=t.autoWidthCount,c=(s-o)/(l+(l-1)*a);c=Math.max(c,0),p(i,function(t,e){var n=t.maxWidth;n&&n<c&&(n=Math.min(n,s),t.width&&(n=Math.min(n,t.width)),s-=n,t.width=n,l--)}),c=(s-o)/(l+(l-1)*a),c=Math.max(c,0);var u,h=0;p(i,function(t,e){t.width||(t.width=c),u=t,h+=t.width*(1+a)}),u&&(h-=u.width*a);var f=-h/2;p(i,function(t,i){n[e][i]=n[e][i]||{offset:f,width:t.width},f+=t.width*(1+a)})}),n}function el(t,e,n){if(t&&e){var i=t[Ks(e)];return null!=i&&null!=n&&(i=i[Zs(n)]),i}}function nl(t){return t.coordinateSystem&&"cartesian2d"===t.coordinateSystem.type}function il(t){return t.pipelineContext&&t.pipelineContext.large}function rl(t,e,n){return u(t.getAxesOnZeroOf(),e)>=0||n?e.toGlobalCoord(e.dataToCoord(0)):e.getGlobalExtent()[0]}function ol(t,e){return Kg(t,Zg(e))}function al(t,e){var n,i,r,o=t.type,a=e.getMin(),s=e.getMax(),l=null!=a,c=null!=s,u=t.getExtent();"ordinal"===o?n=e.getCategories().length:(x(i=e.get("boundaryGap"))||(i=[i||0,i||0]),"boolean"==typeof i[0]&&(i=[0,0]),i[0]=ir(i[0],1),i[1]=ir(i[1],1),r=u[1]-u[0]||Math.abs(u[0])),null==a&&(a="ordinal"===o?n?0:NaN:u[0]-i[0]*r),null==s&&(s="ordinal"===o?n?n-1:NaN:u[1]+i[1]*r),"dataMin"===a?a=u[0]:"function"==typeof a&&(a=a({min:u[0],max:u[1]})),"dataMax"===s?s=u[1]:"function"==typeof s&&(s=s({min:u[0],max:u[1]})),(null==a||!isFinite(a))&&(a=NaN),(null==s||!isFinite(s))&&(s=NaN),t.setBlank(k(a)||k(s)||"ordinal"===o&&!t.getOrdinalMeta().categories.length),e.getNeedCrossZero()&&(a>0&&s>0&&!l&&(a=0),a<0&&s<0&&!c&&(s=0));var h=e.ecModel;if(h&&"time"===o){var f,d=Js("bar",h);if(p(d,function(t){f|=t.getBaseAxis()===e.axis}),f){var g=Qs(d),v=sl(a,s,e,g);a=v.min,s=v.max}}return[a,s]}function sl(t,e,n,i){var r=n.axis.getExtent(),o=r[1]-r[0],a=el(i,n.axis);if(void 0===a)return{min:t,max:e};var s=1/0;p(a,function(t){s=Math.min(t.offset,s)});var l=-1/0;p(a,function(t){l=Math.max(t.offset+t.width,l)}),s=Math.abs(s),l=Math.abs(l);var c=s+l,u=e-t,h=u/(1-(s+l)/o)-u;return e+=h*(l/c),t-=h*(s/c),{min:t,max:e}}function ll(t,e){var n=al(t,e),i=null!=e.getMin(),r=null!=e.getMax(),o=e.get("splitNumber");"log"===t.type&&(t.base=e.get("logBase"));var a=t.type;t.setExtent(n[0],n[1]),t.niceExtent({splitNumber:o,fixMin:i,fixMax:r,minInterval:"interval"===a||"time"===a?e.get("minInterval"):null,maxInterval:"interval"===a||"time"===a?e.get("maxInterval"):null});var s=e.get("interval");null!=s&&t.setInterval&&t.setInterval(s)}function cl(t,e){if(e=e||t.get("type"))switch(e){case"category":return new Rg(t.getOrdinalMeta?t.getOrdinalMeta():t.getCategories(),[1/0,-1/0]);case"value":return new Fg;default:return(Hs.getClass(e)||Fg).create(t)}}function ul(t){var e=t.scale.getExtent(),n=e[0],i=e[1];return!(n>0&&i>0||n<0&&i<0)}function hl(t){var e=t.getLabelModel().get("formatter"),n="category"===t.type?t.scale.getExtent()[0]:null;return"string"==typeof e?e=function(t){return function(e){return t.replace("{value}",null!=e?e:"")}}(e):"function"==typeof e?function(i,r){return null!=n&&(r=i-n),e(fl(t,i),r)}:function(e){return t.scale.getLabel(e)}}function fl(t,e){return"category"===t.type?t.scale.getLabel(e):e}function dl(t){var e=t.model,n=t.scale;if(e.get("axisLabel.show")&&!n.isBlank()){var i,r,o="category"===t.type,a=n.getExtent();r=o?n.count():(i=n.getTicks()).length;var s,l=t.getLabelModel(),c=hl(t),u=1;r>40&&(u=Math.ceil(r/40));for(var h=0;h<r;h+=u){var f=c(i?i[h]:a[0]+h),d=pl(l.getTextRect(f),l.get("rotate")||0);s?s.union(d):s=d}return s}}function pl(t,e){var n=e*Math.PI/180,i=t.plain(),r=i.width,o=i.height,a=r*Math.cos(n)+o*Math.sin(n),s=r*Math.sin(n)+o*Math.cos(n);return new Ft(i.x,i.y,a,s)}function gl(t){return this._axes[t]}function vl(t){iv.call(this,t)}function ml(t){return"category"===t.type?_l(t):wl(t)}function yl(t,e){return"category"===t.type?bl(t,e):{ticks:t.scale.getTicks()}}function _l(t){var e=t.getLabelModel(),n=xl(t,e);return!e.get("show")||t.scale.isBlank()?{labels:[],labelCategoryInterval:n.labelCategoryInterval}:n}function xl(t,e){var n,i,r=Sl(t,"labels"),o=Dl(e),a=Ml(r,o);return a||(n=b(o)?Il(t,o):Cl(t,i="auto"===o?Tl(t):o),Al(r,o,{labels:n,labelCategoryInterval:i}))}function bl(t,e){var n,i,r=Sl(t,"ticks"),o=Dl(e),a=Ml(r,o);if(a)return a;if(e.get("show")&&!t.scale.isBlank()||(n=[]),b(o))n=Il(t,o,!0);else if("auto"===o){var s=xl(t,t.getLabelModel());i=s.labelCategoryInterval,n=g(s.labels,function(t){return t.tickValue})}else n=Cl(t,i=o,!0);return Al(r,o,{ticks:n,tickCategoryInterval:i})}function wl(t){var e=t.scale.getTicks(),n=hl(t);return{labels:g(e,function(e,i){return{formattedLabel:n(e,i),rawLabel:t.scale.getLabel(e),tickValue:e}})}}function Sl(t,e){return rv(t)[e]||(rv(t)[e]=[])}function Ml(t,e){for(var n=0;n<t.length;n++)if(t[n].key===e)return t[n].value}function Al(t,e,n){return t.push({key:e,value:n}),n}function Tl(t){var e=rv(t).autoInterval;return null!=e?e:rv(t).autoInterval=t.calculateCategoryInterval()}function kl(t){var e=Pl(t),n=hl(t),i=(e.axisRotate-e.labelRotate)/180*Math.PI,r=t.scale,o=r.getExtent(),a=r.count();if(o[1]-o[0]<1)return 0;var s=1;a>40&&(s=Math.max(1,Math.floor(a/40)));for(var l=o[0],c=t.dataToCoord(l+1)-t.dataToCoord(l),u=Math.abs(c*Math.cos(i)),h=Math.abs(c*Math.sin(i)),f=0,d=0;l<=o[1];l+=s){var p=0,g=0,v=ie(n(l),e.font,"center","top");p=1.3*v.width,g=1.3*v.height,f=Math.max(f,p,7),d=Math.max(d,g,7)}var m=f/u,y=d/h;isNaN(m)&&(m=1/0),isNaN(y)&&(y=1/0);var _=Math.max(0,Math.floor(Math.min(m,y))),x=rv(t.model),b=x.lastAutoInterval,w=x.lastTickCount;return null!=b&&null!=w&&Math.abs(b-_)<=1&&Math.abs(w-a)<=1&&b>_?_=b:(x.lastTickCount=a,x.lastAutoInterval=_),_}function Pl(t){var e=t.getLabelModel();return{axisRotate:t.getRotate?t.getRotate():t.isHorizontal&&!t.isHorizontal()?90:0,labelRotate:e.get("rotate")||0,font:e.getFont()}}function Cl(t,e,n){function i(t){l.push(n?t:{formattedLabel:r(t),rawLabel:o.getLabel(t),tickValue:t})}var r=hl(t),o=t.scale,a=o.getExtent(),s=t.getLabelModel(),l=[],c=Math.max((e||0)+1,1),u=a[0],h=o.count();0!==u&&c>1&&h/c>2&&(u=Math.round(Math.ceil(u/c)*c));var f={min:s.get("showMinLabel"),max:s.get("showMaxLabel")};f.min&&u!==a[0]&&i(a[0]);for(var d=u;d<=a[1];d+=c)i(d);return f.max&&d!==a[1]&&i(a[1]),l}function Il(t,e,n){var i=t.scale,r=hl(t),o=[];return p(i.getTicks(),function(t){var a=i.getLabel(t);e(t,a)&&o.push(n?t:{formattedLabel:r(t),rawLabel:a,tickValue:t})}),o}function Dl(t){var e=t.get("interval");return null==e?"auto":e}function Ll(t,e){var n=(t[1]-t[0])/e/2;t[0]+=n,t[1]-=n}function Ol(t,e,n,i,r){function o(t,e){return u?t>e:t<e}var a=e.length;if(t.onBand&&!i&&a){var s,l=t.getExtent();if(1===a)e[0].coord=l[0],s=e[1]={coord:l[0]};else{var c=e[1].coord-e[0].coord;p(e,function(t){t.coord-=c/2;var e=e||0;e%2>0&&(t.coord-=c/(2*(e+1)))}),s={coord:e[a-1].coord+c},e.push(s)}var u=l[0]>l[1];o(e[0].coord,l[0])&&(r?e[0].coord=l[0]:e.shift()),r&&o(l[0],e[0].coord)&&e.unshift({coord:l[0]}),o(l[1],s.coord)&&(r?s.coord=l[1]:e.pop()),r&&o(s.coord,l[1])&&e.push({coord:l[1]})}}function El(t,e){return e.type||(e.data?"category":"value")}function Rl(t,e,n){return t.getCoordSysModel()===e}function Nl(t,e,n){this._coordsMap={},this._coordsList=[],this._axesMap={},this._axesList=[],this._initCartesian(t,e,n),this.model=t}function zl(t,e,n){n.getAxesOnZeroOf=function(){return i?[i]:[]};var i,r=t[e],o=n.model,a=o.get("axisLine.onZero"),s=o.get("axisLine.onZeroAxisIndex");if(a)if(null==s){for(var l in r)if(r.hasOwnProperty(l)&&Fl(r[l])){i=r[l];break}}else Fl(r[s])&&(i=r[s])}function Fl(t){return t&&"category"!==t.type&&"time"!==t.type&&ul(t)}function Bl(t,e){var n=t.getExtent(),i=n[0]+n[1];t.toGlobalCoord="x"===t.dim?function(t){return t+e}:function(t){return i-t+e},t.toLocalCoord="x"===t.dim?function(t){return t-e}:function(t){return i-t+e}}function $l(t,e){return g(vv,function(e){return t.getReferringComponents(e)[0]})}function Hl(t){return"cartesian2d"===t.get("coordinateSystem")}function Wl(t){var e={componentType:t.mainType};return e[t.mainType+"Index"]=t.componentIndex,e}function Vl(t,e,n,i){var r,o,a=lr(n-t.rotation),s=i[0]>i[1],l="start"===e&&!s||"start"!==e&&s;return cr(a-mv/2)?(o=l?"bottom":"top",r="center"):cr(a-1.5*mv)?(o=l?"top":"bottom",r="center"):(o="middle",r=a<1.5*mv&&a>mv/2?l?"left":"right":l?"right":"left"),{rotation:a,textAlign:r,textVerticalAlign:o}}function jl(t){var e=t.get("tooltip");return t.get("silent")||!(t.get("triggerEvent")||e&&e.show)}function Gl(t,e,n){var i=t.get("axisLabel.showMinLabel"),r=t.get("axisLabel.showMaxLabel");e=e||[],n=n||[];var o=e[0],a=e[1],s=e[e.length-1],l=e[e.length-2],c=n[0],u=n[1],h=n[n.length-1],f=n[n.length-2];!1===i?(Ul(o),Ul(c)):Xl(o,a)&&(i?(Ul(a),Ul(u)):(Ul(o),Ul(c))),!1===r?(Ul(s),Ul(h)):Xl(l,s)&&(r?(Ul(l),Ul(f)):(Ul(s),Ul(h)))}function Ul(t){t&&(t.ignore=!0)}function Xl(t,e,n){var i=t&&t.getBoundingRect().clone(),r=e&&e.getBoundingRect().clone();if(i&&r){var o=rt([]);return lt(o,o,-t.rotation),i.applyTransform(at([],o,t.getLocalTransform())),r.applyTransform(at([],o,e.getLocalTransform())),i.intersect(r)}}function ql(t){return"middle"===t||"center"===t}function Yl(t,e,n){var i=e.axis;if(e.get("axisTick.show")&&!i.scale.isBlank()){for(var r=e.getModel("axisTick"),o=r.getModel("lineStyle"),a=r.get("length"),s=i.getTicksCoords(),c=[],u=[],h=t._transform,f=[],d=0;d<s.length;d++){var p=s[d].coord;c[0]=p,c[1]=0,u[0]=p,u[1]=n.tickDirection*a,h&&(Y(c,c,h),Y(u,u,h));var g=new Cf(wi({anid:"tick_"+s[d].tickValue,shape:{x1:c[0],y1:c[1],x2:u[0],y2:u[1]},style:l(o.getLineStyle(),{stroke:e.get("axisLine.lineStyle.color")}),z2:2,silent:!0}));t.group.add(g),f.push(g)}return f}}function Zl(t,e,n){var i=e.axis;if(P(n.axisLabelShow,e.get("axisLabel.show"))&&!i.scale.isBlank()){var r=e.getModel("axisLabel"),o=r.get("margin"),a=i.getViewLabels(),s=(P(n.labelRotate,r.get("rotate"))||0)*mv/180,l=xv(n.rotation,s,n.labelDirection),c=e.getCategories(!0),u=[],h=jl(e),f=e.get("triggerEvent");return p(a,function(a,s){var d=a.tickValue,p=a.formattedLabel,g=a.rawLabel,v=r;c&&c[d]&&c[d].textStyle&&(v=new Ki(c[d].textStyle,r,e.ecModel));var m=v.getTextColor()||e.get("axisLine.lineStyle.color"),y=[i.dataToCoord(d),n.labelOffset+n.labelDirection*o],_=new yf({anid:"label_"+d,position:y,rotation:l.rotation,silent:h,z2:10});Fi(_.style,v,{text:p,textAlign:v.getShallow("align",!0)||l.textAlign,textVerticalAlign:v.getShallow("verticalAlign",!0)||v.getShallow("baseline",!0)||l.textVerticalAlign,textFill:"function"==typeof m?m("category"===i.type?g:"value"===i.type?d+"":d,s):m}),f&&(_.eventData=Wl(e),_.eventData.targetType="axisLabel",_.eventData.value=g),t._dumbGroup.add(_),_.updateTransform(),u.push(_),t.group.add(_),_.decomposeTransform()}),u}}function Kl(t){var e=Jl(t);if(e){var n=e.axisPointerModel,i=e.axis.scale,r=n.option,o=n.get("status"),a=n.get("value");null!=a&&(a=i.parse(a));var s=tc(n);null==o&&(r.status=s?"show":"hide");var l=i.getExtent().slice();l[0]>l[1]&&l.reverse(),(null==a||a>l[1])&&(a=l[1]),a<l[0]&&(a=l[0]),r.value=a,s&&(r.status=e.axis.scale.isBlank()?"hide":"show")}}function Jl(t){var e=(t.ecModel.getComponent("axisPointer")||{}).coordSysAxesInfo;return e&&e.axesInfo[ec(t)]}function Ql(t){var e=Jl(t);return e&&e.axisPointerModel}function tc(t){return!!t.get("handle.show")}function ec(t){return t.type+"||"+t.id}function nc(t,e,n,i,r,o){var a=bv.getAxisPointerClass(t.axisPointerClass);if(a){var s=Ql(e);s?(t._axisPointer||(t._axisPointer=new a)).render(e,s,i,o):ic(t,i)}}function ic(t,e,n){var i=t._axisPointer;i&&i.dispose(e,n),t._axisPointer=null}function rc(t,e,n){n=n||{};var i=t.coordinateSystem,r=e.axis,o={},a=r.getAxesOnZeroOf()[0],s=r.position,l=a?"onZero":s,c=r.dim,u=i.getRect(),h=[u.x,u.x+u.width,u.y,u.y+u.height],f={left:0,right:1,top:0,bottom:1,onZero:2},d=e.get("offset")||0,p="x"===c?[h[2]-d,h[3]+d]:[h[0]-d,h[1]+d];if(a){var g=a.toGlobalCoord(a.dataToCoord(0));p[f.onZero]=Math.max(Math.min(g,p[1]),p[0])}o.position=["y"===c?p[f[l]]:h[0],"x"===c?p[f[l]]:h[3]],o.rotation=Math.PI/2*("x"===c?0:1);var v={top:-1,bottom:1,left:-1,right:1};o.labelDirection=o.tickDirection=o.nameDirection=v[s],o.labelOffset=a?p[f[s]]-p[f.onZero]:0,e.get("axisTick.inside")&&(o.tickDirection=-o.tickDirection),P(n.labelInside,e.get("axisLabel.inside"))&&(o.labelDirection=-o.labelDirection);var m=e.get("axisLabel.rotate");return o.labelRotate="top"===l?-m:m,o.z2=1,o}function oc(t,e,n,i,r,o,a){zi(t,e,n.getModel("label"),n.getModel("emphasis.label"),{labelFetcher:r,labelDataIndex:o,defaultText:ds(r.getData(),o),isRectText:!0,autoColor:i}),ac(t),ac(e)}function ac(t,e){"outside"===t.textPosition&&(t.textPosition=e)}function sc(t,e,n){n.style.text=null,Xi(n,{shape:{width:0}},e,t,function(){n.parent&&n.parent.remove(n)})}function lc(t,e,n){n.style.text=null,Xi(n,{shape:{r:n.shape.r0}},e,t,function(){n.parent&&n.parent.remove(n)})}function cc(t,e,n,i,r,o,a,s){var c=e.getItemVisual(n,"color"),u=e.getItemVisual(n,"opacity"),h=i.getModel("itemStyle"),f=i.getModel("emphasis.itemStyle").getBarItemStyle();s||t.setShape("r",h.get("barBorderRadius")||0),t.useStyle(l({fill:c,opacity:u},h.getBarItemStyle()));var d=i.getShallow("cursor");d&&t.attr("cursor",d);var p=a?r.height>0?"bottom":"top":r.width>0?"left":"right";s||oc(t.style,f,i,c,o,n,p),Ni(t,f)}function uc(t,e){var n=t.get(Pv)||0;return Math.min(n,Math.abs(e.width),Math.abs(e.height))}function hc(t,e,n){var i=t.getData(),r=[],o=i.getLayout("valueAxisHorizontal")?1:0;r[1-o]=i.getLayout("valueAxisStart");var a=new Dv({shape:{points:i.getLayout("largePoints")},incremental:!!n,__startPoint:r,__valueIdx:o});e.add(a),fc(a,t,i)}function fc(t,e,n){var i=n.getVisual("borderColor")||n.getVisual("color"),r=e.getModel("itemStyle").getItemStyle(["color","borderColor"]);t.useStyle(r),t.style.fill=null,t.style.stroke=i,t.style.lineWidth=n.getLayout("barWidth")}function dc(t,e,n,i){var r=e.getData(),o=this.dataIndex,a=r.getName(o),s=e.get("selectedOffset");i.dispatchAction({type:"pieToggleSelect",from:t,name:a,seriesId:e.id}),r.each(function(t){pc(r.getItemGraphicEl(t),r.getItemLayout(t),e.isSelected(r.getName(t)),s,n)})}function pc(t,e,n,i,r){var o=(e.startAngle+e.endAngle)/2,a=Math.cos(o),s=Math.sin(o),l=n?i:0,c=[a*l,s*l];r?t.animate().when(200,{position:c}).start("bounceOut"):t.attr("position",c)}function gc(t,e){function n(){o.ignore=o.hoverIgnore,a.ignore=a.hoverIgnore}function i(){o.ignore=o.normalIgnore,a.ignore=a.normalIgnore}pu.call(this);var r=new wf({z2:2}),o=new kf,a=new yf;this.add(r),this.add(o),this.add(a),this.updateData(t,e,!0),this.on("emphasis",n).on("normal",i).on("mouseover",n).on("mouseout",i)}function vc(t,e,n,i,r,o,a){function s(e,n){for(var i=e;i>=0&&(t[i].y-=n,!(i>0&&t[i].y>t[i-1].y+t[i-1].height));i--);}function l(t,e,n,i,r,o){for(var a=e?Number.MAX_VALUE:0,s=0,l=t.length;s<l;s++)if("center"!==t[s].position){var c=Math.abs(t[s].y-i),u=t[s].len,h=t[s].len2,f=c<r+u?Math.sqrt((r+u+h)*(r+u+h)-c*c):Math.abs(t[s].x-n);e&&f>=a&&(f=a-10),!e&&f<=a&&(f=a+10),t[s].x=n+f*o,a=f}}t.sort(function(t,e){return t.y-e.y});for(var c,u=0,h=t.length,f=[],d=[],p=0;p<h;p++)(c=t[p].y-u)<0&&function(e,n,i,r){for(var o=e;o<n;o++)if(t[o].y+=i,o>e&&o+1<n&&t[o+1].y>t[o].y+t[o].height)return void s(o,i/2);s(n-1,i/2)}(p,h,-c),u=t[p].y+t[p].height;for(a-u<0&&s(h-1,u-a),p=0;p<h;p++)t[p].y>=n?d.push(t[p]):f.push(t[p]);l(f,!1,e,n,i,r),l(d,!0,e,n,i,r)}function mc(t,e,n,i,r,o){for(var a=[],s=[],l=0;l<t.length;l++)t[l].x<e?a.push(t[l]):s.push(t[l]);for(vc(s,e,n,i,1,r,o),vc(a,e,n,i,-1,r,o),l=0;l<t.length;l++){var c=t[l].linePoints;if(c){var u=c[1][0]-c[2][0];t[l].x<e?c[2][0]=t[l].x+3:c[2][0]=t[l].x-3,c[1][1]=c[2][1]=t[l].y,c[1][0]=c[2][0]+u}}}var yc=2311,_c=function(){return yc++},xc="object"==typeof wx&&"function"==typeof wx.getSystemInfoSync?{browser:{},os:{},node:!1,wxa:!0,canvasSupported:!0,svgSupported:!1,touchEventsSupported:!0}:"undefined"==typeof n&&"undefined"!=typeof self?{browser:{},os:{},node:!1,worker:!0,canvasSupported:!0}:"undefined"==typeof navigator?{browser:{},os:{},node:!0,worker:!1,canvasSupported:!0,svgSupported:!0}:function(t){var i={},r={},o=t.match(/Firefox\/([\d.]+)/),a=t.match(/MSIE\s([\d.]+)/)||t.match(/Trident\/.+?rv:(([\d.]+))/),s=t.match(/Edge\/([\d.]+)/),l=/micromessenger/i.test(t);return o&&(r.firefox=!0,r.version=o[1]),a&&(r.ie=!0,r.version=a[1]),s&&(r.edge=!0,r.version=s[1]),l&&(r.weChat=!0),{browser:r,os:i,node:!1,canvasSupported:!!n.createElement("canvas").getContext,svgSupported:"undefined"!=typeof SVGRect,touchEventsSupported:"ontouchstart"in e&&!r.ie&&!r.edge,pointerEventsSupported:"onpointerdown"in e&&(r.edge||r.ie&&r.version>=11)}}(navigator.userAgent),bc={"[object Function]":1,"[object RegExp]":1,"[object Date]":1,"[object Error]":1,"[object CanvasGradient]":1,"[object CanvasPattern]":1,"[object Image]":1,"[object Canvas]":1},wc={"[object Int8Array]":1,"[object Uint8Array]":1,"[object Uint8ClampedArray]":1,"[object Int16Array]":1,"[object Uint16Array]":1,"[object Int32Array]":1,"[object Uint32Array]":1,"[object Float32Array]":1,"[object Float64Array]":1},Sc=Object.prototype.toString,Mc=Array.prototype,Ac=Mc.forEach,Tc=Mc.filter,kc=Mc.slice,Pc=Mc.map,Cc=Mc.reduce,Ic={},Dc=function(){return Ic.createCanvas()};Ic.createCanvas=function(){return n.createElement("canvas")};var Lc,Oc="__ec_primitive__";z.prototype={constructor:z,get:function(t){return this.hasOwnProperty(t)?this[t]:null},set:function(t,e){return this[t]=e},each:function(t,e){for(var n in void 0!==e&&(t=y(t,e)),this)this.hasOwnProperty(n)&&t(this[n],n)},removeKey:function(t){delete this[t]}};var Ec="undefined"==typeof Float32Array?Array:Float32Array,Rc=q,Nc=function(t,e){return(t[0]-e[0])*(t[0]-e[0])+(t[1]-e[1])*(t[1]-e[1])};J.prototype={constructor:J,_dragStart:function(t){var e=t.target;e&&e.draggable&&(this._draggingTarget=e,e.dragging=!0,this._x=t.offsetX,this._y=t.offsetY,this.dispatchToElement(Q(e,t),"dragstart",t.event))},_drag:function(t){var e=this._draggingTarget;if(e){var n=t.offsetX,i=t.offsetY,r=n-this._x,o=i-this._y;this._x=n,this._y=i,e.drift(r,o,t),this.dispatchToElement(Q(e,t),"drag",t.event);var a=this.findHover(n,i,e).target,s=this._dropTarget;this._dropTarget=a,e!==a&&(s&&a!==s&&this.dispatchToElement(Q(s,t),"dragleave",t.event),a&&a!==s&&this.dispatchToElement(Q(a,t),"dragenter",t.event))}},_dragEnd:function(t){var e=this._draggingTarget;e&&(e.dragging=!1),this.dispatchToElement(Q(e,t),"dragend",t.event),this._dropTarget&&this.dispatchToElement(Q(this._dropTarget,t),"drop",t.event),this._draggingTarget=null,this._dropTarget=null}};var zc=Array.prototype.slice,Fc=function(){this._$handlers={}};Fc.prototype={constructor:Fc,one:function(t,e,n){var i=this._$handlers;if(!e||!t)return this;i[t]||(i[t]=[]);for(var r=0;r<i[t].length;r++)if(i[t][r].h===e)return this;return i[t].push({h:e,one:!0,ctx:n||this}),this},on:function(t,e,n){var i=this._$handlers;if(!e||!t)return this;i[t]||(i[t]=[]);for(var r=0;r<i[t].length;r++)if(i[t][r].h===e)return this;return i[t].push({h:e,one:!1,ctx:n||this}),this},isSilent:function(t){var e=this._$handlers;return e[t]&&e[t].length},off:function(t,e){var n=this._$handlers;if(!t)return this._$handlers={},this;if(e){if(n[t]){for(var i=[],r=0,o=n[t].length;r<o;r++)n[t][r].h!=e&&i.push(n[t][r]);n[t]=i}n[t]&&0===n[t].length&&delete n[t]}else delete n[t];return this},trigger:function(t){if(this._$handlers[t]){var e=arguments,n=e.length;n>3&&(e=zc.call(e,1));for(var i=this._$handlers[t],r=i.length,o=0;o<r;){switch(n){case 1:i[o].h.call(i[o].ctx);break;case 2:i[o].h.call(i[o].ctx,e[1]);break;case 3:i[o].h.call(i[o].ctx,e[1],e[2]);break;default:i[o].h.apply(i[o].ctx,e)}i[o].one?(i.splice(o,1),r--):o++}}return this},triggerWithContext:function(t){if(this._$handlers[t]){var e=arguments,n=e.length;n>4&&(e=zc.call(e,1,e.length-1));for(var i=e[e.length-1],r=this._$handlers[t],o=r.length,a=0;a<o;){switch(n){case 1:r[a].h.call(i);break;case 2:r[a].h.call(i,e[1]);break;case 3:r[a].h.call(i,e[1],e[2]);break;default:r[a].h.apply(i,e)}r[a].one?(r.splice(a,1),o--):a++}}return this}};var Bc="silent";et.prototype.dispose=function(){};var $c=["click","dblclick","mousewheel","mouseout","mouseup","mousedown","mousemove","contextmenu"],Hc=function(t,e,n,i){Fc.call(this),this.storage=t,this.painter=e,this.painterRoot=i,n=n||new et,this.proxy=null,this._hovered={},this._lastTouchMoment,this._lastX,this._lastY,J.call(this),this.setHandlerProxy(n)};Hc.prototype={constructor:Hc,setHandlerProxy:function(t){this.proxy&&this.proxy.dispose(),t&&(p($c,function(e){t.on&&t.on(e,this[e],this)},this),t.handler=this),this.proxy=t},mousemove:function(t){var e=t.zrX,n=t.zrY,i=this._hovered,r=i.target;r&&!r.__zr&&(r=(i=this.findHover(i.x,i.y)).target);var o=this._hovered=this.findHover(e,n),a=o.target,s=this.proxy;s.setCursor&&s.setCursor(a?a.cursor:"default"),r&&a!==r&&this.dispatchToElement(i,"mouseout",t),this.dispatchToElement(o,"mousemove",t),a&&a!==r&&this.dispatchToElement(o,"mouseover",t)},mouseout:function(t){this.dispatchToElement(this._hovered,"mouseout",t);var e,n=t.toElement||t.relatedTarget;do{n=n&&n.parentNode}while(n&&9!=n.nodeType&&!(e=n===this.painterRoot));!e&&this.trigger("globalout",{event:t})},resize:function(t){this._hovered={}},dispatch:function(t,e){var n=this[t];n&&n.call(this,e)},dispose:function(){this.proxy.dispose(),this.storage=this.proxy=this.painter=null},setCursorStyle:function(t){var e=this.proxy;e.setCursor&&e.setCursor(t)},dispatchToElement:function(t,e,n){var i=(t=t||{}).target;if(!i||!i.silent){for(var r="on"+e,o=tt(e,t,n);i&&(i[r]&&(o.cancelBubble=i[r].call(i,o)),i.trigger(e,o),i=i.parent,!o.cancelBubble););o.cancelBubble||(this.trigger(e,o),this.painter&&this.painter.eachOtherLayer(function(t){"function"==typeof t[r]&&t[r].call(t,o),t.trigger&&t.trigger(e,o)}))}},findHover:function(t,e,n){for(var i=this.storage.getDisplayList(),r={x:t,y:e},o=i.length-1;o>=0;o--){var a;if(i[o]!==n&&!i[o].ignore&&(a=nt(i[o],t,e))&&(!r.topTarget&&(r.topTarget=i[o]),a!==Bc)){r.target=i[o];break}}return r}},p(["click","mousedown","mouseup","mousewheel","dblclick","contextmenu"],function(t){Hc.prototype[t]=function(e){var n=this.findHover(e.zrX,e.zrY),i=n.target;if("mousedown"===t)this._downEl=i,this._downPoint=[e.zrX,e.zrY],this._upEl=i;else if("mouseup"===t)this._upEl=i;else if("click"===t){if(this._downEl!==this._upEl||!this._downPoint||Rc(this._downPoint,[e.zrX,e.zrY])>4)return;this._downPoint=null}this.dispatchToElement(n,t,e)}}),f(Hc,Fc),f(Hc,J);var Wc="undefined"==typeof Float32Array?Array:Float32Array,Vc=rt,jc=5e-5,Gc=function(t){(t=t||{}).position||(this.position=[0,0]),null==t.rotation&&(this.rotation=0),t.scale||(this.scale=[1,1]),this.origin=this.origin||null},Uc=Gc.prototype;Uc.transform=null,Uc.needLocalTransform=function(){return ht(this.rotation)||ht(this.position[0])||ht(this.position[1])||ht(this.scale[0]-1)||ht(this.scale[1]-1)},Uc.updateTransform=function(){var t=this.parent,e=t&&t.transform,n=this.needLocalTransform(),i=this.transform;n||e?(i=i||it(),n?this.getLocalTransform(i):Vc(i),e&&(n?at(i,t.transform,i):ot(i,t.transform)),this.transform=i,this.invTransform=this.invTransform||it(),ut(this.invTransform,i)):i&&Vc(i)},Uc.getLocalTransform=function(t){return Gc.getLocalTransform(this,t)},Uc.setTransform=function(t){var e=this.transform,n=t.dpr||1;e?t.setTransform(n*e[0],n*e[1],n*e[2],n*e[3],n*e[4],n*e[5]):t.setTransform(n,0,0,n,0,0)},Uc.restoreTransform=function(t){var e=t.dpr||1;t.setTransform(e,0,0,e,0,0)};var Xc=[];Uc.decomposeTransform=function(){if(this.transform){var t=this.parent,e=this.transform;t&&t.transform&&(at(Xc,t.invTransform,e),e=Xc);var n=e[0]*e[0]+e[1]*e[1],i=e[2]*e[2]+e[3]*e[3],r=this.position,o=this.scale;ht(n-1)&&(n=Math.sqrt(n)),ht(i-1)&&(i=Math.sqrt(i)),e[0]<0&&(n=-n),e[3]<0&&(i=-i),r[0]=e[4],r[1]=e[5],o[0]=n,o[1]=i,this.rotation=Math.atan2(-e[1]/i,e[0]/n)}},Uc.getGlobalScale=function(){var t=this.transform;if(!t)return[1,1];var e=Math.sqrt(t[0]*t[0]+t[1]*t[1]),n=Math.sqrt(t[2]*t[2]+t[3]*t[3]);return t[0]<0&&(e=-e),t[3]<0&&(n=-n),[e,n]},Uc.transformCoordToLocal=function(t,e){var n=[t,e],i=this.invTransform;return i&&Y(n,n,i),n},Uc.transformCoordToGlobal=function(t,e){var n=[t,e],i=this.transform;return i&&Y(n,n,i),n},Gc.getLocalTransform=function(t,e){Vc(e=e||[]);var n=t.origin,i=t.scale||[1,1],r=t.rotation||0,o=t.position||[0,0];return n&&(e[4]-=n[0],e[5]-=n[1]),ct(e,e,i),r&&lt(e,e,r),n&&(e[4]+=n[0],e[5]+=n[1]),e[4]+=o[0],e[5]+=o[1],e};var qc={linear:function(t){return t},quadraticIn:function(t){return t*t},quadraticOut:function(t){return t*(2-t)},quadraticInOut:function(t){return(t*=2)<1?.5*t*t:-.5*(--t*(t-2)-1)},cubicIn:function(t){return t*t*t},cubicOut:function(t){return--t*t*t+1},cubicInOut:function(t){return(t*=2)<1?.5*t*t*t:.5*((t-=2)*t*t+2)},quarticIn:function(t){return t*t*t*t},quarticOut:function(t){return 1- --t*t*t*t},quarticInOut:function(t){return(t*=2)<1?.5*t*t*t*t:-.5*((t-=2)*t*t*t-2)},quinticIn:function(t){return t*t*t*t*t},quinticOut:function(t){return--t*t*t*t*t+1},quinticInOut:function(t){return(t*=2)<1?.5*t*t*t*t*t:.5*((t-=2)*t*t*t*t+2)},sinusoidalIn:function(t){return 1-Math.cos(t*Math.PI/2)},sinusoidalOut:function(t){return Math.sin(t*Math.PI/2)},sinusoidalInOut:function(t){return.5*(1-Math.cos(Math.PI*t))},exponentialIn:function(t){return 0===t?0:Math.pow(1024,t-1)},exponentialOut:function(t){return 1===t?1:1-Math.pow(2,-10*t)},exponentialInOut:function(t){return 0===t?0:1===t?1:(t*=2)<1?.5*Math.pow(1024,t-1):.5*(2-Math.pow(2,-10*(t-1)))},circularIn:function(t){return 1-Math.sqrt(1-t*t)},circularOut:function(t){return Math.sqrt(1- --t*t)},circularInOut:function(t){return(t*=2)<1?-.5*(Math.sqrt(1-t*t)-1):.5*(Math.sqrt(1-(t-=2)*t)+1)},elasticIn:function(t){var e,n=.1;return 0===t?0:1===t?1:(!n||n<1?(n=1,e=.1):e=.4*Math.asin(1/n)/(2*Math.PI),-n*Math.pow(2,10*(t-=1))*Math.sin((t-e)*(2*Math.PI)/.4))},elasticOut:function(t){var e,n=.1;return 0===t?0:1===t?1:(!n||n<1?(n=1,e=.1):e=.4*Math.asin(1/n)/(2*Math.PI),n*Math.pow(2,-10*t)*Math.sin((t-e)*(2*Math.PI)/.4)+1)},elasticInOut:function(t){var e,n=.1;return 0===t?0:1===t?1:(!n||n<1?(n=1,e=.1):e=.4*Math.asin(1/n)/(2*Math.PI),(t*=2)<1?n*Math.pow(2,10*(t-=1))*Math.sin((t-e)*(2*Math.PI)/.4)*-.5:n*Math.pow(2,-10*(t-=1))*Math.sin((t-e)*(2*Math.PI)/.4)*.5+1)},backIn:function(t){var e=1.70158;return t*t*((e+1)*t-e)},backOut:function(t){var e=1.70158;return--t*t*((e+1)*t+e)+1},backInOut:function(t){var e=2.5949095;return(t*=2)<1?t*t*((e+1)*t-e)*.5:.5*((t-=2)*t*((e+1)*t+e)+2)},bounceIn:function(t){return 1-qc.bounceOut(1-t)},bounceOut:function(t){return t<1/2.75?7.5625*t*t:t<2/2.75?7.5625*(t-=1.5/2.75)*t+.75:t<2.5/2.75?7.5625*(t-=2.25/2.75)*t+.9375:7.5625*(t-=2.625/2.75)*t+.984375},bounceInOut:function(t){return t<.5?.5*qc.bounceIn(2*t):.5*qc.bounceOut(2*t-1)+.5}};ft.prototype={constructor:ft,step:function(t,e){if(this._initialized||(this._startTime=t+this._delay,this._initialized=!0),this._paused)this._pausedTime+=e;else{var n=(t-this._startTime-this._pausedTime)/this._life;if(!(n<0)){n=Math.min(n,1);var i=this.easing,r="string"==typeof i?qc[i]:i,o="function"==typeof r?r(n):n;return this.fire("frame",o),1==n?this.loop?(this.restart(t),"restart"):(this._needsRemove=!0,"destroy"):null}}},restart:function(t){var e=(t-this._startTime-this._pausedTime)%this._life;this._startTime=t-e+this.gap,this._pausedTime=0,this._needsRemove=!1},fire:function(t,e){this[t="on"+t]&&this[t](this._target,e)},pause:function(){this._paused=!0},resume:function(){this._paused=!1}};var Yc=function(){this.head=null,this.tail=null,this._len=0},Zc=Yc.prototype;Zc.insert=function(t){var e=new Kc(t);return this.insertEntry(e),e},Zc.insertEntry=function(t){this.head?(this.tail.next=t,t.prev=this.tail,t.next=null,this.tail=t):this.head=this.tail=t,this._len++},Zc.remove=function(t){var e=t.prev,n=t.next;e?e.next=n:this.head=n,n?n.prev=e:this.tail=e,t.next=t.prev=null,this._len--},Zc.len=function(){return this._len},Zc.clear=function(){this.head=this.tail=null,this._len=0};var Kc=function(t){this.value=t,this.next,this.prev},Jc=function(t){this._list=new Yc,this._map={},this._maxSize=t||10,this._lastRemovedEntry=null},Qc=Jc.prototype;Qc.put=function(t,e){var n=this._list,i=this._map,r=null;if(null==i[t]){var o=n.len(),a=this._lastRemovedEntry;if(o>=this._maxSize&&o>0){var s=n.head;n.remove(s),delete i[s.key],r=s.value,this._lastRemovedEntry=s}a?a.value=e:a=new Kc(e),a.key=t,n.insertEntry(a),i[t]=a}return r},Qc.get=function(t){var e=this._map[t],n=this._list;if(null!=e)return e!==n.tail&&(n.remove(e),n.insertEntry(e)),e.value},Qc.clear=function(){this._list.clear(),this._map={}};var tu={transparent:[0,0,0,0],aliceblue:[240,248,255,1],antiquewhite:[250,235,215,1],aqua:[0,255,255,1],aquamarine:[127,255,212,1],azure:[240,255,255,1],beige:[245,245,220,1],bisque:[255,228,196,1],black:[0,0,0,1],blanchedalmond:[255,235,205,1],blue:[0,0,255,1],blueviolet:[138,43,226,1],brown:[165,42,42,1],burlywood:[222,184,135,1],cadetblue:[95,158,160,1],chartreuse:[127,255,0,1],chocolate:[210,105,30,1],coral:[255,127,80,1],cornflowerblue:[100,149,237,1],cornsilk:[255,248,220,1],crimson:[220,20,60,1],cyan:[0,255,255,1],darkblue:[0,0,139,1],darkcyan:[0,139,139,1],darkgoldenrod:[184,134,11,1],darkgray:[169,169,169,1],darkgreen:[0,100,0,1],darkgrey:[169,169,169,1],darkkhaki:[189,183,107,1],darkmagenta:[139,0,139,1],darkolivegreen:[85,107,47,1],darkorange:[255,140,0,1],darkorchid:[153,50,204,1],darkred:[139,0,0,1],darksalmon:[233,150,122,1],darkseagreen:[143,188,143,1],darkslateblue:[72,61,139,1],darkslategray:[47,79,79,1],darkslategrey:[47,79,79,1],darkturquoise:[0,206,209,1],darkviolet:[148,0,211,1],deeppink:[255,20,147,1],deepskyblue:[0,191,255,1],dimgray:[105,105,105,1],dimgrey:[105,105,105,1],dodgerblue:[30,144,255,1],firebrick:[178,34,34,1],floralwhite:[255,250,240,1],forestgreen:[34,139,34,1],fuchsia:[255,0,255,1],gainsboro:[220,220,220,1],ghostwhite:[248,248,255,1],gold:[255,215,0,1],goldenrod:[218,165,32,1],gray:[128,128,128,1],green:[0,128,0,1],greenyellow:[173,255,47,1],grey:[128,128,128,1],honeydew:[240,255,240,1],hotpink:[255,105,180,1],indianred:[205,92,92,1],indigo:[75,0,130,1],ivory:[255,255,240,1],khaki:[240,230,140,1],lavender:[230,230,250,1],lavenderblush:[255,240,245,1],lawngreen:[124,252,0,1],lemonchiffon:[255,250,205,1],lightblue:[173,216,230,1],lightcoral:[240,128,128,1],lightcyan:[224,255,255,1],lightgoldenrodyellow:[250,250,210,1],lightgray:[211,211,211,1],lightgreen:[144,238,144,1],lightgrey:[211,211,211,1],lightpink:[255,182,193,1],lightsalmon:[255,160,122,1],lightseagreen:[32,178,170,1],lightskyblue:[135,206,250,1],lightslategray:[119,136,153,1],lightslategrey:[119,136,153,1],lightsteelblue:[176,196,222,1],lightyellow:[255,255,224,1],lime:[0,255,0,1],limegreen:[50,205,50,1],linen:[250,240,230,1],magenta:[255,0,255,1],maroon:[128,0,0,1],mediumaquamarine:[102,205,170,1],mediumblue:[0,0,205,1],mediumorchid:[186,85,211,1],mediumpurple:[147,112,219,1],mediumseagreen:[60,179,113,1],mediumslateblue:[123,104,238,1],mediumspringgreen:[0,250,154,1],mediumturquoise:[72,209,204,1],mediumvioletred:[199,21,133,1],midnightblue:[25,25,112,1],mintcream:[245,255,250,1],mistyrose:[255,228,225,1],moccasin:[255,228,181,1],navajowhite:[255,222,173,1],navy:[0,0,128,1],oldlace:[253,245,230,1],olive:[128,128,0,1],olivedrab:[107,142,35,1],orange:[255,165,0,1],orangered:[255,69,0,1],orchid:[218,112,214,1],palegoldenrod:[238,232,170,1],palegreen:[152,251,152,1],paleturquoise:[175,238,238,1],palevioletred:[219,112,147,1],papayawhip:[255,239,213,1],peachpuff:[255,218,185,1],peru:[205,133,63,1],pink:[255,192,203,1],plum:[221,160,221,1],powderblue:[176,224,230,1],purple:[128,0,128,1],red:[255,0,0,1],rosybrown:[188,143,143,1],royalblue:[65,105,225,1],saddlebrown:[139,69,19,1],salmon:[250,128,114,1],sandybrown:[244,164,96,1],seagreen:[46,139,87,1],seashell:[255,245,238,1],sienna:[160,82,45,1],silver:[192,192,192,1],skyblue:[135,206,235,1],slateblue:[106,90,205,1],slategray:[112,128,144,1],slategrey:[112,128,144,1],snow:[255,250,250,1],springgreen:[0,255,127,1],steelblue:[70,130,180,1],tan:[210,180,140,1],teal:[0,128,128,1],thistle:[216,191,216,1],tomato:[255,99,71,1],turquoise:[64,224,208,1],violet:[238,130,238,1],wheat:[245,222,179,1],white:[255,255,255,1],whitesmoke:[245,245,245,1],yellow:[255,255,0,1],yellowgreen:[154,205,50,1]},eu=new Jc(20),nu=null,iu=Array.prototype.slice,ru=function(t,e,n,i){this._tracks={},this._target=t,this._loop=e||!1,this._getter=n||At,this._setter=i||Tt,this._clipCount=0,this._delay=0,this._doneList=[],this._onframeList=[],this._clipList=[]};ru.prototype={when:function(t,e){var n=this._tracks;for(var i in e)if(e.hasOwnProperty(i)){if(!n[i]){n[i]=[];var r=this._getter(this._target,i);if(null==r)continue;0!==t&&n[i].push({time:0,value:Et(r)})}n[i].push({time:t,value:e[i]})}return this},during:function(t){return this._onframeList.push(t),this},pause:function(){for(var t=0;t<this._clipList.length;t++)this._clipList[t].pause();this._paused=!0},resume:function(){for(var t=0;t<this._clipList.length;t++)this._clipList[t].resume();this._paused=!1},isPaused:function(){return!!this._paused},_doneCallback:function(){this._tracks={},this._clipList.length=0;for(var t=this._doneList,e=t.length,n=0;n<e;n++)t[n].call(this)},start:function(t,e){var n,i=this,r=0;for(var o in this._tracks)if(this._tracks.hasOwnProperty(o)){var a=zt(this,t,function(){--r||i._doneCallback()},this._tracks[o],o,e);a&&(this._clipList.push(a),r++,this.animation&&this.animation.addClip(a),n=a)}if(n){var s=n.onframe;n.onframe=function(t,e){s(t,e);for(var n=0;n<i._onframeList.length;n++)i._onframeList[n](t,e)}}return r||this._doneCallback(),this},stop:function(t){for(var e=this._clipList,n=this.animation,i=0;i<e.length;i++){var r=e[i];t&&r.onframe(this._target,1),n&&n.removeClip(r)}e.length=0},delay:function(t){return this._delay=t,this},done:function(t){return t&&this._doneList.push(t),this},getClips:function(){return this._clipList}};var ou=1;"undefined"!=typeof e&&(ou=Math.max(e.devicePixelRatio||1,1));var au=ou,su=function(){},lu=su,cu=function(){this.animators=[]};cu.prototype={constructor:cu,animate:function(t,e){var n,i=!1,r=this,o=this.__zr;if(t){var a=t.split("."),s=r;i="shape"===a[0];for(var l=0,c=a.length;l<c;l++)s&&(s=s[a[l]]);s&&(n=s)}else n=r;if(n){var h=r.animators,f=new ru(n,e);return f.during(function(t){r.dirty(i)}).done(function(){h.splice(u(h,f),1)}),h.push(f),o&&o.animation.addAnimator(f),f}lu('Property "'+t+'" is not existed in element '+r.id)},stopAnimation:function(t){for(var e=this.animators,n=e.length,i=0;i<n;i++)e[i].stop(t);return e.length=0,this},animateTo:function(t,e,n,i,r,o){w(n)?(r=i,i=n,n=0):b(i)?(r=i,i="linear",n=0):b(n)?(r=n,n=0):b(e)?(r=e,e=500):e||(e=500),this.stopAnimation(),this._animateToShallow("",this,t,e,n);var a=this.animators.slice(),s=a.length;s||r&&r();for(var l=0;l<a.length;l++)a[l].done(function(){--s||r&&r()}).start(i,o)},_animateToShallow:function(t,e,n,i,r){var o={},a=0;for(var s in n)if(n.hasOwnProperty(s))if(null!=e[s])S(n[s])&&!d(n[s])?this._animateToShallow(t?t+"."+s:s,e[s],n[s],i,r):(o[s]=n[s],a++);else if(null!=n[s])if(t){var l={};l[t]={},l[t][s]=n[s],this.attr(l)}else this.attr(s,n[s]);return a>0&&this.animate(t,!1).when(null==i?500:i,o).delay(r||0),this}};var uu=function(t){Gc.call(this,t),Fc.call(this,t),cu.call(this,t),this.id=t.id||_c()};uu.prototype={type:"element",name:"",__zr:null,ignore:!1,clipPath:null,isGroup:!1,drift:function(t,e){switch(this.draggable){case"horizontal":e=0;break;case"vertical":t=0}var n=this.transform;n||(n=this.transform=[1,0,0,1,0,0]),n[4]+=t,n[5]+=e,this.decomposeTransform(),this.dirty(!1)},beforeUpdate:function(){},afterUpdate:function(){},update:function(){this.updateTransform()},traverse:function(t,e){},attrKV:function(t,e){if("position"===t||"scale"===t||"origin"===t){if(e){var n=this[t];n||(n=this[t]=[]),n[0]=e[0],n[1]=e[1]}}else this[t]=e},hide:function(){this.ignore=!0,this.__zr&&this.__zr.refresh()},show:function(){this.ignore=!1,this.__zr&&this.__zr.refresh()},attr:function(t,e){if("string"==typeof t)this.attrKV(t,e);else if(S(t))for(var n in t)t.hasOwnProperty(n)&&this.attrKV(n,t[n]);return this.dirty(!1),this},setClipPath:function(t){var e=this.__zr;e&&t.addSelfToZr(e),this.clipPath&&this.clipPath!==t&&this.removeClipPath(),this.clipPath=t,t.__zr=e,t.__clipTarget=this,this.dirty(!1)},removeClipPath:function(){var t=this.clipPath;t&&(t.__zr&&t.removeSelfFromZr(t.__zr),t.__zr=null,t.__clipTarget=null,this.clipPath=null,this.dirty(!1))},addSelfToZr:function(t){this.__zr=t;var e=this.animators;if(e)for(var n=0;n<e.length;n++)t.animation.addAnimator(e[n]);this.clipPath&&this.clipPath.addSelfToZr(t)},removeSelfFromZr:function(t){this.__zr=null;var e=this.animators;if(e)for(var n=0;n<e.length;n++)t.animation.removeAnimator(e[n]);this.clipPath&&this.clipPath.removeSelfFromZr(t)}},f(uu,cu),f(uu,Gc),f(uu,Fc);var hu=Y,fu=Math.min,du=Math.max;Ft.prototype={constructor:Ft,union:function(t){var e=fu(t.x,this.x),n=fu(t.y,this.y);this.width=du(t.x+t.width,this.x+this.width)-e,this.height=du(t.y+t.height,this.y+this.height)-n,this.x=e,this.y=n},applyTransform:function(){var t=[],e=[],n=[],i=[];return function(r){if(r){t[0]=n[0]=this.x,t[1]=i[1]=this.y,e[0]=i[0]=this.x+this.width,e[1]=n[1]=this.y+this.height,hu(t,t,r),hu(e,e,r),hu(n,n,r),hu(i,i,r),this.x=fu(t[0],e[0],n[0],i[0]),this.y=fu(t[1],e[1],n[1],i[1]);var o=du(t[0],e[0],n[0],i[0]),a=du(t[1],e[1],n[1],i[1]);this.width=o-this.x,this.height=a-this.y}}}(),calculateTransform:function(t){var e=this,n=t.width/e.width,i=t.height/e.height,r=it();return st(r,r,[-e.x,-e.y]),ct(r,r,[n,i]),st(r,r,[t.x,t.y]),r},intersect:function(t){if(!t)return!1;t instanceof Ft||(t=Ft.create(t));var e=this,n=e.x,i=e.x+e.width,r=e.y,o=e.y+e.height,a=t.x,s=t.x+t.width,l=t.y,c=t.y+t.height;return!(i<a||s<n||o<l||c<r)},contain:function(t,e){var n=this;return t>=n.x&&t<=n.x+n.width&&e>=n.y&&e<=n.y+n.height},clone:function(){return new Ft(this.x,this.y,this.width,this.height)},copy:function(t){this.x=t.x,this.y=t.y,this.width=t.width,this.height=t.height},plain:function(){return{x:this.x,y:this.y,width:this.width,height:this.height}}},Ft.create=function(t){return new Ft(t.x,t.y,t.width,t.height)};var pu=function(t){for(var e in t=t||{},uu.call(this,t),t)t.hasOwnProperty(e)&&(this[e]=t[e]);this._children=[],this.__storage=null,this.__dirty=!0};pu.prototype={constructor:pu,isGroup:!0,type:"group",silent:!1,children:function(){return this._children.slice()},childAt:function(t){return this._children[t]},childOfName:function(t){for(var e=this._children,n=0;n<e.length;n++)if(e[n].name===t)return e[n]},childCount:function(){return this._children.length},add:function(t){return t&&t!==this&&t.parent!==this&&(this._children.push(t),this._doAdd(t)),this},addBefore:function(t,e){if(t&&t!==this&&t.parent!==this&&e&&e.parent===this){var n=this._children,i=n.indexOf(e);i>=0&&(n.splice(i,0,t),this._doAdd(t))}return this},_doAdd:function(t){t.parent&&t.parent.remove(t),t.parent=this;var e=this.__storage,n=this.__zr;e&&e!==t.__storage&&(e.addToStorage(t),t instanceof pu&&t.addChildrenToStorage(e)),n&&n.refresh()},remove:function(t){var e=this.__zr,n=this.__storage,i=this._children,r=u(i,t);return r<0?this:(i.splice(r,1),t.parent=null,n&&(n.delFromStorage(t),t instanceof pu&&t.delChildrenFromStorage(n)),e&&e.refresh(),this)},removeAll:function(){var t,e,n=this._children,i=this.__storage;for(e=0;e<n.length;e++)t=n[e],i&&(i.delFromStorage(t),t instanceof pu&&t.delChildrenFromStorage(i)),t.parent=null;return n.length=0,this},eachChild:function(t,e){for(var n=this._children,i=0;i<n.length;i++){var r=n[i];t.call(e,r,i)}return this},traverse:function(t,e){for(var n=0;n<this._children.length;n++){var i=this._children[n];t.call(e,i),"group"===i.type&&i.traverse(t,e)}return this},addChildrenToStorage:function(t){for(var e=0;e<this._children.length;e++){var n=this._children[e];t.addToStorage(n),n instanceof pu&&n.addChildrenToStorage(t)}},delChildrenFromStorage:function(t){for(var e=0;e<this._children.length;e++){var n=this._children[e];t.delFromStorage(n),n instanceof pu&&n.delChildrenFromStorage(t)}},dirty:function(){return this.__dirty=!0,this.__zr&&this.__zr.refresh(),this},getBoundingRect:function(t){for(var e=null,n=new Ft(0,0,0,0),i=t||this._children,r=[],o=0;o<i.length;o++){var a=i[o];if(!a.ignore&&!a.invisible){var s=a.getBoundingRect(),l=a.getLocalTransform(r);l?(n.copy(s),n.applyTransform(l),(e=e||n.clone()).union(n)):(e=e||s.clone()).union(s)}}return e||n}},h(pu,uu);var gu=32,vu=7,mu=function(){this._roots=[],this._displayList=[],this._displayListLen=0};mu.prototype={constructor:mu,traverse:function(t,e){for(var n=0;n<this._roots.length;n++)this._roots[n].traverse(t,e)},getDisplayList:function(t,e){return e=e||!1,t&&this.updateDisplayList(e),this._displayList},updateDisplayList:function(t){this._displayListLen=0;for(var e=this._roots,n=this._displayList,i=0,r=e.length;i<r;i++)this._updateAndAddDisplayable(e[i],null,t);n.length=this._displayListLen,xc.canvasSupported&&Ut(n,Xt)},_updateAndAddDisplayable:function(t,e,n){if(!t.ignore||n){t.beforeUpdate(),t.__dirty&&t.update(),t.afterUpdate();var i=t.clipPath;if(i){e=e?e.slice():[];for(var r=i,o=t;r;)r.parent=o,r.updateTransform(),e.push(r),o=r,r=r.clipPath}if(t.isGroup){for(var a=t._children,s=0;s<a.length;s++){var l=a[s];t.__dirty&&(l.__dirty=!0),this._updateAndAddDisplayable(l,e,n)}t.__dirty=!1}else t.__clipPaths=e,this._displayList[this._displayListLen++]=t}},addRoot:function(t){t.__storage!==this&&(t instanceof pu&&t.addChildrenToStorage(this),this.addToStorage(t),this._roots.push(t))},delRoot:function(t){if(null==t){for(n=0;n<this._roots.length;n++){var e=this._roots[n];e instanceof pu&&e.delChildrenFromStorage(this)}return this._roots=[],this._displayList=[],void(this._displayListLen=0)}if(t instanceof Array)for(var n=0,i=t.length;n<i;n++)this.delRoot(t[n]);else{var r=u(this._roots,t);r>=0&&(this.delFromStorage(t),this._roots.splice(r,1),t instanceof pu&&t.delChildrenFromStorage(this))}},addToStorage:function(t){return t&&(t.__storage=this,t.dirty(!1)),this},delFromStorage:function(t){return t&&(t.__storage=null),this},dispose:function(){this._renderList=this._roots=null},displayableSortFunc:Xt};var yu={shadowBlur:1,shadowOffsetX:1,shadowOffsetY:1,textShadowBlur:1,textShadowOffsetX:1,textShadowOffsetY:1,textBoxShadowBlur:1,textBoxShadowOffsetX:1,textBoxShadowOffsetY:1},_u=function(t,e,n){return yu.hasOwnProperty(e)?n*=t.dpr:n},xu=[["shadowBlur",0],["shadowOffsetX",0],["shadowOffsetY",0],["shadowColor","#000"],["lineCap","butt"],["lineJoin","miter"],["miterLimit",10]],bu=function(t,e){this.extendFrom(t,!1),this.host=e};bu.prototype={constructor:bu,host:null,fill:"#000",stroke:null,opacity:1,lineDash:null,lineDashOffset:0,shadowBlur:0,shadowOffsetX:0,shadowOffsetY:0,lineWidth:1,strokeNoScale:!1,text:null,font:null,textFont:null,fontStyle:null,fontWeight:null,fontSize:null,fontFamily:null,textTag:null,textFill:"#000",textStroke:null,textWidth:null,textHeight:null,textStrokeWidth:0,textLineHeight:null,textPosition:"inside",textRect:null,textOffset:null,textAlign:null,textVerticalAlign:null,textDistance:5,textShadowColor:"transparent",textShadowBlur:0,textShadowOffsetX:0,textShadowOffsetY:0,textBoxShadowColor:"transparent",textBoxShadowBlur:0,textBoxShadowOffsetX:0,textBoxShadowOffsetY:0,transformText:!1,textRotation:0,textOrigin:null,textBackgroundColor:null,textBorderColor:null,textBorderWidth:0,textBorderRadius:0,textPadding:null,rich:null,truncate:null,blend:null,bind:function(t,e,n){for(var i=this,r=n&&n.style,o=!r,a=0;a<xu.length;a++){var s=xu[a],l=s[0];(o||i[l]!==r[l])&&(t[l]=_u(t,l,i[l]||s[1]))}if((o||i.fill!==r.fill)&&(t.fillStyle=i.fill),(o||i.stroke!==r.stroke)&&(t.strokeStyle=i.stroke),(o||i.opacity!==r.opacity)&&(t.globalAlpha=null==i.opacity?1:i.opacity),(o||i.blend!==r.blend)&&(t.globalCompositeOperation=i.blend||"source-over"),this.hasStroke()){var c=i.lineWidth;t.lineWidth=c/(this.strokeNoScale&&e&&e.getLineScale?e.getLineScale():1)}},hasFill:function(){var t=this.fill;return null!=t&&"none"!==t},hasStroke:function(){var t=this.stroke;return null!=t&&"none"!==t&&this.lineWidth>0},extendFrom:function(t,e){if(t)for(var n in t)!t.hasOwnProperty(n)||!0!==e&&(!1===e?this.hasOwnProperty(n):null==t[n])||(this[n]=t[n])},set:function(t,e){"string"==typeof t?this[t]=e:this.extendFrom(t,!0)},clone:function(){var t=new this.constructor;return t.extendFrom(this,!0),t},getGradient:function(t,e,n){for(var i=("radial"===e.type?Yt:qt)(t,e,n),r=e.colorStops,o=0;o<r.length;o++)i.addColorStop(r[o].offset,r[o].color);return i}};for(var wu=bu.prototype,Su=0;Su<xu.length;Su++){var Mu=xu[Su];Mu[0]in wu||(wu[Mu[0]]=Mu[1])}bu.getGradient=wu.getGradient;var Au=function(t,e){this.image=t,this.repeat=e,this.type="pattern"};Au.prototype.getCanvasPattern=function(t){return t.createPattern(this.image,this.repeat||"repeat")};var Tu=function(t,e,n){var i;n=n||au,"string"==typeof t?i=Kt(t,e,n):S(t)&&(t=(i=t).id),this.id=t,this.dom=i;var r=i.style;r&&(i.onselectstart=Zt,r["-webkit-user-select"]="none",r["user-select"]="none",r["-webkit-touch-callout"]="none",r["-webkit-tap-highlight-color"]="rgba(0,0,0,0)",r.padding=0,r.margin=0,r["border-width"]=0),this.domBack=null,this.ctxBack=null,this.painter=e,this.config=null,this.clearColor=0,this.motionBlur=!1,this.lastFrameAlpha=.7,this.dpr=n};Tu.prototype={constructor:Tu,__dirty:!0,__used:!1,__drawIndex:0,__startIndex:0,__endIndex:0,incremental:!1,getElementCount:function(){return this.__endIndex-this.__startIndex},initContext:function(){this.ctx=this.dom.getContext("2d"),this.ctx.dpr=this.dpr},createBackBuffer:function(){var t=this.dpr;this.domBack=Kt("back-"+this.id,this.painter,t),this.ctxBack=this.domBack.getContext("2d"),1!=t&&this.ctxBack.scale(t,t)},resize:function(t,e){var n=this.dpr,i=this.dom,r=i.style,o=this.domBack;r&&(r.width=t+"px",r.height=e+"px"),i.width=t*n,i.height=e*n,o&&(o.width=t*n,o.height=e*n,1!=n&&this.ctxBack.scale(n,n))},clear:function(t,e){var n,i=this.dom,r=this.ctx,o=i.width,a=i.height,s=(e=e||this.clearColor,this.motionBlur&&!t),l=this.lastFrameAlpha,c=this.dpr;(s&&(this.domBack||this.createBackBuffer(),this.ctxBack.globalCompositeOperation="copy",this.ctxBack.drawImage(i,0,0,o/c,a/c)),r.clearRect(0,0,o,a),e&&"transparent"!==e)&&(e.colorStops?(n=e.__canvasGradient||bu.getGradient(r,e,{x:0,y:0,width:o,height:a}),e.__canvasGradient=n):e.image&&(n=Au.prototype.getCanvasPattern.call(e,r)),r.save(),r.fillStyle=n||e,r.fillRect(0,0,o,a),r.restore());if(s){var u=this.domBack;r.save(),r.globalAlpha=l,r.drawImage(u,0,0,o,a),r.restore()}}};var ku="undefined"!=typeof e&&(e.requestAnimationFrame&&e.requestAnimationFrame.bind(e)||e.msRequestAnimationFrame&&e.msRequestAnimationFrame.bind(e)||e.mozRequestAnimationFrame||e.webkitRequestAnimationFrame)||function(t){setTimeout(t,16)},Pu=new Jc(50),Cu={},Iu=0,Du=5e3,Lu=/\{([a-zA-Z0-9_]+)\|([^}]*)\}/g,Ou="12px sans-serif",Eu={measureText:function(t,e){var n=c();return n.font=e||Ou,n.measureText(t)}},Ru={left:1,right:1,center:1},Nu={top:1,bottom:1,middle:1},zu=new Ft,Fu=function(){};Fu.prototype={constructor:Fu,drawRectText:function(t,e){var n=this.style;e=n.textRect||e,this.__dirty&&xe(n);var i=n.text;if(null!=i&&(i+=""),ze(i,n)){t.save();var r=this.transform;n.transformText?this.setTransform(t):r&&(zu.copy(e),zu.applyTransform(r),e=zu),we(this,t,i,n,e),t.restore()}}},Fe.prototype={constructor:Fe,type:"displayable",__dirty:!0,invisible:!1,z:0,z2:0,zlevel:0,draggable:!1,dragging:!1,silent:!1,culling:!1,cursor:"pointer",rectHover:!1,progressive:!1,incremental:!1,inplace:!1,beforeBrush:function(t){},afterBrush:function(t){},brush:function(t,e){},getBoundingRect:function(){},contain:function(t,e){return this.rectContain(t,e)},traverse:function(t,e){t.call(e,this)},rectContain:function(t,e){var n=this.transformCoordToLocal(t,e);return this.getBoundingRect().contain(n[0],n[1])},dirty:function(){this.__dirty=!0,this._rect=null,this.__zr&&this.__zr.refresh()},animateStyle:function(t){return this.animate("style",t)},attrKV:function(t,e){"style"!==t?uu.prototype.attrKV.call(this,t,e):this.style.set(e)},setStyle:function(t,e){return this.style.set(t,e),this.dirty(!1),this},useStyle:function(t){return this.style=new bu(t,this),this.dirty(!1),this}},h(Fe,uu),f(Fe,Fu),Be.prototype={constructor:Be,type:"image",brush:function(t,e){var n=this.style,i=n.image;n.bind(t,this,e);var r=this._image=Qt(i,this._image,this,this.onload);if(r&&ee(r)){var o=n.x||0,a=n.y||0,s=n.width,l=n.height,c=r.width/r.height;if(null==s&&null!=l?s=l*c:null==l&&null!=s?l=s/c:null==s&&null==l&&(s=r.width,l=r.height),this.setTransform(t),n.sWidth&&n.sHeight){var u=n.sx||0,h=n.sy||0;t.drawImage(r,u,h,n.sWidth,n.sHeight,o,a,s,l)}else if(n.sx&&n.sy){var f=s-(u=n.sx),d=l-(h=n.sy);t.drawImage(r,u,h,f,d,o,a,s,l)}else t.drawImage(r,o,a,s,l);null!=n.text&&(this.restoreTransform(t),this.drawRectText(t,this.getBoundingRect()))}},getBoundingRect:function(){var t=this.style;return this._rect||(this._rect=new Ft(t.x||0,t.y||0,t.width||0,t.height||0)),this._rect}},h(Be,Fe);var Bu=new Ft(0,0,0,0),$u=new Ft(0,0,0,0),Hu=function(t,e,n){this.type="canvas";var i=!t.nodeName||"CANVAS"===t.nodeName.toUpperCase();this._opts=n=s({},n||{}),this.dpr=n.devicePixelRatio||au,this._singleCanvas=i,this.root=t;var r=t.style;r&&(r["-webkit-tap-highlight-color"]="transparent",r["-webkit-user-select"]=r["user-select"]=r["-webkit-touch-callout"]="none",t.innerHTML=""),this.storage=e;var o=this._zlevelList=[],a=this._layers={};if(this._layerConfig={},this._needsManuallyCompositing=!1,i){var l=t.width,c=t.height;null!=n.width&&(l=n.width),null!=n.height&&(c=n.height),this.dpr=n.devicePixelRatio||1,t.width=l*this.dpr,t.height=c*this.dpr,this._width=l,this._height=c;var u=new Tu(t,this,this.dpr);u.__builtin__=!0,u.initContext(),a[314159]=u,u.zlevel=314159,o.push(314159),this._domRoot=t}else{this._width=this._getSize(0),this._height=this._getSize(1);var h=this._domRoot=Ge(this._width,this._height);t.appendChild(h)}this._hoverlayer=null,this._hoverElements=[]};Hu.prototype={constructor:Hu,getType:function(){return"canvas"},isSingleCanvas:function(){return this._singleCanvas},getViewportRoot:function(){return this._domRoot},getViewportRootOffset:function(){var t=this.getViewportRoot();if(t)return{offsetLeft:t.offsetLeft||0,offsetTop:t.offsetTop||0}},refresh:function(t){var e=this.storage.getDisplayList(!0),n=this._zlevelList;this._redrawId=Math.random(),this._paintList(e,t,this._redrawId);for(var i=0;i<n.length;i++){var r=n[i],o=this._layers[r];if(!o.__builtin__&&o.refresh){var a=0===i?this._backgroundColor:null;o.refresh(a)}}return this.refreshHover(),this},addHover:function(t,e){if(!t.__hoverMir){var n=new t.constructor({style:t.style,shape:t.shape});n.__from=t,t.__hoverMir=n,n.setStyle(e),this._hoverElements.push(n)}},removeHover:function(t){var e=t.__hoverMir,n=this._hoverElements,i=u(n,e);i>=0&&n.splice(i,1),t.__hoverMir=null},clearHover:function(t){for(var e=this._hoverElements,n=0;n<e.length;n++){var i=e[n].__from;i&&(i.__hoverMir=null)}e.length=0},refreshHover:function(){var t=this._hoverElements,e=t.length,n=this._hoverlayer;if(n&&n.clear(),e){Ut(t,this.storage.displayableSortFunc),n||(n=this._hoverlayer=this.getLayer(1e5));var i={};n.ctx.save();for(var r=0;r<e;){var o=t[r],a=o.__from;a&&a.__zr?(r++,a.invisible||(o.transform=a.transform,o.invTransform=a.invTransform,o.__clipPaths=a.__clipPaths,this._doPaintEl(o,n,!0,i))):(t.splice(r,1),a.__hoverMir=null,e--)}n.ctx.restore()}},getHoverLayer:function(){return this.getLayer(1e5)},_paintList:function(t,e,n){if(this._redrawId===n){e=e||!1,this._updateLayerStatus(t);var i=this._doPaintList(t,e);if(this._needsManuallyCompositing&&this._compositeManually(),!i){var r=this;ku(function(){r._paintList(t,e,n)})}}},_compositeManually:function(){var t=this.getLayer(314159).ctx,e=this._domRoot.width,n=this._domRoot.height;t.clearRect(0,0,e,n),this.eachBuiltinLayer(function(i){i.virtual&&t.drawImage(i.dom,0,0,e,n)})},_doPaintList:function(t,e){for(var n=[],i=0;i<this._zlevelList.length;i++){var r=this._zlevelList[i];(s=this._layers[r]).__builtin__&&s!==this._hoverlayer&&(s.__dirty||e)&&n.push(s)}for(var o=!0,a=0;a<n.length;a++){var s=n[a],l=s.ctx,c={};l.save();var u=e?s.__startIndex:s.__drawIndex,h=!e&&s.incremental&&Date.now,f=h&&Date.now(),d=s.zlevel===this._zlevelList[0]?this._backgroundColor:null;if(s.__startIndex===s.__endIndex)s.clear(!1,d);else if(u===s.__startIndex){var g=t[u];g.incremental&&g.notClear&&!e||s.clear(!1,d)}-1===u&&(console.error("For some unknown reason. drawIndex is -1"," at components\\echarts\\echarts.simple.min.js:22"),u=s.__startIndex);for(var v=u;v<s.__endIndex;v++){var m=t[v];if(this._doPaintEl(m,s,e,c),m.__dirty=!1,h&&Date.now()-f>15)break}s.__drawIndex=v,s.__drawIndex<s.__endIndex&&(o=!1),c.prevElClipPaths&&l.restore(),l.restore()}return xc.wxa&&p(this._layers,function(t){t&&t.ctx&&t.ctx.draw&&t.ctx.draw()}),o},_doPaintEl:function(t,e,n,i){var r=e.ctx,o=t.transform;if((e.__dirty||n)&&!t.invisible&&0!==t.style.opacity&&(!o||o[0]||o[3])&&(!t.culling||!We(t,this._width,this._height))){var a=t.__clipPaths;i.prevElClipPaths&&!Ve(a,i.prevElClipPaths)||(i.prevElClipPaths&&(e.ctx.restore(),i.prevElClipPaths=null,i.prevEl=null),a&&(r.save(),je(a,r),i.prevElClipPaths=a)),t.beforeBrush&&t.beforeBrush(r),t.brush(r,i.prevEl||null),i.prevEl=t,t.afterBrush&&t.afterBrush(r)}},getLayer:function(t,e){this._singleCanvas&&!this._needsManuallyCompositing&&(t=314159);var n=this._layers[t];return n||((n=new Tu("zr_"+t,this,this.dpr)).zlevel=t,n.__builtin__=!0,this._layerConfig[t]&&o(n,this._layerConfig[t],!0),e&&(n.virtual=e),this.insertLayer(t,n),n.initContext()),n},insertLayer:function(t,e){var n=this._layers,i=this._zlevelList,r=i.length,o=null,a=-1,s=this._domRoot;if(n[t])lu("ZLevel "+t+" has been used already");else if(He(e)){if(r>0&&t>i[0]){for(a=0;a<r-1&&!(i[a]<t&&i[a+1]>t);a++);o=n[i[a]]}if(i.splice(a+1,0,t),n[t]=e,!e.virtual)if(o){var l=o.dom;l.nextSibling?s.insertBefore(e.dom,l.nextSibling):s.appendChild(e.dom)}else s.firstChild?s.insertBefore(e.dom,s.firstChild):s.appendChild(e.dom)}else lu("Layer of zlevel "+t+" is not valid")},eachLayer:function(t,e){var n,i,r=this._zlevelList;for(i=0;i<r.length;i++)n=r[i],t.call(e,this._layers[n],n)},eachBuiltinLayer:function(t,e){var n,i,r,o=this._zlevelList;for(r=0;r<o.length;r++)i=o[r],(n=this._layers[i]).__builtin__&&t.call(e,n,i)},eachOtherLayer:function(t,e){var n,i,r,o=this._zlevelList;for(r=0;r<o.length;r++)i=o[r],(n=this._layers[i]).__builtin__||t.call(e,n,i)},getLayers:function(){return this._layers},_updateLayerStatus:function(t){function e(t){n&&(n.__endIndex!==t&&(n.__dirty=!0),n.__endIndex=t)}if(this.eachBuiltinLayer(function(t,e){t.__dirty=t.__used=!1}),this._singleCanvas)for(r=1;r<t.length;r++)if((a=t[r]).zlevel!==t[r-1].zlevel||a.incremental){this._needsManuallyCompositing=!0;break}for(var n=null,i=0,r=0;r<t.length;r++){var o,a=t[r],s=a.zlevel;a.incremental?((o=this.getLayer(s+.001,this._needsManuallyCompositing)).incremental=!0,i=1):o=this.getLayer(s+(i>0?.01:0),this._needsManuallyCompositing),o.__builtin__||lu("ZLevel "+s+" has been used by unkown layer "+o.id),o!==n&&(o.__used=!0,o.__startIndex!==r&&(o.__dirty=!0),o.__startIndex=r,o.incremental?o.__drawIndex=-1:o.__drawIndex=r,e(r),n=o),a.__dirty&&(o.__dirty=!0,o.incremental&&o.__drawIndex<0&&(o.__drawIndex=r))}e(r),this.eachBuiltinLayer(function(t,e){!t.__used&&t.getElementCount()>0&&(t.__dirty=!0,t.__startIndex=t.__endIndex=t.__drawIndex=0),t.__dirty&&t.__drawIndex<0&&(t.__drawIndex=t.__startIndex)})},clear:function(){return this.eachBuiltinLayer(this._clearLayer),this},_clearLayer:function(t){t.clear()},setBackgroundColor:function(t){this._backgroundColor=t},configLayer:function(t,e){if(e){var n=this._layerConfig;n[t]?o(n[t],e,!0):n[t]=e;for(var i=0;i<this._zlevelList.length;i++){var r=this._zlevelList[i];r!==t&&r!==t+.01||o(this._layers[r],n[t],!0)}}},delLayer:function(t){var e=this._layers,n=this._zlevelList,i=e[t];i&&(i.dom.parentNode.removeChild(i.dom),delete e[t],n.splice(u(n,t),1))},resize:function(t,e){if(this._domRoot.style){var n=this._domRoot;n.style.display="none";var i=this._opts;if(null!=t&&(i.width=t),null!=e&&(i.height=e),t=this._getSize(0),e=this._getSize(1),n.style.display="",this._width!=t||e!=this._height){for(var r in n.style.width=t+"px",n.style.height=e+"px",this._layers)this._layers.hasOwnProperty(r)&&this._layers[r].resize(t,e);p(this._progressiveLayers,function(n){n.resize(t,e)}),this.refresh(!0)}this._width=t,this._height=e}else{if(null==t||null==e)return;this._width=t,this._height=e,this.getLayer(314159).resize(t,e)}return this},clearLayer:function(t){var e=this._layers[t];e&&e.clear()},dispose:function(){this.root.innerHTML="",this.root=this.storage=this._domRoot=this._layers=null},getRenderedCanvas:function(t){if(t=t||{},this._singleCanvas&&!this._compositeManually)return this._layers[314159].dom;var e=new Tu("image",this,t.pixelRatio||this.dpr);if(e.initContext(),e.clear(!1,t.backgroundColor||this._backgroundColor),t.pixelRatio<=this.dpr){this.refresh();var n=e.dom.width,i=e.dom.height,r=e.ctx;this.eachLayer(function(t){t.__builtin__?r.drawImage(t.dom,0,0,n,i):t.renderToCanvas&&(e.ctx.save(),t.renderToCanvas(e.ctx),e.ctx.restore())})}else for(var o={},a=this.storage.getDisplayList(!0),s=0;s<a.length;s++){var l=a[s];this._doPaintEl(l,e,!0,o)}return e.dom},getWidth:function(){return this._width},getHeight:function(){return this._height},_getSize:function(t){var e=this._opts,i=["width","height"][t],r=["clientWidth","clientHeight"][t],o=["paddingLeft","paddingTop"][t],a=["paddingRight","paddingBottom"][t];if(null!=e[i]&&"auto"!==e[i])return parseFloat(e[i]);var s=this.root,l=n.defaultView.getComputedStyle(s);return(s[r]||$e(l[i])||$e(s.style[i]))-($e(l[o])||0)-($e(l[a])||0)|0},pathToImage:function(t,e){e=e||this.dpr;var i=n.createElement("canvas"),r=i.getContext("2d"),o=t.getBoundingRect(),a=t.style,s=a.shadowBlur*e,l=a.shadowOffsetX*e,c=a.shadowOffsetY*e,u=a.hasStroke()?a.lineWidth:0,h=Math.max(u/2,-l+s),f=Math.max(u/2,l+s),d=Math.max(u/2,-c+s),p=Math.max(u/2,c+s),g=o.width+h+f,v=o.height+d+p;i.width=g*e,i.height=v*e,r.scale(e,e),r.clearRect(0,0,g,v),r.dpr=e;var m={position:t.position,rotation:t.rotation,scale:t.scale};t.position=[h-o.x,d-o.y],t.rotation=0,t.scale=[1,1],t.updateTransform(),t&&t.brush(r);var y=new Be({style:{x:0,y:0,image:i}});return null!=m.position&&(y.position=t.position=m.position),null!=m.rotation&&(y.rotation=t.rotation=m.rotation),null!=m.scale&&(y.scale=t.scale=m.scale),y}};var Wu="undefined"!=typeof e&&!!e.addEventListener,Vu=/^(?:mouse|pointer|contextmenu|drag|drop)|click/,ju=function(t){t=t||{},this.stage=t.stage||{},this.onframe=t.onframe||function(){},this._clips=[],this._running=!1,this._time,this._pausedTime,this._pauseStart,this._paused=!1,Fc.call(this)};ju.prototype={constructor:ju,addClip:function(t){this._clips.push(t)},addAnimator:function(t){t.animation=this;for(var e=t.getClips(),n=0;n<e.length;n++)this.addClip(e[n])},removeClip:function(t){var e=u(this._clips,t);e>=0&&this._clips.splice(e,1)},removeAnimator:function(t){for(var e=t.getClips(),n=0;n<e.length;n++)this.removeClip(e[n]);t.animation=null},_update:function(){for(var t=(new Date).getTime()-this._pausedTime,e=t-this._time,n=this._clips,i=n.length,r=[],o=[],a=0;a<i;a++){var s=n[a],l=s.step(t,e);l&&(r.push(l),o.push(s))}for(a=0;a<i;)n[a]._needsRemove?(n[a]=n[i-1],n.pop(),i--):a++;for(i=r.length,a=0;a<i;a++)o[a].fire(r[a]);this._time=t,this.onframe(e),this.trigger("frame",e),this.stage.update&&this.stage.update()},_startLoop:function(){function t(){e._running&&(ku(t),!e._paused&&e._update())}var e=this;this._running=!0,ku(t)},start:function(){this._time=(new Date).getTime(),this._pausedTime=0,this._startLoop()},stop:function(){this._running=!1},pause:function(){this._paused||(this._pauseStart=(new Date).getTime(),this._paused=!0)},resume:function(){this._paused&&(this._pausedTime+=(new Date).getTime()-this._pauseStart,this._paused=!1)},clear:function(){this._clips=[]},isFinished:function(){return!this._clips.length},animate:function(t,e){var n=new ru(t,(e=e||{}).loop,e.getter,e.setter);return this.addAnimator(n),n}},f(ju,Fc);var Gu=function(){this._track=[]};Gu.prototype={constructor:Gu,recognize:function(t,e,n){return this._doTrack(t,e,n),this._recognize(t)},clear:function(){return this._track.length=0,this},_doTrack:function(t,e,n){var i=t.touches;if(i){for(var r={points:[],touches:[],target:e,event:t},o=0,a=i.length;o<a;o++){var s=i[o],l=Xe(n,s,{});r.points.push([l.zrX,l.zrY]),r.touches.push(s)}this._track.push(r)}},_recognize:function(t){for(var e in Uu)if(Uu.hasOwnProperty(e)){var n=Uu[e](this._track,t);if(n)return n}}};var Uu={pinch:function(t,e){var n=t.length;if(n){var i=(t[n-1]||{}).points,r=(t[n-2]||{}).points||i;if(r&&r.length>1&&i&&i.length>1){var o=Je(i)/Je(r);!isFinite(o)&&(o=1),e.pinchScale=o;var a=Qe(i);return e.pinchX=a[0],e.pinchY=a[1],{type:"pinch",target:t[0].target,event:e}}}}},Xu=["click","dblclick","mousewheel","mouseout","mouseup","mousedown","mousemove","contextmenu"],qu=["touchstart","touchend","touchmove"],Yu={pointerdown:1,pointerup:1,pointermove:1,pointerout:1},Zu=g(Xu,function(t){var e=t.replace("mouse","pointer");return Yu[e]?e:t}),Ku={mousemove:function(t){t=Ye(this.dom,t),this.trigger("mousemove",t)},mouseout:function(t){var e=(t=Ye(this.dom,t)).toElement||t.relatedTarget;if(e!=this.dom)for(;e&&9!=e.nodeType;){if(e===this.dom)return;e=e.parentNode}this.trigger("mouseout",t)},touchstart:function(t){(t=Ye(this.dom,t)).zrByTouch=!0,this._lastTouchMoment=new Date,en(this,t,"start"),Ku.mousemove.call(this,t),Ku.mousedown.call(this,t),nn(this)},touchmove:function(t){(t=Ye(this.dom,t)).zrByTouch=!0,en(this,t,"change"),Ku.mousemove.call(this,t),nn(this)},touchend:function(t){(t=Ye(this.dom,t)).zrByTouch=!0,en(this,t,"end"),Ku.mouseup.call(this,t),+new Date-this._lastTouchMoment<300&&Ku.click.call(this,t),nn(this)},pointerdown:function(t){Ku.mousedown.call(this,t)},pointermove:function(t){rn(t)||Ku.mousemove.call(this,t)},pointerup:function(t){Ku.mouseup.call(this,t)},pointerout:function(t){rn(t)||Ku.mouseout.call(this,t)}};p(["click","mousedown","mouseup","mousewheel","dblclick","contextmenu"],function(t){Ku[t]=function(e){e=Ye(this.dom,e),this.trigger(t,e)}});var Ju=an.prototype;Ju.dispose=function(){for(var t=Xu.concat(qu),e=0;e<t.length;e++){var n=t[e];Ke(this.dom,tn(n),this._handlers[n])}},Ju.setCursor=function(t){this.dom.style&&(this.dom.style.cursor=t||"default")},f(an,Fc);var Qu=!xc.canvasSupported,th={canvas:Hu},eh=function(t,e,n){n=n||{},this.dom=e,this.id=t;var i=this,r=new mu,o=n.renderer;if(Qu){if(!th.vml)throw new Error("You need to require 'zrender/vml/vml' to support IE8");o="vml"}else o&&th[o]||(o="canvas");var a=new th[o](e,r,n,t);this.storage=r,this.painter=a;var s=xc.node||xc.worker?null:new an(a.getViewportRoot());this.handler=new Hc(r,a,s,a.root),this.animation=new ju({stage:{update:y(this.flush,this)}}),this.animation.start(),this._needsRefresh;var l=r.delFromStorage,c=r.addToStorage;r.delFromStorage=function(t){l.call(r,t),t&&t.removeSelfFromZr(i)},r.addToStorage=function(t){c.call(r,t),t.addSelfToZr(i)}};eh.prototype={constructor:eh,getId:function(){return this.id},add:function(t){this.storage.addRoot(t),this._needsRefresh=!0},remove:function(t){this.storage.delRoot(t),this._needsRefresh=!0},configLayer:function(t,e){this.painter.configLayer&&this.painter.configLayer(t,e),this._needsRefresh=!0},setBackgroundColor:function(t){this.painter.setBackgroundColor&&this.painter.setBackgroundColor(t),this._needsRefresh=!0},refreshImmediately:function(){this._needsRefresh=!1,this.painter.refresh(),this._needsRefresh=!1},refresh:function(){this._needsRefresh=!0},flush:function(){var t;this._needsRefresh&&(t=!0,this.refreshImmediately()),this._needsRefreshHover&&(t=!0,this.refreshHoverImmediately()),t&&this.trigger("rendered")},addHover:function(t,e){this.painter.addHover&&(this.painter.addHover(t,e),this.refreshHover())},removeHover:function(t){this.painter.removeHover&&(this.painter.removeHover(t),this.refreshHover())},clearHover:function(){this.painter.clearHover&&(this.painter.clearHover(),this.refreshHover())},refreshHover:function(){this._needsRefreshHover=!0},refreshHoverImmediately:function(){this._needsRefreshHover=!1,this.painter.refreshHover&&this.painter.refreshHover()},resize:function(t){t=t||{},this.painter.resize(t.width,t.height),this.handler.resize()},clearAnimation:function(){this.animation.clear()},getWidth:function(){return this.painter.getWidth()},getHeight:function(){return this.painter.getHeight()},pathToImage:function(t,e){return this.painter.pathToImage(t,e)},setCursorStyle:function(t){this.handler.setCursorStyle(t)},findHover:function(t,e){return this.handler.findHover(t,e)},on:function(t,e,n){this.handler.on(t,e,n)},off:function(t,e){this.handler.off(t,e)},trigger:function(t,e){this.handler.trigger(t,e)},clear:function(){this.storage.delRoot(),this.painter.clear()},dispose:function(){this.animation.stop(),this.clear(),this.storage.dispose(),this.painter.dispose(),this.handler.dispose(),this.animation=this.storage=this.painter=this.handler=null}};var nh=p,ih=S,rh=x,oh="series\0",ah=["fontStyle","fontWeight","fontSize","fontFamily","rich","tag","color","textBorderColor","textBorderWidth","width","height","lineHeight","align","verticalAlign","baseline","shadowColor","shadowBlur","shadowOffsetX","shadowOffsetY","textShadowColor","textShadowBlur","textShadowOffsetX","textShadowOffsetY","backgroundColor","borderColor","borderWidth","borderRadius","padding"],sh=0,lh=".",ch="___EC__COMPONENT__CONTAINER___",uh=0,hh=function(t){for(var e=0;e<t.length;e++)t[e][1]||(t[e][1]=t[e][0]);return function(e,n,i){for(var r={},o=0;o<t.length;o++){var a=t[o][1];if(!(n&&u(n,a)>=0||i&&u(i,a)<0)){var s=e.getShallow(a);null!=s&&(r[t[o][0]]=s)}}return r}},fh=hh([["lineWidth","width"],["stroke","color"],["opacity"],["shadowBlur"],["shadowOffsetX"],["shadowOffsetY"],["shadowColor"]]),dh={getLineStyle:function(t){var e=fh(this,t),n=this.getLineDash(e.lineWidth);return n&&(e.lineDash=n),e},getLineDash:function(t){null==t&&(t=1);var e=this.get("type"),n=Math.max(t,2),i=4*t;return"solid"===e||null==e?null:"dashed"===e?[i,i]:[n,n]}},ph=hh([["fill","color"],["shadowBlur"],["shadowOffsetX"],["shadowOffsetY"],["opacity"],["shadowColor"]]),gh={getAreaStyle:function(t,e){return ph(this,t,e)}},vh=Math.pow,mh=Math.sqrt,yh=1e-8,_h=1e-4,xh=mh(3),bh=1/3,wh=$(),Sh=$(),Mh=$(),Ah=Math.min,Th=Math.max,kh=Math.sin,Ph=Math.cos,Ch=2*Math.PI,Ih=$(),Dh=$(),Lh=$(),Oh=[],Eh=[],Rh={M:1,L:2,C:3,Q:4,A:5,Z:6,R:7},Nh=[],zh=[],Fh=[],Bh=[],$h=Math.min,Hh=Math.max,Wh=Math.cos,Vh=Math.sin,jh=Math.sqrt,Gh=Math.abs,Uh="undefined"!=typeof Float32Array,Xh=function(t){this._saveData=!t,this._saveData&&(this.data=[]),this._ctx=null};Xh.prototype={constructor:Xh,_xi:0,_yi:0,_x0:0,_y0:0,_ux:0,_uy:0,_len:0,_lineDash:null,_dashOffset:0,_dashIdx:0,_dashSum:0,setScale:function(t,e){this._ux=Gh(1/au/t)||0,this._uy=Gh(1/au/e)||0},getContext:function(){return this._ctx},beginPath:function(t){return this._ctx=t,t&&t.beginPath(),t&&(this.dpr=t.dpr),this._saveData&&(this._len=0),this._lineDash&&(this._lineDash=null,this._dashOffset=0),this},moveTo:function(t,e){return this.addData(Rh.M,t,e),this._ctx&&this._ctx.moveTo(t,e),this._x0=t,this._y0=e,this._xi=t,this._yi=e,this},lineTo:function(t,e){var n=Gh(t-this._xi)>this._ux||Gh(e-this._yi)>this._uy||this._len<5;return this.addData(Rh.L,t,e),this._ctx&&n&&(this._needsDash()?this._dashedLineTo(t,e):this._ctx.lineTo(t,e)),n&&(this._xi=t,this._yi=e),this},bezierCurveTo:function(t,e,n,i,r,o){return this.addData(Rh.C,t,e,n,i,r,o),this._ctx&&(this._needsDash()?this._dashedBezierTo(t,e,n,i,r,o):this._ctx.bezierCurveTo(t,e,n,i,r,o)),this._xi=r,this._yi=o,this},quadraticCurveTo:function(t,e,n,i){return this.addData(Rh.Q,t,e,n,i),this._ctx&&(this._needsDash()?this._dashedQuadraticTo(t,e,n,i):this._ctx.quadraticCurveTo(t,e,n,i)),this._xi=n,this._yi=i,this},arc:function(t,e,n,i,r,o){return this.addData(Rh.A,t,e,n,n,i,r-i,0,o?0:1),this._ctx&&this._ctx.arc(t,e,n,i,r,o),this._xi=Wh(r)*n+t,this._yi=Vh(r)*n+t,this},arcTo:function(t,e,n,i,r){return this._ctx&&this._ctx.arcTo(t,e,n,i,r),this},rect:function(t,e,n,i){return this._ctx&&this._ctx.rect(t,e,n,i),this.addData(Rh.R,t,e,n,i),this},closePath:function(){this.addData(Rh.Z);var t=this._ctx,e=this._x0,n=this._y0;return t&&(this._needsDash()&&this._dashedLineTo(e,n),t.closePath()),this._xi=e,this._yi=n,this},fill:function(t){t&&t.fill(),this.toStatic()},stroke:function(t){t&&t.stroke(),this.toStatic()},setLineDash:function(t){if(t instanceof Array){this._lineDash=t,this._dashIdx=0;for(var e=0,n=0;n<t.length;n++)e+=t[n];this._dashSum=e}return this},setLineDashOffset:function(t){return this._dashOffset=t,this},len:function(){return this._len},setData:function(t){var e=t.length;this.data&&this.data.length==e||!Uh||(this.data=new Float32Array(e));for(var n=0;n<e;n++)this.data[n]=t[n];this._len=e},appendPath:function(t){t instanceof Array||(t=[t]);for(var e=t.length,n=0,i=this._len,r=0;r<e;r++)n+=t[r].len();for(Uh&&this.data instanceof Float32Array&&(this.data=new Float32Array(i+n)),r=0;r<e;r++)for(var o=t[r].data,a=0;a<o.length;a++)this.data[i++]=o[a];this._len=i},addData:function(t){if(this._saveData){var e=this.data;this._len+arguments.length>e.length&&(this._expandData(),e=this.data);for(var n=0;n<arguments.length;n++)e[this._len++]=arguments[n];this._prevCmd=t}},_expandData:function(){if(!(this.data instanceof Array)){for(var t=[],e=0;e<this._len;e++)t[e]=this.data[e];this.data=t}},_needsDash:function(){return this._lineDash},_dashedLineTo:function(t,e){var n,i,r=this._dashSum,o=this._dashOffset,a=this._lineDash,s=this._ctx,l=this._xi,c=this._yi,u=t-l,h=e-c,f=jh(u*u+h*h),d=l,p=c,g=a.length;for(u/=f,h/=f,o<0&&(o=r+o),d-=(o%=r)*u,p-=o*h;u>0&&d<=t||u<0&&d>=t||0==u&&(h>0&&p<=e||h<0&&p>=e);)d+=u*(n=a[i=this._dashIdx]),p+=h*n,this._dashIdx=(i+1)%g,u>0&&d<l||u<0&&d>l||h>0&&p<c||h<0&&p>c||s[i%2?"moveTo":"lineTo"](u>=0?$h(d,t):Hh(d,t),h>=0?$h(p,e):Hh(p,e));u=d-t,h=p-e,this._dashOffset=-jh(u*u+h*h)},_dashedBezierTo:function(t,e,n,i,r,o){var a,s,l,c,u,h=this._dashSum,f=this._dashOffset,d=this._lineDash,p=this._ctx,g=this._xi,v=this._yi,m=Dn,y=0,_=this._dashIdx,x=d.length,b=0;for(f<0&&(f=h+f),f%=h,a=0;a<1;a+=.1)s=m(g,t,n,r,a+.1)-m(g,t,n,r,a),l=m(v,e,i,o,a+.1)-m(v,e,i,o,a),y+=jh(s*s+l*l);for(;_<x&&!((b+=d[_])>f);_++);for(a=(b-f)/y;a<=1;)c=m(g,t,n,r,a),u=m(v,e,i,o,a),_%2?p.moveTo(c,u):p.lineTo(c,u),a+=d[_]/y,_=(_+1)%x;_%2!=0&&p.lineTo(r,o),s=r-c,l=o-u,this._dashOffset=-jh(s*s+l*l)},_dashedQuadraticTo:function(t,e,n,i){var r=n,o=i;n=(n+2*t)/3,i=(i+2*e)/3,t=(this._xi+2*t)/3,e=(this._yi+2*e)/3,this._dashedBezierTo(t,e,n,i,r,o)},toStatic:function(){var t=this.data;t instanceof Array&&(t.length=this._len,Uh&&(this.data=new Float32Array(t)))},getBoundingRect:function(){Nh[0]=Nh[1]=Fh[0]=Fh[1]=Number.MAX_VALUE,zh[0]=zh[1]=Bh[0]=Bh[1]=-Number.MAX_VALUE;for(var t=this.data,e=0,n=0,i=0,r=0,o=0;o<t.length;){var a=t[o++];switch(1==o&&(i=e=t[o],r=n=t[o+1]),a){case Rh.M:e=i=t[o++],n=r=t[o++],Fh[0]=i,Fh[1]=r,Bh[0]=i,Bh[1]=r;break;case Rh.L:Vn(e,n,t[o],t[o+1],Fh,Bh),e=t[o++],n=t[o++];break;case Rh.C:jn(e,n,t[o++],t[o++],t[o++],t[o++],t[o],t[o+1],Fh,Bh),e=t[o++],n=t[o++];break;case Rh.Q:Gn(e,n,t[o++],t[o++],t[o],t[o+1],Fh,Bh),e=t[o++],n=t[o++];break;case Rh.A:var s=t[o++],l=t[o++],c=t[o++],u=t[o++],h=t[o++],f=t[o++]+h,d=(t[o++],1-t[o++]);1==o&&(i=Wh(h)*c+s,r=Vh(h)*u+l),Un(s,l,c,u,h,f,d,Fh,Bh),e=Wh(f)*c+s,n=Vh(f)*u+l;break;case Rh.R:Vn(i=e=t[o++],r=n=t[o++],i+t[o++],r+t[o++],Fh,Bh);break;case Rh.Z:e=i,n=r}Z(Nh,Nh,Fh),K(zh,zh,Bh)}return 0===o&&(Nh[0]=Nh[1]=zh[0]=zh[1]=0),new Ft(Nh[0],Nh[1],zh[0]-Nh[0],zh[1]-Nh[1])},rebuildPath:function(t){for(var e,n,i,r,o,a,s=this.data,l=this._ux,c=this._uy,u=this._len,h=0;h<u;){var f=s[h++];switch(1==h&&(e=i=s[h],n=r=s[h+1]),f){case Rh.M:e=i=s[h++],n=r=s[h++],t.moveTo(i,r);break;case Rh.L:o=s[h++],a=s[h++],(Gh(o-i)>l||Gh(a-r)>c||h===u-1)&&(t.lineTo(o,a),i=o,r=a);break;case Rh.C:t.bezierCurveTo(s[h++],s[h++],s[h++],s[h++],s[h++],s[h++]),i=s[h-2],r=s[h-1];break;case Rh.Q:t.quadraticCurveTo(s[h++],s[h++],s[h++],s[h++]),i=s[h-2],r=s[h-1];break;case Rh.A:var d=s[h++],p=s[h++],g=s[h++],v=s[h++],m=s[h++],y=s[h++],_=s[h++],x=s[h++],b=g>v?g:v,w=g>v?1:g/v,S=g>v?v/g:1,M=m+y;Math.abs(g-v)>.001?(t.translate(d,p),t.rotate(_),t.scale(w,S),t.arc(0,0,b,m,M,1-x),t.scale(1/w,1/S),t.rotate(-_),t.translate(-d,-p)):t.arc(d,p,b,m,M,1-x),1==h&&(e=Wh(m)*g+d,n=Vh(m)*v+p),i=Wh(M)*g+d,r=Vh(M)*v+p;break;case Rh.R:e=i=s[h],n=r=s[h+1],t.rect(s[h++],s[h++],s[h++],s[h++]);break;case Rh.Z:t.closePath(),i=e,r=n}}}},Xh.CMD=Rh;var qh=2*Math.PI,Yh=2*Math.PI,Zh=Xh.CMD,Kh=2*Math.PI,Jh=1e-4,Qh=[-1,-1,-1],tf=[-1,-1],ef=Au.prototype.getCanvasPattern,nf=Math.abs,rf=new Xh(!0);si.prototype={constructor:si,type:"path",__dirtyPath:!0,strokeContainThreshold:5,brush:function(t,e){var n,i=this.style,r=this.path||rf,o=i.hasStroke(),a=i.hasFill(),s=i.fill,l=i.stroke,c=a&&!!s.colorStops,u=o&&!!l.colorStops,h=a&&!!s.image,f=o&&!!l.image;(i.bind(t,this,e),this.setTransform(t),this.__dirty)&&(c&&(n=n||this.getBoundingRect(),this._fillGradient=i.getGradient(t,s,n)),u&&(n=n||this.getBoundingRect(),this._strokeGradient=i.getGradient(t,l,n)));c?t.fillStyle=this._fillGradient:h&&(t.fillStyle=ef.call(s,t)),u?t.strokeStyle=this._strokeGradient:f&&(t.strokeStyle=ef.call(l,t));var d=i.lineDash,p=i.lineDashOffset,g=!!t.setLineDash,v=this.getGlobalScale();r.setScale(v[0],v[1]),this.__dirtyPath||d&&!g&&o?(r.beginPath(t),d&&!g&&(r.setLineDash(d),r.setLineDashOffset(p)),this.buildPath(r,this.shape,!1),this.path&&(this.__dirtyPath=!1)):(t.beginPath(),this.path.rebuildPath(t)),a&&r.fill(t),d&&g&&(t.setLineDash(d),t.lineDashOffset=p),o&&r.stroke(t),d&&g&&t.setLineDash([]),null!=i.text&&(this.restoreTransform(t),this.drawRectText(t,this.getBoundingRect()))},buildPath:function(t,e,n){},createPathProxy:function(){this.path=new Xh},getBoundingRect:function(){var t=this._rect,e=this.style,n=!t;if(n){var i=this.path;i||(i=this.path=new Xh),this.__dirtyPath&&(i.beginPath(),this.buildPath(i,this.shape,!1)),t=i.getBoundingRect()}if(this._rect=t,e.hasStroke()){var r=this._rectWithStroke||(this._rectWithStroke=t.clone());if(this.__dirty||n){r.copy(t);var o=e.lineWidth,a=e.strokeNoScale?this.getLineScale():1;e.hasFill()||(o=Math.max(o,this.strokeContainThreshold||4)),a>1e-10&&(r.width+=o/a,r.height+=o/a,r.x-=o/a/2,r.y-=o/a/2)}return r}return t},contain:function(t,e){var n=this.transformCoordToLocal(t,e),i=this.getBoundingRect(),r=this.style;if(t=n[0],e=n[1],i.contain(t,e)){var o=this.path.data;if(r.hasStroke()){var a=r.lineWidth,s=r.strokeNoScale?this.getLineScale():1;if(s>1e-10&&(r.hasFill()||(a=Math.max(a,this.strokeContainThreshold)),ai(o,a/s,t,e)))return!0}if(r.hasFill())return oi(o,t,e)}return!1},dirty:function(t){null==t&&(t=!0),t&&(this.__dirtyPath=t,this._rect=null),this.__dirty=!0,this.__zr&&this.__zr.refresh(),this.__clipTarget&&this.__clipTarget.dirty()},animateShape:function(t){return this.animate("shape",t)},attrKV:function(t,e){"shape"===t?(this.setShape(e),this.__dirtyPath=!0,this._rect=null):Fe.prototype.attrKV.call(this,t,e)},setShape:function(t,e){var n=this.shape;if(n){if(S(t))for(var i in t)t.hasOwnProperty(i)&&(n[i]=t[i]);else n[t]=e;this.dirty(!0)}return this},getLineScale:function(){var t=this.transform;return t&&nf(t[0]-1)>1e-10&&nf(t[3]-1)>1e-10?Math.sqrt(nf(t[0]*t[3]-t[2]*t[1])):1}},si.extend=function(t){var e=function(e){si.call(this,e),t.style&&this.style.extendFrom(t.style,!1);var n=t.shape;if(n){this.shape=this.shape||{};var i=this.shape;for(var r in n)!i.hasOwnProperty(r)&&n.hasOwnProperty(r)&&(i[r]=n[r])}t.init&&t.init.call(this,e)};for(var n in h(e,si),t)"style"!==n&&"shape"!==n&&(e.prototype[n]=t[n]);return e},h(si,Fe);var of=Xh.CMD,af=[[],[],[]],sf=Math.sqrt,lf=Math.atan2,cf=function(t,e){var n,i,r,o,a,s,l=t.data,c=of.M,u=of.C,h=of.L,f=of.R,d=of.A,p=of.Q;for(r=0,o=0;r<l.length;){switch(n=l[r++],o=r,i=0,n){case c:case h:i=1;break;case u:i=3;break;case p:i=2;break;case d:var g=e[4],v=e[5],m=sf(e[0]*e[0]+e[1]*e[1]),y=sf(e[2]*e[2]+e[3]*e[3]),_=lf(-e[1]/y,e[0]/m);l[r]*=m,l[r++]+=g,l[r]*=y,l[r++]+=v,l[r++]*=m,l[r++]*=y,l[r++]+=_,l[r++]+=_,o=r+=2;break;case f:s[0]=l[r++],s[1]=l[r++],Y(s,s,e),l[o++]=s[0],l[o++]=s[1],s[0]+=l[r++],s[1]+=l[r++],Y(s,s,e),l[o++]=s[0],l[o++]=s[1]}for(a=0;a<i;a++)(s=af[a])[0]=l[r++],s[1]=l[r++],Y(s,s,e),l[o++]=s[0],l[o++]=s[1]}},uf=["m","M","l","L","v","V","h","H","z","Z","c","C","q","Q","t","T","s","S","a","A"],hf=Math.sqrt,ff=Math.sin,df=Math.cos,pf=Math.PI,gf=function(t){return Math.sqrt(t[0]*t[0]+t[1]*t[1])},vf=function(t,e){return(t[0]*e[0]+t[1]*e[1])/(gf(t)*gf(e))},mf=function(t,e){return(t[0]*e[1]<t[1]*e[0]?-1:1)*Math.acos(vf(t,e))},yf=function(t){Fe.call(this,t)};yf.prototype={constructor:yf,type:"text",brush:function(t,e){var n=this.style;this.__dirty&&xe(n),n.fill=n.stroke=n.shadowBlur=n.shadowColor=n.shadowOffsetX=n.shadowOffsetY=null;var i=n.text;null!=i&&(i+=""),n.bind(t,this,e),ze(i,n)&&(this.setTransform(t),we(this,t,i,n),this.restoreTransform(t))},getBoundingRect:function(){var t=this.style;if(this.__dirty&&xe(t),!this._rect){var e=t.text;null!=e?e+="":e="";var n=ie(t.text+"",t.font,t.textAlign,t.textVerticalAlign,t.textPadding,t.rich);if(n.x+=t.x||0,n.y+=t.y||0,Oe(t.textStroke,t.textStrokeWidth)){var i=t.textStrokeWidth;n.x-=i/2,n.y-=i/2,n.width+=i,n.height+=i}this._rect=n}return this._rect}},h(yf,Fe);var _f=si.extend({type:"circle",shape:{cx:0,cy:0,r:0},buildPath:function(t,e,n){n&&t.moveTo(e.cx+e.r,e.cy),t.arc(e.cx,e.cy,e.r,0,2*Math.PI,!0)}}),xf=[["shadowBlur",0],["shadowColor","#000"],["shadowOffsetX",0],["shadowOffsetY",0]],bf=function(t){return xc.browser.ie&&xc.browser.version>=11?function(){var e,n=this.__clipPaths,i=this.style;if(n)for(var r=0;r<n.length;r++){var o=n[r],a=o&&o.shape,s=o&&o.type;if(a&&("sector"===s&&a.startAngle===a.endAngle||"rect"===s&&(!a.width||!a.height))){for(l=0;l<xf.length;l++)xf[l][2]=i[xf[l][0]],i[xf[l][0]]=xf[l][1];e=!0;break}}if(t.apply(this,arguments),e)for(var l=0;l<xf.length;l++)i[xf[l][0]]=xf[l][2]}:t},wf=si.extend({type:"sector",shape:{cx:0,cy:0,r0:0,r:0,startAngle:0,endAngle:2*Math.PI,clockwise:!0},brush:bf(si.prototype.brush),buildPath:function(t,e){var n=e.cx,i=e.cy,r=Math.max(e.r0||0,0),o=Math.max(e.r,0),a=e.startAngle,s=e.endAngle,l=e.clockwise,c=Math.cos(a),u=Math.sin(a);t.moveTo(c*r+n,u*r+i),t.lineTo(c*o+n,u*o+i),t.arc(n,i,o,a,s,!l),t.lineTo(Math.cos(s)*r+n,Math.sin(s)*r+i),0!==r&&t.arc(n,i,r,s,a,l),t.closePath()}}),Sf=si.extend({type:"ring",shape:{cx:0,cy:0,r:0,r0:0},buildPath:function(t,e){var n=e.cx,i=e.cy,r=2*Math.PI;t.moveTo(n+e.r,i),t.arc(n,i,e.r,0,r,!1),t.moveTo(n+e.r0,i),t.arc(n,i,e.r0,0,r,!0)}}),Mf=function(t,e){for(var n=t.length,i=[],r=0,o=1;o<n;o++)r+=q(t[o-1],t[o]);var a=r/2;for(a=a<n?n:a,o=0;o<a;o++){var s,l,c,u=o/(a-1)*(e?n:n-1),h=Math.floor(u),f=u-h,d=t[h%n];e?(s=t[(h-1+n)%n],l=t[(h+1)%n],c=t[(h+2)%n]):(s=t[0===h?h:h-1],l=t[h>n-2?n-1:h+1],c=t[h>n-3?n-1:h+2]);var p=f*f,g=f*p;i.push([di(s[0],d[0],l[0],c[0],f,p,g),di(s[1],d[1],l[1],c[1],f,p,g)])}return i},Af=function(t,e,n,i){var r,o,a,s,l=[],c=[],u=[],h=[];if(i){a=[1/0,1/0],s=[-1/0,-1/0];for(var f=0,d=t.length;f<d;f++)Z(a,a,t[f]),K(s,s,t[f]);Z(a,a,i[0]),K(s,s,i[1])}for(f=0,d=t.length;f<d;f++){var p=t[f];if(n)r=t[f?f-1:d-1],o=t[(f+1)%d];else{if(0===f||f===d-1){l.push(H(t[f]));continue}r=t[f-1],o=t[f+1]}V(c,o,r),U(c,c,e);var g=q(p,r),v=q(p,o),m=g+v;0!==m&&(g/=m,v/=m),U(u,c,-g),U(h,c,v);var y=W([],p,u),_=W([],p,h);i&&(K(y,y,a),Z(y,y,s),K(_,_,a),Z(_,_,s)),l.push(y),l.push(_)}return n&&l.push(l.shift()),l},Tf=si.extend({type:"polygon",shape:{points:null,smooth:!1,smoothConstraint:null},buildPath:function(t,e){pi(t,e,!0)}}),kf=si.extend({type:"polyline",shape:{points:null,smooth:!1,smoothConstraint:null},style:{stroke:"#000",fill:null},buildPath:function(t,e){pi(t,e,!1)}}),Pf=si.extend({type:"rect",shape:{r:0,x:0,y:0,width:0,height:0},buildPath:function(t,e){var n=e.x,i=e.y,r=e.width,o=e.height;e.r?_e(t,e):t.rect(n,i,r,o),t.closePath()}}),Cf=si.extend({type:"line",shape:{x1:0,y1:0,x2:0,y2:0,percent:1},style:{stroke:"#000",fill:null},buildPath:function(t,e){var n=e.x1,i=e.y1,r=e.x2,o=e.y2,a=e.percent;0!==a&&(t.moveTo(n,i),a<1&&(r=n*(1-a)+r*a,o=i*(1-a)+o*a),t.lineTo(r,o))},pointAt:function(t){var e=this.shape;return[e.x1*(1-t)+e.x2*t,e.y1*(1-t)+e.y2*t]}}),If=[],Df=si.extend({type:"bezier-curve",shape:{x1:0,y1:0,x2:0,y2:0,cpx1:0,cpy1:0,percent:1},style:{stroke:"#000",fill:null},buildPath:function(t,e){var n=e.x1,i=e.y1,r=e.x2,o=e.y2,a=e.cpx1,s=e.cpy1,l=e.cpx2,c=e.cpy2,u=e.percent;0!==u&&(t.moveTo(n,i),null==l||null==c?(u<1&&(Hn(n,a,r,u,If),a=If[1],r=If[2],Hn(i,s,o,u,If),s=If[1],o=If[2]),t.quadraticCurveTo(a,s,r,o)):(u<1&&(Rn(n,a,l,r,u,If),a=If[1],l=If[2],r=If[3],Rn(i,s,c,o,u,If),s=If[1],c=If[2],o=If[3]),t.bezierCurveTo(a,s,l,c,r,o)))},pointAt:function(t){return gi(this.shape,t,!1)},tangentAt:function(t){var e=gi(this.shape,t,!0);return X(e,e)}}),Lf=si.extend({type:"arc",shape:{cx:0,cy:0,r:0,startAngle:0,endAngle:2*Math.PI,clockwise:!0},style:{stroke:"#000",fill:null},buildPath:function(t,e){var n=e.cx,i=e.cy,r=Math.max(e.r,0),o=e.startAngle,a=e.endAngle,s=e.clockwise,l=Math.cos(o),c=Math.sin(o);t.moveTo(l*r+n,c*r+i),t.arc(n,i,r,o,a,!s)}}),Of=si.extend({type:"compound",shape:{paths:null},_updatePathDirty:function(){for(var t=this.__dirtyPath,e=this.shape.paths,n=0;n<e.length;n++)t=t||e[n].__dirtyPath;this.__dirtyPath=t,this.__dirty=this.__dirty||t},beforeBrush:function(){this._updatePathDirty();for(var t=this.shape.paths||[],e=this.getGlobalScale(),n=0;n<t.length;n++)t[n].path||t[n].createPathProxy(),t[n].path.setScale(e[0],e[1])},buildPath:function(t,e){for(var n=e.paths||[],i=0;i<n.length;i++)n[i].buildPath(t,n[i].shape,!0)},afterBrush:function(){for(var t=this.shape.paths||[],e=0;e<t.length;e++)t[e].__dirtyPath=!1},getBoundingRect:function(){return this._updatePathDirty(),si.prototype.getBoundingRect.call(this)}}),Ef=function(t){this.colorStops=t||[]};Ef.prototype={constructor:Ef,addColorStop:function(t,e){this.colorStops.push({offset:t,color:e})}};var Rf=function(t,e,n,i,r,o){this.x=null==t?0:t,this.y=null==e?0:e,this.x2=null==n?1:n,this.y2=null==i?0:i,this.type="linear",this.global=o||!1,Ef.call(this,r)};Rf.prototype={constructor:Rf},h(Rf,Ef);var Nf=function(t,e,n,i,r){this.x=null==t?.5:t,this.y=null==e?.5:e,this.r=null==n?.5:n,this.type="radial",this.global=r||!1,Ef.call(this,i)};Nf.prototype={constructor:Nf},h(Nf,Ef),vi.prototype.incremental=!0,vi.prototype.clearDisplaybles=function(){this._displayables=[],this._temporaryDisplayables=[],this._cursor=0,this.dirty(),this.notClear=!1},vi.prototype.addDisplayable=function(t,e){e?this._temporaryDisplayables.push(t):this._displayables.push(t),this.dirty()},vi.prototype.addDisplayables=function(t,e){e=e||!1;for(var n=0;n<t.length;n++)this.addDisplayable(t[n],e)},vi.prototype.eachPendingDisplayable=function(t){for(e=this._cursor;e<this._displayables.length;e++)t&&t(this._displayables[e]);for(var e=0;e<this._temporaryDisplayables.length;e++)t&&t(this._temporaryDisplayables[e])},vi.prototype.update=function(){for(this.updateTransform(),t=this._cursor;t<this._displayables.length;t++)(e=this._displayables[t]).parent=this,e.update(),e.parent=null;for(var t=0;t<this._temporaryDisplayables.length;t++){var e=this._temporaryDisplayables[t];e.parent=this,e.update(),e.parent=null}},vi.prototype.brush=function(t,e){for(n=this._cursor;n<this._displayables.length;n++)(i=this._displayables[n]).beforeBrush&&i.beforeBrush(t),i.brush(t,n===this._cursor?null:this._displayables[n-1]),i.afterBrush&&i.afterBrush(t);this._cursor=n;for(var n=0;n<this._temporaryDisplayables.length;n++){var i=this._temporaryDisplayables[n];i.beforeBrush&&i.beforeBrush(t),i.brush(t,0===n?null:this._temporaryDisplayables[n-1]),i.afterBrush&&i.afterBrush(t)}this._temporaryDisplayables=[],this.notClear=!0};var zf=[];vi.prototype.getBoundingRect=function(){if(!this._rect){for(var t=new Ft(1/0,1/0,-1/0,-1/0),e=0;e<this._displayables.length;e++){var n=this._displayables[e],i=n.getBoundingRect().clone();n.needLocalTransform()&&i.applyTransform(n.getLocalTransform(zf)),t.union(i)}this._rect=t}return this._rect},vi.prototype.contain=function(t,e){var n=this.transformCoordToLocal(t,e);if(this.getBoundingRect().contain(n[0],n[1]))for(var i=0;i<this._displayables.length;i++)if(this._displayables[i].contain(t,e))return!0;return!1},h(vi,Fe);var Ff=Math.round,Bf=Math.max,$f=Math.min,Hf={},Wf=(Object.freeze||Object)({extendShape:mi,extendPath:function(t,e){return fi(t,e)},makePath:yi,makeImage:_i,mergePath:function(t,e){for(var n=[],i=t.length,r=0;r<i;r++){var o=t[r];o.path||o.createPathProxy(),o.__dirtyPath&&o.buildPath(o.path,o.shape,!0),n.push(o.path)}var a=new si(e);return a.createPathProxy(),a.buildPath=function(t){t.appendPath(n);var e=t.getContext();e&&t.rebuildPath(e)},a},resizePath:bi,subPixelOptimizeLine:wi,subPixelOptimizeRect:function(t){var e=t.shape,n=t.style.lineWidth,i=e.x,r=e.y,o=e.width,a=e.height;return e.x=Si(e.x,n,!0),e.y=Si(e.y,n,!0),e.width=Math.max(Si(i+o,n,!1)-e.x,0===o?0:1),e.height=Math.max(Si(r+a,n,!1)-e.y,0===a?0:1),t},subPixelOptimize:Si,setHoverStyle:Ni,setLabelStyle:zi,setTextStyle:Fi,setText:function(t,e,n){var i,r={isRectText:!0};!1===n?i=!0:r.autoColor=n,Bi(t,e,r,i),t.host&&t.host.dirty&&t.host.dirty(!1)},getFont:Gi,updateProps:Xi,initProps:qi,getTransform:function(t,e){for(var n=rt([]);t&&t!==e;)at(n,t.getLocalTransform(),n),t=t.parent;return n},applyTransform:Yi,transformDirection:function(t,e,n){var i=0===e[4]||0===e[5]||0===e[0]?1:Math.abs(2*e[4]/e[0]),r=0===e[4]||0===e[5]||0===e[2]?1:Math.abs(2*e[4]/e[2]),o=["left"===t?-i:"right"===t?i:0,"top"===t?-r:"bottom"===t?r:0];return o=Yi(o,e,n),Math.abs(o[0])>Math.abs(o[1])?o[0]>0?"right":"left":o[1]>0?"bottom":"top"},groupTransition:Zi,clipPointsByRect:function(t,e){return g(t,function(t){var n=t[0];n=Bf(n,e.x),n=$f(n,e.x+e.width);var i=t[1];return i=Bf(i,e.y),i=$f(i,e.y+e.height),[n,i]})},clipRectByRect:function(t,e){var n=Bf(t.x,e.x),i=$f(t.x+t.width,e.x+e.width),r=Bf(t.y,e.y),o=$f(t.y+t.height,e.y+e.height);if(i>=n&&o>=r)return{x:n,y:r,width:i-n,height:o-r}},createIcon:function(t,e,n){var i=(e=s({rectHover:!0},e)).style={strokeNoScale:!0};if(n=n||{x:-1,y:-1,width:2,height:2},t)return 0===t.indexOf("image://")?(i.image=t.slice(8),l(i,n),new Be(e)):yi(t.replace("path://",""),e,n,"center")},Group:pu,Image:Be,Text:yf,Circle:_f,Sector:wf,Ring:Sf,Polygon:Tf,Polyline:kf,Rect:Pf,Line:Cf,BezierCurve:Df,Arc:Lf,IncrementalDisplayable:vi,CompoundPath:Of,LinearGradient:Rf,RadialGradient:Nf,BoundingRect:Ft}),Vf=["textStyle","color"],jf={getTextColor:function(t){var e=this.ecModel;return this.getShallow("color")||(!t&&e?e.get(Vf):null)},getFont:function(){return Gi({fontStyle:this.getShallow("fontStyle"),fontWeight:this.getShallow("fontWeight"),fontSize:this.getShallow("fontSize"),fontFamily:this.getShallow("fontFamily")},this.ecModel)},getTextRect:function(t){return ie(t,this.getFont(),this.getShallow("align"),this.getShallow("verticalAlign")||this.getShallow("baseline"),this.getShallow("padding"),this.getShallow("rich"),this.getShallow("truncateText"))}},Gf=hh([["fill","color"],["stroke","borderColor"],["lineWidth","borderWidth"],["opacity"],["shadowBlur"],["shadowOffsetX"],["shadowOffsetY"],["shadowColor"],["textPosition"],["textAlign"]]),Uf={getItemStyle:function(t,e){var n=Gf(this,t,e),i=this.getBorderLineDash();return i&&(n.lineDash=i),n},getBorderLineDash:function(){var t=this.get("borderType");return"solid"===t||null==t?null:"dashed"===t?[5,5]:[1,1]}},Xf=f,qf=mn();Ki.prototype={constructor:Ki,init:null,mergeOption:function(t){o(this.option,t,!0)},get:function(t,e){return null==t?this.option:Ji(this.option,this.parsePath(t),!e&&Qi(this,t))},getShallow:function(t,e){var n=this.option,i=null==n?n:n[t],r=!e&&Qi(this,t);return null==i&&r&&(i=r.getShallow(t)),i},getModel:function(t,e){var n,i=null==t?this.option:Ji(this.option,t=this.parsePath(t));return e=e||(n=Qi(this,t))&&n.getModel(t),new Ki(i,e,this.ecModel)},isEmpty:function(){return null==this.option},restoreData:function(){},clone:function(){return new(0,this.constructor)(r(this.option))},setReadOnly:function(t){},parsePath:function(t){return"string"==typeof t&&(t=t.split(".")),t},customizeGetParent:function(t){qf(this).getParent=t},isAnimationEnabled:function(){if(!xc.node){if(null!=this.option.animation)return!!this.option.animation;if(this.parentModel)return this.parentModel.isAnimationEnabled()}}},Mn(Ki),An(Ki),Xf(Ki,dh),Xf(Ki,gh),Xf(Ki,jf),Xf(Ki,Uf);var Yf=0,Zf=1e-4,Kf=/^(?:(\d{4})(?:[-\/](\d{1,2})(?:[-\/](\d{1,2})(?:[T ](\d{1,2})(?::(\d\d)(?::(\d\d)(?:[.,](\d+))?)?)?(Z|[\+\-]\d\d:?\d\d)?)?)?)?)?$/,Jf=L,Qf=/([&<>"'])/g,td={"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;"},ed=["a","b","c","d","e","f","g"],nd=function(t,e){return"{"+t+(null==e?"":e)+"}"},id=ce,rd=p,od=["left","right","top","bottom","width","height"],ad=[["width","left","right"],["height","top","bottom"]],sd=(_(xr,"vertical"),_(xr,"horizontal"),{getBoxLayoutParams:function(){return{left:this.get("left"),top:this.get("top"),right:this.get("right"),bottom:this.get("bottom"),width:this.get("width"),height:this.get("height")}}}),ld=mn(),cd=Ki.extend({type:"component",id:"",name:"",mainType:"",subType:"",componentIndex:0,defaultOption:null,ecModel:null,dependentModels:[],uid:null,layoutMode:null,$constructor:function(t,e,n,i){Ki.call(this,t,e,n,i),this.uid=tr("ec_cpt_model")},init:function(t,e,n,i){this.mergeDefaultAndTheme(t,n)},mergeDefaultAndTheme:function(t,e){var n=this.layoutMode,i=n?Sr(t):{};o(t,e.getTheme().get(this.mainType)),o(t,this.getDefaultOption()),n&&wr(t,i,n)},mergeOption:function(t,e){o(this.option,t,!0);var n=this.layoutMode;n&&wr(this.option,t,n)},optionUpdated:function(t,e){},getDefaultOption:function(){var t=ld(this);if(!t.defaultOption){for(var e=[],n=this.constructor;n;){var i=n.prototype.defaultOption;i&&e.push(i),n=n.superClass}for(var r={},a=e.length-1;a>=0;a--)r=o(r,e[a],!0);t.defaultOption=r}return t.defaultOption},getReferringComponents:function(t){return this.ecModel.queryComponents({mainType:t,index:this.get(t+"Index",!0),id:this.get(t+"Id",!0)})}});Pn(cd,{registerWhenExtend:!0}),function(t){var e={};t.registerSubTypeDefaulter=function(t,n){t=wn(t),e[t.main]=n},t.determineSubType=function(n,i){var r=i.type;if(!r){var o=wn(n).main;t.hasSubTypes(n)&&e[o]&&(r=e[o](i))}return r}}(cd),function(t,e){function n(t){var n={},o=[];return p(t,function(a){var s=i(n,a),l=r(s.originalDeps=e(a),t);s.entryCount=l.length,0===s.entryCount&&o.push(a),p(l,function(t){u(s.predecessor,t)<0&&s.predecessor.push(t);var e=i(n,t);u(e.successor,t)<0&&e.successor.push(a)})}),{graph:n,noEntryList:o}}function i(t,e){return t[e]||(t[e]={predecessor:[],successor:[]}),t[e]}function r(t,e){var n=[];return p(t,function(t){u(e,t)>=0&&n.push(t)}),n}t.topologicalTravel=function(t,e,i,r){function o(t){s[t].entryCount--,0===s[t].entryCount&&l.push(t)}if(t.length){var a=n(e),s=a.graph,l=a.noEntryList,c={};for(p(t,function(t){c[t]=!0});l.length;){var u=l.pop(),h=s[u],f=!!c[u];f&&(i.call(r,u,h.originalDeps.slice()),delete c[u]),p(h.successor,f?function(t){c[t]=!0,o(t)}:o)}p(c,function(){throw new Error("Circle dependency may exists")})}}}(cd,function(t){var e=[];return p(cd.getClassesByMainType(t),function(t){e=e.concat(t.prototype.dependencies||[])}),e=g(e,function(t){return wn(t).main}),"dataset"!==t&&u(e,"dataset")<=0&&e.unshift("dataset"),e}),f(cd,sd);var ud="";"undefined"!=typeof navigator&&(ud=navigator.platform||"");var hd={color:["#c23531","#2f4554","#61a0a8","#d48265","#91c7ae","#749f83","#ca8622","#bda29a","#6e7074","#546570","#c4ccd3"],gradientColor:["#f6efa6","#d88273","#bf444c"],textStyle:{fontFamily:ud.match(/^Win/)?"Microsoft YaHei":"sans-serif",fontSize:12,fontStyle:"normal",fontWeight:"normal"},blendMode:null,animation:"auto",animationDuration:1e3,animationDurationUpdate:300,animationEasing:"exponentialOut",animationEasingUpdate:"cubicOut",animationThreshold:2e3,progressiveThreshold:3e3,progressive:400,hoverLayerThreshold:3e3,useUTC:!1},fd=mn(),dd={clearColorPalette:function(){fd(this).colorIdx=0,fd(this).colorNameMap={}},getColorFromPalette:function(t,e,n){var i=fd(e=e||this),r=i.colorIdx||0,o=i.colorNameMap=i.colorNameMap||{};if(o.hasOwnProperty(t))return o[t];var a=ln(this.get("color",!0)),s=this.get("colorLayer",!0),l=null!=n&&s?Ar(s,n):a;if((l=l||a)&&l.length){var c=l[r];return t&&(o[t]=c),i.colorIdx=(r+1)%l.length,c}}},pd={cartesian2d:function(t,e,n,i){var r=t.getReferringComponents("xAxis")[0],o=t.getReferringComponents("yAxis")[0];e.coordSysDims=["x","y"],n.set("x",r),n.set("y",o),kr(r)&&(i.set("x",r),e.firstCategoryDimIndex=0),kr(o)&&(i.set("y",o),e.firstCategoryDimIndex=1)},singleAxis:function(t,e,n,i){var r=t.getReferringComponents("singleAxis")[0];e.coordSysDims=["single"],n.set("single",r),kr(r)&&(i.set("single",r),e.firstCategoryDimIndex=0)},polar:function(t,e,n,i){var r=t.getReferringComponents("polar")[0],o=r.findAxisModel("radiusAxis"),a=r.findAxisModel("angleAxis");e.coordSysDims=["radius","angle"],n.set("radius",o),n.set("angle",a),kr(o)&&(i.set("radius",o),e.firstCategoryDimIndex=0),kr(a)&&(i.set("angle",a),e.firstCategoryDimIndex=1)},geo:function(t,e,n,i){e.coordSysDims=["lng","lat"]},parallel:function(t,e,n,i){var r=t.ecModel,o=r.getComponent("parallel",t.get("parallelIndex")),a=e.coordSysDims=o.dimensions.slice();p(o.parallelAxisIndex,function(t,o){var s=r.getComponent("parallelAxis",t),l=a[o];n.set(l,s),kr(s)&&null==e.firstCategoryDimIndex&&(i.set(l,s),e.firstCategoryDimIndex=o)})}},gd="original",vd="arrayRows",md="objectRows",yd="keyedColumns",_d="unknown",xd="typedArray",bd="column",wd="row";Pr.seriesDataToSource=function(t){return new Pr({data:t,sourceFormat:A(t)?xd:gd,fromDataset:!1})},An(Pr);var Sd=mn(),Md="\0_ec_inner",Ad=Ki.extend({init:function(t,e,n,i){n=n||{},this.option=null,this._theme=new Ki(n),this._optionManager=i},setOption:function(t,e){O(!(Md in t),"please use chart.getOption()"),this._optionManager.setOption(t,e),this.resetOption(null)},resetOption:function(t){var e=!1,n=this._optionManager;if(!t||"recreate"===t){var i=n.mountOption("recreate"===t);this.option&&"recreate"!==t?(this.restoreData(),this.mergeOption(i)):Vr.call(this,i),e=!0}if("timeline"!==t&&"media"!==t||this.restoreData(),!t||"recreate"===t||"timeline"===t){var r=n.getTimelineOption(this);r&&(this.mergeOption(r),e=!0)}if(!t||"recreate"===t||"media"===t){var o=n.getMediaOption(this,this._api);o.length&&p(o,function(t){this.mergeOption(t,e=!0)},this)}return e},mergeOption:function(t){var e=this.option,n=this._componentsMap,i=[];Dr(this),p(t,function(t,n){null!=t&&(cd.hasClass(n)?n&&i.push(n):e[n]=null==e[n]?r(t):o(e[n],t,!0))}),cd.topologicalTravel(i,cd.getAllClassMainTypes(),function(i,r){var o=ln(t[i]),a=fn(n.get(i),o);dn(a),p(a,function(t,e){var n=t.option;S(n)&&(t.keyInfo.mainType=i,t.keyInfo.subType=Gr(i,n,t.exist))});var l=jr(n,r);e[i]=[],n.set(i,[]),p(a,function(t,r){var o=t.exist,a=t.option;if(O(S(a)||o,"Empty component definition"),a){var c=cd.getClass(i,t.keyInfo.subType,!0);if(o&&o instanceof c)o.name=t.keyInfo.name,o.mergeOption(a,this),o.optionUpdated(a,!1);else{var u=s({dependentModels:l,componentIndex:r},t.keyInfo);s(o=new c(a,this,this,u),u),o.init(a,this,this,u),o.optionUpdated(null,!0)}}else o.mergeOption({},this),o.optionUpdated({},!1);n.get(i)[r]=o,e[i][r]=o.option},this),"series"===i&&Ur(this,n.get("series"))},this),this._seriesIndicesMap=F(this._seriesIndices=this._seriesIndices||[])},getOption:function(){var t=r(this.option);return p(t,function(e,n){if(cd.hasClass(n)){for(var i=(e=ln(e)).length-1;i>=0;i--)gn(e[i])&&e.splice(i,1);t[n]=e}}),delete t[Md],t},getTheme:function(){return this._theme},getComponent:function(t,e){var n=this._componentsMap.get(t);if(n)return n[e||0]},queryComponents:function(t){var e=t.mainType;if(!e)return[];var n,i=t.index,r=t.id,o=t.name,a=this._componentsMap.get(e);if(!a||!a.length)return[];if(null!=i)x(i)||(i=[i]),n=m(g(i,function(t){return a[t]}),function(t){return!!t});else if(null!=r){var s=x(r);n=m(a,function(t){return s&&u(r,t.id)>=0||!s&&t.id===r})}else if(null!=o){var l=x(o);n=m(a,function(t){return l&&u(o,t.name)>=0||!l&&t.name===o})}else n=a.slice();return Xr(n,t)},findComponents:function(t){var e=t.query,n=t.mainType,i=function(t){var e=n+"Index",i=n+"Id",r=n+"Name";return!t||null==t[e]&&null==t[i]&&null==t[r]?null:{mainType:n,index:t[e],id:t[i],name:t[r]}}(e);return function(e){return t.filter?m(e,t.filter):e}(Xr(i?this.queryComponents(i):this._componentsMap.get(n),t))},eachComponent:function(t,e,n){var i=this._componentsMap;"function"==typeof t?(n=e,e=t,i.each(function(t,i){p(t,function(t,r){e.call(n,i,t,r)})})):w(t)?p(i.get(t),e,n):S(t)&&p(this.findComponents(t),e,n)},getSeriesByName:function(t){return m(this._componentsMap.get("series"),function(e){return e.name===t})},getSeriesByIndex:function(t){return this._componentsMap.get("series")[t]},getSeriesByType:function(t){return m(this._componentsMap.get("series"),function(e){return e.subType===t})},getSeries:function(){return this._componentsMap.get("series").slice()},getSeriesCount:function(){return this._componentsMap.get("series").length},eachSeries:function(t,e){p(this._seriesIndices,function(n){var i=this._componentsMap.get("series")[n];t.call(e,i,n)},this)},eachRawSeries:function(t,e){p(this._componentsMap.get("series"),t,e)},eachSeriesByType:function(t,e,n){p(this._seriesIndices,function(i){var r=this._componentsMap.get("series")[i];r.subType===t&&e.call(n,r,i)},this)},eachRawSeriesByType:function(t,e,n){return p(this.getSeriesByType(t),e,n)},isSeriesFiltered:function(t){return null==this._seriesIndicesMap.get(t.componentIndex)},getCurrentSeriesIndices:function(){return(this._seriesIndices||[]).slice()},filterSeries:function(t,e){Ur(this,m(this._componentsMap.get("series"),t,e))},restoreData:function(t){var e=this._componentsMap;Ur(this,e.get("series"));var n=[];e.each(function(t,e){n.push(e)}),cd.topologicalTravel(n,cd.getAllClassMainTypes(),function(n,i){p(e.get(n),function(e){("series"!==n||!Hr(e,t))&&e.restoreData()})})}});f(Ad,dd);var Td=["getDom","getZr","getWidth","getHeight","getDevicePixelRatio","dispatchAction","isDisposed","on","off","getDataURL","getConnectedDataURL","getModel","getOption","getViewOfComponentModel","getViewOfSeriesModel"],kd={};Yr.prototype={constructor:Yr,create:function(t,e){var n=[];p(kd,function(i,r){var o=i.create(t,e);n=n.concat(o||[])}),this._coordinateSystems=n},update:function(t,e){p(this._coordinateSystems,function(n){n.update&&n.update(t,e)})},getCoordinateSystems:function(){return this._coordinateSystems.slice()}},Yr.register=function(t,e){kd[t]=e},Yr.get=function(t){return kd[t]};var Pd=p,Cd=r,Id=g,Dd=o,Ld=/^(min|max)?(.+)$/;Zr.prototype={constructor:Zr,setOption:function(t,e){t&&p(ln(t.series),function(t){t&&t.data&&A(t.data)&&R(t.data)}),t=Cd(t,!0);var n=this._optionBackup,i=Kr.call(this,t,e,!n);this._newBaseOption=i.baseOption,n?(eo(n.baseOption,i.baseOption),i.timelineOptions.length&&(n.timelineOptions=i.timelineOptions),i.mediaList.length&&(n.mediaList=i.mediaList),i.mediaDefault&&(n.mediaDefault=i.mediaDefault)):this._optionBackup=i},mountOption:function(t){var e=this._optionBackup;return this._timelineOptions=Id(e.timelineOptions,Cd),this._mediaList=Id(e.mediaList,Cd),this._mediaDefault=Cd(e.mediaDefault),this._currentMediaIndices=[],Cd(t?e.baseOption:this._newBaseOption)},getTimelineOption:function(t){var e,n=this._timelineOptions;if(n.length){var i=t.getComponent("timeline");i&&(e=Cd(n[i.getCurrentIndex()],!0))}return e},getMediaOption:function(t){var e=this._api.getWidth(),n=this._api.getHeight(),i=this._mediaList,r=this._mediaDefault,o=[],a=[];if(!i.length&&!r)return a;for(var s=0,l=i.length;s<l;s++)Jr(i[s].query,e,n)&&o.push(s);return!o.length&&r&&(o=[-1]),o.length&&!to(o,this._currentMediaIndices)&&(a=Id(o,function(t){return Cd(-1===t?r.option:i[t].option)})),this._currentMediaIndices=o,a}};var Od=p,Ed=S,Rd=["areaStyle","lineStyle","nodeStyle","linkStyle","chordStyle","label","labelLine"],Nd=function(t,e){Od(lo(t.series),function(t){Ed(t)&&so(t)});var n=["xAxis","yAxis","radiusAxis","angleAxis","singleAxis","parallelAxis","radar"];e&&n.push("valueAxis","categoryAxis","logAxis","timeAxis"),Od(n,function(e){Od(lo(t[e]),function(t){t&&(oo(t,"axisLabel"),oo(t.axisPointer,"label"))})}),Od(lo(t.parallel),function(t){var e=t&&t.parallelAxisDefault;oo(e,"axisLabel"),oo(e&&e.axisPointer,"label")}),Od(lo(t.calendar),function(t){io(t,"itemStyle"),oo(t,"dayLabel"),oo(t,"monthLabel"),oo(t,"yearLabel")}),Od(lo(t.radar),function(t){oo(t,"name")}),Od(lo(t.geo),function(t){Ed(t)&&(ao(t),Od(lo(t.regions),function(t){ao(t)}))}),Od(lo(t.timeline),function(t){ao(t),io(t,"label"),io(t,"itemStyle"),io(t,"controlStyle",!0);var e=t.data;x(e)&&p(e,function(t){S(t)&&(io(t,"label"),io(t,"itemStyle"))})}),Od(lo(t.toolbox),function(t){io(t,"iconStyle"),Od(t.feature,function(t){io(t,"iconStyle")})}),oo(co(t.axisPointer),"label"),oo(co(t.tooltip).axisPointer,"label")},zd=[["x","left"],["y","top"],["x2","right"],["y2","bottom"]],Fd=["grid","geo","parallel","legend","toolbox","title","visualMap","dataZoom","timeline"],Bd=function(t,e){Nd(t,e),t.series=ln(t.series),p(t.series,function(t){if(S(t)){var e=t.type;if("pie"!==e&&"gauge"!==e||null!=t.clockWise&&(t.clockwise=t.clockWise),"gauge"===e){var n=uo(t,"pointer.color");null!=n&&ho(t,"itemStyle.normal.color",n)}fo(t)}}),t.dataRange&&(t.visualMap=t.dataRange),p(Fd,function(e){var n=t[e];n&&(x(n)||(n=[n]),p(n,function(t){fo(t)}))})},$d=go.prototype;$d.pure=!1,$d.persistent=!0,$d.getSource=function(){return this._source};var Hd={arrayRows_column:{pure:!0,count:function(){return Math.max(0,this._data.length-this._source.startIndex)},getItem:function(t){return this._data[t+this._source.startIndex]},appendData:yo},arrayRows_row:{pure:!0,count:function(){var t=this._data[0];return t?Math.max(0,t.length-this._source.startIndex):0},getItem:function(t){t+=this._source.startIndex;for(var e=[],n=this._data,i=0;i<n.length;i++){var r=n[i];e.push(r?r[t]:null)}return e},appendData:function(){throw new Error('Do not support appendData when set seriesLayoutBy: "row".')}},objectRows:{pure:!0,count:vo,getItem:mo,appendData:yo},keyedColumns:{pure:!0,count:function(){var t=this._source.dimensionsDefine[0].name,e=this._data[t];return e?e.length:0},getItem:function(t){for(var e=[],n=this._source.dimensionsDefine,i=0;i<n.length;i++){var r=this._data[n[i].name];e.push(r?r[t]:null)}return e},appendData:function(t){var e=this._data;p(t,function(t,n){for(var i=e[n]||(e[n]=[]),r=0;r<(t||[]).length;r++)i.push(t[r])})}},original:{count:vo,getItem:mo,appendData:yo},typedArray:{persistent:!1,pure:!0,count:function(){return this._data?this._data.length/this._dimSize:0},getItem:function(t,e){t-=this._offset,e=e||[];for(var n=this._dimSize*t,i=0;i<this._dimSize;i++)e[i]=this._data[n+i];return e},appendData:function(t){this._data=t},clean:function(){this._offset+=this.count(),this._data=null}}},Wd={arrayRows:_o,objectRows:function(t,e,n,i){return null!=n?t[i]:t},keyedColumns:_o,original:function(t,e,n,i){var r=un(t);return null!=n&&r instanceof Array?r[n]:r},typedArray:_o},Vd={arrayRows:xo,objectRows:function(t,e,n,i){return bo(t[e],this._dimensionInfos[e])},keyedColumns:xo,original:function(t,e,n,i){var r=t&&(null==t.value?t:t.value);return!this._rawData.pure&&hn(t)&&(this.hasItemOption=!0),bo(r instanceof Array?r[i]:r,this._dimensionInfos[e])},typedArray:function(t,e,n,i){return t[i]}},jd=/\{@(.+?)\}/g,Gd={getDataParams:function(t,e){var n=this.getData(e),i=this.getRawValue(t,e),r=n.getRawIndex(t),o=n.getName(t),a=n.getRawDataItem(t),s=n.getItemVisual(t,"color");return{componentType:this.mainType,componentSubType:this.subType,seriesType:"series"===this.mainType?this.subType:null,seriesIndex:this.seriesIndex,seriesId:this.id,seriesName:this.name,name:o,dataIndex:r,data:a,dataType:e,value:i,color:s,marker:mr(s),$vars:["seriesName","name","value"]}},getFormattedLabel:function(t,e,n,i,r){e=e||"normal";var o=this.getData(n),a=o.getItemModel(t),s=this.getDataParams(t,n);null!=i&&s.value instanceof Array&&(s.value=s.value[i]);var l=a.get("normal"===e?[r||"label","formatter"]:[e,r||"label","formatter"]);return"function"==typeof l?(s.status=e,l(s)):"string"==typeof l?vr(l,s).replace(jd,function(e,n){var i=n.length;return"["===n.charAt(0)&&"]"===n.charAt(i-1)&&(n=+n.slice(1,i-1)),wo(o,t,n)}):void 0},getRawValue:function(t,e){return wo(this.getData(e),t)},formatTooltip:function(){}},Ud=Ao.prototype;Ud.perform=function(t){function e(t){return!(t>=1)&&(t=1),t}var n,i=this._upstream,r=t&&t.skip;if(this._dirty&&i){var o=this.context;o.data=o.outputData=i.context.outputData}this.__pipeline&&(this.__pipeline.currentTask=this),this._plan&&!r&&(n=this._plan(this.context));var a,s=e(this._modBy),l=this._modDataCount||0,c=e(t&&t.modBy),u=t&&t.modDataCount||0;s===c&&l===u||(n="reset"),(this._dirty||"reset"===n)&&(this._dirty=!1,a=ko(this,r)),this._modBy=c,this._modDataCount=u;var h=t&&t.step;if(this._dueEnd=i?i._outputDueEnd:this._count?this._count(this.context):1/0,this._progress){var f=this._dueIndex,d=Math.min(null!=h?this._dueIndex+h:1/0,this._dueEnd);if(!r&&(a||f<d)){var p=this._progress;if(x(p))for(var g=0;g<p.length;g++)To(this,p[g],f,d,c,u);else To(this,p,f,d,c,u)}this._dueIndex=d;var v=null!=this._settedOutputEnd?this._settedOutputEnd:d;this._outputDueEnd=v}else this._dueIndex=this._outputDueEnd=null!=this._settedOutputEnd?this._settedOutputEnd:this._dueEnd;return this.unfinished()};var Xd=function(){function t(){return i<n?i++:null}function e(){var t=i%a*r+Math.ceil(i/a),e=i>=n?null:t<o?t:i;return i++,e}var n,i,r,o,a,s={reset:function(l,c,u,h){i=l,n=c,r=u,o=h,a=Math.ceil(o/r),s.next=r>1&&o>0?e:t}};return s}();Ud.dirty=function(){this._dirty=!0,this._onDirty&&this._onDirty(this.context)},Ud.unfinished=function(){return this._progress&&this._dueIndex<this._dueEnd},Ud.pipe=function(t){(this._downstream!==t||this._dirty)&&(this._downstream=t,t._upstream=this,t.dirty())},Ud.dispose=function(){this._disposed||(this._upstream&&(this._upstream._downstream=null),this._downstream&&(this._downstream._upstream=null),this._dirty=!1,this._disposed=!0)},Ud.getUpstream=function(){return this._upstream},Ud.getDownstream=function(){return this._downstream},Ud.setOutputEnd=function(t){this._outputDueEnd=this._settedOutputEnd=t};var qd=mn(),Yd=cd.extend({type:"series.__base__",seriesIndex:0,coordinateSystem:null,defaultOption:null,legendDataProvider:null,visualColorAccessPath:"itemStyle.color",layoutMode:null,init:function(t,e,n,i){this.seriesIndex=this.componentIndex,this.dataTask=Mo({count:Io,reset:Do}),this.dataTask.context={model:this},this.mergeDefaultAndTheme(t,n),Lr(this);var r=this.getInitialData(t,n);Oo(r,this),this.dataTask.context.data=r,qd(this).dataBeforeProcessed=r,Po(this)},mergeDefaultAndTheme:function(t,e){var n=this.layoutMode,i=n?Sr(t):{},r=this.subType;cd.hasClass(r)&&(r+="Series"),o(t,e.getTheme().get(this.subType)),o(t,this.getDefaultOption()),cn(t,"label",["show"]),this.fillDataTextStyle(t.data),n&&wr(t,i,n)},mergeOption:function(t,e){t=o(this.option,t,!0),this.fillDataTextStyle(t.data);var n=this.layoutMode;n&&wr(this.option,t,n),Lr(this);var i=this.getInitialData(t,e);Oo(i,this),this.dataTask.dirty(),this.dataTask.context.data=i,qd(this).dataBeforeProcessed=i,Po(this)},fillDataTextStyle:function(t){if(t&&!A(t))for(var e=["show"],n=0;n<t.length;n++)t[n]&&t[n].label&&cn(t[n],"label",e)},getInitialData:function(){},appendData:function(t){this.getRawData().appendData(t.data)},getData:function(t){var e=Ro(this);if(e){var n=e.context.data;return null==t?n:n.getLinkedData(t)}return qd(this).data},setData:function(t){var e=Ro(this);if(e){var n=e.context;n.data!==t&&e.modifyOutputEnd&&e.setOutputEnd(t.count()),n.outputData=t,e!==this.dataTask&&(n.data=t)}qd(this).data=t},getSource:function(){return Ir(this)},getRawData:function(){return qd(this).dataBeforeProcessed},getBaseAxis:function(){var t=this.coordinateSystem;return t&&t.getBaseAxis&&t.getBaseAxis()},formatTooltip:function(t,e,n){function i(t){return gr(pr(t))}var r=this.getData(),o=r.mapDimension("defaultedTooltip",!0),a=o.length,s=this.getRawValue(t),l=x(s),c=r.getItemVisual(t,"color");S(c)&&c.colorStops&&(c=(c.colorStops[0]||{}).color),c=c||"transparent";var u=a>1||l&&!a?function(n){function i(t,n){var i=r.getDimensionInfo(n);if(i&&!1!==i.otherDims.tooltip){var o=i.type,l=mr({color:c,type:"subItem"}),u=(a?l+gr(i.displayName||"-")+": ":"")+gr("ordinal"===o?t+"":"time"===o?e?"":_r("yyyy/MM/dd hh:mm:ss",t):pr(t));u&&s.push(u)}}var a=v(n,function(t,e,n){var i=r.getDimensionInfo(n);return t|(i&&!1!==i.tooltip&&null!=i.displayName)},0),s=[];return o.length?p(o,function(e){i(wo(r,t,e),e)}):p(n,i),(a?"<br/>":"")+s.join(a?"<br/>":", ")}(s):i(a?wo(r,t,o[0]):l?s[0]:s),h=mr(c),f=r.getName(t),d=this.name;return pn(this)||(d=""),d=d?gr(d)+(e?": ":"<br/>"):"",e?h+d+u:d+h+(f?gr(f)+": "+u:u)},isAnimationEnabled:function(){if(xc.node)return!1;var t=this.getShallow("animation");return t&&this.getData().count()>this.getShallow("animationThreshold")&&(t=!1),t},restoreData:function(){this.dataTask.dirty()},getColorFromPalette:function(t,e,n){var i=this.ecModel,r=dd.getColorFromPalette.call(this,t,e,n);return r||(r=i.getColorFromPalette(t,e,n)),r},coordDimToDataDim:function(t){return this.getRawData().mapDimension(t,!0)},getProgressive:function(){return this.get("progressive")},getProgressiveThreshold:function(){return this.get("progressiveThreshold")},getAxisTooltipData:null,getTooltipPosition:null,pipeTask:null,preventIncremental:null,pipelineContext:null});f(Yd,Gd),f(Yd,dd);var Zd=function(){this.group=new pu,this.uid=tr("viewComponent")};Zd.prototype={constructor:Zd,init:function(t,e){},render:function(t,e,n,i){},dispose:function(){}};var Kd=Zd.prototype;Kd.updateView=Kd.updateLayout=Kd.updateVisual=function(t,e,n,i){},Mn(Zd),Pn(Zd,{registerWhenExtend:!0});var Jd=function(){var t=mn();return function(e){var n=t(e),i=e.pipelineContext,r=n.large,o=n.progressiveRender,a=n.large=i.large,s=n.progressiveRender=i.progressiveRender;return!!(r^a||o^s)&&"reset"}},Qd=mn(),tp=Jd();No.prototype={type:"chart",init:function(t,e){},render:function(t,e,n,i){},highlight:function(t,e,n,i){Fo(t.getData(),i,"emphasis")},downplay:function(t,e,n,i){Fo(t.getData(),i,"normal")},remove:function(t,e){this.group.removeAll()},dispose:function(){},incrementalPrepareRender:null,incrementalRender:null,updateTransform:null};var ep=No.prototype;ep.updateView=ep.updateLayout=ep.updateVisual=function(t,e,n,i){this.render(t,e,n,i)},Mn(No),Pn(No,{registerWhenExtend:!0}),No.markUpdateMethod=function(t,e){Qd(t).updateMethod=e};var np={incrementalPrepareRender:{progress:function(t,e){e.view.incrementalRender(t,e.model,e.ecModel,e.api,e.payload)}},render:{forceFirstProgress:!0,progress:function(t,e){e.view.render(e.model,e.ecModel,e.api,e.payload)}}},ip={createOnAllSeries:!0,performRawSeries:!0,reset:function(t,e){var n=t.getData(),i=(t.visualColorAccessPath||"itemStyle.color").split("."),r=t.get(i)||t.getColorFromPalette(t.name,null,e.getSeriesCount());if(n.setVisual("color",r),!e.isSeriesFiltered(t))return"function"!=typeof r||r instanceof Ef||n.each(function(e){n.setItemVisual(e,"color",r(t.getDataParams(e)))}),{dataEach:n.hasItemOption?function(t,e){var n=t.getItemModel(e).get(i,!0);null!=n&&t.setItemVisual(e,"color",n)}:null}}},rp={toolbox:{brush:{title:{rect:"矩形选择",polygon:"圈选",lineX:"横向选择",lineY:"纵向选择",keep:"保持选择",clear:"清除选择"}},dataView:{title:"数据视图",lang:["数据视图","关闭","刷新"]},dataZoom:{title:{zoom:"区域缩放",back:"区域缩放还原"}},magicType:{title:{line:"切换为折线图",bar:"切换为柱状图",stack:"切换为堆叠",tiled:"切换为平铺"}},restore:{title:"还原"},saveAsImage:{title:"保存为图片",lang:["右键另存为图片"]}},series:{typeNames:{pie:"饼图",bar:"柱状图",line:"折线图",scatter:"散点图",effectScatter:"涟漪散点图",radar:"雷达图",tree:"树图",treemap:"矩形树图",boxplot:"箱型图",candlestick:"K线图",k:"K线图",heatmap:"热力图",map:"地图",parallel:"平行坐标图",lines:"线图",graph:"关系图",sankey:"桑基图",funnel:"漏斗图",gauge:"仪表盘图",pictorialBar:"象形柱图",themeRiver:"主题河流图",sunburst:"旭日图"}},aria:{general:{withTitle:"这是一个关于“{title}”的图表。",withoutTitle:"这是一个图表，"},series:{single:{prefix:"",withName:"图表类型是{seriesType}，表示{seriesName}。",withoutName:"图表类型是{seriesType}。"},multiple:{prefix:"它由{seriesCount}个图表系列组成。",withName:"第{seriesId}个系列是一个表示{seriesName}的{seriesType}，",withoutName:"第{seriesId}个系列是一个{seriesType}，",separator:{middle:"；",end:"。"}}},data:{allData:"其数据是——",partialData:"其中，前{displayCnt}项是——",withName:"{name}的数据是{value}",withoutName:"{value}",separator:{middle:"，",end:""}}}},op=function(t,n){function i(t,e){if("string"!=typeof t)return t;var n=t;return p(e,function(t,e){n=n.replace(new RegExp("\\{\\s*"+e+"\\s*\\}","g"),t)}),n}function r(t){var e=a.get(t);if(null==e){for(var n=t.split("."),i=rp.aria,r=0;r<n.length;++r)i=i[n[r]];return i}return e}function o(t){return rp.series.typeNames[t]||"自定义图"}var a=n.getModel("aria");if(a.get("show"))if(a.get("description"))t.setAttribute("aria-label",a.get("description"));else{var s=0;n.eachSeries(function(t,e){++s},this);var l,c=a.get("data.maxCount")||10,u=a.get("series.maxCount")||10,h=Math.min(s,u);if(!(s<1)){var f=function(){var t=n.getModel("title").option;return t&&t.length&&(t=t[0]),t&&t.text}();l=f?i(r("general.withTitle"),{title:f}):r("general.withoutTitle");var d=[];l+=i(r(s>1?"series.multiple.prefix":"series.single.prefix"),{seriesCount:s}),n.eachSeries(function(t,n){if(n<h){var a,l=t.get("name"),u="series."+(s>1?"multiple":"single")+".";a=i(a=r(l?u+"withName":u+"withoutName"),{seriesId:t.seriesIndex,seriesName:t.get("name"),seriesType:o(t.subType)});var f=t.getData();e.data=f,f.count()>c?a+=i(r("data.partialData"),{displayCnt:c}):a+=r("data.allData");for(var p=[],g=0;g<f.count();g++)if(g<c){var v=f.getName(g),m=wo(f,g);p.push(i(r(v?"data.withName":"data.withoutName"),{name:v,value:m}))}a+=p.join(r("data.separator.middle"))+r("data.separator.end"),d.push(a)}}),l+=d.join(r("series.multiple.separator.middle"))+r("series.multiple.separator.end"),t.setAttribute("aria-label",l)}}},ap=Math.PI,sp=Wo.prototype;sp.restoreData=function(t,e){t.restoreData(e),this._stageTaskMap.each(function(t){var e=t.overallTask;e&&e.dirty()})},sp.getPerformArgs=function(t,e){if(t.__pipeline){var n=this._pipelineMap.get(t.__pipeline.id),i=n.context,r=!e&&n.progressiveEnabled&&(!i||i.progressiveRender)&&t.__idxInPipeline>n.blockIndex?n.step:null,o=i&&i.modDataCount;return{step:r,modBy:null!=o?Math.ceil(o/r):null,modDataCount:o}}},sp.getPipeline=function(t){return this._pipelineMap.get(t)},sp.updateStreamModes=function(t,e){var n=this._pipelineMap.get(t.uid),i=t.getData().count(),r=n.progressiveEnabled&&e.incrementalPrepareRender&&i>=n.threshold,o=t.get("large")&&i>=t.get("largeThreshold"),a="mod"===t.get("progressiveChunkMode")?i:null;t.pipelineContext=n.context={progressiveRender:r,modDataCount:a,large:o}},sp.restorePipelines=function(t){var e=this,n=e._pipelineMap=F();t.eachSeries(function(t){var i=t.getProgressive(),r=t.uid;n.set(r,{id:r,head:null,tail:null,threshold:t.getProgressiveThreshold(),progressiveEnabled:i&&!(t.preventIncremental&&t.preventIncremental()),blockIndex:-1,step:Math.round(i||700),count:0}),ta(e,t,t.dataTask)})},sp.prepareStageTasks=function(){var t=this._stageTaskMap,e=this.ecInstance.getModel(),n=this.api;p(this._allHandlers,function(i){var r=t.get(i.uid)||t.set(i.uid,[]);i.reset&&jo(this,i,r,e,n),i.overallReset&&Go(this,i,r,e,n)},this)},sp.prepareView=function(t,e,n,i){var r=t.renderTask,o=r.context;o.model=e,o.ecModel=n,o.api=i,r.__block=!t.incrementalPrepareRender,ta(this,e,r)},sp.performDataProcessorTasks=function(t,e){Vo(this,this._dataProcessorHandlers,t,e,{block:!0})},sp.performVisualTasks=function(t,e,n){Vo(this,this._visualHandlers,t,e,n)},sp.performSeriesTasks=function(t){var e;t.eachSeries(function(t){e|=t.dataTask.perform()}),this.unfinished|=e},sp.plan=function(){this._pipelineMap.each(function(t){var e=t.tail;do{if(e.__block){t.blockIndex=e.__idxInPipeline;break}e=e.getUpstream()}while(e)})};var lp=sp.updatePayload=function(t,e){"remain"!==e&&(t.context.payload=e)},cp=Jo(0);Wo.wrapStageHandler=function(t,e){return b(t)&&(t={overallReset:t,seriesType:ea(t)}),t.uid=tr("stageHandler"),e&&(t.visualType=e),t};var up,hp={},fp={};na(hp,Ad),na(fp,qr),hp.eachSeriesByType=hp.eachRawSeriesByType=function(t){up=t},hp.eachComponent=function(t){"series"===t.mainType&&t.subType&&(up=t.subType)};var dp=["#37A2DA","#32C5E9","#67E0E3","#9FE6B8","#FFDB5C","#ff9f7f","#fb7293","#E062AE","#E690D1","#e7bcf3","#9d96f5","#8378EA","#96BFFF"],pp={color:dp,colorLayer:[["#37A2DA","#ffd85c","#fd7b5f"],["#37A2DA","#67E0E3","#FFDB5C","#ff9f7f","#E062AE","#9d96f5"],["#37A2DA","#32C5E9","#9FE6B8","#FFDB5C","#ff9f7f","#fb7293","#e7bcf3","#8378EA","#96BFFF"],dp]},gp=["#dd6b66","#759aa0","#e69d87","#8dc1a9","#ea7e53","#eedd78","#73a373","#73b9bc","#7289ab","#91ca8c","#f49f42"],vp={color:gp,backgroundColor:"#333",tooltip:{axisPointer:{lineStyle:{color:"#eee"},crossStyle:{color:"#eee"}}},legend:{textStyle:{color:"#eee"}},textStyle:{color:"#eee"},title:{textStyle:{color:"#eee"}},toolbox:{iconStyle:{normal:{borderColor:"#eee"}}},dataZoom:{textStyle:{color:"#eee"}},visualMap:{textStyle:{color:"#eee"}},timeline:{lineStyle:{color:"#eee"},itemStyle:{normal:{color:gp[1]}},label:{normal:{textStyle:{color:"#eee"}}},controlStyle:{normal:{color:"#eee",borderColor:"#eee"}}},timeAxis:{axisLine:{lineStyle:{color:"#eee"}},axisTick:{lineStyle:{color:"#eee"}},axisLabel:{textStyle:{color:"#eee"}},splitLine:{lineStyle:{type:"dashed",color:"#aaa"}},splitArea:{areaStyle:{color:"#eee"}}},logAxis:{axisLine:{lineStyle:{color:"#eee"}},axisTick:{lineStyle:{color:"#eee"}},axisLabel:{textStyle:{color:"#eee"}},splitLine:{lineStyle:{type:"dashed",color:"#aaa"}},splitArea:{areaStyle:{color:"#eee"}}},valueAxis:{axisLine:{lineStyle:{color:"#eee"}},axisTick:{lineStyle:{color:"#eee"}},axisLabel:{textStyle:{color:"#eee"}},splitLine:{lineStyle:{type:"dashed",color:"#aaa"}},splitArea:{areaStyle:{color:"#eee"}}},categoryAxis:{axisLine:{lineStyle:{color:"#eee"}},axisTick:{lineStyle:{color:"#eee"}},axisLabel:{textStyle:{color:"#eee"}},splitLine:{lineStyle:{type:"dashed",color:"#aaa"}},splitArea:{areaStyle:{color:"#eee"}}},line:{symbol:"circle"},graph:{color:gp},gauge:{title:{textStyle:{color:"#eee"}}},candlestick:{itemStyle:{normal:{color:"#FD1050",color0:"#0CF49B",borderColor:"#FD1050",borderColor0:"#0CF49B"}}}};vp.categoryAxis.splitLine.show=!1,cd.extend({type:"dataset",defaultOption:{seriesLayoutBy:bd,sourceHeader:null,dimensions:null,source:null},optionUpdated:function(){Cr(this)}}),Zd.extend({type:"dataset"});var mp=O,yp=p,_p=b,xp=S,bp=cd.parseClassType,wp={zrender:"4.0.4"},Sp=1e3,Mp=1e3,Ap=3e3,Tp={PROCESSOR:{FILTER:Sp,STATISTIC:5e3},VISUAL:{LAYOUT:Mp,GLOBAL:2e3,CHART:Ap,COMPONENT:4e3,BRUSH:5e3}},kp="__flagInMainProcess",Pp="__optionUpdated",Cp=/^[a-zA-Z0-9_]+$/;ra.prototype.on=ia("on"),ra.prototype.off=ia("off"),ra.prototype.one=ia("one"),f(ra,Fc);var Ip=oa.prototype;Ip._onframe=function(){if(!this._disposed){var t=this._scheduler;if(this[Pp]){var e=this[Pp].silent;this[kp]=!0,sa(this),Dp.update.call(this),this[kp]=!1,this[Pp]=!1,ha.call(this,e),fa.call(this,e)}else if(t.unfinished){var n=1,i=this._model;this._api,t.unfinished=!1;do{var r=+new Date;t.performSeriesTasks(i),t.performDataProcessorTasks(i),ca(this,i),t.performVisualTasks(i),ya(this,this._model,0,"remain"),n-=+new Date-r}while(n>0&&t.unfinished);t.unfinished||this._zr.flush()}}},Ip.getDom=function(){return this._dom},Ip.getZr=function(){return this._zr},Ip.setOption=function(t,e,n){var i;if(xp(e)&&(n=e.lazyUpdate,i=e.silent,e=e.notMerge),this[kp]=!0,!this._model||e){var r=new Zr(this._api),o=this._theme,a=this._model=new Ad(null,null,o,r);a.scheduler=this._scheduler,a.init(null,null,o,r)}this._model.setOption(t,Np),n?(this[Pp]={silent:i},this[kp]=!1):(sa(this),Dp.update.call(this),this._zr.flush(),this[Pp]=!1,this[kp]=!1,ha.call(this,i),fa.call(this,i))},Ip.setTheme=function(){console.log("ECharts#setTheme() is DEPRECATED in ECharts 3.0"," at components\\echarts\\echarts.simple.min.js:22")},Ip.getModel=function(){return this._model},Ip.getOption=function(){return this._model&&this._model.getOption()},Ip.getWidth=function(){return this._zr.getWidth()},Ip.getHeight=function(){return this._zr.getHeight()},Ip.getDevicePixelRatio=function(){return this._zr.painter.dpr||e.devicePixelRatio||1},Ip.getRenderedCanvas=function(t){if(xc.canvasSupported)return(t=t||{}).pixelRatio=t.pixelRatio||1,t.backgroundColor=t.backgroundColor||this._model.get("backgroundColor"),this._zr.painter.getRenderedCanvas(t)},Ip.getSvgDataUrl=function(){if(xc.svgSupported){var t=this._zr;return p(t.storage.getDisplayList(),function(t){t.stopAnimation(!0)}),t.painter.pathToDataUrl()}},Ip.getDataURL=function(t){var e=(t=t||{}).excludeComponents,n=this._model,i=[],r=this;yp(e,function(t){n.eachComponent({mainType:t},function(t){var e=r._componentsMap[t.__viewId];e.group.ignore||(i.push(e),e.group.ignore=!0)})});var o="svg"===this._zr.painter.getType()?this.getSvgDataUrl():this.getRenderedCanvas(t).toDataURL("image/"+(t&&t.type||"png"));return yp(i,function(t){t.group.ignore=!1}),o},Ip.getConnectedDataURL=function(t){if(xc.canvasSupported){var e=this.group,n=Math.min,i=Math.max;if(Wp[e]){var o=1/0,a=1/0,s=-1/0,l=-1/0,c=[],u=t&&t.pixelRatio||1;p(Hp,function(u,h){if(u.group===e){var f=u.getRenderedCanvas(r(t)),d=u.getDom().getBoundingClientRect();o=n(d.left,o),a=n(d.top,a),s=i(d.right,s),l=i(d.bottom,l),c.push({dom:f,left:d.left,top:d.top})}});var h=(s*=u)-(o*=u),f=(l*=u)-(a*=u),d=Dc();d.width=h,d.height=f;var g=sn(d);return yp(c,function(t){var e=new Be({style:{x:t.left*u-o,y:t.top*u-a,image:t.dom}});g.add(e)}),g.refreshImmediately(),d.toDataURL("image/"+(t&&t.type||"png"))}return this.getDataURL(t)}},Ip.convertToPixel=_(aa,"convertToPixel"),Ip.convertFromPixel=_(aa,"convertFromPixel"),Ip.containPixel=function(t,e){var n;return t=yn(this._model,t),p(t,function(t,i){i.indexOf("Models")>=0&&p(t,function(t){var r=t.coordinateSystem;if(r&&r.containPoint)n|=!!r.containPoint(e);else if("seriesModels"===i){var o=this._chartsMap[t.__viewId];o&&o.containPoint&&(n|=o.containPoint(e,t))}},this)},this),!!n},Ip.getVisual=function(t,e){var n=(t=yn(this._model,t,{defaultMainType:"series"})).seriesModel.getData(),i=t.hasOwnProperty("dataIndexInside")?t.dataIndexInside:t.hasOwnProperty("dataIndex")?n.indexOfRawIndex(t.dataIndex):null;return null!=i?n.getItemVisual(i,e):n.getVisual(e)},Ip.getViewOfComponentModel=function(t){return this._componentsMap[t.__viewId]},Ip.getViewOfSeriesModel=function(t){return this._chartsMap[t.__viewId]};var Dp={prepareAndUpdate:function(t){sa(this),Dp.update.call(this,t)},update:function(t){var e=this._model,n=this._api,i=this._zr,r=this._coordSysMgr,o=this._scheduler;if(e){o.restoreData(e,t),o.performSeriesTasks(e),r.create(e,n),o.performDataProcessorTasks(e,t),ca(this,e),r.update(e,n),ga(e),o.performVisualTasks(e,t),va(this,e,n,t);var a=e.get("backgroundColor")||"transparent";if(xc.canvasSupported)i.setBackgroundColor(a);else{var s=bt(a);a=Mt(s,"rgb"),0===s[3]&&(a="transparent")}_a(e,n)}},updateTransform:function(t){var e=this._model,n=this,i=this._api;if(e){var r=[];e.eachComponent(function(o,a){var s=n.getViewOfComponentModel(a);if(s&&s.__alive)if(s.updateTransform){var l=s.updateTransform(a,e,i,t);l&&l.update&&r.push(s)}else r.push(s)});var o=F();e.eachSeries(function(r){var a=n._chartsMap[r.__viewId];if(a.updateTransform){var s=a.updateTransform(r,e,i,t);s&&s.update&&o.set(r.uid,1)}else o.set(r.uid,1)}),ga(e),this._scheduler.performVisualTasks(e,t,{setDirty:!0,dirtyMap:o}),ya(n,e,0,t,o),_a(e,this._api)}},updateView:function(t){var e=this._model;e&&(No.markUpdateMethod(t,"updateView"),ga(e),this._scheduler.performVisualTasks(e,t,{setDirty:!0}),va(this,this._model,this._api,t),_a(e,this._api))},updateVisual:function(t){Dp.update.call(this,t)},updateLayout:function(t){Dp.update.call(this,t)}};Ip.resize=function(t){this._zr.resize(t);var e=this._model;if(this._loadingFX&&this._loadingFX.resize(),e){var n=e.resetOption("media"),i=t&&t.silent;this[kp]=!0,n&&sa(this),Dp.update.call(this),this[kp]=!1,ha.call(this,i),fa.call(this,i)}},Ip.showLoading=function(t,e){if(xp(t)&&(e=t,t=""),t=t||"default",this.hideLoading(),$p[t]){var n=$p[t](this._api,e),i=this._zr;this._loadingFX=n,i.add(n)}},Ip.hideLoading=function(){this._loadingFX&&this._zr.remove(this._loadingFX),this._loadingFX=null},Ip.makeActionFromEvent=function(t){var e=s({},t);return e.type=Ep[t.type],e},Ip.dispatchAction=function(t,e){xp(e)||(e={silent:!!e}),Op[t.type]&&this._model&&(this[kp]?this._pendingActions.push(t):(ua.call(this,t,e.silent),e.flush?this._zr.flush(!0):!1!==e.flush&&xc.browser.weChat&&this._throttledZrFlush(),ha.call(this,e.silent),fa.call(this,e.silent)))},Ip.appendData=function(t){var e=t.seriesIndex;this.getModel().getSeriesByIndex(e).appendData(t),this._scheduler.unfinished=!0},Ip.on=ia("on"),Ip.off=ia("off"),Ip.one=ia("one");var Lp=["click","dblclick","mouseover","mouseout","mousemove","mousedown","mouseup","globalout","contextmenu"];Ip._initEvents=function(){yp(Lp,function(t){this._zr.on(t,function(e){var n,i=this.getModel(),r=e.target;if("globalout"===t)n={};else if(r&&null!=r.dataIndex){var o=r.dataModel||i.getSeriesByIndex(r.seriesIndex);n=o&&o.getDataParams(r.dataIndex,r.dataType)||{}}else r&&r.eventData&&(n=s({},r.eventData));n&&(n.event=e,n.type=t,this.trigger(t,n))},this)},this),yp(Ep,function(t,e){this._messageCenter.on(e,function(t){this.trigger(e,t)},this)},this)},Ip.isDisposed=function(){return this._disposed},Ip.clear=function(){this.setOption({series:[]},!0)},Ip.dispose=function(){if(!this._disposed){this._disposed=!0,xn(this.getDom(),Gp,"");var t=this._api,e=this._model;yp(this._componentsViews,function(n){n.dispose(e,t)}),yp(this._chartsViews,function(n){n.dispose(e,t)}),this._zr.dispose(),delete Hp[this.id]}},f(oa,Fc);var Op={},Ep={},Rp=[],Np=[],zp=[],Fp=[],Bp={},$p={},Hp={},Wp={},Vp=new Date-0,jp=new Date-0,Gp="_echarts_instance_",Up={},Xp=Aa;La(2e3,ip),Pa(Bd),Ca(5e3,function(t){var e=F();t.eachSeries(function(t){var n=t.get("stack");if(n){var i=e.get(n)||e.set(n,[]),r=t.getData(),o={stackResultDimension:r.getCalculationInfo("stackResultDimension"),stackedOverDimension:r.getCalculationInfo("stackedOverDimension"),stackedDimension:r.getCalculationInfo("stackedDimension"),stackedByDimension:r.getCalculationInfo("stackedByDimension"),isStackedByIndex:r.getCalculationInfo("isStackedByIndex"),data:r,seriesModel:t};if(!o.stackedDimension||!o.isStackedByIndex&&!o.stackedByDimension)return;i.length&&r.setCalculationInfo("stackedOnSeries",i[i.length-1].seriesModel),i.push(o)}}),e.each(po)}),Ea("default",function(t,e){l(e=e||{},{text:"loading",color:"#c23531",textColor:"#000",maskColor:"rgba(255, 255, 255, 0.8)",zlevel:0});var n=new Pf({style:{fill:e.maskColor},zlevel:e.zlevel,z:1e4}),i=new Lf({shape:{startAngle:-ap/2,endAngle:-ap/2+.1,r:10},style:{stroke:e.color,lineCap:"round",lineWidth:5},zlevel:e.zlevel,z:10001}),r=new Pf({style:{fill:"none",text:e.text,textPosition:"right",textDistance:10,textFill:e.textColor},zlevel:e.zlevel,z:10001});i.animateShape(!0).when(1e3,{endAngle:3*ap/2}).start("circularInOut"),i.animateShape(!0).when(1e3,{startAngle:3*ap/2}).delay(300).start("circularInOut");var o=new pu;return o.add(i),o.add(r),o.add(n),o.resize=function(){var e=t.getWidth()/2,o=t.getHeight()/2;i.setShape({cx:e,cy:o});var a=i.shape.r;r.setShape({x:e-a,y:o-a,width:2*a,height:2*a}),n.setShape({x:0,y:0,width:t.getWidth(),height:t.getHeight()})},o.resize(),o}),Ia({type:"highlight",event:"highlight",update:"highlight"},B),Ia({type:"downplay",event:"downplay",update:"downplay"},B),ka("light",pp),ka("dark",vp);var qp={};Ba.prototype={constructor:Ba,add:function(t){return this._add=t,this},update:function(t){return this._update=t,this},remove:function(t){return this._remove=t,this},execute:function(){var t=this._old,e=this._new,n={},i=[],r=[];for($a(t,{},i,"_oldKeyGetter",this),$a(e,n,r,"_newKeyGetter",this),o=0;o<t.length;o++)null!=(s=n[a=i[o]])?((c=s.length)?(1===c&&(n[a]=null),s=s.unshift()):n[a]=null,this._update&&this._update(s,o)):this._remove&&this._remove(o);for(var o=0;o<r.length;o++){var a=r[o];if(n.hasOwnProperty(a)){var s=n[a];if(null==s)continue;if(s.length)for(var l=0,c=s.length;l<c;l++)this._add&&this._add(s[l]);else this._add&&this._add(s)}}}};var Yp=F(["tooltip","label","itemName","itemId","seriesName"]),Zp=S,Kp="e\0\0",Jp={float:"undefined"==typeof Float64Array?Array:Float64Array,int:"undefined"==typeof Int32Array?Array:Int32Array,ordinal:Array,number:Array,time:Array},Qp="undefined"==typeof Uint32Array?Array:Uint32Array,tg="undefined"==typeof Uint16Array?Array:Uint16Array,eg=["hasItemOption","_nameList","_idList","_invertedIndicesMap","_rawData","_chunkSize","_chunkCount","_dimValueGetter","_count","_rawCount","_nameDimIdx","_idDimIdx"],ng=["_extent","_approximateExtent","_rawExtent"],ig=function(t,e){t=t||["x","y"];for(var n={},i=[],r={},o=0;o<t.length;o++){var a=t[o];w(a)&&(a={name:a});var s=a.name;a.type=a.type||"float",a.coordDim||(a.coordDim=s,a.coordDimIndex=0),a.otherDims=a.otherDims||{},i.push(s),n[s]=a,a.index=o,a.createInvertedIndices&&(r[s]=[])}this.dimensions=i,this._dimensionInfos=n,this.hostModel=e,this.dataType,this._indices=null,this._count=0,this._rawCount=0,this._storage={},this._nameList=[],this._idList=[],this._optionModels=[],this._visual={},this._layout={},this._itemVisuals=[],this.hasItemVisual={},this._itemLayouts=[],this._graphicEls=[],this._chunkSize=1e5,this._chunkCount=0,this._rawData,this._rawExtent={},this._extent={},this._approximateExtent={},this._dimensionsSummary=Ha(this),this._invertedIndicesMap=r,this._calculationInfo={}},rg=ig.prototype;rg.type="list",rg.hasItemOption=!0,rg.getDimension=function(t){return isNaN(t)||(t=this.dimensions[t]||t),t},rg.getDimensionInfo=function(t){return this._dimensionInfos[this.getDimension(t)]},rg.getDimensionsOnCoord=function(){return this._dimensionsSummary.dataDimsOnCoord.slice()},rg.mapDimension=function(t,e){var n=this._dimensionsSummary;if(null==e)return n.encodeFirstDimNotExtra[t];var i=n.encode[t];return!0===e?(i||[]).slice():i&&i[e]},rg.initData=function(t,e,n){(Pr.isInstance(t)||d(t))&&(t=new go(t,this.dimensions.length)),this._rawData=t,this._storage={},this._indices=null,this._nameList=e||[],this._idList=[],this._nameRepeatCount={},n||(this.hasItemOption=!1),this.defaultDimValueGetter=Vd[this._rawData.getSource().sourceFormat],this._dimValueGetter=n=n||this.defaultDimValueGetter,this._rawExtent={},this._initDataFromProvider(0,t.count()),t.pure&&(this.hasItemOption=!1)},rg.getProvider=function(){return this._rawData},rg.appendData=function(t){var e=this._rawData,n=this.count();e.appendData(t);var i=e.count();e.persistent||(i+=n),this._initDataFromProvider(n,i)},rg._initDataFromProvider=function(t,e){if(!(t>=e)){for(var n,i=this._chunkSize,r=this._rawData,o=this._storage,a=this.dimensions,s=a.length,l=this._dimensionInfos,c=this._nameList,u=this._idList,h=this._rawExtent,f=this._nameRepeatCount={},d=this._chunkCount,p=d-1,g=0;g<s;g++){h[T=a[g]]||(h[T]=[1/0,-1/0]);var v=l[T];0===v.otherDims.itemName&&(n=this._nameDimIdx=g),0===v.otherDims.itemId&&(this._idDimIdx=g);var m=Jp[v.type];o[T]||(o[T]=[]);var y=o[T][p];if(y&&y.length<i){for(var _=new m(Math.min(e-p*i,i)),x=0;x<y.length;x++)_[x]=y[x];o[T][p]=_}for(A=d*i;A<e;A+=i)o[T].push(new m(Math.min(e-A,i)));this._chunkCount=o[T].length}for(var b=new Array(s),w=t;w<e;w++){b=r.getItem(w,b);for(var S=Math.floor(w/i),M=w%i,A=0;A<s;A++){var T=a[A],k=o[T][S],P=this._dimValueGetter(b,T,w,A);k[M]=P;var C=h[T];P<C[0]&&(C[0]=P),P>C[1]&&(C[1]=P)}if(!r.pure){var I=c[w];if(b&&null==I)if(null!=b.name)c[w]=I=b.name;else if(null!=n){var D=a[n],L=o[D][S];if(L){I=L[M];var O=l[D].ordinalMeta;O&&O.categories.length&&(I=O.categories[I])}}var E=null==b?null:b.id;null==E&&null!=I&&(f[I]=f[I]||0,E=I,f[I]>0&&(E+="__ec__"+f[I]),f[I]++),null!=E&&(u[w]=E)}}!r.persistent&&r.clean&&r.clean(),this._rawCount=this._count=e,this._extent={},Xa(this)}},rg.count=function(){return this._count},rg.getIndices=function(){var t=this._indices;if(t){var e=t.constructor,n=this._count;if(e===Array)for(i=new e(n),r=0;r<n;r++)i[r]=t[r];else i=new e(t.buffer,0,n)}else for(var i=new(e=ja(this))(this.count()),r=0;r<i.length;r++)i[r]=r;return i},rg.get=function(t,e){if(!(e>=0&&e<this._count))return NaN;var n=this._storage;if(!n[t])return NaN;e=this.getRawIndex(e);var i=Math.floor(e/this._chunkSize),r=e%this._chunkSize;return n[t][i][r]},rg.getByRawIndex=function(t,e){if(!(e>=0&&e<this._rawCount))return NaN;var n=this._storage[t];if(!n)return NaN;var i=Math.floor(e/this._chunkSize),r=e%this._chunkSize;return n[i][r]},rg._getFast=function(t,e){var n=Math.floor(e/this._chunkSize),i=e%this._chunkSize;return this._storage[t][n][i]},rg.getValues=function(t,e){var n=[];x(t)||(e=t,t=this.dimensions);for(var i=0,r=t.length;i<r;i++)n.push(this.get(t[i],e));return n},rg.hasValue=function(t){for(var e=this._dimensionsSummary.dataDimsOnCoord,n=this._dimensionInfos,i=0,r=e.length;i<r;i++)if("ordinal"!==n[e[i]].type&&isNaN(this.get(e[i],t)))return!1;return!0},rg.getDataExtent=function(t){t=this.getDimension(t);var e=[1/0,-1/0];if(!this._storage[t])return e;var n,i=this.count();if(!this._indices)return this._rawExtent[t].slice();if(n=this._extent[t])return n.slice();for(var r=(n=e)[0],o=n[1],a=0;a<i;a++){var s=this._getFast(t,this.getRawIndex(a));s<r&&(r=s),s>o&&(o=s)}return n=[r,o],this._extent[t]=n,n},rg.getApproximateExtent=function(t){return t=this.getDimension(t),this._approximateExtent[t]||this.getDataExtent(t)},rg.setApproximateExtent=function(t,e){e=this.getDimension(e),this._approximateExtent[e]=t.slice()},rg.getCalculationInfo=function(t){return this._calculationInfo[t]},rg.setCalculationInfo=function(t,e){Zp(t)?s(this._calculationInfo,t):this._calculationInfo[t]=e},rg.getSum=function(t){var e=0;if(this._storage[t])for(var n=0,i=this.count();n<i;n++){var r=this.get(t,n);isNaN(r)||(e+=r)}return e},rg.getMedian=function(t){var e=[];this.each(t,function(t,n){isNaN(t)||e.push(t)});var n=[].concat(e).sort(function(t,e){return t-e}),i=this.count();return 0===i?0:i%2==1?n[(i-1)/2]:(n[i/2]+n[i/2-1])/2},rg.rawIndexOf=function(t,e){var n=(t&&this._invertedIndicesMap[t])[e];return null==n||isNaN(n)?-1:n},rg.indexOfName=function(t){for(var e=0,n=this.count();e<n;e++)if(this.getName(e)===t)return e;return-1},rg.indexOfRawIndex=function(t){if(!this._indices)return t;if(t>=this._rawCount||t<0)return-1;var e=this._indices,n=e[t];if(null!=n&&n<this._count&&n===t)return t;for(var i=0,r=this._count-1;i<=r;){var o=(i+r)/2|0;if(e[o]<t)i=o+1;else{if(!(e[o]>t))return o;r=o-1}}return-1},rg.indicesOfNearest=function(t,e,n){var i=[];if(!this._storage[t])return i;null==n&&(n=1/0);for(var r=Number.MAX_VALUE,o=-1,a=0,s=this.count();a<s;a++){var l=e-this.get(t,a),c=Math.abs(l);l<=n&&c<=r&&((c<r||l>=0&&o<0)&&(r=c,o=l,i.length=0),i.push(a))}return i},rg.getRawIndex=Ya,rg.getRawDataItem=function(t){if(this._rawData.persistent)return this._rawData.getItem(this.getRawIndex(t));for(var e=[],n=0;n<this.dimensions.length;n++){var i=this.dimensions[n];e.push(this.get(i,t))}return e},rg.getName=function(t){var e=this.getRawIndex(t);return this._nameList[e]||qa(this,this._nameDimIdx,e)||""},rg.getId=function(t){return Ka(this,this.getRawIndex(t))},rg.each=function(t,e,n,i){if(this._count){"function"==typeof t&&(i=n,n=e,e=t,t=[]),n=n||i||this;for(var r=(t=g(Ja(t),this.getDimension,this)).length,o=0;o<this.count();o++)switch(r){case 0:e.call(n,o);break;case 1:e.call(n,this.get(t[0],o),o);break;case 2:e.call(n,this.get(t[0],o),this.get(t[1],o),o);break;default:for(var a=0,s=[];a<r;a++)s[a]=this.get(t[a],o);s[a]=o,e.apply(n,s)}}},rg.filterSelf=function(t,e,n,i){if(this._count){"function"==typeof t&&(i=n,n=e,e=t,t=[]),n=n||i||this,t=g(Ja(t),this.getDimension,this);for(var r=this.count(),o=new(ja(this))(r),a=[],s=t.length,l=0,c=t[0],u=0;u<r;u++){var h,f=this.getRawIndex(u);if(0===s)h=e.call(n,u);else if(1===s){var d=this._getFast(c,f);h=e.call(n,d,u)}else{for(var p=0;p<s;p++)a[p]=this._getFast(c,f);a[p]=u,h=e.apply(n,a)}h&&(o[l++]=f)}return l<r&&(this._indices=o),this._count=l,this._extent={},this.getRawIndex=this._indices?Za:Ya,this}},rg.selectRange=function(t){if(this._count){var e=[];for(var n in t)t.hasOwnProperty(n)&&e.push(n);var i=e.length;if(i){var r=this.count(),o=new(ja(this))(r),a=0,s=e[0],l=t[s][0],c=t[s][1],u=!1;if(!this._indices){var h=0;if(1===i){for(var f=this._storage[e[0]],d=0;d<this._chunkCount;d++)for(var p=f[d],g=Math.min(this._count-d*this._chunkSize,this._chunkSize),v=0;v<g;v++)((b=p[v])>=l&&b<=c||isNaN(b))&&(o[a++]=h),h++;u=!0}else if(2===i){f=this._storage[s];var m=this._storage[e[1]],y=t[e[1]][0],_=t[e[1]][1];for(d=0;d<this._chunkCount;d++){p=f[d];var x=m[d];for(g=Math.min(this._count-d*this._chunkSize,this._chunkSize),v=0;v<g;v++){var b=p[v],w=x[v];(b>=l&&b<=c||isNaN(b))&&(w>=y&&w<=_||isNaN(w))&&(o[a++]=h),h++}}u=!0}}if(!u)if(1===i)for(v=0;v<r;v++)M=this.getRawIndex(v),((b=this._getFast(s,M))>=l&&b<=c||isNaN(b))&&(o[a++]=M);else for(v=0;v<r;v++){var S=!0,M=this.getRawIndex(v);for(d=0;d<i;d++){var A=e[d];((b=this._getFast(n,M))<t[A][0]||b>t[A][1])&&(S=!1)}S&&(o[a++]=this.getRawIndex(v))}return a<r&&(this._indices=o),this._count=a,this._extent={},this.getRawIndex=this._indices?Za:Ya,this}}},rg.mapArray=function(t,e,n,i){"function"==typeof t&&(i=n,n=e,e=t,t=[]),n=n||i||this;var r=[];return this.each(t,function(){r.push(e&&e.apply(this,arguments))},n),r},rg.map=function(t,e,n,i){n=n||i||this;var r=Qa(this,t=g(Ja(t),this.getDimension,this));r._indices=this._indices,r.getRawIndex=r._indices?Za:Ya;for(var o=r._storage,a=[],s=this._chunkSize,l=t.length,c=this.count(),u=[],h=r._rawExtent,f=0;f<c;f++){for(var d=0;d<l;d++)u[d]=this.get(t[d],f);u[l]=f;var p=e&&e.apply(n,u);if(null!=p){"object"!=typeof p&&(a[0]=p,p=a);for(var v=this.getRawIndex(f),m=Math.floor(v/s),y=v%s,_=0;_<p.length;_++){var x=t[_],b=p[_],w=h[x],S=o[x];S&&(S[m][y]=b),b<w[0]&&(w[0]=b),b>w[1]&&(w[1]=b)}}}return r},rg.downSample=function(t,e,n,i){for(var r=Qa(this,[t]),o=r._storage,a=[],s=Math.floor(1/e),l=o[t],c=this.count(),u=this._chunkSize,h=r._rawExtent[t],f=new(ja(this))(c),d=0,p=0;p<c;p+=s){s>c-p&&(s=c-p,a.length=s);for(var g=0;g<s;g++){var v=this.getRawIndex(p+g),m=Math.floor(v/u),y=v%u;a[g]=l[m][y]}var _=n(a),x=this.getRawIndex(Math.min(p+i(a,_)||0,c-1)),b=x%u;l[Math.floor(x/u)][b]=_,_<h[0]&&(h[0]=_),_>h[1]&&(h[1]=_),f[d++]=x}return r._count=d,r._indices=f,r.getRawIndex=Za,r},rg.getItemModel=function(t){var e=this.hostModel;return new Ki(this.getRawDataItem(t),e,e&&e.ecModel)},rg.diff=function(t){var e=this;return new Ba(t?t.getIndices():[],this.getIndices(),function(e){return Ka(t,e)},function(t){return Ka(e,t)})},rg.getVisual=function(t){var e=this._visual;return e&&e[t]},rg.setVisual=function(t,e){if(Zp(t))for(var n in t)t.hasOwnProperty(n)&&this.setVisual(n,t[n]);else this._visual=this._visual||{},this._visual[t]=e},rg.setLayout=function(t,e){if(Zp(t))for(var n in t)t.hasOwnProperty(n)&&this.setLayout(n,t[n]);else this._layout[t]=e},rg.getLayout=function(t){return this._layout[t]},rg.getItemLayout=function(t){return this._itemLayouts[t]},rg.setItemLayout=function(t,e,n){this._itemLayouts[t]=n?s(this._itemLayouts[t]||{},e):e},rg.clearItemLayouts=function(){this._itemLayouts.length=0},rg.getItemVisual=function(t,e,n){var i=this._itemVisuals[t],r=i&&i[e];return null!=r||n?r:this.getVisual(e)},rg.setItemVisual=function(t,e,n){var i=this._itemVisuals[t]||{},r=this.hasItemVisual;if(this._itemVisuals[t]=i,Zp(e))for(var o in e)e.hasOwnProperty(o)&&(i[o]=e[o],r[o]=!0);else i[e]=n,r[e]=!0},rg.clearAllVisual=function(){this._visual={},this._itemVisuals=[],this.hasItemVisual={}};var og=function(t){t.seriesIndex=this.seriesIndex,t.dataIndex=this.dataIndex,t.dataType=this.dataType};rg.setItemGraphicEl=function(t,e){var n=this.hostModel;e&&(e.dataIndex=t,e.dataType=this.dataType,e.seriesIndex=n&&n.seriesIndex,"group"===e.type&&e.traverse(og,e)),this._graphicEls[t]=e},rg.getItemGraphicEl=function(t){return this._graphicEls[t]},rg.eachItemGraphicEl=function(t,e){p(this._graphicEls,function(n,i){n&&t&&t.call(e,n,i)})},rg.cloneShallow=function(t){if(!t){var e=g(this.dimensions,this.getDimensionInfo,this);t=new ig(e,this.hostModel)}if(t._storage=this._storage,Ua(t,this),this._indices){var n=this._indices.constructor;t._indices=new n(this._indices)}else t._indices=null;return t.getRawIndex=t._indices?Za:Ya,t},rg.wrapMethod=function(t,e){var n=this[t];"function"==typeof n&&(this.__wrappedMethods=this.__wrappedMethods||[],this.__wrappedMethods.push(t),this[t]=function(){var t=n.apply(this,arguments);return e.apply(this,[t].concat(D(arguments)))})},rg.TRANSFERABLE_METHODS=["cloneShallow","downSample","map"],rg.CHANGABLE_METHODS=["filterSelf","selectRange"];var ag=function(t,e){return e=e||{},ns(e.coordDimensions||[],t,{dimsDef:e.dimensionsDefine||t.dimensionsDefine,encodeDef:e.encodeDefine||t.encodeDefine,dimCount:e.dimensionsCount,generateCoord:e.generateCoord,generateCoordCount:e.generateCoordCount})};Yd.extend({type:"series.line",dependencies:["grid","polar"],getInitialData:function(t,e){return ls(this.getSource(),this)},defaultOption:{zlevel:0,z:2,coordinateSystem:"cartesian2d",legendHoverLink:!0,hoverAnimation:!0,clipOverflow:!0,label:{position:"top"},lineStyle:{width:2,type:"solid"},step:!1,smooth:!1,smoothMonotone:null,symbol:"emptyCircle",symbolSize:4,symbolRotate:null,showSymbol:!0,showAllSymbol:"auto",connectNulls:!1,sampling:"none",animationEasing:"linear",progressive:0,hoverLayerThreshold:1/0}});var sg=mi({type:"triangle",shape:{cx:0,cy:0,width:0,height:0},buildPath:function(t,e){var n=e.cx,i=e.cy,r=e.width/2,o=e.height/2;t.moveTo(n,i-o),t.lineTo(n+r,i+o),t.lineTo(n-r,i+o),t.closePath()}}),lg=mi({type:"diamond",shape:{cx:0,cy:0,width:0,height:0},buildPath:function(t,e){var n=e.cx,i=e.cy,r=e.width/2,o=e.height/2;t.moveTo(n,i-o),t.lineTo(n+r,i),t.lineTo(n,i+o),t.lineTo(n-r,i),t.closePath()}}),cg=mi({type:"pin",shape:{x:0,y:0,width:0,height:0},buildPath:function(t,e){var n=e.x,i=e.y,r=e.width/5*3,o=Math.max(r,e.height),a=r/2,s=a*a/(o-a),l=i-o+a+s,c=Math.asin(s/a),u=Math.cos(c)*a,h=Math.sin(c),f=Math.cos(c),d=.6*a,p=.7*a;t.moveTo(n-u,l+s),t.arc(n,l,a,Math.PI-c,2*Math.PI+c),t.bezierCurveTo(n+u-h*d,l+s+f*d,n,i-p,n,i),t.bezierCurveTo(n,i-p,n-u+h*d,l+s+f*d,n-u,l+s),t.closePath()}}),ug=mi({type:"arrow",shape:{x:0,y:0,width:0,height:0},buildPath:function(t,e){var n=e.height,i=e.width,r=e.x,o=e.y,a=i/3*2;t.moveTo(r,o),t.lineTo(r+a,o+n),t.lineTo(r,o+n/4*3),t.lineTo(r-a,o+n),t.lineTo(r,o),t.closePath()}}),hg={line:function(t,e,n,i,r){r.x1=t,r.y1=e+i/2,r.x2=t+n,r.y2=e+i/2},rect:function(t,e,n,i,r){r.x=t,r.y=e,r.width=n,r.height=i},roundRect:function(t,e,n,i,r){r.x=t,r.y=e,r.width=n,r.height=i,r.r=Math.min(n,i)/4},square:function(t,e,n,i,r){var o=Math.min(n,i);r.x=t,r.y=e,r.width=o,r.height=o},circle:function(t,e,n,i,r){r.cx=t+n/2,r.cy=e+i/2,r.r=Math.min(n,i)/2},diamond:function(t,e,n,i,r){r.cx=t+n/2,r.cy=e+i/2,r.width=n,r.height=i},pin:function(t,e,n,i,r){r.x=t+n/2,r.y=e+i/2,r.width=n,r.height=i},arrow:function(t,e,n,i,r){r.x=t+n/2,r.y=e+i/2,r.width=n,r.height=i},triangle:function(t,e,n,i,r){r.cx=t+n/2,r.cy=e+i/2,r.width=n,r.height=i}},fg={};p({line:Cf,rect:Pf,roundRect:Pf,square:Pf,circle:_f,diamond:lg,pin:cg,arrow:ug,triangle:sg},function(t,e){fg[e]=new t});var dg=mi({type:"symbol",shape:{symbolType:"",x:0,y:0,width:0,height:0},beforeBrush:function(){var t=this.style;"pin"===this.shape.symbolType&&"inside"===t.textPosition&&(t.textPosition=["50%","40%"],t.textAlign="center",t.textVerticalAlign="middle")},buildPath:function(t,e,n){var i=e.symbolType,r=fg[i];"none"!==e.symbolType&&(r||(r=fg[i="rect"]),hg[i](e.x,e.y,e.width,e.height,r.shape),r.buildPath(t,r.shape,n))}}),pg=ps.prototype,gg=ps.getSymbolSize=function(t,e){var n=t.getItemVisual(e,"symbolSize");return n instanceof Array?n.slice():[+n,+n]};pg._createSymbol=function(t,e,n,i,r){this.removeAll();var o=fs(t,-1,-1,2,2,e.getItemVisual(n,"color"),r);o.attr({z2:100,culling:!0,scale:gs(i)}),o.drift=vs,this._symbolType=t,this.add(o)},pg.stopSymbolAnimation=function(t){this.childAt(0).stopAnimation(t)},pg.getSymbolPath=function(){return this.childAt(0)},pg.getScale=function(){return this.childAt(0).scale},pg.highlight=function(){this.childAt(0).trigger("emphasis")},pg.downplay=function(){this.childAt(0).trigger("normal")},pg.setZ=function(t,e){var n=this.childAt(0);n.zlevel=t,n.z=e},pg.setDraggable=function(t){var e=this.childAt(0);e.draggable=t,e.cursor=t?"move":"pointer"},pg.updateData=function(t,e,n){this.silent=!1;var i=t.getItemVisual(e,"symbol")||"circle",r=t.hostModel,o=gg(t,e),a=i!==this._symbolType;if(a){var s=t.getItemVisual(e,"symbolKeepAspect");this._createSymbol(i,t,e,o,s)}else(l=this.childAt(0)).silent=!1,Xi(l,{scale:gs(o)},r,e);if(this._updateCommon(t,e,o,n),a){var l=this.childAt(0),c=n&&n.fadeIn,u={scale:l.scale.slice()};c&&(u.style={opacity:l.style.opacity}),l.scale=[0,0],c&&(l.style.opacity=0),qi(l,u,r,e)}this._seriesModel=r};var vg=["itemStyle"],mg=["emphasis","itemStyle"],yg=["label"],_g=["emphasis","label"];pg._updateCommon=function(t,e,n,i){var r=this.childAt(0),o=t.hostModel,a=t.getItemVisual(e,"color");"image"!==r.type&&r.useStyle({strokeNoScale:!0});var l=i&&i.itemStyle,c=i&&i.hoverItemStyle,u=i&&i.symbolRotate,h=i&&i.symbolOffset,f=i&&i.labelModel,d=i&&i.hoverLabelModel,p=i&&i.hoverAnimation,g=i&&i.cursorStyle;if(!i||t.hasItemOption){var v=i&&i.itemModel?i.itemModel:t.getItemModel(e);l=v.getModel(vg).getItemStyle(["color"]),c=v.getModel(mg).getItemStyle(),u=v.getShallow("symbolRotate"),h=v.getShallow("symbolOffset"),f=v.getModel(yg),d=v.getModel(_g),p=v.getShallow("hoverAnimation"),g=v.getShallow("cursor")}else c=s({},c);var m=r.style;r.attr("rotation",(u||0)*Math.PI/180||0),h&&r.attr("position",[ir(h[0],n[0]),ir(h[1],n[1])]),g&&r.attr("cursor",g),r.setColor(a,i&&i.symbolInnerColor),r.setStyle(l);var y=t.getItemVisual(e,"opacity");null!=y&&(m.opacity=y);var _=t.getItemVisual(e,"liftZ"),x=r.__z2Origin;null!=_?null==x&&(r.__z2Origin=r.z2,r.z2+=_):null!=x&&(r.z2=x,r.__z2Origin=null);var b=i&&i.useNameLabel;zi(m,c,f,d,{labelFetcher:o,labelDataIndex:e,defaultText:function(e,n){return b?t.getName(e):ds(t,e)},isRectText:!0,autoColor:a}),r.off("mouseover").off("mouseout").off("emphasis").off("normal"),r.hoverStyle=c,Ni(r);var w=gs(n);if(p&&o.isAnimationEnabled()){var S=function(){if(!this.incremental){var t=w[1]/w[0];this.animateTo({scale:[Math.max(1.1*w[0],w[0]+3),Math.max(1.1*w[1],w[1]+3*t)]},400,"elasticOut")}},M=function(){this.incremental||this.animateTo({scale:w},400,"elasticOut")};r.on("mouseover",S).on("mouseout",M).on("emphasis",S).on("normal",M)}},pg.fadeOut=function(t,e){var n=this.childAt(0);this.silent=n.silent=!0,!(e&&e.keepLabel)&&(n.style.text=null),Xi(n,{style:{opacity:0},scale:[0,0]},this._seriesModel,this.dataIndex,t)},h(ps,pu);var xg=ms.prototype;xg.updateData=function(t,e){e=_s(e);var n=this.group,i=t.hostModel,r=this._data,o=this._symbolCtor,a=xs(t);r||n.removeAll(),t.diff(r).add(function(i){var r=t.getItemLayout(i);if(ys(t,r,i,e)){var s=new o(t,i,a);s.attr("position",r),t.setItemGraphicEl(i,s),n.add(s)}}).update(function(s,l){var c=r.getItemGraphicEl(l),u=t.getItemLayout(s);ys(t,u,s,e)?(c?(c.updateData(t,s,a),Xi(c,{position:u},i)):(c=new o(t,s)).attr("position",u),n.add(c),t.setItemGraphicEl(s,c)):n.remove(c)}).remove(function(t){var e=r.getItemGraphicEl(t);e&&e.fadeOut(function(){n.remove(e)})}).execute(),this._data=t},xg.isPersistent=function(){return!0},xg.updateLayout=function(){var t=this._data;t&&t.eachItemGraphicEl(function(e,n){var i=t.getItemLayout(n);e.attr("position",i)})},xg.incrementalPrepareUpdate=function(t){this._seriesScope=xs(t),this._data=null,this.group.removeAll()},xg.incrementalUpdate=function(t,e,n){n=_s(n);for(var i=t.start;i<t.end;i++){var r=e.getItemLayout(i);if(ys(e,r,i,n)){var o=new this._symbolCtor(e,i,this._seriesScope);o.traverse(function(t){t.isGroup||(t.incremental=t.useHoverLayer=!0)}),o.attr("position",r),this.group.add(o),e.setItemGraphicEl(i,o)}}},xg.remove=function(t){var e=this.group,n=this._data;n&&t?n.eachItemGraphicEl(function(t){t.fadeOut(function(){e.remove(t)})}):e.removeAll()};var bg=function(t,e,n,i,r,o,a,s){for(var l=Ms(t,e),c=[],u=[],h=[],f=[],d=[],p=[],g=[],v=bs(r,e,a),m=bs(o,t,s),y=0;y<l.length;y++){var _=l[y],x=!0;switch(_.cmd){case"=":var b=t.getItemLayout(_.idx),w=e.getItemLayout(_.idx1);(isNaN(b[0])||isNaN(b[1]))&&(b=w.slice()),c.push(b),u.push(w),h.push(n[_.idx]),f.push(i[_.idx1]),g.push(e.getRawIndex(_.idx1));break;case"+":S=_.idx,c.push(r.dataToPoint([e.get(v.dataDimsForPoint[0],S),e.get(v.dataDimsForPoint[1],S)])),u.push(e.getItemLayout(S).slice()),h.push(Ss(v,r,e,S)),f.push(i[S]),g.push(e.getRawIndex(S));break;case"-":var S=_.idx,M=t.getRawIndex(S);M!==S?(c.push(t.getItemLayout(S)),u.push(o.dataToPoint([t.get(m.dataDimsForPoint[0],S),t.get(m.dataDimsForPoint[1],S)])),h.push(n[S]),f.push(Ss(m,o,t,S)),g.push(M)):x=!1}x&&(d.push(_),p.push(p.length))}p.sort(function(t,e){return g[t]-g[e]});var A=[],T=[],k=[],P=[],C=[];for(y=0;y<p.length;y++)S=p[y],A[y]=c[S],T[y]=u[S],k[y]=h[S],P[y]=f[S],C[y]=d[S];return{current:A,next:T,stackedOnCurrent:k,stackedOnNext:P,status:C}},wg=Z,Sg=K,Mg=function(t,e,n,i){return t[0]=e[0]+n[0]*i,t[1]=e[1]+n[1]*i,t},Ag=function(t,e){return t[0]=e[0],t[1]=e[1],t},Tg=[],kg=[],Pg=[],Cg=si.extend({type:"ec-polyline",shape:{points:[],smooth:0,smoothConstraint:!0,smoothMonotone:null,connectNulls:!1},style:{fill:null,stroke:"#000"},brush:bf(si.prototype.brush),buildPath:function(t,e){var n=e.points,i=0,r=n.length,o=Cs(n,e.smoothConstraint);if(e.connectNulls){for(;r>0&&As(n[r-1]);r--);for(;i<r&&As(n[i]);i++);}for(;i<r;)i+=Ts(t,n,i,r,r,1,o.min,o.max,e.smooth,e.smoothMonotone,e.connectNulls)+1}}),Ig=si.extend({type:"ec-polygon",shape:{points:[],stackedOnPoints:[],smooth:0,stackedOnSmooth:0,smoothConstraint:!0,smoothMonotone:null,connectNulls:!1},brush:bf(si.prototype.brush),buildPath:function(t,e){var n=e.points,i=e.stackedOnPoints,r=0,o=n.length,a=e.smoothMonotone,s=Cs(n,e.smoothConstraint),l=Cs(i,e.smoothConstraint);if(e.connectNulls){for(;o>0&&As(n[o-1]);o--);for(;r<o&&As(n[r]);r++);}for(;r<o;){var c=Ts(t,n,r,o,o,1,s.min,s.max,e.smooth,a,e.connectNulls);Ts(t,i,r+c-1,c,o,-1,l.min,l.max,e.stackedOnSmooth,a,e.connectNulls),r+=c+1,t.closePath()}}});No.extend({type:"line",init:function(){var t=new pu,e=new ms;this.group.add(e.group),this._symbolDraw=e,this._lineGroup=t},render:function(t,e,n){var i=t.coordinateSystem,r=this.group,o=t.getData(),a=t.getModel("lineStyle"),s=t.getModel("areaStyle"),c=o.mapArray(o.getItemLayout),u="polar"===i.type,h=this._coordSys,f=this._symbolDraw,d=this._polyline,p=this._polygon,g=this._lineGroup,v=t.get("animation"),m=!s.isEmpty(),y=s.get("origin"),_=Os(i,o,bs(i,o,y)),x=t.get("showSymbol"),b=x&&!u&&Bs(t,o,i),w=this._data;w&&w.eachItemGraphicEl(function(t,e){t.__temp&&(r.remove(t),w.setItemGraphicEl(e,null))}),x||f.remove(),r.add(g);var S=!u&&t.get("step");d&&h.type===i.type&&S===this._step?(m&&!p?p=this._newPolygon(c,_,i,v):p&&!m&&(g.remove(p),p=this._polygon=null),g.setClipPath(Ns(i,!1,!1,t)),x&&f.updateData(o,{isIgnore:b,clipShape:Ns(i,!1,!0,t)}),o.eachItemGraphicEl(function(t){t.stopAnimation(!0)}),Is(this._stackedOnPoints,_)&&Is(this._points,c)||(v?this._updateAnimation(o,_,i,n,S,y):(S&&(c=zs(c,i,S),_=zs(_,i,S)),d.setShape({points:c}),p&&p.setShape({points:c,stackedOnPoints:_})))):(x&&f.updateData(o,{isIgnore:b,clipShape:Ns(i,!1,!0,t)}),S&&(c=zs(c,i,S),_=zs(_,i,S)),d=this._newPolyline(c,i,v),m&&(p=this._newPolygon(c,_,i,v)),g.setClipPath(Ns(i,!0,!1,t)));var M=Fs(o,i)||o.getVisual("color");d.useStyle(l(a.getLineStyle(),{fill:"none",stroke:M,lineJoin:"bevel"}));var A=t.get("smooth");if(A=Ds(t.get("smooth")),d.setShape({smooth:A,smoothMonotone:t.get("smoothMonotone"),connectNulls:t.get("connectNulls")}),p){var T=o.getCalculationInfo("stackedOnSeries"),k=0;p.useStyle(l(s.getAreaStyle(),{fill:M,opacity:.7,lineJoin:"bevel"})),T&&(k=Ds(T.get("smooth"))),p.setShape({smooth:A,stackedOnSmooth:k,smoothMonotone:t.get("smoothMonotone"),connectNulls:t.get("connectNulls")})}this._data=o,this._coordSys=i,this._stackedOnPoints=_,this._points=c,this._step=S,this._valueOrigin=y},dispose:function(){},highlight:function(t,e,n,i){var r=t.getData(),o=vn(r,i);if(!(o instanceof Array)&&null!=o&&o>=0){var a=r.getItemGraphicEl(o);if(!a){var s=r.getItemLayout(o);if(!s)return;(a=new ps(r,o)).position=s,a.setZ(t.get("zlevel"),t.get("z")),a.ignore=isNaN(s[0])||isNaN(s[1]),a.__temp=!0,r.setItemGraphicEl(o,a),a.stopSymbolAnimation(!0),this.group.add(a)}a.highlight()}else No.prototype.highlight.call(this,t,e,n,i)},downplay:function(t,e,n,i){var r=t.getData(),o=vn(r,i);if(null!=o&&o>=0){var a=r.getItemGraphicEl(o);a&&(a.__temp?(r.setItemGraphicEl(o,null),this.group.remove(a)):a.downplay())}else No.prototype.downplay.call(this,t,e,n,i)},_newPolyline:function(t){var e=this._polyline;return e&&this._lineGroup.remove(e),e=new Cg({shape:{points:t},silent:!0,z2:10}),this._lineGroup.add(e),this._polyline=e,e},_newPolygon:function(t,e){var n=this._polygon;return n&&this._lineGroup.remove(n),n=new Ig({shape:{points:t,stackedOnPoints:e},silent:!0}),this._lineGroup.add(n),this._polygon=n,n},_updateAnimation:function(t,e,n,i,r,o){var a=this._polyline,s=this._polygon,l=t.hostModel,c=bg(this._data,t,this._stackedOnPoints,e,this._coordSys,n,this._valueOrigin,o),u=c.current,h=c.stackedOnCurrent,f=c.next,d=c.stackedOnNext;r&&(u=zs(c.current,n,r),h=zs(c.stackedOnCurrent,n,r),f=zs(c.next,n,r),d=zs(c.stackedOnNext,n,r)),a.shape.__points=c.current,a.shape.points=u,Xi(a,{shape:{points:f}},l),s&&(s.setShape({points:u,stackedOnPoints:h}),Xi(s,{shape:{points:f,stackedOnPoints:d}},l));for(var p=[],g=c.status,v=0;v<g.length;v++)if("="===g[v].cmd){var m=t.getItemGraphicEl(g[v].idx1);m&&p.push({el:m,ptIdx:v})}a.animators&&a.animators.length&&a.animators[0].during(function(){for(var t=0;t<p.length;t++)p[t].el.attr("position",a.shape.__points[p[t].ptIdx])})},remove:function(t){var e=this.group,n=this._data;this._lineGroup.removeAll(),this._symbolDraw.remove(!0),n&&n.eachItemGraphicEl(function(t,i){t.__temp&&(e.remove(t),n.setItemGraphicEl(i,null))}),this._polyline=this._polygon=this._coordSys=this._points=this._stackedOnPoints=this._data=null}});var Dg={average:function(t){for(var e=0,n=0,i=0;i<t.length;i++)isNaN(t[i])||(e+=t[i],n++);return 0===n?NaN:e/n},sum:function(t){for(var e=0,n=0;n<t.length;n++)e+=t[n]||0;return e},max:function(t){for(var e=-1/0,n=0;n<t.length;n++)t[n]>e&&(e=t[n]);return isFinite(e)?e:NaN},min:function(t){for(var e=1/0,n=0;n<t.length;n++)t[n]<e&&(e=t[n]);return isFinite(e)?e:NaN},nearest:function(t){return t[0]}},Lg=function(t,e){return Math.round(t.length/2)};Hs.prototype.parse=function(t){return t},Hs.prototype.getSetting=function(t){return this._setting[t]},Hs.prototype.contain=function(t){var e=this._extent;return t>=e[0]&&t<=e[1]},Hs.prototype.normalize=function(t){var e=this._extent;return e[1]===e[0]?.5:(t-e[0])/(e[1]-e[0])},Hs.prototype.scale=function(t){var e=this._extent;return t*(e[1]-e[0])+e[0]},Hs.prototype.unionExtent=function(t){var e=this._extent;t[0]<e[0]&&(e[0]=t[0]),t[1]>e[1]&&(e[1]=t[1])},Hs.prototype.unionExtentFromData=function(t,e){this.unionExtent(t.getApproximateExtent(e))},Hs.prototype.getExtent=function(){return this._extent.slice()},Hs.prototype.setExtent=function(t,e){var n=this._extent;isNaN(t)||(n[0]=t),isNaN(e)||(n[1]=e)},Hs.prototype.isBlank=function(){return this._isBlank},Hs.prototype.setBlank=function(t){this._isBlank=t},Hs.prototype.getLabel=null,Mn(Hs),Pn(Hs,{registerWhenExtend:!0}),Ws.createByAxisModel=function(t){var e=t.option,n=e.data,i=n&&g(n,js);return new Ws({categories:i,needCollect:!i,deduplication:!1!==e.dedplication})};var Og=Ws.prototype;Og.getOrdinal=function(t){return Vs(this).get(t)},Og.parseAndCollect=function(t){var e,n=this._needCollect;if("string"!=typeof t&&!n)return t;if(n&&!this._deduplication)return e=this.categories.length,this.categories[e]=t,e;var i=Vs(this);return null==(e=i.get(t))&&(n?(e=this.categories.length,this.categories[e]=t,i.set(t,e)):e=NaN),e};var Eg=Hs.prototype,Rg=Hs.extend({type:"ordinal",init:function(t,e){t&&!x(t)||(t=new Ws({categories:t})),this._ordinalMeta=t,this._extent=e||[0,t.categories.length-1]},parse:function(t){return"string"==typeof t?this._ordinalMeta.getOrdinal(t):Math.round(t)},contain:function(t){return t=this.parse(t),Eg.contain.call(this,t)&&null!=this._ordinalMeta.categories[t]},normalize:function(t){return Eg.normalize.call(this,this.parse(t))},scale:function(t){return Math.round(Eg.scale.call(this,t))},getTicks:function(){for(var t=[],e=this._extent,n=e[0];n<=e[1];)t.push(n),n++;return t},getLabel:function(t){if(!this.isBlank())return this._ordinalMeta.categories[t]},count:function(){return this._extent[1]-this._extent[0]+1},unionExtentFromData:function(t,e){this.unionExtent(t.getApproximateExtent(e))},getOrdinalMeta:function(){return this._ordinalMeta},niceTicks:B,niceExtent:B});Rg.create=function(){return new Rg};var Ng=rr,zg=rr,Fg=Hs.extend({type:"interval",_interval:0,_intervalPrecision:2,setExtent:function(t,e){var n=this._extent;isNaN(t)||(n[0]=parseFloat(t)),isNaN(e)||(n[1]=parseFloat(e))},unionExtent:function(t){var e=this._extent;t[0]<e[0]&&(e[0]=t[0]),t[1]>e[1]&&(e[1]=t[1]),Fg.prototype.setExtent.call(this,e[0],e[1])},getInterval:function(){return this._interval},setInterval:function(t){this._interval=t,this._niceExtent=this._extent.slice(),this._intervalPrecision=Us(t)},getTicks:function(){return Ys(this._interval,this._extent,this._niceExtent,this._intervalPrecision)},getLabel:function(t,e){if(null==t)return"";var n=e&&e.precision;return null==n?n=or(t)||0:"auto"===n&&(n=this._intervalPrecision),t=zg(t,n,!0),pr(t)},niceTicks:function(t,e,n){t=t||5;var i=this._extent,r=i[1]-i[0];if(isFinite(r)){r<0&&(r=-r,i.reverse());var o=Gs(i,t,e,n);this._intervalPrecision=o.intervalPrecision,this._interval=o.interval,this._niceExtent=o.niceTickExtent}},niceExtent:function(t){var e=this._extent;if(e[0]===e[1])if(0!==e[0]){var n=e[0];t.fixMax?e[0]-=n/2:(e[1]+=n/2,e[0]-=n/2)}else e[1]=1;var i=e[1]-e[0];isFinite(i)||(e[0]=0,e[1]=1),this.niceTicks(t.splitNumber,t.minInterval,t.maxInterval);var r=this._interval;t.fixMin||(e[0]=zg(Math.floor(e[0]/r)*r)),t.fixMax||(e[1]=zg(Math.ceil(e[1]/r)*r))}});Fg.create=function(){return new Fg};var Bg="__ec_stack_",$g="undefined"!=typeof Float32Array?Float32Array:Array,Hg={seriesType:"bar",plan:Jd(),reset:function(t){if(nl(t)&&il(t)){var e=t.getData(),n=t.coordinateSystem,i=n.getBaseAxis(),r=n.getOtherAxis(i),o=e.mapDimension(r.dim),a=e.mapDimension(i.dim),s=r.isHorizontal(),l=s?0:1,c=el(Qs([t]),i,t).width;return c>.5||(c=.5),{progress:function(t,e){for(var u,h=new $g(2*t.count),f=[],d=[],p=0;null!=(u=t.next());)d[l]=e.get(o,u),d[1-l]=e.get(a,u),f=n.dataToPoint(d,null,f),h[p++]=f[0],h[p++]=f[1];e.setLayout({largePoints:h,barWidth:c,valueAxisStart:rl(i,r,!1),valueAxisHorizontal:s})}}}}},Wg=Fg.prototype,Vg=Math.ceil,jg=Math.floor,Gg=function(t,e,n,i){for(;n<i;){var r=n+i>>>1;t[r][1]<e?n=r+1:i=r}return n},Ug=Fg.extend({type:"time",getLabel:function(t){var e=this._stepLvl,n=new Date(t);return _r(e[0],n,this.getSetting("useUTC"))},niceExtent:function(t){var e=this._extent;if(e[0]===e[1]&&(e[0]-=864e5,e[1]+=864e5),e[1]===-1/0&&e[0]===1/0){var n=new Date;e[1]=+new Date(n.getFullYear(),n.getMonth(),n.getDate()),e[0]=e[1]-864e5}this.niceTicks(t.splitNumber,t.minInterval,t.maxInterval);var i=this._interval;t.fixMin||(e[0]=rr(jg(e[0]/i)*i)),t.fixMax||(e[1]=rr(Vg(e[1]/i)*i))},niceTicks:function(t,e,n){t=t||10;var i=this._extent,r=i[1]-i[0],o=r/t;null!=e&&o<e&&(o=e),null!=n&&o>n&&(o=n);var a=Xg.length,s=Gg(Xg,o,0,a),l=Xg[Math.min(s,a-1)],c=l[1];"year"===l[0]&&(c*=dr(r/c/t,!0));var u=this.getSetting("useUTC")?0:60*new Date(+i[0]||+i[1]).getTimezoneOffset()*1e3,h=[Math.round(Vg((i[0]-u)/c)*c+u),Math.round(jg((i[1]-u)/c)*c+u)];qs(h,i),this._stepLvl=l,this._interval=c,this._niceExtent=h},parse:function(t){return+ur(t)}});p(["contain","normalize"],function(t){Ug.prototype[t]=function(e){return Wg[t].call(this,this.parse(e))}});var Xg=[["hh:mm:ss",1e3],["hh:mm:ss",5e3],["hh:mm:ss",1e4],["hh:mm:ss",15e3],["hh:mm:ss",3e4],["hh:mm\nMM-dd",6e4],["hh:mm\nMM-dd",3e5],["hh:mm\nMM-dd",6e5],["hh:mm\nMM-dd",9e5],["hh:mm\nMM-dd",18e5],["hh:mm\nMM-dd",36e5],["hh:mm\nMM-dd",72e5],["hh:mm\nMM-dd",216e5],["hh:mm\nMM-dd",432e5],["MM-dd\nyyyy",864e5],["MM-dd\nyyyy",1728e5],["MM-dd\nyyyy",2592e5],["MM-dd\nyyyy",3456e5],["MM-dd\nyyyy",432e6],["MM-dd\nyyyy",5184e5],["week",6048e5],["MM-dd\nyyyy",864e6],["week",12096e5],["week",18144e5],["month",26784e5],["week",36288e5],["month",53568e5],["week",36288e5],["quarter",8208e6],["month",107136e5],["month",13392e6],["half-year",16416e6],["month",214272e5],["month",26784e6],["year",32832e6]];Ug.create=function(t){return new Ug({useUTC:t.ecModel.get("useUTC")})};var qg=Hs.prototype,Yg=Fg.prototype,Zg=or,Kg=rr,Jg=Math.floor,Qg=Math.ceil,tv=Math.pow,ev=Math.log,nv=Hs.extend({type:"log",base:10,$constructor:function(){Hs.apply(this,arguments),this._originalScale=new Fg},getTicks:function(){var t=this._originalScale,e=this._extent,n=t.getExtent();return g(Yg.getTicks.call(this),function(i){var r=rr(tv(this.base,i));return r=i===e[0]&&t.__fixMin?ol(r,n[0]):r,i===e[1]&&t.__fixMax?ol(r,n[1]):r},this)},getLabel:Yg.getLabel,scale:function(t){return t=qg.scale.call(this,t),tv(this.base,t)},setExtent:function(t,e){var n=this.base;t=ev(t)/ev(n),e=ev(e)/ev(n),Yg.setExtent.call(this,t,e)},getExtent:function(){var t=this.base,e=qg.getExtent.call(this);e[0]=tv(t,e[0]),e[1]=tv(t,e[1]);var n=this._originalScale,i=n.getExtent();return n.__fixMin&&(e[0]=ol(e[0],i[0])),n.__fixMax&&(e[1]=ol(e[1],i[1])),e},unionExtent:function(t){this._originalScale.unionExtent(t);var e=this.base;t[0]=ev(t[0])/ev(e),t[1]=ev(t[1])/ev(e),qg.unionExtent.call(this,t)},unionExtentFromData:function(t,e){this.unionExtent(t.getApproximateExtent(e))},niceTicks:function(t){t=t||10;var e=this._extent,n=e[1]-e[0];if(!(n===1/0||n<=0)){var i=hr(n);for(t/n*i<=.5&&(i*=10);!isNaN(i)&&Math.abs(i)<1&&Math.abs(i)>0;)i*=10;var r=[rr(Qg(e[0]/i)*i),rr(Jg(e[1]/i)*i)];this._interval=i,this._niceExtent=r}},niceExtent:function(t){Yg.niceExtent.call(this,t);var e=this._originalScale;e.__fixMin=t.fixMin,e.__fixMax=t.fixMax}});p(["contain","normalize"],function(t){nv.prototype[t]=function(e){return e=ev(e)/ev(this.base),qg[t].call(this,e)}}),nv.create=function(){return new nv};var iv=function(t){this._axes={},this._dimList=[],this.name=t||""};iv.prototype={constructor:iv,type:"cartesian",getAxis:function(t){return this._axes[t]},getAxes:function(){return g(this._dimList,gl,this)},getAxesByScale:function(t){return t=t.toLowerCase(),m(this.getAxes(),function(e){return e.scale.type===t})},addAxis:function(t){var e=t.dim;this._axes[e]=t,this._dimList.push(e)},dataToCoord:function(t){return this._dataCoordConvert(t,"dataToCoord")},coordToData:function(t){return this._dataCoordConvert(t,"coordToData")},_dataCoordConvert:function(t,e){for(var n=this._dimList,i=t instanceof Array?[]:{},r=0;r<n.length;r++){var o=n[r],a=this._axes[o];i[o]=a[e](t[o])}return i}},vl.prototype={constructor:vl,type:"cartesian2d",dimensions:["x","y"],getBaseAxis:function(){return this.getAxesByScale("ordinal")[0]||this.getAxesByScale("time")[0]||this.getAxis("x")},containPoint:function(t){var e=this.getAxis("x"),n=this.getAxis("y");return e.contain(e.toLocalCoord(t[0]))&&n.contain(n.toLocalCoord(t[1]))},containData:function(t){return this.getAxis("x").containData(t[0])&&this.getAxis("y").containData(t[1])},dataToPoint:function(t,e,n){var i=this.getAxis("x"),r=this.getAxis("y");return n=n||[],n[0]=i.toGlobalCoord(i.dataToCoord(t[0])),n[1]=r.toGlobalCoord(r.dataToCoord(t[1])),n},clampData:function(t,e){var n=this.getAxis("x").scale,i=this.getAxis("y").scale,r=n.getExtent(),o=i.getExtent(),a=n.parse(t[0]),s=i.parse(t[1]);return e=e||[],e[0]=Math.min(Math.max(Math.min(r[0],r[1]),a),Math.max(r[0],r[1])),e[1]=Math.min(Math.max(Math.min(o[0],o[1]),s),Math.max(o[0],o[1])),e},pointToData:function(t,e){var n=this.getAxis("x"),i=this.getAxis("y");return e=e||[],e[0]=n.coordToData(n.toLocalCoord(t[0])),e[1]=i.coordToData(i.toLocalCoord(t[1])),e},getOtherAxis:function(t){return this.getAxis("x"===t.dim?"y":"x")}},h(vl,iv);var rv=mn(),ov=[0,1],av=function(t,e,n){this.dim=t,this.scale=e,this._extent=n||[0,0],this.inverse=!1,this.onBand=!1};av.prototype={constructor:av,contain:function(t){var e=this._extent,n=Math.min(e[0],e[1]),i=Math.max(e[0],e[1]);return t>=n&&t<=i},containData:function(t){return this.contain(this.dataToCoord(t))},getExtent:function(){return this._extent.slice()},getPixelPrecision:function(t){return ar(t||this.scale.getExtent(),this._extent)},setExtent:function(t,e){var n=this._extent;n[0]=t,n[1]=e},dataToCoord:function(t,e){var n=this._extent,i=this.scale;return t=i.normalize(t),this.onBand&&"ordinal"===i.type&&Ll(n=n.slice(),i.count()),nr(t,ov,n,e)},coordToData:function(t,e){var n=this._extent,i=this.scale;this.onBand&&"ordinal"===i.type&&Ll(n=n.slice(),i.count());var r=nr(t,n,ov,e);return this.scale.scale(r)},pointToData:function(t,e){},getTicksCoords:function(t){var e=(t=t||{}).tickModel||this.getTickModel(),n=yl(this,e),i=g(n.ticks,function(t){return{coord:this.dataToCoord(t),tickValue:t}},this),r=e.get("alignWithLabel");return Ol(this,i,n.tickCategoryInterval,r,t.clamp),i},getViewLabels:function(){return ml(this).labels},getLabelModel:function(){return this.model.getModel("axisLabel")},getTickModel:function(){return this.model.getModel("axisTick")},getBandWidth:function(){var t=this._extent,e=this.scale.getExtent(),n=e[1]-e[0]+(this.onBand?1:0);0===n&&(n=1);var i=Math.abs(t[1]-t[0]);return Math.abs(i)/n},isHorizontal:null,getRotate:null,calculateCategoryInterval:function(){return kl(this)}};var sv=function(t,e,n,i,r){av.call(this,t,e,n),this.type=i||"value",this.position=r||"bottom"};sv.prototype={constructor:sv,index:0,getAxesOnZeroOf:null,model:null,isHorizontal:function(){var t=this.position;return"top"===t||"bottom"===t},getGlobalExtent:function(t){var e=this.getExtent();return e[0]=this.toGlobalCoord(e[0]),e[1]=this.toGlobalCoord(e[1]),t&&e[0]>e[1]&&e.reverse(),e},getOtherAxis:function(){this.grid.getOtherAxis()},pointToData:function(t,e){return this.coordToData(this.toLocalCoord(t["x"===this.dim?0:1]),e)},toLocalCoord:null,toGlobalCoord:null},h(sv,av);var lv={show:!0,zlevel:0,z:0,inverse:!1,name:"",nameLocation:"end",nameRotate:null,nameTruncate:{maxWidth:null,ellipsis:"...",placeholder:"."},nameTextStyle:{},nameGap:15,silent:!1,triggerEvent:!1,tooltip:{show:!1},axisPointer:{},axisLine:{show:!0,onZero:!0,onZeroAxisIndex:null,lineStyle:{color:"#333",width:1,type:"solid"},symbol:["none","none"],symbolSize:[10,15]},axisTick:{show:!0,inside:!1,length:5,lineStyle:{width:1}},axisLabel:{show:!0,inside:!1,rotate:0,showMinLabel:null,showMaxLabel:null,margin:8,fontSize:12},splitLine:{show:!0,lineStyle:{color:["#ccc"],width:1,type:"solid"}},splitArea:{show:!1,areaStyle:{color:["rgba(250,250,250,0.3)","rgba(200,200,200,0.3)"]}}},cv={};cv.categoryAxis=o({boundaryGap:!0,deduplication:null,splitLine:{show:!1},axisTick:{alignWithLabel:!1,interval:"auto"},axisLabel:{interval:"auto"}},lv),cv.valueAxis=o({boundaryGap:[0,0],splitNumber:5},lv),cv.timeAxis=l({scale:!0,min:"dataMin",max:"dataMax"},cv.valueAxis),cv.logAxis=l({scale:!0,logBase:10},cv.valueAxis);var uv=["value","category","time","log"],hv=function(t,e,n,i){p(uv,function(r){e.extend({type:t+"Axis."+r,mergeDefaultAndTheme:function(e,i){var a=this.layoutMode,s=a?Sr(e):{};o(e,i.getTheme().get(r+"Axis")),o(e,this.getDefaultOption()),e.type=n(t,e),a&&wr(e,s,a)},optionUpdated:function(){"category"===this.option.type&&(this.__ordinalMeta=Ws.createByAxisModel(this))},getCategories:function(t){var e=this.option;if("category"===e.type)return t?e.data:this.__ordinalMeta.categories},getOrdinalMeta:function(){return this.__ordinalMeta},defaultOption:a([{},cv[r+"Axis"],i],!0)})}),cd.registerSubTypeDefaulter(t+"Axis",_(n,t))},fv={getMin:function(t){var e=this.option,n=t||null==e.rangeStart?e.min:e.rangeStart;return this.axis&&null!=n&&"dataMin"!==n&&"function"!=typeof n&&!k(n)&&(n=this.axis.scale.parse(n)),n},getMax:function(t){var e=this.option,n=t||null==e.rangeEnd?e.max:e.rangeEnd;return this.axis&&null!=n&&"dataMax"!==n&&"function"!=typeof n&&!k(n)&&(n=this.axis.scale.parse(n)),n},getNeedCrossZero:function(){var t=this.option;return null==t.rangeStart&&null==t.rangeEnd&&!t.scale},getCoordSysModel:B,setRange:function(t,e){this.option.rangeStart=t,this.option.rangeEnd=e},resetRange:function(){this.option.rangeStart=this.option.rangeEnd=null}},dv=cd.extend({type:"cartesian2dAxis",axis:null,init:function(){dv.superApply(this,"init",arguments),this.resetRange()},mergeOption:function(){dv.superApply(this,"mergeOption",arguments),this.resetRange()},restoreData:function(){dv.superApply(this,"restoreData",arguments),this.resetRange()},getCoordSysModel:function(){return this.ecModel.queryComponents({mainType:"grid",index:this.option.gridIndex,id:this.option.gridId})[0]}});o(dv.prototype,fv);var pv={offset:0};hv("x",dv,El,pv),hv("y",dv,El,pv),cd.extend({type:"grid",dependencies:["xAxis","yAxis"],layoutMode:"box",coordinateSystem:null,defaultOption:{show:!1,zlevel:0,z:0,left:"10%",top:60,right:"10%",bottom:60,containLabel:!1,backgroundColor:"rgba(0,0,0,0)",borderWidth:1,borderColor:"#ccc"}});var gv=Nl.prototype;gv.type="grid",gv.axisPointerEnabled=!0,gv.getRect=function(){return this._rect},gv.update=function(t,e){var n=this._axesMap;this._updateScale(t,this.model),p(n.x,function(t){ll(t.scale,t.model)}),p(n.y,function(t){ll(t.scale,t.model)}),p(n.x,function(t){zl(n,"y",t)}),p(n.y,function(t){zl(n,"x",t)}),this.resize(this.model,e)},gv.resize=function(t,e,n){function i(){p(o,function(t){var e=t.isHorizontal(),n=e?[0,r.width]:[0,r.height],i=t.inverse?1:0;t.setExtent(n[i],n[1-i]),Bl(t,e?r.x:r.y)})}var r=br(t.getBoxLayoutParams(),{width:e.getWidth(),height:e.getHeight()});this._rect=r;var o=this._axesList;i(),!n&&t.get("containLabel")&&(p(o,function(t){if(!t.model.get("axisLabel.inside")){var e=dl(t);if(e){var n=t.isHorizontal()?"height":"width",i=t.model.get("axisLabel.margin");r[n]-=e[n]+i,"top"===t.position?r.y+=e.height+i:"left"===t.position&&(r.x+=e.width+i)}}}),i())},gv.getAxis=function(t,e){var n=this._axesMap[t];if(null!=n){if(null==e)for(var i in n)if(n.hasOwnProperty(i))return n[i];return n[e]}},gv.getAxes=function(){return this._axesList.slice()},gv.getCartesian=function(t,e){if(null!=t&&null!=e){var n="x"+t+"y"+e;return this._coordsMap[n]}S(t)&&(e=t.yAxisIndex,t=t.xAxisIndex);for(var i=0,r=this._coordsList;i<r.length;i++)if(r[i].getAxis("x").index===t||r[i].getAxis("y").index===e)return r[i]},gv.getCartesians=function(){return this._coordsList.slice()},gv.convertToPixel=function(t,e,n){var i=this._findConvertTarget(t,e);return i.cartesian?i.cartesian.dataToPoint(n):i.axis?i.axis.toGlobalCoord(i.axis.dataToCoord(n)):null},gv.convertFromPixel=function(t,e,n){var i=this._findConvertTarget(t,e);return i.cartesian?i.cartesian.pointToData(n):i.axis?i.axis.coordToData(i.axis.toLocalCoord(n)):null},gv._findConvertTarget=function(t,e){var n,i,r=e.seriesModel,o=e.xAxisModel||r&&r.getReferringComponents("xAxis")[0],a=e.yAxisModel||r&&r.getReferringComponents("yAxis")[0],s=e.gridModel,l=this._coordsList;return r?u(l,n=r.coordinateSystem)<0&&(n=null):o&&a?n=this.getCartesian(o.componentIndex,a.componentIndex):o?i=this.getAxis("x",o.componentIndex):a?i=this.getAxis("y",a.componentIndex):s&&s.coordinateSystem===this&&(n=this._coordsList[0]),{cartesian:n,axis:i}},gv.containPoint=function(t){var e=this._coordsList[0];if(e)return e.containPoint(t)},gv._initCartesian=function(t,e,n){function i(n){return function(i,s){if(Rl(i,t,e)){var l=i.get("position");"x"===n?"top"!==l&&"bottom"!==l&&r[l="bottom"]&&(l="top"===l?"bottom":"top"):"left"!==l&&"right"!==l&&r[l="left"]&&(l="left"===l?"right":"left"),r[l]=!0;var c=new sv(n,cl(i),[0,0],i.get("type"),l),u="category"===c.type;c.onBand=u&&i.get("boundaryGap"),c.inverse=i.get("inverse"),i.axis=c,c.model=i,c.grid=this,c.index=s,this._axesList.push(c),o[n][s]=c,a[n]++}}}var r={left:!1,right:!1,top:!1,bottom:!1},o={x:{},y:{}},a={x:0,y:0};if(e.eachComponent("xAxis",i("x"),this),e.eachComponent("yAxis",i("y"),this),!a.x||!a.y)return this._axesMap={},void(this._axesList=[]);this._axesMap=o,p(o.x,function(e,n){p(o.y,function(i,r){var o="x"+n+"y"+r,a=new vl(o);a.grid=this,a.model=t,this._coordsMap[o]=a,this._coordsList.push(a),a.addAxis(e),a.addAxis(i)},this)},this)},gv._updateScale=function(t,e){function n(t,e,n){p(t.mapDimension(e.dim,!0),function(n){e.scale.unionExtentFromData(t,ss(t,n))})}p(this._axesList,function(t){t.scale.setExtent(1/0,-1/0)}),t.eachSeries(function(i){if(Hl(i)){var r=$l(i),o=r[0],a=r[1];if(!Rl(o,e,t)||!Rl(a,e,t))return;var s=this.getCartesian(o.componentIndex,a.componentIndex),l=i.getData(),c=s.getAxis("x"),u=s.getAxis("y");"list"===l.type&&(n(l,c),n(l,u))}},this)},gv.getTooltipAxes=function(t){var e=[],n=[];return p(this.getCartesians(),function(i){var r=null!=t&&"auto"!==t?i.getAxis(t):i.getBaseAxis(),o=i.getOtherAxis(r);u(e,r)<0&&e.push(r),u(n,o)<0&&n.push(o)}),{baseAxes:e,otherAxes:n}};var vv=["xAxis","yAxis"];Nl.create=function(t,e){var n=[];return t.eachComponent("grid",function(i,r){var o=new Nl(i,t,e);o.name="grid_"+r,o.resize(i,e,!0),i.coordinateSystem=o,n.push(o)}),t.eachSeries(function(t){if(Hl(t)){var e=$l(t),n=e[0],i=e[1],r=n.getCoordSysModel().coordinateSystem;t.coordinateSystem=r.getCartesian(n.componentIndex,i.componentIndex)}}),n},Nl.dimensions=Nl.prototype.dimensions=vl.prototype.dimensions,Yr.register("cartesian2d",Nl);var mv=Math.PI,yv=function(t,e){this.opt=e,this.axisModel=t,l(e,{labelOffset:0,nameDirection:1,tickDirection:1,labelDirection:1,silent:!0}),this.group=new pu;var n=new pu({position:e.position.slice(),rotation:e.rotation});n.updateTransform(),this._transform=n.transform,this._dumbGroup=n};yv.prototype={constructor:yv,hasBuilder:function(t){return!!_v[t]},add:function(t){_v[t].call(this)},getGroup:function(){return this.group}};var _v={axisLine:function(){var t=this.opt,e=this.axisModel;if(e.get("axisLine.show")){var n=this.axisModel.axis.getExtent(),i=this._transform,r=[n[0],0],o=[n[1],0];i&&(Y(r,r,i),Y(o,o,i));var a=s({lineCap:"round"},e.getModel("axisLine.lineStyle").getLineStyle());this.group.add(new Cf(wi({anid:"line",shape:{x1:r[0],y1:r[1],x2:o[0],y2:o[1]},style:a,strokeContainThreshold:t.strokeContainThreshold||5,silent:!0,z2:1})));var l=e.get("axisLine.symbol"),c=e.get("axisLine.symbolSize"),u=e.get("axisLine.symbolOffset")||0;if("number"==typeof u&&(u=[u,u]),null!=l){"string"==typeof l&&(l=[l,l]),"string"!=typeof c&&"number"!=typeof c||(c=[c,c]);var h=c[0],f=c[1];p([{rotate:t.rotation+Math.PI/2,offset:u[0],r:0},{rotate:t.rotation-Math.PI/2,offset:u[1],r:Math.sqrt((r[0]-o[0])*(r[0]-o[0])+(r[1]-o[1])*(r[1]-o[1]))}],function(e,n){if("none"!==l[n]&&null!=l[n]){var i=fs(l[n],-h/2,-f/2,h,f,a.stroke,!0),o=e.r+e.offset,s=[r[0]+o*Math.cos(t.rotation),r[1]-o*Math.sin(t.rotation)];i.attr({rotation:e.rotate,position:s,silent:!0}),this.group.add(i)}},this)}}},axisTickLabel:function(){var t=this.axisModel,e=this.opt,n=Yl(this,t,e);Gl(t,Zl(this,t,e),n)},axisName:function(){var t=this.opt,e=this.axisModel,n=P(t.axisName,e.get("name"));if(n){var i,r,o=e.get("nameLocation"),a=t.nameDirection,l=e.getModel("nameTextStyle"),c=e.get("nameGap")||0,u=this.axisModel.axis.getExtent(),h=u[0]>u[1]?-1:1,f=["start"===o?u[0]-h*c:"end"===o?u[1]+h*c:(u[0]+u[1])/2,ql(o)?t.labelOffset+a*c:0],d=e.get("nameRotate");null!=d&&(d=d*mv/180),ql(o)?i=xv(t.rotation,null!=d?d:t.rotation,a):(i=Vl(t,o,d||0,u),null!=(r=t.axisNameAvailableWidth)&&(r=Math.abs(r/Math.sin(i.rotation)),!isFinite(r)&&(r=null)));var p=l.getFont(),g=e.get("nameTruncate",!0)||{},v=g.ellipsis,m=P(t.nameTruncateMaxWidth,g.maxWidth,r),y=null!=v&&null!=m?id(n,m,p,v,{minChar:2,placeholder:g.placeholder}):n,_=e.get("tooltip",!0),x=e.mainType,b={componentType:x,name:n,$vars:["name"]};b[x+"Index"]=e.componentIndex;var w=new yf({anid:"name",__fullText:n,__truncatedText:y,position:f,rotation:i.rotation,silent:jl(e),z2:1,tooltip:_&&_.show?s({content:n,formatter:function(){return n},formatterParams:b},_):null});Fi(w.style,l,{text:y,textFont:p,textFill:l.getTextColor()||e.get("axisLine.lineStyle.color"),textAlign:i.textAlign,textVerticalAlign:i.textVerticalAlign}),e.get("triggerEvent")&&(w.eventData=Wl(e),w.eventData.targetType="axisName",w.eventData.name=n),this._dumbGroup.add(w),w.updateTransform(),this.group.add(w),w.decomposeTransform()}}},xv=yv.innerTextLayout=function(t,e,n){var i,r,o=lr(e-t);return cr(o)?(r=n>0?"top":"bottom",i="center"):cr(o-mv)?(r=n>0?"bottom":"top",i="center"):(r="middle",i=o>0&&o<mv?n>0?"right":"left":n>0?"left":"right"),{rotation:o,textAlign:i,textVerticalAlign:r}},bv=Ra({type:"axis",_axisPointer:null,axisPointerClass:null,render:function(t,e,n,i){this.axisPointerClass&&Kl(t),bv.superApply(this,"render",arguments),nc(this,t,0,n,0,!0)},updateAxisPointer:function(t,e,n,i,r){nc(this,t,0,n,0,!1)},remove:function(t,e){var n=this._axisPointer;n&&n.remove(e),bv.superApply(this,"remove",arguments)},dispose:function(t,e){ic(this,e),bv.superApply(this,"dispose",arguments)}}),wv=[];bv.registerAxisPointerClass=function(t,e){wv[t]=e},bv.getAxisPointerClass=function(t){return t&&wv[t]};var Sv=["axisLine","axisTickLabel","axisName"],Mv=["splitArea","splitLine"],Av=bv.extend({type:"cartesianAxis",axisPointerClass:"CartesianAxisPointer",render:function(t,e,n,i){this.group.removeAll();var r=this._axisGroup;if(this._axisGroup=new pu,this.group.add(this._axisGroup),t.get("show")){var o=t.getCoordSysModel(),a=rc(o,t),s=new yv(t,a);p(Sv,s.add,s),this._axisGroup.add(s.getGroup()),p(Mv,function(e){t.get(e+".show")&&this["_"+e](t,o)},this),Zi(r,this._axisGroup,t),Av.superCall(this,"render",t,e,n,i)}},remove:function(){this._splitAreaColors=null},_splitLine:function(t,e){var n=t.axis;if(!n.scale.isBlank()){var i=t.getModel("splitLine"),r=i.getModel("lineStyle"),o=r.get("color");o=x(o)?o:[o];for(var a=e.coordinateSystem.getRect(),s=n.isHorizontal(),c=0,u=n.getTicksCoords({tickModel:i}),h=[],f=[],d=r.getLineStyle(),p=0;p<u.length;p++){var g=n.toGlobalCoord(u[p].coord);s?(h[0]=g,h[1]=a.y,f[0]=g,f[1]=a.y+a.height):(h[0]=a.x,h[1]=g,f[0]=a.x+a.width,f[1]=g);var v=c++%o.length,m=u[p].tickValue;this._axisGroup.add(new Cf(wi({anid:null!=m?"line_"+u[p].tickValue:null,shape:{x1:h[0],y1:h[1],x2:f[0],y2:f[1]},style:l({stroke:o[v]},d),silent:!0})))}}},_splitArea:function(t,e){var n=t.axis;if(!n.scale.isBlank()){var i=t.getModel("splitArea"),r=i.getModel("areaStyle"),o=r.get("color"),a=e.coordinateSystem.getRect(),s=n.getTicksCoords({tickModel:i,clamp:!0});if(s.length){var c=o.length,u=this._splitAreaColors,h=F(),f=0;if(u)for(v=0;v<s.length;v++){var d=u.get(s[v].tickValue);if(null!=d){f=(d+(c-1)*v)%c;break}}var p=n.toGlobalCoord(s[0].coord),g=r.getAreaStyle();o=x(o)?o:[o];for(var v=1;v<s.length;v++){var m,y,_,b,w=n.toGlobalCoord(s[v].coord);n.isHorizontal()?(m=p,y=a.y,_=w-m,b=a.height,p=m+_):(m=a.x,y=p,_=a.width,p=y+(b=w-y));var S=s[v-1].tickValue;null!=S&&h.set(S,f),this._axisGroup.add(new Pf({anid:null!=S?"area_"+S:null,shape:{x:m,y:y,width:_,height:b},style:l({fill:o[f]},g),silent:!0})),f=(f+1)%c}this._splitAreaColors=h}}}});Av.extend({type:"xAxis"}),Av.extend({type:"yAxis"}),Ra({type:"grid",render:function(t,e){this.group.removeAll(),t.get("show")&&this.group.add(new Pf({shape:t.coordinateSystem.getRect(),style:l({fill:t.get("backgroundColor")},t.getItemStyle()),silent:!0,z2:-1}))}}),Pa(function(t){t.xAxis&&t.yAxis&&!t.grid&&(t.grid={})}),La(function(t,e,n){return{seriesType:t,performRawSeries:!0,reset:function(t,i,r){var o=t.getData(),a=t.get("symbol")||e,s=t.get("symbolSize"),l=t.get("symbolKeepAspect");if(o.setVisual({legendSymbol:n||a,symbol:a,symbolSize:s,symbolKeepAspect:l}),!i.isSeriesFiltered(t)){var c="function"==typeof s;return{dataEach:o.hasItemOption||c?function(e,n){if("function"==typeof s){var i=t.getRawValue(n),r=t.getDataParams(n);e.setItemVisual(n,"symbolSize",s(i,r))}if(e.hasItemOption){var o=e.getItemModel(n),a=o.getShallow("symbol",!0),l=o.getShallow("symbolSize",!0),c=o.getShallow("symbolKeepAspect",!0);null!=a&&e.setItemVisual(n,"symbol",a),null!=l&&e.setItemVisual(n,"symbolSize",l),null!=c&&e.setItemVisual(n,"symbolKeepAspect",c)}}:null}}}}}("line","circle","line")),Da(function(t){return{seriesType:t,plan:Jd(),reset:function(t){var e=t.getData(),n=t.coordinateSystem,i=t.pipelineContext.large;if(n){var r=g(n.dimensions,function(t){return e.mapDimension(t)}).slice(0,2),o=r.length,a=e.getCalculationInfo("stackResultDimension");return as(e,r[0])&&(r[0]=a),as(e,r[1])&&(r[1]=a),o&&{progress:function(t,e){for(var a=t.end-t.start,s=i&&new Float32Array(a*o),l=t.start,c=0,u=[],h=[];l<t.end;l++){var f;if(1===o)d=e.get(r[0],l),f=!isNaN(d)&&n.dataToPoint(d,null,h);else{var d=u[0]=e.get(r[0],l),p=u[1]=e.get(r[1],l);f=!isNaN(d)&&!isNaN(p)&&n.dataToPoint(u,null,h)}i?(s[c++]=f?f[0]:NaN,s[c++]=f?f[1]:NaN):e.setItemLayout(l,f&&f.slice()||[NaN,NaN])}i&&e.setLayout("symbolPoints",s)}}}}}}("line")),Ca(Tp.PROCESSOR.STATISTIC,function(t){return{seriesType:t,modifyOutputEnd:!0,reset:function(t,e,n){var i=t.getData(),r=t.get("sampling"),o=t.coordinateSystem;if("cartesian2d"===o.type&&r){var a,s=o.getBaseAxis(),l=o.getOtherAxis(s),c=s.getExtent(),u=c[1]-c[0],h=Math.round(i.count()/u);if(h>1)"string"==typeof r?a=Dg[r]:"function"==typeof r&&(a=r),a&&t.setData(i.downSample(i.mapDimension(l.dim),1/h,a,Lg))}}}}("line")),Yd.extend({type:"series.__base_bar__",getInitialData:function(t,e){return ls(this.getSource(),this)},getMarkerPosition:function(t){var e=this.coordinateSystem;if(e){var n=e.dataToPoint(e.clampData(t)),i=this.getData(),r=i.getLayout("offset"),o=i.getLayout("size");return n[e.getBaseAxis().isHorizontal()?0:1]+=r+o/2,n}return[NaN,NaN]},defaultOption:{zlevel:0,z:2,coordinateSystem:"cartesian2d",legendHoverLink:!0,barMinHeight:0,barMinAngle:0,large:!1,largeThreshold:400,progressive:3e3,progressiveChunkMode:"mod",itemStyle:{},emphasis:{}}}).extend({type:"series.bar",dependencies:["grid","polar"],brushSelector:"rect",getProgressive:function(){return!!this.get("large")&&this.get("progressive")},getProgressiveThreshold:function(){var t=this.get("progressiveThreshold"),e=this.get("largeThreshold");return e>t&&(t=e),t}});var Tv=hh([["fill","color"],["stroke","borderColor"],["lineWidth","borderWidth"],["stroke","barBorderColor"],["lineWidth","barBorderWidth"],["opacity"],["shadowBlur"],["shadowOffsetX"],["shadowOffsetY"],["shadowColor"]]),kv={getBarItemStyle:function(t){var e=Tv(this,t);if(this.getBorderLineDash){var n=this.getBorderLineDash();n&&(e.lineDash=n)}return e}},Pv=["itemStyle","barBorderWidth"];s(Ki.prototype,kv),za({type:"bar",render:function(t,e,n){this._updateDrawMode(t);var i=t.get("coordinateSystem");return"cartesian2d"!==i&&"polar"!==i||(this._isLargeDraw?this._renderLarge(t,e,n):this._renderNormal(t,e,n)),this.group},incrementalPrepareRender:function(t,e,n){this._clear(),this._updateDrawMode(t)},incrementalRender:function(t,e,n,i){this._incrementalRenderLarge(t,e)},_updateDrawMode:function(t){var e=t.pipelineContext.large;(null==this._isLargeDraw||e^this._isLargeDraw)&&(this._isLargeDraw=e,this._clear())},_renderNormal:function(t,e,n){var i,r=this.group,o=t.getData(),a=this._data,s=t.coordinateSystem,l=s.getBaseAxis();"cartesian2d"===s.type?i=l.isHorizontal():"polar"===s.type&&(i="angle"===l.dim);var c=t.isAnimationEnabled()?t:null;o.diff(a).add(function(e){if(o.hasValue(e)){var n=o.getItemModel(e),a=Iv[s.type](o,e,n),l=Cv[s.type](o,e,n,a,i,c);o.setItemGraphicEl(e,l),r.add(l),cc(l,o,e,n,a,t,i,"polar"===s.type)}}).update(function(e,n){var l=a.getItemGraphicEl(n);if(o.hasValue(e)){var u=o.getItemModel(e),h=Iv[s.type](o,e,u);l?Xi(l,{shape:h},c,e):l=Cv[s.type](o,e,u,h,i,c,!0),o.setItemGraphicEl(e,l),r.add(l),cc(l,o,e,u,h,t,i,"polar"===s.type)}else r.remove(l)}).remove(function(t){var e=a.getItemGraphicEl(t);"cartesian2d"===s.type?e&&sc(t,c,e):e&&lc(t,c,e)}).execute(),this._data=o},_renderLarge:function(t,e,n){this._clear(),hc(t,this.group)},_incrementalRenderLarge:function(t,e){hc(e,this.group,!0)},dispose:B,remove:function(t){this._clear(t)},_clear:function(t){var e=this.group,n=this._data;t&&t.get("animation")&&n&&!this._isLargeDraw?n.eachItemGraphicEl(function(e){"sector"===e.type?lc(e.dataIndex,t,e):sc(e.dataIndex,t,e)}):e.removeAll(),this._data=null}});var Cv={cartesian2d:function(t,e,n,i,r,o,a){var l=new Pf({shape:s({},i)});if(o){var c=l.shape,u=r?"height":"width",h={};c[u]=0,h[u]=i[u],Wf[a?"updateProps":"initProps"](l,{shape:h},o,e)}return l},polar:function(t,e,n,i,r,o,a){var s=i.startAngle<i.endAngle,c=new wf({shape:l({clockwise:s},i)});if(o){var u=c.shape,h=r?"r":"endAngle",f={};u[h]=r?0:i.startAngle,f[h]=i[h],Wf[a?"updateProps":"initProps"](c,{shape:f},o,e)}return c}},Iv={cartesian2d:function(t,e,n){var i=t.getItemLayout(e),r=uc(n,i),o=i.width>0?1:-1,a=i.height>0?1:-1;return{x:i.x+o*r/2,y:i.y+a*r/2,width:i.width-o*r,height:i.height-a*r}},polar:function(t,e,n){var i=t.getItemLayout(e);return{cx:i.cx,cy:i.cy,r0:i.r0,r:i.r,startAngle:i.startAngle,endAngle:i.endAngle}}},Dv=si.extend({type:"largeBar",shape:{points:[]},buildPath:function(t,e){for(var n=e.points,i=this.__startPoint,r=this.__valueIdx,o=0;o<n.length;o+=2)i[this.__valueIdx]=n[o+r],t.moveTo(i[0],i[1]),t.lineTo(n[o],n[o+1])}});Da(_(function(t,e){var n=Js(t,e),i=Qs(n),r={};p(n,function(t){var e=t.getData(),n=t.coordinateSystem,o=n.getBaseAxis(),a=Zs(t),s=i[Ks(o)][a],l=s.offset,c=s.width,u=n.getOtherAxis(o),h=t.get("barMinHeight")||0;r[a]=r[a]||[],e.setLayout({offset:l,size:c});for(var f=e.mapDimension(u.dim),d=e.mapDimension(o.dim),p=as(e,f),g=u.isHorizontal(),v=rl(o,u,p),m=0,y=e.count();m<y;m++){var _=e.get(f,m),x=e.get(d,m);if(!isNaN(_)){var b,w,S,M,A=_>=0?"p":"n",T=v;if(p&&(r[a][x]||(r[a][x]={p:v,n:v}),T=r[a][x][A]),g)b=T,w=(k=n.dataToPoint([_,x]))[1]+l,S=k[0]-v,M=c,Math.abs(S)<h&&(S=(S<0?-1:1)*h),p&&(r[a][x][A]+=S);else{var k=n.dataToPoint([x,_]);b=k[0]+l,w=T,S=c,M=k[1]-v,Math.abs(M)<h&&(M=(M<=0?-1:1)*h),p&&(r[a][x][A]+=M)}e.setItemLayout(m,{x:b,y:w,width:S,height:M})}}},this)},"bar")),Da(Hg),La({seriesType:"bar",reset:function(t){t.getData().setVisual("legendSymbol","roundRect")}});var Lv=function(t,e,n){e=x(e)&&{coordDimensions:e}||s({},e);var i=t.getSource(),r=ag(i,e),o=new ig(r,t);return o.initData(i,n),o},Ov={updateSelectedMap:function(t){this._targetList=x(t)?t.slice():[],this._selectTargetMap=v(t||[],function(t,e){return t.set(e.name,e),t},F())},select:function(t,e){var n=null!=e?this._targetList[e]:this._selectTargetMap.get(t);"single"===this.get("selectedMode")&&this._selectTargetMap.each(function(t){t.selected=!1}),n&&(n.selected=!0)},unSelect:function(t,e){var n=null!=e?this._targetList[e]:this._selectTargetMap.get(t);n&&(n.selected=!1)},toggleSelected:function(t,e){var n=null!=e?this._targetList[e]:this._selectTargetMap.get(t);if(null!=n)return this[n.selected?"unSelect":"select"](t,e),n.selected},isSelected:function(t,e){var n=null!=e?this._targetList[e]:this._selectTargetMap.get(t);return n&&n.selected}},Ev=Na({type:"series.pie",init:function(t){Ev.superApply(this,"init",arguments),this.legendDataProvider=function(){return this.getRawData()},this.updateSelectedMap(this._createSelectableList()),this._defaultLabelLine(t)},mergeOption:function(t){Ev.superCall(this,"mergeOption",t),this.updateSelectedMap(this._createSelectableList())},getInitialData:function(t,e){return Lv(this,["value"])},_createSelectableList:function(){for(var t=this.getRawData(),e=t.mapDimension("value"),n=[],i=0,r=t.count();i<r;i++)n.push({name:t.getName(i),value:t.get(e,i),selected:So(t,i,"selected")});return n},getDataParams:function(t){var e=this.getData(),n=Ev.superCall(this,"getDataParams",t),i=[];return e.each(e.mapDimension("value"),function(t){i.push(t)}),n.percent=sr(i,t,e.hostModel.get("percentPrecision")),n.$vars.push("percent"),n},_defaultLabelLine:function(t){cn(t,"labelLine",["show"]);var e=t.labelLine,n=t.emphasis.labelLine;e.show=e.show&&t.label.show,n.show=n.show&&t.emphasis.label.show},defaultOption:{zlevel:0,z:2,legendHoverLink:!0,hoverAnimation:!0,center:["50%","50%"],radius:[0,"75%"],clockwise:!0,startAngle:90,minAngle:0,selectedOffset:10,hoverOffset:10,avoidLabelOverlap:!0,percentPrecision:2,stillShowZeroSum:!0,label:{rotate:!1,show:!0,position:"outer"},labelLine:{show:!0,length:15,length2:15,smooth:!1,lineStyle:{width:1,type:"solid"}},itemStyle:{borderWidth:1},animationType:"expansion",animationEasing:"cubicOut"}});f(Ev,Ov);var Rv=gc.prototype;Rv.updateData=function(t,e,n){function i(){o.stopAnimation(!0),o.animateTo({shape:{r:u.r+a.get("hoverOffset")}},300,"elasticOut")}function r(){o.stopAnimation(!0),o.animateTo({shape:{r:u.r}},300,"elasticOut")}var o=this.childAt(0),a=t.hostModel,c=t.getItemModel(e),u=t.getItemLayout(e),h=s({},u);h.label=null,n?(o.setShape(h),"scale"===a.getShallow("animationType")?(o.shape.r=u.r0,qi(o,{shape:{r:u.r}},a,e)):(o.shape.endAngle=u.startAngle,Xi(o,{shape:{endAngle:u.endAngle}},a,e))):Xi(o,{shape:h},a,e);var f=t.getItemVisual(e,"color");o.useStyle(l({lineJoin:"bevel",fill:f},c.getModel("itemStyle").getItemStyle())),o.hoverStyle=c.getModel("emphasis.itemStyle").getItemStyle();var d=c.getShallow("cursor");d&&o.attr("cursor",d),pc(this,t.getItemLayout(e),a.isSelected(null,e),a.get("selectedOffset"),a.get("animation")),o.off("mouseover").off("mouseout").off("emphasis").off("normal"),c.get("hoverAnimation")&&a.isAnimationEnabled()&&o.on("mouseover",i).on("mouseout",r).on("emphasis",i).on("normal",r),this._updateLabel(t,e),Ni(this)},Rv._updateLabel=function(t,e){var n=this.childAt(1),i=this.childAt(2),r=t.hostModel,o=t.getItemModel(e),a=t.getItemLayout(e).label,s=t.getItemVisual(e,"color");Xi(n,{shape:{points:a.linePoints||[[a.x,a.y],[a.x,a.y],[a.x,a.y]]}},r,e),Xi(i,{style:{x:a.x,y:a.y}},r,e),i.attr({rotation:a.rotation,origin:[a.x,a.y],z2:10});var l=o.getModel("label"),c=o.getModel("emphasis.label"),u=o.getModel("labelLine"),h=o.getModel("emphasis.labelLine");s=t.getItemVisual(e,"color");zi(i.style,i.hoverStyle={},l,c,{labelFetcher:t.hostModel,labelDataIndex:e,defaultText:t.getName(e),autoColor:s,useInsideStyle:!!a.inside},{textAlign:a.textAlign,textVerticalAlign:a.verticalAlign,opacity:t.getItemVisual(e,"opacity")}),i.ignore=i.normalIgnore=!l.get("show"),i.hoverIgnore=!c.get("show"),n.ignore=n.normalIgnore=!u.get("show"),n.hoverIgnore=!h.get("show"),n.setStyle({stroke:s,opacity:t.getItemVisual(e,"opacity")}),n.setStyle(u.getModel("lineStyle").getLineStyle()),n.hoverStyle=h.getModel("lineStyle").getLineStyle();var f=u.get("smooth");f&&!0===f&&(f=.4),n.setShape({smooth:f})},h(gc,pu),No.extend({type:"pie",init:function(){var t=new pu;this._sectorGroup=t},render:function(t,e,n,i){if(!i||i.from!==this.uid){var r=t.getData(),o=this._data,a=this.group,s=e.get("animation"),l=!o,c=t.get("animationType"),u=_(dc,this.uid,t,s,n),h=t.get("selectedMode");if(r.diff(o).add(function(t){var e=new gc(r,t);l&&"scale"!==c&&e.eachChild(function(t){t.stopAnimation(!0)}),h&&e.on("click",u),r.setItemGraphicEl(t,e),a.add(e)}).update(function(t,e){var n=o.getItemGraphicEl(e);n.updateData(r,t),n.off("click"),h&&n.on("click",u),a.add(n),r.setItemGraphicEl(t,n)}).remove(function(t){var e=o.getItemGraphicEl(t);a.remove(e)}).execute(),s&&l&&r.count()>0&&"scale"!==c){var f=r.getItemLayout(0),d=Math.max(n.getWidth(),n.getHeight())/2,p=y(a.removeClipPath,a);a.setClipPath(this._createClipPath(f.cx,f.cy,d,f.startAngle,f.clockwise,p,t))}this._data=r}},dispose:function(){},_createClipPath:function(t,e,n,i,r,o,a){var s=new wf({shape:{cx:t,cy:e,r0:0,r:n,startAngle:i,endAngle:i,clockwise:r}});return qi(s,{shape:{endAngle:i+(r?1:-1)*Math.PI*2}},a,o),s},containPoint:function(t,e){var n=e.getData().getItemLayout(0);if(n){var i=t[0]-n.cx,r=t[1]-n.cy,o=Math.sqrt(i*i+r*r);return o<=n.r&&o>=n.r0}}});var Nv=function(t,e,n,i){var r,o,a=t.getData(),s=[],l=!1;a.each(function(n){var i,c,u,h,f=a.getItemLayout(n),d=a.getItemModel(n),p=d.getModel("label"),g=p.get("position")||d.get("emphasis.label.position"),v=d.getModel("labelLine"),m=v.get("length"),y=v.get("length2"),_=(f.startAngle+f.endAngle)/2,x=Math.cos(_),b=Math.sin(_);r=f.cx,o=f.cy;var w="inside"===g||"inner"===g;if("center"===g)i=f.cx,c=f.cy,h="center";else{var S=(w?(f.r+f.r0)/2*x:f.r*x)+r,M=(w?(f.r+f.r0)/2*b:f.r*b)+o;if(i=S+3*x,c=M+3*b,!w){var A=S+x*(m+e-f.r),T=M+b*(m+e-f.r),k=A+(x<0?-1:1)*y,P=T;i=k+(x<0?-5:5),c=P,u=[[S,M],[A,T],[k,P]]}h=w?"center":x>0?"left":"right"}var C=p.getFont(),I=p.get("rotate")?x<0?-_+Math.PI:-_:0,D=ie(t.getFormattedLabel(n,"normal")||a.getName(n),C,h,"top");l=!!I,f.label={x:i,y:c,position:g,height:D.height,len:m,len2:y,linePoints:u,textAlign:h,verticalAlign:"middle",rotation:I,inside:w},w||s.push(f.label)}),!l&&t.get("avoidLabelOverlap")&&mc(s,r,o,e,n,i)},zv=2*Math.PI,Fv=Math.PI/180;!function(t,e){p(e,function(e){e.update="updateView",Ia(e,function(n,i){var r={};return i.eachComponent({mainType:"series",subType:t,query:n},function(t){t[e.method]&&t[e.method](n.name,n.dataIndex);var i=t.getData();i.each(function(e){var n=i.getName(e);r[n]=t.isSelected(n)||!1})}),{name:n.name,selected:r}})})}("pie",[{type:"pieToggleSelect",event:"pieselectchanged",method:"toggleSelected"},{type:"pieSelect",event:"pieselected",method:"select"},{type:"pieUnSelect",event:"pieunselected",method:"unSelect"}]),La(function(t){return{getTargetSeries:function(e){var n={},i=F();return e.eachSeriesByType(t,function(t){t.__paletteScope=n,i.set(t.uid,t)}),i},reset:function(t,e){var n=t.getRawData(),i={},r=t.getData();r.each(function(t){var e=r.getRawIndex(t);i[e]=t}),n.each(function(e){var o=i[e],a=null!=o&&r.getItemVisual(o,"color",!0);if(a)n.setItemVisual(e,"color",a);else{var s=n.getItemModel(e).get("itemStyle.color")||t.getColorFromPalette(n.getName(e)||e+"",t.__paletteScope,n.count());n.setItemVisual(e,"color",s),null!=o&&r.setItemVisual(o,"color",s)}})}}}("pie")),Da(_(function(t,e,n,i){e.eachSeriesByType(t,function(t){var e=t.getData(),i=e.mapDimension("value"),r=t.get("center"),o=t.get("radius");x(o)||(o=[0,o]),x(r)||(r=[r,r]);var a=n.getWidth(),s=n.getHeight(),l=Math.min(a,s),c=ir(r[0],a),u=ir(r[1],s),h=ir(o[0],l/2),f=ir(o[1],l/2),d=-t.get("startAngle")*Fv,p=t.get("minAngle")*Fv,g=0;e.each(i,function(t){!isNaN(t)&&g++});var v=e.getSum(i),m=Math.PI/(v||g)*2,y=t.get("clockwise"),_=t.get("roseType"),b=t.get("stillShowZeroSum"),w=e.getDataExtent(i);w[0]=0;var S=zv,M=0,A=d,T=y?1:-1;if(e.each(i,function(t,n){var i;if(isNaN(t))e.setItemLayout(n,{angle:NaN,startAngle:NaN,endAngle:NaN,clockwise:y,cx:c,cy:u,r0:h,r:_?NaN:f});else{(i="area"!==_?0===v&&b?m:t*m:zv/g)<p?(i=p,S-=p):M+=t;var r=A+T*i;e.setItemLayout(n,{angle:i,startAngle:A,endAngle:r,clockwise:y,cx:c,cy:u,r0:h,r:_?nr(t,w,[h,f]):f}),A=r}}),S<zv&&g)if(S<=.001){var k=zv/g;e.each(i,function(t,n){if(!isNaN(t)){var i=e.getItemLayout(n);i.angle=k,i.startAngle=d+T*n*k,i.endAngle=d+T*(n+1)*k}})}else m=S/M,A=d,e.each(i,function(t,n){if(!isNaN(t)){var i=e.getItemLayout(n),r=i.angle===p?p:t*m;i.startAngle=A,i.endAngle=A+T*r,A+=T*r}});Nv(t,f,a,s)})},"pie")),Ca(function(t){return{seriesType:t,reset:function(t,e){var n=e.findComponents({mainType:"legend"});if(n&&n.length){var i=t.getData();i.filterSelf(function(t){for(var e=i.getName(t),r=0;r<n.length;r++)if(!n[r].isSelected(e))return!1;return!0})}}}}("pie")),t.version="4.1.0",t.dependencies=wp,t.PRIORITY=Tp,t.init=function(t,e,n){var i=Ta(t);if(i)return i;var r=new oa(t,e,n);return r.id="ec_"+Vp++,Hp[r.id]=r,xn(t,Gp,r.id),Ma(r),r},t.connect=function(t){if(x(t)){var e=t;t=null,yp(e,function(e){null!=e.group&&(t=e.group)}),t=t||"g_"+jp++,yp(e,function(e){e.group=t})}return Wp[t]=!0,t},t.disConnect=Aa,t.disconnect=Xp,t.dispose=function(t){"string"==typeof t?t=Hp[t]:t instanceof oa||(t=Ta(t)),t instanceof oa&&!t.isDisposed()&&t.dispose()},t.getInstanceByDom=Ta,t.getInstanceById=function(t){return Hp[t]},t.registerTheme=ka,t.registerPreprocessor=Pa,t.registerProcessor=Ca,t.registerPostUpdate=function(t){zp.push(t)},t.registerAction=Ia,t.registerCoordinateSystem=function(t,e){Yr.register(t,e)},t.getCoordinateSystemDimensions=function(t){var e=Yr.get(t);if(e)return e.getDimensionsInfo?e.getDimensionsInfo():e.dimensions.slice()},t.registerLayout=Da,t.registerVisual=La,t.registerLoading=Ea,t.extendComponentModel=function(t){return cd.extend(t)},t.extendComponentView=Ra,t.extendSeriesModel=Na,t.extendChartView=za,t.setCanvasCreator=function(t){i("createCanvas",t)},t.registerMap=function(t,e,n){e.geoJson&&!e.features&&(n=e.specialAreas,e=e.geoJson),"string"==typeof e&&(e="undefined"!=typeof JSON&&JSON.parse?JSON.parse(e):new Function("return ("+e+");")()),Up[t]={geoJson:e,specialAreas:n}},t.getMap=function(t){return Up[t]},t.dataTool=qp})},"172e":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("4ea5"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},"21cd":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("c110"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},2877:function(t,e,n){"use strict";function i(t,e,n,i,r,o,a,s){var l,c="function"===typeof t?t.options:t;if(e&&(c.render=e,c.staticRenderFns=n,c._compiled=!0),i&&(c.functional=!0),o&&(c._scopeId="data-v-"+o),a?(l=function(t){t=t||this.$vnode&&this.$vnode.ssrContext||this.parent&&this.parent.$vnode&&this.parent.$vnode.ssrContext,t||"undefined"===typeof __VUE_SSR_CONTEXT__||(t=__VUE_SSR_CONTEXT__),r&&r.call(this,t),t&&t._registeredComponents&&t._registeredComponents.add(a)},c._ssrRegister=l):r&&(l=s?function(){r.call(this,this.$root.$options.shadowRoot)}:r),l)if(c.functional){c._injectStyles=l;var u=c.render;c.render=function(t,e){return l.call(e),u(t,e)}}else{var h=c.beforeCreate;c.beforeCreate=h?[].concat(h,l):[l]}return{exports:t,options:c}}n.d(e,"a",function(){return i})},2981:function(t,e,n){"use strict";(function(e){var n={yAxisWidth:15,yAxisSplit:5,xAxisHeight:15,xAxisLineHeight:15,legendHeight:15,yAxisTitleWidth:15,padding:12,pixelRatio:1,rotate:!1,columePadding:3,fontSize:13,dataPointShape:["circle","circle","circle","circle"],colors:["#1890ff","#2fc25b","#facc14","#f04864","#8543e0","#90ed7d"],pieChartLinePadding:15,pieChartTextPadding:5,xAxisTextPadding:3,titleColor:"#333333",titleFontSize:20,subtitleColor:"#999999",subtitleFontSize:15,toolTipPadding:3,toolTipBackground:"#000000",toolTipOpacity:.7,toolTipLineHeight:20,radarGridCount:3,radarLabelTextMargin:15,gaugeLabelTextMargin:15};function i(t,e){if(null==t)throw new TypeError("Cannot convert undefined or null to object");for(var n=Object(t),i=1;i<arguments.length;i++){var r=arguments[i];if(null!=r)for(var o in r)Object.prototype.hasOwnProperty.call(r,o)&&(n[o]=r[o])}return n}var r={toFixed:function(t,e){return e=e||2,this.isFloat(t)&&(t=t.toFixed(e)),t},isFloat:function(t){return t%1!==0},approximatelyEqual:function(t,e){return Math.abs(t-e)<1e-10},isSameSign:function(t,e){return Math.abs(t)===t&&Math.abs(e)===e||Math.abs(t)!==t&&Math.abs(e)!==e},isSameXCoordinateArea:function(t,e){return this.isSameSign(t.x,e.x)},isCollision:function(t,e){t.end={},t.end.x=t.start.x+t.width,t.end.y=t.start.y-t.height,e.end={},e.end.x=e.start.x+e.width,e.end.y=e.start.y-e.height;var n=e.start.x>t.end.x||e.end.x<t.start.x||e.end.y>t.start.y||e.start.y<t.end.y;return!n}};function o(t,e){var n=/^#?([a-f\d])([a-f\d])([a-f\d])$/i,i=t.replace(n,function(t,e,n,i){return e+e+n+n+i+i}),r=/^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(i),o=parseInt(r[1],16),a=parseInt(r[2],16),s=parseInt(r[3],16);return"rgba("+o+","+a+","+s+","+e+")"}function a(t,e,n){if(isNaN(t))throw new Error("[wxCharts] unvalid series data!");n=n||10,e=e||"upper";var i=1;while(n<1)n*=10,i*=10;t="upper"===e?Math.ceil(t*i):Math.floor(t*i);while(t%n!==0)"upper"===e?t++:t--;return t/i}function s(t,e,n,i){var r=i.width-n.padding-e.xAxisPoints[0],o=e.eachSpacing*i.categories.length,a=t;return t>=0?a=0:Math.abs(t)>=o-r&&(a=r-o),a}function l(t,e,n){function i(t){while(t<0)t+=2*Math.PI;while(t>2*Math.PI)t-=2*Math.PI;return t}return t=i(t),e=i(e),n=i(n),e>n&&(n+=2*Math.PI,t<e&&(t+=2*Math.PI)),t>=e&&t<=n}function c(t,e,n){var i=t,r=n-e,o=i+(n-r-i)/Math.sqrt(2);o*=-1;var a=(n-r)*(Math.sqrt(2)-1)-(n-r-i)/Math.sqrt(2);return{transX:o,transY:a}}function u(t,e){function n(t,e){return!(!t[e-1]||!t[e+1])&&(t[e].y>=Math.max(t[e-1].y,t[e+1].y)||t[e].y<=Math.min(t[e-1].y,t[e+1].y))}var i=.2,r=.2,o=null,a=null,s=null,l=null;if(e<1?(o=t[0].x+(t[1].x-t[0].x)*i,a=t[0].y+(t[1].y-t[0].y)*i):(o=t[e].x+(t[e+1].x-t[e-1].x)*i,a=t[e].y+(t[e+1].y-t[e-1].y)*i),e>t.length-3){var c=t.length-1;s=t[c].x-(t[c].x-t[c-1].x)*r,l=t[c].y-(t[c].y-t[c-1].y)*r}else s=t[e+1].x-(t[e+2].x-t[e].x)*r,l=t[e+1].y-(t[e+2].y-t[e].y)*r;return n(t,e+1)&&(l=t[e+1].y),n(t,e)&&(a=t[e].y),{ctrA:{x:o,y:a},ctrB:{x:s,y:l}}}function h(t,e,n){return{x:n.x+t,y:n.y-e}}function f(t,e){if(e)while(r.isCollision(t,e))t.start.x>0?t.start.y--:t.start.x<0?t.start.y++:t.start.y>0?t.start.y++:t.start.y--;return t}function d(t,e){var n=0;return t.map(function(t){return t.color||(t.color=e.colors[n],n=(n+1)%e.colors.length),t})}function p(t,e){return t.map(function(t){return t.type||(t.type=e.type),t})}function g(t,e){var n=0,i=e-t;return n=i>=1e4?1e3:i>=1e3?100:i>=100?10:i>=10?5:i>=1?1:i>=.1?.1:.01,{minRange:a(t,"lower",n),maxRange:a(e,"upper",n)}}function v(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:n.fontSize;t=String(t);t=t.split("");var i=0;return t.forEach(function(t){/[a-zA-Z]/.test(t)?i+=7:/[0-9]/.test(t)?i+=5.5:/\./.test(t)?i+=2.7:/-/.test(t)?i+=3.25:/[\u4e00-\u9fa5]/.test(t)?i+=10:/\(|\)/.test(t)?i+=3.73:/\s/.test(t)?i+=2.5:/%/.test(t)?i+=8:i+=10}),i*e/10}function m(t){return t.reduce(function(t,e){return(t.data?t.data:t).concat(e.data)},[])}function y(t){for(var e=new Array(t[0].data.length),n=0;n<e.length;n++)e[n]=0;for(var i=0;i<t.length;i++)for(n=0;n<e.length;n++)e[n]+=t[i].data[n];return t.reduce(function(t,n){return(t.data?t.data:t).concat(n.data).concat(e)},[])}function _(t,e,n){var i,r;return t.clientX?e.rotate?(r=e.height-t.clientX*e.pixelRatio,i=(t.pageY-n.mp.currentTarget.offsetTop-e.height/e.pixelRatio/2*(e.pixelRatio-1))*e.pixelRatio):(i=t.clientX*e.pixelRatio,r=(t.pageY-n.mp.currentTarget.offsetTop-e.height/e.pixelRatio/2*(e.pixelRatio-1))*e.pixelRatio):e.rotate?(r=e.height-t.x*e.pixelRatio,i=t.y*e.pixelRatio):(i=t.x*e.pixelRatio,r=t.y*e.pixelRatio),{x:i,y:r}}function x(t,e){var n=[];return t.forEach(function(t){if(null!==t.data[e]&&"undefined"!==typeof t.data[e]){var i={};i.color=t.color,i.name=t.name,i.data=t.format?t.format(t.data[e]):t.data[e],i.count=t.count,i.nameGroup=t.nameGroup,n.push(i)}}),n}function b(t){var e=t.map(function(t){return v(t)});return Math.max.apply(null,e)}function w(t){for(var e=2*Math.PI/t,n=[],i=0;i<t;i++)n.push(e*i);return n.map(function(t){return-1*t+Math.PI/2})}function S(t,e,n,i){var r=arguments.length>4&&void 0!==arguments[4]?arguments[4]:{},o=t.map(function(t){return{text:r.format?r.format(t,i[n]):t.name+": "+t.data,color:t.color}}),a=[],s={x:0,y:0};return e.forEach(function(t){"undefined"!==typeof t[n]&&null!==t[n]&&a.push(t[n])}),a.forEach(function(t){s.x=Math.round(t.x),s.y+=t.y}),s.y/=a.length,{textList:o,offset:s}}function M(t,e,n,i,r,o){arguments.length>6&&void 0!==arguments[6]&&arguments[6];var a=o.color.upFill,s=o.color.downFill,l=[a,a,s,a],c=[],u={text:r[i],color:null};c.push(u),e.map(function(e){0==i&&e.data[1]-e.data[0]<0?l[1]=s:(e.data[0]<t[i-1][1]&&(l[0]=s),e.data[1]<e.data[0]&&(l[1]=s),e.data[2]>t[i-1][1]&&(l[2]=a),e.data[3]<t[i-1][1]&&(l[3]=s));var n={text:"开盘："+e.data[0],color:l[0]},r={text:"收盘："+e.data[1],color:l[1]},o={text:"最低："+e.data[2],color:l[2]},u={text:"最高："+e.data[3],color:l[3]};c.push(n,r,o,u)});var h=[],f={x:0,y:0};return n.forEach(function(t){"undefined"!==typeof t[i]&&null!==t[i]&&h.push(t[i])}),f.x=Math.round(h[0][0].x),{textList:c,offset:f}}function A(t,e,n,i){var r=arguments.length>4&&void 0!==arguments[4]?arguments[4]:0,o=-1;return T(t,n,i)&&e.forEach(function(e,n){t.x+r>e&&(o=n)}),o}function T(t,e,n){return t.x<e.width-n.padding&&t.x>n.padding+n.yAxisWidth+n.yAxisTitleWidth&&t.y>n.padding&&t.y<e.height-n.legendHeight-n.xAxisHeight-n.padding}function k(t,e,n){var i=2*Math.PI/n,r=-1;if(C(t,e.center,e.radius)){var o=function(t){return t<0&&(t+=2*Math.PI),t>2*Math.PI&&(t-=2*Math.PI),t},a=Math.atan2(e.center.y-t.y,t.x-e.center.x);a*=-1,a<0&&(a+=2*Math.PI);var s=e.angleList.map(function(t){return t=o(-1*t),t});s.forEach(function(t,e){var n=o(t-i/2),s=o(t+i/2);s<n&&(s+=2*Math.PI),(a>=n&&a<=s||a+2*Math.PI>=n&&a+2*Math.PI<=s)&&(r=e)})}return r}function P(t,e){var n=-1;if(C(t,e.center,e.radius)){var i=Math.atan2(e.center.y-t.y,t.x-e.center.x);i=-i;for(var r=0,o=e.series.length;r<o;r++){var a=e.series[r];if(l(i,a._start_,a._start_+2*a._proportion_*Math.PI)){n=r;break}}}return n}function C(t,e,n){return Math.pow(t.x-e.x,2)+Math.pow(t.y-e.y,2)<=Math.pow(n,2)}function I(t){var e=[],n=[];return t.forEach(function(t,i){null!==t?n.push(t):(n.length&&e.push(n),n=[])}),n.length&&e.push(n),e}function D(t,e,n){if(!1===e.legend)return{legendList:[],legendHeight:0};var i=5*e.pixelRatio,r=8*e.pixelRatio,o=15*e.pixelRatio,a=[],s=0,l=[];return t.forEach(function(t){var n=3*i+o+v(t.name||"undefined");s+n>e.width?(a.push(l),s=n,l=[t]):(s+=n,l.push(t))}),l.length&&a.push(l),{legendList:a,legendHeight:a.length*(n.fontSize+r)+i}}function L(t,e,n){var i={angle:0,xAxisHeight:n.xAxisHeight},r=W(t,e,n),o=r.eachSpacing,a=t.map(function(t){return v(t)}),s=Math.max.apply(this,a);return 1==e.xAxis.rotateLabel&&s+2*n.xAxisTextPadding>o&&(i.angle=45*Math.PI/180,i.xAxisHeight=2*n.xAxisTextPadding+s*Math.sin(i.angle)),i}function O(t,e,n,i,r){var o=arguments.length>5&&void 0!==arguments[5]?arguments[5]:1,a=r.extra.radar||{};a.max=a.max||0;var s=Math.max(a.max,Math.max.apply(null,m(i))),l=[];return i.forEach(function(i){var r={};r.color=i.color,r.data=[],i.data.forEach(function(i,a){var l={};l.angle=t[a],l.proportion=i/s,l.position=h(n*l.proportion*o*Math.cos(l.angle),n*l.proportion*o*Math.sin(l.angle),e),r.data.push(l)}),l.push(r)}),l}function E(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:1,n=0,i=0;return t.forEach(function(t){t.data=null===t.data?0:t.data,n+=t.data}),t.forEach(function(t){t.data=null===t.data?0:t.data,t._proportion_=t.data/n*e}),t.forEach(function(t){t._start_=i,i+=2*t._proportion_*Math.PI}),t}function R(t,e){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:1;return 1==n&&(n=.999999),t.forEach(function(t){var i;t.data=null===t.data?0:t.data,i="default"==e.type?e.startAngle-e.endAngle+1:2,t._proportion_=i*t.data*n+e.startAngle,t._proportion_>=2&&(t._proportion_=t._proportion_%2)}),t}function N(t,e,n){for(var i=e-n+1,r=e,o=0;o<t.length;o++)t[o].value=null===t[o].value?0:t[o].value,t[o]._startAngle_=r,t[o]._endAngle_=i*t[o].value+e,t[o]._endAngle_>=2&&(t[o]._endAngle_=t[o]._endAngle_%2),r=t[o]._endAngle_;return t}function z(t,e,n){var i=arguments.length>3&&void 0!==arguments[3]?arguments[3]:1;return t.forEach(function(t){if(t.data=null===t.data?0:t.data,"auto"==n.pointer.color){for(var r=0;r<e.length;r++)if(t.data<=e[r].value){t.color=e[r].color;break}}else t.color=n.pointer.color;var o=n.startAngle-n.endAngle+1;t._endAngle_=o*t.data+n.startAngle,t._oldAngle_=n.oldAngle,n.oldAngle<n.endAngle&&(t._oldAngle_+=2),t.data>=n.oldData?t._proportion_=(t._endAngle_-t._oldAngle_)*i+n.oldAngle:t._proportion_=t._oldAngle_-(t._oldAngle_-t._endAngle_)*i,t._proportion_>=2&&(t._proportion_=t._proportion_%2)}),t}function F(t){t=E(t);var e=0;return t.forEach(function(t){var n=t.format?t.format(+t._proportion_.toFixed(2)):r.toFixed(100*t._proportion_)+"%";e=Math.max(e,v(n))}),e}function B(t,e,n,i,r,o){return t.map(function(t){return null===t?null:(t.width=(e-2*r.columePadding)/n,o.extra.column&&o.extra.column.width&&+o.extra.column.width>0?t.width=Math.min(t.width,+o.extra.column.width):t.width=Math.min(t.width,25),t.x+=(i+.5-n/2)*t.width,t)})}function $(t,e,n,i,r,o,a){return t.map(function(t){return null===t?null:(t.width=e-2*r.columePadding,o.extra.column&&o.extra.column.width&&+o.extra.column.width>0?t.width=Math.min(t.width,+o.extra.column.width):t.width=Math.min(t.width,25),i>0&&(t.width-=2*a),t)})}function H(t,e,n,i,r,o,a){return t.map(function(t,n){return null===t?null:(t.width=e-2*r.columePadding,o.extra.column&&o.extra.column.width&&+o.extra.column.width>0?t.width=Math.min(t.width,+o.extra.column.width):t.width=Math.min(t.width,25),t)})}function W(t,e,n){var i=n.yAxisWidth+n.yAxisTitleWidth,r=e.width-2*n.padding-i,o=e.enableScroll?Math.min(e.xAxis.itemCount,t.length):t.length,a=r/o,s=[],l=n.padding+i,c=e.width-n.padding;return t.forEach(function(t,e){s.push(l+e*a)}),!0===e.enableScroll?s.push(l+t.length*a):s.push(c),{xAxisPoints:s,startX:l,endX:c,eachSpacing:a}}function V(t,e,n,i,r,o,a){var s=arguments.length>7&&void 0!==arguments[7]?arguments[7]:1,l=[],c=o.height-2*a.padding-a.xAxisHeight-a.legendHeight;return t.forEach(function(t,u){if(null===t)l.push(null);else{var h=[];t.forEach(function(t,l){var f={};f.x=i[u]+Math.round(r/2);var d=t.value||t,p=c*(d-e)/(n-e);p*=s,f.y=o.height-a.xAxisHeight-a.legendHeight-Math.round(p)-a.padding,h.push(f)}),l.push(h)}}),l}function j(t,e,n,i,r,o,a){var s=arguments.length>7&&void 0!==arguments[7]?arguments[7]:1,l=[],c=o.height-2*a.padding-a.xAxisHeight-a.legendHeight;return t.forEach(function(t,u){if(null===t)l.push(null);else{var h={};h.color=t.color,h.x=i[u]+Math.round(r/2);var f=t.value||t,d=c*(f-e)/(n-e);d*=s,h.y=o.height-a.xAxisHeight-a.legendHeight-Math.round(d)-a.padding,l.push(h)}}),l}function G(t,e,n,i,r,o,a,s,l){var c=arguments.length>9&&void 0!==arguments[9]?arguments[9]:1,u=[],h=o.height-2*a.padding-a.xAxisHeight-a.legendHeight;return t.forEach(function(t,f){if(null===t)u.push(null);else{var d={};if(d.color=t.color,d.x=i[f]+Math.round(r/2),s>0){for(var p=0,g=0;g<=s;g++)p+=l[g].data[f];var v=p-t,m=h*(p-e)/(n-e),y=h*(v-e)/(n-e)}else p=t,m=h*(p-e)/(n-e),y=0;var _=y;m*=c,_*=c,d.y=o.height-a.xAxisHeight-a.legendHeight-Math.round(m)-a.padding,d.y0=o.height-a.xAxisHeight-a.legendHeight-Math.round(_)-a.padding,u.push(d)}}),u}function U(t,e,n,i){var r;r="stack"==i?y(t):m(t);var o=[];r=r.filter(function(t){return"object"===typeof t&&null!==t?t.constructor==Array?null!==t:null!==t.value:null!==t}),r.map(function(t){"object"===typeof t?t.constructor==Array?t.map(function(t){o.push(t)}):o.push(t.value):o.push(t)});var a=0,s=0;if(o.length>0&&(a=Math.min.apply(this,o),s=Math.max.apply(this,o)),"number"===typeof e.yAxis.min&&(a=Math.min(e.yAxis.min,a)),"number"===typeof e.yAxis.max&&(s=Math.max(e.yAxis.max,s)),a===s){var l=s||10;s+=l}for(var c=g(a,s),u=c.minRange,h=c.maxRange,f=[],d=(h-u)/n.yAxisSplit,p=0;p<=n.yAxisSplit;p++)f.push(u+d*p);return f.reverse()}function X(t,e,n){var o=i({},e.extra.column||{type:""}),a=U(t,e,n,o.type),s=n.yAxisWidth,l=a.map(function(t){return t=r.toFixed(t,2),t=e.yAxis.format?e.yAxis.format(Number(t)):t,s=Math.max(s,v(t)+5),t});return!0===e.yAxis.disabled&&(s=0),{rangesFormat:l,ranges:a,yAxisWidth:s}}function q(t,e,n,i,r){var o=U(e,n,i),a=n.height-2*i.padding-i.xAxisHeight-i.legendHeight,s=o[0],l=o[o.length-1],c=i.padding,u=i.padding+a,h=s-(s-l)*(t-c)/(u-c);return h=n.yAxis.format?n.yAxis.format(Number(h)):h,h}function Y(t,e){!0!==e.rotateLock?(t.translate(e.height,0),t.rotate(90*Math.PI/180)):!0!==e._rotate_&&(t.translate(e.height,0),t.rotate(90*Math.PI/180),e._rotate_=!0)}function Z(t,e,n,i,r){i.beginPath(),i.setStrokeStyle("#ffffff"),i.setLineWidth(1*r.pixelRatio),i.setFillStyle(e),"diamond"===n?t.forEach(function(t,e){null!==t&&(i.moveTo(t.x,t.y-4.5),i.lineTo(t.x-4.5,t.y),i.lineTo(t.x,t.y+4.5),i.lineTo(t.x+4.5,t.y),i.lineTo(t.x,t.y-4.5))}):"circle"===n?t.forEach(function(t,e){null!==t&&(i.moveTo(t.x+3.5*r.pixelRatio,t.y),i.arc(t.x,t.y,4*r.pixelRatio,0,2*Math.PI,!1))}):"rect"===n?t.forEach(function(t,e){null!==t&&(i.moveTo(t.x-3.5,t.y-3.5),i.rect(t.x-3.5,t.y-3.5,7,7))}):"triangle"===n&&t.forEach(function(t,e){null!==t&&(i.moveTo(t.x,t.y-4.5),i.lineTo(t.x-4.5,t.y+4.5),i.lineTo(t.x+4.5,t.y+4.5),i.lineTo(t.x,t.y-4.5))}),i.closePath(),i.fill(),i.stroke()}function K(t,e,n){var i=t.title.fontSize||e.titleFontSize,r=t.subtitle.fontSize||e.subtitleFontSize,o=t.title.name||"",a=t.subtitle.name||"",s=t.title.color||e.titleColor,l=t.subtitle.color||e.subtitleColor,c=o?i:0,u=a?r:0,h=5;if(a){var f=v(a,r),d=(t.width-f)/2+(t.subtitle.offsetX||0),p=(t.height-e.legendHeight+r)/2+(t.subtitle.offsetY||0);o&&(p-=(c+h)/2),n.beginPath(),n.setFontSize(r),n.setFillStyle(l),n.fillText(a,d,p),n.closePath(),n.stroke()}if(o){var g=v(o,i),m=(t.width-g)/2+(t.title.offsetX||0),y=(t.height-e.legendHeight+i)/2+(t.title.offsetY||0);a&&(y+=(u+h)/2),n.beginPath(),n.setFontSize(i),n.setFillStyle(s),n.fillText(o,m,y),n.closePath(),n.stroke()}}function J(t,e,n,i){var r=e.data;t.forEach(function(t,o){if(null!==t){i.beginPath(),i.setFontSize(n.fontSize),i.setFillStyle("#666666");var a=r[o].value||r[o],s=e.format?e.format(a):a;i.fillText(s,t.x-v(s)/2,t.y-2),i.closePath(),i.stroke()}})}function Q(t,e,n,i,r,o){e-=t.width/2+r.gaugeLabelTextMargin;for(var a=t.startAngle-t.endAngle+1,s=a/t.splitLine.splitNumber,l=t.endNumber-t.startNumber,c=l/t.splitLine.splitNumber,u=t.startAngle,h=t.startNumber,f=0;f<t.splitLine.splitNumber+1;f++){var d={x:e*Math.cos(u*Math.PI),y:e*Math.sin(u*Math.PI)};d.x+=n.x-v(h)/2,d.y+=n.y;var p=d.x,g=d.y;o.beginPath(),o.setFontSize(r.fontSize),o.setFillStyle(t.labelColor||"#666666"),o.fillText(h,p,g+r.fontSize/2),o.closePath(),o.stroke(),u+=s,u>=2&&(u%=2),h+=c}}function tt(t,e,n,i,o,a){var s=i.extra.radar||{};e+=o.radarLabelTextMargin,t.forEach(function(t,l){var c={x:e*Math.cos(t),y:e*Math.sin(t)},u=h(c.x,c.y,n),f=u.x,d=u.y;r.approximatelyEqual(c.x,0)?f-=v(i.categories[l]||"")/2:c.x<0&&(f-=v(i.categories[l]||"")),a.beginPath(),a.setFontSize(o.fontSize),a.setFillStyle(s.labelColor||"#666666"),a.fillText(i.categories[l]||"",f,d+o.fontSize/2),a.closePath(),a.stroke()})}function et(t,e,n,i,o,a){var s=o+n.pieChartLinePadding,l=[],c=null,u=t.map(function(t){var e=2*Math.PI-(t._start_+2*Math.PI*t._proportion_/2),n=t.format?t.format(+t._proportion_.toFixed(2)):r.toFixed(100*t._proportion_)+"%",i=t.color;return{arc:e,text:n,color:i}});u.forEach(function(t){var e=Math.cos(t.arc)*s,i=Math.sin(t.arc)*s,a=Math.cos(t.arc)*o,u=Math.sin(t.arc)*o,h=e>=0?e+n.pieChartTextPadding:e-n.pieChartTextPadding,d=i,p=v(t.text),g=d;c&&r.isSameXCoordinateArea(c.start,{x:h})&&(g=h>0?Math.min(d,c.start.y):e<0?Math.max(d,c.start.y):d>0?Math.max(d,c.start.y):Math.min(d,c.start.y)),h<0&&(h-=p);var m={lineStart:{x:a,y:u},lineEnd:{x:e,y:i},start:{x:h,y:g},width:p,height:n.fontSize,text:t.text,color:t.color};c=f(m,c),l.push(c)}),l.forEach(function(t){var r=h(t.lineStart.x,t.lineStart.y,a),o=h(t.lineEnd.x,t.lineEnd.y,a),s=h(t.start.x,t.start.y,a);i.setLineWidth(1*e.pixelRatio),i.setFontSize(n.fontSize),i.beginPath(),i.setStrokeStyle(t.color),i.setFillStyle(t.color),i.moveTo(r.x,r.y);var l=t.start.x<0?s.x+t.width:s.x,c=t.start.x<0?s.x-5:s.x+5;i.quadraticCurveTo(o.x,o.y,l,s.y),i.moveTo(r.x,r.y),i.stroke(),i.closePath(),i.beginPath(),i.moveTo(s.x+t.width,s.y),i.arc(l,s.y,2,0,2*Math.PI),i.closePath(),i.fill(),i.beginPath(),i.setFontSize(n.fontSize),i.setFillStyle("#666666"),i.fillText(t.text,c,s.y+3),i.closePath(),i.stroke(),i.closePath()})}function nt(t,e,n,i){var r=e.extra.tooltip||{};r.gridType=void 0==r.gridType?"solid":r.gridType,r.dashLength=void 0==r.dashLength?4:r.dashLength;var a=n.padding,s=e.height-n.padding-n.xAxisHeight-n.legendHeight;if("dash"==r.gridType&&i.setLineDash([r.dashLength,r.dashLength]),i.beginPath(),i.setStrokeStyle(r.gridColor||"#cccccc"),i.setLineWidth(1*e.pixelRatio),i.moveTo(t,a),i.lineTo(t,s),i.closePath(),i.stroke(),i.setLineDash([]),r.xAxisLabel){var l=e.categories[e.tooltip.index];i.setFontSize(n.fontSize);var c=i.measureText(l).width,u=t-n.toolTipPadding-.5*c,h=s;i.beginPath(),i.setFillStyle(o(r.labelBgColor||n.toolTipBackground,r.labelBgOpacity||n.toolTipOpacity)),i.setStrokeStyle(r.labelBgColor||n.toolTipBackground),i.setLineWidth(1*e.pixelRatio),i.rect(u,h,c+2*n.toolTipPadding,n.fontSize+2*n.toolTipPadding),i.closePath(),i.stroke(),i.fill(),i.beginPath(),i.setFontSize(n.fontSize),i.setFillStyle(r.labelFontColor||n.fontColor),i.fillText(l,u+2*n.toolTipPadding,h+n.toolTipPadding+n.fontSize),i.closePath(),i.stroke()}}function it(t,e,n,i,r){var a=t.extra.tooltip||{};a.gridType=void 0==a.gridType?"solid":a.gridType,a.dashLength=void 0==a.dashLength?4:a.dashLength;var s=e.padding+e.yAxisWidth+e.yAxisTitleWidth,l=t.width-e.padding;if("dash"==a.gridType&&n.setLineDash([a.dashLength,a.dashLength]),n.beginPath(),n.setStrokeStyle(a.gridColor||"#cccccc"),n.setLineWidth(1*t.pixelRatio),n.moveTo(s,t.tooltip.offset.y),n.lineTo(l,t.tooltip.offset.y),n.closePath(),n.stroke(),n.setLineDash([]),a.yAxisLabel){var c=q(t.tooltip.offset.y,t.series,t,e,i);n.setFontSize(e.fontSize);var u=n.measureText(c).width,h=s-2*e.toolTipPadding-u,f=t.tooltip.offset.y;n.beginPath(),n.setFillStyle(o(a.labelBgColor||e.toolTipBackground,a.labelBgOpacity||e.toolTipOpacity)),n.setStrokeStyle(a.labelBgColor||e.toolTipBackground),n.setLineWidth(1*t.pixelRatio),n.rect(h,f-.5*e.fontSize-e.toolTipPadding,u+2*e.toolTipPadding,e.fontSize+2*e.toolTipPadding),n.closePath(),n.stroke(),n.fill(),n.beginPath(),n.setFontSize(e.fontSize),n.setFillStyle(a.labelFontColor||e.fontColor),n.fillText(c,h+e.toolTipPadding,f+.5*e.fontSize),n.closePath(),n.stroke()}}function rt(t,e,n,i,r){var a=n.padding,s=e.height-n.padding-n.xAxisHeight-n.legendHeight;i.beginPath(),i.setFillStyle(o("#000000",.08)),i.rect(t-r/2,a,r,s-a),i.closePath(),i.fill()}function ot(t,e,n,r,a,s,l){n.extra.tooltip;var c=4*n.pixelRatio,u=5*n.pixelRatio,h=8*n.pixelRatio,f=!1;"line"!=n.type&&"area"!=n.type&&"candle"!=n.type&&"mix"!=n.type||nt(n.tooltip.offset.x,n,r,a),e=i({x:0,y:0},e),e.y-=8*n.pixelRatio;var d=t.map(function(t){return v(t.text)}),p=c+u+4*r.toolTipPadding+Math.max.apply(null,d),g=2*r.toolTipPadding+t.length*r.toolTipLineHeight;e.x-Math.abs(n._scrollDistance_)+h+p>n.width&&(f=!0),a.beginPath(),a.setFillStyle(o(n.tooltip.option.background||r.toolTipBackground,r.toolTipOpacity)),f?(a.moveTo(e.x,e.y+10*n.pixelRatio),a.lineTo(e.x-h,e.y+10*n.pixelRatio-5*n.pixelRatio),a.lineTo(e.x-h,e.y),a.lineTo(e.x-h-Math.round(p),e.y),a.lineTo(e.x-h-Math.round(p),e.y+g),a.lineTo(e.x-h,e.y+g),a.lineTo(e.x-h,e.y+10*n.pixelRatio+5*n.pixelRatio),a.lineTo(e.x,e.y+10*n.pixelRatio)):(a.moveTo(e.x,e.y+10*n.pixelRatio),a.lineTo(e.x+h,e.y+10*n.pixelRatio-5*n.pixelRatio),a.lineTo(e.x+h,e.y),a.lineTo(e.x+h+Math.round(p),e.y),a.lineTo(e.x+h+Math.round(p),e.y+g),a.lineTo(e.x+h,e.y+g),a.lineTo(e.x+h,e.y+10*n.pixelRatio+5*n.pixelRatio),a.lineTo(e.x,e.y+10*n.pixelRatio)),a.closePath(),a.fill(),t.forEach(function(t,n){if(null!==t.color){a.beginPath(),a.setFillStyle(t.color);var i=e.x+h+2*r.toolTipPadding,o=e.y+(r.toolTipLineHeight-r.fontSize)/2+r.toolTipLineHeight*n+r.toolTipPadding+1;f&&(i=e.x-p-h+2*r.toolTipPadding),a.fillRect(i,o,c,r.fontSize),a.closePath()}}),t.forEach(function(t,n){var i=e.x+h+2*r.toolTipPadding+c+u;f&&(i=e.x-p-h+2*r.toolTipPadding+ +c+u);var o=e.y+(r.toolTipLineHeight-r.fontSize)/2+r.toolTipLineHeight*n+r.toolTipPadding;a.beginPath(),a.setFontSize(r.fontSize),a.setFillStyle("#ffffff"),a.fillText(t.text,i,o+r.fontSize),a.closePath(),a.stroke()})}function at(t,e,n,i){var r=n.xAxisHeight+(e.height-n.xAxisHeight-v(t))/2;i.save(),i.beginPath(),i.setFontSize(n.fontSize),i.setFillStyle(e.yAxis.titleFontColor||"#333333"),i.translate(0,e.height),i.rotate(-90*Math.PI/180),i.fillText(t,r,n.padding+.5*n.fontSize),i.closePath(),i.stroke(),i.restore()}function st(t,e,n,i){var r=arguments.length>4&&void 0!==arguments[4]?arguments[4]:1,o=e.extra.column||{type:{},meter:{}};o.type=void 0==o.type?"group":o.type,o.meter=o.meter||{},o.meter.border=void 0==o.meter.border?4:o.meter.border,o.meter.fillColor=void 0==o.meter.fillColor?"#FFFFFF":o.meter.fillColor;var a=X(t,e,n),s=a.ranges,l=W(e.categories,e,n),c=l.xAxisPoints,u=l.eachSpacing,h=s.pop(),f=s.shift(),d=[];return i.save(),e._scrollDistance_&&0!==e._scrollDistance_&&!0===e.enableScroll&&i.translate(e._scrollDistance_,0),e.tooltip&&e.tooltip.textList&&e.tooltip.textList.length&&1===r&&rt(e.tooltip.offset.x,e,n,i,u),t.forEach(function(a,s){var l=a.data;switch(o.type){case"group":var p=j(l,h,f,c,u,e,n,r),g=G(l,h,f,c,u,e,n,s,t,r);d.push(g),p=B(p,u,t.length,s,n,e),p.forEach(function(t,r){if(null!==t){i.beginPath(),i.setFillStyle(t.color||a.color);var o=t.x-t.width/2+1,s=e.height-t.y-n.padding-n.xAxisHeight-n.legendHeight;i.moveTo(o,t.y),i.fillRect(o,t.y,t.width-2,s),i.closePath(),i.fill()}});break;case"stack":p=G(l,h,f,c,u,e,n,s,t,r);d.push(p),p=H(p,u,t.length,s,n,e,t),p.forEach(function(t,r){if(null!==t){i.beginPath(),i.setFillStyle(t.color||a.color);var o=t.x-t.width/2+1,l=e.height-t.y-n.padding-n.xAxisHeight-n.legendHeight,c=e.height-t.y0-n.padding-n.xAxisHeight-n.legendHeight;s>0&&(l-=c),i.moveTo(o,t.y),i.fillRect(o,t.y,t.width-2,l),i.closePath(),i.fill()}});break;case"meter":p=j(l,h,f,c,u,e,n,r);d.push(p),p=$(p,u,t.length,s,n,e,o.meter.border),0==s?p.forEach(function(t,r){if(null!==t){i.beginPath(),i.setFillStyle(o.meter.fillColor);var s=t.x-t.width/2+1,l=e.height-t.y-n.padding-n.xAxisHeight-n.legendHeight;i.moveTo(s,t.y),i.fillRect(s,t.y,t.width-2,l),i.closePath(),i.fill(),i.beginPath(),i.setStrokeStyle(a.color),i.setLineWidth(o.meter.border*e.pixelRatio),i.moveTo(s+.5*o.meter.border,t.y+l),i.lineTo(s+.5*o.meter.border,t.y+.5*o.meter.border),i.lineTo(s+t.width-o.meter.border,t.y+.5*o.meter.border),i.lineTo(s+t.width-o.meter.border,t.y+l),i.stroke()}}):p.forEach(function(t,r){if(null!==t){i.beginPath(),i.setFillStyle(t.color||a.color);var o=t.x-t.width/2+1,s=e.height-t.y-n.padding-n.xAxisHeight-n.legendHeight;i.moveTo(o,t.y),i.rect(o,t.y,t.width-2,s),i.closePath(),i.fill()}});break}}),!1!==e.dataLabel&&1===r&&t.forEach(function(a,s){var l=a.data;switch(o.type){case"group":var d=j(l,h,f,c,u,e,n,r);d=B(d,u,t.length,s,n,e),J(d,a,n,i);break;case"stack":d=G(l,h,f,c,u,e,n,s,t,r);J(d,a,n,i);break;case"meter":d=j(l,h,f,c,u,e,n,r);J(d,a,n,i);break}}),i.restore(),{xAxisPoints:c,calPoints:d,eachSpacing:u}}function lt(t,e,n,i){var r=arguments.length>4&&void 0!==arguments[4]?arguments[4]:1,o=e.extra.candle||{color:{},average:{}};o.color.upLine=o.color.upLine?o.color.upLine:"#f04864",o.color.upFill=o.color.upFill?o.color.upFill:"#f04864",o.color.downLine=o.color.downLine?o.color.downLine:"#2fc25b",o.color.downFill=o.color.downFill?o.color.downFill:"#2fc25b",o.average.show=!0===o.average.show,o.average.name=o.average.name?o.average.name:[],o.average.day=o.average.day?o.average.day:[],o.average.color=o.average.color?o.average.color:["#1890ff","#2fc25b","#facc14","#f04864","#8543e0","#90ed7d"],e.extra.candle=o;var a=X(t,e,n),s=a.ranges,l=W(e.categories,e,n),c=l.xAxisPoints,u=l.eachSpacing,h=s.pop(),f=s.shift(),d=[];return i.save(),e._scrollDistance_&&0!==e._scrollDistance_&&!0===e.enableScroll&&i.translate(e._scrollDistance_,0),t.forEach(function(t,a){var s=t.data,l=V(s,h,f,c,u,e,n,r);d.push(l);var p=I(l);p=p[0],p.forEach(function(t,n){i.beginPath(),s[n][1]-s[n][0]>0?(i.setStrokeStyle(o.color.upLine),i.setFillStyle(o.color.upFill),i.setLineWidth(1*e.pixelRatio),i.moveTo(t[3].x,t[3].y),i.lineTo(t[1].x,t[1].y),i.lineTo(t[1].x-u/4,t[1].y),i.lineTo(t[0].x-u/4,t[0].y),i.lineTo(t[0].x,t[0].y),i.lineTo(t[2].x,t[2].y),i.lineTo(t[0].x,t[0].y),i.lineTo(t[0].x+u/4,t[0].y),i.lineTo(t[1].x+u/4,t[1].y),i.lineTo(t[1].x,t[1].y),i.moveTo(t[3].x,t[3].y)):(i.setStrokeStyle(o.color.downLine),i.setFillStyle(o.color.downFill),i.setLineWidth(1*e.pixelRatio),i.moveTo(t[3].x,t[3].y),i.lineTo(t[0].x,t[0].y),i.lineTo(t[0].x-u/4,t[0].y),i.lineTo(t[1].x-u/4,t[1].y),i.lineTo(t[1].x,t[1].y),i.lineTo(t[2].x,t[2].y),i.lineTo(t[1].x,t[1].y),i.lineTo(t[1].x+u/4,t[1].y),i.lineTo(t[0].x+u/4,t[0].y),i.lineTo(t[0].x,t[0].y),i.moveTo(t[3].x,t[3].y)),i.closePath(),i.fill(),i.stroke()})}),i.restore(),o.average.show,{xAxisPoints:c,calPoints:d,eachSpacing:u}}function ct(t,e,n,i){var r=arguments.length>4&&void 0!==arguments[4]?arguments[4]:1,o=X(t,e,n),a=o.ranges,s=W(e.categories,e,n),l=s.xAxisPoints,c=s.eachSpacing,h=a.pop(),f=a.shift(),d=e.height-n.padding-n.xAxisHeight-n.legendHeight,p=[];return i.save(),e._scrollDistance_&&0!==e._scrollDistance_&&!0===e.enableScroll&&i.translate(e._scrollDistance_,0),e.tooltip&&e.tooltip.textList&&e.tooltip.textList.length&&1===r&&nt(e.tooltip.offset.x,e,n,i),t.forEach(function(t,o){var a=t.data,s=j(a,h,f,l,c,e,n,r);p.push(s);var g=I(s);if(g.forEach(function(n){if(i.beginPath(),i.setStrokeStyle(t.color),i.setFillStyle(t.color),i.setGlobalAlpha(.2),i.setLineWidth(2*e.pixelRatio),n.length>1){var r=n[0],o=n[n.length-1];i.moveTo(r.x,r.y),"curve"===e.extra.lineStyle?n.forEach(function(t,e){if(e>0){var r=u(n,e-1);i.bezierCurveTo(r.ctrA.x,r.ctrA.y,r.ctrB.x,r.ctrB.y,t.x,t.y)}}):n.forEach(function(t,e){e>0&&i.lineTo(t.x,t.y)}),i.lineTo(o.x,d),i.lineTo(r.x,d),i.lineTo(r.x,r.y)}else{var a=n[0];i.moveTo(a.x-c/2,a.y),i.lineTo(a.x+c/2,a.y),i.lineTo(a.x+c/2,d),i.lineTo(a.x-c/2,d),i.moveTo(a.x-c/2,a.y)}i.closePath(),i.fill(),i.setGlobalAlpha(1),i.beginPath(),i.setStrokeStyle(t.color),i.setLineWidth(2*e.pixelRatio),1===n.length?(i.moveTo(n[0].x,n[0].y),i.arc(n[0].x,n[0].y,1,0,2*Math.PI)):(i.moveTo(n[0].x,n[0].y),"curve"===e.extra.lineStyle?n.forEach(function(t,e){if(e>0){var r=u(n,e-1);i.bezierCurveTo(r.ctrA.x,r.ctrA.y,r.ctrB.x,r.ctrB.y,t.x,t.y)}}):n.forEach(function(t,e){e>0&&i.lineTo(t.x,t.y)}),i.moveTo(n[0].x,n[0].y)),i.closePath(),i.stroke()}),!1!==e.dataPointShape){var v=n.dataPointShape[o%n.dataPointShape.length];Z(s,t.color,v,i,e)}}),!1!==e.dataLabel&&1===r&&t.forEach(function(t,o){var a=t.data,s=j(a,h,f,l,c,e,n,r);J(s,t,n,i)}),i.restore(),{xAxisPoints:l,calPoints:p,eachSpacing:c}}function ut(t,e,n,i){var r=arguments.length>4&&void 0!==arguments[4]?arguments[4]:1,o=X(t,e,n),a=o.ranges,s=W(e.categories,e,n),l=s.xAxisPoints,c=s.eachSpacing,h=a.pop(),f=a.shift(),d=[];return i.save(),e._scrollDistance_&&0!==e._scrollDistance_&&!0===e.enableScroll&&i.translate(e._scrollDistance_,0),e.tooltip&&e.tooltip.textList&&e.tooltip.textList.length&&1===r&&nt(e.tooltip.offset.x,e,n,i),t.forEach(function(t,o){var a=t.data,s=j(a,h,f,l,c,e,n,r);d.push(s);var p=I(s);if(p.forEach(function(n,r){i.beginPath(),i.setStrokeStyle(t.color),i.setLineWidth(2*e.pixelRatio),1===n.length?(i.moveTo(n[0].x,n[0].y),i.arc(n[0].x,n[0].y,1,0,2*Math.PI)):(i.moveTo(n[0].x,n[0].y),"curve"===e.extra.lineStyle?n.forEach(function(t,e){if(e>0){var r=u(n,e-1);i.bezierCurveTo(r.ctrA.x,r.ctrA.y,r.ctrB.x,r.ctrB.y,t.x,t.y)}}):n.forEach(function(t,e){e>0&&i.lineTo(t.x,t.y)}),i.moveTo(n[0].x,n[0].y)),i.closePath(),i.stroke()}),!1!==e.dataPointShape){var g=n.dataPointShape[o%n.dataPointShape.length];Z(s,t.color,g,i,e)}}),!1!==e.dataLabel&&1===r&&t.forEach(function(t,o){var a=t.data,s=j(a,h,f,l,c,e,n,r);J(s,t,n,i)}),i.restore(),{xAxisPoints:l,calPoints:d,eachSpacing:c}}function ht(t,e,n,i){var r=arguments.length>4&&void 0!==arguments[4]?arguments[4]:1,o=X(t,e,n),a=o.ranges,s=W(e.categories,e,n),l=s.xAxisPoints,c=s.eachSpacing,h=a.pop(),f=a.shift(),d=e.height-n.padding-n.xAxisHeight-n.legendHeight,p=[],g=0,v=0;if(t.forEach(function(t,e){"column"==t.type&&(v+=1)}),i.save(),e._scrollDistance_&&0!==e._scrollDistance_&&!0===e.enableScroll&&i.translate(e._scrollDistance_,0),e.tooltip&&e.tooltip.textList&&e.tooltip.textList.length&&1===r&&nt(e.tooltip.offset.x,e,n,i),t.forEach(function(t,o){var a=t.data,s=j(a,h,f,l,c,e,n,r);if(p.push(s),"column"==t.type&&(s=B(s,c,v,g,n,e),s.forEach(function(r,o){if(null!==r){i.beginPath(),i.setFillStyle(r.color||t.color);var a=r.x-r.width/2+1,s=e.height-r.y-n.padding-n.xAxisHeight-n.legendHeight;i.moveTo(a,r.y),i.rect(a,r.y,r.width-2,s),i.closePath(),i.fill()}}),g+=1),"area"==t.type){var m=I(s);m.forEach(function(n){if(i.beginPath(),i.setStrokeStyle(t.color),i.setFillStyle(t.color),i.setGlobalAlpha(.2),i.setLineWidth(2*e.pixelRatio),n.length>1){var r=n[0],o=n[n.length-1];i.moveTo(r.x,r.y),"curve"===t.style?n.forEach(function(t,e){if(e>0){var r=u(n,e-1);i.bezierCurveTo(r.ctrA.x,r.ctrA.y,r.ctrB.x,r.ctrB.y,t.x,t.y)}}):n.forEach(function(t,e){e>0&&i.lineTo(t.x,t.y)}),i.lineTo(o.x,d),i.lineTo(r.x,d),i.lineTo(r.x,r.y)}else{var a=n[0];i.moveTo(a.x-c/2,a.y),i.lineTo(a.x+c/2,a.y),i.lineTo(a.x+c/2,d),i.lineTo(a.x-c/2,d),i.moveTo(a.x-c/2,a.y)}i.closePath(),i.fill(),i.setGlobalAlpha(1)})}if("line"==t.type){m=I(s);m.forEach(function(n,r){i.beginPath(),i.setStrokeStyle(t.color),i.setLineWidth(2*e.pixelRatio),1===n.length?(i.moveTo(n[0].x,n[0].y),i.arc(n[0].x,n[0].y,1,0,2*Math.PI)):(i.moveTo(n[0].x,n[0].y),"curve"==t.style?n.forEach(function(t,e){if(e>0){var r=u(n,e-1);i.bezierCurveTo(r.ctrA.x,r.ctrA.y,r.ctrB.x,r.ctrB.y,t.x,t.y)}}):n.forEach(function(t,e){e>0&&i.lineTo(t.x,t.y)}),i.moveTo(n[0].x,n[0].y)),i.closePath(),i.stroke()})}if("point"==t.type){m=I(s);m.forEach(function(n,r){i.beginPath(),i.setStrokeStyle(t.color),i.setLineWidth(2*e.pixelRatio),i.moveTo(n[0].x,n[0].y),i.arc(n[0].x,n[0].y,1,0,2*Math.PI),i.closePath(),i.stroke()})}if(!1!==e.dataPointShape&&"column"!==t.type){var y=n.dataPointShape[o%n.dataPointShape.length];Z(s,t.color,y,i,e)}}),!1!==e.dataLabel&&1===r){g=0;t.forEach(function(t,o){var a=t.data,s=j(a,h,f,l,c,e,n,r);"column"!==t.type?J(s,t,n,i):(s=B(s,c,v,g,n,e),J(s,t,n,i),g+=1)})}return i.restore(),{xAxisPoints:l,calPoints:p,eachSpacing:c}}function ft(t,e,n,i,r,o){var a=t.extra.tooltip||{};a.horizentalLine&&t.tooltip&&1===i&&("line"==t.type||"area"==t.type||"column"==t.type||"candle"==t.type)&&it(t,e,n,r,o),n.save(),t._scrollDistance_&&0!==t._scrollDistance_&&!0===t.enableScroll&&n.translate(t._scrollDistance_,0),t.tooltip&&t.tooltip.textList&&t.tooltip.textList.length&&1===i&&ot(t.tooltip.textList,t.tooltip.offset,t,e,n,r,o),n.restore()}function dt(t,e,n,i){var r=W(t,e,n),o=r.xAxisPoints,a=r.startX,s=r.endX,l=r.eachSpacing,u=e.height-n.padding-n.xAxisHeight-n.legendHeight,h=n.padding;if(e.enableScroll&&e.xAxis.scrollShow){var f=e.height-n.padding-n.legendHeight+4*e.pixelRatio,d=s-a,p=l*(o.length-1),g=d*d/p,m=0;e._scrollDistance_&&(m=-e._scrollDistance_*d/p),i.beginPath(),i.setLineCap("round"),i.setLineWidth(6*e.pixelRatio),i.setStrokeStyle(e.xAxis.scrollBackgroundColor||"#EFEBEF"),i.moveTo(a,f),i.lineTo(s,f),i.stroke(),i.closePath(),i.beginPath(),i.setLineCap("round"),i.setLineWidth(6*e.pixelRatio),i.setStrokeStyle(e.xAxis.scrollColor||"#A6A6A6"),i.moveTo(a+m,f),i.lineTo(a+m+g,f),i.stroke(),i.closePath()}if(i.save(),e._scrollDistance_&&0!==e._scrollDistance_&&i.translate(e._scrollDistance_,0),i.beginPath(),i.setStrokeStyle(e.xAxis.gridColor||"#cccccc"),i.setLineCap("butt"),i.setLineWidth(1*e.pixelRatio),"dash"==e.xAxis.gridType&&i.setLineDash([e.xAxis.dashLength,e.xAxis.dashLength]),!0!==e.xAxis.disableGrid&&("calibration"===e.xAxis.type?o.forEach(function(t,n){n>0&&(i.moveTo(t-l/2,u),i.lineTo(t-l/2,u+4*e.pixelRatio))}):o.forEach(function(t,e){i.moveTo(t,u),i.lineTo(t,h)})),i.closePath(),i.stroke(),i.setLineDash([]),!0!==e.xAxis.disabled){var y=e.width-2*n.padding-n.yAxisWidth-n.yAxisTitleWidth,_=Math.min(t.length,Math.ceil(y/n.fontSize/1.5)),x=Math.ceil(t.length/_);t=t.map(function(t,e){return e%x!==0?"":t}),0===n._xAxisTextAngle_?t.forEach(function(t,r){var a=l/2-v(t)/2;i.beginPath(),i.setFontSize(n.fontSize),i.setFillStyle(e.xAxis.fontColor||"#666666"),i.fillText(t,o[r]+a,u+n.fontSize+5),i.closePath(),i.stroke()}):t.forEach(function(t,r){i.save(),i.beginPath(),i.setFontSize(n.fontSize),i.setFillStyle(e.xAxis.fontColor||"#666666");var a=v(t),s=l/2-a,h=c(o[r]+l/2,u+n.fontSize/2+5,e.height),f=h.transX,d=h.transY;i.rotate(-1*n._xAxisTextAngle_),i.translate(f,d),i.fillText(t,o[r]+s,u+n.fontSize+5),i.closePath(),i.stroke(),i.restore()})}i.restore()}function pt(t,e,n,i){if(!0!==e.yAxis.disableGrid){for(var r=e.height-2*n.padding-n.xAxisHeight-n.legendHeight,o=Math.floor(r/n.yAxisSplit),a=n.yAxisWidth+n.yAxisTitleWidth,s=n.padding+a,l=W(t,e,n),c=l.xAxisPoints,u=l.eachSpacing,h=u*(c.length-1),f=s+h,d=[],p=0;p<n.yAxisSplit;p++)d.push(n.padding+o*p);d.push(n.padding+o*n.yAxisSplit+2),i.save(),e._scrollDistance_&&0!==e._scrollDistance_&&i.translate(e._scrollDistance_,0),"dash"==e.yAxis.gridType&&i.setLineDash([e.yAxis.dashLength,e.yAxis.dashLength]),i.beginPath(),i.setStrokeStyle(e.yAxis.gridColor||"#cccccc"),i.setLineWidth(1*e.pixelRatio),d.forEach(function(t,e){i.moveTo(s,t),i.lineTo(f,t)}),i.closePath(),i.stroke(),i.setLineDash([]),i.restore()}}function gt(t,e,n,i){if(!0!==e.yAxis.disabled){var r=X(t,e,n),o=r.rangesFormat,a=n.yAxisWidth+n.yAxisTitleWidth,s=e.height-2*n.padding-n.xAxisHeight-n.legendHeight,l=Math.floor(s/n.yAxisSplit),c=n.padding+a,u=e.width-n.padding,h=e.height-n.padding-n.xAxisHeight-n.legendHeight;i.beginPath(),i.setFillStyle(e.background||"#ffffff"),e._scrollDistance_<0&&i.fillRect(0,0,c,h+n.xAxisHeight),i.fillRect(u,0,e.width,h+n.xAxisHeight),i.closePath(),i.stroke();for(var f=[],d=0;d<=n.yAxisSplit;d++)f.push(n.padding+l*d);o.forEach(function(t,r){var o=f[r]?f[r]:h;i.beginPath(),i.setFontSize(n.fontSize),i.setFillStyle(e.yAxis.fontColor||"#666666"),i.fillText(t,n.padding+n.yAxisTitleWidth,o+n.fontSize/2),i.closePath(),i.stroke()}),e.yAxis.title&&at(e.yAxis.title,e,n,i)}}function vt(t,e,n,i){if(!1!==e.legend){var r=D(t,e,n),o=r.legendList,a=5*e.pixelRatio,s=10*e.pixelRatio,l=15*e.pixelRatio;o.forEach(function(t,r){var o=0;t.forEach(function(t){t.name=t.name||"undefined",o+=3*a+v(t.name)+l});var c=(e.width-o)/2+a,u=e.height-n.padding-n.legendHeight+r*(n.fontSize+s)+a+s;i.setFontSize(n.fontSize),t.forEach(function(t){switch(e.type){case"line":i.beginPath(),i.setLineWidth(1*e.pixelRatio),i.setStrokeStyle(t.color),i.setFillStyle(t.color),i.moveTo(c+7.5*e.pixelRatio,u+5*e.pixelRatio),i.arc(c+7.5*e.pixelRatio,u+5*e.pixelRatio,6*e.pixelRatio,0,2*Math.PI),i.closePath(),i.fill(),i.stroke();break;case"pie":i.beginPath(),i.setLineWidth(1*e.pixelRatio),i.setStrokeStyle(t.color),i.setFillStyle(t.color),i.moveTo(c+7.5*e.pixelRatio,u+5*e.pixelRatio),i.arc(c+7.5*e.pixelRatio,u+5*e.pixelRatio,6*e.pixelRatio,0,2*Math.PI),i.closePath(),i.fill(),i.stroke();break;case"ring":i.beginPath(),i.setLineWidth(1*e.pixelRatio),i.setStrokeStyle(t.color),i.setFillStyle(t.color),i.moveTo(c+7.5*e.pixelRatio,u+5*e.pixelRatio),i.arc(c+7.5*e.pixelRatio,u+5*e.pixelRatio,6*e.pixelRatio,0,2*Math.PI),i.closePath(),i.fill(),i.stroke();break;case"gauge":break;case"arcbar":break;default:i.beginPath(),i.setFillStyle(t.color),i.moveTo(c,u),i.fillRect(c,u,15*e.pixelRatio,10*e.pixelRatio),i.closePath(),i.fill(),i.stroke()}c+=a+l,i.beginPath(),i.setFontSize(n.fontSize),i.setFillStyle(e.extra.legendTextColor||"#333333"),i.fillText(t.name,c,u+6*e.pixelRatio+3*e.pixelRatio),i.closePath(),i.stroke(),c+=v(t.name)+2*a})})}}function mt(t,e,n,i){var r=arguments.length>4&&void 0!==arguments[4]?arguments[4]:1,a=e.extra.pie||{};t=E(t,r);var s={x:e.width/2,y:(e.height-n.legendHeight)/2},l=Math.min(s.x-n.pieChartLinePadding-n.pieChartTextPadding-n._pieTextMaxLength_,s.y-n.pieChartLinePadding-n.pieChartTextPadding);e.dataLabel?l-=10:l-=2*n.padding;var c=l+n.pieChartLinePadding/2;if(t=t.map(function(t){return t._start_+=(a.offsetAngle||0)*Math.PI/180,t}),t.forEach(function(t,n){e.tooltip&&e.tooltip.index==n&&(i.beginPath(),i.setFillStyle(o(t.color,e.extra.pie.activeOpacity||.5)),i.moveTo(s.x,s.y),i.arc(s.x,s.y,c,t._start_,t._start_+2*t._proportion_*Math.PI),i.closePath(),i.fill()),i.beginPath(),i.setLineWidth(2*e.pixelRatio),i.setStrokeStyle("#ffffff"),i.setFillStyle(t.color),i.moveTo(s.x,s.y),i.arc(s.x,s.y,l,t._start_,t._start_+2*t._proportion_*Math.PI),i.closePath(),i.fill(),!0!==e.disablePieStroke&&i.stroke()}),"ring"===e.type){var u=.6*l;"number"===typeof e.extra.pie.ringWidth&&e.extra.pie.ringWidth>0&&(u=Math.max(0,l-e.extra.pie.ringWidth)),i.beginPath(),i.setFillStyle(e.background||"#ffffff"),i.moveTo(s.x,s.y),i.arc(s.x,s.y,u,0,2*Math.PI),i.closePath(),i.fill()}if(!1!==e.dataLabel&&1===r){for(var h=!1,f=0,d=t.length;f<d;f++)if(t[f].data>0){h=!0;break}h&&et(t,e,n,i,l,s)}return 1===r&&"ring"===e.type&&K(e,n,i),{center:s,radius:l,series:t}}function yt(t,e,n,i){var r=arguments.length>4&&void 0!==arguments[4]?arguments[4]:1,o=e.extra.arcbar||{};o.startAngle=o.startAngle?o.startAngle:.75,o.endAngle=o.endAngle?o.endAngle:.25,o.type=o.type?o.type:"default",t=R(t,o,r);var a={x:e.width/2,y:e.height/2},s=Math.min(a.x,a.y);return"number"===typeof o.width&&o.width>0?o.width=o.width:o.width=12*e.pixelRatio,s-=n.padding+o.width/2,i.setLineWidth(o.width),i.setStrokeStyle(o.backgroundColor||"#E9E9E9"),i.setLineCap("round"),i.beginPath(),"default"==o.type?i.arc(a.x,a.y,s,o.startAngle*Math.PI,o.endAngle*Math.PI,!1):i.arc(a.x,a.y,s,0,2*Math.PI,!1),i.stroke(),t.forEach(function(t){i.setLineWidth(o.width),i.setStrokeStyle(t.color),i.setLineCap("round"),i.beginPath(),i.arc(a.x,a.y,s,o.startAngle*Math.PI,t._proportion_*Math.PI,!1),i.stroke()}),K(e,n,i),{center:a,radius:s,series:t}}function _t(t,e,n,i,r){var o=arguments.length>5&&void 0!==arguments[5]?arguments[5]:1,a=n.extra.gauge||{};a.startAngle=a.startAngle?a.startAngle:.75,void 0==a.oldAngle&&(a.oldAngle=a.startAngle),void 0==a.oldData&&(a.oldData=0),a.endAngle=a.endAngle?a.endAngle:.25,t=N(t,a.startAngle,a.endAngle);var s={x:n.width/2,y:n.height/2},l=Math.min(s.x,s.y);"number"===typeof a.width&&a.width>0?a.width=a.width:a.width=15*n.pixelRatio,l-=i.padding+a.width/2;var c=l-a.width;r.setLineWidth(a.width),r.setLineCap("butt"),t.forEach(function(t){r.beginPath(),r.setStrokeStyle(t.color),r.arc(s.x,s.y,l,t._startAngle_*Math.PI,t._endAngle_*Math.PI,!1),r.stroke()}),r.save();var u=a.startAngle-a.endAngle+1;a.splitLine.fixRadius=a.splitLine.fixRadius?a.splitLine.fixRadius:0,a.splitLine.splitNumber=a.splitLine.splitNumber?a.splitLine.splitNumber:10,a.splitLine.width=a.splitLine.width?a.splitLine.width:15*n.pixelRatio,a.splitLine.color=a.splitLine.color?a.splitLine.color:"#FFFFFF",a.splitLine.childNumber=a.splitLine.childNumber?a.splitLine.childNumber:5,a.splitLine.childWidth=a.splitLine.childWidth?a.splitLine.childWidth:5*n.pixelRatio;var h=u/a.splitLine.splitNumber,f=u/a.splitLine.splitNumber/a.splitLine.childNumber,d=-l-.5*a.width-a.splitLine.fixRadius,p=-l-.5*a.width-a.splitLine.fixRadius+a.splitLine.width,g=-l-.5*a.width-a.splitLine.fixRadius+a.splitLine.childWidth;r.translate(s.x,s.y),r.rotate((a.startAngle-1)*Math.PI);for(var v=0;v<a.splitLine.splitNumber+1;v++)r.beginPath(),r.setStrokeStyle(a.splitLine.color),r.setLineWidth(2*n.pixelRatio),r.moveTo(d,0),r.lineTo(p,0),r.stroke(),r.rotate(h*Math.PI);r.restore(),r.save(),r.translate(s.x,s.y),r.rotate((a.startAngle-1)*Math.PI);for(var m=0;m<a.splitLine.splitNumber*a.splitLine.childNumber+1;m++)r.beginPath(),r.setStrokeStyle(a.splitLine.color),r.setLineWidth(1*n.pixelRatio),r.moveTo(d,0),r.lineTo(g,0),r.stroke(),r.rotate(f*Math.PI);return r.restore(),a.pointer.width=a.pointer.width?a.pointer.width:15*n.pixelRatio,void 0==a.pointer.color||"auto"==a.pointer.color?a.pointer.color:(a.pointer.color,a.pointer.color),e=z(e,t,a,o),e.forEach(function(t){r.save(),r.translate(s.x,s.y),r.rotate((t._proportion_-1)*Math.PI),r.beginPath(),r.setFillStyle(t.color),r.moveTo(a.pointer.width,0),r.lineTo(0,-a.pointer.width/2),r.lineTo(-c,0),r.lineTo(0,a.pointer.width/2),r.lineTo(a.pointer.width,0),r.closePath(),r.fill(),r.beginPath(),r.setFillStyle("#FFFFFF"),r.arc(0,0,a.pointer.width/6,0,2*Math.PI,!1),r.fill(),r.restore()}),!1!==n.dataLabel&&Q(a,l,s,n,i,r),K(n,i,r),1===o&&"gauge"===n.type&&(a.oldAngle=e[0]._proportion_,a.oldData=e[0].data),{center:s,radius:l,innerRadius:c,categories:t,totalAngle:u}}function xt(t,e,n,i){var r=arguments.length>4&&void 0!==arguments[4]?arguments[4]:1,o=e.extra.radar||{},a=w(e.categories.length),s={x:e.width/2,y:(e.height-n.legendHeight)/2},l=Math.min(s.x-(b(e.categories)+n.radarLabelTextMargin),s.y-n.radarLabelTextMargin);l-=n.padding,i.beginPath(),i.setLineWidth(1*e.pixelRatio),i.setStrokeStyle(o.gridColor||"#cccccc"),a.forEach(function(t){var e=h(l*Math.cos(t),l*Math.sin(t),s);i.moveTo(s.x,s.y),i.lineTo(e.x,e.y)}),i.stroke(),i.closePath();for(var c=function(t){var r={};i.beginPath(),i.setLineWidth(1*e.pixelRatio),i.setStrokeStyle(o.gridColor||"#cccccc"),a.forEach(function(e,o){var a=h(l/n.radarGridCount*t*Math.cos(e),l/n.radarGridCount*t*Math.sin(e),s);0===o?(r=a,i.moveTo(a.x,a.y)):i.lineTo(a.x,a.y)}),i.lineTo(r.x,r.y),i.stroke(),i.closePath()},u=1;u<=n.radarGridCount;u++)c(u);var f=O(a,s,l,t,e,r);return f.forEach(function(t,r){if(i.beginPath(),i.setFillStyle(t.color),i.setGlobalAlpha(.3),t.data.forEach(function(t,e){0===e?i.moveTo(t.position.x,t.position.y):i.lineTo(t.position.x,t.position.y)}),i.closePath(),i.fill(),i.setGlobalAlpha(1),!1!==e.dataPointShape){var o=n.dataPointShape[r%n.dataPointShape.length],a=t.data.map(function(t){return t.position});Z(a,t.color,o,i,e)}}),tt(a,l,s,e,n,i),{center:s,radius:l,angleList:a}}function bt(t,e){e.draw()}var wt={easeIn:function(t){return Math.pow(t,3)},easeOut:function(t){return Math.pow(t-1,3)+1},easeInOut:function(t){return(t/=.5)<1?.5*Math.pow(t,3):.5*(Math.pow(t-2,3)+2)},linear:function(t){return t}};function St(t){this.isStop=!1,t.duration="undefined"===typeof t.duration?1e3:t.duration,t.timing=t.timing||"linear";var e=17,n=function(){return"undefined"!==typeof requestAnimationFrame?requestAnimationFrame:"undefined"!==typeof setTimeout?function(t,e){setTimeout(function(){var e=+new Date;t(e)},e)}:function(t){t(null)}},i=n(),r=null,o=function(n){if(null===n||!0===this.isStop)return t.onProcess&&t.onProcess(1),void(t.onAnimationFinish&&t.onAnimationFinish());if(null===r&&(r=n),n-r<t.duration){var a=(n-r)/t.duration,s=wt[t.timing];a=s(a),t.onProcess&&t.onProcess(a),i(o,e)}else t.onProcess&&t.onProcess(1),t.onAnimationFinish&&t.onAnimationFinish()};o=o.bind(this),i(o,e)}function Mt(t,e,n,i){var r=this,o=e.series,a=e.categories;o=d(o,n),o=p(o,e);var s=D(o,e,n),l=s.legendHeight;n.legendHeight=l;var c=X(o,e,n),u=c.yAxisWidth;if(n.yAxisWidth=u,a&&a.length){var h=L(a,e,n),f=h.xAxisHeight,g=h.angle;n.xAxisHeight=f,n._xAxisTextAngle_=g}"pie"!==t&&"ring"!==t||(n._pieTextMaxLength_=!1===e.dataLabel?0:F(o));var v=e.animation?1e3:0;switch(this.animationInstance&&this.animationInstance.stop(),i.clearRect(0,0,e.width,e.height),t){case"line":this.animationInstance=new St({timing:"easeIn",duration:v,onProcess:function(t){e.rotate&&Y(i,e),pt(a,e,n,i),dt(a,e,n,i);var s=ut(o,e,n,i,t),l=s.xAxisPoints,c=s.calPoints,u=s.eachSpacing;r.chartData.xAxisPoints=l,r.chartData.calPoints=c,r.chartData.eachSpacing=u,vt(e.series,e,n,i),gt(o,e,n,i),ft(e,n,i,t,u,l),bt(e,i)},onAnimationFinish:function(){r.event.trigger("renderComplete")}});break;case"mix":this.animationInstance=new St({timing:"easeIn",duration:v,onProcess:function(t){e.rotate&&Y(i,e),pt(a,e,n,i),dt(a,e,n,i);var s=ht(o,e,n,i,t),l=s.xAxisPoints,c=s.calPoints,u=s.eachSpacing;r.chartData.xAxisPoints=l,r.chartData.calPoints=c,r.chartData.eachSpacing=u,vt(e.series,e,n,i),gt(o,e,n,i),ft(e,n,i,t,u,l),bt(e,i)},onAnimationFinish:function(){r.event.trigger("renderComplete")}});break;case"column":this.animationInstance=new St({timing:"easeIn",duration:v,onProcess:function(t){e.rotate&&Y(i,e),pt(a,e,n,i),dt(a,e,n,i);var s=st(o,e,n,i,t),l=s.xAxisPoints,c=s.calPoints,u=s.eachSpacing;r.chartData.xAxisPoints=l,r.chartData.calPoints=c,r.chartData.eachSpacing=u,vt(e.series,e,n,i),gt(o,e,n,i),ft(e,n,i,t,u,l),bt(e,i)},onAnimationFinish:function(){r.event.trigger("renderComplete")}});break;case"area":this.animationInstance=new St({timing:"easeIn",duration:v,onProcess:function(t){e.rotate&&Y(i,e),pt(a,e,n,i),dt(a,e,n,i);var s=ct(o,e,n,i,t),l=s.xAxisPoints,c=s.calPoints,u=s.eachSpacing;r.chartData.xAxisPoints=l,r.chartData.calPoints=c,r.chartData.eachSpacing=u,vt(e.series,e,n,i),gt(o,e,n,i),ft(e,n,i,t,u,l),bt(e,i)},onAnimationFinish:function(){r.event.trigger("renderComplete")}});break;case"ring":case"pie":this.animationInstance=new St({timing:"easeInOut",duration:v,onProcess:function(t){e.rotate&&Y(i,e),r.chartData.pieData=mt(o,e,n,i,t),vt(e.series,e,n,i),ft(e,n,i,t),bt(e,i)},onAnimationFinish:function(){r.event.trigger("renderComplete")}});break;case"radar":this.animationInstance=new St({timing:"easeInOut",duration:v,onProcess:function(t){e.rotate&&Y(i,e),r.chartData.radarData=xt(o,e,n,i,t),vt(e.series,e,n,i),bt(e,i)},onAnimationFinish:function(){r.event.trigger("renderComplete")}});break;case"arcbar":this.animationInstance=new St({timing:"easeInOut",duration:v,onProcess:function(t){e.rotate&&Y(i,e),r.chartData.arcbarData=yt(o,e,n,i,t),bt(e,i)},onAnimationFinish:function(){r.event.trigger("renderComplete")}});break;case"gauge":this.animationInstance=new St({timing:"easeInOut",duration:v,onProcess:function(t){e.rotate&&Y(i,e),r.chartData.gaugeData=_t(a,o,e,n,i,t),bt(e,i)},onAnimationFinish:function(){r.event.trigger("renderComplete")}});break;case"candle":this.animationInstance=new St({timing:"easeIn",duration:v,onProcess:function(t){e.rotate&&Y(i,e),pt(a,e,n,i),dt(a,e,n,i);var s=lt(o,e,n,i,t),l=s.xAxisPoints,c=s.calPoints,u=s.eachSpacing;r.chartData.xAxisPoints=l,r.chartData.calPoints=c,r.chartData.eachSpacing=u,vt(e.series,e,n,i),gt(o,e,n,i),ft(e,n,i,t,u,l),bt(e,i)},onAnimationFinish:function(){r.event.trigger("renderComplete")}});break}}function At(){this.events={}}St.prototype.stop=function(){this.isStop=!0},At.prototype.addEventListener=function(t,e){this.events[t]=this.events[t]||[],this.events[t].push(e)},At.prototype.trigger=function(){for(var t=arguments.length,e=Array(t),n=0;n<t;n++)e[n]=arguments[n];var i=e[0],r=e.slice(1);this.events[i]&&this.events[i].forEach(function(t){try{t.apply(null,r)}catch(e){console.error(e," at components\\u-charts.js:3233")}})};var Tt=function(t){t.fontSize=t.fontSize?t.fontSize*t.pixelRatio:13*t.pixelRatio,t.title=t.title||{},t.subtitle=t.subtitle||{},t.yAxis=t.yAxis||{},t.yAxis.gridType=t.yAxis.gridType?t.yAxis.gridType:"solid",t.yAxis.dashLength=t.yAxis.dashLength?t.yAxis.dashLength:4*t.pixelRatio,t.xAxis=t.xAxis||{},t.xAxis.rotateLabel=!!t.xAxis.rotateLabel,t.xAxis.type=t.xAxis.type?t.xAxis.type:"calibration",t.xAxis.gridType=t.xAxis.gridType?t.xAxis.gridType:"solid",t.xAxis.dashLength=t.xAxis.dashLength?t.xAxis.dashLength:4*t.pixelRatio,t.xAxis.itemCount=t.xAxis.itemCount?t.xAxis.itemCount:5,t.xAxis.scrollAlign=t.xAxis.scrollAlign?t.xAxis.scrollAlign:"left",t.extra=t.extra||{},t.legend=!1!==t.legend,t.rotate=!!t.rotate,t.animation=!1!==t.animation;var r=i({},n);if(r.yAxisTitleWidth=!0!==t.yAxis.disabled&&t.yAxis.title?r.yAxisTitleWidth:0,"pie"!=t.type&&"ring"!=t.type||(r.pieChartLinePadding=!1===t.dataLabel?0:t.extra.pie.lableWidth||r.pieChartLinePadding*t.pixelRatio),r.pieChartTextPadding=!1===t.dataLabel?0:r.pieChartTextPadding*t.pixelRatio,r.yAxisSplit=t.yAxis.splitNumber?t.yAxis.splitNumber:n.yAxisSplit,r.rotate=t.rotate,t.rotate){var o=t.width,a=t.height;t.width=a,t.height=o}if(r.yAxisWidth=n.yAxisWidth*t.pixelRatio,r.xAxisHeight=n.xAxisHeight*t.pixelRatio,t.enableScroll&&t.xAxis.scrollShow&&(r.xAxisHeight+=4*t.pixelRatio),r.xAxisLineHeight=n.xAxisLineHeight*t.pixelRatio,r.legendHeight=n.legendHeight*t.pixelRatio,r.padding=n.padding*t.pixelRatio,r.fontSize=t.fontSize,r.titleFontSize=n.titleFontSize*t.pixelRatio,r.subtitleFontSize=n.subtitleFontSize*t.pixelRatio,r.toolTipPadding=n.toolTipPadding*t.pixelRatio,r.toolTipLineHeight=n.toolTipLineHeight*t.pixelRatio,r.columePadding=n.columePadding*t.pixelRatio,n.pixelRatio=t.pixelRatio,n.fontSize=t.fontSize,n.rotate=t.rotate,this.opts=t,this.config=r,t.$this=t.$this?t.$this:this,this.context=e.createCanvasContext(t.canvasId,t.$this),this.chartData={},this.event=new At,this.scrollOption={currentOffset:0,startTouchX:0,distance:0},t.enableScroll&&"right"==t.xAxis.scrollAlign){var s=X(t.series,t,r),l=s.yAxisWidth;r.yAxisWidth=l;var c=0,u=W(t.categories,t,r),h=u.xAxisPoints,f=u.startX,d=u.endX,p=u.eachSpacing,g=p*(h.length-1),v=d-f;c=v-g,this.scrollOption={currentOffset:c,startTouchX:c,distance:0},t._scrollDistance_=c}Mt.call(this,t.type,t,r,this.context)};Tt.prototype.updateData=function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};this.opts.series=t.series||this.opts.series,this.opts.categories=t.categories||this.opts.categories,this.opts.title=i({},this.opts.title,t.title||{}),this.opts.subtitle=i({},this.opts.subtitle,t.subtitle||{}),Mt.call(this,this.opts.type,this.opts,this.config,this.context)},Tt.prototype.zoom=function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:this.opts.xAxis.itemCount;!0===this.opts.enableScroll?(this.opts.animation=!1,this.opts.xAxis.itemCount=t.itemCount,Mt.call(this,this.opts.type,this.opts,this.config,this.context)):console.log("请启用滚动条后使用！"," at components\\u-charts.js:3351")},Tt.prototype.stopAnimation=function(){this.animationInstance&&this.animationInstance.stop()},Tt.prototype.addEventListener=function(t,e){this.event.addEventListener(t,e)},Tt.prototype.getCurrentDataIndex=function(t){var e=t.mp.changedTouches[0];if(e){var n=_(e,this.opts,t);return"pie"===this.opts.type||"ring"===this.opts.type?P({x:n.x,y:n.y},this.chartData.pieData):"radar"===this.opts.type?k({x:n.x,y:n.y},this.chartData.radarData,this.opts.categories.length):A({x:n.x,y:n.y},this.chartData.xAxisPoints,this.opts,this.config,Math.abs(this.scrollOption.currentOffset))}return-1},Tt.prototype.showToolTip=function(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{},n=t.mp.changedTouches[0],r=_(n,this.opts,t);if("line"===this.opts.type||"area"===this.opts.type||"mix"===this.opts.type||"column"===this.opts.type){var o=this.getCurrentDataIndex(t),a=this.scrollOption.currentOffset,s=i({},this.opts,{_scrollDistance_:a,animation:!1});if(o>-1){var l=x(this.opts.series,o);if(0!==l.length){var c=S(l,this.chartData.calPoints,o,this.opts.categories,e),u=c.textList,h=c.offset;h.y=r.y,s.tooltip={textList:u,offset:h,option:e,index:o}}}Mt.call(this,s.type,s,this.config,this.context)}if("candle"===this.opts.type){o=this.getCurrentDataIndex(t),a=this.scrollOption.currentOffset,s=i({},this.opts,{_scrollDistance_:a,animation:!1});if(o>-1){l=x(this.opts.series,o);if(0!==l.length){c=M(this.opts.series[0].data,l,this.chartData.calPoints,o,this.opts.categories,this.opts.extra.candle,e),u=c.textList,h=c.offset;h.y=r.y,s.tooltip={textList:u,offset:h,option:e,index:o}}}Mt.call(this,s.type,s,this.config,this.context)}if("pie"===this.opts.type||"ring"===this.opts.type){o=this.getCurrentDataIndex(t),a=this.scrollOption.currentOffset,s=i({},this.opts,{_scrollDistance_:a,animation:!1});if(o>-1){l=this.opts.series[o],u=[{text:e.format?e.format(l):l.name+": "+l.data,color:l.color}],h={x:r.x,y:r.y};s.tooltip={textList:u,offset:h,option:e,index:o}}Mt.call(this,s.type,s,this.config,this.context)}},Tt.prototype.scrollStart=function(t){var e=t.mp.changedTouches[0];e&&!0===this.opts.enableScroll&&(e.x?this.scrollOption.startTouchX=e.x:this.scrollOption.startTouchX=e.clientX)},Tt.prototype.scroll=function(t){var e=t.mp.changedTouches[0];if(e&&!0===this.opts.enableScroll){var n;n=e.x?e.x-this.scrollOption.startTouchX:e.clientX-this.scrollOption.startTouchX;var r=this.scrollOption.currentOffset,o=s(r+n,this.chartData,this.config,this.opts);this.scrollOption.distance=n=o-r;var a=i({},this.opts,{_scrollDistance_:r+n,animation:!1});Mt.call(this,a.type,a,this.config,this.context)}},Tt.prototype.scrollEnd=function(t){if(!0===this.opts.enableScroll){var e=this.scrollOption,n=e.currentOffset,i=e.distance;this.scrollOption.currentOffset=n+i,this.scrollOption.distance=0}},t.exports=Tt}).call(this,n("6e42")["default"])},"2a44":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("cbb8"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},"3a0a":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("5647"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},"46a8":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("9b21"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},"4e43":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("848f"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},"595e":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("74ed"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},6036:function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("2001"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},"612c":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("57a3"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},6492:function(t,e,n){"use strict";(function(t){n("b0bc");var e=o(n("66fd")),i=o(n("b3ad")),r=o(n("c8c7"));function o(t){return t&&t.__esModule?t:{default:t}}function a(t){for(var e=1;e<arguments.length;e++){var n=null!=arguments[e]?arguments[e]:{},i=Object.keys(n);"function"===typeof Object.getOwnPropertySymbols&&(i=i.concat(Object.getOwnPropertySymbols(n).filter(function(t){return Object.getOwnPropertyDescriptor(n,t).enumerable}))),i.forEach(function(e){s(t,e,n[e])})}return t}function s(t,e,n){return e in t?Object.defineProperty(t,e,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[e]=n,t}e.default.config.productionTip=!1,e.default.prototype.http=r.default,i.default.mpType="app";var l=new e.default(a({},i.default));t(l).$mount()}).call(this,n("6e42")["createApp"])},"66fd":function(t,e,n){"use strict";n.r(e),function(t){
/*!
 * Vue.js v2.6.10
 * (c) 2014-2019 Evan You
 * Released under the MIT License.
 */
var n=Object.freeze({});function i(t){return void 0===t||null===t}function r(t){return void 0!==t&&null!==t}function o(t){return!0===t}function a(t){return!1===t}function s(t){return"string"===typeof t||"number"===typeof t||"symbol"===typeof t||"boolean"===typeof t}function l(t){return null!==t&&"object"===typeof t}var c=Object.prototype.toString;function u(t){return"[object Object]"===c.call(t)}function h(t){return"[object RegExp]"===c.call(t)}function f(t){var e=parseFloat(String(t));return e>=0&&Math.floor(e)===e&&isFinite(t)}function d(t){return r(t)&&"function"===typeof t.then&&"function"===typeof t.catch}function p(t){return null==t?"":Array.isArray(t)||u(t)&&t.toString===c?JSON.stringify(t,null,2):String(t)}function g(t){var e=parseFloat(t);return isNaN(e)?t:e}function v(t,e){for(var n=Object.create(null),i=t.split(","),r=0;r<i.length;r++)n[i[r]]=!0;return e?function(t){return n[t.toLowerCase()]}:function(t){return n[t]}}v("slot,component",!0);var m=v("key,ref,slot,slot-scope,is");function y(t,e){if(t.length){var n=t.indexOf(e);if(n>-1)return t.splice(n,1)}}var _=Object.prototype.hasOwnProperty;function x(t,e){return _.call(t,e)}function b(t){var e=Object.create(null);return function(n){var i=e[n];return i||(e[n]=t(n))}}var w=/-(\w)/g,S=b(function(t){return t.replace(w,function(t,e){return e?e.toUpperCase():""})}),M=b(function(t){return t.charAt(0).toUpperCase()+t.slice(1)}),A=/\B([A-Z])/g,T=b(function(t){return t.replace(A,"-$1").toLowerCase()});function k(t,e){function n(n){var i=arguments.length;return i?i>1?t.apply(e,arguments):t.call(e,n):t.call(e)}return n._length=t.length,n}function P(t,e){return t.bind(e)}var C=Function.prototype.bind?P:k;function I(t,e){e=e||0;var n=t.length-e,i=new Array(n);while(n--)i[n]=t[n+e];return i}function D(t,e){for(var n in e)t[n]=e[n];return t}function L(t){for(var e={},n=0;n<t.length;n++)t[n]&&D(e,t[n]);return e}function O(t,e,n){}var E=function(t,e,n){return!1},R=function(t){return t};function N(t,e){if(t===e)return!0;var n=l(t),i=l(e);if(!n||!i)return!n&&!i&&String(t)===String(e);try{var r=Array.isArray(t),o=Array.isArray(e);if(r&&o)return t.length===e.length&&t.every(function(t,n){return N(t,e[n])});if(t instanceof Date&&e instanceof Date)return t.getTime()===e.getTime();if(r||o)return!1;var a=Object.keys(t),s=Object.keys(e);return a.length===s.length&&a.every(function(n){return N(t[n],e[n])})}catch(c){return!1}}function z(t,e){for(var n=0;n<t.length;n++)if(N(t[n],e))return n;return-1}function F(t){var e=!1;return function(){e||(e=!0,t.apply(this,arguments))}}var B=["component","directive","filter"],$=["beforeCreate","created","beforeMount","mounted","beforeUpdate","updated","beforeDestroy","destroyed","activated","deactivated","errorCaptured","serverPrefetch"],H={optionMergeStrategies:Object.create(null),silent:!1,productionTip:!1,devtools:!1,performance:!1,errorHandler:null,warnHandler:null,ignoredElements:[],keyCodes:Object.create(null),isReservedTag:E,isReservedAttr:E,isUnknownElement:E,getTagNamespace:O,parsePlatformTagName:R,mustUseProp:E,async:!0,_lifecycleHooks:$},W=/a-zA-Z\u00B7\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u037D\u037F-\u1FFF\u200C-\u200D\u203F-\u2040\u2070-\u218F\u2C00-\u2FEF\u3001-\uD7FF\uF900-\uFDCF\uFDF0-\uFFFD/;function V(t){var e=(t+"").charCodeAt(0);return 36===e||95===e}function j(t,e,n,i){Object.defineProperty(t,e,{value:n,enumerable:!!i,writable:!0,configurable:!0})}var G=new RegExp("[^"+W.source+".$_\\d]");function U(t){if(!G.test(t)){var e=t.split(".");return function(t){for(var n=0;n<e.length;n++){if(!t)return;t=t[e[n]]}return t}}}var X,q="__proto__"in{},Y="undefined"!==typeof window,Z="undefined"!==typeof WXEnvironment&&!!WXEnvironment.platform,K=Z&&WXEnvironment.platform.toLowerCase(),J=Y&&window.navigator.userAgent.toLowerCase(),Q=J&&/msie|trident/.test(J),tt=(J&&J.indexOf("msie 9.0"),J&&J.indexOf("edge/")>0),et=(J&&J.indexOf("android"),J&&/iphone|ipad|ipod|ios/.test(J)||"ios"===K),nt=(J&&/chrome\/\d+/.test(J),J&&/phantomjs/.test(J),J&&J.match(/firefox\/(\d+)/),{}.watch);if(Y)try{var it={};Object.defineProperty(it,"passive",{get:function(){}}),window.addEventListener("test-passive",null,it)}catch(nr){}var rt=function(){return void 0===X&&(X=!Y&&!Z&&"undefined"!==typeof t&&(t["process"]&&"server"===t["process"].env.VUE_ENV)),X},ot=Y&&window.__VUE_DEVTOOLS_GLOBAL_HOOK__;function at(t){return"function"===typeof t&&/native code/.test(t.toString())}var st,lt="undefined"!==typeof Symbol&&at(Symbol)&&"undefined"!==typeof Reflect&&at(Reflect.ownKeys);st="undefined"!==typeof Set&&at(Set)?Set:function(){function t(){this.set=Object.create(null)}return t.prototype.has=function(t){return!0===this.set[t]},t.prototype.add=function(t){this.set[t]=!0},t.prototype.clear=function(){this.set=Object.create(null)},t}();var ct=O,ut=0,ht=function(){this.id=ut++,this.subs=[]};ht.prototype.addSub=function(t){this.subs.push(t)},ht.prototype.removeSub=function(t){y(this.subs,t)},ht.prototype.depend=function(){ht.target&&ht.target.addDep(this)},ht.prototype.notify=function(){var t=this.subs.slice();for(var e=0,n=t.length;e<n;e++)t[e].update()},ht.target=null;var ft=[];function dt(t){ft.push(t),ht.target=t}function pt(){ft.pop(),ht.target=ft[ft.length-1]}var gt=function(t,e,n,i,r,o,a,s){this.tag=t,this.data=e,this.children=n,this.text=i,this.elm=r,this.ns=void 0,this.context=o,this.fnContext=void 0,this.fnOptions=void 0,this.fnScopeId=void 0,this.key=e&&e.key,this.componentOptions=a,this.componentInstance=void 0,this.parent=void 0,this.raw=!1,this.isStatic=!1,this.isRootInsert=!0,this.isComment=!1,this.isCloned=!1,this.isOnce=!1,this.asyncFactory=s,this.asyncMeta=void 0,this.isAsyncPlaceholder=!1},vt={child:{configurable:!0}};vt.child.get=function(){return this.componentInstance},Object.defineProperties(gt.prototype,vt);var mt=function(t){void 0===t&&(t="");var e=new gt;return e.text=t,e.isComment=!0,e};function yt(t){return new gt(void 0,void 0,void 0,String(t))}function _t(t){var e=new gt(t.tag,t.data,t.children&&t.children.slice(),t.text,t.elm,t.context,t.componentOptions,t.asyncFactory);return e.ns=t.ns,e.isStatic=t.isStatic,e.key=t.key,e.isComment=t.isComment,e.fnContext=t.fnContext,e.fnOptions=t.fnOptions,e.fnScopeId=t.fnScopeId,e.asyncMeta=t.asyncMeta,e.isCloned=!0,e}var xt=Array.prototype,bt=Object.create(xt),wt=["push","pop","shift","unshift","splice","sort","reverse"];wt.forEach(function(t){var e=xt[t];j(bt,t,function(){var n=[],i=arguments.length;while(i--)n[i]=arguments[i];var r,o=e.apply(this,n),a=this.__ob__;switch(t){case"push":case"unshift":r=n;break;case"splice":r=n.slice(2);break}return r&&a.observeArray(r),a.dep.notify(),o})});var St=Object.getOwnPropertyNames(bt),Mt=!0;function At(t){Mt=t}var Tt=function(t){this.value=t,this.dep=new ht,this.vmCount=0,j(t,"__ob__",this),Array.isArray(t)?(q?kt(t,bt):Pt(t,bt,St),this.observeArray(t)):this.walk(t)};function kt(t,e){t.__proto__=e}function Pt(t,e,n){for(var i=0,r=n.length;i<r;i++){var o=n[i];j(t,o,e[o])}}function Ct(t,e){var n;if(l(t)&&!(t instanceof gt))return x(t,"__ob__")&&t.__ob__ instanceof Tt?n=t.__ob__:Mt&&!rt()&&(Array.isArray(t)||u(t))&&Object.isExtensible(t)&&!t._isVue&&(n=new Tt(t)),e&&n&&n.vmCount++,n}function It(t,e,n,i,r){var o=new ht,a=Object.getOwnPropertyDescriptor(t,e);if(!a||!1!==a.configurable){var s=a&&a.get,l=a&&a.set;s&&!l||2!==arguments.length||(n=t[e]);var c=!r&&Ct(n);Object.defineProperty(t,e,{enumerable:!0,configurable:!0,get:function(){var e=s?s.call(t):n;return ht.target&&(o.depend(),c&&(c.dep.depend(),Array.isArray(e)&&Ot(e))),e},set:function(e){var i=s?s.call(t):n;e===i||e!==e&&i!==i||s&&!l||(l?l.call(t,e):n=e,c=!r&&Ct(e),o.notify())}})}}function Dt(t,e,n){if(Array.isArray(t)&&f(e))return t.length=Math.max(t.length,e),t.splice(e,1,n),n;if(e in t&&!(e in Object.prototype))return t[e]=n,n;var i=t.__ob__;return t._isVue||i&&i.vmCount?n:i?(It(i.value,e,n),i.dep.notify(),n):(t[e]=n,n)}function Lt(t,e){if(Array.isArray(t)&&f(e))t.splice(e,1);else{var n=t.__ob__;t._isVue||n&&n.vmCount||x(t,e)&&(delete t[e],n&&n.dep.notify())}}function Ot(t){for(var e=void 0,n=0,i=t.length;n<i;n++)e=t[n],e&&e.__ob__&&e.__ob__.dep.depend(),Array.isArray(e)&&Ot(e)}Tt.prototype.walk=function(t){for(var e=Object.keys(t),n=0;n<e.length;n++)It(t,e[n])},Tt.prototype.observeArray=function(t){for(var e=0,n=t.length;e<n;e++)Ct(t[e])};var Et=H.optionMergeStrategies;function Rt(t,e){if(!e)return t;for(var n,i,r,o=lt?Reflect.ownKeys(e):Object.keys(e),a=0;a<o.length;a++)n=o[a],"__ob__"!==n&&(i=t[n],r=e[n],x(t,n)?i!==r&&u(i)&&u(r)&&Rt(i,r):Dt(t,n,r));return t}function Nt(t,e,n){return n?function(){var i="function"===typeof e?e.call(n,n):e,r="function"===typeof t?t.call(n,n):t;return i?Rt(i,r):r}:e?t?function(){return Rt("function"===typeof e?e.call(this,this):e,"function"===typeof t?t.call(this,this):t)}:e:t}function zt(t,e){var n=e?t?t.concat(e):Array.isArray(e)?e:[e]:t;return n?Ft(n):n}function Ft(t){for(var e=[],n=0;n<t.length;n++)-1===e.indexOf(t[n])&&e.push(t[n]);return e}function Bt(t,e,n,i){var r=Object.create(t||null);return e?D(r,e):r}Et.data=function(t,e,n){return n?Nt(t,e,n):e&&"function"!==typeof e?t:Nt(t,e)},$.forEach(function(t){Et[t]=zt}),B.forEach(function(t){Et[t+"s"]=Bt}),Et.watch=function(t,e,n,i){if(t===nt&&(t=void 0),e===nt&&(e=void 0),!e)return Object.create(t||null);if(!t)return e;var r={};for(var o in D(r,t),e){var a=r[o],s=e[o];a&&!Array.isArray(a)&&(a=[a]),r[o]=a?a.concat(s):Array.isArray(s)?s:[s]}return r},Et.props=Et.methods=Et.inject=Et.computed=function(t,e,n,i){if(!t)return e;var r=Object.create(null);return D(r,t),e&&D(r,e),r},Et.provide=Nt;var $t=function(t,e){return void 0===e?t:e};function Ht(t,e){var n=t.props;if(n){var i,r,o,a={};if(Array.isArray(n)){i=n.length;while(i--)r=n[i],"string"===typeof r&&(o=S(r),a[o]={type:null})}else if(u(n))for(var s in n)r=n[s],o=S(s),a[o]=u(r)?r:{type:r};else 0;t.props=a}}function Wt(t,e){var n=t.inject;if(n){var i=t.inject={};if(Array.isArray(n))for(var r=0;r<n.length;r++)i[n[r]]={from:n[r]};else if(u(n))for(var o in n){var a=n[o];i[o]=u(a)?D({from:o},a):{from:a}}else 0}}function Vt(t){var e=t.directives;if(e)for(var n in e){var i=e[n];"function"===typeof i&&(e[n]={bind:i,update:i})}}function jt(t,e,n){if("function"===typeof e&&(e=e.options),Ht(e,n),Wt(e,n),Vt(e),!e._base&&(e.extends&&(t=jt(t,e.extends,n)),e.mixins))for(var i=0,r=e.mixins.length;i<r;i++)t=jt(t,e.mixins[i],n);var o,a={};for(o in t)s(o);for(o in e)x(t,o)||s(o);function s(i){var r=Et[i]||$t;a[i]=r(t[i],e[i],n,i)}return a}function Gt(t,e,n,i){if("string"===typeof n){var r=t[e];if(x(r,n))return r[n];var o=S(n);if(x(r,o))return r[o];var a=M(o);if(x(r,a))return r[a];var s=r[n]||r[o]||r[a];return s}}function Ut(t,e,n,i){var r=e[t],o=!x(n,t),a=n[t],s=Zt(Boolean,r.type);if(s>-1)if(o&&!x(r,"default"))a=!1;else if(""===a||a===T(t)){var l=Zt(String,r.type);(l<0||s<l)&&(a=!0)}if(void 0===a){a=Xt(i,r,t);var c=Mt;At(!0),Ct(a),At(c)}return a}function Xt(t,e,n){if(x(e,"default")){var i=e.default;return t&&t.$options.propsData&&void 0===t.$options.propsData[n]&&void 0!==t._props[n]?t._props[n]:"function"===typeof i&&"Function"!==qt(e.type)?i.call(t):i}}function qt(t){var e=t&&t.toString().match(/^\s*function (\w+)/);return e?e[1]:""}function Yt(t,e){return qt(t)===qt(e)}function Zt(t,e){if(!Array.isArray(e))return Yt(e,t)?0:-1;for(var n=0,i=e.length;n<i;n++)if(Yt(e[n],t))return n;return-1}function Kt(t,e,n){dt();try{if(e){var i=e;while(i=i.$parent){var r=i.$options.errorCaptured;if(r)for(var o=0;o<r.length;o++)try{var a=!1===r[o].call(i,t,e,n);if(a)return}catch(nr){Qt(nr,i,"errorCaptured hook")}}}Qt(t,e,n)}finally{pt()}}function Jt(t,e,n,i,r){var o;try{o=n?t.apply(e,n):t.call(e),o&&!o._isVue&&d(o)&&!o._handled&&(o.catch(function(t){return Kt(t,i,r+" (Promise/async)")}),o._handled=!0)}catch(nr){Kt(nr,i,r)}return o}function Qt(t,e,n){if(H.errorHandler)try{return H.errorHandler.call(null,t,e,n)}catch(nr){nr!==t&&te(nr,null,"config.errorHandler")}te(t,e,n)}function te(t,e,n){if(!Y&&!Z||"undefined"===typeof console)throw t;console.error(t)}var ee,ne=[],ie=!1;function re(){ie=!1;var t=ne.slice(0);ne.length=0;for(var e=0;e<t.length;e++)t[e]()}if("undefined"!==typeof Promise&&at(Promise)){var oe=Promise.resolve();ee=function(){oe.then(re),et&&setTimeout(O)}}else if(Q||"undefined"===typeof MutationObserver||!at(MutationObserver)&&"[object MutationObserverConstructor]"!==MutationObserver.toString())ee="undefined"!==typeof setImmediate&&at(setImmediate)?function(){setImmediate(re)}:function(){setTimeout(re,0)};else{var ae=1,se=new MutationObserver(re),le=document.createTextNode(String(ae));se.observe(le,{characterData:!0}),ee=function(){ae=(ae+1)%2,le.data=String(ae)}}function ce(t,e){var n;if(ne.push(function(){if(t)try{t.call(e)}catch(nr){Kt(nr,e,"nextTick")}else n&&n(e)}),ie||(ie=!0,ee()),!t&&"undefined"!==typeof Promise)return new Promise(function(t){n=t})}var ue=new st;function he(t){fe(t,ue),ue.clear()}function fe(t,e){var n,i,r=Array.isArray(t);if(!(!r&&!l(t)||Object.isFrozen(t)||t instanceof gt)){if(t.__ob__){var o=t.__ob__.dep.id;if(e.has(o))return;e.add(o)}if(r){n=t.length;while(n--)fe(t[n],e)}else{i=Object.keys(t),n=i.length;while(n--)fe(t[i[n]],e)}}}var de=b(function(t){var e="&"===t.charAt(0);t=e?t.slice(1):t;var n="~"===t.charAt(0);t=n?t.slice(1):t;var i="!"===t.charAt(0);return t=i?t.slice(1):t,{name:t,once:n,capture:i,passive:e}});function pe(t,e){function n(){var t=arguments,i=n.fns;if(!Array.isArray(i))return Jt(i,null,arguments,e,"v-on handler");for(var r=i.slice(),o=0;o<r.length;o++)Jt(r[o],null,t,e,"v-on handler")}return n.fns=t,n}function ge(t,e,n,r,a,s){var l,c,u,h;for(l in t)c=t[l],u=e[l],h=de(l),i(c)||(i(u)?(i(c.fns)&&(c=t[l]=pe(c,s)),o(h.once)&&(c=t[l]=a(h.name,c,h.capture)),n(h.name,c,h.capture,h.passive,h.params)):c!==u&&(u.fns=c,t[l]=u));for(l in e)i(t[l])&&(h=de(l),r(h.name,e[l],h.capture))}function ve(t,e,n){var o=e.options.props;if(!i(o)){var a={},s=t.attrs,l=t.props;if(r(s)||r(l))for(var c in o){var u=T(c);me(a,l,c,u,!0)||me(a,s,c,u,!1)}return a}}function me(t,e,n,i,o){if(r(e)){if(x(e,n))return t[n]=e[n],o||delete e[n],!0;if(x(e,i))return t[n]=e[i],o||delete e[i],!0}return!1}function ye(t){for(var e=0;e<t.length;e++)if(Array.isArray(t[e]))return Array.prototype.concat.apply([],t);return t}function _e(t){return s(t)?[yt(t)]:Array.isArray(t)?be(t):void 0}function xe(t){return r(t)&&r(t.text)&&a(t.isComment)}function be(t,e){var n,a,l,c,u=[];for(n=0;n<t.length;n++)a=t[n],i(a)||"boolean"===typeof a||(l=u.length-1,c=u[l],Array.isArray(a)?a.length>0&&(a=be(a,(e||"")+"_"+n),xe(a[0])&&xe(c)&&(u[l]=yt(c.text+a[0].text),a.shift()),u.push.apply(u,a)):s(a)?xe(c)?u[l]=yt(c.text+a):""!==a&&u.push(yt(a)):xe(a)&&xe(c)?u[l]=yt(c.text+a.text):(o(t._isVList)&&r(a.tag)&&i(a.key)&&r(e)&&(a.key="__vlist"+e+"_"+n+"__"),u.push(a)));return u}function we(t){var e=t.$options.provide;e&&(t._provided="function"===typeof e?e.call(t):e)}function Se(t){var e=Me(t.$options.inject,t);e&&(At(!1),Object.keys(e).forEach(function(n){It(t,n,e[n])}),At(!0))}function Me(t,e){if(t){for(var n=Object.create(null),i=lt?Reflect.ownKeys(t):Object.keys(t),r=0;r<i.length;r++){var o=i[r];if("__ob__"!==o){var a=t[o].from,s=e;while(s){if(s._provided&&x(s._provided,a)){n[o]=s._provided[a];break}s=s.$parent}if(!s)if("default"in t[o]){var l=t[o].default;n[o]="function"===typeof l?l.call(e):l}else 0}}return n}}function Ae(t,e){if(!t||!t.length)return{};for(var n={},i=0,r=t.length;i<r;i++){var o=t[i],a=o.data;if(a&&a.attrs&&a.attrs.slot&&delete a.attrs.slot,o.context!==e&&o.fnContext!==e||!a||null==a.slot)(n.default||(n.default=[])).push(o);else{var s=a.slot,l=n[s]||(n[s]=[]);"template"===o.tag?l.push.apply(l,o.children||[]):l.push(o)}}for(var c in n)n[c].every(Te)&&delete n[c];return n}function Te(t){return t.isComment&&!t.asyncFactory||" "===t.text}function ke(t,e,i){var r,o=Object.keys(e).length>0,a=t?!!t.$stable:!o,s=t&&t.$key;if(t){if(t._normalized)return t._normalized;if(a&&i&&i!==n&&s===i.$key&&!o&&!i.$hasNormal)return i;for(var l in r={},t)t[l]&&"$"!==l[0]&&(r[l]=Pe(e,l,t[l]))}else r={};for(var c in e)c in r||(r[c]=Ce(e,c));return t&&Object.isExtensible(t)&&(t._normalized=r),j(r,"$stable",a),j(r,"$key",s),j(r,"$hasNormal",o),r}function Pe(t,e,n){var i=function(){var t=arguments.length?n.apply(null,arguments):n({});return t=t&&"object"===typeof t&&!Array.isArray(t)?[t]:_e(t),t&&(0===t.length||1===t.length&&t[0].isComment)?void 0:t};return n.proxy&&Object.defineProperty(t,e,{get:i,enumerable:!0,configurable:!0}),i}function Ce(t,e){return function(){return t[e]}}function Ie(t,e){var n,i,o,a,s;if(Array.isArray(t)||"string"===typeof t)for(n=new Array(t.length),i=0,o=t.length;i<o;i++)n[i]=e(t[i],i);else if("number"===typeof t)for(n=new Array(t),i=0;i<t;i++)n[i]=e(i+1,i);else if(l(t))if(lt&&t[Symbol.iterator]){n=[];var c=t[Symbol.iterator](),u=c.next();while(!u.done)n.push(e(u.value,n.length)),u=c.next()}else for(a=Object.keys(t),n=new Array(a.length),i=0,o=a.length;i<o;i++)s=a[i],n[i]=e(t[s],s,i);return r(n)||(n=[]),n._isVList=!0,n}function De(t,e,n,i){var r,o=this.$scopedSlots[t];o?(n=n||{},i&&(n=D(D({},i),n)),r=o(n)||e):r=this.$slots[t]||e;var a=n&&n.slot;return a?this.$createElement("template",{slot:a},r):r}function Le(t){return Gt(this.$options,"filters",t,!0)||R}function Oe(t,e){return Array.isArray(t)?-1===t.indexOf(e):t!==e}function Ee(t,e,n,i,r){var o=H.keyCodes[e]||n;return r&&i&&!H.keyCodes[e]?Oe(r,i):o?Oe(o,t):i?T(i)!==e:void 0}function Re(t,e,n,i,r){if(n)if(l(n)){var o;Array.isArray(n)&&(n=L(n));var a=function(a){if("class"===a||"style"===a||m(a))o=t;else{var s=t.attrs&&t.attrs.type;o=i||H.mustUseProp(e,s,a)?t.domProps||(t.domProps={}):t.attrs||(t.attrs={})}var l=S(a),c=T(a);if(!(l in o)&&!(c in o)&&(o[a]=n[a],r)){var u=t.on||(t.on={});u["update:"+a]=function(t){n[a]=t}}};for(var s in n)a(s)}else;return t}function Ne(t,e){var n=this._staticTrees||(this._staticTrees=[]),i=n[t];return i&&!e?i:(i=n[t]=this.$options.staticRenderFns[t].call(this._renderProxy,null,this),Fe(i,"__static__"+t,!1),i)}function ze(t,e,n){return Fe(t,"__once__"+e+(n?"_"+n:""),!0),t}function Fe(t,e,n){if(Array.isArray(t))for(var i=0;i<t.length;i++)t[i]&&"string"!==typeof t[i]&&Be(t[i],e+"_"+i,n);else Be(t,e,n)}function Be(t,e,n){t.isStatic=!0,t.key=e,t.isOnce=n}function $e(t,e){if(e)if(u(e)){var n=t.on=t.on?D({},t.on):{};for(var i in e){var r=n[i],o=e[i];n[i]=r?[].concat(r,o):o}}else;return t}function He(t,e,n,i){e=e||{$stable:!n};for(var r=0;r<t.length;r++){var o=t[r];Array.isArray(o)?He(o,e,n):o&&(o.proxy&&(o.fn.proxy=!0),e[o.key]=o.fn)}return i&&(e.$key=i),e}function We(t,e){for(var n=0;n<e.length;n+=2){var i=e[n];"string"===typeof i&&i&&(t[e[n]]=e[n+1])}return t}function Ve(t,e){return"string"===typeof t?e+t:t}function je(t){t._o=ze,t._n=g,t._s=p,t._l=Ie,t._t=De,t._q=N,t._i=z,t._m=Ne,t._f=Le,t._k=Ee,t._b=Re,t._v=yt,t._e=mt,t._u=He,t._g=$e,t._d=We,t._p=Ve}function Ge(t,e,i,r,a){var s,l=this,c=a.options;x(r,"_uid")?(s=Object.create(r),s._original=r):(s=r,r=r._original);var u=o(c._compiled),h=!u;this.data=t,this.props=e,this.children=i,this.parent=r,this.listeners=t.on||n,this.injections=Me(c.inject,r),this.slots=function(){return l.$slots||ke(t.scopedSlots,l.$slots=Ae(i,r)),l.$slots},Object.defineProperty(this,"scopedSlots",{enumerable:!0,get:function(){return ke(t.scopedSlots,this.slots())}}),u&&(this.$options=c,this.$slots=this.slots(),this.$scopedSlots=ke(t.scopedSlots,this.$slots)),c._scopeId?this._c=function(t,e,n,i){var o=on(s,t,e,n,i,h);return o&&!Array.isArray(o)&&(o.fnScopeId=c._scopeId,o.fnContext=r),o}:this._c=function(t,e,n,i){return on(s,t,e,n,i,h)}}function Ue(t,e,i,o,a){var s=t.options,l={},c=s.props;if(r(c))for(var u in c)l[u]=Ut(u,c,e||n);else r(i.attrs)&&qe(l,i.attrs),r(i.props)&&qe(l,i.props);var h=new Ge(i,l,a,o,t),f=s.render.call(null,h._c,h);if(f instanceof gt)return Xe(f,i,h.parent,s,h);if(Array.isArray(f)){for(var d=_e(f)||[],p=new Array(d.length),g=0;g<d.length;g++)p[g]=Xe(d[g],i,h.parent,s,h);return p}}function Xe(t,e,n,i,r){var o=_t(t);return o.fnContext=n,o.fnOptions=i,e.slot&&((o.data||(o.data={})).slot=e.slot),o}function qe(t,e){for(var n in e)t[S(n)]=e[n]}je(Ge.prototype);var Ye={init:function(t,e){if(t.componentInstance&&!t.componentInstance._isDestroyed&&t.data.keepAlive){var n=t;Ye.prepatch(n,n)}else{var i=t.componentInstance=Je(t,Mn);i.$mount(e?t.elm:void 0,e)}},prepatch:function(t,e){var n=e.componentOptions,i=e.componentInstance=t.componentInstance;Pn(i,n.propsData,n.listeners,e,n.children)},insert:function(t){var e=t.context,n=t.componentInstance;n._isMounted||(n._isMounted=!0,Ln(n,"mounted")),t.data.keepAlive&&(e._isMounted?jn(n):In(n,!0))},destroy:function(t){var e=t.componentInstance;e._isDestroyed||(t.data.keepAlive?Dn(e,!0):e.$destroy())}},Ze=Object.keys(Ye);function Ke(t,e,n,a,s){if(!i(t)){var c=n.$options._base;if(l(t)&&(t=c.extend(t)),"function"===typeof t){var u;if(i(t.cid)&&(u=t,t=gn(u,c),void 0===t))return pn(u,e,n,a,s);e=e||{},di(t),r(e.model)&&en(t.options,e);var h=ve(e,t,s);if(o(t.options.functional))return Ue(t,h,e,n,a);var f=e.on;if(e.on=e.nativeOn,o(t.options.abstract)){var d=e.slot;e={},d&&(e.slot=d)}Qe(e);var p=t.options.name||s,g=new gt("vue-component-"+t.cid+(p?"-"+p:""),e,void 0,void 0,void 0,n,{Ctor:t,propsData:h,listeners:f,tag:s,children:a},u);return g}}}function Je(t,e){var n={_isComponent:!0,_parentVnode:t,parent:e},i=t.data.inlineTemplate;return r(i)&&(n.render=i.render,n.staticRenderFns=i.staticRenderFns),new t.componentOptions.Ctor(n)}function Qe(t){for(var e=t.hook||(t.hook={}),n=0;n<Ze.length;n++){var i=Ze[n],r=e[i],o=Ye[i];r===o||r&&r._merged||(e[i]=r?tn(o,r):o)}}function tn(t,e){var n=function(n,i){t(n,i),e(n,i)};return n._merged=!0,n}function en(t,e){var n=t.model&&t.model.prop||"value",i=t.model&&t.model.event||"input";(e.attrs||(e.attrs={}))[n]=e.model.value;var o=e.on||(e.on={}),a=o[i],s=e.model.callback;r(a)?(Array.isArray(a)?-1===a.indexOf(s):a!==s)&&(o[i]=[s].concat(a)):o[i]=s}var nn=1,rn=2;function on(t,e,n,i,r,a){return(Array.isArray(n)||s(n))&&(r=i,i=n,n=void 0),o(a)&&(r=rn),an(t,e,n,i,r)}function an(t,e,n,i,o){if(r(n)&&r(n.__ob__))return mt();if(r(n)&&r(n.is)&&(e=n.is),!e)return mt();var a,s,l;(Array.isArray(i)&&"function"===typeof i[0]&&(n=n||{},n.scopedSlots={default:i[0]},i.length=0),o===rn?i=_e(i):o===nn&&(i=ye(i)),"string"===typeof e)?(s=t.$vnode&&t.$vnode.ns||H.getTagNamespace(e),a=H.isReservedTag(e)?new gt(H.parsePlatformTagName(e),n,i,void 0,void 0,t):n&&n.pre||!r(l=Gt(t.$options,"components",e))?new gt(e,n,i,void 0,void 0,t):Ke(l,n,t,i,e)):a=Ke(e,n,t,i);return Array.isArray(a)?a:r(a)?(r(s)&&sn(a,s),r(n)&&ln(n),a):mt()}function sn(t,e,n){if(t.ns=e,"foreignObject"===t.tag&&(e=void 0,n=!0),r(t.children))for(var a=0,s=t.children.length;a<s;a++){var l=t.children[a];r(l.tag)&&(i(l.ns)||o(n)&&"svg"!==l.tag)&&sn(l,e,n)}}function ln(t){l(t.style)&&he(t.style),l(t.class)&&he(t.class)}function cn(t){t._vnode=null,t._staticTrees=null;var e=t.$options,i=t.$vnode=e._parentVnode,r=i&&i.context;t.$slots=Ae(e._renderChildren,r),t.$scopedSlots=n,t._c=function(e,n,i,r){return on(t,e,n,i,r,!1)},t.$createElement=function(e,n,i,r){return on(t,e,n,i,r,!0)};var o=i&&i.data;It(t,"$attrs",o&&o.attrs||n,null,!0),It(t,"$listeners",e._parentListeners||n,null,!0)}var un,hn=null;function fn(t){je(t.prototype),t.prototype.$nextTick=function(t){return ce(t,this)},t.prototype._render=function(){var t,e=this,n=e.$options,i=n.render,r=n._parentVnode;r&&(e.$scopedSlots=ke(r.data.scopedSlots,e.$slots,e.$scopedSlots)),e.$vnode=r;try{hn=e,t=i.call(e._renderProxy,e.$createElement)}catch(nr){Kt(nr,e,"render"),t=e._vnode}finally{hn=null}return Array.isArray(t)&&1===t.length&&(t=t[0]),t instanceof gt||(t=mt()),t.parent=r,t}}function dn(t,e){return(t.__esModule||lt&&"Module"===t[Symbol.toStringTag])&&(t=t.default),l(t)?e.extend(t):t}function pn(t,e,n,i,r){var o=mt();return o.asyncFactory=t,o.asyncMeta={data:e,context:n,children:i,tag:r},o}function gn(t,e){if(o(t.error)&&r(t.errorComp))return t.errorComp;if(r(t.resolved))return t.resolved;var n=hn;if(n&&r(t.owners)&&-1===t.owners.indexOf(n)&&t.owners.push(n),o(t.loading)&&r(t.loadingComp))return t.loadingComp;if(n&&!r(t.owners)){var a=t.owners=[n],s=!0,c=null,u=null;n.$on("hook:destroyed",function(){return y(a,n)});var h=function(t){for(var e=0,n=a.length;e<n;e++)a[e].$forceUpdate();t&&(a.length=0,null!==c&&(clearTimeout(c),c=null),null!==u&&(clearTimeout(u),u=null))},f=F(function(n){t.resolved=dn(n,e),s?a.length=0:h(!0)}),p=F(function(e){r(t.errorComp)&&(t.error=!0,h(!0))}),g=t(f,p);return l(g)&&(d(g)?i(t.resolved)&&g.then(f,p):d(g.component)&&(g.component.then(f,p),r(g.error)&&(t.errorComp=dn(g.error,e)),r(g.loading)&&(t.loadingComp=dn(g.loading,e),0===g.delay?t.loading=!0:c=setTimeout(function(){c=null,i(t.resolved)&&i(t.error)&&(t.loading=!0,h(!1))},g.delay||200)),r(g.timeout)&&(u=setTimeout(function(){u=null,i(t.resolved)&&p(null)},g.timeout)))),s=!1,t.loading?t.loadingComp:t.resolved}}function vn(t){return t.isComment&&t.asyncFactory}function mn(t){if(Array.isArray(t))for(var e=0;e<t.length;e++){var n=t[e];if(r(n)&&(r(n.componentOptions)||vn(n)))return n}}function yn(t){t._events=Object.create(null),t._hasHookEvent=!1;var e=t.$options._parentListeners;e&&wn(t,e)}function _n(t,e){un.$on(t,e)}function xn(t,e){un.$off(t,e)}function bn(t,e){var n=un;return function i(){var r=e.apply(null,arguments);null!==r&&n.$off(t,i)}}function wn(t,e,n){un=t,ge(e,n||{},_n,xn,bn,t),un=void 0}function Sn(t){var e=/^hook:/;t.prototype.$on=function(t,n){var i=this;if(Array.isArray(t))for(var r=0,o=t.length;r<o;r++)i.$on(t[r],n);else(i._events[t]||(i._events[t]=[])).push(n),e.test(t)&&(i._hasHookEvent=!0);return i},t.prototype.$once=function(t,e){var n=this;function i(){n.$off(t,i),e.apply(n,arguments)}return i.fn=e,n.$on(t,i),n},t.prototype.$off=function(t,e){var n=this;if(!arguments.length)return n._events=Object.create(null),n;if(Array.isArray(t)){for(var i=0,r=t.length;i<r;i++)n.$off(t[i],e);return n}var o,a=n._events[t];if(!a)return n;if(!e)return n._events[t]=null,n;var s=a.length;while(s--)if(o=a[s],o===e||o.fn===e){a.splice(s,1);break}return n},t.prototype.$emit=function(t){var e=this,n=e._events[t];if(n){n=n.length>1?I(n):n;for(var i=I(arguments,1),r='event handler for "'+t+'"',o=0,a=n.length;o<a;o++)Jt(n[o],e,i,e,r)}return e}}var Mn=null;function An(t){var e=Mn;return Mn=t,function(){Mn=e}}function Tn(t){var e=t.$options,n=e.parent;if(n&&!e.abstract){while(n.$options.abstract&&n.$parent)n=n.$parent;n.$children.push(t)}t.$parent=n,t.$root=n?n.$root:t,t.$children=[],t.$refs={},t._watcher=null,t._inactive=null,t._directInactive=!1,t._isMounted=!1,t._isDestroyed=!1,t._isBeingDestroyed=!1}function kn(t){t.prototype._update=function(t,e){var n=this,i=n.$el,r=n._vnode,o=An(n);n._vnode=t,n.$el=r?n.__patch__(r,t):n.__patch__(n.$el,t,e,!1),o(),i&&(i.__vue__=null),n.$el&&(n.$el.__vue__=n),n.$vnode&&n.$parent&&n.$vnode===n.$parent._vnode&&(n.$parent.$el=n.$el)},t.prototype.$forceUpdate=function(){var t=this;t._watcher&&t._watcher.update()},t.prototype.$destroy=function(){var t=this;if(!t._isBeingDestroyed){Ln(t,"beforeDestroy"),t._isBeingDestroyed=!0;var e=t.$parent;!e||e._isBeingDestroyed||t.$options.abstract||y(e.$children,t),t._watcher&&t._watcher.teardown();var n=t._watchers.length;while(n--)t._watchers[n].teardown();t._data.__ob__&&t._data.__ob__.vmCount--,t._isDestroyed=!0,t.__patch__(t._vnode,null),Ln(t,"destroyed"),t.$off(),t.$el&&(t.$el.__vue__=null),t.$vnode&&(t.$vnode.parent=null)}}}function Pn(t,e,i,r,o){var a=r.data.scopedSlots,s=t.$scopedSlots,l=!!(a&&!a.$stable||s!==n&&!s.$stable||a&&t.$scopedSlots.$key!==a.$key),c=!!(o||t.$options._renderChildren||l);if(t.$options._parentVnode=r,t.$vnode=r,t._vnode&&(t._vnode.parent=r),t.$options._renderChildren=o,t.$attrs=r.data.attrs||n,t.$listeners=i||n,e&&t.$options.props){At(!1);for(var u=t._props,h=t.$options._propKeys||[],f=0;f<h.length;f++){var d=h[f],p=t.$options.props;u[d]=Ut(d,p,e,t)}At(!0),t.$options.propsData=e}i=i||n;var g=t.$options._parentListeners;t.$options._parentListeners=i,wn(t,i,g),c&&(t.$slots=Ae(o,r.context),t.$forceUpdate())}function Cn(t){while(t&&(t=t.$parent))if(t._inactive)return!0;return!1}function In(t,e){if(e){if(t._directInactive=!1,Cn(t))return}else if(t._directInactive)return;if(t._inactive||null===t._inactive){t._inactive=!1;for(var n=0;n<t.$children.length;n++)In(t.$children[n]);Ln(t,"activated")}}function Dn(t,e){if((!e||(t._directInactive=!0,!Cn(t)))&&!t._inactive){t._inactive=!0;for(var n=0;n<t.$children.length;n++)Dn(t.$children[n]);Ln(t,"deactivated")}}function Ln(t,e){dt();var n=t.$options[e],i=e+" hook";if(n)for(var r=0,o=n.length;r<o;r++)Jt(n[r],t,null,t,i);t._hasHookEvent&&t.$emit("hook:"+e),pt()}var On=[],En=[],Rn={},Nn=!1,zn=!1,Fn=0;function Bn(){Fn=On.length=En.length=0,Rn={},Nn=zn=!1}var $n=Date.now;if(Y&&!Q){var Hn=window.performance;Hn&&"function"===typeof Hn.now&&$n()>document.createEvent("Event").timeStamp&&($n=function(){return Hn.now()})}function Wn(){var t,e;for($n(),zn=!0,On.sort(function(t,e){return t.id-e.id}),Fn=0;Fn<On.length;Fn++)t=On[Fn],t.before&&t.before(),e=t.id,Rn[e]=null,t.run();var n=En.slice(),i=On.slice();Bn(),Gn(n),Vn(i),ot&&H.devtools&&ot.emit("flush")}function Vn(t){var e=t.length;while(e--){var n=t[e],i=n.vm;i._watcher===n&&i._isMounted&&!i._isDestroyed&&Ln(i,"updated")}}function jn(t){t._inactive=!1,En.push(t)}function Gn(t){for(var e=0;e<t.length;e++)t[e]._inactive=!0,In(t[e],!0)}function Un(t){var e=t.id;if(null==Rn[e]){if(Rn[e]=!0,zn){var n=On.length-1;while(n>Fn&&On[n].id>t.id)n--;On.splice(n+1,0,t)}else On.push(t);Nn||(Nn=!0,ce(Wn))}}var Xn=0,qn=function(t,e,n,i,r){this.vm=t,r&&(t._watcher=this),t._watchers.push(this),i?(this.deep=!!i.deep,this.user=!!i.user,this.lazy=!!i.lazy,this.sync=!!i.sync,this.before=i.before):this.deep=this.user=this.lazy=this.sync=!1,this.cb=n,this.id=++Xn,this.active=!0,this.dirty=this.lazy,this.deps=[],this.newDeps=[],this.depIds=new st,this.newDepIds=new st,this.expression="","function"===typeof e?this.getter=e:(this.getter=U(e),this.getter||(this.getter=O)),this.value=this.lazy?void 0:this.get()};qn.prototype.get=function(){var t;dt(this);var e=this.vm;try{t=this.getter.call(e,e)}catch(nr){if(!this.user)throw nr;Kt(nr,e,'getter for watcher "'+this.expression+'"')}finally{this.deep&&he(t),pt(),this.cleanupDeps()}return t},qn.prototype.addDep=function(t){var e=t.id;this.newDepIds.has(e)||(this.newDepIds.add(e),this.newDeps.push(t),this.depIds.has(e)||t.addSub(this))},qn.prototype.cleanupDeps=function(){var t=this.deps.length;while(t--){var e=this.deps[t];this.newDepIds.has(e.id)||e.removeSub(this)}var n=this.depIds;this.depIds=this.newDepIds,this.newDepIds=n,this.newDepIds.clear(),n=this.deps,this.deps=this.newDeps,this.newDeps=n,this.newDeps.length=0},qn.prototype.update=function(){this.lazy?this.dirty=!0:this.sync?this.run():Un(this)},qn.prototype.run=function(){if(this.active){var t=this.get();if(t!==this.value||l(t)||this.deep){var e=this.value;if(this.value=t,this.user)try{this.cb.call(this.vm,t,e)}catch(nr){Kt(nr,this.vm,'callback for watcher "'+this.expression+'"')}else this.cb.call(this.vm,t,e)}}},qn.prototype.evaluate=function(){this.value=this.get(),this.dirty=!1},qn.prototype.depend=function(){var t=this.deps.length;while(t--)this.deps[t].depend()},qn.prototype.teardown=function(){if(this.active){this.vm._isBeingDestroyed||y(this.vm._watchers,this);var t=this.deps.length;while(t--)this.deps[t].removeSub(this);this.active=!1}};var Yn={enumerable:!0,configurable:!0,get:O,set:O};function Zn(t,e,n){Yn.get=function(){return this[e][n]},Yn.set=function(t){this[e][n]=t},Object.defineProperty(t,n,Yn)}function Kn(t){t._watchers=[];var e=t.$options;e.props&&Jn(t,e.props),e.methods&&ai(t,e.methods),e.data?Qn(t):Ct(t._data={},!0),e.computed&&ni(t,e.computed),e.watch&&e.watch!==nt&&si(t,e.watch)}function Jn(t,e){var n=t.$options.propsData||{},i=t._props={},r=t.$options._propKeys=[],o=!t.$parent;o||At(!1);var a=function(o){r.push(o);var a=Ut(o,e,n,t);It(i,o,a),o in t||Zn(t,"_props",o)};for(var s in e)a(s);At(!0)}function Qn(t){var e=t.$options.data;e=t._data="function"===typeof e?ti(e,t):e||{},u(e)||(e={});var n=Object.keys(e),i=t.$options.props,r=(t.$options.methods,n.length);while(r--){var o=n[r];0,i&&x(i,o)||V(o)||Zn(t,"_data",o)}Ct(e,!0)}function ti(t,e){dt();try{return t.call(e,e)}catch(nr){return Kt(nr,e,"data()"),{}}finally{pt()}}var ei={lazy:!0};function ni(t,e){var n=t._computedWatchers=Object.create(null),i=rt();for(var r in e){var o=e[r],a="function"===typeof o?o:o.get;0,i||(n[r]=new qn(t,a||O,O,ei)),r in t||ii(t,r,o)}}function ii(t,e,n){var i=!rt();"function"===typeof n?(Yn.get=i?ri(e):oi(n),Yn.set=O):(Yn.get=n.get?i&&!1!==n.cache?ri(e):oi(n.get):O,Yn.set=n.set||O),Object.defineProperty(t,e,Yn)}function ri(t){return function(){var e=this._computedWatchers&&this._computedWatchers[t];if(e)return e.dirty&&e.evaluate(),ht.target&&e.depend(),e.value}}function oi(t){return function(){return t.call(this,this)}}function ai(t,e){t.$options.props;for(var n in e)t[n]="function"!==typeof e[n]?O:C(e[n],t)}function si(t,e){for(var n in e){var i=e[n];if(Array.isArray(i))for(var r=0;r<i.length;r++)li(t,n,i[r]);else li(t,n,i)}}function li(t,e,n,i){return u(n)&&(i=n,n=n.handler),"string"===typeof n&&(n=t[n]),t.$watch(e,n,i)}function ci(t){var e={get:function(){return this._data}},n={get:function(){return this._props}};Object.defineProperty(t.prototype,"$data",e),Object.defineProperty(t.prototype,"$props",n),t.prototype.$set=Dt,t.prototype.$delete=Lt,t.prototype.$watch=function(t,e,n){var i=this;if(u(e))return li(i,t,e,n);n=n||{},n.user=!0;var r=new qn(i,t,e,n);if(n.immediate)try{e.call(i,r.value)}catch(o){Kt(o,i,'callback for immediate watcher "'+r.expression+'"')}return function(){r.teardown()}}}var ui=0;function hi(t){t.prototype._init=function(t){var e=this;e._uid=ui++,e._isVue=!0,t&&t._isComponent?fi(e,t):e.$options=jt(di(e.constructor),t||{},e),e._renderProxy=e,e._self=e,Tn(e),yn(e),cn(e),Ln(e,"beforeCreate"),"mp-toutiao"!==e.mpHost&&Se(e),Kn(e),"mp-toutiao"!==e.mpHost&&we(e),"mp-toutiao"!==e.mpHost&&Ln(e,"created"),e.$options.el&&e.$mount(e.$options.el)}}function fi(t,e){var n=t.$options=Object.create(t.constructor.options),i=e._parentVnode;n.parent=e.parent,n._parentVnode=i;var r=i.componentOptions;n.propsData=r.propsData,n._parentListeners=r.listeners,n._renderChildren=r.children,n._componentTag=r.tag,e.render&&(n.render=e.render,n.staticRenderFns=e.staticRenderFns)}function di(t){var e=t.options;if(t.super){var n=di(t.super),i=t.superOptions;if(n!==i){t.superOptions=n;var r=pi(t);r&&D(t.extendOptions,r),e=t.options=jt(n,t.extendOptions),e.name&&(e.components[e.name]=t)}}return e}function pi(t){var e,n=t.options,i=t.sealedOptions;for(var r in n)n[r]!==i[r]&&(e||(e={}),e[r]=n[r]);return e}function gi(t){this._init(t)}function vi(t){t.use=function(t){var e=this._installedPlugins||(this._installedPlugins=[]);if(e.indexOf(t)>-1)return this;var n=I(arguments,1);return n.unshift(this),"function"===typeof t.install?t.install.apply(t,n):"function"===typeof t&&t.apply(null,n),e.push(t),this}}function mi(t){t.mixin=function(t){return this.options=jt(this.options,t),this}}function yi(t){t.cid=0;var e=1;t.extend=function(t){t=t||{};var n=this,i=n.cid,r=t._Ctor||(t._Ctor={});if(r[i])return r[i];var o=t.name||n.options.name;var a=function(t){this._init(t)};return a.prototype=Object.create(n.prototype),a.prototype.constructor=a,a.cid=e++,a.options=jt(n.options,t),a["super"]=n,a.options.props&&_i(a),a.options.computed&&xi(a),a.extend=n.extend,a.mixin=n.mixin,a.use=n.use,B.forEach(function(t){a[t]=n[t]}),o&&(a.options.components[o]=a),a.superOptions=n.options,a.extendOptions=t,a.sealedOptions=D({},a.options),r[i]=a,a}}function _i(t){var e=t.options.props;for(var n in e)Zn(t.prototype,"_props",n)}function xi(t){var e=t.options.computed;for(var n in e)ii(t.prototype,n,e[n])}function bi(t){B.forEach(function(e){t[e]=function(t,n){return n?("component"===e&&u(n)&&(n.name=n.name||t,n=this.options._base.extend(n)),"directive"===e&&"function"===typeof n&&(n={bind:n,update:n}),this.options[e+"s"][t]=n,n):this.options[e+"s"][t]}})}function wi(t){return t&&(t.Ctor.options.name||t.tag)}function Si(t,e){return Array.isArray(t)?t.indexOf(e)>-1:"string"===typeof t?t.split(",").indexOf(e)>-1:!!h(t)&&t.test(e)}function Mi(t,e){var n=t.cache,i=t.keys,r=t._vnode;for(var o in n){var a=n[o];if(a){var s=wi(a.componentOptions);s&&!e(s)&&Ai(n,o,i,r)}}}function Ai(t,e,n,i){var r=t[e];!r||i&&r.tag===i.tag||r.componentInstance.$destroy(),t[e]=null,y(n,e)}hi(gi),ci(gi),Sn(gi),kn(gi),fn(gi);var Ti=[String,RegExp,Array],ki={name:"keep-alive",abstract:!0,props:{include:Ti,exclude:Ti,max:[String,Number]},created:function(){this.cache=Object.create(null),this.keys=[]},destroyed:function(){for(var t in this.cache)Ai(this.cache,t,this.keys)},mounted:function(){var t=this;this.$watch("include",function(e){Mi(t,function(t){return Si(e,t)})}),this.$watch("exclude",function(e){Mi(t,function(t){return!Si(e,t)})})},render:function(){var t=this.$slots.default,e=mn(t),n=e&&e.componentOptions;if(n){var i=wi(n),r=this,o=r.include,a=r.exclude;if(o&&(!i||!Si(o,i))||a&&i&&Si(a,i))return e;var s=this,l=s.cache,c=s.keys,u=null==e.key?n.Ctor.cid+(n.tag?"::"+n.tag:""):e.key;l[u]?(e.componentInstance=l[u].componentInstance,y(c,u),c.push(u)):(l[u]=e,c.push(u),this.max&&c.length>parseInt(this.max)&&Ai(l,c[0],c,this._vnode)),e.data.keepAlive=!0}return e||t&&t[0]}},Pi={KeepAlive:ki};function Ci(t){var e={get:function(){return H}};Object.defineProperty(t,"config",e),t.util={warn:ct,extend:D,mergeOptions:jt,defineReactive:It},t.set=Dt,t.delete=Lt,t.nextTick=ce,t.observable=function(t){return Ct(t),t},t.options=Object.create(null),B.forEach(function(e){t.options[e+"s"]=Object.create(null)}),t.options._base=t,D(t.options.components,Pi),vi(t),mi(t),yi(t),bi(t)}Ci(gi),Object.defineProperty(gi.prototype,"$isServer",{get:rt}),Object.defineProperty(gi.prototype,"$ssrContext",{get:function(){return this.$vnode&&this.$vnode.ssrContext}}),Object.defineProperty(gi,"FunctionalRenderContext",{value:Ge}),gi.version="2.6.10";var Ii="[object Array]",Di="[object Object]";function Li(t,e){var n={};return Oi(t,e),Ei(t,e,"",n),n}function Oi(t,e){if(t!==e){var n=Ni(t),i=Ni(e);if(n==Di&&i==Di){if(Object.keys(t).length>=Object.keys(e).length)for(var r in e){var o=t[r];void 0===o?t[r]=null:Oi(o,e[r])}}else n==Ii&&i==Ii&&t.length>=e.length&&e.forEach(function(e,n){Oi(t[n],e)})}}function Ei(t,e,n,i){if(t!==e){var r=Ni(t),o=Ni(e);if(r==Di)if(o!=Di||Object.keys(t).length<Object.keys(e).length)Ri(i,n,t);else{var a=function(r){var o=t[r],a=e[r],s=Ni(o),l=Ni(a);if(s!=Ii&&s!=Di)o!=e[r]&&Ri(i,(""==n?"":n+".")+r,o);else if(s==Ii)l!=Ii?Ri(i,(""==n?"":n+".")+r,o):o.length<a.length?Ri(i,(""==n?"":n+".")+r,o):o.forEach(function(t,e){Ei(t,a[e],(""==n?"":n+".")+r+"["+e+"]",i)});else if(s==Di)if(l!=Di||Object.keys(o).length<Object.keys(a).length)Ri(i,(""==n?"":n+".")+r,o);else for(var c in o)Ei(o[c],a[c],(""==n?"":n+".")+r+"."+c,i)};for(var s in t)a(s)}else r==Ii?o!=Ii?Ri(i,n,t):t.length<e.length?Ri(i,n,t):t.forEach(function(t,r){Ei(t,e[r],n+"["+r+"]",i)}):Ri(i,n,t)}}function Ri(t,e,n){t[e]=n}function Ni(t){return Object.prototype.toString.call(t)}function zi(t){if(t.__next_tick_callbacks&&t.__next_tick_callbacks.length){if(Object({VUE_APP_PLATFORM:"app-plus",NODE_ENV:"production",BASE_URL:"/"}).VUE_APP_DEBUG){var e=t.$scope;console.log("["+ +new Date+"]["+(e.is||e.route)+"]["+t._uid+"]:flushCallbacks["+t.__next_tick_callbacks.length+"]")}var n=t.__next_tick_callbacks.slice(0);t.__next_tick_callbacks.length=0;for(var i=0;i<n.length;i++)n[i]()}}function Fi(t){return On.find(function(e){return t._watcher===e})}function Bi(t,e){if(!t.__next_tick_pending&&!Fi(t)){if(Object({VUE_APP_PLATFORM:"app-plus",NODE_ENV:"production",BASE_URL:"/"}).VUE_APP_DEBUG){var n=t.$scope;console.log("["+ +new Date+"]["+(n.is||n.route)+"]["+t._uid+"]:nextVueTick")}return ce(e,t)}if(Object({VUE_APP_PLATFORM:"app-plus",NODE_ENV:"production",BASE_URL:"/"}).VUE_APP_DEBUG){var i=t.$scope;console.log("["+ +new Date+"]["+(i.is||i.route)+"]["+t._uid+"]:nextMPTick")}var r;if(t.__next_tick_callbacks||(t.__next_tick_callbacks=[]),t.__next_tick_callbacks.push(function(){if(e)try{e.call(t)}catch(nr){Kt(nr,t,"nextTick")}else r&&r(t)}),!e&&"undefined"!==typeof Promise)return new Promise(function(t){r=t})}function $i(t){var e=Object.create(null),n=[].concat(Object.keys(t._data||{}),Object.keys(t._computedWatchers||{}));return n.reduce(function(e,n){return e[n]=t[n],e},e),Object.assign(e,t.$mp.data||{}),Array.isArray(t.$options.behaviors)&&-1!==t.$options.behaviors.indexOf("uni://form-field")&&(e["name"]=t.name,e["value"]=t.value),JSON.parse(JSON.stringify(e))}var Hi=function(t,e){var n=this;if(null!==e&&("page"===this.mpType||"component"===this.mpType)){var i=this.$scope,r=Object.create(null);try{r=$i(this)}catch(s){console.error(s)}r.__webviewId__=i.data.__webviewId__;var o=Object.create(null);Object.keys(r).forEach(function(t){o[t]=i.data[t]});var a=Li(r,o);Object.keys(a).length?(Object({VUE_APP_PLATFORM:"app-plus",NODE_ENV:"production",BASE_URL:"/"}).VUE_APP_DEBUG&&console.log("["+ +new Date+"]["+(i.is||i.route)+"]["+this._uid+"]差量更新",JSON.stringify(a)),this.__next_tick_pending=!0,i.setData(a,function(){n.__next_tick_pending=!1,zi(n)})):zi(this)}};function Wi(){}function Vi(t,e,n){if(!t.mpType)return t;"app"===t.mpType&&(t.$options.render=Wi),t.$options.render||(t.$options.render=Wi),"mp-toutiao"!==t.mpHost&&Ln(t,"beforeMount");var i=function(){t._update(t._render(),n)};return new qn(t,i,O,{before:function(){t._isMounted&&!t._isDestroyed&&Ln(t,"beforeUpdate")}},!0),n=!1,t}function ji(t,e){return r(t)||r(e)?Gi(t,Ui(e)):""}function Gi(t,e){return t?e?t+" "+e:t:e||""}function Ui(t){return Array.isArray(t)?Xi(t):l(t)?qi(t):"string"===typeof t?t:""}function Xi(t){for(var e,n="",i=0,o=t.length;i<o;i++)r(e=Ui(t[i]))&&""!==e&&(n&&(n+=" "),n+=e);return n}function qi(t){var e="";for(var n in t)t[n]&&(e&&(e+=" "),e+=n);return e}var Yi=b(function(t){var e={},n=/;(?![^(]*\))/g,i=/:(.+)/;return t.split(n).forEach(function(t){if(t){var n=t.split(i);n.length>1&&(e[n[0].trim()]=n[1].trim())}}),e});function Zi(t){return Array.isArray(t)?L(t):"string"===typeof t?Yi(t):t}var Ki=["createSelectorQuery","createIntersectionObserver","selectAllComponents","selectComponent"];function Ji(t,e){var n=e.split("."),i=n[0];return 0===i.indexOf("__$n")&&(i=parseInt(i.replace("__$n",""))),1===n.length?t[i]:Ji(t[i],n.slice(1).join("."))}function Qi(t){t.config.errorHandler=function(t){console.error(t)};var e=t.prototype.$emit;t.prototype.$emit=function(t){return this.$scope&&t&&this.$scope["triggerEvent"](t,{__args__:I(arguments,1)}),e.apply(this,arguments)},t.prototype.$nextTick=function(t){return Bi(this,t)},Ki.forEach(function(e){t.prototype[e]=function(t){if(this.$scope)return this.$scope[e](t)}}),t.prototype.__init_provide=we,t.prototype.__init_injections=Se,t.prototype.__call_hook=function(t,e){var n=this;dt();var i,r=n.$options[t],o=t+" hook";if(r)for(var a=0,s=r.length;a<s;a++)i=Jt(r[a],n,e?[e]:null,n,o);return n._hasHookEvent&&n.$emit("hook:"+t),pt(),i},t.prototype.__set_model=function(t,e,n,i){Array.isArray(i)&&(-1!==i.indexOf("trim")&&(n=n.trim()),-1!==i.indexOf("number")&&(n=this._n(n))),t||(t=this),t[e]=n},t.prototype.__set_sync=function(t,e,n){t||(t=this),t[e]=n},t.prototype.__get_orig=function(t){return u(t)&&t["$orig"]||t},t.prototype.__get_value=function(t,e){return Ji(e||this,t)},t.prototype.__get_class=function(t,e){return ji(e,t)},t.prototype.__get_style=function(t,e){if(!t&&!e)return"";var n=Zi(t),i=e?D(e,n):n;return Object.keys(i).map(function(t){return T(t)+":"+i[t]}).join(";")},t.prototype.__map=function(t,e){var n,i,r,o,a;if(Array.isArray(t)){for(n=new Array(t.length),i=0,r=t.length;i<r;i++)n[i]=e(t[i],i);return n}if(l(t)){for(o=Object.keys(t),n=Object.create(null),i=0,r=o.length;i<r;i++)a=o[i],n[a]=e(t[a],a,i);return n}return[]}}var tr=["onLaunch","onShow","onHide","onUniNViewMessage","onError","onLoad","onReady","onUnload","onPullDownRefresh","onReachBottom","onTabItemTap","onShareAppMessage","onResize","onPageScroll","onNavigationBarButtonTap","onBackPress","onNavigationBarSearchInputChanged","onNavigationBarSearchInputConfirmed","onNavigationBarSearchInputClicked","onPageShow","onPageHide","onPageResize"];function er(t){var e=t.extend;t.extend=function(t){t=t||{};var n=t.methods;return n&&Object.keys(n).forEach(function(e){-1!==tr.indexOf(e)&&(t[e]=n[e],delete n[e])}),e.call(this,t)};var n=t.config.optionMergeStrategies,i=n.created;tr.forEach(function(t){n[t]=i}),t.prototype.__lifecycle_hooks__=tr}gi.prototype.__patch__=Hi,gi.prototype.$mount=function(t,e){return Vi(this,t,e)},er(gi),Qi(gi),e["default"]=gi}.call(this,n("c8ba"))},"69d4":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("134c"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},"6e42":function(t,e,n){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.createApp=pe,e.createPage=Se,e.createComponent=Me,e.default=void 0;var i=r(n("66fd"));function r(t){return t&&t.__esModule?t:{default:t}}function o(t,e){return l(t)||s(t,e)||a()}function a(){throw new TypeError("Invalid attempt to destructure non-iterable instance")}function s(t,e){var n=[],i=!0,r=!1,o=void 0;try{for(var a,s=t[Symbol.iterator]();!(i=(a=s.next()).done);i=!0)if(n.push(a.value),e&&n.length===e)break}catch(l){r=!0,o=l}finally{try{i||null==s["return"]||s["return"]()}finally{if(r)throw o}}return n}function l(t){if(Array.isArray(t))return t}function c(t,e,n){return e in t?Object.defineProperty(t,e,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[e]=n,t}function u(t){return d(t)||f(t)||h()}function h(){throw new TypeError("Invalid attempt to spread non-iterable instance")}function f(t){if(Symbol.iterator in Object(t)||"[object Arguments]"===Object.prototype.toString.call(t))return Array.from(t)}function d(t){if(Array.isArray(t)){for(var e=0,n=new Array(t.length);e<t.length;e++)n[e]=t[e];return n}}var p=Object.prototype.toString,g=Object.prototype.hasOwnProperty;function v(t){return"function"===typeof t}function m(t){return"string"===typeof t}function y(t){return"[object Object]"===p.call(t)}function _(t,e){return g.call(t,e)}function x(){}function b(t){var e=Object.create(null);return function(n){var i=e[n];return i||(e[n]=t(n))}}var w=/-(\w)/g,S=b(function(t){return t.replace(w,function(t,e){return e?e.toUpperCase():""})}),M=["invoke","success","fail","complete","returnValue"],A={},T={};function k(t,e){var n=e?t?t.concat(e):Array.isArray(e)?e:[e]:t;return n?P(n):n}function P(t){for(var e=[],n=0;n<t.length;n++)-1===e.indexOf(t[n])&&e.push(t[n]);return e}function C(t,e){var n=t.indexOf(e);-1!==n&&t.splice(n,1)}function I(t,e){Object.keys(e).forEach(function(n){-1!==M.indexOf(n)&&v(e[n])&&(t[n]=k(t[n],e[n]))})}function D(t,e){t&&e&&Object.keys(e).forEach(function(n){-1!==M.indexOf(n)&&v(e[n])&&C(t[n],e[n])})}function L(t,e){"string"===typeof t&&y(e)?I(T[t]||(T[t]={}),e):y(t)&&I(A,t)}function O(t,e){"string"===typeof t?y(e)?D(T[t],e):delete T[t]:y(t)&&D(A,t)}function E(t){return function(e){return t(e)||e}}function R(t){return!!t&&("object"===typeof t||"function"===typeof t)&&"function"===typeof t.then}function N(t,e){for(var n=!1,i=0;i<t.length;i++){var r=t[i];if(n)n=Promise.then(E(r));else{var o=r(e);if(R(o)&&(n=Promise.resolve(o)),!1===o)return{then:function(){}}}}return n||{then:function(t){return t(e)}}}function z(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};return["success","fail","complete"].forEach(function(n){if(Array.isArray(t[n])){var i=e[n];e[n]=function(e){N(t[n],e).then(function(t){return v(i)&&i(t)||t})}}}),e}function F(t,e){var n=[];Array.isArray(A.returnValue)&&n.push.apply(n,u(A.returnValue));var i=T[t];return i&&Array.isArray(i.returnValue)&&n.push.apply(n,u(i.returnValue)),n.forEach(function(t){e=t(e)||e}),e}function B(t){var e=Object.create(null);Object.keys(A).forEach(function(t){"returnValue"!==t&&(e[t]=A[t].slice())});var n=T[t];return n&&Object.keys(n).forEach(function(t){"returnValue"!==t&&(e[t]=(e[t]||[]).concat(n[t]))}),e}function $(t,e,n){for(var i=arguments.length,r=new Array(i>3?i-3:0),o=3;o<i;o++)r[o-3]=arguments[o];var a=B(t);if(a&&Object.keys(a).length){if(Array.isArray(a.invoke)){var s=N(a.invoke,n);return s.then(function(t){return e.apply(void 0,[z(a,t)].concat(r))})}return e.apply(void 0,[z(a,n)].concat(r))}return e.apply(void 0,[n].concat(r))}var H={returnValue:function(t){return R(t)?t.then(function(t){return t[1]}).catch(function(t){return t[0]}):t}},W=/^\$|interceptors|Interceptor$|getSubNVueById|requireNativePlugin|upx2px|hideKeyboard|canIUse|^create|Sync$|Manager$|base64ToArrayBuffer|arrayBufferToBase64/,V=/^create|Manager$/,j=/^on/;function G(t){return V.test(t)}function U(t){return W.test(t)}function X(t){return j.test(t)}function q(t){return t.then(function(t){return[null,t]}).catch(function(t){return[t]})}function Y(t){return!(G(t)||U(t)||X(t))}function Z(t,e){return Y(t)?function(){for(var n=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},i=arguments.length,r=new Array(i>1?i-1:0),o=1;o<i;o++)r[o-1]=arguments[o];return v(n.success)||v(n.fail)||v(n.complete)?F(t,$.apply(void 0,[t,e,n].concat(r))):F(t,q(new Promise(function(i,o){$.apply(void 0,[t,e,Object.assign({},n,{success:i,fail:o})].concat(r)),Promise.prototype.finally||(Promise.prototype.finally=function(t){var e=this.constructor;return this.then(function(n){return e.resolve(t()).then(function(){return n})},function(n){return e.resolve(t()).then(function(){throw n})})})})))}:e}var K=1e-4,J=750,Q=!1,tt=0,et=0;function nt(){var t=wx.getSystemInfoSync(),e=t.platform,n=t.pixelRatio,i=t.windowWidth;tt=i,et=n,Q="ios"===e}function it(t,e){if(0===tt&&nt(),t=Number(t),0===t)return 0;var n=t/J*(e||tt);return n<0&&(n=-n),n=Math.floor(n+K),0===n?1!==et&&Q?.5:1:t<0?-n:n}var rt={promiseInterceptor:H},ot=Object.freeze({upx2px:it,interceptors:rt,addInterceptor:L,removeInterceptor:O}),at={},st=[],lt=[],ct=["success","fail","cancel","complete"];function ut(t,e,n){return function(i){return e(ft(t,i,n))}}function ht(t,e){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:{},i=arguments.length>3&&void 0!==arguments[3]?arguments[3]:{},r=arguments.length>4&&void 0!==arguments[4]&&arguments[4];if(y(e)){var o=!0===r?e:{};for(var a in v(n)&&(n=n(e,o)||{}),e)if(_(n,a)){var s=n[a];v(s)&&(s=s(e[a],e,o)),s?m(s)?o[s]=e[a]:y(s)&&(o[s.name?s.name:a]=s.value):console.warn("app-plus ".concat(t,"暂不支持").concat(a))}else-1!==ct.indexOf(a)?o[a]=ut(t,e[a],i):r||(o[a]=e[a]);return o}return v(e)&&(e=ut(t,e,i)),e}function ft(t,e,n){var i=arguments.length>3&&void 0!==arguments[3]&&arguments[3];return v(at.returnValue)&&(e=at.returnValue(t,e)),ht(t,e,n,{},i)}function dt(t,e){if(_(at,t)){var n=at[t];return n?function(e,i){var r=n;v(n)&&(r=n(e)),e=ht(t,e,r.args,r.returnValue);var o=[e];"undefined"!==typeof i&&o.push(i);var a=wx[r.name||t].apply(wx,o);return U(t)?ft(t,a,r.returnValue,G(t)):a}:function(){console.error("app-plus 暂不支持".concat(t))}}return e}var pt=Object.create(null),gt=["subscribePush","unsubscribePush","onPush","offPush","share"];function vt(t){return function(e){var n=e.fail,i=e.complete,r={errMsg:"".concat(t,":fail:暂不支持 ").concat(t," 方法")};v(n)&&n(r),v(i)&&i(r)}}gt.forEach(function(t){pt[t]=vt(t)});var mt=function(){return"function"===typeof getUniEmitter?getUniEmitter:function(){return t||(t=new i.default),t};var t}();function yt(t,e,n){return t[e].apply(t,n)}function _t(){return yt(mt(),"$on",Array.prototype.slice.call(arguments))}function xt(){return yt(mt(),"$off",Array.prototype.slice.call(arguments))}function bt(){return yt(mt(),"$once",Array.prototype.slice.call(arguments))}function wt(){return yt(mt(),"$emit",Array.prototype.slice.call(arguments))}var St=Object.freeze({$on:_t,$off:xt,$once:bt,$emit:wt});function Mt(t){t.$processed=!0,t.postMessage=function(e){plus.webview.postMessageToUniNView({type:"UniAppSubNVue",data:e},t.id)};var e=[];if(t.onMessage=function(t){e.push(t)},t.$consumeMessage=function(t){e.forEach(function(e){return e(t)})},t.__uniapp_mask_id){var n=t.__uniapp_mask,i=plus.webview.getWebviewById(t.__uniapp_mask_id);i=i.parent()||i;var r=t.show,o=t.hide,a=t.close,s=function(){i.setStyle({mask:n})},l=function(){i.setStyle({mask:"none"})};t.show=function(){s();for(var e=arguments.length,n=new Array(e),i=0;i<e;i++)n[i]=arguments[i];return r.apply(t,n)},t.hide=function(){l();for(var e=arguments.length,n=new Array(e),i=0;i<e;i++)n[i]=arguments[i];return o.apply(t,n)},t.close=function(){l(),e=[];for(var n=arguments.length,i=new Array(n),r=0;r<n;r++)i[r]=arguments[r];return a.apply(t,i)}}}function At(t){var e=plus.webview.getWebviewById(t);return e&&!e.$processed&&Mt(e),e}function Tt(t){return"undefined"!==typeof weex?weex.requireModule(t):__requireNativePlugin__(t)}var kt=Object.freeze({requireNativePlugin:Tt,getSubNVueById:At}),Pt=Page,Ct=Component,It=/:/g,Dt=b(function(t){return S(t.replace(It,"-"))});function Lt(t){if(wx.canIUse("nextTick")){var e=t.triggerEvent;t.triggerEvent=function(n){for(var i=arguments.length,r=new Array(i>1?i-1:0),o=1;o<i;o++)r[o-1]=arguments[o];return e.apply(t,[Dt(n)].concat(r))}}}function Ot(t,e){var n=e[t];e[t]=n?function(){Lt(this);for(var t=arguments.length,e=new Array(t),i=0;i<t;i++)e[i]=arguments[i];return n.apply(this,e)}:function(){Lt(this)}}Page=function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Ot("onLoad",t),Pt(t)},Component=function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Ot("created",t),Ct(t)};var Et=["onPullDownRefresh","onReachBottom","onShareAppMessage","onPageScroll","onResize","onTabItemTap"];function Rt(t,e){var n=t.$mp[t.mpType];e.forEach(function(e){_(n,e)&&(t[e]=n[e])})}function Nt(t,e){if(!e)return!0;if(i.default.options&&Array.isArray(i.default.options[t]))return!0;if(e=e.default||e,v(e))return!!v(e.extendOptions[t])||!!(e.super&&e.super.options&&Array.isArray(e.super.options[t]));if(v(e[t]))return!0;var n=e.mixins;return Array.isArray(n)?!!n.find(function(e){return Nt(t,e)}):void 0}function zt(t,e,n){e.forEach(function(e){Nt(e,n)&&(t[e]=function(t){return this.$vm&&this.$vm.__call_hook(e,t)})})}function Ft(t,e){var n;return e=e.default||e,v(e)?(n=e,e=n.extendOptions):n=t.extend(e),[n,e]}function Bt(t,e){if(Array.isArray(e)&&e.length){var n=Object.create(null);e.forEach(function(t){n[t]=!0}),t.$scopedSlots=t.$slots=n}}function $t(t,e){t=(t||"").split(",");var n=t.length;1===n?e._$vueId=t[0]:2===n&&(e._$vueId=t[0],e._$vuePid=t[1])}function Ht(t,e){var n=t.data||{},i=t.methods||{};if("function"===typeof n)try{n=n.call(e)}catch(r){Object({VUE_APP_PLATFORM:"app-plus",NODE_ENV:"production",BASE_URL:"/"}).VUE_APP_DEBUG&&console.warn("根据 Vue 的 data 函数初始化小程序 data 失败，请尽量确保 data 函数中不访问 vm 对象，否则可能影响首次数据渲染速度。",n)}else try{n=JSON.parse(JSON.stringify(n))}catch(r){}return y(n)||(n={}),Object.keys(i).forEach(function(t){-1!==e.__lifecycle_hooks__.indexOf(t)||_(n,t)||(n[t]=i[t])}),n}var Wt=[String,Number,Boolean,Object,Array,null];function Vt(t){return function(e,n){this.$vm&&(this.$vm[t]=e)}}function jt(t,e){var n=t["behaviors"],i=t["extends"],r=t["mixins"],o=t["props"];o||(t["props"]=o=[]);var a=[];return Array.isArray(n)&&n.forEach(function(t){a.push(t.replace("uni://","wx".concat("://"))),"uni://form-field"===t&&(Array.isArray(o)?(o.push("name"),o.push("value")):(o["name"]={type:String,default:""},o["value"]={type:[String,Number,Boolean,Array,Object,Date],default:""}))}),y(i)&&i.props&&a.push(e({properties:Ut(i.props,!0)})),Array.isArray(r)&&r.forEach(function(t){y(t)&&t.props&&a.push(e({properties:Ut(t.props,!0)}))}),a}function Gt(t,e,n,i){return Array.isArray(e)&&1===e.length?e[0]:e}function Ut(t){var e=arguments.length>1&&void 0!==arguments[1]&&arguments[1],n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:"",i={};return e||(i.vueId={type:String,value:""},i.vueSlots={type:null,value:[],observer:function(t,e){var n=Object.create(null);t.forEach(function(t){n[t]=!0}),this.setData({$slots:n})}}),Array.isArray(t)?t.forEach(function(t){i[t]={type:null,observer:Vt(t)}}):y(t)&&Object.keys(t).forEach(function(e){var r=t[e];if(y(r)){var o=r["default"];v(o)&&(o=o()),r.type=Gt(e,r.type,o,n),i[e]={type:-1!==Wt.indexOf(r.type)?r.type:null,value:o,observer:Vt(e)}}else{var a=Gt(e,r,null,n);i[e]={type:-1!==Wt.indexOf(a)?a:null,observer:Vt(e)}}}),i}function Xt(t){try{t.mp=JSON.parse(JSON.stringify(t))}catch(e){}return t.stopPropagation=x,t.preventDefault=x,t.target=t.target||{},_(t,"detail")||(t.detail={}),y(t.detail)&&(t.target=Object.assign({},t.target,t.detail)),t}function qt(t,e){var n=t;return e.forEach(function(e){var i=e[0],r=e[2];if(i||"undefined"!==typeof r){var o=e[1],a=e[3],s=i?t.__get_value(i,n):n;Number.isInteger(s)?n=r:o?Array.isArray(s)?n=s.find(function(e){return t.__get_value(o,e)===r}):y(s)?n=Object.keys(s).find(function(e){return t.__get_value(o,s[e])===r}):console.error("v-for 暂不支持循环数据：",s):n=s[r],a&&(n=t.__get_value(a,n))}}),n}function Yt(t,e,n){var i={};return Array.isArray(e)&&e.length&&e.forEach(function(e,r){"string"===typeof e?e?"$event"===e?i["$"+r]=n:0===e.indexOf("$event.")?i["$"+r]=t.__get_value(e.replace("$event.",""),n):i["$"+r]=t.__get_value(e):i["$"+r]=t:i["$"+r]=qt(t,e)}),i}function Zt(t){for(var e={},n=1;n<t.length;n++){var i=t[n];e[i[0]]=i[1]}return e}function Kt(t,e){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:[],i=arguments.length>3&&void 0!==arguments[3]?arguments[3]:[],r=arguments.length>4?arguments[4]:void 0,o=arguments.length>5?arguments[5]:void 0,a=!1;if(r&&(a=e.currentTarget&&e.currentTarget.dataset&&"wx"===e.currentTarget.dataset.comType,!n.length))return a?[e]:e.detail.__args__||e.detail;var s=Yt(t,i,e),l=[];return n.forEach(function(t){"$event"===t?"__set_model"!==o||r?r&&!a?l.push(e.detail.__args__[0]):l.push(e):l.push(e.target.value):Array.isArray(t)&&"o"===t[0]?l.push(Zt(t)):"string"===typeof t&&_(s,t)?l.push(s[t]):l.push(t)}),l}var Jt="~",Qt="^";function te(t,e){return t===e||"regionchange"===e&&("begin"===t||"end"===t)}function ee(t){var e=this;t=Xt(t);var n=(t.currentTarget||t.target).dataset;if(!n)return console.warn("事件信息不存在");var i=n.eventOpts||n["event-opts"];if(!i)return console.warn("事件信息不存在");var r=t.type;i.forEach(function(n){var i=n[0],o=n[1],a=i.charAt(0)===Qt;i=a?i.slice(1):i;var s=i.charAt(0)===Jt;i=s?i.slice(1):i,o&&te(r,i)&&o.forEach(function(n){var i=n[0];if(i){var r=e.$vm;r.$options.generic&&r.$parent&&r.$parent.$parent&&(r=r.$parent.$parent);var o=r[i];if(!v(o))throw new Error(" _vm.".concat(i," is not a function"));if(s){if(o.once)return;o.once=!0}o.apply(r,Kt(e.$vm,t,n[1],n[2],a,i))}})})}var ne=["onShow","onHide","onError","onPageNotFound"];function ie(t,e){var n=e.mocks,r=e.initRefs;i.default.prototype.mpHost="app-plus",i.default.mixin({beforeCreate:function(){this.$options.mpType&&(this.mpType=this.$options.mpType,this.$mp=c({data:{}},this.mpType,this.$options.mpInstance),this.$scope=this.$options.mpInstance,delete this.$options.mpType,delete this.$options.mpInstance,"app"!==this.mpType&&(r(this),Rt(this,n)))}});var o={onLaunch:function(e){this.$vm||(this.$vm=t,this.$vm.$mp={app:this},this.$vm.$scope=this,this.$vm._isMounted=!0,this.$vm.__call_hook("mounted",e),this.$vm.__call_hook("onLaunch",e))}};return o.globalData=t.$options.globalData||{},zt(o,ne),o}var re=["__route__","__wxExparserNodeId__","__wxWebviewId__"];function oe(t,e){var n=t.$children,i=n.find(function(t){return t.$scope._$vueId===e});if(i)return i;for(var r=n.length-1;r>=0;r--)if(i=oe(n[r],e),i)return i}function ae(t){return Behavior(t)}function se(){return!!this.route}function le(t){this.triggerEvent("__l",t)}function ce(t){var e=t.$scope;Object.defineProperty(t,"$refs",{get:function(){var t={},n=e.selectAllComponents(".vue-ref");n.forEach(function(e){var n=e.dataset.ref;t[n]=e.$vm||e});var i=e.selectAllComponents(".vue-ref-in-for");return i.forEach(function(e){var n=e.dataset.ref;t[n]||(t[n]=[]),t[n].push(e.$vm||e)}),t}})}function ue(t){var e,n=t.detail||t.value,i=n.vuePid,r=n.vueOptions;i&&(e=oe(this.$vm,i)),e||(e=this.$vm),r.parent=e}function he(t){return ie(t,{mocks:re,initRefs:ce})}var fe=["onUniNViewMessage"];function de(t){var e=he(t);return zt(e,fe),e}function pe(t){return App(de(t)),t}function ge(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{},n=e.isPage,r=e.initRelation,a=Ft(i.default,t),s=o(a,2),l=s[0],c=s[1],u={options:{multipleSlots:!0,addGlobalClass:!0},data:Ht(c,i.default.prototype),behaviors:jt(c,ae),properties:Ut(c.props,!1,c.__file),lifetimes:{attached:function(){var t=this.properties,e={mpType:n.call(this)?"page":"component",mpInstance:this,propsData:t};$t(t.vueId,this),r.call(this,{vuePid:this._$vuePid,vueOptions:e}),this.$vm=new l(e),Bt(this.$vm,t.vueSlots),this.$vm.$mount()},ready:function(){this.$vm&&(this.$vm._isMounted=!0,this.$vm.__call_hook("mounted"),this.$vm.__call_hook("onReady"))},detached:function(){this.$vm.$destroy()}},pageLifetimes:{show:function(t){this.$vm&&this.$vm.__call_hook("onPageShow",t)},hide:function(){this.$vm&&this.$vm.__call_hook("onPageHide")},resize:function(t){this.$vm&&this.$vm.__call_hook("onPageResize",t)}},methods:{__l:ue,__e:ee}};return n?u:[u,l]}function ve(t){return ge(t,{isPage:se,initRelation:le})}function me(t){var e=ve(t);return e.methods.$getAppWebview=function(){return plus.webview.getWebviewById("".concat(this.__wxWebviewId__))},e}var ye=["onShow","onHide","onUnload"];function _e(t,e){var n=e.isPage,i=e.initRelation,r=me(t,{isPage:n,initRelation:i});return zt(r.methods,ye,t),r.methods.onLoad=function(t){this.$vm.$mp.query=t,this.$vm.__call_hook("onLoad",t)},r}function xe(t){return _e(t,{isPage:se,initRelation:le})}ye.push.apply(ye,Et);var be=["onBackPress","onNavigationBarButtonTap","onNavigationBarSearchInputChanged","onNavigationBarSearchInputConfirmed","onNavigationBarSearchInputClicked"];function we(t){var e=xe(t);return zt(e.methods,be),e}function Se(t){return Component(we(t))}function Me(t){return Component(me(t))}st.forEach(function(t){at[t]=!1}),lt.forEach(function(t){var e=at[t]&&at[t].name?at[t].name:t;wx.canIUse(e)||(at[t]=!1)});var Ae={};Object.keys(ot).forEach(function(t){Ae[t]=ot[t]}),Object.keys(St).forEach(function(t){Ae[t]=St[t]}),Object.keys(kt).forEach(function(t){Ae[t]=Z(t,kt[t])}),Object.keys(wx).forEach(function(t){(_(wx,t)||_(at,t))&&(Ae[t]=Z(t,dt(t,wx[t])))}),"undefined"!==typeof t&&(t.uni=Ae,t.UniEmitter=St),wx.createApp=pe,wx.createPage=Se,wx.createComponent=Me;var Te=Ae,ke=Te;e.default=ke}).call(this,n("c8ba"))},"746c":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("6a82"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},"98c4":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("6f3c"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},"9bba":function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("fa60"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},"9fd5":function(t,e,n){"use strict";(function(t){function n(t){return t<10?"0"+t:t}function i(t){var e=new Date(t),i=e.getFullYear(),r=e.getMonth()+1,o=e.getDate(),a=e.getHours(),s=e.getMinutes(),l=e.getSeconds();return i+"-"+n(r)+"-"+n(o)+" "+n(a)+":"+n(s)+":"+n(l)}Object.defineProperty(e,"__esModule",{value:!0}),e.format=i,e.default=void 0;var r="set_ocean_mch_storageSync",o="set_mch_choosed_sync";function a(){var e=t.getStorageSync("tokenUser"),n=new Array;return n=e.split("@"),n[1].split("!")[0]}function s(){var e=t.getStorageSync(r);return e}function l(){var t=new Date,e="-",n=t.getFullYear(),i=t.getMonth()+1,r=t.getDate();i>=1&&i<=9&&(i="0"+i),r>=0&&r<=9&&(r="0"+r);var o=n+e+i+e+r;return o}function c(e,n){t.showLoading({title:"正在切换"+e+"："+n}),setTimeout(function(){t.hideLoading(),t.showToast({title:"切换成功",icon:"none",mask:!0})},1500)}var u={set_ocean_mch_storageSync:r,set_mch_choosed_sync:o,getShowLoading:c,getSupplierId:a,getNowDate:l,getMchId:s};e.default=u}).call(this,n("6e42")["default"])},a0fa:function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("2a18"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},aaae:function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("881f"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},b0bc:function(t,e,n){},bc5f:function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("b802"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},c196:function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("8582"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},c502:function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("320d"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},c8ba:function(t,e){var n;n=function(){return this}();try{n=n||new Function("return this")()}catch(i){"object"===typeof window&&(n=window)}t.exports=n},c8c7:function(t,e,n){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var n="http://192.168.1.189:8085",i={resquetData:function(e){var i=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"GET",r=arguments.length>2?arguments[2]:void 0,o=arguments.length>3?arguments[3]:void 0,a=t.getStorageSync("tokenUser");t.request({url:n+e,data:r,method:i,contentType:"application/json",header:{Authorization:a},success:function(e){console.log(e," at utils\\ajax.js:15"),1e4===e.data.code?o(e.data):10100===e.data.code?t.navigateTo({url:"/pages/login/login.vue"}):t.showToast({title:e.data.msg,icon:"none",mask:!0})},fail:function(t){console.log(t.data," at utils\\ajax.js:31")}})}};e.default=i}).call(this,n("6e42")["default"])},d616:function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("041a"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},dac2:function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("da57"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},e3f8:function(t,e,n){"use strict";function i(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}function r(t,e){for(var n=0;n<e.length;n++){var i=e[n];i.enumerable=i.enumerable||!1,i.configurable=!0,"value"in i&&(i.writable=!0),Object.defineProperty(t,i.key,i)}}function o(t,e,n){return e&&r(t.prototype,e),n&&r(t,n),t}Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var a=function(){function t(e,n){i(this,t),this.ctx=e,this.canvasId=n,this.chart=null,t.initStyle(e),this.initEvent()}return o(t,[{key:"getContext",value:function(t){return"2d"===t?this.ctx:null}},{key:"setChart",value:function(t){this.chart=t}},{key:"attachEvent",value:function(){}},{key:"detachEvent",value:function(){}},{key:"initEvent",value:function(){var t=this;this.event={};var e=[{wxName:"touchStart",ecName:"mousedown"},{wxName:"touchMove",ecName:"mousemove"},{wxName:"touchEnd",ecName:"mouseup"},{wxName:"touchEnd",ecName:"click"}];e.forEach(function(e){t.event[e.wxName]=function(n){var i=n.mp.touches[0];t.chart._zr.handler.dispatch(e.ecName,{zrX:"tap"===e.wxName?i.clientX:i.x,zrY:"tap"===e.wxName?i.clientY:i.y})}})}}],[{key:"initStyle",value:function(t){var e=arguments,n=["fillStyle","strokeStyle","globalAlpha","textAlign","textBaseAlign","shadow","lineWidth","lineCap","lineJoin","lineDash","miterLimit","fontSize"];n.forEach(function(e){Object.defineProperty(t,e,{set:function(n){("fillStyle"!==e&&"strokeStyle"!==e||"none"!==n&&null!==n)&&t["set".concat(e.charAt(0).toUpperCase()).concat(e.slice(1))](n)}})}),t.createRadialGradient=function(){return t.createCircularGradient(e)}}}]),t}();e.default=a},ea99:function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("9452"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},f0af:function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("48e3"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])},f82e:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var i={lunarInfo:[19416,19168,42352,21717,53856,55632,91476,22176,39632,21970,19168,42422,42192,53840,119381,46400,54944,44450,38320,84343,18800,42160,46261,27216,27968,109396,11104,38256,21234,18800,25958,54432,59984,28309,23248,11104,100067,37600,116951,51536,54432,120998,46416,22176,107956,9680,37584,53938,43344,46423,27808,46416,86869,19872,42416,83315,21168,43432,59728,27296,44710,43856,19296,43748,42352,21088,62051,55632,23383,22176,38608,19925,19152,42192,54484,53840,54616,46400,46752,103846,38320,18864,43380,42160,45690,27216,27968,44870,43872,38256,19189,18800,25776,29859,59984,27480,23232,43872,38613,37600,51552,55636,54432,55888,30034,22176,43959,9680,37584,51893,43344,46240,47780,44368,21977,19360,42416,86390,21168,43312,31060,27296,44368,23378,19296,42726,42208,53856,60005,54576,23200,30371,38608,19195,19152,42192,118966,53840,54560,56645,46496,22224,21938,18864,42359,42160,43600,111189,27936,44448,84835,37744,18936,18800,25776,92326,59984,27424,108228,43744,41696,53987,51552,54615,54432,55888,23893,22176,42704,21972,21200,43448,43344,46240,46758,44368,21920,43940,42416,21168,45683,26928,29495,27296,44368,84821,19296,42352,21732,53600,59752,54560,55968,92838,22224,19168,43476,41680,53584,62034,54560],solarMonth:[31,28,31,30,31,30,31,31,30,31,30,31],Gan:["甲","乙","丙","丁","戊","己","庚","辛","壬","癸"],Zhi:["子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"],Animals:["鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"],solarTerm:["小寒","大寒","立春","雨水","惊蛰","春分","清明","谷雨","立夏","小满","芒种","夏至","小暑","大暑","立秋","处暑","白露","秋分","寒露","霜降","立冬","小雪","大雪","冬至"],sTermInfo:["9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf97c3598082c95f8c965cc920f","97bd0b06bdb0722c965ce1cfcc920f","b027097bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf97c359801ec95f8c965cc920f","97bd0b06bdb0722c965ce1cfcc920f","b027097bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf97c359801ec95f8c965cc920f","97bd0b06bdb0722c965ce1cfcc920f","b027097bd097c36b0b6fc9274c91aa","9778397bd19801ec9210c965cc920e","97b6b97bd19801ec95f8c965cc920f","97bd09801d98082c95f8e1cfcc920f","97bd097bd097c36b0b6fc9210c8dc2","9778397bd197c36c9210c9274c91aa","97b6b97bd19801ec95f8c965cc920e","97bd09801d98082c95f8e1cfcc920f","97bd097bd097c36b0b6fc9210c8dc2","9778397bd097c36c9210c9274c91aa","97b6b97bd19801ec95f8c965cc920e","97bcf97c3598082c95f8e1cfcc920f","97bd097bd097c36b0b6fc9210c8dc2","9778397bd097c36c9210c9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf97c3598082c95f8c965cc920f","97bd097bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf97c3598082c95f8c965cc920f","97bd097bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf97c359801ec95f8c965cc920f","97bd097bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf97c359801ec95f8c965cc920f","97bd097bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf97c359801ec95f8c965cc920f","97bd097bd07f595b0b6fc920fb0722","9778397bd097c36b0b6fc9210c8dc2","9778397bd19801ec9210c9274c920e","97b6b97bd19801ec95f8c965cc920f","97bd07f5307f595b0b0bc920fb0722","7f0e397bd097c36b0b6fc9210c8dc2","9778397bd097c36c9210c9274c920e","97b6b97bd19801ec95f8c965cc920f","97bd07f5307f595b0b0bc920fb0722","7f0e397bd097c36b0b6fc9210c8dc2","9778397bd097c36c9210c9274c91aa","97b6b97bd19801ec9210c965cc920e","97bd07f1487f595b0b0bc920fb0722","7f0e397bd097c36b0b6fc9210c8dc2","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf7f1487f595b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf7f1487f595b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf7f1487f531b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf7f1487f531b0b0bb0b6fb0722","7f0e397bd07f595b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c9274c920e","97bcf7f0e47f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722","9778397bd097c36b0b6fc9210c91aa","97b6b97bd197c36c9210c9274c920e","97bcf7f0e47f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722","9778397bd097c36b0b6fc9210c8dc2","9778397bd097c36c9210c9274c920e","97b6b7f0e47f531b0723b0b6fb0722","7f0e37f5307f595b0b0bc920fb0722","7f0e397bd097c36b0b6fc9210c8dc2","9778397bd097c36b0b70c9274c91aa","97b6b7f0e47f531b0723b0b6fb0721","7f0e37f1487f595b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc9210c8dc2","9778397bd097c36b0b6fc9274c91aa","97b6b7f0e47f531b0723b0b6fb0721","7f0e27f1487f595b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b7f0e47f531b0723b0787b0721","7f0e27f0e47f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722","9778397bd097c36b0b6fc9210c91aa","97b6b7f0e47f149b0723b0787b0721","7f0e27f0e47f531b0723b0b6fb0722","7f0e397bd07f595b0b0bc920fb0722","9778397bd097c36b0b6fc9210c8dc2","977837f0e37f149b0723b0787b0721","7f07e7f0e47f531b0723b0b6fb0722","7f0e37f5307f595b0b0bc920fb0722","7f0e397bd097c35b0b6fc9210c8dc2","977837f0e37f14998082b0787b0721","7f07e7f0e47f531b0723b0b6fb0721","7f0e37f1487f595b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc9210c8dc2","977837f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722","977837f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722","977837f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722","977837f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722","977837f0e37f14998082b0787b06bd","7f07e7f0e47f149b0723b0787b0721","7f0e27f0e47f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722","977837f0e37f14998082b0723b06bd","7f07e7f0e37f149b0723b0787b0721","7f0e27f0e47f531b0723b0b6fb0722","7f0e397bd07f595b0b0bc920fb0722","977837f0e37f14898082b0723b02d5","7ec967f0e37f14998082b0787b0721","7f07e7f0e47f531b0723b0b6fb0722","7f0e37f1487f595b0b0bb0b6fb0722","7f0e37f0e37f14898082b0723b02d5","7ec967f0e37f14998082b0787b0721","7f07e7f0e47f531b0723b0b6fb0722","7f0e37f1487f531b0b0bb0b6fb0722","7f0e37f0e37f14898082b0723b02d5","7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e37f1487f531b0b0bb0b6fb0722","7f0e37f0e37f14898082b072297c35","7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e37f0e37f14898082b072297c35","7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e37f0e366aa89801eb072297c35","7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f149b0723b0787b0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e37f0e366aa89801eb072297c35","7ec967f0e37f14998082b0723b06bd","7f07e7f0e47f149b0723b0787b0721","7f0e27f0e47f531b0723b0b6fb0722","7f0e37f0e366aa89801eb072297c35","7ec967f0e37f14998082b0723b06bd","7f07e7f0e37f14998083b0787b0721","7f0e27f0e47f531b0723b0b6fb0722","7f0e37f0e366aa89801eb072297c35","7ec967f0e37f14898082b0723b02d5","7f07e7f0e37f14998082b0787b0721","7f07e7f0e47f531b0723b0b6fb0722","7f0e36665b66aa89801e9808297c35","665f67f0e37f14898082b0723b02d5","7ec967f0e37f14998082b0787b0721","7f07e7f0e47f531b0723b0b6fb0722","7f0e36665b66a449801e9808297c35","665f67f0e37f14898082b0723b02d5","7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e36665b66a449801e9808297c35","665f67f0e37f14898082b072297c35","7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e26665b66a449801e9808297c35","665f67f0e37f1489801eb072297c35","7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722"],nStr1:["日","一","二","三","四","五","六","七","八","九","十"],nStr2:["初","十","廿","卅"],nStr3:["正","二","三","四","五","六","七","八","九","十","冬","腊"],lYearDays:function(t){var e,n=348;for(e=32768;e>8;e>>=1)n+=this.lunarInfo[t-1900]&e?1:0;return n+this.leapDays(t)},leapMonth:function(t){return 15&this.lunarInfo[t-1900]},leapDays:function(t){return this.leapMonth(t)?65536&this.lunarInfo[t-1900]?30:29:0},monthDays:function(t,e){return e>12||e<1?-1:this.lunarInfo[t-1900]&65536>>e?30:29},solarDays:function(t,e){if(e>12||e<1)return-1;var n=e-1;return 1==n?t%4==0&&t%100!=0||t%400==0?29:28:this.solarMonth[n]},toGanZhiYear:function(t){var e=(t-3)%10,n=(t-3)%12;return 0==e&&(e=10),0==n&&(n=12),this.Gan[e-1]+this.Zhi[n-1]},toAstro:function(t,e){var n="魔羯水瓶双鱼白羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯",i=[20,19,21,21,21,22,23,23,23,23,22,22];return n.substr(2*t-(e<i[t-1]?2:0),2)+"座"},toGanZhi:function(t){return this.Gan[t%10]+this.Zhi[t%12]},getTerm:function(t,e){if(t<1900||t>2100)return-1;if(e<1||e>24)return-1;var n=this.sTermInfo[t-1900],i=[parseInt("0x"+n.substr(0,5)).toString(),parseInt("0x"+n.substr(5,5)).toString(),parseInt("0x"+n.substr(10,5)).toString(),parseInt("0x"+n.substr(15,5)).toString(),parseInt("0x"+n.substr(20,5)).toString(),parseInt("0x"+n.substr(25,5)).toString()],r=[i[0].substr(0,1),i[0].substr(1,2),i[0].substr(3,1),i[0].substr(4,2),i[1].substr(0,1),i[1].substr(1,2),i[1].substr(3,1),i[1].substr(4,2),i[2].substr(0,1),i[2].substr(1,2),i[2].substr(3,1),i[2].substr(4,2),i[3].substr(0,1),i[3].substr(1,2),i[3].substr(3,1),i[3].substr(4,2),i[4].substr(0,1),i[4].substr(1,2),i[4].substr(3,1),i[4].substr(4,2),i[5].substr(0,1),i[5].substr(1,2),i[5].substr(3,1),i[5].substr(4,2)];return parseInt(r[e-1])},toChinaMonth:function(t){if(t>12||t<1)return-1;var e=this.nStr3[t-1];return e+="月",e},toChinaDay:function(t){var e;switch(t){case 10:e="初十";break;case 20:e="二十";break;case 30:e="三十";break;default:e=this.nStr2[Math.floor(t/10)],e+=this.nStr1[t%10]}return e},getAnimal:function(t){return this.Animals[(t-4)%12]},solar2lunar:function(t,e,n){if(t<1900||t>2100)return-1;if(1900==t&&1==e&&n<31)return-1;if(t)i=new Date(t,parseInt(e)-1,n);else var i=new Date;var r,o=0,a=0,s=(t=i.getFullYear(),e=i.getMonth()+1,n=i.getDate(),(Date.UTC(i.getFullYear(),i.getMonth(),i.getDate())-Date.UTC(1900,0,31))/864e5);for(r=1900;r<2101&&s>0;r++)a=this.lYearDays(r),s-=a;s<0&&(s+=a,r--);var l=new Date,c=!1;l.getFullYear()==t&&l.getMonth()+1==e&&l.getDate()==n&&(c=!0);var u=i.getDay(),h=this.nStr1[u];0==u&&(u=7);var f=r,d=(o=this.leapMonth(r),!1);for(r=1;r<13&&s>0;r++)o>0&&r==o+1&&0==d?(--r,d=!0,a=this.leapDays(f)):a=this.monthDays(f,r),1==d&&r==o+1&&(d=!1),s-=a;0==s&&o>0&&r==o+1&&(d?d=!1:(d=!0,--r)),s<0&&(s+=a,--r);var p=r,g=s+1,v=e-1,m=this.toGanZhiYear(f),y=this.getTerm(t,2*e-1),_=this.getTerm(t,2*e),x=this.toGanZhi(12*(t-1900)+e+11);n>=y&&(x=this.toGanZhi(12*(t-1900)+e+12));var b=!1,w=null;y==n&&(b=!0,w=this.solarTerm[2*e-2]),_==n&&(b=!0,w=this.solarTerm[2*e-1]);var S=Date.UTC(t,v,1,0,0,0,0)/864e5+25567+10,M=this.toGanZhi(S+n-1),A=this.toAstro(e,n);return{lYear:f,lMonth:p,lDay:g,Animal:this.getAnimal(f),IMonthCn:(d?"闰":"")+this.toChinaMonth(p),IDayCn:this.toChinaDay(g),cYear:t,cMonth:e,cDay:n,gzYear:m,gzMonth:x,gzDay:M,isToday:c,isLeap:d,nWeek:u,ncWeek:"星期"+h,isTerm:b,Term:w,astro:A}},lunar2solar:function(t,e,n,i){i=!!i;var r=this.leapMonth(t);this.leapDays(t);if(i&&r!=e)return-1;if(2100==t&&12==e&&n>1||1900==t&&1==e&&n<31)return-1;var o=this.monthDays(t,e),a=o;if(i&&(a=this.leapDays(t,e)),t<1900||t>2100||n>a)return-1;for(var s=0,l=1900;l<t;l++)s+=this.lYearDays(l);var c=0,u=!1;for(l=1;l<e;l++)c=this.leapMonth(t),u||c<=l&&c>0&&(s+=this.leapDays(t),u=!0),s+=this.monthDays(t,l);i&&(s+=o);var h=Date.UTC(1900,1,30,0,0,0),f=new Date(864e5*(s+n-31)+h),d=f.getUTCFullYear(),p=f.getUTCMonth()+1,g=f.getUTCDate();return this.solar2lunar(d,p,g)}},r=i;e.default=r},ffb3:function(t,e,n){"use strict";(function(t){n("b0bc");i(n("66fd"));var e=i(n("178a"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("6e42")["createPage"])}}]);
});

define('app.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){

require('./common/runtime.js')
require('./common/vendor.js')
require('./common/main.js')
});
require('app.js');

__wxRoute = 'components/forget-header';__wxRouteBegin = true;__wxAppCurrentFile__ = 'components/forget-header.js';

define('components/forget-header.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
"use strict";

(global["webpackJsonp"] = global["webpackJsonp"] || []).push([["components/forget-header"], {
  "01f6": function f6(t, n, e) {
    "use strict";

    var u = function u() {
      var t = this,
          n = t.$createElement;
      t._self._c;
    },
        a = [];

    e.d(n, "a", function () {
      return u;
    }), e.d(n, "b", function () {
      return a;
    });
  },
  "32fe": function fe(t, n, e) {
    "use strict";

    e.r(n);
    var u = e("01f6"),
        a = e("7a7b");

    for (var r in a) {
      "default" !== r && function (t) {
        e.d(n, t, function () {
          return a[t];
        });
      }(r);
    }

    e("7f91");
    var f = e("2877"),
        o = Object(f["a"])(a["default"], u["a"], u["b"], !1, null, null, null);
    n["default"] = o.exports;
  },
  "405f": function f(t, n, e) {},
  "7a7b": function a7b(t, n, e) {
    "use strict";

    e.r(n);
    var u = e("c839"),
        a = e.n(u);

    for (var r in u) {
      "default" !== r && function (t) {
        e.d(n, t, function () {
          return u[t];
        });
      }(r);
    }

    n["default"] = a.a;
  },
  "7f91": function f91(t, n, e) {
    "use strict";

    var u = e("405f"),
        a = e.n(u);
    a.a;
  },
  c839: function c839(t, n, e) {
    "use strict";

    Object.defineProperty(n, "__esModule", {
      value: !0
    }), n.default = void 0;
    var u = {
      props: {
        curStep: {
          type: Number,
          default: 1
        }
      },
      data: function data() {
        return {
          stepList: [{
            name: "输入账号"
          }, {
            name: "验证手机号"
          }, {
            name: "重设密码"
          }]
        };
      },
      onLoad: function onLoad() {},
      methods: {}
    };
    n.default = u;
  }
}]);
;
(global["webpackJsonp"] = global["webpackJsonp"] || []).push(['components/forget-header-create-component', {
  'components/forget-header-create-component': function componentsForgetHeaderCreateComponent(module, exports, __webpack_require__) {
    __webpack_require__('6e42')['createComponent'](__webpack_require__("32fe"));
  }
}, [['components/forget-header-create-component']]]);
});
require('components/forget-header.js');
__wxRoute = 'components/mpvue-echarts/src/echarts';__wxRouteBegin = true;__wxAppCurrentFile__ = 'components/mpvue-echarts/src/echarts.js';

define('components/mpvue-echarts/src/echarts.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
"use strict";

(global["webpackJsonp"] = global["webpackJsonp"] || []).push([["components/mpvue-echarts/src/echarts"], {
  "28ce": function ce(t, e, n) {},
  "2c37": function c37(t, e, n) {
    "use strict";

    n.r(e);
    var a = n("c086"),
        c = n("f166");

    for (var r in c) {
      "default" !== r && function (t) {
        n.d(e, t, function () {
          return c[t];
        });
      }(r);
    }

    n("93b0");
    var i = n("2877"),
        o = Object(i["a"])(c["default"], a["a"], a["b"], !1, null, "c88dafcc", null);
    e["default"] = o.exports;
  },
  "93b0": function b0(t, e, n) {
    "use strict";

    var a = n("28ce"),
        c = n.n(a);
    c.a;
  },
  aceb: function aceb(t, e, n) {
    "use strict";

    Object.defineProperty(e, "__esModule", {
      value: !0
    }), e.default = void 0;
    var a = c(n("e3f8"));

    function c(t) {
      return t && t.__esModule ? t : {
        default: t
      };
    }

    function r(t) {
      for (var e = 1; e < arguments.length; e++) {
        var n = null != arguments[e] ? arguments[e] : {},
            a = Object.keys(n);
        "function" === typeof Object.getOwnPropertySymbols && (a = a.concat(Object.getOwnPropertySymbols(n).filter(function (t) {
          return Object.getOwnPropertyDescriptor(n, t).enumerable;
        }))), a.forEach(function (e) {
          i(t, e, n[e]);
        });
      }

      return t;
    }

    function i(t, e, n) {
      return e in t ? Object.defineProperty(t, e, {
        value: n,
        enumerable: !0,
        configurable: !0,
        writable: !0
      }) : t[e] = n, t;
    }

    var o = {
      props: {
        canvasId: {
          type: String,
          default: "ec-canvas"
        },
        lazyLoad: {
          type: Boolean,
          default: !1
        },
        disableTouch: {
          type: Boolean,
          default: !1
        },
        throttleTouch: {
          type: Boolean,
          default: !1
        }
      },
      onReady: function onReady() {
        this.lazyLoad || this.init();
      },
      methods: {
        setChart: function setChart(t) {
          this.chart = t;
        },
        init: function init() {
          var t = this,
              e = this.canvasId;
          this.ctx = wx.createCanvasContext(e, this), this.canvas = new a.default(this.ctx, e);
          var n = wx.createSelectorQuery().in(this);
          n.select("#".concat(e)).boundingClientRect(function (e) {
            e ? t.$emit("onInit", {
              width: e.width,
              height: e.height
            }) : setTimeout(function () {
              return t.init();
            }, 50);
          }).exec();
        },
        canvasToTempFilePath: function canvasToTempFilePath(t) {
          var e = this.canvasId;
          this.ctx.draw(!0, function () {
            wx.canvasToTempFilePath(r({
              canvasId: e
            }, t));
          });
        },
        touchStart: function touchStart(t) {
          var e = this.disableTouch,
              n = this.chart;

          if (!e && n && t.mp.touches.length) {
            var a = t.mp.touches[0];
            n._zr.handler.dispatch("mousedown", {
              zrX: a.x,
              zrY: a.y
            }), n._zr.handler.dispatch("mousemove", {
              zrX: a.x,
              zrY: a.y
            });
          }
        },
        touchMove: function touchMove(t) {
          var e = this.disableTouch,
              n = this.throttleTouch,
              a = this.chart,
              c = this.lastMoveTime;

          if (!e && a && t.mp.touches.length) {
            if (n) {
              var r = Date.now();
              if (r - c < 240) return;
              this.lastMoveTime = r;
            }

            var i = t.mp.touches[0];

            a._zr.handler.dispatch("mousemove", {
              zrX: i.x,
              zrY: i.y
            });
          }
        },
        touchEnd: function touchEnd(t) {
          var e = this.disableTouch,
              n = this.chart;

          if (!e && n) {
            var a = t.mp.changedTouches ? t.mp.changedTouches[0] : {};
            n._zr.handler.dispatch("mouseup", {
              zrX: a.x,
              zrY: a.y
            }), n._zr.handler.dispatch("click", {
              zrX: a.x,
              zrY: a.y
            });
          }
        }
      }
    };
    e.default = o;
  },
  c086: function c086(t, e, n) {
    "use strict";

    var a = function a() {
      var t = this,
          e = t.$createElement;
      t._self._c;
    },
        c = [];

    n.d(e, "a", function () {
      return a;
    }), n.d(e, "b", function () {
      return c;
    });
  },
  f166: function f166(t, e, n) {
    "use strict";

    n.r(e);
    var a = n("aceb"),
        c = n.n(a);

    for (var r in a) {
      "default" !== r && function (t) {
        n.d(e, t, function () {
          return a[t];
        });
      }(r);
    }

    e["default"] = c.a;
  }
}]);
;
(global["webpackJsonp"] = global["webpackJsonp"] || []).push(['components/mpvue-echarts/src/echarts-create-component', {
  'components/mpvue-echarts/src/echarts-create-component': function componentsMpvueEchartsSrcEchartsCreateComponent(module, exports, __webpack_require__) {
    __webpack_require__('6e42')['createComponent'](__webpack_require__("2c37"));
  }
}, [['components/mpvue-echarts/src/echarts-create-component']]]);
});
require('components/mpvue-echarts/src/echarts.js');
__wxRoute = 'components/mutiChoose';__wxRouteBegin = true;__wxAppCurrentFile__ = 'components/mutiChoose.js';

define('components/mutiChoose.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
"use strict";

(global["webpackJsonp"] = global["webpackJsonp"] || []).push([["components/mutiChoose"], {
  "43d7": function d7(t, e, o) {
    "use strict";

    Object.defineProperty(e, "__esModule", {
      value: !0
    }), e.default = void 0;
    var s = {
      props: {
        isShow2: {
          type: Boolean,
          default: !1
        },
        chooseList: {
          type: Array,
          default: [{
            title: "宁波海洋世界-成人票",
            selected: !1
          }, {
            title: "宁波海洋世界-儿童票",
            selected: !1
          }, {
            title: "宁波海洋世界-家庭票",
            selected: !1
          }]
        }
      },
      data: function data() {
        return {
          result: [],
          selectedObj: ""
        };
      },
      onLoad: function onLoad() {},
      methods: {
        choose: function choose(t, e) {
          if (1 == t.selected) t.selected = !1;else for (var o in this.chooseList) {
            o == e ? (this.chooseList[o].selected = !0, this.selectedObj = t) : this.chooseList[o].selected = !1;
          }
          console.log(this.result, " at components\\mutiChoose.vue:78");
        },
        closeMak: function closeMak() {
          this.$emit("isShow2", !1);
        },
        submitChoose: function submitChoose() {
          this.$emit("isShow2", !1), this.$emit("submit", this.selectedObj);
        }
      }
    };
    e.default = s;
  },
  "63f2": function f2(t, e, o) {},
  a51c: function a51c(t, e, o) {
    "use strict";

    o.r(e);
    var s = o("f242"),
        n = o("f007");

    for (var i in n) {
      "default" !== i && function (t) {
        o.d(e, t, function () {
          return n[t];
        });
      }(i);
    }

    o("c705");
    var c = o("2877"),
        u = Object(c["a"])(n["default"], s["a"], s["b"], !1, null, null, null);
    e["default"] = u.exports;
  },
  c705: function c705(t, e, o) {
    "use strict";

    var s = o("63f2"),
        n = o.n(s);
    n.a;
  },
  f007: function f007(t, e, o) {
    "use strict";

    o.r(e);
    var s = o("43d7"),
        n = o.n(s);

    for (var i in s) {
      "default" !== i && function (t) {
        o.d(e, t, function () {
          return s[t];
        });
      }(i);
    }

    e["default"] = n.a;
  },
  f242: function f242(t, e, o) {
    "use strict";

    var s = function s() {
      var t = this,
          e = t.$createElement;
      t._self._c;
      t._isMounted || (t.e0 = function (e) {
        e.stopPropagation(), t.isShow2 = !0;
      });
    },
        n = [];

    o.d(e, "a", function () {
      return s;
    }), o.d(e, "b", function () {
      return n;
    });
  }
}]);
;
(global["webpackJsonp"] = global["webpackJsonp"] || []).push(['components/mutiChoose-create-component', {
  'components/mutiChoose-create-component': function componentsMutiChooseCreateComponent(module, exports, __webpack_require__) {
    __webpack_require__('6e42')['createComponent'](__webpack_require__("a51c"));
  }
}, [['components/mutiChoose-create-component']]]);
});
require('components/mutiChoose.js');
__wxRoute = 'components/range-dtpicker/range-dtpicker';__wxRouteBegin = true;__wxAppCurrentFile__ = 'components/range-dtpicker/range-dtpicker.js';

define('components/range-dtpicker/range-dtpicker.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
"use strict";

(global["webpackJsonp"] = global["webpackJsonp"] || []).push([["components/range-dtpicker/range-dtpicker"], {
  "277a": function a(t, e, r) {
    "use strict";

    r.r(e);
    var n = r("b1a9"),
        i = r.n(n);

    for (var a in n) {
      "default" !== a && function (t) {
        r.d(e, t, function () {
          return n[t];
        });
      }(a);
    }

    e["default"] = i.a;
  },
  8096: function _(t, e, r) {
    "use strict";

    r.r(e);
    var n = r("b4c2"),
        i = r("277a");

    for (var a in i) {
      "default" !== a && function (t) {
        r.d(e, t, function () {
          return i[t];
        });
      }(a);
    }

    r("bea7");
    var s = r("2877"),
        h = Object(s["a"])(i["default"], n["a"], n["b"], !1, null, null, null);
    e["default"] = h.exports;
  },
  b1a9: function b1a9(t, e, r) {
    "use strict";

    (function (t) {
      Object.defineProperty(e, "__esModule", {
        value: !0
      }), e.default = void 0;
      var r = {
        name: "range-dtpicker",
        props: {
          start: {
            type: String,
            default: "1900-01-01"
          },
          end: {
            type: String,
            default: "2500-12-31"
          },
          value: {
            type: Array,
            default: function _default() {
              return [0, 0, 0];
            }
          },
          show: {
            type: Boolean,
            default: !1
          },
          themeColor: {
            type: String,
            default: "#00CE9F"
          }
        },
        created: function created() {
          this.start && this.end ? this.start > this.end && console.error("结束时间必须大等于开始时间", " at components\\range-dtpicker\\range-dtpicker.vue:76") : console.error("时间不能为空", " at components\\range-dtpicker\\range-dtpicker.vue:74"), this.value[0] ? (this.startDate = this.value[0], this.value[1] ? (this.endDate = this.value[1], this.dateType = "endDate", this.pickerValue = this.getIndex(this.value[1]), console.log(this.pickerValue, " at components\\range-dtpicker\\range-dtpicker.vue:84")) : (this.dateType = "startDate", console.log(this.value[0], " at components\\range-dtpicker\\range-dtpicker.vue:87"), this.pickerValue = this.getIndex(this.value[0]), console.log(this.pickerValue, " at components\\range-dtpicker\\range-dtpicker.vue:89"))) : this.startDate = this.start;
        },
        data: function data() {
          return {
            showPicker: !1,
            pickerValue: [0, 0, 0],
            dateType: "startDate",
            startDate: "",
            endDate: "",
            year: "",
            month: "",
            dateArr: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31]
          };
        },
        watch: {
          show: function show(t) {
            this.showPicker = t;
          },
          year: function year(t) {
            var e = [];
            if ("02" == this.month) {
              if (t % 4 == 0 && t % 100 != 0 || t % 400 == 0) for (var r = 1; r <= 29; r++) {
                var n = r;
                n < 10 && (n = "0" + n), e.push(n);
              } else for (r = 1; r <= 28; r++) {
                n = r;
                n < 10 && (n = "0" + n), e.push(n);
              }
            } else if ("01" == this.month || "03" == this.month || "05" == this.month || "07" == this.month || "08" == this.month || "10" == this.month || "12" == this.month) for (r = 1; r <= 31; r++) {
              n = r;
              n < 10 && (n = "0" + n), e.push(n);
            } else for (r = 1; r <= 30; r++) {
              n = r;
              n < 10 && (n = "0" + n), e.push(n);
            }
            this.dateArr = e;
          },
          month: function month(t) {
            var e = [];
            if ("02" == t) {
              if (this.year % 4 == 0 && this.year % 100 != 0 || this.year % 400 == 0) for (var r = 1; r <= 29; r++) {
                var n = r;
                n < 10 && (n = "0" + n), e.push(n);
              } else for (r = 1; r <= 28; r++) {
                n = r;
                n < 10 && (n = "0" + n), e.push(n);
              }
            } else if ("01" == this.month || "03" == this.month || "05" == this.month || "07" == this.month || "08" == this.month || "10" == this.month || "12" == this.month) for (r = 1; r <= 31; r++) {
              n = r;
              n < 10 && (n = "0" + n), e.push(n);
            } else for (r = 1; r <= 30; r++) {
              n = r;
              n < 10 && (n = "0" + n), e.push(n);
            }
            this.dateArr = e;
          }
        },
        computed: {
          yearArr: function yearArr() {
            for (var t = [], e = parseInt(this.start.slice(0, 4)), r = parseInt(this.end.slice(0, 4)), n = 0; n < r - e; n++) {
              t.push(e + n);
            }

            return t;
          },
          monthArr: function monthArr() {
            for (var t = [], e = 1; e <= 12; e++) {
              var r = e;
              r < 10 && (r = "0" + r), t.push(r);
            }

            return t;
          }
        },
        methods: {
          returnHandle: function returnHandle() {},
          maskClick: function maskClick() {
            this.$emit("showchange", !1);
          },
          pickerConfirm: function pickerConfirm() {
            this.endDate < this.startDate ? t.showToast({
              title: "结束时间不得小于开始时间",
              icon: "none",
              mask: !0
            }) : (this.$emit("change", [this.startDate, this.endDate]), this.$emit("showchange", !1));
          },
          pickerCancel: function pickerCancel() {
            this.$emit("cancel"), this.$emit("showchange", !1);
          },
          changeDateType: function changeDateType(t) {
            this.dateType = t;
          },
          pickerChangeMul: function pickerChangeMul(t) {
            var e = t.detail.value,
                r = "";
            r = this.yearArr[e[0]] + "-" + this.monthArr[e[1]] + "-" + this.dateArr[e[2]], this.year = this.yearArr[e[0]], this.month = this.monthArr[e[1]], "endDate" == this.dateType ? this.endDate = r : this.startDate = r;
          },
          getIndex: function getIndex(t) {
            var e = t.slice(0, 4),
                r = t.slice(5, 7),
                n = t.slice(8, 10),
                i = 0,
                a = 0,
                s = 0;

            for (var h in this.yearArr) {
              if (e == this.yearArr[h]) {
                i = h;
                break;
              }
            }

            for (var h in this.monthArr) {
              if (r == this.monthArr[h]) {
                a = h;
                break;
              }
            }

            for (var h in this.dateArr) {
              if (n == this.dateArr[h]) {
                s = h;
                break;
              }
            }

            return [i, a, s];
          }
        }
      };
      e.default = r;
    }).call(this, r("6e42")["default"]);
  },
  b246: function b246(t, e, r) {},
  b4c2: function b4c2(t, e, r) {
    "use strict";

    var n = function n() {
      var t = this,
          e = t.$createElement;
      t._self._c;
    },
        i = [];

    r.d(e, "a", function () {
      return n;
    }), r.d(e, "b", function () {
      return i;
    });
  },
  bea7: function bea7(t, e, r) {
    "use strict";

    var n = r("b246"),
        i = r.n(n);
    i.a;
  }
}]);
;
(global["webpackJsonp"] = global["webpackJsonp"] || []).push(['components/range-dtpicker/range-dtpicker-create-component', {
  'components/range-dtpicker/range-dtpicker-create-component': function componentsRangeDtpickerRangeDtpickerCreateComponent(module, exports, __webpack_require__) {
    __webpack_require__('6e42')['createComponent'](__webpack_require__("8096"));
  }
}, [['components/range-dtpicker/range-dtpicker-create-component']]]);
});
require('components/range-dtpicker/range-dtpicker.js');
__wxRoute = 'components/tabNav';__wxRouteBegin = true;__wxAppCurrentFile__ = 'components/tabNav.js';

define('components/tabNav.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
"use strict";

(global["webpackJsonp"] = global["webpackJsonp"] || []).push([["components/tabNav"], {
  4655: function _(t, n, e) {
    "use strict";

    e.r(n);
    var u = e("5ae1"),
        a = e.n(u);

    for (var r in u) {
      "default" !== r && function (t) {
        e.d(n, t, function () {
          return u[t];
        });
      }(r);
    }

    n["default"] = a.a;
  },
  "5ae1": function ae1(t, n, e) {
    "use strict";

    Object.defineProperty(n, "__esModule", {
      value: !0
    }), n.default = void 0;
    var u = {
      data: function data() {
        return {
          navList: ["最新", "待回复", "带图", "星际筛选"],
          curIndex: 0
        };
      },
      methods: {
        choose: function choose(t) {
          this.curIndex = t, this.$emit("choose", t);
        }
      }
    };
    n.default = u;
  },
  9698: function _(t, n, e) {
    "use strict";

    var u = e("977b"),
        a = e.n(u);
    a.a;
  },
  "977b": function b(t, n, e) {},
  be9d: function be9d(t, n, e) {
    "use strict";

    e.r(n);
    var u = e("e55d"),
        a = e("4655");

    for (var r in a) {
      "default" !== r && function (t) {
        e.d(n, t, function () {
          return a[t];
        });
      }(r);
    }

    e("9698");
    var o = e("2877"),
        c = Object(o["a"])(a["default"], u["a"], u["b"], !1, null, null, null);
    n["default"] = c.exports;
  },
  e55d: function e55d(t, n, e) {
    "use strict";

    var u = function u() {
      var t = this,
          n = t.$createElement;
      t._self._c;
    },
        a = [];

    e.d(n, "a", function () {
      return u;
    }), e.d(n, "b", function () {
      return a;
    });
  }
}]);
;
(global["webpackJsonp"] = global["webpackJsonp"] || []).push(['components/tabNav-create-component', {
  'components/tabNav-create-component': function componentsTabNavCreateComponent(module, exports, __webpack_require__) {
    __webpack_require__('6e42')['createComponent'](__webpack_require__("be9d"));
  }
}, [['components/tabNav-create-component']]]);
});
require('components/tabNav.js');
__wxRoute = 'components/uni-calendar/uni-calendar-item';__wxRouteBegin = true;__wxAppCurrentFile__ = 'components/uni-calendar/uni-calendar-item.js';

define('components/uni-calendar/uni-calendar-item.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
"use strict";

(global["webpackJsonp"] = global["webpackJsonp"] || []).push([["components/uni-calendar/uni-calendar-item"], {
  "2d4e": function d4e(n, e, t) {
    "use strict";

    var a = function a() {
      var n = this,
          e = n.$createElement;
      n._self._c;
    },
        u = [];

    t.d(e, "a", function () {
      return a;
    }), t.d(e, "b", function () {
      return u;
    });
  },
  "84e8": function e8(n, e, t) {
    "use strict";

    var a = t("a006"),
        u = t.n(a);
    u.a;
  },
  "93b3": function b3(n, e, t) {
    "use strict";

    t.r(e);
    var a = t("2d4e"),
        u = t("baf8");

    for (var r in u) {
      "default" !== r && function (n) {
        t.d(e, n, function () {
          return u[n];
        });
      }(r);
    }

    t("84e8");
    var c = t("2877"),
        i = Object(c["a"])(u["default"], a["a"], a["b"], !1, null, null, null);
    e["default"] = i.exports;
  },
  a006: function a006(n, e, t) {},
  baf8: function baf8(n, e, t) {
    "use strict";

    t.r(e);
    var a = t("dbc7"),
        u = t.n(a);

    for (var r in a) {
      "default" !== r && function (n) {
        t.d(e, n, function () {
          return a[n];
        });
      }(r);
    }

    e["default"] = u.a;
  },
  dbc7: function dbc7(n, e, t) {
    "use strict";

    Object.defineProperty(e, "__esModule", {
      value: !0
    }), e.default = void 0;
    var a = {
      name: "UniCalendarItem",
      props: {
        canlender: {
          type: null,
          default: function _default() {
            return {};
          }
        },
        lunar: {
          type: Boolean,
          default: !1
        }
      },
      data: function data() {
        return {};
      },
      created: function created() {},
      methods: {
        selectDays: function selectDays(n, e, t, a, u) {
          this.$emit("selectDays", {
            week: n,
            index: e,
            ischeck: t,
            isDay: a,
            lunar: u
          });
        }
      }
    };
    e.default = a;
  }
}]);
;
(global["webpackJsonp"] = global["webpackJsonp"] || []).push(['components/uni-calendar/uni-calendar-item-create-component', {
  'components/uni-calendar/uni-calendar-item-create-component': function componentsUniCalendarUniCalendarItemCreateComponent(module, exports, __webpack_require__) {
    __webpack_require__('6e42')['createComponent'](__webpack_require__("93b3"));
  }
}, [['components/uni-calendar/uni-calendar-item-create-component']]]);
});
require('components/uni-calendar/uni-calendar-item.js');
__wxRoute = 'components/uni-calendar/uni-calendar';__wxRouteBegin = true;__wxAppCurrentFile__ = 'components/uni-calendar/uni-calendar.js';

define('components/uni-calendar/uni-calendar.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
"use strict";

(global["webpackJsonp"] = global["webpackJsonp"] || []).push([["components/uni-calendar/uni-calendar"], {
  "562e": function e(t, _e, a) {
    "use strict";

    a.r(_e);
    var n = a("fb19"),
        i = a("aaba");

    for (var r in i) {
      "default" !== r && function (t) {
        a.d(_e, t, function () {
          return i[t];
        });
      }(r);
    }

    a("6a9e");
    var s = a("2877"),
        l = Object(s["a"])(i["default"], n["a"], n["b"], !1, null, null, null);
    _e["default"] = l.exports;
  },
  6389: function _(t, e, a) {},
  "6a9e": function a9e(t, e, a) {
    "use strict";

    var n = a("6389"),
        i = a.n(n);
    i.a;
  },
  aaba: function aaba(t, e, a) {
    "use strict";

    a.r(e);
    var n = a("edcc"),
        i = a.n(n);

    for (var r in n) {
      "default" !== r && function (t) {
        a.d(e, t, function () {
          return n[t];
        });
      }(r);
    }

    e["default"] = i.a;
  },
  edcc: function edcc(t, e, a) {
    "use strict";

    Object.defineProperty(e, "__esModule", {
      value: !0
    }), e.default = void 0;
    var n = i(a("f82e"));

    function i(t) {
      return t && t.__esModule ? t : {
        default: t
      };
    }

    function r(t, e) {
      return u(t) || l(t, e) || s();
    }

    function s() {
      throw new TypeError("Invalid attempt to destructure non-iterable instance");
    }

    function l(t, e) {
      var a = [],
          n = !0,
          i = !1,
          r = void 0;

      try {
        for (var s, l = t[Symbol.iterator](); !(n = (s = l.next()).done); n = !0) {
          if (a.push(s.value), e && a.length === e) break;
        }
      } catch (u) {
        i = !0, r = u;
      } finally {
        try {
          n || null == l["return"] || l["return"]();
        } finally {
          if (i) throw r;
        }
      }

      return a;
    }

    function u(t) {
      if (Array.isArray(t)) return t;
    }

    var o = function o() {
      return a.e("components/uni-calendar/uni-calendar-item").then(a.bind(null, "93b3"));
    },
        h = {
      name: "UniCalendar",
      components: {
        uniCalendarItem: o
      },
      props: {
        date: {
          type: String,
          default: ""
        },
        selected: {
          type: Array,
          default: function _default() {
            return [];
          }
        },
        lunar: {
          type: Boolean,
          default: !1
        },
        disableBefore: {
          type: Boolean,
          default: !1
        },
        startDate: {
          type: String,
          default: ""
        },
        endDate: {
          type: String,
          default: ""
        },
        range: {
          type: Boolean,
          default: !1
        },
        insert: {
          type: Boolean,
          default: !1
        }
      },
      data: function data() {
        return {
          maskShow: !1,
          aniMaskShow: !1,
          dateShow: !1,
          canlender: {
            weeks: []
          },
          multiple: 0,
          multipleDates: {
            begin: "",
            end: "",
            data: []
          },
          isLunar: !1
        };
      },
      watch: {
        date: function date() {
          this.init();
        },
        selected: function selected() {
          this.init();
        },
        lunar: function lunar(t) {
          this.isLunar = t, this.init();
        },
        disableBefore: function disableBefore() {
          this.init();
        },
        startDate: function startDate() {
          this.init();
        },
        endDate: function endDate() {
          this.init();
        }
      },
      created: function created() {
        this.init();
      },
      methods: {
        init: function init() {
          this.getMonthAll(0, this.date, !0);
        },
        open: function open() {
          var t = this;
          this.maskShow = !0, this.multiple = 0, this.multipleDates.data = [], this.multipleDates.begin = "", this.multipleDates.end = "", this.init(), this.$nextTick(function () {
            setTimeout(function () {
              return t.aniMaskShow = !0;
            }, 30);
          });
        },
        close: function close() {
          var t = this;
          this.aniMaskShow = !1, this.$nextTick(function () {
            setTimeout(function () {
              return t.maskShow = !1;
            }, 300);
          });
        },
        confirm: function confirm() {
          this.setEmit("confirm"), this.close();
        },
        getMonthAll: function getMonthAll(t, e) {
          "" === e && (e = new Date());
          var a = this.getWeek(this.getDate(e, t, "month"));
          this.canlender = a, this.insert && this.setEmit("change");
        },
        setEmit: function setEmit(t) {
          var e = this.canlender;
          this.$emit(t, {
            range: this.range ? this.multipleDates : {},
            year: e.year,
            month: e.month,
            date: e.date,
            lunar: e.lunar,
            clockinfo: e.clockinfo || {},
            fulldate: e.year + "-" + e.month + "-" + e.date
          });
        },
        isDisableBefore: function isDisableBefore(t, e, a) {
          var n = this.date || new Date(),
              i = t + "-" + e + "-" + a,
              r = !1,
              s = !1;
          return this.startDate && (r = this.dateCompare(this.startDate, i)), this.endDate && (s = this.dateCompare(this.getDate(this.endDate, 1), i)), this.disableBefore ? this.startDate ? !this.dateCompare(this.getDate(n, 0), i) || !r || s : this.endDate ? !this.dateCompare(this.getDate(n, 0), i) || s : !this.dateCompare(this.getDate(n, 0), i) : this.startDate ? !r || s : !!this.endDate && s;
        },
        backtoday: function backtoday() {
          this.getMonthAll(0, this.date);
        },
        dataBefor: function dataBefor(t, e) {
          var a = this.canlender.year + "-" + this.canlender.month + "-" + this.canlender.date;
          this.getMonthAll(t, a);
        },
        selectDays: function selectDays(t) {
          var e = t.week,
              a = t.index,
              n = t.ischeck,
              i = t.isDay;

          if (n && !i) {
            var r = this.canlender,
                s = r.weeks[e][a].month < 10 ? "0" + r.weeks[e][a].month : r.weeks[e][a].month,
                l = r.weeks[e][a].date < 10 ? "0" + r.weeks[e][a].date : r.weeks[e][a].date,
                u = r.year + "-" + s + "-" + l;
            if (this.isClick = !0, 0 === this.multiple) this.multipleDates.begin = u, this.multiple = 1;else if (1 === this.multiple) {
              this.multiple = 2, this.multipleDates.data && (this.multipleDates.data = []);
              var o = this.dateCompare(this.multipleDates.begin, u);
              o ? (this.multipleDates.data = this.geDateAll(this.multipleDates.begin, u), this.multipleDates.end = u) : (this.multipleDates.data = this.geDateAll(u, this.multipleDates.begin), this.multipleDates.end = this.multipleDates.begin, this.multipleDates.begin = u);
            } else this.multiple = 0, this.multipleDates.data = [], this.multipleDates.begin = "", this.multipleDates.end = "";
            this.getMonthAll(0, u);
          }
        },
        getWeek: function getWeek(t) {
          var e = this;
          "object" !== typeof t && (t = t.replace(/-/g, "/"));

          for (var a = this.selected, i = this.getDate(this.date || new Date()), s = this.getNewDate(t), l = s.year, u = s.month, o = s.date, h = s.day, c = [], d = {
            firstDay: new Date(l, u - 1, 1).getDay(),
            lastMonthDays: [],
            currentMonthDys: [],
            nextMonthDays: [],
            endDay: new Date(l, u, 0).getDay(),
            weeks: []
          }, f = d.firstDay; f > 0; f--) {
            var D = new Date(l, u - 1, 1 - f).getDate() + "";
            d.lastMonthDays.push({
              date: D,
              month: u - 1,
              disable: this.isDisableBefore(l, u - 1, D),
              lunar: this.getlunar(l, u - 1, D),
              isDay: !1
            });
          }

          for (var m = {
            have: !1
          }, p = function p(t) {
            for (var n = !1, s = {}, h = 0; h < a.length; h++) {
              var c = a[h].date.split("-");
              Number(l) === Number(c[0]) && Number(u) === Number(c[1]) && Number(t) === Number(c[2]) && (n = !0, s.have = !0, s.date = a[h].date, a[h].info && (s.info = a[h].info), "{}" !== JSON.stringify(a[h].data) && void 0 === a[h].data || (s.data = a[h].data), Number(l) === Number(c[0]) && Number(u) === Number(c[1]) && Number(o) === Number(c[2]) && (m = s));
            }

            var f = e.multipleDates,
                D = f.begin,
                p = f.end,
                g = f.data,
                b = D.split("-"),
                y = r(b, 3),
                v = y[0],
                w = y[1],
                k = y[2],
                M = p.split("-"),
                N = r(M, 3),
                B = N[0],
                C = N[1],
                A = N[2],
                S = !1,
                T = !1,
                x = !1;
            g.forEach(function (e, a) {
              var n = e.split("-"),
                  i = r(n, 3),
                  s = i[0],
                  o = i[1],
                  h = i[2];
              l === Number(s) && u === Number(o) && t === Number(h) && (S = !0);
            }), l === Number(v) && u === Number(w) && t === Number(k) && (T = !0), l === Number(B) && u === Number(C) && t === Number(A) && (x = !0), d.currentMonthDys.push({
              checked: !!e.range && S,
              multipleBegin: !!e.range && T,
              multipleEnd: !!e.range && x,
              date: t + "",
              month: u,
              have: n,
              clockinfo: s,
              disable: e.isDisableBefore(l, u, t + ""),
              lunar: e.getlunar(l, u, t + ""),
              isDay: i === l + "-" + (u < 10 ? "0" + u : u) + "-" + (t < 10 ? "0" + t : t)
            });
          }, g = 1; g <= new Date(l, u, 0).getDate(); g++) {
            p(g);
          }

          for (var b = 42 - (d.lastMonthDays.length + d.currentMonthDys.length), y = 1; y < b + 1; y++) {
            d.nextMonthDays.push({
              date: y + "",
              month: u + 1,
              disable: this.isDisableBefore(l, u + 1, y + ""),
              lunar: this.getlunar(l, u + 1, y + ""),
              isDay: !1
            });
          }

          c = c.concat(d.lastMonthDays, d.currentMonthDys, d.nextMonthDays);

          for (var v = 0; v < c.length; v++) {
            v % 7 === 0 && (d.weeks[parseInt(v / 7)] = new Array(7)), d.weeks[parseInt(v / 7)][v % 7] = c[v];
          }

          return {
            weeks: d.weeks,
            month: u,
            date: o,
            day: h,
            year: l,
            clockinfo: m,
            lunar: n.default.solar2lunar(l, u, o),
            lastDate: d.currentMonthDys[d.currentMonthDys.length - 1].date
          };
        },
        getlunar: function getlunar(t, e, a) {
          return n.default.solar2lunar(t, e, a).IDayCn;
        },
        getNewDate: function getNewDate(t) {
          var e = new Date(t);
          return {
            year: e.getFullYear(),
            month: e.getMonth() + 1,
            date: e.getDate(),
            day: e.getDay()
          };
        },
        getDate: function getDate(t) {
          var e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 0,
              a = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : "day";
          "object" !== typeof t && (t = t.replace(/-/g, "/"));
          var n = new Date(t);

          switch (a) {
            case "day":
              n.setDate(n.getDate() + e);
              break;

            case "month":
              n.setMonth(n.getMonth() + e);
              break;

            case "year":
              n.setFullYear(n.getFullYear() + e);
              break;
          }

          var i = n.getFullYear(),
              r = n.getMonth() + 1 < 10 ? "0" + (n.getMonth() + 1) : n.getMonth() + 1,
              s = n.getDate() < 10 ? "0" + n.getDate() : n.getDate();
          return i + "-" + r + "-" + s;
        },
        dateCompare: function dateCompare(t, e) {
          return t = new Date(t.replace("-", "/").replace("-", "/")), e = new Date(e.replace("-", "/").replace("-", "/")), t <= e;
        },
        geDateAll: function geDateAll(t, e) {
          var a = [],
              n = t.split("-"),
              i = e.split("-"),
              r = new Date();
          r.setUTCFullYear(n[0], n[1] - 1, n[2]);
          var s = new Date();
          s.setUTCFullYear(i[0], i[1] - 1, i[2]);

          for (var l = r.getTime() - 864e5, u = s.getTime() - 864e5, o = l; o <= u;) {
            o += 864e5, a.push(this.getDate(new Date(parseInt(o))));
          }

          return a;
        }
      }
    };

    e.default = h;
  },
  fb19: function fb19(t, e, a) {
    "use strict";

    var n = function n() {
      var t = this,
          e = t.$createElement;
      t._self._c;
    },
        i = [];

    a.d(e, "a", function () {
      return n;
    }), a.d(e, "b", function () {
      return i;
    });
  }
}]);
;
(global["webpackJsonp"] = global["webpackJsonp"] || []).push(['components/uni-calendar/uni-calendar-create-component', {
  'components/uni-calendar/uni-calendar-create-component': function componentsUniCalendarUniCalendarCreateComponent(module, exports, __webpack_require__) {
    __webpack_require__('6e42')['createComponent'](__webpack_require__("562e"));
  }
}, [['components/uni-calendar/uni-calendar-create-component']]]);
});
require('components/uni-calendar/uni-calendar.js');

__wxRoute = 'pages/login/login';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/login/login.js';

define('pages/login/login.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/login/login"],{3921:function(n,t,o){},"57a3":function(n,t,o){"use strict";o.r(t);var e=o("f3d1"),a=o("7dd0");for(var i in a)"default"!==i&&function(n){o.d(t,n,function(){return a[n]})}(i);o("a54b");var s=o("2877"),u=Object(s["a"])(a["default"],e["a"],e["b"],!1,null,null,null);t["default"]=u.exports},"7dd0":function(n,t,o){"use strict";o.r(t);var e=o("e614"),a=o.n(e);for(var i in e)"default"!==i&&function(n){o.d(t,n,function(){return e[n]})}(i);t["default"]=a.a},a54b:function(n,t,o){"use strict";var e=o("3921"),a=o.n(e);a.a},e614:function(n,t,o){"use strict";(function(n){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o={data:function(){return{isPassword:!0,userName:"admin@1002",passWord:"",storeInfo:""}},onShow:function(){this.getInfo()},methods:{getInfo:function(){var n=this;this.http.resquetData("/app/supplier","GET",{},function(t){console.log(t," at pages\\login\\login.vue:44"),n.storeInfo=t.data})},toForget:function(){n.navigateTo({url:"../login/forget"})},login:function(){return""==this.userName?(n.showToast({title:"请输入用户名",duration:2e3,icon:"none",mask:!0}),!1):""==this.passWord?(n.showToast({title:"请输入密码",duration:2e3,icon:"none",mask:!0}),!1):(n.showLoading({title:"登录中"}),void this.http.resquetData("/app/supplier/login","POST",{username:this.userName,password:this.passWord},function(t){console.log(t," at pages\\login\\login.vue:78"),""!=t.token&&(n.setStorageSync("tokenUser",t.token),n.hideLoading(),n.switchTab({url:"/pages/index/index"}))}))}}};t.default=o}).call(this,o("6e42")["default"])},f3d1:function(n,t,o){"use strict";var e=function(){var n=this,t=n.$createElement;n._self._c;n._isMounted||(n.e0=function(t){n.isPassword=!n.isPassword})},a=[];o.d(t,"a",function(){return e}),o.d(t,"b",function(){return a})}},[["612c","common/runtime","common/vendor"]]]);
});
require('pages/login/login.js');
__wxRoute = 'pages/data/calendar';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/data/calendar.js';

define('pages/data/calendar.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/data/calendar"],{"0b41":function(t,a,r){"use strict";r.r(a);var e=r("c368"),n=r.n(e);for(var u in e)"default"!==u&&function(t){r.d(a,t,function(){return e[t]})}(u);a["default"]=n.a},"134c":function(t,a,r){"use strict";r.r(a);var e=r("5e4b"),n=r("0b41");for(var u in n)"default"!==u&&function(t){r.d(a,t,function(){return n[t]})}(u);r("82d2");var o=r("2877"),c=Object(o["a"])(n["default"],e["a"],e["b"],!1,null,null,null);a["default"]=c.exports},1761:function(t,a,r){},"5e4b":function(t,a,r){"use strict";var e=function(){var t=this,a=t.$createElement;t._self._c},n=[];r.d(a,"a",function(){return e}),r.d(a,"b",function(){return n})},"82d2":function(t,a,r){"use strict";var e=r("1761"),n=r.n(e);n.a},c368:function(t,a,r){"use strict";Object.defineProperty(a,"__esModule",{value:!0}),a.default=void 0;r("9fd5");var e=n(r("9fd5"));function n(t){return t&&t.__esModule?t:{default:t}}var u={data:function(){return{monthArr:[],totalPage:"",curPage:1,isMore:!0,curDay:"",curDay2:""}},created:function(){this.getMonth(this.curPage)},methods:{loadMore:function(){this.curPage++,this.curPage<=this.totalPage&&1==this.isMore?this.getMonth(this.curPage):this.isMore=!1},returnDay:function(){var t=this,a="day"+(new Date).getMonth();console.log(this.curDay," at pages\\data\\calendar.vue:69"),this.curDay="",this.$nextTick(function(){t.curDay=a})},getMonth:function(t){var a=this,r=e.default.getMchId(),n=e.default.getSupplierId();this.http.resquetData("/app/orderDataInfo/buyNumPageCalendar","GET",{mchId:r,supplierId:n},function(t){a.monthArr=t.data.result;var r={};for(var e in a.monthArr)switch(a.monthArr[e].calendarVOS[0].weekDay){case"星期六":for(var n=0;n<6;n++)a.monthArr[e].calendarVOS.unshift(r);break;case"星期五":for(n=0;n<5;n++)a.monthArr[e].calendarVOS.unshift(r);break;case"星期四":for(n=0;n<4;n++)a.monthArr[e].calendarVOS.unshift(r);break;case"星期三":for(n=0;n<3;n++)a.monthArr[e].calendarVOS.unshift(r);break;case"星期二":for(n=0;n<2;n++)a.monthArr[e].calendarVOS.unshift(r);break;case"星期一":for(n=0;n<1;n++)a.monthArr[e].calendarVOS.unshift(r);break;default:break}console.log(a.monthArr," at pages\\data\\calendar.vue:121")})}}};a.default=u}},[["69d4","common/runtime","common/vendor"]]]);
});
require('pages/data/calendar.js');
__wxRoute = 'pages/index/index';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/index/index.js';

define('pages/index/index.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/index/index"],{"1fac":function(e,t,s){"use strict";var o=function(){var e=this,t=e.$createElement;e._self._c;e._isMounted||(e.e0=function(t){e.isMask=!1})},n=[];s.d(t,"a",function(){return o}),s.d(t,"b",function(){return n})},5539:function(e,t,s){"use strict";(function(e){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o=n(s("9fd5"));function n(e){return e&&e.__esModule?e:{default:e}}var i={data:function(){return{choosedTitle:"检",choosedColor:"choosedColor",showPoint:!1,storeInfo:"",selectList:"",isactive:"-1",sync_mch_id:"",is_choose_mask:!1,venueList:"",isUp:!1,checketList:"",isMask:!1,result:[],orderNo:""}},onShow:function(){this.getInfo()},onNavigationBarButtonTap:function(t){if(this.isUp=!this.isUp,1==this.isUp){var s=e.getStorageSync(o.default.set_ocean_mch_storageSync);s&&(this.sync_mch_id=s),document.getElementsByTagName("body")[0].className="pages-index-indexactive"}else document.getElementsByTagName("body")[0].className="pages-index-index"},methods:{tel:function(){e.scanCode({success:function(e){console.log(JSON.stringify(e)," at pages\\index\\index.vue:175"),alert(JSON.stringify(e))}})},getInfo:function(){var t=this,s=e.getStorageSync(o.default.set_ocean_mch_storageSync);if(s&&(this.is_choose_mask=!0),this.http.resquetData("/app/supplier","",{},function(e){console.log(e," at pages\\index\\index.vue:189"),t.storeInfo=e.data}),this.http.resquetData("/app/supplier/supplierStore","GET",{},function(e){console.log(e," at pages\\index\\index.vue:194"),t.selectList=e.data}),s)if(e.getStorageSync(o.default.set_mch_choosed_sync)){var n=e.getStorageSync(o.default.set_mch_choosed_sync),i=n.split(",");this.choosedTitle=i[1]}else this.http.resquetData("/app/supplier/mchVenue","GET",{mchId:s},function(s){var n=s.data;1==n.length&&(t.choosedColor="point",t.choosedTitle=n[0].title,e.setStorageSync(o.default.set_mch_choosed_sync,n[0].id+","+n[0].title))})},venueClick:function(t,s){o.default.getShowLoading("检票口",s),this.choosedTitle=s,this.showPoint=!this.showPoint;var n=document.getElementsByClassName("check-point");n[0].style.height="55px",e.setStorageSync(o.default.set_mch_choosed_sync,t+","+s)},changeSel:function(t,s,n){e.setStorageSync(o.default.set_ocean_mch_storageSync,t);for(var i=document.getElementsByClassName("point"),a=i.length-1;a>=0;a--)document.getElementsByClassName("check-point")[0].removeChild(i[a]);var c=document.getElementsByClassName("check-point");c[0].style.height="55px",o.default.getShowLoading("商户",n)},choosed:function(){var t=this;this.showPoint=!this.showPoint;var s=document.getElementsByClassName("check-point");if(1==this.showPoint){var n=e.getStorageSync(o.default.set_ocean_mch_storageSync);n&&this.http.resquetData("/app/supplier/mchVenue","GET",{mchId:n},function(e){var o=e.data;if(o.length>=1){var n=55*(o.length+1);s[0].style.height=n+"px"}t.venueList=o})}else s[0].style.height="55px"},selectClick:function(e,t,s){this.isactive=t,this.is_choose_mask=!0,this.showPoint=!1,this.choosed_img="../../static/img/point2.png",this.$options.methods.changeSel(e,t,s)},choose_sel:function(e,t,s){this.sync_mch_id=e,this.isUp=!this.isUp,this.showPoint=!1,this.choosed_img="../../static/img/point2.png",this.$options.methods.changeSel(e,t,s)},toCheck:function(){var t=this;if(""==this.orderNo)return e.showToast({title:"请输入订单号",duration:1e3,icon:"none",mask:!0}),!1;if(!e.getStorageSync(o.default.set_mch_choosed_sync))return e.showToast({title:"请选择检票口,然后再进行核销",icon:"none",mask:!0}),!1;var s=o.default.getMchId(),n=o.default.getSupplierId();this.http.resquetData("/app/orderCheck/queryOrder","",{orderNo:this.orderNo,mchId:s,mchVenueId:n},function(e){console.log(e," at pages\\index\\index.vue:315"),""!=e.data&&(t.checketList=e.data,t.isMask=!0)})},reduce:function(t){t.defaultNum>0?t.defaultNum--:e.showToast({title:"不能再减少了",duration:2e3,icon:"none",mask:!0})},add:function(t){t.defaultNum<t.canUseNum?t.defaultNum++:e.showToast({title:"不能再增加了",duration:2e3,icon:"none",mask:!0})},submitInfo:function(){var t=this,s={};for(var n in this.checketList)this.checketList[n].defaultNum>0&&(s={orderGoodsId:this.checketList[n].orderGoodsId,num:this.checketList[n].defaultNum},console.log(s," at pages\\index\\index.vue:357"),this.result.push(s));if(this.result.length>0){var i=o.default.getMchId(),a=o.default.getSupplierId(),c=e.getStorageSync(o.default.set_mch_choosed_sync).split(",")[0];this.http.resquetData("/app/orderCheck/checkOrder","POST",{orderNo:this.orderNo,mchId:i,mchVenueId:c,supplierId:a,goods:this.result},function(s){e.showToast({title:"验证成功",duration:2e3,mask:!0}),setTimeout(function(e){t.isMask=!1},2e3)})}}}};t.default=i}).call(this,s("6e42")["default"])},"6bdd":function(e,t,s){},"8c61":function(e,t,s){"use strict";s.r(t);var o=s("5539"),n=s.n(o);for(var i in o)"default"!==i&&function(e){s.d(t,e,function(){return o[e]})}(i);t["default"]=n.a},"8ec4":function(e,t,s){"use strict";s.r(t);var o=s("1fac"),n=s("8c61");for(var i in n)"default"!==i&&function(e){s.d(t,e,function(){return n[e]})}(i);s("c420");var a=s("2877"),c=Object(a["a"])(n["default"],o["a"],o["b"],!1,null,null,null);t["default"]=c.exports},c420:function(e,t,s){"use strict";var o=s("6bdd"),n=s.n(o);n.a}},[["08f3","common/runtime","common/vendor"]]]);
});
require('pages/index/index.js');
__wxRoute = 'pages/login/forget';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/login/forget.js';

define('pages/login/forget.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/login/forget"],{"229c":function(n,t,e){"use strict";e.r(t);var o=e("de01"),u=e.n(o);for(var r in o)"default"!==r&&function(n){e.d(t,n,function(){return o[n]})}(r);t["default"]=u.a},"232f":function(n,t,e){"use strict";var o=e("f455"),u=e.n(o);u.a},"74ed":function(n,t,e){"use strict";e.r(t);var o=e("7888"),u=e("229c");for(var r in u)"default"!==r&&function(n){e.d(t,n,function(){return u[n]})}(r);e("232f");var i=e("2877"),f=Object(i["a"])(u["default"],o["a"],o["b"],!1,null,null,null);t["default"]=f.exports},7888:function(n,t,e){"use strict";var o=function(){var n=this,t=n.$createElement;n._self._c},u=[];e.d(t,"a",function(){return o}),e.d(t,"b",function(){return u})},de01:function(n,t,e){"use strict";(function(n){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o=function(){return e.e("components/forget-header").then(e.bind(null,"32fe"))},u={components:{forget:o},data:function(){return{shopId:""}},onLoad:function(){},methods:{next:function(){if(""==this.shopId)return n.showToast({title:"请输入商家账号",duration:1e3,icon:"none",mask:!0}),!1;n.navigateTo({url:"../login/forget1?id="+this.shopId})}}};t.default=u}).call(this,e("6e42")["default"])},f455:function(n,t,e){}},[["595e","common/runtime","common/vendor"]]]);
});
require('pages/login/forget.js');
__wxRoute = 'pages/login/forget1';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/login/forget1.js';

define('pages/login/forget1.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/login/forget1"],{5647:function(t,e,n){"use strict";n.r(e);var o=n("b7ea"),i=n("85ae");for(var a in i)"default"!==a&&function(t){n.d(e,t,function(){return i[t]})}(a);n("e50e");var u=n("2877"),r=Object(u["a"])(i["default"],o["a"],o["b"],!1,null,null,null);e["default"]=r.exports},"85ae":function(t,e,n){"use strict";n.r(e);var o=n("b027"),i=n.n(o);for(var a in o)"default"!==a&&function(t){n.d(e,t,function(){return o[t]})}(a);e["default"]=i.a},b027:function(t,e,n){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var o=function(){return n.e("components/forget-header").then(n.bind(null,"32fe"))},i={components:{forget:o},data:function(){return{curStep:2,phoneNum:"",phoneCode:"",isStart:!1,countDown:60,timer:"",shopId:""}},onLoad:function(t){console.log(t.id," at pages\\login\\forget1.vue:39"),this.shopId=t.id},onHide:function(){clearInterval(this.timer)},methods:{sendCode:function(){var e=this,n=/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/;if(""==this.phoneNum||0==n.test(this.phoneNum))return t.showToast({title:"请输入正确的手机号",duration:1e3,icon:"none",mask:!0}),!1;0==this.isStart&&this.http.resquetData("/app/supplier/code","",{mobile:this.phoneNum,username:escape(this.shopId)},function(t){e.isStart=!0,e.timer=setInterval(function(t){e.countDown>1?(e.countDown--,console.log(e.countDown," at pages\\login\\forget1.vue:64")):(e.isStart=!1,e.countDown=60,clearInterval(e.timer))},1e3)})},toNext:function(){var e=this,n=/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/;return""==this.phoneNum||0==n.test(this.phoneNum)?(t.showToast({title:"请输入正确的手机号",duration:1e3,icon:"none",mask:!0}),!1):""==this.phoneCode?(t.showToast({title:"请输入验证码",duration:1e3,icon:"none",mask:!0}),!1):void this.http.resquetData("/app/supplier/validatorCode","",{mobile:this.phoneNum,code:this.phoneCode,username:this.shopId},function(n){1e4==n.code&&(console.log(n," at pages\\login\\forget1.vue:97"),t.setStorageSync("phoneNum",e.phoneNum),t.navigateTo({url:"../login/forget2?id="+e.shopId}))})}}};e.default=i}).call(this,n("6e42")["default"])},b7ea:function(t,e,n){"use strict";var o=function(){var t=this,e=t.$createElement;t._self._c},i=[];n.d(e,"a",function(){return o}),n.d(e,"b",function(){return i})},c3d1:function(t,e,n){},e50e:function(t,e,n){"use strict";var o=n("c3d1"),i=n.n(o);i.a}},[["3a0a","common/runtime","common/vendor"]]]);
});
require('pages/login/forget1.js');
__wxRoute = 'pages/login/forget2';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/login/forget2.js';

define('pages/login/forget2.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/login/forget2"],{1769:function(t,n,o){"use strict";(function(t){Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var e=function(){return o.e("components/forget-header").then(o.bind(null,"32fe"))},i={components:{forget:e},data:function(){return{curStep:3,password1:"",password2:"",phoneNum:"",shopId:""}},onLoad:function(n){this.shopId=n.id,this.phoneNum=t.getStorageSync("phoneNum")},methods:{finish:function(){return""!=this.password1?(t.showToast({title:"请输入密码",duration:1e3,icon:"none",mask:!0}),!1):this.password2!=this.password1?(t.showToast({title:"请输入相同的密码",duration:1e3,icon:"none",mask:!0}),!1):void this.http.resquetData("/app/supplier/rebuildPwd","POST",{mobile:this.phoneNum,password:this.password2,username:this.shopId},function(n){1e4==n.code&&(console.log(n," at pages\\login\\forget2.vue:57"),t.showToast({title:"修改成功",duration:1500}),setTimeout(function(n){t.navigateTo({url:"../login/login"})},1500))})}}};n.default=i}).call(this,o("6e42")["default"])},4263:function(t,n,o){},"4b6d":function(t,n,o){"use strict";var e=o("4263"),i=o.n(e);i.a},"59c7":function(t,n,o){"use strict";o.r(n);var e=o("1769"),i=o.n(e);for(var s in e)"default"!==s&&function(t){o.d(n,t,function(){return e[t]})}(s);n["default"]=i.a},"9b21":function(t,n,o){"use strict";o.r(n);var e=o("9d05"),i=o("59c7");for(var s in i)"default"!==s&&function(t){o.d(n,t,function(){return i[t]})}(s);o("4b6d");var u=o("2877"),r=Object(u["a"])(i["default"],e["a"],e["b"],!1,null,null,null);n["default"]=r.exports},"9d05":function(t,n,o){"use strict";var e=function(){var t=this,n=t.$createElement;t._self._c},i=[];o.d(n,"a",function(){return e}),o.d(n,"b",function(){return i})}},[["46a8","common/runtime","common/vendor"]]]);
});
require('pages/login/forget2.js');
__wxRoute = 'pages/checking/checking';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/checking/checking.js';

define('pages/checking/checking.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/checking/checking"],{"48e3":function(e,t,n){"use strict";n.r(t);var c=n("8292"),o=n("8f0b");for(var a in o)"default"!==a&&function(e){n.d(t,e,function(){return o[e]})}(a);n("81a2");var u=n("2877"),r=Object(u["a"])(o["default"],c["a"],c["b"],!1,null,null,null);t["default"]=r.exports},"81a2":function(e,t,n){"use strict";var c=n("9424"),o=n.n(c);o.a},8292:function(e,t,n){"use strict";var c=function(){var e=this,t=e.$createElement;e._self._c},o=[];n.d(t,"a",function(){return c}),n.d(t,"b",function(){return o})},"8f0b":function(e,t,n){"use strict";n.r(t);var c=n("f0ee"),o=n.n(c);for(var a in c)"default"!==a&&function(e){n.d(t,e,function(){return c[e]})}(a);t["default"]=o.a},9424:function(e,t,n){},f0ee:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var c=o(n("9fd5"));function o(e){return e&&e.__esModule?e:{default:e}}var a={data:function(){return{checkSum:"0",checkMoney:"0.00",notCheckSum:"0",notCheckMoney:"0.00",orderCheckList:"",today_view:!1}},onLoad:function(){this.initList()},methods:{initList:function(){var e=this,t=c.default.getMchId();this.http.resquetData("/app/order/check/todayOrderSum","GET",{mchId:t},function(t){e.today_view=!0,e.checkSum=t.data.checkSum,e.checkMoney=t.data.checkMoney,e.notCheckSum=t.data.notCheckSum,e.notCheckMoney=t.data.notCheckMoney,e.orderCheckList=t.data.orderCheckVOs})}}};t.default=a}},[["f0af","common/runtime","common/vendor"]]]);
});
require('pages/checking/checking.js');
__wxRoute = 'pages/checking/historyList';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/checking/historyList.js';

define('pages/checking/historyList.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/checking/historyList"],{"01eb":function(t,e,o){"use strict";o.r(e);var n=o("3d1a"),c=o.n(n);for(var i in n)"default"!==i&&function(t){o.d(e,t,function(){return n[t]})}(i);e["default"]=c.a},"0888":function(t,e,o){},"207a":function(t,e,o){"use strict";var n=o("0888"),c=o.n(n);c.a},"3d1a":function(t,e,o){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var n=c(o("9fd5"));function c(t){return t&&t.__esModule?t:{default:t}}var i={data:function(){return{productList:[],choosedId:""}},created:function(){this.initList()},methods:{initList:function(){var t=this,e=n.default.getMchId(),o=n.default.getSupplierId();this.http.resquetData("/app/order/check/mchProduct","GET",{mchId:e,supplierId:o},function(e){console.log(e," at pages\\checking\\historyList.vue:37"),t.productList=e.data,t.productList.forEach(function(e){t.$set(e,"choosed",!1)}),console.log(t.productList," at pages\\checking\\historyList.vue:42")})},change:function(e,o){if(1==e.choosed)e.choosed=!1;else for(var n in this.productList)n==o?(this.productList[n].choosed=!0,this.choosedId=e.id,t.setStorageSync("choosedId",e.id),t.setStorageSync("choosedName",e.title)):this.productList[n].choosed=!1;console.log(this.choosedId," at pages\\checking\\historyList.vue:61")},submitChoose:function(){t.navigateBack({delta:1})}}};e.default=i}).call(this,o("6e42")["default"])},"4ea5":function(t,e,o){"use strict";o.r(e);var n=o("886f"),c=o("01eb");for(var i in c)"default"!==i&&function(t){o.d(e,t,function(){return c[t]})}(i);o("207a");var s=o("2877"),u=Object(s["a"])(c["default"],n["a"],n["b"],!1,null,null,null);e["default"]=u.exports},"886f":function(t,e,o){"use strict";var n=function(){var t=this,e=t.$createElement;t._self._c},c=[];o.d(e,"a",function(){return n}),o.d(e,"b",function(){return c})}},[["172e","common/runtime","common/vendor"]]]);
});
require('pages/checking/historyList.js');
__wxRoute = 'pages/checking/historyList2';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/checking/historyList2.js';

define('pages/checking/historyList2.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/checking/historyList2"],{"1f05":function(t,e,i){},"320d":function(t,e,i){"use strict";i.r(e);var n=i("892e"),s=i("8130");for(var o in s)"default"!==o&&function(t){i.d(e,t,function(){return s[t]})}(o);i("fe07");var c=i("2877"),r=Object(c["a"])(s["default"],n["a"],n["b"],!1,null,null,null);e["default"]=r.exports},"38cb":function(t,e,i){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var i={data:function(){return{checkNum:"",historyList:"",checkId:""}},onLoad:function(){this.initList()},methods:{choose:function(t,e){if(0==t.chooseStatus)for(var i in this.historyList)i==e?(this.historyList[i].chooseStatus=1,this.checkId=this.historyList[i].id):this.historyList[i].chooseStatus=0;else t.chooseStatus=0,this.checkId=""},initList:function(){var t=this;this.http.resquetData("/app/orderCheck/queryCheckOrder","",{param:"20190604144311"},function(e){console.log(e," at pages\\checking\\historyList2.vue:53"),t.historyList=e.data})},cancelOrder:function(){var e=this;this.http.resquetData("/app/orderCheck/cancel","POST",{checkOrderId:this.checkId},function(i){console.log(i," at pages\\checking\\historyList2.vue:60"),t.showToast({title:"撤销成功",duration:1e3,mask:!0}),setTimeout(function(t){e.initList()},1e3)})}}};e.default=i}).call(this,i("6e42")["default"])},8130:function(t,e,i){"use strict";i.r(e);var n=i("38cb"),s=i.n(n);for(var o in n)"default"!==o&&function(t){i.d(e,t,function(){return n[t]})}(o);e["default"]=s.a},"892e":function(t,e,i){"use strict";var n=function(){var t=this,e=t.$createElement;t._self._c},s=[];i.d(e,"a",function(){return n}),i.d(e,"b",function(){return s})},fe07:function(t,e,i){"use strict";var n=i("1f05"),s=i.n(n);s.a}},[["c502","common/runtime","common/vendor"]]]);
});
require('pages/checking/historyList2.js');
__wxRoute = 'pages/data/data';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/data/data.js';

define('pages/data/data.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/data/data"],{1707:function(e,t,a){"use strict";a.r(t);var n=a("b883"),i=a.n(n);for(var o in n)"default"!==o&&function(e){a.d(t,e,function(){return n[e]})}(o);t["default"]=i.a},a742:function(e,t,a){},b883:function(e,t,a){"use strict";(function(e){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;r(a("0f02"));var n=o(a("9fd5")),i=o(a("2981"));function o(e){return e&&e.__esModule?e:{default:e}}function r(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var a in e)if(Object.prototype.hasOwnProperty.call(e,a)){var n=Object.defineProperty&&Object.getOwnPropertyDescriptor?Object.getOwnPropertyDescriptor(e,a):{};n.get||n.set?Object.defineProperty(t,a,n):t[a]=e[a]}return t.default=e,t}var c,l=function(){return Promise.all([a.e("common/vendor"),a.e("components/mpvue-echarts/src/echarts")]).then(a.bind(null,"2c37"))},d=function(){return Promise.all([a.e("common/vendor"),a.e("components/uni-calendar/uni-calendar")]).then(a.bind(null,"562e"))},u=null,s={components:{mpvueEcharts:l,uniCalendar:d},data:function(){return{hidden:!1,nowTime:"",dataDetail:"",calendarDeatail:"",uniCalendarStartDate:"2019-09-11",uniCalendarendDate:"2019-09-11",blank:"",showCaledar:!1,beginDate:"2019-05-06",endDate:"2019-05-07",cWidth:"",cHeight:"",pixelRatio:1,dataTest:{categories:["1:00","2:00","3:00","4:00","5:00","6:00"],series:[{name:"成交量A",data:[220,102,446,600,800,440],color:"#00CE9F"}]}}},onLoad:function(){this.getData(),this.calendarData(),this.optTime(),c=this,this.cWidth=e.upx2px(620),this.cHeight=e.upx2px(500),this.showLine("canvasLine",this.dataTest)},mounted:function(){var e=this;setInterval(function(){e.nowTime=(new Date).toLocaleString()},1e3)},beforeDestroy:function(){this.timer&&clearInterval(this.timer)},methods:{open:function(){this.$refs.calendar.open()},confirm:function(e){var t=this,a=e.range.begin,i=e.range.end,o=n.default.getMchId(),r=a+" ~ "+i;this.http.resquetData("/app/orderDataInfo","GET",{mchId:o,date:r},function(e){console.log(1111," at pages\\data\\data.vue:284"),t.dataDetail=e.data})},getDate:function(e){console.log(e," at pages\\data\\data.vue:290")},getTime:function(){var e=this;setInterval(function(){e.nowTime=(new Date).toLocaleString()},1e3)},showLine:function(e,t){u=new i.default({$this:c,canvasId:e,type:"line",fontSize:11,legend:!0,dataLabel:!0,dataPointShape:!0,background:"#FFFFFF",pixelRatio:c.pixelRatio,categories:t.categories,series:t.series,animation:!0,enableScroll:!0,xAxis:{disableGrid:!1,type:"grid",gridType:"dash",itemCount:4,scrollShow:!0,scrollAlign:"left"},yAxis:{gridType:"dash",splitNumber:8,min:10,max:180},width:c.cWidth*c.pixelRatio,height:c.cHeight*c.pixelRatio,extra:{line:{type:"straight"}}})},touchLine:function(e){u.scrollStart(e)},moveLine:function(e){u.scroll(e)},touchEndLine:function(e){u.scrollEnd(e),u.showToolTip(e,{format:function(e,t){return t+" "+e.name+":"+e.data}})},getData:function(){var e=this,t=n.default.getMchId();this.http.resquetData("/app/orderDataInfo","GET",{mchId:t,date:n.default.getNowDate()},function(t){console.log(t," at pages\\data\\data.vue:377"),e.dataDetail=t.data})},optTime:function(){var e=this,t=n.default.getMchId();this.http.resquetData("/app/orderDataInfo/buyNumPageCalendar","GET",{mchId:t},function(t){var a=t.data;e.uniCalendarStartDate=a.time,e.uniCalendarendDate=a.nowTime})},calendarData:function(){var e=this,t=n.default.getMchId();this.http.resquetData("/app/orderDataInfo/buyNumCalendar","GET",{mchId:t},function(t){switch(console.log(t," at pages\\data\\data.vue:402"),t.data.calendarVOS[0].weekDay){case"星期六":e.blank=6;break;case"星期五":e.blank=5;break;case"星期四":e.blank=4;break;case"星期三":e.blank=3;break;case"星期二":e.blank=2;break;case"星期一":e.blank=1;break;default:break}e.calendarDeatail=t.data})},change:function(e){var t=e.choiceDate,a=e.dayCount;console.log(t,a," at pages\\data\\data.vue:437")}}};t.default=s}).call(this,a("6e42")["default"])},c370:function(e,t,a){"use strict";var n=a("a742"),i=a.n(n);i.a},c54e:function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement;e._self._c;e._isMounted||(e.e0=function(t){e.showCaledar=!e.showCaledar})},i=[];a.d(t,"a",function(){return n}),a.d(t,"b",function(){return i})},d249:function(e,t,a){"use strict";a.r(t);var n=a("c54e"),i=a("1707");for(var o in i)"default"!==o&&function(e){a.d(t,e,function(){return i[e]})}(o);a("c370");var r=a("2877"),c=Object(r["a"])(i["default"],n["a"],n["b"],!1,null,null,null);t["default"]=c.exports}},[["0742","common/runtime","common/vendor"]]]);
});
require('pages/data/data.js');
__wxRoute = 'pages/data/comment';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/data/comment.js';

define('pages/data/comment.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/data/comment"],{"12c6":function(t,i,e){"use strict";(function(t){Object.defineProperty(i,"__esModule",{value:!0}),i.default=void 0;var s=e("9fd5"),a=function(){return e.e("components/tabNav").then(e.bind(null,"be9d"))},n={components:{tabNav:a},data:function(){return{isMore:!1,commentList:[],tagList:"",curIndex:-1,commtentType:1,tagsId:"",score:"",goodsId:"",time:"",page:1,pageSize:10,isStar:!1,imageList:[],totalPage:""}},onLoad:function(){this.initList(this.commtentType,this.tagsId,this.score,this.goodsId,this.time,this.page,this.pageSize),this.initTag()},onReachBottom:function(){this.page<this.totalPage&&(this.page++,this.initList(this.commtentType,this.tagsId,this.score,this.goodsId,this.time,this.page,this.pageSize))},methods:{choose:function(t){var i=t+1;i<4?(this.commtentType=i,this.initList2(this.commtentType,this.tagsId,this.score,this.goodsId,this.time,this.page,this.pageSize)):this.isStar=!0},getStar:function(t){this.score=t,this.initList2(this.commtentType,this.tagsId,this.score,this.goodsId,this.time,this.page,this.pageSize),this.isStar=!1},initList:function(t,i,e,s,a,n,o){var c=this;this.http.resquetData("/api/supplierComment/findList","",{commtentType:t,tagsId:i,score:e,goodsId:s,time:a,page:n,pageSize:o},function(t){c.totalPage=t.data.page.totalPage,c.commentList=c.commentList.concat(t.data.result)})},initList2:function(t,i,e,s,a,n,o){var c=this;this.http.resquetData("/api/supplierComment/findList","",{commtentType:t,tagsId:i,score:e,goodsId:s,time:a,page:n,pageSize:o},function(t){c.totalPage=t.data.page.totalPage,c.commentList=t.data.result})},initTag:function(){var t=this;this.http.resquetData("/api/supplierComment/findTags","",{},function(i){t.tagList=i.data})},toDetail:function(i){t.navigateTo({url:"../data/commentDetail?commentId="+i.id+"&mchId="+i.mchId})},formats:function(t){return(0,s.format)(t)},changeTag:function(t,i){this.curIndex=i,this.tagsId=t.value,this.initList(this.commtentType,this.tagsId,this.score,this.goodsId,this.time,this.page,this.pageSize)},prievewImg:function(i){var e=i.target.dataset.src;this.imageList.push(e),t.previewImage({current:e,urls:this.imageList})},toReplay:function(i){t.navigateTo({url:"replay?commentId="+i.id+"&mchId="+i.mchId})}}};i.default=n}).call(this,e("6e42")["default"])},"178a":function(t,i,e){"use strict";e.r(i);var s=e("934a"),a=e("7a78");for(var n in a)"default"!==n&&function(t){e.d(i,t,function(){return a[t]})}(n);e("60b0");var o=e("2877"),c=Object(o["a"])(a["default"],s["a"],s["b"],!1,null,null,null);i["default"]=c.exports},"60b0":function(t,i,e){"use strict";var s=e("be8d"),a=e.n(s);a.a},"7a78":function(t,i,e){"use strict";e.r(i);var s=e("12c6"),a=e.n(s);for(var n in s)"default"!==n&&function(t){e.d(i,t,function(){return s[t]})}(n);i["default"]=a.a},"934a":function(t,i,e){"use strict";var s=function(){var t=this,i=t.$createElement,e=(t._self._c,t.__map(t.commentList,function(i,e){var s=t.formats(1e3*i.ctime);return{$orig:t.__get_orig(i),m0:s}}));t._isMounted||(t.e0=function(i){t.isMore=!t.isMore}),t.$mp.data=Object.assign({},{$root:{l0:e}})},a=[];e.d(i,"a",function(){return s}),e.d(i,"b",function(){return a})},be8d:function(t,i,e){}},[["ffb3","common/runtime","common/vendor"]]]);
});
require('pages/data/comment.js');
__wxRoute = 'pages/data/replay';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/data/replay.js';

define('pages/data/replay.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/data/replay"],{"041a":function(t,n,e){"use strict";e.r(n);var a=e("f23d"),o=e("9c4a");for(var c in o)"default"!==c&&function(t){e.d(n,t,function(){return o[t]})}(c);e("a9e8");var i=e("2877"),u=Object(i["a"])(o["default"],a["a"],a["b"],!1,null,null,null);n["default"]=u.exports},"3c37":function(t,n,e){"use strict";(function(t){Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var a=e("9fd5"),o={data:function(){return{commentId:"",mchId:"",detailInfo:{comment:{content:""}},content:""}},onLoad:function(t){this.commentId=t.commentId,this.mchId=t.mchId,this.getDetail()},methods:{getDetail:function(){var t=this;this.http.resquetData("/api/supplierComment/details","",{commentId:this.commentId,mchId:this.mchId},function(n){console.log(n," at pages\\data\\replay.vue:48"),t.detailInfo=n.data})},formats:function(t){return(0,a.format)(t)},submitReplay:function(){var n=this;if(console.log(this.content," at pages\\data\\replay.vue:58"),""==this.content)return t.showToast({title:"请输入回复",duration:1e3,icon:"none",mask:!0}),!1;this.http.resquetData("/api/supplierComment/reply","POST",{commentId:parseInt(this.commentId),content:this.content},function(t){console.log(t," at pages\\data\\replay.vue:69"),n.detailInfo=t.data})}}};n.default=o}).call(this,e("6e42")["default"])},"9c4a":function(t,n,e){"use strict";e.r(n);var a=e("3c37"),o=e.n(a);for(var c in a)"default"!==c&&function(t){e.d(n,t,function(){return a[t]})}(c);n["default"]=o.a},a9e8:function(t,n,e){"use strict";var a=e("e290"),o=e.n(a);o.a},e290:function(t,n,e){},f23d:function(t,n,e){"use strict";var a=function(){var t=this,n=t.$createElement,e=(t._self._c,t.formats(t.detailInfo.comment.ctime));t.$mp.data=Object.assign({},{$root:{m0:e}})},o=[];e.d(n,"a",function(){return a}),e.d(n,"b",function(){return o})}},[["d616","common/runtime","common/vendor"]]]);
});
require('pages/data/replay.js');
__wxRoute = 'pages/data/commentDetail';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/data/commentDetail.js';

define('pages/data/commentDetail.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/data/commentDetail"],{"1afd":function(t,n,e){"use strict";e.r(n);var o=e("6919"),a=e.n(o);for(var c in o)"default"!==c&&function(t){e.d(n,t,function(){return o[t]})}(c);n["default"]=a.a},"233b":function(t,n,e){"use strict";var o=function(){var t=this,n=t.$createElement,e=(t._self._c,t.formats(t.detailInfo.comment.ctime));t.$mp.data=Object.assign({},{$root:{m0:e}})},a=[];e.d(n,"a",function(){return o}),e.d(n,"b",function(){return a})},6919:function(t,n,e){"use strict";(function(t){Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var o=e("9fd5"),a={data:function(){return{commentId:"",mchId:"",detailInfo:{comment:{content:""},goods:{photo:""}}}},onLoad:function(t){this.commentId=t.commentId,this.mchId=t.mchId,this.getDetail()},methods:{getDetail:function(){var t=this;this.http.resquetData("/api/supplierComment/details","",{commentId:this.commentId,mchId:this.mchId},function(n){console.log(n," at pages\\data\\commentDetail.vue:52"),t.detailInfo=n.data})},formats:function(t){return(0,o.format)(t)},toReplay:function(){t.navigateTo({url:"replay?commentId="+this.commentId+"&mchId="+this.mchId})}}};n.default=a}).call(this,e("6e42")["default"])},"77b9":function(t,n,e){},f699:function(t,n,e){"use strict";var o=e("77b9"),a=e.n(o);a.a},fa60:function(t,n,e){"use strict";e.r(n);var o=e("233b"),a=e("1afd");for(var c in a)"default"!==c&&function(t){e.d(n,t,function(){return a[t]})}(c);e("f699");var i=e("2877"),u=Object(i["a"])(a["default"],o["a"],o["b"],!1,null,null,null);n["default"]=u.exports}},[["9bba","common/runtime","common/vendor"]]]);
});
require('pages/data/commentDetail.js');
__wxRoute = 'pages/data/commentDetail2';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/data/commentDetail2.js';

define('pages/data/commentDetail2.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/data/commentDetail2"],{7582:function(t,n,e){"use strict";var a=e("e39b"),o=e.n(a);o.a},"7a00":function(t,n,e){"use strict";(function(t){Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var a=e("9fd5"),o={data:function(){return{id:"",detailInfo:""}},onLoad:function(t){this.id=t.id},onShow:function(){this.getDetail()},methods:{getDetail:function(){var t=this;this.http.resquetData("/api/supplierQuestion","",{questionId:this.id},function(n){console.log(n," at pages\\data\\commentDetail2.vue:69"),t.detailInfo=n.data})},formats:function(t){return(0,a.format)(t)},toAnswer:function(n){t.navigateTo({url:"commentDetail3?id="+n.questionId})}}};n.default=o}).call(this,e("6e42")["default"])},"93f6":function(t,n,e){"use strict";var a=function(){var t=this,n=t.$createElement,e=(t._self._c,t.__map(t.detailInfo.mchAnswers,function(n,e){var a=t.formats(n.ctime);return{$orig:t.__get_orig(n),m0:a}}));t.$mp.data=Object.assign({},{$root:{l0:e}})},o=[];e.d(n,"a",function(){return a}),e.d(n,"b",function(){return o})},9452:function(t,n,e){"use strict";e.r(n);var a=e("93f6"),o=e("ed4f");for(var i in o)"default"!==i&&function(t){e.d(n,t,function(){return o[t]})}(i);e("7582");var u=e("2877"),r=Object(u["a"])(o["default"],a["a"],a["b"],!1,null,null,null);n["default"]=r.exports},e39b:function(t,n,e){},ed4f:function(t,n,e){"use strict";e.r(n);var a=e("7a00"),o=e.n(a);for(var i in a)"default"!==i&&function(t){e.d(n,t,function(){return a[t]})}(i);n["default"]=o.a}},[["ea99","common/runtime","common/vendor"]]]);
});
require('pages/data/commentDetail2.js');
__wxRoute = 'pages/data/commentDetail3';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/data/commentDetail3.js';

define('pages/data/commentDetail3.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/data/commentDetail3"],{1255:function(t,e,n){},"8fe2":function(t,e,n){"use strict";n.r(e);var a=n("ee1e"),i=n.n(a);for(var o in a)"default"!==o&&function(t){n.d(e,t,function(){return a[t]})}(o);e["default"]=i.a},b415:function(t,e,n){"use strict";var a=n("1255"),i=n.n(a);i.a},d372:function(t,e,n){"use strict";var a=function(){var t=this,e=t.$createElement;t._self._c},i=[];n.d(e,"a",function(){return a}),n.d(e,"b",function(){return i})},da57:function(t,e,n){"use strict";n.r(e);var a=n("d372"),i=n("8fe2");for(var o in i)"default"!==o&&function(t){n.d(e,t,function(){return i[t]})}(o);n("b415");var u=n("2877"),s=Object(u["a"])(i["default"],a["a"],a["b"],!1,null,null,null);e["default"]=s.exports},ee1e:function(t,e,n){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var a=i(n("9fd5"));function i(t){return t&&t.__esModule?t:{default:t}}var o={data:function(){return{id:"",detailInfo:"",content:""}},onLoad:function(t){this.id=t.id,this.getDetail()},methods:{getDetail:function(){var t=this;this.http.resquetData("/api/supplierQuestion","",{questionId:this.id},function(e){console.log(e," at pages\\data\\commentDetail3.vue:37"),t.detailInfo=e.data})},submitReplay:function(){if(""==this.content)return t.showToast({title:"回复不能为空",time:1500,icon:"none",mask:!0}),!1;var e=a.default.getMchId();this.http.resquetData("/api/supplierQuestion/instert","PUT",{questionId:this.id,mchId:e,content:this.content},function(e){console.log(e," at pages\\data\\commentDetail3.vue:54"),t.showToast({title:"回复成功",time:1500,mask:!0}),setTimeout(function(e){t.navigateBack({delta:1})},1500)})}}};e.default=o}).call(this,n("6e42")["default"])}},[["dac2","common/runtime","common/vendor"]]]);
});
require('pages/data/commentDetail3.js');
__wxRoute = 'pages/data/problem';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/data/problem.js';

define('pages/data/problem.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/data/problem"],{"33b0":function(t,n,e){},"4f72":function(t,n,e){"use strict";var i=e("33b0"),o=e.n(i);o.a},"6f3c":function(t,n,e){"use strict";e.r(n);var i=e("aa53"),o=e("b166");for(var a in o)"default"!==a&&function(t){e.d(n,t,function(){return o[t]})}(a);e("4f72");var r=e("2877"),u=Object(r["a"])(o["default"],i["a"],i["b"],!1,null,null,null);n["default"]=u.exports},8948:function(t,n,e){"use strict";(function(t){Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var i=e("9fd5"),o={data:function(){return{isShow:!1,problemList:{result:[{answers:{content:""}}]}}},onShow:function(){this.initProblem(1)},methods:{tabChange:function(t){this.isShow=1!=t,this.initProblem(t)},initProblem:function(t){var n=this;void 0===t&&(t=1),this.http.resquetData("/api/supplierQuestion/list","",{type:t,startDate:"",endDate:"",page:1,pageSize:10},function(t){console.log(t," at pages\\data\\problem.vue:113"),n.problemList=t.data})},formats:function(t){return(0,i.format)(t)},toAnswer:function(n){t.navigateTo({url:"commentDetail3?id="+n.id})},toDetail:function(n){t.navigateTo({url:"commentDetail2?id="+n.id})},delet:function(n){var e=this;t.showModal({content:"确认删除？",success:function(i){i.confirm?e.http.resquetData("/api/supplierQuestion/del","DELETE",{questionId:n.id},function(n){t.showToast({title:"删除成功",time:1500,mask:!0}),setTimeout(function(t){e.initProblem()},1500)}):i.cancel}})}}};n.default=o}).call(this,e("6e42")["default"])},aa53:function(t,n,e){"use strict";var i=function(){var t=this,n=t.$createElement,e=(t._self._c,t.__map(t.problemList.result,function(n,e){var i=t.formats(n.ctime);return{$orig:t.__get_orig(n),m0:i}}));t.$mp.data=Object.assign({},{$root:{l0:e}})},o=[];e.d(n,"a",function(){return i}),e.d(n,"b",function(){return o})},b166:function(t,n,e){"use strict";e.r(n);var i=e("8948"),o=e.n(i);for(var a in i)"default"!==a&&function(t){e.d(n,t,function(){return i[t]})}(a);n["default"]=o.a}},[["98c4","common/runtime","common/vendor"]]]);
});
require('pages/data/problem.js');
__wxRoute = 'pages/data/scenic';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/data/scenic.js';

define('pages/data/scenic.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/data/scenic"],{2001:function(e,t,a){"use strict";a.r(t);var s=a("8763"),i=a("389f");for(var n in i)"default"!==n&&function(e){a.d(t,e,function(){return i[e]})}(n);a("ba6f");var r=a("2877"),o=Object(r["a"])(i["default"],s["a"],s["b"],!1,null,null,null);t["default"]=o.exports},"389f":function(e,t,a){"use strict";a.r(t);var s=a("f16c"),i=a.n(s);for(var n in s)"default"!==n&&function(e){a.d(t,e,function(){return s[e]})}(n);t["default"]=i.a},"3b11":function(e,t,a){},8763:function(e,t,a){"use strict";var s=function(){var e=this,t=e.$createElement;e._self._c;e._isMounted||(e.e0=function(t){e.isMask=!0},e.e1=function(t){e.isMask=!0},e.e2=function(t){e.isMask=!1},e.e3=function(t){t.stopPropagation(),e.isMask=!0},e.e4=function(t){t.stopPropagation(),e.isShow2=!0},e.e5=function(t){t.stopPropagation(),e.isShow2=!1})},i=[];a.d(t,"a",function(){return s}),a.d(t,"b",function(){return i})},ba6f:function(e,t,a){"use strict";var s=a("3b11"),i=a.n(s);i.a},f16c:function(e,t,a){"use strict";(function(e){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var s,i=n(a("2981"));function n(e){return e&&e.__esModule?e:{default:e}}function r(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}var o=null,u={data:function(){return{TRANSACTION:"TRANSACTION",REFUND_VOUCHER:"REFUND_VOUCHER",REFUND:"REFUND",jiegou:"TRANSACTION",color:["#00CE9F","#F88692","#FFD069","#FFE8B4"],dataDetail:"",isMask:!1,isShow:!1,isShow2:!0,isProduct:!1,Ring:{series:[]},cWidth:"",cHeight:"",pixelRatio:1,tag:"TRANSACTION,VOUCHER",user:"order",nameArr:[],dataArr:[],titleArr:[],chartsInfo:"",lastArr:[],tagsArr:[{name:"交易指标",arr:[{name:"交易额",val:"TRANSACTION",isChoose:!1,isUse:!0},{name:"退款额",val:"REFUND",isChoose:!1,isUse:!0},{name:"购买量",val:"BUY",isChoose:!1,isUse:!0},{name:"验券量",val:"VOUCHER",isChoose:!1,isUse:!0},{name:"退券量",val:"REFUND_VOUCHER",isChoose:!1,isUse:!0}]},{name:"业务指标",arr:[{name:"访购率",val:"VISIT_BUY",isChoose:!1,isUse:!0},{name:"核销率",val:"ORDER_CHECK",isChoose:!1,isUse:!0},{name:"退款率",val:"REFUND_RATE",isChoose:!1,isUse:!0},{name:"笔单价",val:"",isChoose:!1,isUse:!1},{name:"客单价",val:"",isChoose:!1,isUse:!1}]},{name:"交易漏斗",arr:[{name:"浏览量",val:"",isChoose:!1,isUse:!0},{name:"访客数",val:"",isChoose:!1,isUse:!0}]}],choosedTag:[],rank:"1-100"}},onLoad:function(){s=this,this.cWidth=e.upx2px(750),this.cHeight=e.upx2px(500)},created:function(){this.getAllData(),this.getCharts(this.user)},mounted:function(){},methods:{chooseTag:function(e,t){if(1==e.isUse)if(e.isChoose=!e.isChoose,1==e.isChoose)this.lastArr.push(e.val);else{var a=this.lastArr.indexOf(e.val);this.lastArr.splice(a,1)}},submitTag:function(e){e.preventDefault(),e.stopPropagation(),this.isMask=!1,this.tag.length>1&&(this.tag=this.lastArr.join(""),this.getAllData())},changeRank:function(e){this.rank=e+1+"-100",this.isShow=!1,console.log(this.rank," at pages\\data\\scenic.vue:280")},changeUser:function(e){1==e?(this.isProduct=!1,this.user="order"):(this.isProduct=!0,this.user="orderGoods"),this.getCharts(),this.getAllData()},changeChart:function(e){this.tag=e,this.getCharts(),this.jiegou=e},showRing:function(e,t){var a;o=new i.default((a={$this:s,canvasId:e,type:"ring",fontSize:11,legend:!1,extra:{pie:{offsetAngle:-45,ringWidth:40*s.pixelRatio,lableWidth:15}},background:"#FFFFFF",pixelRatio:s.pixelRatio,series:t.series,animation:!0,width:s.cWidth*s.pixelRatio,height:s.cHeight*s.pixelRatio,disablePieStroke:!0,dataLabel:!0},r(a,"legend",!0),r(a,"enableScroll",!0),a))},touchRing:function(e){o.showToolTip(e,{format:function(t){return console.log(e," at pages\\data\\scenic.vue:342"),t.name+":"+t.data}})},getCharts:function(){var e=this;this.http.resquetData("/app/order/census/mchMapping","GET",{mchId:"126",pageBean:"",target:this.tag,regionTime:"2016-07-06 - 2019-06-06",type:this.user},function(t){for(var a in e.chartsInfo=t.data,e.Ring.series=[],t.data.censusTypeVOList)e.Ring.series.push(t.data.censusTypeVOList[a]);e.showRing("canvasRing",e.Ring)})},getAllData:function(){var e=this;this.http.resquetData("/app/order/census/mchTransaction","GET",{mchId:"126",pageBean:"",target:this.tag,regionTime:"2016-07-06 - 2019-06-06",type:this.user},function(t){for(var a in console.log(t," at pages\\data\\scenic.vue:361"),e.dataDetail=t.data,e.nameArr=[],e.titleArr=[],e.dataArr=[],e.dataDetail.censusTypeVOList)e.nameArr.push(e.dataDetail.censusTypeVOList[a].name);console.log(e.nameArr," at pages\\data\\scenic.vue:371");var s=e.tag.split(",");for(var i in s)switch(console.log(s[i].toLowerCase()," at pages\\data\\scenic.vue:377"),s[i].toLowerCase()){case"transaction":"交易额",e.titleArr.push("交易额");break;case"voucher":e.titleArr.push("验券量");break;case"refund_voucher":e.titleArr.push("退券量");break;case"visit_buy":e.titleArr.push("访购率");break;case"order_check":e.titleArr.push("核销率");break;case"refund_rate":e.titleArr.push("退款率");break;case"refund":e.titleArr.push("退款额");break;case"buy":e.titleArr.push("购买量");break;default:break}for(var n in e.dataDetail.censusTypeVOList)e.dataArr.push(e.dataDetail.censusTypeVOList[n].censusList);console.log(e.dataArr," at pages\\data\\scenic.vue:412")})}}};t.default=u}).call(this,a("6e42")["default"])}},[["6036","common/runtime","common/vendor"]]]);
});
require('pages/data/scenic.js');
__wxRoute = 'pages/new/new';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/new/new.js';

define('pages/new/new.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/new/new"],{"3f60":function(n,t,e){"use strict";var u=function(){var n=this,t=n.$createElement;n._self._c},a=[];e.d(t,"a",function(){return u}),e.d(t,"b",function(){return a})},"881f":function(n,t,e){"use strict";e.r(t);var u=e("3f60"),a=e("ab83");for(var r in a)"default"!==r&&function(n){e.d(t,n,function(){return a[n]})}(r);var o=e("2877"),c=Object(o["a"])(a["default"],u["a"],u["b"],!1,null,null,null);t["default"]=c.exports},ab83:function(n,t,e){"use strict";e.r(t);var u=e("b5c5"),a=e.n(u);for(var r in u)"default"!==r&&function(n){e.d(t,n,function(){return u[n]})}(r);t["default"]=a.a},b5c5:function(n,t,e){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var u={data:function(){return{}}};t.default=u}},[["aaae","common/runtime","common/vendor"]]]);
});
require('pages/new/new.js');
__wxRoute = 'pages/person/person';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/person/person.js';

define('pages/person/person.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/person/person"],{"43f8":function(n,t,e){"use strict";e.r(t);var o=e("738c"),a=e.n(o);for(var u in o)"default"!==u&&function(n){e.d(t,n,function(){return o[n]})}(u);t["default"]=a.a},"68da":function(n,t,e){},"738c":function(n,t,e){"use strict";(function(n){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o=a(e("9fd5"));function a(n){return n&&n.__esModule?n:{default:n}}var u={data:function(){return{storeInfo:""}},created:function(){this.getInfo()},methods:{getInfo:function(){var n=this;this.http.resquetData("/app/supplier","GET",{},function(t){console.log(t," at pages\\person\\person.vue:68"),n.storeInfo=t.data})},quit:function(){n.showModal({content:"确认退出登录？",success:function(t){t.confirm?(n.removeStorageSync(o.default.set_ocean_mch_storageSync),n.removeStorageSync(o.default.set_mch_choosed_sync),n.navigateTo({url:"../login/login"})):t.cancel}})}}};t.default=u}).call(this,e("6e42")["default"])},cbb8:function(n,t,e){"use strict";e.r(t);var o=e("f7da"),a=e("43f8");for(var u in a)"default"!==u&&function(n){e.d(t,n,function(){return a[n]})}(u);e("f6e1");var c=e("2877"),r=Object(c["a"])(a["default"],o["a"],o["b"],!1,null,null,null);t["default"]=r.exports},f6e1:function(n,t,e){"use strict";var o=e("68da"),a=e.n(o);a.a},f7da:function(n,t,e){"use strict";var o=function(){var n=this,t=n.$createElement;n._self._c},a=[];e.d(t,"a",function(){return o}),e.d(t,"b",function(){return a})}},[["2a44","common/runtime","common/vendor"]]]);
});
require('pages/person/person.js');
__wxRoute = 'pages/checking/search';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/checking/search.js';

define('pages/checking/search.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/checking/search"],{"1e23":function(e,t,n){"use strict";var o=function(){var e=this,t=e.$createElement;e._self._c;e._isMounted||(e.e0=function(t){e.isShow=!0})},a=[];n.d(t,"a",function(){return o}),n.d(t,"b",function(){return a})},"6a82":function(e,t,n){"use strict";n.r(t);var o=n("1e23"),a=n("6eea");for(var i in a)"default"!==i&&function(e){n.d(t,e,function(){return a[e]})}(i);n("73da");var c=n("2877"),r=Object(c["a"])(a["default"],o["a"],o["b"],!1,null,null,null);t["default"]=r.exports},"6eea":function(e,t,n){"use strict";n.r(t);var o=n("cd16"),a=n.n(o);for(var i in o)"default"!==i&&function(e){n.d(t,e,function(){return o[e]})}(i);t["default"]=a.a},"73da":function(e,t,n){"use strict";var o=n("d985"),a=n.n(o);a.a},cd16:function(e,t,n){"use strict";(function(e){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o=function(){return n.e("components/range-dtpicker/range-dtpicker").then(n.bind(null,"8096"))},a={components:{rangeDatePick:o},data:function(){return{isShow:!1,value:["2019-06-12","2019-06-12"],areaTime:"",areaTime2:"",nowTime:"",checkType:"",chooseId:""}},onLoad:function(){this.nowTime=this.getNowFormatDate(),this.value[0]=this.value[1]=this.nowTime,this.areaTime2=this.getNowFormatDate()+" - "+this.getNowFormatDate()},onShow:function(){this.chooseId=e.getStorageSync("choosedId"),this.checkType=e.getStorageSync("choosedName"),console.log(this.checkType," at pages\\checking\\search.vue:57")},onHide:function(){e.removeStorageSync("choosedName")},onUnload:function(){e.removeStorageSync("choosedName")},methods:{showchange:function(e){this.isShow=e},bindChange:function(e){console.log(e," at pages\\checking\\search.vue:71"),this.areaTime=e[0]+"至"+e[1],this.areaTime2=e[0]+" - "+e[1],console.log(this.areaTime2," at pages\\checking\\search.vue:74")},bindCancel:function(){},submitSearch:function(){if(""==this.checkType)return e.showToast({title:"请选择产品",icon:"none",time:1500}),!1;e.navigateTo({url:"detail2?id="+this.chooseId+"&areaTime="+this.areaTime2})},getNowFormatDate:function(){var e=new Date,t="-",n=e.getFullYear(),o=e.getMonth()+1,a=e.getDate();o>=1&&o<=9&&(o="0"+o),a>=0&&a<=9&&(a="0"+a);var i=n+t+o+t+a;return i},toChoose:function(){e.navigateTo({url:"historyList"})}}};t.default=a}).call(this,n("6e42")["default"])},d985:function(e,t,n){}},[["746c","common/runtime","common/vendor"]]]);
});
require('pages/checking/search.js');
__wxRoute = 'pages/checking/cancelReg';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/checking/cancelReg.js';

define('pages/checking/cancelReg.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/checking/cancelReg"],{"015d":function(n,t,e){},"83aa":function(n,t,e){"use strict";e.r(t);var a=e("b77a"),u=e.n(a);for(var c in a)"default"!==c&&function(n){e.d(t,n,function(){return a[n]})}(c);t["default"]=u.a},"848f":function(n,t,e){"use strict";e.r(t);var a=e("bc9b"),u=e("83aa");for(var c in u)"default"!==c&&function(n){e.d(t,n,function(){return u[n]})}(c);e("c05a");var o=e("2877"),r=Object(o["a"])(u["default"],a["a"],a["b"],!1,null,null,null);t["default"]=r.exports},b77a:function(n,t,e){"use strict";(function(n){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var e={data:function(){return{checkNum:""}},methods:{toSearch:function(){""==this.checkNum?n.showToast({title:"输入相关信息后验证",duration:1e3,mask:!0,icon:"none"}):n.navigateTo({url:"historyList2"})}}};t.default=e}).call(this,e("6e42")["default"])},bc9b:function(n,t,e){"use strict";var a=function(){var n=this,t=n.$createElement;n._self._c},u=[];e.d(t,"a",function(){return a}),e.d(t,"b",function(){return u})},c05a:function(n,t,e){"use strict";var a=e("015d"),u=e.n(a);u.a}},[["4e43","common/runtime","common/vendor"]]]);
});
require('pages/checking/cancelReg.js');
__wxRoute = 'pages/checking/detail';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/checking/detail.js';

define('pages/checking/detail.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/checking/detail"],{"1e00":function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var i={data:function(){return{}},created:function(){this.initList()},methods:{initList:function(){this.http.resquetData("/api/order/check/historyDetail","GET",{mchId:3,goodsId:11,param:"CHECK",regionTime:"2016-07-10 - 2019-08-08"},function(t){console.log(t," at pages\\checking\\detail.vue:70")})}}};n.default=i},2949:function(t,n,e){"use strict";var i=e("c5e0"),u=e.n(i);u.a},8582:function(t,n,e){"use strict";e.r(n);var i=e("f2d5"),u=e("e081");for(var c in u)"default"!==c&&function(t){e.d(n,t,function(){return u[t]})}(c);e("2949");var a=e("2877"),o=Object(a["a"])(u["default"],i["a"],i["b"],!1,null,null,null);n["default"]=o.exports},c5e0:function(t,n,e){},e081:function(t,n,e){"use strict";e.r(n);var i=e("1e00"),u=e.n(i);for(var c in i)"default"!==c&&function(t){e.d(n,t,function(){return i[t]})}(c);n["default"]=u.a},f2d5:function(t,n,e){"use strict";var i=function(){var t=this,n=t.$createElement;t._self._c},u=[];e.d(n,"a",function(){return i}),e.d(n,"b",function(){return u})}},[["c196","common/runtime","common/vendor"]]]);
});
require('pages/checking/detail.js');
__wxRoute = 'pages/checking/detail2';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/checking/detail2.js';

define('pages/checking/detail2.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/checking/detail2"],{"0b8f":function(t,e,n){"use strict";n.r(e);var i=n("5ab4"),o=n.n(i);for(var c in i)"default"!==c&&function(t){n.d(e,t,function(){return i[t]})}(c);e["default"]=o.a},"405d":function(t,e,n){"use strict";var i=function(){var t=this,e=t.$createElement,n=(t._self._c,t.detailInfo.time.slice(5,10)),i=t.detailInfo.time.slice(16,22);t._isMounted||(t.e0=function(e){t.isShow2=!0},t.e1=function(e){t.isShow=!0}),t.$mp.data=Object.assign({},{$root:{g0:n,g1:i}})},o=[];n.d(e,"a",function(){return i}),n.d(e,"b",function(){return o})},"5ab4":function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var i=n("9fd5"),o=c(n("9fd5"));function c(t){return t&&t.__esModule?t:{default:t}}var a=function(){return n.e("components/mutiChoose").then(n.bind(null,"a51c"))},u=function(){return n.e("components/range-dtpicker/range-dtpicker").then(n.bind(null,"8096"))},s={components:{choosType:a,rangeDatePick:u},data:function(){return{isShow2:!1,isShow:!1,value:["2019-06-12","2019-06-12"],detailInfo:"",checkSum:"",notCheckSum:"",checkId:"",time:"",productList:[]}},computed:{},onLoad:function(t){this.checkId=t.id,this.time=t.areaTime,this.initList(1),this.initChoose()},methods:{initList:function(t){var e=this,n="";n=1==t?"CHECK":"NOT_CHECK";var i=o.default.getMchId();this.http.resquetData("/app/order/check/historyDetail","GET",{mchId:i,goodsId:this.checkId,param:n,regionTime:this.time},function(t){console.log(t," at pages\\checking\\detail2.vue:113"),e.detailInfo=t.data,e.checkSum=t.data.checkSum,e.notCheckSum=t.data.notCheckSum})},initChoose:function(){var t=this,e=o.default.getMchId(),n=o.default.getSupplierId();this.http.resquetData("/app/order/check/mchProduct","GET",{mchId:e,supplierId:n},function(e){console.log(e," at pages\\checking\\detail2.vue:127"),t.productList=e.data,t.productList.forEach(function(e){t.$set(e,"selected",!1)}),console.log(t.productList," at pages\\checking\\detail2.vue:132")})},formats:function(t){return(0,i.format)(t)},showchange2:function(t){this.isShow=t},bindChange2:function(t){console.log(t," at pages\\checking\\detail2.vue:145"),this.detailInfo.time=t[0]+"至"+t[1],this.time=t[0]+" - "+t[1],console.log(this.time," at pages\\checking\\detail2.vue:148")},bindCancel2:function(){},colse:function(t){this.isShow2=t,console.log(this.isShow2," at pages\\checking\\detail2.vue:156")},submit:function(t){this.detailInfo.goodNameList[0].name=t.title,this.checkId=t.id,console.log(t," at pages\\checking\\detail2.vue:162")},toSearch:function(){this.initList(1)}}};e.default=s},"6a95":function(t,e,n){},c110:function(t,e,n){"use strict";n.r(e);var i=n("405d"),o=n("0b8f");for(var c in o)"default"!==c&&function(t){n.d(e,t,function(){return o[t]})}(c);n("cf7e");var a=n("2877"),u=Object(a["a"])(o["default"],i["a"],i["b"],!1,null,null,null);e["default"]=u.exports},cf7e:function(t,e,n){"use strict";var i=n("6a95"),o=n.n(i);o.a}},[["21cd","common/runtime","common/vendor"]]]);
});
require('pages/checking/detail2.js');
__wxRoute = 'pages/person/manage';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/person/manage.js';

define('pages/person/manage.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/person/manage"],{"302b":function(n,e,t){},"3cbc":function(n,e,t){"use strict";var a=function(){var n=this,e=n.$createElement;n._self._c},u=[];t.d(e,"a",function(){return a}),t.d(e,"b",function(){return u})},"7e4d":function(n,e,t){"use strict";var a=t("302b"),u=t.n(a);u.a},8303:function(n,e,t){"use strict";t.r(e);var a=t("aaa3"),u=t.n(a);for(var r in a)"default"!==r&&function(n){t.d(e,n,function(){return a[n]})}(r);e["default"]=u.a},aaa3:function(n,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var a={data:function(){return{staffList:[{name:"员工1",number:101111},{name:"员工1",number:101111},{name:"员工1",number:101111},{name:"员工1",number:101111}]}}};e.default=a},b802:function(n,e,t){"use strict";t.r(e);var a=t("3cbc"),u=t("8303");for(var r in u)"default"!==r&&function(n){t.d(e,n,function(){return u[n]})}(r);t("7e4d");var c=t("2877"),o=Object(c["a"])(u["default"],a["a"],a["b"],!1,null,null,null);e["default"]=o.exports}},[["bc5f","common/runtime","common/vendor"]]]);
});
require('pages/person/manage.js');
__wxRoute = 'pages/person/edit';__wxRouteBegin = true;__wxAppCurrentFile__ = 'pages/person/edit.js';

define('pages/person/edit.js',function(require, module, exports, window, document, frames, self, location, navigator, localStorage, history, Caches, screen, alert, confirm, prompt, fetch, XMLHttpRequest, WebSocket, webkit, WeixinJSCore, Reporter, print, WeixinJSBridge){
(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/person/edit"],{"2a18":function(n,t,e){"use strict";e.r(t);var u=e("c37d"),a=e("ba07");for(var r in a)"default"!==r&&function(n){e.d(t,n,function(){return a[n]})}(r);e("4b99");var c=e("2877"),o=Object(c["a"])(a["default"],u["a"],u["b"],!1,null,null,null);t["default"]=o.exports},"4b99":function(n,t,e){"use strict";var u=e("52b2"),a=e.n(u);a.a},"52b2":function(n,t,e){},ba07:function(n,t,e){"use strict";e.r(t);var u=e("ee34"),a=e.n(u);for(var r in u)"default"!==r&&function(n){e.d(t,n,function(){return u[n]})}(r);t["default"]=a.a},c37d:function(n,t,e){"use strict";var u=function(){var n=this,t=n.$createElement;n._self._c},a=[];e.d(t,"a",function(){return u}),e.d(t,"b",function(){return a})},ee34:function(n,t,e){}},[["a0fa","common/runtime","common/vendor"]]]);
});
require('pages/person/edit.js');
;(function(global) {
    __uni_launch_ready(function() {
        var entryPagePath = __wxConfig.entryPagePath.replace('.html', '')
        if (entryPagePath.indexOf('/') !== 0) {
            entryPagePath = '/' + entryPagePath
        }
        wx.navigateTo({
            url: entryPagePath,
            query: {},
            openType: 'appLaunch',
            webviewId: 1
        })
        __wxConfig.__ready__ = true
    })
})(this);

