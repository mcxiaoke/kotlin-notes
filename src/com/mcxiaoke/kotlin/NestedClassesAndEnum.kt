/**
 * User: mcxiaoke
 * Date: 16/1/21
 * Time: 11:59
 */

// 内部类

// Nested Classes 相当于Java的静态内部类
class Outer2 {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
    }
}

val demo2 = Outer2.Nested().foo() // == 2

// Inner classes 相当于Java的内部类
class Outer3 {
    private val bar: Int = 1

    inner class Inner {
        fun foo() = bar
    }
}

val demo3 = Outer3().Inner().foo() // == 1

// 枚举 Enum Classes
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

// 枚举初始化
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

// 枚举的匿名类
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

// 枚举常量
fun enum1() {
    var color = Color.valueOf("0xFF0000")
    var colors = Color.values()
    var name = Color.GREEN.name
    var ord = Color.RED.ordinal
}