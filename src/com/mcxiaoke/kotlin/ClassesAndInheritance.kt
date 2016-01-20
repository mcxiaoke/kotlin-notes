package com.mcxiaoke.kotlin

import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

/**
 * User: mcxiaoke
 * Date: 2016/1/20
 * Time: 22:03
 */
// 类使用class关键字声明
class Invoice {}

// 大括号也可以忽略
class Empty

// 构造函数
// Kotlin的泪可以有一个主要构造器和一个或多个次要构造器
// 主要构造器放在类名字后面
class Person1 constructor(firstName: String) {}

// 如果主要构造器没有注解或者可见性修饰符，constructor关键字可以省略
class Person2(firstName: String)

// 主要构造器不能包含代码块，初始化代码块使用init关键字
class Customer2(name: String) {
    init {
        println("Customer initialized with value $name")
    }
}

// 初始化块中可以使用主要构造器的参数
// 初始化块中也可以用于属性初始化
class Customer3(name: String) {
    val customerKey = name.toUpperCase()
}

// 在主要构造器中声明并初始化属性的语法糖
class Person3(val firstName: String,
              val lastName: String,
              var age: Int) {
    // ...
}

// 如果主要构造器有注解或可见性修饰符，constructor就不能省略
class Customer4 public @Inject constructor(name: String) { //...
    //
}

// 类也可以声明次要构造器，前缀constructor
class Person4 {
    constructor(parent: Person1) {
        // do something
        //        parent.children.add(this)
    }
}

// 如果类有主要构造器，次要构造器必须调用主要构造器
class Person5(val name: String) {
    constructor(name: String, parent: Person1) : this(name) {
        // do something
        //        parent.children.add(this)
    }
}

// 如果非抽象类并且没有任何构造器，会生成一个无参的public默认构造器
// 如果想改变这个行为，可以这样
class DoNotCreateMe private constructor() {
}

// 创建类的实例
// Kotlin没有new关键字
val invoice = Invoice()
val customer = Customer2("Joe Smith")

// 类成员
// 类可以包含构造器和初始化块，函数，属性，内部类，对象声明

// 继承
// Kotlin中所有的类的公共基类是Any，也是没有父类的类的默认父类
class Example // 隐式继承自Any
// Any不是java.lang.Object，没有equals/hashCode/toString之外的成员

// 使用冒号声明显式继承
open class Base(p: Int)
class Derived(p: Int) : Base(p)

// 如果一个类有主要构造器，子类必须初始化父类的构造器参数
// 次要构造器使用super关键字初始化父类
/**
class MyView : View {
    constructor(ctx: Context) : super(ctx) {
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
    }
}
 **/

// class默认是final，除非使用open关键字

// 覆写类成员
// 需要显式声明，open和override都是必须的
// 使用override关键字的函数默认是open的，可以被子类覆盖
open class Base1 {
    open fun v() {
    }

    fun nv() {
    }
}

class Derived1() : Base1() {
    override fun v() {
    }
}

// 覆写规则
// 如果一个类从父类继承链继承了某个成员的多个实现，就必须覆写此成员，并提供自己的实现
// 可以调用父类的实现，示例如下
open class A {
    open fun f() { print("A") }
    fun a() { print("a") }
}

interface B {
    fun f() { print("B") } // interface members are 'open' by default
    fun b() { print("b") }
}

class C() : A(), B {
    // The compiler requires f() to be overridden:
    // 必须覆写函数f()
    override fun f() {
        super<A>.f() // call to A.f()
        super<B>.f() // call to B.f()
    }
}

// 抽象类
// 类和类的某些成员可以生命为abstract，这样的类必须是open的
open class Base2 {
    open fun f() {}
}

abstract class Derived2 : Base2() {
    override abstract fun f()
}

// 同伴对象 Companion Objects
// Kotlin的类没有static方法，可以使用包级函数代替
// 如果一定要类方法，可以使用同伴对象
class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}
val instance = MyClass.create()

// 密封类 Sealed Classes
// 密封类使用sealed修饰符，放在class前面，可以有内部子类
// 类似于Enum
// 密封类的子类不一定是内部类，可以放在任何地方
sealed class Expr {
    class Const(val number: Double) : Expr()
    class Sum(val e1: Expr, val e2: Expr) : Expr()
    object NotANumber : Expr()
}

// 密封类和when表达式搭配使用
fun eval(expr: Expr): Double = when(expr) {
    is Expr.Const -> expr.number
    is Expr.Sum -> eval(expr.e1) + eval(expr.e2)
    Expr.NotANumber -> Double.NaN
// the `else` clause is not required because we've covered all the cases
}





