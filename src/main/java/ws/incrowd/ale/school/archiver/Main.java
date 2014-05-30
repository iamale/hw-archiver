package ws.incrowd.ale.school.archiver;

import java.io.*;
import org.apache.commons.io.IOUtils;

public class Main {
  public static void main (String[] args) {
    byte[] data = {};

    try {
      FileInputStream infile = new FileInputStream(new File(args[1]));
      data = IOUtils.toByteArray(infile);
    } catch (Exception e) {
      System.err.println("Can't read input file!");
      System.err.println(e.getMessage());
      System.exit(1);
    }
    // todo: streamable encoding modules

    byte[] encoded = RLE.encode(MTF.encode(BWT.encode(data, (byte) -1)));

    try {
      FileOutputStream outfile = new FileOutputStream(new File(args[2]));
      IOUtils.write(encoded, outfile);
    } catch (Exception e) {
      System.err.println("Can't write output file!");
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }
}