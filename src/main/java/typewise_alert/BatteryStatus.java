package typewise_alert;

import java.util.function.BiFunction;
import java.util.function.Function;

import typewise_alert.alerts.Alerter;
import typewise_alert.breach.BreachType;
import typewise_alert.cooling.Cooling;

import static typewise_alert.breach.BreachType.*;

/**
 * Depending on breach type alert the alerter
 *
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public final class BatteryStatus {

    private static final BiFunction<Cooling, Double, BreachType> classifyTemperatureBreach =
          (cooling, temperatureInC) -> inferBreach(temperatureInC, cooling.getLowerLimit(), cooling.getUpperLimit());

    private BatteryStatus() {
    }

    /**
     * Invoke alerters based on breach type
     *
     * @param alerter        AlertListener to invoke alerters
     * @param batteryChar    BatteryCharacter
     * @param temperatureInC double value
     */
    public static void checkAndAlert(
          Alerter alerter,
          BatteryCharacter batteryChar,
          double temperatureInC
    )
    {
        alerter.alert(classifyTemperatureBreach.apply(batteryChar.getCoolingType(), temperatureInC));
    }
}
