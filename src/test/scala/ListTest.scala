import org.scalacheck.{Arbitrary, Gen, Prop}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers

class ListTest extends AnyFunSuite with Checkers {
  test("Cons") {
    val list = List(1, 3, 5, 7)
    // right-associative operator
    assert(list.::(0) == List(0, 1, 3, 5, 7))
    assert(-1 :: list == List(-1, 1, 3, 5, 7))
  }

  test("Concatenation") {
    val gen = for {
      list  <- Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])
      index <- Gen.chooseNum(0, list.size - 1)
    } yield (list, index)

    check(Prop.forAll(gen) {
      case (list, index) =>
        val forward  = list.slice(0, index)
        val backward = list.slice(index, list.size)
        forward ::: backward == list
    })
  }

  test("Access values") {
    val list = List(1, 2, 3)
    assert(list(2) == 3)
    assertThrows[IndexOutOfBoundsException] { list(4) }
    assert(list.headOption.contains(1))
    assert(list.tail == List(2, 3))

    check(Prop.forAll { l: List[Int] =>
      if (l.isEmpty) {
        Prop.throws(classOf[NoSuchElementException]) { l.head }
      } else {
        l.head == l(0)
      }
    })

    check(Prop.forAll { (n: Int, l: List[Int]) => (n :: l).tail == l })
  }

  test("Comparison") {
    val listA = List(1, 2, 3)
    val listB = List(1, 2, 3)
    assert(listA == listB)
    assert(!(listA eq listB))

    val listC: List[String] = Nil
    val listD: List[Int]    = Nil
    assert(listC == Nil)
    assert(listD == Nil)
    assert(listC eq Nil)
    assert(listD eq Nil)
    assert(listC eq listD)
  }

  test("Mapping") {
    val makeUpper = { xs: List[String] => xs map { _.toUpperCase } }
    val result = makeUpper(List("a", "b", "c"))
    assert(result == List("A", "B", "C"))
  }

  test("Filter Lists / Immutability") {
    val list = List(4, 5, 6)
    assertResult(List(4, 6))(list.filter({ _ % 2 == 0 }))
    assertResult(List(4, 5))(list.filterNot({ _ % 3 == 0 }))
    assert(list === List(4, 5, 6))
  }

  test("Reduce Lists") {
    val list = List(1, 3, 5, 7)
    assertResult(16)(list.foldLeft(z = 0)(op = (acc, elem) => acc + elem))
    assertResult(26)(list.foldLeft(10) { _ + _ })
  }
}
