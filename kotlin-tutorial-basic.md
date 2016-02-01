# Kotlin教程：入门篇

## Kotlin介绍

官网：<http://kotlinlang.org/>

Kotlin是JetBrains在JVM上推出的新一代静态类型编程语言，具备泛型，高阶函数，Lambda，闭包，代理属性等大多数现代编程语言的特性，支持可空类型，语法简洁。Kotlin生来就是为了弥补Java缺失的现代语言的特性，并极大的简化了代码，使得开发者可以编写尽量少的代码，非常适合于Android开发，推荐所有Android开发者学习。

### Kotlin安装

可以下载单独的Kotlin编译工具，建议直接下载 IntelliJ IDEA 15，然后安装Kotlin插件，创建新项目的时候选择Kotlin支持即可

单独安装Kotlin的方法，Unix系列的系统可以使用SDKMAN安装，方法如下：
```shell
curl -s get.sdkman.io | bash
sdk install kotlin
```

OS X系统可以使用Homebrew安装，如下：

```shell
brew update
brew install kotlin
```

Kotlin版的Hello, World代码如下：

```kotlin
fun main(args: Array<String>) {
    println("Hello, World!")
}
```
如果使用IDEA，点击编辑器窗口main函数左边的标记可以运行代码，命令行需要这样做：

```shell
kotlinc hello.kt -include-runtime -d hello.jar
java -jar hello.jar
```

## 基本语法

下面开始介绍Kotlin的基本语法

### 定义包

Kotlin和Java一样使用package组织代码，package定义必须放在源文件的顶部，如

```kotlin
package com.mcxiaoke.koi
import java.util.*
```

import语句也和Java类似

```kotlin
import foo.Bar 
import foo.* 
```

还可以使用`as`关键字解决命名冲突

```kotlin
import foo.Bar // Bar is accessible
import bar.Bar as bBar // bBar stands for 'bar.Bar'
```
import还可以用于导入顶层作用域（不在任何类的内部）定义的函数和属性，可以导入对象内的函数和属性，导入enum常量

和Java不同的是，package定义不需要和磁盘上的目录结构保持一致，源文件可以放在任何地方，但是建议保持一致

### 定义函数

Kotlin的main函数是这样的，和Java差不多，Kotlin的函数默认是public的
```kotlin
fun main(args: Array<String>) {
	println("hello, world")
}
```

Kotlin的语句不需要分号做结束符，函数用`fun`关键字，个人觉得用`func`更符合习惯，或者用`function`也不错，用`fun`有点不伦不类的

下面是有两个Int参数，返回值为Int类型的函数：
```kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}
```

下面的函数使用一个表达式作为函数体，自动推断返回类型
```kotlin
fun sum(a: Int, b: Int) = a + b
```
如果函数体足够简单，只需要一个表达式，就可以省略大括号，直接将表达式放在等于号 = 后面，就像上面的sum函数一样

从上面的例子可以看出，Kotlin的类型声明是放在变量名后面，用冒号:分隔，函数的返回类型也是放在函数声明的后面，大括号之前，也是用冒号:分隔

下面的函数的 返回类型为Unit，相当于Java里的Void，即没有返回值，Unit可以省略：

```kotlin
fun printSum(a: Int, b: Int): Unit {
    print(a + b)
}
```

省略Unit后

```kotlin
fun printSum(a: Int, b: Int) {
    print(a + b)
}
```

### 定义局部变量

定义一次性赋值（只读）的局部变量使用`val`关键字

```kotlin
fun localVariables() {
    val a: Int = 1
    val b = 1   // 自动推导类型为Int
    val c: Int  // 没有初始值时需要显式制定变量类型
    c = 1       // 初始复制
    // c = 2 这个是错误的，val定义的只读变量不可重新赋值
}
```

`val`大致相当于Java里的final，C/C++里的const，Swift里的let，使用val定义的是不可变量，这种类型的变量有很多优点，后面会提到

定义可重复赋值（读写）的变量使用`var`关键字

```kotlin
fun mutableVariables() {
    var x = 5 // 类型自动推导为Int
    x += 1
}
```

`var`定义的变量就是大部分编程语言里的普通变量，可读写，可重新赋值，Swift也是使用`var`


### 字符串模板

Kotlin支持许多动态语言早就支持的字符串模板，与Groovy和Swift类似

```kotlin
fun strTemplate() {
    val name = "Miufox"
    val age = 6
    print("I have a cat, name is $name age:$age")

    val a:Int = 2016
    val b = 40
    print("sum($a+$b) = ${a+b}")

    var args = arrayOf("Cat", "Dog", "Rabbit")
    print("Hello ${args[0]}")
}
```

