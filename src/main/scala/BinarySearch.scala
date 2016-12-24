import java.io.{BufferedReader, IOException, InputStream, InputStreamReader}
import java.util.StringTokenizer

import scala.annotation.tailrec

object BinarySearch {

  def binarySearch(a: Array[Int], x: Int): Int = {
    bs(a, 0, a.length - 1, x)
  }

  // Returns index of first occurance of x
  def linearSearch(a: Array[Int], x: Int): Int = {
    for (i <- a.indices) {
      if (a(i) == x) return i
    }
    -1
  }

  /*
  Input Format.
  - The first line of the input contains an integer 𝑛 and a sequence 𝑎_0 < 𝑎_1 < ... < 𝑎_𝑛−1 of
  𝑛 pairwise distinct positive integers in increasing order.
  - The next line contains an integer 𝑘 and 𝑘 positive integers 𝑏_0, 𝑏_1, ... , 𝑏_𝑘−1.

  Constraints. 1 ≤ 𝑛, 𝑘 ≤ 10^5; 1 ≤ 𝑎𝑖 ≤ 10^9
  for all 0 ≤ 𝑖 < 𝑛; 1 ≤ 𝑏_𝑗 ≤ 10^9
  for all 0 ≤ 𝑗 < 𝑘;

  Output Format. For all 𝑖 from 0 to 𝑘 − 1, output an index 0 ≤ 𝑗 ≤ 𝑛 − 1 such that 𝑎𝑗 = 𝑏𝑖 or −1 if there
  is no such index.
   */
  def main(args: Array[String]) {
    val scanner = new FastScanner(System.in)
    val n = scanner.nextInt
    val a = Array.ofDim[Int](n)
    for (i <- 0 until n) {
      a(i) = scanner.nextInt
      require(a(i) <= 10e9)
    }
    //Sorting.quickSort(a)
    val m = scanner.nextInt
    val b = Array.ofDim[Int](m)
    for (i <- 0 until m) {
      b(i) = scanner.nextInt
      require(b(i) <= 10e9)
    }
    for (i <- 0 until m) {
      print(s"${binarySearch(a, b(i))} ")
    }
  }

  @tailrec
  private def bs(a: Array[Int], low: Int, high: Int, key: Int): Int = {
    if (high < low)
      return -1

    val mid = Math.floor(low + ((high - low) / 2)).toInt
    if (key == a(mid)) {
      mid
    } else if (key < a(mid)) {
      bs(a, low, mid - 1, key)
    } else {
      bs(a, mid + 1, high, key)
    }
  }

  class FastScanner(val stream: InputStream) {
    val br = new BufferedReader(new InputStreamReader(stream))
    var st: StringTokenizer = _

    def nextInt: Int = next.toInt

    def next: String = {
      while (st == null || !st.hasMoreTokens) try
        st = new StringTokenizer(br.readLine)
      catch {
        case e: IOException =>
          e.printStackTrace()
      }
      st.nextToken
    }
  }

}
