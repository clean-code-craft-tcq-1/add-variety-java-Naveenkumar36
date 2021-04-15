package typewise_alert.alerts;

import typewise_alert.breach.BreachType;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class MailAlert implements Alerter {

    private boolean isInvoked = false;

    @Override
    public void alert(BreachType type) {
        isInvoked = true;
        String recipients = "a.b@c.com";
        System.out.printf("To: %s\n", recipients);
        System.out.println(type.getMessage());
    }

    public boolean hasInvoked() {
        return isInvoked;
    }
}
