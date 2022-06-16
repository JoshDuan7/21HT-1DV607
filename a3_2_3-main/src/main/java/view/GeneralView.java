package view;

/**
 * Create an abstract class to remove duplicated code for UI and improving the system's flexibility
 * for multi-language support.
 */
public abstract class GeneralView implements View {
  private Option chosen;
  private String welcomeText;
  private String instructionsText;
  private String prepositionText;
  private String playerText;
  private String dealerText;
  private String hasText;
  private String scoreText;
  private String gameOverText;
  private String dealerWinText;
  private String userWinText;

  /** Shows a welcome message. */
  public void displayWelcomeMessage() {

    System.out.println(welcomeText);
    System.out.println("----------------------");
    System.out.println(instructionsText);
  }

  /** Prints empty lines. */
  public void emptySpace() {
    for (int i = 0; i < 50; i++) {
      System.out.print("\n");
    }
  }

  /**
   * Returns pressed characters from the keyboard.
   *
   * @return the pressed character.
   */
  public Option getInput() {
    try {
      int c = System.in.read();
      while (c == '\r' || c == '\n') {
        c = System.in.read();
      }
      switch (c) {
        case('p'):
          chosen = Option.PLAY;
          break;
        case('h'):
          chosen = Option.HIT;
          break;
        case('s'):
          chosen = Option.STAND;
          break;
        case('q'):
          chosen = Option.QUIT;
          break;
        default:
          chosen = Option.None;
      }
      return chosen;
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return chosen;
    }
  }

  public void displayCard(model.Card card) {
    System.out.println("" + card.getValue() + prepositionText + card.getColor());
  }

  public void displayPlayerHand(Iterable<model.Card> hand, int score) {
    displayHand(playerText, hand, score);
  }

  public void displayDealerHand(Iterable<model.Card> hand, int score) {
    displayHand(dealerText, hand, score);
  }

  private void displayHand(String name, Iterable<model.Card> hand, int score) {
    System.out.println(name + hasText);
    for (model.Card c : hand) {
      displayCard(c);
    }
    System.out.println(scoreText + score);
    System.out.println("");
  }

  /**
   * Displays the winner of the game.
   *
   * @param dealerIsWinner True if the dealer is the winner.
   */
  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println(gameOverText);
    if (dealerIsWinner) {
      System.out.println(dealerWinText);
    } else {
      System.out.println(userWinText);
    }
  }

  public void setWelcomeText(String welcomeText) {
    this.welcomeText = welcomeText;
  }

  public void setInstructionsText(String instructionsText) {
    this.instructionsText = instructionsText;
  }

  public void setPrepositionText(String prepositionText) {
    this.prepositionText = prepositionText;
  }

  public void setPlayerText(String playerText) {
    this.playerText = playerText;
  }

  public void setDealerText(String dealerText) {
    this.dealerText = dealerText;
  }

  public void setHasText(String hasText) {
    this.hasText = hasText;
  }

  public void setScoreText(String scoreText) {
    this.scoreText = scoreText;
  }

  public void setGameOverText(String gameOverText) {
    this.gameOverText = gameOverText;
  }

  public void setDealerWinText(String dealerWinText) {
    this.dealerWinText = dealerWinText;
  }

  public void setUserWinText(String userWinText) {
    this.userWinText = userWinText;
  }

}
