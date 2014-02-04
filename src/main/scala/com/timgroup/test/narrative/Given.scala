package com.timgroup.test.narrative

class Given[A, B <: Actor[A, B]](val actor: Actor[A, B]) {

  def was_able_to(action: Action[A, B]): Given[A, B] = {
    try {
      actor.perform(action)
    } catch {
      case t: Throwable => throw new RuntimeException(t)
    }

    this
  }

  def and_to = was_able_to _

  def and_the = Given.the _

}

object Given {
  def the[A, B <: Actor[A, B]](actor: Actor[A, B]) = new Given(actor)
}
