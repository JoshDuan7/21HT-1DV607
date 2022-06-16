package model;

import model.rules.GameRuleSetInterface;
import model.rules.HitStrategy;
import model.rules.NewGameStrategy;
import model.rules.RulesFactory;
import model.rules.TieRule;

/** Represents a dealer player that handles the deck of cards and runs the game using rules. */
public class Dealer extends Player {

  private Deck deck;
  private NewGameStrategy newGameRule;
  private HitStrategy hitRule;
  private TieRule tie;

  /**
   * Initializing constructor.
   *
   * @param rulesFactory A factory that creates the rules to use.
   */
  public Dealer(RulesFactory rulesFactory) {

    newGameRule = rulesFactory.getNewGameRule();
    hitRule = rulesFactory.getHitRule();
    tie = rulesFactory.getTieRule();
  }
  
  /**
   * Initializing constructor.
   *
   * @param rulesFactory A factory that creates the rules to use.
   */
  public Dealer(GameRuleSetInterface rulesFactory) {

    newGameRule = rulesFactory.getNewGameRule();
    hitRule = rulesFactory.getHitRule();
    tie = rulesFactory.getTieRule();
  }

  /**
   * Starts a new game if the game is not currently under way.
   *
   * @param player The player to play agains.
   * @return True if the game could be started.
   */
  public boolean newGame(Player player) {
    if (deck == null || isGameOver()) {
      deck = new Deck();
      clearHand();
      player.clearHand();
      return newGameRule.newGame(this, player);
    }
    return false;
  }

  /**
   * Gives the player one more card if possible. I.e. the player hits.
   *
   * @param player The player to give a card to.
   * @return true if the player could get a new card, false otherwise.
   */
  public boolean hit(Player player) {
    if (deck != null && player.calcScore() < maxScore && !isGameOver()) {
      cardDealt(player, true);
      return true;
    }
    return false;
  }

  /**
   * Checks if the dealer is the winner compared to a player.
   *
   * @param player The player to check agains.
   * @return True if the dealer is the winner, false if the player is the winner.
   */
  public boolean isDealerWinner(Player player) {

    if (player.calcScore() > maxScore) {
      return true;
    } else if (calcScore() > maxScore) {
      return false;
    } else if (player.calcScore() == calcScore()) {
      return tie.tieWhoWins();
    } else if (player.calcScore() > calcScore()) {
      return false;
    } else if (player.calcScore() < calcScore()) {
      return true;
    } else {
      return tie.tieWhoWins();
    }
  }

  /**
   * Checks if the game is over, i.e. the dealer can take no more cards.
   *
   * @return True if the game is over.
   */
  public boolean isGameOver() {
    return deck != null && !hitRule.doHit(this);
  }

  /** The player has choosen to take no more cards, it is the dealers turn. */
  public boolean stand() {
    showHand();
    boolean initiaive = false;
    while (hitRule.doHit(this)) {
      hitRule.doHit(this);
      initiaive = true;
      cardDealt(this, true);
      callObserver();
    }
    return initiaive;
  }

  /**
   * A workaround used to refactor the code in the strategies, shows or hides a card.
   *
   * @param player Could either be the player or the dealer.
   * @param statusCard Determines whether the card should be shown or not.
   */
  public void cardDealt(Player player, boolean statusCard) {
    Card.Mutable c = deck.getCard();
    c.show(statusCard);
    player.dealCard(c);
  }
}
