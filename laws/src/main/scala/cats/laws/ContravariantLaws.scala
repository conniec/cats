package cats.laws

import cats.functor.Contravariant
import cats.syntax.contravariant._

/**
 * Laws that must be obeyed by any [[cats.functor.Contravariant]].
 */
class ContravariantLaws[F[_]](implicit F: Contravariant[F]) extends InvariantLaws[F] {
  def contravariantIdentity[A](fa: F[A]): (F[A], F[A]) =
    fa.contramap(identity[A]) -> fa

  def contravariantComposition[A, B, C](fa: F[A], f: B => A, g: C => B): (F[C], F[C]) =
    fa.contramap(f).contramap(g) -> fa.contramap(f compose g)
}
