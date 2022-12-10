package works.redcode
package day5

import scala.annotation.tailrec
import scala.util.matching.Regex

object StackHandler:
  import scala.util.matching.Regex

  private lazy val txt = util.Using(io.Source.fromResource("day5/input-data.txt")) {_.getLines.toList}.get

  type Stack = List[Char]

  case class Instruction(qty: Int, fromLoc: Int, toLoc: Int):
    def deinc: Instruction = Instruction(qty - 1, fromLoc, toLoc)

    def collect(stacks: List[Stack], f: (Instruction, List[Stack]) => (Stack, Stack)): List[Stack] =
      val (fromStack, toStack) = f(this, stacks)

      for i <- (1 to stacks.size).toList
        yield (
          if i == fromLoc then fromStack
          else if i == toLoc then toStack
          else stacks(i - 1)
          )

//  TODO: Refactor crateMover functions to remove boilerplate code
  private def crateMover9000(in: Instruction, stacks: List[Stack]): (Stack, Stack) =
    @tailrec
    def recur(i: Int, rmStack: Stack, addStack: Stack): (Stack, Stack) = (i, rmStack, addStack) match
      case (0, _, _) => (rmStack, addStack)
      case (_, rmFrom, addTo) =>
        recur(i - 1, rmFrom.tail, rmFrom.head :: addTo)

    recur(in.qty, stacks(in.fromLoc - 1), stacks(in.toLoc - 1))


  private def crateMover9001(in: Instruction, stacks: List[Stack]): (Stack, Stack) =
    val moveThese = stacks(in.fromLoc - 1).take(in.qty)

    (stacks(in.fromLoc - 1).drop(in.qty), moveThese ++ stacks(in.toLoc - 1))


  @tailrec
  private def doCrateMover9000(insts: List[Instruction], stacks: List[Stack]): List[Stack] = insts match
    case Nil => stacks
    case head :: tail => doCrateMover9000(tail, head.collect(stacks, crateMover9000))

  @tailrec
  private def doCrateMover9001(insts: List[Instruction], stacks: List[Stack]): List[Stack] = insts match
    case Nil => stacks
    case head :: tail => doCrateMover9001(tail, head.collect(stacks, crateMover9001))


  lazy val instructions: List[Instruction] = txt.drop(txt.indexOf("") + 1)
    .map(Regex("([0-9]+)").findAllIn)
    .map(xs =>
      Instruction(xs.next.toInt, xs.next.toInt, xs.next.toInt)
    )


  val stacks: List[Stack] = txt.take(txt.indexOf("") - 1)
    .map(
      ls => ls.padTo(35, ' ').grouped(4).toList
    )
    .transpose
    .map(_.flatMap(Regex("(?<=\\[)(.*?)(?=\\])").findFirstIn)
      .map(_.charAt(0)
      )
    )

  lazy val finishedStacksPart1: List[Stack] = doCrateMover9000(instructions, stacks)
  def doPart1: String = finishedStacksPart1.map(_.head).mkString

  lazy val finishedStacksPart2: List[Stack] = doCrateMover9001(instructions, stacks)
  def doPart2: String = finishedStacksPart2.map(_.head).mkString