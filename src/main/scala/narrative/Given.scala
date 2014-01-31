package narrative

class Given[A](val actor: Actor[A]) {

  def was_able_to(action: Action[A]): Given[A] = {
    actor.perform(action)
    this
  }

  def and_the = Given.the _

}

object Given {
  def the[T](actor: Actor[T]) = new Given(actor)
}
