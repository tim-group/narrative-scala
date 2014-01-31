package narrative

class When[A](val actor: Actor[A]) {

  def attempts_to(action: Action[A]): When[A] = {
    actor.perform(action)
    this
  }

  def and_to = attempts_to _

  def and_the = When.the _

}

object When {
  def the[T](actor: Actor[T]) = new When(actor)
}