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
		* [内容过滤选择器 :star:](#内容过滤选择器-star)
		* [可见性过滤选择器 :star:](#可见性过滤选择器-star)
		* [属性过滤选择器 :star:](#属性过滤选择器-star)
		* [子元素过滤选择器](#子元素过滤选择器)
		* [表单过滤选择器](#表单过滤选择器)
		* [表单对象过滤选择器 :star:](#表单对象过滤选择器-star)

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

1. `ancestor descendant`: 以空格分隔 获得ancestor元素**内部所有**的descendant元素, 不能涉及多少层
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

1. `.on( events [, selector ] [, data ], handler(eventObject) )` 绑定事件
	* `events`: 一个或多个空格分隔的事件类型
	* `selector`: 选择器
	* `data`: 当一个事件被触发时, 要传递给事件处理函数的`event.data`
	* `handler(eventObject)`: 事件被触发时, 执行的函数. 若该函数只是return false的话, 那么该参数位置可以直接简写成 false.

2. `.off( events [, selector ] [, handler(eventObject) ] )`: 移除由.on()绑定的事件
	* `events`: 一个或多个空格分隔的事件类型
	* `selector`: 一个选择器, 当绑定事件处理程序时最初传递给 .on()的那个.
	* `handler(eventObject)`

### 内容过滤选择器 :star:

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

### 可见性过滤选择器 :star:

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

2. `visible`:

```js
// <input type="button" value=" 选取所有可见的div元素"  id="b1"/>
$("div:visible").css("background-color", "#0f0");
```

### 属性过滤选择器 :star:

### 子元素过滤选择器

### 表单过滤选择器

### 表单对象过滤选择器 :star: