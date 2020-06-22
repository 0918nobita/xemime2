package vision.kodai.xemime

import cats.data.State

object Main {
  def main(args: Array[String]): Unit = {
    val state  = State[Int, Int](s => (s + 1, s * 2))
    val state2 = state.flatMap(_ => state)
    println(state2.run(1).value)

    println(new Point(1, 2))
  }
}
