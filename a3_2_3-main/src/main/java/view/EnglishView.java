package view;

import model.rules.GameRuleSetInterface;
import model.visitor.PrintEnglishRuleVisitor;

/** Implements an english console view. */
public class EnglishView extends GeneralView {
  /**
   * Constructor for creating English UI.
   */
  public EnglishView() {
    setWelcomeText("Hello Black Jack World");
    setInstructionsText("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
    setPrepositionText(" of ");
    setPlayerText("Player");
    setDealerText("Dealer");
    setHasText("Has: ");
    setScoreText("Score: ");
    setGameOverText("GameOver: ");
    setDealerWinText("Dealer Won!");
    setUserWinText("You Won!");
  }

  /**
   * Displays the rules of the game.
   *
   * @param rules Print all the rules used in this game.
   */
  @Override
  public void showRules(GameRuleSetInterface rules) {
    System.out.println("The rules applied in this game is as following: ");
    rules.showGameRules(new PrintEnglishRuleVisitor());
  }
}
