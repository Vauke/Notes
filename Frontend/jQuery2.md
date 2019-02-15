# jQuery2.md
Friday, February 15th 2019, 17:19

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [jQuery2.md](#jquery2md)
* [筛选](#筛选)
	* [过滤](#过滤)
	* [查找](#查找)
* [事件](#事件)
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

# 事件

# 动画

# ajax

# miscellaneous
