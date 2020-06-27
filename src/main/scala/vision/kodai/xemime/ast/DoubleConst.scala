package vision.kodai.xemime.ast

import scala.util.parsing.input.Position

case class DoubleConst(val pos: Position, val value: Double) extends Ast
