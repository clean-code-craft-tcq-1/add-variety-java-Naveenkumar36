package typewise_alert.cooling;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public enum Cooling {
    PASSIVE_COOLING(0, 35),
    HI_ACTIVE_COOLING(0, 45),
    MED_ACTIVE_COOLING(0, 40);

    private final double lowerLimit;
    private final double upperLimit;

    Cooling(
          double lowThreshold,
          double upperLimit
    )
    {
        this.lowerLimit = lowThreshold;
        this.upperLimit = upperLimit;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }
}
