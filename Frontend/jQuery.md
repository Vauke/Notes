# jQuery.md
Sunday, February 10th 2019, 20:46

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [jQuery.md](#jquerymd)
* [Syntax](#syntax)
	* [jQuery对象和DOM对象的相互转化](#jquery对象和dom对象的相互转化)
	* [selectors](#selectors)
		* [基本选择器 :star:](#基本选择器-star)
		* [层级选择器](#层级选择器)
		* [基本过滤选择器](#基本过滤选择器)
			* [事件的绑定:](#事件的绑定)
		* [内容选择器 :star:](#内容选择器-star)
		* [可见性选择器 :star:](#可见性选择器-star)
		* [属性选择器 :star:](#属性选择器-star)
		* [子元素选择器](#子元素选择器)
		* [表单选择器](#表单选择器)
		* [表单对象选择器 :star:](#表单对象选择器-star)
	* [属性](#属性)
	* [CSS](#css)
		* [class](#class)
		* [位置](#位置)
	* [HTML代码 / 文本 / 值](#html代码-文本-值)
	* [文档处理](#文档处理)
		* [内部插入](#内部插入)
		* [外部插入](#外部插入)
		* [删除](#删除)
		* [复制](#复制)
		* [替换](#替换)
		* [包裹](#包裹)

<!-- /code_chunk_output -->

# Syntax

```js
if ($ === jQuery) {
    alert("true"); // true
}

window.onload = function() {...}
$(document).ready(function() {...}) // same as window.onload
```

## jQuery对象和DOM对象的相互转化

使用`$(dom对象)`的方式即可将dom对象转换为jQuery对象, 习惯上将jQuery的变量名前加上 **$**, username2一般写作$username2

其实jQuery对象内部使用**数组**存放所有数据, 因此可以通过指定其索引的方式来获得DOM对象, 还可以使用jQuery对象的get(index)来获得DOM对象

example:

```js
<script src="../js/jquery-1.11.3.js"></script>
<body>
    <input type="text" id="username" value="vauke"/> <br/>

<script>
    alert(document.getElementById("username").value); // native js way
    alert($("#username").val()) // jquery way

    // DOM ==> jQuery
    var username = document.getElementById("username");
    var username2 = $(username);
    alert(username2.val());

    // jQuery ==> DOM
    var username3 = username2[0];
    var test = username2[1]; // undefined  因为这里的jQuery对象只存了一个DOM对象

    var username4 = username2.get(0); // same as username3
    alert(username3.value);
</script>
</body>
```

`val()`: 表示获取jQuery对象的值
`val("xxx")`: 设置值

## selectors

### 基本选择器 :star:

same as the selectors in CSS

1. `#id`
2. 标签
3. `.classname`
4. `selector1, selector2...selectorn`: 将多个选择器共同作用的结果添加到数组中

`$("xxx").click(function() {...})`: 定义元素的onclick事件, jQuery中, 一般将原生js事件前的`on`去掉, 改成了方法

`$("xxx").css("属性", "值")`: 定义元素的css

`$("*")`: 选择所有元素

example:

```js
$(document).ready(function() {
   // <input type="button" value="选择 id为 one 的元素." id="btn1"/>
   $("#btn1").click(function() {
   // $("#one, span, .mini").css("background-color", "red");
   var $one = $("#one, span, .mini");
   $one.css("background-color", "red");
 });
});
```

### 层级选择器

1. `ancestor descendant`: 以空格分隔 获得ancestor元素**内部所有**的descendant元素, **能向下跨多个层级**
2. `parent>child`: 获得parent元素**内部的所有**child子元素, 只涉及两层
3. `prev+next`: 获得prev元素*之后*的**第一个**出现的next元素, 只在同一层, 兄弟
4. `prev~siblings`: 获得prev元素*之后*的**所有**的siblings元素, 只在同一层, 兄弟

### 基本过滤选择器

格式都是: `:关键字`

1. `:first`: 索引为0的元素

```js
// <input type="button" value="选择第一个div元素." id="btn1"/>
$("#btn1").click(function() {
  $("div:first").css("background-color", "#0f0");
})
```

2. `:last`: 索引最大的元素

3. `:eq(index)`

```js
// <input type="button" value="选择索引值等于3的div元素." id="btn6"/>
$("#btn6").click(function() {
	$("div:eq(3)").css("background-color", "#0f0");
});
```

4. `:gt(index)`

5. `:lt(index)`

6. `:even`: 指索引为偶数, 从0开始, 例如查找表格的1,3,5行, 索引为0,2,4

```js
// <input type="button" value="选择索引值为偶数 的div元素." id="btn4"/>
$("#btn4").click(function() {
	$("div:even").css("background-color", "#0f0");
});
```

7. `:odd`: 索引为奇数

8. `:not(basic selector)`: 去除与给定选择器匹配的元素

```js
// <input type="button" value="选择class不为one的 所有div元素." id="btn3"/>
$("#btn3").click(function() {
	$("div:not('.one')").css("background-color", "#0f0");
});
```

9. `:header`: 获得所有的标题元素, `<h1>...<h6>`

```js
// <input type="button" value="选择所有的标题元素." id="btn9"/>
  $("#btn9").click(function() {
	$(":header").css("background-color", "#0f0");
});
```

10. `:animated`: 获得所有动画

```js
// <input type="button" value="选择当前正在执行动画的所有元素." id="btn10"/>
  $("#btn10").click(function() {
	$(":animated").css("background-color", "#0f0");
});
```

11. `:focus`: 获得焦点

模拟html表单的placeholder属性:

```js
// <input type="text" value="请输入账号" defaultValue="请输入账号" />
  $("input[type='text']").on("blur focus", function() { // 绑定事件
	var dv = $(this).attr("defaultValue"); // 获得自定义属性defaultValue的值
	if ($(this).is(":focus")) { // 获得焦点
	  if ($(this).val() == dv) {
		$(this).val("");
	  }
	} else {
	  if ($(this).val().length == 0) {
		$(this).val(dv)
	  }
	}
});
```

`attr("attribute name")`: 获得jQuery对象的对应属性

#### 事件的绑定:

1. `on( events [, selector ] [, data ], handler(eventObject) )`: 提供绑定事件所需要的所有功能
	* `events`: 一个或多个空格分隔的事件类型
	* `selector`: 选择器
	* `data`: 当一个事件被触发时, 要传递给事件处理函数的`event.data`
	* `handler(eventObject)`: 事件被触发时, 执行的函数. 若该函数只是return false的话, 那么该参数位置可以直接简写成 false.

2. `off( events [, selector ] [, handler(eventObject) ] )`: 移除由on()绑定的事件
	* `events`: 一个或多个空格分隔的事件类型
	* `selector`: 一个选择器, 当绑定事件处理程序时最初传递给on()的那个.
	* `handler(eventObject)`

### 内容选择器 :star:

1. `:empty`: 不含有子元素 标签体为空
	* `<div></div>`: 标签体为空

```js
// <input type="button" value="选取不包含子元素(或者文本元素)的div空元素." id="btn2"/>
$("#btn2").click(function() {
  $("div:empty").css("background-color", "#0f0");
});
```

2. `:parent`: 匹配包含子元素或文本的元素, 标签体不为空, 和`:empty`相反

```js
// <input type="button" value="选取含有子元素(或者文本元素)的div元素." id="btn4"/>
$("#btn4").click(function() {
  $("div:parent").css("background-color", "#0f0");
});
```

3. `:has(selector)`: **当前**元素是否含有指定的子元素

```js
// <input type="button" value="选取含有class为mini元素 的div元素." id="btn3"/>
$("#btn3").click(function() {
  $("div:has('.mini')").css("background-color", "#0f0");
});
```

4. `:contains(text)`: 匹配标签体有指定的文本的元素

```js
// <input type="button" value="选取含有文本“di”的div元素." id="btn1"/>
$(document).ready(function() {
  $("#btn1").click(function() {
    $("div:contains('di')").css("background-color", "#0f0");
  });
});
```

### 可见性选择器 :star:

1. `hidden`: 特指 `<xxx style="display:none;...">`和`<input type="hidden">`

```js
// <input type="button" value=" 选取所有不可见的元素, 利用 jQuery 中的 show() 方法将它们显示出来"  id="b2"/>
$(":hidden").show();

// <input type="button" value=" 选取所有的文本隐藏域, 并打印它们的值"  id="b3"/>
// 使用each()遍历
$("input:hidden").each(function() {
  alert($(this).val());
})

// 使用全局each遍历
$.each($("input:hidden"), function() {
  alert($(this).val());
});
```

`jQuery对象.each(function)`: 遍历元素并执行function

`$.each(jQuery对象, function(index, DOMElement))`: 这个是全局函数, 多了两个参数, `$.函数`都是全局函数

	* `index`: 索引
	* `DOMElement`: 当前的遍历对象, 即this

以上示例可改为:

```js
// 使用全局each遍历
$.each($("input:hidden"), function(index, ele) {
  alert(ele.value);
});
```

2. `visible`

```js
// <input type="button" value=" 选取所有可见的div元素"  id="b1"/>
$("div:visible").css("background-color", "#0f0");
```

### 属性选择器 :star:

1. `[attribute]`: 获得指定属性名的元素

2. `[attribute=value]`: 获得指定属性名和值的元素

```js
// <input type="button" value="选取 属性title值等于“test”的div元素." id="btn2"/>
$("div[title='test']").css("background-color", "#0f0");
```

3. `[attribute!=value]`: 获得指定属性名但值不为value的元素, *没有该属性的也会被选中*

4. `[属性选择器1][属性选择器2][属性选择器3]...`: 复合选择器, 多个条件同时成立

```js
// <input type="button" value="组合属性选择器,首先选取有属性id的div元素，然后在结果中 选取属性title值 含有“es”的元素." id="btn7"/
$("div[id][title*='es']").css("background-color", "#0f0");
```

5. `[attribute^=value]`: 获得属性值是以value开头的元素

6. `[attribute$=value]`: 获得属性值是以value结尾的元素

7. `[attribute*=value]`: 获得属性值含有value的元素

### 子元素选择器

1. `:nth-child(index)`: 获得第index个孩子, index从1开始

```js
// <input type="button" value="选取每个class为one的div父元素下的第2个子元素." id="btn1"/>
$("div.one :nth-child(2)").css("background-color", "#0f0");
$("div[class='one'] :nth-child(2)").css("background-color", "#0f0");
```

元素无多余条件时, `:` 前不用加空格 i.e.`$("div:nth-child(2)")`, 使用id等选择器时需要加空格

对比:

`$("div.one")`: 自己的class="one"的div元素
`$("div .one")`: 有层级关系, div下的所有class="one"的元素 参照[层级选择器](#层级选择器)

2. `:first-child`: 获得第一个

3. `:last-child`: 最后一个

4. `:only-child`: 唯一

### 表单选择器

1. `:input`: 所有的表单元素 `<input> <select> <textarea> <button>`
2. `:text`: `<input type='text'>`
3. `:password`: `<input type='password'>`
4. `:radio`: `<input type='radio'>`
5. `:checkbox`
6. `:submit`
7. `:image`
8. `:reset`
9. `:file`
10. `:button`: 匹配`<button>`和`<input type='button'>`
11. `:hidden`

### 表单对象选择器 :star:

1. `:enabled`

```js
// <button id="btn1">对表单内 可用input 赋值操作.</button>
$("input:enabled").val("test");
```

2. `:disabled`: `<xxx disabled='disabled'>`或`<xxx disabled=''>`或`<xxx disabled>`

3. `:checked`: radio 或 checkbox

```js
// <button id="btn3">获取多选框选中的个数.</button>
$("input[name='newsletter']:checked").each(function() {
	alert(this.value);
})
```

注意这里的`:`前不能加空格

4. `:selected`: select > option

```js
// <button id="btn4">获取下拉框选中的内容.</button>
$.each($("option:selected"), function() {
	alert($(this).val());
	$("#selectDivId").append(this.value); // 将内容写入div
});
```

选择器间可以嵌套: `$("li:gt(4):not(:last)");`

## 属性

1. `attr(name)`: 获得属性`name`的值

2. `attr(key, value)`: 设置属性`key`的值为`value`

3. `attr(prop)`: 给多个属性赋值, prop格式为json map {k:v, k:v, ...}

4. `removeAttr(name)`: 删除属性`name`

## CSS

### class

以下基于: `<xxx class="name1 name2 name3 ...">`

`addClass(name)`: 追加一个class

`removeClass(name)`: 删除

`toggleClass(name)`: 添加和删除之间切换, 如果有就删除, 没有就添加

```js
$("#button").click(function() {
	$("[name='username']").toggleClass("textClass");
});

<input type="text" name="username" class="textClass" value="vauke" />
```

以下基于: `<xxx style="key:value; key:value ...">`

`css(key)`: 获得指定名称的css的值

`css(key, value)`: 设置值

`css(prop)`: 设置一组值

```js
$div.css("border", "solid").css("width", "200px").css("height", "300px");

// same
$div.css({
	"border": "1px solid",
	"height": "200px",
	"width": "300px"
});
```

### 位置

`offset()`: 获得坐标

`offset(...)`: 设置坐标 `$div.offset({"top":0, "left":0});`

`scrollTop(...)`: 垂直滚动条滚动的距离

`scrollLeft(...)`: 水平滚动条滚动的距离


## HTML代码 / 文本 / 值

`val()`: 获取`value`属性值, 如果标签没有`value`属性, 则获取标签体的内容

`html()`: 获得html代码, 可能会含有标签

`text()`: 同html(), 但只获得文本值


`val(...)`: 设置值

`text(...)`: 参数为啥就写啥, `text("<a href="#">test</a>")` // 显示文本`<a href="#">test</a>`

`html(...)`: 同text(...), 但识别html标签, 会转换

## 文档处理

### 内部插入

针对标签体的插入

`A.append(B)`: 将B追加到A`内部`的后面 `$("#love").append($("#tj"));`

`A.prepend(B)`: 是prepend, 将B追加到A`内部`前面 `$("#love").prepend($("#tj"));`

`A.appendTo(B)`: 将A追加到B`内部`后

`A.prependTo(B)`: 将A追加到B`内部`前

### 外部插入

针对同级元素的插入

`A.after(B)`: 将B插入到A后面

`A.before(B)`: 前面

`A.insertAfter(B)`

`A.insertBefore(B)`

### 删除

`empty()`: 清空`标签体`

`remove()`: 删除当前对象绑定的事件和数据等, 但对象本身**不会**被删除

`detach()`: 删除当前对象, 但绑定的事件和数据还可用

```js
var $city = $("#city");

// 绑定事件
$city.click(function() {
	alert("aaa");
});

// 绑定数据
$city.data("username", "vauke"); // key value

// 1. 使用remove()删除
$city.remove(); // 移除绑定的事件和数据, 因此再点击时不会alert aaa

// 获得绑定数据
alert($city.data("username")); // undefined, 获得数据时, 数据已经清空

// 2. 使用detach()删除
$city.detach(); // 绑定的事件和数据还存在, 只是$city中的对象不存在了

// 获得绑定数据
alert($city.data("username")); // vauke, 获得数据时, 数据还存在

alert($city.data("username")); // vauke
```

### 复制

`clone([even[, deepEven]])`: clone对象, even用于决定事件的处理函数是否也被复制, 默认为false, `clone(true)`:效果和原来完全一样, `clone(false)`: 没有事件, 其余一样

### 替换

`A.replaceWith(B)`: 用B替换所有的A

`A.replaceAll(B)`: 用A替换所有的B

### 包裹

`A.wrap(B)`: 使用B分别包裹每个A, B成为A的父元素 包含多个B

	<B><A></A></B>
	<B><A></A></B>

`A.wrapAll(B)`: 使用B将所有A包裹 一个B

	<B>
		<A></A>
		<A></A>
 		...
	</B>

`A.wrapInner(B)`: 使用B将**每一个**A的**标签体**进行包裹

	<A><B>A的标签体</B></A>

`A.unwrap(B)`: 将A的父元素删除
