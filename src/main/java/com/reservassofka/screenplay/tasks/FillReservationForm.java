package com.reservassofka.screenplay.tasks;

import com.reservassofka.screenplay.userinterfaces.ReservationModal;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillReservationForm implements Task {
    private final String day;
    private final String startTime;
    private final String endTime;

    public FillReservationForm(String day, String startTime, String endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static FillReservationForm withDetails(String day, String startTime, String endTime) {
        return Tasks.instrumented(FillReservationForm.class, day, startTime, endTime);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ReservationModal.START_TIME_INPUT, isVisible()).forNoMoreThan(5).seconds(),
                Click.on(ReservationModal.CALENDAR_DAY.of(day)),
                Enter.theValue(startTime).into(ReservationModal.START_TIME_INPUT),
                Enter.theValue(endTime).into(ReservationModal.END_TIME_INPUT),
                Click.on(ReservationModal.CONFIRM_BUTTON)
        );
    }
}
