
import org.scalatest.{ShouldMatchers, FunSpec}
import com.timgroup.narrative.test._

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


class CalculatorActor extends Actor[Calculator, CalculatorActor] {

  val calculator = new Calculator

  def tool() = calculator

  def perform(action: Action[Calculator, CalculatorActor]) = action.performFor(this)

  def grabUsing[T](extractor: Extractor[T, CalculatorActor]) = extractor.grabFor(this)
}