package TypewiseAlert.alerts;

import TypewiseAlert.breach.BreachType;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class ControllerAlert implements TargetAlert {

    public static final int HEADER = 0xfeed;

    @Override
    public void invoke(BreachType type) {
        System.out.printf("%i : %i\n", HEADER, type.getMessage());
    }
}
