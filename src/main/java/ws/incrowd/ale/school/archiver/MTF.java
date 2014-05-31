package ws.incrowd.ale.school.archiver;

import java.util.Arrays;

public class MTF {
  public static byte[] encode(byte[] src) {
    MTFEncoder coder = new MTFEncoder();
    byte[] data = new byte[src.length];
    for (int i = 0; i < src.length; i++) {
      data[i] = coder.encode(src[i]);
    }
    byte[] order = coder.getOrder();

    byte[] both = Arrays.copyOf(order, 256 + data.length);
    System.arraycopy(data, 0, both, 256, data.length);

    return both;
  }

  public static byte[] decode(byte[] both) {
    byte[] order = Arrays.copyOf(both, 256);
    byte[] data = Arrays.copyOfRange(both, 256, both.length);

    MTFDecoder coder = new MTFDecoder(order);
    byte[] outta = new byte[data.length];
    for (int i = data.length-1; i >= 0; i--) {
      outta[i] = coder.decode(data[i]);
    }
    return outta;
  }

  private static class MTFEncoder {
    private byte[] order = new byte[256];

    public MTFEncoder() {
      reset();
    }

    public void reset() {
      for (int i=0; i<256; i++){
        order [i] = (byte) i;
      }
    }

    byte encode (byte b) {
      int p=0;
      while (order[p] != b) p++;

      for (int i=p; i>0; i--) {
        order[i] = order[i-1];
      }

      order[0] = b;
      return (byte) p;
    }

    public byte[] getOrder() {
      return order;
    }
  }

  private static class MTFDecoder {
    // Reverse before using
    private byte[] order = new byte[256];

    public MTFDecoder(byte[] o) {
      order = o;
    }

    byte decode (byte p) {
      byte res = order[0];

      for (int i=0; i<p; i++) {
        order[i] = order[i+1];
      }

      order[_good(p)] = res;
      return res;
    }

    private static int _good(byte p) {
      if (p >= 0) {
        return (int) p;
      } else {
        return (256 + (int) p);
      }
    }
  }
}