import java.io.{BufferedReader, IOException, InputStream, InputStreamReader}
import java.util.StringTokenizer

object MajorityElement {

  /*
  Task. The goal in this code problem is to check whether an input sequence contains a majority element.

  Input Format. The first line contains an integer 𝑛, the next one contains a sequence of 𝑛 non-negative
  integers 𝑎_0, 𝑎_1, ..., 𝑎_𝑛−1.

  Constraints. 1 ≤ 𝑛 ≤ 10^5; 0 ≤ 𝑎_𝑖 ≤ 10^9 for all 0 ≤ 𝑖 < 𝑛.

  Output Format. Output 1 if the sequence contains an element that appears strictly more than 𝑛/2 times,
  and 0 otherwise.
   */
  def main(args: Array[String]) {
    val scanner = new FastScanner(System.in)

    val n = scanner.nextInt
    require(n <= 10e5)

    val a = Array.ofDim[Int](n)
    for (i <- 0 until n) {
      a(i) = scanner.nextInt
      require(0 <= a(i) && a(i) <= 10e9)
    }

    val me = getMajorityElement(a, 0, a.length)
    val r = if (me != -1) {
      1
    } else {
      0
    }
    println(r)
  }

  /*
      Indeed, if a sequence of length 𝑛 contains a majority element, then the same element is also
      a majority element for one of its halves. Thus, to solve this problem you first split a given sequence into
      halves and make two recursive calls. Do you see how to combine the results of two recursive calls?
   */
  private def getMajorityElement(a: Array[Int], left: Int, right: Int): Int = {
    if (left == right) {
      //"sub-array" length 0
      -1
    } else if (left + 1 == right) {
      // 'sub-array' of length 1
      a(left) // this is the majority because it's the only one
    } else {
      val half = (right - left) / 2
      val m = left + half

      val x = getMajorityElement(a, left, m)
      val y = getMajorityElement(a, m, right)

      if (x == y) {
        x
      } else {
        if (count(a, left, right, x) > half) {
          x
        } else if (count(a, left, right, y) > half) {
          y
        } else {
          -1
        }
      }
    }
  }

  // Duplicates Array.count() in Scala but without requiring us to copy array
  private def count(a: Array[Int], s: Int, e: Int, x: Int): Int = {
    var count = 0
    for (i <- s until e) {
      if (a(i) == x)
        count += 1
    }
    count
  }

  private def getMajorityElementNaive(a: Array[Int]): Int = {
    val n = a.length
    for (i <- 0 until n) {
      val currentElement = a(i)
      var count = 0
      for (j <- 0 until n) {
        if (a(j) == currentElement)
          count += 1
      }
      if (count > n / 2)
        return a(i)
    }
    -1
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
