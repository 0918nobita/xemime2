package vision.kodai.xemime

import vision.kodai.xemime.ast.{
  AddOp,
  Ast,
  BinExpr,
  DoubleConst,
  IntConst,
  StrConst
}
import vision.kodai.xemime.entity.{XeDouble, XeInt, XeStr, XeValue}

object Runtime {
  def run(program: Ast): Either[String, XeValue] =
    program match {
      case IntConst(_, value)    => Right(XeInt(value))
      case DoubleConst(_, value) => Right(XeDouble(value))
      case StrConst(_, value)    => Right(XeStr(value))
      case BinExpr(_, lhs, AddOp(_), rhs) =>
        for {
          lhsVal <- run(lhs)
          lhsInt <- lhsVal match {
            case XeInt(v) => Right(v)
            case _        => Left("TypeError")
          }
          rhsVal <- run(rhs)
          rhsInt <- rhsVal match {
            case XeInt(v) => Right(v)
            case _        => Left("TypeError")
          }
        } yield XeInt(lhsInt + rhsInt)
      case _ => Left("Unimplemented")
    }
}
