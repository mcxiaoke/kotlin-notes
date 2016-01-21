/**
 * User: mcxiaoke
 * Date: 16/1/21
 * Time: 10:48
 */

// 扩展 Extensions
// 类似于C#,Kotlin的扩展提供了扩展一个已有类的能力
// Kotlin支持扩展函数和扩展属性

// 扩展函数
// 函数的名字要使用被扩展的类型的名字做前缀
// 下面的代码给MutableList<Int>类型增加了swap方法
// this引用被扩展类型的接受对象
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this'表示这个列表
    this[index1] = this[index2]
    this[index2] = tmp
}

fun ext1() {
    val l = arrayListOf(1, 2, 3)
    l.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'l'
}

// 还可以让这个函数更通用一点
fun <T> MutableList<T>.swap2(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this'表示这个列表
    this[index1] = this[index2]
    this[index2] = tmp
}

// 扩展是静态解析和分发的
// resolved and dispatched statically
// 扩展并没有真正修改它扩展的类,扩展不能给类增加新的成员
// 这意味着扩展函数是按照表达式的类型调用,而不是按照运行时表达式的结果的类型来调用
// 如下例子
open class CC

class DD : CC()

fun CC.foo() = "c"

fun DD.foo() = "d"

fun printFoo(c: CC) {
    println(c.foo())
}

fun ext2() {
    // printFoo的参数类型是CC,所以打印出来的是c
    printFoo(DD()) // print 'c'
}

// 如果这个类有相同签名的成员函数,成员函数总是胜出
class CCC {
    fun foo() {
        println("member")
    }
}

fun CCC.foo() {
    println("extension")
}

fun ext3() {
    var c = CCC()
    c.foo() // print ‘member’
}

// 可空接受者
// 可以定义nullable的接受者扩展
fun Any?.toString(): String {
    if (this == null) return "null"
    // after the null check, 'this' is auto cast to a non-null type,
    // so the toString() below
    // resolves to the member function of the Any class
    return toString()
}

// 扩展属性
val <T> List<T>.lastIndex: Int
    get() = size - 1

// 同伴对象的扩展
class MyClass {
    companion object {}  // will be called "Companion"
}

fun MyClass.Companion.foo() {
    // ...
}

fun ext4() {
    MyClass.foo()
}

// 扩展的作用域
// 使用import,详情参考import章节
