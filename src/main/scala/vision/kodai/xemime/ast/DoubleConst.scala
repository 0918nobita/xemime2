package vision.kodai.xemime.ast

import scala.util.parsing.input.Position

case class DoubleConst(override val pos: Position, val value: Double)
    extends Ast
