Feature: Validación de QR - Manejo de Errores
  Como sistema de reservas
  Quiero rechazar códigos QR inválidos o incorrectos
  Para evitar check-ins fraudulentos o erróneos

  Background:
    Given el usuario "juan.perez@sofka.com.co" está autenticado
    And tiene una reserva para "Sala Zeus" (ID: "RES-20260408-002")
    And la reserva está en estado "PENDING"
    And la hora actual es "2026-04-08T10:11:00Z"

  @critical @security
  Scenario: QR con firma JWT inválida (token adulterado)
    When el usuario intenta hacer check-in 
    And escanea un QR con token adulterado "eyJhbGciOiJIUzI1NiJ9.FAKE.SIGNATURE"
    Then se muestra el error "QR inválido o no corresponde a tu reserva actual"
    And el estado de la reserva permanece en "PENDING"
    And se registra el intento fallido en los logs de auditoría

  @critical @negative
  Scenario: QR de un espacio diferente (Space ID mismatch)
    Given la reserva es para el espacio con ID 1 ("Sala Zeus")
    When el usuario escanea el QR válido del espacio con ID 2 ("Sala Apolo")
    Then se muestra el error "Este QR no corresponde a tu reserva actual"
    And el estado permanece en "PENDING"
    And se sugiere escanear el QR del espacio correcto

  @negative
  Scenario Outline: QR ilegible o corrupto
    When el usuario intenta escanear un QR <tipo_qr>
    Then se muestra el error "<mensaje_error>"
    And no se envía request al backend
    
    Examples:
      | tipo_qr           | mensaje_error                          |
      | corrupto          | No se pudo leer el código QR           |
      | con formato erróneo | El código escaneado no es válido      |
      | vacío             | No se detectó ningún código QR         |

  @negative @timing
  Scenario: Múltiples intentos fallidos de escaneo
    When el usuario escanea 3 QR inválidos consecutivamente
    Then se muestra el error acumulado "Múltiples intentos fallidos. Verifica que estés escaneando el QR correcto de tu espacio reservado"
    And se sugiere contactar soporte si el problema persiste
