import java.util.*;

/**
 * Created by wsgreen on 10/20/15.
 */
public class Player {

  private boolean first;

  State root;


  public Player() {
    first = true;
  }

  public Player(boolean first, Space[][] board) {
    this.first = first;
    root = new State(board);

    for(int i=0;i<3;i++)
      fillOptions(root);
  }


  public Object yourTurn(Object o) {
    State nRoot = new State((Space[][]) o);
    String hash = nRoot.hash();
    if(root.hash().compareTo(hash)!=0)
      root = root.options.get(hash);

    fillOptions(root);
    return null;
  }

  private List<State> getLeaves(State root) {
    List<State> ret = new ArrayList<>();
    Queue<State> queue = new LinkedList<>();
    queue.add(root);

    while(!queue.isEmpty()) {
      State curr = queue.poll();
      if(curr.options.isEmpty())
        ret.add(curr);

      for(State s: curr.options.values())
        queue.add(s);
    }

    return ret;

  }

  public void fillOptions(State root) {
    List<State> leaves = getLeaves(root);

    for(State state: leaves) {
      for(int i=0;i<state.board.length;i++) {
        for(int j=0;j<state.board[0].length;j++){
          if(state.board[i][j].ownedBy == null) {
            State n = new State(takeSpot(i, j, state.board));
            state.options.put(n.hash(), n);

          }
        }
      }
    }
  }

  private Space[][] takeSpot(int x, int y, Space[][] b) {
    Space[][] board = new Space[b.length][b[0].length];
    for(int i=0;i<board.length;i++) {
      for(int j=0;j<board[0].length;j++){
        board[i][j] = b[i][j];


        }
      }
    
      board[x][y]= new Space(board[x][y]);
      board[x][y].ownedBy = this.toString();
    return board;
    }

  @Override
  public String toString() {
    if(first)
      return "P1";
    else
      return "P2";
  }
    
  }
  
  class State {
    Space[][] board;
    Map<String, State> options;

    public State(Space[][] b) {
      this.board = new Space[b.length][b[0].length];

      options = new HashMap<>();

      for (int i = 0; i < b.length; i++) {
        for (int j = 0; j < b[0].length; j++) {
          this.board[i][j] = b[i][j];

        }
      }

    }

    public String hash() {
      String hash = "";
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          this.board[i][j] = board[i][j];
          if (board[i][j].ownedBy == null)
            hash += "-";
          else
            hash += board[i][j].ownedBy;
        }
      }
      return hash;
    }

  }

