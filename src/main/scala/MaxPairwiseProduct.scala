import java.io._
import java.util._

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

object MaxPairwiseProduct {
  def main(args: Array[String]) {
    val scanner = new FastScanner(System.in)
    val n = scanner.nextInt
    val numbers = new Array[Int](n)
    for (i <- 0 until n) {
      numbers(i) = scanner.nextInt
    }
    println(getMaxPairwiseProductFast(numbers))
  }

  def getMaxPairwiseProduct(numbers: Array[Int]): Long = {
    var result: Long = 0
    val n: Int = numbers.length
    for (i <- 0 until n) {
      for (j <- (i + 1) until n) {
        val x = numbers(i).toLong * numbers(j)
        if (x > result) {
          result = x
        }
      }
    }
    result
  }

  def getMaxPairwiseProductFast(numbers: Array[Int]): Long = {
    // if x or y is 0, then replace that
    // if value is higher than one or both x and y replace value with greatest difference
    var x = 0
    var y = 0
    for (z <- numbers) {
      if (z > y || z > x)
        if (z - x > z - y) x = z else y = z
    }
    x.toLong * y
  }
}