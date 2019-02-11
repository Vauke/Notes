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

## selectors

just like selectors in CSS

example:

```js
<script src="../js/jquery.js"></script>
<body>
    <input type="text" id="username" value="vauke"/> <br/>

<script>
    alert(document.getElementById("username").value); // native js way
    alert($("#username").val()) // jquery way

    // convert DOM object to jQuery object
    var username = document.getElementById("username");
    var username2 = $(username);
    alert(username2.val());

    // convert jQuery Object to DOM Object
    var username3 = username2[0];
    var username4 = username2.get(0); // same as username3
    alert(username3.value);
</script>
</body>
```

## jQuery对象和DOM对象的相互转化

使用`$(dom对象)`的方式即可将dom对象转换为jQuery对象, 习惯上将jQuery的变量名前加上 **$**, username2一般写作$username2

其实jQuery对象内部使用**数组**存放所有数据, 因此可以通过指定其索引的方式来获得DOM对象, 还可以使用jQuery对象的get(index)来获得DOM对象

example:

```js
<script src="../js/jquery.js"></script>
<body>
    <input type="text" id="username" value="vauke"/> <br/>

<script>
    // DOM ==> jQuery
    var username = document.getElementById("username");
    var username2 = $(username);
    alert(username2.val());

    // jQuery ==> DOM
    var username3 = username2[0];
    var test = username2[1]; // undefined  因为这里的jQuery对象只存了一个DOM元素

    var username4 = username2.get(0); // same as username3
    alert(username3.value);
</script>
</body>
```
