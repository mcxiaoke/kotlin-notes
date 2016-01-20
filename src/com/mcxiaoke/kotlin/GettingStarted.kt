package com.mcxiaoke.kotlin

import java.io.File

/**
 * Author: mcxiaoke
 * Date: 2016/1/20 20:05
 */

// http://kotlinlang.org/docs/reference/basic-syntax.html

// 定义函数
fun sum1(a: Int, b: Int): Int {
    return a + b
}

// 使用类型推导的函数
fun sum2(a: Int, b: Int) = a + b

// 无返回值的函数
fun printSum1(a: Int, b: Int): Unit {
    print(a + b)
}

// Unit可以忽略
fun printSum2(a: Int, b: Int) {
    print(a + b)
}

// 定义局部变量
// 不可变
fun localVariables() {
    val a: Int = 1
    val b = 1
    val c: Int
    c = 1
}

// 可变
fun mutableVariables() {
    var x = 5
    x += 1
}

// 字符串模板
fun strTemplate() {
    var args = arrayOf("Cat", "Dog", "Rabbit")
    print("Hello ${args[0]}")
}

// 条件表达式
fun max1(a: Int, b: Int): Int {
    if (a > b) return a
    else return b
}

// 也可以这样写
fun max2(a: Int, b: Int) = if (a > b) a else b

// 空值 nullable
// 如果str不是数字，返回null
fun parseInt(str: String): Int? {
    return str.toInt()
}

// 使用返回nullable的函数
fun testInt(args: Array<String>) {
    if (args.size < 2) {
        print("Two integers expected")
        return
    }

    val x = parseInt(args[0]) // Int?
    val y = parseInt(args[1])//Int?

    // Using `x * y` yields error because they may hold nulls.
    if (x != null && y != null) {
        // x and y are automatically cast to non-nullable after null check
        print(x * y)
    }
}

// 类型检查和自动转换
fun getStringLength1(obj: Any): Int? {
    if (obj is String) {
        return obj.length
    }
    return null
}

// 也可以这样写
fun getStringLength2(obj: Any): Int? {
    if (obj is String && obj.length > 0) {
        return obj.length
    }
    return null
}

// for循环
fun forLoop1(args: Array<String>) {
    for (arg in args) {
        print(arg)
    }
}

// 或者这样
fun forLoop2(args: Array<String>) {
    for (i in args.indices) {
        print(args[i])
    }
}

// while循环
fun whileLoop1(args: Array<String>) {
    var i = 0
    while (i < args.size)
        print(args[i++])
}

// when表达式
fun cases(obj: Any) {
    when (obj) {
        1 -> print("One")
        "Hello" -> print("Greeting")
        is Long -> print("Long")
        !is String -> print("Not a string")
        else -> print("Unknown")
    }
}

// 区间表达式
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

// 创建数据类 POJO
// 自动生成 getter/setter/equals/hashCode/toString/copy等
data class Customer(val name: String, val email: String)

//函数参数默认值
fun foo(a: Int = 0, b: String = "") {
    // do something
}

// 列表过滤
fun filters(list: Collection<Int>) {
    val positives1 = list.filter { x -> x > 0 }
    // 也可以这样
    val positives2 = list.filter { it > 0 }
}

// map遍历
fun maps(map: Map<String, String>) {
    for ((k, v) in map) {
        println("$k -> $v")
    }
}

// 只读列表
fun readOnlyList() {
    val list = listOf("a", "b", "c")
}

// 只读map
fun readOnlyMap() {
    val map1 = mapOf("a" to 1, "b" to 2, "c" to 3)
    println(map1["a"])
    //    map2["key"] = 123
}

// lazy属性
class LazyDemo {
    //    val p:String by lazy{
    //        // compute the string
    //    }
}

// 函数扩展
fun Int.biggerThanTen(): Boolean {
    return this > 10
}

// 扩展测试
fun extensions() {
    100.biggerThanTen()
    5.biggerThanTen()
}

// 单例模式
object Resource {
    val name = "Name"
}

//nullable用法
fun testNullable() {
    val files = File("Test").listFiles()
    println(files?.size)
    println(files?.size ?: "empty")
}

// when表达式
fun transform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}

// try catch用法
fun tryCatch() {
    val result = try {
        // do something
    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }

    // Working with result
}

// if表达式
fun ifExp(param: Int) {
    val result = if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else {
        "three"
    }
}

// with表达式
class Turtle {
    fun penDown() {
    }

    fun penUp() {
    }

    fun turn(degrees: Double) {
    }

    fun forward(pixels: Double) {
    }
}

// with用法
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


// main函数
fun main(args: Array<String>) {
}


// 编码约定
// 命名风格
/**
 * 1. 使用驼峰不要使用下划线
 * 2. 类型名是用大写字母开头
 * 3. 方法和属性名小写开头
 * 4. 使用四个空格的缩进
 * 5. 公开方法应该有文档
 *
 */

// 冒号前后有空格

// Lambdas
// 大括号两边有空格，箭头两边有空格
//  list.filter { it > 10 }.map { element -> element * 2 }

// Unit
// 如果一个函数返回Unit，应该忽略
fun noReturn(){}


