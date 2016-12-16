import java.util.Scanner

import scala.annotation.tailrec

object GCD {
  private def gcd_naive(a: Int, b: Int): Int = {
    var current_gcd: Int = 1
    var d: Int = 2
    while (d <= a && d <= b) {
      {
        if (a % d == 0 && b % d == 0) {
          if (d > current_gcd) {
            current_gcd = d
          }
        }
      }
      {
        d += 1; d
      }
    }
    current_gcd
  }

  @tailrec
  private def euclidGcd(a: Int, b: Int): Int = {
    if (b == 0)
      return a
    val aPrime = a % b
    euclidGcd(b, aPrime)
  }

  /*
  Problem Description
  Task. Given two integers a and b, find their greatest common divisor.
  Input Format. The two integers a, b are given in the same line separated by space.
  Constraints. 1 ≤ a, b ≤ 2 * 10^9
  Output Format. Output GCD(a, b).
   */
  def main(args: Array[String]) {
    val scanner: Scanner = new Scanner(System.in)
    val a: Int = scanner.nextInt
    val b: Int = scanner.nextInt
    require(a >= 1 && b <= 2 * Math.pow(10, 9))
    //println(gcd_naive(a, b))
    println(euclidGcd(a, b))
  }
}