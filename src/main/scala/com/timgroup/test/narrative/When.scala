package com.timgroup.test.narrative

class When[A, B <: Actor[A, B]](val actor: Actor[A, B]) {

  def attempts_to(action: Action[A, B]): When[A, B] = {
    actor.perform(action)
    this
  }

  def and_to = attempts_to _

  def and_the[T, U <: Actor[T, U]] = When.the[T, U] _
}

object When {
  def the[T, U <: Actor[T, U]](actor: Actor[T, U]) = new When(actor)
}