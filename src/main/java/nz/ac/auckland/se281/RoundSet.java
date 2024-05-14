package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

public class RoundSet implements GameForm {
 
  private int roundNumber;
  private int numberOfEvensPlayed;
  private int numberOfOddsPlayed;
  private String playerName;

  private Choice playerChoice;
  private Difficulty botDifficulty;

  public RoundSet(Choice choice, Difficulty difficulty, String playerName) {
    this.playerChoice = choice;
    this.botDifficulty = difficulty;
    this.playerName = playerName;

    this.roundNumber = 0;
    this.numberOfEvensPlayed = 0;
    this.numberOfOddsPlayed = 0;
  }

  public void playRound() {

    MessageCli.ASK_INPUT.printMessage();

    boolean validNumberGiven = false;
    String input = null;

    while (!validNumberGiven) {

      input = Utils.scanner.nextLine();

      int inputInt = -1;

      try {
        inputInt = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        MessageCli.INVALID_INPUT.printMessage();
        validNumberGiven = false;
      }

      if (!Utils.isInteger(input)) {
        MessageCli.INVALID_INPUT.printMessage();
        validNumberGiven = false;
      } if (inputInt > 5 | inputInt < 0) {
        MessageCli.INVALID_INPUT.printMessage();
        validNumberGiven = false;
      }

      if (inputInt <= 5 & inputInt >= 0) {
        validNumberGiven = true;
      }
    }
    // If you have made it here then you have a valid human finger amount as a string in variable input
    //and as an int in inputInt

    // PRINT_INFO_HAND("Player %s: fingers: %s")
    MessageCli.PRINT_INFO_HAND.printMessage(this.playerName, input);

    Bot computer = BotFactory.createBot(this.botDifficulty);

    String botFingers = computer.generateFingers();

    // PRINT_INFO_HAND("Player %s: fingers: %s")
    MessageCli.PRINT_INFO_HAND.printMessage("HAL-9000", botFingers);

  }

  public void incrementRoundNumber() {
    this.roundNumber ++;
  }

  public void setPlayerName(String name) {
    this.playerName = name;
  }

  public String getPlayername() {
    return this.playerName;
  }

  public int getRoundNumber() {
    return this.roundNumber;
  }

  public int getNumberOfEvensPlayes() {
    return this.numberOfEvensPlayed;
  }

  public int getNumberOfOddsPlayes() {
    return this.numberOfOddsPlayed;
  }


}