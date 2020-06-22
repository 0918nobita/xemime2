import org.scalatest.funsuite.AnyFunSuite

class Test extends AnyFunSuite {
  test("Option.getOrElse") {
    def maybeItWillReturnSomething(cond: Boolean): Option[String] =
      if (cond) Some("Found value") else None

    val value1 = maybeItWillReturnSomething(true)
    val value2 = maybeItWillReturnSomething(false)
    assert(value1.getOrElse("No value") == "Found value")
    assert(value2.getOrElse("No value") == "No value")
  }
}
