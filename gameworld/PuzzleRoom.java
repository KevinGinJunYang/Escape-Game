package gameworld;


/**
 * A PreDefined Puzzle in a Room.
 * @author remi
 *
 */
public class PuzzleRoom extends Room {
  int type;
  String discription;
  GameObject[] solution;
  GameObject prize;

  /**
   * Create a new Puzzle Room of type(1-4).
   *
   * @param roomId The ID of this Room.
   *
   * @param type Determines the Puzzle in this room
   *
   */
  public PuzzleRoom(int roomId, int type) {
    super(roomId, ("Logic Puzzle " + type));
    this.type = type;
    switch (type) {
      case 1:
        solution = new GameObject[4];
        puzzle_1(roomId);
        break;
      case 2:
        puzzle_2(roomId);
        break;
      case 3:
        puzzle_3(roomId);
        break;
      case 4:
        solution = new GameObject[4];
        puzzle_4(roomId);
        break;
      default:
        break;
    }
  }

  private void puzzle_1(int roomId) {
    // prize of solving the puzzle.
    Chest c = new Chest("plain");
    c.addItemToChest(new Kitten());
    prize = c;

    // puzzle Set up
    discription = "Four Switchs opens to show the Chest, one Switch Hides it. "
        + "\nThe Value of Colors Lead the Way";

    solution[0] = new Chest("red");
    solution[1] = new Chest("Green");
    solution[2] = new Chest("BLUE");
    solution[3] = new Chest("YEllow");

    setGameObjectAt(0, 0, new Switch((Chest) solution[0]));
    setGameObjectAt(4, 4, new Switch((Chest) solution[3]));
    setGameObjectAt(0, 4, new Switch((Chest) solution[1]));
    setGameObjectAt(4, 0, new Switch((Chest) solution[2]));
    setGameObjectAt(2, 2, new Switch(this));
  }

  // can only have a single entrance from the south.
  private void puzzle_2(int roomId) {
    Chest c = new Chest("plain");
    c.addItemToChest(new Kitten());
    prize = c;
    // puzzle Set up
    discription = "Move the Boxes to get the Kitten in the Chest";

    setRoomLayoutForPuzzle2();

  }

  private void setRoomLayoutForPuzzle2() {
    // wipe the room clean
    for (int i = 0; i < roomLayout.length; i++) {
      for (int j = 0; j < roomLayout[0].length; j++) {
        roomLayout[i][j].item = null;
      }
    }

    setGameObjectAt(0, 1, new Box());
    setGameObjectAt(1, 1, new Box());
    setGameObjectAt(1, 3, new Box());
    setGameObjectAt(1, 4, new Box());
    setGameObjectAt(2, 1, new Box());
    setGameObjectAt(2, 3, new Box());
    setGameObjectAt(3, 1, new Box());
    setGameObjectAt(3, 3, new Box());
    setGameObjectAt(3, 4, new Switch(this));
    setGameObjectAt(3, 0, new Box());
    setGameObjectAt(4, 1, new Box());
    setGameObjectAt(2, 0, prize);
    // its a pushing puzzle no cheating
    for (int i = 0; i < roomLayout.length; i++) {
      for (int j = 0; j < roomLayout[0].length; j++) {
        if (roomLayout[i][j].item != null) {
          roomLayout[i][j].item.isPickupable = false;
        }
      }
    }
  }

  private void puzzle_3(int roomId) {
    Chest c = new Chest("plain");
    c.addItemToChest(new Kitten());
    prize = c;
    // puzzle Set up
    discription = "A Table for two in the center reveals the Chest. Plain Switch Solves the Puzzle";
    setGameObjectAt(0, 1, new Switch(this));
    setGameObjectAt(2, 2, new Table());
    setGameObjectAt(4, 4, new Chair());
    setGameObjectAt(0, 4, new Chair());

  }

