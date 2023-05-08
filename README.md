<div align="center">
 <img  src="https://user-images.githubusercontent.com/68038931/236700538-b1d4b189-d4dd-4fbc-8e14-ec793acd35e2.gif" alt="test-light" width="1000" height="250" />
 
 
# ✨ Rest Assured Practice ✨
</div>

* 🌐 **[FakeRESTApi](https://fakerestapi.azurewebsites.net/index.html)**

* 🌐 **[RestfulBookerApi](https://restful-booker.herokuapp.com/apidoc/index.html)**


 ### 📝 The main Frameworks included in the project:

 * RestAssured
 
 * TestNG
 
 * Allure Report
 
 ### 🎨 Project Design:

 *  Object Model design pattern
 
 * Data Driven framework
 
 * Java Docs
 
 ### 🏗️ Project Structure 
 
 ![rest](https://user-images.githubusercontent.com/68038931/236828708-3e076917-b584-41e6-a8e5-a2ffdbf0be01.png)
 
#### 🔊 this project included

#### 📦️ 3 packages in src/main/java
 * FakeRestApi ( object Model Design )
 * Restfulbooker ( object Model Design )
 * Utils ( used to read data from external json file )
 
#### 📦️ 3 packages in src/test/java
 * FakeRestApiTest ( object Model Design )
 * RestfulbookerApiTest ( object Model Design )
 * Practice ( linear Design )
 
 #### 🧱 2 folders in src/test/resources/TestData
 * ActivitiesTestData
 * BookingTestData
 
#### ⚗️ 2 xml files
* pom.xml ( XML file that contains information about the project and configuration details used by Maven to build the project)
* testng.xml ( is the configuration for TestNG testing framework ( defining test suite )
 
 
 ### 🗃️ Documentation
* **[Rest-Assured Docs](https://rest-assured.io/)**
 
 ### 🚧 Requirements

* Java JDK-19 
* RestAssured 5.3.0
* Maven

 ### 🚀 Running Tests

### Run All tests open a terminal on the project root path:
1. Run API tests:
` mvn clean test` 
2. Generate reports:
`mvn allure:serve` or `allure serve`

-  ### 📄 Allure Report: 

![api](https://user-images.githubusercontent.com/68038931/236701114-2dae07df-cf93-408c-950c-4fbea78088cf.png)

