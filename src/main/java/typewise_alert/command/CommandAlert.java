package typewise_alert.command;

import java.util.ArrayList;
import java.util.List;

import typewise_alert.alerts.Alerter;
import typewise_alert.breach.BreachType;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class CommandAlert implements Alerter {

    private final List<Alerter> alerters = new ArrayList<>();

    /**
     * Add alerter to list
     *
     * @param alerter Alerter object
     */
    public void addAlerter(Alerter alerter) {
        alerters.add(alerter);
    }

    /**
     * Alert all the alerter from typewise_alert.alerts package
     *
     * @param type BreachType data
     */
    @Override
    public void alert(BreachType type) {
        alerters.forEach(alerter -> alerter.alert(type));
    }
}
