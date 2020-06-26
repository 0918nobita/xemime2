package vision.kodai.xemime

import scala.util.parsing.input.Position

case class IntConst(val pos: Position, val value: Int) extends Ast
