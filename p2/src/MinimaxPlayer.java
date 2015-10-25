/**
 * Created by wsgreen on 10/21/15.
 */
public class MinimaxPlayer extends Player {


  public MinimaxPlayer(boolean first, Space[][] board) {
    super(first, board);

    for (int i=0;i<3;i++) {
      root.fillOptions(player);
    }


  }


  @Override
  public Object yourTurn(Object o) {
    super.yourTurn(o);
    move = (MinState) root.getValue(player);
    root.fillOptions(player);

    return move;
  }

}
