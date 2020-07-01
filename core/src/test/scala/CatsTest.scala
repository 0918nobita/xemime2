import cats.data.State
import org.scalatest.funsuite.AnyFunSuite

class CatsTest extends AnyFunSuite {
  test("State") {
    val state  = State[Int, Int](s => (s + 1, s * 2))
    val state2 = state.flatMap(_ => state)
    assert(state2.run(1).value == ((3, 4)))
  }
}
