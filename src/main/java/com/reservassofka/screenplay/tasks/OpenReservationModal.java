package com.reservassofka.screenplay.tasks;

import com.reservassofka.screenplay.userinterfaces.DashboardPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class OpenReservationModal implements Task {

    public static OpenReservationModal ofAvailableWorkspace() {
        return Tasks.instrumented(OpenReservationModal.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(DashboardPage.AVAILABLE_WORKSPACE_BOOK_BUTTON, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(DashboardPage.AVAILABLE_WORKSPACE_BOOK_BUTTON)
        );
    }
}
