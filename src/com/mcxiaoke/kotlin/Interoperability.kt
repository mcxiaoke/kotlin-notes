import java.util.*
import java.util.concurrent.Executors

/**
 * User: mcxiaoke
 * Date: 16/1/21
 * Time: 16:03
 */

// Java和Kotlin的互操作

// Kotlin中调用Java代码
// https://kotlinlang.org/docs/reference/java-interop.html

// 所有的Java代码都可以在Kotlin中调用
fun callJava1(source: List<Int>) {
    val list = ArrayList<Int>()
    // 'for'-loops work for Java collections:
    for (item in source)
        list.add(item)
    // Operator conventions work as well:
    for (i in 0..source.size - 1)
        list[i] = source[i] // get and set are called
}

// Getters和Setters自动转换为属性调用
fun callJava2() {
    val calendar = Calendar.getInstance()
    if (calendar.firstDayOfWeek == Calendar.SUNDAY) {
        // call getFirstDayOfWeek()
        calendar.firstDayOfWeek = Calendar.MONDAY       // call setFirstDayOfWeek()
    }
}

// Null安全
fun callJava3() {
    val list = ArrayList<String>() // 非空 (constructor result)
    list.add("Item")
    val size = list.size // 非空 (primitive int)
    val item = list[0] // platform type inferred (ordinary Java object)

    item.substring(1) // allowed, may throw an exception if item == null
    val nullable: String? = item // allowed, always works
    val notNull: String = item // allowed, may fail at runtime
}

// JDK类型注意
/**
T! 表示 “T or T?”,
(Mutable)Collection<T>! 表示 “Java collection of T may be mutable or not, may be nullable or not”,
Array<(out) T>! 表示 “Java array of T (or a subtype of T), nullable or not”
 */

// Kotlin和Java类型映射关系
/**
原始类型
Java type	Kotlin type
byte	kotlin.Byte
short	kotlin.Short
int	kotlin.Int
long	kotlin.Long
char	kotlin.Char
float	kotlin.Float
double	kotlin.Double
boolean	kotlin.Boolean

Java type	Kotlin type
int[]	kotlin.IntArray!
String[]	kotlin.Array<(out) String>!

引用类型
Java type	Kotlin type
java.lang.Object	kotlin.Any!
java.lang.Cloneable	kotlin.Cloneable!
java.lang.Comparable	kotlin.Comparable!
java.lang.Enum	kotlin.Enum!
java.lang.Annotation	kotlin.Annotation!
java.lang.Deprecated	kotlin.Deprecated!
java.lang.Void	kotlin.Nothing!
java.lang.CharSequence	kotlin.CharSequence!
java.lang.String	kotlin.String!
java.lang.Number	kotlin.Number!
java.lang.Throwable	kotlin.Throwable!

集合类型
Java type	Kotlin read-only type	Kotlin mutable type	Loaded platform type
Iterator<T>	Iterator<T>	MutableIterator<T>	(Mutable)Iterator<T>!
Iterable<T>	Iterable<T>	MutableIterable<T>	(Mutable)Iterable<T>!
Collection<T>	Collection<T>	MutableCollection<T>	(Mutable)Collection<T>!
Set<T>	Set<T>	MutableSet<T>	(Mutable)Set<T>!
List<T>	List<T>	MutableList<T>	(Mutable)List<T>!
ListIterator<T>	ListIterator<T>	MutableListIterator<T>	(Mutable)ListIterator<T>!
Map<K, V>	Map<K, V>	MutableMap<K, V>	(Mutable)Map<K, V>!
Map.Entry<K, V>	Map.Entry<K, V>	MutableMap.MutableEntry<K,V>	(Mutable)Map.(Mutable)Entry<K, V>!
 */

// Kotlin中Java的泛型
// Kotlin的泛型也只是编译时的
/**
Foo<? extends Bar> 变为 Foo<out Bar!>!
Foo<? super Bar> 变为 Foo<in Bar!>!
List 变为 List<*>!, i.e. List<out Any?>!
 */

// Java的数组
class JavaArrayExample {

    fun removeIndices(indices: IntArray) {
        // code here...
    }
}

// 数组和可变参数列表
fun javaArray1() {
    val javaObj = JavaArrayExample()
    val array = intArrayOf(0, 1, 2, 3)
    javaObj.removeIndices(array)  // passes int[] to method

    val array2 = arrayOf(1, 2, 3, 4)
    array[x] = array[x] * 2 // no actual calls to get() and set() generated
    for (x in array) // no iterator created
        print(x)

    for (i in array2.indices) // no iterator created
        array[i] += 2
}

// 对象方法
fun callJava4(x: Any) {
    (x as java.lang.Object).wait()
    val fooClass = x.javaClass
    val fooClass2 = Foo::class.java

}

class Example2 : Cloneable {
    override fun clone(): Any {
        /****/
        return Example2()
    }

    protected fun finalize() {
        // finalization logic
    }
}

