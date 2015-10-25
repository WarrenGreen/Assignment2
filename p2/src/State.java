import java.util.*;

class State {
  Space[][] board;
  Map<String, State> options;
  int val;


  public State(){

  };

  public State(Space[][] b) {
    options = new HashMap<>();
    this.board = new Space[b.length][b[0].length];

    for (int i = 0; i < b.length; i++) {
      for (int j = 0; j < b[0].length; j++) {
        this.board[i][j] = b[i][j];

      }
    }

  }

  public State getValue(int player) {
    int val = 0;

      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          if (board[i][j].ownedBy == -1) {
            continue;
          } else if (player == board[i][j].ownedBy) {
            val += board[i][j].value;
          } else {
            val -= board[i][j].value;
          }
        }
      }
    this.val = val;
    return this;
  }
  
  public List<State> getLeaves(State root) {
    List<State> ret = new ArrayList<>();
    Queue<State> queue = new LinkedList<>();
    queue.add(root);

    while(!queue.isEmpty()) {
      State curr = queue.poll();
      if(curr.options.isEmpty())
        ret.add(curr);

      queue.addAll(curr.options.values());
    }

    return ret;

  }

  Space[][] takeSpot(int x, int y, Space[][] b, int player) {
    Space[][] board = new Space[b.length][b[0].length];
    for(int i=0;i<board.length;i++) {
      for(int j=0;j<board[0].length;j++){
        board[i][j] = b[i][j];


      }
    }

    board[x][y]= new Space(board[x][y]);
    board[x][y].ownedBy = player;
    return board;
  }

  public String hash() {
    String hash = "";
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        this.board[i][j] = board[i][j];
        if (board[i][j].ownedBy == -1)
          hash += "-";
        else
          hash += board[i][j].ownedBy;
      }
    }
    return hash;
  }

}