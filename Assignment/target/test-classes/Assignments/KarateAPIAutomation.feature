Feature: API Testing with Karate

Background:
  * url 'https://restful-booker.herokuapp.com'

Scenario: Perform GET Request
  Given path '/booking'
  When method GET
  Then status 200

Scenario: Perform POST Request
  Given path '/booking'
  And request { "firstname": "John", "lastname": "Doe", "totalprice": 100, "depositpaid": true, "bookingdates": { "checkin": "2024-04-01", "checkout": "2024-04-05" }, "additionalneeds": "Breakfast" }
  When method POST
  Then status 200

Scenario: Perform PUT Request
  Given path '/booking/<bookingId>'
  And request { "firstname": "Jane", "lastname": "Doe", "totalprice": 120, "depositpaid": true, "bookingdates": { "checkin": "2024-04-02", "checkout": "2024-04-06" }, "additionalneeds": "Lunch" }
  When method PUT
  Then status 200

Scenario: Perform PATCH Request
  Given path '/booking/<bookingId>'
  And request { "firstname": "UpdatedFirstName" }
  When method PATCH
  Then status 200

Scenario: Perform DELETE Request
  Given path '/booking/<bookingId>'
  When method DELETE
  Then status 201