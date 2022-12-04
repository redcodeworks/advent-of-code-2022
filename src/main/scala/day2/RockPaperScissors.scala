package works.redcode
package day2

@main def RockPaperScissors(): Unit =

  val rps1 = GamePart1("day2/input-data.txt")
  val rps2 = GamePart2("day2/input-data.txt")

  println(s"""
    ========================================================
    Part 1 total score is:
    ${rps1.calcScore}

    Part 2 total score is:
    ${rps2.calcScore}
    ========================================================
    """
  )