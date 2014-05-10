package ws.incrowd.ale.school.archiver;

import java.util.Arrays;
import java.util.Comparator;

public class BWT {
  public static byte[] encode(byte[] str, byte padding) {
    byte[] src = new byte[str.length + 1];
    System.arraycopy(str, 0, src, 0, str.length);
    src[str.length] = padding;

    byte[][] perms = permutations(src);
    Byte[][] pErms = new Byte[perms.length][perms[0].length];

    for (int i = 0; i < perms.length; i++) {
      for (int j = 0; j < perms[0].length; j++) {
        pErms[i][j] = (Byte) perms[i][j];
      }
    }

    Arrays.sort(pErms, TwoDComparator);
    byte[] lastChars = new byte[pErms.length];
    for (int i = 0; i < pErms.length; i++) {
      lastChars[i] = pErms[i][pErms[i].length-1];
    }

    return lastChars;
  }

  public static byte[] decode(byte[] data, byte padding) {
    Byte[][] perms = new Byte[data.length][data.length];
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data.length; j++) {
        System.arraycopy(perms[j], 0, perms[j], 1, i);
        perms[j][0] = (Byte) data[j];
      }
      Arrays.sort(perms, TwoDComparator);
    }

    for (int i = 0; i < data.length; i++) {
      if (perms[i][perms[i].length - 1] == padding) {
        byte[] res = new byte[perms[i].length - 1];
        // System.arraycopy(perms[i], 0, res, 0, res.length);
        for (int j = 0; j < res.length; j++) {
          res[j] = perms[i][j].byteValue();
        }
        return res;
      }
    }
    return null;
  }

  private static Comparator<Byte[]> TwoDComparator = new Comparator<Byte[]>() {
    public int compare(Byte[] b1, Byte[] b2) {
      int minLength = (b1.length < b2.length) ? b1.length : b2.length;
      for (int i = 0; i < minLength; i++) {
        int res = b1[i].compareTo(b2[i]);
        if (res != 0) {
          return res;
        }
      }
      if (b1.length == b2.length) {
        return 0;
      } else {
        return (b1.length < b2.length) ? 1 : -1;
      }
    }
  };

  private static byte[][] permutations(byte[] orig) {
    byte[][] perms = new byte[orig.length][orig.length];
    
    byte[] now = orig;
    for (int i = 0; i < orig.length; i++) {
      perms[i] = now.clone();
      byte tmp = now[0];
      for (int j=0; j < now.length - 1; j++) {
        now[j] = now[j+1];
      }
      now[now.length - 1] = tmp;
    }
    return perms;
  }
}
