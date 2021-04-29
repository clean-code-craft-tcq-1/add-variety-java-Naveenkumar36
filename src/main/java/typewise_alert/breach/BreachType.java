package typewise_alert.breach;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public enum BreachType {
    NORMAL("Hi, the temperature is normal"),
    TOO_LOW("Hi, the temperature is too low"),
    TOO_HIGH("Hi, the temperature is too high");

    private final String message;

    BreachType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Returns BreachType based on argument values
     *
     * @param value      input value
     * @param lowerLimit minThreshold
     * @param upperLimit maxThreshold
     * @return BreachType
     */
    public static BreachType inferBreach(
          double value,
          double lowerLimit,
          double upperLimit
    )
    {
        if (value < lowerLimit) {
            return TOO_LOW;
        }
        if (value > upperLimit) {
            return TOO_HIGH;
        }
        return NORMAL;
    }
}
