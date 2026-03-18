# Screenplay Automation Project - Reservas Sofka

Este proyecto contiene la automatización de pruebas para la aplicación **Reservas Sofka**, utilizando el patrón de diseño **Screenplay** con Serenity BDD, Cucumber y Gradle.

## 🚀 Comenzando

### Requisitos
- Java 17 o superior
- Gradle
- Navegador Chrome

### Ejecución de Pruebas
Para ejecutar todos los escenarios y generar el reporte de Serenity, utiliza el siguiente comando desde la carpeta `ScreenPlay`:

```powershell
gradle clean test aggregate
```

Los reportes detallados se generarán en la siguiente ruta:
`ScreenPlay/target/site/serenity/index.html`

## 🎭 Estructura Screenplay

El proyecto sigue los principios del patrón Screenplay, promoviendo la reutilización y el mantenimiento:

- **Actors**: El actor principal es `Employee`, quien tiene la capacidad de navegar y realizar reservas.
- **Tasks**: Acciones de alto nivel como `Login`, `OpenReservationModal` y `FillReservationForm`.
- **UI Targets**: Definiciones de selectores para `LoginPage`, `DashboardPage` y `ReservationModal`.
- **Questions**: Validaciones para verificar el estado exitoso o los mensajes de error/bloqueo.

## 🧪 Escenarios Automatizados

1. **Creación Exitosa de Reserva**: Verifica que un empleado puede reservar un espacio de trabajo disponible en una fecha futura.
2. **Validación de Horario Inválido (Flujo Negativo)**: Asegura que el sistema bloquea (deshabilita el botón de confirmación) cuando la hora de inicio es posterior a la hora de fin.

## ⚠️ Condiciones Requeridas para una Ejecución Exitosa
Para que los escenarios de prueba finalicen exitosamente sin errores, el entorno debe cumplir las siguientes condiciones:

1. **Disponibilidad de Datos (Libertad de Reservas)**: El escenario de éxito crea una reserva real en la base de datos usando el **Día 20** de las **10:00 a las 11:00**. Si este horario ya está ocupado por una ejecución anterior, la prueba fallará intencionalmente al detectar el conflicto. Para correr el test nuevamente, debes borrar las reservas previas desde la base de datos o cambiar el día/hora en el archivo `CreateReservationStepDefinitions.java`.
2. **Frontend en Ejecución**: El servidor local de React (Vite) debe estar levantado (`npm run dev`) para que responda a `http://localhost:5173`.
3. **Backend e Infraestructura**: Los servicios backend (APIs y bases de datos) deben estar funcionando, dado que el login y la carga del dashboard (`admin@sofka.com.co`) consultan los datos reales.
4. **Navegador Embebido (Chrome)**: Debe tener Google Chrome instalado. Se recomienda no utilizar activamente el ordenador durante la ejecución para evitar cierres o interrupciones de foco sobre la ventana del navegador.

---
> [!IMPORTANT]
> **Persistencia de Datos**: Dado que las pruebas interactúan con una aplicación real (o local en ejecución), las reservas creadas persisten. Si la prueba positiva falla por un "Conflicto", referirse al punto 1 de las condiciones requeridas.
