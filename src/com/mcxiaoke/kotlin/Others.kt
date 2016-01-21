import com.mcxiaoke.kotlin.parseInt
import org.w3c.dom.Node
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter

/**
 * User: mcxiaoke
 * Date: 16/1/21
 * Time: 14:58
 */

// 解构声明 Destructuring Declarations
// 有时候需要将一个对象解构为几个变量,例如
// val (name, age) = person
// 解构声明相当于下面的代码
// val name = person.component1()
// val age = person.component2()
// 解构声明在for循环中也可以使用
// for ((a, b) in collection) { ... }

// 示例,从一个函数返回两个值
// Data Class 数据类自动生成componentN()函数

class Status

data class Person(val name: String, val age: Int)
data class Result(val result: Int, val status: Status)

fun function(): Result {
    // computations
    return Result(result, Status())
}

fun destruct1() {
    val (result, status) = function()
    val person = Person("Cat", 5)
    val (name, age) = person
}

// 示例: 解构声明和Map
fun destruct2(map: Map<Int, String>) {
    for ((key, value) in map) {
        // do something with the key and the value
    }
}

/**
标准库提供的方法
operator fun <K, V> Map<K, V>.iterator(): Iterator<Map.Entry<K, V>> = entrySet().iterator()
operator fun <K, V> Map.Entry<K, V>.component1() = getKey()
operator fun <K, V> Map.Entry<K, V>.component2() = getValue()
 **/

// 区间 Range
// ClosedRange<T>

fun range1(i: Int) {
    if (i in 1..10) {
        // equivalent of 1 <= i && i <= 10
        println(i)
    }
    for (j in 1..4) print(j) // prints "1234"
    for (j in 4..1) print(j) // prints nothing
    for (j in 4 downTo 1) print(j) // prints "4321"
}

// Range rangeTo()
/**
class Int {
//...
operator fun rangeTo(other: Long): LongRange = LongRange(this, other)
//...
operator fun rangeTo(other: Int): IntRange = IntRange(this, other)
//...
}
 **/

// Range downTo()
fun Long.downTo(other: Int): LongProgression {
    return LongProgression.fromClosedRange(this, other as Long, -1)
}

fun Byte.downTo(other: Int): IntProgression {
    return IntProgression.fromClosedRange(this as Int, other, -1)
}

// Range reversed()
fun IntProgression.reversed(): IntProgression {
    return IntProgression.fromClosedRange(last, first, -step)
}

// Range step()
fun IntProgression.step(step: Int): IntProgression {
    if (step <= 0) throw IllegalArgumentException("Step must be positive, was: $step")
    return IntProgression.fromClosedRange(first, last, if (this.step > 0) step else -step)
}

fun CharProgression.step(step: Int): CharProgression {
    if (step <= 0) throw IllegalArgumentException("Step must be positive, was: $step")
    return CharProgression.fromClosedRange(first, last, step)
}

fun range2() {
    (1..12 step 2).last == 11  // progression with values [1, 3, 5, 7, 9, 11]
    (1..12 step 3).last == 10  // progression with values [1, 4, 7, 10]
    (1..12 step 4).last == 9   // progression with values [1, 5, 9]
}

// 类型检查和强制转换 Type Checks and Casts

// is和!is操作符
// 用于运行时判断对象的类型
fun typeCheck(obj: Any) {
    if (obj is String) {
        print(obj.length)
    }

    if (obj !is String) {
        // same as !(obj is String)
        print("Not a String")
    } else {
        print(obj.length)
    }
}

// 智能类型转换
// if判断之后就知道类型了
fun cast1(x: Any) {
    if (x is String) {
        print(x.length) // x is automatically cast to String
    }
}

fun cast2(x: Any) {
    // x is automatically cast to string on the right-hand side of `||`
    if (x !is String || x.length == 0) return

    // x is automatically cast to string on the right-hand side of `&&`
    if (x is String && x.length > 0)
        print(x.length) // x is automatically cast to String
}

