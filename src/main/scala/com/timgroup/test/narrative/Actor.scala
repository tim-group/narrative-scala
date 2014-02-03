package com.timgroup.test.narrative

trait Actor[A, B <: Actor[A, B]] {

  def tool(): A

  def perform(action: Action[A, B]): Unit

  def grabUsing[T](extractor: Extractor[T, B]): T

}

