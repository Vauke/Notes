# JVM-related.md
Sunday, March 3rd 2019, 14:32

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [JVM-related.md](#jvm-relatedmd)
* [JVM](#jvm)
	* [JIT](#jit)
	* [GC](#gc)

<!-- /code_chunk_output -->

refer :point_right: [Java核心技术36讲](https://time.geekbang.org/column/article/6845)

众所周知, 我们通常把 Java 分为编译期和运行时. 这里说的Java的编译和 C/C++ 是有着不同的意义的, Javac 的编译, 编译 Java 源码生成".class"文件里面实际是字节码, 而不是可以直接执行的机器码.

# JVM

源码从编译到执行的过程:

首先通过javac编译成为字节码(bytecode), 然后, 在运行时, 通过JVM内嵌的解释器将字节码转换成最终的机器码.

Java从源码到被执行所经过的流程:

```mermaid
graph LR
    A(Java源码) --> |javac编译| B(字节码)
    B --> |ClassLoader加载字节码, 解释器解释执行或者JIT即时编译| C(机器码)
```

Java既是编译执行也是解释执行的, 是解释和编译混合的模式, 即所谓的混合模式(-Xmixed)

类加载大致过程:

```mermaid
graph LR
    A(加载) --> B(验证)
    B --> C(链接)
    C --> D(初始化)
```

## JIT

JIT(Just-In-Time)即时编译器, 是Oracle JDK提供的HotSpot VM中的编译器, 用于将*热点*(常出现的)字节码动态地编译为机器码, HotSpot VM内置有两个JIT, c1和c2

    1. c1: client模式, 适用于小型的需要快速编译的程序, 速度较快
    2. c2: server模式, 会经过上万次的调用, 以收集足够的信息进行高效的编译, 为长时间运行的服务器应用设计

启动JVM时指定不同的参数来选择不同的运行模式, 默认采用分层编译(Tiered Compilation):

    1. -Xint: 只解释执行, 不编译, 因为这样会使解释器逐条读入再解释运行, 因此性能不好
    2. -Xcomp: 关闭解释器, 只进行编译, 这种模式叫做最大优化级别, 但这样却不一定是最高效的, 需要配置, 且会导致JVM启动变慢

Java9还提供了一种新的编译方式, AOT(Ahead-of-Time Compilation), 可以直接将源码编译成机器码, 这样就避免了JIT预热等各方面的开销.

Oracle JDK支持分层编译和AOT编译之间进行协作

写个程序直接执行字节码就是解释执行. 写个程序运行时把字节码动态翻译成机器码就是jit. 写个程序把java源代码直接翻译为机器码就是aot. 造个CPU直接执行字节码, 字节码就是机器码.

## GC

常见GC: Serial GC, Parallel GC, CMS, G1
