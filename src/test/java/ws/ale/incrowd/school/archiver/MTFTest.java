package ws.ale.incrowd.school.archiver;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.junit.Assert.*;
import java.util.Arrays;

import ws.incrowd.ale.school.archiver.MTF;

public class MTFTest extends TestCase {
  public MTFTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(MTFTest.class);
  }

  // /**
  //  * Checking assertions
  //  */
  // public void testSelf() {
  //   byte[] arr = {0xd, 0xe, 0xa, 0xd, 0xb, 0xe, 0xe, 0xf};
  //   assert(Arrays.equals(
  //     arr,
  //     arr));
  // }

  /**
   * Basic MTF test.
   */
  public void testMTF() {
    byte[] arr = {0xd, 0xe, 0xa, 0xd, 0xb, 0xe, 0xe, 0xf};

    assertArrayEquals(
      arr,
      MTF.decode(MTF.encode(arr)));
  }
}
