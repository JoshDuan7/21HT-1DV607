package model.search;

import java.util.ArrayList;
import java.util.List;
import model.domain.Member;

/**
 * Will search for members with the same number of boats as specified.
 */
public class BoatCountMemberSearch implements MemberSearch {
  private int boatCount;

  public BoatCountMemberSearch(int boatCount) {
    this.boatCount = boatCount;
  }

  public BoatCountMemberSearch() {
    this.boatCount = 0;
  }

  /**
   * Will search for members who have the same number of boats as specified.
   *
   * @param members The ArrayList of Members in the registry.
   * @return New ArrayList with members containing the searchable.
   */
  @Override
  public List<Member> search(List<Member> members) {
    List<Member> filterMembers = new ArrayList<>();
    int searchCount = boatCount;

    for (Member member : members) {
      int memCount = member.getBoats().size();
      if (searchCount == memCount) {
        filterMembers.add(member);
      }
    }

    return filterMembers;
  }

  public MemberSearch setSearchable(int searchable) {
    this.boatCount = searchable;
    return this;
  }
}
