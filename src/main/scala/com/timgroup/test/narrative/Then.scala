package com.timgroup.test.narrative

class Then[A, B <: Actor[A, B]](val actor: Actor[A, B]) {

  def expects_that[T](expected: Extractor[T, B])(matcher: T => Unit): Then[A, B] = {
    matcher(actor.grabUsing(expected))
    this
  }

  def and_that[T] = expects_that[T] _

  def and_the[T, U <: Actor[T, U]] = Then.the[T, U] _

}

object Then {
  def the[T, U <: Actor[T, U]](actor: Actor[T, U]) = new Then(actor)
}
