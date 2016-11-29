package date;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunTestSuite {
    public static void main(String[] args) {
        // can be called from the same class or elsewhere
        Result result = JUnitCore.runClasses(ExerciseTests.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}
