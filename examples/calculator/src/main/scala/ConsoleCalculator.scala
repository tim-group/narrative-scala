
object ConsoleCalculator {

  def main(args: Array[String]) {
    val calculator = new Calculator

    println("Welcome to the calculator")

    val input = readLine()

    while(true) {
      val keypress = readChar()
      calculator.press(keypress)
      println("You pressed " + keypress + ". Display reads: " + calculator.read());
    }

  }
}

