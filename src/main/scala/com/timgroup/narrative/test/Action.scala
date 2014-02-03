package com.timgroup.narrative.test

trait Action[A, B <: Actor[A, B]] {

  def performFor(actor: B): Unit
}
