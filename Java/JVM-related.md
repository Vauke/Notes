# JVM-related.md
Sunday, March 3rd 2019, 14:32

refer :point_right: [Java核心技术36讲](https://time.geekbang.org/column/article/6845)

# JVM

```mermaid
graph LR
    A(Java源码) --> |javac编译| B(字节码)
    B --> |解释执行或者JIT即时编译| C(机器码)
```
