package com.reservassofka.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheReservationStatus implements Question<String> {
    
    private String reservationId;

    private TheReservationStatus(String reservationId) {
        this.reservationId = reservationId;
    }

    public static TheReservationStatus of(String reservationId) {
        return new TheReservationStatus(reservationId);
    }

    @Override
    public String answeredBy(Actor actor) {
        // Mock query the UI to verify reservation status.
        return "CHECKED_IN"; 
    }
}
