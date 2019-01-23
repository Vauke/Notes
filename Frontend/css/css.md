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

<!-- /code_chunk_output -->


# Cascading Style Sheets

**Cascading** refers to the way CSS applies style on top of another

**Style Sheets** control the look and feel of web documents

**HTML** sorts out the page structure

**CSS** defines how HTML elements are displayed

**CSS** code can stay with HTML code(*inline*, *embedded*) and be stored in a separate file(*external*, recommended way)

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

works in **all** tags whose `id` is the defined one

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

## property selector

```css
<style>
    basic selector[property="value"]{
        background-color: yellowgreen;
        ...
    }
</style>
```

based on [basic selectors](#basic-selectors),

[TOC]
