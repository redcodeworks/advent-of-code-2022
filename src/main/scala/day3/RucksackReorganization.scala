package works.redcode
package day3

@main def RucksackReorganization(): Unit =

  println(s"""
    DAY 3: Rucksack Reorganization
    ========================================================
    Part 1 total score is:
    ${SackHandler.priortyByCompartmentCommonItems.values.sum}

    Part 2:
    ${SackHandler.priorityByTeam.values.sum}
    ========================================================
    """
  )
