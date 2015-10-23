/**
 * Created by wsgreen on 10/23/15.
 */
public class Space {
  int value;
  String ownedBy;

  public Space(int value) {
    this.value = value;
  }

  public Space(Space s) {
    this.value = s.value;
    this.ownedBy = s.ownedBy;
  }
}
