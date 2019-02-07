# ajax.md
Thursday, February 7th 2019, 20:26

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [ajax.md](#ajaxmd)

<!-- /code_chunk_output -->

# XMLHttpRequest对象

`XMLHttpRequest`对象: 简写`XHR`, 发送请求到服务器并获得返回结果, 是整个AJAX技术的核心, 提供异步发送请求的能力

## 构造方法

```js
var xmlhttp;
if (window.XMLHttpRequest) {
    // IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp = new XMLHttpRequest();
} else {
    // IE5, IE6
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
}
```
