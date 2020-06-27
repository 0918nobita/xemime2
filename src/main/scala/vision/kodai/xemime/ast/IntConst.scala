package vision.kodai.xemime.ast

import scala.util.parsing.input.Position

case class IntConst(override val pos: Position, val value: Int) extends Ast
