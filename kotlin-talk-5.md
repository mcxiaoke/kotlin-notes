# Calling Java code from Kotlin

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
```

---

# Calling Java

```kotlin
import java.util.Calendar

fun calendarDemo() {
    val calendar = Calendar.getInstance()
    if (calendar.firstDayOfWeek == Calendar.SUNDAY) {  // call getFirstDayOfWeek()
        calendar.firstDayOfWeek = Calendar.MONDAY       // call setFirstDayOfWeek()
    }
}
```

---

# Types

Java type	| Kotlin type
------|------
byte	| kotlin.Byte
short	| kotlin.Short
int	| kotlin.Int
long	| kotlin.Long
char	| kotlin.Char
float	| kotlin.Float
double	| kotlin.Double
boolean	| kotlin.Boolean

---

# Generics

- Java’s wildcards are converted into type projections
	- Foo<? extends Bar> becomes Foo<out Bar!>!
	- Foo<? super Bar> becomes Foo<in Bar!>!
- Java’s raw types are converted into star projections
	- List becomes List<*>!, i.e. List<out Any?>!

	
---

# Calling Kotlin from Java

- Properties
- Package-Level Functions
- Instance Fields
- Static Fields
- Static Methods
- Handling signature clashes with @JvmName
- Overloads Generation

---

# Calling Kotlin

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

# Calling Kotlin

```kotlin
class C(id: String) {
    @JvmField val ID = id
}
```

```java
// Java
class JavaClient {
    public String getID(C c) {
        return c.ID;
    }
}
```

---

# Calling Kotlin

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

# Calling Kotlin

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

# Calling Kotlin

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
