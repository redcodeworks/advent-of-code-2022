package works.redcode
package day2


class GamePart2(resourceName: String) extends GamePart1(resourceName):
  override def decodePick(token: String): Pick = token.toUpperCase match
      case "A" => Pick.Rock
      case "B" => Pick.Paper
      case "C" => Pick.Scissors

  def decodeOutcome(token: String): Outcome = token.toUpperCase match
    case "X" => Outcome.Loss
    case "Y" => Outcome.Draw
    case "Z" => Outcome.Win

  def traceOutcomeToPick(myOutcome: Outcome, theirPick: Pick): Pick = (myOutcome, theirPick) match
    case (Outcome.Draw, _) => theirPick
    case (Outcome.Win, Pick.Rock) => Pick.Paper
    case (Outcome.Win, Pick.Paper) => Pick.Scissors
    case (Outcome.Win, Pick.Scissors) => Pick.Rock
    case (Outcome.Loss, Pick.Rock) => Pick.Scissors
    case (Outcome.Loss, Pick.Paper) => Pick.Rock
    case (Outcome.Loss, Pick.Scissors) => Pick.Paper


  private type StrategyGuide = List[(Outcome, Pick)]

  private def parseStrategyGuide(input: List[String]): StrategyGuide =
    input.map(
      _.split(" ")
        .toList
    ).map(xs => (decodeOutcome(xs.tail.head), decodePick(xs.head))
    )

  private lazy val strategyGuide = util.Using(io.Source.fromResource(resourceName)) { stream => parseStrategyGuide(stream.getLines.toList) }.get
  override lazy val playRounds: List[(Pick, Outcome)] = strategyGuide.map((myOutcome, theirPick) => (traceOutcomeToPick(myOutcome, theirPick), myOutcome))
  override lazy val calcScore = playRounds.map((myPick, myOutcome) => pickScore(myPick) + outcomeScore(myOutcome)).sum

  def getStrategyGuideP2 = strategyGuide

object GamePart2:
  def apply(resourceName: String) = new GamePart2(resourceName)