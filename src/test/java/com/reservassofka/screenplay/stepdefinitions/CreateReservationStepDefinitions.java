package com.reservassofka.screenplay.stepdefinitions;

import com.reservassofka.screenplay.questions.ReservationErrorMessage;
import com.reservassofka.screenplay.questions.ReservationSuccessMessage;
import com.reservassofka.screenplay.tasks.FillReservationForm;
import com.reservassofka.screenplay.tasks.Login;
import com.reservassofka.screenplay.tasks.OpenReservationModal;
import com.reservassofka.screenplay.userinterfaces.ReservationModal;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import net.serenitybdd.screenplay.questions.WebElementQuestion;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotEnabled;
import static org.hamcrest.Matchers.containsString;

public class CreateReservationStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("the employee is logged in and is on the Dashboard")
    public void theEmployeeIsLoggedInAndIsOnTheDashboard() {
        theActorCalled("Employee").attemptsTo(
                Login.withCredentials("admin@sofka.com.co", "password123")
        );
    }

    @When("they select an available workspace to reserve")
    public void theySelectAnAvailableWorkspaceToReserve() {
        theActorInTheSpotlight().attemptsTo(
                OpenReservationModal.ofAvailableWorkspace()
        );
    }

    @When("they fill the reservation form with valid details for today")
    public void theyFillTheReservationFormWithValidDetailsForToday() {
        // Using Day 20 as Day 19 might be full from previous test runs.
        String day = "20";
        String startTime = "10:00"; 
        String endTime = "11:00";
        theActorInTheSpotlight().attemptsTo(
                FillReservationForm.withDetails(day, startTime, endTime)
        );
    }

    @Then("the system should confirm the reservation was created successfully")
    public void theSystemShouldConfirmTheReservationWasCreatedSuccessfully() {
        theActorInTheSpotlight().should(
                seeThat(ReservationSuccessMessage.text(), containsString("exitosa"))
        );
    }

    @When("they attempt to create a reservation with the start time after the end time")
    public void theyAttemptToCreateAReservationWithTheStartTimeAfterTheEndTime() {
        // Invalid times: Start time is greater than end time
        String day = "19";
        String startTime = "14:00"; 
        String endTime = "13:00";
        theActorInTheSpotlight().attemptsTo(
                FillReservationForm.withDetails(day, startTime, endTime)
        );
    }

    @Then("the system should display an error message indicating invalid times")
    public void theSystemShouldDisplayAnErrorMessageIndicatingInvalidTimes() {
        theActorInTheSpotlight().should(
                seeThat(WebElementQuestion.the(ReservationModal.CONFIRM_BUTTON), isNotEnabled())
        );
    }
}
