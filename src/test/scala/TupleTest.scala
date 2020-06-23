import org.scalatest.funsuite.AnyFunSuite

class TupleTest extends AnyFunSuite {
  test("Create Tuples / Access elements") {
    val tuple = (1, "str")
    assert(tuple._1 == 1)
    assert(tuple._2 == "str")
  }

  test("Deconstruct Tuples") {
    val (a, head :: tail) = ("foo", List(1, 2, 3))
    assert(a == "foo")
    assert(head == 1)
    assert(tail == List(2, 3))
  }

  test("Swap Tuples") {
    val (x, y) = (7, "bar").swap
    assert(x == "bar")
    assert(y == 7)
  }
}
