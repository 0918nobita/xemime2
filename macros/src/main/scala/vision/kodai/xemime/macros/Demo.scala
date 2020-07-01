package vision.kodai.xemime.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object Demo {
  def desugar(a: Any): String = macro desugarImpl

  def desugarImpl(c: blackbox.Context)(a: c.Expr[Any]): c.Expr[Nothing] = {
    import c.universe._
    val s = show(a.tree)
    c.Expr(Literal(Constant(s)))
  }
}
