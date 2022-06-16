package model.domain;

/** Parent class for entities stored in the club's registry. */
public abstract class ClubEntity {
  private int id;

  public ClubEntity() {
  }

  public ClubEntity(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
