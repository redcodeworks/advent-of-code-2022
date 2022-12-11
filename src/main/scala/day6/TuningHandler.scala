package works.redcode
package day6

import scala.annotation.tailrec

object TuningHandler:
  val encrypted = io.Source.fromResource("day6/input-data.txt").toList


  def buildSet(encrypted: List[Char], setSize: Int): (Int, String) =
    @tailrec
    def recur(xs: List[Char], currentSet: List[Char], i: Int = setSize): (Int, String) = currentSet match
      case Nil => (i, "")
      case head :: tail if currentSet.toSet.size == setSize => (i, currentSet.mkString)
      case head :: tail => recur(xs.drop(1), tail :+ xs.head, i + 1)

    recur(encrypted.drop(setSize), encrypted.take(setSize).toList)


  def decrypted(setSize: Int): (Int, String) = buildSet(encrypted, setSize)