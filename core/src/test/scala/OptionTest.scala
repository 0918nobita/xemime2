import org.scalatest.funsuite.AnyFunSuite

class OptionTest extends AnyFunSuite {
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

  test("Option.getOrElse") {
    def maybeItWillReturnSomething(cond: Boolean): Option[String] =
      if (cond) Some("Found value") else None

    val value1 = maybeItWillReturnSomething(true)
    val value2 = maybeItWillReturnSomething(false)
    assert(value1.getOrElse("No value") == "Found value")
    assert(value2.getOrElse("No value") == "No value")
  }

  test("Option.map/fold") {
    val number: Option[Int]   = Some(3)
    val noNumber: Option[Int] = None
    val result1               = number.map(_ * 1.5)
    val result2               = noNumber.map(_ * 1.5)
    assert(result1.contains(4.5))
    assert(result2.isEmpty)

    assert(number.fold(ifEmpty = 8)(f = _ * 2) == 6)
    assert(noNumber.fold(ifEmpty = 8)(f = _ * 2) == 8)
  }
}
