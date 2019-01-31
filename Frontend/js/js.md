# js.md
Thursday, January 31st 2019, 20:54


<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [js.md](#jsmd)
* [JavaScript](#javascript)
* [Inline js](#inline-js)
* [Embedded / Internal js](#embedded-internal-js)

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

write in tag `script`, inside the section `head` of a html page

```html
<head>
    ...
    <script>
        alert("hello js");
    </script>
</head>
```
