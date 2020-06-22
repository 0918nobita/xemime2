import org.scalatest.funsuite.AnyFunSuite

import vision.kodai.xemime.{Point, Point2}

class Test extends AnyFunSuite {
  test("Option.getOrElse") {
    def maybeItWillReturnSomething(cond: Boolean): Option[String] =
      if (cond) Some("Found value") else None

    val value1 = maybeItWillReturnSomething(true)
    val value2 = maybeItWillReturnSomething(false)
    assert(value1.getOrElse("No value") == "Found value")
    assert(value2.getOrElse("No value") == "No value")
  }

  test("Pattern matching on Option") {
    val someValue: Option[Double] = Some(20.0)
    val doubleValue = someValue match {
      case Some(v) => v
      case None    => 0.0
    }
    assert(doubleValue == 20.0)

    val noneValue: Option[Double] = None
    val doubleValue2 = noneValue match {
      case Some(v) => v
      case None    => 0.0
    }
    assert(doubleValue2 == 0.0)
  }

  test("Option.map/fold") {
    val number: Option[Int]   = Some(3)
    val noNumber: Option[Int] = None
    val result1               = number.map(_ * 1.5)
    val result2               = noNumber.map(_ * 1.5)
    assert(result1.contains(4.5))
    assert(result2.isEmpty)

    assert(number.fold(ifEmpty = 8)(f = _ * 2) == 6)
    assert(noNumber.fold(ifEmpty = 8)(f = _ * 2) == 8)
  }

  test("Class definition / Instantiation") {
    val p = Point(1, 2)
    assert(Point.shiftInXDirection(p)(4) == Point(5, 2))

    val p2 = new Point(50, 10) {
      override def toString = "modified"
    }

    assert(p2.toString == "modified")
    assert(p.toString == "Point(1, 2)")
  }

  test("Extractor Objects") {
    val p           = Point(10, 20)
    val Point(x, y) = p
    assert(x == 10)
    assert(y == 20)
  }

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

  test("List.map") {
    val makeUpper = { xs: List[String] => xs map { _.toUpperCase } }
    val actual   = makeUpper(List("a", "b", "c"))
    val expected = List("A", "B", "C")
    assert(actual == expected)
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

  test("Access values of Lists") {
    val list = List(1, 2, 3)
    assert(list(2) == 3)
    assertThrows[IndexOutOfBoundsException] { list(4) }
    assert(list.headOption.contains(1))
    assert(list.tail == List(2, 3))
  }

  test("Filter Lists / Immutability") {
    val list = List(4, 5, 6)
    assert(list.filter({ _ % 2 == 0 }) == List(4, 6))
    assert(list.filterNot({ _ % 3 == 0 }) == List(4, 5))
    assert(list === List(4, 5, 6))
  }

  test("Case class") {
    val point = Point2(15, 30)
    assert(point.toString == "Point2(15,30)")
    assert(point.x == 15)
    assert(point.y == 30)
    assert(point == Point2(15, 30))

    val Point2(x, y) = point
    assert(x == 15)
    assert(y == 30)

    assert(Point2.unapply(Point2(1, 2)).contains((1, 2)))
  }
}
