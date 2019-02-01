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

// 属性方法
```
