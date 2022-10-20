Feature: Register Nutritionist Functionality


  Scenario Outline: As a nutritionist i want to register in nutrix.
    Given I want to register as nutritionist
    And I enter my own information like name <name>, dni <dni>, email <email>, password <password>, phone <phone>, specialization <specialization>, formation <formation>, about <about>, genre <genre>, sessionType <session> and code <cmp>
    Then I should be able to see my newly nutritionist account

    Examples:
      | name   | dni         | email           | password      | phone       | specialization | formation              | about               | genre           | session    | cmp     |
      | "Luis" | "76139991"  |"fano@gmail.com" | "12345679"    | "987425689" | "Ansiedad"     | "Universitaria"        | "Soy una prueba"    |  "Masculino"    | "Virtual"  | "1235"  |