import org.scalacheck.{Gen, Prop}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers
import scala.util.parsing.combinator._

class ParsecTest extends AnyFunSuite with Checkers {
  test("Regex parsers") {
    object SimpleParser extends RegexParsers {
      def word: Parser[String] = "[a-z]+".r ^^ (_ + "!")
    }

    val genNelOfAlphaLowerChars =
      Gen.nonEmptyListOf(Gen.alphaLowerChar).map(_.mkString)

    val genNelOfMarks = Gen
      .nonEmptyListOf(Gen.oneOf(Seq('!', '\"', '$', '%', '&')))
      .map(_.mkString)

    val gen = for {
      forward  <- genNelOfAlphaLowerChars
      backward <- genNelOfMarks
    } yield {
      (forward, forward + backward)
    }

    check(Prop.forAll(gen) {
      case (expected, src) =>
        val result = SimpleParser.parse(SimpleParser.word, src)
        result.successful && result.get == s"$expected!"
    })
  }
}