fun cast3(x: Any) {
    when (x) {
        is Int -> print(x + 1)
        is String -> print(x.length + 1)
        is IntArray -> print(x.sum())
    }
}

// 智能类型检查和转换的规则:
/**
 * 1. val局部变量 - 总是转换
 * 2. val属性 - 适用于同一个模块的private或internal属性
 * 3. var局部变量 - 如果在检查和使用之间没有修改,没有比捕获的lambda修改
 * 4. var属性 - 不适用
 */


// 使用as操作符
fun cast4(y: Any) {
    // 不安全的类型转换
    val x: String = y as String
    //"安全"的类型转换
    val x2: String? = y as? String
}

// This表达式
// 对于类的成员,this指向当前类的对象
// 对于扩展函数或函数字面量,this指向接受者参数指向的对象,即圆点的左侧
// 如果没有限制,this指向最里层的作用域闭包

// this限制
class AO { // implicit label @A
    inner class B { // implicit label @B
        fun Int.foo() {
            // implicit label @foo
            val a = this@AO // A's this
            val b = this@B // B's this

            val c = this // foo()'s receiver, an Int
            val c1 = this@foo // foo()'s receiver, an Int

            //            val funLit = @lambda {String.() ->
            //                val d = this // funLit's receiver
            //                val d1 = this@lambda // funLit's receiver
            //            }

            //            val funLit2 = { (s: String) ->
            //                // foo()'s receiver, since enclosing lambda expression
            //                // doesn't have any receiver
            //                val d1 = this
            //            }
        }
    }
}

// 相等性 Equality
// 检查两个引用是否是同一个用三引号 ===
// 检查内容是否相同用双引号 ==
// a === b 等价于 a?.equals(b) ?: (b === null)

// 操作符重载 Operator overloading

// 一元操作符
/**
Expression	Translated to
+a	a.unaryPlus()
-a	a.unaryMinus()
!a	a.not()

Expression	Translated to
a++	a.inc() + see below
a--	a.dec() + see below
 */

// 二元操作符
/**
Binary operations

Expression	Translated to
a + b	a.plus(b)
a - b	a.minus(b)
a * b	a.times(b)
a / b	a.div(b)
a % b	a.mod(b)
a..b	a.rangeTo(b)

Expression	Translated to
a in b	b.contains(a)
a !in b	!b.contains(a)

For in and !in the procedure is the same, but the order of arguments is reversed.

Symbol	Translated to
a[i]	a.get(i)
a[i, j]	a.get(i, j)
a[i_1, ..., i_n]	a.get(i_1, ..., i_n)
a[i] = b	a.set(i, b)
a[i, j] = b	a.set(i, j, b)
a[i_1, ..., i_n] = b	a.set(i_1, ..., i_n, b)

Symbol	Translated to
a()	a.invoke()
a(i)	a.invoke(i)
a(i, j)	a.invoke(i, j)
a(i_1, ..., i_n)	a.invoke(i_1, ..., i_n)

Expression	Translated to
a += b	a.plusAssign(b)
a -= b	a.minusAssign(b)
a *= b	a.timesAssign(b)
a /= b	a.divAssign(b)
a %= b	a.modAssign(b)

Symbol	Translated to
a > b	a.compareTo(b) > 0
a < b	a.compareTo(b) < 0
a >= b	a.compareTo(b) >= 0
a <= b	a.compareTo(b) <= 0
 */


// Null安全 Null Safety
// Kotlin的类型系统目的在于消除空指针引用

// ?: 操作符
fun null1(b: String?) {
    var a: String = "abc"
    //    a = null // compilation error
    val l = a.length
    //    val l = b.length // error: variable 'b' can be null
    val l2 = if (b != null) b.length else -1

    if (b != null && b.length > 0)
        print("String of length ${b.length}")
    else
        print("Empty string")

    // safe call
    b?.length

    val l3: Int = if (b != null) b.length else -1
    // 等价于
    val l4 = b?.length ?: -1

    // 强制解包nullable
    // 可能会造成空指针异常
    val l5 = b!!.length
}

