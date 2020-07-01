import org.scalacheck.Prop.forAll
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers

class FormatTest extends AnyFunSuite with Checkers {
  test("%s") {
    check(forAll { (s: String) => "Hello, %s".format(s) == s"Hello, $s" })
  }

  test("%c") {
    check(forAll { (c: Char) => "%c".format(c) == c.toString })
  }

  test("%d") {
    check(forAll { (n: Int) =>
      ("[%d]" format n - 1) == s"[${(n - 1).toString}]"
    })
  }

  test("Multiple arguments") {
    check(forAll { (n: Int, str: String) =>
      "[%d, %s]".format(n, str) == s"[${n.toString}, $str]"
    })
  }
}
