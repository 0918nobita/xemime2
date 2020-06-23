import org.scalatest.funsuite.AnyFunSuite

class FuncTest extends AnyFunSuite {
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
}
