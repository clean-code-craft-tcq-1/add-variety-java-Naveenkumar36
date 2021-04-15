package typewise_alert.alerts;

import typewise_alert.breach.BreachType;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public interface Alerter {

    /**
     * Perform alert based on BreachType
     *
     * @param type BreachType
     */
    void alert(BreachType type);

    /**
     * Returns boolean value if alert method is invoked
     *
     * @return boolean
     */
    boolean hasInvoked();
}
