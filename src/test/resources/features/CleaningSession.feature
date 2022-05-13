#This is a feature file that contains all the scenarios to test /cleaning-sessions api
#Sample Request for vi /cleaning-sessions
#            {
#            "roomSize" : [0, 0],
#            "coords" : [0,0],
#            "patches" : [
#            [0, 0]
#            ],
#            "instructions" : "NNESEESWNWW"
#            }
# Pass the coordinates as a string and comma seperated eg: "5,5" i.e equivalant to [5,5]
# To pass multiple patches to be cleaned eg: "1,0&2,2&2,3" i.e equivalant to [[1,0],[2,2],[2,3]]
# Currently all the scenarios are tagged to @Regression. We can assign multiple tags if needed.

Feature: Cleaning sessions API test scenario

##### scenario to test Happy Path Scenario################################################
  @Regression
  Scenario: Validating cleaning sessions happy path response
    Given RoomSize is "5,5"
    And Coords are "1,2"
    And Patches are "1,0&2,2&2,3"
    And Moving instructions are "NNESEESWNWW"
    When Make a cleaning-sessions POST call
    Then validate that the reponse body has coords "1,3" and patches cleaned are "2"

  @Regression
  Scenario: Validating cleaning sessions happy path response
    Given RoomSize is "5,5"
    And Coords are "1,2"
    And Patches are "1,0&2,2&2,3"
    And Moving instructions are "NNESEESWNWW"
    When Make a cleaning-sessions POST call
    Then validate that the reponse body has coords "1,3" and patches cleaned are "2"

  @Regression
  Scenario: Validating cleaning sessions happy path response
    Given RoomSize is "4,5"
    And Coords are "2,3"
    And Patches are "3,4"
    And Moving instructions are "NNEE"
    When Make a cleaning-sessions POST call
    Then validate that the reponse body has coords "1,3" and patches cleaned are "2"

############################################################################################

##### Scenarios to test Inplace functionality when robot hits the wall #####################
  @Regression
  Scenario: Validating when robot hits the EAST wall and remains inplace
    Given RoomSize is "5,5"
    And Coords are "2,2"
    And Patches are "3,2&4,2&2,3"
    And Moving instructions are "EEE"
    When Make a cleaning-sessions POST call
    Then validate that the reponse body has coords "4,2" and patches cleaned are "2"

  @Regression
  Scenario: Validating when robot hits the WEST wall and remains inplace
    Given RoomSize is "5,5"
    And Coords are "2,2"
    And Patches are "3,2&4,2&2,3"
    And Moving instructions are "WWWWW"
    When Make a cleaning-sessions POST call
    Then validate that the reponse body has coords "0,2" and patches cleaned are "0"

  @Regression
  Scenario: Validating when robot hits the SOUTH wall and remains inplace
    Given RoomSize is "5,5"
    And Coords are "2,2"
    And Patches are "3,2&4,2&2,3"
    And Moving instructions are "SSSSS"
    When Make a cleaning-sessions POST call
    Then validate that the reponse body has coords "2,0" and patches cleaned are "0"

  @Regression
  Scenario: Validating when robot hits the NORTH wall and remains inplace
    Given RoomSize is "5,5"
    And Coords are "2,2"
    And Patches are "3,2&4,2&2,3"
    And Moving instructions are "NNNNN"
    When Make a cleaning-sessions POST call
    Then validate that the reponse body has coords "2,4" and patches cleaned are "0"

  @Regression
  Scenario: Validating when robot hits the wall and remains inplace with no patches to clean
    Given RoomSize is "5,5"
    And Coords are "3,2"
    And Patches are "0,0"
    And Moving instructions are "SSSSSNEWW"
    When Make a cleaning-sessions POST call
    Then validate that the reponse body has coords "2,1" and patches cleaned are "0"
#################################################################################################

