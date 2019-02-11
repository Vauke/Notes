# jQuery.md
Sunday, February 10th 2019, 20:46

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [jQuery.md](#jquerymd)
* [Syntax](#syntax)
	* [selectors](#selectors)
	* [jQuery对象和DOM对象的相互转化](#jquery对象和dom对象的相互转化)

<!-- /code_chunk_output -->

# Syntax

```js
if ($ === jQuery) {
    alert("true"); // true
}
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

selector1, selector2...selectorn 将多个选择器的结果添加到同一个数组中

### 层级选择器

### 基本过滤

### 内容过滤 :star:

### 可见性过滤 :star:

### 属性过滤 :star:

### 子元素过滤

### 表单过滤

### 表单对象过滤 :star:
