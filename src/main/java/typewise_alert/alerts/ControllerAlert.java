package typewise_alert.alerts;

import typewise_alert.breach.BreachType;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class ControllerAlert implements Alerter {

    private static boolean isInvoked = false;

    @Override
    public void alert(BreachType type) {
        isInvoked = true;
        System.out.printf(type.getMessage());
    }
}
