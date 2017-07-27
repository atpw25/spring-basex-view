# Spring BaseX View

An experiment with using XQuery for view rendering in [Spring Boot](http://projects.spring.io/spring-boot/) web application. The XQuery processor is provided by [BaseX](http://basex.org/) running in embedded mode.

## Dependencies

> With the exception of Java 8, the dependencies are handled by the Gradle wrapper in the project.

* [Java 8](http://www.oracle.com/technetwork/java/index.html)
* [Gradle](https://gradle.org/) 4.0.2 (for build)
* [Spring Boot](http://projects.spring.io/spring-boot/) 1.5.5
* [BaseX](http://basex.org/) 8.6.4
* [Saxon](http://www.saxonica.com/) 9.8.0

## Running the Application

### Defaults

* **Port**: `8080`
* **View files location**: `{APPLICATION FOLDER}/view/`
* **URL paths**: URL paths are relative to the application folder (although not fully tested)

### Run the Project In-Place

You can execute `./gradlew bootRun` to run the application in-place.

### Building the Project

Execute `./gradlew build` to build the project. The resulting JAR file will be located at `build/libs/spring-basex-view-xxx.jar`.

### Required Resources for Distribution

> These are relative to application folder or wherever the application is started from):

|Name|Description|
|---|---|
|`.basex`|This is BaseX configuration file|
|`spring-basex-view-xxx.jar`|The application JAR|
|`views`|The application will look for XQuery files in this folder. Note that the XQuery file must have `.xql` extension.|
|`xqmodules`|This folder is for storing XQuery modules. XQuery modules located in this folder will automatically be available to XQuery running in BaseX XQuery processor. These modules can be imported by namespace without specifying the physical path of the module's file.|
|`BaseXData`|This folder will be automatically created by BaseX when it's database feature is used (e.g. storing XML).|

### Running the Distribution

Once all the necessary files are available, execute `java -jar spring-basex-view-xxx.jar` to start the application.