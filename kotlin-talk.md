class: center, middle

# Kotlin

Kotlin is a pragmatic programming language for JVM and Android that combines OO and functional features and is focused on interoperability, safety, clarity and tooling support.[R](http://blog.jetbrains.com/kotlin/2016/02/kotlin-1-0-released-pragmatic-language-for-jvm-and-android/)

---

# 目标

- 简约：减少实现所需的代码量
- 易懂：代码更容易阅读和理解
- 安全：移除了容易犯错误的功能
- 灵活：支持扩展和高阶函数
- 通用：基于JVM，利用现有的库
- 互操作性：目标是 100% 兼容

---

# 特性

- Lambda 表达式
- 数据类 (Data classes)
- 函数字面量和内联函数（Inline functions）
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

???

note test!

---

# 发展

[KotlinAdoption](http://blog.jetbrains.com/kotlin/files/2016/02/KotlinAdoption.gif)

---

# 支持

- IntelliJ IDEA
- Android Studio
- Eclipse

---


# Hello, World

### Kotlin

```kotlin
// hello.kt
package com.mcxiaoke.kotlin.demo

fun main(args: Array<String>) {
    println("Hello, World!")
}
```

### Java

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

# Function

### Kotlin

```kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum(a: Int, b: Int) = a + b
```

### Java

```java
public int sum(int a, int b) {
    return a + b;
}
```

---

# Function

```kotlin
fun printSum(a: Int, b: Int): Unit {
    print(a + b)
}

fun printSum(a: Int, b: Int) {
    print(a + b)
}
```

```java
public void printSum(int a, int b) {
    System.out.println(a + b);
}
```

---

# Variables

```kotlin
    val a: Int = 1
    val b = 1
    val c: Int
    c = 1
    // c = 2
    var x = 5
    x += 1
```

---

# Variables

```java
    final int a = 1;
    final int b = 1;
    final int c;
    c = 1;
    // c = 2;
    int x = 5;
    x += 1;
```

---

# String Template

```kotlin
fun strTemplate() {
    val name = "BigHead"
    val age = 6
    print("I have a cat, name is $name age is $age")

    val a:Int = 2016
    val b = 40
    print("sum($a+$b) = ${a+b}")

    var args = arrayOf("Cat", "Dog", "Rabbit")
    print("Hello ${args[0]}")
}
```

---

# String Template

```java
public void strTemplate() {
    final String name = "BigHead";
    final int age = 6;
    System.out.println(String.format("I have a cat, name is %s age is %d", name, age));
    final int a = 2016;
    final int b = 40;
    System.out.println(String.format("sum(%d+%d) = %d", a, b, a + b));
    String[] args = new String[]{"Cat", "Dog", "Rabbit"};
    System.out.println(String.format("Hello %s", args[0]));
}
```

---

# Condition

```kotlin
fun max(a: Int, b: Int): Int {
  if (a > b)
    return a
  else
    return b
}

fun max(a: Int, b: Int) = if (a > b) a else b
```

---

# Condition

```java
public int max(int a, int b) {
    if (a > b) {
        return a;
    } else {
        return b;
    }
}

public int max(int a, int b) {
    return a > b ? a : b;
}
```

---

# Nullable

```kotlin
fun parseInt(str: String): Int? {
    try {
        return Integer.parseInt(str)
    } catch(ex: NumberFormatException) {
        return 0
    }
}

fun testInt(args: Array<String>) {
    if (args.size < 2) {
        print("Two integers expected")
        return
    }
    val x = parseInt(args[0]) // Int?
    val y = parseInt(args[1])//Int?
    if (x != null && y != null) {
        print(x * y)
    }
}
```

---

# Nullable

```java
public Integer parseInt(String str) {
    try {
        return Integer.parseInt(str);
    } catch (NumberFormatException ex) {
        return null;
    }
}

public void testInt(String[] args) {
    if (args == null || args.length < 2) {
        System.out.print("Two integers expected");
        return;
    }
    final Integer x = parseInt(args[0]);
    final Integer y = parseInt(args[1]);
    if (x != null && y != null) {
        System.out.print(x + y);
    }
}
```

---

# Type Checks

```kotlin
fun getStringLength(obj: Any): Int? {
  if (obj is String) {
    // `obj` is automatically cast to `String` in this branch
    return obj.length
  }
  // `obj` is still of type `Any` outside of the type-checked branch
  return null
}

fun getStringLength(obj: Any): Int? {
  // `obj` is automatically cast to `String` 
  // on the right-hand side of `&&`
  if (obj is String && obj.length > 0)
    return obj.length

  return null
}
```

---

# Type Checks

```java
    public int getStringLength(Object obj) {
        if (obj instanceof String) {
            return ((String) obj).length();
        }
        return -1;
    }
```

---

# For

```kotlin
fun forLoop1(args: Array<String>) {
    for (arg in args) {
        print(arg)
    }
}

fun forLoop2(args: Array<String>) {
    for (i in args.indices) {
        print(args[i])
    }
}
```

---

# While

```kotlin
fun whileLoop1(args: Array<String>) {
    var i = 0
    while (i < args.size)
        print(args[i++])
}
```

---

# When

```kotlin
fun cases(obj: Any) {
    when (obj) {
        1 -> print("One")
        "Hello" -> print("Greeting")
        is Long -> print("Long")
        !is String -> print("Not a string")
        else -> print("Unknown")
    }
}
```

---

# Range

```kotlin
fun range1(x: Int, y: Int) {
    if (x in 1..y - 1) {
        print("OK")
    }
}

fun range2(x: Int, array: Array<Int>) {
    if (x !in 0..array.lastIndex) {
        print("Out")
    }
}

fun range3(x: Int) {
    if (x in 1..5) {
        print(x)
    }
}
```

---

# Collections

```kotlin
fun names1(names: Array<String>) {
    for (name in  names) {
        println(name)
    }
}

fun names2(text: String, names: Array<String>) {
    if (text in names) {
        print("Yes")
    }
}
```

---

# Collections

```kotlin
fun names3(names: Collection<String>) {
    names.filter { it.startsWith("A") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { print(it) }
}
```

# Basic Types

Type    | Bit width
---|---
Double  | 64
Float   | 32
Long    | 64
Int     | 32
Short   | 16
Byte    | 8

---

# Numbers

```kotlin

val a: Int? = 1 
val b: Long? = a 
print(a == b) // false

val b: Byte = 1 // OK,
val i: Int = b // ERROR

val i: Int = b.toInt() // OK
``` 

---

# Numbers

```kotlin
toByte(): Byte
toShort(): Short
toInt(): Int
toLong(): Long
toFloat(): Float
toDouble(): Double
toChar(): Char
```

---

# Operations

```kotlin
shl(bits) – signed shift left (Java’s <<)
shr(bits) – signed shift right (Java’s >>)
ushr(bits) – unsigned shift right (Java’s >>>)
and(bits) – bitwise and
or(bits) – bitwise or
xor(bits) – bitwise xor
inv() – bitwise inversion
```

---

# Characters

```kotlin
fun decimalDigitValue(c: Char): Int {
  if (c !in '0'..'9')
    throw IllegalArgumentException("Out of range")
  return c.toInt() - '0'.toInt() // Explicit conversions to numbers
}
```

# Boolean 

```kotlin
true and false
|| – lazy disjunction
&& – lazy conjunction
! - negation
```

---

# Arrays

```kotlin
class Array<T> private constructor() {
val size: Int
fun get(index: Int): T
fun set(index: Int, value: T): Unit
fun iterator(): Iterator<T>
// ...
}
```

---

# Arrays

```kotlin
val asc = Array(5, { i -> (i * i).toString() })

val x: IntArray = intArrayOf(1, 2, 3)
x[0] = x[1] + x[2]
```

---

# Strings

```kotlin
for (c in str) {
    println(c)
}

val s = "Hello, world!\n"

val text = """
  for (c in "foo")
    print(c)
"""

val s = "abc"
val str = "$s.length is ${s.length}"
```

---

# If

```kotlin
// As expression 
val max = if (a > b) a else b

val max = if (a > b) { 
    print("Choose a") 
    a 
  } 
  else { 
    print("Choose b") 
    b 
  }
```

---

# When

```kotlin
when (x) {
  1 -> print("x == 1")
  2 -> print("x == 2")
  else -> { // Note the block
    print("x is neither 1 nor 2")
  }
}
```

# When

```kotlin
when (x) {
  0, 1 -> print("x == 0 or x == 1")
  else -> print("otherwise")
}

when (x) {
  parseInt(s) -> print("s encodes x")
  else -> print("s does not encode x")
}
```

# When

```kotlin
when (x) {
  in 1..10 -> print("x is in the range")
  in validNumbers -> print("x is valid")
  !in 10..20 -> print("x is outside the range")
  else -> print("none of the above")
}
```

# When

```kotlin
val hasPrefix = when(x) {
  is String -> x.startsWith("prefix")
  else -> false
}

when {
  x.isOdd() -> print("x is odd")
  x.isEven() -> print("x is even")
  else -> print("x is funny")
}
```

# Returns

```kotlin
- return. 
- break. 
- continue. 
```

---

# Returns

```kotlin
loop@ for (i in 1..100) {
  for (j in 1..100) {
    if (...)
      break@loop
  }
}
```

# Returns

```kotlin
fun foo() {
  ints.forEach {
    if (it == 0) return
    print(it)
  }
}

fun foo() {
  ints.forEach lit@ {
    if (it == 0) return@lit
    print(it)
  }
}
```

---

# Returns

```kotlin
fun foo() {
  ints.forEach {
    if (it == 0) return@forEach
    print(it)
  }
}
```

# Returns

```kotlin
fun foo() {
  ints.forEach(fun(value: Int) {
    if (value == 0) return
    print(value)
  })
}
```