在字符串模板中可以使用变量和表达式，表达式需要使用${}包裹，简单的变量可以省略大括号

### 条件表达式

Kotlin中的if语句和Java类似

```kotlin
fun max(a: Int, b: Int): Int {
  if (a > b)
    return a
  else
    return b
}
```

使用if表达式，上面的函数可以简化成这样

```kotlin
fun max(a: Int, b: Int) = if (a > b) a else b
```

### 空值检查

Kotlin中，如果一个值可能为null就必须显式标示为`nullable`，使用问号?，下面的函数返回可能为null

```kotlin
fun parseInt(str: String): Int? {
  // ...
}
```

使用返回值可能为null的函数

```kotlin
fun testInt(args: Array<String>) {
    if (args.size < 2) {
        print("Two integers expected")
        return
    }
    val x = parseInt(args[0]) // Int?
    val y = parseInt(args[1])//Int?
    if (x != null && y != null) {
        // null检查之后，这里自动类型转换为非空值
        print(x * y)
    }
}

```

详细的说明可以参考空值安全章节

### 类型检查和自动转换

`is`操作符用于检查某个对象是否是指定的类型，检查完成后自动转换为指定的类型，无需再显式转换

```kotlin
fun getStringLength(obj: Any): Int? {
  if (obj is String) {
    // `obj` 自动转换为 `String`
    return obj.length
  }
  // 在类型检查的if分支外 obj依然是 `Any` 类型
  return null
}
```

下面的例子可能更清晰一些

```kotlin
fun getStringLength(obj: Any): Int? {
  if (obj !is String)
    return null
  // `obj` 自动转换为 `String` 类型
  return obj.length
}
```

甚至可以这样写

```kotlin
fun getStringLength(obj: Any): Int? {
  // && 右边 obj 已经自动转换为 String 类型
  if (obj is String && obj.length > 0)
    return obj.length

  return null
}
```

### 循环语句

for循环使用in操作符，相当于Java的冒号

```kotlin
fun forLoop1(args: Array<String>) {
    for (arg in args) {
        print(arg)
    }
}
```

或者这样写，遍历数组，后面会看到更优雅的方法

```kotlin
fun forLoop2(args: Array<String>) {
    for (i in args.indices) {
        print(args[i])
    }
}
```

while循环和Java类似

```kotlin
fun whileLoop1(args: Array<String>) {
    var i = 0
    while (i < args.size)
        print(args[i++])
}
```

Kotlin增加的Java没有的when表达式，支持强大的类型匹配功能，这里是一个简单的例子

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

Range表达式

```kotlin
fun range1(x: Int, y: Int) {
    if (x in 1..y - 1) {
        print("OK")
    }
}
```

另一个例子

```kotlin
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

使用集合的简单例子，Kotlin支持Java的集合类型，但比Java强大很多

```kotlin
// 集合类型
fun names1(names: Array<String>) {
    for (name in  names) {
        println(name)
    }
}

// 检查是否包含
fun names2(text: String, names: Array<String>) {
    if (text in names) {
        print("Yes")
    }
}

