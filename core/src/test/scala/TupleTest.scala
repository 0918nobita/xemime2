import org.scalacheck.Prop
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers

class TupleTest extends AnyFunSuite with Checkers {
  test("Create Tuples / Access elements") {
    check(Prop.forAll({ (a: Int, b: String) =>
      val tuple = (a, b)
      tuple._1 == a && tuple._2 == b
    }))
  }

  test("Deconstruct Tuples") {
    check(Prop.forAll({ (tuple: (String, List[Int])) =>
      val (a, list) = tuple
      a == tuple._1 && list == tuple._2
    }))
  }

  test("Swap Tuples") {
    check(Prop.forAll({ (tuple: (Int, String)) =>
      val (x, y) = tuple.swap
      x == tuple._2 && y == tuple._1
    }))
  }
}
