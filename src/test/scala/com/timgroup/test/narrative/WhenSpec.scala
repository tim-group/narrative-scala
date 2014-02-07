package com.timgroup.test.narrative

import org.scalatest.FunSpec
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar

class WhenSpec extends FunSpec with MockitoSugar{

  describe("When:") {
    it("uses the actor to perform the action") {
      val actor = mock[StringActor]
      val action = mock[Action[String, StringActor]]

      When.the(actor).attempts_to(action)

      verify(actor, times(1)).perform(action)
    }

    it("can perform many actions in a row") {
      val actor = mock[StringActor]
      val action = mock[Action[String, StringActor]]("first action")
      val otherAction = mock[Action[String, StringActor]]("second action")

      When.the(actor).attempts_to(action).attempts_to(otherAction)


      val order = inOrder(actor)

      order.verify(actor).perform(action)
      order.verify(actor).perform(otherAction)
    }

    it("can mix calls to attempts_to and to and_to") {
      val actor = mock[StringActor]
      val firstAction = mock[Action[String, StringActor]]("first action")
      val secondAction = mock[Action[String, StringActor]]("second action")
      val thirdAction = mock[Action[String, StringActor]]("third action")
      val fourthAction = mock[Action[String, StringActor]]("fourth action")

      When.the(actor).attempts_to(firstAction)
                     .and_to(secondAction)
                     .attempts_to(thirdAction)
                     .and_to(fourthAction)

      val order = inOrder(actor)

      order.verify(actor).perform(firstAction)
      order.verify(actor).perform(secondAction)
      order.verify(actor).perform(thirdAction)
      order.verify(actor).perform(fourthAction)

    }
  }
}
