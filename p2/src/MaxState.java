import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MaxState extends State {

  public MaxState(Space[][] b)  {
    super(b);
    options = new HashMap<>();

  }

  public MinState getOption(String key) {
    return (MinState) options.get(key);
  }

  public void fillOptions(int player){
    List<State> leaves = getLeaves(this);

    for(State state: leaves) {
      for(int i=0;i<state.board.length;i++) {
        for(int j=0;j<state.board[0].length;j++){
          if(state.board[i][j].ownedBy == -1) {
            MinState n = new MinState(takeSpot(i, j, state.board,player+1 ));
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
      State max = null;
      for(State s: options.values()) {
        State val = s.getValue(player);
        if(max == null || max.val < val.val)
          max = val;
      }
      this.val = max.val;
      return max;
    }
  }

}