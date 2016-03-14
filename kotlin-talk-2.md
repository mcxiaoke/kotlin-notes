# Class

```kotlin
class Invoice {
}

class Empty

class Person constructor(firstName: String) {
}

class Person(firstName: String) {
}
```

---

# Class

```kotlin
class Person(name: String) {
    init {
        logger.info("Person initialized with value ${name}")
    }
}

class Person(name: String) {
    val customName = name.toUpperCase()
}

class Person(val firstName: String, val lastName: String, var age: Int) {
  // ...
}
```

---

# Class

```kotlin
class DontCreateMe private constructor () {
}

class Customer(val customerName: String = "")
```

---

# Class

```kotlin
val invoice = Invoice()

val customer = Customer("Joe Smith")
```

---

# Inheritance

```kotlin
class Example // Implicitly inherits from Any

open class Base(p: Int)

class Derived(p: Int) : Base(p)
```

---

# Constructor

```kotlin
class MyView : View {
    constructor(ctx: Context) : super(ctx) {
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
    }
}
```

---

# Inheritance

```kotlin
open class Base {
  open fun v() {}
  fun nv() {}
}
class Derived() : Base() {
  override fun v() {}
}

open class AnotherDerived() : Base() {
  final override fun v() {}
}
```

---

# Override

```kotlin
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
  override fun f() {
    super<A>.f() // call to A.f()
    super<B>.f() // call to B.f()
  }
}
```

---

# Sealed Class

```kotlin
sealed class Expr {
    class Const(val number: Double) : Expr()
    class Sum(val e1: Expr, val e2: Expr) : Expr()
    object NotANumber : Expr()
}

fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
    // the `else` clause is not required because we've covered all the cases
}
```

---

# Properties

```kotlin
public class Address {
  public val code:Int = 10015
  public var name: String = ...
  public var street: String = ...
  public var city: String = ...
  public var state: String? = ...
  public var zip: String = ...
}

fun copyAddress(address: Address): Address {
  val result = Address() // there's no 'new' keyword in Kotlin
  result.name = address.name // accessors are called
  result.street = address.street
  // ...
  return result
}
```


---

# Getter/Setter

```kotlin
val isEmpty: Boolean
  get() = this.size == 0
  
var stringRepresentation: String
  get() = this.toString()
  set(value) {
    setDataFromString(value) // parses the string and assigns values to other properties
  }
  
var setterVisibility: String = "abc" // Initializer required, not a nullable type
  private set // the setter is private and has the default implementation

var setterWithAnnotation: Any?
  @Inject set // annotate the setter with Inject
```

---

# Field

```kotlin
private var _table: Map<String, Int>? = null
public val table: Map<String, Int>
  get() {
    if (_table == null)
      _table = HashMap() // Type parameters are inferred
    return _table ?: throw AssertionError("Set to null by another thread")
  }
```

---

# Constants

```kotlin
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"
const val SOCKET_TIMEOUT = 30*1000L
```

---

# Lazy Init

```kotlin
public class MyTest {
    lateinit var subject: TestSubject

    @SetUp fun setup() {
        subject = TestSubject()
    }

    @Test fun test() {
        subject.method()  // dereference directly
    }
}
```

---

# Interface

```kotlin
interface MyInterface {
    fun bar()
    fun foo() {
      // optional body
    }
}

class Child : MyInterface {
   override fun bar() {
      // body
   }
}
```

---

# Interface

```kotlin
interface MyInterface {
    val property: Int // abstract

    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(property)
    }
}

class Child : MyInterface {
    override val property: Int = 29
}
```

---

# Override

```kotlin
interface A {
  fun foo() { print("A") }
  fun bar()
}

interface B {
  fun foo() { print("B") }
  fun bar() { print("bar") }
}

class C : A {
  override fun bar() { print("bar") }
}

class D : A, B {
  override fun foo() {
    super<A>.foo()
    super<B>.foo()
  }
}
```

---

# Visibility

```kotlin
open class Outer {
    private val a = 1
    protected val b = 2
    val c = 3 // internal by default
    public val d: Int = 4 // return type required

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    // a is not visible
    // b, c and d are visible
    // Nested and e are visible
}

class Unrelated(o: Outer) {
    // o.a, o.b are not visible
    // o.c and o.d are visible (same module)
    // Outer.Nested is not visible, and Nested::e is not visible either
}
```

