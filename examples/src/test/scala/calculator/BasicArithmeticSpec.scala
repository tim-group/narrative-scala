package calculator


import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import com.timgroup.test.narrative._

class BasicArithmeticSpec extends FunSpec with ShouldMatchers {


  describe("the calculator") {

    it("can add two numbers") {

      val operator = new CalculatorActor

      Given.the(operator).was_able_to(press('2'))
                         .was_able_to(press('+'))
                         .was_able_to(press('2'))

      When.the(operator).attempts_to(press('='))

      Then.the(operator).expects_that(the_displayed_value)(_ should be("4"))
    }

    it("can subtract two numbers") {

      val operator = new CalculatorActor

      Given.the(operator).was_able_to(press('3'))
                         .was_able_to(press('-'))
                         .was_able_to(press('1'))

      When.the(operator).attempts_to(press('='))

      Then.the(operator).expects_that(the_displayed_value)(_ should be("2"))
    }
  }


  def press(keypress: Char): Action[Calculator, CalculatorActor] = {
    new Action[Calculator, CalculatorActor] {
      def performFor(actor: CalculatorActor) = actor.tool().press(keypress)
    }
  }

  def the_displayed_value: Extractor[String, CalculatorActor] = {
    new Extractor[String, CalculatorActor] {
      def grabFor(actor: CalculatorActor) = actor.tool().read()
    }
  }

}
