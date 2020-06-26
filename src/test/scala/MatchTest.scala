import org.scalacheck.Prop.{forAll, propBoolean}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers

class MatchTest extends AnyFunSuite with Checkers {
  test("`match` keyword") {
    val makeProp = (matchFn: Int => String) =>
      forAll { (n: Int) =>
        val result = matchFn(n)
        (n == 0) ==> (result == "Zero") ||
        (n == 1) ==> (result == "One") ||
        result == "Other"
      }

    val matchFn1 = (x: Int) =>
      x match {
        case 0 => "Zero"
        case 1 => "One"
        case _ => "Other"
      }

    check(makeProp(matchFn1))

    val matchFn2 = (x: Int) => {
      val fn: Int => String = {
        case 0 => "Zero"
        case 1 => "One"
        case _ => "Other"
      }
      fn(x)
    }

    check(makeProp(matchFn2))
  }
}
