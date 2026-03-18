Feature: Create a new reservation
  As a Sofka employee
  I want to create a reservation from the dashboard
  So that I can use a workspace or equipment when needed

  Background:
    Given the employee is logged in and is on the Dashboard

  @positive @create_reservation
  Scenario: Successfully create a new reservation for a workspace
    When they select an available workspace to reserve
    And they fill the reservation form with valid details for today
    Then the system should confirm the reservation was created successfully

  @negative @create_reservation
  Scenario: Error when creating a reservation with an invalid time range
    When they select an available workspace to reserve
    And they attempt to create a reservation with the start time after the end time
    Then the system should display an error message indicating invalid times
