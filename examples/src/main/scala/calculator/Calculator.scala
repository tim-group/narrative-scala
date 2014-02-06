package calculator


class Calculator {

  var accumulator: Int = 0
  var currentOperator = Operators.plus

  def press(keypress: Char) {
    if (keypress.isDigit)
      accumulator = currentOperator.apply(accumulator, Character.digit(keypress, 10))
    else
      currentOperator = Operators.lookup(keypress)
  }

  def read(): String = {
    accumulator.toString
  }


  object Operators {

    trait Operator {
      def apply(accumulator: Int, value: Int): Int
    }

    val plus = new Operator {
      def apply(accumulator: Int, value: Int) = accumulator + value
    }

    val minus = new Operator {
      def apply(accumulator: Int, value: Int) = accumulator - value
    }

    val equals = new Operator {
      def apply(accumulator: Int, value: Int) = accumulator
    }

    def lookup(keypress: Char): Operator = {
      keypress match {
        case '+' => plus
        case '-' => minus
        case '=' => equals
        case _ => null
      }
    }
  }


}
