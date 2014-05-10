package ws.incrowd.ale.school.archiver;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.junit.Assert.*;
import java.util.Arrays;

public class RLETest extends TestCase {
  public RLETest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(RLETest.class);
  }

  public void testRLE() {
    byte[] arr = {1, 1, 1, 1, 2, 3, 4};

    byte[] enc = RLE.encode(arr);
    System.out.println(Arrays.toString(enc));

    byte[] dec = RLE.decode(enc);
    System.out.println(Arrays.toString(dec));

    _assertArrayEquals(
      arr,
      dec);
  }

  private void _assertArrayEquals(byte[] expected, byte[] actual) {
    assertArrayEquals("Arrays are different: exp: "
      + Arrays.toString(expected)
      + ", act: "
      + Arrays.toString(actual), expected, actual);
  }
}
