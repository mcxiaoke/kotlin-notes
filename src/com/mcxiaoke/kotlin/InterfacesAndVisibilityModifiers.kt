/**
 * User: mcxiaoke
 * Date: 16/1/21
 * Time: 10:27
 */
// 接口
// Kotlin中的接口和Java 8类似,可以包含抽象方法的声明和实现,但是不能存储状态
// 可以包含属性,但是只能是抽象的,或者提供访问方法实现
// 接口定义使用interface关键字
interface MyInterface {
    fun bar()
    fun foo() {
        // optional body
    }
}

// 一个类或对象可实现一个或多个接口
class Child : MyInterface {
    override fun bar() {
        // body
    }
}

// 接口的属性
// 接口的属性是抽象的,否则必须提供访问访问
// 接口的属性没有支持字段,不能使用field访问
interface MyInterface2 {
    val property: Int // abstract

    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(property)
    }
}

class Child2 : MyInterface2 {
    override val property: Int = 29
}

// 解决覆写冲突
interface A {
    fun foo() {
        print("A")
    }

    fun bar()
}

interface B {
    fun foo() {
        print("B")
    }

    fun bar() {
        print("bar")
    }
}

class C : A {
    override fun bar() {
        print("bar")
    }
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super.bar()
    }
}


// 可见性修饰符
// 类,对象,接口,构造器,函数,属性和设置方法都可以有可见性修饰符
// Kotlin有四种可见性修饰符:private, protected, internal, public
// 如果没有修饰符,默认是public
// 函数,属性,类,对象和接口可以在顶级（某个package里）声明
// file name: example.kt
fun baz() {
}

class Bar {}

// 顶级声明如果没有任何标识符,默认是public,在任何地方都可见
// 如果使用private,只能在包含它的文件里可见
// 如果使用internal,只能在同一个模块可见
// protected不能用于顶级声明
// 举例
// file name: example.kt

private fun foo() {
} // 只在当前文件可见

public var bar: Int = 5 // 属性bar任何地方可见
    private set         // 设置方法只在当前文件科技

internal val baz = 6    // 只在同一模块可见

// 类和接口中的可见性
// private - 只在此类内部可见
// protected - 在类和它的直接子类内部可见
// internal - 模块的任何客户端都可见
// pubic - 对任何能看到这个类的客户端可见
// 注意: Kotlin外部类不能看到内部类的私有成员
// 举例
open class Outer {
    private val a = 1
    protected val b = 2
    internal val c = 3
    val d = 4  // public by default

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    // a 不可见
    // b, c 和 d 可见
    // Nested 和 e 可见
}

class Unrelated(o: Outer) {
    // o.a, o.b 不可见
    // o.c 和 o.d 可见 (同一个模块)
    // Outer.Nested 不可见, Nested::e 也不可见
}

// 构造器的可见性
// 构造器默认是public的,和类的可见性一样
class CR private constructor(a: Int) {
    /****/
}

// 局部变量,函数和类不能使用可见性修饰符

// 模块的可见性
// internal表示只在同一个模块里可见
// 模块表示编译在一起的一组Kotlin文件集合
// 如一个IDEA模块,一个Maven/Gradle工程,一个ANT任务编译的文件集合