  private void puzzle_4(int roomId) {
    Chest c = new Chest("plain");
    c.addItemToChest(new Kitten());
    prize = c;
    // puzzle Set up
    discription = "Yellow sits, Red has a key, Blue boxes, Green Tables";

    solution[0] = new Chest("red");
    solution[1] = new Chest("Green");
    solution[2] = new Chest("BLUE");
    solution[3] = new Chest("YEllow");
    for (int i = 0; i < solution.length; i++) {
      solution[i].isLocked = false;
    }

    setGameObjectAt(0, 0, solution[0]);
    setGameObjectAt(0, 4, solution[1]);
    setGameObjectAt(4, 0, solution[2]);
    setGameObjectAt(4, 4, solution[3]);
    setGameObjectAt(2, 2, new Switch(this));
    setGameObjectAt(4, 2, new Key(""));
    setGameObjectAt(4, 3, new Chair());
    setGameObjectAt(3, 1, new Table());
    setGameObjectAt(1, 3, new Box());

  }

  /**
   * Checks if the Puzzle in this Room is Solved, or resets the Puzzle.
   * @param player The Player solving this puzzle.
   *
   * @return True if the puzzle has been solved.
   */
  public boolean checkIfSolved(Player player) {
    switch (type) {
      case 1:
        for (int i = 0; i < solution.length; i++) {
          if (i != 3 && !solution[i].isLocked()) {
            // reset the puzzle
            for (int j = 0; j < solution.length; j++) {
              solution[j].isLocked = true;
            }

            return false;

          } else if (i == 3 && !solution[i].isLocked()) {
            roomLayout[2][2].setItem(prize);
            for (Door d : getDoors()) {
              if (d.isLocked()) {
                d.isLocked = false;
              }
            }
            return true;
          }
        }
        break;

      case 2:
        setRoomLayoutForPuzzle2();
        player.setRoomLocation(new Pair<Integer, Integer>(2, 4));
        System.out.println("Puzzle Reset");
        return false;

      case 3:
        if (roomLayout[2][2].getItem() instanceof Table
            && roomLayout[2][1].getItem() instanceof Chair
            && roomLayout[2][3].getItem() instanceof Chair
            || roomLayout[2][2].getItem() instanceof Table
                && roomLayout[1][2].getItem() instanceof Chair
                && roomLayout[3][2].getItem() instanceof Chair) {
          for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
              roomLayout[i][j].setItem(null);
            }
          }
          roomLayout[2][2].setItem(prize);
          return true;
        } else {
          return false;
        }

      case 4:
        Chest rc = (Chest) solution[0];
        Chest gc = (Chest) solution[1];
        Chest bc = (Chest) solution[2];
        Chest yc = (Chest) solution[3];
        if (rc.item instanceof Key && gc.item instanceof Table && bc.item instanceof Box
            && yc.item instanceof Chair) {
          roomLayout[2][2].setItem(prize);
          return true;
        } else {
          return false;
        }
      default:
        break;
    }
    return false;
  }



  /**
   * Get the type of Puzzle.
   * @return the type
   */
  public int getType() {
    return type;
  }

  /**
   * Set the type of Puzzle.
   * @param type the type to set
   */
  public void setType(int type) {
    this.type = type;
  }

  /**
   * Get the Solution Array.
   * @return the solution
   */
  public GameObject[] getSolution() {
    return solution;
  }

  /**
   * Set the Solution Array.
   * @param solution the solution to set
   */
  public void setSolution(GameObject[] solution) {
    this.solution = solution;
  }

  /**
   * Get the Prize for solving the Puzzle.
   * @return the prize
   */
  public GameObject getPrize() {
    return prize;
  }

  /**
   * Set the prize for solving the puzzle.
   * @param prize the prize to set
   */
  public void setPrize(GameObject prize) {
    this.prize = prize;
  }

  /**
   * Getter for description for this puzzle.
   *
   * @return The description
   */
  public String getDescription() {
    return discription;
  }

  /**
   * Set the Description for this puzzle.
   * @param discription the description to set.
   */
  public void setDiscription(String discription) {
    this.discription = discription;
  }
}
