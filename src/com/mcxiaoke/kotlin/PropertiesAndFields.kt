package com.mcxiaoke.kotlin

import java.util.*

/**
 * User: mcxiaoke
 * Date: 2016/1/20
 * Time: 22:54
 */

// Kotlin的类支持属性
// 不变属性用val，可变属性用var
public class Address {
    public var name: String = ""
    public var street: String = ""
    public var city: String = ""
    public var state: String? = ""
    public var zip: String = ""
}

// 属性使用方法，就像Java中的字段一样
fun copyAddress(address: Address): Address {
    val result = Address() // there's no 'new' keyword in Kotlin
    result.name = address.name // accessors are called
    result.street = address.street
    // ...
    return result
}

// Getters和Setters
/**
var <propertyName>: <PropertyType> [= <property_initializer>]
[<getter>]
[<setter>]
 */
// 初始化块，设置方法和读取方法都是可选的，属性类型也是可选的
class Example2 {
    // var allByDefault: Int?
    // error: explicit initializer required, default getter and setter implied
    var initialized = 1 // has type Int, default getter and setter

    // 只读属性
    // val simple: Int? // has type Int, default getter, must be initialized in constructor
    val inferredType = 1 // has type Int and a default getter

    var size = 0

    fun setDataFromString(value: String) {
        // set value
    }

    // 自定义访问方法
    val isEmpty: Boolean
        get() = this.size == 0

    // 自定义设置方法
    var stringRepresentation: String
        get() = this.toString()
            // 设置方法默认参数是value，可以选择其它的名字
        set(value) {
            // parses the string and assigns values to other properties
            setDataFromString(value)
        }

    // 可以改变访问方法的可见性和修饰符
    var setterVisibility: String = "abc" // Initializer required, not a nullable type
        private set // the setter is private and has the default implementation

    // var setterWithAnnotation: Any?
    //    @Inject set // annotate the setter with Inject
}

// 支持字段

// Kotlin的类有属性，没有成员字段，但是可以使用field访问支持的字段
class Example3 {
    // field只能在属性的访问方法中使用
    var counter = 0 // the initializer value is written directly to the backing field
        set(value) {
            if (value >= 0)
                field = value
        }

    var size = 100

    // 也可能没有field
    val isEmpty: Boolean
        get() = this.size == 0
}

// 支持属性
class Example4 {
    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null)
                _table = HashMap() // Type parameters are inferred
            return _table ?: throw AssertionError("Set to null by another thread")
        }
}

// 编译时常量
// const常量属性：顶层或者object的成员，使用基础类型或String初始化，没有自定义访问方法
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

@Deprecated(SUBSYSTEM_DEPRECATED) fun foo() {
    /** do something **/
}

// 延迟初始化属性
// 通常，声明为非null的属性必须在构造器中初始化。然而这样有时候不方便
// 延迟初始化属性使用 lateinit 声明
// 使用var在类内部声明，不能再主要构造器中，不能有自定义的访问和设置方法
// 延迟初始化属性不能是null，也不能是原始类型
/**
public class LazyProperty {
    lateinit var subject: TestSubject

    @SetUp fun setup() {
        subject = TestSubject()
    }

    @Test fun test() {
        subject.method()  // dereference directly
    }
}
 **/

// 属性覆写参见成员覆写章节

// 委托属性见委托属性章节





