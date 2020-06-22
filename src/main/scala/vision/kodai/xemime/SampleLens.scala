package vision.kodai.xemime

import monocle.Lens

case class Street(name: String)
case class Address(street: Street)
case class Company(address: Address)
case class Employee(company: Company)

object SampleLens {
  val _name: Lens[Street, String] = {
    val streetGetter = { s: Street => s.name }
    val streetSetter = { str: String => s: Street =>
      s.copy(name = str)
    }
    Lens(get = streetGetter)(set = streetSetter)
  }

  val _street: Lens[Address, Street] = Lens[Address, Street](_.street)({
    s => a => a.copy(street = s)
  })

  val _address: Lens[Company, Address] = Lens[Company, Address](_.address)({
    a => c => c.copy(address = a)
  })

  val _company: Lens[Employee, Company] = Lens[Employee, Company](_.company)({
    c => e => e.copy(company = c)
  })
}
