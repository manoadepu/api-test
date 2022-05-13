
# api-test
This repo utilizes rest-assured, testNG and cucumber framework to test platform-science apis.

## **Steps to setup and execute the tests locally**

1. Install [JAVA](https://stackoverflow.com/questions/24342886/how-to-install-java-8-on-mac)
2. Install [Maven](https://maven.apache.org/install.html) 
3. Install Intellij or other JAVA IDE
4. Git clone https://github.com/manoadepu/api-test.git
5. Go to Project root in terminal (path: /api-test)
6. Execute ***mvn clean***
7. Execute ***mvn clean install***
8. Execute ***mvn test*** to trigger the tests from command line **or** right click on ***runners.TestRunner*** and and click on ***"Run 'Test Runner'"***

## **Directory Structure**

![alt text](DirectoryStructureExplaination.png)

***Note:*** All the classes and the methods have the comments that will help to understand the code 

## **Environment variables**
BASE_URI
- pass base uri depending on the environment.
- eg: BASE_URI=http://localhost:8080/v1

## ***Test Reporting***
This repo utilizes **Extent Reports** and generates a beautiful dashboard

**_Note:_** Anyone can view the [latest test report](https://manoadepu.github.io/api-test/Reports/index.html#) as it is integrated with github pages. 


## ***Assumptions made for the coding challenge*** ##
1. Robot works when the patches array is empty.
2. Robot continues to travel after all the patches are cleaned
3. When directions are not sent in the request the robot stays where it is
4. Message is “0” in response[ does not look correct]
5. Robot works and moves when patches to be cleaned are 0. I.e "patches" : [ [ ] ]
6. In case of a negative scenario - the error response body will be sent back with appropriate status, description and message
