<?php
// Database credentials
$servername = "localhost"; // or your server name
$username = "root"; // or your database username
$password = ""; // your database password
$dbname = "EmployeeDB"; // database name

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Check if form is submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Get the form data
    $empID = $_POST['empid'];
    $newSalary = $_POST['new_salary'];

    // Prepare and bind
    $stmt = $conn->prepare("UPDATE EMPDETAILS SET SALARY = ? WHERE EMPID = ?");
    $stmt->bind_param("di", $newSalary, $empID);

    // Execute the statement
    if ($stmt->execute()) {
        echo "Salary updated successfully for EmpID: " . $empID;
    } else {
        echo "Error updating salary: " . $stmt->error;
    }

    // Close the statement
    $stmt->close();
}

// Close connection
$conn->close();
?>