fun null2(v: Any) {

    // 安全类型转换
    val aInt: Int? = v as? Int
}

fun foo(node: Node): String? {
    val parent = node.parentNode ?: return null
    val name = node.nodeName ?: throw IllegalArgumentException("name expected")
    return name
}

// 异常 Exceptions
// Kotlin中所有的异常都继承自Throwable
class SomeException(message: String?) : Exception(message) {

}

fun error1() {
    try {
        // some code
    } catch (e: SomeException) {
        // handler
    } finally {
        // optional finally block
    }
}

// try是一个表达式,有返回值
fun try1(input: String) {
    val a: Int? = try {
        parseInt(input)
    } catch (e: NumberFormatException) {
        null
    }
}

// 注解 Annotations

// 注解声明
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,
        AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION,
        AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
public annotation class Fancy

// 注解使用
@Fancy class Foo {
    @Fancy fun baz(@Fancy foo: Int): Int {
        return (@Fancy 1)
    }
}

// 如果想给主要构造器加注解,不能省略constructor关键字
class Foo2 @Fancy constructor(dependency: String) {
    var x: String? = null
        @Fancy set
}

// 注解也可以有构造器
// 构造器允许的参数类型:
// 原始类型,字符串,类,Enum,其它注解,上述类型的数组
annotation class Special(val why: String)

@Special("example") class Foo3 {}

// 注解作为其它注解的参数时,不需要@符号
public annotation class ReplaceWith(val expression: String)

public annotation class Deprecated(
        val message: String,
        val replaceWith: ReplaceWith = ReplaceWith(""))

@Deprecated("This function is deprecated, use === instead", ReplaceWith("this === other"))
class Foo4 {}

// 注解也可以用于lambda表达式
annotation class SuspendAble

val fa = @SuspendAble { Thread.sleep(10) }

// 注解使用位置  Annotation Use-site Targets
annotation class Ann

class AnnotationExample(@field:Ann val foo: Int, // annotate Java field
                        @get:Ann val bar: String, // annotate Java getter
                        @param:Ann val quux: Double)   // annotate Java constructor parameter{
// }

// Kotlin百分百兼容Java的注解
// 注意:实际上不是,比如ButterKnife使用就有问题


// 反射 Reflection

// 类
fun reflection1() {
    val kc = MyClass::class // KClass
    val c = MyClass::class.java // java class
}

fun isOdd(x: Int) = x % 2 != 0

// 函数引用
fun reflection2() {

    val numbers = listOf(1, 2, 3)
    // ::isOdd 是函数类型 (Int) -> Boolean 的值
    println(numbers.filter(::isOdd)) // prints [1, 3]
}

// 函数组合
// compose(f, g) = f(g(*))
fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}

fun compose1() {
    fun length(s: String) = s.length

    val oddLength = compose(::isOdd, ::length)
    val strings = listOf("a", "ab", "abc")

    println(strings.filter(oddLength)) // Prints "[a, abc]"
}

// 属性引用可以使用::操作符
var x = 1

fun propRef1() {
    // ::x KProperty<Int>
    println(::x.get()) // prints "1"
    ::x.set(2)
    println(x)         // prints "2"

    val strs = listOf("a", "bc", "def")
    println(strs.map(String::length)) // prints [1, 2, 3]
}

//引用类的属性
class ABC(val p: Int)

fun propRef2() {
    val prop = ABC::p
    println(prop.get(ABC(1))) // prints "1"
}

// 扩展的属性引用
val String.lastChar: Char
    get() = this[length - 1]

fun propRef3() {
    println(String::lastChar.get("abc")) // prints "c"
}

// 与Java反射的互操作
class ACC(val p: Int)

fun javaReflect1() {
    println(ACC::p.javaGetter) // prints "public final int A.getP()"
    println(ACC::p.javaField)  // prints "private final int A.p"
}

// 构造器引用
class Foo5

fun function(factory: () -> Foo5) {
    val x: Foo5 = factory()
}

fun reflection3() {
    function(::Foo5)
}
