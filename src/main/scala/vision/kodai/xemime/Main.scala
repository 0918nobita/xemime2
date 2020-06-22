package vision.kodai.xemime

import cats.data.State

object Main {
  def maybeItWillReturnSomething(cond: Boolean): Option[String] = {
    if (cond) Some("Found value") else None
  }

  def main(args: Array[String]): Unit = {
    val state  = State[Int, Int](s => (s + 1, s * 2))
    val state2 = state.flatMap(_ => state)
    println(state2.run(1).value)

    println(new Point(1, 2)) // => Point(1, 2)

    val value1 = maybeItWillReturnSomething(true)
    val value2 = maybeItWillReturnSomething(false)
    println(value1.getOrElse("Placeholder")) // => Found value
    println(value2.getOrElse("Placeholder")) // => Placeholder

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
  }
}
