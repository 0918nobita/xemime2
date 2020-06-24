import org.scalacheck.{Arbitrary, Gen, Prop}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers

class MapTest extends AnyFunSuite with Checkers {
  test("(->) operator") {
    // alternate syntax for key-value pair
    check(Prop.forAll { (str: String, n: Int) => (str, n) == (str -> n) })
  }

  test("Initialization") {
    val myMap = Map("JA" -> "Japanese", "EN" -> "English", "ZH" -> "Chinese")
    assert(myMap.size == 3)

    val redundantMap = Map("a" -> "A", "b" -> "B", "a" -> "A", "c" -> "C")
    assert(redundantMap.size == 3)
  }

  test("Add elements") {
    check(Prop.forAll {
      (baseMap: Map[String, Int], newKey: String, newValue: Int) =>
        val newMap = baseMap + (newKey -> newValue)
        newMap.contains(newKey) && newMap(newKey) == newValue
    })
  }

  test("Values") {
    val gen = Gen.nonEmptyMap(Arbitrary.arbitrary[(String, String)])
    check(Prop.forAll(gen) { m => m.values.head == m.head._2 })
  }
}
