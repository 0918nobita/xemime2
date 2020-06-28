import org.scalacheck.Prop
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers
import scala.util.parsing.combinator.{JavaTokenParsers, RegexParsers}
import scala.util.parsing.input.CharSequenceReader
import scalacheck.{ExtendedGen => ExGen}

class ParsecTest extends AnyFunSuite with Checkers {
  test("Regex parsers") {
    object SimpleParser extends RegexParsers {
      def word: Parser[String] = "[a-z]+".r ^^ (_ + "!")

      def parse(input: String): ParseResult[String] =
        word(new CharSequenceReader(input))
    }

    val gen = for {
      forward  <- ExGen.nonEmptyLowerChars
      backward <- ExGen.nonEmptyStringOf(ExGen.mark)
    } yield {
      (forward, forward + backward)
    }

    check(Prop.forAll(gen) {
      case (expected, src) =>
        val result = SimpleParser.parse(src)
        result.successful && result.get == s"$expected!"
    })
  }

  test("JavaTokenParsers.ident") {
    object TokenParser extends JavaTokenParsers {
      def parse(input: String): ParseResult[String] =
        ident(new CharSequenceReader(input))
    }

    assertResult("a")(TokenParser.parse("a ").get)
  }

  test("case class `~` (A wrapper over sequence of matches)") {
    object MyParser extends JavaTokenParsers {
      def concat(p1: Parser[String], p2: Parser[String]): Parser[String] =
        p1 ~ p2 ^^ { case a ~ b => a + b }

      def parse(input: String): ParseResult[String] =
        concat("a", "b")(new CharSequenceReader(input))
    }

    assertResult("ab")(MyParser.parse("ab ").get)
    assert(!MyParser.parse("ax").successful)
  }
}
