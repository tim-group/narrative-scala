package narrative

trait Actor[A] {

  def tool(): A

  def perform(action: Action[A]): Unit

  def grabUsing[T](extractor: Extractor[A, T]): T

}

