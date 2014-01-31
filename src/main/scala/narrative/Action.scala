package narrative

trait Action[A] {

  def performFor(actor: Actor[A]): Unit
}
