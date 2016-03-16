class: center, middle


# Kotlin

### 

Concise, Expressive, Safe, Versatile, Interoperable

---

class: left, middle

## Features

- Lambdas
- Data classes
- Function literals
- Extension functions
- Null safety
- Smart casts
- String templates
- Properties
- Class delegation
- Type inference
- Range expressions

---

class: center, middle

# Basic Syntax

---

## Hello World

```kotlin
// hello.kt
package com.mcxiaoke.kotlin.demo

fun main(args: Array<String>) {
    println("Hello, World!")
}

val a: Int = 1
val b = 1
val c: Int
c = 1
// c = 2
var x = 5
x += 1
```

---

## Define Function

```kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum(a: Int, b: Int) = a + b

fun printSum(a: Int, b: Int): Unit {
    print(a + b)
}

fun printSum(a: Int, b: Int) {
    print(a + b)
}
```

---

## Condition

```kotlin
fun max(a: Int, b: Int): Int {
  if (a > b)
    return a
  else
    return b
}

fun max(a: Int, b: Int) = if (a > b) a else b

public int max(int a, int b) {
    if (a > b) {
        return a;
    } else {
        return b;
    }
}

public int max(int a, int b) {
    return a > b ? a : b;
}
```

---

## If Expressions

```kotlin
// As expression 
val max = if (a > b) a else b

val max = if (a > b) { 
    print("Choose a") 
    a 
  } 
  else { 
    print("Choose b") 
    b 
  }
```

---

## Loop

```kotlin
fun forLoop1(args: Array<String>) {
    for (arg in args) {
        print(arg)
    }
}

fun forLoop2(args: Array<String>) {
    for (i in args.indices) {
        print(args[i])
    }
}

fun whileLoop1(args: Array<String>) {
    var i = 0
    while (i < args.size)
        print(args[i++])
}
```

---

## When

```kotlin
when (x) {
  0, 1 -> print("x == 0 or x == 1")
  3 -> print("x == 3")
  4 -> print("x == 4")
  else -> print("otherwise")
}

when (x) {
  in 1..10 -> print("x is in the range")
  in validNumbers -> print("x is valid")
  !in 10..20 -> print("x is outside the range")
  else -> print("none of the above")
}
```

---

## Returns

```kotlin
return. 
break. 
continue. 

loop@ for (i in 1..100) {
  for (j in 1..100) {
    if (...)
      break@loop
  }
}

fun foo() {
  ints.forEach lit@ {
    if (it == 0) return@lit
    print(it)
  }
}
```

---

## Numbers

```kotlin

val a: Int? = 1 
val b: Long? = a 
print(a == b) // false

val b: Byte = 1 // OK,
val i: Int = b // ERROR

val i: Int = b.toInt() // OK

toByte(): Byte
toShort(): Short
toInt(): Int
toLong(): Long
toFloat(): Float
toDouble(): Double
toChar(): Char
```

---

## Arrays

```kotlin
class Array<T> private constructor() {
val size: Int
fun get(index: Int): T
fun set(index: Int, value: T): Unit
fun iterator(): Iterator<T>
// ...
}

val asc = Array(5, { i -> (i * i).toString() })

val x: IntArray = intArrayOf(1, 2, 3)
x[0] = x[1] + x[2]
```

---

## Strings

```kotlin
for (c in str) {
    println(c)
}

val s = "Hello, world!\n"

val text = """
  for (c in "foo")
    print(c)
"""

val s = "abc"
val str = "$s.length is ${s.length}"

var args = arrayOf("Cat", "Dog", "Rabbit")
print("Hello ${args[0]}")
```

---

class: center, middle

# Classes and Objects

---

## Classes

```kotlin
class Invoice {
}

class Empty

class Person(name: String) {
}

class Person(name: String) {
    val customName = name.toUpperCase()
}

class Person(val firstName: String, val lastName: String, 
	var age: Int) {
  // ...
}
```

---

## Constructors

```kotlin
class Person constructor(name: String) {
}

class Person {
    constructor(parent: Person) {
        parent.children.add(this)
    }
}

class Person(name: String) {
    init {
        logger.info("Person initialized with value ${name}")
    }
}

class DontCreateMe private constructor () {
}

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

# Overriding Members

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

## Properties

```kotlin
const val DEPRECATED: String = "deprecated"
const val SOCKET_TIMEOUT = 30*1000L

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

## Getters and Setters

