import monocle.Lens
import org.scalacheck.{Arbitrary, Prop}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers

case class Street(name: String)
case class Address(street: Street)
case class Company(address: Address)
case class Employee(company: Company)

class MonocleTest extends AnyFunSuite with Checkers {
  import Arbitrary.arbitrary
  import Prop.forAll

  implicit val street: Arbitrary[Street] =
    Arbitrary(arbitrary[String].map(Street))

  implicit val address: Arbitrary[Address] =
    Arbitrary(arbitrary[Street].map(Address))

  implicit val company: Arbitrary[Company] =
    Arbitrary(arbitrary[Address].map(Company))

  implicit val employee: Arbitrary[Employee] =
    Arbitrary(arbitrary[Company].map(Employee))

  val _name: Lens[Street, String] =
    Lens[Street, String](_.name) { str => s => s.copy(name = str) }

  val _street: Lens[Address, Street] =
    Lens[Address, Street](_.street) { s => a => a.copy(street = s) }

  val _address: Lens[Company, Address] =
    Lens[Company, Address](_.address) { a => c => c.copy(address = a) }

  val _company: Lens[Employee, Company] =
    Lens[Employee, Company](_.company) { c => e => e.copy(company = c) }

  val lens: Lens[Employee, String] =
    _company composeLens _address composeLens _street composeLens _name

  test("get") {
    check(forAll { (employee: Employee) =>
      val expected = employee.company.address.street.name
      val actual   = lens get employee
      actual == expected
    })
  }

  test("set") {
    check(forAll { (employee: Employee) =>
      val employee2 = lens.set("fleet")(employee)
      employee2 == Employee(Company(Address(Street("fleet"))))
    })
  }

  test("modify") {
    check(forAll { (employee: Employee) =>
      val name      = lens get employee
      val employee2 = lens.modify(_.capitalize)(employee)
      employee2 == Employee(Company(Address(Street(name.capitalize))))
    })
  }
}
