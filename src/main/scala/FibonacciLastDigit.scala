import java.util._

object FibonacciLastDigit {
//  private def getFibonacciLastDigitNaive(n: Int): BigInt = {
//    if (n <= 1) return n
//    var previous: BigInt = 0
//    var current: BigInt = 1
//    for (i <- 0 until n - 1) {
//      val tmp_previous = previous
//      previous = current
//      current = tmp_previous + current
//    }
//    current mod 10
//  }

//  private def getFibonacciLastDigitBinet(n: Int): Int = {
//    //1/sqrt(5) * ((1 + sqrt(5)/2)^n - (1 - sqrt(5)/2)^n)
//    if (n <= 1) return n
//    val fib = (1 / Math.sqrt(5)) * (Math.pow((1 + Math.sqrt(5)) / 2, n) - Math.pow((1 - Math.sqrt(5)) / 2, n))
//    (fib % 10).toInt
//  }

  // Stolen from https://bosker.wordpress.com/2011/07/27/computing-fibonacci-numbers-using-binet%E2%80%99s-formula/
  private def getFibonacciLastDigitBinet(n: Int): Int = {
    val phi = (Math.sqrt(5) + 1) / 2.0
    Math.round(Math.pow(phi, n) / Math.sqrt(5)).toInt
  }

  def getLastDigits(n: Int): Array[BigInt] = {
    // Stolen from lecture:
    //    create an array F [0 . . . n]
    //    F [0] ← 0
    //    F [1] ← 1
    //    for i from 2 to n:
    //      F [i] ← F [i − 1] + F [i − 2]
    //    return F [n]

    val numbers = new Array[BigInt](n + 1)
    numbers(0) = 0
    if (n > 0) // index out of bounds in n = 0 case
      numbers(1) = 1
    for (i <- 2 to n) {
      numbers(i) = (numbers(i - 1) + numbers(i - 2)) mod 10
    }
    numbers
  }

  // The last digit of Fibonacci numbers cycle every 60 so we can just calculate the first 60 last digits and recycle
  private def getFibonacciLastDigitFast(n: Int): BigInt = {
    val lastDigits = getLastDigits(60)
    lastDigits(n % 60)
  }

  /*
  Input Format. The input consists of a single integer n.
  Constraints. 0 ≤ n ≤ 10^7 .
  Output Format. Output the last digit of F n .
   */

  def main(args: Array[String]) {
    val scanner = new Scanner(System.in)
    val n = scanner.nextInt()
    require(n >= 0 && n <= Math.pow(10, 7))
    //val c = getFibonacciLastDigitFast(n)
    val c = getFibonacciLastDigitBinet(n % 60) % 10
    println(c)
  }

  private def time[R](block: => R): R = {
    val t0 = System.currentTimeMillis()
    val result = block    // call-by-name
    val t1 = System.currentTimeMillis()
    println(s"Elapsed time: ${t1 - t0} ms")
    result
  }
}