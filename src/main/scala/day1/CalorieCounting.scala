package works.redcode
package day1

@main def CalorieCounting(): Unit =

  println(s"""
    DAY 1: Calorie Counting
    ========================================================
    The Snackiest Elf is:
    ${Expedition.snackiest.head}

    The kcals of the top 3 elves totals to:
    ${Expedition.totalSnackiness(3)} kcals
    ========================================================

    Random Sample of Elves:

    ${util.Random.shuffle(Expedition.elves).take(10).mkString("\n\t")}
    """
  )