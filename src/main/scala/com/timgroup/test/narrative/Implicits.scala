package com.timgroup.test.narrative

trait Implicits {
  implicit def functionToAction[A, B <: Actor[A, B]](f: B => Unit) = new Action[A, B] {
    override def performFor(actor: B): Unit = f(actor)
  }

  implicit def functionToExtractor[A, B <: Actor[_, B]](f: B => A) = new Extractor[A, B] {
    override def grabFor(actor: B): A = f(actor)
  }
}

object Implicits extends Implicits