```kotlin
val isEmpty: Boolean
  get() = this.size == 0
  
var stringRepresentation: String
  get() = this.toString()
  set(value) {
    setDataFromString(value) 
  }
  
var setterVisibility: String = "abc" 
// Initializer required, not a nullable type
  private set // the setter is private 

var setterWithAnnotation: Any?
  @Inject set // annotate the setter with Inject
```

---

## Interface

```kotlin
interface MyInterface {
    val property: Int // abstract

    val propertyWithImplementation: String
        get() = "foo"

    fun bar()
    fun foo() {
      // optional body
    }
}

class Child : MyInterface {
    override val property: Int = 29
    
    override fun bar() {
      // body
   }
}
```

---

## Data Class

- equals()/hashCode()/toString()/componentN()/copy()

```kotlin
data class User(val name: String, val age: Int)
data class User(val name: String = "", val age: Int = 0)

fun copy(name: String = this.name, age: Int = this.age) 
	= User(name, age)

val jack = User(name = "Jack", age = 1)
val olderJack = jack.copy(age = 2)

val jane = User("Jane", 35) 
val (name, age) = jane
println("$name, $age years of age") 
// prints "Jane, 35 years of age"
```

---

## Destructuring Declarations

```kotlin
data class Person(val name:String, val age:Int){}

val person = Person("John",20)
val (name, age) = person

val name = person.component1()
val age = person.component2()

for ((key, value) in map) {
}

operator fun <K, V> Map<K, V>.iterator(): Iterator<Map.Entry<K, V>> 
	= entrySet().iterator()
operator fun <K, V> Map.Entry<K, V>.component1() = getKey()
operator fun <K, V> Map.Entry<K, V>.component2() = getValue()
```

---

## Nested and Inner Classes

```kotlin
class Outer {
  private val bar: Int = 1
  class Nested {
    fun foo() = 2
  }
}

val demo = Outer.Nested().foo() // == 2
```

```kotlin
class Outer {
  private val bar: Int = 1
  inner class Inner {
    fun foo() = bar
  }
}

val demo = Outer().Inner().foo() // == 1
```

---

## Enum

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


---

## Object

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
  })
}

object DataProviderManager {
  fun registerDataProvider(provider: DataProvider) {
  }

  val allDataProviders: Collection<DataProvider>
    get() = // ...
}
```

---

## Companion

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

## Class Delegation

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

## Delegated Properties

- Lazy Properties
- Observable Properties
- Storing Properties

```kotlin
class Delegate {
  operator fun getValue(thisRef: Any?, 
  	property: KProperty<*>): String {
    return "$thisRef, delegating '${property.name}' to me!"
  }
 
  operator fun setValue(thisRef: Any?, property: KProperty<*>, 
  	value: String) {
    println("$value assigned to '${property.name} in $thisRef.'")
  }
}

class Example {
  var p: String by Delegate()
}
```

---

## By Map

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


---

## Lazy Init

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

## Extensions

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

---

class: center, middle

# Functions and Lambdas

---

## Function Declarations

```kotlin
fun double(x: Int): Int {
}
val result = double(2)

infix fun Int.shl(x: Int): Int {
...
}
1 shl 2

fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size()) {
...
}

fun printHello(name: String?): Unit {
    if (name != null)
        println("Hello ${name}")
}

fun printHello(name: String?) {
    ...
}
```

---

## Function Arguments

```kotlin
fun reformat(str: String,
             normalizeCase: Boolean = true,
             upperCaseFirstLetter: Boolean = true,
             divideByCamelHumps: Boolean = false,
             wordSeparator: Char = ' ') {
...
}

reformat(str, true, true, false, '_')

reformat(str,
    normalizeCase = true,
    upperCaseFirstLetter = true,
    divideByCamelHumps = false,
    wordSeparator = '_'
  )
  
reformat(str, wordSeparator = '_')
```

---

## Function Usage

```kotlin
fun double(x: Int): Int = x * 2
fun double(x: Int) = x * 2

fun <T> asList(vararg ts: T): List<T> {
  val result = ArrayList<T>()
  for (t in ts) // ts is an Array
    result.add(t)
  return result
}

val list = asList(1, 2, 3)

val a = arrayOf(1, 2, 3)
val list = asList(-1, 0, *a, 4)

class Sample() {
  fun foo() { print("Foo") }
}

Sample().foo()
```

---

## Higher-Order Functions

- Inline Functions
- Lambda Expressions
- Anonymous Functions

```kotlin
fun <T> lock(lock: Lock, body: () -> T): T {
  lock.lock()
  try {
    return body()
  }
  finally {
    lock.unlock()
  }
}

fun toBeSynchronized() = sharedResource.operation()

