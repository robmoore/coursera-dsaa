import java.util.Scanner

/*
  Task.
    The goal of this problem is to implement the algorithm for computing the edit distance between two strings.
  Input Format.
    Each of the two lines of the input contains a string consisting of lower case latin letters.
  Constraints.
    The length of both strings is at least 1 and at most 100.
  Output Format.
    Output the edit distance between the given two strings.
 */
object EditDistance extends App {
  val scan = new Scanner(System.in)
  val s = scan.next()
  val t = scan.next()
  require(s.length <= 100)

  def editDistance(s: String, t: String): Int = {
    val n = s.length
    val m = t.length
    val d = Array.ofDim[Int](n + 1, m + 1)
    for (i <- 1 to n)
      d(i)(0) = i
    for (j <- 1 to m)
      d(0)(j) = j

    // build map of edit distances
    for {
      j <- 1 to m
      i <- 1 to n
    } {
      val insertion = d(i)(j - 1)
      val deletion = d(i - 1)(j)
      val matching = d(i - 1)(j - 1)

      d(i)(j) = if (s(i - 1) == t(j - 1))
        matching
      else
        Array(insertion, deletion, matching).min + 1
    }

    d(n)(m)
  }
  require(t.length <= 100)
  println(editDistance(s, t))
}
