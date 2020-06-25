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

  test("Remove existing entries") {
    val baseMap = Map("foo" -> 0, "bar" -> 1, "baz" -> 2)
    val map1    = baseMap - "foo"
    assert(!map1.contains("foo"))
    assert(baseMap.contains("foo"))

    val map2 = baseMap -- List("foo", "baz")
    assert(!map2.contains("foo"))
    assert(!map2.contains("baz"))
  }

  test("Remove nonexistent entries") {
    val myMap = Map(1 -> "First", 2 -> "Second")
    assert(myMap - 3 == myMap)
  }

  test("getOrElse / withDefaultValue") {
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

  test("Comparison") {
    val map1 = Map(0 -> "foo", 1 -> "bar", 2 -> "baz")
    val map2 = Map(0 -> "bar", 1 -> "foo", 2 -> "baz")
    val map3 = Map(2 -> "baz", 1 -> "bar", 0 -> "foo")
    assert(map1 != map2)
    assert(map1 == map3)
  }
}
