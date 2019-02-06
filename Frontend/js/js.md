# js.md
Thursday, January 31st 2019, 20:54


<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [js.md](#jsmd)
* [JavaScript](#javascript)
* [Inline js](#inline-js)
* [Embedded / Internal js](#embedded-internal-js)
* [External js](#external-js)
* [Syntax](#syntax)
	* [variables](#variables)
	* [primitive data type](#primitive-data-type)
		* [type convert](#type-convert)
	* [reference data type](#reference-data-type)
	* [operators](#operators)
	* [logics](#logics)
* [js内建对象](#js内建对象)
	* [Number](#number)
	* [Boolean](#boolean)
	* [String](#string)
	* [Array](#array)
	* [Date](#date)
	* [Math](#math)
	* [RegExp](#regexp)
* [函数](#函数)
	* [定义方式](#定义方式)
	* [函数的参数](#函数的参数)
	* [返回值](#返回值)
	* [全局函数](#全局函数)
		* [encode / decode](#encode-decode)
		* [eval()](#eval)
* [js events](#js-events)
	* [事件的绑定方式](#事件的绑定方式)
	* [this关键字](#this关键字)
	* [onfocus onblur](#onfocus-onblur)
	* [onload](#onload)
	* [阻止标签的默认事件](#阻止标签的默认事件)
	* [阻止事件的传播](#阻止事件的传播)
* [BOM](#bom)
	* [window对象](#window对象)

<!-- /code_chunk_output -->


# JavaScript

ECMAScript: 一套js标准

BOM(Browser Object Model): 浏览器对象模型

DOM(Document Object Model): 文档对象模型

# Inline js

write in html tag

```html
<input type="button" value="button" onclick="alert('hello js!')" />
```

# Embedded / Internal js

write in `script` tag, inside the section `head` of a html page

```html
<head>
    ...
    <script type="text/javascript">
        alert("hello js");
    </script>
</head>
```

# External js

write in a independent file with format js

```html
<script type="text/javascript" src="path_to_js.js"></script>
```

js代码可以放在html中的任何地方, 但是在不影响html显示和交互的前提下, js加载的越晚越好(放到body外面, 保证页面的加载不受js加载的影响, 某些js可能不需要在页面加载时立即被调用)

如果要用js获取html中的元素, 那么js代码应该放在body标签的尾部或者外面

> script标签中使用src**引入**js文件, link标签中使用href**引用**css文件 <br/>
> src是source的缩写, 在加载和处理所引入的资源时, 浏览器会暂停渲染当前页面, 直到外部文件加载完成 <br/>
> href是hypertext reference的缩写, 在加载所引用的资源时, 并行处理, 并不会暂停当前页面的渲染

以上, 因此常常将js放在底部而不是头部 [reference](https://blog.csdn.net/annsheshira23/article/details/51133709)

# Syntax

## variables

use `var` to declare a variable, use `=` to assign a value

a variable can be declared without a value, and it's value will be assigned to the default `undefined`

```javascript
var x = 5;
x = 'javascript';
var y = "hello";
var z = true;

a = 1; // var can be omitted
```

## primitive data type

use `typeof` to indicate a variable's data type: `typeof x` or `typeof(x)`

1. number
2. string
3. boolean
4. null (null is an object)
5. undefined

### type convert

number/boolean :point_right: string, use `x.toString()`

string/boolean :point_right: number, use `parseInt(x)` or `parseFloat(x)`

强制转换:

1. Boolean(x)
    * number: `Boolean(0) // false` `Boolean(非0) // true`
    * string: `Boolean("") // 空串为false` `Boolean("非空") // true`

2. Number(x)
    * boolean: `Number(true) // 1` `Number(false) // 0`
    * string: 结果为`NaN`

3. String()

## reference data type

java:

```java
Object o = new Object();
```

javascript:

```javascript
var obj = new Object();
var num = new Number(); // num is an object
```

## operators

算术运算符(除`+`外, `+`为拼接字符串)在遇到字符串时, 会先将字符串(`"123"`)转为相应数字(类似`123abc`的字符串除外)

```js
x = "5", y = "123";
x * y = 615; y = "123abc" // NaN
```

`===`: 全等 类型与值都相等时才为`true`

```js
"123" == 123 // true
"123" === 123 // false
```

`void` 运算符: `<a href="javascript:void(0);">表示空链</a>`

`typeof` 运算符: 判断数据类型并返回

`instanceof` 运算符: 判断是否为指定类型并返回boolean

## logics

`if`的条件: 非0数字, 非空串为true `if(1) // true`

`switch`: 可以用字符串, java中是1.7之后才行

`for in`:

```js
var arr = [1, true, "js"];

for (index in arr) {
    alert(arr[index]);
}
```

# js内建对象

## Number

```js
// 创建方式:
var myNum = new Number(value);
var myNum = Number(value);

// 属性方法
toString();
valueOf();
```

## Boolean

```js
// 创建方式
var myBool = new Boolean(value);
var myBool = Boolean(value);

// 属性方法
toString()
valueOf()
```

## String

```js
// 创建方式
var myStr = new String(value);
var myStr = String(value);
var myStr = "value";

// 属性方法
length

charAt(): return character at specified index
charCodeAt(): return character unicode at specified index
indexOf(): return index of specified character
lastIndexOf(): reverse version of indexOf()
split(): split string to array
substr(x, y): 从起始索引x开始截取指定数目y个字符
substring(x, y): 截取两个指定索引之间[x, y)的字符
```

## Array

```js
// 创建方式
var myArr = new Array(); // empty array without size
var myArr = new Array(size); // specified array's size 超出之后也可以加元素 和Java不同
var myArr = new Array(element0, element1, element2...); // 创建并实例化
var myArr = []; // same as first one
var myArr = [element0, ...] // same as third one

// 属性方法
length

join(x): 将数组中的所有元素放入字符串并用指定的分隔符x进行连接
pop(): delete and return the last element of the array
push(): append a new element to the tail and return new length
reverse(): reverse array
sort(): sort array, 所有元素都按照字典顺序排序, 数字在前
```

## Date

```js
// 创建方式
var myDate = new Date();
var myDate = new Date(Millis);

// 属性方法
getFullYear()
getMonth(): 0-11
getDate(): 1-31
getDay(): 星期中的天 0-6
getTime(): 当前时间的milliseconds
toLocaleString(): 当前时间的本地格式
```

## Math

```js
// 创建方式
Math不像Date和String那样是对象的类, 它没有构造函数Math(), 无需创建

// 函数
Math.PI

Math.abs()
Math.ceil(): 向上取整
Math.floor(): 向下取整
Math.pow(x, y): 取x的y次幂
Math.random(): [0, 1)
Math.round(): 四舍五入
```

## RegExp

```js
// 创建方式
var myReg = new RegExp(pattern);
var myReg = /^regular expression$/;

// 正则写法
[A-z]: 所有字母 比Java多的
\d: 数字
\D: 非数字
\w: 单词字符
\W: 非单词字符
\s: 空白字符
\S: 非空白字符
n?: 规则n出现0或1次
n+: 规则n出现1或多次
n*: 规则n出现0或多次
{n}: 出现n次
{m, n}: m到n次

// 方法
test(str)
```

# 函数

## 定义方式

```js
// 一般方式
function func_name(parameter list) {
	...
}

func_name(); // 调用函数

// 匿名函数
function(parameter list) {
	...
}

// 匿名函数无法调用, 一般使用变量来调用
var func = function(...) {
	...
};

func(); // 调用此匿名函数

// 函数对象
new Function(param1, param2,..., 函数体); // 参数和函数体以字符串形式给出

var fn = new Function("a", "b", "alert(a + b)");
fn(1, 2); // 结果为3
```

## 函数的参数

1. 形参不需要var修饰
2. 形参和实参个数可以不同
3. 函数体类使用arguments数组获得所有实参

```js
var fn = function(a,b,c) {
	for (var i = 0; i < arguments.length; i++) {
		alert(arguments[i]);
	}
}

fn(1,2,3,4);
```

## 返回值

1. 不用在方法声明上添加返回值类型
2. 仅仅通过`return`关键字说明有返回值
3. `return`后的语句不执行

function fn(a, b)

## 全局函数

### encode / decode

1. encodeURI() decodeURI() // 将字符串编码/解码为URI
2. encodeURIComponent() decodeURIComponent() // 将字符串编码/解码为URI组件
3. escape() unescape() // 将字符串编码/解码

example:

```js
var url = "https://www.baidu.com?name=张三&password=123456"

encodeURI(url): https://www.baidu.com?name=%E5%BC%A0%E4%B8%89&password=123456
encodeURIComponent(url): https%3A%2F%2Fwww.baidu.com%3Fname%3D%E5%BC%A0%E4%B8%89%26password%3D123456
escape(url): https%3A//www.baidu.com%3Fname%3D%u5F20%u4E09%26password%3D123456
```

### eval()

将字符串当做js代码, 解析运行

example:

```js
var str = "var a = 2; var b = 3; alert(a + b);";

eval(str); // 5
```

# js events

reference: :point_right: http://www.w3school.com.cn/jsref/jsref_events.asp

`onclick`

`onchange`: 域内容改变时触发

`onfocus`: 获得焦点时触发

`onblur`: 失去焦点时触发

`onmouseover`: 鼠标悬浮时触发

`onmouseout`: 鼠标离开时触发

`onload`: 加载完毕时触发

## 事件的绑定方式

1. 将事件和响应行为都内嵌到html中

```js
<input type="button" value="submit" onclick="alert('xxx')" />
```

2. 将事件内嵌到html中, 而响应行为使用函数封装

```js
<input type="button" value="submit" onclick="fn()" />

<script>
    function fn() {
        alert('yyy');
    }
</script>
```

3. 将事件和响应行为与html标签完全分离

```js
<input type="button" value="submit" id="btn" />

<script>
    var btn = document.getElementById("btn");

    btn.onclick = function() {
        alert('zzz');
    };
</script>
```

## this关键字

表示所出现位置的html标签

```js
<input type="button" value="submit" id="btn" onclick="fn(this)" />

<script>
	// 这里this表示html标签对象
    function fn(obj) {
        alert(obj.type); // button
        alert(obj.value); // submit
    }

	// 表示click事件对象???
	var btn = document.getElementById("btn");

    btn.onclick = function(obj) {
        alert(obj.type); // click
    };
</script>
```

## onfocus onblur

```js
<body>
    <label for="txt">name: </label>
    <input id="txt" type="text"> <span id="tips"></span>
</body>
<script>
    var txt = document.getElementById("txt");

    txt.onfocus = function() {
        var tips = document.getElementById("tips");
        tips.style.color = "green";
        tips.innerHTML = "username is longer than 8 characters";
    };

    txt.onblur = function() {
        var tips = document.getElementById("tips");

        tips.style.color = "red";
        tips.innerHTML = "username has already been taken";
    };
</script>
```

## onload

window.onload事件表示当前页面加载完毕

example:

此时的`alert(span);`打印出的是null, 因为当加载到js代码时还未加载html(为什么? :point_right: [External js](#external-js))

```js
<head>
	...
	<script>
		var span = document.getElementById("span");

		alert(span); // null
	</script>
</head>
<body>
	<span id="span"></span>
</body>
```

使用`window.onload`事件来做些改变

```js
<head>
	...
	<script>
		window.onload = function() {
			var span = document.getElementById("span");

			alert(span); // [object HTMLSpanElement]

			span.innerHTML = "hello onload"
		};
	</script>
</head>
<body>
	<span id="span"></span>
</body>
```

## 阻止标签的默认事件

标签的默认事件, 比如a标签的默认事件是点击就跳转页面

IEs: `window.event.returnValue = false;`

others:: `传递过来的事件对象.preventDefault();`

```js
<body>
    <a href="https://www.baidu.com" onclick="fn(event)">click me</a>
</body>
<script>
    function fn(e) {
        alert('redirecting to baidu'); // 依旧会打印
        // window.event.returnValue = false;
        e.preventDefault(); // 不会跳转到baidu
    };
</script>
```

`fn(event)`: 这里不能写为fn(this), this表示a标签对象m, event关键字代表onclick事件

通过事件`return false;`也可以阻止:

```js
<a href="https://www.baidu.com" onclick="return false;">click me</a>
```

## 阻止事件的传播

事件的传播:

```js
<body>
    <div onclick="fn1()" style="width:100px; height:100px; padding:50px; background-color: green">
        <div onclick="fn2()" style="width:100px; height:100px; background-color: red"></div>
    </div>
</body>
<script>
    function fn1() {
        alert("outter div");
    };

    function fn2() {
        alert("inner div");
    };
</script>
```

以上代码, 当点击inner div时, 会先提示inner div, **接着** 会提示outter div, 这在开发中可能是有问题的, 应该阻止这样的事件传播行为

传播行为产生的原因: 测试传播行为时, 发现事件的传播只发生在父子标签中, 且由子向父传播, 因此猜测是因为父级元素包含子级元素, 在视觉上子级元素"覆盖"了父级元素的一部分, 而实际上在该区域是共存了两个元素的, 因此点击子级元素时, 实际也是在点击父级元素, 而且是先点击到子级元素, 再"穿透"点击到父级元素

IEs: `window.event.cancelBubble = true;`

others: `传递过来的事件对象.stopPropagation();`

```js
<body>
    <div onclick="fn1()" style="width:100px; height:100px; padding:50px; background-color: green">
        <div onclick="fn2(event)" style="width:100px; height:100px; background-color: red"></div>
    </div>
</body>
<script>
    function fn1(e) {
        alert("outter div");
    };

    function fn2(e) {
        e.stopPropagation();
        alert("inner div");
    };
</script>
```

这样在点击inner div后, 阻止了点击事件向父级元素传递

# BOM

## window对象

`alert("提示信息")`: window.alert()省略而来 无返回值

`confirm("确认信息")`: 确认 返回true 取消 返回false

```js
var result = confirm("aaa");

alert(result);
```

`prompt("提示信息")`: 提示信息和输入框, 确认 返回输入的值 取消 返回null

`open("url")`: `open("../demo1.html");`新开一个pop-ups, 在新标签页打开demo1.html

定时器:

`setTimeout(函数, millis)`

```js
// 延时递归调用
function fn() {
	alert("a");
	setTimeout(fn, 2000);
};

fn();
```

`clearTimeout(定时器的名称)`: `clearTimeout(fn);`

每隔一段时间定时一次:

`setInterval(函数, millis)`

`clearInterval(定时器的名称)`
