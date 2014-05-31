package ws.incrowd.ale.school.archiver;

import java.util.Arrays;

class RLE {
  public static byte[] encode(byte[] data) {
    byte[][] pairs = detectPairs(data);
    byte[] code = new byte[2 * pairs.length];
    
    int pos = 0;
    for (byte[] pair : pairs) {
      code[pos] = pair[0]; pos++;
      code[pos] = pair[1]; pos++;
    }
    return code;
  }

  public static byte[] decode(byte[] code) {
    byte[] data = new byte[32000];

    int pos = 0, codePos = 0;
    while (codePos + 1 < code.length) {
      byte len = code[codePos]; codePos++;
      byte b = code[codePos]; codePos++;
      for (int i = 0; i < len; i++) {
        data[pos] = b; pos++;
      }
    }

    return Arrays.copyOf(data, pos);
  }

  private static byte[][] detectPairs(byte[] data) {
    byte[][] pairs = new byte[data.length][2];
    byte prev = -1;
    boolean isBeginning = true;
    byte count = 0;
    int pos = 0;

    for (byte b : data) {
      if (isBeginning != true) {
        if (b != prev) {
          pairs[pos][0] = count;
          pairs[pos][1] = prev;
          pos++;
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

    pairs[pos][0] = count;
    pairs[pos][1] = prev;
    pos++;
    
    return Arrays.copyOf(pairs, pos);
  }
}