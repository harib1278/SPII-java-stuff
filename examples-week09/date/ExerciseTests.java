package date;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dllTest.DLLCoverageTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ // runs the JUnit tests in DummyTest, in DateTest,
                      // and in DLLCoverageTest
    DummyTest.class,
    DateTest.class,
    DLLCoverageTest.class
})

/**
 * A test suite for tests in several classes.
 *
 * @author Carsten Fuhs
 */
public class ExerciseTests {
}
