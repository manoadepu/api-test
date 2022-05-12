
# api-test
This repo utilizes rest-assured, junit5 and cucumber framework to test platform-science apis.

## **Steps to setup and execute the tests locally:***

***Language:*** Java
***TestFrameworks:*** junit, cucumber
1. Install [JAVA](https://stackoverflow.com/questions/24342886/how-to-install-java-8-on-mac)
2. Install [Maven](https://maven.apache.org/install.html) 
3. Install Intellij or other JAVA IDE
4. Go to Project root in terminal (path: /api-test)
5. Execute ***mvn clean***
6. Execute ***mvn clean install***
7. Execute ***mvn test*** to trigger the tests from command line **or** right click on ***runners.TestRunner*** and and click on ***"Run 'Test Runner'"***

***Note:*** All the classes and the methods have the comments that will help to understand the code 

## ***Test Reporting***

The HTML test report will be generated after all the tests execute and be placed inside ***target/cucumber-html-report/index.html***
