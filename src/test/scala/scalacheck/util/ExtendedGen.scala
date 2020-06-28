package scalacheck.util

import org.scalacheck.Gen
import scala.language.implicitConversions

object ExtendedGen {
  def mark: Gen[Char] = Gen.oneOf(Seq('!', '?', '\"', '$', '%', '&', '@'))

  def nonEmptyStringOf(gen: Gen[Char]): Gen[String] =
    Gen.nonEmptyListOf(gen).map(_.mkString)

  def nonEmptyLowerChars: Gen[String] =
    ExtendedGen.nonEmptyStringOf(Gen.alphaLowerChar)
}

object ImplicitConversions {
  implicit def convert(gen: Gen.type): ExtendedGen.type = ExtendedGen
}
