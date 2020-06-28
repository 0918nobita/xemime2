import org.scalacheck.{Gen, Prop}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers
import scala.util.parsing.combinator.{JavaTokenParsers, RegexParsers}
import scala.util.parsing.input.CharSequenceReader

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

  test("case class `~` (A wrapper over sequence of matches)") {
    object MyParser extends JavaTokenParsers {
      def concat(p1: Parser[String], p2: Parser[String]): Parser[String] =
        p1 ~ p2 ^^ { case a ~ b => a + b }

      def parse(input: String): ParseResult[String] =
        concat("a", "b")(new CharSequenceReader(input))
    }

    assert(MyParser.parse("ab ").get == "ab")
    assert(!MyParser.parse("ax").successful)
  }
}
