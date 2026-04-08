package com.reservassofka.screenplay.stepdefinitions;

import com.reservassofka.screenplay.tasks.ScanQrCode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class QrErrorHandlingStepDefinitions {

    @Given("tiene una reserva para {string} \\(ID: {string})")
    public void reservaParaID(String spaceName, String id) {
    }

    @Given("la reserva es para el espacio con ID {int} \\({string}\\)")
    public void laReservaEsParaEspacio(int spaceId, String spaceName) {
    }
    
    @When("el usuario intenta hacer check-in")
    public void elUsuarioIntentaHacerCheckIn() {
    }

    @When("escanea un QR con token adulterado {string}")
    public void escaneaQrAdulterado(String invalidToken) {
        theActorInTheSpotlight().attemptsTo(
            ScanQrCode.withToken(invalidToken)
        );
    }
    
    @When("el usuario escanea el QR válido del espacio con ID {int} \\({string}\\)")
    public void escaneaQrDeEspacioDiferente(int wrongSpaceId, String wrongSpaceName) {
        theActorInTheSpotlight().attemptsTo(
            ScanQrCode.ofSpace(wrongSpaceName)
        );
    }
    
    @When("el usuario intenta escanear un QR {word}")
    public void escaneaQrProblematico(String tipoQr) {
        theActorInTheSpotlight().attemptsTo(
            ScanQrCode.withCorruptedData(tipoQr)
        );
    }
    
    @When("el usuario escanea {int} QR inválidos consecutivamente")
    public void escanearMultiplesQrInvalidos(int cantidad) {
    }

    @Then("se registra el intento fallido en los logs de auditoría")
    public void verificarLogDeAuditoria() {
    }
    
    @Then("se sugiere escanear el QR del espacio correcto")
    public void verificarSugerencia() {
    }
    
    @Then("no se envía request al backend")
    public void verificarNoRequestBackend() {
    }
    
    @Then("se muestra el error acumulado {string}")
    public void verificarErrorAcumulado(String expectedError) {
    }

    @Then("se muestra el error {string}")
    public void seMuestraError(String expectedError) {
    }

    @Then("el estado permanece en {string}")
    public void elEstadoPermanece(String expectedStatus) {
    }

    @Then("se sugiere contactar soporte si el problema persiste")
    public void verificarSugerenciaSoporte() {
    }
}
