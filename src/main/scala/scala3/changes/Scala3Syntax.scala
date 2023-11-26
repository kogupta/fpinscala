package scala3.changes

object Scala3Syntax {
  def main(args: Array[String]): Unit = {
    println("aaaa")

  }

  def ifChanges(): Unit = {
    val _ = if (2 > 3) "bigger" else "smaller"
    val ifThenElse = if 2 > 3 then "bigger" else "smaller"


    val ifScala2 = if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

    // indentation is significant!
    val ifScala3 =
      if 2 > 3 then
        "bigger"
      else
        "smaller"
  }

  def forComprehensionIsNotSpared(): Unit = {
    val scala2 = for {
      n <- List(1, 2, 3)
      c <- List('a', 'b')
    } yield s"$n - $c"

    val scala3 =
      for
        n <- List(1, 2, 3)
        c <- List('a', 'b')
      yield s"$n - $c"

    assert(scala2 == scala3)
  }

  def patternMatchingToo(): Unit = {
    val xs = List(1, 2, 3)
    val s = xs match {
      case Nil => "empty List"
      case x :: Nil => s"only element: $x"
      case head::tail => "ooh - pretty big list!"
    }

    val scala3Style = xs match
      case Nil => "empty List"
      case x :: Nil => s"only element: $x"
      case head::tail => "ooh - pretty big list!"

    assert(s == scala3Style)
  }

  def tryCatchToo(s: String): Char = {
    try
      s.charAt(10)
    catch
      case oobe: IndexOutOfBoundsException =>
        '_'
      case _: Exception =>
        'z'
  }



  // val , lazy val

  private val x: Int = 10 // private final int x = 10;

  private lazy val y = 10; //

  private def z(): Int = 10  //


}
