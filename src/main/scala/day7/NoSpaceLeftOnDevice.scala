package works.redcode
package day7

@main def NoSpaceLeftOnDevice(): Unit =

  println(s"""
    DAY 7: No Space Left On Device
    ========================================================
    Disk Space Remaining: ${Filesystem.tree.diskSpaceRemaining(70000000)}
    
    Part 1 Total size of directories <= 10000
    ${Filesystem.doPart1.sum}

    Part 2 Smallest file to be deleted:
    ${Filesystem.doPart2.values.min}

    ========================================================

    """
  )

  util.Random.shuffle(Filesystem.tree.collectSizes).take(10) foreach println

