import org.scalacheck.Prop
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers

class StringTest extends AnyFunSuite with Checkers {
  test("startsWith") {
    check(Prop.forAll { (a: String, b: String) =>
      (a + b).startsWith(a)
    })
  }
}
