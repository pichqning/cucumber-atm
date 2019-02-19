Feature: overdrawn
  As a customer
  I can withdraw money even my account balance is 0.

Background:
  Given a customer with id 1 and pin 111 with balance 200, OD account status with negotiated amount 1000
  And I login to ATM with id 1 and pin 111

Scenario: Overdraw amount less than negotiated amount
  When I withdraw 200 from ATM
  Then my account balance is 0
  Then I can overdraw
  Then I overdraw 500 from ATM, id 1
  Then my account negotiated amount is 500

Scenario: Overdraw amount more than negotiated amount
  When I withdraw 200 from ATM
  Then my account balance is 0
  Then I can overdraw
  Then I overdraw 5000 from ATM, id 1
  Then my account negotiated amount is 1000


