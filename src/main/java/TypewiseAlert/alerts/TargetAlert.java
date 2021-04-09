package TypewiseAlert.alerts;

import TypewiseAlert.breach.BreachType;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public interface TargetAlert {

    void invoke(BreachType type);
}
