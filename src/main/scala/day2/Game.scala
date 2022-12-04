package works.redcode
package day2

object Game:

  enum Pick:
    case Rock, Paper, Scissors

  enum Outcome:
    case Win, Loss, Draw

  def decodeInput(token: String): Pick = token.toUpperCase match
    case "A" | "X" => Pick.Rock
    case "B"| "Y" => Pick.Paper
    case "C" | "Z" => Pick.Scissors

  def pickScore(p: Pick): Int = p match
    case Pick.Rock => 1
    case Pick.Paper => 2
    case Pick.Scissors => 3

  def outcomeScore(o: Outcome): Int = o match
    case Outcome.Win => 6
    case Outcome.Draw => 3
    case Outcome.Loss => 0

  def doRoShamBeau(myPick: Pick, theirPick: Pick): Outcome = (myPick, theirPick) match
    case (Pick.Rock, Pick.Scissors) => Outcome.Win
    case (Pick.Rock, Pick.Paper) => Outcome.Loss
    case (Pick.Scissors, Pick.Paper) => Outcome.Win
    case (Pick.Scissors, Pick.Rock) => Outcome.Loss
    case (Pick.Paper, Pick.Rock) => Outcome.Win
    case (Pick.Paper, Pick.Scissors) => Outcome.Loss
    case _ => Outcome.Draw

  def computeScore(myPick: Pick, theirPick: Pick): Int =
    pickScore(myPick) + outcomeScore(doRoShamBeau(myPick, theirPick))

  type StrategyGuide = List[(Pick, Pick)]

  def parseStrategyGuide(input: List[String]): StrategyGuide =
    input.map(
    _.split(" ")
    .toList
    .map(decodeInput)
    ).map(xs => (xs.head, xs.tail.head)
  )

  lazy val strategyGuide = util.Using(io.Source.fromResource("day2/input-data.txt"))
    { stream => parseStrategyGuide(stream.getLines.toList)}.get

  lazy val playRounds = strategyGuide.map((them, me) => (me, doRoShamBeau(me, them)))
  lazy val calcScore = playRounds.map((myPick, myOutcome) => pickScore(myPick) + outcomeScore(myOutcome)).sum