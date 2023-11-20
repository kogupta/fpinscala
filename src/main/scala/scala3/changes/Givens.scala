package scala3.changes

object Givens {
  case class Person(name: String, age: Int)

  val people = List(
    Person("Daniel", 99),
    Person("Alice", 23),
    Person("Bob", 35),
    Person("Master Yoda", 350)
  )

//  implicit val orderByAge: Ordering[Person] = (x, y) => x.age.compareTo(y.age)

//  given personByAge: Ordering[Person] with {
//    override def compare(x: Person, y: Person): Int = x.age.compareTo(y.age)
//  }

  given personByAge2: Ordering[Person] = (x, y) => x.age.compareTo(y.age)

  def withImplicit(implicit value: Int): Int = value * 10
  def withUsing(using value: Int): Int = value * 10

  given threadCount: Int = 16

  def main(args: Array[String]): Unit = {
    println("givens")

    val xs: List[Person] = people.sorted

    withImplicit
    withImplicit(10)

    withUsing
//    withUsing(10)
    withUsing(using 10)


  }
}
