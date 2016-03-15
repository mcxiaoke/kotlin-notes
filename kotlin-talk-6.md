# Ranges

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

# Collections

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

# Collections

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


