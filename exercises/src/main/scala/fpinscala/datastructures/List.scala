package fpinscala.datastructures

sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List { // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int = ints match { // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x,xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x,y) => x + y)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar

  /* 3.2 */
  def tail[A](l: List[A]): List[A] = ???

  /* 3.3 */
  def setHead[A](l: List[A], h: A): List[A] = ???

  /* 3.4 */
  def drop[A](l: List[A], n: Int): List[A] = ???

  /* 3.5 */
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = ???

  /* 3.6 */
  def init[A](l: List[A]): List[A] = ???

  /* 3.7 */
  def productUsingFoldRight(ns: List[Double]) : Double = ???

  /* 3.9 */
  def length[A](l: List[A]): Int = ???

  /* 3.10 */
  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = ???

  /* 3.11 */
  def sum3(l: List[Int]) = ???
  def product3(l: List[Double]) = ???
  def length2[A](l: List[A]): Int = ???

  /* 3.12 */
  def reverse[A](l: List[A]): List[A] =  ???

  /* 3.13 */
  def foldRightViaFoldLeft[A,B](l: List[A], z: B)(f: (A,B) => B): B = ???

  def foldLeftViaFoldRight[A,B](l: List[A], z: B)(f: (B,A) => B): B = ???

  /* 3.14 */
  def appendViaFold[A](l: List[A], r: List[A]): List[A] = ???

  /* 3.15 */
  def concat[A](l: List[List[A]]): List[A] = ???

  /* 3.16 */
  def add1(l: List[Int]): List[Int] = ???

  /* 3.17 */
  def doubleToString(l: List[Double]): List[String] = ???

  /* 3.18 */
  def map[A,B](l: List[A])(f: A => B): List[B] = ???

  /* 3.19 */
  def filter[A](l: List[A])(f: A => Boolean): List[A] = ???

  /* 3.20 */
  def flatMap[A,B](l: List[A])(f: A => List[B]): List[B] = ???

  /* 3.21 */
  def filterViaFlatMap[A](l: List[A])(f: A => Boolean): List[A] = ???

  /* 3.22 */
  def addPairwise(a: List[Int], b: List[Int]): List[Int] = ???

  /* 3.23 */
  def zipWith[A,B,C](a: List[A], b: List[B])(f: (A,B) => C): List[C] = ???

  /* 3.24 */
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = ???
}

object ListDemo extends App {
  import List.{sum, foldRight}

  /* 3.1 */
  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y case Cons(h, t) => h + sum(t)
    case _ => 101
  }
  println(x)

  /* 3.8 */
  val y = foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_))
  println(y)
  
}
