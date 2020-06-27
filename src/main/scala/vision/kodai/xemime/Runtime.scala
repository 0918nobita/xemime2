package vision.kodai.xemime

import vision.kodai.xemime.ast.{AddOp, Ast, BinExpr, DoubleConst, IntConst}
import vision.kodai.xemime.entity.{XeDouble, XeInt, XeValue}

object Runtime {
  def run(program: Ast): Either[String, XeValue] =
    program match {
      case IntConst(_, value)    => Right(XeInt(value))
      case DoubleConst(_, value) => Right(XeDouble(value))
      case BinExpr(_, lhs, AddOp(_), rhs) =>
        run(lhs).flatMap {
          case lhsVal: XeInt =>
            run(rhs).flatMap {
              case rhsVal: XeInt => Right(XeInt(lhsVal.value + rhsVal.value))
              case _             => Left("TypeError")
            }
          case _ => Left("TypeError")
        }
      case _ => Left("Unimplemented")
    }
}
