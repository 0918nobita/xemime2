package scalacheck

import org.scalacheck.Gen

object ExtendedGen {
  def mark: Gen[Char] = Gen.oneOf(Seq('!', '?', '\"', '$', '%', '&', '@'))

  def nonEmptyStringOf(gen: Gen[Char]): Gen[String] =
    Gen.nonEmptyListOf(gen).map(_.mkString)

  def nonEmptyLowerChars: Gen[String] =
    ExtendedGen.nonEmptyStringOf(Gen.alphaLowerChar)
}
