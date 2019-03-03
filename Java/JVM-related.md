# JVM-related.md
Sunday, March 3rd 2019, 14:32

refer :point_right: [Java核心技术36讲](https://time.geekbang.org/column/article/6845)

# JVM

```sequence diagram
Alice->Bob: Hello Bob, how are you?
Note right of Bob: Bob thinks
Bob-->Alice: I am good thanks!
```

```mermaid
graph LR
    A --> B;
    B --> C;
    C --> A;
```

```ditaa {cmd=true args=["-E"]}
+--------+   +-------+    +-------+
|        | --+ ditaa +--> |       |
|  Text  |   +-------+    |diagram|
|Document|   |!magic!|    |       |
|     {d}|   |       |    |       |
+---+----+   +-------+    +-------+
    :                         ^
    |       Lots of work      |
    +-------------------------+
```

```plantuml
@startuml
'default
top to bottom direction
user1 --> (Usecase 1)
user2 --> (Usecase 2)

@enduml
```
