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

  test("Pattern matching on Option") {
    val someValue: Option[Double] = Some(20.0)
    val doubleValue = someValue match {
      case Some(v) => v
      case None    => 0.0
    }
    assert(doubleValue == 20.0)

    val noneValue: Option[Double] = None
    val doubleValue2 = noneValue match {
      case Some(v) => v
      case None    => 0.0
    }
    assert(doubleValue2 == 0.0)
  }
}
