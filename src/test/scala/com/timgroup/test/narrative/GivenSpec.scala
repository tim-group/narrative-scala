package com.timgroup.test.narrative

import org.scalatest.FunSpec
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito.{verify, times, inOrder}

class GivenSpec extends FunSpec with MockitoSugar {

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

      Given.the(actor).was_able_to(action).was_able_to(otherAction)

      val order = inOrder(actor)

      order.verify(actor).perform(action)
      order.verify(actor).perform(otherAction)

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

      val order = inOrder(actor)

      order.verify(actor).perform(firstAction)
      order.verify(actor).perform(secondAction)
      order.verify(actor).perform(thirdAction)
      order.verify(actor).perform(fourthAction)

    }
   
  }
}
