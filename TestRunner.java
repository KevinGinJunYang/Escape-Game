import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Test Runnner to run suite.
 *
 * @author joewill
 *
 */
public class TestRunner {

  /**
   * Runs the test.
   * @param args arguement.
   */
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(Tests.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }

    System.out.println(result.wasSuccessful());
  }
}
