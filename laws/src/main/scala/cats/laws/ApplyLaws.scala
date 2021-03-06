package cats.laws

import cats.Apply
import cats.syntax.apply._
import cats.syntax.functor._

/**
 * Laws that must be obeyed by any [[Apply]].
 */
class ApplyLaws[F[_]](implicit F: Apply[F]) extends FunctorLaws[F] {
  def applyComposition[A, B, C](fa: F[A], fab: F[A => B], fbc: F[B => C]): (F[C], F[C]) = {
    val compose: (B => C) => (A => B) => (A => C) = _.compose
    fa.apply(fab).apply(fbc) -> fa.apply(fab.apply(fbc.map(compose)))
  }
}
