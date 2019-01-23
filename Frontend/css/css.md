# css.md
Wednesday, January 23rd 2019, 15:35


<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [css.md](#cssmd)
* [Cascading Style Sheets](#cascading-style-sheets)
	* [Inline CSS](#inline-css)
	* [Embedded/Internal CSS](#embeddedinternal-css)
	* [External CSS](#external-css)
	* [use @import](#use-import)
* [Syntax](#syntax)
	* [basic selectors](#basic-selectors)
		* [type selector](#type-selector)
		* [id selector](#id-selector)
		* [class selector](#class-selector)
	* [property selector](#property-selector)
	* [伪元素选择器](#伪元素选择器)
	* [descendant selectors 层级选择器](#descendant-selectors-层级选择器)
* [inheritance](#inheritance)
* [properties](#properties)
	* [font related properties](#font-related-properties)
	* [text related properties](#text-related-properties)
	* [background related properties](#background-related-properties)

<!-- /code_chunk_output -->


# Cascading Style Sheets

**Cascading** refers to the way CSS applies style on top of another

**Style Sheets** control the look and feel of web documents

**HTML** sorts out the page structure

**CSS** defines how HTML elements are displayed

**CSS** code can stay with HTML code(*inline*, *embedded*) and be stored in a separate file(*external*, recommended way)

there are three sources responsible for the styles we see on the web page: 1. the style sheets created by the author of the page 2. the user customized styles, if any 3. the default styles of the browser itself

## Inline CSS

write in HTML tags and just work in that tag

```html
<p style="color: green">"style='color: green'" is a inline styling css</p>
```

## Embedded/Internal CSS

write in `<style>` tag, inside the `<head>` section of a html page

```html
<head>
    ...
    <style>
        div {
            color: pink;
            background-color: green;
        }
    </style>
</head>
<body>
    <div>this div is applied the style</div>
    <div>this div is also applied the style</div>
</body>
```

## External CSS

write in a separate file and then referenced in the HTML file using `<link rel="stylesheet" type="text/css" href="path_to_css.css">`

## use @import

a way that combines Embedded and External CSS, **this way is not compatible with old versioned IE, and the style is loaded after html loading and not support the dynamic style change by js**

# Syntax

consist of **selector**, **property** and **value**

usage:

```css
selector {
    property: value;
    ...
}
```

**selector** points to the html tag you want to style.

property and its value is separated by a colon.

one or more properties are separated by semicolons.

## basic selectors

### type selector

```css
html-tag {
    property: value;
    ...
    }
```

this is coarse-grained selector that **all** the corresponding tag will be applied to the style

coarse-grained 粗粒度的 `antonym`: :point_right: fine-grained 细粒度的

example:

```html
<style>
    span {
        color: red;
        font-size: 100px;
    }
</style>

<span>the font's color is red, and the size is 100px</span>
```

### id selector

```css
#id {
    property: value;
    ...
}
```

works in **all** tags whose `id` is the defined one, and the `id` can only be applied once per page

start with a `hash tag`, followed by the name of the id

example:

```html
<style type="text/css">
    #div1 {
        background-color: pink;
    }
</style>

<span id="div1">this span tag's background color is pink</span>
<div id="div1">this div tag's background color is also pink</div>
```

### class selector

```css
.class_name {
    property: value;
    ...
}
```

works in **all** tags whose `class`'s value is the same as defined one

start with a `period character`, followed by the name of the class

example:

```html
<style>
    .color-yellowgreen {
        background-color: yellowgreen;
    }
</style>

<span class="color-yellowgreen">the background color is yellowgreen</span>
<div>yellowgreen background</div>
```

the priority of these 3 selectors is: id > class > type

**Do NOT start a class or id name with a number!**

## property selector

```css
<style>
    basic selector[property="value"] {
        background-color: yellowgreen;
        ...
    }
</style>
```

based on [basic selectors](#basic-selectors), and dive into the html elements specified by the `basic selector`, style the corresponding elements with the same property value.

example:

```html
<style>
	input[type="text"] {
		background-color: pink;
	}
	input[type="password"] {
		background-color: green;
	}
</style>

<form action="">
	username: <input type="text" /> <br/>
	password: <input type="password" /> <br/>
</form>
```

the username form will be pink while the password form will be green

## 伪元素选择器

a标签的伪元素选择器

```css
<style>
	a:link{property: value;} /* link: 链接的初始状态 */
	a:hover{property: value;} /* hover: 鼠标悬浮时的状态 */
	a:active{property: value;} /* active: 点击时的状态 */
	a:visited{property: value;} /* visited: 动作完成时的状态 */
</style>
```

example:

```html
<style>
	a:link{color: blue;} /* link: 链接的初始为蓝色 */
	a:hover{color: red;} /* hover: 鼠标悬浮在链接上时为红色 */
	a:active{color: yellow;} /* active: 点击时为黄色 */
	a:visited{color: green;} /* visited: 动作完成时为绿色 */
</style>

<a href="#">click to see variant</a>
```

## descendant selectors 层级选择器

These selectors are used to select elements that are descendants of another element. When selecting levels, you can select as many levels deep as you need to.

```css
<style>
	父级选择器 子级选择器 ... {
		property: value;
		...
	}
</style>
```

选择器之间使用空格隔开

example:

to select the third span element

```html
<style>
	#d2 .dd1 span {
		color: pink;
	}
</style>

<div id="d1">
	<div class="dd1">
		<span>span1-1</span>
	</div>
	<div class="dd2">
		<span>span1-2</span>
	</div>
</div>
<div id="d2">
	<div class="dd1">
		<span>span1-1</span>
	</div>
	<div class="dd2">
		<span>span1-2</span>
	</div>
</div>
```

# inheritance

Inheritance refers to the way properties flow through the page. Achild element will usually take on the characteristics of the parent element unless otherwise defined.

# properties

## font related properties

`font-size`: defines fonts' size

`font-family`: defines type of font

## text related properties

`color`

`text-decoration`: none | underline

a link on the web page often has a describe text with underline, just like this: __a link__, if want to remove it, just set `text-decoration: none`

`text-align`: left | center | right

## background related properties

`background-color`

`background-image`: url("path_to_image")

`background-repeat`: 平铺方式

* `repeat`: 横向, 纵向平铺
* `no-repeat`: none
* `repeat-y`: vertical
* `repeat-x`: horizontal