val result = lock(lock, ::toBeSynchronized)

val result = lock(lock, { sharedResource.operation() })

lock (lock) {
  sharedResource.operation()
}
```

---

## Function Types


```kotlin
fun <T, R> List<T>.map(transform: (T) -> R): List<R> {
  val result = arrayListOf<R>()
  for (item in this)
    result.add(transform(item))
  return result
}

val doubled = ints.map { it -> it * 2 }

ints.map { it * 2 }

strings filter {it.length == 5} sortBy {it} map {it.toUpperCase()}
```

---

## Lambda Expressions

```kotlin
val sum = { x: Int, y: Int -> x + y }

val sum: (Int, Int) -> Int = { x, y -> x + y }

ints.filter { it > 0 } 

fun(x: Int, y: Int): Int = x + y

fun(x: Int, y: Int): Int {
  return x + y
}

ints.filter(fun(item) = item > 0)
```

---

## Closures

```kotlin
var sum = 0
ints.filter { it > 0 }.forEach {
  sum += it
}
print(sum)

val sum = fun Int.(other: Int): Int = this + other
```

---

class: center, middle

# Standard Library

---

## Types

- Any
- Unit
- Number
- String
- Array
- Iterator
- Sequence
- Collection

---

## Functions

```kotlin
fun assert(value: Boolean, lazyMessage: () -> Any)
fun check(value: Boolean, lazyMessage: () -> Any)
fun require(value: Boolean, lazyMessage: () -> Any)
fun error(message: Any): Nothing

fun <R> run(block: () -> R): R
fun <R> synchronized(lock: Any, block: () -> R): R
fun <T> lazy(initializer: () -> T): Lazy<T>
fun <T> lazyOf(value: T): Lazy<T>
fun <T, R> T.let(block: (T) -> R): R
fun <T> T.apply(block: T.() -> Unit): T
fun repeat(times: Int, action: (Int) -> Unit)
fun <T, R> with(receiver: T, block: T.() -> R): R

```

---

---

---

---

---

---


## Ranges

```kotlin
if (i in 1..10) { // equivalent of 1 <= i && i <= 10
  println(i)
}

for (i in 1..4) print(i) // prints "1234"
for (i in 4..1) print(i) // prints nothing

for (i in 4 downTo 1) print(i) // prints "4321"

for (i in 1..4 step 2) print(i) // prints "13"

for (i in 4 downTo 1 step 2) print(i) // prints "42"

(1..12 step 2).last == 11  // progression with values [1, 3, 5, 7, 9, 11]
(1..12 step 3).last == 10  // progression with values [1, 4, 7, 10]
(1..12 step 4).last == 9   // progression with values [1, 5, 9]
```

---

## Collections

```kotlin
val numbers: MutableList<Int> = mutableListOf(1, 2, 3)
val readOnlyView: List<Int> = numbers
println(numbers)        // prints "[1, 2, 3]"
numbers.add(4)
println(readOnlyView)   // prints "[1, 2, 3, 4]"
readOnlyView.clear()    // -> does not compile

val strings = hashSetOf("a", "b", "c", "c")
assert(strings.size == 3)
```

---

## Collections

```kotlin
val items = listOf(1, 2, 3, 4)
items.first == 1
items.last == 4
items.filter { it % 2 == 0 }   // Returns [2, 4]
rwList.requireNoNulls()
if (rwList.none { it > 6 }) println("No items above 6")
val item = rwList.firstOrNull()

val readWriteMap = hashMapOf("foo" to 1, "bar" to 2)
println(map["foo"])
val snapshot: Map<String, Int> = HashMap(readWriteMap)
```

---

class: center, middle

# Others

---

## Generics

- Java’s wildcards are converted into type projections
	- Foo<? extends Bar> becomes Foo<out Bar!>!
	- Foo<? super Bar> becomes Foo<in Bar!>!
- Java’s raw types are converted into star projections
	- List becomes List<*>!, i.e. List<out Any?>!

```kotlin
abstract class Comparable1<in T> {
    abstract fun compareTo(other: T): Int
}

fun demo2(x: Comparable1<Number>) {
    x.compareTo(1.0)
    val y: Comparable1<Double> = x // OK!
}

// Array<out Any> -> Java: Array<? extends Object>
fun copy2(from: Array<out Any>, to: Array<Any>) {
    // ...
}

// Array<in String> -> Java: Array<? super String>
fun fill(dest: Array<in String>, value: String) {
    // ...
}
```

---

## Is Operator

```kotlin
if (obj is String) {
  print(obj.length)
}

