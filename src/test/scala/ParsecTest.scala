import org.scalacheck.{Gen, Prop}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers
import scala.util.parsing.combinator.{JavaTokenParsers, RegexParsers}

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

  test("JavaTokenParsers.ident") {
    object TokenParser extends JavaTokenParsers {
      def parse(input: String): ParseResult[String] =
        parseAll(ident, input)
    }

    assert(TokenParser.parse("a ").get == "a")
  }
}
