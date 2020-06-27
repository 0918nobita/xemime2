package vision.kodai.xemime.ast

import vision.kodai.xemime.Position

case class IntConst(override val pos: Position, val value: Int) extends Ast
