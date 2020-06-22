package vision.kodai.xemime

import cats.data.State

object Main {
  def main(args: Array[String]) {
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
