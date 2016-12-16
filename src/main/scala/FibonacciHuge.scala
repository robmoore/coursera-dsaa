import java.util.Scanner

object FibonacciHuge {

//  private def getFibonacciHugeNaive(n: Long, m: Long): Long = {
//    if (n <= 1) return n
//    var previous = 0
//    var current = 1
//    for (i <- 0 until n - 1) {
//      val tmp_previous = previous
//      previous = current
//      current = tmp_previous + current
//    }
//    current % m
//  }

  /**
    * For m = 2, the period is 011 and has length 3, while for m = 3 the period is 01120221 and has length 8. Therefore,
    * to compute, say, F 2015 mod 3 we just need to find the remainder of 2015 when divided by 8. Since
    * 2015 = 251 · 8 + 7, we conclude that F 2015 mod 3 = F 7 mod 3 = 1.
    */
  private def getFibonacciHuge(n: Long, m: Long): Long = {
    if (n <= 1) return n
    // TODO figure out a way to determine length of Pisano period
    // perhaps look for 011 as the marker of the beginning? must be evenly divisible?
    // The sequence must start to repeat itself no later than n=m^2
    (n % (scala.math.pow(m, 2) - 1) % m).toLong // 2015 % ((3 ^ 2) - 1) % 3
  }

  /**
    * Task. Given two integers n and m, output F n mod m (that is, the remainder of F n when divided by m).
    * Input Format. The input consists of two integers n and m given on the same line (separated by a space).
    * Constraints. 1 ≤ n ≤ 10^18 , 2 ≤ m ≤ 10^5 .
    * Output Format. Output F n mod m.
    */
  def main(args: Array[String]) {
    val scanner = new Scanner(System.in)
    val n = scanner.nextLong()
    require(n >= 1 && n <= Math.pow(10, 18))
    val m = scanner.nextLong()
    require(m >= 2 && m <= Math.pow(10, 5))
    println(getFibonacciHuge(n, m))
  }
}
