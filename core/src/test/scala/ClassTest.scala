import org.scalatest.funsuite.AnyFunSuite

class ClassTest extends AnyFunSuite {
  class Point(private val x: Int, private val y: Int) {
    override def toString = s"Point($x, $y)"

    override def equals(obj: Any): Boolean = {
      if (!obj.isInstanceOf[Point]) return false
      val point = obj.asInstanceOf[Point]
      this.x == point.x && this.y == point.y
    }
  }

  // companion object
  object Point {
    def shiftInXDirection(point: Point)(distance: Int) =
      new Point(point.x + distance, point.y)

    def apply(x: Int, y: Int) = new Point(x, y)

    def unapply(point: Point): Option[(Int, Int)] = Some((point.x, point.y))
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

  test("Case class") {
    case class Point2(x: Int, y: Int)

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
