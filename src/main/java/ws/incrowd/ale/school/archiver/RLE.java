package ws.incrowd.ale.school.archiver;

import java.util.Arrays;

class RLE {
  public static byte[] encode(byte[] data) {
    byte[][] pairs = detectParis(data);
    byte[] out = new byte[2 * pairs.length];
    int iii = 0;
    for (byte[] pair : pairs) {
      out[iii] = pair[0];
      iii++;
      out[iii] = pair[1];
      iii++;
    }
    return out;
  }

  public static byte[] decode(byte[] data) {
    int iii = 0;
    byte[] out = new byte[32000];
    int jjj = 0;
    while (iii + 1 < data.length) {
      byte len = data[iii]; iii++;
      byte byet = data[iii]; iii++;
      for (int i = 0; i < len; i++) {
        out[jjj] = byet; jjj++;
      }
    }
    byte[] piece = new byte[jjj];
    System.arraycopy(out, 0, piece, 0, jjj);
    return piece;
  }

  private static byte[][] detectParis(byte[] data) {
    byte[][] res = new byte[data.length][2];
    byte prev = -1;
    boolean isBeginning = true;
    byte count = 0;
    int iii = 0;
    for (byte b : data) {
      if (isBeginning != true) {
        if (b != prev) {
          res[iii][0] = count;
          res[iii][1] = prev;
          iii++;
          prev = b;
          count = 1;
        } else {
          count++;
        }
      } else {
        prev = b;
        count = 1;
        isBeginning = false;
      }
    }

    res[iii][0] = count;
    res[iii][1] = prev;
    iii++;

    byte[][] piece = new byte[iii][2];
    System.arraycopy(res, 0, piece, 0, iii);
    return piece;
  }
}