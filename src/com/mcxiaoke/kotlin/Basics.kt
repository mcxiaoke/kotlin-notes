package com.mcxiaoke.kotlin

/**
 * User: mcxiaoke
 * Date: 2016/1/20
 * Time: 21:17
 */

// 基础类型

// 数字
// 与Java类似，但是不完全一样
// Kotlin提供下列内置类型
/**
Type	Bit width
Double	64
Float	32
Long	64
Int	32
Short	16
Byte	8
 *
 */

// 字面量
/**
十进制: 123
Long类型 以L结尾: 123L
十六进制: 0x0F
二进制: 0b00001011
注意：不支持八进制.

也支持下列类型的浮点数:

默认是Double: 123.5, 123.5e10
Float类型以F或f结尾: 123.5f
 *
 */

// 表现方式

fun box1() {
    val a: Int = 10000
    print(a === a) // Prints 'true'
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    print(boxedA === anotherBoxedA) // !!!Prints 'false'!!!
}

fun box2() {
    val a: Int = 10000
    print(a == a) // Prints 'true'
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    print(boxedA == anotherBoxedA) // Prints 'true'
}

// 显式转换
// 小数类型不是大数类型的子类型，所以下面的代码编译不过
fun conversation1() {
    // Hypothetical code, does not actually compile:
    val a: Int? = 1 // A boxed Int (java.lang.Integer)
    // val b: Long? = a // implicit conversion yields a boxed Long (java.lang.Long)
    // print(a == b) // Surprise! This prints "false" as Long's equals() check for other part to be Long as well
    val b: Byte = 1 // OK, literals are checked statically
    // val i: Int = b // ERROR
    val i: Int = b.toInt() // OK: explicitly widened
}

// 所有的数字类型都支持下列转换
/**
oByte(): Byte
toShort(): Short
toInt(): Int
toLong(): Long
toFloat(): Float
toDouble(): Double
toChar(): Char
 **/

// 隐式类型转换很少见
fun implicit() {
    val l = 1L + 3 // Long + Int => Long
}

// 操作符
//Kotlin支持标准的算术操作符
// 还支持位操作
fun bitwise() {
    val x = (1 shl 2) and 0x000FF000
}

/**
shl(bits) – signed shift left (Java’s <<)
shr(bits) – signed shift right (Java’s >>)
ushr(bits) – unsigned shift right (Java’s >>>)
and(bits) – bitwise and
or(bits) – bitwise or
xor(bits) – bitwise xor
inv() – bitwise inversion
 **/

// 字符
// 字符是Char类型，不能当作数字，使用单引号包含
fun testChar(c: Char) {
    //    if (c == 1) { // ERROR: incompatible types
    // ...
    //    }
}

// 布尔值
// 两个值：true和false， &&和||和!三种操作

// 数组
// Kotlin的数组是Array类型，有get和set方法[]，size属性
/**
class Array<T> private constructor() {
val size: Int
fun get(index: Int): T
fun set(index: Int, value: T): Unit

fun iterator(): Iterator<T>
// ...
}
 **/

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

// 字符串 String
// 字符串是不可变的，可以通过[i]访问单个字符
fun testString1(str: String) {
    for (c in str) {
        println(c)
    }
}

// 字符串字面量
fun testString2() {
    val s = "Hello, world!\n"
    // 或者三引号
    val text = """
    for (c in "foo")
        print(c)
    """
}

// 字符串模板
fun stringTemplate() {
    val i = 10
    val s = "i = $i" // evaluates to "i = 10"
    val x = "abc"
    val str = "$x.length is ${x.length}" // evaluates to "abc.length is 3"
    // 可以包含反义字符
    val price = "${'$'}9.99"
}


// 包
// package和Java类似，import也类似
/**
import foo.Bar // Bar is now accessible without qualification
import foo.* // everything in 'foo' becomes accessible
 **/

