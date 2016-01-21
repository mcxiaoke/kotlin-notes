package com.mcxiaoke.kotlin.stdlib

/**
 * User: mcxiaoke
 * Date: 16/1/21
 * Time: 16:28
 */

// Package kotlin
// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/index.html
// 核心包,包含主要数据类型的定义

// 数组构造函数
// 每种原始类型都有对应的数组类型

fun createArray() {
    var a1 = Array(10, fun(i): Int {
        return i * i
    })
    println(a1.joinToString())

}

fun createBooleanArray() {
    var a1 = BooleanArray(10, fun(i): Boolean {
        return i % 2 == 0
    })
    println(a1.joinToString())
}

// 数组的工厂方法,也是每个原始类型都有一个
fun createArray2() {
    val a = arrayOf(1, 2, 3, 4, 5)
    val b = booleanArrayOf(true, false, true, true)
}

fun main(args: Array<String>) {
    createArray()
    createBooleanArray()
    createArray2()
}

