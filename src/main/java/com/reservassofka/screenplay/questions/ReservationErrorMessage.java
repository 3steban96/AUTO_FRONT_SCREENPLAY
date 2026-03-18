package com.reservassofka.screenplay.questions;

import com.reservassofka.screenplay.userinterfaces.ReservationModal;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ReservationErrorMessage implements Question<String> {

    public static ReservationErrorMessage text() {
        return new ReservationErrorMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(ReservationModal.ERROR_MESSAGE, isVisible()).forNoMoreThan(5).seconds()
        );
        return Text.of(ReservationModal.ERROR_MESSAGE).answeredBy(actor);
    }
}
