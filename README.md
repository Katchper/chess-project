## ATTENTION
THIS IS A REUPLOAD OF THE ORIGINAL CHESS PROJECT REPOSITORY. THE OLD REPOSITORY IS NO LONGER AVAILABLE.

## Name
CS22120 Group Project Group 18

## Description
This is the repository for Group 18's CS22120 Group Project.

# Setting up the Project:

This project is built with JavaFX, a popular Java library for building rich client applications.
### Prerequisites:
Before setting up the project, make sure you have the following installed:

•[Java Development Kit (JDK) USE 21](https://www.oracle.com/java/technologies/downloads/)

•[IntelliJ IDEA](https://www.jetbrains.com/idea/download/) (or any other Java IDE)

### Clone the Project
To clone the project, follow these steps:

1. Open Terminal.
2. Navigate to the directory where you want to store the project.
3. Run the following command to clone the project:

`git clone {URL}`

### Import the Project:
To import the project in IntelliJ IDEA, follow these steps:

1. Open IntelliJ IDEA.
2. From the welcome screen, click "Import Project".
3. Navigate to the directory where you cloned the project, and select the project folder.
4. Click "OK" to import the project.

### Set up the JDK:
To set up the JDK in IntelliJ IDEA, follow these steps:

1. Open the project in IntelliJ IDEA.
2. From the main menu, select "File" -> "Project Structure".
3. In the "Project Settings" section, select "Project".
4. From the "Project SDK" dropdown, select the JDK you installed.
5. Click "Apply" and then "OK".

### Download and Import JavaFX 20.0.1 Library
There is a Javafx package available within the resources folder however if it is missing or not working follow the steps below.

To download and import JavaFX 20.0.1 Library in IntelliJ IDEA, follow these steps:

1. Go to the [OpenJFX website](https://gluonhq.com/products/javafx/) and download the JavaFX SDK for your operating system.
2. Extract the downloaded file to a directory on your machine.
3. In IntelliJ IDEA, from the main menu, select "File" -> "Project Structure".
4. In the "Global Libraries" section, click the "+" icon to add a new library.
5. Select "Java" from the dropdown, and then select the directory where you extracted the JavaFX SDK.
6. Select the lib folder within the extracted file and click "OK" to add the library.

### Add VM Options in Configuration:
To set up the project with JavaFX in IntelliJ IDEA, follow these steps:

1. Open the project in IntelliJ IDEA.
2. From the main menu, select "Run" -> "Edit Configurations".
3. Select the application configuration you want to modify.
4. In the "VM options" field, add the following line:

`--module-path resources/javafx-sdk-20.0.1/lib --add-modules javafx.controls,javafx.fxml`

5.Click "Apply" and then "OK".


## Conclusion:
You should now have the project set up on your Machine. You can run the project by selecting the application configuration you modified and clicking "Run". If you encounter any issues during the setup process, feel free to refer to the official documentation for [JavaFX](https://openjfx.io/) and [IntelliJ IDEA](IntelliJ IDEA).

