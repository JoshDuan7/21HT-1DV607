package model.cardobserver;

import java.util.ArrayList;
/** Abstract class in order to be subclassed so that the Observer pattern can be implemented. */

public abstract class Observer {
  private ArrayList<InCardObserver> cardObservers = new ArrayList<>();

  /** Method used to essentially call the observer.*/

  public void callObserver() {
    for (InCardObserver cardObserver : cardObservers) {
      cardObserver.notifyObserver();
    }
  }
  /** Method used to initialize the observer.*/

  public void initObserver(InCardObserver cardObserver) {
    cardObservers.add(cardObserver);
  }
}
