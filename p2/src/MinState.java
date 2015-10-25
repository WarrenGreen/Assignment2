import java.util.List;

class MinState  extends State{
  int min = Integer.MAX_VALUE;

  public MinState(Space[][] b) {
    super(b);
  }

  public MaxState getOption(String key) {
    return (MaxState) options.get(key);
  }

  public void fillOptions(){
    List<State> leaves = getLeaves(this);

    for(State state: leaves) {
      for(int i=0;i<state.board.length;i++) {
        for(int j=0;j<state.board[0].length;j++){
          if(state.board[i][j].ownedBy == -1) {
            MaxState n = new MaxState(takeSpot(i, j, state.board, 9));
            state.options.put(n.hash(), n);
          }
        }
      }
    }
  }

  public State getValue(int player) {

    if(options.isEmpty()) {
      return super.getValue(player);
    }else{
      State min = null;
      for(State s: options.values()) {
        State val = s.getValue(player+1);
        if(min ==null || min.val > val.val)
          min = val;

      }
      this.min = min.val;
      return min;
    }
  }

}