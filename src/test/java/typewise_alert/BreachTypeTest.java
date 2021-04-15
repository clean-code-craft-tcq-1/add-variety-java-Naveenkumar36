package typewise_alert;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import typewise_alert.breach.BreachType;

import static org.junit.Assert.*;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
@RunWith(Parameterized.class)
public class BreachTypeTest {

    private final double input;
    private final double minThreshold;
    private final double maxThreshold;
    private final BreachType expectedResult;

    public BreachTypeTest(
          double input,
          double minThreshold,
          double maxThreshold,
          BreachType expectedResult
    )
    {
        this.input = input;
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static List<Object> setUpTests() {
        final double maxThreshold = 30;
        final double minThreshold = 20;
        return Arrays.asList(new Object[][]{
              {12, minThreshold, maxThreshold, BreachType.TOO_LOW},
              {60, minThreshold, maxThreshold, BreachType.TOO_HIGH},
              {25, minThreshold, maxThreshold, BreachType.NORMAL}
        });
    }

    @Test
    public void infersBreachAsPerLimits()
    {
        BreachType breachType = BreachType.inferBreach(input, minThreshold, maxThreshold);
        assertSame(breachType, expectedResult);
        assertEquals(breachType.getMessage(), expectedResult.getMessage());
    }
}
