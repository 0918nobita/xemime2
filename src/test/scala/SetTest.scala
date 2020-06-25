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

  test("Comparison") {
    val set1 = Set("a", "b", "c")
    val set2 = Set("b", "c", "a")
    assert(set1 == set2)
  }
}
