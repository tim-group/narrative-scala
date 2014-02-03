package com.timgroup.narrative.test

class Then[A, B <: Actor[A, B]](val actor: Actor[A, B]) {

  def expects_that[T](expected: Extractor[T, B])(matcher: T => Unit): Then[A, B] = {
    matcher(actor.grabUsing(expected))
    this
  }

  def and_that = expects_that _

  def and_the = Given.the _

}

object Then {
  def the[A, B <: Actor[A, B]](actor: Actor[A, B]) = new Then(actor)
}
