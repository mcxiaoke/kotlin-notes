import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

/**
 * User: mcxiaoke
 * Date: 16/1/21
 * Time: 12:04
 */

// 对象表达式和声明

// 对象表达式
/**
window.addMouseListener(object : MouseAdapter() {
override fun mouseClicked(e: MouseEvent) {
// ...
}

override fun mouseEntered(e: MouseEvent) {
// ...
}
})
 **/

// 如果父类有构造器,必须初始化父类
// 使用逗号分隔多个父类
open class AA(x: Int) {
    public open val y: Int = x
}

interface BB {
    /****/
}

fun obj1() {
    val ab = object : AA(1), BB {
        override val y = 15
    }
}

// 如果需要一个没有任何父类的对象
fun obj2() {
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }
    print(adHoc.x + adHoc.y)
}

// 类似于Java的匿名内部类,可以访问闭包的变量
/**
fun countClicks(window: JComponent) {
var clickCount = 0
var enterCount = 0

window.addMouseListener(object : MouseAdapter() {
override fun mouseClicked(e: MouseEvent) {
clickCount++
}

override fun mouseEntered(e: MouseEvent) {
enterCount++
}
})
// ...
}
 */

// 对象声明使用object关键字
// Object declarations
// 声明一个单例
class DataProvider

object DataProviderManager {
    fun registerDataProvider(provider: DataProvider) {
        // ...
    }

    val allDataProviders: Collection<DataProvider>
        get() = listOf()// ...
}

// 也可以这样
object DefaultListener : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) {
        // ...
    }

    override fun mouseEntered(e: MouseEvent) {
        // ...
    }
}

// 同伴对象 Companion Objects
// 使用companion关键字
// 相当于Java中的static方法
class MyClass2 {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}

val instance = MyClass2.create()

// 同伴对象可以实现接口
interface Factory<T> {
    fun create(): T
}


class MyClass3 {
    companion object : Factory<MyClass3> {
        override fun create(): MyClass3 = MyClass3()
    }
}

// 在JVM上,同伴对象会生成真正的static方法和字段
// 有关@JvmStatic注解参考Java互操作章节

// 对象表达式和对象声明的区别
// 1. 对象声明是第一次访问时延迟初始化的
// 2. 对象表达式是使用时立即执行的


