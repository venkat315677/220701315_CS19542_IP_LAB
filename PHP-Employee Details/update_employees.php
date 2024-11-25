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

// Fetch employee details
$sql = "SELECT * FROM EMPDETAILS";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // Start the table
    echo "<table border='1'>
            <tr>
                <th>EmpID</th>
                <th>Name</th>
                <th>Designation</th>
                <th>Department</th>
                <th>Date of Joining</th>
                <th>Salary</th>
            </tr>";
    
    // Output data of each row
    while($row = $result->fetch_assoc()) {
        echo "<tr>
                <td>" . $row["EMPID"] . "</td>
                <td>" . $row["ENAME"] . "</td>
                <td>" . $row["DESIG"] . "</td>
                <td>" . $row["DEPT"] . "</td>
                <td>" . $row["DOJ"] . "</td>
                <td>" . $row["SALARY"] . "</td>
              </tr>";
    }
    echo "</table>";
} else {
    echo "0 results";
}

// Close connection
$conn->close();
?>
