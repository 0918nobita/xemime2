import org.scalacheck.Prop.forAll
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers

class MatchTest extends AnyFunSuite with Checkers {
  test("`match` keyword") {
    def matchFn(x: Int): String =
      x match {
        case 0 => "Zero"
        case 1 => "One"
        case _ => "Other"
      }

    assertResult("One")(matchFn(1))
    assertResult("Other")(matchFn(2))

    def matchFn2(x: Int): String = {
      val fn: Int => String = {
        case 0 => "Zero"
        case 1 => "One"
        case _ => "Other"
      }
      fn(x)
    }

    check(forAll { (n: Int) => matchFn(n) == matchFn2(n) })
  }
}
