package com.reservassofka.screenplay.tasks;

import com.reservassofka.screenplay.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Login implements Task {
    private final String email;
    private final String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static Login withCredentials(String email, String password) {
        return Tasks.instrumented(Login.class, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url("http://localhost:5173"),
                WaitUntil.the(LoginPage.EMAIL_INPUT, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(email).into(LoginPage.EMAIL_INPUT),
                Enter.theValue(password).into(LoginPage.PASSWORD_INPUT),
                Click.on(LoginPage.LOGIN_BUTTON),
                WaitUntil.the(com.reservassofka.screenplay.userinterfaces.DashboardPage.AVAILABLE_WORKSPACE_BOOK_BUTTON, isVisible()).forNoMoreThan(20).seconds()
        );
    }
}
