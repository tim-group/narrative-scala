package com.timgroup.test.narrative

class Given[A, B <: Actor[A, B]](val actor: Actor[A, B]) {

  def was_able_to(action: Action[A, B]): Given[A, B] = {
    actor.perform(action)
    this
  }

  def and_to = was_able_to _

  def and_the[T, U <: Actor[T, U]] = Given.the[T, U] _

}

object Given {
  def the[T, U <: Actor[T, U]](actor: Actor[T, U]) = new Given(actor)
}
