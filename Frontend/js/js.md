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
		* [string](#string)
	* [reference data type](#reference-data-type)
	* [operators](#operators)
	* [logics](#logics)

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
<script type="text/javascript" src="path_to_js"></script>
```

js代码可以放在html中的任何地方, 但是在不影响html显示和交互的前提下, js加载的越晚越好(放到body外面, 保证页面的加载不受js加载的影响, 某些js可能不需要在页面加载时立即被调用)

如果要用js获取html中的元素, 那么js代码应该放在body标签的尾部或者外面

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
    1. Boolean()
    2. Number()

### string

## reference data type

## operators

## logics
