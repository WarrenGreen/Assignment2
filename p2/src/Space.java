/**
 * Created by wsgreen on 10/23/15.
 */
public class Space {
  int value;
  int ownedBy = -1;

  public Space(int value) {
    this.value = value;
  }

  public Space(Space s) {
    this.value = s.value;
    this.ownedBy = s.ownedBy;
  }
}
