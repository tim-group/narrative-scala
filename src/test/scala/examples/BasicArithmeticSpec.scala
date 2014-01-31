package examples

import org.scalatest.{ShouldMatchers, FunSpec}
import narrative._

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


  def press(keypress: Char): Action[Calculator] = {
    new Action[Calculator] {
      def performFor(actor: Actor[Calculator]) = actor.tool().press(keypress)
    }
  }

  def the_displayed_value: Extractor[Calculator, String] = {
    new Extractor[Calculator, String] {
      def grabFor(actor: Actor[Calculator]) = actor.tool().read()
    }
  }

}


class CalculatorActor extends Actor[Calculator] {

  val calculator = new Calculator

  def tool() = calculator

  def perform(action: Action[Calculator]) = action.performFor(this)

  def grabUsing[T](extractor: Extractor[Calculator, T]) = extractor.grabFor(this)
}