if (obj !is String) { // same as !(obj is String)
  print("Not a String")
}
else {
  print(obj.length)
}
```

---

## Smart Cast

```kotlin
fun demo(x: Any) {
  if (x is String) {
    print(x.length) // x is automatically cast to String
  }
}

if (x !is String) return
  print(x.length) // x is automatically cast to String
  
// x is automatically cast to string on the right-hand side of `||`
  if (x !is String || x.length == 0) return

  // x is automatically cast to string on the right-hand side of `&&`
  if (x is String && x.length > 0)
      print(x.length) // x is automatically cast to String
     
```

---

## Type Cast

```kotlin
when (x) {
  is Int -> print(x + 1)
  is String -> print(x.length + 1)
  is IntArray -> print(x.sum())
}
```

```kotlin
val x: String = y as String

val x: String? = y as String?

val x: String? = y as? String
``` 

---

## This

```kotlin
class A { // implicit label @A
  inner class B { // implicit label @B
    fun Int.foo() { // implicit label @foo
      val a = this@A // A's this
      val b = this@B // B's this

      val c = this // foo()'s receiver, an Int
      val c1 = this@foo // foo()'s receiver, an Int

      val funLit = lambda@ fun String.() {
        val d = this // funLit's receiver
      }


      val funLit2 = { s: String ->
        // foo()'s receiver, since enclosing lambda expression
        // doesn't have any receiver
        val d1 = this
      }
    }
  }
}
```

---

## Null Safety

```kotlin
var a: String = "abc"
a = null // compilation error

var b: String? = "abc"
b = null // ok

val l = a.length
val l = b.length // error: variable 'b' can be null

val l = if (b != null) b.length else -1
  
b?.length
bob?.department?.head?.name
  
val l: Int = if (b != null) b.length else -1
val l = b?.length ?: -1
val l = b!!.length() // npe
val aInt: Int? = a as? Int
```

---

## Exceptions

```kotlin
try {
  // some code
}
catch (e: SomeException) {
  // handler
}
finally {
  // optional finally block
}

// try is an expression
val a: Int? = try { parseInt(input) } catch (e: NumberFormatException) { null }
```

---


## Reflection

```kotlin
val c = MyClass::class

fun isOdd(x: Int) = x % 2 != 0

val numbers = listOf(1, 2, 3)
println(numbers.filter(::isOdd)) // prints [1, 3]

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}

fun length(s: String) = s.size

val oddLength = compose(::isOdd, ::length)
val strings = listOf("a", "ab", "abc")

println(strings.filter(oddLength)) // Prints "[a, abc]"
```

---

class: center, middle

# Java Interop

---

## Calling Java from Kotlin

```kotlin
import java.util.*

fun demo(source: List<Int>) {
  val list = ArrayList<Int>()
  // 'for'-loops work for Java collections:
  for (item in source)
    list.add(item)
  // Operator conventions work as well:
  for (i in 0..source.size() - 1)
    list[i] = source[i] // get and set are called
}

val calendar = Calendar.getInstance()
if (calendar.firstDayOfWeek == Calendar.SUNDAY) {  // call getFirstDayOfWeek()
    calendar.firstDayOfWeek = Calendar.MONDAY       // call setFirstDayOfWeek()
}
```

---

## Calling Kotlin from Java

- Properties
- Package-Level Functions
- Instance Fields
- Static Fields
- Static Methods
- Handling signature clashes with @JvmName
- Overloads Generation

---

## Calling Kotlin from Java

```kotlin
@file:JvmName("DemoUtils")

package demo

class Foo

fun bar() {
}
```

```java
// Java
new demo.Foo();
demo.DemoUtils.bar();
```

---

## Calling Kotlin from Java

```kotlin
class C {
  companion object {
    @JvmStatic fun foo() {}
    fun bar() {}
  }
}
```

```java
C.foo(); // works fine
C.bar(); // error: not a static method
```

---

## Calling Kotlin from Java

```kotlin
object Obj {
    @JvmStatic fun foo() {}
    fun bar() {}
}
```

```java
Obj.foo(); // works fine
Obj.bar(); // error
Obj.INSTANCE.bar(); // works, a call through the singleton instance
Obj.INSTANCE.foo(); // works too
```

---

## Calling Kotlin from Java

```kotlin
@JvmOverloads fun f(a: String, b: Int = 0, c: String = "abc") {
    ...
}
```

```java
// Java
void f(String a, int b, String c) { }
void f(String a, int b) { }
void f(String a) { }
```

---

---
---

---
