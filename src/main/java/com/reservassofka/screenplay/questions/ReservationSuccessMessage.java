package com.reservassofka.screenplay.questions;

import com.reservassofka.screenplay.userinterfaces.ReservationModal;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ReservationSuccessMessage implements Question<String> {

    public static ReservationSuccessMessage text() {
        return new ReservationSuccessMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(ReservationModal.SUCCESS_MESSAGE, isVisible()).forNoMoreThan(20).seconds()
        );
        return Text.of(ReservationModal.SUCCESS_MESSAGE).answeredBy(actor);
    }
}
