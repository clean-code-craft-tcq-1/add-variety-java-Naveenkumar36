package typewise_alert;

import typewise_alert.breach.BreachType;
import typewise_alert.cooling.Cooling;
import typewise_alert.listener.AlertListener;

import static typewise_alert.breach.BreachType.*;

/**
 * Depending on breach type alert the alerter
 *
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public final class BatteryStatus {

    private BatteryStatus() {
    }

    private static BreachType classifyTemperatureBreach(
          Cooling coolingType,
          double temperatureInC
    )
    {
        return inferBreach(temperatureInC, coolingType.getLowerLimit(), coolingType.getUpperLimit());
    }

    /**
     * Invoke alerters based on breach type
     *
     * @param alertListener  AlertListener to invoke alerters
     * @param batteryChar    BatteryCharacter
     * @param temperatureInC double value
     */
    public static void checkAndAlert(
          AlertListener alertListener,
          BatteryCharacter batteryChar,
          double temperatureInC
    )
    {
        alertListener.invokeAlerter(classifyTemperatureBreach(
              batteryChar.getCoolingType(), temperatureInC
        ));
    }
}
