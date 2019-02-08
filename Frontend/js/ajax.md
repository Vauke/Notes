# ajax.md
Thursday, February 7th 2019, 20:26

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [ajax.md](#ajaxmd)
* [XMLHttpRequest对象](#xmlhttprequest对象)
	* [构造方法](#构造方法)
	* [方法](#方法)
	* [属性](#属性)
		* [onreadystatechange事件](#onreadystatechange事件)

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

## 方法

向服务器发送请求使用`open()`和`send()`

1. `open(method, url, async)`: 用于创建连接, 参数依次为规定请求的类型, url, 是否异步处理请求
    * method: 请求类型, GET | POST
    * url: 文件在服务器上的位置
    * async: true 异步 false 同步 default true 如果要用ajax, 此值**必须**为true
2. `send(str)`: 将请求发送到服务器, str参数仅在使用POST方法时使用
3. `sendRequestHeader(header, value)`: 向请求添加http头, header 头名 value 头值

## 属性

获得来自服务器的响应

1. `responseText`: 获得字符串形式的响应数据
2. `responseXML`: 获得XML形式的响应数据

### onreadystatechange事件

当请求发送到服务器后, 会触发一系列的请求处理, 每当`readyState`改变时, 都会触发`onreadystatechange`事件

1. `readyState`: 存放XMLHttpRequest对象的状态 0-4
    * `0`: 请求未初始化 XMLHttpRequest对象还未完成初始化
    * `1`: 服务器已建立连接 XMLHttpRequest对象开始发送请求(调用了open(), 还未调用send() 请求还未发出
    * `2`: 请求已接收 XMLHttpRequest对象请求发送完成(send()已调用, 服务器已接收到数据, 但还没有作出响应)
    * `3`: 请求处理中 XMLHttpRequest对象正在读取响应
    * `4`: 请求已完成, 且响应已接收
2. `status`: 响应码 200 404 403无权限
3. `onreadystatechange`: 存储函数(名), 每当readyState改变时, 就调用该函数

当`readyState`为4且`status`为200时, 表示响应已就绪

当`open()`的async参数为false时, 不需要编写onreadystatechange的函数, 直接把代码放到send()后面就行
