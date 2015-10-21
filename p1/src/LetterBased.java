import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by wsgreen on 10/15/15.
 */
public class LetterBased {
  private String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private Map<String, TrieNode> map;

  public LetterBased() {
    map = new HashMap<>();
    for(Map.Entry<String, Set<String>> entry: Kicker.categories.entrySet()) {
      TrieNode root = new TrieNode('-');
      for(String s: entry.getValue()) {
        TrieNode curr = root;
        for(int i=0;i<s.length();i++) {
          char c = s.charAt(i);
          if(curr.children.containsKey(c)) {
            curr =curr.children.get(c);
          }else{
            TrieNode temp = new TrieNode(s.charAt(i));
            curr.children.put(s.charAt(i), temp);
            curr = temp;
          }
        }
        curr.mark=true;
      }
      map.put(entry.getKey(), root);
    }
  }

  public Set<String> getWord(String word) {
    Set<String> ret = new HashSet<>();
    if(word.length() == Kicker.length){
      ret.add(word);
      return ret;
    }

    for(int i=0;i<alpha.length();i++) {
      char c = alpha.charAt(i);
      if(isValid(word+c)) {
        ret.addAll(getWord(word + c));
      }
    }

    return ret;
  }

  public boolean isValid(String word) {
    for(Map.Entry<String, Set<Integer>> entry: Kicker.problem.entrySet()) {
      TrieNode curr = map.get(entry.getKey());
      for(int ind: entry.getValue()) {
        if(ind < word.length()) {
          if (curr.children.containsKey(word.charAt(ind)))
            curr = curr.children.get(word.charAt(ind));
          else
            return false;
        }else
          break;
      }
    }

    return true;

  }

  class TrieNode {
    char element;
    HashMap<Character, TrieNode> children;
    boolean mark = false;
    public TrieNode(char c) {
      element= c;
      children = new HashMap<>();
    }
  }

}
