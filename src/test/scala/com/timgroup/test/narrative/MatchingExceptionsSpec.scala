package com.timgroup.test.narrative

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import com.timgroup.test.narrative.Implicits._

class MatchingExceptionsSpec extends FunSpec with ShouldMatchers {

  val actor = new ExceptionActor
  val app_failure_exception = new AppSpecificException("Our app failed.")

  describe("Matching exceptions:") {

    it("can catch and match against exceptions") {
      Given.the(actor).was_able_to(remember_the_error_from(a_failing_action))
      Then.the(actor).expects_that(the_exception_thrown)(_ should equal(app_failure_exception))
    }
  }

  def remember_the_error_from(action: Action[Any, ExceptionActor]): Action[Any, ExceptionActor] = {
    (actor : ExceptionActor) => {
      try {
        action.performFor(actor)
      } catch {
        case e: AppSpecificException => actor.remember("exception", e)
      }
    }
  }

  def a_failing_action: Action[Any, ExceptionActor] = {
    (_: ExceptionActor) => throw app_failure_exception
  }

  def the_exception_thrown = {
    (actor: ExceptionActor) => {
      actor.recall("exception", classOf[Throwable])
    }
  }


  class ExceptionActor extends ActorWithMemory[Any, ExceptionActor] {

    private var memory = Map[Any, Any]()

    def grabUsing[T](extractor: Extractor[T, ExceptionActor]) = {
      extractor.grabFor(this)
    }

    def perform(action: Action[Any, ExceptionActor]) = {
      action.performFor(this)
    }

    def tool() = null

    def recall[T](identifier: Any, typeOfValue: Class[T]) = {
      memory.get(identifier).get.asInstanceOf[T]
    }

    def remember(identifier: Any, value: Any) = {
      memory += (identifier -> value)
    }

  }

  class AppSpecificException(message: String) extends Exception(message)

}
