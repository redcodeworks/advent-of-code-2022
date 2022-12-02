package works.redcode
package day1

@main def CalorieCounting(): Unit =
//  Expedition.elves.take(10).foreach(println)

  println(s"""
    ========================================================
    The Snackiest Elf is:
    ${Expedition.snackiest.head}

    The kcals of the top 3 elves totals to:
    ${Expedition.totalSnackiness(3)} kcals
    ========================================================
    """
  )