import org.scalacheck.{Prop, Properties}

class ScalaCheckTest extends Properties("ScalaCheck") {
  property("startsWith") = Prop.forAll { (a: String, b: String) =>
    (a + b).startsWith(a)
  }

  property("list head") = Prop.forAll { l: List[Int] =>
    if (l.isEmpty) {
      Prop.throws(classOf[NoSuchElementException]) { l.head }
    } else {
      l.head == l(0)
    }
  }

  property("list tail") = Prop.forAll { (n: Int, l: List[Int]) =>
    (n :: l).tail == l
  }
}
