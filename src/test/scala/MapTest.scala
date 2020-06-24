import org.scalacheck.Prop.forAll
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers

class MapTest extends AnyFunSuite with Checkers {
  test("(->) operator") {
    // alternate syntax for key-value pair
    assert((1, "a") == (1 -> "a"))
  }

  test("Initialization") {
    val myMap = Map("JA" -> "Japanese", "EN" -> "English", "ZH" -> "Chinese")
    assert(myMap.size == 3)

    val redundantMap = Map("a" -> "A", "b" -> "B", "a" -> "A", "c" -> "C")
    assert(redundantMap.size == 3)
  }

  test("Add elements") {
    val myMap  = Map(1 -> "a", 2 -> "b")
    val newMap = myMap + (3 -> "c") + (4 -> "d")
    assert(newMap.contains(3))
    assert(newMap.toString === "Map(1 -> a, 2 -> b, 3 -> c, 4 -> d)")
  }

  test("Values") {
    val myMap     = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin")
    val mapValues = myMap.values
    assert(mapValues.head == "Michigan")

    val myMap2     = Map("OH" -> "Ohio", "MI" -> "Michigan", "WI" -> "Wisconsin")
    val mapValues2 = myMap2.values
    assert(mapValues2.head == "Ohio")

    check(forAll { (m: Map[String, String]) =>
      m.isEmpty || m.values.head == m.head._2
    })
  }
}
