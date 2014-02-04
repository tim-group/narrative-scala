package com.timgroup.test.narrative

import org.scalatest.{ShouldMatchers, FunSpec}
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito.{verify, times, when}
import org.mockito.Mockito

class GivenSpec extends FunSpec with MockitoSugar with ShouldMatchers {

  describe("Given:") {
    it("uses the actor to perform the action") {
      val actor = mock[StringActor]
      val action = mock[Action[String, StringActor]]

      Given.the(actor).was_able_to(action)

      verify(actor, times(1)).perform(action)
    }

    it("can perform many actions in a row") {
      val actor = mock[StringActor]
      val action = mock[Action[String, StringActor]]("first action")
      val otherAction = mock[Action[String, StringActor]]("second action")

      Given.the(actor).was_able_to(action).and_to(otherAction)

      verify(actor, times(1)).perform(action)
      verify(actor, times(1)).perform(otherAction)
    }

    it("can mix calls to was_able_to and to and_to") {
      val actor = mock[StringActor]
      val firstAction = mock[Action[String, StringActor]]("first action")
      val secondAction = mock[Action[String, StringActor]]("second action")
      val thirdAction = mock[Action[String, StringActor]]("third action")
      val fourthAction = mock[Action[String, StringActor]]("fourth action")

      Given.the(actor).was_able_to(firstAction)
                      .and_to(secondAction)
                      .was_able_to(thirdAction)
                      .and_to(fourthAction)

      val order = Mockito.inOrder(actor)

      order.verify(actor).perform(firstAction)
      order.verify(actor).perform(secondAction)
      order.verify(actor).perform(thirdAction)
      order.verify(actor).perform(fourthAction)

    }

    it("can re-throw exceptions from actors as unchecked exceptions") {
      val actor = mock[StringActor]
      val action = mock[Action[String, StringActor]]
      val throwable = new RuntimeException("Something Broke")

      when(actor.perform(action)).thenThrow(throwable)

      val thrown = intercept[RuntimeException] {
        Given.the(actor).was_able_to(action)
      }

      thrown.getMessage should endWith("Something Broke")
      thrown.getCause should be(throwable)
    }
   
  }



  trait StringActor extends Actor[String, StringActor]
}
