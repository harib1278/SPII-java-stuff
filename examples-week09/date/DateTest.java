package date;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DateTest {
    // almost every test should have a timeout so it can't 
    // lead to an infinite loop; good to set a default, too
    private static final int DEFAULT_TIMEOUT = 2000;

    @Test(timeout = DEFAULT_TIMEOUT)
    public void test_addDays_withinSameMonth_1() {
        Date d = new DateImpl(2050, 2, 15);
        d.addDays(4);
        Date expected = new DateImpl(2050, 2, 19);
        assertEquals("date after +4 days", expected, d);
    }

    @Test(timeout = 3000)
    public void test_addDays_wrapToNextMonth_2() {
        Date d = new DateImpl(2050, 2, 15);
        d.addDays(14);
        Date expected = new DateImpl(2050, 3, 1);
        assertEquals("date after +14 days", expected, d);
    }
}
