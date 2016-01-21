import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * User: mcxiaoke
 * Date: 16/1/21
 * Time: 12:14
 */

// 委托 Delegation

// 类委托 Class Delegation
// 代理模式是继承的很好的替代品,Kotlin支持委托
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() {
        print(x)
    }
}

// by关键字表示将所有Base的方法委托给b
class Derived(b: Base) : Base by b

fun delegate1() {
    val b = BaseImpl(10)
    Derived(b).print() // prints 10
}

// 委托属性
// 有很多公共类型的属性不需要每次都实现,可以放入一个库中:
// 1. 延迟属性:在第一次访问时才计算值
// 2. 可观察属性:属性值变化时观察者获得通知
// 3. 存储在map中的属性,不是单独的字段

// 委托属性的语法是:val/var <property name>: <Type> by <expression>
// 委托对象需要有 getValue/setValue方法

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name} in $thisRef.'")
    }
}

class Example {
    var p: String by Delegate()
}

fun delegate2() {
    val e = Example()
    e.p = "NewValue"
    println(e.p)
}

// 属性委托条件
/**
1. 对于只读属性,委托对象必须有一个getValue()方法,参数:
receiver - 必须是属性所有者同类型或者父类型
metadata - 必须是KProperty<*>及其父类型

2. 对于读写属性,委托对象还必须有一个setValue()方法,参数:
receiver - 要求同getValue
metadata - 要求同getValue
new value - 必须是属性的同类型或者父类型

3. getValue/setValue可以是成员函数,也可以是扩展函数

 **/

// Kotlin提供了一些标准类型的委托

// 延迟委托 Lazy
// 默认情况下,延迟属性的执行是同步的:值的计算在一个线程,其它所有线程看到相同的值
// 多个线程初始化用LazyThreadSafetyMode.PUBLICATION参数
// 如果不需要线程安全保证,用LazyThreadSafetyMode.NONE

val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}

fun lazy1() {
    println(lazyValue)
    println(lazyValue)
}

// 可观察对象 Observable
// Delegates.observable()接受两个参数,初始值和修改handler
// 每次修改属性值时(操作完成后),handler会被调用
// handler有三个参数:property, old value, new value

class User {
    // 另有一个vetoable在值被修改之前调用
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new")
    }
}

// out:
// <no name> -> first
// first -> second
fun observable1() {
    val user = User()
    user.name = "first"
    user.name = "second"
}

// 存储在Map中的属性
// 在Map中存储属性是一个很常见的用例,例如解析JSON或者其它动态数据

class User4(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}

fun map1() {
    val user = User4(mapOf(
            "name" to "John Doe",
            "age"  to 25
    ))
    println(user.name) // Prints "John Doe"
    println(user.age)  // Prints 25
}

// 可变属性使用MutableMap
class MutableUser(val map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int     by map
}


fun main(args: Array<String>) {
    map1()
}



