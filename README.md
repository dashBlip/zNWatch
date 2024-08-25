You can add the link to your Doxygen-generated documentation by updating the "Getting Started" section or any relevant section in your README file. Here’s how you might incorporate it into your existing documentation:

---

# zNWatch 📈
[Documentation](https://dashblip.github.io/zNWatch/) 📚

**zNWatch** is a Java-based console application designed to simulate stock market operations. This application provides a range of features for managing and manipulating dummy stock data, including real-time stock value changes, JDBC integration for database operations, file input/output for data persistence, serialization to preserve application state between sessions, and a new admin feature for enhanced control.

## Features

- **Automatic Stock Value Changes**: Simulates real-time stock value changes to mimic market behavior. 📉📈
- **JDBC Integration**: Connects to relational databases (e.g., MySQL, PostgreSQL) to manage and retrieve stock data. 💾
- **File I/O**: Facilitates reading from and writing to files for data persistence. 📂
- **Serialization**: Supports serialization and deserialization of stock data to maintain state between different runs of the application. 🔄
- **Admin Feature**: Allows administrators to perform advanced operations, such as managing user roles and accessing detailed logs. 👨‍💼

## Getting Started 🚀

To get started with zNWatch, follow these instructions:

### Prerequisites

1. **Java JDK 11 or Later**: Ensure you have Java Development Kit (JDK) version 11 or later installed on your machine. You can download it from:
   - [Oracle JDK Downloads](https://www.oracle.com/java/technologies/javase-downloads.html) ☕
   - [OpenJDK](https://openjdk.java.net/) ☕

2. **Database Setup**:
   - Set up a relational database such as MySQL or PostgreSQL.
   - Use the `StockManagement.sql` file included in this repository to create and configure your database schema. This SQL script sets up the necessary tables and triggers for `zNWatch`.

     To execute the SQL script:
      1. **Open your database client** (e.g., phpMyAdmin, MySQL Workbench).
      2. **Create a new database** named `StockManagement`.
      3. **Import the `StockManagement.sql` file** into the newly created database. This can usually be done through the client’s import feature. 🗂️

     Example command line execution for MySQL:

     ```bash
     mysql -u your_username -p StockManagement < path/to/StockManagement.sql
     ```

     Replace `your_username` with your MySQL username and `path/to/StockManagement.sql` with the path to your SQL file. 📑

3. **Configure Database Connection**: Update the `database.properties` file with your database connection details:

   ```properties
   jdbc.url=jdbc:mysql://localhost:3306/StockManagement
   jdbc.username=your_username
   jdbc.password=your_password
   ```

### Cloning the Repository

Clone this repository to your local machine using the following command:

```bash
git clone https://github.com/dashBlip/zNWatch.git
```

### Building and Running the Project

1. Navigate to the project directory:

   ```bash
   cd zNWatch
   ```

2. **Compile the Project**: Compile the project using `javac`. For example:

   ```bash
   javac -d bin src/**/*.java
   ```

3. **Run the Application**: Execute the following command to run the application:

   ```bash
   java -cp bin StockManagementSystem.Runner
   ```

### Example Usage

1. **Start the Application**: Launch the application to begin simulating stock market operations. 🚀

2. **Interact with the Console**: Follow the prompts in the console to manipulate stock data, view stock prices, perform admin tasks, and carry out other operations. 🖥️

### Documentation

For detailed documentation of the `zNWatch` application, including API details and code structure, visit our [Documentation](https://dashblip.github.io/zNWatch/). 📚

## Screenshots

Here are some screenshots to help you get familiar with the application:

### Database Management

The database management interface provides a view of the database schema and allows for interactions with stock data.

![Database Structure](https://github.com/user-attachments/assets/0f184f50-c299-4443-adce-624bc2c13a94)

### Class Diagram

The class diagram illustrates the main components of the zNWatch application and their relationships.

![Class_Diagram](https://github.com/user-attachments/assets/f6815c50-0071-4f26-b27d-6690f54a2682)

### Use Case Diagram

The use case diagram depicts the interactions between different user roles and the system functionalities.

![Use Case Diagram](https://github.com/user-attachments/assets/d1184adb-dd51-4839-a946-7c19bde99022)

## Contact 📬

For any questions or support, please reach out to:

- **Email**: connect_yuvraj@outlook.com

---