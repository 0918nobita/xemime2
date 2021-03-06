package vision.kodai.xemime.ast

import vision.kodai.xemime.Position

case class BinExpr(
    override val pos: Position,
    val lhs: Ast,
    val op: Ast,
    val rhs: Ast
) extends Ast