// 还可以使用as关键词解决命名冲突
/**
import foo.Bar // Bar is accessible
import bar.Bar as bBar // bBar stands for 'bar.Bar'
 **/

// import还可以用于导入顶层函数和属性，可以导入对象内的函数和属性，导入enum常量
// 顶层声明的默认可见性是private


// 控制流

// if表达式
fun ifExp1(a: Int, b: Int) {
    // Traditional usage
    var max1 = a
    if (a < b)
        max1 = b

    // With else
    var max2: Int
    if (a > b)
        max2 = a
    else
        max2 = b

    // As expression
    val max3 = if (a > b) a else b
}

// 分支里最后一个表达式的值是返回值
fun ifExp2(a: Int, b: Int) {
    val max = if (a > b) {
        print("Choose a")
        a
    } else {
        print("Choose b")
        b
    }
}

fun Int.isOdd(): Boolean {
    return this % 2 != 0
}

fun Int.isEven(): Boolean {
    return this % 2 == 0
}

// when表达式
// 匹配所有流程直到匹配上
fun whenExp(x: Int) {
    when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        else -> {
            // Note the block
            print("x is neither 1 nor 2")
        }
    }

    // 还可以使用逗号
    when (x) {
        0, 1 -> print("x == 0 or x == 1")
        else -> print("otherwise")
    }

    // 还可以使用表达式
    val s = "123"
    when (x) {
        parseInt(s) -> print("s encodes x")
        else -> print("s does not encode x")
    }

    // 使用range和collection
    val validNumbers = intArrayOf(1, 2, 3, 4, 100)
    when (x) {
        in 1..10 -> print("x is in the range")
        in validNumbers -> print("x is valid")
        !in 10..20 -> print("x is outside the range")
        else -> print("none of the above")
    }

    // 使用is检查值
    val hasPrefix = when (s) {
        is String -> s.startsWith("prefix")
        else -> false
    }

    // 代替if-else if
    val n = 100
    when {
        n.isOdd() -> print("x is odd")
        n.isEven() -> print("x is even")
        else -> print("x is funny")
    }
}

// for循环
fun forExp1(items: Collection<Int>) {
    for (item in items)
        print(item)
}

// 或者这样
fun forExp2(items: Array<Int>) {
    for (i in items.indices)
        print(items[i])
}

// 使用with
fun forExp3(array: Array<Int>) {
    for ((index, value) in array.withIndex()) {
        println("the element at $index is $value")
    }
}

// while循环
fun whileExp(x: Int) {
    while (x > 0) {
        //        x--
    }

    do {
        val y: String? = "String"
        //        val y = retrieveData()
    } while (y != null) // y is visible here!
}

// Kotlin也支持传统的break和continue


// 返回值和跳转
/**
三种跳转操作
return. 从最里层的函数或者匿名函数返回
break. 结束最里层的循环.
continue. 跳到最里层的循环的下一步.
 **/

// 返回和跳转标志
// break label
fun jumpExp1() {
    loop@ for (i in 1..100) {
        for (j in 1..100) {
            if (i > 50)
                break@loop
        }
    }
}

// return label
// Kotlin支持函数闭包
// 从lambda表达式返回
fun jumpExp2(ints: Array<Int>) {
    fun foo() {
        ints.forEach {
            if (it == 0) return
            print(it)
        }
    }
}

// 从闭包函数返回
fun jumpExp3(ints: Array<Int>) {
    ints.forEach lit@ {
        if (it == 0) return@lit
        print(it)
    }
}

// 从闭包函数返回，也可以这样用
fun jumpExp4(ints: Array<Int>) {
    ints.forEach {
        if (it == 0) return@forEach
        print(it)
    }
}

// 或者使用匿名函数，而不是lambda表达式
fun jumpExp5(ints: Array<Int>) {
    ints.forEach(fun(value: Int) {
        if (value == 0) return
        print(value)
    })
}



