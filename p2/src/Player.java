import java.util.*;

/**
 * Created by wsgreen on 10/20/15.
 */
public class Player {

  private boolean first;

  MaxState root;
  MinState move;
  int player = 0;


  public Player() {
    first = true;
  }

  public Player(boolean first, Space[][] board) {
    if(first)
      player = 1;
    else
      player = 2;
    root = new MaxState(board);
  }


  public Object yourTurn(Object o) {
    MaxState nRoot = new MaxState((Space[][]) o);
    String hash = nRoot.hash();
    if(move!= null) {
      root = move.getOption(hash);
    }

    return o;
  }
    
  }
  


