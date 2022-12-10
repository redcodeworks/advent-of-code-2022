package works.redcode
package day5

@main def SupplyStacks(): Unit =

  println(s"""
    DAY 5: Camp Supply Stacks
    ========================================================
    Part 1:
    ${StackHandler.doPart1}


    Part 2:
    ${StackHandler.doPart2}

    ========================================================

    Final Stacks For Part 1:
    ${StackHandler.finishedStacksPart1.mkString("\n\t")}

    Final Stacks For Part 2:
    ${StackHandler.finishedStacksPart2.mkString("\n\t")}

    """
  )

