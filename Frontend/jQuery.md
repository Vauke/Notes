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

### 内容过滤选择器 :star:

### 可见性过滤选择器 :star:

### 属性过滤选择器 :star:

### 子元素过滤选择器

### 表单过滤选择器

### 表单对象过滤选择器 :star:
