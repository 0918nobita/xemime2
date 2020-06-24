import monocle.Lens
import org.scalatest.funsuite.AnyFunSuite

class MonocleTest extends AnyFunSuite {
  test("Create / Compose / Use Lens") {
    case class Street(name: String)
    case class Address(street: Street)
    case class Company(address: Address)
    case class Employee(company: Company)

    val _name = Lens[Street, String](_.name) { str => s => s.copy(name = str) }
    val _street = Lens[Address, Street](_.street) { s => a =>
      a.copy(street = s)
    }
    val _address = Lens[Company, Address](_.address) { a => c =>
      c.copy(address = a)
    }
    val _company = Lens[Employee, Company](_.company) { c => e =>
      e.copy(company = c)
    }

    val employee = Employee(Company(Address(Street("bourbon"))))
    val lens =
      _company composeLens _address composeLens _street composeLens _name
    assert((lens get employee) == "bourbon")

    val employee2 = lens.set("fleet")(employee)
    assert(employee2 == Employee(Company(Address(Street("fleet")))))

    val employee3 = lens.modify(_.capitalize)(employee)
    assert(employee3 == Employee(Company(Address(Street("Bourbon")))))
  }
}
