class: center, middle

# What is Kotlin

Kotlin is a pragmatic programming language for JVM and Android that combines OO and functional features and is focused on interoperability, safety, clarity and tooling support.[R](http://blog.jetbrains.com/kotlin/2016/02/kotlin-1-0-released-pragmatic-language-for-jvm-and-android/)

---

# Kotlin

- 简约：减少实现所需的代码量
- 易懂：代码更容易阅读和理解
- 安全：移除了容易犯错误的功能
- 灵活：支持扩展和高阶函数
- 通用：基于JVM，利用现有的库
- 互操作性：目标是 100% 兼容

---

# Kotlin

- Lambda 表达式
- 数据类 (Data classes)
- 函数字面量和内联函数（Function literals & inline functions）
- 函数扩展 (Extension functions)
- 空安全（Null safety）
- 智能转换（Smart casts）
- 字符串模板（String templates）
- 主构造函数（Primary constructors）
- 类委托（Class delegation）
- 类型推断（Type inference）
- 单例（Singletons）
- 声明点变量（Declaration-site variance）
- 区间表达式（Range expressions）

---

# Kotlin

[KotlinAdoption](http://blog.jetbrains.com/kotlin/files/2016/02/KotlinAdoption.gif)

---

# Kotlin

- IntelliJ IDEA
- Android Studio
- Eclipse

---

# Kotlin

### Kotlin

```kotlin
// hello.kt
package com.mcxiaoke.kotlin.demo

fun main(args: Array<String>) {
    println("Hello, World!")
}
```

### java

```java
// Hello.java
package com.mcxiaoke.kotlin.demo;

public class Hello {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

---

# Kotlin

- 包定义与Java基本一致
- 目录与包的结构无需匹配
- 使用as解决命名冲突


