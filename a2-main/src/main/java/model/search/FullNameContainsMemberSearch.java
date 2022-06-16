package model.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import model.domain.Member;

/**
 * Will search for members containing in their full name the provided String argument.
 */
public class FullNameContainsMemberSearch implements MemberSearch {
  private String containing;

  public FullNameContainsMemberSearch(String containing) {
    this.containing = containing.toLowerCase();
  }

  public FullNameContainsMemberSearch() {
    this.containing = "";
  }

  /**
   * Will search for members containing in their full name the provided String argument.
   *
   * @param members The ArrayList of Members in the registry.
   * @return New ArrayList with members containing the searchable.
   */
  @Override
  public List<Member> search(List<Member> members) {
    List<Member> filterMembers = new ArrayList<>();

    for (Member member : members) {
      String fullName = member.getFirstName() + " " + member.getLastName();
      fullName = fullName.toLowerCase(Locale.ENGLISH);
      if (fullName.contains(containing)) {
        filterMembers.add(member);
      }
    }

    return filterMembers;
  }

  public MemberSearch setSearchable(String searchable) {
    this.containing = searchable.toLowerCase();
    return this;
  }
}
