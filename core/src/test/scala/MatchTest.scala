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

  test("Substitute parts of expressions / Use guards") {
    def matchFn(pair: (Int, String)) =
      pair match {
        case (n, str) if n % 2 == 0 => s"2$str"
        case (n, str) if n % 3 == 0 => s"3$str"
        case (n, str) => s"($n)$str"
      }

    check(forAll { (pair: (Int, String)) =>
      val (n, str) = pair
      val result   = matchFn(pair)
      (n % 2 == 0) ==> (result == s"2$str") ||
      (n % 3 == 0) ==> (result == s"3$str") ||
      result == s"($n)$str"
    })
  }
}
