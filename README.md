
# zNWatch 📈

**zNWatch** is a Java-based console application designed to simulate stock market operations. This application provides a range of features for managing and manipulating dummy stock data, including real-time stock value changes, JDBC integration for database operations, file input/output for data persistence, and serialization to preserve application state between sessions.

## Features

- **Automatic Stock Value Changes**: Simulates real-time stock value changes to mimic market behavior. 📉📈
- **JDBC Integration**: Connects to relational databases (e.g., MySQL, PostgreSQL) to manage and retrieve stock data. 💾
- **File I/O**: Facilitates reading from and writing to files for data persistence. 📂
- **Serialization**: Supports serialization and deserialization of stock data to maintain state between different runs of the application. 🔄

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

   Make sure to adjust the paths according to your project structure. 🛠️

3. **Run the Application**: Execute the following command to run the application:

   ```bash
   java -cp bin com.yourpackage.MainClass
   ```

   Replace `com.yourpackage.MainClass` with the fully qualified name of your main class. 🎬

### Example Usage

1. **Start the Application**: Launch the application to begin simulating stock market operations. 🚀

2. **Interact with the Console**: Follow the prompts in the console to manipulate stock data, view stock prices, and perform other operations. 🖥️

## Screenshots

Here are some screenshots to help you get familiar with the application:

1. **Database Management**:

   ![Database Management](https://github.com/user-attachments/assets/62ab7661-9202-48b2-95c2-50dc3c10cc54)

## Contact 📬

For any questions or support, please reach out to:

- **Email**: connect_yuvraj@outlook.com