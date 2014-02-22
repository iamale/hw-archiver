package ws.ale.incrowd.school.archiver;

import java.util.Arrays;

public class BWT {
  public static void main(String[] args) {
    System.out.println(encode(args[0], '~'));
    System.out.println(decode(args[0], '~'));
  }

  public static String encode(String str, char padding) {
    String[] perms = permutations(str + padding);
    Arrays.sort(perms);
    char[] lastChars = new char[perms.length];
    for (int i = 0; i < perms.length; i++) {
      lastChars[i] = perms[i].charAt(perms[i].length()-1);
    }

    return arrayToString(lastChars);
  }

  public static String decode(String str, char padding) {
    String[] perms = new String[str.length()];

    for (int j = 0; j < str.length(); j++) {
      perms[j] = "";
    }

    for (int i = 0; i < str.length(); i++) {
      for (int j = 0; j < str.length(); j++) {
        perms[j] = str.charAt(j) + perms[j];
      }
      Arrays.sort(perms);
    }

    for (int j = 0; j < str.length(); j++) {
      if (perms[j].charAt(perms[j].length() - 1) == padding) {
        return perms[j].substring(0, str.length()-1);
      }
    }

    return perms[0].substring(0, str.length()-1);
  }

  private static String[] permutations(String orig) {
    String[] perms = new String[orig.length()];
    String now = new String(orig);
    for (int i = 0; i < orig.length(); i++) {
      perms[i] = now;
      now = now.substring(1) + now.charAt(0);
    }
    return perms;
  }

  private static String arrayToString(char[] chars) {
    String string = "";
    for (char c : chars) {
      string = string + c;
    }
    return string;
  }
}