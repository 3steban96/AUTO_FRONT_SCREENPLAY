Feature: Marcado Automático NO_SHOW por Inasistencia
  Como administrador de espacios
  Quiero que el sistema libere automáticamente reservas sin check-in
  Para maximizar la disponibilidad de salas y equipos

  Background:
    Given el job "ReservationMonitorJob" está configurado para ejecutarse cada 1 minuto
    And el grace period está configurado en 5 minutos

  @critical @automation
  Scenario: Reservas sin check-in son marcadas como NO_SHOW tras 5 minutos
    Given existen las siguientes reservas en estado PENDING:
      | ReservationID      | Usuario            | Espacio    | Inicio           | CheckIn |
      | RES-20260408-001  | anderson.rodriguez | Sala Zeus  | 10:09:00Z        | No      |
      | RES-20260408-002  | juan.perez         | Sala Apolo | 10:14:00Z        | No      |
      | RES-20260408-003  | maria.garcia       | Proyector1 | 10:19:00Z        | Sí      |
    And la hora actual del sistema es "2026-04-08T10:15:00Z"
    When el job ReservationMonitorJob se ejecuta automáticamente
    Then la reserva "RES-20260408-001" cambia a estado "NO_SHOW"
    And la reserva "RES-20260408-002" permanece en "PENDING"
    And la reserva "RES-20260408-003" permanece en "CHECKED_IN"
    And el espacio "Sala Zeus" queda disponible para nuevas reservas
    And el usuario "anderson.rodriguez" recibe notificación:
      """
      Tu reserva de Sala Zeus fue cancelada por inasistencia (No-Show).
      El espacio ahora está disponible para otros colaboradores.
      """

  @automation @idempotence
  Scenario: El job es idempotente (no duplica actualizaciones)
    Given existe una reserva "RES-20260408-004" en estado PENDING desde "10:09:00Z"
    And la hora actual es "2026-04-08T10:17:00Z"
    And el job ya ejecutó y marcó la reserva como NO_SHOW en la iteración anterior
    When el job se ejecuta nuevamente (segunda iteración)
    Then la reserva permanece en "NO_SHOW" (sin cambios)
    And NO se envía notificación duplicada al usuario
    And se registra en logs: "Skip: reservation already processed as NO_SHOW"

  @automation @batch-performance
  Scenario: Procesamiento eficiente de múltiples reservas expiradas
    Given existen 50 reservas en estado PENDING con inicio hace 6+ minutos
    When el job se ejecuta
    Then las 50 reservas se actualizan a NO_SHOW en una sola transacción batch
    And el tiempo de ejecución del job es menor a 2 segundos
    And se registra en logs: "Processed 50 expired reservations in batch"

  @automation @edge-case
  Scenario: Reserva recibe check-in justo cuando el job se está ejecutando (race condition)
    Given una reserva "RES-20260408-005" en PENDING desde "10:09:00Z"
    And la hora actual es "2026-04-08T10:14:30Z"
    When el job inicia lectura de reservas expiradas
    And simultáneamente el usuario hace check-in exitoso
    Then el job NO debe sobrescribir el estado CHECKED_IN con NO_SHOW
    And la reserva final queda en "CHECKED_IN" (check-in gana)
