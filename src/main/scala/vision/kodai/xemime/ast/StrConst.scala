package vision.kodai.xemime.ast

import vision.kodai.xemime.Position

case class StrConst(override val pos: Position, val value: String) extends Ast
