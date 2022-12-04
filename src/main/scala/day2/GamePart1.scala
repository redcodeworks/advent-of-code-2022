package works.redcode
package day2

class GamePart1(resourceName: String):

  def decodePick(token: String): Pick = token.toUpperCase match
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

  def doRoShamBeaux(myPick: Pick, theirPick: Pick): Outcome = (myPick, theirPick) match
    case (Pick.Rock, Pick.Scissors) => Outcome.Win
    case (Pick.Rock, Pick.Paper) => Outcome.Loss
    case (Pick.Scissors, Pick.Paper) => Outcome.Win
    case (Pick.Scissors, Pick.Rock) => Outcome.Loss
    case (Pick.Paper, Pick.Rock) => Outcome.Win
    case (Pick.Paper, Pick.Scissors) => Outcome.Loss
    case _ => Outcome.Draw

  def scoreRound(myPick: Pick, theirPick: Pick): Int =
    pickScore(myPick) + outcomeScore(doRoShamBeaux(myPick, theirPick))


  private type StrategyGuide = List[(Pick, Pick)]

  private def parseStrategyGuide(input: List[String]): StrategyGuide =
    input.map(
    _.split(" ")
    .toList
    .map(decodePick)
    ).map(xs => (xs.tail.head, xs.head)
  )

  private lazy val strategyGuide = util.Using(io.Source.fromResource(resourceName))
    { stream => parseStrategyGuide(stream.getLines.toList)}.get

  lazy val playRounds: List[(Pick, Outcome)] = strategyGuide.map((me, them) => (me, doRoShamBeaux(me, them)))
  lazy val calcScore = playRounds.map((myPick, myOutcome) => pickScore(myPick) + outcomeScore(myOutcome)).sum

  def getStrategyGuideP1 = strategyGuide

object GamePart1:
  def apply(resourceName: String) = new GamePart1(resourceName)