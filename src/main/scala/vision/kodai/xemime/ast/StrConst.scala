package vision.kodai.xemime.ast

import scala.util.parsing.input.Position

case class StrConst(override val pos: Position, val value: String) extends Ast
