# jQuery.md
Sunday, February 10th 2019, 20:46

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [jQuery.md](#jquerymd)
* [Syntax](#syntax)
	* [selectors](#selectors)

<!-- /code_chunk_output -->

# Syntax

## selectors

like selectors in CSS

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
</script>
</body>
```

使用`$(dom对象)`的方式即可将dom对象转换为jQuery对象, 习惯上将jQuery的变量名前加上 **$** (username2一般写作$username2)
