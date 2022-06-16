package controller;

import model.Game;
import model.rules.AbstractFactoryRuleSetInterface;
import model.rules.GameRuleSetInterface;
import model.rules.PlayerAdvantageRuleFactory;
import view.EnglishView;
import view.View;

/** Starts the application using the console. */
public class App {

  /**
   * The main method of the application, simply replace EnglishView() with SwedishView()
   * if you want to play in the Swedish language instead. Also you can change the rules applied
   * in the game easily by new DealerAdvantageRuleFactory(), or new DefaultGameRuleFactory() for
   * rulesFactory.
   */
  public static void main(String[] args) {
    
    AbstractFactoryRuleSetInterface rulesFactory;
    GameRuleSetInterface rules;

    rulesFactory = new PlayerAdvantageRuleFactory(); //DealerAdvantageRuleFactory, DefaultGameRuleFactory
    rules = rulesFactory.createRuleSet();

    Game g = new Game();
    if (rules != null) {
      g = new Game(rules);
    }
    
    View v = new EnglishView(); // new SwedishView();
    Player ctrl = new Player(g, v);

    while (ctrl.play()) {}
  }
}
