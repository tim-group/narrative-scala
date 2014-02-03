package com.timgroup.narrative.test

class Given[A, B <: Actor[A, B]](val actor: Actor[A, B]) {

  def was_able_to(action: Action[A, B]): Given[A, B] = {
    actor.perform(action)
    this
  }

  def and_the = Given.the _

}

object Given {
  def the[A, B <: Actor[A, B]](actor: Actor[A, B]) = new Given(actor)
}
