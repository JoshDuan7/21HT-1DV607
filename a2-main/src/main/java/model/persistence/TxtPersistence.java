package model.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.domain.Boat;
import model.domain.BoatType;
import model.domain.Member;
import model.domain.Registry;

/**
 * This class is used to read off members from a .txt file.
 */

public class TxtPersistence implements Persistence {
  private Registry registry;

  public TxtPersistence(Registry reg) {
    this.registry = reg;
  }

  @Override
  public List<Member> load() {
    File file = new File("files/Registry.txt");
    FileReader fr = null;
    String line = "";
    List<Member> members = new ArrayList<>();

    try {
      fr = new FileReader(file);

      BufferedReader br = new BufferedReader(fr);
      while (true) {
        try {
          if (!((line = br.readLine()) != null)) {
            break;
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        String[] apa = line.split(";");

        Member member = new Member();
        ArrayList<Boat> boats = new ArrayList<>();

        member.setId(Integer.parseInt(apa[0]));
        member.setFirstName(String.valueOf(apa[1]));
        member.setLastName(String.valueOf(apa[2]));
        member.setPersonNum(String.valueOf(apa[3]));

        String[] boatsString = apa[4].split("/");
        for (String string : boatsString) {
          Boat boat = new Boat();
          List<String> listForBoat = Arrays.asList(string.split(":"));
          boat.setId(Integer.parseInt(listForBoat.get(0)));
          boat.setName(String.valueOf(listForBoat.get(1)));
          boat.setType(BoatType.valueOf(listForBoat.get(2)));
          boat.setLength(Double.parseDouble(listForBoat.get(3)));
          boats.add(boat);
        }
        member.setBoats(boats);
        members.add(member);
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return members;
  }

  @Override
  public void save() {
    List<Member> membersForSaveTxt = registry.getMembers();
    BufferedWriter output = null;
    try {
      File file = new File("files/Registry.txt");
      output = new BufferedWriter(new FileWriter(file));
      for (Member m : membersForSaveTxt) {
        StringBuilder sb = new StringBuilder();
        sb.append(m.getId()
                + (";")
                + (m.getFirstName()
                + ";"
                +
                (m.getLastName()
                        + ";"
                        + m.getPersonNum()
                        + ";")));
        boolean skip = true;
        for (Boat b : m.getBoats()) {
          if (!skip) {
            sb.append("/");
          }
          skip = false;
          sb.append(b.getId() + ":" + b.getName() + ":" + b.getType() + ":" + b.getLength());
        }
        sb.append("/");
        sb.append("\n");
        output.write(sb.toString());
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (output != null) {
        try {
          output.close();
        } catch (IOException e) {
          //Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }
}
