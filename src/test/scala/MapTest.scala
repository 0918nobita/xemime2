import org.scalacheck.Prop.forAll
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers

class MapTest extends AnyFunSuite with Checkers {
  test("(->) operator") {
    // alternate syntax for key-value pair
    check(forAll { (str: String, n: Int) => (str, n) == (str -> n) })
  }

  test("Initialization") {
    val myMap = Map("JA" -> "Japanese", "EN" -> "English", "ZH" -> "Chinese")
    assert(myMap.size == 3)

    val redundantMap = Map("a" -> "A", "b" -> "B", "a" -> "A", "c" -> "C")
    assert(redundantMap.size == 3)
  }

  test("Add elements") {
    check(forAll { (baseMap: Map[String, Int], newKey: String, newValue: Int) =>
      val newMap = baseMap + (newKey -> newValue)
      newMap.contains(newKey) && newMap(newKey) == newValue
    })
  }

  test("Values") {
    check(forAll { (m: Map[String, String]) =>
      m.isEmpty || m.values.head == m.head._2
    })
  }
}
