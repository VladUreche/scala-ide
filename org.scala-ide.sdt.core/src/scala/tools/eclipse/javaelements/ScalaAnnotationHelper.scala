package scala.tools.eclipse.javaelements

import scala.tools.eclipse.ScalaPresentationCompiler

trait ScalaAnnotationHelper { self: ScalaPresentationCompiler =>

  // The following two fiels can be removed once we drop support for 2.8.x. Simply use
  // definitions.TransientAttr and deficitions.ScalaStrictFPAttr
  private lazy val TransientAttr: Symbol = definitions.getClass("scala.transient")
  private lazy val ScalaStrictFPAttr: Symbol = {
    try {
      definitions.getClass("scala.annotation.strictfp")
    } catch {
      // this annotation does not exists on 2.8.x, therefore
      // we intercept the exception
      case e: scala.tools.nsc.MissingRequirementError =>
        NoSymbol
    }
  }

  protected def hasTransientAnn(sym: Symbol) = sym.hasAnnotation(TransientAttr)
  protected def hasVolatileAnn(sym: Symbol) = sym.hasAnnotation(definitions.VolatileAttr)
  protected def hasNativeAnn(sym: Symbol) = sym.hasAnnotation(definitions.NativeAttr)
  protected def hasStrictFPAnn(sym: Symbol) = sym.hasAnnotation(ScalaStrictFPAttr)
  protected def hasThrowsAnn(sym: Symbol) = sym.hasAnnotation(definitions.ThrowsClass)
  protected def hasDeprecatedAnn(sym: Symbol) = sym.hasAnnotation(definitions.DeprecatedAttr)

  protected def isScalaAnnotation(ann: AnnotationInfo) = {
    val isJava = ann.atp.typeSymbol.isJavaDefined
    !isJava
  }
}