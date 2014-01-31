package narrative

class Given[A](val actor: Actor[A]) {

  def was_able_to(action: Action[A]): Given[A] = {
    actor.perform(action)
    this
  }

  def the = Given.the _
  def and_the = Given.and_the _

}

object Given {
  def the[T](actor: Actor[T]) = new Given(actor)

  def and_the[T](actor: Actor[T]) = new Given(actor)
}
