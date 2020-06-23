import org.scalatest.funsuite.AnyFunSuite

class Test extends AnyFunSuite {
  test("(->) operator") {
    // alternate syntax for key-value pair
    assert((1, "a") == (1 -> "a"))
  }

  test("(Maps) Initialization") {
    val myMap = Map("JA" -> "Japanese", "EN" -> "English", "ZH" -> "Chinese")
    assert(myMap.size == 3)

    val redundantMap = Map("a" -> "A", "b" -> "B", "a" -> "A", "c" -> "C")
    assert(redundantMap.size == 3)
  }

  test("(Maps) Add elements") {
    val myMap  = Map(1 -> "a", 2 -> "b")
    val newMap = myMap + (3 -> "c") + (4 -> "d")
    assert(newMap.contains(3))
    assert(newMap.toString === "Map(1 -> a, 2 -> b, 3 -> c, 4 -> d)")
  }
}
