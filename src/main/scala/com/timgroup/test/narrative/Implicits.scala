package com.timgroup.test.narrative

trait Implicits {
	implicit class FunctionAsAction[A, B <: Actor[A, B]](f: B => Unit) extends Action[A, B] {
	  override def performFor(actor: B): Unit = f(actor)
	}
	
	implicit class FunctionAsExtractor[A, B <: Actor[_, B]](f: B => A) extends Extractor[A, B] {
	  override def grabFor(actor: B): A = f(actor)
	}
}

object Implicits extends Implicits