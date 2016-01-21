import java.util.*
import java.util.concurrent.locks.Lock
import javax.swing.tree.TreeNode

/**
 * User: mcxiaoke
 * Date: 16/1/21
 * Time: 13:24
 */

// 函数 Functions

// 函数声明使用fun关键字
fun double(x: Int): Int {
    return x * 2
}

val result = double(2)

// Define extension to Int
infix fun Int.mul(x: Int): Int {
    return this * x
}

// 函数调用支持中缀表达式
fun infix() {
    4 mul 2
    4.mul(2)
}

// 函数参数
fun powerOf(number: Int, exponent: Int) {
    println("power function")
}

// 默认参数
fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size) {
    // do something
}

// 命名参数
fun reformat(str: String,
             normalizeCase: Boolean = true,
             upperCaseFirstLetter: Boolean = true,
             divideByCamelHumps: Boolean = false,
             wordSeparator: Char = ' ') {
    // function body
}

// 注意:调用Java方法时不能使用命名参数
fun nameArgs() {
    var str = "Named Args Test"
    reformat(str)
    reformat(str, true, true, false, '_')
    reformat(str,
            normalizeCase = true,
            upperCaseFirstLetter = true,
            divideByCamelHumps = false,
            wordSeparator = '_'
    )
    reformat(str, wordSeparator = '_')
}

// 无返回值的函数可以省略Unit
fun printHello(name: String?): Unit {
    if (name != null)
        println("Hello $name")
    else
        println("Hi there!")
    // `return Unit` or `return` is optional
}

// 单行表达式函数可以省略大括号
fun double1(x: Int): Int = x * 2

fun double2(x: Int) = x * 2

// Kotlin不会自动推断有函数体的函数的返回值

// 可变数目参数使用vararg
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
    return result
}

// 如果可变参数不是列表的最后一个
// 之后的参数可以使用命名参数语法
fun varargs() {
    val list = asList(1, 2, 3)
}

// 还可以使用展开操作符*
fun spread() {
    val a = arrayOf(1, 2, 3)
    val list = asList(-1, 0, *a, 4)
    println(list) // [-1,0,1,2,3,4]
}

// 函数作用域
// Kotlin可以在顶级作用域声明函数,不需要创建一个类

// 局部函数 (其它函数内部的函数)
class Graph {
    var vertices: Array<Vertex> = arrayOf()
}

class Vertex {
    var neighbors: Set<Vertex> = setOf()
}

fun dfs1(graph: Graph) {
    fun dfs(current: Vertex, visited: MutableSet<Vertex>) {
        if (!visited.add(current)) return
        for (v in current.neighbors)
            dfs(v, visited)
    }

    dfs(graph.vertices[0], HashSet())
}

// 局部函数可以访问外层函数的局部变量
fun dfs2(graph: Graph) {
    val visited = HashSet<Vertex>()
    fun dfs(current: Vertex) {
        if (!visited.add(current)) return
        for (v in current.neighbors)
            dfs(v)
    }

    dfs(graph.vertices[0])
}

// 局部函数甚至可以从外层函数返回
fun reachable(from: Vertex, to: Vertex): Boolean {
    val visited = HashSet<Vertex>()
    fun dfs(current: Vertex) {
        // here we return from the outer function:
        // not allowed in v1.0.0-beta-4584
        //        if (current == to) return@reachable true
        // And here -- from local function:
        if (!visited.add(current)) return
        for (v in current.neighbors)
            dfs(v)
    }

    dfs(from)
    return false // if dfs() did not return true already
}

// 成员函数
// 类或对象内部的函数称作成员函数
class Sample() {
    fun foo() {
        print("Foo")
    }
}

fun members1() {
    Sample().foo()
}

// 泛型函数
fun <T> singletonList2(item: T): List<T> {
    // ...
    return listOf()
}

// 内联函数和扩展函数见相关章节

// 尾递归函数
// 使用tailrec关键字,函数的最后一个操作必须是调用自身
tailrec fun findFixPoint(x: Double = 1.0): Double
        = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))

// 对应的传统方式定义
private fun findFixPoint(): Double {
    var x = 1.0
    while (true) {
        val y = Math.cos(x)
        if (x == y) return y
        x = y
    }
}


// Lambda表达式

// 高阶函数是指接受函数作为参数,或者以函数作为返回值的函数
// body的类型是一个函数: () -> T
fun <T> lock(lock: Lock, body: () -> T): T {
    lock.lock()
    try {
        return body()
    } finally {
        lock.unlock()
    }
}

// lambda表达式
/**
 * 1. lambda表达式用大括号包裹
 * 2. lambda表达式的参数在 -> 之前声明
 * 3. lambda表示打的body在 -> 之后出现
 */

// Kotlin中,如果最后一个参数是函数,这个参数可以放在括号的后面
fun <T, R> List<T>.map1(transform: (T) -> R): List<R> {
    val result = arrayListOf<R>()
    for (item in this)
        result.add(transform(item))
    return result
}

fun fun2() {
    val ints: Collection<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    val doubled = ints.map { it -> it * 2 }
    println(doubled)
}

