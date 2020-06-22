package vision.kodai.xemime

import cats.data.State

object Main {
  def main(args: Array[String]) {
    val someValue: Option[Double] = Some(20.0)
    val doubleValue = someValue match {
      case Some(v) => v
      case None    => 0.0
    }
    println(doubleValue) // => 20.0

    val noneValue: Option[Double] = None
    val doubleValue2 = noneValue match {
      case Some(v) => v
      case None    => 0.0
    }
    println(doubleValue2) // => 0.0

    val number: Option[Int]   = Some(3)
    val noNumber: Option[Int] = None
    val result1               = number.map(_ * 1.5)
    val result2               = noNumber.map(_ * 1.5)
    println(result1) // => Some(4.5)
    println(result2) // => None

    println(number.fold(ifEmpty = 8)(f = _ * 2))   // => 6
    println(noNumber.fold(ifEmpty = 8)(f = _ * 2)) // => 8

    val p = new Point(1, 2)
    println(Point.shiftInXDirection(p)(4)) // => Point(5, 2)
    val p2 = new Point(50, 10) {
      override def toString = "modified"
    }
    println(p2) // => modified
    println(p)  // => Point(1, 2)

    val tuple = (1, "str")
    println(tuple._1) // => 1
    println(tuple._2) // => "str"
    val (a, b) = tuple
    println((b + "!", a + 2)) // => ("str!", 3)
    println(tuple.swap)       // => ("str", 1)

    val func = new Function[Int, Int] {
      // implement abstract method
      def apply(v1: Int): Int = v1 - 1
    }
    println(func(10))       // => 9
    println(func.apply(10)) // => 9
    println(
      func.isInstanceOf[Int => Int]
    ) // => true (`Int => Int` == `Function1[Int, Int]`)

    val func2 = (x: Int) => { x * 2 }
    println(func2(3)) // => 6

    val func3 = { x: Int => x * 3 }
    println(func3(4)) // => 12

    var count = 0
    val countUp = { diff: Int =>
      count += diff
      count
    }
    println(countUp(1)) // => 1
    println(countUp(3)) // => 4

    val makeUpper = { xs: List[String] => xs map { _.toUpperCase } }
    println(makeUpper(List("a", "b", "c"))) // => List(A, B, C)

    val listA = List(1, 2, 3)
    val listB = List(1, 2, 3)
    println(listA == listB) // => true
    println(listA eq listB) // => false

    val listC: List[String] = Nil
    val listD: List[Int]    = Nil
    println(listC == Nil)   // => true
    println(listD == Nil)   // => true
    println(listC eq Nil)   // => true
    println(listD eq Nil)   // => true
    println(listC eq listD) // => true

    val state  = State[Int, Int](s => (s + 1, s * 2))
    val state2 = state.flatMap(_ => state)
    println(state2.run(1).value) // => (3, 4)
  }
}
