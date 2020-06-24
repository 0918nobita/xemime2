import org.scalatest.funsuite.AnyFunSuite
import scala.util.parsing.combinator._

class ParsecTest extends AnyFunSuite {
  test("Regex parsers") {
    object SimpleParser extends RegexParsers {
      def word: Parser[String] = "[a-z]+".r ^^ (_ + "!")
    }

    val result = SimpleParser.parse(SimpleParser.word, "abc7")
    assert(result.successful)
    assert(result.get == "abc!")
  }
}
