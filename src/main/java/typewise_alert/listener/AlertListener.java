package typewise_alert.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import typewise_alert.alerts.Alerter;
import typewise_alert.breach.BreachType;

import static typewise_alert.utils.ClassHandler.*;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public final class AlertListener {

    public static final String PACKAGE_NAME = "typewise_alert";
    private final List<Alerter> alerters = new ArrayList<>();

    /**
     * Fetch all the Alerter from typewise_alert.alerts package
     *
     * @throws Throwable throws Throwable
     */
    public AlertListener() throws Throwable
    {
        List<Class<Object>> alerterClass = Arrays.stream(getClasses(PACKAGE_NAME))
              .filter(aClass -> aClass.toString().endsWith("Alert"))
              .collect(Collectors.toList());
        for (Class<Object> alert : alerterClass) {
            alerters.add((Alerter) getInstanceForNoArgConstructor(alert));
        }
    }

    /**
     * Alert all the alerter from typewise_alert.alerts package
     *
     * @param breachType BreachType data
     */
    public void invokeAlerter(BreachType breachType) {
        alerters.forEach(alerter -> alerter.alert(breachType));
    }
}
