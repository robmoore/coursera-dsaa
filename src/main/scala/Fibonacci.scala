import java.util.Scanner

import scala.annotation.tailrec

object Fibonacci {
  private def calc_fib(n: Int): Long = {
    if (n <= 1) return n
    calc_fib(n - 1) + calc_fib(n - 2)
  }

  def fib(x: Int): BigInt = {
    // Stolen from http://alvinalexander.com/scala/scala-recursion-examples-recursive-programming
    @tailrec def fibHelper(x: Int, prev: BigInt = 0, next: BigInt = 1): BigInt = x match {
      case 0 => prev
      case 1 => next
      case _ => fibHelper(x - 1, next, next + prev)
    }
    fibHelper(x)
  }

  def fib2(n: Int): BigInt = {
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
      numbers(i) = numbers(i - 1) + numbers(i - 2)
    }
    numbers(n)
  }

  //45 = 1134903170
  def main(args: Array[String]) {
    val in = new Scanner(System.in)
    val n = in.nextInt()
    require(n >= 0 && n <= 45)
    //val r = time { calc_fib(n) }
    //val r = fib(n)
    //val r = time { fib2(n) }
    val r = fib2(n)
    println(r)
  }

  def time[R](block: => R): R = {
    val t0 = System.currentTimeMillis()
    val result = block    // call-by-name
    val t1 = System.currentTimeMillis()
    println(s"Elapsed time: ${t1 - t0} ms")
    result
  }
}
