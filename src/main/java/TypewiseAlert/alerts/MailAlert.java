package TypewiseAlert.alerts;

import TypewiseAlert.breach.BreachType;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class MailAlert implements TargetAlert {

    @Override
    public void invoke(BreachType type) {
        String recipients = "a.b@c.com";
        System.out.printf("To: %s\n", recipients);
        System.out.println(type.getMessage());
    }
}