//SAM用法,类似于Java 8
fun callJava5() {
    val runnable = Runnable { println("This runs in a runnable") }
    val executor = Executors.newCachedThreadPool()
    // Java signature: void execute(Runnable command)
    executor.execute { println("This runs in a thread pool") }
    executor.execute(Runnable { println("This runs in a thread pool") })
}


// Java中调用Kotlin代码
// https://kotlinlang.org/docs/reference/java-to-kotlin-interop.html

// 生成的Java类的名字可以用 @JvmName注解改变
/**
@file:JvmName("DemoUtils")
package demo
class Foo
fun bar() {
}
 **/

// 字段
// 如果想将Kotlin的属性导出为Java的成员字段
// 可以使用 @JvmField 注解
/**
class C(id: String) {
@JvmField val ID = id
}
// Java
class JavaClient {
public String getID(C c) {
return c.ID;
}
}
 */

// 静态方法和字段使用@JvmStatic
/**
class C {
companion object {
@JvmStatic fun foo() {}
fun bar() {}
}
}

C.foo(); // works fine
C.bar(); // error: not a static method

object Obj {
@JvmStatic fun foo() {}
fun bar() {}
}

Basic Syntax
Idioms
Coding Conventions
Basics
Classes and Objects
Functions and Lambdas
Functions
Lambdas
Inline Functions
Other
Destructuring Declarations
Ranges
Type Checks and Casts
This expressions
Equality
Operator overloading
Null Safety
Exceptions
Annotations
Reflection
Type-Safe Builders
Dynamic Type
Reference
API Reference
Grammar
Interop
Calling Java from Kotlin
Calling Kotlin from Java
Tools
FAQ
Full Kotlin Reference
Edit Page
Calling Kotlin from Java

Kotlin code can be called from Java easily.

Properties

Property getters are turned into get-methods, and setters – into set-methods.

Package-Level Functions

All the functions and properties declared in a file example.kt inside a package org.foo.bar are put into a Java class named org.foo.bar.ExampleKt.

// example.kt
package demo

class Foo

fun bar() {
}
// Java
new demo.Foo();
demo.ExampleKt.bar();
The name of the generated Java class can be changed using the @JvmName annotation:

@file:JvmName("DemoUtils")

package demo

class Foo

fun bar() {
}
// Java
new demo.Foo();
demo.DemoUtils.bar();
Having multiple files which have the same generated Java class name (the same package and the same name or the same @JvmName annotation) is normally an error. However, the compiler has the ability to generate a single Java facade class which has the specified name and contains all the declarations from all the files which have that name. To enable the generation of such a facade, use the @JvmMultifileClass annotation in all of the files.

// oldutils.kt
@file:JvmName("Utils")
@file:JvmMultifileClass

package demo

fun foo() {
}
// newutils.kt
@file:JvmName("Utils")
@file:JvmMultifileClass

package demo

fun bar() {
}
// Java
demo.Utils.foo();
demo.Utils.bar();
Fields

If you need to expose a Kotlin property as a field in Java, you need to annotate it with the @JvmField annotation. The field will have the same visibility as the underlying property. You can annotate a property with @JvmField if it has a backing field, is not private, does not have open, override or const modifiers, and is not a delegated property.

class C(id: String) {
@JvmField val ID = id
}
// Java
class JavaClient {
public String getID(C c) {
return c.ID;
}
}
Static Methods and Fields

As mentioned above, Kotlin generates static methods for package-level functions. Kotlin can also generate static methods for functions defined in named objects or companion objects if you annotate those functions as @JvmStatic. For example:

class C {
companion object {
@JvmStatic fun foo() {}
fun bar() {}
}
}
Now, foo() is static in Java, while bar() is not:

C.foo(); // works fine
C.bar(); // error: not a static method
Same for named objects:

object Obj {
@JvmStatic fun foo() {}
fun bar() {}
}
In Java:

Obj.foo(); // works fine
Obj.bar(); // error
Obj.INSTANCE.bar(); // works, a call through the singleton instance
Obj.INSTANCE.foo(); // works too

object Obj {
val CONST = 1
}
const val MAX = 239

In Java:
int c = Obj.CONST;
int d = ExampleKt.MAX;
 */

// 使用@JvmName解决签名冲突
/**
这里编译后类型擦除后会冲突
fun List<String>.filterValid(): List<String>
fun List<Int>.filterValid(): List<Int>

解决办法:
fun List<String>.filterValid(): List<String>
@JvmName("filterValidInt")
fun List<Int>.filterValid(): List<Int>

val x: Int
@JvmName("getX_prop")
get() = 15

fun getX() = 10
 */

// 方法重载
/**

根据参数默认值生成不同的方法
@JvmOverloads fun f(a: String, b: Int = 0, c: String = "abc") {
...
}

// Java
void f(String a, int b, String c) { }
void f(String a, int b) { }
void f(String a) { }
 */


