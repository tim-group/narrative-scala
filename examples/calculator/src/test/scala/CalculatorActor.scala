import com.timgroup.test.narrative._

class CalculatorActor extends Actor[Calculator, CalculatorActor] {

  val calculator = new Calculator

  def tool() = calculator

  def perform(action: Action[Calculator, CalculatorActor]) = action.performFor(this)

  def grabUsing[T](extractor: Extractor[T, CalculatorActor]) = extractor.grabFor(this)
}