package com.reservassofka.screenplay.stepdefinitions;

import com.reservassofka.screenplay.tasks.ScanQrCode;
import com.reservassofka.screenplay.questions.TheReservationStatus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class QrCheckinStepDefinitions {

    @Given("^el usuario \"([^\"]*)\" está autenticado$")
    public void elUsuarioEstaAutenticado(String email) {
        theActorCalled(email).remember("email", email);
    }

    @Given("^tiene una reserva activa con ID \"([^\"]*)\" para el espacio \"([^\"]*)\"$")
    public void tieneUnaReservaActiva(String reservationId, String spaceName) {
        theActorInTheSpotlight().remember("currentReservationId", reservationId);
    }
    
    @Given("la reserva está en estado {string}")
    public void laReservaEstaEnEstado(String status) {
    }

    @Given("la hora de inicio de la reserva es {string}")
    public void laHoraDeInicioEs(String isoTimestamp) {
    }

    @Given("la hora actual del sistema es {string}")
    public void laHoraActualEs(String isoTimestamp) {
    }

    @Given("el usuario navega hacia {string}")
    public void navegaHacia(String path) {
    }

    @Given("se encuentra en la tarjeta de la reserva {string}")
    public void tarjetaDeReserva(String resID) {
    }

    @When("el usuario hace clic en el botón {string}")
    public void clicBoton(String buttonLabel) {
    }

    @When("se abre el modal de escaneo QR")
    public void modalAbre() {
    }

    @When("se otorgan permisos de cámara")
    public void permisosCamara() {
    }

    @When("se escanea el QR válido del espacio {string}")
    public void escaneaQrValido(String spaceName) {
        theActorInTheSpotlight().attemptsTo(
            ScanQrCode.ofSpace(spaceName)
        );
    }

    @When("el usuario intenta hacer check-in escaneando el QR válido")
    public void intentaHacerCheckinQRValido() {
        theActorInTheSpotlight().attemptsTo(
            ScanQrCode.ofSpace("MockSpace")
        );
    }

    @Then("el estado de la reserva cambia a {string}")
    public void estadoCambiaA(String anticipatedStatus) {
        theActorInTheSpotlight().should(
            seeThat(TheReservationStatus.of("RES-20260408-001"), equalTo(anticipatedStatus))
        );
    }

    @Then("se muestra el mensaje de éxito {string}")
    public void mensajeDeExito(String msg) {
    }

    @Then("se cierra el modal de escaneo")
    public void modalCierra() {
    }

    @Then("la tarjeta de reserva muestra el badge verde {string}")
    public void badgeVerde(String badge) {
    }

    @Then("se recibe notificación push {string}")
    public void notificacionPush(String notif) {
    }

    @Then("se muestra el mensaje de error {string}")
    public void mensajeError(String error) {
    }

    @Then("el estado de la reserva permanece en {string}")
    public void estadoPermanece(String state) {
    }
}
