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
    check(Prop.forAll(gen) { m =>
      val values = m.values
      values.head == m.head._2 &&
      values.last == m.last._2
    })
  }

  test("Update existing entries") {
    check(Prop.forAll {
      (baseMap: Map[String, Int], key: String, value: Int, newValue: Int) =>
        val oldMap = baseMap + (key -> value)
        val newMap = oldMap + (key  -> newValue)
        newMap(key) == newValue
    })
  }

  test("Mixed type") {
    val myMap = Map("Japan" -> "JP", 81 -> "JP")
    assertResult("JP")(myMap("Japan"))
    assertResult("JP")(myMap(81))
  }

  test("getOrElse / withDefaultValue") {
    val myMap = Map("foo" -> "bar")
    assert(myMap == myMap - "baz")

    val gen = for {
      baseMap <- Gen.mapOf(Arbitrary.arbitrary[(String, Int)])
      key     <- Arbitrary.arbitrary[String]
    } yield {
      (key, baseMap - key)
    }

    check(Prop.forAll(gen) {
      case (key, map) =>
        Prop.throws(classOf[NoSuchElementException]) { map(key) } &&
          map.getOrElse(key, "missing key") == "missing key" &&
          (map withDefaultValue "missing key")(key) == "missing key"
    })
  }
}
