package typewise_alert;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import typewise_alert.alerts.Alerter;
import typewise_alert.cooling.Cooling;
import typewise_alert.listener.AlertListener;

import static org.junit.Assert.*;
import static typewise_alert.utils.ClassHandler.*;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
@RunWith(Parameterized.class)
public class BatteryStatusTest {

    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    private static List<Alerter> alerters = null;
    private final AlertListener alertListener;
    private final BatteryCharacter batteryCharacter;
    private final double input;

    public BatteryStatusTest(
          AlertListener alertListener,
          BatteryCharacter batteryCharacter,
          double input
    )
    {
        this.alertListener = alertListener;
        this.batteryCharacter = batteryCharacter;
        this.input = input;
    }

    @Parameterized.Parameters
    public static List<Object> setUpTests() throws Throwable {
        AlertListener alertListener = (AlertListener) getInstanceForNoArgConstructor(AlertListener.class);
        Field field = AlertListener.class.getDeclaredField("alerters");
        field.setAccessible(true);
        alerters = (List<Alerter>) LOOKUP.unreflectGetter(field).invoke(alertListener);

        return Arrays.asList(new Object[][]{
              // High value
              {alertListener, new BatteryCharacter(Cooling.HI_ACTIVE_COOLING, ""), 50},
              {alertListener, new BatteryCharacter(Cooling.MED_ACTIVE_COOLING, ""), 45},
              {alertListener, new BatteryCharacter(Cooling.PASSIVE_COOLING, ""), 40},
              // Low values
              {alertListener, new BatteryCharacter(Cooling.HI_ACTIVE_COOLING, ""), -1},
              {alertListener, new BatteryCharacter(Cooling.MED_ACTIVE_COOLING, ""), -5},
              {alertListener, new BatteryCharacter(Cooling.PASSIVE_COOLING, ""), -4},
              // Normal value
              {alertListener, new BatteryCharacter(Cooling.HI_ACTIVE_COOLING, ""), 20},
              {alertListener, new BatteryCharacter(Cooling.MED_ACTIVE_COOLING, ""), 25},
              {alertListener, new BatteryCharacter(Cooling.PASSIVE_COOLING, ""), 15}
        });
    }

    @Test
    public void testIfAllAlertersAreInvoked() {
        BatteryStatus.checkAndAlert(alertListener, batteryCharacter, input);
        int totalAlerterInvoked = (int) alerters.stream().filter(Alerter::hasInvoked).count();
        assertTrue(alerters.size() == totalAlerterInvoked);
    }
}
