package typewise_alert;

import typewise_alert.cooling.Cooling;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class BatteryCharacter {

    private final Cooling coolingType;
    private final String brand;

    /**
     * Construct BatterCharacter object based on arguments
     *
     * @param coolingType CoolingType object
     * @param brand       String value
     */
    public BatteryCharacter(
          Cooling coolingType,
          String brand
    )
    {
        this.coolingType = coolingType;
        this.brand = brand;
    }

    public Cooling getCoolingType() {
        return coolingType;
    }

    public String getBrand() {
        return brand;
    }
}
