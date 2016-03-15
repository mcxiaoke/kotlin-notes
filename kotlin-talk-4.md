# Destructuring Declarations

```kotlin
data class Person(val name:String, val age:Int){}

val person = Person("John",20)
val (name, age) = person

println(name)
println(age)

val name = person.component1()
val age = person.component2()

for ((key, value) in map) {
   // do something with the key and the value
}
```


---

# Destructuring Declarations


```kotlin
data class Result(val result: Int, val status: Status)
fun function(...): Result {
    // computations

    return Result(result, status)
}

// Now, to use this function:
val (result, status) = function(...)
```

---

# Destructuring Declarations

```kotlin
operator fun <K, V> Map<K, V>.iterator(): Iterator<Map.Entry<K, V>> = entrySet().iterator()
operator fun <K, V> Map.Entry<K, V>.component1() = getKey()
operator fun <K, V> Map.Entry<K, V>.component2() = getValue()
```

---

# is

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

# Smart Cast

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

# Smart Cast

```kotlin
when (x) {
  is Int -> print(x + 1)
  is String -> print(x.length + 1)
  is IntArray -> print(x.sum())
}
```

---

# Cast

```kotlin
val x: String = y as String

val x: String? = y as String?

val x: String? = y as? String
```

---

# This

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

# Equals

```kotlin
a === b
a !== b
a == b
a != b
a?.equals(b) ?: (b === null)
```

---

# Null Safety

```kotlin
var a: String = "abc"
a = null // compilation error

var b: String? = "abc"
b = null // ok

val l = a.length

val l = b.length // error: variable 'b' can be null
```

---

# Null Safety

```kotlin
val l = if (b != null) b.length else -1

if (b != null && b.length > 0)
  print("String of length ${b.length}")
else
  print("Empty string")
  
b?.length

bob?.department?.head?.name
  
al l: Int = if (b != null) b.length else -1

val l = b?.length ?: -1

val l = b!!.length() // npe

val aInt: Int? = a as? Int
```

---

# Exceptions

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

# Reflection

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

# Reference

```kotlin
var x = 1

fun main(args: Array<String>) {
    println(::x.get()) // prints "1"
    ::x.set(2)
    println(x)         // prints "2"
}

val strs = listOf("a", "bc", "def")
println(strs.map(String::length)) // prints [1, 2, 3]

class A(val p: Int)

fun main(args: Array<String>) {
    val prop = A::p
    println(prop.get(A(1))) // prints "1"
}

val String.lastChar: Char
  get() = this[size - 1]

fun main(args: Array<String>) {
  println(String::lastChar.get("abc")) // prints "c"
}
```
