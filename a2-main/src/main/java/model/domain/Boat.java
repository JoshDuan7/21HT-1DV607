package model.domain;

/** Boat class describes information related to a boat. */
public class Boat extends ClubEntity {
  private String name;
  private BoatType type;
  private double length;

  /**
   * Default constructor for creating a new boat object.
   */
  public Boat() {}

  /**
   * Creates a new boat object.
   *
   * @param name Name of the boat.
   * @param id ID of the boat.
   * @param type Type of the boat.
   * @param length Length of the boat.
   */
  public Boat(String name, int id, BoatType type, double length) {
    super(id);
    this.name = name;
    this.type = type;
    this.length = length;
  }

  public double getLength() {
    return length;
  }

  public BoatType getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setType(BoatType type) {
    this.type = type;
  }

  public void setLength(double length) {
    this.length = length;
  }

}
