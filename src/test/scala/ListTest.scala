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

  test("Concat Lists") {
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

  test("Access values of Lists") {
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

  test("List Comparison") {
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

  test("List.map") {
    val makeUpper = { xs: List[String] => xs map { _.toUpperCase } }
    val actual   = makeUpper(List("a", "b", "c"))
    val expected = List("A", "B", "C")
    assert(actual == expected)
  }

  test("Filter Lists / Immutability") {
    val list = List(4, 5, 6)
    assert(list.filter({ _ % 2 == 0 }) == List(4, 6))
    assert(list.filterNot({ _ % 3 == 0 }) == List(4, 5))
    assert(list === List(4, 5, 6))
  }

  test("Reduce Lists") {
    val list = List(1, 3, 5, 7)
    assert(16 == list.foldLeft(z = 0)(op = (acc, elem) => acc + elem))
    assert(26 == list.foldLeft(10) { _ + _ })
  }
}
