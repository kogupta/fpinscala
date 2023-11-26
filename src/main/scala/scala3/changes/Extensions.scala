package scala3.changes

object Extensions {

  extension[A] (tree: Tree[A]) {
    def map[B](f: A => B): Tree[B] = tree match {
      case Leaf(a) => Leaf(f(a))
      case Branch(left, right) => Branch(left.map(f), right.map(f))
    }

    def forall(predicate: A => Boolean): Boolean = tree match {
      case Leaf(a) => predicate(a)
      case Branch(left, right) => left.forall(predicate) && right.forall(predicate)
    }

    private def show(): String = {
      def helper(acc: String, t: Tree[A]): String = t match {
        case Leaf(a) => acc + a
        case Branch(left, right) =>
          val l = helper("", left)
          val r = helper("", right)
          s"$acc[$l, $r]"
      }

      helper("", tree)
    }
  }

  extension (tree: Tree[Int]) {
    def sum(): Int = {
      def helper(acc: Int, t: Tree[Int]): Int = t match {
        case Leaf(a) => acc + a
        case Branch(left, right) => helper(acc, left) + helper(acc, right)
      }

      helper(0, tree)
    }
  }

  sealed trait Tree[A]

  case class Leaf[A](value: A) extends Tree[A]

  case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

  def main(args: Array[String]): Unit = {
    println("aaaa")

    val double: Int => Int = _ * 2
    val asString: Int => String = _.toString
    val isEven: Int => Boolean = _ % 2 == 0
    val positive: Int => Boolean = _ > 0

    {
      // test on single node
      val tree = Leaf(1)
      println(tree.show())
      println(tree.map(double).show())
    }

    {
      val tree = Branch(Leaf(1), Leaf(2))
      println(tree.show())
      println(tree.map(double).show())
    }
    {
      val tree = Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))
      println(tree.show())
      println(tree.map(double).show())
    }
    {
      val tree = Branch(Branch(Leaf(1), Branch(Leaf(2), Leaf(3))), Leaf(2))
      println(tree.show())
      println(tree.map(double).show())
    }


  }
}