---

# Data Class

```kotlin
data class User(val name: String, val age: Int)
```

- equals()/hashCode()
- toString()
- componentN()
- copy()

---

# Data Class

```kotlin
data class User(val name: String = "", val age: Int = 0)

fun copy(name: String = this.name, age: Int = this.age) = User(name, age)

val jack = User(name = "Jack", age = 1)
val olderJack = jack.copy(age = 2)

val jane = User("Jane", 35) 
val (name, age) = jane
println("$name, $age years of age") // prints "Jane, 35 years of age"
```

---

# Generics

```kotlin
abstract class Comparable1<in T> {
    abstract fun compareTo(other: T): Int
}

fun demo2(x: Comparable1<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, we can assign x to a variable of type Comparable<Double>
    val y: Comparable1<Double> = x // OK!
}
```

---

# Generics

```kotlin

// Array<out Any> -> Java: Array<? extends Object>
fun copy2(from: Array<out Any>, to: Array<Any>) {
    // ...
}

// Array<in String> -> Java: Array<? super String>
fun fill(dest: Array<in String>, value: String) {
    // ...
}

fun <T> stringsWhenGreater(list: List<T>, threshold: T): List<String>
        where T : Comparable<T>, T : Cloneable {
    return list.filter { it > threshold }.map { it.toString() }
}
```

---

# NestedClass

```kotlin
class Outer {
  private val bar: Int = 1
  class Nested {
    fun foo() = 2
  }
}

val demo = Outer.Nested().foo() // == 2
```

---

# Inner Class

```kotlin
class Outer {
  private val bar: Int = 1
  inner class Inner {
    fun foo() = bar
  }
}

val demo = Outer().Inner().foo() // == 1
```

# Enum

```kotlin
enum class Direction {
  NORTH, SOUTH, WEST, EAST
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

enum class ProtocolState {
  WAITING {
    override fun signal() = TALKING
  },

  TALKING {
    override fun signal() = WAITING
  };

  abstract fun signal(): ProtocolState
}
```

# Object Expressions

```kotlin
val adHoc = object {
  var x: Int = 0
  var y: Int = 0
}
print(adHoc.x + adHoc.y)

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
```

---

# Object

```kotlin
object DataProviderManager {
  fun registerDataProvider(provider: DataProvider) {
    // ...
  }

  val allDataProviders: Collection<DataProvider>
    get() = // ...
}
```

---

# Companion

```kotlin
class MyClass {
  companion object Factory {
    fun create(): MyClass = MyClass()
  }
}

interface Factory<T> {
  fun create(): T
}


class MyClass {
  companion object : Factory<MyClass> {
    override fun create(): MyClass = MyClass()
  }
}
```

---

# Class Delegation

```kotlin
interface Base {
  fun print()
}

class BaseImpl(val x: Int) : Base {
  override fun print() { print(x) }
}

class Derived(b: Base) : Base by b

fun main() {
  val b = BaseImpl(10)
  Derived(b).print() // prints 10
}
```

---

# Delegated Properties

- Lazy Properties
- Observable Properties
- Storing Properties

```kotlin
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
```

---

# Lazy

```kotlin
val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}

fun main(args: Array<String>) {
    println(lazyValue)
    println(lazyValue)
}
```

---

# Observable

```kotlin
import kotlin.properties.Delegates

class User {
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new")
    }
}

fun main(args: Array<String>) {
    val user = User()
    user.name = "first"
    user.name = "second"
}
```

# Map

```kotlin
class User(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}

val user = User(mapOf(
    "name" to "John Doe",
    "age"  to 25
))

println(user.name) // Prints "John Doe"
println(user.age)  // Prints 25

lass MutableUser(val map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int     by map
}
```

# Extensions

```kotlin
fun MutableList<Int>.swap(index1: Int, index2: Int) {
  val tmp = this[index1] // 'this' corresponds to the list
  this[index1] = this[index2]
  this[index2] = tmp
}

val l = mutableListOf(1, 2, 3)
l.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'l'

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
  val tmp = this[index1] // 'this' corresponds to the list
  this[index1] = this[index2]
  this[index2] = tmp
}

val <T> List<T>.lastIndex: Int
  get() = size - 1
```

