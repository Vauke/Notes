# jQuery2.md
Friday, February 15th 2019, 17:19

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [jQuery2.md](#jquery2md)
* [筛选](#筛选)
	* [过滤](#过滤)
	* [查找](#查找)
	* [串联](#串联)
* [事件](#事件)
	* [jQuery完善的部分(原生js或许没有)](#jquery完善的部分原生js或许没有)
	* [页面加载](#页面加载)
	* [事件绑定](#事件绑定)
		* [处理](#处理)
			* [jQuery事件的别名](#jquery事件的别名)
		* [委派 delegate](#委派-delegate)
		* [切换](#切换)
		* [示例](#示例)
* [效果](#效果)
	* [基本](#基本)
	* [滑动](#滑动)
	* [淡入淡出](#淡入淡出)
* [ajax](#ajax)
	* [全局](#全局)
	* [常用](#常用)
	* [高级](#高级)
* [miscellaneous](#miscellaneous)
	* [表单序列化](#表单序列化)

<!-- /code_chunk_output -->

接[jQuery.md](jQuery.md)

# 筛选

选择器可以完成的功能, 筛选也提供相同函数 i.e. 选择器`:first` 筛选的函数`first()`

区别:

`$("div:first")`: 直接获得第一个div, 也只能获得一个

`$("div").first()`: 先获得所有div, 再取第一个, 还能去操作其他的div

## 过滤

1. `eq(index | -index)`
    * index: 从头往后数第index+1个元素, index=0,1...
    * -index: 从尾开始

```js
// <input type="button" value=" 选择最后一个div元素"  id="b3"/>
$("div").eq(-1).show().css("background-color", "#0f0");
```

2. `first()`

3. `last()`

4. `is()`: 判断是否成立 是true 否false

```js
// <input type="button" value=" 样式为hide div 下一个兄弟是否是span"  id="b6"/>
alert($("div.hide").next().is("span")); // true
```

5. `hasClass()`: 判断class是否是指定的class, 其实就是`is("."+class)`

```js
// <input type="button" value=" id=one div样式是否是one"  id="b4"/>
alert($("div#one").hasClass("one")); // true
```

6. `filter(expr)`: 过滤出与指定表达式expr匹配的元素集合

```js
// <input type="button" value=" 选择class为none的所有div"  id="b5"/>
$("div").filter(".none").show().css("background-color", "#0f0");
```

7. `not()`: 排除

```js
// <input type="button" value=" 选择样式为one div 子元素中没有title属性的div"  id="b8"/>
$("div.one").children().not("[title]").parent().css("background-color", "#0f0");
```

8. `has()`: 筛选出子元素

```js
// <input type="button" value=" 选择所有div中含有div的"  id="b7"/>
$("div").has("div").css("background-color", "#0f0");
```

9. `slice(start, end)`: 截取jQuery数组元素

```js
// <input type="button" value=" 选择所以号为3,4的div"  id="b9"/>
$("div").slice(3,5).css("background-color", "#0f0");
```

10. `map()`: 将jQuery对象数组中的每个元素单独又包装为多个jQuery对象

## 查找

    <A>
        <B>
            <C></C>
            <D></D>
            <E></E>
            <F></F>
        </B>
    </A>

1. `B.children([...])`: 获得所有子元素, CDEF

```js
// <input type="button" value=" 选择 id=two 子元素title=other 元素 "  id="b2"/>
$("#two").children("[title='other']").css("background-color", "#0f0");
```

2. `A.find(D)`: 从指定区域开始查询指定元素, D

```js
$(document).ready(function(){
    $("div").children("a").hide().prev("span").click(function() {
        $(this).nextAll().toggle().parent().siblings("div").find("a").hide();
    });
});

<div>
    <span>xxxx</span>
    <a>yyyy</a>
    <a>yyyy</a>
</div>
<div>
    <span>aaaa</span>
    <a>bbb</a>
    <a>bbbb</a>
</div>
```

3. `D.next()`: 获得下一个兄弟, E

```js
// <input type="button" value=" 选择 id=two 下一个兄弟"  id="b3"/>
$("#two").next().css("background-color", "#0f0");
```

4. `D.nextAll([expr])`: 后面的所有兄弟, EF

5. `E.prev()`: 上一个兄弟, D

6. `E.prevAll()`: 前面的所有兄弟, CD

7. `E.siblings()`: 所有兄弟, CDF

8. `E.parent()`: 父元素, B

9. `E.closest(A)`: 向上获得指定的父级元素A, 获得返回一个对象(1), 没有返回0

10. `C.nextUntil(E)`: 获得C到E之间的所有的C的兄弟, DE

11. `E.prevUntil(D)`: D

12. `E.parents()`: 获得所有父级元素, BA

## 串联

1. `A.add(B)`: 将A和B组合, 同时操作

```js
// 	  <input type="button" value=" 选择 id=one和two 的div"  id="b1"/>
$("#one").add("#two").css("background-color","#0f0");
```

2. `andSelf()`
    * `A.children().css(...)`: 对A的孩子做css操作
    * `A.children().andSelf().css(...)`: 自身和孩子**同时**css

```js
// <input type="button" value=" 选择id=one 所有的孩子，以及one自己"  id="b2"/>
$("#one").children().andSelf().css("background-color","#0f0");
```

3. `contents()`: 获得所有的子节点(子元素和文本)

4. `end()`: 回到最近的一个"破坏性"操作之前
    * `A.children().children().css(...)`: 操作孙子
    * `A.children().children().end().css(...)`: 操作孩子
    * `A.children().children().end().end().css(...)`: 操作A

```js
// <input type="button" value=" 选择id=one 所有的孩子，设置颜色为红，设置one自己为黄"  id="b3"/>
// $("#one").children().css("background-color","#f00").andSelf().css("background-color", "#ff0"); // 不能用andSelf()
$("#one").css("background-color", "#ff0").children().css("background-color", "#f00");
$("#one").children().css("background-color","#f00").end().css("background-color", "#ff0");
$("#one").children().css("background-color","#f00").parent().css("background-color", "#ff0");
```

不能用`andSelf()`, 最终会变为都是#ff0(黄色), andSelf()在自己和孩子后续操作一致的情况下使用, 否则使用end(), '隔离'开, 单独操作

# 事件

refer :point_right: [常见事件](./assets/常见事件.html)

## jQuery完善的部分(原生js或许没有)

以下基于:

```html
<div id="outerDiv" style="border:1px solid #f00;width:200px;height:200px">
    <div id="innerDiv" style="border:1px solid #00f;width:100px;height:100px"></div>
</div>

<br/>
<span id="showSpan"></span>
```

1. `focusin()`: 获得焦点 同focus()或 js: onfocus 区别: focusin()可以在父元素上检测子元素获得焦点的情况

```js
// 点击innerDiv可以触发事件, 事件的冒泡
$("#outterDiv").click(function() {
    alert("outterDiv");
});

// 点击innerDiv不会触发事件
$("#outterDiv").focus(function() {
    alert("outterDiv");
});

// 点击innerDiv可以触发事件
$("#outterDiv").focusin(function() {
    alert("outterDiv");
});
```

2. `focusout()`: 失去焦点 同blur()或 js: onblur 区别: focusout()可以在父元素上检测子元素失去焦点的情况

3. `mouseenter()`: 移入, 同 js: mouseover 区别: mouseenter()只在进入备选元素时才会触发事件

```js
var i= 0;

// 鼠标进入父元素和子元素时都会触发事件
$("#outterDiv").mouseover(function() {
    $("#showSpan").html(i++);
});

// 进入子元素innerDiv不触发事件
$("#outterDiv").mouseenter(function() {
    $("#showSpan").html(i++);
});
```

4. `mouseleave()`: 移出 同 js: mouseout 区别: mouseleave()只在离开被选元素时才会触发事件

## 页面加载

`ready(fn)`

```js
$(document).ready(function() {...});

// 简化写法
$(function() {...});
```

```js
window.onload = function() {
	alert("111");
};

window.onload = function() {
	alert("222");
};
```

执行结果: 222, 后面代码将覆盖前面的

```js
$(function(){
	alert("aaa");
});

$(function() {
	alert("bbb");
});
```

执行结果: aaa, bbb 可以重复使用$(fn() {...})

## 事件绑定

### 处理

1. `bind(type, fn)`: *deprecated since 3.0* 使用on替代 给当前对象绑定**一个**事件, A.bind("click", fn), 类似: A.click(fn)

2. `unbind(type)`: *deprecated since 3.0* 解除当前对象绑定的事件

```js
// bind
// <input id="h02" type="button" value="2可以点击多次" />
$("#h02").bind("click", function() {
	alert("run any times");
});

$("#h02").on("click", function() {
	alert("replace bind with on");
});

// unbind
// <input id="h03" type="button" value="3解绑2" />
$("#h03").click(function() {
	$("#h02").unbind("click");
});

$("#h03").on("click", function() {
	$("#h02").off("click");
});
```

3. `one(type, fn)`: 给当前对象绑定事件, 该事件最多被触发一次

```js
// 	<input id="h01" type="button" value="1只能点击一次，之后失效" />
$("#h01").one("click", function() {
	alert("only run once");
});
```

4. `on(...), off(...)`: refer :point_right: [使用on/off绑定/解绑事件](jQuery.md#事件的绑定)

5. `trigger(type)`: 在**每一个**匹配的元素上触发事件, 但会导致浏览器同名的[默认行为](#浏览器默认动作)的执行, A.trigger("submit") 类似: A.submit()

6. `triggerHandler(type)`: 同`trigger()`, 但不会导致浏览器默认行为执行和[事件冒泡](#事件冒泡)

#### jQuery事件的别名

off/unbind也可以解绑bind/on, 并且同一事件可以多次绑定调用不同函数(*一次点击多次触发*), 调用解绑函数将**解绑全部**

```js
// 点击一次h02按钮, 将依次出现run any times和run any times again
$("#h02").bind("click", function() {
	alert("run any times");
});

$("#h02").bind("click", function() {
	alert("run any times again");
});

// h02的两个事件将被同时解绑
$("#h03").on("click", function() {
	$("#h02").off("click");
});
```

使用别名:

```js
$("#h02").bind("click.first", function() {
	alert("run any times");
});

$("#h02").bind("click.again", function() {
	alert("run any times again");
});

// 只解绑h02的click.first事件
$("#h03").click(function() {
	$("#h02").unbind("click.first");
});

// 解绑h02的全部click事件
$("#h03").click(function() {
	$("#h02").unbind("click");
});
```

### 委派 delegate

1. `live(type, fn)`: *deprecated since 1.7* 使用on或delegate替代 同bind 绑定事件, 但**之后动态添加**的符合type的元素也将被绑定相同的事件

```js
// <input type="button" value="4 添加一个按钮，样式为myClass，且拥有相同的事件" class="myClass" />
var $myClass = $(".myClass");

$myClass.live("click", function() {
	$("body").append("<input type='button' value='a replica' class='myClass' />");
});

// 使用clone()也可以达到同样效果
$myClass.on("click", function() {
	$("body").append($(this).clone(true)); // 参数true表示, 绑定的事件也一同复制
});
```

2. `die(type)`: *deprecated since 1.7* 解绑事件, 之后动态添加的也被解绑

```js
// <input id="h05" type="button" value="5 解绑4"/>
$("#h05").on("click", function() {
	$(".myClass").die("click");
});
```

### 切换

1. `hover(over, fn)`: 两参数都是函数
	* 移入移出 简化版的 A.mouseover(over).mouseout(out) ==> A.hover(over, out)

```js
var $divMsg = $("#divMsg");

$("#e02").hover(function() {
	// mouseover
	$divMsg.html("mouse move in </br>");
}, function() {
	//mouseout
	$divMsg.append("<a href='#'>mouse move out</a> </br>");
});
```

2. `toggle(fn, fn, fn ...)`: *removed since 1.9* 执行click事件, 每点击一次, 执行一个fn(不同于[一次点击多次执行](#jQuery事件的别名))

```js
$("#e01").toggle(function() {
	alert("aaa");
}, function() {
	alert("bbb");
})
```

以下两个toggle的api属于jQuery的效果范畴

3. `toggle(bool)`: bool必需, 切换隐藏/显示的开关

```js
$("span").toggle(bool);

// 相当于
if (bool) {
	$("span").show();
} else {
	$("span").hide();
}

// 也即
toggle(true) == show()
toggle(false) == hide()
```

4. `toggle([speed], [easing], [fn])`: 切换元素显示/隐藏, 类似toggle(bool), 但是以时间过渡, 还可以执行函数
	* speed: 隐藏/显示 效果的速度. 默认是 "0"毫秒. 可能的值: 数值或slow, normal, fast
	* easing: (Optional) 用来指定切换效果, 默认是"swing", 可用参数"linear"
	* fn: 在动画完成时执行的函数, 每个符合条件的元素各自执行一次

以下动画隐藏/显示三个span

```js
$(function() {
	// 直接隐藏
	$("span").toggle();

	// 过渡隐藏
	$("span").toggle("slow"); // 这里隐藏
	$("span").toggle(1000); // 再次调用了toggle, 这里显示
});

<span>test1</span>
<span>test2</span>
<span>test3</span>
```

以下动画为: test1,test2,test3同时开始动画并在1秒后隐藏, 接着alert三次"aaa"

```js
$(function() {
	$("span").toggle(1000, function() { // 去除1000这个时间参数, 3个span将迅速隐藏/显示
		alert("aaa");
	});
});

<span>test1</span>
<span>test2</span>
<span>test3</span>
```

### 示例

以下基于[文字提示示例](assets/文字提示.html)

以下代码在第一次鼠标移动时能正常显示, 后续提示就会变成aaa, 由于有两个元素绑定事件, 不能简单的提取公共变量解决bug

```js
$(function(){
	$(".mytooltip").hover(function(event) {
		// 获得自带提示
		var $title = $(this).attr("title");

		// 去除自带提示
		$(this).removeAttr("title");
		// 创建新的提示对象
		var $new = $("<div id='tooltip'>aaa</div>");
		//添加内容并显示
		$new.text($title).appendTo("body").show();
		//设置坐标, 获得鼠标坐标
		$new.offset({top:event.pageX, left:event.pageY});
	}, function() {
		// 移除
		$("#tooltip").remove();
	}).mousemove(function(event) {
		// 跟着鼠标滑动
		$("#tooltip").offset({top: event.pageX, left: event.pageY});
	});
});
```

使用缓存(事件中, 绑定给对象的数据)改进

```js
$(".mytooltip").hover(function(event) {
	// mouseover
	// 获得绑定数据, 第一次为空 title为undefined
	var title = $(this).data("mytitle");

	// 第一次
	if (!title) {
		// 获得自带提示 attr()获得的是值 string
		var title = $(this).attr("title");
		// 去除自带提示
		$(this).removeAttr("title");
		// 绑定数据
		$(this).data("mytitle", title);
	}

	// 创建新的提示对象
	var $new = $("<div id='tooltip'>aaa</div>");
	//添加内容并显示
	$new.text(title).appendTo("body").show();
	//设置坐标, 获得鼠标坐标
	$new.offset({top:event.pageX, left:event.pageY});
}, function() {
	// mouseout
	// 移除
	$("#tooltip").remove();
}).mousemove(function(event) {
	// 跟着鼠标滑动
	$("#tooltip").offset({top: event.pageX, left: event.pageY});
});
```

`data()`: 在*元素*上存放/读取数据

```js
// 存取值
$("div").data("blah");  // undefined
$("div").data("blah", "hello");  // blah设置为hello
$("div").data("blah");  // hello
$("div").data("blah", 86);  // 设置为86
$("div").data("blah");  //  86
$("div").removeData("blah");  //移除blah
$("div").data("blah");  // undefined

// 存取键值对数据
$("div").data("test", { first: 16, last: "pizza!" });
$("div").data("test").first  //16;
$("div").data("test").last  //pizza!;
```

**event对象** : 代表事件本身

`event.pageX`: 鼠标相对于文档的左边缘的位置

`event.pageY`: 鼠标相对于文档的顶部边缘的位置

`event.data`: *绑定给事件* 当前执行的函数被绑定的时候，包含可选的数据传递给jQuery.fn.bind [绑定给事件的数据](jQuery.md#事件的绑定)

以下基于[图片预览示例](assets/图片预览.html)

```js
$(function() {
	// 似乎必须要加偏移 否则预览图一直闪烁
	var x = 10;
	var y = 20;
	var src;

	$("a").not(".tooltip").hover(function(e) {
		src = this.href;

		this.href = "";

		var $div = $("<div id = 'tooltip'><img src='"+src+"'/></div>");

		$("body").append($div);

		$("#tooltip").css({
			// 使用css() 坐标后可以加 px
			top : e.pageY + y + "px",
			left : e.pageX + x + "px"
		}).toggle(1000);
	}, function() {
		this.href = src;
		// 不要用hide() 占空间, 还影响显示
		$("#tooltip").remove();
	}).mousemove(function(e){
		$("#tooltip").offset({
			// 使用offset() 坐标后不要加 px 否则预览图卡顿
			top : e.pageY + y,
			left : e.pageX + x
		});
	});
});
```

# 效果

## 基本

1. `show([speed],[easing],[fn])`: 显示被隐藏的匹配元素
	* speed: 可选, "slow","normal", or "fast"或表示动画时长的毫秒数值(如：1000)
	* easing: (Optional) 用来指定切换效果, 默认是"swing", 可用参数"linear"
	* fn: 在动画完成时执行的函数, 每个符合条件的元素各自执行一次

2. `hide()`: 隐藏已显示的匹配元素, 参数同show()

3. `toggle()`: 同show() hide() refer :point_right: [事件>事件绑定>切换>toggle](#切换)

## 滑动

*滑动只改变选定元素的高度*

参数都同show()

1. `slideDown([speed],[easing],[fn])`: 向下滑动元素

2. `slideUp([speed],[easing],[fn])`: 向上滑动元素

3. `slideToggle([speed],[easing],[fn])`: 切换

## 淡入淡出

*通过改变选定元素的透明度进行显示或隐藏元素*

参数都同show()

1. `fadeIn([speed],[easing],[fn])`: 显示

2. `fadeOut([speed],[easing],[fn])`: 隐藏

3. `fadeToggle([speed],[easing],[fn])`: 切换

4. `fadeTo([speed],opacity,[easing],[fn])`: 指定透明度
	* opacity: 一个0至1之间表示透明度的数字 数字越大透明度越低

# ajax

## 全局

jQuery底层ajax实现

`$.ajax(url [, settings ] )`: 一般使用`$.ajax(settings)`
	$.ajax({
		"url": url,
		"data": params,
		"type(或method)": method (默认get),
		"success": 请求成功时的回调函数 `function(data[, responseStatus, jqXHR]) {...}`,
		"error": 请求失败时的回调函数 `function() {alert("服务器繁忙, 请稍后重试")}`,
		"dataType": The type of data expected from the server  json | xml | html | text | script 会将数据转为的指定类型,
		...
	})

## 常用

1. `load(url [, data ] [, complete ])`: 使用GET, 有参数时(有参数data)使用POST 必须由jQuery对象调用 Load data from the server and place the returned HTML into the matched element
	* url: 发送的请求地址
	* data: 待发送的 Key/value 参数 map形式
	* complete: 请求*完成*时的回调函数
		* responseText: 服务器响应的数据data 为string类型 `var jsonData = eval("("+data+")")"; jsonData.message // 获得数据`
		* responseStatus: success|error
		* jqXHR: XMLHttpRequest对象 XHR

2. `$.get(url [, data ] [, success ] [, dataType ] )`: 全局函数 使用GET
	* dataType: The type of data expected from the server  json | xml | html | text | script 会将数据转为的指定类型

3. `$.post()`: 同GET 使用POST

## 高级

1. `$.getJSON(url,[data],[callback])`: 使用GET 可以执行js跨域请求, js默认是不能跨域的
	* url: 发送的请求地址
	* data: 待发送的 Key/value 参数 map形式
	* callback: 载入*成功时*的回调函数

2. `$.getScript()`: 使用GET 动态加载并执行js文件 参数同getJSON

# miscellaneous

## 表单序列化

1. `serialize()`: Encode a set of form elements as a string for submission

```js
<form>
  <select name="single">
    <option>Single</option>
    <option>Single2</option>
  </select>
  <input type="checkbox" name="check" value="check1" id="ch1">
  <label for="ch1">check1</label>
  <input type="checkbox" name="check" value="check2" checked="checked" id="ch2">
  <label for="ch2">check2</label>
</form>

function showValues() {
    var str = $( "form" ).serialize();
    $( "#results" ).text( str );
 }

 $( "input[type='checkbox'], input[type='radio']" ).on( "click", showValues );
 $( "select" ).on( "change", showValues );
 showValues();

 // results
single=Single&check=check1&check=check2
 ```

2. `serializeArray()`: Encode a set of form elements as an array of names and values

```js
<form>
  <div><input type="text" name="a" value="1" id="a"></div>
  <div><input type="text" name="b" value="2" id="b"></div>
</form>

$( "form" ).submit(function( event ) {
  console.log( $( this ).serializeArray() );
  event.preventDefault();
});

// results
[
  {
    name: "a",
    value: "1"
  },
  {
    name: "b",
    value: "2"
  }
]
```

## 事件冒泡

事件冒泡: 子元素事件执行时, 一并触发了父元素的相同事件

[事件冒泡和事件传播的区别](https://www.cnblogs.com/painsOnline/p/5106310.html)

阻止事件冒泡:

```js
$(function() {
	$("#outterDiv").click(function() {
		alert("outterDiv");
	});

	$("#innerDiv").click(function(event) {
		// way 1
		// return false;

		// way2
		event.stopPropagation();
	});
});
```

`event.stopPropagation()`: 防止事件冒泡到DOM树上，也就是不触发的任何前辈元素上的事件处理函数

## 浏览器默认动作

默认行为: 比如a标签点击就会跳转页面, 这个就是a标签的默认行为, 自定义行为时, 就需要阻止默认行为

`event.preventDefault()`

```js
$("a").click(function(event) {
	// way1
	// return false;

	// way2
	event.preventDefault();
});
