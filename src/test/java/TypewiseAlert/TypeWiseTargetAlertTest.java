package TypewiseAlert;

import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import TypewiseAlert.alerts.TargetAlert;

@RunWith(Parameterized.class)
public class TypeWiseTargetAlertTest {

    @Parameterized.Parameters
    public static List<Object> setUpTests() {
        List<TargetAlert> alerts = useReflectionToGetAlerts();
        return null;
    }

    private static List<TargetAlert> useReflectionToGetAlerts() {
        return null;
    }

}
