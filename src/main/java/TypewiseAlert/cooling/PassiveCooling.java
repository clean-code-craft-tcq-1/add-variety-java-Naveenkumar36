package TypewiseAlert.cooling;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class PassiveCooling implements Cooling {

    private static final double UPPER_LIMIT = 35;
    private static final double LOWER_LIMIT = 0;

    @Override
    public double getUpperLimit() {
        return UPPER_LIMIT;
    }

    @Override
    public double getLowerLimit() {
        return LOWER_LIMIT;
    }
}
