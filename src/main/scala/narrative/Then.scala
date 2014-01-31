package narrative

class Then[A](val actor: Actor[A]) {

  def expects_that[T](expected: Extractor[A, T])(matcher: T => Unit): Then[A] = {
    matcher(actor.grabUsing(expected))
    this
  }

  def and_that = expects_that _

  def and_the = Given.the _

}

object Then {
  def the[T](actor: Actor[T]) = new Then(actor)
}