####### Validating the error response ###########################################################
  @Regression
  Scenario: Validating the error response when room size is empty
    Given RoomSize is ""
    And Coords are "3,2"
    And Patches are "0,0"
    And Moving instructions are "SSSSSNEWW"
    When Make a cleaning-sessions POST call
    Then Validate that the reponse body returns status "400" and message "Roomsize incorrect"

  @Regression
  Scenario: Validating the error response when coords are empty
    Given RoomSize is "5,5"
    And Coords are ""
    And Patches are "0,0"
    And Moving instructions are "SSSSSNEWW"
    When Make a cleaning-sessions POST call
    Then Validate that the reponse body returns status "400" and message "Coords incorrect"

  @Regression
  Scenario: Validating the error response when patches are empty
    Given RoomSize is "5,5"
    And Coords are "3,2"
    And Patches are ""
    And Moving instructions are "SSSSSNEWW"
    When Make a cleaning-sessions POST call
    Then Validate that the reponse body returns status "400" and message "Patches incorrect"

  @Regression
  Scenario: Validating the error response when instructions are empty
    Given RoomSize is "5,5"
    And Coords are "2,0"
    And Patches are "1,0&2,2&2,3"
    And Moving instructions are ""
    When Make a cleaning-sessions POST call
    Then validate that the reponse body has coords "2,0" and patches cleaned are "0"

  @Regression
  Scenario: Validating the error response when instructions are sent in lowercase
    Given RoomSize is "5,5"
    And Coords are "2,0"
    And Patches are "1,0&2,2&2,3"
    And Moving instructions are "nnnwwsss"
    When Make a cleaning-sessions POST call
    Then Validate that the reponse body returns status "400" and message "TO_BE_DETERMINED_FORM_THE_SPEC"

  @Regression
  Scenario: Validating the error response when instructions are sent in uppercase+lowercase
    Given RoomSize is "5,5"
    And Coords are "2,0"
    And Patches are "1,0&2,2&2,3"
    And Moving instructions are "NNnWwSs"
    When Make a cleaning-sessions POST call
    Then Validate that the reponse body returns status "400" and message "TO_BE_DETERMINED_FORM_THE_SPEC"

  @Regression
  Scenario: Validating that the api responds with appropriate error message when start location is greater than room size
    Given RoomSize is "5,5"
    And Coords are "100,100"
    And Patches are "0,0"
    And Moving instructions are "SSSSSNEWW"
    When Make a cleaning-sessions POST call
    Then Validate that the reponse body returns status "400" and message "0"

  @Regression
  Scenario: Validating that the api responds with appropriate error message when patch location is greater than room size
    Given RoomSize is "5,5"
    And Coords are "100,100"
    And Patches are "176,203&29,21"
    And Moving instructions are "NWSWSEEEN"
    When Make a cleaning-sessions POST call
    Then Validate that the reponse body returns status "400" and message "0"

  @Regression
  Scenario: Validating that the api responds with appropriate error message when one patch location is greater than room size
    Given RoomSize is "4,5"
    And Coords are "2,3"
    And Patches are "3,4&3,5"
    And Moving instructions are "NWSWSEEEN"
    When Make a cleaning-sessions POST call
    Then Validate that the reponse body returns status "400" and message "0"

  @Regression
  Scenario: Validating that the api responds with appropriate error message when room size is 0
    Given RoomSize is "0,0"
    And Coords are "0,0"
    And Patches are "0,0"
    And Moving instructions are "NWSWSEEEN"
    When Make a cleaning-sessions POST call
    Then Validate that the reponse body returns status "400" and message "0"

  #Demonstration purpose test
  @Regression
  Scenario: Validating that the api responds with appropriate error message when room size is not sent
    Given RoomSize is "null"
    And Coords are "0,0"
    And Patches are "0,0"
    And Moving instructions are "NWSWSEEEN"
    When Make a cleaning-sessions POST call
    Then Validate that the reponse body returns status "400" and message "0"