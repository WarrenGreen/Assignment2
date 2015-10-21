import oracle.jrockit.jfr.StringConstantPool;

import java.util.*;

/**
 * Created by wsgreen on 10/15/15.
 */
public class WordBased {

  public Set<String> getWord() {
    char[] w = new char[Kicker.length];
    Arrays.fill(w, '-');
    return getWord(w);
  }

  private Set<String> getWord(char[] word) {
    Set<String> ret = new HashSet<>();
    if(isFilled(word)) {
      ret.add(String.valueOf(word));
      return ret;
    }

    for(String category: Kicker.problem.keySet()) {
     Set<String> words = Kicker.categories.get(category);
      if(isCatValid(word, category))
        continue;
      else{
        for(String s: words){
          char[] temp = add(word, s, category);
          if(isValid(temp)){
            ret.addAll(getWord(temp));
          }
        }

      }
    }

    return ret;
  }

  private boolean isFilled(char[] w) {
    for(int i=0;i<w.length;i++) {
      if(w[i] == '-'){
        return false;
      }
    }

    return true;
  }

  private char[] add(char[] w, String n, String category) {
    char[] ret = new char[w.length];
    for(int i=0;i<w.length;i++) {
      ret[i] = w[i];
    }

    int i=0;
    for(int ind: Kicker.problem.get(category)) {
      ret[ind] = n.charAt(i);
      i++;
    }

    return ret;
  }

  private boolean isValid(char[] w) {
    for(String category: Kicker.problem.keySet()) {
      String word = "";
      for(int i: Kicker.problem.get(category)){
        word += w[i];
      }

      if(word.contains("-"))
        continue;

      if(!Kicker.categories.get(category).contains(word)){
        return false;
      }
    }

    return true;
  }

  private boolean isCatValid(char[] w, String category) {
    String word = "";
    for(int i: Kicker.problem.get(category)){
      word += w[i];
    }

    if(Kicker.categories.get(category).contains(word)){
      return true;
    }else
      return false;
  }
}
