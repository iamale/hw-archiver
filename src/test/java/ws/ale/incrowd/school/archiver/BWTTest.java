package ws.ale.incrowd.school.archiver;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
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
  public void testBWT() {
    assertEquals(
      "The quick brown fox jumps over the lazy doge.",
      BWT.decode(BWT.encode("The quick brown fox jumps over the lazy doge.", '~'), '~'));
  }
}
