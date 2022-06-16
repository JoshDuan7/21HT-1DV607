package model.search;

import java.util.ArrayList;
import java.util.List;
import model.domain.Member;
import wniemiec.data.java.Pair;

/**
 * This class is "encapsulate" the search type of AND.
 */

public class AndOnlySearchBracket extends SearchBracket {

  private final List<Pair<Operator, MemberSearch>> searchConditions;

  /**
   * Constructor ---> Setting up the arraylist for the search conditions.
   */
  public AndOnlySearchBracket() {
    searchConditions = new ArrayList<Pair<Operator, MemberSearch>>();
  }

  /**
   * Constructor that continues the logic bridge of the search conditions.
   */
  public AndOnlySearchBracket(Pair<Operator, MemberSearch> searchConditionLeft, Pair<Operator,
          MemberSearch> searchConditionRight) {
    searchConditions = new ArrayList<Pair<Operator, MemberSearch>>();
    searchConditions.add(new Pair<>(Operator.None, searchConditionLeft.getSecond()));
    searchConditions.add(searchConditionRight);
  }

  /**
   * Method that adds extra rules to the searcher.
   */
  public void add(MemberSearch memberSearch) {
    if (searchConditions.size() == 0) {
      searchConditions.add(new Pair<>(Operator.None, memberSearch));
    } else {
      searchConditions.add(new Pair<>(Operator.And, memberSearch));
    }

  }

  @Override
  public List<Member> search(List<Member> members) {
    List<Member> foundMembers = new ArrayList<>();
    for (Pair<Operator, MemberSearch> searchCondition : searchConditions) {
      if (searchCondition.getFirst() == Operator.And) {
        int leftIdx = findConditionIndex(searchCondition.getSecond(), searchConditions) - 1;
        List<Member> leftSide = searchConditions.get(leftIdx).getSecond().search(members);
        List<Member> rightSide = searchCondition.getSecond().search(members);

        if (foundMembers.size() == 0) {
          for (Member member : leftSide) {
            if (rightSide.contains(member)) {
              foundMembers.add(member);
            }
          }
        } else {
          for (Member member : rightSide) {
            if (! foundMembers.contains(member)) {
              foundMembers.remove(member);
            }
          }
        }

      }
    }

    return foundMembers;
  }
}
