package typewise_alert;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import typewise_alert.alerts.Alerter;
import typewise_alert.alerts.ControllerAlert;
import typewise_alert.alerts.MailAlert;
import typewise_alert.breach.BreachType;
import typewise_alert.command.CommandAlert;
import typewise_alert.cooling.Cooling;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
class BatteryStatusTest {

    private static final double MAX_THRESHOLD = 30;
    private static final double MIN_THRESHOLD = 20;

    @ParameterizedTest
    @MethodSource("argumentsData")
    void infersBreachAsPerLimits(
          double input,
          double minThreshold,
          double maxThreshold,
          BreachType expectedResult
    )
    {
        BreachType breachType = BreachType.inferBreach(input, minThreshold, maxThreshold);
        assertSame(breachType, expectedResult);
        assertEquals(breachType.getMessage(), expectedResult.getMessage());
    }

    private static Stream<Arguments> argumentsData() {
        return Stream.of(
              Arguments.of(12, MIN_THRESHOLD, MAX_THRESHOLD, BreachType.TOO_LOW),
              Arguments.of(60, MIN_THRESHOLD, MAX_THRESHOLD, BreachType.TOO_HIGH),
              Arguments.of(25, MIN_THRESHOLD, MAX_THRESHOLD, BreachType.NORMAL)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void givenBatterStatus_whenBreachTypeIsHighOrLow_ThenEmailAlert(
          double input,
          BatteryCharacter batteryCharacter,
          BreachType expectedType
    )
    {
        // ARRANGE
        Alerter alerter = spy(new MailAlert());
        // ACT
        BatteryStatus.checkAndAlert(alerter, batteryCharacter, input);
        // ASSERT
        verify(alerter).alert(expectedType);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void givenBatterStatus_whenBreachTypeIsHighOrLow_ThenVerifyAlerters(
          double input,
          BatteryCharacter batteryCharacter,
          BreachType expectedType
    )
    {
        // ARRANGE
        CommandAlert alerter = spy(new CommandAlert());
        MailAlert mailAlert = spy(new MailAlert());
        ControllerAlert controllerAlert = spy(new ControllerAlert());
        alerter.addAlerter(mailAlert);
        alerter.addAlerter(controllerAlert);
        // ACT
        BatteryStatus.checkAndAlert(alerter, batteryCharacter, input);
        // ASSERT
        verify(alerter).alert(expectedType);
        verify(mailAlert).alert(expectedType);
        verify(controllerAlert).alert(expectedType);
        verify(alerter, times(2)).addAlerter(any());
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
              Arguments.of(60, new BatteryCharacter(Cooling.HI_ACTIVE_COOLING, ""), BreachType.TOO_HIGH),
              Arguments.of(50, new BatteryCharacter(Cooling.MED_ACTIVE_COOLING, ""), BreachType.TOO_HIGH),
              Arguments.of(45, new BatteryCharacter(Cooling.PASSIVE_COOLING, ""), BreachType.TOO_HIGH),
              Arguments.of(40, new BatteryCharacter(Cooling.HI_ACTIVE_COOLING, ""), BreachType.NORMAL),
              Arguments.of(30, new BatteryCharacter(Cooling.MED_ACTIVE_COOLING, ""), BreachType.NORMAL),
              Arguments.of(20, new BatteryCharacter(Cooling.PASSIVE_COOLING, ""), BreachType.NORMAL),
              Arguments.of(-1, new BatteryCharacter(Cooling.HI_ACTIVE_COOLING, ""), BreachType.TOO_LOW),
              Arguments.of(-2, new BatteryCharacter(Cooling.MED_ACTIVE_COOLING, ""), BreachType.TOO_LOW),
              Arguments.of(-3, new BatteryCharacter(Cooling.PASSIVE_COOLING, ""), BreachType.TOO_LOW)
        );
    }
}
