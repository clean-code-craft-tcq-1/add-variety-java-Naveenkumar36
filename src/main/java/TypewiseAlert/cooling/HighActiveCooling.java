package TypewiseAlert.cooling;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class HighActiveCooling implements Cooling {

    private static final double UPPER_LIMIT = 45;
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