// 使用lambda表达式
fun names3(names: Collection<String>) {
    names.filter { it.startsWith("A") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { print(it) }
}
```

## 数据类型

### 数字类型

Kotlin提供下列内置类型，与Java的基本数据类型是对应的
```
Type	Bit width
Double	64
Float	32
Long	64
Int		32
Short	16
Byte	8
```

### 字面量

十进制: 123
Long类型 以L结尾: 123L
十六进制: 0x0F
二进制: 0b00001011
注意：不支持八进制

浮点数默认是Double: 123.5, 123.5e10
Float类型以F或f结尾: 123.5f

### 类型转换

Int类型不是Long类型的子类型，下面的代码无法通过编译
```kotlin
fun conversation1() {
    val a: Int? = 1 // Int 包装类型 (java.lang.Integer)
    // val b: Long? = a // Long 包装类型 (java.lang.Long)
    // print(a == b) // 结果是false，因为两者类型不一样
    val b: Byte = 1 // OK, literals are checked statically
    // val i: Int = b // ERROR
    val i: Int = b.toInt() // OK: 显式转换
}
```

所有的数字类型都支持下列转换

```
oByte(): Byte
toShort(): Short
toInt(): Int
toLong(): Long
toFloat(): Float
toDouble(): Double
toChar(): Char
```

### 算术操作

Kotlin支持标准的算术操作符，还支持位操作

```
shl(bits) – signed shift left (Java’s <<)
shr(bits) – signed shift right (Java’s >>)
ushr(bits) – unsigned shift right (Java’s >>>)
and(bits) – bitwise and
or(bits) – bitwise or
xor(bits) – bitwise xor
inv() – bitwise inversion
```

### 字符

字符是Char类型，不能当作数字用，使用单引号包裹
```kotlin
fun testChar(c: Char) {
    //    if (c == 1) { // ERROR: incompatible types
    // ...
    //    }
}
```

### 布尔值
两个值：true和false， &&和||和!三种操作

### 数组
 Kotlin的数组是Array类型，有get和set方法[]，size属性

```kotlin
//数组的部分接口
class Array<T> private constructor() {
val size: Int
fun get(index: Int): T
fun set(index: Int, value: T): Unit
fun iterator(): Iterator<T>
// ...
}

// 创建数组
fun testArray1() {
    val asc = Array(5, { i -> (i * i).toString() })
}

// Kotlin的数组是不可变的
// 不允许将Array<String>赋值给Array<Any>
// Kotlin还有ByteArray, ShortArray, IntArray等类型
fun testArray2() {
    val x: IntArray = intArrayOf(1, 2, 3)
    x[0] = x[1] + x[2]
}
```

### 字符串
和Java一样，Kotlin的字符串是不可变的，可以通过[i]访问单个字符

```kotlin
fun testString1(str: String) {
    for (c in str) {
        println(c)
    }
}
```

字符串字面量
```kotlin
fun testString2() {
    val s = "Hello, world!\n"
    // 或者三引号
    val text = """
    for (c in "foo")
        print(c)
    """
}
```

字符串模板
```kotlin
fun stringTemplate() {
    val i = 10
    val s = "i = $i" // evaluates to "i = 10"
    val x = "abc"
    val str = "$x.length is ${x.length}" // "abc.length is 3"
    // 可以包含反义字符
    val price = "${'$'}9.99"
}
```


## 习惯用法

下面是符合Kotlin习惯的一些用法

创建Model，Kotlin中称作数据类（Data Class）
自动生成 getter/setter/equals/hashCode/toString/copy等
```kotlin
data class Customer(val name: String, val email: String)
```

函数参数支持默认值
```kotlin
fun foo(a: Int = 0, b: String = "") {
    // do something
}
```

集合数据过滤

```kotlin
fun filters(list: Collection<Int>) {
    val positives1 = list.filter { x -> x > 0 }
    // 也可以这样
    val positives2 = list.filter { it > 0 }
}
```

字典数据遍历
```kotlin
fun maps(map: Map<String, String>) {
    for ((k, v) in map) {
        println("$k -> $v")
    }
}
```

不可变List
```kotlin
fun readOnlyList() {
    val list = listOf("a", "b", "c")
    // list.add("d") 错误：不能修改元素
}
```

不可变Map
```kotlin
fun readOnlyMap() {
    val map1 = mapOf("a" to 1, "b" to 2, "c" to 3)
    println(map1["a"])
    // map2["key"] = 123 错误：不能修改元素
}
```

延迟计算属性
```kotlin
class LazyDemo {
    //    val p:String by lazy{
    //        // compute the string
    //    }
}
```

支持给已有类型扩展属性和函数，非常强大好用的特性
```kotlin
fun Int.biggerThanTen(): Boolean {
    return this > 10
}

// 测试一下扩展函数
fun extensions() {
    100.biggerThanTen()
    5.biggerThanTen()
}
```

创建单例对象不要太简单
```kotlin
object Resource {
    val name = "Name"
}
```

可空类型的使用
```kotlin
//nullable用法
fun testNullable() {
    val files = File("Test").listFiles()
    println(files?.size)
    println(files?.size ?: "empty")
}
```

when表达式的另一个例子
```kotlin
fun transform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}
```

异常的使用和Java几乎一样，除了类型定义在后面
```kotlin
fun tryCatch() {
    val result = try {
        // do something
    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }

    // Working with result
}
```

if表达式的另一个例子，可以有返回值
```kotlin
fun ifExp(param: Int) {
    val result = if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else {
        "three"
    }
}
```

with的用法，先看一个例子，后面再细说
```kotlin
class Turtle {
    fun penDown() { }
    fun penUp() {}
    fun turn(degrees: Double) { }
    fun forward(pixels: Double) { }
}

// 如果你要调用某个对象的多个函数，可以这样用
fun withExp() {
    val turtle = Turtle()
    with(turtle) { //draw a 100 pix square
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }
}
```

### 编码风格

 * 使用驼峰不要使用下划线
 * 类型名是用大写字母开头
 * 方法和属性名小写开头
 * 使用四个空格的缩进
 * 公开方法应该有文档

冒号前后有空格，大括号两边有空格，箭头两边有空格，举例

```kotlin
list.filter { it > 10 }.map { element -> element * 2 }
```

