package ws.incrowd.ale.school.archiver;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.junit.Assert.*;
import java.util.Arrays;

public class BWTTest extends TestCase {
  public BWTTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(BWTTest.class);
  }

  /**
   * Basic BWT test.
   */
  // public void testBWT() {
  //   assertEquals(
  //     BWT.decode(BWT.encode("The quick brown fox jumps over the lazy doge.", '~'), '~'),
  //     "The quick brown fox jumps over the lazy doge.");
  // }

  public void testBWT() {
    byte[] arr = {1, 2, 3, 4};
    byte[] exp = {4, -1, 1, 2, 3};
    assertArrayEquals(
      BWT.encode(arr, (byte) -1),
      exp);
  }

  public void testUnBWT() {
    byte[] arr = {4, -1, 1, 2, 3};
    byte[] exp = {1, 2, 3, 4};
    assertArrayEquals(
      BWT.decode(arr, (byte) -1),
      exp);
  }
}
