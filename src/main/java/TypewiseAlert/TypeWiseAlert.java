package TypewiseAlert;

import TypewiseAlert.alerts.TargetAlert;
import TypewiseAlert.breach.BreachType;
import TypewiseAlert.cooling.Cooling;

import static TypewiseAlert.breach.BreachType.*;

public final class TypeWiseAlert {

    private TypeWiseAlert() {
    }

    private static BreachType classifyTemperatureBreach(
          Cooling coolingType,
          double temperatureInC
    )
    {
        return inferBreach(temperatureInC, coolingType.getLowerLimit(), coolingType.getUpperLimit());
    }

    public static void checkAndAlert(
          TargetAlert alertTarget,
          BatteryCharacter batteryChar,
          double temperatureInC
    )
    {
        alertTarget.invoke(classifyTemperatureBreach(
              batteryChar.getCoolingType(), temperatureInC
        ));
    }
}
