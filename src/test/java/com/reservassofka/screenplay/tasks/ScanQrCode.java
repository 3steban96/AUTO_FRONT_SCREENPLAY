package com.reservassofka.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ScanQrCode implements Task {
    
    private String spaceName;
    private String token;
    
    public ScanQrCode(String spaceName, String token) {
        this.spaceName = spaceName;
        this.token = token;
    }
    
    public static ScanQrCode ofSpace(String spaceName) {
        return Tasks.instrumented(ScanQrCode.class, spaceName, "");
    }

    public static ScanQrCode withToken(String token) {
        return Tasks.instrumented(ScanQrCode.class, "", token);
    }

    public static ScanQrCode withCorruptedData(String corruptedData) {
        return Tasks.instrumented(ScanQrCode.class, "", corruptedData);
    }
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        // En una implementación real, aquí interactuaríamos con el modal usando Selenium/Playwright
        // para inyectar el código QR. Ya que es un skeleton mock para compilar:
        String data = (spaceName != null && !spaceName.isEmpty()) ? spaceName : token;
        actor.remember("latestScannedSpace", data);
    }
}
