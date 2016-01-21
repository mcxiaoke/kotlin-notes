/**
 * User: mcxiaoke
 * Date: 16/1/21
 * Time: 11:06
 */

// 数据类 Data Classes
// 数据类只用于存储数据,使用data关键字
data class User1(val name: String, val age: Int)
// 按照主要构造器中定义的属性
// 编译器自动给数据类增加equals/hashCode/toString/copy()方法
// 如果某个成员在类内部定义了,就不会自动生成
// 数据类必须满足下列条件:
// 1. 主要构造器至少有一个参数
// 2. 所有的主要构造器参数必须使用var或val
// 3. 数据类不能是抽象类,封闭类,内部类
// 4. 数据类不能继承其它类（可以实现接口）

// 数据类如果想要生成一个无参构造器,必须为所有的参数指定默认值
data class User2(val name: String = "", val age: Int = 0)

// 复制
// 生成的copy函数用于复制某些属性

class User3(val name: String, val age: Int) {
    fun copy(name: String = this.name, age: Int = this.age) = User1(name, age)
}

// 可以这样使用
fun dc1() {
    val jack = User3(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)
}

// 分支函数
fun dc2() {
    val jane = User3("Jane", 35)
    //    val (name, age) = jane
    //    println("$name, $age years of age") // prints "Jane, 35 years of age"
}

// 数据类和析构声明见析构章节

// Pair和Triple是标准库提供的两个数据类
