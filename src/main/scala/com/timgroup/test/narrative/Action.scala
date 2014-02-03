package com.timgroup.test.narrative

trait Action[A, B <: Actor[A, B]] {

  def performFor(actor: B): Unit
}
