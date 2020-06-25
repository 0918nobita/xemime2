import org.scalacheck.{Arbitrary, Gen, Prop}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers

class SetTest extends AnyFunSuite with Checkers {
  test("Initialization") {
    val set1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    assert(set1.size == 4)
    val set2 = Set("Michigan", "Ohio", "Wisconsin", "Michigan")
    assert(set2.size == 3)
  }

  test("Add elements") {
    val mySet   = Set("Michigan", "Ohio")
    val aNewSet = mySet + "Illinois"
    assert(aNewSet.contains("Illinois"))
    assert(!mySet.contains("Illinois"))
  }

  test("Mixed type") {
    val mySet = Set("Michigan", "Ohio", 12)
    assert(mySet.contains(12))
    assert(!mySet.contains("MI"))
  }

  test("Check member existence") {
    val mySet = Set("Michigan", "Ohio", 12)
    assert(mySet(12))
    assert(!mySet("MI"))
  }

  test("Remove elements") {
    val map1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val map2 = map1 - "Michigan"
    assert(!map2.contains("Michigan"))
    assert(map1.contains("Michigan"))

    val map3 = map2 - "Illinois"
    assert(map2 == map3)

    val map4 = map1 -- List("Michigan", "Wisconsin")
    assert(map4 == Set("Ohio", "Iowa"))
  }

  test("Intersection") {
    val map1 = Set("a", "b", "c", "d")
    val map2 = Set("c", "d", "e", "f")
    assert((map1 intersect map2) == Set("c", "d"))
  }

  test("Union") {
    val map1 = Set("a", "b", "c")
    val map2 = Set("b", "c", "d")
    assert((map1 union map2) == Set("a", "b", "c", "d"))
  }

  test("Subset") {
    val map1 = Set("a", "b", "c")
    val map2 = Set("a", "c")
    val map3 = Set("A", "c")
    assert(map1 subsetOf map1)
    assert(map2 subsetOf map1)
    assert(!(map3 subsetOf map1))
  }

  test("Comparison") {
    val set1 = Set("a", "b", "c")
    val set2 = Set("b", "c", "a")
    assert(set1 == set2)
  }
}
