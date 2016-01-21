/**
 * User: mcxiaoke
 * Date: 16/1/21
 * Time: 11:15
 */

// 泛型
// Kotlin的类也可以有泛型参数,例如
class Box<T>(t: T) {
    var value = t
}

// 创建实例
val box1: Box<Int> = Box<Int>(1)
// 类型自动推导
// 1是Int类型,所以编译器知道我们用的是Box<Int>
val box2 = Box(1)

// 与Java的不同
// Java的泛型实现存在诸多限制
/**
// Java
List<String> strs = new ArrayList<String>();
List<Object> objs = strs; // !!! The cause of the upcoming problem sits here. Java prohibits this!
objs.add(1); // Here we put an Integer into a list of Strings
String s = strs.get(0); // !!! ClassCastException: Cannot cast Integer to String

// Java
void copyAll(Collection<Object> to, Collection<String> from) {
to.addAll(from); // !!! Would not compile with the naive declaration of addAll:
//       Collection<String> is not a subtype of Collection<Object>
}
 */

// Java
/**
interface Source<T> {
T nextT();
}

void demo(Source<String> strs) {
Source<Object> objects = strs; // !!! Not allowed in Java
// ...
}
 **/

// Java是使用位置变量
// Kotlin是声明位置变量
// Declaration-site variance
abstract class Source<out T> {
    abstract fun nextT(): T
}

fun demo1(strs: Source<String>) {
    // This is OK, since T is an out-parameter
    val objects: Source<Any> = strs
    // ...
}

// out被称作型变注释,协变 外边界
// in被称作逆变注释,逆变 内边界
abstract class Comparable1<in T> {
    abstract fun compareTo(other: T): Int
}

fun demo2(x: Comparable1<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, we can assign x to a variable of type Comparable<Double>
    val y: Comparable1<Double> = x // OK!
}

// 类型预测 Type projections
class Array1<T>(val size: Int) {
    //    fun get(index: Int): T {
    //        /* ... */
    //    }

    fun set(index: Int, value: T) {
        /* ... */
    }
}

fun copy1(from: Array<Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
}

fun test1() {
    val ints: Array<Int> = arrayOf(1, 2, 3)
    //    val any = Array<Any>(3)
    // 两个参数的类型必须一致
    //    copy(ints, any) // Error: expects (Array<Any>, Array<Any>)
}

// Array<out Any>对应Java中的Array<? extends Object>
fun copy2(from: Array<out Any>, to: Array<Any>) {
    // ...
}

// Array<in String>对应Java中的Array<? super String>
fun fill(dest: Array<in String>, value: String) {
    // ...
}

// 泛型函数
fun <T> singletonList(item: T): List<T>? {
    // ...
    return null
}

fun <T> T.basicToString(): String? {
    // extension function
    // ...
    return null
}

val l = singletonList<Int>(1)

// 泛型约束
// 上边界 upper bounds
fun <T : Comparable<T>> sort(list: List<T>) {
    // ...
}

fun test2() {
    sort(listOf(1, 2, 3)) // 没问题. Int是Comparable<Int>的子类型
    // sort(listOf(HashMap<Int, String>()))
    // 错误: HashMap<Int, String>不是Comparable<HashMap<Int, String>>的子类型
}

// 如果需要多个边界约束,可以用where语句
fun <T> stringsWhenGreater(list: List<T>, threshold: T): List<String>
        where T : Comparable<T>, T : Cloneable {
    return list.filter { it > threshold }.map { it.toString() }
}