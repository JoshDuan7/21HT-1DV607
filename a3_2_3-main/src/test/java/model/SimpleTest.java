package model;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import model.Card.Color;
import model.Card.Value;
import model.rules.HitStrategy;
import model.rules.PlayerAdvantageRuleFactory;
import model.rules.RulesFactory;
import model.rules.AbstractFactoryRuleSetInterface;
import model.rules.GameRuleSetInterface;


public class SimpleTest {
  private Dealer dealer;
  private Player player;
  private Game game;
  private HitStrategy hitRule;
  private RulesFactory rulesFactory;

  /**
   * Test should give true if the player won
   */
  @Test
  public void testWinOnTie() {
    List<Card.Mutable> c = new ArrayList<Card.Mutable>();
    List<Card.Mutable> p = new ArrayList<Card.Mutable>();
    dealer = new Dealer(new model.rules.RulesFactory());
    rulesFactory = new RulesFactory();
    hitRule = rulesFactory.getHitRule();
    player = new Player();
    

    c.add(new model.Card.Mutable.Mutable(Color.Diamonds, Value.Ten));
    c.add(new model.Card.Mutable.Mutable(Color.Hearts, Value.Ten));

    p.add(new model.Card.Mutable.Mutable(Color.Spades, Value.Ten));
    p.add(new model.Card.Mutable.Mutable(Color.Clubs, Value.Ten));

    for (Card.Mutable a : c) {
      boolean test = true;
      while (test) {
        test = false;
        a.show(true);
        dealer.dealCard(a);
      }

    }

    for (Card.Mutable card : p) {
      boolean test = true;
      while (test) {
        test = false;
        card.show(true);
        player.dealCard(card);
      }
    }
    int valueP = player.calcScore();
    int valueD = dealer.calcScore();

    // both Player and Dealer have the same score

    assertFalse(dealer.isDealerWinner(player), "values are " + valueP + " and " + valueD);

    

  }

  /** 
   * Method used to test how would the dealer react if their hand had exactly an ace and a six
  */
  @Test 
  public void testSoft17() {
    List<Card.Mutable> c = new ArrayList<Card.Mutable>();
    dealer = new Dealer(new model.rules.RulesFactory());
    rulesFactory = new RulesFactory();
    hitRule = rulesFactory.getHitRule();
    player = new Player();
    dealer.newGame(player);
    
    
    c.add(new model.Card.Mutable.Mutable(Color.Diamonds, Value.Ace));
    c.add(new model.Card.Mutable.Mutable(Color.Hearts, Value.Six));
   
    for (Card.Mutable a: c) {
      boolean test = true;
      while (test) {
        test = false;
        a.show(true);
        dealer.dealCard(a);
      }
      
    }

    int value = dealer.calcScore();

    dealer.stand();

    
    value = dealer.calcScore();
    assertTrue(value != 16, "value should not be 16 instead is " + value);
  
    
    
  }
  
  @Test
  public void testWinOnTieFactory() {
    List<Card.Mutable> c = new ArrayList<Card.Mutable>();
    List<Card.Mutable> p = new ArrayList<Card.Mutable>();
    AbstractFactoryRuleSetInterface  rulesFactory = new PlayerAdvantageRuleFactory();
    GameRuleSetInterface rules = rulesFactory.createRuleSet();
    dealer = new Dealer(rules);
    player = new Player();

    c.add(new model.Card.Mutable.Mutable(Color.Diamonds, Value.Ten));
    c.add(new model.Card.Mutable.Mutable(Color.Hearts, Value.Ten));

    p.add(new model.Card.Mutable.Mutable(Color.Spades, Value.Ten));
    p.add(new model.Card.Mutable.Mutable(Color.Clubs, Value.Ten));

    for (Card.Mutable a : c) {
      boolean test = true;
      while (test) {
        test = false;
        a.show(true);
        dealer.dealCard(a);
      }

    }

    for (Card.Mutable card : p) {
      boolean test = true;
      while (test) {
        test = false;
        card.show(true);
        player.dealCard(card);
      }
    }
    int valueP = player.calcScore();
    int valueD = dealer.calcScore();

    // both Player and Dealer have the same score

    assertFalse(dealer.isDealerWinner(player), "values are " + valueP + " and " + valueD);

  }
  
}
