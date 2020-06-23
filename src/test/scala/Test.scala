import org.scalatest.funsuite.AnyFunSuite

class Test extends AnyFunSuite {
  test("Tuples") {
    val tuple = (1, "str")
    assert(tuple._1 == 1)
    assert(tuple._2 == "str")

    val (a, b) = tuple
    assert(a == 1)
    assert(b == "str")

    val (c, d) = tuple.swap
    assert(c == "str")
    assert(d == 1)
  }

  test("Functions") {
    val func = new Function[Int, Int] {
      // implement abstract method
      def apply(v1: Int): Int = v1 - 1
    }
    assert(func(10) == 9)
    assert(func.apply(10) == 9)
    assert(
      func.isInstanceOf[Int => Int] // `Int => Int` == `Function1[Int, Int]`
    )

    val func2 = (x: Int) => { x * 2 }
    assert(func2(3) == 6)

    val func3 = { x: Int => x * 3 }
    assert(func3(4) == 12)
  }

  test("Closures") {
    var count = 0
    val countUp = { diff: Int =>
      count += diff
      count
    }
    assert(countUp(1) == 1)
    assert(countUp(3) == 4)
  }

  test("Cons") {
    val list = List(1, 3, 5, 7)
    // right-associative operator
    assert(list.::(0) == List(0, 1, 3, 5, 7))
    assert(-1 :: list == List(-1, 1, 3, 5, 7))
  }

  test("Concat Lists") {
    val forward  = List(1, 3)
    val backward = List(5, 7)
    assert(forward ::: backward == List(1, 3, 5, 7))
  }

  test("Access values of Lists") {
    val list = List(1, 2, 3)
    assert(list(2) == 3)
    assertThrows[IndexOutOfBoundsException] { list(4) }
    assert(list.headOption.contains(1))
    assert(list.tail == List(2, 3))
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
