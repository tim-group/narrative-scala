package narrative

trait Extractor[A, B] {
  def grabFor(actor: Actor[A]): B
}