package com.timgroup.test.narrative

import org.scalatest.FunSpec
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.exceptions.TestFailedException

class ThenSpec extends FunSpec with MockitoSugar with ShouldMatchers {

  describe("Then:") {
    it("grabs the data to check using the actor") {
      val actor = mock[StringActor]
      val extractor = mock[Extractor[String, StringActor]]

      when(actor.grabUsing(extractor)).thenReturn("a")

      Then.the(actor).expects_that(extractor)(_ should be("a"))

      verify(actor, times(1)).grabUsing(extractor)
    }

    it("fails if the grabbed data does not match") {
      val actor = mock[StringActor]
      val extractor = mock[Extractor[String, StringActor]]

      when(actor.grabUsing(extractor)).thenReturn("b")

      val failure = intercept[TestFailedException] {
        Then.the(actor).expects_that(extractor)(_ should be("a"))
      }

      failure.getMessage() should be(""""[b]" was not equal to "[a]"""")

    }

    it("can assert for different actors") {
      val actor = mock[StringActor]
      val otherActor = mock[BooleanActor]

      val characterExtractor = mock[Extractor[Char, StringActor]]("character extractor")
      val booleanExtractor = mock[Extractor[Boolean, BooleanActor]]("boolean extractor")

      when(actor.grabUsing(characterExtractor)).thenReturn('a')
      when(otherActor.grabUsing(booleanExtractor)).thenReturn(true)


      Then.the(actor).expects_that(characterExtractor)(_ should be('a'))
          .and_the(otherActor).expects_that(booleanExtractor)(_ should be(true))

    }
  }
}