// 为方便,如果一个函数仅有一个参数,可以省略声明,参数名为it
fun fun3() {
    val ints: Collection<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    val doubled = ints.map { it * 2 }
    println(doubled)
    val strings: Collection<String> = listOf("Hello", "World", "Cat", "Rabbit")
    // LINQ风格的表达式
    //    strings.filter { it.length == 5 }.sortBy { it }.map { it.toUpperCase() }
}

// Lambda表达式和匿名函数
// lambda和匿名函数都是函数字面量
fun max(strings: Collection<String>, comp: (String, String) -> Boolean) {
}

fun compare(a: String, b: String): Boolean = a.length < b.length

fun fun4() {
    val strings: Collection<String> = listOf("Hello", "World", "Cat", "Rabbit")
    max(strings, { a, b -> a.length < b.length })
}

// 函数类型
// less的类型是 (T, T) -> Boolean
// val compare: (x: T, y: T) -> Int = ...
fun <T> max2(collection: Collection<T>, less: (T, T) -> Boolean): T? {
    var max: T? = null
    for (it in collection)
        if (max == null || less(max, it))
            max = it
    return max
}

// lambda表达式语法
val sum1 = { x: Int, y: Int -> x + y }

// 不省略参数声明的话是这样的
val sum2: (Int, Int) -> Int = { x, y -> x + y }

// 匿名函数
// 匿名函数和普通的函数差不多,但是没有名字
//fun(x: Int, y: Int): Int {
//    return x + y
//}
fun fun5() {
    val ints: Collection<Int> = listOf(-1, -2, -3, 0, 1, 2)
    // 匿名函数,参数不能省略,返回值自动推导
    ints.filter(fun(item) = item > 0)
    // lambda表达式,参数可以省略
    ints.filter { it > 0 }
}

// 闭包 Closures
// lambda表达式和匿名函数可以访问它的闭包
fun fun6() {
    val ints: Collection<Int> = listOf(-1, -2, -3, 0, 1, 2)
    var sum = 0
    ints.filter { it > 0 }.forEach {
        sum += it
    }
    print(sum)
}

//有Receiver的函数字面量
fun fun7() {
    val sum3 = fun Int.(other: Int): Int = this + other
    3.sum3(4)
    5.sum3(100)
}

// lambda表达式的隐式Receiver
class HTML {
    fun body() {
        /****/
    }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()  // create the receiver object
    html.init()        // pass the receiver object to the lambda
    return html
}

// 可用于创建DSL
fun fun8() {
    html { // lambda with receiver begins here
        body()   // calling a method on the receiver object
    }
}

// 内联函数
// 使用高阶函数是由代价的,运行时每个函数都是一个对象,而且它捕获了闭包变量
// 内存分配和许方法调用增加了运行时成本
// 内联函数生成代码时直接插入函数和lambda表达式的代码
// inline修饰符可以作用于函数和lambda表达式
// inline让生成的代码量增加了
//inline fun lock2<T>(lock: Lock, body: () -> T): T { }
// 可以同时使用noinline,如果你只想让某些lambda或函数内联

// 非局部返回
// 使用return只能从命名的普通函数或匿名函数返回
// 要想从lambda返回,需要使用一个标志
fun ordinaryFunction() {
}

inline fun inlineFunction() {
}

//fun foo1() {
//    ordinaryFunction {
//        //        return // ERROR: can not make `foo` return here
//    }
//}
//
//fun foo2() {
//    inlineFunction {
//        return // OK: the lambda is inlined
//    }
//}

// 位于lambda表达式里,但是从闭包函数返回成为非局部返回
// 这种形式行用于循环
fun hasZeros(ints: List<Int>): Boolean {
    ints.forEach {
        if (it == 0) return true // returns from hasZeros
    }
    return false
}

// 内联的lambda目前比支持break和continue
inline fun f(crossinline body: () -> Unit) {
    val f1 = Runnable { body() }
    // ...
}

// 具体化类型参数
// 又是我们需要访问传递给函数的参数的类型
fun <T> TreeNode.findParentOfType1(clazz: Class<T>): T? {
    var p = parent
    while (p != null && !clazz.isInstance(p)) {
        p = p.parent
    }
    @Suppress("UNCHECKED_CAST")
    return p as T
}

abstract class MyTreeNodeType : TreeNode {}
// 调用方法
//    myTree.findParentOfType2(MyTreeNodeType::class.java)

// 使用reified修饰符后,T就可以在内部访问了,像一个普通的类一样
// 现在可以这样调用
// myTree.findParentOfType<MyTreeNodeType>()
inline fun <reified T> TreeNode.findParentOfType(): T? {
    var p = parent
    while (p != null && p !is T) {
        p = p?.parent
    }
    return p as T
}

// 很多时候不需要反射,也可以用reified
inline fun <reified T> membersOf() = T::class.members

fun main(s: Array<String>) {
    println(membersOf<Any>().joinToString("\n"))
}

// 普通函数没有reified参数,inline函数才可以













