import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Created by wsgreen on 10/20/15.
 */
public class Game {
  public Space[][] board;
  List<Function> players;

  public Game(int size, String file) {
    board = new Space[size][size];
    readFile(file);

    players = new ArrayList<>();
    Player p1 = new MinimaxPlayer(true, board);
    Player p2 = new MinimaxPlayer(false, board);
    players.add(p1::yourTurn);
    players.add(p2::yourTurn);


  }

  public void registerPlayer(Function fun) {
    players.add(fun);
  }

  private void readFile(String file) {
    Scanner in = null;
    try {
      in = new Scanner(new File(file));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }

    int row = 0;
    while(in.hasNext()) {
      String[] line = in.nextLine().trim().split("\t");

      for(int i =0;i<line.length;i++) {
        board[row][i] = new Space(Integer.valueOf(line[i]));

      }
      row++;
    }
  }

  public void start() {
    int i=0;
    while(!gameOver()) {
      MinState m = (MinState) players.get(i).apply(board);
      i++;
      if(i>=board.length)
        i=0;
    }
  }

  private boolean gameOver() {
    for(int i=0;i<board.length;i++) {
      for(int j=0;j<board[0].length;j++) {
        if(board[i][j].ownedBy == -1)
          return false;
      }
    }

    return true;
  }

}
