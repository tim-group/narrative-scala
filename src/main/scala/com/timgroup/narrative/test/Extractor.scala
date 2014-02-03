package com.timgroup.narrative.test

trait Extractor[A, B <: Actor[_, B]] {
  def grabFor(actor: B): A
}