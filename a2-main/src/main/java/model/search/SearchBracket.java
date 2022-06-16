package model.search;

import java.util.List;
import wniemiec.data.java.Pair;

/**
 * A class that looks for what is the current condition that is being used.
 */

public abstract class SearchBracket implements MemberSearch {

  protected int findConditionIndex(MemberSearch memberSearch, List<Pair<Operator, MemberSearch>> searchConditions) {
    for (int i = 0; i < searchConditions.size(); i++) {
      if (searchConditions.get(i).getSecond() == memberSearch) {
        return i;
      }
    }
    return - 99;
  }

}
