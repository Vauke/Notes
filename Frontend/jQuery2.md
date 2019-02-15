# jQuery2.md
Friday, February 15th 2019, 17:19

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [jQuery2.md](#jquery2md)
* [筛选](#筛选)
	* [过滤](#过滤)
	* [查找](#查找)
	* [串联](#串联)
* [事件](#事件)
	* [jQuery完善的部分(原生js或许没有)](#jquery完善的部分原生js或许没有)
	* [事件绑定](#事件绑定)
		* [处理](#处理)
		* [委派](#委派)
		* [切换](#切换)
* [动画](#动画)
* [ajax](#ajax)
* [miscellaneous](#miscellaneous)

<!-- /code_chunk_output -->

接[jQuery.md](jQuery.md)

# 筛选

选择器可以完成的功能, 筛选也提供相同函数 i.e. 选择器`:first` 筛选的函数`first()`

区别:

`$("div:first")`: 直接获得第一个div, 也只能获得一个

`$("div").first()`: 先获得所有div, 再取第一个, 还能去操作其他的div

## 过滤

1. `eq(index | -index)`
    * index: 从头往后数第index+1个元素, index=0,1...
    * -index: 从尾开始

```js
// <input type="button" value=" 选择最后一个div元素"  id="b3"/>
$("div").eq(-1).show().css("background-color", "#0f0");
```

2. `first()`

3. `last()`

4. `is()`: 判断是否成立 是true 否false

```js
// <input type="button" value=" 样式为hide div 下一个兄弟是否是span"  id="b6"/>
alert($("div.hide").next().is("span")); // true
```

5. `hasClass()`: 判断class是否是指定的class, 其实就是`is("."+class)`

```js
// <input type="button" value=" id=one div样式是否是one"  id="b4"/>
alert($("div#one").hasClass("one")); // true
```

6. `filter(expr)`: 过滤出与指定表达式expr匹配的元素集合

```js
// <input type="button" value=" 选择class为none的所有div"  id="b5"/>
$("div").filter(".none").show().css("background-color", "#0f0");
```

7. `not()`: 排除

```js
// <input type="button" value=" 选择样式为one div 子元素中没有title属性的div"  id="b8"/>
$("div.one").children().not("[title]").parent().css("background-color", "#0f0");
```

8. `has()`: 筛选出子元素

```js
// <input type="button" value=" 选择所有div中含有div的"  id="b7"/>
$("div").has("div").css("background-color", "#0f0");
```

9. `slice(start, end)`: 截取jQuery数组元素

```js
// <input type="button" value=" 选择所以号为3,4的div"  id="b9"/>
$("div").slice(3,5).css("background-color", "#0f0");
```

10. `map()`: 将jQuery对象数组中的每个元素单独又包装为多个jQuery对象

## 查找

    <A>
        <B>
            <C></C>
            <D></D>
            <E></E>
            <F></F>
        </B>
    </A>

1. `B.children([...])`: 获得所有子元素, CDEF

```js
// <input type="button" value=" 选择 id=two 子元素title=other 元素 "  id="b2"/>
$("#two").children("[title='other']").css("background-color", "#0f0");
```

2. `A.find(D)`: 从指定区域开始查询指定元素, D

```js
$(document).ready(function(){
    $("div").children("a").hide().prev("span").click(function() {
        $(this).nextAll().toggle().parent().siblings("div").find("a").hide();
    });
});

<div>
    <span>xxxx</span>
    <a>yyyy</a>
    <a>yyyy</a>
</div>
<div>
    <span>aaaa</span>
    <a>bbb</a>
    <a>bbbb</a>
</div>
```

3. `D.next()`: 获得下一个兄弟, E

```js
// <input type="button" value=" 选择 id=two 下一个兄弟"  id="b3"/>
$("#two").next().css("background-color", "#0f0");
```

4. `D.nextAll([expr])`: 后面的所有兄弟, EF

5. `E.prev()`: 上一个兄弟, D

6. `E.prevAll()`: 前面的所有兄弟, CD

7. `E.siblings()`: 所有兄弟, CDF

8. `E.parent()`: 父元素, B

9. `E.closest(A)`: 向上获得指定的父级元素A, 获得返回一个对象(1), 没有返回0

10. `C.nextUntil(E)`: 获得C到E之间的所有的C的兄弟, DE

11. `E.prevUntil(D)`: D

12. `E.parents()`: 获得所有父级元素, BA

## 串联

1. `A.add(B)`: 将A和B组合, 同时操作

```js
// 	  <input type="button" value=" 选择 id=one和two 的div"  id="b1"/>
$("#one").add("#two").css("background-color","#0f0");
```

2. `andSelf()`
    * `A.children().css(...)`: 对A的孩子做css操作
    * `A.children().andSelf().css(...)`: 自身和孩子**同时**css

```js
// <input type="button" value=" 选择id=one 所有的孩子，以及one自己"  id="b2"/>
$("#one").children().andSelf().css("background-color","#0f0");
```

3. `contents()`: 获得所有的子节点(子元素和文本)

4. `end()`: 回到最近的一个"破坏性"操作之前
    * `A.children().children().css(...)`: 操作孙子
    * `A.children().children().end().css(...)`: 操作孩子
    * `A.children().children().end().end().css(...)`: 操作A

```js
// <input type="button" value=" 选择id=one 所有的孩子，设置颜色为红，设置one自己为黄"  id="b3"/>
// $("#one").children().css("background-color","#f00").andSelf().css("background-color", "#ff0"); // 不能用andSelf()
$("#one").css("background-color", "#ff0").children().css("background-color", "#f00");
$("#one").children().css("background-color","#f00").end().css("background-color", "#ff0");
$("#one").children().css("background-color","#f00").parent().css("background-color", "#ff0");
```

不能用`andSelf()`, 最终会变为都是#ff0(黄色), andSelf()在自己和孩子后续操作一致的情况下使用, 否则使用end(), '隔离'开, 单独操作

# 事件

refer :point_right: [常见事件](./assets/常见事件.html)

## jQuery完善的部分(原生js或许没有)

以下基于:

```html
<div id="outerDiv" style="border:1px solid #f00;width:200px;height:200px">
    <div id="innerDiv" style="border:1px solid #00f;width:100px;height:100px"></div>
</div>

<br/>
<span id="showSpan"></span>
```

1. `focusin()`: 获得焦点 同focus()或 js: onfocus 区别: focusin()可以在父元素上检测子元素获得焦点的情况

```js
// 点击innerDiv可以触发事件, 事件的传播
$("#outterDiv").click(function() {
    alert("outterDiv");
});

// 点击innerDiv不会触发事件
$("#outterDiv").focus(function() {
    alert("outterDiv");
});

// 点击innerDiv可以触发事件
$("#outterDiv").focusin(function() {
    alert("outterDiv");
});
```

2. `focusout()`: 失去焦点 同blur()或 js: onblur 区别: focusout()可以在父元素上检测子元素失去焦点的情况

3. `mouseenter()`: 移入, 同 js: mouseover 区别: mouseenter()只在进入备选元素时才会触发事件

```js
var i= 0;

// 鼠标进入父元素和子元素时都会触发事件
$("#outterDiv").mouseover(function() {
    $("#showSpan").html(i++);
});

// 进入子元素innerDiv不触发事件
$("#outterDiv").mouseenter(function() {
    $("#showSpan").html(i++);
});
```

4. `mouseleave()`: 移出 同 js: mouseout 区别: mouseleave()只在离开被选元素时才会触发事件

## 事件绑定

### 处理

### 委派

### 切换

# 动画

# ajax

# miscellaneous
