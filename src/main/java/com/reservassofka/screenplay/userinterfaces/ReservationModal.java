package com.reservassofka.screenplay.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ReservationModal {
    public static final Target START_TIME_INPUT = Target.the("Start Time Input").located(By.id("startTime"));
    public static final Target END_TIME_INPUT = Target.the("End Time Input").located(By.id("endTime"));
    public static final Target ATTENDEES_INPUT = Target.the("Attendees Input").located(By.cssSelector(".modal-section-attendees input[type='number']"));
    public static final Target CONFIRM_BUTTON = Target.the("Confirm Reservation Button").located(By.cssSelector(".btn-confirm"));
    public static final Target ERROR_MESSAGE = Target.the("Error Message").located(By.cssSelector(".modal-error-banner"));
    public static final Target SUCCESS_MESSAGE = Target.the("Success Message").located(By.cssSelector(".modal-success-banner"));
    public static final Target CALENDAR_DAY = Target.the("Calendar Day {0}")
            .locatedBy("//div[contains(@class, 'calendar-day') and not(contains(@class, 'disabled')) and text()='{0}']");
}
