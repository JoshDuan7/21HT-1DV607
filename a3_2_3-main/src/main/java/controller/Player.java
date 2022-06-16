package controller;

import model.Game;
import model.cardobserver.InCardObserver;
import view.Option;
import view.View;

/** Scenario controller for playing the game. */

public class Player implements InCardObserver {
  private Game game;
  private View view;

  /** Scenario controller for playing the game. */

  public Player(Game newGame, View newView) {
    this.game = newGame;
    this.view = newView;
    this.game.newCardObserver(this);
  }

  /**
   * Runs the play use case.
   *
   * @return True as long as the game should continue.
   */
  public boolean play() {
    view.emptySpace();
    view.showRules(game.getRules());
    view.displayWelcomeMessage();
    view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
    view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());

    if (game.isGameOver()) {
      view.displayGameOver(game.isDealerWinner());
    }

    Option playerChoice = view.getInput();

    switch (playerChoice) {
      case PLAY:
        game.newGame();
        break;
      case HIT:
        game.hit();
        break;
      case STAND:
        game.stand();
        break;
      case QUIT:
        return false;
      default:
    }
    return true;
  }

  /** The interface method in order to display a message that the card is being dealt
   * along with a delay. */

  @Override
  public void notifyObserver() {
    System.out.println("* dealing card");
    try {
      Thread.sleep(2000);
      view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
      view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
