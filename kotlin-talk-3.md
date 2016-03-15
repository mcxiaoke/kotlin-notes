
# Function

```kotlin
fun double(x: Int): Int {
}

val result = double(2)

// Define extension to Int
infix fun Int.shl(x: Int): Int {
...
}

1 shl 2

fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size()) {
...
}
```

---

# Function

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

# Function

```kotlin
fun printHello(name: String?): Unit {
    if (name != null)
        println("Hello ${name}")
    else
        println("Hi there!")
    // `return Unit` or `return` is optional
}

fun printHello(name: String?) {
    ...
}
```

# Function

```kotlin
fun double(x: Int): Int = x * 2

fun double(x: Int) = x * 2
```

---

# Function

```kotlin
fun <T> asList(vararg ts: T): List<T> {
  val result = ArrayList<T>()
  for (t in ts) // ts is an Array
    result.add(t)
  return result
}

val list = asList(1, 2, 3)

val a = arrayOf(1, 2, 3)
val list = asList(-1, 0, *a, 4)
```

---

# Functions

```kotlin
class Sample() {
  fun foo() { print("Foo") }
}

Sample().foo()
```

---

# Function

```kotlin
fun <T> singletonList(item: T): List<T> {
  // ...
}
```

---

# Lambda

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
```

# Lambda

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

# Lambda

```kotlin
val sum = { x: Int, y: Int -> x + y }

val sum: (Int, Int) -> Int = { x, y -> x + y }

ints.filter { it > 0 } 
```

---

# Anonymous Functions

```kotlin
fun(x: Int, y: Int): Int = x + y

fun(x: Int, y: Int): Int {
  return x + y
}

ints.filter(fun(item) = item > 0)
```

---

# Closures

```kotlin
var sum = 0
ints.filter { it > 0 }.forEach {
  sum += it
}
print(sum)

val sum = fun Int.(other: Int): Int = this + other
```

---

# Lambda

```kotlin
class HTML {
    fun body() { ... }
}

fun html(init: HTML.() -> Unit): HTML {
  val html = HTML()  // create the receiver object
  html.init()        // pass the receiver object to the lambda
  return html
}


html {       // lambda with receiver begins here
    body()   // calling a method on the receiver object
}
```

---

# Inline Functions

```kotlin
lock(l) { foo() }

l.lock()
try {
  foo()
}
finally {
  l.unlock()
}
```

---

# Inline Functions

```kotlin
inline fun lock<T>(lock: Lock, body: () -> T): T {
  // ...
}

inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
  // ...
}
```

```kotlin
```

```kotlin
```
