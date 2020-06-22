package vision.kodai.xemime

import SampleLens._

object Main {
  def main(args: Array[String]) {
    val employee = Employee(Company(Address(Street("foo"))))
    val lens =
      _company composeLens _address composeLens _street composeLens _name
    val name      = lens get employee
    val employee2 = lens.set("bar")(employee)
    val employee3 = lens.modify(_.capitalize)(employee)
    println(name)      // => foo
    println(employee2) // => Employee(Company(Address(Street(bar))))
    println(employee3) // => Employee(Company(Address(Street(Foo))))
  }
}
