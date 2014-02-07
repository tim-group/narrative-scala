package com.timgroup.test.narrative

trait ActorWithMemory[A, B <: Actor[A, B]] extends Actor[A, B] {

  def remember(identifier: Any, value: Any): Unit

  def recall[T](identifier: Any, typeOfValue: Class[T]): T

}